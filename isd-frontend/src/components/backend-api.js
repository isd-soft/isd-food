import axios from "axios";
import store from "@/store/index";

const AXIOS = axios.create({
    baseURL: `http://localhost:8098/api`,
    timeout: 30000
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

        if (error.response.status === 403) {
            store.state.errorDialog = true;
            store.state.errorModel = {errorType: "Access denied", message: "You have no permission on this action"};
        }
        // Any status codes that falls outside the range of 2xx cause this function to trigger
        // Do something with response error
        return Promise.reject(error);
    }
);

// Add a request interceptor
AXIOS.interceptors.request.use(
    function (config) {
        // Do something before request is sent
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
    getAllCurrentOrders() {
        return AXIOS.get("/orders?ordered=false");
    },
    getConfirmedOrders() {
        return AXIOS.get("/orders?ordered=true");
    },
    editProvider(id, name, contact, price, active, desc, img) {
        return AXIOS.put(
            "/provider/edit/" +
            id +
            "/" +
            name +
            "/" +
            contact +
            "/" +
            price +
            "/" +
            active +
            "/" +
            desc +
            "/" +
            img
        );
    },
    getAllOrders() {
        return AXIOS.get("/orders?ordered=false");
    },
    getAllUsers() {
        return AXIOS.get("/users/all");
    },
    deleteOrder(id) {
        return AXIOS.delete("/orders/delete/" + id);
    },

    confirmOrderId(id, confirm) {
        return AXIOS.put("/orders/confirm/" + id + "/" + confirm);
    },
    getHistory() {
        return AXIOS.get("/users/orders");
    },
    getProvidersOrders() {
        return AXIOS.get("/orders?ordered=false");
    },
    getAllProviders() {
        return AXIOS.get("/provider/all");
    },
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
    resetPassword(email) {
        return AXIOS.post(`/users/password/reset?email=` + email);
    },
    getUserWithoutId() {
        return AXIOS.get("users/getUser");
    },
    changeMenu(menu) {
        return AXIOS.put("/updateMenu", menu)
    },
    getMenu() {
        return AXIOS.get(`/menu`);
    },

    createOrder(order) {
        return AXIOS.post(`/orders`, order);
    },

    addOrder(order) {
        return AXIOS.post(`/orders`, order);
    },
    getLastOrderDate() {
        return AXIOS.get(`/orders/lastOrder`);
    },
    confirmOrders(){
      return AXIOS.post("/orders/place")
    },
    createProvider(provider) {
        console.log(provider);
        return AXIOS.post(`/provider`, provider);
    },

    createMenu(menu) {
        console.log(menu);
        return AXIOS.post(`/new_menu`, menu);
    },

    createMenuType(menuType) {
        console.log(menuType);
        return AXIOS.post(`/new_menuType`, menuType);
    },

    createFullMenu(menu) {
        return AXIOS.post(`/newMenuFull`, menu).catch(err => console.log(err));
    },

    getMenuType() {
        return AXIOS.get("/menu_type");
    },

    getUser(user_id) {
        return AXIOS.get("/user/" + user_id);
    },

    getItems() {
        return AXIOS.get("/items");
    },

    getProviders() {
        return AXIOS.get("/provider/all");
    },

    getUsers({page, employmentDateSort}) {
        return AXIOS.get("/users?page=" + page + employmentDateSort);
    },
    searchUsers(name) {
        return AXIOS.get("/users/search?keywords=" + name)
    },
    getUserByName(name) {
        return AXIOS.get("/users?name=" + name)
    },
    deleteUser(user_id) {
        return AXIOS.delete("/users/deleteUser/" + user_id);
    },

    updateUser(firstName, lastName, skypeId, email, enable, data) {
        return AXIOS.put(
            "/users/edit/" +
            "?firstName=" +
            firstName +
            "&lastName=" +
            lastName +
            "&skypeId=" +
            skypeId +
            "&email=" +
            email +
            "&enable=" +
            enable +
            "&data=" +
            data
        );
    },
    updateUserBySupervisor(
        user_id,
        firstName,
        lastName,
        skypeId,
        email,
        role,
        enable,
        data
    ) {
        return AXIOS.put(
            "/users/editBySupervisor/" +
            user_id +
            "?firstName=" +
            firstName +
            "&lastName=" +
            lastName +
            "&skypeId=" +
            skypeId +
            "&email=" +
            email +
            "&role=" +
            role +
            "&enable=" +
            enable +
            "&data=" +
            data
        );
    },

    getMenuDay(day) {
        return AXIOS.get("/menu/day?day=" + day);
    },
    getProviderById(id) {
        return AXIOS.get("/provider/getProvider/" + id);
    },
    deleteProvider(id) {
        return AXIOS.delete("/provider/delete/" + id);
    },

    deleteMenu(id) {
        return AXIOS.delete("/menu/delete/" + id)
    },

    newItem(itemName) {
        return AXIOS.post("/new_item?name=" + itemName);
    },

    changePass(password) {
        return AXIOS.put(
            "/users/edit/password?password=" + password
        );
    },
    getAvailableProviders(dateFromParam, dateToParam) {
        return AXIOS.get("/provider/available" + dateFromParam + dateToParam + (dateFromParam === "" && dateToParam === "" ? "?" : "&") + "ordered=true")
    },
    getUserCurrentOrders() {
        return AXIOS.get(
            "/users/orders?ordered=false"
        );
    },
    getUserOrdersHistory({page, dateFrom, dateTo, providers, sort}) {
        return AXIOS.get(
            "/users/orders?ordered=true&page=" + page + dateFrom + dateTo + providers + sort
        );
    },
    deleteUserOrder(orderId) {
        return AXIOS.delete("/orders/" + orderId)
    },
    getOrder(orderId) {
        return AXIOS.get("/orders/" + orderId)
    },
    updateOrder(orderId, order) {
        return AXIOS.put("/orders/" + orderId, order)
    },
    // Payment endpoints
    getUserPaymentOnMonth(month, year) {
        return AXIOS.get("/users/payment/monthly?month=" + month + "&year=" + year);
    },
    getUserPaymentOnPeriod(dateFrom, dateTo) {
        return AXIOS.get("/users/payment?dateFrom=" + dateFrom + "&dateTo=" + dateTo);
    },
    getAllUserPaymentOnMonth(month, year, page, paymentSort) {
        return AXIOS.get("/payment/monthly?month=" + month + "&year=" + year + "&page=" + (page - 1) + paymentSort);
    },
    getAvailableMonths() {
        return AXIOS.get("/payment/months");
    },
    getAllUserPaymentOnPeriod(dateFrom, dateTo, page) {
        return AXIOS.get("/payment?dateFrom=" + dateFrom + "&dateTo=" + dateTo + "&page=" + (page - 1));
    },
    downloadMonthlyPaymentData(month, year) {
        window.location.href = "http://localhost:8098/api/payment/export?month=" + month + "&year=" + year;
    },
    downloadPaymentDataByPeriod(dateFrom, dateTo) {
        window.location.href = "http://localhost:8098/api/payment/export?dateFrom=" + dateFrom + "&dateTo=" + dateTo;
    }
};
