<img src="../../pictures/Snipaste_2023-05-17_16-08-46.png" width="1000"/>

<table>
    <tr>
        <th width="15%">指令集架构</th>
        <th>架构模型说明</th>
    </tr>
    <tr>
        <td>基于栈</td>
        <td>1. 设计、实现更简单，适用资源受限的系统<br/>2. 跨平台性，不需要硬件支持，可移植性好<br/>3. 指令集小，零地址指令方式分配，执行过程依赖于操作栈</td>
    </tr>
    <tr>
        <td>基于寄存器</td>
        <td>1. 性能优秀，执行高效<br/>2. 完全依赖硬件，可移植性差<br/>3. 操作需要的指令少，通常以一地址指令、二地址指令、三地址指令为主</td>
    </tr>
</table>

<table>
    <tr>
        <th width="15%">生命周期</th>
        <th>虚拟机阶段说明</th>
    </tr>
    <tr>
        <td>启动</td>
        <td>BootClassloader创建（由虚拟机具体实现指定的）初始类（initial class）</td>
    </tr>
    <tr>
        <td>执行</td>
        <td>执行Java程序（JVM的进程）</td>
    </tr>
    <tr>
        <td>退出</td>
        <td>1.正常执行结束<br />2.执行过程异常、错误<br />3.操作系统出错<br />4.Java安全管理器允许线程调用Runtime/System的exit()/halt()<br />5.JNI加载、卸载JVM。</td>
    </tr>
</table>