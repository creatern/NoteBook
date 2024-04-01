# Oracle安装

- 以Oracle Linux 9为例

## 预安装

### 自动配置 oracle-database-preinstall

```shell
# 安装 oracle-database-preinstall的RPM包
# 会自动创建标准（非角色分配）Oracle 安装所有者，并根据 Oracle 安装的需要进行分组和设置其他内核配置设置（不包括grid）
# 具体参考 https://docs.oracle.com/en/database/oracle/oracle-database/19/ladbi/Chunk151261164.html#GUID-C15A642B-534D-4E4A-BDE8-6DC7772AA9C8
dnf install oracle-database-preinstall-19c-1.0-1.el9.x86_64
```

### 手动配置

<table>
    <caption>user</caption>
    <tr>
        <td width="10%">oracle</td>
        <td width="90%">拥有所有Oracle安装或一至多个Oracle数据库安装的用户</td>
    </tr>
    <tr>
        <td>grid</td>
        <td>创建为仅拥有 Oracle Grid Infrastructure 软件安装的用户，同时拥有 Oracle Clusterware 和 Oracle Automatic Storage Management 二进制文件</td>
    </tr>
</table>
<table>
    <caption>group</caption>
    <tr>
        <td>oinstall</td>
        <td>Oracle软件所有者必须将Oracle oraInventory组作为其主要组，以便每个 Oracle 软件安装所有者都可以写入中央清单 （oraInventory），并正确设置 OCR 和 Oracle Clusterware 资源权限</td>
    </tr>
    <tr>
        <td>可选</td>
        <td>OSDBA、OSOPER、OSBACKUPDBA、OSDGDBA、OSRACDBA 和 OSKMDBA 组作为Oracle软件所有者的辅助组</td>
    </tr>
    <tr>
        <td align="center" colspan="2">数据库管理员 标准组</td>
    </tr>
    <tr>
        <td rowspan="2">dba</td>
        <td>OSDBA组，首次在系统上安装 Oracle 数据库软件时，必须创建此组；此组标识具有数据库管理权限（特权） SYSDBA 的操作系统用户帐户</td>
    <tr>
        <td>如果未为 Oracle ASM 实例创建单独的 OSDBA、OSOPER 和 OSASM 组，则具有 SYSOPER 和 SYSASM 权限的操作系统用户帐户必须是此组的成员；即如果未将单独的组指定为 OSASM 组，则默认情况下，您定义的 OSDBA 组也是 OSASM 组。</td>
    </tr>
    <tr>
        <td>oper</td>
        <td>OSOPER 授予 OPERATOR 启动和关闭数据库的权限（权限 SYSOPER ）。默认情况下，OSDBA 组的成员具有该 SYSOPER 权限授予的所有权限。</td>
    </tr>
    <tr>
        <td align="center" colspan="2">作业角色分离 扩展组</td>
    </tr>
    <tr>
        <td>backupdba</td>
        <td>OSBACKUPDBA 组，使得单独的操作系统用户组具有一组有限的数据库备份和恢复相关管理特权（SYSBACKUP 特权）</td>
    </tr>
    <tr>
        <td rowspan="2">dgdba</td>
        <td>Oracle Data Guard 的 OSDGDBA 组，使得单独的操作系统用户组具有一组有限的特权来管理和监视 Oracle Data Guard（SYSDG 特权）</td>
    </tr>
    <tr>
        <td>如果创建了该组，则必须将Oracle软件所有者添加为此组的成员</td>
    </tr>
    <tr>
        <td rowspan="2">kmdba</td>
        <td>OSKMDBA 组，使得一组单独的操作系统用户具有一组有限的加密密钥管理特权</td>
    </tr>
    <tr>
        <td>如果创建了该组，则必须将Oracle软件所有者添加为此组的成员</td>
    </tr>
    <tr>
        <td rowspan="2">racdba</td>
        <td>用于 Oracle Real Application Clusters Administration 的 OSRACDBA 组，使得单独的操作系统用户组具有一组有限的 Oracle Real Application Clusters （RAC） 管理权限（SYSRAC 权限）</td>
    </tr>
    <tr>  
        <td>必须执行如下操作：（1）将 Oracle 数据库安装所有者添加为此组的成员；（2）对于 Oracle 重新启动配置，如果您具有单独的 Oracle Grid Infrastructure 安装所有者用户 （ grid ），则还必须将该 grid 用户添加为数据库的 OSRACDBA 组的成员，以使 Oracle Grid Infrastructure 组件能够连接到数据库</td>
    </tr>
    <tr>
        <td colspan="2" align="center">ASMSNMP</td>
    </tr>
    <tr>
        <td rowspan="2">ASMSNMP</td>
        <td>ASMSNMP 用户是具有监视 Oracle ASM 实例权限的 Oracle ASM 用户</td>
    </tr>
    <tr>
        <td>除了 OSASM 组（其成员被授予管理 Oracle ASM 的 SYSASM 系统权限）之外，Oracle 还建议您创建一个权限较低的用户 ASMSNMP ，并授予该用户监视 Oracle ASM 实例的 SYSDBA 权限。Oracle Enterprise Manager 使用 ASMSNMP 用户监视 Oracle ASM 状态。</td>
    </tr>
    <tr>
        <td colspan="2" align="center"> ASM </td>
    </tr>
    <tr>
        <td rowspan="4">asmadmin</td>
        <td>Oracle ASM 管理的 OSASM 组，将此组创建为单独的组，以便为 Oracle ASM 和 Oracle 数据库管理员分隔管理权限组。此组的成员被授予管理 Oracle ASM 的 SYSASM 系统特权</td>
    </tr>
    <tr>
        <td>Oracle ASM 可以支持多个数据库。如果系统上有多个数据库，并且使用多个 OSDBA 组以便为每个数据库提供单独的 SYSDBA 特权，则应创建一个组，其成员被授予 OSASM/SYSASM 管理特权，并创建一个不拥有数据库安装的网格基础结构用户（网格），以便将 Oracle Grid Infrastructure SYSASM 管理特权与数据库管理特权组分开。</td>
    </tr>
    <tr>
        <td>OSASM 组的成员可以使用 SQL 通过操作系统身份验证将 Oracle ASM 实例作为 SYSASM 连接到 Oracle ASM 实例。SYSASM 权限允许装载和卸除磁盘组以及其他存储管理任务。SYSASM 权限不提供对 RDBMS 实例的访问权限。</td>
    </tr>
    <tr>
        <td>如果未将单独的组指定为 OSASM 组，但确实定义了用于数据库管理的 OSDBA 组，则默认情况下，您定义的 OSDBA 组也定义为 OSASM 组。</td>
    </tr>
    <tr>
        <td>asmoper</td>
        <td>Oracle ASM 的 OSOPER 组，可选组。如果您希望单独的操作系统用户组具有一组有限的 Oracle 实例管理权限（用于 ASM 的 SYSOPER 特权），包括启动和停止 Oracle ASM 实例，请创建此组。默认情况下，OSASM 组的成员还具有 SYSOPER 授予的 ASM 权限的所有权限。</td>
    </tr>
</table>


```shell
# Oracle oraInventory组
groupadd -g 54321 oinstall
```

```shell
id oracle
#uid=54321(oracle) gid=54321(oinstall) #groups=54321(oinstall),54322(dba),54323(oper),54324(backupdba),54325(dgdba),54326(kmdba),54330(racdba)

id grid
#uid=54331(grid) gid=54321(oinstall) groups=54321(oinstall),54322(dba),
#54327(asmdba),54328(asmoper),54329(asmadmin),54330(racdba)
```

