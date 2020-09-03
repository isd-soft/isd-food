<template>
  <table
      class="table"
      width="100%"
      cellspacing="0"
  >
    <thead>
    <tr>
      <th>Provider</th>
      <th>Menu</th>
      <th>
        <span v-show="$store.getters.userOrdersType === 'current'"> Date</span>
        <span v-show="$store.getters.userOrdersType === 'history'" class="sort-column" name="sortByDate"
              @click="switchSortCol" v-html="dateCol">
      </span></th>
      <th><span v-show="$store.getters.userOrdersType === 'current'"> Price</span>
        <span v-show="$store.getters.userOrdersType === 'history'"
              class="sort-column"
              name="sortByPrice"
              @click="switchSortCol"
              v-html="priceCol">
      </span></th>
      <th v-if="$store.getters.userOrdersType === 'current'">Delete</th>
      <th v-if="$store.getters.userOrdersType === 'current'">Edit</th>
    </tr>
    </thead>
    <tbody v-for="order of $store.getters.userOrders" :key="order.id">
    <tr>
      <td>{{ order.providerName }}</td>
      <td>{{ order.menuName + " " + order.menuType }}</td>
      <td>{{ order.date }}</td>
      <td>{{ order.price }} lei</td>
      <td class="text-left" v-if="$store.getters.userOrdersType === 'current'">
        <button
            @click="$emit('setOrderToDelete', order.id)"
        >
          <i class="fas fa-trash"/>
        </button>
      </td>
      <td class="text-left" v-if="$store.getters.userOrdersType === 'current'">
        <button
            @click="$emit('openOrderEdit', order.id)"
        >
          <i class="fas fa-edit"/></button>
      </td>
    </tr>
    </tbody>
  </table>
</template>
<script>
export default {
  name: "orders",
  data() {
    return {
      sortByDate: null,
      sortByPrice: null,
      dateCol: 'Date <i class="fas fa-sort"/>',
      priceCol: 'Price <i class="fas fa-sort"/>'
    };
  },
  methods: {
    switchSortCol(e) {
      let varName = e.target.attributes.name.value;

      if (this[varName] == null)
        this[varName] = "ASC"
      else if (this[varName] === "ASC")
        this[varName] = "DESC"
      else this[varName] = null;

      this[varName.split("y")[1].toLowerCase() + "Col"] = this.setFieldColValue(varName, varName.split("y")[1], varName.split("y")[1].toLowerCase() + "Col")

      this.$emit('setSortColsDirection', {name: varName, direction: this[varName]})
    },
    setFieldColValue(varName, fieldName) {
      if (this[varName] == null)
        return fieldName + ' <i class="fas fa-sort"/> '
      else if (this[varName] === "ASC")
        return fieldName + ' <i class="fas fa-caret-up"/>'
      else return fieldName + ' <i class="fas fa-caret-down"/>'
    },

  }
}
</script>