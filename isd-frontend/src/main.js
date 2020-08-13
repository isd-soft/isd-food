import "@babel/polyfill";
import "mutationobserver-shim";
import Vue from "vue";
import "./plugins/bootstrap-vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import vueCookies from "vue-cookies";
import { ValidationProvider, extend } from "vee-validate";
import { required } from "vee-validate/dist/rules";

import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'



Vue.config.productionTip = false;


new Vue({
  components: {
    ValidationProvider
  },
  router,
  store,
  vuetify,
  vueCookies,
  render: h => h(App)
}).$mount("#app");
