<template>
  <div>
    <div class="card border-left-warning shadow mb-4">
      <div class="card-body">
        <div >
          <div class="tab-content" id="pills-tabContent">
            <!---Current--->
            <div
              class="tab-pane fade show active"
              id="pills-home"
              role="tabpanel"
              aria-labelledby="pills-home-tab"
            >
              <confirmationDialog @closeDeleteDialog="dialog1 = false" :action-button="'Agree'" :method="deleteMenu" :title="title" :message="message + currentName+ '?'" :dialog1.sync="dialog1"/>
                <table class="table table-bordered" width="100%" cellspacing="0">
                <thead>
                  <tr>
                    <th>Name</th>
                    <th>Provider</th>
                    <th>Day</th>
                    <th>Available</th>
                    <th>Delete</th>
                    <th>Edit</th>
                  </tr>
                </thead>
                <tbody v-for="menu of menus" :key="menu.id">
                  <td>{{ menu.name }}</td>
                  <td>{{ menu.provider.name }}</td>
                  <td>{{ menu.dayOfWeek }}</td>
                  <td v-if="menu.active === true" class="text-center">
                    <i
                      class="fas fa-check-circle"
                      style="margin: 0; padding: 0;color: #008000 !important; font-size: 25px"
                    ></i>
                  </td>
                  <td v-else class="text-center">
                    <i
                      class="fas fa-times-circle"
                      style="margin: 0; padding: 0;color: rgb(255,0,0) !important; font-size: 25px"
                    ></i>
                  </td>

                  <td class="text-center">
                    <button
                      style="outline: none"
                      @click="openDialog(menu.id, menu.name)"
                    >
                      <i class="fas fa-trash"></i>
                    </button>
                  </td>

                  <!--  <td>
                      <button style="background : none !important;box-shadow: none;color: grey; outline: none"

                              v-bind="attrs"
                              v-on="on"
                      >
                          <i class="fas fa-edit" style="margin: 0 !important;padding: 0 !important"></i>
                      </button>
                  </td>-->

                  <td>
                    <v-app
                      style="background: none; height: content-box !important; max-height: 30px"
                      class="text-center"
                    >

                      <v-dialog v-model="dialogNote[menu.id]" width="500">
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
                            {{ menu.name }}
                          </v-card-title>

                          <v-card-text>
                            <v-text-field
                              ref="name"
                              v-model="menu.name"
                              :counter="20"
                              label="Menu name"
                              placeholder="Enter name"
                              required
                            ></v-text-field>

                            <v-autocomplete
                              ref="provider"
                              return-object
                              v-model="menu.provider"
                              :items="providers"
                              item-text="name"
                              label="Provider"
                              placeholder="Select..."
                              required
                            ></v-autocomplete>

                            <v-autocomplete
                              ref="weekday"
                              v-model="menu.dayOfWeek"
                              :items="days"
                              label="Day"
                              placeholder="Select..."
                              required
                            ></v-autocomplete>

                            <v-text-field
                              ref="image"
                              v-model="menu.image"
                              label="Image"
                            ></v-text-field>

                            <br />
                            <h4>Menu type S</h4>

                            <v-container fluid>
                              <v-select
                                return-object
                                v-model="menu.menuTypes[0].items"
                                :items="items"
                                item-text="name"
                                label="Items"
                                multiple
                                required
                              >
                                <template v-slot:prepend-item>
                                  <v-list-item ripple>
                                    <v-list-item-content>
                                      <v-list-item-title
                                        >Choose items:</v-list-item-title
                                      >
                                    </v-list-item-content>
                                  </v-list-item>

                                  <v-divider class="mt-2"></v-divider>
                                </template>
                              </v-select>
                            </v-container>

                            <v-text-field
                              ref="priceS"
                              v-model="menu.menuTypes[0].price"
                              label="price"
                              required
                              placeholder="Enter price"
                            ></v-text-field>

                            <br />
                            <h4>Menu type M</h4>
                            <p>All items from S will be added automatically.</p>

                            <v-container fluid>
                              <v-select
                                return-object
                                v-model="menu.menuTypes[1].items"
                                :items="items"
                                item-text="name"
                                label="Items"
                                multiple
                                required
                              >
                                <template v-slot:prepend-item>
                                  <v-list-item ripple>
                                    <v-list-item-content>
                                      <v-list-item-title
                                        >Choose items:</v-list-item-title
                                      >
                                    </v-list-item-content>
                                  </v-list-item>

                                  <v-divider class="mt-2"></v-divider>
                                </template>
                              </v-select>

                              <v-text-field
                                ref="priceS"
                                v-model="menu.menuTypes[1].price"
                                label="price"
                                required
                                placeholder="Enter price"
                              ></v-text-field>
                            </v-container>

                            <div class="form-group ml-1">
                              <div class="form-check">
                                <input class="form-check-input" type="checkbox" v-model="menu.active" required id="gridCheck">
                                <label class="form-check-label" for="gridCheck">
                                  Active
                                </label>
                              </div>
                            </div>

                          </v-card-text>

                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn
                              color="primary"
                              text
                              @click.stop="$set(dialogNote, menu.id, false)"
                            >
                              Close
                            </v-btn>
                            <v-btn color="warning"
                                   text
                                   @click = "changeMenu(menu)">
                              Save
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                        <v-snackbar
                                v-model="snackbar"
                                :color=snackbarColor
                        >
                          <div class="text-center">
                            {{ text }}
                          </div>
                        </v-snackbar>
                      </v-dialog>
                    </v-app>
                  </td>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
    <router-link class="btn btn-warning mb-3 btn-lg" style="position: absolute;right: 10px; position: fixed;bottom: 0px; border-radius: 100px;padding: 26px 23px;font-size: 20px !important;" :to="'/add_menu'"><i class="fas fa-hamburger"></i><i class="fas fa-plus ml-1" style="font-size:15px "> </i></router-link>

  </div>
