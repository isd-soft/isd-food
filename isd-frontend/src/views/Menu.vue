<template>
  <v-app class="home">
    <MenuList/>
  </v-app>
</template>

<script>
import MenuList from '../components/MenuList.vue'
export default {
  name: "Home",
    components: {
    MenuList,
  },
};
</script>

<style>
.menu{

  margin: 0 auto;
}
</style>