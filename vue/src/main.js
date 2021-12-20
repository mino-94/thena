import {createApp} from "vue"
import App from "./App.vue"
import {Store} from "./store"
import router from "./router"
import vueAgile from "vue-agile"
import {Api} from "./assets/js/api"
import {Msg} from "./assets/js/message"
import {Util} from "./assets/js/util"
import {Modal} from "./assets/js/modal"
import "es6-promise/auto"

const app = createApp(App);
app.use(router).use(Store).use(vueAgile).mount("#app");
app.config.globalProperties.$Api = Api;
app.config.globalProperties.$Msg = Msg;
app.config.globalProperties.$Util = Util;
app.config.globalProperties.$Modal = Modal;
