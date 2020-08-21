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
            <v-alert
              class="lg-col-16"
              v-if="$store.state.register.success"
              type="success"
            >
              User saved
            </v-alert>
            <div
              class="lg-col-16"
              v-if="$store.state.register.errors.length > 0"
            >
              <v-alert
                v-bind:key="error"
                v-for="error in $store.state.register.errors"
                type="error"
              >
                {{ error }}
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

            <v-menu
              v-model="menu2"
              :close-on-content-click="false"
              :nudge-right="40"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="formattedDate"
                  label="Employment Date"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                v-model="employmentDate"
                @input="menu2 = false"
                color="warning"
                @change="formatDate"
                value="dd-mm-yy"
              ></v-date-picker>
            </v-menu>
            <v-radio-group class="radio-group" v-model="role" id="roles" row>
              <span class="pr-3">Role</span>
              <v-radio label="User" color="primary" value="user"></v-radio>
              <v-radio
                label="Supervisor"
                color="primary"
                value="supervisor"
              ></v-radio>
            </v-radio-group>

            <v-btn
              class="col-12"
              color="warning"
              style="background: orange !important"
              depressed
              type="submit"
              form="register-form"
              :disabled="$store.state.register.loading"
              :loading="$store.state.register.loading"
              >Register
            </v-btn>
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
      menu2: false,
      modal: false,
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
      employmentDate: new Date().toISOString().substr(0, 10),
      role: "",
      formattedDate: ""
    };
  },
  methods: {
    formatDate() {
      let array = this.employmentDate.split("-");
      this.formattedDate = array[2] + "-" + array[1] + "-" + array[0];
    },
    submitHandler() {
      this.$store
        .dispatch("createUser", {
          firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
          employmentDate: this.employmentDate,
          skypeId: this.skypeId,
          role: { name: this.role }
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
