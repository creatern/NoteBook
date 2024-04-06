// router 引入
import { createRouter, createWebHistory } from 'vue-router'

// 创建 router
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue')
    },
    {
      path: '/doc/vue',
      name: 'vue',
      component: ()=> import('../views/DocVue.vue')
    }
  ]
})

export default router
