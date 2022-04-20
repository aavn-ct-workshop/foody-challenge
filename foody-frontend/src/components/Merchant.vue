<template>
  <div class="p-d-flex p-jc-center">
    <div v-if="merchant" class="p-grid pricing-table-price">
      <div class="merchant">
        <img id="image" :src="merchant.img" alt="Image" class="merchant-logo" />
        <span id="merchant-name" class="p-m-4 mt-3">{{ merchant.name }}</span>
      </div>
      <hr/>
      <div class="d-flex justify-content-center flex-wrap">
        <div v-for="product in merchant.foods" :key="product.id" class="p-col mx-3">
          <ProductItem :product="product"></ProductItem>
        </div>
      </div>
    </div>
    <div v-else>loading ...</div>
    <ShoppingCart class="mt-5 shopping-cart p-col-3" />
  </div>
</template>

<script>
import ProductItem from "./ProductItem.vue";
import ShoppingCart from "./ShoppingCart.vue";

import { getMerchant } from '../assets/js/services/backend';

export default {
  name: "Merchant",
  props: ["id"],
  components: {
    ProductItem,
    ShoppingCart,
  },
  data() {
    return {
      boughtProducts: this.$store.getters["cart/getAllProducts"],
      merchant: undefined
    };
  },
  mounted() {
    // fetch the data when the view is created and the data is
    // already being observed
    getMerchant(this.id).then(m => this.merchant = m)
  },
  
  methods: {
  },
  computed: {},
};
</script>

<style scoped>
.merchant-logo {
  align-self: auto;
  background-color: white;
  max-width: 20rem;
  max-height: 25rem;
}
#merchant-name {
  font-size: 2rem;
  color: burlywood;
  width: 100%;
}
</style>
