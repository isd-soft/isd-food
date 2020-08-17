<template>
  <div class=" col-md-4 col-sm-6 mb-4">
    <div class="card border-left-warning shadow h-100 py-2">
      <div class="card-body">
        <div class="row no-gutters align-items-center">
          <div class="col mr-2">
            <div class="text-xs font-weight-bold text-warning ml-1 text-uppercase mb-1" >{{product_data.name}}</div>
            <div class="h5 mb-0 font-weight-bold text-gray-800" style="font-size: 12px">
              <div class="ml-1">
                <p>Provider : {{product_data.provider.name}}</p>
                <div>
                  <img src="https://www.bbcgoodfood.com/sites/default/files/recipe-collections/collection-image/2013/05/chorizo-mozarella-gnocchi-bake-cropped.jpg" class="image" alt="">
                </div>
                <v-app style="height: auto; max-height: 230px">
                  <v-radio-group class = "ml-1" v-model="type" id="type" :mandatory="false" style="color: orange !important" row>
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
                        <v-card-actions class="justify-center" >
                          <v-btn style="border-radius: 5px !important;"
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

                        <v-card-text class="mt-2">
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
                </v-app>
              </div>
            </div>
          </div>
        </div>
      </div>
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
        api.createOrder(1, this.product_data.menuTypes[this.type_id].id).then(response => {
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
    border-radius: 30px;
    color: black;
    height: 500px;
}
.image{
  background-size: cover;
  width: 100%;
  border-radius: 4px;

}
</style>