<script setup>
/* Get 获取 repositories 的列表，用于生成对应的仓库链接 */
axios({
  method: "get",
  url: "/api/repository/list",
}).then((response) => {
  console.log(url);
  console.log(response.data);
});
</script>

<template></template>
