<template>
  <div class="p-d-flex p-flex-column">
    <div class="p-mb-3"><strong>Shopping Cart</strong></div>
    <div class="p-d-flex w-25 mx-auto p-flex-column wrapper">
      <template v-if="allProducts?.length">
        <div class="p-d-flex p-flex-column list-of-products">
          <div
            class="product-item p-mb-2"
            v-for="(product, index) in allProducts"
            :key="product.id"
          >
            <div class="name">{{ product.name }}</div>
            <div>{{ product.quantity }}</div>
            <div class="p-text-right">
              {{ productPrice(product) }}
            </div>
            <div>
              <RemoveFromCart :productIndex="index" />
            </div>
          </div>
        </div>
        <hr class="devider" />
        <div class="d-flex justify-content-between align-items-center">
          <label class="mr-1" for="address">Address:</label>
          <input id="address" class="address form-control" type="text" v-model="deliveryAddress"/>
        </div>
        <div class="d-flex p-jc-center total">
          <div class="d-flex p-mr-3"><strong>Total:</strong></div>
          <div class="d-flex w-100 justify-content-center">{{ total }}</div>
        </div>

        <div class="mt-4">
          <button label="Checkout" class="btn btn-success" @click="checkout()">Checkout</button>
        </div>
      </template>
      <template v-else>
        <div>Please buy somethings</div>
      </template>
    </div>
  </div>
</template>

<script>
import RemoveFromCart from "./RemoveFromCart.vue";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  name: "ShoppingCart",
  components: {
    RemoveFromCart,
  },

  data() {
    return {
      allProducts: this.$store.getters["cart/getAllProducts"],
      deliveryAddress: 'anywhere on earth',
      connected: false
    };
  },

  mounted() {
    this.connect()
  },

  methods: {
    productPrice(product) {
      const number = product.quantity
      return number * product.price;
    },
    async checkout() {
      const order = { 
        hostId: 'ab893719-f74c-4ab0-b5b3-096ba5102aa5',
        hostName: 'Nguyen Van A',
        deliveryAddress: this.deliveryAddress,
        orderItems: this.allProducts 
      };
      console.log('submitting to', order.hostName);

      const opts = {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        
        body: JSON.stringify(order)
      };
      const url = 'http://localhost:80/orders';
      try {
        const data = await fetch(url, opts);
        if (data.status == 201) {
          alert('Your order is submitted, it will be deliver in next minutes!!!');
          this.publishOrder(order)
          console.log('Order submitted', order);
          this.$router.push('/driver/orders/')
        } else {
          alert('Your order can not process at this time, please try again!!!');
        }
      } catch (e) {
        alert('Error: Technical issue, please try again!')
      }
    },
    publishOrder(order) {
      const msg = JSON.stringify(order)
      console.log("Send message:" + msg);
      if (this.stompClient && this.stompClient.connected) {
        this.stompClient.send("/app/hello", msg, {});
      }
    },
    connect() {
      this.socket = new SockJS("http://localhost:80/notifier");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe("/delivery", tick => {
            console.log(tick);
          });
        },
        error => {
          console.log(error);
          this.connected = false;
        }
      );
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
    tickleConnection() {
      this.connected ? this.disconnect() : this.connect();
    }
  },
  computed: {
    total() {
      const total = this.allProducts.reduce((currentValue, product) => {
        const number =
          product.quantity && product.quantity > 0 ? product.quantity : 1;
        return currentValue + number * product.price;
      }, 0);
      return total;
    },
  },
};
</script>

<style scoped>
.wrapper {
  padding: 1em;
  border-radius: 8px;
  /* background: rgb(241,230,246); */
  border: 1px solid rgb(65, 184, 131);
  padding-right: 20px;
}

.list-of-products {
  max-height: 300px;
  overflow: auto;
  margin-right: -10px;
  padding-right: 10px;
}

.product-item {
  display: grid;
  grid-template-columns: 1fr 30px 80px 20px;
  column-gap: 10px;
}

.product-item > * {
  text-align: right;
}

.product-item > .name {
  text-align: left;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.devider {
  height: 1px;
  width: 100%;
  background: rgb(65, 184, 131);
}
</style>