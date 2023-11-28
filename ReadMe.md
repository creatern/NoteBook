# C/C++

# Java

## Java

### 基础卷

[配置与简述](./Books/Java/配置与简述.md)

[基础语法](./Books/Java/基础语法.md)

[OOP 面向对象编程](./Books/Java/OOP.md)

[enum 枚举](./Books/Java/enum.md)

[Exception 异常处理](./Books/Java/Exception.md)

[Collection/Map 集合](./Books/Java/集合.md)

[Object 根父类](./Books/Java/Object.md)

[包装类](./Books/Java/包装类.md)

[String 字符串](./Books/JVM/StringTable.md)

[System 系统](./Books/Java/System.md)

[Math 数学相关](./Books/Java/Math.md)

[Comparator 比较器](./Books/Java/Comparator.md)

[Pattern 正则](./Books/Java/Pattern.md)

[日期时间API](./Books/Java/Date.md)

[format 格式化 ](./Books/Java/format.md)

### 高级卷

[泛型](./Books/Java/泛型.md)

[reflect 反射](./Books/Java/reflect.md)

[@interface Annotation 注解](./Books/Java/Annotation.md)

[IO 输入/输出](./Books/Java/IO.md)

[JDBC](./Books/Java/JDBC.md)

[Socket 网络](./Books/Java/Socket.md)

#### 函数式编程

[Lamdba 对象表达式](./Books/Java/Lambda.md)

[Stream 流](./Books/Java/Stream.md)

#### 单元测试

[Junit 单元测试](./Books/Java/单元测试.md)

#### JUC 并发编程

[Thread 线程类](./Books/Java/Thread.md)

[Unsafe](./Books/Java/Unsafe.md)

[Atomic\* 原子操作](./Books/Java/Atomic.md)

[ThreadLocalRandom](./Books/Java/ThreadLocalRandom.md)

## JVM（HotSpot VM）

[JVM概述](./Books/JVM/JVM概述.md)

### 内存结构

[运行时数据区 Runtime Data Areas](./Books/JVM/RuntimeDataAreas.md)

[本地方法接口 JNI](./Books/JVM/JNI.md)

[对象实例化](./Books/JVM/对象实例化.md)

[直接内存 DirectMemory](./Books/JVM/DirectMemory.md)

[执行引擎 ExecutionEngine](./Books/JVM/ExecutionEngine.md)

[字符串常量池 StringTable](./Books/JVM/StringTable.md)

### GC 垃圾回收

#### GC算法

##### 标记阶段

- 垃圾标记阶段：对象存活判断（死亡对象，即一个对象不再被任何的存活对象引用）。

[引用计数算法 Reference Counting](./Books/JVM/ReferenceCountingAlgorithm.md)

[可达性分析算法 GC Roots](./Books/JVM/GCRoots.md)

[对象终止机制 finalization](./Books/JVM/finalization.md)

##### 清除阶段

[标记-清除算法 Mark-Sweep](./Books/JVM/MarkSweep.md)

[复制算法](./Books/JVM/ReplicationAlgorithms.md)

[标记-压缩（整理）算法  Mark-Compact](./Books/JVM/MarkCompact.md)

[分代收集算法](./Books/JVM/GenerationalCollectionAlgorithms.md)

[增量收集算法](./Books/JVM/IncrementalCollectionAlgorithms.md)

[分区算法](./Books/JVM/PartitioningAlgorithms.md)

#### 垃圾收集器

[垃圾回收器概述](./Books/JVM/垃圾回收器概述.md)

[Serial、Serial Old GC](./Books/JVM/Serial.md)

[ParNew GC、Parallel Scavenge GC、Parallel Old GC](./Books/JVM/Parallel.md)

[CMS GC](./Books/JVM/GMS.md)

[G1 GC](./Books/JVM/G1.md)

### 字节码和类的加载

[\.class 字节码文件](./Books/JVM/classFile.md)

[字节码指令集](./Books/JVM/字节码指令集.md)

[类的加载过程](./Books/JVM/类的加载过程.md)

[类加载器 ClassLoader](./Books/JVM/ClassLoader.md)

## Java性能优化

| 性能评价指标  | 说明                                 |
| ------------- | ------------------------------------ |
| 停顿/响应时间 |                                      |
| 吞吐量        | 单位时间内完成的工作量               |
| 并发数        | 同一时刻，对服务器有实际交互的请求数 |
| 内存占用      | Java堆区所占内存                     |

| 检测分析工具                               | 说明                                         |
| ------------------------------------------ | -------------------------------------------- |
| [JDK工具](./Books/Java性能优化/JDK工具.md) | JDK自带工具                                  |
| Visual VM                                  | 综合分析                                     |
| MAT（Memory Analyzer Tool）                | Java堆内存分析器，查找内存泄漏和内存消耗情况 |

OQL

[JVM参数](./Books/Java性能优化/JVM参数.md)

[GC日志](./Books/Java性能优化/GC日志.md)

[OOM](./Books/Java性能优化/OOM.md)

# Web

## 环境与构建工具

[Git](./Books/BuildTools/Git.md)

