<template>
  <v-app>
    <div>
      <div class="row">
        <div class="col-xl-12 col-md-12">
          <div class="card border-left-warning shadow h-100 py-1">
            <div class="card-body">
              <table class="table " cellspacing="0">
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

                <confirmationDialog
                  :action-button="'Agree'"
                  :method="deleteUser"
                  :title="title"
                  :message="message + currentName + '?'"
                  :dialog1.sync="dialog1"
                />

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
                        @click="
                          openDialog(
                            user.id,
                            user.firstName + ' ' + user.lastName
                          )
                        "
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
                              <v-col class="col-sm-12">
                                <v-text-field
                                  v-model="user.firstName"
                                  label="First name"
                                  :rules="firstNameRules"
                                  required
                                ></v-text-field>

                                <v-text-field
                                  v-model="user.lastName"
                                  label="Last name"
                                  :rules="lastNameRules"
                                  required
                                ></v-text-field>

                                <v-text-field
                                  v-model="user.email"
                                  :rules="emailRules"
                                  label="Email"
                                  required
                                ></v-text-field>

                                <v-text-field
                                  v-model="user.skypeId"
                                  label="Skype Id"
                                  required
                                ></v-text-field>

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
                                      v-model="user.employmentDate"
                                      label="Employment Date"
                                      readonly
                                      v-bind="attrs"
                                      v-on="on"
                                    ></v-text-field>
                                  </template>
                                  <v-date-picker
                                    v-model="user.employmentDate"
                                    @input="menu2 = false"
                                    color="warning"
                                    value="dd-mm-yy"
                                    :max="nowDate"
                                  ></v-date-picker>
                                </v-menu>

                                <v-select
                                  v-model="user.role.name"
                                  :items="role_items"
                                  label="Role"
                                  required
                                ></v-select>
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
                              </v-col>
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
              <Pagination
                class="pb-15 pt-5"
                @pageChanged="setPage"
                :page="page"
                :totalPages="users.totalPages"
              />
            </div>
          </div>
        </div>
      </div>
      <v-snackbar v-model="snackbar">
        <div class="text-center">
          {{ text }}
        </div>
      </v-snackbar>
      <router-link
        class="btn btn-warning mb-3 btn-lg"
        style="position: absolute;right: 10px; position: fixed;bottom: 0px; border-radius: 100px;padding: 20px 23px;font-size: 20px !important;"
        :to="'/users/register'"
        ><i class="fas fa-user-plus"></i
      ></router-link>
    </div>
  </v-app>
</template>

<script>
import api from "@/components/backend-api.js";
import confirmationDialog from "../components/confirmationDialog";
import Pagination from "@/components/Pagination";

export default {
  name: "UsersTable",
  components: {
    confirmationDialog,
    Pagination
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
      firstNameRules: [
        v => !!v || "First Name is required",
        v => /^[A-Z]+[a-z]+$/.test(v) || "First Name must be only letters, big and small"
      ],
      lastNameRules: [
        v => !!v || "First Name is required",
        v => /^[A-Z]+[a-z]+$/.test(v) || "First Name must be only letters, big and small"
      ],
      emailRules: [
        v => !!v || "Email is required",
        v => /.+@.+\..+/.test(v) || "Email must be valid"
      ]
    };
  },
  methods: {
    setPage(page) {
      if (page !== this.page) {
        window.scrollTo({
          top: 0,
          left: 0,
          behavior: "smooth"
        });
        this.page = page;
        api.getUsers(this.page).then(response => {
          this.users = response.data;
        });
      }
    },
    openDialog(id, name) {
      this.dialog1 = true;
      this.currentId = id;
      this.currentName = name;
      console.log(this.currentName);
      console.log(this.currentId);
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

    deleteUser() {
      api.deleteUser(this.currentId);
    }
  },

  beforeCreate() {
    api.getUsers(this.page).then(response => {
      this.users = response.data;
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

/*.wrapScroll{*/
/*  overflow: auto;*/
/*  white-space: nowrap;*/
/*}*/
</style>
