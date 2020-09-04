<template>
  <v-app style="background: none">
    <v-row justify="center">
      <v-col cols="12" sm="10" md="8" lg="6">
        <v-card class="border-left-warning" ref="form" v-model="valid">
          <v-card-text>
              <CreateMenuSuccess/>
              <CreateMenuError/>
            <v-text-field
              ref="name"
              v-model="name"

              :counter="15"
              :rules="nameRules"
              :error-messages="errorMessages"
              label="Menu name"
              placeholder="Enter name"
              required
            ></v-text-field>

            <v-autocomplete
              ref="provider"
              return-object
              v-model="provider"

              :rules="ProviderRules"

              :items="providers"
              item-text="name"
              label="Provider"
              placeholder="Select..."
              required
            ></v-autocomplete>

            <v-autocomplete
              ref="weekday"
              v-model="day"

              :rules="DayRules"

              :items="days"
              label="Day"
              placeholder="Select..."
              required
            ></v-autocomplete>

            <v-text-field
              ref="image"
              v-model="image"
              :rules="imageRules"
              label="image"
              placeholder="image"
              required
            ></v-text-field>

            <br />
            <h4>Menu type S</h4>

            <v-container fluid>
              <v-select
                return-object
                v-model="itemsS"
                :items="items"
                :rules="itemRules"
                item-text="name"
                label="Items"
                required

                multiple
              >
                <template v-slot:prepend-item>
                  <v-list-item ripple>
                    <v-list-item-content>
                      <v-list-item-title>Choose items:</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                  <v-divider class="mt-2"></v-divider>
                </template>
                <template v-slot:append-item>
                  <v-divider class="mb-2"></v-divider>
                </template>
              </v-select>
              <v-row justify="center">
                <v-dialog v-model="dialog" persistent max-width="600px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn color="warning" v-bind="attrs" v-on="on">
                      Add item
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="headline">Item name</span>
                    </v-card-title>
                    <v-card-text>
                      <v-container>
                        <v-row>
                          <v-col cols="12">
                            <v-text-field
                              v-model="item"
                              label="Item name"
                              required
                            >
                            </v-text-field>
                          </v-col>
                        </v-row>
                      </v-container>
                    </v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="error" text @click="dialog = false"
                        >Close</v-btn
                      >
                      <v-btn color="success" text @click="saveItem()"
                        >Save</v-btn
                      >
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-row>
            </v-container>

            <v-text-field
              ref="priceS"
              v-model="priceS"
              :rules="PriceRules"
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
                v-model="itemsM"
                :items="items"
                :rules="itemRules"
                item-text="name"
                label="Items"
                multiple
                required

              >
                <template v-slot:prepend-item>
                  <v-list-item ripple>
                    <v-list-item-content>
                      <v-list-item-title>Choose items:</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>

                  <v-divider class="mt-2"></v-divider>
                </template>
              </v-select>
              <v-row justify="center">
                <v-dialog v-model="dialog" persistent max-width="600px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn color="warning" v-bind="attrs" v-on="on">
                      Add item
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="headline">Item name</span>
                    </v-card-title>
                    <v-card-text>
                      <v-container>
                        <v-row>
                          <v-col cols="12">
                            <v-text-field
                              v-model="item"
                              label="Item name"
                              required
                            >
                            </v-text-field>
                          </v-col>
                        </v-row>
                      </v-container>
                    </v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="error" text @click="dialog = false"
                        >Close</v-btn
                      >
                      <v-btn color="success" text @click="saveItem()"
                        >Save</v-btn
                      >
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-row>
            </v-container>


            <br />

            <v-text-field
              ref="priceS"
              v-model="priceM"
              :rules="[v => !!v || 'This field is required']"

              label="price"
              required
              placeholder="Enter price"
            ></v-text-field>
          </v-card-text>
          <v-divider class="mt-12"></v-divider>
          <v-card-actions class = "d-flex justify-content-end">

            <v-btn color="primary" :to="'/allmenus'">Cancel</v-btn>
            <v-btn color="success" @click="createMenu()"
              >Save
            </v-btn>

          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>

    <v-snackbar
            v-model="snackbar"
            :color=snackbarColor
    >
      <div class="text-center">
        {{ text }}
      </div>
    </v-snackbar>

  </v-app>
</template>

<script>
import api from "./backend-api";
import CreateMenuSuccess from "./modal/CreateMenuSuccess";
import CreateMenuError from "./modal/CreateMenuError";

export default {
    name: "AddMenu",
    components: {
        CreateMenuSuccess,
        CreateMenuError
    },
  data: () => ({
    valid: false,
    nameRules: [
      v => !!v || "Is it menu without name? Come on!",
      v => (v && v.length <= 15) || "Name must be less than 15 characters"
    ],

    PriceRules: [
      v => !!v || "Price is required",
      v => v >= 0 || "Must be a positive number"
    ],

    ProviderRules: [v => !!v || "Was this menu mady by your grandma or who?"],

    DayRules: [v => !!v || "Enter day!"],

    imageRules: [v => /http+/.test(v) || "Must be a link"],

    itemRules: [v => !!v || "Items can't be empty!"],

    dialog: false,
    item: null,
    items: [],
    image: null,
    itemSCount: 0,
    itemMCount: 0,
    itemsS: [],
    itemsM: [],
    days: ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"],
    providers: [],
    errorMessages: "",
    provider: [],
    day: null,
    name: null,
    priceS: null,
    priceM: null,
    formHasErrors: false,
    snackbar: false,
    snackbarColor: 'error',
    text: 'pcela',

  }),


  computed: {
    form() {
      return {
        name: this.name,
        provider: this.provider,
        day: this.day,
        priceS: this.priceS,
        priceM: this.priceM,
        itemsS: this.itemsS,
        itemsM: this.itemsM
      };
    }
  },


  watch: {
    name() {
      this.errorMessages = "";
    }
  },

  methods: {
    submit() {
      this.formHasErrors = false;

      Object.keys(this.form).forEach(f => {
        if (!this.form[f]) this.formHasErrors = true;

        this.$refs[f].submit(true);
      });
    },

    createItem() {
      api.newItem(this.item);
    },

    saveItem() {
      this.createItem()
      this.dialog = false
      this.item = null
      api
        .getItems()
        .then(response => {
          this.items = response.data
          console.log(response.data)
        })
        .catch(error => {
          this.errors.push(error)
        });
    },


    createMenu() {
      let error = false

      if (this.name === null) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Where is name?"
        error = true
      }

      if (this.provider.length === 0) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Who is provider?"
        error = true
      }

      if (this.day === null) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Where is day?"
        error = true
      }

      if (this.image === null) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Where is image?"
        error = true
      }

      if (this.itemsS.length === 0) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Items for S please"
        error = true
      }
      if (this.itemsM.length === 0) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Items for M please"
        error = true
      }
      if (this.priceM === null || this.priceM < 0) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Price M can't be negative"
        error = true
      }
      if (this.priceS === null || this.priceS < 0) {
        this.snackbarColor = "error"
        this.snackbar = true;
        this.text = "Price S can't be negative"
        error = true
      }

      if (!error) {
        this.$store
                .dispatch("createMenu", {
            name: this.name,
            provider: this.provider,
            image: this.image,
            dayOfWeek: this.day,
            menuTypes: [
              {
                type: "S",
                price: this.priceS,
                items: this.itemsS
              },
              {
                type: "M",
                price: this.priceM,
                items: this.itemsM
              }
            ]
          })
      }
    }
  },

  beforeCreate() {
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
