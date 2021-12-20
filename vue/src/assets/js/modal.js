import swal from "sweetalert2"
import router from "@/router"
import {Store} from "@/store"

export const Modal = {
    errorAlert : function(msg) {
        swal.fire({
            title : msg.title,
            text : msg.text,
            backdrop : "rgba(0, 0, 0, 0.1)",
            position : "top",
            width : "350px",
            heightAuto : false,
            allowOutsideClick : false,
            allowEnterKey : false,
            allowEscapeKey : false,
            confirmButtonText : "<b>확인</b>",
            confirmButtonColor : "#00b9ff"
        }).then(result => {
            if (result.value) {
                const redirect = Store.getters.getRedirect;

                if (redirect != null) {
                    Store.commit("setRedirect", null);
                    router.push(redirect);
                }
            }
        });
    }
}
