<template>

    <v-overlay>
      <v-app>
        <v-card width="100vw" height="100vh" class="mx-auto">

          <body style="background: orange;">
          <div class="container">
            <!-- Outer Row -->
            <div class="row justify-content-center">
              <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                  <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                      <div class="col-lg-6 d-none d-lg-block bg-login-image"
                           style="background-image:url('https://www.learnathome.ru/files/media/food.jpg') !important; "></div>
                      <div class="col-lg-6">
                        <div class="p-5">
                          <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Welcome to isd-food</h1>
                            <v-alert class="lg-col-16" v-if="$store.state.login.loginError" type="warning">
                              {{ $store.state.login.error }}
                            </v-alert>
                          </div>

                          <div class="user">
                            <v-form ref="form" v-model="valid" lazy-validation @submit.prevent="submitHandler" id="login-form">
                              <v-col sm="12" md="12">
                                <v-text-field v-model="email" :rules="emailRules" label="Email" outlined required></v-text-field>
                                <v-text-field type="password" v-model="password" :rules="passwordRules" label="Password" outlined required></v-text-field>
                                <v-btn class="col-12" color="warning " style="background: orange !important" type="submit" form="login-form" :disabled="$store.getters.isLoggingInProcess" :loading="$store.getters.isLoggingInProcess">Login</v-btn>
                              </v-col>
                            </v-form>
                          </div>
                          <div class="text-center">
                            <span class="small forgot-pass" @click="showForgotDialog()">Forgot Password?</span>
                          </div>
                          <ForgotDialog showDialog="showDialog"/>
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

</template>
<script>

import ForgotDialog from "@/components/modal/ForgotDialog.vue";

export default {
  name: "dialog",
  components: { ForgotDialog },
  data() {
    return {
      email: "",
      password: ""
    };
  },
  methods: {
    showForgotDialog() {
      this.$store.state.login.showDialog = true;
      this.$store.state.login.email = this.email;
    },
    submitHandler() {
      this.$store
          .dispatch("login", {email: this.email, password: this.password})
          .then(() => {
            this.$router.push("/");
          })
          .catch(error => {
            this.loginError = true;
            this.errors.push(error);
            this.error = true;
          });
    }
  }
};
</script>
