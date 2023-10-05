# ASM 自动存储管理

- 自动管理磁盘组，提供数据冗余。

# ASM体系结构

## ASM实例(ASM Instances)

- ASM实例有自己的内存（SGA）和后台进程（Background Processes）。
  - 数据库实例和ASM实例是两种不同的实例
- ASM实例用于加载磁盘组(DiskGroups)，使ASM文件有效，以便数据库实例能够访问磁盘组中的数据。
- 在启动ASM实例时，可以不启动数据库实例。但启动数据库实例时，ASM实例必须已经启动（如果存在ASM）
  - ASM实例必须先于数据库实例的启动，和数据库实例同步运行，迟于数据库实例的关闭。
- ASM实例与数据库实例可以是1: n的关系，（如果是n>1,则需要ASM_HOME）。

## ASM磁盘组(ASM Disk Groups)

- ASM磁盘组其实是一个虚拟的概念，
- 一个ASM磁盘组由多个磁盘组成。
- 一个ASM文件只能存放在一个磁盘组中，一个文件所获得的空间要从磁盘组中进行划分。
- 每个磁盘组都包含ASM元数据(ASM Metadata)。

<img src="../../../../pictures/376745114239489.png" width="300"/> 

### ASM元数据(ASM Metadata)

ASM元数据驻留在磁盘组(Disk Groups)中，Orace使用这些信息管理磁盘组。

**ASM元数据包括：**

- 一个磁盘属于哪个磁盘组
- 磁盘组上的有效空间
- 一个磁盘组中有哪些文件
- 区(Extent)的位置

### ASM磁盘(ASM Disk)

ASM磁盘是真正存放数据的地方，

**一个ASM磁盘可以是：**

- 磁盘阵列上的一个磁盘或者分区
- 一整块磁盘或者来自一个磁盘上的分区
- 逻辑卷(Logical Volumes)
- 网络文件系统(Network-attached Files,NFS)

### 分配单元(Allocation Unit,AU)

- 磁盘组中的每个磁盘会被分成多个单元(Allocation Units,AU)，AU是磁盘组中空间分配的单位。
- 在创建磁盘组时，可以指定AU的大小。可以是1MB、2MB、4MB、8MB、16MB、32MB或者64MB。
- AU是ASM存储中结构最小的元素。

## ASM文件(ASM Files)

- 存放在ASM磁盘组中的文件(ASM Disk Group)叫ASM磁盘文件，
- 一个ASM文件由多个区(Extent)组成。
- 一个ASM磁盘文件只能存放在一个磁盘组中，
- ASM自动产生ASM文件的文件名，我们也可以给ASM文件指定一个容易记忆的别名。

<img src="../../../../pictures/275055414247522.png" width="228"/> 

**可以存放在磁盘组中的文件有：**

- 控制文件(Control files)
- 数据文件(Datafiles)、临时文件(Temporary Datafiles)和数据文件副本(Datafil Copies)
- 服务器参数文件(SPFILE)
- 重做日志文件(Online Redo Logs)、归档日志文件(Archive Logs)和闪回日志文件(Flashback Logs)
- RMAN生成的备份(RMAN Backups)
- 灾难恢复配置(Disaster Recovery Configurations)
- Change Tracking Bitmaps(改变跟踪位图)

## 区

- ASM磁盘组上创建文件时，最小的可寻址单元是区(ASM区和数据库的区概念不相同)。
- 每个区驻留在磁盘组中一个单独的磁盘，每个区由多个AU组成。
- 区的大小可变，区大小从1MB开始，文件达到某个阈值时，区大小增加至4MB，然后是16MB,最后当达到某个阈值后，区大小为64MB。
  - ASM实例会自动分配合适的区大小。
  - 较少数量的区即可容纳大量数据，减少共享池中的区总数，从而将性能提高。

<img src="../../../../pictures/562945614240191.png" width="186"/> 

## ASM冗余 镜像和失败组(Mirroring and Failure Groups)

- ASM通过<mark>镜像算法</mark>来实现对数据的保护，Oracle提供三种冗余机制(Redundancy Level Controls)来保护数据
- 在一个磁盘组内，ASM会把每个区尽量分布在不同的ASM磁盘上，当要读取数据时，Oracle会<mark>并行读取</mark>多块磁盘中的数据。

