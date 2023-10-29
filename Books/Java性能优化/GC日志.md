| 参数                   | 说明                                                         |
| ---------------------- | ------------------------------------------------------------ |
| -XX:+PrintGC           | 输出GC日志<br />-verbose:gc                                  |
| -XX:+PrintGCDetails    | 输出GC详细日志                                               |
| -XX+PrintGCTimeStamps  | 输出GC时间戳（基准时间）                                     |
| -XX:+PrintGCDateStamps | 输出GC时间戳（日期：2013-05-04T21:53:59.234+0800）           |
| -XX:+PrintheapAtGC     | 输出GC前后的堆信息                                           |
| -Xloggc:路径           | 日志文件的输出路径<br />GCeasy、GCviewer等工具对导出的日志文件分析 |

| 名词                                                 | 说明                                                         |
| ---------------------------------------------------- | ------------------------------------------------------------ |
| [GC]<br />[Full GC]                                  | GC停顿类型：Full GC说明发生STW                               |
| [DefNew]                                             | Serial GC对应新生代名称：Default New Generation              |
| [ParNew]                                             | ParNew GC对应新生代名称：Parallele New Generation            |
| [PSYoungGen]                                         | Parallel Scavenge GC对应新生代名称                           |
| [garbage-first heap]                                 | G1 GC                                                        |
| Allocation Failure                                   | 引起GC的原因：年轻代中没有足够的空间存储新的数据             |
| Ergonomics                                           |                                                              |
| [PSYoungGen:  5986K->696K(870K)]  5986K->704K(9216K) | [PSYoungGen:  5986K->696K(870K)]  ：GC回收前年轻代大小->回收后大小<br />5986K->704K(9216K)：GC回收前年轻代、老年代总大小->回收后大小 |
| [Times: user=0.00 sys=0.00, real=0.00 secs]          | user：用户态回收耗时<br />sys：内核态回收耗时<br />real：实际耗时，user+sys可能会大于real。 |

<img src="../../pictures/Snipaste_2023-06-10_11-03-32.png" width="1200"/>

<img src="../../pictures/JVM-GC日志垃圾收集器信息.drawio.svg" width="1200"/>

<img src="../../pictures/Snipaste_2023-06-10_12-51-55.png" width="1200"/>