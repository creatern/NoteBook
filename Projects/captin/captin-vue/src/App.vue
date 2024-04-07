<script setup>
// 引入 RouterView 和 RouterLink 标签
import { RouterView, RouterLink } from "vue-router";
</script>

<template>
  <div>
    <!-- RuoterLink 对a标签的一种替代： to 路由，active-class 激活该路由时启用的class -->
    <RouterLink to="/" active-class="link-active">Home</RouterLink>

    <!-- RouterView 标签： 表明路由（src/router/index.js）中配置对应的组件应该存放的位置 -->
    <RouterView></RouterView>
  </div>
</template>

<style scoped>
.link-active {
  background-color: rgb(255, 0, 0);
  color: rgb(110, 223, 220);
}
</style>
