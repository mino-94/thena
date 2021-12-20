import axios from "axios"
import qs from "querystring"
import interceptor from "./interceptor"
// import {Store} from "../../../store";

axios.interceptors = interceptor;
// const params = Store.getters.getParams;

export const Api = {
    guard : function(method, to) {
        return axios({
            method : method,
            url : to
        });
    },
    login : function(params) {
        return axios.post("/login", qs.stringify(params));
    },
    logout : function() {
        return axios.post("/logout");
    },
    getWeekTop : function (params) {
        return axios.post("/week/top", qs.stringify(params));
    }
};
