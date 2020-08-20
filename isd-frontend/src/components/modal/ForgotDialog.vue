<template>
  <v-app>
    <v-dialog
      v-model="$store.state.login.showDialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">Reset password</v-card-title>
        <v-card-text>
          An email will be sent with your new password, continue ?
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            class="white--text grey darken-2 mr-1"
            depressed
            @click="$store.state.login.showDialog = false"
            >Close
          </v-btn>
          <v-btn
            class="mr-3"
            color="warning"
            depressed
            @click="resetPassword()"
            >Ok</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-app>
</template>
<script>
export default {
  data() {
    return {};
  },
  methods: {
    resetPassword() {
      this.$store.state.login.showDialog = false;

      this.$store
        .dispatch("resetPassword", { email: this.$store.state.login.email })
        .then(() => {
        })
        .catch(error => {
          this.loginError = true;
        });
    }
  }
};
</script>
