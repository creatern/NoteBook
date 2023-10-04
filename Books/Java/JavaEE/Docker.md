# 概述

- 容器技术只隔离应用程序的运行时环境但容器之间可以共享同一个操作系统（程序的运行只和容器有关，即屏蔽环境差异）。

- docker将程序以及程序所有的依赖都打包到docker container。

<img src="../../../pictures/Docker-Docker_Container_pre.drawio.svg" width="500"> 

| 概念       | 说明                        |
| ---------- | --------------------------- |
| Dockerfile | 自动化脚本（创建Image）     |
| Image      | 镜像（创建Container的模板） |
| Container  |                             |

| 指令         | 说明                                                         |
| ------------ | ------------------------------------------------------------ |
| docker build | <img src="../../../pictures/20231003225611.png" width="500"/> |
| docker run   | <img src="../../../pictures/20231003225612.png"  width="500"/> |
| docker pull  | <img src="../../../pictures/20231003230540.png" width="500"/> |

<img src="../../../pictures/Docker-Volume.drawio.svg" width="500"/> 

docker-compose.yml

