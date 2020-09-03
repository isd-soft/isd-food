<template>
  <v-layout row justify-center>
    <v-dialog v-model="dialog1" persistent max-width="290">
      <v-card>
        <v-card-title class="headline">{{ title }}</v-card-title>
        <v-card-text>{{ message }}</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="error" text @click="close">Cancel</v-btn>
          <v-btn
            color="green darken-1"
            text
            @click="method"
            >{{ actionButton }}</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
</template>
<script>
export default {
  props: {
    title: {
      default: "Confirm"
    },
    id: {},
    message: {
      default: "Are you sure what you want delete it?"
    },
    dialog1: {
      default: false
    },
    actionButton: {
      default: "Save"
    },
    method: { type: Function },
    mounted() {
      this.method();
    }
  },

  methods: {
    close() {
      console.log("CLOSING !!!!")
      this.$emit("closeDeleteDialog");
    }
  }
};
</script>
