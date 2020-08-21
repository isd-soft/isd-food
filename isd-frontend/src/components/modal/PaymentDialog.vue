<template>
  <v-app class="payment-dialog">
    <v-row justify="center">
      <v-dialog v-model="$store.state.payment.displayUserPayment" persistent max-width="600px">
        <v-card>
          <v-card-title>
            <span class="headline">User payment</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-radio-group v-model="filterType" row>
                <span class="theme--light pr-5">Filter by</span>
                <v-radio
                    label="Month"
                    value="Month"
                ></v-radio>
                <v-radio
                    label="Period"
                    value="Period"
                ></v-radio>
              </v-radio-group>
              <v-row>
                <v-col v-if="filterType==='Month'" cols="3">
                  <DatePicker label="Select month" picker-type="month" :initial-date="monthYearPicker"
                              @dateChanged="setMonthAndYear"/>
                </v-col>
                <div class="row ml-1" v-if="filterType!=='Month'">
                  <v-col cols="3">
                    <DatePicker label="Date from" picker-type="date" :initial-date="dateFromPicker" @dateChanged="setDateFrom"/>
                  </v-col>
                  <v-col cols="3">
                    <DatePicker label="Date to" picker-type="date" :initial-date="dateToPicker" @dateChanged="setDateTo"/>
                  </v-col>
                </div>
              </v-row>


              <v-row no-gutters >
                <v-col cols="4">
                  <v-text-field
                      class="payment-value"
                      readonly
                      disabled
                      label="Payment for selected period"
                      :value="$store.state.payment.userPayment.payment+' '+'lei'"
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="$store.state.payment.displayUserPayment = false">Close</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </v-app>
</template>
<script>

import DatePicker from "@/components/picker/DatePicker";
import SkeletonLoader from "@/components/loader/SkeletonLoader";

export default {
  components: {DatePicker, SkeletonLoader},
  name: "Home",
  data() {
    return {
      monthYearPicker: new Date().toISOString().substring(0, 7),
      dateFromPicker: null,
      dateToPicker: null,
      filterType: "Month"
    }
  },
  methods: {
    setMonthAndYear(date) {
      this.monthYearPicker = date;

      this.getUserPaymentOnMonth();
    },
    setDateFrom(date) {
      this.dateFromPicker = date;
      this.getUserPaymentOnPeriod();
    },
    setDateTo(date) {
      this.dateToPicker = date;
      this.getUserPaymentOnPeriod();
    },
    getUserPaymentOnMonth() {
      let year = this.monthYearPicker.split('-')[0];
      let month = this.monthYearPicker.split('-')[1];
      this.dateFromPicker = null;
      this.dateToPicker = null;
      this.$store.dispatch('getUserPaymentOnMonth', {month: month, year: year});
    },
    getUserPaymentOnPeriod() {
      if (this.dateFromPicker != null && this.dateToPicker != null) {
        this.monthYearPicker = null;
        this.$store.dispatch('getUserPaymentOnPeriod', {dateFrom: this.dateFromPicker, dateTo: this.dateToPicker});
      }
    },
  },

  beforeMount() {
    this.getUserPaymentOnMonth();
  }
}

</script>