<template>
<div>
  <div class="row container" >
    <div class="col-xl-12 col-md-12 mb-4">
      <div class="card border-left-warning shadow h-100 py-1">
        <div class="card-body">


          <table  class="table table-bordered"  width="100%" cellspacing="0">
            <thead>
            <tr>


              <th>Provider name</th>
              <th>Contact</th>
              <th>Delivery price</th>
              <th>active</th>
              <th class="text-center">Edit</th>

            </tr>
            </thead>

            <tbody v-for="provider of providers" :key="provider.id" >
            <tr>
              <td><input type="text" style="width: 100%; outline: none; color: grey" v-model="provider.name" required></td>
              <td><input type="text" style="width: 100%; outline: none;color: grey" v-model="provider.contactInfo" required></td>
              <td><input type="text" style="width: 100%; outline: none;color: grey" v-model="provider.deliveryPrice" required></td>
              <td>
                <div class="custom-control custom-switch d-flex justify-content-center">
                  <input type="checkbox" class="custom-control-input" id="customSwitches" style="width: 100%; outline: none;color: grey" v-model="provider.active" required>
                  <label class="custom-control-label" for="customSwitches"></label>
                </div>
<!--                <input type="checkbox" style="width: 100%; outline: none;color: grey" v-model="provider.active" required></td>-->
              <td class="text-center">
              <button style="outline: none" @click="editProvider(provider.id,provider.name,provider.contactInfo,provider.deliveryPrice,provider.active)" >
                <i class="fas fa-user-edit"></i>
              </button>
              </td>

            </tr>

            </tbody>


          </table>


        </div>
      </div>
    </div>
  </div>

</div>
</template>

<script>
import api from "@/components/backend-api.js"

export default {
  name: "ProviderList",
  data (){
    return{
      providers:[],
      dialog: false,

    }
  },
  methods:{
    editProvider(id,name,contact,price,active){
      api.editProvider(id,name,contact,price,active);
    }
  },

  beforeCreate() {
    api.getAllProviders().then(r => {this.providers = r.data;console.log(r.data)})
  }
}
</script>

<style scoped>

</style>