<template>
<div class="container">
<div class="row flex-lg-nowrap">
  <div class="col">
    <div class="row">
      <div class="col mb-3">
        <div class="card">
          <div class="card-body">
            <div class="e-profile">        
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
export default {
  name: 'edit',
  data () {
    return {
        firstName: '',
        lastName: '',
        email: '',
        skypeId: '',
    }
  },
  created () {
  this.fetchAuthenticatedUser()
  },
  methods: {
    fetchAuthenticatedUser () {
      this.$store.dispatch('updateUser',{
         firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
          skypeId: this.skypeId,
      })
      .then(Response => {
          console.log(Response);
          //   this.$router.push("/");
        })

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
    }
  }
  
};
</script>
<style scoped> </style>