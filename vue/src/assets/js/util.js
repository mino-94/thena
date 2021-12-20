import {Store} from "@/store"
import {Modal} from "./modal"
import {Msg} from "./message"

export const Util = {
    isNull : function(data) {
        if (data == null) {
            return true;
        }
        return false;
    },
    isNullEmpty : function(data) {
        if (data == null || data == "") {
            return true;
        }
        return false;
    },
    isLogin : function(redirect) {
        if (redirect == "/login") {
            return true;
        }
        return false;
    },
    setTitle : function(name) {
        return name + " | Tha";
    },
    setNumberFormat : function(num) {
        return num.toLocaleString(navigator.language);
    },
    setErrorAlert : function(redirect, msg, code) {
        console.log(msg);
        Store.commit("setRedirect", redirect);
        Modal.errorAlert(Msg.errorMsg(code, msg));
    },
    setResponse : function(data) {
        Store.commit("setToken", data.xsrfToken);
        Store.commit("setAuth", data.authentication);
    }
}