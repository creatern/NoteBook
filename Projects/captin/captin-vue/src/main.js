// 引入 css入口
import './assets/main.css'

// 引入 createApp 创建应用
import { createApp } from 'vue'
// 引入 App 根组件
import App from './App.vue'
// 引入路由
import router from './router'

// 创建 App 应用
const app = createApp(App)
// 使用 router
app.use(router)
// 挂载整个应用到 app 容器
app.mount('#app')
