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
|\-class|显示ClassLoader的相关信息|
|\-gc|显示与GC相关的堆信息|
|\-gccapacity|与\-gc基本相同，主要关注Java堆各个区域使用到的最大、最小空间|
|\-gcutil|与\-gc基本相同，主要关注已使用空间占总空间的百分比|
|\-gccause|与\-gc基本相同，额外输出导致最后一次或当前正在发生的GC的原因|
|\-gcnew|显示新生代GC状况|
|\-gcnewcapacity|与\-gcnew基本相同，主要关注使用到的最大、最小空间|
|\-gcold|显示老年代GC状况|
|\-gcoldcapacity|与\-gcold基本相同，主要关注使用到的最大、最小空间|
|\-gcmetacapacity|显示元空间使用到的最大、最小空间|
|\-compiler|显示JIT编译器编译信息|
|\-printcompilation|输出已经被JIT编译的方法|