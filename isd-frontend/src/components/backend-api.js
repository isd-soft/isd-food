import axios from "axios";

const AXIOS = axios.create({
    baseURL: `http://localhost:8085`,
    timeout: 10000000,
});

// Add a response interceptor
AXIOS.interceptors.response.use(function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    if (response.headers["authorization"] != null && localStorage.getItem("access_token") !== response.headers["authorization"]) {
        localStorage.setItem("access_token", response.headers["authorization"])
    }
    return response;
}, function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    return Promise.reject(error);
});


// Add a request interceptor
AXIOS.interceptors.request.use(function (config) {
    // Do something before request is sent
    if (!config.baseURL.includes("login"))
        config.headers.Authorization = localStorage.getItem("access_token");

    return config
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});

export default {
    createUser(user) {
        console.log(user);
        return AXIOS.post(`/users`, user,);
    },
    login(email, password) {
        return AXIOS.post(`/login`, {
            email: email,
            password: password,
        });
    },
};