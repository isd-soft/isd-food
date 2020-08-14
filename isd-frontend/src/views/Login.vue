<template>
  <v-app>
  <v-container>
    <Header/>
    <v-row justify="center">
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
              color="warning"
              depressed="true"
              type="submit"
              form="login-form"
              :disabled="$store.getters.isLoggingInProcess"
              :loading="$store.getters.isLoggingInProcess"
          >Login
          </v-btn><v-alert v-for="error in errors"
                           v-bind:key="error"
                           v-bind:title="error" type="warning" class="bg-warning mt-5">
        </v-alert>
        </v-col>
      </v-form>

    </v-row>
  </v-container>
  </v-app>
</template>
<script>

export default {

  name: "login",
  data() {
    return {
      email: "",
      password: "",
      errors: []
    };
  },
  methods: {
    submitHandler() {

      this.$store
          .dispatch("login", {email: this.email, password: this.password})
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
