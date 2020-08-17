<template>
  <v-app>
    <v-container>
      <v-row justify="center">
        <v-form
            ref="form"
            v-model="valid"
            lazy-validation
            @submit.prevent="submitHandler"
            id="register-form"
        >

          <v-col class="lg-col-16">
            <v-alert class="lg-col-16" v-if="$store.state.register.success" type="success">
              User saved
            </v-alert>
            <div class="lg-col-16" v-if="$store.state.register.errors.length>0">
              <v-alert v-bind:key="error" v-for="error in $store.state.register.errors" type="error">
                {{error}}
              </v-alert>
            </div>
            <v-text-field
                v-model="firstName"
                label="First name"
                outlined
                required
            ></v-text-field>

            <v-text-field
                v-model="lastName"
                label="Last name"
                outlined
                required
            ></v-text-field>

            <v-text-field
                v-model="email"
                :rules="emailRules"
                label="Email"
                outlined
                required
            ></v-text-field>

            <v-text-field
                v-model="skypeId"
                :rules="skypeIdRules"
                label="Skype Id"
                outlined
                required
            ></v-text-field>

            <v-text-field
                v-model="employmentDate"
                :rules="employmentDateRules"
                label="Employment date"
                outlined
                required
            ></v-text-field>

            <v-radio-group class="radio-group" v-model="role" id="roles" row>
              <span class="pr-3">Role</span>
              <v-radio label="User" color="primary" value="User"></v-radio>
              <v-radio
                  label="Supervisor"
                  color="primary"
                  value="Supervisor"
              ></v-radio>
            </v-radio-group>

            <v-btn
                class="col-12"
                color="warning"
                style="background: orange !important"
                depressed="true"
                type="submit"
                form="register-form"
                :disabled="$store.state.register.loading"
                :loading="$store.state.register.loading"
            >Register
            </v-btn
            >
          </v-col>
        </v-form>
      </v-row>
    </v-container>

  </v-app>
</template>
<script>
export default {
  name: "register",
  data() {
    return {
      registerError: false,
      errors: [],
      firstName: "",
      lastName: "",
      email: "",
      emailRules: [
        v => !!v || "Email is required",
        v => /.+@.+\..+/.test(v) || "Email must be valid"
      ],
      skypeId: "",
      employmentDate: "",
      role: ""
    };
  },
  methods: {
    submitHandler() {
      this.$store
          .dispatch("createUser", {
            firstName: this.firstName,
            lastName: this.lastName,
            email: this.email,
            employmentDate: this.employmentDate,
            skypeId: this.skypeId,
            role: this.role
          })
          .then(Response => {
            console.log(Response);
            //   this.$router.push("/");
          })
          .catch(error => {
            console.log(error);
            this.registerError = true;
            this.errors.push(error);
          });
    }
  }
};
</script>
