- Serial GC：复制算法、串行回收、STW。

  - Client模式：默认的新生代垃圾收集器。

- Serial Old GC：Mark-Compact、串行回收、STW。

  - Client模式：默认的老年代垃圾收集器。
  - Server模式：
    1. 与新生代的Parallel Scavenge配合。
    2. 作为老年代CMS GC的后备垃圾收集方案。

<img src="../../pictures/Snipaste_2023-06-06_11-00-17.png" width="1200"/> 

- 简单高效（单线程）：对于限定单核CPU的环境，Serial GC没有线程交互的开销，有最高的单线程收集效率。

> 可用内存不大时（几十MB`~`一两百MB），可以较短时间内完成GC（几十ms`~`一百多ms），只要不频繁发生，使用串行回收器是可以接收的。

- 指定新生代、老年代都使用串行回收器：

```
-XX:+UseSerialGC
```
