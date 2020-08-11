<template>
<div class="menu">
    <h1>Menus</h1>
    <v-btn @click="callMenuApi()" small color="warning">Action</v-btn>
<br>
<br>
     <div class="menu_list">
     <MenuItem
     v-for="product in products"
     :key="product.name"
     :product_data="product"
     />

</div>
</div>
</template>

<script>
import api from './backend-api';
import MenuItem from './MenuItem.vue'

  export default {
    name: 'menu',

  components: {
    MenuItem,
  },

    data () {
      return {
        msg: 'Display some info from spring',
        products: [],
        menu_types: [],
        helloResponse: [],
        errors: []
      }
    },
    methods: {
      // Fetches posts when the component is created.
      callMenuApi () {
        api.getMenu().then(response => {
            this.products = response.data;
            console.log(response.data)
        })
        .catch(error => {
          this.errors.push(error)
        })
      },
        callHelloApi () {
        api.Hello().then(response => {
            this.helloResponse = response.data;
            console.log(response.data)
        })
        .catch(error => {
          this.errors.push(error)
        })
      },
      callMenuType(){
        api.getMenuType().then(response => {
            this.menu_types = response.data;
            console.log(response.data)
        })
        .catch(error => {
          this.errors.push(error)
        })
      },
    }
  }

</script>

<style lang = "scss">
.menu{
  &_list{
    margin: 0 auto;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    align-items: center;
  }
}
</style>

