# node

```shell
# 将解压后的node加入环境变量中
sudo vim /etc/profile

export NODE_HOME=/opt/node20.10.0
export PATH=$PATH:$NODE_HOME/bin

# 重新应用环境变量文件
source /etc/profile
```

```shell
# sudo npm命令找不到的解决，其余（vue等命令）也是如此
sudo ln -s /opt/node20.10.0/bin/npm /usr/bin/npm
sudo ln -s /opt/node20.10.0/bin/node /usr/bin/node
```

# npm

```shell
# npm换源
sudo npm config set registry https://registry.npm.taobao.org
```

## 搭建Vue项目

```shell
# 全局安装Vue CLI 并指定版本
sudo npm install -g @vue/cli@

# 创建项目
sudo vue create captain-vue

# 启动
npm run serve
```

