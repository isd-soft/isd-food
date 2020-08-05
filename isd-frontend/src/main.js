import Vue from 'vue'
import App from './App.vue'
import Register from './Register.vue'
import vuetify from './plugins/vuetify';


Vue.config.productionTip = false

new Vue({
  vuetify,
  render: h => h(App)
}).$mount('#app')

new Vue({
  vuetify,
  render: h => h(Register)
}).$mount('#register')