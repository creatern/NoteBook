import { fileURLToPath, URL } from "node:url";

import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import VueDevTools from "vite-plugin-vue-devtools";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue(), VueDevTools()],
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./src", import.meta.url)),
    },
  },
  server: {
    host: "127.0.0.1",
    port: 8089,
    proxy: {
      // 匹配所有以 "/api" 开头的请求路径
      "/api": {
        target: "http://127.0.0.1:8088", // 代理目标的基础路径
        changeOrigin: true, // 支持跨域 与Spring进行交互
        pathRewrite: {
          "^/api": "", // 路径重写，去除路径中的 "/api"
        },
      },
    },
  },
});
