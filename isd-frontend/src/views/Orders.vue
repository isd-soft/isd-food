<template >

<div>
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary" >History</h6>
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
          <tr>
            <td>Test</td>
            <td>Test</td>
            <td>Test</td>
            <td>Test</td>
            <td>Test</td>
          </tr>
          {{userHistory}}
          <tr v-for="order of userHistory" :key="order.id">
            <td>{{order.menu}}</td>
            <td>{{order.id}}</td>
            <td>{{order.id}}</td>
            <td>{{order.id}}</td>
            <td>{{order.id}}</td>
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
       userHistory:[]
     }
   },
  methods:{
   getHistoryUser(){
     api.getHistory().then(r => {this.userHistory = r.data;console.log(r.data)})
   }
  }
};

</script>

<style>
</style>