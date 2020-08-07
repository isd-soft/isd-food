import Vue from "vue";
import Vuex from "vuex";
import api from "../components/backend-api";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    login: {
      loginError: false,
      loginSuccess: false,
      email: null,
      password: null,
      loading: false,
    },
    register: { loading: false },
  },
  mutations: {
    login_success(state, payload) {
      state.login.loading = false;
      state.loginSuccess = true;
      state.email = payload.email;
      state.password = payload.password;
    },
    login_error(state, payload) {
      state.loginError = true;
      state.email = payload.email;
    },
    register_success(state) {
      state.register.loading = false;
    },
  },
  actions: {
    login({ commit }, { email, password }) {
      return new Promise((resolve, reject) => {
        this.state.login.loading = true;

        api
          .login(email, password)
          .then((response) => {
            if (response.status == 200) {
              window.$cookies.set(
                "access_token",
                response.headers["authorization"]
              );
              // place the loginSuccess state into our vuex store
              commit("login_success", {
                email: email,
                password: password,
              });
            }
            resolve(response);
          })
          .catch(() => {
            // place the loginError state into our vuex store
            commit("login_error", {
              email: email,
            });
            reject("Invalid credentials!");
          });
      });
    },
    createUser({ commit }, user) {
      return new Promise((resolve, reject) => {
        // this.state.login.loading = true;
        console.log(user);
        api
          .createUser(user)
          .then((response) => {
            if (response.status == 200) {
              // place the loginSuccess state into our vuex store
              commit("register_success", {
                user: user,
              });
            }
            resolve(response);
          })
          .catch(() => {
            // place the loginError state into our vuex store
            // commit("login_error", {
            //   email: email,
            // });
            reject("Invalid credentials!");
          });
      });
    },
  },
  modules: {},
  getters: {
    isLoggedIn: (state) => state.login.loginSuccess,
    hasLoginErrored: (state) => state.login.loginError,
    isLoggingInProcess: (state) => state.login.loading,
    getEmail: (state) => state.login.email,
    getPassword: (state) => state.login.password,
  },
});
