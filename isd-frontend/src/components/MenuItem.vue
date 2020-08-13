<template>
    <div class="item">
    <div >
            <v-img  class="image" src="https://www.bbcgoodfood.com/sites/default/files/recipe-collections/collection-image/2013/05/chorizo-mozarella-gnocchi-bake-cropped.jpg" aspect-ratio="1.7"></v-img>
    </div>
            <br>
         <p>Name: {{product_data.name}}</p>
         <p>Provider: {{product_data.provider.name}}</p>

            <v-radio-group class="radio-group" v-model="type" id="type" :mandatory="false" row>
            <span class="pr-3">Type:</span>
            <v-radio
                label="M"
                color="warning" 
                value="M">
            </v-radio>
            <v-radio
              label="S"
              color="warning"
              value="S"
            ></v-radio>
          </v-radio-group>
       
         <div v-if= "type === 'M'">
             <div v-for="item in product_data.menuTypes" :key="item.type">
                 <div v-if= "item.type === 'M'">
                 </div>
             </div>
             {{check_menu('M')}}
         </div>
         <div v-else>
            {{check_menu('S')}}
        </div>

         <p>Items:</p>

            <li v-for="item in product_data.menuTypes[(type_id)].items" :key="item.name">
            {{ item.name }}
            </li>
    <br>
        <!-- <p>Delivery price: {{product_data.provider.deliveryPrice}} mdl.</p>
          <p>Price: {{product_data.menuTypes[(type_id)].price}} mdl.</p>
          <p>Total price: {{Number(product_data.provider.deliveryPrice) + Number(product_data.menuTypes[(type_id)].price)}}  mdl.</p>
         <br>-->

        <div class="text-center">
    <v-dialog
      v-model="dialog"
      width="500"
    >

      <template v-slot:activator="{ on, attrs }">
          <v-card-actions class="justify-center">
         <v-btn 
         @click="makeOrder()" 
          v-bind="attrs"
          v-on="on"
         large 
         rounded
          color="warning" 
          align="center">
            Order
          </v-btn>
        </v-card-actions>   

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

export default {
    name: "menuItem",
    data () {
      return {
        type: 'M',
        type_id: 0,
        dialog: false,
      }
    },
props:{
    product_data:{
        type: Object,
        default() {
            return{}
        }
    },
},
    methods: {
        check_menu(type){
            var i = 0;
            if(this.type === type)
                this.product_data.menuTypes.forEach(element => {
                    if(element.type === type){
                    console.log(i)
                    this.type_id = i
                    }
                    else i++
                });
        },

      makeOrder() {
        api.createUser(1, this.product_data.id).then(response => {
            this.response = response.data;
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
    }
}
</script>
<style lang = "scss">
.item{
    flex-basis: 25%;
    box-shadow: 0 0 8px 0 orange;
    padding: 16px;
    margin-bottom: 8px;
    border-radius: 30px;
}
.image{
    border-radius: 15px;
    box-shadow: 0 0 8px 0 orange;
}
</style>