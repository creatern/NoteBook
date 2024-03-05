# 数据库

## 数据库系统概念

### [关系语言](./Books/数据库理论/关系语言.md)

### [数据库设计与应用程序开发](./Books/数据库理论/数据库设计与应用程序开发.md)

### [数据管理实现技术](./Books/数据库理论/数据管理实现技术.md)

## Oracle

### 基础篇

#### [概述与事务](./Books/SQL/SQL概念与事务.md)

#### [权限管理](./Books/Oracle/权限管理.md)

#### [数据类型](./Books/Oracle/SQL数据类型.md)

#### [CRUD](./Books/Oracle/CRUD.md)

#### [Table](./Books/Oracle/Table.md)

#### [View](./Books/Oracle/View.md)

#### [子查询](./Books/Oracle/子查询.md)

#### [join 连接](./Books/Oracle/Join.md)

#### [组函数](./Books/Oracle/Group.md)

#### [Sequence 序列](./Books/Oracle/Sequence.md)

#### [index 索引](./Books/Oracle/Index.md)

### PL/SQL

#### [synonym 同义词](./Books/Oracle/Synonym.md)

#### [cursor 游标](./Books/Oracle/Cursor.md)

#### [procedure、function 子程序](./Books/Oracle/ProcedureAndFunction.md)

#### [trigger 触发器](./Books/Oracle/Trigger.md)

#### [Exception 异常处理](./Books/Oracle/Exception.md)

#### [批量绑定](./Books/Oracle/bulk.md)

#### [PL/SQL软件包](./Books/Oracle/package.md)

#### [程序使用权限](./Books/Oracle/程序使用权限.md)

#### [自治事务](./Books/Oracle/自治事务.md)

#### [外部语言例程](./Books/Oracle/外部语言例程.md)

#### [源码导出与加密](./Books/Oracle/源码导出与加密.md)

## MySQL

### 基础篇

#### [安装与配置（单实例）](./Books/MySQL/安装与配置.md)

#### [MySQL Shell](./Books/MySQL/Shell.md)

#### [CRUD 增删改查](./Books/MySQL/CRUD.md)

#### [Table](./Books/MySQL/table.md)

#### [函数和运算符](./Books/MySQL/函数和运算符.md)

### 进阶篇

#### [Database 数据库](./Books/MySQL/database.md)

#### [User 权限](./Books/MySQL/user.md)

#### [字符集](./Books/MySQL/character.md)

#### [数据类型](./Books/MySQL/DataType.md)

## MongoDB

## Cassandra

## Redis

### [数据结构与编码](./Books/NoSQL/Redis/数据结构与编码.md)

## [数据挖掘导论 Data Mining](./Books/数据库理论/数据挖掘导论.md)

# Java

## 基础卷

### [配置与简述](./Books/Java/配置与简述.md)

### [基础语法](./Books/Java/基础语法.md)

### [OOP 面向对象编程](./Books/Java/OOP.md)

### [enum 枚举](./Books/Java/enum.md)

### [Exception 异常](./Books/Java/Exception.md)

### [Collection/Map 集合](./Books/Java/集合.md)

### [Object 根对象](./Books/Java/Object.md)

### [包装类](./Books/Java/包装类.md)

### [String 字符串](./Books/JVM/StringTable.md)

### [System 系统](./Books/Java/System.md)

### [Math 数学](./Books/Java/Math.md)

### [Comparator 比较器](./Books/Java/Comparator.md)


### [Pattern 正则](./Books/Java/Pattern.md)


### [时间日期API](./Books/Java/Date.md)


### [格式化/国际化](./Books/Java/format.md)


## 进阶卷

### [泛型](./Books/Java/泛型.md)

### [反射](./Books/Java/reflect.md)

### [注解](./Books/Java/Annotation.md)

### [IO](./Books/Java/IO.md)


### [JDBC](./Books/Java/JDBC.md)


### [网络](./Books/Java/Socket.md)


## [FP 函数式编程](./Books/Java/FP.md)

