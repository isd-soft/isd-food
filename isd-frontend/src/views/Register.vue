<template>
  <v-container>
    <v-row justify="center">
      <v-dialog v-model="dialog" value="true">DIalog</v-dialog>
      <v-form
        ref="form"
        v-model="valid"
        lazy-validation
        @submit.prevent="submitHandler"
        id="register-form"
      >
        <v-col>
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
          >
          </v-text-field>

          <v-text-field
            v-model="email"
            :rules="emailRules"
            label="Email"
            outlined
            required
          >
          </v-text-field>

          <v-text-field
            v-model="skypeId"
            :rules="skypeIdRules"
            label="Skype Id"
            outlined
            required
          >
          </v-text-field>

          <v-text-field
            v-model="employmentDate"
            :rules="employmentDateRules"
            label="Employment date"
            outlined
            required
          >
          </v-text-field>

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
            depressed="true"
            type="submit"
            form="register-form"
            :disabled="$store.getters.isLoggingInProcess"
            :loading="$store.getters.isLoggingInProcess"
            >Register</v-btn
          >
        </v-col>
      </v-form>
    </v-row>
  </v-container>
</template>
<script>
export default {
  name: "register",
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      skypeId: "",
      employmentDate: "",
      role: "",
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
          role: this.role,
        })
        .then((Response) => {
          console.log(Response);
          //   this.$router.push("/");
        })
        .catch((error) => {
          console.log(error);
          this.loginError = true;
          this.errors.push(error);
          this.error = true;
        });
    },
  },
};
</script>
