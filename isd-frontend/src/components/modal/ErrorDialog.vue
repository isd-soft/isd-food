<template>
    <v-dialog
        v-model="$store.state.errorDialog"
        persistent
        max-width="290"
        class="err-modal"
    >
      <v-card>
        <v-card-title class="headline">{{ $store.state.errorModel.errorType }}</v-card-title>
        <v-card-text>
          {{ $store.state.errorModel.message }}
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              class="white--text grey darken-2 mr-1"
              depressed
              @click="$store.state.errorDialog = false"
          >Ok
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
