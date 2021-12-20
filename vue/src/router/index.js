import {createRouter, createWebHistory} from "vue-router"
import {Routes} from "./routes"
import {Api} from "../assets/js/api"
import {Store} from "../store"
import {Util} from "../assets/js/util"

const routes = Routes;

const router = createRouter({
    history : createWebHistory(process.env.BASE_URL),
    routes
});

router.beforeEach(function(to, from, next) {
    const toMatch = to.matched[0];
    const meta = toMatch.meta;
    const secure = Util.isNull(meta.secure) ? true : meta.secure;
    const method = secure ? "post" : "get";
    const prevent = Util.isNull(meta.prevent) ? false : meta.prevent;
    const pass = Util.isNull(meta.pass) ? false : meta.pass;
    document.title = meta.title;

    if (Util.isNull(toMatch.name) || pass) {
        next();
    } else {
        Api.guard(method, to.path).then(response => {
            const redirect = Util.isNull(response) ? null : response.redirect;

            if (prevent && Store.getters.isAuth) {
                returnFalse(from.path, next);
            } else if (redirect != null && to.path != redirect) {
                if (Util.isLogin(redirect)) {
                    errorAlert(redirect, "Required Login", 403, next);
                } else {
                    returnTrue(redirect, next)
                }
            } else {
                returnTrue(null, next)
            }
        }).catch(() => {
            returnFalse(null, next);
        });
    }
});

router.afterEach(function (to, from, next) {
    const toMatch = to.matched[0];
    const fromMatch = from.matched[0];
    const meta = Util.isNull(next) ? toMatch.meta : fromMatch.meta;
    const prevent = Util.isNull(meta.prevent) ? false : meta.prevent;

    if (prevent != Store.getters.getPrevent) {
        Store.commit("setPrevent", prevent);
    }
});

const returnTrue = function (redirect, next) {
    if (!Util.isNull(redirect)) {
        next(redirect);
    } else {
        next();
    }
}

const returnFalse = function (redirect, next) {
    if (!Util.isNull(redirect)) {
        location.replace(redirect);
    }
    next(false);
}

const errorAlert = function (redirect, msg, code, next) {
    Util.setErrorAlert(redirect, msg, code);
    next(false);
}

export default router
