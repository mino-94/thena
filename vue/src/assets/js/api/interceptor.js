import axios from "axios"
import {Store} from "@/store"
import {Util} from "../util"

export const interceptor = axios.interceptors;

interceptor.request.use(
    function(config) {
        const method = config.method;
        config.baseURL = "/api";

        if (method == "post") {
            config.headers["X-CSRF-TOKEN"] = Store.getters.getToken;
        }
        return config;
    },
    function(error) {
        const err = error.response.data;
        console.log(JSON.stringify(err));

        return Promise.reject(err);
    }
);

interceptor.response.use(
    function(response) {
        const data = response.data;
        const result = data.result;

        Util.setResponse(data);
        Store.commit("setData", result);

        if (!Util.isNull(result) && !Util.isNull(result.msg)) {
            console.log(result.msg);
        }
        return result;
    },
    function(error) {
        const data = error.response.data;
        const result = data.result;

        Util.setResponse(data);
        Util.setErrorAlert(result.redirect, result.msg, error.response.status);

        return Promise.reject(result);
    }
);

export default interceptor;
