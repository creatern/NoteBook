// 引入 css入口
import "./assets/main.css";

// 引入 createApp 创建应用
import { createApp } from "vue";
// 引入 App 根组件
import App from "./App.vue";
// 引入路由
import router from "./router";

// 引入自建组件
import Guide from "./components/home/Guide.vue";
import Document from "./components/home/Document.vue";
import Information from "./components/home/Infomation.vue";
import Record from "./components/home/Record.vue";
import Repositories from "./components/home/Repositories.vue";
import Todo from "./components/home/Todo.vue";

// 创建 App 应用
const app = createApp(App);
// 使用 router
app.use(router);
// 挂载组件
app.component("Guide", Guide);
app.component("Document", Document);
app.component("Information", Information);
app.component("Record", Record);
app.component("Repositories", Repositories);
app.component("Todo", Todo);
// 挂载整个应用到 app 容器
app.mount("#app");
