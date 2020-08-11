<template>
    <div class="item">
         <img src ="" alt="img">
         <p>Name: {{product_data.name}}</p>
         <p>Provider: {{product_data.provider.name}}</p>
            <v-radio-group class="radio-group" v-model="role" id="roles" row>
            <span class="pr-3">Type:</span>
            <v-radio label="M" color="warning" value="M"></v-radio>
            <v-radio
              label="S"
              color="warning"
              value="S"
            ></v-radio>
          </v-radio-group>
         <p>Ingridients:</p>
         <p>Delivery price: {{product_data.provider.deliveryPrice}} mdl.</p>
          <p>Price: {{product_data.price}} mdl.</p>
          <p>Total price:</p>
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
    }
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