import axios from "axios";

const AXIOS = axios.create({
  baseURL: `http://localhost:8085`,
  timeout: 10000000,
});

export default {
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