[Maven](./Books/BuildTools/Maven.md)

[Gradle](./Books/BuildTools/Gradle.md)

[Docker](./Books/BuildTools/Docker.md)

## Servlet API

[Servlet](./Books/Java/Servlet.md)

## Spring

| 基本思想                              | 说明                                                         |
| ------------------------------------- | ------------------------------------------------------------ |
| [IoC 控制反转](./Books/Spring/IOC.md) | 将创造Bean的权利交给Spring进行管理                           |
| [DI 依赖注入](./Books/Spring/DI.md)   | 某个Bean的完整创建依赖于其他Bean（或普通参数）的注入<br />注入顺序（后面的覆盖前面的）：字面量/声明 \<\-\- 属性标注 \<\-\- setXxx()标注 |
| [AOP 面向切面](./Books/Spring/AOP.md) | 横向抽取方法（属性、对象等）思想，组装成一个功能性切面       |

| MVC框架                                           | [WebMvcConfigurer 配置](./Books/Spring/WebMvcConfigurer.md) |
| ------------------------------------------------- | ----------------------------------------------------------- |
| [Controller 控制器](./Books/Spring/Controller.md) | 控制层，[@RequestMapping](./Books/Spring/RequestMapping.md) |
| [Model 模型](./Books/Spring/Model.md)             | 模型层                                                      |
| [View 视图](./Books/Spring/View.md)               | 视图层                                                      |

### Spring Boot

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

### Spring Data

| 依赖库                                               | 说明                       |
| ---------------------------------------------------- | -------------------------- |
| [JDBC](./Books/Spring/JDBC.md)                       |                            |
| [JPA](./Books/Spring/JPA.md)                         | 适用于SQL和NoSQL           |
| [Cassandra](./Books/Spring/Cassandra.md)             |                            |
| [MongoDB](./Books/Spring/MongoDB.md)                 |                            |
| [Spring Data REST](./Books/Spring/SpringDataREST.md) | 基于储存库自动生成REST API |

## [MyBatis](./Books/Spring/MyBatis.md)

## 前端网页

| W3C标准 | 网页主要由以下三个部分组成   |
| ------- | ---------------------------- |
| 结构    | [HTML](./Books/前端/HTML.md) |
| 表现    | [CSS](./Books/前端/CSS.md)   |
| 行为    | [JS](./Books/前端/JS.md)     |

[AJAX](./Books/前端/AJAX.md) 

[Vue](./Books/前端/Vue.md)

# 算法

## 数据结构

- 线性表（线性结构）：数据元素之间存在一对一的线性关系，包括数组、队列、链表、栈。
- 非线性结构：二维数组、多维数组、广义表、树、图。

### 线性结构

[数组 Array](./Books/Arithmetic/Array.md)

[链表 LinkedList](./Books/Arithmetic/LinkedList.md)

[栈 Stack](./Books/Arithmetic/Stack.md)

[队列 Queue](./Books/Arithmetic/Queue.md)

### 树 Tree

[树 Tree 概述](./Books/Arithmetic/Tree.md)

[二叉树 Binary Tree](./Books/Arithmetic/BinaryTree.md)

[森林 Forest](./Books/Arithmetic/Forest.md)

[哈夫曼树 Huffman Tree](./Books/Arithmetic/HuffmanTree.md)

[字典树（前缀树） Trie Tree](./Books/Arithmetic/TrieTree.md)

[平衡二叉树](./Books/Arithmetic/BalancedBinaryTree.md)

[二叉排序树](./Books/Arithmetic/SortedBinaryTree.md)

线索二叉树

B树

B\-tree

B\+tree

B\*tree

红黑树

### 图 Graph

[图 概述](./Books/Arithmetic/Graph.md)

[图的实现](./Books/Arithmetic/图的实现.md)

[图的遍历 DFS、BFS](./Books/Arithmetic/GraphDFSBFS.md)

[查询模式](./Books/Arithmetic/图的查询模式.md)

[有向无环图 DAG](./Books/Arithmetic/DAG.md)

## 算法

[算法复杂度](./Books/Arithmetic/算法复杂度.md)

[查找算法](./Books/Arithmetic/查找算法.md)

[排序算法](./Books/Arithmetic/排序算法.md)

[贪心算法](./Books/Arithmetic/贪心算法.md)

[分治策略](./Books/Arithmetic/分治策略.md)

[概率算法](./Books/Arithmetic/概率算法.md)

## UML

[基本元素](./Books/Arithmetic/基本元素.md)

[关系元素](./Books/Arithmetic/关系元素.md)

[视图和图](./Books/Arithmetic/视图和图.md)

[静态视图](./Books/Arithmetic/静态视图.md)

[用例视图](./Books/Arithmetic/用例视图.md)

[状态机视图](./Books/Arithmetic/状态机视图.md)

[活动视图](./Books/Arithmetic/活动视图.md)

[物理视图](./Books/Arithmetic/物理视图.md)

[模型管理视图](./Books/Arithmetic/模型管理视图.md)

[Rational Rose](./Books/Arithmetic/Rational_Rose.md)

## 设计模式

