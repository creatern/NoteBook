# ParNew GC

- ParNew GC：年轻代的多线程垃圾收集器。并行回收、复制算法、STW。

<img src="../../pictures/Snipaste_2023-06-06_12-02-51.png" width="1200"/>

```
-XX:+UseParNewGC 指定年轻代使用ParNewGC，不影响老年代
-XX:ParallelGCThreads 限制线程数量，默认开启和CPU数据相同的线程数
```

# Parallel Scavenge GC、Parallel Old GC

> JDK8默认的GC：Pallele Scavenge GC、Pallel Old GC。

- Parallel Scavenge GC：年轻代的多线程垃圾收集器。复制算法、并行回收、STW。
- Parallel Scavenge GC与ParNew GC区别：
  - Parallel Scavenge GC目标是达到一个可控制的吞吐量（吞吐量优先）。
  - Parallel Scavenge GC的自适应调节策略。

> 高吞吐量：适合后台运算而不需要太多交互的任务。（服务器环境）
>
> - 执行批量处理、订单处理、工资支付、科学计算等。

- Parallel Old GC：老年代的多线程垃圾收集器。Mark-Sweap、并行回收、STW。

<img src="../../pictures/Snipaste_2023-06-06_12-21-39.png" width="1200"/>

- 开启Parallel GC：默认开启。相互激活：以下参数开启一个，另一个也会被开启

```
-XX:+UseParallelGC 年轻代使用Parallel Scavenge GC
-XX:+UseParallelOldGC 老年代使用Parallel Old GC
```

- 设置年轻代Parallel Scavenge GC的线程数，一般最好与CPU数相等。
  - 当CPU数量小于8，PrallelGCThreads值 = CPU\_COUNT。
  - 当CPU数量大于8，PrallelGCThreads值 = 3 \+ \[5 \* CPU\_COUNT / 8\]。

```
-XX:ParallelGCThreads
```

- 最大STW时间（ms）：垃圾收集器工作时会调整Java堆大小、其他参数，谨慎使用。

```
-XX:MaxGCPauseMillis
```

- GC时间占总时间的比例： 1 / \(N \+ 1\)。
  - 取值范围：（0，100）。默认99，即GC时间不超过1%。
  - \-XX:MaxGCPauseMillis值越大，\-XX:GCTimeRatio越容易超过设定的比例。

```
-XX:GCTimeRatio
```

- 设置Parallel的自适应调节机制。
  - JVM自动调整年轻代大小、Eden和Survivor的比例、晋升老年代的对象年龄阈值等参数，从而达到在堆大小、吞吐量、停顿时间的平衡点。

```
-XX:+UseAdaptiveSizePolicy
```
