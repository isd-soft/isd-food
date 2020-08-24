<template>
  <v-app>

    <v-container>
      <v-radio-group v-model="filterType" row>
        <span class="theme--light pr-5 pt-1">Filter by</span>
        <v-radio
            label="Month"
            value="Month"
        ></v-radio>
        <v-radio
            label="Period"
            value="Period"
        ></v-radio>
        <v-btn class="ml-4 export-btn" color="warning" @click="exportPayment">Export</v-btn>
      </v-radio-group>

      <v-row>
        <v-col v-if="filterType==='Month'" cols="2">
          <DatePicker label="Select month" picker-type="month" :initial-date="monthYearPicker"
                      :availableDates="$store.state.payment.availableMonths"
                      @dateChanged="setMonthAndYear"/>
        </v-col>
        <div class="row ml-1" v-if="filterType!=='Month'">
          <v-col cols="2">
            <DatePicker label="Date from" picker-type="date" :initial-date="dateFromPicker"
                        @dateChanged="setDateFrom"/>
          </v-col>
          <v-col cols="2">
            <DatePicker label="Date to" picker-type="date" :initial-date="dateToPicker"
                        @dateChanged="setDateTo"/>
          </v-col>
        </div>
      </v-row>
      <v-row no-gutters v-if="$store.state.payment.userPaymentLoading">
        <v-col cols="5">
          <SkeletonLoader/>
        </v-col>
      </v-row>
      <table v-else
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
        <tbody v-for="userPayment of $store.state.payment.allUserPayments.userPayments" :key="userPayment.userId">
        <tr>
          <td>{{ userPayment.fullName }}</td>
          <td>{{ userPayment.payment }}</td>
        </tr>
        </tbody>
      </table>

      <Pagination @pageChanged="setPage" :page="page"
                  :totalPages="$store.state.payment.allUserPayments.totalPages"/>

    </v-container>
  </v-app>
</template>
<script>

import DatePicker from "@/components/picker/DatePicker";
import SkeletonLoader from "@/components/loader/SkeletonLoader";
import Pagination from "@/components/Pagination";
import api from "@/components/backend-api";

export default {
  components: {DatePicker, SkeletonLoader, Pagination},
  name: "Home",
  data() {
    return {
      monthYearPicker: null,
      dateFromPicker: null,
      dateToPicker: null,
      filterType: "Month",
      page: 1
    }
  },
  methods: {
    setPage(page) {
      if (page !== this.page)
        window.scrollTo({
          top: 0,
          left: 0,
          behavior: 'smooth'
        });
      this.page = page
      if (this.monthYearPicker != null)
        this.getAllUserPaymentOnMonth();

      else this.getAllUserPaymentOnPeriod();

    },
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
      console.log(this.monthYearPicker)
      let year = this.monthYearPicker.split('-')[0];
      let month = this.monthYearPicker.split('-')[1];
      this.dateFromPicker = null;
      this.dateToPicker = null;
      this.$store.dispatch('getAllUserPaymentOnMonth', {month: month, year: year, page: this.page}).catch(error => {
        console.log(error)
      });
    },
    getAllUserPaymentOnPeriod() {
      if (this.dateFromPicker != null && this.dateToPicker != null) {
        this.monthYearPicker = null;
        this.$store.dispatch('getAllUserPaymentOnPeriod', {
          dateFrom: this.dateFromPicker,
          dateTo: this.dateToPicker,
          page: this.page
        });
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
    this.$store.dispatch('getPaymentAvailableMonths').then(() => {
      this.monthYearPicker = this.$store.state.payment.availableMonths.length > 0 ? this.$store.state.payment.availableMonths[0] : null;
      this.getAllUserPaymentOnMonth();
    })
  }
}

</script>