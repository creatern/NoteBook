# 检测命令

## uptime 负载信息

- uptime：查看Linux系统负载信息。

<img src="../../pictures/20231011231831.png" width="600"/> 

## vmstat 虚拟内存状态

```shell
# vmstat
procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
 1  0      0 4128428   2116 428040   0    0     3     0   14   11  0  0 100 0  0
```

<table>
    <tr>
        <th>参数</th>
        <th>值</th>
        <th>说明</th>
    </tr>
    <tr>
        <td rowspan="2">Procs</td>
        <td>r</td>
        <td>运行队列中进程数量，可判断是否需要增加CPU。（长期大于1）</td>
    </tr>
    <tr>
        <td>b</td>
        <td>等待IO的进程数量</td>
    </tr>
    <tr>
        <td rowspan="4">Memory</td>
        <td>swpd</td>
        <td>当前使用虚拟内存大小；若swpd不为0，而si、si长期为0，并不会影响系统性能。</td>
    </tr>
    <tr>
        <td>free</td>
        <td>空闲物理内存大小</td>
    </tr>
    <tr>
        <td>buffer</td>
        <td>缓冲的内存大小</td>
    </tr>
    <tr>
        <td>cache</td>
        <td>缓存的内存大小；若频繁访问到的文件都能被cache处理，则磁盘的读IO bi会非常小。</td>
    </tr>
    <tr>
        <td rowspan="3">Swap</td>
        <td>si</td>
        <td>每秒从交换区写到内存的大小，由磁盘调入内存</td>
    </tr>
    <tr>
        <td>so</td>
        <td>每秒写入交换区的内存大小，由内存调入磁盘</td>
    </tr>
    <tr>
        <td colspan="2">内存够用的时候，si和so都是0；若si、so长期大于0，影响系统性能，磁盘IO和CPU资源都会被消耗</td>
    </tr>
    <tr>
        <td rowspan="3">IO</td>
        <td>bi</td>
        <td>每秒读取的块数</td>
    </tr>
    <tr>
        <td>bo</td>
        <td>每秒写入的块数</td>
    </tr>
    <tr>
        <td colspan="2">随机磁盘读写时，bi、bo越大，能看到CPU在IO等待的值也会越大</td>
    </tr>
    <tr>
        <td rowspan="3">system</td>
        <td>in</td>
        <td>每秒中断数（包括时钟中断）</td>
    </tr>
    <tr>
        <td>cs</td>
        <td>每秒上下文切换数</td>
    </tr>
    <tr>
        <td colspan="2">in、cs值越大，由内核消耗的CPU时间就越大</td>
    </tr>
    <tr>
        <td rowspan="4">CPU</td>
        <td>us</td>
        <td>用户进程执行时间百分比（user time）；值高时，说明用户进程消耗的CPU时间多；<br />若长期超50%的使用，就该考虑优化程序算法、进行加速</td>
    </tr>
    <tr>
        <td>sy</td>
        <td>内核系统进程执行时间百分比（system time）；值高时，说明系统内核消耗的CPU资源多，非良性</td>
    </tr>
    <tr>
        <td>wa</td>
        <td>O等待时间百分比；值高时，说明IO等待比较严重，这可能由于磁盘大量作随机访问造成，也有可能磁盘出现瓶颈（块操作）</td>
    </tr>
    <tr>
        <td>id</td>
        <td>空闲时间百分比</td>
    </tr>
</table>


## 系统内存状态

### free

- free：显示系统内存使用量情况，包含物理和交换内存的总量、使用量和空闲量情况。

<table><tbody><tr><td>-b</td><td>设置显示单位为Byte</td></tr><tr><td>-g</td><td>设置显示单位为GB</td></tr><tr><td>-h</td><td>自动调整合适的显示单位</td></tr><tr><td>-k</td><td>设置显示单位为KB</td></tr><tr><td>-l</td><td>显示低内存和高内存统计数据</td></tr><tr><td>-m</td><td>设置显示单位为MB</td></tr><tr><td>-o</td><td>不显示缓冲区数据列</td></tr><tr><td>-s</td><td>持续显示内存数据</td></tr><tr><td>-t</td><td>显示内存使用总合 </td></tr><tr><td>-V</td><td>显示版本信息</td></tr></tbody></table>

```shell
# free -mts 10
               total        used        free      shared  buff/cache   available
Mem:           15381        3061        8663         611        3657       11384
Swap:          15624           0       15624
Total:         31006        3061       24288
```

| 参数       | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| total      | 内存总数，total = used + free                                |
| used       | 已使用的内存数，总计分配给缓存（buffers+cache）使用的数量    |
| free       | 空闲的内存数                                                 |
| shared     | 共享内存（已废弃）                                           |
| buff/cache | 缓存内存数<br />buffers Buffer是系统分配但未使用的buffers数量；cached Page是系统分配但未使用的cache数量<br />(-buffer/cache) used：Mem中的used - buffers - cached，反映被程序真实消耗的内存<br />(+buffer/cache) free：Mem中的free + buffers + cached，反映可以挪用的内存 |
| available  |                                                              |

- 内存使用百分比：(total - free - buffers - cached) / total，即(-/+ buffers/cache)行的used / total；若超过80%，则需要进行应用程序的算法优化。

### [top](#top)
