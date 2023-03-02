## RMAN

#### RMAN环境简介

1. RMAN是对数据库进行备份和恢复操作并自动管理相关备份策略的客户端工具。
2. RMAN环境至少包括两部分：
      - 目标数据库（target database）：即需要进行备份和恢复的数据库，在RMAN命令行下通过target命令指定；
      - RMAN客户端：默认存放于$ORACLE_HOME/bin目录下的可执行程序，用于执行RMAN命令进行数据库备份和恢复操作。
- 在某些情况下，RMAN环境还可能包括：
   - 快速恢复区（fast recovery area）：用于存放和管理备份恢复相关文件的地方，可通过DB_RECOVERY_FILE_DEST和DB_RECOVERY_FILE_DEST_SIZE设置。
   - 介质管理器：RMAN与介质设备打交道所需要的应用程序；
   - 恢复目录（recovery catalog）：单独的数据库方案，用于存储其管理的多个目标数据库的元数据。

#### 启动RMAN并连接到目标数据库

- 通过cmd命令行而不是SQL环境启动RMAN；

##### 1.选择要连接的数据库实例

- 如果只有一个实例，则无需选择，Oracle自动默认连接

```
SET ORACLE_SID=数据库实例;
```

```
SET ORACLE_SID=ORCL;
```

##### 2.连接RMAN

1. 本地连接

```
RMAN TARGET 用户/密码;
RMAN TARGET /; --无密码连接
```

```
RMAN TARGET sys/change_on_install;
```

2. 连接到远程数据库

```
RMAN TARGET 用户/密码@数据库实例
```

```
RMAN TARGET sys/change_on_install@orcl;
```

**记录RMAN日志**

- 记录本次RMAN的操作,连接失败也记录
- 该文件实时更新，记录RMAN语句的输出值，查询结果等
   - 此时，在命令行界面没有显示输出值
   - 而在设置的RMAN日志文件中实时更新应该显示的输出值。 
   - 直到HOST；

```
RMAN TARGET sys/change_on_install LOG '记录日志存放路径.txt'
```

```
RMAN TARGET sys/chenge_on_install@orcl LOG 'E:\rman_test.txt'

RMAN SHOW DEVICE TYPE;
--文件内容
恢复管理器: Release 11.2.0.1.0 - Production on 星期五 10月 21 21:21:12 2022

Copyright (c) 1982, 2009, Oracle and/or its affiliates.  All rights reserved.

连接到目标数据库: ORCL (DBID=1634085857)

RMAN> 
使用目标数据库控制文件替代恢复目录
db_unique_name 为 ORCL 的数据库的 RMAN 配置参数为:
CONFIGURE DEVICE TYPE DISK PARALLELISM 1 BACKUP TYPE TO BACKUPSET; # default
```

###### 无密码连接时 可能出现的错误(远程数据库连接的方式正常)

```
C:\Users\zjk10>RMAN TARGET /

恢复管理器: Release 11.2.0.1.0 - Production on 星期五 10月 21 20:30:52 2022

Copyright (c) 1982, 2009, Oracle and/or its affiliates.  All rights reserved.

RMAN-00571: ===========================================================
RMAN-00569: =============== ERROR MESSAGE STACK FOLLOWS ===============
RMAN-00571: ===========================================================
RMAN-00554: 内部恢复管理器程序包初始化失败
RMAN-04005: 目标数据库中存在错误:
ORA-12560: TNS: 协议适配器错误
```

**解决**

1. 法1

- 到Oracle安装目录找到 network\admin\sqlnet.ora文件`C:\app\10548\product\11.2.0\dbhome_1\NETWORK\ADMIN`
- 将**SQLNET.AUTHENTICATION_SERVICES**= (NONE)修改为 SQLNET.AUTHENTICATION_SERVICES= (NTS)，将操作系统用户添加到ORA_DBA组

2. 法2

- 增加recovery_file_dest_size参数值即可(SQL环境)

```
ALTER SYSTEM
SET DB_RECOVERY_FILE_DEST_SIZE=大小 SCOPE=SPFILE;
```

##### 3.退出RMAN

```
HOST
```

#### RMAN基本使用

