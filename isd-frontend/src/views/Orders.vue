<template>
<div>

  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">History</h6>
    </div>
    <div class="card-body">
      <div class="table-responsive">
        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
          <thead>
          <tr>
            <th>Provider</th>
            <th>Menu</th>
            <th>Menu type</th>
            <th>Data</th>
            <th>Price</th>

          </tr>
          </thead>

          <tbody>
          <tr v-for="order of orders" :key="order.id">
            <td>{{order.id}}</td>
            <td>{{order.menu}}</td>
            <td>{{order.menu_type}}</td>
            <td>{{order.data}}</td>
            <td>{{order.price}}</td>
          </tr>

          </tbody>
        </table>
      </div>
    </div>
  </div>

</div>
</template>

<script>

export default {
  name: "Home",
};

</script>

<style>
</style>