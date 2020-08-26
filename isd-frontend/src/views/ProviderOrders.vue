<template>
  <div>
    <ul
      class="nav nav-pills d-flex justify-content-center mb-3"
      id="pills-tab"
      role="tablist"
    >
      <li class="nav-item mr-2" role="presentation">
        <a
          class="nav-link btn-media btn-outline-warning  active"
          id="pills-home-tab"
          data-toggle="pill"
          href="#pills-home"
          role="tab"
          aria-controls="pills-home"
          aria-selected="true"
          >Current orders</a
        >
      </li>
      <li class="nav-item mr-2" role="presentation">
        <a
          class="nav-link btn-media btn-outline-warning  "
          id="pills-profile-tab"
          data-toggle="pill"
          href="#pills-profile"
          role="tab"
          aria-controls="pills-profile"
          aria-selected="false"
          >Current menus</a
        >
      </li>
      <li class="nav-item" role="presentation">
        <a
          class="nav-link btn-media btn-outline-warning"
          id="pills-contact-tab"
          data-toggle="pill"
          href="#pills-contact"
          role="tab"
          aria-controls="pills-contact"
          aria-selected="false"
          >Confirmed orders
        </a>
      </li>
    </ul>
    <div class="tab-content" id="pills-tabContent">
      <div
        class="tab-pane fade show active"
        id="pills-home"
        role="tabpanel"
        aria-labelledby="pills-home-tab"
      >
        <div class="row" v-if="ordersFalse.length != 0">
          <div class="col-xl-12 col-md-12 mb-4">
            <div class="card border-left-warning shadow h-100 py-1">
              <div class="card-body wrapScroll ">
                <table
                  class="table table-bordered"
                  width="100%"
                  cellspacing="0"
                >
                  <thead>
                    <tr>
                      <th>User</th>
                      <th>Menu</th>
                      <th>MenuType</th>
                      <th>Provider</th>
                      <th>Confirm</th>
                      <th>Delete</th>
                    </tr>
                  </thead>



                  <tbody
                    v-for="order in ordersFalse"
                    :key="order.providerName + order.date"
                  >
                    <tr>
                      <td>{{ getUserName(order.userId) }}</td>
                      <td>{{ order.menuName }}</td>
                      <td>{{ order.menuType }}</td>
                      <td>{{ order.providerName }}</td>
                      <td>
                        <button
                          type="submit"
                          @click="confirmOrder(order.id, true)"
                          onclick="window.location.reload();"
                        >
                          <v-icon
                            data-toggle="modal"
                            data-target="#exampleModal"
                            >fas fa-check</v-icon
                          >
                        </button>
                      </td>
                      <td>
                        <button type="button" @click="openDialog(order.id)">
                          <v-icon>fas fa-trash</v-icon>
                        </button>

                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
        <div v-else class="text-center" style="margin-top: 25vh">
          <h2>Empty</h2>
        </div>
      </div>
      <div
        class="tab-pane fade"
        id="pills-profile"
        role="tabpanel"
        aria-labelledby="pills-profile-tab"
      >
        <div class="row ">
          <div
            class="col-xl-6 col-md-6 mb-4"
            v-for="provider of this.providers"
            :key="provider.name + provider.deliveryPrice"
          >
            <div class="card border-left-warning shadow h-100 py-1">
              <div class="card-body wrapScroll">
                <table
                  class="table table-bordered"
                  width="100%"
                  cellspacing="0"
                >
                  <thead>
                    <tr>
                      <th>{{ provider.name }}</th>
                      <th>S</th>
                      <th>M</th>
                    </tr>
                  </thead>

                  <tbody v-for="menu of menus" :key="menu.id">
                    <tr v-if="menu.provider.name === provider.name">
                      <td>{{ menu.name }}</td>
                      <td>{{ calcOnce(menu.name, provider.name, "S") }}</td>
                      <td>{{ calcOnce(menu.name, provider.name, "M") }}</td>
                    </tr>
                  </tbody>

                  <tbody>
                    <tr>
                      <td><b>Total:</b></td>
                      <td>{{ calcForEach(provider.name, "S") }}</td>
                      <td>{{ calcForEach(provider.name, "M") }}</td>
                    </tr>
                  </tbody>
                </table>
                <div class="text-right">
                  <button
                    class="btn btn-warning"
                    style="color: white !important;"
                    type="submit"
                    @click="confirmAll(provider.name)"
                    onclick="window.location.reload();"
                    v-if="
                      calcForEach(provider.name, 'S') != 0 ||
                        calcForEach(provider.name, 'M') != 0
                    "
                  >
                    confirm
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div
        class="tab-pane fade"
        id="pills-contact"
        role="tabpanel"
        aria-labelledby="pills-contact-tab"
      >
        <div class="row " v-if="ordersTrue.length != 0">
          <div class="col-xl-12 col-md-12 mb-4">
            <div class="card border-left-warning shadow h-100 py-1">
              <div class="card-body wrapScroll">
                <table
                  class="table table-bordered"
                  width="100%"
                  cellspacing="0"
                >
                  <thead>
                    <tr>
                      <th>User</th>
                      <th>Menu</th>
                      <th>MenuType</th>
                      <th>Provider</th>
                      <th>Revoke</th>
                    </tr>
                  </thead>

                  <tbody
                    v-for="order in ordersTrue"
                    :key="order.providerName + order.date"
                  >
                    <tr>
                      <td>{{ getUserName(order.userId) }}</td>
                      <td>{{ order.menuName }}</td>
                      <td>{{ order.menuType }}</td>
                      <td>{{ order.providerName }}</td>
                      <td>
                        <button
                          type="submit"
                          @click="confirmOrder(order.id, false)"
                          onclick="window.location.reload();"
                        >
                          <v-icon
                            data-toggle="modal"
                            data-target="#exampleModal"
                            >fas fa-times-circle</v-icon
                          >
                        </button>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
        <div v-else class="text-center" style="margin-top: 25vh">
          <h2>Empty</h2>
        </div>
      </div>
    </div>
    <v-app style="background: none; height: 1px">
      <confirmationDialog
          :action-button="'Agree'"
          :method="deleteOrder"
          :title="title"
          :message="message"
          :dialog1.sync="dialog1"
      />
    </v-app>
  </div>
