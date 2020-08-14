<template >

<div>
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-warning" >History</h6>
    </div>
    {{getOnce()}}
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
            <td>{{order.providerName}}</td>
            <td>{{order.menuName}}</td>
            <td>{{order.menuType}}</td>
            <td>{{order.date}}</td>
            <td>{{order.price}} lei</td>
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
  methods:{
   getHistoryUser(){
     api.getHistory().then(r => {this.userHistory = r.data.content;console.log(r.data)})
   },
    getOnce(){
     if (!this.getHistory){
       this.getHistoryUser()
       this.getHistory = true
     }
    }
  }
};

</script>

<style>
</style>