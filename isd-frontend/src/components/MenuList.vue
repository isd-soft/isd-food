<template>
<div class="menu">
  <div class="text-center">
    <h1>Menus</h1>
    <v-btn @click="callMenuApi()" color="warning">Show menu</v-btn>
  </div>
<br>
<br>
     <div class="menu_list">
     <MenuItem
     v-for="product in products"
     :key="product.name"
     :product_data="product"
     />

</div>
<br>
<br>
<div class="text-center">
    <v-dialog
      v-model="dialog"
      width="500"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="warning"
          dark
          v-bind="attrs"
          v-on="on"
        >
          Confirm order
        </v-btn>
      </template>
      <v-card>
        <v-card-title class="headline grey lighten-2">
          Order confirmed!
        </v-card-title>

        <v-card-text>
          Enjoy your food! <br>
          Isd-food with love🧡
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="warning"
            text
            @click="dialog = false"
          >
            Excelent
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
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
        errors: [],
        dialog: false,
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

