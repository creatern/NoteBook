<script setup>
import { inject } from "vue";

/* Get 获取 repositories 的列表，用于生成对应的仓库链接 */
const axios = inject("axios");
axios.get("/api/repository/list").then((reps) => {
  console.log("OK");
  console.log(reps.data);
});
</script>

<template></template>
