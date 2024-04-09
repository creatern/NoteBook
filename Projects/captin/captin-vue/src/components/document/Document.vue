<script setup>
import { inject, ref } from "vue";

/* Get 获取 documents 数据，用于构建图*/
const axios = inject("axios");
const documentsGraph = ref(null);
// axios 请求
axios.get("/api/documents/graph").then((reps) => {
  documentsGraph.value = reps.data; // 获取响应数据
});
</script>
<template></template>