[设计原则](./Books/Arithmetic/设计原则.md)

[多线程设计模式](./Books/Arithmetic/多线程设计模式.md)

### 创建型

[工厂模式 Foctory](./Books/Arithmetic/Foctory.md)

[单例模式 SingleTon](./Books/Arithmetic/SingleTon.md)

[建造者模式 Builder](./Books/Arithmetic/Builder.md)

### 结构型

[适配器模式 Adapter](./Books/Arithmetic/Adapter.md)

[桥接模式 Bridge](./Books/Arithmetic/Bridge.md)

[装饰者模式 Decorator](./Books/Arithmetic/Decorator.md)

[组合模式 Composite](./Books/Arithmetic/Composite.md)

[外观模式 Facade](./Books/Arithmetic/Facade.md)

[享元模式 Flyweight](./Books/Arithmetic/Flyweight.md)

[代理模式 Proxy](./Books/Arithmetic/Proxy.md)

### 行为型

[模板方法模式 Template](./Books/Arithmetic/Template.md)

[命令模式 Command](./Books/Arithmetic/Command.md)

[访问者模式 Visitor](./Books/Arithmetic/Visitor.md)

[迭代器模式 Iterator](./Books/Arithmetic/Iterator.md)

[观察者模式 Observer](./Books/Arithmetic/Observer.md)

[中介者模式 Mediator](./Books/Arithmetic/Mediator.md)

[备忘录模式 Memento](./Books/Arithmetic/Memento.md)

[解释器模式 Interpreter](./Books/Arithmetic/Interpreter.md)

[状态模式 State](./Books/Arithmetic/State.md)

[策略模式 Strategy](./Books/Arithmetic/Strategy.md)

[职责链模式 Chain of Responsibility](./Books/Arithmetic/ChainOfResponsibility.md)

# Linux

[Vim](./Books/Linux/Vim.md)

[基本服务与管理](./Books/Linux/基本服务与管理.md)

[服务器配置](./Books/Linux/服务器配置.md)

[系统功能与监测](./Books/Linux/系统功能与监测.md)

[Shell](./Books/Linux/Shell.md)

[Kernel](./Books/Linux/Kernel.md)

# 数据库

## SQL

### 基本SQL

[SQL概念与事务](./Books/SQL/SQL概念与事务.md)

[权限管理](./Books/SQL/权限管理.md)

[数据类型](./Books/SQL数据类型.md)

[CRUD](./Books/SQL/CRUD.md)
[Table](./Books/SQL/Table.md)

[View](./Books/SQL/View.md)

[子查询](./Books/SQL/子查询.md)

[Join 连接](./Books/SQL/Join.md)

[组函数](./Books/SQL/Group.md)

[Sequence 序列](./Books/SQL/Sequence.md)

[Index 索引](./Books/SQL/Index.md)

[Synonym 同义词](./Books/SQL/Synonym.md)

[Cursor 游标](./Books/SQL/Cursor.md)

[子程序](./Books/SQL/ProcedureAndFunction.md)

[Trigger 触发器](./Books/SQL/Trigger.md)

### 扩展SQL

[扩展SQL基本语法](./Books/SQL/扩展SQL.md)

[组合数据类型](./Books/SQL/组合数据类型.md)

[异常处理](./Books/SQL/Exception.md)

[批量绑定](./Books/SQL/bulk.md)

[PL/SQL软件包](./Books/SQL/package.md)

[程序使用权限](./Books/SQL/程序使用权限.md)

[自治事务](./Books/SQL/自治事务.md)

[外部语言例程](./Books/SQL/外部语言例程.md)

[源码导出与加密](./Books/SQL/源码导出与加密.md)

## NoSQL

### MongoDB

[SQL to MongoDB Mapping Chart](./Books/NoSQL/MongoDB/SQL_to_MongoDB_Mapping.md)

### [Cassandra](./Books/NoSQL/Cassandra.md)

### Redis

[数据结构与编码](./Books/NoSQL/Redis/数据结构与编码.md)

# 计算机理论

## 组成原理

[组成原理概述](./Books/Computer/组成原理概述.md)

[系统总线](./Books/Computer/系统总线.md)

[数据的表示](./Books/Computer/数据的表示.md)

[存储器 Memory](./Books/Computer/Memory.md)

## 网络结构

[网络体系结构](./Books/Computer/网络体系结构.md)

[物理层 Physical Layer](./Books/Computer/PhysicalLayer.md)

[数据链路层 Data Link Layer](./Books/Computer/DataLinkLayer.md)

## 操作系统

## 数据库原理

# 数学逻辑与数学基础

高等数学

线性代数

[运筹学 Operations](./Books/Mathematics/Operations.md)

概率论与数理统计

[统计学 Statistics](./Books/Mathematics/Statistics.md)

离散数学

图论

# 其他

[信息管理系统 MIS](./Books/MIS/MIS.md)

[物流基础 Logistics](./Books/Others/Logistics.md)

[运营管理 Operations Management](./Books/Others/OperationsManagement.md)

[公司理财 Corporate Finance](./Books/Others/CorporateFinance.md)

[Android](./Books/Android/Android.md)

