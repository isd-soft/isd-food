<template>
  <v-app class="row" heavy style="background: none">
    <v-card class="providers col-md-6 p-5 border-left-warning container">

      <v-form ref="form" v-model="valid">

        <v-text-field
          v-model="name"
          :counter="10"
          :rules="nameRules"
          label="Name"
          required
        ></v-text-field>

        <v-text-field
          v-model="deliveryPrice"
          :rules="deliveryPriceRules"
          label="Delivery Price"
          required
        ></v-text-field>

        <v-text-field
          v-model="contactInfo"
          :rules="contactInfoRules"
          label="Contact Info"
          required
        ></v-text-field>

        <v-text-field
          v-model="description"
          :rules="descriptionRules"
          label="Description"
          required
        ></v-text-field>

        <v-text-field
          v-model="image"
          :rules="imageRules"
          label="Image"
          required
        ></v-text-field>

        <v-select
          v-model="select"
          :items="items"
          :rules="[ v => !!v || 'Item is required']"
          label="Is Active"
          required
        ></v-select>

        <div class="d-flex justify-content-between">

        <v-btn
          :disabled="!valid"
          color="success"
          
          @click="validate"
        >
          Save
        </v-btn>

        <v-btn color="warning"  @click="reset">
          Reset
        </v-btn>
        </div>

      </v-form>
    </v-card>
  </v-app>
</template>

<script>
// eslint-disable-next-line no-unused-vars

export default {
  name: "Provider",
  data: () => ({
    valid: false,
    name: "",
    nameRules: [
      v => !!v || "Name is required",
      v => (v && v.length <= 10) || "Name must be less than 10 characters"
    ],
    deliveryPrice: "",
    deliveryPriceRules: [
      v => !!v || "Delivery Price is required",
      v => v >= 0 || "Must be a positive number"
    ],
    contactInfo: "",
    contactInfoRules: [
      v => !!v || "Contact Info is required",
      v => /.+@.+\..+/.test(v) || "Contact Info  must be valid"
    ],
    description: "",
    descriptionRules: [v => !!v || "Delivery Price is required"],
    image: "",
    imageRules: [v => /http+/.test(v) || "Must be a link"],
    select: true,
    items: ["true", "false"]
  }),

  methods: {
    reset() {
      this.$refs.form.reset();
    },

    validate() {
      // if (
      //   this.name !== "" &&
      //   this.deliveryPrice !== "" &&
      //   this.contactInfo !== "" &&
      //   this.description !== "" &&
      //   this.image !== ""
      // ) {
        this.$store
            .dispatch("createProvider", {
              name: this.name,
              deliveryPrice: this.deliveryPrice,
              contactInfo: this.contactInfo,
              description: this.description,
              image: this.image,
              active: this.select
            })
            // eslint-disable-next-line no-unused-vars
            .then(({status}) => {
              this.$store.commit("SET_NOTIFICATION", {
                display: true,
                text:
                    "Your account has been successfully created! you can now login.",
                alertClass: "danger"
              });
            })
            // eslint-disable-next-line no-unused-vars
            .catch(error => {
              this.providerExists = true;
            });

        this.$router.push("/ProviderList");
      // }
      // else {
      //   this.valid = false;
      // }
    }
  }
};
</script>

<style scoped></style>
