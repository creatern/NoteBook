# 数据库

## 数据库系统概念

### 关系语言

#### [关系模型](./Books/数据库理论/关系模型.md)

#### [SQL](./Books/数据库理论/SQL.md)

#### [形式化关系查询语言](./Books/数据库理论/形式化关系查询语言.md)

### 数据库设计与应用程序开发

#### [E-R模型](./Books/数据库理论/ER模型.md)

#### [关系数据库设计](./Books/数据库理论/关系数据库设计.md)

#### [DBAS开发方法](./Books/数据库理论/DBAS.md)

### 数据管理实现技术

#### [存储管理](./Books/数据库理论/存储管理.md)

#### [索引](./Books/数据库理论/索引.md)

#### [查询处理和查询优化](./Books/数据库理论/查询处理和查询优化.md)

## 数据挖掘导论 Data Mining

### [数据挖掘概述](./Books/数据库理论/数据挖掘概述.md)

### [数据与探索数据](./Books/数据库理论/数据与探索数据.md)

### [分类：基本概念、决策树与模型评估](./Books/数据库理论/分类基本概念.md)

### [分类：其他技术](./Books/数据库理论/分类其他技术.md)

## Oracle

[Oracle参考文档阅读](https://docs.oracle.com/en/database/oracle/oracle-database/19/cncpt/introduction-to-oracle-database.html#GUID-C756D70F-75D4-4234-BEB5-CB05A4742128)

### 单实例篇

#### [Oracle数据库简介](./Books/Oracle/Oracle数据库简介.md)

#### [单实例安装](./Books/Oracle/单实例安装.md)

## MySQL

### 基础篇

#### [安装与配置（单实例）](./Books/MySQL/安装与配置.md)

#### [MySQL Shell](./Books/MySQL/Shell.md)

#### [CRUD 增删改查](./Books/MySQL/CRUD.md)

#### [数据类型](./Books/MySQL/DataType.md)

#### [Table](./Books/MySQL/table.md)

#### [内置函数 Function](./Books/MySQL/内置函数.md)

### 进阶篇

#### [Database 数据库](./Books/MySQL/database.md)

#### [User 权限](./Books/MySQL/user.md)

#### [Character 字符集](./Books/MySQL/character.md)

## MongoDB

## Cassandra

## Redis

### [数据结构与编码](./Books/NoSQL/Redis/数据结构与编码.md)

# C

## 基础篇

### [基础配置与语法](./Books/C/基础配置与语法.md)

# Java

## 基础篇

### [基础配置与语法](./Books/Java/基础配置与语法.md)

### [OOP 面向对象编程](./Books/Java/OOP.md)

### [String 字符串](./Books/JVM/StringTable.md)

### [enum 枚举类](./Books/Java/enum.md)

### [Collection/Map 集合](./Books/Java/集合.md)

### [Exception 异常](./Books/Java/Exception.md)

### [常用工具类](./Books/Java/常用工具类.md)

### [System 系统](./Books/Java/System.md)

### [日期时间与格式化](./Books/Java/日期时间与格式化.md)

## 动态篇

### [泛型](./Books/Java/泛型.md)

### [反射](./Books/Java/reflect.md)

### [注解](./Books/Java/Annotation.md)

## [FP篇](./Books/Java/FP.md)

### [Lambda 对象表达式](./Books/Java/Lambda.md)

### [Stream 流式操作](./Books/Java/Stream.md)

## I/O篇

### [IO](./Books/Java/IO.md)

### [NIO](./Books/Java/NIO.md)

## 并发篇

### [Thread](./Books/Java/Thread.md)

### [Unsafe](./Books/Java/Unsafe.md)

### [Atomic* 原子性](./Books/Java/Atomic.md)

### [ThreadLocalRandom](./Books/Java/ThreadLocalRandom.md)

### [多线程设计模式](./Books/Algorithm/多线程设计模式.md)

## JDBC篇

[JDBC](./Books/Java/JDBC.md)

## 网络篇

[网络](./Books/Java/Socket.md)

## 脚本篇

[java.script 脚本](./Books/Java/script.md)

## [Junit](./Books/Java/单元测试.md)

# JavaWeb

## 工具篇

### [Git 版本控制](./Books/BuildTools/Git.md)

### [Maven 依赖管理](./Books/BuildTools/Maven.md)

### [Node.js JS环境](./Books/BuildTools/Node.md)

## 后端篇



## 前端篇

### [HTML]((./Books/前端/HTML.md))

### [CSS](./Books/前端/CSS.md)

### [JavaScript](./Books/前端/JavaScript.md)

### [Vue](./Books/前端/Vue.md)

# [JVM](./Books/JVM/JVM概述.md)

## 内存结构

### [运行时数据区](./Books/JVM/RuntimeDataAreas.md)

### [本地方法接口](./Books/JVM/JNI.md)

### [对象实例化](./Books/JVM/对象实例化.md)

### [直接内存](./Books/JVM/DirectMemory.md)

### [执行引擎](./Books/JVM/ExecutionEngine.md)

### [字符串常量池](./Books/JVM/StringTable.md)

## GC

### GC算法

#### 标记阶段

##### [引用计数算法](./Books/JVM/ReferenceCountingAlgorithm.md)

##### [可达性分析算法](./Books/JVM/GCRoots.md)

##### [对象终止机制](./Books/JVM/finalization.md)

#### 清除阶段

##### [Mark-Sweep](./Books/JVM/MarkSweep.md)

##### [复制算法](./Books/JVM/ReplicationAlgorithms.md)

##### [Mark-Compact](./Books/JVM/MarkCompact.md)

##### [分代收集算法](./Books/JVM/GenerationalCollectionAlgorithms.md)

##### [增量收集算法](./Books/JVM/IncrementalCollectionAlgorithms.md)

##### [分区算法](./Books/JVM/PartitioningAlgorithms.md)

### [垃圾收集器](./Books/JVM/垃圾回收器概述.md)

#### [Serial、Serial Old GC](./Books/JVM/Serial.md)

#### [ParNew GC、Parallel Scavenge GC、Parallel Old GC](./Books/JVM/Parallel.md)

#### [GMS](./Books/JVM/GMS.md)

#### [G1 GC](./Books/JVM/G1.md)

## 字节码和类的加载

### [.class 字节码文件](./Books/JVM/classFile.md)

### [字节码指令集](./Books/JVM/字节码指令集.md)

### [类的加载过程](./Books/JVM/类的加载过程.md)

### [ClassLoader](./Books/JVM/ClassLoader.md)

## 性能优化

### [评价指标](./Books/Java性能优化/评价指标.md)

### 检测分析工具

#### [JDK工具](./Books/Java性能优化/JDK工具.md)

#### [Visual VM](./Books/Java性能优化/VisualVM.md)

#### [MAT](./Books/Java性能优化/MAT.md)

#### [OQL](./Books/Java性能优化/OQL.md)

#### [JVM参数](./Books/Java性能优化/JVM参数.md)

### [GC日志](./Books/Java性能优化/GC日志.md)

### [OOM](./Books/Java性能优化/OOM.md)

# Linux

## 基础篇

### [文本编辑器](./Books/Linux/文本编辑器.md)

### [应用安装](./Books/Linux/应用安装.md)

### [用户管理](./Books/Linux/用户管理.md)

### [文件管理](./Books/Linux/文件管理.md)

### [文本处理](./Books/Linux/文本处理.md)

### [界面与CLI](./Books/Linux/界面与CLI.md)

## 系统篇

### [信号与进程](./Books/Linux/信号与进程.md)

### [日志与计划任务](./Books/Linux/日志与计划任务.md)

### [systemctl 服务单元控制](./Books/Linux/服务单元控制.md)

### [防火墙](./Books/Linux/防火墙.md)

### [网络管理](./Books/Linux/网络管理.md)

### [文件系统](./Books/Linux/FileSystem.md)

### [硬件信息](./Books/Linux/硬件信息.md)

### [负载监测](./Books/Linux/负载监测.md)

## 服务器篇

### 文件服务器

#### [rsync](./Books/Linux/rsync.md)

#### [FTP](./Books/Linux/FTP.md)

#### [Samba](./Books/Linux/Samba.md)

#### [NFS](./Books/Linux/NFS.md)

### 网络服务器

#### [DNS](./Books/Linux/DNS.md)

#### [DHCP](./Books/Linux/DHCP.md)

#### [NAT](./Books/Linux/NAT.md)

#### [OpenVPN](./Books/Linux/OpenVPN.md)

### 服务部署

#### [SSH](./Books/Linux/SSH.md)

#### [Docker](./Books/Linux/Docker.md)

#### [Nginx](./Books/Linux/Nginx.md)

### 虚拟化

#### [KVM](./Books/Linux/KVM.md)

## Shell篇

### [Shell概述](./Books/Linux/Shell.md)

### [环境变量](./Books/Linux/环境变量.md)

### [Shell脚本](./Books/Linux/Shell脚本.md)

## Kernel篇

## [Rasberry Pi](./Books/Linux/RasberryPi.md)

# 数据结构

## [线性表](./Books/Algorithm/线性表.md)

### [数组](./Books/Algorithm/数组.md)

### [栈](./Books/Algorithm/栈.md)

### [队列](./Books/Algorithm/队列.md)

## 树

### 树

#### [树的基本性质](./Books/Algorithm/树的基本性质.md)

#### [森林](./Books/Algorithm/森林.md)

### 二叉树

#### [二叉树的性质 Binary Tree](./Books/Algorithm/二叉树.md)

#### [哈夫曼树 Huffman Tree](./Books/Algorithm/哈夫曼树.md)

#### [线索二叉树]

#### [二叉排序/查找树 BST](./Books/Algorithm/二叉排序树.md)

### 堆

#### [堆的性质](./Books/Algorithm/堆的性质.md)

### [字典树/前缀树 Trie-tree](./Books/Algorithm/字典树.md)

### [B树 B-tree](./Books/Algorithm/B树.md)

## 图

### [图的基本性质](./Books/Algorithm/图的基本性质.md)

### [图的遍历方式](./Books/Algorithm/图的遍历方式.md)

### [图的查询模式](./Books/Algorithm/图的查询模式.md)

# 算法

## 基础篇

### [算法分析](./Books/Algorithm/算法分析.md)

### [排序与查找](./Books/Algorithm/排序与查找.md)

### [贪心策略](./Books/Algorithm/贪心策略.md)

### [分治策略](./Books/Algorithm/分治策略.md)

### [概率算法](./Books/Algorithm/概率算法.md)

### [Dijkstra](./Books/Algorithm/Dijkstra.md)

# 计算机

## 组成原理

### [概述与了解](./Books/Computer/组成原理概述.md)

### [系统总线](./Books/Computer/系统总线.md)

### [数据的表示与运算](./Books/Computer/数据的表示与运算.md)

### [存储器 Memory](./Books/Computer/Memory.md)

### [指令系统](./Books/Computer/指令系统.md)

## 网络

### [网络体系结构](./Books/Computer/网络体系结构.md)

### [物理层 Physical Layer](./Books/Computer/PhysicalLayer.md)

### [数据链路层 Data Link Layer](./Books/Computer/DataLinkLayer.md)

### [网络层 Network Layer](./Books/Computer/NetworkLayer.md)

### [传输层 Transport Layer](./Books/Computer/TransportLayer.md)

### [应用层 Application Layer](./Books/Computer/ApplicationLayer.md)

## 操作系统

[计算机系统概述](./Books/Computer/计算机系统概述.md)

### [操作系统概述](./Books/Computer/操作系统概述.md)

# 软件设计

## UML

### [基本元素](./Books/Algorithm/基本元素.md)

### [关系元素](./Books/Algorithm/关系元素.md)

### [视图和图](./Books/Algorithm/视图和图.md)

### [静态视图](./Books/Algorithm/静态视图.md)

### [用例视图](./Books/Algorithm/用例视图.md)

### [状态机视图](./Books/Algorithm/状态机视图.md)

### [活动视图](./Books/Algorithm/活动视图.md)

### [物理视图](./Books/Algorithm/物理视图.md)

### [模型管理视图](./Books/Algorithm/模型管理视图.md)

### [Rational Rose](./Books/Algorithm/Rational_Rose.md)

## GOF

### [设计原则](./Books/Algorithm/设计原则.md)

### 创建型

#### [工厂模式 Factory](./Books/Algorithm/Factory.md)

#### [单例模式 SingleTon](./Books/Algorithm/SingleTon.md)

#### [建造者模式 Builder](./Books/Algorithm/Builder.md)

### 结构型

#### [适配器模式 Adapter](./Books/Algorithm/Adapter.md)

#### [桥接模式](./Books/Algorithm/Bridge.md)

#### [装饰者模式 Decorator](./Books/Algorithm/Decorator.md)

#### [组合模式 Composite](./Books/Algorithm/Composite.md)

#### [外观模式 Facade](./Books/Algorithm/Facade.md)

#### [享元模式 Flyweight](./Books/Algorithm/Flyweight.md)

#### [代理模式 Proxy](./Books/Algorithm/Proxy.md)

### 行为型

#### [模板方法模式 Template](./Books/Algorithm/Template.md)

#### [命令模式 Command](./Books/Algorithm/Command.md)

#### [访问者模式 Visitor](./Books/Algorithm/Visitor.md)

#### [迭代器模式 Iterator](./Books/Algorithm/Iterator.md)

#### [观察者模式 Observer](./Books/Algorithm/Observer.md)

#### [中介者模式 Mediator](./Books/Algorithm/Mediator.md)

#### [备忘录模式 Memento](./Books/Algorithm/Memento.md)

#### [解释器模式 Interpreter](./Books/Algorithm/Interpreter.md)

#### [状态模式 State](./Books/Algorithm/State.md)

#### [策略模式 Strategy](./Books/Algorithm/Strategy.md)

#### [职责链模式 Chain of Responsibility](./Books/Algorithm/ChainOfResponsibility.md)

# 数学

## 高等数学

## 线性代数

## [运筹学 Operations](./Books/Mathematics/Operations.md)

## 概率论与数理统计

## [统计学 Statistics](./Books/Mathematics/Statistics.md)

## 离散数学

## 图论

# 管理

## [信息管理系统 MIS](./Books/MIS/MIS.md)

## [物流基础 Logistics](./Books/Others/Logistics.md)

## [运营管理 Operations Management](./Books/Others/OperationsManagement.md)

## [公司理财 Corporate Finance](./Books/Others/CorporateFinance.md)

## [IT项目管理](./Books/MIS/IT项目管理.md)

# Android

## [Gradle 依赖配置](./Books/BuildTools/Gradle.md)

## [基础篇](./Books/Android/Android.md)

## Kotlin
