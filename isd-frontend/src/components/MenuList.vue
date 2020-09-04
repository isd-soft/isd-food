<template>
  <div>
    <div class="row">
      <v-app class="col-12 pb-5 mb-5" style="background: none">
        <v-btn-toggle class="d-flex justify-content-center" v-model="weekDay" tile color="warning" group>
          <v-btn :class="weekDay === 'MONDAY'  ? 'v-btn--active' : ''" @click="getDayMenu('MONDAY')" value="MONDAY"
                 :disabled="getWeekDayId() >1 || getWeekDayId()==0">Mo
          </v-btn>
          <v-btn :class="weekDay === 'TUESDAY'  ? 'v-btn--active' : ''" @click="getDayMenu('TUESDAY')" value="TUESDAY"
                 :disabled="getWeekDayId() > 2 || getWeekDayId()==0">Tu
          </v-btn>
          <v-btn :class="weekDay === 'WEDNESDAY'  ? 'v-btn--active' : ''" @click="getDayMenu('WEDNESDAY')"
                 value="WEDNESDAY" :disabled="getWeekDayId() > 3 || getWeekDayId()==0">
            We
          </v-btn>
          <v-btn @click="getDayMenu('THURSDAY')" value="THURSDAY" :disabled="getWeekDayId() > 4 || getWeekDayId()==0">
            Th
          </v-btn>
          <v-btn @click="getDayMenu('FRIDAY')" value="FRIDAY" :disabled="getWeekDayId() > 5 || getWeekDayId()==0">Fr
          </v-btn>
        </v-btn-toggle>
        <div class="row">
          <MenuItem v-for="product in dailyMenu" :key="product.id" :product_data="product"/>
        </div>
      </v-app>
    </div>

  </div>
</template>

<script>
import api from './backend-api';
import MenuItem from './MenuItem.vue'

export default {
  name: 'menu',
  components: {
    MenuItem,
  },
  data() {
    return {
      weekDay: this.getWeekDay(),
      msg: 'Display some info from spring',
      products: [],
      dailyMenu: [],
      menu_types: [],
      helloResponse: [],
      errors: [],
      get: false,
      alignments: [
        'start',
        'center',
        'end',
      ],
      lastOrderDate: null
    }
  },
  methods: {
    // Fetches posts when the component is created.
    callOnce() {
      if (!this.get) {
        this.getMenuFirst();
        this.get = true
      }
    },

    getDayMenu(day) {
      console.log(this.weekDay)
      api
          .getMenuDay(day)
          .then(response => {
            this.dailyMenu = response.data;
            console.log(response.data)
          })
          .catch(error => {
            this.errors.push(error)
          })
    },

    callMenuApi() {
      api.getMenu().then(response => {
        this.products = response.data;
        console.log(response.data)
      })
          .catch(error => {
            this.errors.push(error)
          })
    },

    getWeekDayId() {
      // var date = new Date()
      // var count = date.getDay()
      // if(date.getHours() > 10)
      //   count++;
      // if(count == 7)
      //   count = 0
      // return count

      console.log(new Date(this.lastOrderDate).getDay())

      if (new Date(this.lastOrderDate).getDay() === 5 && new Date().getDay() === 1) return 1;
      if (new Date(this.lastOrderDate).getDay() > 4) return 0;

      if (new Date(this.lastOrderDate).getDay() > new Date().getDay())
        return new Date(this.lastOrderDate).getDay()
      else return new Date().getDay()

    },

    getWeekDay() {
      let days = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"];
      console.log(this.weekDay)
      return days[this.getWeekDayId() - 1];
    },

    callMenuType() {
      api.getMenuType().then(response => {
        this.menu_types = response.data;
        console.log(response.data)
      })
          .catch(error => {
            this.errors.push(error)
          })
    },
    getMenuFirst() {
      console.log(this.getWeekDay())
      this.getDayMenu(this.getWeekDay());
    },
  },
  beforeCreate() {
    api.getLastOrderDate().then(response => {
      this.lastOrderDate = response.data;
    })
  },
  beforeMount() {
    this.callOnce();
  }
}
</script>

<style lang="scss">

.font-style-apple {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

.menu {
  &_list {
    margin: 0 auto;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    align-items: center;
  }
}

.rounded-card {
  border-radius: 500px;
}

#wrapper #content-wrapper {
  overflow: hidden !important;
}
</style>