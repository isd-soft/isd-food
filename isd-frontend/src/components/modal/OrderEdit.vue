<template>
  <v-dialog persistent v-model="editDialog" width="600">

    <v-card class="p-4">
      <v-card-text>

        <v-btn class="m-1" v-for="day of days" :key="day"
               :disabled="days.indexOf(day) < currentDay-1"
               :color="weekDay === day ? 'orange' : ''"
               :class="weekDay === day ? 'text-white' : 'text-black'"
               @click="selectDay(day)"> {{
            day.substring(0, 3)
          }}
        </v-btn>
      </v-card-text>
      <div class="menu-list">
        <v-card
            v-for="menu of menus" :key="menu.id"
            class="menu-card"

            outlined
        >
          <div class="overline mb-4">{{ menu.name }}</div>
          <div style="width: 100%;margin:0 auto;" class="row justify-content-center">
          <div class="menu-img">
            <img width="100%" :src="menu.image=== 'http' ?'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQr8Ii7nOMGIR0CwhLMRMjspLjlop2KbdVYTw&usqp=CAU' :menu.image" />
          </div><br/><br/><br/><br/><br/>
          <div style="text-align:center;width: 270px;min-height:50px;font-size: 14px;color:dimgray;">{{
              menu.menuTypes[0].items.map(item => {
                return item.name
              }).join(', ')
            }}</div>

          <v-card-actions>
            <v-btn :color="orderMenuTypeId === menuType.id ? 'orange' : ''"
                   :class="orderMenuTypeId === menuType.id ? 'text-white' : 'text-black'"
                   v-for="menuType of menu.menuTypes" :key="menuType.id+menuType.type"
                   @click="setMenuType(menuType.id)">{{ menuType.type }}
            </v-btn>
          </v-card-actions>
          </div>
        </v-card>
      </div>
      <v-card-actions style="">
        <v-spacer></v-spacer>
        <v-btn
            color="primary"
            text
            @click="$emit('closeEditDialog')"
        >
          Close
        </v-btn>
        <v-btn
            color="warning"
            text
            @click="save"
        >

          Save
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>
<script>
export default {
  props: [
    "editDialog",
    "orderMenuTypeId",
    "menus",
    "menu",
    "days",
    "weekDay"
  ],
  data() {
    return {
      currentDay: new Date().getDay(),
    };
  },
  methods: {
    save() {
      this.$emit('updateOrder');
    },
    setMenuType(id) {
      this.$emit('setMenuTypeId', id);
    },
    selectDay(day) {
      this.$emit('getMenusByDay', day)
    }
  },

}
</script>