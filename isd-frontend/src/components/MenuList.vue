<template>
<div class="menu">
  <div class="text-center">
    <h1>Menus</h1>
    <v-btn @click="callMenuApi()" color="warning">Show menu</v-btn>
  </div>
<br>
<br>
 <!-- <v-carousel
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
  </v-carousel> -->
  <!-- <v-carousel
    cycle
    height="400"
    hide-delimiter-background
    show-arrows-on-hover
  >
    <v-carousel-item
     v-for="product in products"
     :key="product.name"
    >
          <div class="display-3">
            <MenuItem
              :product_data="product"
            />
          </div>

    </v-carousel-item>
  </v-carousel> -->

  <!-- <v-carousel hide-delimiters height="750" show-arrows-on-hover cycle>
    <v-carousel-item
     v-for="product in products"
     :key="product.name"
    >
      <v-row class="fill-height" align="center" justify="center">
        <div class="display-3 white--text"><MenuItem
              :product_data="product"
            /></div>
      </v-row>
    </v-carousel-item>
  </v-carousel> -->

  <!-- <v-carousel>
    <v-carousel-item
      v-for="product in products"
      :key="product.name"
      :product_data="product"
      reverse-transition="fade-transition"
      transition="fade-transition"
    ></v-carousel-item>
  </v-carousel> -->

<!-- <div id="header-carousel">
      <v-carousel style="height: 100%; width: 100%">
        <v-carousel-item style="height: 100%; width: 100%"
     v-for="product in products"
     :key="product.name">
          <v-jumbotron dark>
            <v-container fill-height fill-width>
              <v-layout align-center>
                <v-flex>
                  <span class="subheading"> <MenuItem
              :product_data="product"
            /></span>
                </v-flex>
              </v-layout>
            </v-container>
          </v-jumbotron>
        </v-carousel-item>
      </v-carousel>
    </div> -->

     <!-- <div class="menu_list">
     <MenuItem
     v-for="product in products"
     :key="product.name"
     :product_data="product"
     />
     </div> -->
     
    <!-- <v-row>
    <v-col cols="12" sm="3" md="4" v-for="product in products"
     :key="product.name" >
        <MenuItem :product_data="product" />
    </v-col>
    </v-row> -->
    <v-container fluid grid-list-xl>
    <v-row no-gutters
    :align="alignment"
          :justify="justify-content">
      <v-col
        v-for="product in products"
        :key="product.name"
        cols="12"
        sm="4"
      >
        <!-- <v-card shaped
          class="pa-4 mx-lg-auto ma-3 mx-5 rounded-card"
          color="white"
          width="250px"
        > -->
          <MenuItem :product_data="product" />
        <!-- </v-card> -->
      </v-col>
    </v-row>
  </v-container>
  <!-- <v-container
      v-for="align in alignments"
      :key="align"
    >
      <v-row
        :align="align"
        no-gutters
        style="height: 150px;"
      >
        <v-col
          v-for="product in products"
          :key="product.name"
        >
          <v-card
            class="pa-2"
            outlined
            tile
          >
            <MenuItem :product_data="product" />
          </v-card>
        </v-col>
      </v-row>
    </v-container> -->




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

<!-- <v-card
    color="black"
    dark
    flat
    tile
  >
    <v-window v-model="onboarding">
      <v-window-item
        v-for="n in length"
        :key="`card-${n}`"
      >
        <v-card
          color="transparent"
          height="200"
        >
          <v-row
            class="fill-height"
            align="center"
            justify="center"
            tag="v-card-text"
          >
            <MenuItem :product_data="product"
/>
          </v-row>
        </v-card>
      </v-window-item>
    </v-window>

    <v-card-actions class="justify-space-between">
      <v-btn
        text
        @click="prev"
      >
        <v-icon>mdi-chevron-left</v-icon>
      </v-btn>
      <v-item-group
        v-model="onboarding"
        class="text-center"
        mandatory
      >
        <v-item
          v-for="n in length"
          :key="`btn-${n}`"
          v-slot:default="{ active, toggle }"
        >
          <v-btn
            :input-value="active"
            icon
            @click="toggle"
          >
            <v-icon>mdi-record</v-icon>
          </v-btn>
        </v-item>
      </v-item-group>
      <v-btn
        text
        @click="next"
      >
        <v-icon>mdi-chevron-right</v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>-->
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
        alignments: [
        'start',
        'center',
        'end',
      ],
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