import {Util} from "../assets/js/util"

export const Routes = [
    {
        path : "/",
        name : "Home",
        component : () => import("../views/Home"),
        meta : {
            title : Util.setTitle("메인"),
            secure : false
        }
    },
    {
        path : "/login",
        name : "Login",
        component : () => import("../views/Login"),
        meta : {
            title : Util.setTitle("로그인"),
            secure : false,
            prevent : true
        }
    },
    {
        path : "/join",
        name : "Join",
        component : () => import("../views/Join"),
        meta : {
            title : Util.setTitle("회원가입"),
            secure : false,
            prevent : true
        }
    },
    {
        path : "/guide",
        name : "Guide",
        component : () => import("../views/Guide"),
        meta : {
            title : Util.setTitle("이용 가이드"),
            secure : false
        }
    },
    {
        path : "/detail/admin",
        name : "TEST",
        component : () => import("../views/test/detail"),
        meta : {
            title : "TEST"
        }
    },
    {
        path : "/:pathMatch(.*)*",
        component : () => import("../views/error/404"),
        meta : {
            title : "404",
            prevent : true
        }
    }
];
