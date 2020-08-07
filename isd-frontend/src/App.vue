<template>

  <v-app>
    <router-view />
  </v-app>
</template>

<script>
import "./css/app.css";

export default {
  name: "App",

  components: {

  },

  data: () => ({
    //
  })
};
</script>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 20px;
}

#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
     color: orange;
    }
  }
}
</style>