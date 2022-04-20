<template>
  <Banner msg="FOOD-ĐÂY" />
  <!-- <NavBar/> -->
  <router-view></router-view>
</template>

<script>
import Banner from "./components/Banner.vue";
export default {
  name: "App",
  components: {
    Banner
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #aaa7a7;
  margin-top: 20px;
}
</style>
