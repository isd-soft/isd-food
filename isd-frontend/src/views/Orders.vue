<template >

<div>
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-warning" >History</h6>
    </div>
    <div class="card-body">
      <div class="table-responsive">
        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
          <thead>
          <tr>
            <th>Provider</th>
            <th>Menu</th>
            <th>Menu type</th>
            <th>Data</th>
            <th>Price</th>

          </tr>
          </thead>
          <tbody>


          <tr v-for="order of userHistory" :key="order.id">
<!--            <td>{{order.providerName}}</td>-->
<!--            <td>{{order.menuName}}</td>-->
<!--            <td>{{order.menuType}}</td>-->
<!--            <td>{{order.date}}</td>-->
<!--            <td>{{order.price}} lei</td>-->

            <td v-if="order.providerName == null">Default</td>
            <td v-else>{{order.providerName}}</td>

            <td v-if="order.menuName != null">{{order.menuName}}</td>
            <td v-else>Default</td>

            <td v-if="order.menuType != null">{{order.menuType}}</td>
            <td v-else>Default</td>

            <td v-if="order.date != null">{{order.date}}</td>
            <td v-else>Default</td>

            <td v-if="order.price == null">Default</td>
            <td v-else>{{order.price}}</td>



          </tr>




          </tbody>
        </table>
      </div>
    </div>
  </div>

</div>


</template>

<script>

import api from "@/components/backend-api.js"

export default {

  name: "Home",
   data (){
     return{
       userHistory:[],
       getHistory: false
     }
   },
  beforeCreate() {
    api.getHistory().then(r => {this.userHistory = r.data.content;console.log(r.data)})
  }
};

</script>

<style>
</style>