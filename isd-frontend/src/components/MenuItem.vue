<template>
    <div class="item">
         <img src ="" alt="img">
         <p>Name: {{product_data.name}}</p>
         <p>Provider: {{product_data.provider.name}}</p>
            <v-radio-group class="radio-group" v-model="role" id="roles" row>
            <span class="pr-3">Type:</span>
            <v-radio 
                default
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
         <p>Items:</p>

            <li v-for="item in product_data.menuTypes[1].items" :key="item.name">
            {{ item.name }}
            </li>

         <p>Delivery price: {{product_data.provider.deliveryPrice}} mdl.</p>
          <p>Price: {{product_data.menuTypes[0].price}} mdl.</p>
          <p>Total price: {{Number(product_data.provider.deliveryPrice) + Number(product_data.menuTypes[0].price)}}  mdl.</p>
         <br>
         <v-btn rounded color="warning">Order</v-btn>
      </div>
</template>

<script>
export default {
    name: "menuItem",

props:{
    product_data:{
        type: Object,
        default() {
            return{}
        }
    },
}
}
</script>
<style lang = "scss">
.item{
    flex-basis: 25%;
    box-shadow: 0 0 8px 0;
    padding: 16px;
    margin-bottom: 8px;
    border-radius: 30px;
}
</style>