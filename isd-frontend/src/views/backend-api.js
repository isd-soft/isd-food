import axios from "axios";

const AXIOS = axios.create({
  baseURL: `/api`,
  timeout: 1000,
});

export default {
   hello() {
        return AXIOS.get(`/hello`);
    },
  createUser(user) {
    console.log(user);
    return AXIOS.post(`/users`, user, {
      headers: { Authorization: window.$cookies.get("access_token") },
    });
  },
  login(email, password) {
    return AXIOS.post(`/login`, {
      email: email,
      password: password,
    });
  },
};
