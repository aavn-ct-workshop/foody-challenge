<template>
  <div id="navbar">
    <span id="logout">
      <button icon="pi pi-sign-out" @click="logout">Logout</button>
    </span>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  methods: {
    logout() {
      let { redirectUri } = process.env.VUE_APP_ROOT_URI;
      location.href = redirectUri;
    },
  },
};
</script>
<style scoped>
#navbar {
  display: flex;
  justify-content: flex-end;
}
</style>
