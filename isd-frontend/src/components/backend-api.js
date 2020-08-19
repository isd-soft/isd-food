import axios from "axios";
import store from "@/store/index";

const AXIOS = axios.create({
    baseURL: `http://localhost:8098/api`,
    timeout: 10000
});

// Add a response interceptor
AXIOS.interceptors.response.use(
    function (response) {
        // Any status code that lie within the range of 2xx cause this function to trigger
        // Do something with response data
        if (
            response.headers["authorization"] != null &&
            localStorage.getItem("access_token") !== response.headers["authorization"]
        ) {
            localStorage.setItem("access_token", response.headers["authorization"]);
        }

        if (response.data !== null && response.data.errorType != null) {
            store.state.errorDialog = true;
            store.state.errorModel = response.data;
        }

        return response;
    },
    function (error) {
        // Any status codes that falls outside the range of 2xx cause this function to trigger
        // Do something with response error
        return Promise.reject(error);
    }
);

// Add a request interceptor
AXIOS.interceptors.request.use(
    function (config) {
        // Do something before request is sent
        console.log(config)
        if (!config.url.includes("login") || !config.url.includes("password/reset"))
            config.headers.Authorization = localStorage.getItem("access_token");
        else delete config.headers.Authorization;

        return config;
    },
    function (error) {
        // Do something with request error
        return Promise.reject(error);
    }
);

export default {
    createUser(user) {
        console.log(user);
        return AXIOS.post(`/users`, user);
    },
    login(email, password) {
        return AXIOS.post(`/login`, {
            email: email,
            password: password
        });
    },
    getUserRole() {
        return AXIOS.get(`/users/role`);
    },
    resetPassword(email) {
        return AXIOS.post(`/users/password/reset?email=` + email,);
    },

    getMenu() {
        return AXIOS.get(`/menu`);
    },

    createOrder(order) {
        return AXIOS.post(`/orders`, order);
    },

    createProvider(provider) {
        console.log(provider);
        return AXIOS.post(`/provider`, provider);
    },
    getMenuDay(day) {
        return AXIOS.get("/menu/day?day=" + day)
    },
    getMenuType() {
        return AXIOS.get("/menu_type");
    },
    getUser(user_id) {
        return AXIOS.get("/user/" + user_id);
    },
    getUserCurrentOrders() {
        return AXIOS.get("/users/orders?ordered=false");
    },
    getUserOrdersHistory() {
        return AXIOS.get("/users/orders");
    },
    deleteUserOrder(orderId) {
        return AXIOS.delete("/orders/" + orderId);
    },
    updateUser(user_id, firstName, lastName, skypeId, email) {
        return AXIOS.put(
            "/user/edit/" +
            user_id +
            "?firstName=" +
            firstName +
            "&lastName=" +
            lastName +
            "&skypeId=" +
            skypeId +
            "&email=" +
            email
        );
    },

    changePass(user_id, password) {
        return AXIOS.put(
            "/user/edit/password/" + user_id + "?password=" + password
        );
    },
    downloadPaymentData(month, year) {
        window.location.href = "http://localhost:8098/api/payment/export?month=" + month + "&year=" + year;
    }
};
