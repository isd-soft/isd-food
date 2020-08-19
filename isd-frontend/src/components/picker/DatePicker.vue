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
        ></v-text-field>
      </template>
      <v-date-picker header-color="orange" color="warning" v-model="date" type="month" @change="$emit('dateChanged', date)"/>
    </v-menu>
</template>
<script>
export default {
  name: "DatePicker",
  props: ["label"],
  data: () => ({
    date: new Date().toISOString().substr(0, 10),
    menu: true
  }),
  methods: {
  }
}

</script>