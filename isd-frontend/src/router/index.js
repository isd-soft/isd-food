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
            requiresAuth: true
        }
    },
    {
        path: "/login",
        name: "Login",
        component: () => import("../views/Login.vue")
    },
    {
        path: "/edit",
        name: "Edit",
        component: () => import("../views/EditUser.vue")
    },

    {

        path: "/add_menu",
        name: "AddMenu",
        component: () => import("../components/Addmenu.vue")
    },

    {
        path: "/allmenus",
        name: "AllMenus",
        component: () => import("../components/AllMenus.vue")
    },

    {
        path: "/ProviderList",
        name: "ProviderList",
        props: true,
        component: () => import("../views/ProviderList.vue")
    },

    {
        path: "/ProviderOrders",
        name: "Provider Orders",
        component: () => import("../views/ProviderOrders.vue")
    },

    {
        path: "/users/register",
        name: "Register",
        component: () => import("../views/Register.vue")
    },
    {
        path: "/orders",
        name: "Orders",
        component: () => import("../views/Orders.vue")
    },

    {
        path: "/menu",
        name: "menu",
        component: () => import("../views/Menu.vue"),
    },

    {
        path: "/providers",
        name: "Provider",
        component: () => import("../views/Provider.vue"),
    },
    {
        path: '*',
        name: "error",
        component: () => import("../views/404"),
    },

    {
        path: "/users/table",
        name: "UsersTable",
        component: () => import("../views/UsersTable.vue")
    },
    {
        path: "/payment",
        name: "Payment",
        component: () => import("../views/Payment.vue")
    },
    {
        path: "/about",
        name: "About",
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import(/* webpackChunkName: "about" */ "../views/About.vue")
    }
 

];

const router = new VueRouter({
    mode: "history",
    routes
});

router.beforeEach((to, from, next) => {
    // if (to.matched.some(record => record.meta.requiresAuth)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    if (to.path !== "/login")
        if (localStorage.getItem("access_token") == null) {
            next({
                path: "/login"
            });
        } else {
            next();
        }
      next(); // make sure to always call next()!
});
export default router;
