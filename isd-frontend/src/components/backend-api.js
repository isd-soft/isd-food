import axios from "axios";

const AXIOS = axios.create({
    baseURL: `http://localhost:8098/api`,
    timeout: 10000
});

// Add a response interceptor
AXIOS.interceptors.response.use(
  function(response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    if (
      response.headers["authorization"] != null &&
      localStorage.getItem("access_token") !== response.headers["authorization"]
    ) {
      localStorage.setItem("access_token", response.headers["authorization"]);
    }
    return response;
  },
  function(error) {
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
    deleteOrder(id){
        return AXIOS.delete('/orders/' + id)
    },
    getHistory(){
        return AXIOS.get("/users/orders");
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
        return AXIOS.post(`/users/password/reset?email=` + email,);
    },
    getUserWithoutId() {
        return AXIOS.get("users/getUser");
    },

  getMenu() {
    return AXIOS.get(`/menu`);
  },

  createOrder(user_id, menu_type_id) {
    return AXIOS.post(`/order/` + user_id + "/" + menu_type_id);
  },

  addOrder(order){
    return AXIOS.post(`/orders`, order);
  },

  createProvider(provider) {
    console.log(provider);
    return AXIOS.post(`/provider`, provider);
  },

  getMenuType() {
    return AXIOS.get("/menu_type");
  },
  getUser(user_id) {
    return AXIOS.get("/user/" + user_id);
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

  changePass(user_id, password) {
    return AXIOS.put(
      "/user/edit/password/" + user_id + "?password=" + password
    );
  }
};