- 在RMAN中可以使用数据库的启动关闭等语句。
- 使用RMAN连接到数据库时，默认使用SYSDBA角色登陆。
- 可以使用除了SELECT外的SQL语句
   - 格式
   
```
SQL 'sql语句';
```

#### 查看当前RMAN配置

```
SHOW ALL;
```

```
SHOW CHANNEL;
```

```
SHOW DEVICE TYPE;
```

```
SHOW DEFAULT DEVICE TYPE;
```

#### BACKUP 备份数据库

1. 通过BACKUP命令备份数据库，默认情况下将备份文件放到快速恢复区中，若要备份到其他路径，需要设置FORMAT参数。

```
BACKUP;
```

2. RMAN默认备份类型为备份集而非镜像。可通过输入BACKUP AS COPY来备份镜像文件。

```
BACKUP AS COPY;
```

##### 在归档模式备份数据库

- 若数据库开启归档，则数据库支持OPEN状态进行RMAN备份，
   - 但此时的备份为非一致性备份，即在恢复时，需要使用到归档重做日志文件，才能使数据库恢复到一致性状态。

```
BACKUP DATABASE PLUS ARCHIVELOG;
```

##### 在非归档模式下进行RMAN备份

- 在非归档模式下的数据库，只能进行一致性备份，
    - 备份前需要将数据库一致性关闭后，打开到MOUNT状态，再进行备份。


```
SHUTDOWN IMMEDIATE;

BACKUP DATABASE;
```

##### 常用典型备份选项

**FORMAT**：指定备份片段的存放路径和命名规则。

```
BACKUP FORMAT '/u01/app/oracle/oradata/enmo1/AL_%d/%t/%s/%p'   
ARCHIVELOG LIKE '%arc_dest%';

其中：
%U获得系统分配的一个唯一名；
%d为DB_NAME;
%t备份设置的时间戳；
%s为备份设置的编号；
%p为备份片段编号。
```

**TAG**：给备份片段指定一个标签，若不指定，则系统自动生成一个随机唯一标签。

- 标签在备份原数据中总是以大写方式存储。

```
BACKUP TAG 'weekly_full_db_bkup' DATABASE MAXSETSIZE 10M;
```

##### 增量备份

###### 创建增量备份

- 增量备份分为累计增量备份（cumulative incremental backup）和差异增量备份（differential incremental backup）。
- oracle默认类型为差异增量备份。
- 增量备份需要先进行一次0级备份，作为备份的起点。
    - oracle建议备份的级别只限于0级和1级，方便备份管理

**差异增量备份和累积增量备份的差别：**

- 差异增量备份：每次备份至上一次备份级别小于等于当前级别的备份。
- 累积增量备份：每次备份至上一次小于当前级别的备份。

**增量备份例子：**

```
backup incremental level 0 database;----0级增量备份，作为增量备份策略的基础
backup incremental level 1 cumulative database;----1级累积增量备份
backup incremental level 1 database;----1级差异增量备份
```

###### 创建增量更新备份

**RMAN支持增量更新备份，但是该特性需要以下前提条件：**

1. 需要0级数据文件镜像作为基础，并且这些镜像需要一个系统定义或者个人定义的标签。
2. 定期备份期间，1级差异增量备份的标签需要和0级数据文件镜像的标签一致。
    - 通过`BACKUP FOR RECOVER OF COPY`命令可以指定一个只备份从上一个相同标签的备份以来发生数据块改变的增量备份。
3. 定期备份期间，增量备份被应用到0级镜像上，由于数据文件镜像已经被增量备份更新过，这样在介质恢复时，所需要的工作量将大大减少。

**命令例子：**

```
backup incremental level 0 for recover of copy tag 'test' database; ----使用tag标记数据文件镜像作为备份策略基础
recover copy of database with tag 'test';----增量更新备份
```

#### 检查数据库文件和备份的有效性

1. 有效性包括：数据文件是否存在于正确的路径下，并且是否存在物理块损坏。
    - 通过`CHECK LOGICAL`命令，还可以检查是否存在逻辑块损坏。
2. 在备份时，对数据文件和归档日志文件进行检查：`BACKUP VALIDATE CHECK LOGICAL DATABASE ARCHIVELOG ALL;`
3. 支持对数据文件中的数据库进行检查：`VALIDATE DATAFILE 4 BLOCK 10 TO 30;`
4. 支持对数据库备份集进行验证：`VALIDATE BACKUPSET 3;`

