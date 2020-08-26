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
          <div class="tab-content" id="pills-tabContent">
            <!---Current--->
            <div
                class="tab-pane fade show active"
                id="pills-home"
                role="tabpanel"
                aria-labelledby="pills-home-tab"
            >
              <div class="row justify-content-center" v-show="$store.state.orders.userOrdersType==='history'">
                <v-col cols="2">
                  <DatePicker label="Date from" picker-type="date" :initial-date="dateFromPicker"
                              @dateChanged="setDateFrom"/>
                </v-col>
                <v-col cols="2">
                  <DatePicker label="Date to" picker-type="date" :initial-date="dateToPicker"
                              @dateChanged="setDateTo"/>
                </v-col>
                <v-col cols="2" class="p-0">
                  <v-select
                      v-model="selectedProviders"
                      :items="providers"
                      item-text="name"
                      item-disabled="notAvailable"
                      :menu-props="{ maxHeight: '400' }"
                      @change="getUserOrdersHistory"
                      label="Select providers"
                      multiple
                      persistent-hint
                  ></v-select>
                </v-col>
              </div>
              <OrderTable @deleteOrder="deleteOrder" @setSortColsDirection="setSortColsDirection"/>
              <Pagination v-show="this.$store.state.orders.userOrdersType==='history'" class="pb-15 pt-5"
                          @pageChanged="setPage" :page="page"
                          :totalPages="this.$store.state.orders.userOrdersHistory.totalPages"/>
            </div>

          </div>
        </div>
      </div>
    </div>
  </v-app>
</template>

<script>
import OrderTable from "@/components/OrderTable";
import DatePicker from "@/components/picker/DatePicker";
import Pagination from "@/components/Pagination";
import api from "@/components/backend-api";

export default {
  components: {OrderTable, DatePicker, Pagination},
  name: "Home",
  data() {
    return {
      userHistory: [],
      getHistory: false,
      currentOrders: [],
      historyOrders: [],
      providers: [],
      selectedProviders: [],
      dateFromPicker: null,
      dateToPicker: null,
      sortByDate: null,
      sortByPrice: null,
      page: 1
    };
  },
  methods: {
    setPage(page) {
      if (page !== this.page)
        window.scrollTo({
          top: 0,
          left: 0,
          behavior: 'smooth'
        });
      this.page = page
      this.getUserOrdersHistory();
    },
    setDateFrom(date) {
      this.dateFromPicker = date;
      this.getUserOrdersHistory();
      this.getProviders();
    },
    setDateTo(date) {
      this.dateToPicker = date;
      this.getUserOrdersHistory();
      this.getProviders();
    },
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
    switchOrdersType(e) {
      this.$store.state.orders.userOrdersType = e.target.name;
    },
    getUserCurrentOrders() {
      this.$store.dispatch("getUserCurrentOrders");
    },
    getUserOrdersHistory() {
      let dateToParam = this.dateToPicker != null ? "&dateTo=" + this.dateToPicker : "";
      let dateFromParam = this.dateFromPicker != null ? "&dateFrom=" + this.dateFromPicker : "";
      let providersParam = this.selectedProviders.length > 0 ? "&providers=" + this.selectedProviders.join(",") : "";

      let sortParam = this.sortByDate != null ? "&sort=date," + this.sortByDate : "";
      sortParam += this.sortByPrice != null ? "&sort=mt.price," + this.sortByPrice : "";

      this.$store.dispatch("getUserOrdersHistory", {
        dateFrom: dateFromParam,
        dateTo: dateToParam,
        providers: providersParam,
        sort: sortParam,
        page: this.page - 1
      });
    },
    getProviders() {
      let dateToParam = this.dateToPicker != null ? "&dateTo=" + this.dateToPicker : "";
      let dateFromParam = this.dateFromPicker != null ? "?dateFrom=" + this.dateFromPicker : "";

      api.getAvailableProviders(dateFromParam, dateToParam).then(r => {
        this.providers = r.data;
      }).catch(e => {
        console.log(e)
      })
    },
    deleteOrder(orderId) {
      this.$store.dispatch("deleteUserOrder", orderId)
    },
    setSortColsDirection(col) {
      this[col.name] = col.direction;
      this.getUserOrdersHistory()
    }
  },
  beforeMount() {
    this.getUserCurrentOrders();
    this.getUserOrdersHistory();
    this.getProviders();
  }
};
</script>

<style></style>