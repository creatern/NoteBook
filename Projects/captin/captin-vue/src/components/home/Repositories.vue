<script setup>
import axios from "axios";

axios({
  method: "get",
  url: "http://127.0.0.1:8080/repository/list",
}).then((response) => {
  console.log(response.data);
});
</script>
<template>
  <h1>Repositorie</h1>
</template>
