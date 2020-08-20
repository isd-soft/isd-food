<template>
  <v-dialog
      v-model="$store.state.orders.createOrderSuccess"
      max-width="300"
  >
    <v-card>
    <v-card-title class="headline grey lighten-2">
      Order confirmed!
    </v-card-title>

    <v-card-text class="mt-2">
      Enjoy your food! <br>
      Isd-food with love🧡
    </v-card-text>

    <v-divider></v-divider>

    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
          color="warning"
          text
          @click="$store.state.orders.createOrderSuccess = false"
      >
        Excellent
      </v-btn>
    </v-card-actions>
    </v-card>
  </v-dialog>
</template>
<script>
export default {
  data() {
    return {};
  },
  methods: {
  }
};
</script>