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
        <div class="col-lg-6" id="Password">
          <div class="mb-2"><b>Password</b></div>
          <form
            oninput='password2.setCustomValidity(password2.value !== password1.value ? "Passwords do not match." : "")'
          >
            <label for="password1">New Password</label>
            <input
              class="form-control validate"
              id="password1"
              name="password1"
              type="password"
              placeholder="Password"

              required
              v-model="password1"
            />

            <label for="password2" class="mt-2"
              >Confirm <span class="d-none d-xl-inline">Password</span></label
            >
            <input
              class="form-control"
              id="password2"
              name="password2"
              type="password"
              placeholder="Repeat password"
              v-model="password2"
            />
            <v-dialog v-model="dialog" persistent max-width="350">
              <template v-slot:activator="{ on, attrs }">
                <div class="text-right">
                  <v-btn
                    rounded
                    color="warning"
                    class="mt-3"
                    v-bind="attrs"
                    v-on="on"
                  >Change password</v-btn
                  >
                </div>
              </template>
              <v-card>
                <v-card-title class="headline"
                  >Do you want to change the password?</v-card-title
                >
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="green darken-1" text @click="dialog = false"
                    >Disagree</v-btn
                  >
                  <v-btn color="green darken-1" text @click="checkPass()"
                    >Agree</v-btn
                  >
                </v-card-actions>
              </v-card>
            </v-dialog>
          </form>
        </div>
        <div class="col-lg-6" id="Notification">
          <b>Keeping in Touch</b>
          <v-switch
            v-model="enableNotification"
            color="orange"
            :label="`Notification: ${enableNotification.toString()}`"
          >
          </v-switch>

          <div v-if="enableNotification === false">
            <label class=" mt-4">
              Data to enable
            </label>
            <input
              class="form-control "
              type="date"
              name="dataNotification"
              v-model="dataNotification"
            />
          </div>
        </div>

        <div class="col-12 text-right">
          <v-btn @click="UpadateInfo()" rounded color="warning">Save</v-btn>
          <v-snackbar v-model="snackbar">
            <div class="text-center">
              {{ text }}
            </div>
          </v-snackbar>
        </div>
      </form>
    </v-card>
  </v-app>
</template>

<script>
import api from "@/components/backend-api.js";

export default {
  name: "edit",
  data() {
    return {
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
      enableNotification: true,
      dataNotification: ""
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
          this.dataNotification = response.data.dataNotification;
          this.enableNotification = response.data.enableNotification;
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
      this.enableNotification = this.UserInfo.enableNotification;
      this.dataNotification = this.UserInfo.dataNotification;
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
        this.text = "First name can't be empty";

      }

      if (this.lastName.length === 0){
        this.error = true;
        this.snackbar = true;
        this.text = "Last name can't be empty";

      }

      if(this.email.length === 0){
        this.error = true;
        this.snackbar = true;
        this.text = "Email can't be empty";

      }

      if(this.skypeId.length === 0){
        this.error = true;
        this.snackbar = true;
        this.text = "Skype id can't be empty";
      }

        if (!this.error) {
          this.snackbar = true;
          this.text = "Success";


          api
            .updateUser(
              this.firstName,
              this.lastName,
              this.skypeId,
              this.email,
              this.enableNotification,
              this.dataNotification
            )
            .then(response => {
              this.response = response.data;
            })
            .catch(e => {
              this.errors.push(e);
            });
          window.location.reload();
        }
    },

    checkPass() {
      // if (this.password1 === this.password2 && this.password1.length > 7) {
      //alert("Changing password");
      let passError = false;

      if(this.password1 !== this.password2){
        passError = true;
        this.snackbar = true;
        this.text = "Passwords are different";
      }

      if(this.password1.length < 5){
        passError = true;
        this.snackbar = true;
        this.text = "Passwords can't be smaller than 5";
      }
      this.dialog = false;
      if(!passError){
      api.changePass(this.password1);
      this.$router.push("/login");
      }
      // } else {
      //    alert("Cannot change");
      // }
    }
  },

  /*fetchAuthenticatedUser () {
      this.$store.dispatch('updateUser',{
         firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
          skypeId: this.skypeId,
      })
      .then(Response => {
          console.log(Response);
          //   this.$router.push("/");
        })*/

  //     const token = localStorage.getItem('tweetr-token')

  //     axios
  //         .get('account/me', {
  //             headers: {
  //                 Authorization: `Bearer ${token}`
  //             }
  //         })
  //         .then(response => {
  //             this.name = response.data.data.name
  //             this.username = response.data.data.username
  //             this.email = response.data.data.email
  //             this.location = response.data.data.location
  //             this.bio = response.data.data.bio
  //             this.websiteUrl = response.data.data.website_url
  //         })

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
      })
      .catch(error => {
        this.errors.push(error);
      });
  }
};
</script>
<style scoped></style>