#### 使用脚本文件来执行RMAN任务

- RMAN支持所有格式的文件

**脚本文件的引用**

1.

```
RMAN @/my_dir/test.txt
```

2.登陆RMAN后

```
@/my_dir/test.txt
```

#### 列出RMAN备份信息

##### 使用LIST列出RMAN备份信息

**列出数据库中所有文件的备份信息：**

```
LIST BACKUP OF DATABASE;
```

**列出指定表空间的备份信息：**

```
LIST COPY OF TABLESPACE '表空间名';
```

**列出指定数据文件的备份信息：**

```
LIST BACKUP OF DATAFILE '数据文件路径';
```

```
list backup of database by backup;
list backup by file;
list backup summary;
list expired backupset/copy;
list backup recoverable;
```

##### 使用REPORT列出RMAN备份信息

```
REPORT NEED BACKUP DATABASE;--列出当前需要备份的数据文件
REPORT OBSOLETE;
REPORT SCHEMA;
REPORT UNRECOVERABLE;
```

#### 维护RMAN备份

**CROSSCHECK**：

- 该命令将同步数据库备份和镜像在实际存储上的位置和资源库中的逻辑记录，如果备份文件在磁盘存在，CROSSCHECK会检查文件头部是否有效。
- 一般在删除备份之前，需要进行CROSSCHECK检查。

```
CROSSCHECK BACKUP ;     CROSSCHECK  COPY;
```

#### 删除备份

**DELETE命令**

- 将会删除磁盘或者磁带上的备份文件，并且更新控制文件中的状态为“已删除”或者删除备份目录（backup catalog）中的记录。


**删除陈旧备份**

- 当使用RMAN执行备份操作时，RMAN会根据备份冗余策略确定陈旧备份。     


```
DELETE OBSOLETE;
```

**删除EXPIRED备份**

- 执行crosscheck命令核对备份集，那么会将该备份集标记为EXPIRED状态。为了删除相应的备份记录，可以执行delete expired backup命令。     

```
DELETE EXPIRED BACKUP;
```

**删除EXPIRED副本**

```
DELETE EXPIRED COPY;
```

**删除特定备份集**

```
DELETE BACKUPSET 22;
```

**删除特定备份片**

```
DELETE BACKUPPIECE '路径'
```

RMAN> delete backuppiece 'd:\backup\rusky.bak';

**删除所有备份集**

```
DELETE BACKUP
```

**删除特定映像副本**

```
DELETE DATAFILECOPY '路径';
```

RMAN> delete datafilecopy 'd:\backup\rusky.bak';

**删除所有映像副本**

```
DELETE COPY;
```

**在备份后删除输入对象**

```
DELETE ARCHIVELOG ALL DELETE INPUT;
```

```
DELETE BACKUPSET 22 FORMAT = "路径" DELETE INPUT;
```

RMAN> delete backupset 22 format = ''d:\backup\%u.bak'' delete input;  

#### 使用数据恢复建议器（data recovery advisor）进行失败诊断和修复

1. 列出当前失败并确定修复选项：
    - oracle中失败是指被Health Monitor监测到的数据损坏，例如逻辑或物理的数据块损坏、数据文件丢失等。
    - 监测到的失败有不同的优先级（CRITICAL、HIGHT和LOW），还有状态（OPEN和CLOSED）。
2. 通过`LIST FAILURE`可以查看当前监测到的失败，
    - 若在同一会话中执行`ADVISE FAILURE`命令，数据库会列出手工和自动的修复选项以供选择。
3. 一般首先通过手工修复方式来进行修复，若手工修复不成功，再进行自动修复。


#### 使用闪回数据库技术
1. 想使用闪回数据库技术，需要先开启闪回日志功能。
   - 闪回日志只能存放在快速恢复区中，并且不会进行归档
2. 闪回数据库不能用于介质恢复和修复数据文件的丢失。
3. 闪回数据库需要在MOUNT状态下进行。

**命令：**

