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
          class="col-14 p-0"
          v-model="date"
          :label="label"
          readonly
          v-bind="attrs"
          v-on="on"
          :disabled="isDatePickerDisabled()"
          @click="menu=true"
          prepend-icon="mdi-calendar"
      ></v-text-field>
    </template>
    <v-date-picker :allowed-dates="allowedMonths" header-color="orange" color="warning" v-model="date"
                   :type="pickerType" @change="$emit('dateChanged', date); menu = false"
                   :min="getMinDate()" :max="getMaxDate()"
                   :disabled="isDatePickerDisabled()"/>
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
    allowedMonths(date) {
      if (this.pickerType === "month") {
        if (this.availableDates == null || this.availableDates.length === 0)
          return false;
        else return this.availableDates.indexOf(date) !== -1
      }

      return true;
    },
    getMinDate() {
      if (this.pickerType === "month") {
        if (this.availableDates == null || this.availableDates.length === 0)
          return undefined;
        else return this.availableDates[this.availableDates.length - 1]
      }

      return undefined;
    },
    getMaxDate() {
      if (this.pickerType === "month") {
        if (this.availableDates == null || this.availableDates.length === 0)
          return undefined;
        else return this.availableDates[0]
      }

      return undefined;

    },
    isDatePickerDisabled() {
      if (this.pickerType === "month") {
        return this.availableDates == null || this.availableDates.length === 0;

      }

      return false;

    }
  },
  beforeMount() {
    console.log(this.availableDates)
    this.date = this.initialDate;
  }
}
</script>