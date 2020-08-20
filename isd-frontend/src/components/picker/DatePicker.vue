<template>
    <v-menu
        ref="menu"
        v-model="menu"
        transition="scale-transition"
        offset-y
        min-width="290px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-text-field
            v-model="date"
            :label="label"
            readonly
            v-bind="attrs"
            v-on="on"
            @click="menu=true"
        ></v-text-field>
      </template>
      <v-date-picker header-color="orange" color="warning" v-model="date" :type="pickerType" @change="$emit('dateChanged', date); menu = false"/>
    </v-menu>
</template>
<script>
export default {
  name: "DatePicker",
  props: ["label","pickerType"],
  data: () => ({
    date: new Date().toISOString().substr(0, 7),
    menu: false
  }),
  methods: {
  }
}

</script>