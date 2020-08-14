import axios from "axios";

const AXIOS = axios.create({
  baseURL: `/api`,
  timeout: 10000000
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
  function(config) {
    // Do something before request is sent
    if (!config.baseURL.includes("login"))
      config.headers.Authorization = localStorage.getItem("access_token");

    return config;
  },
  function(error) {
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

  getMenu() {
    return AXIOS.get(`/menu`);
  },

  createOrder(user_id, menu_type_id) {
    return AXIOS.post(`/order/` + user_id + "/" + menu_type_id);
  },

  getMenuType() {
    return AXIOS.get("/menu_type");
  },

  getHistory() {
    return AXIOS.get("/get/all");
  },

  createProvider(provider) {
    console.log(provider);
    return AXIOS.post(`/provider`, provider);
  }
};
