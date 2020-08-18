<template>
  <div>

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
      menus: []
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
  },

  beforeCreate() {
    api.getAllProviders().then(r => {this.providers = r.data; console.log(r.data)})
    api.getAllCurrentOrders().then(r => {this.orders = r.data.content; console.log(r.data)})
    api.getMenu().then(r => {this.menus = r.data; console.log(r.data)})
  }
}
</script>

<style scoped>

</style>