```
shutdown immediate ;----调整至mount状态 
startup mount;
flashback database to scn 1526845;----闪回到过去的某时刻
flashback database to restore point before_points;
flashback database to timestamp to_date('20140510','yyyymmdd');
alter database read only;----将数据库置为只读状态，进行验证
shutdown immediate;----若闪回后满足要求，启动数据库
startup mount;
alter database open resetlogs;
```

### 还原和恢复数据库文件

1. 还原是指从所有的备份或镜像文件中找到一个用于恢复操作的数据文件。
2. 恢复是指在还原的数据文件上应用redo日志或增量备份中记录的变化，使得数据文件向前滚到一个SCN值或者一个时间点。
3. 在对数据库进行还原恢复操作时，可以先进行预览：restore database preview summary;
4. 恢复整个数据库：
    
```
     startup force mount;----将数据库至于mount状态
     restore database;----还原数据库
     recover database;----恢复数据库
     alter database open;
```

5. 恢复表空间：当数据库处于打开状态，可以通过restore tablespace和recover tablespace来进行表空间级的还原和恢复。在对表空间进行还原和恢复时，需要将表空间涉及到的数据文件置为离线，恢复完成后，再设为在线。
     alter tablespace users offline;
     ![](C:/Users/zjk10/OneDrive/NoteBook/pictures/563094213221062.png)
     alter tablespace users online;
6. 对数据块进行恢复
    RMAN工具在进行备份时，会将备份过程中检测到的坏块记录到V$DATABASE_BLOCK_CORRUPTION视图中；
    在备份过程中，坏块还会记录在告警日志和TRACE文件中，可以通过V$DIAG_INFO查看这些文件的位置，找到相关文件进行坏块查看。
    使用RMAN进行坏块恢复
     recover corruption list;----修复所有的坏块
     recover datafile 1 block 33, 44 datafile 2 blocke 1 to 200;


### 介质恢复

介质恢复是基于物理备份恢复数据，它包括块恢复、数据文件恢复、表空间恢复和整个数据库的恢复。

#### 介质恢复的过程

**步骤**

介质恢复过程包括还原（RESTORE）和恢复（RECOVER）两个步骤

- RESTORE：将某个时间点的数据文件的拷贝再拷贝回去，还原到一个不一致的状态或不是最新的状态，还需要recover操作
- RECOVER：使用日志（归档或是联机在线日志）将不一致的数据库应用到一致性状态

**恢复过程分类**

数据库的恢复过程又分为完全恢复和不完全恢复。

- 完全恢复
   - 完全恢复是一种没有数据丢失的恢复方式，它能够恢复到最新联机在线日志中已提交的数据。
- 不完全恢复
   - 不完全恢复是一种丢失数据的恢复方式，称为基于时间点的恢复。
   - 它只能针对整个数据库的恢复。
   - 不完全恢复根据备份情况恢复到与指定时间、日志序列号和SCN具有一致性的数据，之后的数据都将丢失。
   - 使用`RESTLOGS`选项打开数据库

**场景**

- 丢失归档日志，联机日志的情形
- 在某一刻误操作数据，想要找回数据


##### 数据库完全恢复

Oracle数据库中有各种各样的块，如空块、表头块、数据块、索引块、数据文件头块、UNDO段块等。如果损坏的是普通表的数据块，那即不会影响启动数据库，也不会影响其他表的正常访问，只是在访问该表的时候会出现问题。如果损坏的是数据文件的块头，那么将导致数据库无法正常启动和停止，进行块恢复不能修复这个问题，只有数据文件的完全恢复才能恢复数据文件块头的损坏或尝试使用BBED修改数据文件块头。

###### 数据文件的完全恢复

**命令还原、恢复6号数据文件**

```
RMAN TARGET sys/change_on_install;
RESTORE DATAFILE '数据文件路径'|文件号;
RECOVER DATAFILE '数据文件路径'|文件号;
```

```
RMAN TARGET sys/change_on_install;
RESTORE DATAFILE 'E:\test_dat_01.DBF';
RECOVER DATAFIILE 'E:\test_dat_01.DBF';
```

**打开数据库**

```
ALTER DATABASE OPEN;
```

##### 数据库不完全恢复

###### 不完全恢复的选项