### [Lamdba 对象表达式](./Books/Java/Lambda.md)

### [Stream](./Books/Java/Stream.md)

## [Junit](./Books/Java/单元测试.md")

## JUC 并发编程

### [Thread](./Books/Java/Thread.md)


### [Unsafe](./Books/Java/Unsafe.md)

### [Atomic* 原子性](./Books/Java/Atomic.md)

### [ThreadLocalRandom](./Books/Java/ThreadLocalRandom.md)

### [多线程设计模式](./Books/Algorithm/多线程设计模式.md)

# JavaWeb

## 工具篇

### [Git 版本控制](./Books/BuildTools/Git.md)

### [Maven 依赖管理](./Books/BuildTools/Maven.md)

### [Node.js JS环境](./Books/BuildTools/Node.md)

## 后端篇

### [Servlet API](./Books/Java/Servlet.md)

### Spring

#### Spring 基本

| 基本思想                              | 说明                                                         |
| ------------------------------------- | ------------------------------------------------------------ |
| [IoC 控制反转](./Books/Spring/IOC.md) | 将创造Bean的权利交给Spring进行管理                           |
| [DI 依赖注入](./Books/Spring/DI.md)   | 某个Bean的完整创建依赖于其他Bean（或普通参数）的注入<br />注入顺序（后面的覆盖前面的）：字面量/声明 \<\-\- 属性标注 \<\-\- setXxx()标注 |
| [AOP 面向切面](./Books/Spring/AOP.md) | 横向抽取方法（属性、对象等）思想，组装成一个功能性切面       |

[MVC框架](./Books/Spring/MVC.md)

#### Spring Boot

| Initializr结构                                               | 说明             |
| ------------------------------------------------------------ | ---------------- |
| [@SpringBootApplication](./Books/Spring/SpringBootApplication.md) | SpringBoot启动类 |
| /static                                                      | 静态资源         |
| /templates                                                   | 模板文件         |
| application.properties<br />application.yml                  | 配置文件         |

- Starter依赖管理（spring\-boot\-starter）：Spring对依赖包的集中描述，本身不包含库代码，而是传递性地拉取其他库。
- [Spring DevTools](./Books/Spring/DevTools.md)：Spring开发环境工具，应用部署后DevTools禁用自身。
- [配置属性](./Books/Spring/properties.md)：Spring从各个属性源获取数据并注入到各个Bean。

| 依赖库                                     | 功能                                                         |
| ------------------------------------------ | ------------------------------------------------------------ |
| [Lombok](./Books/Spring/Lombok.md)         | 编译期自动生成类的方法（@Data），生成jar、war时自动剔除Lombok |
| [视图模板库](./Books/Spring/View.md)       | [Thymeleaf](./Books/Spring/Thymeleaf.md)                     |
| [validation](./Books/Spring/validation.md) | 校验（JSR-303）                                              |

> 模板缓存：模板默认只有第一次使用时解析，防止每次请求时多余的模板解析（对生产友好、不利于开发）。Spring Boot Devtools默认禁用模板缓存（应用部署后DevTools禁用自身）：spring.thymeleaf.cache。
>

#### Spring Data

| 依赖库                                               | 说明                       |
| ---------------------------------------------------- | -------------------------- |
| [JDBC](./Books/Spring/JDBC.md)                       |                            |
| [JPA](./Books/Spring/JPA.md)                         | 适用于SQL和NoSQL           |
| [Cassandra](./Books/Spring/Cassandra.md)             |                            |
| [MongoDB](./Books/Spring/MongoDB.md)                 |                            |
| [Spring Data REST](./Books/Spring/SpringDataREST.md) | 基于储存库自动生成REST API |

### [MyBatis](./Books/Spring/MyBatis.md)

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

### [Vim](./Books/Linux/Vim.md)

### [应用安装](./Books/Linux/应用安装.md)

### [用户管理](./Books/Linux/用户管理.md)

### [文件管理](./Books/Linux/文件管理.md)

