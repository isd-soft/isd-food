<template>
  <div>
    <div class="row ">
      <div class="col-xl-12 col-md-12">
        <div class="card border-left-warning shadow h-100 py-1">

          <div class="card-body wrapScroll">
            <table class="table table-bordered" cellspacing="0">
              <thead>
                <tr>
                  <th>Email</th>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Employment Date</th>
                  <th>Active</th>
                  <th class="text-center">Delete</th>
                  <th>Edit</th>
                </tr>
              </thead>

              <tbody v-for="user of users" :key="user.id">
                <tr>
                  <td>{{ user.email }}</td>
                  <td>{{ user.firstName }}</td>
                  <td>{{ user.lastName }}</td>
                  <td>{{ user.employmentDate }}</td>
                  <td v-if="user.enabled === true" class="text-center">
                    <i
                      class="fas fa-check-circle"
                      style="margin: 0; padding: 0;color: green !important; font-size: 25px"
                    ></i>
                  </td>
                  <td v-else class="text-center">
                    <i
                      class="fas fa-times-circle"
                      style="margin: 0; padding: 0;color: red !important; font-size: 25px"
                    ></i>
                  </td>

                  <td class="text-center">
                    <button
                      style="outline: none"
                      @click="deleteUser(user.id)"
                      onclick="window.location.reload()"
                    >
                      <i class="fas fa-trash"></i>
                    </button>
                  </td>

                  <td>
                    <v-app
                      style="background: none; height: content-box !important; max-height: 30px"
                      class="text-center"
                    >
                      <v-dialog v-model="dialogNote[user.id]" width="600">
                        <template v-slot:activator="{ on, attrs }">
                          <button
                            style="background : none !important;box-shadow: none;color: grey; outline: none"
                            v-bind="attrs"
                            v-on="on"
                          >
                            <i
                              class="fas fa-edit"
                              style="margin: 0 !important;padding: 0 !important"
                            ></i>
                          </button>
                        </template>

                        <v-card>
                          <v-card-title
                            class="headline orange lighten-2"
                            style="color: white"
                          >
                            {{ user.email }}
                          </v-card-title>

                          <v-card-text>
                            <div class="container text-left">
                              <div class="form-row">
                                <div class="form-group col-md-6">
                                  <label>First Name</label>
                                  <input
                                    class="form-control"
                                    type="text"
                                    name="firstName"
                                    v-model="user.firstName"
                                  />
                                </div>
                                <div class="form-group col-md-6">
                                  <label>Last Name</label>
                                  <input
                                    class="form-control"
                                    type="text"
                                    name="lastName"
                                    v-model="user.lastName"
                                  />
                                </div>
                              </div>
                              <div class="form-row">
                                <div class="form-group col-md-6">
                                  <label>Email</label>
                                  <input
                                    class="form-control"
                                    type="text"
                                    name="email"
                                    v-model="user.email"
                                  />
                                </div>
                                <div class="form-group col-md-6">
                                  <label>Skype Id</label>
                                  <input
                                    class="form-control"
                                    type="text"
                                    name="skypeId"
                                    v-model="user.skypeId"
                                  />
                                </div>
                              </div>
                              <div class="form-row">
                                <div class="col mb-6">
                                  <div class="form-group">
                                    <v-menu
                                      v-model="menu2"
                                      :close-on-content-click="false"
                                      :nudge-right="40"
                                      transition="scale-transition"
                                      offset-y
                                      full-width
                                      min-width="290px"
                                    >
                                      <template
                                        v-slot:activator="{ on, attrs }"
                                      >
                                        <v-text-field
                                          v-model="user.employmentDate"
                                          label="Employment Date"
                                          readonly
                                          v-bind="attrs"
                                          v-on="on"
                                        ></v-text-field>
                                      </template>
                                      <v-date-picker
                                        id="emp-date-picker"
                                        v-model="user.employmentDate"
                                        @input="menu2 = false"
                                        color="warning"
                                        @change="formatDate(user.employmentDate)"
                                        width="400"
                                        scrollable
                                        default
                                        elevation="15"
                                      ></v-date-picker>
                                    </v-menu>
                                  </div>
                                </div>
                                <div class="form-group col-md-6">
                                  <v-select
                                    v-model="user.role.name"
                                    :items="role_items"
                                    label="Role"
                                    required
                                  ></v-select>
                                </div>
                              </div>
                              <div class="form-group ml-1">
                                <div class="form-check">
                                  <input
                                    class="form-check-input"
                                    type="checkbox"
                                    v-model="user.enabled"
                                    required
                                    id="gridCheck"
                                  />
                                  <label
                                    class="form-check-label"
                                    for="gridCheck"
                                  >
                                    Active
                                  </label>
                                </div>
                              </div>
                            </div>
                          </v-card-text>

                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn
                              color="primary"
                              text
                              @click.stop="$set(dialogNote, user.id, false)"
                            >
                              Close
                            </v-btn>
                            <v-btn
                              color="warning"
                              text
                              @click="
                                editUser(
                                  user.id,
                                  user.firstName,
                                  user.lastName,
                                  user.skypeId,
                                  user.email,
                                  user.role.name,
                                  user.enabled,
                                  user.employmentDate
                                )
                              "
                            >
                              Save
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </v-app>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    <v-snackbar v-model="snackbar">
      <div class="text-center">
        {{ text }}
      </div>
    </v-snackbar>
    <router-link class="btn btn-warning mb-3 btn-lg" style="position: absolute;right: 10px; position: fixed;bottom: 0px; border-radius: 100px;padding: 20px 23px;font-size: 20px !important;" :to="'/users/register'"><i class="fas fa-user-plus"></i></router-link>

  </div>
</template>

<script>
import api from "@/components/backend-api.js";

export default {
  name: "UsersTable",
  data() {
    return {
      dialogNote: {},
      users: [],
      dialog: false,
      snackbar: false,
      text: "asd",
      formattedDate: "",
      menu2: false,
      modal: false,
      role_items: ["ROLE_user", "ROLE_supervisor"]
    };
  },
  methods: {
    formatDate(date) {
      let array = date.split("-");
      date = array[2] + "-" + array[1] + "-" + array[0];
    },
    editUser(
      id,
      firstName,
      lastName,
      skypeId,
      email,
      role,
      enabled,
      employmentDate
    ) {
      api.updateUserBySupervisor(
        id,
        firstName,
        lastName,
        skypeId,
        email,
        role,
        enabled,
        employmentDate
      );
      window.location.reload();
    },
    deleteUser(id) {
      api.deleteUser(id);
    }
  },

  beforeCreate() {
    api.getUsers().then(response => {
      this.users = response.data;
      console.log(response.data);
    });
  }
};
</script>

<style scoped>
#emp-date-picker::v-deep .v-date-picker-table {
  height: auto;
}

#emp-date-picker::v-deep .v-date-picker-title__date {
  display: none !important;
}
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

input[type="number"] {
  -moz-appearance: textfield;
}
.wrapScroll{
  overflow: auto;
  white-space: nowrap;

}
</style>
