<script src="../router/index.js"></script>
<template>
<div>
  <v-navigation-drawer
      v-model="drawer"
      app
      dark
  >
    <v-list dense>
      <v-list-item link v-for="link in links" :key="link.text" router :to = "link.route">
        <v-list-item-action>
          <v-icon>{{link.icon}}</v-icon>
        </v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>{{link.text}}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>

  <v-app-bar
      app
      dark>
    <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
    <v-toolbar-title>Isd-food</v-toolbar-title>
    <v-badge
        :content="messages"
        :value="messages"
        color="green"
        overlap
        right
        class="float-right"
    >
      <v-icon large right>mdi-bell</v-icon>
    </v-badge>
  </v-app-bar>
</div>
</template>

<script>
export default {
name: "navBarDashBoard",

  data: () => ({
    drawer: null,
    links:[
      {icon:"mdi-account",text:"Account",route:"/userEdit"},
      {icon:"mdi-chart-bar",text:"Statistic",route:"/statistic"},
      {icon:"mdi-food",text:"Menus",route:"/menu"},
      {icon:"mdi-cart-outline",text:"Orders",route:"/orders"},
      {icon:"mdi-calendar",text:"Calendar",route:"/calendar"},
      {icon:"mdi-account-off",text:"Sign out",route:"/sign out"}
    ],
    messages: 1,
    show: false,
  }),
}
</script>

<style scoped>

</style>