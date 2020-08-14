<template>
<div class="container">
<div class="row flex-lg-nowrap">
  <div class="col">
    <div class="row">
      <div class="col mb-3">
        <div class="card">
          <div class="card-body">
            <div class="e-profile">
              <!-- <div class="row">
                <div class="col d-flex flex-column flex-sm-row justify-content-between mb-3">
                  <div class="text-center text-sm-left mb-2 mb-sm-0">
                    <div class="field" :class="{error: errors.has('name')}">
                      <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">John Smith</h4>
                      <p class="mb-0">@user</p>
                    </div>
                  </div>
                  <div class="text-center text-sm-right">
                    <span class="badge badge-secondary">Role</span>
                  </div>
                </div>
              </div> -->
              <ul class="nav nav-tabs">
                <li class="nav-item"><a href="" class="active nav-link">Settings</a></li>
              </ul>
              <div class="tab-content pt-3">
                <div class="tab-pane active">
                  <form class="form" novalidate="">
                    <div class="row">
                      <div class="col">
                        <div class="row">
                          <div class="col">
                            <div class="form-group" :class="{ error: errors.has('firstName')}">
                              <label>First Name</label>
                              <input class="form-control" type="text" name="firstName" v-model="firstName" v-validate="'required'">
                              <span v-show="errors.has('firstName')" class = "is-danger"> {{errors.first('firstName')}}</span>
                            </div>
                          </div>
                          <div class="col">
                            <div class="form-group">
                              <label>Last Name</label>
                              <input class="form-control" type="text" name="username" placeholder="johnny.s" value="johnny.s">
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col">
                            <div class="form-group">
                              <label>Email</label>
                              <input class="form-control" type="text" placeholder="user@example.com">
                            </div>
                          </div>
                        <!-- </div> -->
                        <!-- <div class="row">
                          <div class="col mb-3">
                            <div class="form-group">
                              <label>Employment Data</label>
                              <input class="form-control" type="text" placeholder="data">
                            </div>
                          </div>
                        </div> -->
                        <!-- <div class="row"> -->
                          <div class="col mb-3">
                            <div class="form-group">
                              <label>Skype Id</label>
                              <input class="form-control" type="text" placeholder="skype Id">
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-12 col-sm-6 mb-3">
                        <div class="mb-2"><b>Change Password</b></div>
                        <div class="row">
                          <div class="col">
                            <div class="form-group">
                              <label>Current Password</label>
                              <input class="form-control" type="password" placeholder="••••••">
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col">
                            <div class="form-group">
                              <label>New Password</label>
                              <input class="form-control" type="password" placeholder="••••••">
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col">
                            <div class="form-group">
                              <label>Confirm <span class="d-none d-xl-inline">Password</span></label>
                              <input class="form-control" type="password" placeholder="••••••"></div>
                          </div>
                        </div>
                      </div>
                      <div class="col-12 col-sm-3 offset-sm-1 mb-3">
                        <div class="mb-2"><b>Keeping in Touch</b></div>
                        <div class="row">
                          <div class="col">
                            <div class="custom-control custom-switch">
                              <input type="checkbox" class="custom-control-input" id="customSwitches">
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
                        <button class="btn btn-primary" type="submit">Save Changes</button>
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
</template>
<script>
import api from './backend-api';
export default {
  
  name: "edit",
  data () {
    return {
    firstName: '', 
    lastName: '',
    email: '',
    skypeId: ''
    }
  },
  beforeRouteEnter (to, from, next) {
    const token = localStorage.getItem('tweetr-token')

    return token ? next() : next('/login')
  },
  created () {
    this.fetchAuthenticatedUser()
  },
  // methods: {
  //   submitHandler() {
  //     this.$store
  //       .dispatch("editUser", {
  //         firstName: this.firstName,
  //         lastName: this.lastName,
  //         email: this.email,
  //         employmentDate: this.employmentDate,
  //         skypeId: this.skypeId,
  //         password: this.password
  //       })
  //       .then((Response) => {
  //         console.log(Response);
  //       })
  //       .catch((error) => {
  //         console.log(error);
  //         this.loginError = true;
  //         this.errors.push(error);
  //         this.error = true;
  //       });
  //   },
  // },
  methods: {
      fetchAuthenticatedUser () {
          const token = localStorage.getItem('access_token')

          // axios
          //     .get('users/currentUser', {
          //         headers: {
          //             Authorization: `Bearer ${token}`
          //         }
          //     })
          //     .then(response => {
          //       this.firstName = response.data.data.firstName
          //       this.lastName = response.data.data.lastName
          //       this.email = response.data.data.email
          //       this.skypeId = response.data.data.skypeId
          //     })
          api.getUser().then(response => {
                this.firstName = response.data.data.firstName
                this.lastName = response.data.data.lastName
                this.email = response.data.data.email
                this.skypeId = response.data.data.skypeId
              })
      },
      updateProfile () {
          const token = localStorage.getItem('access_token')

      //     axios
      //         .put(
      //             'users/editUser',
      //             {
      //                 name: this.name,
      //                 username: this.username,
      //                 email: this.email,
      //             },
      //             {
      //                 headers: {
      //                     Authorization: `Bearer ${token}`
      //                 }
      //             }
      //         )
      //         .then(response => {
      //             // display success notification
      //             this.notification = Object.assign({}, this.notification, {
      //               message: response.data.message,
      //               type: 'success'
      //             })
      //         })
       }
  }

};
</script>

<style scoped></style>
