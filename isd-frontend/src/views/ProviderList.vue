<template>
  <div>
    <div class="row">
      <div class="col-xl-12 col-md-12 mb-4">
        <div class="card border-left-warning shadow h-100 py-1">
          <div class="card-body ">
            <confirmationDialog
              :action-button="'Agree'"
              :method="deleteProvider"
              :title="title"
              :message="message + currentName + '?'"
              :dialog1.sync="dialog1"
              @closeDeleteDialog="closeConfirmation()"
            />
            <table class="table table-bordered" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th>Provider name</th>
                  <th>Contact</th>
                  <th>Delivery price</th>
                  <th>Active</th>
                  <th class="text-center">Delete</th>
                  <th>Edit</th>
                </tr>
              </thead>

              <tbody v-for="provider of providers" :key="provider.id">
                <tr>
                  <td>{{ provider.name }}</td>
                  <td>{{ provider.contactInfo }}</td>
                  <td>{{ provider.deliveryPrice }}</td>
                  <td v-if="provider.active === true" class="text-center">
                    <i
                      class="fas fa-check-circle"
                      style="margin: 0; padding: 0;color: green !important; font-size: 25px"
                    ></i>
                  </td>
                  <td v-else class="text-center">
                    <i
                      class="fas fa-times-circle"
                      style="margin: 0; padding: 0;color: #ff0000 !important; font-size: 25px"
                    ></i>
                  </td>

                  <td class="text-center">
                    <button
                      style="outline: none"
                      @click="openDialog(provider.id, provider.name)"
                    >
                      <i class="fas fa-trash"></i>
                    </button>
                  </td>

                  <td>
                    <v-app
                      style="background: none; height: content-box !important; max-height: 30px"
                      class="text-center"
                    >
                      <v-dialog v-model="dialogNote[provider.id]" width="500">
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
                            {{ provider.name }}
                          </v-card-title>

                          <v-card-text>
                            <v-text-field
                              v-model="provider.name"
                              :counter="20"
                              :rules="nameRules"
                              label="Name"
                              required
                            ></v-text-field>

                            <v-text-field
                              v-model="provider.deliveryPrice"
                              :rules="deliveryPriceRules"
                              label="Delivery Price"
                              required
                            ></v-text-field>

                            <v-text-field
                              v-model="provider.contactInfo"
                              label="Contact Info"
                              required
                            ></v-text-field>

                            <v-text-field
                              v-model="provider.description"
                              label="Description"
                              required
                            ></v-text-field>

                            <v-text-field
                              v-model="provider.image"
                              :rules="imageRules"
                              label="Image"
                              required
                            ></v-text-field>

                            <div class="form-group ml-1">
                              <div class="form-check">
                                <input
                                  class="form-check-input"
                                  type="checkbox"
                                  v-model="provider.active"
                                  required
                                  id="gridCheck"
                                />
                                <label class="form-check-label" for="gridCheck">
                                  Active
                                </label>
                              </div>
                            </div>
                            <!--                            <div class="container text-left">-->
                            <!--                              <div class="form-row">-->
                            <!--                                <div class="form-group col-md-6">-->
                            <!--                                  <label for="inputEmail4">Provider</label>-->
                            <!--                                  <input-->
                            <!--                                    type="text"-->
                            <!--                                    class="form-control"-->
                            <!--                                    id="inputEmail4"-->
                            <!--                                    v-model="provider.name"-->
                            <!--                                    required-->
                            <!--                                  />-->
                            <!--                                </div>-->
                            <!--                                <div class="form-group col-md-6">-->
                            <!--                                  <label for="inputPassword4"-->
                            <!--                                    >Contact info</label-->
                            <!--                                  >-->
                            <!--                                  <input-->
                            <!--                                    type="text"-->
                            <!--                                    class="form-control"-->
                            <!--                                    id="inputPassword4"-->
                            <!--                                    v-model="provider.contactInfo"-->
                            <!--                                    required-->
                            <!--                                  />-->
                            <!--                                </div>-->
                            <!--                              </div>-->
                            <!--                              <div class="form-group">-->
                            <!--                                <label for="inputAddress">Description</label>-->
                            <!--                                <textarea-->
                            <!--                                  v-model="provider.description"-->
                            <!--                                  min-height="40px"-->
                            <!--                                  class="form-control"-->
                            <!--                                  id="inputAddress"-->
                            <!--                                ></textarea>-->
                            <!--                              </div>-->

                            <!--                              <div class="form-row">-->
                            <!--                                <div class="form-group col-md-6">-->
                            <!--                                  <label for="inputCity">Delivery price</label>-->
                            <!--                                  <input-->
                            <!--                                    type="text"-->
                            <!--                                    v-model="provider.deliveryPrice"-->
                            <!--                                    required-->
                            <!--                                    class="form-control"-->
                            <!--                                    id="inputCity"-->
                            <!--                                  />-->
                            <!--                                </div>-->

                            <!--                                <div class="form-group col-md-6">-->
                            <!--                                  <label for="inputCity">Provider image</label>-->
                            <!--                                  <input-->
                            <!--                                    type="text"-->
                            <!--                                    v-model="provider.image"-->
                            <!--                                    required-->
                            <!--                                    class="form-control"-->
                            <!--                                  />-->
                            <!--                                </div>-->
                            <!--                              </div>-->
                            <!--                              <div class="form-group ml-1">-->
                            <!--                                <div class="form-check">-->
                            <!--                                  <input-->
                            <!--                                    class="form-check-input"-->
                            <!--                                    type="checkbox"-->
                            <!--                                    v-model="provider.active"-->
                            <!--                                    required-->
                            <!--                                    id="gridCheck"-->
                            <!--                                  />-->
                            <!--                                  <label-->
                            <!--                                    class="form-check-label"-->
                            <!--                                    for="gridCheck"-->
                            <!--                                  >-->
                            <!--                                    Active-->
                            <!--                                  </label>-->
                            <!--                                </div>-->
                            <!--                              </div>-->
                            <!--                            </div>-->
                          </v-card-text>

                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn
                              color="primary"
                              text
                              @click.stop="$set(dialogNote, provider.id, false)"
                            >
                              Close
                            </v-btn>
                            <v-btn
                              color="warning"
                              text
                              @click="
                                editProvider(
                                  provider.id,
                                  provider.name,
                                  provider.contactInfo,
                                  provider.deliveryPrice,
                                  provider.active,
                                  provider.description,
                                  provider.image
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
    <v-snackbar v-model="snackbar" color="error">
      <div class="text-center">
        {{ text }}
      </div>
    </v-snackbar>
    <router-link
      class="btn btn-warning mb-3 btn-lg"
      style="position: absolute;right: 10px; position: fixed;bottom: 0px; border-radius: 100px;padding: 20px 23px;font-size: 20px !important;"
      :to="'/providers'"
      ><i class="fas fa-user-plus"></i
    ></router-link>
  </div>
</template>

<script>
import api from "@/components/backend-api.js";
import confirmationDialog from "../components/ConfirmationDialog";

export default {
  name: "ProviderList",
  components: {
    confirmationDialog
  },
  data() {
    return {
      nameRules: [
        v => !!v || "Name is required",
        v => (v && v.length <= 20) || "Name must be less than 10 characters"
      ],
      deliveryPriceRules: [
        v => !!v || "Delivery Price is required",
        v => v >= 0 || "Must be a positive number"
      ],
      contactInfoRules: [
        v => !!v || "Contact Info is required"
      ],
      descriptionRules: [v => !!v || "Description is required"],
      imageRules: [v => /http+.+\..+/.test(v) || "Must be a link"],
      currentName: null,
      currentId: null,
      title: "Confirmation",
      message: "Do you really want to delete ",
      dialog1: false,
      dialogNote: {},
      providers: [],
      dialog: false,
      snackbar: false,
      text: "asd"
    };
  },
  methods: {
    openDialog(id, name) {
      this.dialog1 = true;
      this.currentId = id;
      this.currentName = name;
      console.log(this.currentId);
    },

    editProvider(id, name, contact, price, active, desc, img) {
      let error = false;
      if (name.length === 0) {
        this.text = "Provider name cannot be empty!";
        this.snackbar = true;
        error = true;
      }
      // if (contact.length === 0) {
      //   this.text = "Provider email cannot be empty!";
      //   this.snackbar = true;
      //   error = true;
      // }
      // if (!contact.includes("@")) {
      //   this.text = "The contact must contain an email address";
      //   this.snackbar = true;
      //   error = true;
      // }
      if (price < 0) {
        this.text = "Price can't be negative";
        this.snackbar = true;
        error = true;
      }
      if (!error) {
        api.editProvider(id, name, contact, price, active, desc, img).then(()=>{
          api.getAllProviders().then(r => {
            this.providers = r.data;
           window.location.reload();
            console.log(r.data);
          });
        })
      }
    },
    deleteProvider() {
      api.deleteProvider(this.currentId).then(()=>{
        api.getAllProviders().then(r => {
          this.providers = r.data;
        });
        this.dialog1 = false;
      });
    },
    closeConfirmation() {
      this.dialog1 = false
    }
  },

  beforeCreate() {
    api.getAllProviders().then(r => {
      this.providers = r.data;
      console.log(r.data);
    });
  }
};
</script>

<style scoped>
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

input[type="number"] {
  -moz-appearance: textfield;
}
.wrapScroll {
  overflow: auto;
  white-space: nowrap;
}
</style>
