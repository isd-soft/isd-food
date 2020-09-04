<template>
  <div v-if="product_data.provider.active" class=" col-md-4 col-sm-6 mb-4">
    <div class="card border-left-warning shadow py-2" style="height: content-box">
      <div class="card-body">
        <div class="row no-gutters align-items-center" style="height:auto !important;">
          <div class="col mr-2">
            <div class="text-xl font-weight-bold text-warning ml-1 text-uppercase mb-1">{{ product_data.name }}</div>
            <div class="h5 mb-0 font-weight-bold text-gray-800" style="font-size: 12px">
              <div class="ml-1">
                <h6><b>Provider : {{ product_data.provider.name }}</b></h6>
                <div class="menu-image">
                  <img v-if="product_data.image == null" src="../assets/food-default.png" class="image" alt=""
                       width="100%">
                  <img v-else :src="product_data.image" class="image" alt="" width="100%">
                </div>

                <v-app style="height: auto; max-height: 230px">
                  <v-radio-group class="ml-1" v-model="type" id="type" :mandatory="false"
                                 style="color: orange !important" row>
                    <v-radio
                        label="M"
                        color="warning"
                        value="M">
                    </v-radio>
                    <v-radio
                        label="S"
                        color="warning"
                        value="S"
                    ></v-radio>
                  </v-radio-group>
                  <div v-if="type === 'M'">
                    <div v-for="item in product_data.menuTypes" :key="item.type">
                      <div v-if="item.type === 'M'">
                      </div>
                    </div>
                    {{ check_menu('M') }}
                  </div>
                  <div v-else>
                    {{ check_menu('S') }}
                  </div>

                  <h6><b>Items:</b></h6>

                  <h6>
                    <!--                    <li v-for="item in product_data.menuTypes[(type_id)].items" :key="item.name">-->
                    <!--                      <b>{{ item.name }}</b>-->
                    <!--                    </li>-->
                    <div class="items"> {{
                        product_data.menuTypes[(type_id)].items.map(item => {
                          return item.name
                        }).join(', ')
                      }}
                    </div>
                  </h6>

                  <br>
                  <!-- <p>Delivery price: {{product_data.provider.deliveryPrice}} mdl.</p>
                    <p>Price: {{product_data.menuTypes[(type_id)].price}} mdl.</p>
                    <p>Total price: {{Number(product_data.provider.deliveryPrice) + Number(product_data.menuTypes[(type_id)].price)}}  mdl.</p>
                   <br>-->


                  <div class="text-center">
                    <v-btn style="border-radius: 5px !important;"
                           @click="makeOrder()"
                           large
                           rounded
                           color="warning"
                           align="center">
                      Order
                    </v-btn>


                  </div>
                </v-app>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment"

export default {
  name: "menuItem",
  data() {
    return {
      type: 'M',
      type_id: 0,
      dialog: false,
      weekDays: {'MONDAY': 1, 'TUESDAY': 2, 'WEDNESDAY': 3, 'THURSDAY': 4, 'FRIDAY': 5, 'SATURDAY': 6},
      lastOrderDate: null
    }
  },
  props: {
    product_data: {
      type: Object,
      default() {
        return {}
      }
    },
  },
  methods: {
    check_menu(type) {
      var i = 0;
      if (this.type === type)
        this.product_data.menuTypes.forEach(element => {
          if (element.type === type) {
            console.log(i)
            this.type_id = i
          } else i++
        });
    },

    makeOrder() {
      var currentDate = new Date(moment(new Date()).format('yyyy-MM-DD'));
      var result = new Date(currentDate);
      result.setDate(result.getDate() + ((this.weekDays[this.product_data.dayOfWeek] - 1) - currentDate.getDay() + 1));

      this.$store
          .dispatch("createOrder", {
            menuTypeId: this.product_data.menuTypes[this.type_id].id,
            date: moment(result).format('yyyy-MM-DD')
          })
    },
  }
}
</script>
<style lang="scss">
.item {
  flex-basis: 25%;
  box-shadow: 0 0 8px 0 orange;
  padding: 16px;
  border-radius: 30px;
  color: black;
  height: 500px;
}

.menu-image {
  width: 100%;
  height: 120px;
  object-fit: cover;
  display: block;
  position: relative;
  text-align: center;
}

.image {
  width: auto;
  height: 100%;
  border-radius: 4px;
}

.items {
  height: 70px;
  font-size: 14px !important;
  padding-bottom: 10px;
}
</style>
