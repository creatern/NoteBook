# 基础卷

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

# 高级卷

[泛型](./Books/Java/泛型.md)

[reflect 反射](./Books/Java/reflect.md)

[@interface Annotation 注解](./Books/Java/Annotation.md)

[IO 输入/输出](./Books/Java/IO.md)

[JDBC](./Books/Java/JDBC.md)

[Socket 网络](./Books/Java/Socket.md)

## 函数式编程

[Lamdba 对象表达式](./Books/Java/Lambda.md)

[Stream 流](./Books/Java/Stream.md)

## 单元测试

[Junit 单元测试](./Books/Java/单元测试.md)

## JUC 并发编程

[Thread 线程类](./Books/Java/Thread.md)

[Unsafe](./Books/Java/Unsafe.md)

[Atomic\* 原子操作](./Books/Java/Atomic.md)

[ThreadLocalRandom](./Books/Java/ThreadLocalRandom.md)

# J2EE

## 环境与构建工具

[Git](./Books/BuildTools/Git.md)

[Maven](./Books/BuildTools/Maven.md)

[Gradle](./Books/BuildTools/Gradle.md)

[Docker](./Books/BuildTools/Docker.md)

## [Spring](./Spring.md)

## 前端网页

[HTML](./Books/前端/HTML.md)

[Vue](./Books/前端/Vue.md)

# JVM

## [JVM概述](./Books/JVM/JVM概述.md)

## 内存结构

[运行时数据区 Runtime Data Areas](./Books/JVM/RuntimeDataAreas.md)

[本地方法接口 JNI](./Books/JVM/JNI.md)

[对象实例化](./Books/JVM/对象实例化.md)

[直接内存 DirectMemory](./Books/JVM/DirectMemory.md)

[执行引擎 ExecutionEngine](./Books/JVM/ExecutionEngine.md)

[字符串常量池 StringTable](./Books/JVM/StringTable.md)

## GC 垃圾回收

### GC算法

#### 标记阶段

- 垃圾标记阶段：对象存活判断（死亡对象，即一个对象不再被任何的存活对象引用）。

[引用计数算法 Reference Counting](./Books/JVM/ReferenceCountingAlgorithm.md)

[可达性分析算法 GC Roots](./Books/JVM/GCRoots.md)

[对象终止机制 finalization](./Books/JVM/finalization.md)

#### 清除阶段

[标记-清除算法 Mark-Sweep](./Books/JVM/MarkSweep.md)

[复制算法](./Books/JVM/ReplicationAlgorithms.md)

[标记-压缩（整理）算法  Mark-Compact](./Books/JVM/MarkCompact.md)

[分代收集算法](./Books/JVM/GenerationalCollectionAlgorithms.md)

[增量收集算法](./Books/JVM/IncrementalCollectionAlgorithms.md)

[分区算法](./Books/JVM/PartitioningAlgorithms.md)

### 垃圾收集器

[垃圾回收器概述](./Books/JVM/垃圾回收器概述.md)

[Serial、Serial Old GC](./Books/JVM/Serial.md)

[ParNew GC、Parallel Scavenge GC、Parallel Old GC](./Books/JVM/Parallel.md)

[CMS GC](./Books/JVM/GMS.md)

[G1 GC](./Books/JVM/G1.md)

## 字节码和类的加载

[\.class 字节码文件](./Books/JVM/classFile.md)

[字节码指令集](./Books/JVM/字节码指令集.md)

[类的加载过程](./Books/JVM/类的加载过程.md)

[类加载器 ClassLoader](./Books/JVM/ClassLoader.md)

# Java性能优化

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