<template>
  <v-app>
    <v-overlay
        :absolute="absolute"
        :opacity="opacity"
        :value="overlay"
        :z-index="zIndex"
    >

      <v-app>
        <v-card width="100vw" height="100vh" class="mx-auto">
          <body class="bg-gradient-warning">

          <div class="container " style="padding-top: 20vh" >

            <!-- Outer Row -->
            <div class="row justify-content-center ">

              <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                  <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                      <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                      <div class="col-lg-6">
                        <div class="p-5">
                          <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Welcome to isd-food</h1>
                          </div>
                          <v-form class="user">


                            <div class="form-group">
                              <input type="email" class="form-control form-control-user" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Enter Email Address...">
                            </div>
                            <div class="form-group">
                              <input type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="Password">
                            </div>
                            <div class="form-group">
                              <div class="custom-control custom-checkbox small">
                                <input type="checkbox" class="custom-control-input" id="customCheck">
                                <label class="custom-control-label" for="customCheck">Remember Me</label>
                              </div>
                            </div>
                            <a href="index.html" class="btn btn-warning btn-user btn-block" style="color: white">
                              Login
                            </a>
                          </v-form>
                          <hr>
                          <div class="text-center">
                            <a class="small" href="forgot-password.html">Forgot Password?</a>
                          </div>

                        </div>
                      </div>
                    </div>
                  </div>
                </div>

              </div>

            </div>

          </div>



          </body>
        </v-card>
      </v-app>
    </v-overlay>
  </v-app>

</template>
<script>

export default {

  name: "login",
  data() {
    return {
      email: "",
      password: ""
    };
  },
  methods: {
    submitHandler() {
      
      console.log(this);

      this.$store
        .dispatch("login", { email: this.email, password: this.password })
        .then(() => {
          this.$router.push("/");
        })
        .catch(error => {
          console.log(error);
          this.loginError = true;
          this.errors.push(error);
          this.error = true;
        });
    }
  }
};
</script>
