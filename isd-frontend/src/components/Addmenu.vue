<template>
    <v-app>
    <v-row justify="center">
        <v-col cols="12" sm="10" md="8" lg="6">
            <v-card ref="form">

                <v-card-text>
                    <v-text-field
                            ref="name"
                            v-model="name"
                            :rules="[() => !!name || 'Is it menu without name? Come on!']"
                            :error-messages="errorMessages"
                            label="Menu name"
                            placeholder="Enter name"
                            required
                    ></v-text-field>

                    <v-autocomplete
                            ref="provider"
                            return-object
                            v-model="provider"
                            :rules="[() => !!provider|| 'Was this menu mady by your grandma or who?']"
                            :items="providers"
                            item-text="name"
                            label="Provider"
                            placeholder="Select..."
                            required
                    ></v-autocomplete>

                    <v-autocomplete
                            ref="weekday"
                            v-model="day"
                            :rules="[() => !! day|| 'Please choose day!']"
                            :items="days"
                            label="Day"
                            placeholder="Select..."
                            required
                    ></v-autocomplete>

                    <v-text-field
                            ref="image"
                            v-model="image"
                            label="image"
                            placeholder="image"
                    ></v-text-field>

                    <br>
                    <h4>Menu type S</h4>

                    <v-container fluid>
                        <v-select
                                return-object
                                v-model="itemsS"
                                :items="items"
                                item-text="name"
                                label="Items"
                                multiple
                        >
                            <template v-slot:prepend-item>
                                <v-list-item
                                        ripple
                                        @click="toggle"
                                >
                                    <v-list-item-action>
                                        <v-icon :color="itemsS.length > 0 ? 'orange' : ''">{{ icon }}</v-icon>
                                    </v-list-item-action>
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
                                    <v-btn
                                            color="warning"
                                            v-bind="attrs"
                                            v-on="on"
                                    >
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
                                                            v-model= "item"
                                                            label="Item name"
                                                            required>
                                                    </v-text-field>
                                                </v-col>
                                            </v-row>
                                        </v-container>
                                    </v-card-text>
                                    <v-card-actions>
                                        <v-spacer></v-spacer>
                                        <v-btn color="error" text @click="dialog = false">Close</v-btn>
                                        <v-btn color="success" text @click="saveItem()">Save</v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-dialog>
                        </v-row>
                    </v-container>

                    <v-text-field
                            ref="priceS"
                            v-model="priceS"
                            :rules="[() => !!priceS || 'This field is required']"
                            label="price"
                            required
                            placeholder="Enter price"
                    ></v-text-field>

                    <br>
                    <h4>Menu type M</h4>
                    <p>All items from S will be added automatically.</p>

                    <v-container fluid>
                        <v-select
                                return-object
                                v-model="itemsM"
                                :items="items"
                                item-text="name"
                                label="Items"
                                multiple
                        >
                            <template v-slot:prepend-item>
                                <v-list-item
                                        ripple
                                        @click="toggle"
                                >
                                    <v-list-item-action>
                                        <v-icon :color="itemsM.length > 0 ? 'indigo darken-4' : ''">{{ icon }}</v-icon>
                                    </v-list-item-action>
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
                                    <v-btn
                                            color="warning"
                                            v-bind="attrs"
                                            v-on="on"
                                    >
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
                                                            v-model= "item"
                                                            label="Item name"
                                                            required>
                                                    </v-text-field>
                                                </v-col>
                                            </v-row>
                                        </v-container>
                                    </v-card-text>
                                    <v-card-actions>
                                        <v-spacer></v-spacer>
                                        <v-btn color="error" text @click="dialog = false">Close</v-btn>
                                        <v-btn color="success" text @click="saveItem()">Save</v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-dialog>
                        </v-row>
                    </v-container>


                    <br>

                    <v-text-field
                            ref="priceS"
                            v-model="priceM"
                            :rules="[() => !!priceM || 'This field is required']"
                            label="price"
                            required
                            placeholder="Enter price"
                    ></v-text-field>


                </v-card-text>
                <v-divider class="mt-12"></v-divider>
                <v-card-actions>
                    <v-btn text>Cancel</v-btn>
                    <v-spacer></v-spacer>
                    <v-slide-x-reverse-transition>
                        <v-tooltip
                                v-if="formHasErrors"
                                left
                        >
                            <template v-slot:activator="{ on, attrs }">
                                <v-btn
                                        icon
                                        class="my-0"
                                        v-bind="attrs"
                                        @click="resetForm"
                                        v-on="on"
                                >
                                    <v-icon>mdi-refresh</v-icon>
                                </v-btn>
                            </template>
                            <span>Refresh form</span>
                        </v-tooltip>
                    </v-slide-x-reverse-transition>
                    <v-btn color="success" text @click="createMenu()">Submit</v-btn>
                </v-card-actions>
            </v-card>
        </v-col>
    </v-row>
    </v-app>
</template>

<script>
    import api from './backend-api';
    export default {
        data: () => ({
            dialog: false,
            item: null,
            items: [],
            itemSCount: 0,
            itemMCount: 0,
            itemsS: [],
            itemsM: [],
            days: ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"],
            providers: [],
            errorMessages: '',
            provider: [],
            day: null,
            name: null,
            priceS: null,
            formHasErrors: false,
        }),

        computed: {
            form () {
                return {
                    name: this.name,
                    provider: this.provider,
                    day: this.day,
                    priceS: this.priceS,
                    priceM: this.priceM,
                    itemsS: this.itemsS,
                    itemsM: this.itemsM
                }
            },
        },

        watch: {
            name () {
                this.errorMessages = ''
            },
        },

        methods: {


            resetForm () {
                this.errorMessages = []
                this.formHasErrors = false

                Object.keys(this.form).forEach(f => {
                    this.$refs[f].reset()
                })
            },
            submit () {
                this.formHasErrors = false

                Object.keys(this.form).forEach(f => {
                    if (!this.form[f]) this.formHasErrors = true

                    this.$refs[f].validate(true)
                })
            },

            createItem(){
                api.newItem(this.item);
            },

            saveItem(){
                this.createItem()
                this.dialog = false
                this.item = null
                api.getItems().then(response => {
                    this.items = response.data;
                    console.log(response.data)
                })
                    .catch(error => {
                        this.errors.push(error)
                    })
            },


            createMenu(){
                api.createFullMenu({
                    menu:
                {
                    name: this.name,
                    provider: this.provider,
                    image: this.image,
                    dayOfWeek: this.day
                },
                    menuTypeS:
                    {
                        type: 'S',
                        price: this.priceS,
                        items: this.itemsS
                    },
                    menuTypeM:
                    {
                        type: 'M',
                        price: this.priceM,
                        items: this.itemsM
                    }
                }
                )
            }

        },

        beforeCreate() {
            api.getItems().then(response => {
                this.items = response.data;
                console.log(response.data)
            })
                .catch(error => {
                    this.errors.push(error)
                }),

            api.getProviders().then(response => {
                this.providers = response.data;
                console.log(response.data)
            })
                .catch(error => {
                    this.errors.push(error)
                })
        }
    }
</script>