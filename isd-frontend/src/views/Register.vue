<template>
  <v-app class="row" heavy style="background: none">
    <v-card class="col-md-6 container border-left-warning" style="margin-top: 20px">
      <v-form
        ref="form"
        v-model="valid"
        @submit.prevent="submitHandler"
        id="register-form"
        class="py-5"
      >
        <v-col class="col-sm-12">
          <v-alert
            class="col-sm-12"
            v-if="$store.state.register.success"
            type="success"
          >
            User saved
          </v-alert>
          <v-text-field
            v-model="firstName"
            label="First name"
            :rules="firstNameRules"
            required
          ></v-text-field>

          <v-text-field
            v-model="lastName"
            label="Last name"
            :rules="lastNameRules"
            required
          ></v-text-field>

          <v-text-field
            v-model="email"
            :rules="emailRules"
            label="Email"
            required
          ></v-text-field>

          <v-text-field
            v-model="skypeId"
            label="Skype Id"
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
              :max="nowDate"
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
            :disabled="!valid"
            :loading="$store.state.register.loading"
            >Register
          </v-btn>
        </v-col>
      </v-form>
    </v-card>
  </v-app>
</template>
<script>
import route from "../router/index";
export default {
  name: "register",
  data() {
    return {
      nowDate: new Date().toISOString().slice(0,10),
      valid: false,
      menu2: false,
      modal: false,
      registerError: false,
      errors: [],
      firstName: "",
      firstNameRules: [
        v => !!v || "First Name is required",
        v => /^[A-Z]+[a-z]+$/.test(v) || "First Name must be only letters, big and small"
      ],
      lastName: "",
      lastNameRules: [
        v => !!v || "First Name is required",
        v => /^[A-Z]+[a-z]+$/.test(v) || "First Name must be only letters, big and small"
      ],
      email: "",
      emailRules: [
        v => !!v || "Email is required",
        v => /.+@.+\..+/.test(v) || "Email must be valid"
      ],
      skypeId: "",
      employmentDate: new Date().toISOString().substr(0, 10),
      role: { name: "" },
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
          if(Response.status === 201){
            this.$refs.form.reset();
            //self.$route.push("/users/table");
            this.$router.push("/users/table");
          }
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
