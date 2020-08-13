<template>
  <v-app>
    <v-overlay

    >
      <v-app>
        <v-card width="100vw" height="100vh" class="mx-auto">
          <body style="background: orange">

          <div class="container " style="padding-top: 20vh" >

            <!-- Outer Row -->
            <div class="row justify-content-center ">

              <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                  <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                      <div class="col-lg-6 d-none d-lg-block bg-login-image" style="background-image:url('https://bipbap.ru/wp-content/uploads/2017/06/14813uxVsXjAPBFmIovEzZkHNnR.jpg') !important; "></div>
                      <div class="col-lg-6">
                        <div class="p-5">
                          <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Welcome to isd-food</h1>
                          </div>


                          <div class="user">
                            <v-form
                                ref="form"
                                v-model="valid"
                                lazy-validation
                                @submit.prevent="submitHandler"
                                id="login-form"
                            >
                              <v-col sm="12" md="12">
                                <v-text-field
                                    v-model="email"
                                    :rules="emailRules"
                                    label="Email"
                                    outlined
                                    required
                                ></v-text-field>

                                <v-text-field
                                    type="password"
                                    v-model="password"
                                    :rules="passwordRules"
                                    label="Password"
                                    outlined
                                    required
                                ></v-text-field>

                                <v-btn
                                    class="col-12"
                                    color="warning " style="background: orange !important"
                                    type="submit"
                                    form="login-form"
                                    :disabled="$store.getters.isLoggingInProcess"
                                    :loading="$store.getters.isLoggingInProcess"

                                >Login</v-btn
                                >
                              </v-col>
                            </v-form>





                          </div>
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
