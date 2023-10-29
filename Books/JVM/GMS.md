- CMS GC（Concurrent-Mark-Sweap GC）：并发收集器，垃圾收集线程和用户线程同时工作。Mark-Sweep，STW。关注低延迟。

<img src="../../pictures/Snipaste_2023-06-07_19-01-26.png" width="1200"/>

1. 初始标记阶段（Initial-Mark）：短暂的STW、仅仅只是标记GC Roots的直接关联对象。
2. 并发标记阶段（Concurrent-Mark）：从GC Roots的直接关联对象开始遍历整个对象图的过程，耗时长、但不需要停顿用户线程。
3. 重新标记阶段（Remark）：修正并发标记阶段，因用户线程继续运行而导致标记产生变动的那一部分对象的标记记录。
4. 并发清除阶段（Cocurrent-Sweep）：清理标记阶段判断已经死亡的对象。
   - 由于并发：CMS不能移动存活的对象，只能清除已经死亡的对象。故不能使用Mark-Compact。

- 由于部分阶段并发而没有STW，CMS回收过程中需要确保用户线程有足够的可用内存：CMS不能等到老年代几乎被填满时才回收，而是当堆内存使用率达到某一阈值时，便开始回收。
  - 如果CMS运行期间预留的内存无法满足需要，则“Concurrent Mode Failure”，启动后备方案：临时启用Serial Old GC来重新进行老年代的垃圾收集。

1. 内存碎片。
2. 对CPU资源敏感：占用一部分线程，吞吐量降低。
3. 无法处理浮动垃圾：“Concurrent Mode Failure”失败而导致另一次Full GC。并发标记阶段如果产生新的垃圾对象，CMS将无法对这些垃圾对象进行标记，最终导致这些新产生的垃圾对象没有被及时回收。

| 参数                               | 说明                                                         |
| ---------------------------------- | ------------------------------------------------------------ |
| -XX:+UseConcMarkSweepGC            | 指定使用CMS收集器，开启该参数后，自动打开-XX:+UseParNewGC。  |
| -XX:CMSInitiatingOccupanyFraction  | 设置堆内存使用率的阈值，一旦达到该阈值，则开始回收。<br />如果内存增长缓慢，则设置较大的阈值，降低Full GC的执行次数。反之，则设置较小的，避免触发Serial Old GC。 |
| -XX:+UseCMSCompactAtFullCollection | 指定执行Full GC之后对内存空间进行压缩整理                    |
| -XX:CMSFullGCsBeforeCompaction     | 设置执行多少次Full GC之后对内存空间进行整理                  |
| -XX:ParallelCMSThreads             | 设置CMS的线程数量，默认启动的线程数：(ParallelGCThreads + 3 ) /4。<br />ParallelGCThreads：年轻代并行收集器的线程数。-XX:ParallelGCThreads |

> 阈值：老年代的空间使用率（%）
>
> - JDK5及之前：68
> - JDK6及之后：92