</template>

<script>
  import api from "./backend-api";
  import confirmationDialog from "./ConfirmationDialog";


  export default {
    components: {
      confirmationDialog,
    },
  name: "Home",
  data() {
    return {
      currentName: null,
      currentId: null,
      title: "Confirmation",
      message: "Do you really want to delete ",
      dialog1: false,
      typeSId: null,
      typeMId: null,
      days: ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"],
      providers: [],
      items: [],
      dialogNote: {},
      dialog: false,
      menus: [],
      userHistory: [],
      getHistory: false,
      snackbar: false,
      snackbarColor: 'error',
      text: 'pcela',
    };
  },
  methods: {

    openDialog(id, name){
      this.dialog1 = true
      this.currentId = id
      this.currentName = name
      console.log(this.currentId)
    },


    callMenuApi() {
      api
        .getMenu()
        .then(response => {
          this.products = response.data;
          console.log(response.data);
        })
        .catch(error => {
          this.errors.push(error);
        });
    },

    deleteMenu() {
      api.deleteMenu(this.currentId).then(()=>{
        this.callMenuApi();
        this.dialog1 = false;
      });
      window.location.reload();

    },

    // changeMenu(menu){
    //   api.changeMenu(menu);
    //   window.location.reload();
    //
    // },

    changeMenu(menu) {
      let error = false

      if (menu.name.length === 0) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Where is name?"
        error = true
      }

      if (menu.provider.length === 0) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Who is provider?"
        error = true
      }

      if (menu.dayOfWeek === null) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Where is day?"
        error = true
      }

      if (menu.image === null) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Where is image?"
        error = true
      }

      if (menu.menuTypes[0].items.length === 0) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Items for S please"
        error = true
      }
      if (menu.menuTypes[1].items.length === 0) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Items for M please"
        error = true
      }
      if (menu.menuTypes[1].price.length === 0 || menu.menuTypes[1].price < 0) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Price M can't be negative"
        error = true
      }
      if (menu.menuTypes[0].price.length === 0 || menu.menuTypes[0].price < 0) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Price S can't be negative"
        error = true
      }

      if (!error) {
        this.snackbarColor = "success"
        this.snackbar = true;
        this.text = "Menu has been successfully updated!"

          api.changeMenu(menu).then(()=>{window.location.reload();})

      }
    }
  },
  beforeCreate() {
    api
      .getMenu()
      .then(response => {
        this.menus = response.data;
        console.log(response.data);
      })
      .catch(error => {
        this.errors.push(error);
      });
    api
      .getItems()
      .then(response => {
        this.items = response.data;
        console.log(response.data);
      })
      .catch(error => {
        this.errors.push(error);
      }),
      api
        .getProviders()
        .then(response => {
          this.providers = response.data;
          console.log(response.data);
        })
        .catch(error => {
          this.errors.push(error);
        });
  }
};

</script>
<style></style>
