import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";




Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("../views/Login.vue"),
  },

  {
    path: "/callservice", 
    name: "Service",
    component: () => import(/* webpackChunkName: "about" */"../views/Service.vue"), 
  },
  
  {
    path: "/users/register",
    name: "Register",
    component: () => import(/* webpackChunkName: "about" */ "../views/Register.vue"),
  },
  {
    path: "/about",
    name: "About",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },

];

const router = new VueRouter({
  mode: "history",
  routes,
});

router.beforeEach((to, from, next) => {
  console.log(to.matched.some((record) => record.meta.requiresAuth));
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    if (window.$cookies.get("access_token") == null) {
      next({
        path: "/login",
      });
    } else {
      next();
    }
  } else {
    next(); // make sure to always call next()!
  }
});
export default router;
