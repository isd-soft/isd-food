<template>
<div class="menu">
  <div class="text-center">
    <h1>Menus</h1>
    <v-btn @click="callMenuApi()" color="warning">Show menu</v-btn>
  </div>
<br>
<br>
 <!--<v-carousel
    cycle
    height="400"
    hide-delimiter-background
    show-arrows-on-hover
  >
    <v-carousel-item
     v-for="product in products"
     :key="product.name"
    >
    <MenuItem
     :product_data="product"
     />

    </v-carousel-item>
  </v-carousel>-->

     <div class="menu_list">
     <MenuItem
     v-for="product in products"
     :key="product.name"
     :product_data="product"
     />
     </div>




<!--<div class="container-fluid">
<div class="row">
  <div class="col-md-3 col-lg-5 mb-3" style="background: red; border-radius:40px">
    as
  </div>
  <div class="col-auto"></div>
    <div class="col-md-3 col-lg-5 mb-3" style="background: red; border-radius:40px">
    as
  </div>
  <div class="col-auto"></div>
    <div class="col-md-3 col-lg-5 mb-3" style="background: red; border-radius:40px">
    as
  </div>
  <div class="col-auto"></div>
    <div class="col-md-3  col-lg-5 mb-3" style="background: red; border-radius:40px">
    as
  </div>
  <div class="col-auto"></div>
    <div class="col-md-3 col-lg-5 mb-3" style="background: red; border-radius:40px">
    as
  </div>
  <div class="col-auto"></div>
  
  
</div>    
</div>-->
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
        errors: [],
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