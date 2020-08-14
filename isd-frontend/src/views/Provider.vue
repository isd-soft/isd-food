<template>
  <div class="providers">
    <v-form ref="form" v-model="valid" lazy-validation>
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

      <v-text-field v-model="image" label="Image" required></v-text-field>

      <v-select
        v-model="select"
        :items="items"
        :rules="[v => !!v || 'Item is required']"
        label="Is Active"
        required
      ></v-select>

      <v-checkbox
        v-model="checkbox"
        :rules="[v => !!v || 'You must agree to continue!']"
        label="Do you agree?"
        required
      ></v-checkbox>

      <v-btn :disabled="!valid" color="green" class="mr-4" @click="validate">
        Validate
      </v-btn>

      <v-btn color="red" class="mr-4" @click="reset">
        Reset Form
      </v-btn>

      <v-btn color="orange" @click="resetValidation">
        Reset Validation
      </v-btn>
    </v-form>
  </div>
</template>

<script>
// eslint-disable-next-line no-unused-vars

export default {
  name: "Provider",
  data: () => ({
    valid: true,
    name: "",
    nameRules: [
      v => !!v || "Name is required",
      v => (v && v.length <= 10) || "Name must be less than 10 characters"
    ],
    deliveryPrice: "",
    deliveryPriceRules: [v => !!v || "Delivery Price is required"],
    contactInfo: "",
    contactInfoRules: [
      v => !!v || "Contact Info is required",
      v => /.+@.+\..+/.test(v) || "Contact Info  must be valid"
    ],
    description: "",
    descriptionRules: [v => !!v || "Delivery Price is required"],
    image: "",
    select: null,
    items: ["Yes", "No"],
    checkbox: false
  }),


  methods: {
    reset() {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    validate() {
      this.$store
        .dispatch("createProvider", {
          name: this.name,
          deliveryPrice: this.deliveryPrice,
          contactInfo: this.contactInfo,
          description: this.description,
          image: this.image,
          items: this.items
        })
        // eslint-disable-next-line no-unused-vars
        .then(({ status }) => {
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
    }
  }
};
</script>

<style scoped></style>
