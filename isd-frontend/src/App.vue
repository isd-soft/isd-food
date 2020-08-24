<template>
  <div id="page-top">
    <PaymentDialog v-if="$store.state.payment.displayUserPayment"/>
    <ErrorDialog v-if="$store.state.errorDialog"/>
    <CreateOrderDialog v-if="$store.state.orders.createOrderSuccess"/>

    <div id="wrapper">
      <ul
          class="navbar-nav fixed-top sidebar sidebar-dark accordion"
          id="accordionSidebar"
          style="background-color: #ff9800"
      >
        <a
            class="sidebar-brand d-flex align-items-center justify-content-center"
        >
          <li class="nav-item text-center">
            <span class="nav-link mt-2">
              <img width="50px" src="./assets/food-bot.png"/>
              <span class="ml-2">Isd-food</span></span
            >
          </li>
        </a>
        <hr class="sidebar-divider my-0">
        <div v-if="role==='ROLE_supervisor'">
          <li class="nav-item " v-for="link in SuperVisorLinks" :key="link.text">
            <router-link class="nav-link" :to="link.route">
              <v-icon style="color: white; font-size: 18px">{{ link.icon }}</v-icon>
              <span> {{ link.text }}</span></router-link>
            <hr class="sidebar-divider my-0">
          </li>
        </div>
        <div v-else>
          <li class="nav-item " v-for="link in links" :key="link.text">
            <router-link class="nav-link" :to="link.route">
              <v-icon style="color: white; font-size: 18px">{{ link.icon }}</v-icon>
              <span> {{ link.text }}</span></router-link>
            <hr class="sidebar-divider my-0">
          </li>
        </div>

      </ul>
      <!-- End of Sidebar -->
      <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
          <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
            <!-- Sidebar Toggle (Topbar) -->

            <!-- Topbar Navbar -->
            <ul class="navbar-nav ml-auto">
              <!-- Nav Item - Alerts -->
              <li class="nav-item dropdown no-arrow mx-1">
                <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    id="alertsDropdown"
                    role="button"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                >
                  <i class="fas fa-bell fa-fw"></i>
                  <!-- Counter - Alerts -->
                  <span class="badge badge-danger badge-counter">3+</span>
                </a>
                <!-- Dropdown - Alerts -->
                <div
                    class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                    aria-labelledby="alertsDropdown"
                >
                  <h6 class="dropdown-header">
                    Alerts Center
                  </h6>
                  <a class="dropdown-item d-flex align-items-center" href="#">
                    <div class="mr-3">
                      <div class="icon-circle bg-primary">
                        <i class="fas fa-file-alt text-white"></i>
                      </div>
                    </div>
                    <div>
                      <div class="small text-gray-500">December 12, 2019</div>
                      <span class="font-weight-bold"
                      >A new monthly report is ready to download!</span
                      >
                    </div>
                  </a>
                  <a class="dropdown-item d-flex align-items-center" href="#">
                    <div class="mr-3">
                      <div class="icon-circle bg-success">
                        <i class="fas fa-donate text-white"></i>
                      </div>
                    </div>
                    <div>
                      <div class="small text-gray-500">December 7, 2019</div>
                      $290.29 has been deposited into your account!
                    </div>
                  </a>
                  <a class="dropdown-item d-flex align-items-center" href="#">
                    <div class="mr-3">
                      <div class="icon-circle bg-warning">
                        <i class="fas fa-exclamation-triangle text-white"></i>
                      </div>
                    </div>
                    <div>
                      <div class="small text-gray-500">December 2, 2019</div>
                      Spending Alert: We've noticed unusually high spending for
                      your account.
                    </div>
                  </a>
                  <a
                      class="dropdown-item text-center small text-gray-500"
                      href="#"
                  >Show All Alerts</a
                  >
                </div>
              </li>

              <div class="topbar-divider d-none d-sm-block"></div>

              <!-- Nav Item - User Information -->
              <li class="nav-item dropdown no-arrow">
                <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    id="userDropdown"
                    role="button"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                >
                  <span class="mr-2 d-none d-lg-inline text-gray-600 small">{{
                      user
                    }}</span>
                  <img
                      class="img-profile rounded-circle"
                      src="https://source.unsplash.com/QAB-WJcbgJk/60x60"
                  />
                </a>
                <!-- Dropdown - User Information -->
                <div
                  class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                  aria-labelledby="userDropdown"
                >
                  <a class="dropdown-item" href="/edit">
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    Profile
                  </a>
                  <a class="dropdown-item" href="#">
                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                    Settings
                  </a>
                  <a class="dropdown-item" href="#">
                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                    Activity Log
                  </a>
                  <div class="dropdown-divider"></div>
                  <a
                    class="dropdown-item"
                    href="#"
                    data-toggle="modal"
                    data-target="#logoutModal"
                  >
                    <i
                      class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"
                    ></i>
                    Logout
                  </a>
                </div>
              </li>
            </ul>
          </nav>
          <div class="container-fluid">
            <router-view></router-view>
          </div>
        </div>
      </div>
    </div>

    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fas fa-angle-up"></i>
    </a>

    <div
      class="modal fade"
      id="logoutModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button
              class="close"
              type="button"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">
            Select "Logout" below if you are ready to end your current session.
          </div>
          <div class="modal-footer">
            <button
              class="btn btn-secondary"
              type="button"
              data-dismiss="modal"
            >
              Cancel
            </button>
            <a class="btn btn-primary" href="/login">Logout</a>
          </div>
        </div>
      </div>
    </div>




  </div>
</template>
<script>

import "@/assets/styles/app.css";
import ErrorDialog from "@/components/modal/ErrorDialog";
import CreateOrderDialog from "@/components/modal/CreateOrderDialog";
import PaymentDialog from "@/components/modal/PaymentDialog";
import api from "@/components/backend-api"

export default {
  name: "App",
  components: {ErrorDialog, CreateOrderDialog, PaymentDialog},
  data: () => ({
    scrollTop: false,
    role: localStorage.getItem("userRole"),
    links: [
      { icon: "fas fa-user", text: "Account", route: "/edit" },
      { icon: "fas fa-pizza-slice", text: "Menus", route: "/menu" },
      { icon: "fas fa-history", text: "Orders", route: "/orders" },
      { icon: "fas fa-envelope", text: "Mail", route: "/" }
    ],

    SuperVisorLinks: [

      { icon: "fas fa-users", text: "Users", route: "/users/table" },
      { icon: "fas fa-pizza-slice", text: "Menus", route: "/allmenus" },
      { icon: "fab fa-ubuntu", text: "Providers", route: "/ProviderList" },
      { icon: "fas fa-history", text: "Orders", route: "/ProviderOrders" }

    ]
  }),
  methods: {
    handleScroll(event) {
      this.scrollTop = window.scrollY > 300;
    },
    scroll() {
      console.log("scrolling")
      window.scrollTo({
        top: 0,
        left: 0,
        behavior: 'smooth'
      });
    }
  },
  beforeCreate() {
    api.getUserWithoutId().then(r => {
      this.user = r.data.firstName + " " + r.data.lastName;
    });
  },
  created() {
    window.addEventListener('scroll', this.handleScroll);
  },
  destroyed() {
    window.removeEventListener('scroll', this.handleScroll);
  },
};
</script>
<style>
@import "css/sb-admin-2.min.css";

.color-orange {
  color: orange;
}

</style>
