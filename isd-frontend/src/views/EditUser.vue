<template>
  <v-app class="row" style="background: none;height: 100vh">
    <v-card class="container col-xl-9 col-md-10 col-sm-12">
      <form class="row" novalidate="">
        <div class="col-lg-6" id="firstName">
          <label>First Name</label>
          <input
              class="form-control"
              type="text"
              name="firstName"
              v-model="firstName"
              placeholder="First Name"
          />
        </div>
        <div class="col-lg-6" id="lastName">
          <label>Last Name</label>
          <input
              class="form-control"
              type="text"
              name="lastName"
              v-model="lastName"
              placeholder="Last Name"
          />
        </div>
        <div class="col-lg-6" id="Email">
          <label>Email</label>
          <input
              class="form-control"
              type="text"
              name="email"
              v-model="email"
              placeholder="Email"
          />
        </div>
        <div class="col-lg-6" id="SkypeId">
          <label>Skype Id</label>
          <input
              class="form-control"
              type="text"
              name="skypeId"
              v-model="skypeId"
              placeholder="Skype Id"
          />
        </div>
        <div class="col-lg-6" id="Notification">
          <b>Keeping in Touch</b>
          <v-switch
              v-model="notificationEnabled"
              color="orange"
              label="Notification"
          >
          </v-switch>

          <div v-if="notificationEnabled === false">
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
                    v-model="dateToChange"
                    label="Notification Date"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                  v-model="dateToChange"
                  @input="menu2 = false"
                  color="warning"
                  value="dd-mm-yy"
              ></v-date-picker>
            </v-menu>
          </div>
        </div>
        <div class="col-lg-12" id="Password">

          <form class = "row"
                oninput='password2.setCustomValidity(password2.value !== password1.value ? "Passwords do not match." : "")'
          >
            <div class="col-6">
              <div>
                <div class="mb-2"><b>Password</b></div>
              </div>
              <label for="password1">New Password</label>
              <div class="form-group pass_show">
                <input
                    class="form-control validate"
                    id="password1"
                    name="password1"
                    placeholder="Password"
                    required
                    v-model="password1"
                    :value="password1"
                    :append-icon="value ? 'mdi-eye' : 'mdi-eye-off'"
                    @click:append="() => (value = !value)"
                    :type="value ? 'password' : 'text'"
                />
              </div>
            </div>
            <div class="col-6">
              <label for="password2" class="mt-8"
              >Confirm <span class="d-none d-xl-inline ">Password</span></label
              >
              <div class="form-group pass_show ">
                <input
                    class="form-control"
                    id="password2"
                    name="password2"
                    placeholder="Repeat password"
                    v-model="password2"
                    :value="password2"
                    :append-icon="value ? 'mdi-eye' : 'mdi-eye-off'"
                    @click:append="() => (value = !value)"
                    :type="value ? 'password' : 'text'"
                />
              </div>
            </div>
            <v-dialog v-model="dialog" persistent max-width="350">
              <template v-slot:activator="{ on, attrs }">
                <div class="text-right d-flex justify-content-end">
                  <div class="ml-4">
                    <v-btn
                        rounded
                        color="warning"
                        v-bind="attrs"
                        class="mr-3"
                        v-on="on"
                    >Change password</v-btn
                    >
                  </div>
                  <div>
                    <v-btn @click="UpadateInfo()" rounded color="success">Save</v-btn>
                  </div>

                </div>
              </template>
              <v-card>
                <v-card-title class="headline"
                >Do you want to change the password?</v-card-title
                >
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="error" text @click="dialog = false"
                  >Cancel</v-btn
                  >
                  <v-btn color="green darken-1" text @click="checkPass()"
                  >Agree</v-btn
                  >
                </v-card-actions>
              </v-card>
            </v-dialog>
          </form>
        </div>
      </form>
    </v-card>
    <v-snackbar v-model="snackbar" :color="snackColor">
      <div class="text-center">
        {{ text }}
      </div>
    </v-snackbar>
  </v-app>
</template>

<script>
import api from "@/components/backend-api.js";

// eslint-disable-next-line no-undef
$(document).ready(function(){
  // eslint-disable-next-line no-undef
  $('.pass_show').append('<span class="ptxt">Show</span>');
});


