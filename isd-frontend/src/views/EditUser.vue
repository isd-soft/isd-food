<template>
<v-app style="background: none">
<div class="container">
<div class="row flex-lg-nowrap">
  <div class="col">
    <div class="row">
      <div class="col mb-3">
        <div class="card">
          <div class="card-body">
            <div class="e-profile">

              <div class="tab-content pt-3">
                <div class="tab-pane active">
                  <form class="form" novalidate="">
                    <div class="row">
                      <div class="col">
                        <div class="row">
                          <div class="col">
                            <div class="form-group" >
                              <label>First Name</label>
                              <input class="form-control" type="text" name="firstName" v-model="firstName" >

                            </div>
                          </div>
                          <div class="col">
                            <div class="form-group">
                              <label>Last Name</label>
                              <input class="form-control" type="text" name="lastName" v-model="lastName" >
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col">
                            <div class="form-group">
                              <label>Email</label>
                              <input class="form-control" type="text" name="email" v-model="email">
                            </div>
                          </div>
                          <div class="col mb-3">
                            <div class="form-group">
                              <label>Skype Id</label>
                              <input class="form-control" type="text" name="skypeId" v-model="skypeId">
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-12 col-sm-6 mb-3">
                        <div class="mb-2"><b>Password</b></div>
                        <v-btn @click="hidden =!hidden" rounded color="warning">Change password</v-btn>
                        <div class="row">
                          <div class="col">
                          </div>
                        </div>
                        <div v-if="!hidden" class="password">
                        <div class="row">
                          <div class="col">
                            <div class="form-group">
                              <label>New Password</label>
                              <input class="form-control" name="password1" type="password" placeholder="" v-model="password1">
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col">
                            <div class="form-group">
                              <label>Confirm <span class="d-none d-xl-inline">Password</span></label>
                              <input class="form-control" name="password2" type="password" placeholder="" v-model="password2"></div>
                          </div>
                        </div>
                        <v-btn @click="checkPass()" rounded color="warning">Change</v-btn>
                        </div>
                      </div>
                      <div class="col-12 col-sm-3 offset-sm-1 mb-3">
                        <div class="mb-2"><b>Keeping in Touch</b></div>
                        <div class="row">
                          <div class="col">
                            <div class="custom-control custom-switch" >
                              <input type="checkbox" class="custom-control-input  warning"  id="customSwitches">
                              <label class="custom-control-label" for="customSwitches">Notifications</label>
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col mb-1/2">
                              <label for="Data to disable">Data to disable:</label>
                              <input type="date" id="Data to disable" name="Data to disable">
                          </div>
                        </div>
                        <label for="Data to enable">Data to enable:</label>
                        <input type="date" id="Data to enable" name="Data to enable">
                      </div>
                    </div>
                    <div class="row">
                      <div class="col d-flex justify-content-end">
                      <!-- <button class="btn btn-primary" type="submit">Save Changes</button>-->
                      <v-btn @click="UpadateInfo()" rounded color="warning">Edit info</v-btn>
                      </div>
                    </div>
                  </form>

                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</div>
</div>
</v-app>
</template>

<script>

import api from '@/components/backend-api.js';

export default {
  name: 'edit',
  data () {
    return {
        hidden: true,
        UserInfo: [],
        firstName: '',
        lastName: '',
        email: '',
        skypeId: '',
        password: '',
        password1: "",
        password2: "",
        
    }
  },
/*  created () {
  this.fetchAuthenticatedUser()
  },*/
  methods: {

    GetUserInfo(user_id) {
      api.getUser(user_id).then(response => {
            console.log(response.data);
            this.firstName = response.data.firstName;
            this.lastName = response.data.lastName;
            this.skypeId = response.data.skypeId;
            this.email = response.data.email
        })
        .catch(error => {
          this.errors.push(error)
        })
      },

      displayUser(){
        this.firstName = this.UserInfo.firstName
        this.lastName = this.UserInfo.lastName
        this.skypeId = this.UserInfo.skypeId
        this.email = this.UserInfo.email
      },

            makeOrder() {
        api.createOrder(1, this.product_data.menuTypes[this.type_id].id).then(response => {
            this.response = response.data;
          })
          .catch(e => {
            this.errors.push(e)
          })
      },

      UpadateInfo(){
        api.updateUser(1, this.firstName, this.lastName, this.skypeId, this.email).then(response => {
            this.response = response.data;
          })
          .catch(e => {
            this.errors.push(e)
          })
      },

          checkPass(){
      if(this.password1 === this.password2 && this.password1.length > 7){
        alert(("Меняем пароль"))
        api.changePass(1, this.password1)
        console.log("Меняем пароль")}
      else{
        console.log("Не меняем пароль")
        alert(("Мы не меняем пароль"))
      }
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
  

  beforeCreate(){
          api.getUser(1).then(response => {
            console.log(response.data);
            this.firstName = response.data.firstName;
            this.lastName = response.data.lastName;
            this.skypeId = response.data.skypeId;
            this.email = response.data.email
        })
        .catch(error => {
          this.errors.push(error)
        })
  } 
}
</script>
<style scoped> </style>