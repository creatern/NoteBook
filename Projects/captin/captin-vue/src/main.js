// 引入 css入口
import "./assets/main.css";
// 引入 axios
import axios from "axios";
import VueAxios from "vue-axios";
// 引入 createApp 创建应用
import { createApp } from "vue";
// 引入 App 根组件
import App from "./App.vue";
// 引入路由
import router from "./router";

// 创建 App 应用
const app = createApp(App);
// 使用 router
app.use(router);
// 引入 axios
app.use(VueAxios, axios);
// 全局注入axios
app.provide("axios", app.config.globalProperties.axios);
// 挂载整个应用到 app 容器
app.mount("#app");