|      不完全恢复方式      |   	RMAN选项    |
| :--------------------: | :------------: |
|    恢复到某个时间点	    |   until time   |
| 恢复到某个日志序列号	 | until suquence |
|     恢复到某个SCN号     |  	until SCN   |


###### 基于时间点的不完全恢复

```
rman target sys/change_on_install
run {
shutdown immediate;
startup mount;

SQL "alter session set nls_date_format=''YYYY-MM-DD HH24:MI:SS''";

set until time '2022-10-21 18:00:00';

restore database;

recover database;

alter database open resetlogs;
}
```

```
RMAN> run {
2> shutdown immediate;
3> startup mount;
4>
5> SQL "alter session set nls_date_format=''YYYY-MM-DD HH24:MI:SS''";
6>
7> set until time '2022-10-21 18:00:00';
8>
9> restore database;
10>
11> recover database;
12>
13> alter database open resetlogs;
14> }

数据库已卸装
Oracle 实例已关闭

已连接到目标数据库 (未启动)
Oracle 实例已启动
数据库已装载

系统全局区域总计    1720328192 字节

Fixed Size                     2176448 字节
Variable Size               1174407744 字节
Database Buffers             536870912 字节
Redo Buffers                   6873088 字节

sql 语句: alter session set nls_date_format=''YYYY-MM-DD HH24:MI:SS''

正在执行命令: SET until clause

启动 restore 于 21-10月-22
分配的通道: ORA_DISK_1
通道 ORA_DISK_1: SID=130 设备类型=DISK

RMAN-00571: ===========================================================
RMAN-00569: =============== ERROR MESSAGE STACK FOLLOWS ===============
RMAN-00571: ===========================================================
RMAN-03002: restore 命令 (在 10/21/2022 21:39:04 上) 失败
RMAN-06026: 有些目标没有找到 - 终止还原
RMAN-06023: 没有找到数据文件5的副本来还原
RMAN-06023: 没有找到数据文件4的副本来还原
RMAN-06023: 没有找到数据文件3的副本来还原
RMAN-06023: 没有找到数据文件2的副本来还原
RMAN-06023: 没有找到数据文件1的副本来还原
```

###### 基于SCN的不完全恢复

```
run {
shutdown immediate;

startup mount;

set until scn 5166390;

restore database;

recover database;

alter database open resetlogs;
}
```

###### 基于序列的不完全恢复

```
run {
shutdown immediate;

startup mount;

set until scn 5166390;

restore database;

recover database;

alter database open resetlogs;
}
```


### 归档模式  (Data guard???)

Oracle数据库有联机重做日志，这个日志是记录对数据库所做的修改，比如插入，删除，更新数据等，对这些操作都会记录在联机重做日志里。

一般数据库至少要有2个联机重做日志组。当一个联机重做日志组被写满的时候，就会发生日志切换，这时联机重做日志组2成为当前使用的日志，当联机重做日志组2写满的时候，又会发生日志切换，去写联机重做日志组1，就这样反复进行。

- 如果数据库处于非归档模式,联机日志在切换时就会丢弃. 
- 而在归档模式下，当发生日志切换的时候，被切换的日志会进行归档。
    - 比如，当前在使用联机重做日志1，当1写满的时候，发生日志切换，开始写联机重做日志2，这时联机重做日志1的内容会被拷贝到另外一个指定的目录下。这个目录叫做归档目录，拷贝的文件叫归档重做日志。
- 数据库使用归档方式运行时才可以进行灾难性恢复。

**归档日志模式和非归档日志模式的区别**

- 非归档模式只能做冷备份,并且恢复时只能做完全备份.最近一次完全备份到系统出错期间的数据不能恢复.
- 归档模式可以做热备份,并且可以做增量备份,可以做部分恢复.
- 用ARCHIVE LOG LIST 可以查看模式状态是归档模式还是非归档模式.

#### 归档日志文件