<img src="../../../../pictures/281610815247984.png" width="231"/>  

**常规冗余(Normal Redundancy)**

<img src="../../../../pictures/424270215236746.png" width="504"/> 

- 常规冗余(Normal Redundancy），只有两份相同的区(Extent)，一份存放在一个磁盘组中，另外一份存放在另一个磁盘组中。
- 常规冗余只能容忍一个磁盘组的损坏。

**高度冗余(High Redundancy)**

<img src="../../../../pictures/188950515250380.png" width="573"/> 

- 高度冗余(High Redundancy)，只有3份相同的区(Extent),分别放在3个磁盘组中。
- 其中两个磁盘组损坏，也不会导致数据的丢失

**外部冗余(Extemal Redundancy)**

- 由外部存储系统实现

# ASM使用

## 配置ASM实例

### Windows Server

- windows server
- 11g
- 11g_grid

#### ASM磁盘准备 

**1.准备磁盘分区**

- 不分配路径盘符
- 不格式化

<img src="../../../../pictures/167412115226727.png" width="462"/> 

**2.使用asmtoolg标记磁盘分区**

- 运行-->asmtoolg
  - 路径：C:\app\zjk10\product\11.2.0\dbhome_1\BIN\asmtoolg.exe
- 需要以管理员权限运行
  - 否则 
    <img src="../../../../pictures/419713515249167.png" width="268"/> 

<img src="../../../../pictures/587283915244303.png" width="402"/> 
<img src="../../../../pictures/141724015237849.png" width="402"/> 

#### 安装grid

<img src="../../../../pictures/427465918240191.png" width="579"/> 
<img src="../../../../pictures/244490019236746.png" width="576"/> 
<img src="../../../../pictures/309030119232500.png" width="583"/> 
<img src="../../../../pictures/52480319250380.png" width="580"/> 
<img src="../../../../pictures/Snipaste_2022-11-27_13-44-47.png" width="580"/> 
<img src="../../../../pictures/Snipaste_2022-11-27_13-48-35.png" width="580"/> 

#### 安装11gDatabase

1. 安装服务器类
2. 使用自动内存管理
   <img src="../../../../pictures/Snipaste_2022-12-04_00-10-57.png" width="580"/> 

- 不启用自动备份
- 口令：tiger

#### OHAS的配置

**1. 查看ora.orcl.db配置文件**

- 路径  C:\app\Administrator\product\11.2.0\grid\BIN

- 命令：

```
C:\app\Administrator\product\11.2.0\grid\BIN\crsctl.exe modify resource ora.orcl.db -attr "AUTO_START=1"

C:\app\Administrator\product\11.2.0\grid\BIN\crs_stat.exe -p ora.orcl.db
```

```相当于
C:\app\Administrator\product\11.2.0\grid\BIN>crsctl enable
```

<img src="../../../../pictures/Snipaste_2022-11-27_14-10-14.png" width="600"/> 

**2.查看各个资源状态**

```
C:\app\Administrator\product\11.2.0\grid\BIN\crs_stat.exe -t -v
```

<img src="../../../../pictures/Snipaste_2022-11-27_14-12-09.png" width="600"/> 

**3.关闭ohas服务**

```
C:\app\Administrator\product\11.2.0\grid\BIN\crsctl.exe stop has
```

<img src="../../../../pictures/Snipaste_2022-12-04_01-36-49.png" width="600"/> 

- 有出错。。。
  <img src="../../../../pictures/Snipaste_2022-11-27_14-15-41.png" width="600"/> 

**4.启动ohas服务**

```
C:\app\Administrator\product\11.2.0\grid\BIN\crsctl.exe start has
CRS-4123: Oracle High Availability Services 已启动。
```

#### 其他查看

- 以sysdba身份

**查看磁盘组**

```sql
SELECT name,state,total_mb
FROM v$asm_diskgroup;
```

<img src="../../../../pictures/Snipaste_2022-11-27_14-28-58.png" width="400"/> 

**查看实例**

```sql
SELECT instance_name,status,database_status
FROM v$instance;
```

<img src="../../../../pictures/Snipaste_2022-11-27_14-31-06.png" width="400"/> 

### Linux

[Linux](https://cloud.tencent.com/developer/article/1431952)

## ASM实例管理

**初始化参数**

| 参数            | 说明                                                         |
| :-------------- | :----------------------------------------------------------- |
| INSTANCE_TYPE   | 指定实例的类型，设置为ASM                                    |
| DB_UNIQUE_NAME  | ASM实例的名称，默认ASM+                                      |
| ASM_POWER_LIMIT | ASM实例的磁盘重新平衡的能力，默认为1                         |
| ASM_DISKTRING   | 指定一个字符串，ASM实例在创建磁盘组时按照这个字符串搜索可用的磁盘 |
| ASM_DISKGROUPS  | 指定磁盘组的名称，ASM实例启动时将自动加载这些磁盘组          |

### 登入

1. SET ORACLE_SID=+ASM
2. sqlplus / as sysasm

```
C:\Users\Administrator>SET ORACLE_SID=+ASM
C:\Users\Administrator>sqlplus / as sysasm

SQL*Plus: Release 11.2.0.1.0 Production on 星期日 12月 4 01:47:21 2022

Copyright (c) 1982, 2010, Oracle.  All rights reserved.

连接到:
Oracle Database 11g Enterprise Edition Release 11.2.0.1.0 - 64bit Production
With the Automatic Storage Management option
```

### 启动/关闭ASM实例

#### sqlplus

```sql
startup;
shutdown;
```

#### srvctl工具

```sql
srvctl status ASM; --ASM实例的状态
srvctl stop ASM;  --关闭ASM实例
srvctl start ASM; --启动ASM实例 
```

<img src="../../../../pictures/Snipaste_2022-11-27_17-29-46.png" width="400"/> 

### 开启CSS服务

- 用于同步ASM实例和数据库实例

**启动CSS服务**

```cmd
localconfig.bat -add
```

<img src="../../../../pictures/Snipaste_2022-11-27_17-31-26.png" width="600"/> 

**检查是否运行正常**

```cmd
crsctl.exe check css
```

<img src="../../../../pictures/Snipaste_2022-11-27_17-34-24.png" width="400"/> 

## 磁盘组管理

- 磁盘组，Oracle通过ASM技术将多个磁盘组织在一起，作为一个整体向数据库提供存储空间。在数据库运行时，数据将平均分布在磁盘组的各个磁盘。数据库管理员可以在不关闭数据库的情况下向磁盘组中添加/删除磁盘。在删除/添加磁盘时，自动存储管理将重新平衡磁盘，将数据重新平均发布在各个磁盘。
- 为获得最佳性能，在创建磁盘组时，应该将大小、性能相同或相近的磁盘放在一个磁盘组中，相反则放在其他磁盘组中。

### 磁盘组信息

V$ASM_DISK

V$ASM_DISKGROUP

### 创建磁盘组

#### asmtool 标记磁盘

- 即利用asmtool工具进行标记磁盘
- 或使用asmtoolg工具标记

##### asmtool -list 查看可用磁盘分区

<img src="../../../../pictures/Snipaste_2022-11-27_17-27-23.png" width="600"/> 

- 标有ORCLDISK___0~n 的表示已被使用的磁盘组
- 若为空置的，则为可用磁盘分区（空余磁盘空间）

##### asmtool -add 标记ASM磁盘

```cmd
asmtool -add 指向 命名（即：ORCLDISK的后缀）
```

<img src="../../../../pictures/Snipaste_2022-12-04_14-16-44.png" width="600"/> 


#### 查看ASM磁盘状态

```sql
SELECT path,mount_status
FROM V$ASM_DISK;
```

<img src="../../../../pictures/Snipaste_2022-12-04_14-26-28.png" width="500"/> 

#### 创建磁盘组的同时加入新的ASM磁盘

```sql
CREATE DISKGROUP 磁盘组名 HIGH|NORMAL|EXTERNAL REDUNANCY 
--高/普通/外部冗余
[DISK 'ASM磁盘的path'; --EXTERNAL 只有一块磁盘时的外部冗余]
[FAILGROUP 失败磁盘组名 DISK 'ASM磁盘的path'
 FAILGROUP 失败磁盘组名 DISK 'ASM磁盘的path'
] --NORMAL|HIGH 普通/高冗余，需要失败磁盘组
```

<img src="../../../../pictures/Snipaste_2022-12-04_14-36-28.png" width="500"/> 

##### 磁盘阵列的RAID盘

- 则不需要为磁盘组指定镜像。

##### 有失败组的普通冗余

- 如果是普通冗余，至少要两块ASM磁盘作为失败组，可以多。
- 如果是高冗余，则至少需要3块ASM磁盘作为失败组，可以多。

<img src="../../../../pictures/Snipaste_2022-12-04_15-17-22.png" width="600"/> 

##### 一块磁盘的外部冗余

<img src="../../../../pictures/Snipaste_2022-12-04_14-34-19.png" width="600"/> 

- 此时，其对应的mount_status变为CACHED
  <img src="../../../../pictures/Snipaste_2022-12-04_14-48-38.png" width="600"/> 

#### 将磁盘组加入到SPFILE

```sql
SHOW PARAMETER ASM_DISK;
```

<img src="../../../../pictures/Snipaste_2022-12-04_14-50-20.png" width="550"/> 

- TYPE 标识为string的即为加入SPFIEL的。

**若TYPE没有标识，即为新加入的磁盘组，未加入SPFIEL**

1. 导出PFILE
   <img src="../../../../pictures/Snipaste_2022-12-04_14-56-09.png" width="300"/> 
2. 修改PFILE，在INIT_ASM.ora加入 `.asm_diskgroups='磁盘组名1',磁盘组名2`
   <img src="../../../../pictures/Snipaste_2022-12-04_14-57-57.png" width="450"/> 
3. 创建新的SPFIEL，并启动（需要先将连接的Oracle实例关闭，才能关闭ASM实例）
   <img src="../../../../pictures/Snipaste_2022-12-04_15-02-55.png" width="400"/> 
4. 检查：`SHOW PARAMETER ASM_DISK`

### 删除磁盘组

- 删除磁盘组时，磁盘组上的所有文件将一起被删除。在删除时，ASM实例必须被启动，磁盘组必须被加载，并且磁盘组上的所有文件必须被关闭。
- 在删除之后，需要查看SPFILE是否改变。

**磁盘组中没有文件时**

```sql
DROP DISKGROUP 磁盘组名;
```

**磁盘组中有文件时**

```sql
DROP DISKGROUP 磁盘组名 INCLUDING CONTENTS;
```

<img src="../../../../pictures/Snipaste_2022-12-04_15-34-02.png" width="800"/> 
<img src="../../../../pictures/Snipaste_2022-12-04_15-36-31.png" width="500"/> 

### 磁盘组添加/删除磁盘

#### 添加

**添加一个磁盘**

```sql
ALTER DISKGROUP 磁盘组名 ADD DISK 'ASM磁盘的path' NAME '对该磁盘命名';
```

**添加多个磁盘**

```sql
ALTER DISKGROUP 磁盘组名 ADD DISK 'ASM磁盘的path','ASM磁盘的path'...;
-- 或
ALTER DISKGROUP 磁盘组名 ADD FILEGROUP 失败组名 DISK 'ASM磁盘的path','ASM磁盘的path'...;
```

- 如果没有指定ASM磁盘所属的失败组，Oracle指定确定磁盘属于哪个磁盘组。
- FAILGROUP关键字指定要加入/创建的失败组

<img src="../../../../pictures/Snipaste_2022-12-04_15-50-27.png" width="500"/> 

#### 删除

**删除一个磁盘**

```sql
ALTER DISKGROUP 磁盘组名 DROP DISK 磁盘名;
```

**删除失败组内所有磁盘**

```sql
ALETR DISKGROUP 磁盘组名 DROP DISKS IN FAILGROUP 失败组名;
```

##### UNDROP 取消删除

- 在删除完成前可以使用UNDROP进行取消

```sql
ALTER DISKGROUP 磁盘组名 UNDROP DISKS;
```

##### FORCE 强制删除，无法恢复

### 磁盘组的重新平衡

- 当磁盘组中的磁盘数量改变时，ASM实例将对其自动进行一次重新平衡，将磁盘组中的内容重新平均分布到现有的各个磁盘上。
- 磁盘组的平衡能力分为12级从0~11，0表示停止平衡，11表示速度最快的平衡；指定的级别越高，需要消耗的系统资源就越多。**初始化参数ASM_POWER_LIMIT**限制可以使用的最高平衡级别，使高于这个值的级别的平衡操作无效。

```sql
ALTER DISKGROUP 磁盘组名 REBALANCE POWER 级别;
```

### 磁盘组的加载/卸载

- 为了能访问磁盘组中的文件，必须在ASM实例启动时加载磁盘组。**初始化参数ASM_DISKGROUP**指定自动加载的磁盘组。当关闭ASM实例时，磁盘组被自动卸载。对于新创建的磁盘组，用户需要手动设置。

**手动加载**

```sql
ALTER DISKGROUP 磁盘组名 MOUNT;
--加载所有
ALTER DISKGROUP ALL MOUNT;
```

**手动卸载**

- 当磁盘组上有文件处于打开状态时，卸载将失败，此时使用FORCE关键字强制卸载。

```sql
ALTER DISKGROUP 磁盘组名 DISMOUNT;
--强制卸载
ALTER DISKGROUP 磁盘组名 DISMOUNT FORCE;
```

### 目录管理

- 在磁盘组中包含一套完整的目录层次，在创建磁盘组时自动产生，数据库文件存储在这些目录中。每个文件名称由Oracle自动产生，称为系统别名。也可以指定用户别名，存储在用户创建的目录。

**用户别名**

- 每个目录以"+"号和磁盘组的名称为开始，包括完整路径。
- 在创建一个目录时，其上层目录必须存在。

#### 创建目录 

```sql
ALTER DISKGROUP 磁盘组名 ADD DIRECTORY '+磁盘组名\目录';
```

<img src="../../../../pictures/Snipaste_2022-12-05_13-41-16.png" width="500"/> 

#### 重命名目录

```sql
ALTER DISKGROUP 磁盘组名 RENAME DIRECTORY '+磁盘组名\要重命名的目录' TO '+磁盘组名\新的目录名';
```

<img src="../../../../pictures/Snipaste_2022-12-05_13-43-53.png" width="500"/> 

#### 删除目录

```sql
ALTER DISKGROUP mydg1 DROP DIRECTORY '+磁盘组名\要删除的目录' [FORCE];
```

- 如果目录中包含文件，那么需要FORCE关键字强制删除。
- 对于磁盘组中本就存在的文件（系统别名），无法删除

<img src="../../../../pictures/Snipaste_2022-12-05_13-46-16.png" width="500"/> 

#### 查看目录内容

#### 别名

- 只能给文件起别名，目录不能起别名。

##### 指定别名

- 在添加用户别名时，需要提供该别名的完整路径。

```sql
ALTER DISKGROUP 磁盘组名 ADD ALIAS '+磁盘组\别名' FOR '+磁盘组\文件路径';
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/)

##### 修改别名

```sql
ALTER DISKGROUP 磁盘组名 RENAME ALIAS '+磁盘组\旧别名' TO '+磁盘组\新别名';
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/)

##### 删除别名

```sql
ALTER DISKGROUP 磁盘组 DELETE ALIAS '+磁盘组\要删除的别名';
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/)

### 文件操作

- 利用ASM管理数据库文件时，文件存储在磁盘组的磁盘中，磁盘组中的文件对操作系统是不可见的。

**Oracle实例的SPFILE**

<img src="../../../../pictures/Snipaste_2022-12-05_14-06-17.png" width="650"/> 

**在创建表空间、数据文件等时，可以使用ASM磁盘作为存放的位置，与之前在Windows磁盘的使用相同**
**可以通过对SPFILE等启动文件的初始化参数的修改实现对存放位置等的修改或创建**