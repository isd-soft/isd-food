<template>
<div>
  <div class="row container" >
    <div class="col-xl-12 col-md-12 mb-4">
      <div class="card border-left-warning shadow h-100 py-1">
        <div class="card-body">
          <h3 class="mb-3">Providers</h3>
          <table  class="table table-bordered"  width="100%" cellspacing="0">
            <thead>
            <tr>


              <th class="text-14">Provider name</th>
              <th class="text-14">Contact</th>
              <th class="text-14">Delivery price</th>
              <th class="text-14">active</th>
              <th class="text-14 text-center" >Delete</th>
              <th class="text-14">Edit</th>

            </tr>
            </thead>

            <tbody v-for="provider of providers" :key="provider.id" >
            <tr>
              <td class="text-14">{{provider.name}}</td>
              <td class="text-14">{{provider.contactInfo}}</td>
              <td class="text-14">{{provider.deliveryPrice}}</td>
              <td  v-if="provider.active === true" class="text-center text-14">
                <i class="fas fa-check-circle icons-text" style="margin: 0; padding: 0;color: green !important; font-size: 25px"></i>
              </td>
              <td v-else class="text-center">
                <i class="fas fa-times-circle icons-text" style="margin: 0; padding: 0;color: red !important; font-size: 25px"></i>
              </td>


              <td class="text-center">
                <button style="outline: none" @click="deleteProvider(provider.id)" onclick="window.location.reload()">
                  <i class="fas fa-trash icons-text"></i>
                </button>
              </td>

              <td>
                  <v-app style="background: none; height: content-box !important; max-height: 30px" class="text-center">
                    <v-dialog
                        v-model="dialogNote[provider.id]"
                        width="500"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <button style="background : none !important;box-shadow: none;color: grey; outline: none"

                            v-bind="attrs"
                            v-on="on"
                        >
                          <i class="fas fa-edit" style="margin: 0 !important;padding: 0 !important"></i>
                        </button>
                      </template>

                      <v-card>
                        <v-card-title class="headline orange lighten-2" style="color: white">
                          {{provider.name}}
                        </v-card-title>

                        <v-card-text>
                          <div class="container text-left">
                              <div class="form-row">
                                <div class="form-group col-md-6">
                                  <label for="inputEmail4">Provider</label>
                                  <input type="text" class="form-control" id="inputEmail4" v-model="provider.name" required >
                                </div>
                                <div class="form-group col-md-6">
                                  <label for="inputPassword4">Contact info</label>
                                  <input type="text" class="form-control" id="inputPassword4" v-model="provider.contactInfo" required >
                                </div>
                              </div>
                              <div class="form-group">
                                <label for="inputAddress">Description</label>
                                <textarea v-model="provider.description" min-height = "40px" class="form-control" id="inputAddress" ></textarea>
                              </div>

                              <div class="form-row">
                                <div class="form-group col-md-6">
                                  <label for="inputCity" >Delivery price</label>
                                  <input type="text" v-model="provider.deliveryPrice" required  class="form-control" id="inputCity">
                                </div>

                                <div class="form-group col-md-6">
                                  <label for="inputCity" >Provider image</label>
                                  <input type="text" v-model="provider.image" required  class="form-control" >
                                </div>

                              </div>
                              <div class="form-group ml-1">
                                <div class="form-check">
                                  <input class="form-check-input" type="checkbox" v-model="provider.active" required id="gridCheck">
                                  <label class="form-check-label" for="gridCheck">
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
                              @click.stop="$set(dialogNote, provider.id, false)"

                          >
                            Close
                          </v-btn>
                          <v-btn
                              color="warning"
                              text
                              @click="editProvider(provider.id,provider.name,provider.contactInfo,provider.deliveryPrice,provider.active,provider.description,provider.image)"
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
      <v-snackbar
          v-model="snackbar"
      >
        <div class="text-center">
         {{ text }}
        </div>


      </v-snackbar>
</div>
</template>

<script>
import api from "@/components/backend-api.js"

export default {
  name: "ProviderList",
  data (){
    return{
      dialogNote:{},
      providers:[],
      dialog: false,
      snackbar: false,
      text: 'asd',
    }
  },
  methods:{
    editProvider(id,name,contact,price,active,desc,img){
      if(name.length === 0){
        this.text = "Provider name cannot be empty!";
        this.snackbar = true;
      }
      else if(contact.length === 0){
        this.text = "Provider email cannot be empty!";
        this.snackbar = true;
      }
      else if(!contact.includes("@")){
        this.text = "The contact must contain an email address";
        this.snackbar = true;
      }
      else if(price < 0){
        this.text = "Price can't be negative";
        this.snackbar = true;
      }
      else{
        api.editProvider(id,name,contact,price,active,desc,img);
        window.location.reload()

      }
    },
    deleteProvider(id){
      api.deleteProvider(id)
    }
  },

  beforeCreate() {
    api.getAllProviders().then(r => {this.providers = r.data;console.log(r.data)});
  }
}
</script>

<style scoped>
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

input[type=number] {
  -moz-appearance:textfield;
}
@media (max-width: 900px) {
  .text-14{
    font-size: 11px;
  }

}
@media (max-width: 700px) {
  .text-14{
    font-size: 10px;
  }

}
</style>