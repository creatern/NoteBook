// router 引入
import { createRouter, createWebHistory } from "vue-router";

// 创建 router
const router = createRouter({
  // 工作模式
  history: createWebHistory(import.meta.env.BASE_URL),
  // 配置路由
  routes: [
    {
      path: "/home", // url路径
      name: "home",
      component: () => import("../views/HomeView.vue"), // 使用的组件
    },
    {
      path: "/document",
      name: "document",
      component: () => import("../views/DocumentView.vue"),
    },
  ],
});

export default router;
