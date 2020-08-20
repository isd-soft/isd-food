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
    getAllCurrentOrders(){
     return AXIOS.get("/orders?ordered=false");
    },
    getConfirmedOrders(){
        return AXIOS.get("/orders?ordered=true");

    },
    editProvider(id,name,contact,price,active,desc,img){
        return AXIOS.put("/provider/edit/"+id+"/"+name +"/" + contact + "/" + price + "/" + active +"/" + desc +"/" + img)
    },
    getAllOrders(){
        return AXIOS.get("/orders?ordered=false");
    },
    getAllUsers(){
        return AXIOS.get("/users/all");
    },
    deleteOrder(id){
        return AXIOS.delete("/orders/delete/" + id)
    },

    confirmOrderId(id,confirm){
        return AXIOS.put("/orders/confirm/" + id + "/" + confirm)
    },
    getHistory(){
        return AXIOS.get("/users/orders");
    },
    getProvidersOrders(){
        return AXIOS.get("/orders?ordered=false");
    },
    getAllProviders(){
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
    getUserRole() {
        return AXIOS.get(`/users/role`);
    },
    resetPassword(email) {
        return AXIOS.post(`/users/password/reset?email=` + email,);
    },
    getUserWithoutId() {
        return AXIOS.get("users/getUser");
    },

  getMenu() {
    return AXIOS.get(`/menu`);
  },

    createOrder(order) {
        return AXIOS.post(`/orders`, order);
    },

  addOrder(order){
    return AXIOS.post(`/orders`, order);
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

    createFullMenu(menu){
        return AXIOS.post(`/newMenuFull`, menu).catch(err => console.log(err));
    },


  getMenuType() {
    return AXIOS.get("/menu_type");
  },

  getUser(user_id) {
    return AXIOS.get("/user/" + user_id);
  },


    getItems(){
        return AXIOS.get("/items");
    },

    getProviders(){
      return AXIOS.get("/provider");
    },

  getUsers() {
    return AXIOS.get("/users/allUsers");
  },

  deleteUser(user_id) {
    return AXIOS.delete("/users/deleteUser/" + user_id);
  },

  updateUser(user_id, firstName, lastName, skypeId, email, enable, data) {
    return AXIOS.put(
      "/users/edit/" +
        user_id +
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


  getMenuDay(day){
    return AXIOS.get("/menu/day?day="+day)
},
    getProviderById(id){
        return AXIOS.get("/provider/getProvider/" + id)
    },
    deleteProvider(id){
       return AXIOS.delete("/provider/delete/" + id)
    },




    newItem(itemName){
        return AXIOS.post("/new_item?name="+itemName);
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
