<script setup>
// 引入 RouterView 和 RouterLink 标签
import { RouterView, RouterLink } from "vue-router";
</script>

<template>
  <!-- 侧边栏 -->
  <aside class="aside-nav-container">
    <!-- RuoterLink 对a标签的一种替代： to 路由，active-class 激活该路由时启用的class -->
    <RouterLink to="/home" active-class="link-active">Home</RouterLink>
    <RouterLink to="/document" active-class="link-active">Document</RouterLink>
  </aside>
  <!-- 页面栏 -->
  <div>
    <!-- RouterView 标签： 表明路由（src/router/index.js）中配置对应的组件应该存放的位置 -->
    <RouterView></RouterView>
  </div>
</template>

<style scoped>
.aside-nav-container {
  background-color: rgb(0, 255, 0);
  border-radius: 4px;
}
.link-active {
  background-color: rgb(255, 0, 0);
  color: rgb(110, 223, 220);
}
</style>
