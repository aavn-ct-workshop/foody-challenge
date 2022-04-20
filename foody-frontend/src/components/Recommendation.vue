<template>
  <h2 class="mb-5">Recommentdations</h2>
  <div class="px-3 mx-3 d-flex w-100 p-grid p-jc-center p-mt-5">
    <a
      v-for="product in products"
      :key="product.id"
      @click="$router.push('/merchant/' + product.id)"
      class="mx-3 pointer food-card d-flex flex-column p-col-2 p-pointer"
    >
      <img width="250" height="156" :src="product.img" alt="Picture" />
      <span>
        {{ product.name }}
      </span>
    </a>
  </div>
</template>

<script>
import { getMerchants } from '../assets/js/services/backend'
export default {
  name: "Recommentdation",
  data() {
    return {
      products: [],
    };
  },
  mounted() {
    getMerchants().then(products => this.products = products);
  },
  
  methods: {
  },
};
</script>

<style>
.grid-container {
  display: grid;
  grid-template-columns: auto auto auto;
  padding: 10px;
}
.grid-item {
  background-color: rgba(255, 255, 255, 0.8);
  border: 1px solid rgba(0, 0, 0, 0.109);
  padding: 20px;
  font-size: 30px;
  text-align: center;
}
.right-col {
  grid-column: 3;
  grid-row: 1 / span 2;
}

</style>
