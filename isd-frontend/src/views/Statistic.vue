<template>
<div>
  <div  class="row container" v-if="ordersFalse.length != 0" >
    <div class="col-xl-12 col-md-12 mb-4">
      <div class="card border-left-warning shadow h-100 py-1">
        <div class="card-body">


          <table  class="table table-bordered"  width="100%" cellspacing="0" >
            <thead>
            <tr>
              <th>User</th>
              <th>Menu</th>
              <th>MenuType</th>
              <th>Provider</th>
              <th>Confirm</th>

            </tr>
            </thead>

            <tbody v-for="order in ordersFalse" :key = "order.providerName + order.date">
            <tr>
              <td>{{ getUserName(order.userId) }}</td>
              <td>{{order.menuName}}</td>
              <td>{{order.menuType}}</td>
              <td>{{order.providerName}}</td>
              <td class="text-center">
                <button type="submit" @click="confirmOrder(order.id)" onclick="window.location.reload();">
                <v-icon  data-toggle="modal"  data-target="#exampleModal">fas fa-check</v-icon>
                </button>
              </td>

            </tr>

            </tbody>


          </table>


        </div>
      </div>
    </div>
  </div>
  <!---Statistic--->
  <div  class="row container">
    <div class="col-xl-3 col-md-6 mb-4"  v-for="provider of this.providers" :key="provider.name + provider.deliveryPrice">
      <div class="card border-left-warning shadow h-100 py-1">
        <div class="card-body">

          <table  class="table table-bordered"  width="100%" cellspacing="0">
            <thead>
            <tr>
              <th>{{provider.name}}</th>
              <th>S</th>
              <th>M</th>
            </tr>
            </thead>

            <tbody v-for="menu of menus" :key="menu.id">
            <tr v-if="menu.provider.name === provider.name">
              <td>{{menu.name}}</td>
              <td>{{calcOnce(menu.name,provider.name,"S")}}</td>
              <td>{{calcOnce(menu.name,provider.name,"M")}}</td>
            </tr>
            </tbody>

            <tbody >
            <tr>
              <td><b>Total:</b></td>
              <td>{{calcForEach(provider.name,"S")}}</td>
              <td>{{calcForEach(provider.name,"M")}}</td>
            </tr>
            </tbody>

          </table>

        </div>
      </div>
    </div>
  </div>
</div>



</template>

<script>
import api from "@/components/backend-api";


export default {
  data (){
    return{
      n: 0,
      m: 0,
      providers:[],
      orders:[],
      menus: [],
      users:[],
      ordersFalse:[]
    }
  },
  name: "Statistic",
  methods:{
    calcOnce(menuName,Provider,Type){
      let count = 0;
      this.orders.forEach(el => {
        if(menuName === el.menuName && Provider === el.providerName && Type === el.menuType){
          count++;
        }
      })
      return count;
    },
    calcForEach(provider,type){
      let res = 0;
      this.menus.forEach(el => {
        res += this.calcOnce(el.name,provider,type);
      })
      return res;
    },
    getUserName(id){
      var name = "standart";
      this.users.forEach(el => {
        if(el.id === id)
          name =  el.firstName + " " + el.lastName;
      })
      return name;
    }
    ,
    confirmOrder(id){
      api.confirmOrderId(id);
    }

  },

  beforeCreate() {
    api.getAllProviders().then(r => {this.providers = r.data; console.log(r.data)})
    api.getAllCurrentOrders().then(r => {this.orders = r.data.content; console.log(r.data)})
    api.getMenu().then(r => {this.menus = r.data; console.log(r.data)})
    api.getAllUsers().then(r => {this.users = r.data; console.log(r.data)})
    api.getAllOrders().then(r => {this.ordersFalse = r.data.content; console.log(r.data)})
  }
}
</script>

<style scoped>

</style>