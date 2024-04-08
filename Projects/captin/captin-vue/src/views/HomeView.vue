<script setup>
import { inject, ref } from "vue";

/* Get 获取 repositories 的列表，用于生成对应的仓库链接 */
const axios = inject("axios");
const repositoryList = ref(null);
// axios 请求
axios.get("/api/repository/list").then((reps) => {
  // [{"id":2,"category":"Java"},{"id":1,"category":"Linux"}]
  repositoryList.value = reps.data; // 获取响应数据
});
</script>

<template>
  <h1>HomeView</h1>
  <h3 v-if="repositoryList === null">Loading...</h3>
  <ul v-else>
    <li>Ok...</li>
    <li v-for="repo in repositoryList.value" :key="repo.id">
      {{ repo.category }}
    </li>
    <li>{{ repositoryList.value }}</li>
  </ul>
</template>

<style></style>
