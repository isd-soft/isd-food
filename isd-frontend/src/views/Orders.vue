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
  </v-app>
</template>

<script>

import api from "@/components/backend-api.js";
import OrderTable from "@/components/OrderTable";

export default {
  components: {OrderTable},
  name: "Home",
  data() {
    return {
      userHistory: [],
      getHistory: false,
      ordersType: "current",
      currentOrders: [],
      historyOrders: [],
    };
  },
  methods: {
    isEmptyCurrent() {
      for (let i = 0; i < this.historyOrders.length; i++)
        if (!this.historyOrders[i].ordered) return false;
      return true;
    },
    isEmptyHistory() {
      for (let i = 0; i < this.historyOrders.length; i++)
        if (this.historyOrders[i].ordered) return true;
      return false;
    },
    deleteOrder(id) {
      api.deleteOrder(id);
    },
    switchOrdersType(e) {
      this.label = "updated";
      this.$store.state.orders.userOrdersType = e.target.name;
    },

    deleteOrder(){
      api.deleteOrder(this.currentId)
    }

  beforeCreate() {
    this.$store.dispatch("getUserCurrentOrders");
    this.$store.dispatch("getUserOrdersHistory");
  }
};
</script>

<style></style>