### [界面与CLI](./Books/Linux/界面与连接.md)

## 系统篇

### [进程管理](./Books/Linux/进程管理.md)

### [日志与计划任务](./Books/Linux/日志与计划任务.md)

### [systemctl 服务单元控制](./Books/Linux/服务单元控制.md)

### [防火墙](./Books/Linux/防火墙.md)

### [网络管理](./Books/Linux/网络管理.md)

### [文件系统](./Books/Linux/FileSystem.md)

### [磁盘管理](./Books/Linux/磁盘管理.md)

### [硬件信息](./Books/Linux/硬件信息.md)

### [CPU](./Books/Linux/CPU.md)

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

## Shell篇

### [Shell概述](./Books/Linux/Shell.md)

### [环境变量](./Books/Linux/环境变量.md)

### [文本处理](./Books/Linux/文本处理.md)

### [Shell脚本](./Books/Linux/Shell脚本.md)

## Kernel篇

## [Rasberry Pi](./Books/Linux/RasberryPi.md)

# 算法

## 数据结构

### [线性表](./Books/Algorithm/线性表.md)

#### [数组](./Books/Algorithm/数组.md)

#### [栈](./Books/Algorithm/栈.md)

#### [队列](./Books/Algorithm/队列.md)

### [树](./Books/Algorithm/树.md)

### 图

#### [图的基本性质](./Books/Algorithm/图的基本性质.md)

#### [图的遍历方式](./Books/Algorithm/图的遍历方式.md)

#### [图的查询模式](./Books/Algorithm/图的查询模式.md)

## 基础篇

### [算法分析](./Books/Algorithm/算法分析.md)

### [排序与查找](./Books/Algorithm/排序与查找.md)

### [贪心算法](./Books/Algorithm/贪心算法.md)

### [分治算法](./Books/Algorithm/分治算法.md)

### [概率算法](./Books/Algorithm/概率算法.md)

### [Dijkstra](./Books/Algorithm/Dijkstra.md)

# 计算机

## 组成原理

### [概述与了解](./Books/Computer/组成原理概述.md)

### [系统总线](./Books/Computer/系统总线.md)

### [数据的表示](./Books/Computer/数据的表示.md)

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

### [计算机系统概述](./Books/Computer/计算机系统概述.md)

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

####  [工厂模式 Factory](./Books/Algorithm/Factory.md)

####  [单例模式 SingleTon](./Books/Algorithm/SingleTon.md)

####  [建造者模式 Builder](./Books/Algorithm/Builder.md)

### 结构型

####  [适配器模式 Adapter](./Books/Algorithm/Adapter.md)

####  [桥接模式](./Books/Algorithm/Bridge.md)

####  [装饰者模式 Decorator](./Books/Algorithm/Decorator.md)

####  [组合模式 Composite](./Books/Algorithm/Composite.md)

####  [外观模式 Facade](./Books/Algorithm/Facade.md)

####  [享元模式 Flyweight](./Books/Algorithm/Flyweight.md)

####  [代理模式 Proxy](./Books/Algorithm/Proxy.md)

### 行为型

####  [模板方法模式 Template](./Books/Algorithm/Template.md)

####  [命令模式 Command](./Books/Algorithm/Command.md)

####  [访问者模式 Visitor](./Books/Algorithm/Visitor.md)

####  [迭代器模式 Iterator](./Books/Algorithm/Iterator.md)

####  [观察者模式 Observer](./Books/Algorithm/Observer.md)

####  [中介者模式 Mediator](./Books/Algorithm/Mediator.md)

####  [备忘录模式 Memento](./Books/Algorithm/Memento.md)

####  [解释器模式 Interpreter](./Books/Algorithm/Interpreter.md)

####  [状态模式 State](./Books/Algorithm/State.md)

####  [策略模式 Strategy](./Books/Algorithm/Strategy.md)

####  [职责链模式 Chain of Responsibility](./Books/Algorithm/ChainOfResponsibility.md)

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

# C/C++
