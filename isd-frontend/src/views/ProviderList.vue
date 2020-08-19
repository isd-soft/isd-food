<template>
<div>
  <div class="row container" >
    <div class="col-xl-12 col-md-12 mb-4">
      <div class="card border-left-warning shadow h-100 py-1">
        <div class="card-body">
          <h3 class="mb-3">Providers</h3>
          <table  class="table table-bordered"  width="100%" cellspacing="0">
            <thead>
            <tr>


              <th>Provider name</th>
              <th>Contact</th>
              <th>Delivery price</th>
              <th>active</th>
              <th>Edit</th>
              <th class="text-center">Save</th>

            </tr>
            </thead>

            <tbody v-for="provider of providers" :key="provider.id" >
            <tr>
              <td><input type="text" style="width: 100%; outline: none; color: grey" v-model="provider.name" required ></td>
              <td><input type="number" style="width: 100%; outline: none;color: grey" v-model="provider.contactInfo" required></td>
              <td><input type="text" style="width: 100%; outline: none;color: grey" v-model="provider.deliveryPrice" required></td>
              <td>
                <input type="checkbox"  style="width: 100%; outline: none;color: grey" v-model="provider.active" required>
              </td>
              <td class="text-center">
                <router-link style="color: grey" :to ="{path: '/editProvider'}" ><i class="fas fa-user-edit"></i></router-link>
              </td>
              <td class="text-center">
              <button style="outline: none" @click="editProvider(provider.id,provider.name,provider.contactInfo,provider.deliveryPrice,provider.active)"  onclick="window.location.reload();" >
                <i class="fas fa-save" style="font-size: 17px"></i>
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
      if(price < 0)
        alert("Price cant be smaller than 0");
      else if(contact.length < 8)
        alert("Number cant be smaller length 8");
      else
        api.editProvider(id,name,contact,price,active);

    }
  },

  beforeCreate() {
    api.getAllProviders().then(r => {this.providers = r.data;console.log(r.data)})
  }
}
</script>

<style scoped>
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

input[type=number] {
  -moz-appearance:textfield;
}
</style>