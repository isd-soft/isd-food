<template>
<!--  <v-app class="home">-->
<!--    <MenuList/>-->
<!--  </v-app>-->
  <!-- Page Heading -->
<div class="container-fluid">
     <MenuList/>
</div>
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