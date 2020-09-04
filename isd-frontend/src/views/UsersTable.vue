<template>
  <v-app>
    <div>
      <div class="row">
        <div class="col-xl-12 col-md-12">
          <div class="card border-left-warning shadow h-100 py-1">

            <div class="card-body">
              <v-row justify="end">
                <v-autocomplete
                    class="col-3"
                    v-model="usersNames"
                    :items="searchedUsers"
                    :search-input.sync="searchName"
                    @update:search-input="searchUsers"
                    @input="setSearchedUsers"
                    @click:clear="getUsers"
                    hide-no-data
                    item-text="name"
                    item-value="id"
                    label="Employee"
                    prepend-icon="mdi-account"
                    placeholder="Search by name"
                    clearable
                    return-object
                ></v-autocomplete>
              </v-row>

              <table class="table " cellspacing="0">
                <thead>
                <tr>
                  <th>Email</th>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th><span name="sortByEmploymentDate" class="sort-column"
                            @click="switchDateSortDirection">Employment Date <span v-html="sortIcon"></span></span></th>
                  <th>Active</th>
                  <th class="text-center">Delete</th>
                  <th>Edit</th>
                </tr>
                </thead>

                <confirmationDialog :action-button="'Agree'" :method="deleteUser" :title="title"
                                    :message="message + currentName +'?'" :dialog1="dialog1"
                                    @closeDeleteDialog="dialog1 = false"/>

              <tbody v-for="user of users.content" :key="user.id">
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
                      @click="openDialog(user.id, user.firstName + ' '+ user.lastName)"
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
                                          :max="nowDate"
                                      ></v-date-picker>
                                    </v-menu>
                                  </div>
                                </div>
                                <div class="form-group col-md-6">
                                  <v-select
                                      v-model="user.roleName"
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
                                                            user.roleName,
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
            <Pagination class="pb-15 pt-5"
                        @pageChanged="setPage" :page="page"
                        :totalPages="users.totalPages"/>
          </div>
        </div>
      </div>
    </div>
    <v-snackbar v-model="snackbar">
      <div class="text-center">
        {{ text }}
      </div>
    </v-snackbar>
    <router-link class="btn btn-warning mb-3 btn-lg"
                 style="color: white;position: absolute;right: 10px; position: fixed;bottom: 0px; border-radius: 100px;padding: 20px 23px;font-size: 20px !important;"
                 :to="'/users/register'"><i class="fas fa-user-plus"></i></router-link>

  </div>
  </v-app>
</template>

<script>
import api from "@/components/backend-api.js";
import ConfirmationDialog from "../components/ConfirmationDialog";
import Pagination from "@/components/Pagination";

export default {
  name: "UsersTable",
  components: {
    ConfirmationDialog, Pagination
  },
  data() {
    return {
      nowDate: new Date().toISOString().slice(0, 10),
      currentName: null,
      currentId: null,
      title: "Confirmation",
      message: "Do you really want to delete ",
      dialog1: false,
      dialogNote: {},
      users: {},
      dialog: false,
      snackbar: false,
      text: "asd",
      formattedDate: "",
      menu2: false,
      modal: false,
      role_items: ["ROLE_user", "ROLE_supervisor"],
      page: 1,
      sortIcon: "<i class='fas fa-caret-up'/>",
      sortByEmploymentDate: "ASC",
      searchName: "",
      searchedUsers: [],
      usersNames: []
    };
  },
  methods: {
    setPage(page) {
      if (page !== this.page) {
        window.scrollTo({
          top: 0,
          left: 0,
          behavior: 'smooth'
        });
        this.page = page
        this.getUsers();
      }
    },
    switchDateSortDirection(e) {
      let varName = "sortByEmploymentDate";
      if (this[varName] == null) {
        this[varName] = "ASC"
        this.sortIcon = "<i class='fas fa-caret-up'/>"
      } else if (this[varName] === "ASC") {
        this[varName] = "DESC"
        this.sortIcon = "<i class='fas fa-caret-down'/>"
      } else {
        this[varName] = null
        this.sortIcon = "<i class='fas fa-sort'/>"
      }

      this.getUsers();

    },
    openDialog(id, name) {
      this.dialog1 = true;
      this.currentId = id;
      this.currentName = name;
    },

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
    getUsers() {
      let datSortParam = this.sortByEmploymentDate != null ? "&sort=employmentDate," + this.sortByEmploymentDate : "";

      api.getUsers({page: this.page - 1, employmentDateSort: datSortParam}).then(response => {
        this.users = response.data;
      });
    },
    searchUsers(name) {
      if (name !== "" && name !== null)
        api.searchUsers(name).then(r => {
          this.searchName = name;
          this.searchedUsers = r.data;
        })
    },
    setSearchedUsers(name) {
      this.searchName = name;
      this.getUser();
    },
    getUser() {
      if (this.searchName !== "" && this.searchName !== null && this.searchName !== undefined)
        api.getUserByName(this.searchName).then(r => {
          this.users.content = [r.data]
          this.searchedUsers = [];
        })
    },
    removeUserName(item) {
      const index = this.usersNames.indexOf(item)
      if (index >= 0) this.usersNames.splice(index, 1)
    },

    deleteUser() {
      api.deleteUser(this.currentId).then(() => {
        this.getUsers();
        this.dialog1 = false;
      });

    }

  },

  beforeMount() {
    this.getUsers();
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

/*.wrapScroll{*/
/*  overflow: auto;*/
/*  white-space: nowrap;*/
/*}*/
</style>
