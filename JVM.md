# [JVM概述](./Books/JVM/JVM概述.md)

# 内存结构

[运行时数据区 Runtime Data Areas](./Books/JVM/RuntimeDataAreas.md)

[本地方法接口 JNI](./Books/JVM/JNI.md)

[对象实例化](./Books/JVM/对象实例化.md)

[直接内存 DirectMemory](./Books/JVM/DirectMemory.md)

[执行引擎 ExecutionEngine](./Books/JVM/ExecutionEngine.md)

[字符串常量池 StringTable](./Books/JVM/StringTable.md)

# GC 垃圾回收

## GC算法

### 标记阶段

- 垃圾标记阶段：对象存活判断（死亡对象，即一个对象不再被任何的存活对象引用）。

[引用计数算法 Reference Counting](./Books/JVM/ReferenceCountingAlgorithm.md)

[可达性分析算法 GC Roots](./Books/JVM/GCRoots.md)

[对象终止机制 finalization](./Books/JVM/finalization.md)

### 清除阶段

[标记-清除算法 Mark-Sweep](./Books/JVM/MarkSweep.md)

[复制算法](./Books/JVM/ReplicationAlgorithms.md)

[标记-压缩（整理）算法  Mark-Compact](./Books/JVM/MarkCompact.md)

[分代收集算法](./Books/JVM/GenerationalCollectionAlgorithms.md)

[增量收集算法](./Books/JVM/IncrementalCollectionAlgorithms.md)

[分区算法](./Books/JVM/PartitioningAlgorithms.md)

## 垃圾收集器

[垃圾回收器概述](./Books/JVM/垃圾回收器概述.md)

[Serial、Serial Old GC](./Books/JVM/Serial.md)

[ParNew GC、Parallel Scavenge GC、Parallel Old GC](./Books/JVM/Parallel.md)

[CMS GC](./Books/JVM/GMS.md)

[G1 GC](./Books/JVM/G1.md)

# 字节码和类的加载

[\.class 字节码文件](./Books/JVM/classFile.md)

[字节码指令集](./Books/JVM/字节码指令集.md)

[类的加载过程](./Books/JVM/类的加载过程.md)

[类加载器 ClassLoader](./Books/JVM/ClassLoader.md)