[2768249.html](https://www.cnblogs.com/mq0036/archive/2012/11/13/2768249.html)

#### 归档参数

* log_archive_dest_n：归档文件的存放目录。可以是本地目录，网络目录和网络连接存储（NAS）。
* log_archive_dest_state_n：为归档目标值定义两个状态：如果为 enable，则 arch 进程可以将归档日志归档至指定的值；如果为 defer，则 arch 进程不会在相对应的位置存放归档。
* log_archive_format：指定归档日志的命名格式。
* log_archive_max_processes：定义数据库启动 arch 进程的数量。
* log_archive_min_succeed_dest：指定归档最小成功复制的位置个数。
* log_archive_start：默认为 false。Oracle 10g 及其以后版本 arch 进程自动启动，故不在使用。

#### 配置数据库的归档模式

- 需在 MOUNT 状态下修改归档模式

##### 改变非归档模式/归档模式

**需在 MOUNT 状态下修改归档模式**

1. 关闭数据库

```
SHUTDOWN NORMAL/IMMEDIATE;
```

2. 将数据库启动到MOUNT状态

```
START MOUNT;
```

3. 修改数据库为归档模式/非归档模式

```
ALTER DATABASE ARCHIVELOG|NOARCHIVELOG;
```

4. 将数据库完全启动

```
ALTER DATABASE OPEN;
```

##### 启用自动归档: LOG_ARCHIVE_START=TRUE

- 归档模式下,日志文件组不允许被覆盖(重写)，
   - 当日志文件写满之后，如果没有进行手动归档,
   - 那么系统将挂起,直到归档完成为止.
   - 这时只能读而不能写.

**运行过程中关闭归档日志进程**

```
ARCHIVE LOG STOP
```

**运行过程中关闭和重启归档日志进程**

```
ARCHIVE LOG START
```

##### 手动归档: LOG_ARCHIVE_START=FALSE

###### 归档当前/所有日志文件

```
ALTER SYSTEM 
ARCHIVE LOG CURRENT|ALL;
```

###### 归档指定序号的日志文件

1. 查看日志文件的序号

```
SQL> ARCHIVE LOG LIST
数据库日志模式         存档模式
自动存档              启用
存档终点              USE_DB_RECOVERY_FILE_DEST
最早的联机日志序列     58
下一个存档日志序列     60
当前日志序列           60
```

2. 执行归档指定序列的日志文件

```
ALTER SYSTEM 
ARCHIVE LOG SEQUENCE 序号;

--不能用于当前的日志文件归档
ORA-00259: 日志 10 (打开实例 orcl 的日志, 线程 1) 为当前日志, 无法归档
--不能用于已经归档的日志文件
ORA-16013: 日志 1 sequence# 58 不需要归档
```

###### 改变归档日志目标

```
ALTER SYSTEM 
ARCHIVE LOG CURRENT TO '归档日志文件存放路径';
```

##### 配置多个归档进程 LOG_ARCHIVE_MAX_PROCESSES

- 如果归档过程会消耗大量的时间，那么可以启动多个归档进程
- 这是个动态参数,可以用`ALTER SYSTEM`动态修改.

```
ALTER SYSTEM 
SET LOG_ARCHIVE_MAX_PROCESSES = 数量;
```

Oracle9i中最多可以指定10个归档进程

**与归档进程有关的动态性能视图**

- V$BGPROCESS

```
SQL> DESC V$ARCHIVE_PROCESSES
 名称                                      是否为空? 类型
 ----------------------------------------- -------- ----------------------------
 PROCESS                                            NUMBER
 STATUS                                             VARCHAR2(10)
 LOG_SEQUENCE                                       NUMBER
 STATE                                              VARCHAR2(4)
 
SELECT *
FROM V$ARCHIVE_PROCESSES;
```

- V$ARCHIVE_PROCESSES

```
DESC V$BGPROCESS
 名称                                      是否为空? 类型
 ----------------------------------------- -------- ----------------------------
 PADDR                                              RAW(8)
 PSERIAL#                                           NUMBER
 NAME                                               VARCHAR2(5)
 DESCRIPTION                                        VARCHAR2(64)
 ERROR                                              NUMBER
```

##### 配置归档目标(归档路径)   LOG_ARCHIVE_DEST_n


| Oracle基本参数(LOG_ARCHIVE_DEST_n)            |
| :------------------------------------------- |
| 此参数指定归档日志文件的位置                    |
| 参数类型:字符串                                |
| 语法:LOG_ARCHIVE_DEST_1=’path_to_dir’         |
| 默认值:无                                     |
| 大部分可通过ALTER SYSTEM 或 ALTER SESSION 修改 |
| 此为基本参数                                  |

**其他信息**

- n的取值范围为1到10,可设置多个归档日志路径
- 该参数有很多属性，其中location或service是必须的(二选一)
- location指定本地路径，service为dataguard备库的service name
- location或service必须是第一个设置的,其他属性可选

**语法**

```
ALTER SYSTEM|SESSION 
SET LOG_ARCHIVE_DEST_n = '
 { null_string |
 { LOCATION=path_name | SERVICE=service_name }
 [ MANDATORY ]
 [ REOPEN[=seconds] ]
 [ DELAY[=minutes] ]
 [ ENCRYPTION=ENABLED|DISABLED ]
 [ GROUP=group ]
 [ NOREGISTER ]
 [ PRIORITY=priority ]
 [ TEMPLATE=template ]
 [ ALTERNATE=destination ]
 [ MAX_FAILURE=count ]
 [ SYNC | ASYNC ]
 [ AFFIRM | NOAFFIRM ]
 [ NET_TIMEOUT=seconds ]
 [ VALID_FOR=(redo_log_type,database_role) ]
 [ DB_UNIQUE_NAME ]
 [ COMPRESSION={ENABLE|DISABLE|ZLIB|LZO} ]
 }'
```

**注：**

- 可通过查询V$ARCHIVE_DEST 视图来查看具体的属性设置
- 该参数必须和LOG_ARCHIVE_DEST_STATE_n一一对应

**查看信息**

```
SHOW PARAMETER LOG_ARCHIVE_DEST_n;
```

**存档终点**

oracle10默认是把存档终点设置为USE_DB_RECOVERY_FILE_DEST，所以归档日志默认是保存在oracle系统的闪回恢复区（Flash recovery area）的。默认的db_recovery_file_dest是2G，所以一般建议将存档终点修改为操作系统的适当目录。

```
SHOW USE_DB_RECOVERY_FILE_DEST;
```

###### 归档目标(归档路径) 

**本地归档目标:**

```
ALTER SYSTEM 
SET LOG_ARCHIVE_DEST_n = "LOCATION=路径";
```

```
ALTER SYSTEM
SET LOG_ARCHIVE_DEST_1 = 'LOCATION=E:\log_archive_test\';
```

**远程归档目标:**

```
ALTER SYSTEM 
SET LOG_ARCHIVE_DEST_n = "SERVICE=STANDBY_DB1";
```

**强制的归档目标,如果出错,一定时间后重试:**

```
ALTER SYSTEM 
SET LOG_ARCHIVE_DEST_n = "LOCATION=路径 MANDATORY REOPEN=时间(秒)";
```

```
ALTER SYSTEM
SET LOG_ARCHIVE_DEST_4 = 'LOCATION=E:\log_archive_test\ MANDATORY ROPEN=30';
```

**可选的归档目标,如果出错,放弃归档:**

```
ALTER SYSTEM 
SET LOG_ARCHIVE_DEST_n = "LOCATION=路径 OPTIONAL";
```

##### 归档目标状态 LOG_ARCHIVE_DEST_STATE_n

**关闭归档目标1**

```
ALTER SYSTEM 
SET LOG_ARCHIVE_DEST_STATE_n = DEFER 
```

**打开归档目标2**

```
ALTER SYSTEM 
SET LOG_ARCHIVE_DEST_STATE_n = ENABLE 
```

##### 归档日志格式

```
LOG_ARCHIVE_FORMAT
```

##### 获取归档日志信息

**数据字典**

- V$ARCHVIED_LOG
- V$ARCHVIE_DEST
- V$LOG_HISTORY
- V$DATABASE
- V$ARCHIVE_PROCESSES

**语句**

```
ARCHIVE LOG LIST;
```

```
SQL> ARCHIVE LOG LIST
数据库日志模式         存档模式
自动存档              启用
存档终点              USE_DB_RECOVERY_FILE_DEST
最早的联机日志序列     58
下一个存档日志序列     60
当前日志序列           60
```

#### 查看oracle数据库是否为归档模式：

**视图V$DATABASE**

```
SELECT name
      ,log_mode
FROM V$DATABASE;
```