// eslint-disable-next-line no-undef
$(document).on('click','.pass_show .ptxt', function(){

  // eslint-disable-next-line no-undef
  $(this).text($(this).text() == "Show" ? "Hide" : "Show");

  // eslint-disable-next-line no-undef
  $(this).prev().attr('type', function(index, attr){return attr == 'password' ? 'text' : 'password'; });

});
export default {
  name: "edit",
  data() {
    return {
      snackColor: "green",
      error: false,
      snackbar: false,
      text: "asd",
      dialog: false,
      hidden: true,
      UserInfo: [],
      id: 0,
      firstName: "",
      lastName: "",
      email: "",
      skypeId: "",
      password: "",
      password1: "",
      password2: "",
      notificationEnabled: false,
      dateToChange: new Date().toISOString().substr(0, 10),
      value: true,
      menu2: false,
      modal: false,
    };
  },
  /*  created () {
  this.fetchAuthenticatedUser()
  },*/
  methods: {
    GetUserInfo(user_id) {
      api
          .getUser(user_id)
          .then(response => {
            console.log(response.data);
            this.firstName = response.data.firstName;
            this.lastName = response.data.lastName;
            this.skypeId = response.data.skypeId;
            this.email = response.data.email;
            this.dateToChange = response.data.dateToChange;
            this.notificationEnabled = response.data.notificationEnabled;
          })
          .catch(error => {
            this.errors.push(error);
          });
    },

    displayUser() {
      this.firstName = this.UserInfo.firstName;
      this.lastName = this.UserInfo.lastName;
      this.skypeId = this.UserInfo.skypeId;
      this.email = this.UserInfo.email;
      this.notificationEnabled = this.UserInfo.notificationEnabled;
      this.dateToChange = this.UserInfo.dateToChange;
    },

    makeOrder() {
      api
          .createOrder(1, this.product_data.menuTypes[this.type_id].id)
          .then(response => {
            this.response = response.data;
          })
          .catch(e => {
            this.errors.push(e);
          });
    },

    UpadateInfo() {
      this.error = false;

      if (this.firstName.length === 0) {
        this.error = true;
        this.snackbar = true;
        this.snackColor = "red";
        this.text = "First name can't be empty";
      }

      if (this.lastName.length === 0) {
        this.error = true;
        this.snackColor = "red";

        this.snackbar = true;
        this.text = "Last name can't be empty";
      }

      if (this.email.length === 0) {
        this.error = true;
        this.snackbar = true;
        this.snackColor = "red";

        this.text = "Email can't be empty";
      }

      if (this.skypeId.length === 0) {
        this.error = true;
        this.snackbar = true;
        this.snackColor = "red";

        this.text = "Skype id can't be empty";
      }

      if (!this.error) {
        this.snackbar = true;

        this.snackColor = "green";

        this.text = "Success";

        api
            .updateUser(
                this.firstName,
                this.lastName,
                this.skypeId,
                this.email,
                this.notificationEnabled,
                this.dateToChange
            )
            .then(response => {
              this.response = response.data;
            })
            .catch(e => {
              this.errors.push(e);
            });
      }
    },

    checkPass() {
      let passError = false;

      if (this.password1 !== this.password2) {
        passError = true;
        this.snackbar = true;
        this.text = "Passwords are different";
        this.snackColor = "red";
      }
      if (this.password1.length < 5) {
        passError = true;
        this.snackbar = true;
        this.snackColor = "red";
        this.text = "Passwords can't be smaller than 5";
      }
      if (this.password2.length === 0) {
        passError = true;
        this.snackbar = true;
        this.snackColor = "red";
        this.text = "Repeat password please!";
      }
      this.dialog = false;
      if (!passError) {
        this.snackColor = "green";
        api.changePass(this.password1);
        this.$router.push("/login");
      }
    }
  },
  beforeCreate() {
    api
        .getUserWithoutId()
        .then(response => {
          console.log(response.data);
          this.id = response.data.id;
          this.firstName = response.data.firstName;
          this.lastName = response.data.lastName;
          this.skypeId = response.data.skypeId;
          this.email = response.data.email;
          this.dateToChange = response.data.dateToChange;
          this.notificationEnabled = response.data.notificationEnabled;
        })
        .catch(error => {
          this.errors.push(error);
        });
  }
};
</script>
<style>
::-webkit-scrollbar {
  width: 0;
  background: yellow;
  display: inline !important;
}
.pass_show{position: relative}

.pass_show .ptxt {

  position: absolute;

  top: 50%;

  right: 10px;

  z-index: 1;

  color: #f36c01;

  margin-top: -10px;

  cursor: pointer;

  transition: .3s ease all;

}

.pass_show .ptxt:hover{color: #333333;}
</style>
