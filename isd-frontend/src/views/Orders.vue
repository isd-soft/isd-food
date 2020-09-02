<template>
  <v-app>
    <div>
      <confirmationDialog :action-button="'Agree'"
                          :method="deleteOrder" title="Delete order"
                          :message="'Are you sure you want do delete this order ?'" :dialog1="deleteConfirmation"
                          @closeDeleteDialog="deleteConfirmation = false"/>
      <OrderEdit @closeEditDialog="closeEditDialog" :edit-dialog="orderEditDialog"
                 :order-to-edit="orderToUpdate"
                 :menus="menus"
                 :menu="orderToUpdate==null ? null : orderToUpdate.menuType"
                 :days="days"
                 :weekDay="weekDay"
                 :order-menu-type-id="orderMenuTypeId"
                 @setMenuTypeId="setMenuTypeId"
                 @getMenusByDay="getMenusByDay"
                 @updateOrder="updateOrder"/>
      <div class="card shadow mb-4">
        <div class="card-header py-3">
          <v-progress-linear
              :active="$store.state.orders.usersOrdersLoading"
              :indeterminate="true"
              absolute
              top
              color="amber darken-1"
          ></v-progress-linear>
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
                v-show="$store.getters.showUserOrderHistory"
            >
              <div class="row justify-content-end mr-5" v-show="$store.state.orders.userOrdersType==='history'">
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
                      label="Providers"
                      multiple
                      persistent-hint
                  ></v-select>
                </v-col>
              </div>
              <OrderTable @openOrderEdit="openOrderEdit" @setOrderToDelete="setOrderToDelete"
                          @setSortColsDirection="setSortColsDirection"/>
              <Pagination v-show="this.$store.state.orders.userOrdersType==='history'" class="pb-15 pt-5"
                          @pageChanged="setPage" :page="page"
                          :totalPages="this.$store.state.orders.userOrdersHistory.totalPages"/>
            </div>

            <div class="row justify-content-center" v-show="!$store.getters.showUserOrderHistory"><img
                src="https://i.pinimg.com/originals/88/ce/4c/88ce4c21492635e9e1422d0667dde555.png" width="300px"/></div>
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
import confirmationDialog from "@/components/ConfirmationDialog";
import OrderEdit from "@/components/modal/OrderEdit";
import moment from "moment";

export default {
  components: {OrderTable, DatePicker, Pagination, confirmationDialog, OrderEdit},
  name: "Home",
  data() {
    return {
      days: ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"],
      weekDay: null,
      orderIdToDelete: 0,
      deleteConfirmation: false,
      orderEditDialog: false,
      orderToUpdate: null,
      orderMenuTypeId: 0,
      menus: [],
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
    setOrderToDelete(orderId) {
      this.orderIdToDelete = orderId;
      this.deleteConfirmation = true;
    },
    deleteOrder() {
      this.$store.dispatch("deleteUserOrder", this.orderIdToDelete).then(() => {
        this.orderIdToDelete = 0;
        this.deleteConfirmation = false;
        this.getUserCurrentOrders();
        this.getUserOrdersHistory();
        this.getProviders();
      });

    },
    openOrderEdit(orderId) {
      api.getOrder(orderId).then(response => {
        if (response.data.id !== null) {
          this.getMenusByDay(
              this.days[new Date(response.data.date).getDay() - 1]
          );
          this.weekDay = this.days[new Date(response.data.date).getDay() - 1];
          this.orderMenuTypeId = response.data.menuType.id;
        }
        this.orderToUpdate = response.data;
        this.orderEditDialog = true;
      });
    },
    closeEditDialog() {
      this.orderEditDialog = false;
    },
    getMenusByDay(day) {
      api.getMenuDay(day).then(r => {
        let menus = r.data;

        menus.forEach(menu => {
          if (menu.menuTypes[0].type !== "M") {
            let temp = menu.menuTypes[0];
            menu.menuTypes[0] = menu.menuTypes[1];
            menu.menuTypes[1] = temp;
          }
        });

        this.menus = r.data;
        this.weekDay = day;
      });
    },

    setMenuTypeId(id) {
      this.orderMenuTypeId = id;
    }
    ,
    updateOrder() {

      var currentDate = new Date(moment(new Date()).format('yyyy-MM-DD'));
      var result = new Date(currentDate);
      result.setDate(result.getDate() + ((this.days.indexOf(this.weekDay) + 1) - currentDate.getDay()));

      api.updateOrder(this.orderToUpdate.id, {
        menuTypeId: this.orderMenuTypeId,
        date: moment(result).format('yyyy-MM-DD')
      }).then((r) => {
        if (r.data === "") {
          this.getUserCurrentOrders();
          this.getUserOrdersHistory();
          this.getProviders();
          this.orderEditDialog = false
        }
      })

    }
    ,
    setSortColsDirection(col) {
      this[col.name] = col.direction;
      this.getUserOrdersHistory();
    }
  },
  beforeMount() {
    this.getUserCurrentOrders();
    this.getUserOrdersHistory();
    this.getProviders();
  }
}
;
</script>

<style></style>