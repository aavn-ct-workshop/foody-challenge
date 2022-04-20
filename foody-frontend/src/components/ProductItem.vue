<template>
  <div class="food-card product-container pricing-table-price">
    <img :src="product.img" class="product-image" />
    <div class="product-name">{{ product.name }}</div>
    <div class="product-price">${{ product.price }}</div>
    <div class="product-add-cart p-mt-3">
      <AddToCart :product="product" />
    </div>
  </div>
</template>
<script>
import AddToCart from "./AddToCart.vue";

export default {
  name: "ProductItem",
  components: {
    AddToCart,
  },
  props: {
    product: Object,
  },
  data() {
    return {};
  },
};
</script>
<style>
@import "../assets/css/styles.css";

.product-container {
  max-width: 100%;
  text-align: center;
  font-size: 18px;
  padding: 20px 15px;
  height: 100%;
  min-width: 280px;
  width: max-content;
}
.product-container .product-image {
  display: block;
  margin-left: auto;
  margin-right: auto;
  padding: 15px 0px;
  height: 120px;
  max-width: 100%;
  border-radius: 4px;
}
.product-container .product-name {
  font-size: 22px;
  font-weight: bold;
}
.product-container .product-price {
  font-size: 20px;
  color: rgb(36, 196, 134);
}
</style>