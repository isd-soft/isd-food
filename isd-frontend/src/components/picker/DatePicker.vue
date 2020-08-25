<template>
  <v-menu
      ref="menu"
      v-model="menu"
      transition="scale-transition"
      offset-y
      min-width="290px"
      :close-on-content-click="false"
  >
    <template v-slot:activator="{ on, attrs }">
      <v-text-field
          v-model="date"
          :label="label"
          readonly
          v-bind="attrs"
          v-on="on"
          @click="menu=true"
          prepend-icon="date"
      ></v-text-field>
    </template>
    <v-date-picker :allowed-dates="allowedDates" header-color="orange" color="warning"
                   v-model="date"
                   :min="availableDates[availableDates.length-1]"
                   :max="availableDates[0]"
                   :type="pickerType" @change="$emit('dateChanged', date); menu = false"/>
  </v-menu>
</template>
<script>
export default {
  name: "DatePicker",
  props: ["label", "pickerType", "initialDate", "availableDates"],
  data: () => ({
    date: null,
    menu: false
  }),
  methods: {
    allowedDates(dateVal) {
      if (this.availableDates == null || this.availableDates.length === 0 || dateVal === null) return true;
      return this.availableDates.indexOf(dateVal) !== -1;
    },
  },
  beforeMount() {
    this.date = this.initialDate;
  }
}

</script>