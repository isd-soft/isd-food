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
             M
             <br>
             {{check_menu('M')}}
             {{type_id}}
         </div>
         <div v-else>
            S
            <br>
            {{check_menu('S')}}
            {{type_id}}
        </div>


         <p>Items:</p>

            <li v-for="item in product_data.menuTypes[(type_id-1)].items" :key="item.name">
            {{ item.name }}
            </li>
    <br>
         <p>Delivery price: {{product_data.provider.deliveryPrice}} mdl.</p>
          <p>Price: {{product_data.menuTypes[(type_id-1)].price}} mdl.</p>
          <p>Total price: {{Number(product_data.provider.deliveryPrice) + Number(product_data.menuTypes[(type_id-1)].price)}}  mdl.</p>
         <br>
        <v-card-actions class="justify-center">
         <v-btn rounded color="warning" align="center">Order</v-btn>
        </v-card-actions>
      </div>
</template>

<script>
export default {
    name: "menuItem",
    data () {
      return {
        type: 'M',
        type_id: 0,
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
            if(this.type === type)
                this.product_data.menuTypes.forEach(element => {
                    if(element.type === type){
                    console.log(element.id)
                    this.type_id = element.id
                    }
                });
        }
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