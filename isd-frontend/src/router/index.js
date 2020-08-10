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
    path: "/calendar",
    name: "Calendar",
    component: () => import("../views/Calendar.vue"),
  },
  {
    path: "/menu",
    name: "Menu",
    component: () => import("../views/Menu.vue"),
  },
  {
    path: "/orders",
    name: "Orders",
    component: () => import("../views/Orders.vue"),
  },
  {
    path: "/statistic",
    name: "Statistic",
    component: () => import("../views/Statistic.vue"),
  },
  {
    path: "/userEdit",
    name: "User Edit",
    component: () => import("../views/UserEdit.vue"),
  },
  {
    path: "/users/register",
    name: "Register",
    component: () => import("../views/Register.vue"),
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
  {
    path: "/user/{id}/edit",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
        import(/* webpackChunkName: "about" */ "../views/UserEdit.vue"),
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
