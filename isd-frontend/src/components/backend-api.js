import axios from "axios";

const AXIOS = axios.create({
  baseURL: `/api`,
  timeout: 1000
});


export default {

  getMenu() {
    return AXIOS.get(`/menu`);
},

  getMenuType(){
  return AXIOS.get('/menu_type')
},

  Hello(){
    return AXIOS.get('/hello')
},


createUser(firstName, lastName) {
  return AXIOS.post(`/user/` + firstName + '/' + lastName);
},

createOrder(firstName, lastName) {
  return AXIOS.post(`/menu/` + firstName + '/' + lastName);
},

  login(email, password) {
    return AXIOS.post(`/login`, {
      email: email,
      password: password,
    });
  },

};
