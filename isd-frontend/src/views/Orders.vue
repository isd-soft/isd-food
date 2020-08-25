<template>
  <v-app>
    <div>
      <div class="card shadow mb-4">
        <div class="card-header py-3">
          <ul class="nav d-flex mb-3 mt-4" id="pills-tab" role="tablist">
            <li class="nav-item mr-3" role="presentation">
              <button
                  name="current"
                  v-bind:class="{ active: $store.getters.userOrdersType === 'current' }"
                  class="btn btn-outline-warning orders-type-btn"
                  @click="switchOrdersType"
              >
                Current orders
              </button>
            </li>
            <li class="nav-item" role="presentation">
              <button
                  name="history"
                  v-bind:class="{ active: $store.getters.userOrdersType !== 'current' }"
                  class="btn btn-outline-warning orders-type-btn"
                  @click="switchOrdersType"
              >
                Orders history
              </button>
            </li>
            <li class="nav-item " role="presentation">
            </li>
          </ul>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <div class="tab-content" id="pills-tabContent">
              <!---Current--->
              <div
                  class="tab-pane fade show active"
                  id="pills-home"
                  role="tabpanel"
                  aria-labelledby="pills-home-tab"
              >
                <OrderTable/>
              </div>
              <!---Orders History--->
              <div
                  class="tab-pane fade"
                  id="pills-profile"
                  role="tabpanel"
                  aria-labelledby="pills-profile-tab"
              >
              </div>
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
  },
  beforeCreate() {
    this.$store.dispatch("getUserCurrentOrders");
    this.$store.dispatch("getUserOrdersHistory");
  }
};
</script>

<style></style>