# jps

```shell
jps [-q] [-mlvV] [<hostid>]
```

| 参数 | 说明                                                         |
| ---- | ------------------------------------------------------------ |
| \-q  | 仅显示本地虚拟机唯一ID（local virtual machine id，LVMID）    |
| \-l  | 输出应用程序主类的全类名。若执行的是jar包，则输出jar完整路径 |
| \-m  | 输出虚拟机进程启动时传递给主类main()方法的参数               |
| \-v  | 列出虚拟机进程启动时的JVM参数。                              |
| \-V  |                                                              |

- 若Java进程关闭了UsePerfData参数（默认打开），则jps命令无法探知该进程。

```shell
-XX:-UsePerfData
```

# jstat

```shell
jstat -<option> [-t] [-h<lines>] <vmid> [<interval> [<count>]]
```
| 参数     | 说明                                      |
| -------- | ----------------------------------------- |
| option   | 查询的信息选项                            |
| \-t      | Timestamp列，显示程序运行时间（s）        |
| \-h      | 表头信息输出，每lines行后输出一次         |
| vmid     | 虚拟机进程唯一ID                          |
| interval | 指定输出统计数据的周期（查询间隔），ms/次 |
| count    | 指定查询的总次数                          |

|option选项|说明|
|--|--|
|\-class|显示ClassLoader的相关信息<br />Loaded Bytes Unloaded Bytes Time|
|\-gc|显示与GC相关的堆信息<br />S0C S1C S0U S1U EC EU OC OU MC MU CCSC CCSU YGC YGCT FGC FGCT CGC CGCT GCT|
|\-gccapacity|与\-gc基本相同，主要关注Java堆各个区域使用到的最大、最小空间<br />NGCMN NGCMX NGC S0C S1C EC OGCMN OGCMX OGC <br />OC MCMN MCMX MC CCSMN CCSMX CCSC YGC  FGC CGC|
|\-gcutil|与\-gc基本相同，主要关注已使用空间占总空间的百分比<br />S0 S1 E O M CCS YGC YGCT FGC FGCT CGC CGCT GCT|
|\-gccause|与\-gcutil基本相同，额外输出导致最后一次或当前正在发生的GC的原因<br />S0 S1 E O M CCS YGC YGCT FGC FGCT CGC CGCT GCT LGC GCC|
|\-gcnew|显示新生代GC状况<br />S0C S1C S0U S1U TT MTT DSS EC EU YGC YGCT|
|\-gcnewcapacity|与\-gcnew基本相同，主要关注使用到的最大、最小空间<br />NGCMN NGCMX NGC S0CMX S0C S1CMX S1C ECMX EC YGC FGC CGC|
|\-gcold|显示老年代GC状况<br />MC MU CCSC CCSU OC OU YGC FGC FGCT CGC CGCT GCT|
|\-gcoldcapacity|与\-gcold基本相同，主要关注使用到的最大、最小空间<br />OGCMN OGCMX OGC OC YGC FGC FGCT CGC CGCT GCT|
|\-gcmetacapacity|显示元空间使用到的最大、最小空间<br />MCMN MCMX MC CCSMN CCSMX CCSC YGC FGC FGCT CGC CGCT GCT|
|\-compiler|显示JIT编译器编译信息<br />Compiled Failed Invalid Time FailedType FailedMethod|
|\-printcompilation|输出已经被JIT编译的方法<br />Compiled Size Type Method|

- 判断OOM：计算两次测量之间的GCT增量和Timestamp增量，GC时间占运行时间的比例 = GCT增量 / Timestamp增量。比例超过20%，则当前堆的压力较大；比例超过90%，则堆内几乎没有可用空间，随时可能OOM。

- 判断内存泄漏：抽取多组OU中的最小值，若呈现上升趋势，则该Java进程的老年代内存已使用量不断上升，无法回收的对象在不断增加，可能存在内存泄漏。