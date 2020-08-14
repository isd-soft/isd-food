<template>
  <div class="row">
    {{this.callOnce()}}
    <MenuItem
        v-for="product in products"
        :key="product.name"
        :product_data="product"/>

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
        get: false,
        alignments: [
        'start',
        'center',
        'end',
      ],
      }
    },
    methods: {
      // Fetches posts when the component is created.
      callOnce(){
        if(!this.get){
          this.callMenuApi();
          this.get = true
        }
      },
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
      next () {
        this.onboarding = this.onboarding + 1 === this.length
          ? 0
          : this.onboarding + 1
      },
      prev () {
        this.onboarding = this.onboarding - 1 < 0
          ? this.length - 1
          : this.onboarding - 1
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
.rounded-card{
    border-radius:500px;
}
</style>