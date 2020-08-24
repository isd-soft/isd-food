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
        name: "Create menu",
        component: () => import("../components/Addmenu.vue")
    },

    {
        path: "/allmenus",
        name: "Menus",
        component: () => import("../components/AllMenus.vue")
    },

    {
        path: "/ProviderList",
        name: "Provider list",
        props: true,
        component: () => import("../views/ProviderList.vue")
    },

    {
        path: "/ProviderOrders",
        name: "Orders",
        component: () => import("../views/ProviderOrders.vue")
    },

    {
        path: "/confirm",
        name: "confirmation",
        component: () => import("../components/confirmationDialog")
    },


    {
        path: "/users/register",
        name: "Create user",
        component: () => import("../views/Register.vue")
    },
    {
        path: "/orders",
        name: "Orders",
        component: () => import("../views/Orders.vue")
    },

    {
        path: "/menu",
        name: "Menu",
        component: () => import("../views/Menu.vue"),
    },

    {
        path: "/providers",
        name: "Create new provider",
        component: () => import("../views/Provider.vue"),
    },
    {
        path: '*',
        name: "error",
        component: () => import("../views/404"),
    },

    {
        path: "/users/table",
        name: "Users",
        component: () => import("../views/UsersTable.vue")
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
