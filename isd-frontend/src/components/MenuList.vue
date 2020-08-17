<template>
<v-app style="background: none">
  <div class="row">

  <v-container fluid>
    <v-row>
     <v-col
        cols="12"
        sm="6"
        class="py-2"
      >

      {{callOnce()}}
       Select day
       <!-- {{dailyMenu}} -->
        

        <v-btn-toggle
          v-model="weekDay"
          tile
          color="warning"
          group
        >
          <v-btn  @click="getDayMenu('MONDAY')" value="MONDAY" :disabled="getWeekDayId() >1 || getWeekDayId()==0">
            Monday
          </v-btn>

          <v-btn @click="getDayMenu('TUESDAY')" value="TUESDAY" :disabled="getWeekDayId() > 2 || getWeekDayId()==0">
            Tuesday
          </v-btn>

          <v-btn @click="getDayMenu('WEDNESDAY')" value="WEDNESDAY" :disabled="getWeekDayId() > 3 || getWeekDayId()==0">
            Wednesday
          </v-btn>

          <v-btn @click="getDayMenu('THURSDAY')" value="THURSDAY" :disabled="getWeekDayId() > 4 || getWeekDayId()==0">
            Thursday
          </v-btn>
           <v-btn @click="getDayMenu('FRIDAY')" value="FRIDAY" :disabled="getWeekDayId() > 5 || getWeekDayId()==0">
            Friday
          </v-btn>
        </v-btn-toggle>
      </v-col>
    </v-row>
  </v-container>


    <MenuItem
        v-for="product in dailyMenu"
        :key="product.name"
        :product_data="product"/>
  </div>

</v-app>
</template>

<script>
import api from './backend-api';
import MenuItem from './MenuItem.vue'
  export default {
    name: 'menu',
  components: {
    MenuItem,
  },
    data () {
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
      }
    },
    methods: {
      // Fetches posts when the component is created.
      callOnce(){
        if(!this.get){
          this.getMenuFirst();
          this.get = true
        }
      },

      getDayMenu(day) {
        api.getMenuDay(day).then(response => {
            this.dailyMenu = response.data;
            console.log(response.data)
        })
        .catch(error => {
          this.errors.push(error)
        })
      },
      
      callMenuApi () {
        api.getMenu().then(response => {
            this.products = response.data;
            console.log(response.data)
        })
        .catch(error => {
          this.errors.push(error)
        })
      },
        callHelloApi () {
        api.Hello().then(response => {
            this.helloResponse = response.data;
            console.log(response.data)
        })
        .catch(error => {
          this.errors.push(error)
        })
      },

      getWeekDayId(){
          var date = new Date()
          var count = date.getDay()
          console.log(date.getHours())
          if(date.getHours() > 10)
            count++;
          if(count == 7)
            count = 0
          console.log(count)
          return count
      },

      getWeekDay() {
      let days = ["SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"];
      return days[this.getWeekDayId()];
      },


      callMenuType(){
        api.getMenuType().then(response => {
            this.menu_types = response.data;
            console.log(response.data)
        })
        .catch(error => {
          this.errors.push(error)
        })
      },
      next () {
        this.onboarding = this.onboarding + 1 === this.length
          ? 0
          : this.onboarding + 1
      },
      prev () {
        this.onboarding = this.onboarding - 1 < 0
          ? this.length - 1
          : this.onboarding - 1
      },

      getMenuFirst(){
        this.getDayMenu(this.getWeekDay());
      },
    },
  }
</script>

<style lang = "scss">
.menu{
  &_list{
    margin: 0 auto;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    align-items: center;
  }
}
.rounded-card{
    border-radius:500px;
}
</style>