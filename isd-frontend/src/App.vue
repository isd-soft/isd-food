<template>
  <div  id="page-top">
    <div  id="wrapper">
      <ul class="navbar-nav sidebar sidebar-dark accordion" id="accordionSidebar"  style="background-color: #ff9800">
        <a class="sidebar-brand d-flex align-items-center justify-content-center">
          <li class="nav-item text-center">
            <span  class="nav-link" >
              <v-icon style="color: white; font-size: 20px">fas fa-utensils</v-icon>
              <span class="ml-md-5">Isd-food</span></span>
          </li>
        </a>
        <hr class="sidebar-divider my-0" >

        <li class="nav-item " v-for="link in links" :key="link.text" >
          <router-link class="nav-link" :to = "link.route">
           <v-icon style="color: white; font-size: 18px">{{link.icon}}</v-icon>
            <span> {{ link.text }}</span></router-link>
          <hr class="sidebar-divider my-0">
        </li>
      </ul>
    <!-- End of Sidebar -->
    <div id="content-wrapper"  class="d-flex flex-column">
      <div id="content">
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">
            <!-- Nav Item - Alerts -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-bell fa-fw"></i>
                <!-- Counter - Alerts -->
                <span class="badge badge-danger badge-counter">1</span>
              </a>
            </li>
            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Valerie Luna</span>
              </a>
            </li>
          </ul>
        </nav>

        <!---------------------------------==Main Content==----------------------------------->
        <div class="container-fluid">

          <router-view></router-view>

        </div>
        <!-----------------------------------==Main Content==----------------------------------->
      </div>
    </div>
  </div>
    <script type="application/javascript"  src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script type="application/javascript"  src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script  type="application/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
  </div>
</template>

<script >
export default {
  name: "App",
  data: () => ({
    links :[

      {icon: "fas fa-user", text:"Account",route:"/edit"},
      {icon: "fas fa-pizza-slice", text:"Menus",route:"/menu"},
      {icon: "fas fa-history", text:"Orders",route:"/orders"},
      {icon: "fas fa-sign-out-alt", text:"Log out",route:"/"},

    ],
  })

};
</script>
<style>
.color-orange{
  color: orange;
}
@import "css/sb-admin-2.min.css";
</style>