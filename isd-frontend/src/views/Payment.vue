<template>
  <v-app>

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
        <v-btn class="ml-3 export-btn" color="warning" @click="exportPayment">Export</v-btn>
      </v-radio-group>

      <v-row>
        <v-col v-if="filterType==='Month'" cols="2">
          <DatePicker label="Select month" picker-type="month" :initial-date="monthYearPicker"
                      @dateChanged="setMonthAndYear"/>
        </v-col>
        <div class="row ml-1" v-if="filterType!=='Month'">
          <v-col cols="2">
            <DatePicker label="Date from" picker-type="date" :initial-date="dateFromPicker" @dateChanged="setDateFrom"/>
          </v-col>
          <v-col cols="2">
            <DatePicker label="Date to" picker-type="date" :initial-date="dateToPicker" @dateChanged="setDateTo"/>
          </v-col>
        </div>
      </v-row>
      <table
          class="table table-bordered payment-table"
          width="100%"
          cellspacing="0"
      >
        <thead>
        <tr>
          <th>Full name</th>
          <th>Payment</th>
        </tr>
        </thead>
        <tbody v-for="userPayment of $store.state.payment.allUserPayments" :key="userPayment.userId">
        <tr>
          <td>{{ userPayment.fullName }}</td>
          <td>{{ userPayment.payment }}</td>
        </tr>
        </tbody>
      </table>
      <v-row no-gutters v-if="$store.state.payment.userPaymentLoading">
        <v-col cols="5">
          <SkeletonLoader/>
        </v-col>
      </v-row>

    </v-container>
  </v-app>
</template>
<script>

import DatePicker from "@/components/picker/DatePicker";
import SkeletonLoader from "@/components/loader/SkeletonLoader";
import api from "@/components/backend-api";

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

      this.getAllUserPaymentOnMonth();
    },
    setDateFrom(date) {
      this.dateFromPicker = date;
      this.getAllUserPaymentOnPeriod();
    },
    setDateTo(date) {
      this.dateToPicker = date;
      this.getAllUserPaymentOnPeriod();
    },
    getAllUserPaymentOnMonth() {
      let year = this.monthYearPicker.split('-')[0];
      let month = this.monthYearPicker.split('-')[1];
      this.dateFromPicker = null;
      this.dateToPicker = null;
      this.$store.dispatch('getAllUserPaymentOnMonth', {month: month, year: year});
    },
    getAllUserPaymentOnPeriod() {
      if (this.dateFromPicker != null && this.dateToPicker != null) {
        this.monthYearPicker = null;
        this.$store.dispatch('getAllUserPaymentOnPeriod', {dateFrom: this.dateFromPicker, dateTo: this.dateToPicker});
      }
    },
    exportPayment() {
      if (this.dateFromPicker != null && this.dateToPicker != null)
        api.downloadPaymentDataByPeriod(this.dateFromPicker, this.dateToPicker)
      else if (this.monthYearPicker != null)
        api.downloadMonthlyPaymentData(this.monthYearPicker.split('-')[1], this.monthYearPicker.split('-')[0])
      else {
        this.$store.state.errorModel = {
          errorType: "Payment export",
          message: "Select a month or a date period to be able to export"
        };
        this.$store.state.errorDialog = true;
      }
    }
  },

  beforeMount() {
    this.getAllUserPaymentOnMonth();
  }
}

</script>