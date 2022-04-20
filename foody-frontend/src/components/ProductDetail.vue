<template>
  <div id="card-detail">
    <Card>
      <template #header>
        <img :src="imgUrl">
      </template>
      <template #title>
        {{ product.name }}
      </template>
      <template #content>
        <p>{{product.desc}}</p>
      </template>
      <template #footer> 
          <div class="product-price">${{product.price}}</div>
          <br>
          <AddToCart :product="product"/>
      </template>
    </Card>
  </div>
</template>

<script>
import AddToCart from './AddToCart.vue';

export default {
  name: "ProductDetail",
  components: {
    AddToCart
  },
  props: {
    id: String,
  },
  data() {
    return {
      product: this.$store.getters['products/getProductById'](this.id),
      imgUrl: this.$store.getters['products/getImgUrl'](this.id),
    };
  }
};
</script>

<style scoped>
  #card-detail {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: -50px;
  }

  #card-detail .p-card {
    width: 45em;
  }

  #card-detail img {
    display: block;
    margin-left: auto;
    margin-right: auto;
    padding: 15px 15px;
    height: 300px;
    width: unset !important;
  }

  #card-detail .product-price {
    font-size: 40px;
    font-weight: bold;
    color: rgb(36, 196, 134);
  }

  .p-card-content p {
    text-align: left;
  }
</style>
