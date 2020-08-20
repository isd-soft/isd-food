<template>
  <v-app>
    <table
        class="table table-bordered"
        width="100%"
        cellspacing="0"
    >
      <thead>
      <tr>
        <th>Provider</th>
        <th>Menu</th>
<!--        <th>Menu type</th>-->
        <th>Date</th>
        <th>Price</th>
        <th v-if="$store.getters.userOrdersType === 'current'">Delete</th>
      </tr>
      </thead>
      <tbody v-for="order of $store.getters.userOrders" :key="order.id">
      <tr>
        <td>{{ order.providerName }}</td>
        <td>{{ order.menuName +" "+order.menuType}}</td>
<!--        <td>{{  order.menuType }}</td>-->
        <td>{{ order.date }}</td>
        <td>{{ order.price }} lei</td>
        <td class="text-center" v-if="$store.getters.userOrdersType === 'current'">
          <button
              @click="deleteOrder(order.id)"
          >
            <v-icon data-toggle="modal" data-target="#exampleModal"
            >fas fa-trash-alt
            </v-icon
            >
          </button>
        </td>
      </tr>
      </tbody>
    </table>
  </v-app>
</template>
<script>
export default {
  name: "orders",
  data() {
    return {
    };
  },
  methods: {
    deleteOrder(orderId) {
      console.log("delete order :"+orderId)
      this.$store.dispatch("deleteUserOrder", orderId)
    }
  }
}
</script>