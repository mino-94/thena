import {createStore} from "vuex"
import persistedstate from "vuex-persistedstate"

export const Store = createStore({
    plugins : [
        persistedstate({
            paths : ["params"]
        })
    ],
    state : {
        account : null,
        data : null,
        params : null,
        csrfToken : null,
        redirect : null,
        prevent : false
    },
    getters : {
        isAuth(state) {
            if (state.account != null) {
                return true;
            } else {
                return false;
            }
        },
        getCorporation(state) {
            return state.account.corporation;
        },
        getUserName(state) {
            return state.account.userName;
        },
        getUserType(state) {
            return state.account.userType;
        },
        getUserRole(state) {
            return state.account.userRole;
        },
        getToken(state) {
            return state.csrfToken;
        },
        getData(state) {
            return state.data;
        },
        getParams(state) {
            return state.params;
        },
        getRedirect(state) {
            return state.redirect;
        },
        getPrevent(state) {
            return state.prevent;
        }
    },
    mutations : {
        setAuth(state, data) {
            state.account = data;
        },
        setToken(state, data) {
            state.csrfToken = data;
        },
        setData(state, data) {
            state.data = data;
        },
        setParams(state, data) {
            state.params = data;
        },
        setRedirect(state, data) {
            state.redirect = data;
        },
        setPrevent(state, data) {
            state.prevent = data;
        }
    },
    actions : {
    }
});
