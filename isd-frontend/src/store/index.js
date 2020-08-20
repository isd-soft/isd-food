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
            error: null,
            showDialog: false
        },
        orders: {
            userCurrentOrders: [],
            userOrdersHistory: null,
            userOrdersType: "current",
            createOrderSuccess: false
        },
        register: {loading: false, error: false, success: false, errors: []},
        Provider: {loading: false},
        errorDialog: false,
        errorModel: null
    },
    mutations: {
        login_success(state, payload) {
            state.login.loading = false;
            state.loginSuccess = true;
            state.email = payload.email;
            state.password = payload.password;
        },
        login_error(state, payload) {
            state.login.loginError = true;
            state.login.error = payload.message;
            state.login.loading = false;
            setTimeout(() => {
                state.login.error = false;
                state.login.loginError = false;
            }, 3000)
        },
        register_success(state) {
            state.register.loading = false;
            state.register.success = true

            setTimeout(() => {
                state.register.success = false
            }, 3000)
        },
        register_error(state, payload) {
            state.register.loading = false;
            state.register.error = true;
            state.register.errors.push(payload.message);
            setTimeout(() => {
                state.register.error = false
                state.register.errors = [];
            }, 3000)
        },
        create_order_success(state) {
            console.log(state)
            state.orders.createOrderSuccess = true;
        }
    },
    actions: {
        login({commit}, {email, password}) {
            return new Promise((resolve, reject) => {
                this.state.login.loading = true;

                api
                    .login(email, password)
                    .then(response => {
                        if (response.status == 200) {
                            commit("login_success", {
                                email: email,
                                password: password
                            });
                            api.getUserRole().then(response => {
                                localStorage.setItem("userRole",response.data)
                            }).catch(()=>{

                            })
                        }
                        resolve(response);
                    })
                    .catch(() => {
                        // place the loginError state into our vuex store
                        commit("login_error", {
                            message: "Invalid credentials"
                        });
                        reject("Invalid credentials!");
                    });
            });
        },
        resetPassword({commit}) {
            return new Promise((resolve, reject) => {
                api
                    .resetPassword(this.state.login.email)
                    .then(response => {
                        if (response.status == 200) {
                            localStorage.removeItem("access_token")
                            commit("reset_password", {});
                        }
                        resolve(response);
                    })
                    .catch(() => {
                    });
            });
        },
        createUser({commit}, user) {
            return new Promise((resolve, reject) => {
                this.state.register.loading = true;
                api
                    .createUser(user)
                    .then(response => {
                        if (response.status == 201 && response.data.errorType == null) {
                            commit("register_success", {
                                user: user
                            });
                        } else {
                            commit("register_error", {
                                message: response.data.message
                            });
                        }
                        resolve(response);
                    })
                    .catch(() => {
                        commit("register_error", {
                            message: "Something went wrong...",
                        });
                        reject("Something went wrong...");
                    });
            });
        },
        createProvider({commit}, provider) {
            return new Promise((resolve, reject) => {
                // this.state.login.loading = true;
                console.log(provider);
                api
                    .createProvider(provider)
                    .then(response => {
                        if (response.status == 200) {
                            commit("register_success", {
                                provider: provider
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
        createOrder({commit}, order) {
            return new Promise((resolve, reject) => {
                api
                    .createOrder(order)
                    .then(response => {
                        if (response.status == 201 && response.data.errorType == null) {
                            commit("create_order_success");
                        }
                        resolve(response);
                    })
                    .catch(() => {
                        // place the loginError state into our vuex store
                        // commit("login_error", {
                        //   email: email,
                        // });
                        reject("Error create order");
                    });
            });
        },
        getUserCurrentOrders({commit}) {
            return new Promise((resolve, reject) => {
                api
                    .getUserCurrentOrders()
                    .then(response => {
                        if (response.status == 200) {
                            this.state.orders.userCurrentOrders = response.data
                        }
                        resolve(response);
                    })
                    .catch(() => {

                        reject("Error");
                    });
            });
        },
        getUserOrdersHistory({commit}) {
            return new Promise((resolve, reject) => {
                // this.state.login.loading = true;
                api
                    .getUserOrdersHistory()
                    .then(response => {
                        if (response.status == 200) {
                            this.state.orders.userOrdersHistory = response.data.content
                        }
                        resolve(response);
                    })
                    .catch(() => {
                        reject("Error");
                    });
            });
        },
        deleteUserOrder({commit, dispatch}, orderId) {
            return new Promise((resolve, reject) => {
                // this.state.login.loading = true;
                api
                    .deleteUserOrder(orderId)
                    .then(response => {
                        if (response.status == 200) {
                            dispatch('getUserCurrentOrders')
                        }
                        resolve(response);
                    })
                    .catch(() => {
                        reject("Error");
                    });
            });
        },

    },
    modules: {},
    getters: {
        isLoggedIn: state => state.login.loginSuccess,
        hasLoginErrored: state => state.login.loginError,
        isLoggingInProcess: state => state.login.loading,
        getEmail: state => state.login.email,
        getPassword: state => state.login.password,
        userCurrentOrders: state => state.orders.userCurrentOrders,
        userOrdersHistory: state => state.orders.userOrdersHistory,
        userOrdersType: state => state.orders.userOrdersType,
        userOrders: state => state.orders.userOrdersType === "current" ? state.orders.userCurrentOrders : state.orders.userOrdersHistory
    }
});
