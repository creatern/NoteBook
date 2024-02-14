# NFS概述

- NFS是一种分布式文件系统，允许网络中不同操作系统的计算机间共享文件，其通信协议基于TCP/IP协议层，可以将远程计算机磁盘挂载（mount）到本地并进行读写。NFS在文件传送/信息传送过程中依赖于RPC协议，该协议可以在不同的系统之间使用，此通信协议设计于主机及操作系统无关。

<table>
    <tr>
        <td width="20%">文件和进程</td>
        <td width="80%">意义</td>
    </tr>
    <tr>
        <td>nfs.service</td>
        <td>NFS服务启停控制单元，位于<code>/usr/lib/systemd/system/nfs.service</code></td>
    </tr>
    <tr>
        <td>rpc.nfsd</td>
        <td>NFS守护进程，控制客户端是否可以登录服务器，是nfs-utils的一部分存放在usr/sbin目录，可以结合/etc/hosts.allow和/etc/hosts.deny</td>
    </tr>
    <tr>
        <td>rpc.mountd</td>
        <td>RPC安装守护进程，管理NFS的文件系统，初始化客户端的mount请求，是nfs-utils的一部分存放在usr/sbin目录</td>
    </tr>
    <tr>
        <td>rpc.statd</td>
        <td>抓取文件锁，是nfs-utils的一部分存放在usr/sbin目录</td>
    </tr>
    <tr>
        <td>rpc.rquotad</td>
        <td>对客户文件的磁盘配额限制，是nfs-utils的一部分存放在usr/sbin目录</td>
    </tr>
    <tr>
        <td>rpcbind</td>
        <td>管理RPC连接，对NFS是必需的。因为是NFS的动态端口分配守护进程，如果rpcbind不启动，NFS服务无法启动</td>
    </tr>
    <tr>
        <td>exportfs（指令）</td>
        <td>管理NFS共享文件系统列表 <br />如果修改了/etc/exports，则不需要重新激活NFS，只需要重新扫描一遍/etc/exports文件，并重新将设定加载即可</td>
    </tr>
    <tr>
        <td>showmount（指令）</td>
        <td>查询“mountd”守护进程，以显示NFS服务器加载的信息。</td>
    </tr>
</table>
