<template>
  <div class="p-d-flex">
    <div class="p-col-8 p-d-flex p-flex-column p-ai-end">
      <router-view></router-view>
    </div>
    
    <ShoppingCart class="shopping-cart p-col-3"/>
  </div>
</template>

<script>
import ShoppingCart from "./ShoppingCart";

export default {
  name: "Product",
  components: {
    ShoppingCart
  },
  data() {
    return { };
  },
  methods: {
  },
  computed: {
  }
};
</script>

<style scoped>
@import "../assets/css/styles.css";

  .shopping-cart {
    position: fixed;
    right: 2em;
    height: min-content;
    padding: 20px;
  }
</style>
