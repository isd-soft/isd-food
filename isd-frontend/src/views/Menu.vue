<template>
  <div class="home">
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
  max-width: 900px;
  margin: 0 auto;
}

</style>