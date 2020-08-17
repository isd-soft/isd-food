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

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>



          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">
            <!-- Nav Item - Alerts -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-bell fa-fw"></i>
                <!-- Counter - Alerts -->
                <span class="badge badge-danger badge-counter">3+</span>
              </a>
            </li>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Valerie Luna</span>
                <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
              </a>
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

  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
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
      {icon: "fas fa-envelope", text:"Mail",route:"/"},

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