</template>

<script>
import api from "@/components/backend-api";
import confirmationDialog from "../components/confirmationDialog";

export default {
  name: "ProviderOrders",
  components: {
    confirmationDialog
  },
  data() {
    return {
      n: 0,
      m: 0,
      providers: [],
      orders: [],
      menus: [],
      users: [],
      ordersFalse: [],
      ordersTrue: [],
      currentId: null,
      title: "Confirmation",
      message: "Do you really want to delete this order?",
      dialog1: false,
    };
  },

  methods: {
    openDialog(id) {
      this.currentId = id;
      this.dialog1 = true;
      console.log(this.dialog1);
    },

    calcOnce(menuName, Provider, Type) {
      let count = 0;
      this.orders.forEach(el => {
        if (
          menuName === el.menuName &&
          Provider === el.providerName &&
          Type === el.menuType
        ) {
          count++;
        }
      });
      return count;
    },
    calcForEach(provider, type) {
      let res = 0;
      this.menus.forEach(el => {
        res += this.calcOnce(el.name, provider, type);
      });
      return res;
    },
    getUserName(id) {
      let name = "standart";
      this.users.forEach(el => {
        if (el.id === id) name = el.firstName + " " + el.lastName;
      });
      return name;
    },
    confirmOrder(id, bool) {
      api.confirmOrderId(id, bool);
    },

    deleteOrder() {
      api.deleteOrder(this.currentId);
    },

    confirmAll(Provider) {
      this.ordersFalse.forEach(el => {
        if (el.providerName === Provider) {
          this.confirmOrder(el.id, true);
        }
      });
    }
  },

  beforeCreate() {
    api.getAllProviders().then(r => {
      this.providers = r.data;
      console.log(r.data);
    });
    api.getAllCurrentOrders().then(r => {
      this.orders = r.data.content;
      console.log(r.data);
    });
    api.getMenu().then(r => {
      this.menus = r.data;
      console.log(r.data);
    });
    api.getAllUsers().then(r => {
      this.users = r.data;
      console.log(r.data);
    });
    api.getAllOrders().then(r => {
      this.ordersFalse = r.data.content;
      console.log(r.data);
    });
    api.getConfirmedOrders().then(r => {
      this.ordersTrue = r.data.content;
      console.log(r.data);
    });
  }
};
</script>

<style scoped>
.wrapScroll {
  overflow: auto;
  white-space: nowrap;
}
@media (max-width: 900px) {
  .btn-media {
    font-size: 12px;
  }
}
</style>
