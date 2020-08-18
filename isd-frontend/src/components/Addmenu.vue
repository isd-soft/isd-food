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
                            v-model="provider"
                            :rules="[() => !!provider || 'Was this menu mady by your grandma or who?']"
                            :items="providers"
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
                    <div class="d-flex justify-content-around">
                        <v-btn
                                @click="addItem1('S')"
                                color="warning"
                                style="background: orange !important"
                                depressed="true"
                                type="submit"
                        >Add item
                        </v-btn>

                        <v-btn
                                @click="deleteItem1('S')"
                                color="error"
                                depressed="true"
                                type="submit"
                        >Delete item
                        </v-btn>
                    </div>

                    <div v-for="item in itemsS" v-bind:key="item">
                        <v-select
                                :items="items"
                                label="Item"
                                data-vv-name="select"
                                required
                        ></v-select>
                    </div>



                    <v-text-field
                            ref="priceS"
                            v-model="priceS"
                            :rules="[() => !!priceS || 'This field is required']"
                            label="price"
                            required
                            placeholder="Enter price"
                    ></v-text-field>

                   <v-btn
                                @click="showItems()"
                                color="warning"
                                style="background: orange !important"
                                depressed="true"
                                type="submit"
                        >Show items
                        </v-btn>

                    <br>
                    <h4>Menu type M</h4>
                    <p>All items from S will be added automatically.</p>


                    <div class="d-flex justify-content-around">
                        <v-btn
                                @click="addItem1('M')"
                                color="warning"
                                style="background: orange !important"
                                depressed="true"
                                type="submit"
                        >Add item
                        </v-btn>

                        <v-btn
                                @click="deleteItem1('M')"
                                color="error"
                                depressed="true"
                                type="submit"
                        >Delete item
                        </v-btn>
                    </div>


                    <div v-for="item in itemsM" v-bind:key="item">
                    <v-text-field
                            label="item"
                            placeholder="Enter item name"
                    ></v-text-field>
                    </div>

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
                    <v-btn color="success" text @click="submit">Submit</v-btn>
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
            item: null,
            items: ["Cucumber", "Perets", "Bocaco"],
            itemSCount: 0,
            itemMCount: 0,
            itemsS: [],
            itemsM: [],
            days: ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"],
            providers: ['Andys pizza', 'MacDonalds'],
            errorMessages: '',
            day: null,
            name: null,
            priceS: null,
            formHasErrors: false,
        }),

        computed: {
            form () {
                return {
                    name: this.name,
                    day: this.day,
                    priceS: this.priceS,
                }
            },
        },

        watch: {
            name () {
                this.errorMessages = ''
            },
        },

        methods: {
            addItem1(type){
                if(type == 'S')
                    this.itemsS.push("")
                else
                    this.itemsM.push("")
            },

            deleteItem1(type){
                if(type == 'S')
                    this.itemsS.pop()
                else
                    this.itemsM.pop()
            },

            showItems(){
                this.itemsS.forEach(element => console.log(element));
            },


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
        },

        beforeCreate() {
            api.getItems().then(response => {
                this.items = response.data;
                console.log(response.data)
            })
                .catch(error => {
                    this.errors.push(error)
                })

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