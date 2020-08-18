<template>
  <div>
    <div class="card shadow mb-4">
      <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-warning">Users</h6>
        <button style="float: right;" class="button button1" onclick="window.location.pathname= '/users/register'">Add new user</button>
      </div>
      <div class="card-body">
        <div  class="table-responsive">
          <div  class="tab-content" id="pills-tabContent">
            <div  class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
              <table class="table table-bordered"  width="100%" cellspacing="0">
                <thead>
                <tr>
                  <th>Id</th>
                  <th>Email</th>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Employment Date </th>
                  <th>skypeId</th>
                </tr>
                </thead>
                <tbody >
                <tr v-for="user of Users" :key="user.id">
                  <td>{{user.id}}</td>
                  <td>{{user.email}}</td>
                  <td>{{user.firstName}}</td>
                  <td>{{user.lastName}}</td>
                  <td>{{user.employmentDate}}</td>
                  <td>{{user.skypeId}}</td>
                  <td class="text-center">
                    <button type="submit" @click="deleteUser(user.id)" onclick="window.location.reload();">
                      <v-icon  data-toggle="modal"  data-target="#exampleModal">fas fa-trash-alt</v-icon>
                    </button>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import api from "@/components/backend-api.js"

export default {

  name: "Home",
  data (){
    return{
      Users:[],
      getUsers: false,
    }
  },
  mounted() {
    this.getAllUsers();
  },

  methods:{
    deleteUser(id){
      api.deleteUser(id);
    },
    getAllUsers() {
      api.getUsers().then(response => {this.Users = response.data;
        console.log(response.data)})
    }
  }
};

</script>

<style>
.button1 {
  background-color: orange;
  color: white;
  border: 2px solid orange;
}

.button1:hover {
  background-color: darkorange;
  color: white;
}
</style>
