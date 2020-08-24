<template>
<div>
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-warning">Orders</h6>
      <ul class="nav d-flex mb-3 mt-4" id="pills-tab" role="tablist">
        <li class="nav-item mr-3" role="presentation">
          <a class="btn btn-outline-warning active " id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">Current orders</a>
        </li>
        <li class="nav-item" role="presentation">
          <a class="btn btn-outline-warning" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false">Orders history</a>
        </li>
      </ul>
    </div>
    <div class="card-body">
      <div  class="table-responsive">
        <div  class="tab-content" id="pills-tabContent">
          <!---Current--->
          <div  class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
            <confirmationDialog :action-button="'Agree'" :method="deleteOrder" :title="title" :message="message" :dialog1.sync="dialog1"/>
            <table v-if="!this.isEmptyCurrent()" class="table table-bordered"  width="100%" cellspacing="0">
              <thead>
              <tr>
                <th>Provider</th>
                <th>Menu</th>
                <th>Menu type</th>
                <th>Data</th>
                <th>Price</th>
                <th>Delete</th>
              </tr>
                </thead>
                <tbody v-for="order of userHistory" :key="order.id">
                <tr v-if="!order.ordered">
                  <td>{{order.providerName}}</td>
                  <td>{{order.menuName}}</td>
                  <td>{{order.menuType}}</td>
                  <td>{{order.date}}</td>
                  <td>{{order.price}} lei</td>
                  <td class="text-center">
                     <button type="submit" @click="openDialog(order.id)">
                       <v-icon  data-toggle="modal"  data-target="#exampleModal">fas fa-trash-alt</v-icon>
                     </button>
                  </td>
                </tr>
                </tbody>
            </table>
            <div v-else class="text-center">
              <h1 class="mt-1">Empty </h1>
            </div>
          </div>

          <!---Orders History--->
          <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
            <table v-if="this.isEmptyHistory()" class="table table-bordered"  width="100%" cellspacing="0">
              <thead>
              <tr>
                <th>Provider</th>
                <th>Menu</th>
                <th>Menu type</th>
                <th>Data</th>
                <th>Price</th>
              </tr>
              </thead>
              <tbody v-for="order of userHistory" :key="order.id">
              <tr v-if="order.ordered">
                <td>{{order.providerName}}</td>
                <td>{{order.menuName}}</td>
                <td>{{order.menuType}}</td>
                <td>{{order.date}}</td>
                <td>{{order.price}} lei</td>
              </tr>
              </tbody>
            </table>
            <div v-else class="text-center">
              <h1 class="mt-1">Empty History</h1>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>

import api from "@/components/backend-api.js"
import confirmationDialog from "../components/confirmationDialog";

export default {
  components: {
    confirmationDialog,
  },
  name: "Home",
   data (){
     return{
       currentId: null,
       title: "Confirmation",
       message: "Do you really want to delete this user?",
       dialog1: false,
       userHistory:[],
       getHistory: false,
     }
   },
  methods:{
    openDialog(id){
      this.dialog1 = true
      this.currentId = id
      console.log(this.currentId)
    },

    isEmptyCurrent(){
      for(let i = 0; i < this.userHistory.length;i++)
        if(!this.userHistory[i].ordered)
          return false
      return true
    },
    isEmptyHistory(){
      for(let i = 0; i < this.userHistory.length;i++)
        if(this.userHistory[i].ordered)
          return true
      return false
    },
    deleteOrder(){
      api.deleteOrder(this.currentId)
    }
  },
  beforeCreate() {
    api.getHistory().then(r => {this.userHistory = r.data.content;console.log(r.data)})
  }
};

</script>

<style>

</style>