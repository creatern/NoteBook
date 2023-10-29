# Java

## Java基础

[配置与简述](./Books/Java/配置与简述.md)

[基础语法](./Books/Java/基础语法.md)

[OOP 面向对象编程](./Books/Java/OOP.md)

[enum 枚举](./Books/Java/enum.md)

[Exception 异常处理](./Books/Java/Exception.md)

[Collection/Map 集合](./Books/Java/集合.md)

[Object 根父类](./Books/Java/Object.md)

[包装类](./Books/Java/包装类.md)

[String 字符串](./Books/Java/String.md)

[System 系统](./Books/Java/System.md)

[Math 数学相关](./Books/Java/Math.md)

[Comparator 比较器](./Books/Java/Comparator.md)

[Pattern 正则](./Books/Java/Pattern.md)

[日期时间API](./Books/Java/Date.md)

[format 格式化 ](./Books/Java/format.md)

## 高级特性

[泛型](./Books/Java/泛型.md)

[reflect 反射](./Books/Java/reflect.md)

[@interface Annotation 注解](./Books/Java/Annotation.md)

[Thread 多线程](./Books/Java/Thread.md)

[Lamdba 对象表达式](./Books/Java/Lambda.md)

[Stream 流](./Books/Java/Stream.md)

[IO 输入/输出](./Books/Java/IO.md)

[JDBC](./Books/Java/JDBC.md)

[Socket 网络](./Books/Java/Socket.md)

## [单元测试](./Books/Java/单元测试.md)

## [JavaWeb（Servlet）](./Books/Spring/Servlet.md)

# JVM

## [JVM概述](./Books/JVM/JVM概述.md)

## 内存

[运行时数据区 Runtime Data Areas](./Books/JVMRuntimeDataAreas.md)

[本地方法接口 JNI](./Books/JVM/JNI.md)

[对象实例化](./Books/JVM/对象实例化.md)

[直接内存](./Books/JVM/直接内存.md)

[执行引擎 ExecutionEngine](./Books/JVM/ExecutionEngine.md)

[字符串常量池 StringTable](./Books/JVM/StringTable.md)

## GC 垃圾回收

> 哪些内存需要回收？什么时候回收？如何回收？

- 垃圾：运行程序中没有任何指针指向的对象。如果不对这些垃圾进行回收，所占用的空间会一直保留到程序结束而无法被其他对象使用，甚至内存溢出。

> 早期的垃圾回收，内存泄漏问题。：一处内存区间由于程序员编码的问题而忘记被回收，就会产生内存泄漏，垃圾对象永远无法被清除，随着系统运行时间的不断增长，垃圾对象所耗内存可能持续上升，直到内存溢出并造成应用程序崩溃。 

> Java自动内存管理：黑匣子问题，实施必要的监控和调节。

- GC工作的区域：Java堆、方法区。  频繁收集Young区、较少收集Old区、基本不动Perm区/Metaspase。

### [GC算法](./Books/JVM/GC算法.md)

### 垃圾收集器

[垃圾回收器概述](./Books/JVM/垃圾回收器概述.md)

[Serial、Serial Old GC](./Books/JVM/Serial.md)

[ParNew GC、Parallel Scavenge GC、Parallel Old GC](./Books/JVM/Parallel.md)

[CMS GC](./Books/JVM/GMS.md)

[G1 GC][./Books/JVM/G1.md]

## 字节码和类的加载

[class文件](./Books/JVM/classFile.md)

[字节码指令集](./Books/JVM/字节码指令集.md)

[类的加载过程](./Books/JVM/类的加载过程.md)

[类加载器 ClassLoader](./Books/JVM/ClassLoader.md)

# Spring

[Git](./Books/Spring/Git.md)

[Maven](./Books/Spring/Maven.md)

[Docker](./Books/Spring/Docker.md)

[Spring](./Books/Spring/Spring.md)

[Spring MVC](./Books/Spring/SpringMVC.md)

[Spring Boot](./Books/Spring/SpringBoot.md)

[Spring Data](./Books/Spring/SpringData.md)

[Spring Security](./Books/Spring/SpringSecurity.md)

# Java性能优化

## JVM性能监控与调优

| 性能评价指标  | 说明                                 |
| ------------- | ------------------------------------ |
| 停顿/响应时间 | -                                    |
| 吞吐量        | 单位时间内完成的工作量               |
| 并发数        | 同一时刻，对服务器有实际交互的请求数 |
| 内存占用      | Java堆区所占内存                     |

[JDK工具](./Books/Java性能优化/JDK工具.md)

[JVM参数](./Books/Java性能优化/JVM参数.md)

### 调优工具

### [GC日志](./Books/Java性能优化/GC日志.md)

### [OOM](./Books/Java性能优化/OOM.md)

# 算法

## 数据结构

[线性结构](./Books/Arithmetic/线性结构.md)

[树](./Books/Arithmetic/树.md)

[图](./Books/Arithmetic/Graph.md)

## 算法

[算法复杂度](./Books/Arithmetic/算法复杂度.md)

[查找算法](./Books/Arithmetic/查找算法.md)

[排序算法](./Books/Arithmetic/排序算法.md)

[贪心算法](./Books/Arithmetic/贪心算法.md)

[分治策略](./Books/Arithmetic/分治策略.md)

[概率算法](./Books/Arithmetic/概率算法.md)

## UML

- UML：可视化的面向对象的统一建模语言，软件开发过程中的分析设计阶段使用、支持面向对象、独立与软件的实现。

[基本元素](./Books/Arithmetic/基本元素.md)

[关系元素](./Books/Arithmetic/关系元素.md)

[视图和图](./Books/Arithmetic/视图和图.md)

[静态视图](./Books/Arithmetic/静态视图.md)

[用例视图](./Books/Arithmetic/用例视图.md)

[状态机视图](./Books/Arithmetic/状态机视图.md)

[活动视图](./Books/Arithmetic/活动视图.md)

[物理视图](./Books/Arithmetic/物理视图.md)

[模型管理试图](./Books/Arithmetic/模型管理试图.md)

[Rational Rose](./Books/Arithmetic/Rational_Rose.md)

## 设计模式

[设计原则](./Books/Arithmetic/设计原则.md)

### 创建型

[工厂模式](./Books/Arithmetic/Foctory.md)

[单例模式](./Books/Arithmetic/SingleTon.md)

[建造者模式](./Books/Arithmetic/Builder.md)

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

## [多线程设计模式](../Books/Arithmetic/多线程设计模式.md)

# Linux

[Vim](./Books/Linux/Vim.md)

[基本服务与管理](基本服务与管理.md)

[服务器配置](./Books/Linux/服务器配置.md)

[系统功能与监测](./Books/Linux/系统功能与监测.md)

[Shell](./Books/Linux/Shell.md)

[Kernel](./Books/Linux/Kernel.md)

# [前端](./Books/前端/前端.md)

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

# [Android](./Books/Android/Android.md)

