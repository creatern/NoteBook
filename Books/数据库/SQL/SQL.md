# SQL概述

| 引号   | 使用位置                                                     |
| ------ | ------------------------------------------------------------ |
| 单引号 | 字符型：'hello' <br />日期型：'17-12月-80' <br />to_char/to_date(日期,'YYYY-MM-DD HH24:MI:SS') |
| 双引号 | 列别名：select ename "姓 名" from emp;<br />to_char/to_date(日期,'YYYY"年"MM"月"DD"日" HH24:MI:SS') |
| 逃逸符 | 字符型、日期型等内部使用单引号时：使用两个连续的单引号''来表示一个单引号。<br />第一个单引号为逃逸符，用来消除第二个单引号的特殊含义。 |

## DML、DDL、DCL

| 术语                                          | 说明                                                         |
| --------------------------------------------- | ------------------------------------------------------------ |
| DML（data manipulation language）数据操纵语言 | 对数据库的数据进行一些操作<br />DML会对每个语句的每条记录都做日志记录以便于回滚。<br />insert、update、delete、select。 |
| DDL（data definition language）数据库定义语言 | 定义或改变表的结构，数据类型，表之间的链接和约束等初始化工作。<br />create、alter、drop。 |
| DCL （data control language) 数据库操纵语言   | grant、revoke、commit、rollback、savepoint、lock。           |

## 事务

- 事务会将所有在事务中被修改的数据行加上锁（行级锁），来阻止其它人（会话）同时对这些数据的修改操作。当事务被提交或回滚后，这些数据才会被释放锁。事务分为：一个或多个DML语句、一个DDL语句、一个DCL语句。


- DML事务（一般事务）由以下情况之一结束：commit、ROLLBACK；DDL(自动提交)；用户会话正常结束；系统异常终止。

- commit、ROLLBACK：

1. 数据完整性。
2. 数据改变被提交之前预览。
3. 将逻辑上的操作分组。

| 事务进程（数据状态） | 说明                                                         |
| -------------------- | ------------------------------------------------------------ |
| 提交/回滚前          | 1. 改变前的数据状态是可以改变的。<br/>2. 执行DML操作的用户可以通过select语句查询之前的修正。<br/>3. 其他用户不能看到当前用户所做的改变，直到当前用户结束事务。<br/>4. DML语句所涉及的行被锁定，其他用户不能操作。 |
| 提交后               | 1. 数据的改变保存到数据库中。<br/>2. 改变前的数据已经丢失。<br/>3. 所有用户可以看到结果。<br/>4. 所有保存点被释放。 |
| 回滚后               | 1. 数据改变被取消。<br/>2. 修改前的数据状态被恢复。<br/>3. 锁被释放。 |

### 提交 commit

#### 显式提交、隐式提交

- 显示提交：commit语句。

> 触发隐式提交的SQL：alter，AUDIT，COMMENT，ConNECT，create，DISConNECT，drop，EXIT，grant，NOAUDIT，QUIT，REVOKE，rename。

- 隐式提交：无需显示执行commit语句，session中的操作被自动提交到数据库的过程。
  - 此隐式提交是在自己的session，如果在其他人的session中正在修改相同的数据，则引起隐式提交的语句则必须等待。

| 隐式提交           | 说明                                  |
| ------------------ | ------------------------------------- |
| 正常执行完DDL语句  | create、alter、drop、truncate、rename |
| 正常执行完DCL语句  | grant、REVOKE                         |
| 正常退出`sql*plus` | 没有明确发出commit/ROLLBACK           |

- 执行DDL语句时（即使DDL语句执行失败），其之前的DML操作也会被提交到数据库中：为了避免隐式提交或者回滚，尽量保证一条或者几条DML操作完成后有显示的提交/回滚，防止后续执行的DCL或者DDL自动提交前期的DML操作。

- 事务读一致性：commit之前的数据，其他用户不可见。执行DDL语句时，Oracle需要在它的系统表中进行元数据的记录操作，如果不隐式提交就无法保证一致性。

#### 自动提交 autocommit

- 自动提交：autocommit设置为on，则DML语句执行后，系统将自动进行提交。

```sql
set AUtocommit on；
```

```sql
--查看当前是否是自动提交：
show AUtocommit；
```

#### 保存点 savepoint

```sql
--设置保存点：当事物提交后保存点也被清除，需要重新设置
savepoint 保存点名称;         
```

```sql
--回滚到保存点
rollback to savepoint 保存点名称; 
```

# 权限管理

> **系统权限SYSDBA、SYSOPER**
>
> - SYSDBA > SYSOPER（超级权限）：同属于系统权限（System Privileges），Oracle允许拥有该权限的用户在数据库未打开的情况下访问实例、执行重大的数据库操作（数据库启动、关闭等）。
>
> | sysdba可执行任务          | 说明                                           |
> | ------------------------- | ---------------------------------------------- |
> | STARTUP、SHUTDOWN         | 启动、关闭数据库                               |
> | alter dataBASE            | 修改数据库<br />打开、装载、备份、改变字符集等 |
> | create dataBASE           | 创建数据库                                     |
> | drop dataBASE             | 删除数据库                                     |
> | create SPFILE             | 创建服务器参数文件                             |
> | alter dataBASE ARCHIVELOG | 改变归档模式                                   |
> | alter dataBASE RECOVER    | 执行数据库恢复                                 |

```shell
# 以SYSDBA登录数据库
sqlplus sys/change_on_install[@orcl] as sysdba
```


## 数据库安全性

- 数据库安全性：用户、角色、概要文件。

> 数据库安全性、系统安全性、数据安全性。

### 用户

#### 创建用户 

> 创建用户需要DBA权限。

- 一般信息 表空间 分配用户创建表空间等数据库对象存储再硬盘中分配空间
- 系统 系统权限
- 角色 系统/对象权限赋予角色，角色赋予用户
- 限额 表空间

```sql
create user user_name
IDENTIFIED BY password;
```

#### 用户解锁

```sql
alter user scott  
IDENTIFIED BY tiger 
ACcount UNLOCK;
```

#### 用户信息

##### 查看当前用户名

```sql
show user
```

##### 查看所有用户名 all_userS

```sql
select *
from all_userS
```

#### 用户密码

- 可用户自己修改

```sql
alter user user_name
inDETIFIED BY password;
```

#### 切换用户

```sql
ConNECT scott/tiger@orcl
```

### 角色

- 角色可以像用户一样被赋予权限和收回权限。
- 当角色被赋予某个用户时，其权限也被赋予给用户。因此：一个用户的权限=其本身的权限+其拥有的角色的权限。

#### 创建角色

```sql
create role 角色名
```

#### 常用角色

| 角色     | 拥有的权限     |
| :------- | :------------- |
| connect  | 连接数据库     |
| resource | 使用数据库资源 |

## 权限管理

1. 不同对象具不同的对象权限
2. 对象的拥有者拥有所有的权限  或 SYSDBA/SYSTEM用户
3. 对象的拥有者可以向外分配权限

### public 公共用户

- 向public分配/收回权限，相当于向所有用户（包括还未创建的和已经创建的用户）分配/收回权限。

### SQL标准权限

| 权限            | 说明         |
| :-------------- | :----------- |
| select          | 查询         |
| insert          | 插入         |
| update          | 更新         |
| delete          | 删除         |
| all [privilege] | 以上所有权限 |

### grant 赋予权限

```sql
grant 权限列表
[on [模式.]关系/视图]
to 用户/角色列表;
```

#### with grant OPTIon 获得分配权限的能力

```sql
grant 权限列表
[on [模式.]关系/视图]
to 用户/角色列表;
with grant OPTIon;
```

#### grant BY current_role

- 通过角色授权，避免级联收权。

```sql
--先设置与当前会话关联的角色（默认空）必须是该授权用户拥有的角色
set role 角色
--通过与当前会话关联的角色授权
grant 权限列表
[on [模式.]关系/视图]
to 用户/角色列表
with grant OPTIon
BY CURRENT_ROLE; 
```

### REVOKE 收回对象权限

```sql
REVOKE 权限列表
[on [模式.]关系/视图]
from 用户/角色列表
```

#### REVOKE grant OPTIon FOR 仅收回分配权

```sql
revoke grant option for 权限列表
[on [模式.]关系/视图]
from 用户/角色列表
```

#### RESTRICT 阻止级联收权

- 在grant授权时，通常会产生一个**授权图**。当revoke收回权限时，默认级联收权：即A授权给B，B授权给C时，如果收回B的权限，则C的权限也被收回。
- RESTRICT 阻止级联收权；如果发生级联授权则使这次收权失败。

```sql
revoke 权限列表
[on [模式.]关系/视图]
from 用户/角色列表
restrict
```

### 行级授权

#### VPD Oracle虚拟私有数据库

- 将函数和关系相关联，该函数返回一个谓词，并自动将该谓词添加到使用该关系的任何查询。
- 隐患：可能会改变查询的含义。

### grant references 引用权限

### 权限分配情况

| 数据库字典          | 描述                       |
| :------------------ | :------------------------- |
| ROLE_SYS_PRIVS      | 角色拥有的系统权限         |
| ROLE_TAB_PRIVS      | 角色拥有的对象权限         |
| user_ROLE_PRIVS     | 用户拥有的角色             |
| user_TAB_PRIVS_MADE | 用户分配的关于表对象权限   |
| user_TAB_PRIVS_RECD | 用户拥有的关于表对象权限   |
| user_COL_PRIVS_MADE | 用户分配的关于列的对象权限 |
| user_COL_PRIVS_RECD | 用户拥有的关于列的对象权限 |
| user_SYS_PRIVS      | 用户拥有的系统权限         |

# 基本查询

## select .. from

- from：确定查询的数据源。

```sql
--查询指定值域
select emp_name
from emp;

--查询所有*
select *
from emp;

--查询运算式
select salary * 1.02
from emp;
```

| 关键字   | 说明                           |
| -------- | ------------------------------ |
| as       | 别名（没有命名规范，可省略as） |
| `||`     | 连接符                         |
| distinct | 唯一性                         |

```sql
select first_name || '' || last_name
from employees;
```

| 运算符              | 说明       |
| ------------------- | ---------- |
| `+  - * /`          | 算术运算符 |
| `> >= < <= = <> !=` | 比较运算符 |
| AND OR not          | 逻辑运算符 |

## where 谓词查询

- where子句（如果存在）则必须置于from子句之后。

```sql
select * 
from emp
where salary > 100;
```

| 关键字          | 说明                                                         |
| --------------- | ------------------------------------------------------------ |
| in              | 列表取值                                                     |
| is null         | 判断空值                                                     |
| like            | 模糊查询<br />`-`：一个字符<br />`%`：n个字符<br />\\：转义符 |
| between A and B | 范围查询（A<=X<=B）                                          |

```sql
select last_name
      ,employee_id
      ,salary
from employees
where salary > 1000
 AND department_id in (10,20,30);
```

```sql
--查询没有部门的员工
select *
from emp
where dept_name = null;
```

```sql
--查询员工姓名含有a的
select last_name
      ,employee_id
from employees
where last_name LIKE '%a%'
```

```sql
select last_name
      ,employee_id
      ,salary
from employees
where salary BETWEEN 1000 AND 7000;
```

### null 空值处理

- null表示的是未知，而不是空、0、空格。
- null=null：判断的结果是false/unknow，两个null并不相等。

#### not in

- 对于not in，如果测试值不在列表内，但列表中有一个null，则结果为false。

- 单行子查询中返回空值，要使用in之类的关键字，= 的话查询结果永远为空

```sql
--查询不是管理者的员工
select *
from employees
where employee_id not in (
    select manager_id
    from employees  --包含null值
);  
--会不返回结果，返回空值，
--因为当子查询中包含空值的时候，不能使用not in，not in等同于不等于所有（永远为假）

--改为：
select*
from employees
where employee_id not in (
    select manager_id
    from employees
    where manager_id IS not null --去除null值的影响
);
```

#### 组函数处理null

- count()：将null看作0来处理。
- 其他：忽略输入集合中的null，如果作用在null上，则返回null。

## order 排序

- order by子句只能在语句最后出现，可以使用第一个查询中的列名，别名或相对位置。

| 顺序 | 说明       |
| :--- | :--------- |
| ASC  | 默认，升序 |
| DESC | 降序       |

```sql
select employee_id
      ,last_name
from employees
order by employee_id DESC;
```

- 多列排序：依次在先前列的排序结果上进行排序。

```sql
--先对employee_id排列，再在employee_id排列的基础上对last_name排列
select emlpoyee_id
      ,last_name
from employees
order by employee_id,last_name;
```

- 别名排列：order by子句直接使用别名进行排序。

```sql
select employee_id
      ,last_name
      ,salary*1.1 sal --别名
from employees
order by sal;
```

## 集合运算

| 运算 | 说明                 |
| ---- | -------------------- |
| 并集 | union<br />union all |
| 交集 | intersect            |
| 差集 | minus                |

- 多个进行集合运算的结果集的select字句中的列名、表达式在数量、数据类型上一一对应。

1. 除union all之外，其余集合运算会自动去重。

2. 除union all之外，其余集合运算会自动按照第一个查询中的第一个列的升序排列。

```sql
--使用相对位置排序举例
select job_id,1
from employees
where department_id = 10
union
select job_Id ,2
from employees
where department_id = 20
order by 1 DESC;
```

## case .. when

- case语句可以使用在任何应该出现值的地方。

```sql
case
    when 条件 then 值
    when 条件 then 值
    ....
    else 值
end
```

```sql
--输出除编号为1的员工即where empno <> 1;
select *
from emp
where 0 = case
           when empno = '1' then 1
           else 0
          end
```

# 数据类型

## 字符型

| 长度类型 | 数据类型     | 说明                                   |
| :------- | :----------- | :------------------------------------- |
| 固定     | CHAR(n)      | max2000                                |
|          | nchar(n)     | unicode格式，max1000，多数用来存储汉字 |
| 可变     | varchar2(n)  | max4000                                |
|          | nvarchar2(n) | unicode格式，max2000                   |

## 数值型

| 数据类型    | 说明                                                    |
| :---------- | :------------------------------------------------------ |
| number(p,s) | p为有效数字，s为小数点后面的位数，s可正可负             |
| FLOAT(n)    | 用来存储二进制数据，二进制数据的1-126位，一般使用number |
| int         | 整数                                                    |
| intEGER     |                                                         |

## 日期型

| 数据类型  | 说明                                                       |
| :-------- | :--------------------------------------------------------- |
| DATE      | 范围为公元前4712年1月1日到公元9999年12月31日，可以精确到秒 |
| TIMESTAMP | 可以精确到小数秒，一般用DATE类型                           |

## 其他类型

| 数据类型 | 说明                             |
| :------- | :------------------------------- |
| LonG     | 可变长字符数据，最大2G           |
| RAW      | (LonG RAW) 原始的二进制          |
| BLOB     | 可存4G数据以二进制存放           |
| CLOB     | 可存4G数据以字符串存放           |
| BFILB    | 存储外部文件的二进制数据，最大4G |

# table 表（关系）

- table二维表，row行（记录）， column列（域、字段）。  每一列是相同的数据类型、列名唯一的；每一行的数据是唯一的。  

## 表结构操作

### desc 查看表结构

```sql
desc tablename;
```

### create table 创建表

- create table：权限、存储空间、命名规则。

- 命名规则：以字母开头、在1-30个字符之间、只能包含`A ~ Z,a ~ z，0~9，_，$，#`、不能和其他对象重名、不能是关键字。

```sql
create table emp (
 id number(6,2)
,salary number(10)
,name varchar2(25)
,hire_date DATE
);
```

#### as 复制表 

```sql
create table emp
as
select *
from employees
where 1 = 2;  --只复制结构，不复制内容
--where 1 = 1;  --既复制结构也 复制内容
```

### alter table 修改表结构

#### add 添加字段

```sql
alter table emp
add job varchar2(30);
```

#### modify 更改字段数据类型   

- modify：更改字段数据类型、为新追加的列定义默认值default。

```sql
alter table emp
modify id number(10) [default 100];
```

#### drop column 删除字段 

```sql
alter table emp
drop column job;
```

#### rename column 修改字段名 

```sql
alter table emp
rename column id to emp_id;
```

### rename table 修改表名 

```sql
rename table emp to emp1;
```

### drop 删除表

```sql
--删除表,数据和结构都删掉
drop table emp;

--删除表的同时，删除约束
drop table emp cascade constraints;
```

#### recyclebin 回收站

- drop并未真正删除表，而是把表重命名之后放在回收站 recyclebin。

```sql
--删除表且清空回收站中的该表
drop table emp purge;
```

##### show 查看回收站

```sql
--查看回收站中表重命名的信息
show recyclebin
```

##### purge 清空回收站

```sql
--清空回收站中指定recyclebin_name的表
purge table "recyclebin_name";
```

```sql
--清空回收站
purge recyclebin;
```

##### flashback 闪回

```sql
--闪回
flashback table emp to before drop;

--闪回并重命名
flashback table emp to before drop
rename to employees;
```

## constraint 约束

- constraint（约束）定义规则，确保完整性。

1. 若不指定约束名，ORACLE自动按sys_cn格式指定约束名。
2. 创建约束在建表同时，修改约束在建表之后。

| 约束类型                       | 命名标准         | 级别   | 说明                                                         |
| ------------------------------ | ---------------- | ------ | ------------------------------------------------------------ |
| not null<br />（非空约束 ）    | 无               | 列     | 只能用于列约束                                               |
| unique<br />（唯一性约束）     | `uk_`<br />`_uk` | 列     | 允许出现多个空值<br />超码                                   |
| primary key<br />（主键约束）  | `pk_`<br />`_pk` | 表、列 | 非空且唯一<br />一张表只能存在一个主键约束<br />主键约束可以由多个字段构成（联合主键、复合主键）。 |
| foreign key<br />（外键约束 ） | `fk_`<br />`_fk` | 表、列 | 作为外键的列，在其对应的关联表中必须是主键。<br />datatype references：仅数据类型关联。<br />`on delete|update cascade`：级联删除/更新 <br />`on delete|update set null`：置空 |
| check(条件)<br />（检查约束）  | `_c`             | 表、列 |                                                              |

- 外键约束：主表当中的字段必须是其主键，主表和从表的字段必须是相同的数据类型。向设置外键约束的表输入值时，从表中外键字段的值必须来自主表中的相应字段的值或null。

| 级别     | 表级定义表约束，列级定义列约束                         |
| -------- | ------------------------------------------------------ |
| 列级约束 | 列后面声明、必须跟在列后面<br />只作用在一个列         |
| 表级约束 | 末处声明constraint、与列分开单独定义<br />作用在多个列 |

```sql
create table table1(
    no1 number(10) not null,  --非空约束
    no2 number(10,2) unique,   --唯一性约束
    no3 number(20) check(0 < no3 <= 99),  --check约束
    no4 varchar2(25) datatype references table2(no4),  
    --外键table2为主表，table1为从表。
    constraint table1_no1_c check(1 < no1 < 9), --check约束
    constraint table1_no1_pk primary key (no1),  --主键（非空且唯一）
    constraint table1_no3_fk foreign key (no3) references table2(no1),   --外键
    constraint table1_no4_fk foreign key (no4) references table2(no4) on delete cascade, --外键，级联删除
    constraint table1_no5_fk foreign key (no5) references table2(no5) on delete set null  --外键，级联置空
);
```

```sql
--非空约束 not null
create table 表名
( 
 列名 数据类型 not null
,列名 数据类型
);
```

```sql
--唯一性约束 unique
create table 表名
(
 列1 数据类型 [constraint 约束名] unique
,列2 数据类型
);
```

```sql
--主键约束 primary key

--列级
create table 表名
(
 列1 数据类型 [constraint 约束名] primary key
,列2 数据类型
);

--表级
create table 表名
(
 列1 数据类型 
,列2 数据类型
,列3 数据类型
,[constraint 约束名] primary key(列1[,列2,...])
);
```

```sql
--外键约束 foreign key

--列级
create table 表名
(
    列1 数据类型 [constraint 约束名] [datatype] references 要关联的表(列,...)
    -- datatype 仅数据类型关联
    [on delete|update cascade]  --级联删除/更新
    [on delete|update set null] --置空
    ,列2 数据类型
);

--表级
create table 表名
(
    列1 数据类型 
    ,列2 数据类型
    ,[constraint 约束名] foreign key(列1[,列2,...])
    references 要关联的表(列,...)
    [on delete cascade]  --级联删除
    [on delete set null] --级联置空
);
```

```sql
--检查约束 check

--列级
create table 表名
(
    列1 数据类型 [constraint 约束名] check (条件表达式)
    ,列2 数据类型
    ,列3 数据类型
);

--表级
create table 表名
(
    列1 数据类型
    ,列2 数据类型
    ,列3 数据类型
    ,[constraint 约束名] check (条件表达式)
);
```

### alter table 修改表约束

1. 约束可以添加/删除，但不可以修改。
2. 有效、无效化约束。
3. 添加not null约束要用modify关键字。

#### drop constraint 删除

```sql
alter table 表
drop constraint 约束名;
```

```sql
--cascade 删除主键约束的同时，（如果存在）以该主键（作为关联表的外键）建立的外键约束也一起删除。
alter table 表
drop primary key 约束名 [cascade];   
```

#### modify 修改 

```sql
--修改不得破坏表中原有的数据。
alter table 表
modify (列名 数据类型 约束);
```

#### rename constraint 改名

```sql
alter table 表
rename constraint 旧的约束名 to 新的约束名;
```

#### add constraint 添加表约束

```sql
alter table 表
add constraint 约束;
```

```sql;
alter table t1
add constraint table_fk forreign KEY (no2) references t2(no2);
```

#### disable/enable constraint 无效化/激活约束

- 当定义/激活unique、primary key约束时，系统自动创建unique、primary key索引。

```sql
alter table 表
disable constraint 约束名;
```

```sql
alter table 表
enable constraint 约束名;
```

## default 默认值

```sql
--创建表时添加
create table emp(
 salary number(20) default 1000
);
```

```sql
--修改表时添加默认值 modify
alter table emp
modify salary number(10) default 1000;
```

## 表的类型

### 只读表 reade only

```sql
--只读
alter table tb_name reade only;

--可读写
alter table tb_name read write;
```

- 不允许在只读表上进行的操作：DML、truncate、锁住指定的数据 `select ...for update`、修改表的属性  `alter table add|modify`、把列置于不可用的状态 `alter table set column unused`、分区操作 `alter table drop|truncate|EXCHANGE [SUB]PARTITIon`、`alter table upgrade including data`、`alter type cascade including`、在线重定义、闪回表 `flashback table`。

### 临时表 global temporary table 

- 临时表：只在会话期间、事务处理期间存在的表，在插入数据时，动态分配空间。临时表的表结构一直存在，直到删除（创建一次，永久使用），默认保存在TEMP，不支持主外键。临时表可以拥有触发器、索引（只对当前会话或者事务有效）、视图。

| 选项                    | 说明                                             |
| ----------------------- | ------------------------------------------------ |
| on commit preserve rows | 会话期间表一直可以存在（保留数据）               |
| on commit delete rows   | 事务结束清除数据（在事务结束时自动删除表的数据） |

| 临时表类型 | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| 会话型     | 基于会话的临时表<br />数据从会话开始到会话结束之间是有效的，当会话结束时，表中的数据会自动清空。 <br />不同会话之间的数据是隔离的，互不影响。<br />会话型临时表在会话期间可以采用 `delete 临时表名;` 的方式清空临时表数据。<br />on commit preserve rows; |
| 事务型     | 基于事务的临时表，比会话型的临时表更灵活（优化）<br />数据的保存时间与会话型相同。<br />事务提交、事务回滚时将清空临时表中的数据。<br />on commit delete rows; |

```sql
create global temporary table temp_dept
(
    dno number, 
    dname varchar2(10)
)
on commit delete rows;

insert into temp_dept values(10,'ABC');

commit;

--无数据显示,数据自动清除
select * from temp_dept;  
```

#### 全局临时表

- 全局临时表以常规表的形式创建临时数据表的表结构（可以有 lob 列和主外键），但要在每一个表的主键中加入一个SessionID 列以区分不同的会话。
- 用户注销触发器在用户结束会话的时候删除本次会话所插入的所有记录 （SessionID 等于本次会话 ID 的记录 ）。程序写入数据时，要顺便将当前的会话ID写入表中；程序读取数据时，只读取与当前会话ID相同的记录即可。

#### with 临时查询表

- with子句将该子句执行一次并存储到用户的临时表空间中，在查询结束后，该临时表被删除。

```sql
--查询公司各部门的总工资大于公司中各部门的平均总工资的部门信息
with
dept_costs
as
(
select department_name,d.department_id,sum(salary) sum_sal
from employees e,departments d
where e.department_id = d.department_id
group by department_name,d.department_id
),
avg_cost
as
(
select sum(sum_sal)/count(*) dept_avg
from dept_costs
)

select *
from dept_costs
where sum_sal >(
                select dept_avg
                from avg_cost
                )
order by department_name
```

### 簇表 cluster

```sql
create cluster tb_cluster(postcode int)
tablespace userdb;

create table student
(
 id int primary key
,name varchar2(20) not null
,postcode int
)
cluster tb_cluster(postcode);

create table address_info
(
 postcode int primary key
,name varchar2(40)
,detail varchar2(40)
)
cluster tb_cluster(postcode);

create table students
(
 name varchar2(40) primary key
,id number
,detail varchar2(100)
)
organization index
tablespace userdb
pctthreshold 30
including detail
overflow tablespace userdb;
```

### 外部表

```sql
/*
‪F:\temtb\data\temstu.txt
‪‪50016,小张,上海,22
30011,小李,天津,24
*/

--外部表文件路径
create or replace directory dat_dir
as 'F:\temtb\data';
create or replace directory log_dir
as 'F:\temtb\log';
create or replace directory bad_dir
as 'F:\temtb\bad';
grant read on directory dat_dir to rawman;
grant read, write on directory log_dir to rawman;
grant read, write on directory bad_dir to rawman;

--创建外部表
create table fitness_member
(
    id intEGER,
    name varchar2(14),
    city varchar2(30),
    AGE int
)
organization external
(
    type oracle_loader
    default directory dat_dir
    access parameters
    (
        records delimited by newline
        badfile bad_dir:'empxt%a_%p.bad'
        logfile log_file:'empxt%a_%p.log'
        fileds terminated by ','
        missing field values are null
        (id, name, city, age)
    )
    default directory exd_dir
    location ('temstu.txt')
)
parallel
reject limit unlimited;
```

# 数据操作

```
SQL> DESC emp
name      type         nullable default Comments
--------- ------------ -------- ------- --------
ID        number(6,2)  Y
SALARY    number(10)   Y
name      varchar2(25) Y
HIRE_DATE DATE         Y
```

## insert 添加数据

- insert一次只能向表中插入一条数据。

```sql
--显式插入
insert into emp(id,salary,name,hire_date)
values(1,1500,'张三',to_DATE('2002-2-3','yyyy-mm-dd'));

--隐式插入
insert into emp
values(2,3000,'李四',to_DATE('2003-3-2','yyyy-mm-dd'));

--显式null
insert into emp
values(5,null,'赵七'，null);

--字段插入 对于没有列出的值域，则插入null
insert into emp(name,salary,hire_date,id)
values(2500,'王五',to_DATE('2004-4-5','yyyy-mm-dd'),3);
```

### & 窗口输入

- 窗口输入 ：对于日期和字符型等需要单引号的数据类型，可以在&外面加上单引号（`'&name'`），从而不用在窗口输入时使用单引号。而其他不需要单引号的数据类型则可以直接使用`&id` 。

```sql
insert into emp
values(&id,&name,&salary,&hire_date);
--5 '李四' 2000 '11-2月-1999'

insert into emp
values(&id,'&name',&salary,'&hire_date');
--5 李四 2000 11-2月-1999
```

### select 复制插入

- 相应的列数据类型要一致

```sql
insert into emp
select employee_id
      ,salary
      ,last_name
      ,hire_date 
from employees
where department_id = 90;
```

## update 更新数据 

```sql
update 表
set 列=新的列
where --没有where ，则全部更新
```

```sql
--更新114号员工的工作和工资使其和205号员工相同
update employees
set job_id = (
             select job_id
             from employees
             where employee_id = 205
             )
   ,salary = (
              select salary
              from employees
              where employee_id = 205
              )
where employee_id = 114;
```


## delete 删除数据 

```sql
delete from table_name
where  --若无where子句，则全部删除
```

```sql
--删除emp表中id为1的员工
delete from emp
where id = 1;
```

#### truncate 清空数据

| 操作     | 对比     |
| -------- | -------- |
| delete   | 可以回滚 |
| truncate | 不可回滚 |

```sql
truncate table 表;
```

## merge 合并数据

- 数据转储操作：从表中选择数据行以修改或插入到另一个表。
- merge基于on子句中的条件来决定对目标表执行的是修改还是插入操作，必须在目标表上有insert和update系统权限，源表上具有select系统权限。

```sql
merge into 表 别名  --说明正在修改或插入的目标表
using (table|view|sub_query) 别名  --标识要修改或插入的数据源（表，视图，子查询等）
on (条件) --定义merge语句是进行修改还是插入操作的条件
when matched then  --当条件满足时，执行
 update set         --执行的语句块
   col1 = col1_val,
   col2 = col2_val
when not matched then  --当条件不满足时，执行
 insert (列名)
 values (值)
```

1. update、insert可仅仅出现其一、附加where条件。（无条件insert：`on(1=1)`）
2. delete子句仅仅能够删除目标表的行而无法删除源表的行，必须存在update set，且delete字句中的where必须位于最后。
3. 不能更新on子句引用的列，且应该保证on中条件的唯一性（在源表中获得一组稳定的行）。

> on(t1.name = t2.name)：则t1和t2表中的name应该是一一对应的，而不能重复。

4. 自我更新的using若存在null，则改为count()将null改为0处理。

```sql
merge into t_bonus T
using (select employee_id, salary
       from  s_employee
      ) S
on (T.employee_id = S.employee_id)
when matched then 
  update set T.bonus = S.salary + 40
  where T.bonus > 1000
             
  delete 
  where (T.bonus > 1300)
when not matched then 
  insert into (T.employee_id, T.bonus)
  values (S.employee_id, S.salary +20)
  where (S.salary > 3000)
```

```sql
merge into T2
using (select * 
       from t2 
       where name='D') T
on (T.name=T2.name)
when matched then
 update set T2.MonEY=100
when not matched then
 insert values ('D',200);

--本来T表应该由于name=D不存在而要添加记录。可是实际却根本无变化。
SQL> select * from T2;
name                      MonEY
-------------------------------------------------------
A                            30
C                            20
--由于此时select * from t2 where name='D'为null,所以出现了无法插入的情况。

--自我更新的using若存在null，则改为count()将null改为0处理。
merge into T2
using (select count(*) CNT 
       from t2 
       where name='D') T
on (T.CNT <> 0)
when matched then
 update
 set T2.MonEY = 100
when not matched then
 insert
 values ('D',100);

SQL> select * from T2;
name                      MonEY
-------------------------------
A                            30
C                            20
D                           100
```

# view 视图

> scott用户创建视图view权限不足：使用dba授予scott用户创建视图view权限。
>
> ```sql
> grant create view to scott; 
> ```

- 视图：从表中抽出的逻辑上相关的数据集合（视图==储存的select语句）。视图是一种虚表，建立在已有的基础上（基表），当基表的数据发生变化时，视图里面的数据也会跟着发生变化。视图向用户提供基表数据的另一种表现形式，向视图提供内容的语句为select语句 。

- 视图用来控制数据访问、简化查询、避免重复访问相同的数据。

| 视图类型      | 说明                                                         |
| ------------- | ------------------------------------------------------------ |
| 连接视图      | 基于多个表建立的视图。<br />一般不会在该视图上执行insert、update、delete。 |
| 只读视图      | 允许进行select操作的视图<br />创建视图时指定with read only选项。<br />不能执行insert、update、delete。 |
| check约束视图 | 视图上定义check约束<br />该视图上执行insert或update操作时,数据必须符合查询结果。 |

## 视图操作

### create [or replace] view 创建/修改视图

- create view子句中各列的别名应和子查询中各列相对应。
- 修改视图只能重新创建视图。

| 特性     | 简单视图 | 复杂视图           |
| :------- | :------- | :----------------- |
| 表的数量 | 一个     | 一个、多个         |
| 函数     | 没有     | 有（使用别名）     |
| 分组     | 没有     | 有                 |
| DML      | 可以     | 必须要符合特定条件 |

```sql
--简单视图
create or replace view table1_view
as
select *
from table1;
```

```sql
--复杂视图:组函数一定要使用别名，否则报错。
create or replace view table1_view
as
select avg(salary) avg_sal,department_id --组函数一定要使用别名，否则报错
from employees
group by department_id;  
```

```sql
--连接视图 (table1,table2外键为column2)
create or replace view table1_view
as
select column1,t1.column2,column3
from table1 t1,table2 t2
where t1.column2(+) = t2.column2;
```

### select 查询视图

```sql
select *
from table1_view;
```

### drop view 删除视图 

- drop view只是删除视图的定义，并不会删除基表的数据。

```sql
drop view table1_view;
```

## 视图DML条件

- 用户可以通过视图对基表执行增删改操作，但是有很多在基表上的限制。

> 若基表中某列不能为空，但是该列没有出现在视图中，则不能通过视图执行insert操作。若基表设置了某些约束，这时候插入视图或者修改视图的值，有可能会报错。

1. 简单视图中可执行DML操作。
2. 视图定义中包含以下元素之一时，不能用delete语句：组函数、group by子句、distinct关键字、rownum伪例
3. 且若还包含列的定义为表达式时，不能用update语句：列的定义为表达式。
4. 且若还包含以下的元素时，不能用insert语句：表中非空的列在视图定义中未包括。

### with read only 只读视图（屏蔽DML）

- with read only：选读屏蔽，设置只读。对只读视图的任何DML操作都会返回ORACLE SEVER错误。

```sql
create or replace view table1_view(no1,no2)
as
select no1,no2
from table1
with read only;
```

## materialized 物化视图

- 物化视图（相当于一个表）将视图的查询结果存储在数据库中。使用该视图时，使用的是查询的结果，而不是查询表达式。

### create|alter materialized view 创建/修改物化视图

> 创建物化视图的权限
>
> ```sql
> grant create materialized view to 用户;
> ```

```sql
create|alter materialized view 视图名
as 
查询表达式
```

```sql
create|alter materialized view view_name
refresh [ fast|complete|force ]  --刷新方法
[
on [commit|demand ]   | --刷新模式
start with (start_time) next (next_time) --设置刷新时间
]
as 
查询表达式;
```

| 生成数据        | 说明                                     |
| --------------- | ---------------------------------------- |
| build immediate | 创建物化视图的时候就生成数据             |
| build deferred  | 创建时不生成数据，以后根据需要在生成数据 |

#### 物化视图刷新

##### 刷新模式

| 刷新模式  | 说明                                                         |
| --------- | ------------------------------------------------------------ |
| on demand | 默认，仅在该物化视图“需要”被刷新时，才进行刷新（refresh），更新物化视图，以保证和基表数据的一致性。 |
| on commit | 提交触发，一旦基表有了commit（事务提交），则立刻刷新，立刻更新物化视图，使得数据和基表一致。<br />一般用这种方法在操作基表时速度会比较慢。 |

| 刷新方法                   | 说明                                                         |
| -------------------------- | ------------------------------------------------------------ |
| complete<br />（完全刷新） | 删除表中所有的记录（如果是单表刷新，可能会采用truncate的方式），然后根据物化视图中查询语句的定义重新生成物化视图。 |
| fast<br />（快速刷新）     | 增量刷新的机制，只将自上次刷新以后对基表进行的所有操作刷新到物化视图中去。<br />fast必须创建基于主表的视图日志。<br />对于增量刷新选项，如果在子查询中存在分析函数，则物化视图不起作用。 |
| force                      | 默认，自动判断是否满足快速刷新的条件，如果满足则进行快速刷新，否则进行完全刷新。 |

> Oracle物化视图的快速刷新机制是通过物化视图日志完成的。Oracle通过一个物化视图日志还可以支持多个物化视图的快速刷新 。 物化视图日志根据不同物化视图的快速刷新的需要，可以建立为rowid、primary key类型的 ，还可以选择是否包括sequence、including new values以及指定列的列表。

###### 物化视图日志

```sql
create materialized  view log on test_table  
tablespace test_space  -- 日志空间  
with primary key ;      -- 指定为主键类型
```

```sql
--删除物化视图日志
drop materialized view log on test_table;
--删除物化视图        
drop materialized view mv_materialized_test; 
```

##### 刷新时间

```sql
create materialized view mv_materialized_test 
refresh force
on demand 
start with sysdate next to_date( concat (to_char( sysdate + 1 , 'dd-mm-yyyy' ), '10:25:00' ), 'dd-mm-yyyy hh24:mi:ss' ) 
as
select  *  from  user_info;  --这个物化视图在每天10：25进行刷新 
```

##### 查询重写（QueryRewrite）

- `enable|disable query rewrite`：指出创建的物化视图是否支持查询重写，默认disable。 查询重写是当对物化视图的基表进行查询时 ， oracle会自动判断能否通过查询物化视图来得到结果，如果可以，直接从已经计算好的物化视图中读取数据。

# 子查询

- 子查询 （内查询） 在主查询之前执行完成，其结果被主查询（外查询）使用。子查询的查询性能通常消耗较大，尽量减少使用子查询。

| 子查询返回       | 说明                     |
| ---------------- | ------------------------ |
| 未返回任何行     | 主查询也不会返回任何结果 |
| 单行结果（标量） | 使用在适用于标量的位置   |
| 多行结果（关系） | 使用在适用于关系的位置   |

## 单行子查询

- 单行子查询只能返回一行结果，不能返回空值、只能使用单行操作符 `=、>、>=、<、<=、<>`。


```sql
--显示员工的employee_id,last_name和location。
--其中，若员工department_id与location_id为1800的department_id相同，则location为’Canada’,其余则 为’USA’。

--1.1 case --在case中使用单列子查询
select employee_id
      ,last_name
      ,department_id
      ,(case when department_id = (
                                   select department_id
                                   from departments
                                   where location_id = 1800
                                  )
                 then 'Canada'
             ELSE 'USA'
        END) location
from employees
order by department_id;

--1.2 case --在case中使用单列子查询
select employee_id
      ,last_name
      ,department_id
      ,(case department_id when  (
                                  select department_id
                                  from departments
                                  where location_id = 1800
                                  )
                                then 'Canada'
                           ELSE 'USA'
END) location
from employees
order by department_id;

--2. decode
select employee_id
      ,last_name
      ,decode( department_id
              ,(
                select department_id
                from departments
                where location_id = 1800
                )
              ,'Canada'
              ,'USA'
             ) location
from employees
order by location DESC;
```

## 多行子查询

| 多行操作符     | 描述                                           |
| :------------- | :--------------------------------------------- |
| in<br />exists | 等号列表中任意一个/关系中是否存在满足条件的行  |
| any<br />some  | 和子查询返回的任意一个值比较，只要一个满足即可 |
| all            | 和子查询返回的所有值比较，需要满足所有         |


```sql
--返回job_id和141号员工相同，salary比143号员工多的姓名，job_id工资
select last_name
      ,salary
      ,job_id
from employees
where job_id = (
                select job_id
                from employees
                where employee_id = 141
                )
AND salary > (
              select salary
              from employees
              where employee_id = 143
              );
```

```sql
--返回其他部门中比job_id为'IT_PROG'部门任一工资低的员工的员工号，姓名，job_id和salary
select employee_id
      ,last_name
      ,job_id
      ,salary
from employees
where salary < ANY(
                   select salary
                   from employees
                   where job_id = 'IT_PROG'
                   );
```


```sql
--查找部门中工资高于部门平均工资的员工
--1)
select last_name
      ,department_id
      ,salary
      ,(
        select avg(salary)
        from employees e
        where e1.department_id = e.department_id
        group by department_id
        ) avg_sal
from employees e1
where salary > (
                select avg(salary)
                from employees e2
                where e1.department_id = e2.department_id
                group by department_id
                );

--2)
select a.last_name
      ,a.salary
      ,a.department_id
      ,b.avg_sal
from employees a,(
                  select department_id
                        ,avg(salary) avg_sal
                  from employees
                  group by department_id
                  ) b
where a.department_id = b.department_id
AND a.salary > b.avg_sal;
```

### in、 exists

#### in

- 以下两个查询等价：

```sql
select name 
from student 
where name in ('zhang','wang','li','zhao');
```

```sql
select name 
from student 
where name = 'zhang' 
  or name = 'li' 
  or name = 'wang' 
  or name= 'zhao';
```

#### exists 

**检查规则**

1. 如果在子查询中存在满足条件的行，不在子查询中继续查找，条件返回TRUE。
2. 如果在子查询中不存在满足条件的行，条件返回FALSE，继续在子查询中查找。

```sql
--查询公司管理者的employee_id,last_name,job_id,department_id
select employee_id
      ,last_name
      ,job_id
      ,department_id
from employees OUTER
where exists(
    select *
    from employees
    where manager_id = OUTER.employee_id
);
```

**not exists**

- 检查在子查询中是否存在不满足条件的行

```sql
--查询departments表中不存在与employees表的部门的department_id,department_name
select department_id
      ,department_name
from departments d
where not exists(
    select 'x'  --'x'可以是任何
    from employees
    where department_id = d.department_id
);
```

#### in 和 exists 区别

- 如果**子查询**得出的结果集记录较少，主查询中的表较大且又有索引时应该用**in**,  
- 如果**外层**的主查询记录较少，子查询中的表大，又有索引时使用**exists**。  
- 另外in不对null进行处理。

**区分in和exists主要是驱动顺序的改变（这是性能变化的关键）**

- 如果是exists，那么外层表为驱动表，先被访问。如果是in，那么先执行子查询。
- **in是把外表和内表作hash join，而exists是对外表作LOOP**，每次LOOP再对内表进行查询

```sql
select * 
from T1 
where x in ( select y 
             from T2 
            )
--执行的过程相当于:
select*
from t1
    ,( 
      select distinct y 
      from t2 
      ) t2
where t1.x = t2.y;
```

```sql
select * 
from t1 
where exists ( 
              select null 
              from t2 
              where y = x 
              )
--执行的过程相当于:
for x in ( select * 
           from t1 
          )
   loop
      if ( exists ( select null 
                    from t2 
                    where y = x.x 
                  )
      then
         OUTPUT THE RECORD
      end if
end loop
--表 T1 不可避免的要被完全扫描一遍
```

- 带in的关联子查询是多余的，因为in子句和子查询中相关的操作的功能是一样的。  如：

```sql
--查找员工中在部门的员工姓名
select staff_name 
from staff_member 
where staff_id in (select staff_id 
                   from staff_func 
                   where staff_member.staff_id = staff_func.staff_id
                  );
```

改为：

```sql
select staff_name 
from staff_member 
where staff_id in (select staff_id 
                   from staff_func
                  );
```

- 为非关联子查询指定exists子句是不适当的，因为这样会产生笛卡乘积。  如：

```sql
select staff_name 
from staff_member 
where staff_id exists (select staff_id 
                       from staff_func
                       );
```

改为：

```sql
select staff_name 
from staff_member 
where staff_id exists (select staff_id 
                       from staff_func 
                       where staff_member.staff_id = staff_func.staff_id
                      );
```

#### not in 和 not exists

- 如果查询语句使用了**not in 那么内外表都进行全表扫描，没有用到索引**；  而**not extsts 的子查询依然能用到表上的索引**。  所以无论哪个表大，用not exists都比not in要快。  
- **尽量不要使用not in子句**。使用minus 子句都比not in 子句快，虽然使用minus子句要进行两次查询：  

```sql
select staff_name 
from staff_member 
where staff_id in (select staff_id 
                   from staff_member 
                   minus 
                   select staff_id 
                   from staff_func 
                   where func_id like '81%'
                   );
```

### group by多字段分组和from子查询的区别

- 即：此时 emp 表之中存在有 14000 条数据，dept 表中存在有 4000 条数据。  多表查询一定会产生笛卡儿积  

**多字段分组**：积的数量：emp 的 14000 条*dept 的 4000 条 = 56,000,000 条数据；

**from子查询**：

- 第一步（内嵌子查询）：针对于 emp 表查询，最多操作 14000 条记录，最多返回 4000 条记录；
- 第二步，子查询和 dept 表关联；
- 积的数量：dept 表的 4000 条* 子查询返回的最多 4000 条 = 16000000 条记录；
- 总的数据量：16000000 + 14000 = 16,014,000 条记录

## 子查询中使用主查询的列

```sql
--查询员工中工资大于部门平均工资的员工last_name,salary和department_id
select last_name
      ,salary
      ,department_id
from employees outer
where salary > (
                select avg(salary)
                from employees
                where department_id = out.department_id
                );
```

```sql
--若employees表中的employee_id与job_history中employee_Id相同的数目不小于2，输出这些相同的员工的employee_id,job_id
select e.employee_id
      ,last_name
      ,e.job_id
from employees e
where 2 <= (
            select count(*)
            from job_hitoryy
            where employee_id = e.employee_id
            );
```

## 相关子查询

### 相关查询

- 在where子句中，可以使用子句中关系的别名。
- 而在from子句中，则不能使用在当前子句中的别名。

### 相关更新 

- 使用相关子查询依据一个表中的数据更新/修改另一个表中的数据

```sql
alter table employees
add(department_name varchar2(14));
```

```sql
update emlpoyees e
set department_name = (
                       select department_name
                       from departments d
                       where e.departmet_id = d.department_id
                       );
```

### 相关删除

```sql
-- 删除employees表中，基于emp_history表皆有数据
delete from employees e
where (
       select employee_id
       from emp_history
       where employee_id = e.employee_id
       );  
```

## 成对比较 

- 多列子查询 主查询与子查询返回的多个列进行比较
- 成对比较

```sql
--查询与141号或174号员工的manager_id,department_id相同的其他员工信息

--1)成对比较
select employee_id
      ,manager_id
      ,department_id
from employees
where (manager_id,department_id) in (
                                     select manager_id
                                           ,department_id
                                     from employees
                                     where employee_id in (141,174)
                                     )
AND employee_id not in  (141,174);

--2)不成对比较
select employee_id
      ,manager_id
      ,department_id
from employees
where manager_id in (
                     select manager_id
                     from employees
                     where employee_id in (141,174)
                     )
  AND department_id in (
                        select department_id
                        from employees
                        where employee_id in (141,174)
                        )
  AND employee_id not in (141,174);
```

# join 连接

- 连接分为内连接（常规连接）和外连接。

## 内连接

**用处**

- 合并具有同一列的两个以上的表的行
- 结果集不包含一个表与另一个表不匹配的行

**解释**

- 自连接 SELF join ; inNER join (正常select) 不适合查询大表，用来比较表中的行，或查询分层数据
- 通过别名，将同一张表视为多张表
- 连接的表是同一张表，使用自连接可以将自身表的一个镜像当作另一个表来对待，从而能够得到一些特殊的数据。

**语法**

```sql
select t1.no1,no2,no3
from table1
inNER join table1 t1 on no1;
```

```sql
--查询所有雇用日期相同(同一天入职)的员工
select e1.employee_id,e1.hire_date
from employees e1
 inNER join employees e2 
  on e1.employee_id <> e2.employee_id
  AND e1.hire_date = e2.hire_date;
```

### natural join

- 全自动连接(自然连接) NATURAL join
- 只返回在两个关系中的都拥有的相同属性的相同元组。

1. 如果做自然连接的两个表的有多个字段都满足有<mark>相同名称和相同数据类型（系列）</mark>，那么他们会被作为自然连接的条件。
2. 如果自然连接的两个表仅是字段名称相同，但数据类型不同，那么将会返回一个错误。
3. 在设计表时，应该尽量在不同表中具有相同含义的字段使用相同的名字和数据类型。以便使用NATURAL join

```sql
select no1,no2,no4
from tabel1
NATURAL join table2;
```

```sql
select department_id,
       department_name
from employees
NATURAL join departments;
```

- 注：`NATURAL join`的select子句中的列，不能有限定词，
  - 如`employees.department_id`
  - 其他连接都需要限定词


### join using

- 使用同名列的连接 join .. using .. 
- using多表连接时，若列名不同，则不适用，类似于natural join的扩展。

```sql
select t1.no1,no2,no4
from table1 t1
 join table2 using (no1);
```

### join on

- 等值连接 join on

**1. [inNER] join**

- join子句可以视为from子句的一部分

```sql
select t1.no1,no2,no4
from table1 t1
 join table2 t2 on t1.no1 = t2.no1
[where...];
```

**2. where**

```sql
select t1.no1,no2,no4
from table1 t1,table2 t2
where t1.no1 = t2.no1;
```

**例：两表连接**

```sql
--1.join
select distinct e.department_id,d.department_id
from employees e
join departments d
on e.department_id = d.department_id;

--2.where
select distinct e.department_id,d.department_id
from employees e
    ,departments d
where e.department_id = d.department_id;
```

**例：三表连接**

- 注意：需要中间表连接。 

```sql
--1.join
select employee_id,e.department_id,department_name,d.location_id,city
from employees e
  join departments d on e.department_id = d.department_id
  join locations l on d.location_id = l.location_id; 
  --department表中的department_id和location_id

--2. where
select employee_id,e.department_id,department_name,d.location_id,city
from employees e,departments d,locations l
where e.department_id = d.department_id
  AND d.location_id = l.location_id;
```

## 外连接  

### 左外连接 LEFT [OUTER] join 

- 当连接条件不成立的时候，等号左边的表仍然被包含
  - 即可以包含from子句中的表与on中的表不匹配的值 

```sql
--可以返回左表(table1)中与右表(table2)不匹配的值
select t1.no1,no2,no4
from table1 t1,table2,t2
 LEFT OUTER join table2 on t1.no1 = t2.no1;
```

### 右外连接 RIGHT [OUTER] join 

- 当连接条件不成立的时候，等号右边的表仍然被包含

```sql
--可以返回右表(table2)中与左表(table1)不匹配的值
select t1.no1,no2,no4
from table1 t1
 RIGHT OUTER join table2 on t1.no1 = t2.no1;
```

### 全连接 FULL [OUTER] join 

- 查询结果等于左外连接和右外连接的和

```sql
select t1.no1,no2,no4
from table1 t1
 FULL OUTER join table2 on t1.no1 = t2.no1;
--可以返回左右表中不匹配的行
```

### 外连接运算符(+)

- 在连接条件中无匹配行的表的列后面加(+)
- 两表在连接中除了内连接，还包含返回左(右)表中不满足条件的行，称为左(右)内连接
- 没有匹配的行时，结果表中相对应的列为空

**哪边无加号+就是什么连接**

```sql
--左外连接
select t1.no1,no2,no4
from table1 t1,table2 t2
where t1.no1 = t2.no1(+);  

--右外连接
select t1.no1,no2,no4
from table1 t1,table2 t2
where t1.no1(+) = t2.no1;
```

### 交叉连接 CROSS join 不带on子句

- 返回被连接的两个表所有数据行的笛卡尔积，
- 返回到结果集合中的数据行数等于第一个表中符合查询条件的数据行数乘以第二个表中符合查询条件的数据行数.

```sql
select t1.no1,no2,no4
from table1 t1
CROSS join table2;
```

# 常用函数

## 数值函数

### ROUND(n[,m]) 四舍五入 

- 传回一个数值，该数值是按照指定的小数位元数进行四舍五入运算的结果。
  - 省略m：0  
  - m>0:小数点后m位;
  - m<0:小数点前m位

```sql
select ROUND( number, [ decimal_places ] ) 
from DUAL

/*
参数:
number : 欲处理数值
decimal_places : 四舍五入 , 小数取几位 ( 预设为 0 )
*/
```

```sql
select ROUND(4.5),ROUND(2.3),ROUND(2.12,2)
from dual;

ROUND(4.5) ROUND(2.3) ROUND(2.12,2)
---------- ---------- -------------
         5          2          2.12
```

```sql
select round(123.456, 0) 
from dual;          
--回传 123
```

```sql
select round(123.456, 1) 
from dual;          
--回传 123.5
```

```sql
select round(-123.456, 2) 
from dual;        
--回传 -123.46
```

### 取整函数 

#### CEIL() 天花板 取最大值(向上取整) 

- ceil(n) 取大于等于数值n的最小整数

#### FLOOR() 地板 取最小值(向下取整)

- floor(n)取小于等于数值n的最大整数

```
select CEIL(23.45),FLOOR(23.45) 
from dual;

CEIL(23.45) FLOOR(23.45)
----------- ------------
         24           23
```

```
--对于每个员工，显示其加入公司的天数
select floor(sysdate-hiredate) "入职天数"
      ,ename 
from emp;

--等价于

select trunc(sysdate-hiredate) "入职天数"
      ,ename 
from emp;
```

#### Oracle计算时间差

Oracle计算时间差表达式

```
--获取两时间的相差豪秒数
select ceil((to_date('2008-05-02 00:00:00' , 'yyyy-mm-dd hh24-mi-ss') - 
             to_date('2008-04-30 23:59:59' , 'yyyy-mm-dd hh24-mi-ss')) * 24 * 60 * 60 * 1000
           ) 相差豪秒数 
from DUAL;

/*
相差豪秒数

----------

  86401000

1 row selected
*/
```

```
--获取两时间的相差秒数

select ceil((to_date('2008-05-02 00:00:00' , 'yyyy-mm-dd hh24-mi-ss') - 
             to_date('2008-04-30 23:59:59' , 'yyyy-mm-dd hh24-mi-ss')) * 24 * 60 * 60
           ) 相差秒数 
from DUAL;

/*
相差秒数

----------

     86401

1 row selected
*/
```

```
--获取两时间的相差分钟数

select ceil(((to_date('2008-05-02 00:00:00' , 'yyyy-mm-dd hh24-mi-ss') - 
              to_date('2008-04-30 23:59:59' , 'yyyy-mm-dd hh24-mi-ss'))) * 24 * 60
           )  相差分钟数 
from DUAL;

/*
相差分钟数

----------

      1441

1 row selected
*/
```

```
--获取两时间的相差小时数

select ceil((to_date('2008-05-02 00:00:00' , 'yyyy-mm-dd hh24-mi-ss') - 
             to_date('2008-04-30 23:59:59' , 'yyyy-mm-dd hh24-mi-ss')) * 24
           )  相差小时数 
from DUAL;

/*
```

```
相差小时数

----------

        25

1 row selected

*/

```

```
--获取两时间的相差天数

select ceil((to_date('2008-05-02 00:00:00' , 'yyyy-mm-dd hh24-mi-ss') - 
             to_date('2008-04-30 23:59:59' , 'yyyy-mm-dd hh24-mi-ss'))
             )  相差天数 
from DUAL;

/*

相差天数

----------

         2

1 row selected

*/
```

```
--获取两时间月份差

select (EXTRACT(year from to_date('2009-05-01','yyyy-mm-dd')) - EXTRACT(year from to_date('2008-04-30','yyyy-mm-dd'))) * 12 + 

        EXTRACT(month from to_date('2008-05-01','yyyy-mm-dd')) - EXTRACT(month from to_date('2008-04-30','yyyy-mm-dd')) months

from dual;

/*

MonTHS

----------

        13

1 row selected
```

*/

```
--获取两时间年份差

select EXTRACT(year from to_date('2009-05-01','yyyy-mm-dd')) - EXTRACT(year from to_date('2008-04-30','yyyy-mm-dd')) years from dual;

/*

YEARS

----------

         1

1 row selected

*/
```


### 常用计算

| 函数       | 说明         |
| :--------- | :----------- |
| ABS(n)     | 取绝对值     |
| MOD(m,n)   | 取模 m%n     |
| POWER(m,n) | 取m的n次幂   |
| SQRT(n)    | 求平方根     |
| SIGN(n)    | 返回值的符号 |

### 三角函数 提供弧度参数


| 函数           | 说明 |
| :------------- | :--- |
| Sin(n) ASin(n) |      |
| COS(N) ACOS(n) |      |
| TAN(n) ATAN(n) |      |


### 随机

#### DBMS_RANDOM.VALUE(n,m) 随机数 

- 生成一个指定范围[n,m)的38位随机小数（小数点后38位），若不指定范围则默认为范围为[0,1)的随机数。

```sql
select DBMS_RANDOM.VALUE
from dual;

select TRUNC(DBMS_RANDOM.VALUE(10,50),0)
from dual;
```

#### DBMS_RANDOM.STRinG('c',n) 随机字符串

- 生成一个长度为n的随机字符串。

```sql
select DBMS_RANDOM.STRinG('A',20)
from dual
```

## 字符函数

### 大小写转换函数

| 函数          | 说明       |
| :------------ | :--------- |
| UPPER(char)   | 转换为大写 |
| LOWER(char)   | 转换为小写 |
| inITCAP(char) | 首字母大写 |

```sql
select UPPER('hello'),LOWER('HELLO'),inITCAP('hELlo')
from dual;

UPPER('HELLO') LOWER('HELLO') inITCAP('HELLO')
-------------- -------------- ----------------
HELLO          hello          Hello
```

```sql
select employee_id,salary
from employees
where LOWER(last_name) = 'king'
```

### SUBSTR(char,[m[,n]])  获取子字符

- char：字符串
- m 子字符串的起始位置，m从1开始,如果m写0也是从第一个字符开始。如果m为负数时，从字符串的尾部开始截取。
- n 取出多少位,而不是取到哪一位，n省略时，从m的位置截取到结束，

```sql
select SUBSTR('hello',2,3)
from dual;

SUBSTR('HELLO',2,3)
-------------------
ell
```

```sql
select employee_id,last_name,salary
from employees
where LOWER(SUBSTR(last_name,0)) = 'king'; --即where LOWER(last_name) = 'king';  --LIKE 模糊查询类似作用
```

### LENTH(char) 获取字符串长度函数 

- 获取的长度会包含空格的长度 

### ConCAT(char1,char2)  字符串连接函数 

- 与||作用一样

```sql
select ConCAT('hello ','world')
from dual;
```

```sql
select ConCAT(first_name,last_name)
from employees;
```

### inSTR(c1,c2) 获取c2在c1中的位置

```sql
select inSTR('hello','h')
from dual;
```

### 填充函数

#### LPAD(c1,n,c2) 左填充

- 用c2从左往右补满(n-LENTH(c1))空位

```sql
select LPAD(salary,10,'*')
from employees;
```

#### RPAD(c1,n,c2) 右填充

- 用c2从左往右补满(n-LENTH(c1))空位

### 去除字符串函数

#### TRIM(c2 from c1) 首尾均去除

- 要求首/尾是要去除的字符代表从c1中去除c2字符串。就是子文本替换，要求c2中只能是一个字符

```sql
select TRIM('e' from 'hello') --结果不变
from dual;

TRIM('E'from'HELLO')
--------------------
hello
```

```sql
select trim(leading 9 from 9998767999) s1,
       trim(trailing 9 from 9998767999) s2,
       trim(9 from 9998767999) s3 
from dual;
```

#### TRIM(c1) 去空

- 代表去除首尾的空格，删首尾空，LTRIM和RTRIM只有一个参数时同理

```sql
select TRIM('   hello    ')
from dual;
```

#### LTRIM(c1[,c2]) 左去除

- 从c1中去除c2，从左边开始去除，要求第一个就是要去除的字符，有多少个重复的该字符就会去除多少次
- 默认去除左边的空格

```sql
select LTRIM('h','hello')
from dual;

LTRIM('H','HELLO')
--------------------
ello
```

#### RTRIM(c1[,c2]) 右去除

- 从c1中去除c2，要求右侧第一个就是要去除的字符，有多少个重复的该字符就会去除多少次
- 默认去除右边的空格


#### REPLACE(char,s_string[,r_string]) 字符串替换函数，省略第三个参数则用空白替换

```
select REPLACE('hello','el','lll')
from dual;

REPLACE('HELLO','EL','LLL')
------------------------
helllo
```

#### TRANSLATE(c1,c2,c3) 字符替换

- 将c1中的c2字符替换为c3字符中
- 如果c3的字符长度大于c2的字符长度：替换为c3中与c2字符长度相对应的子字符。
- 如果c3的字符长度小于c2的字符长度：仍然是将c2替换为c3。

```sql
TRANSLATE('ABC','C','XX') 
'ABC' --要被替换的字符串
'AC' --''内的所有字符被一一替换，不是字符串'AC'而是替换'A''C';
'XX' --要替换成的字符与'AC'一一对应
```

```sql
select translate('abc','b','xx') from dual; -- x是1位
--返回axc
select translate('abc','bc','xx') from dual;
--axx
select translate('abc','bc','x') from dual;
--ax
```

```sql
select TRANSLATE('abcd','acd','#')
from dual;
--#B
```

##### 统计某个字符出现次数

```sql
select LENGTH(TRANSLATE('abcdbbb','b' || 'abcdbbb','b'))
from dual

--而不是
select LENGTH('abcdbbb') - LENGTH(TRANSLATE('abcdbbb','b',''))
from dual; --返回空值 
```




```sql
insert into mytable 
values chr(38);
```

- ASCII

```sql
select ASCII('x'), ASCII('y'),ASCII('z') 
from dual;
```

### ASCII码相关函数

#### Ascii值 返回字符串首字的Ascii值

- ASCII()函数表示返回指定字符的ASCII码，作用和 CHR() 相反。

```sql
select ascii('a') from dual
```

#### chr() 返回ascii值对应的字符 

- CHR() 函数表示返回指定 ASCII 码的字符，作用和 ASCII() 相反。

```sql
chr(9);  tab
chr(10);  换行符
chr(13);  回车符
chr(32);  空格符
chr(34;，  双引号“"”
```

```sql
select chr(97) from dual
```

## 日期函数

- 日期不允许加法，可以相减
- 系统时间 sysdate 默认格式：DD-Mon-RR 天-月-年

### add_MonTHS(date,i)  用于添加指定的月份，返回在指定的日期上添加的月份

i可以是任意整数，如果i是负数，则是在原有的值上减去该月份了

```
select add_MonTHS(sysdate,1)
from dual;
```

### next_DAY(date,char)  第二个参数指定星期几，在中文环境下输入星期X即可，返回下一个周几是哪一天

```
select next_DAY(sysdate,'星期二')
from dual;
```

### LAST_DAY(date) 用于返回日期所在月的最后一天

```
select LAST_DAY(sysdate)
from dual;
```

### MonTHS_BETWEEN(date1,date2) 计算两个日期之间间隔的月份，前者减后者

```
select MonTHS_BETWEEN('20-5月-15','10-1月-15') 
from dual;
```

### EXTRACT(date from datetime) 返回相应的日期部分

```
select EXTRACT(year from sysdate)  --可以改month或者day
from dual; 
```

```
select EXTRACT(hour from timestamp '2015-10-1 17:25:13') 
from dual;
```

### 应用

#### 1. 显示Two Hundred Twenty-Two  

```
select to_char( to_date(222,'J'),'Jsp') 
from dual     
```

***

#### 2.求某天是星期几

```
--星期一     
select to_char(to_date('2002-08-26','yyyy-mm-dd'),'day') 
from dual;     
--monday
```

```
select to_char(to_date('2002-08-26','yyyy-mm-dd'),'day','NLS_DATE_LANGUAGE = American') 
from dual;     
```

***

#### 3. 设置日期语言  NLS_DATE_LANGUAGE

##### 1)修改会话

```
alter SESSIon set NLS_DATE_LANGUAGE='AMERICAN';     
```

##### 2)参数

```
to_DATE ('2002-08-26', 'YYYY-mm-dd', 'NLS_DATE_LANGUAGE = American')    
```

***

#### 4. 两个日期间的天数   相减返回天数

```
select floor(sysdate - to_date('20020405','yyyymmdd'))   
from dual;    
```

***

#### 5. 时间为null的用法

```
select id
      ,active_date 
from table1     
UNIon     
select 1
       ,to_DATE(null) 
from dual;     
--注意要用to_DATE(null)    
????
```

#### 6.月份差  

```
a_date between to_date('20011201','yyyymmdd') and to_date('20011231','yyyymmdd')     

那么12月31号中午12点之后和12月1号的12点之前是不包含在这个范围之内的。     
所以，当时间需要精确的时候，觉得to_char还是必要的
```

#### 7. 日期格式冲突问题

输入的格式要看你安装的ORACLE字符集的类型, 比如: US7ASCII, date格式的类型就是: '01-Jan-01'

```
(1)
alter system set NLS_DATE_LANGUAGE = American     
(2)
alter session set NLS_DATE_LANGUAGE = American     
(3) 在to_date中写     
select to_char(to_date('2002-08-26','yyyy-mm-dd'),'day','NLS_DATE_LANGUAGE = American') 
from dual;     
(4)
注意我这只是举了NLS_DATE_LANGUAGE，当然还有很多，     
可查看:     
select * from nls_session_parameters     
select * from V$NLS_parameters    
```

#### 8

```
   select count(*)     
   from ( select rownum-1 rnum     
       from all_objects     
       where rownum <= to_date('2002-02-28','yyyy-mm-dd') - to_date('2002-     
       02-01','yyyy-mm-dd')+1     
      )     
   where to_char( to_date('2002-02-01','yyyy-mm-dd')+rnum-1, 'D' )     
        not in ( '1', '7' )     
  
   查找2002-02-28至2002-02-01间除星期一和七的天数     
   在前后分别调用DBMS_UTILITY.GET_TIME, 让后将结果相减(得到的是1/100秒, 而不是毫秒).    
```

#### 9. 查找月份

```
    select months_between(to_date('01-31-1999','MM-DD-YYYY'),to_date('12-31-1998','MM-DD-YYYY')) "MonTHS" 
    from DUAL;     
    --1     
   
    select months_between(to_date('02-01-1999','MM-DD-YYYY'),to_date('12-31-1998','MM-DD-YYYY')) "MonTHS" 
    from DUAL;     
    --1.03225806451613
```

#### 10. next_day的用法

```
    next_day(date, day)     
   
    Monday-Sunday, for format code DAY     
    Mon-Sun, for format code DY     
    1-7, for format code D    
```

#### 11

```
   select to_char(sysdate,'hh:mi:ss') TIME from all_objects     
   
   注意：第一条记录的TIME 与最后一行是一样的     
```

```
   可以建立一个函数来处理这个问题     
   create or replace function sys_date return date is     
   begin     
   return sysdate;     
   end;     
```

```
   select to_char(sys_date,'hh:mi:ss') from all_objects;  
```

#### 12.获得小时数

extract()找出日期或间隔值的字段值

```
    select EXTRACT(HOUR from TIMESTAMP '2001-02-16 2:38:40') 
    from offer;
         
    SQL> select sysdate ,to_char(sysdate,'hh') from dual;     
   
    SYSDATE to_CHAR(SYSDATE,'HH')     
    -------------------- ---------------------     
    2003-10-13 19:35:21 07     
   
    SQL> select sysdate ,to_char(sysdate,'hh24') from dual;     
   
    SYSDATE to_CHAR(SYSDATE,'HH24')     
    -------------------- -----------------------     
    2003-10-13 19:35:21 19    
```

#### 13.年月日的处理

```
   select older_date
         ,newer_date
         ,years
         ,months
         ,abs(trunc(     
                    newer_date-     
                    add_months( older_date, years * 12 + months )     
                    )     
          ) days
   from ( select trunc(months_between( newer_date, older_date ) / 12) YEARS
                ,mod(trunc(months_between( newer_date, older_date )),12 ) MonTHS
                ,newer_date
                ,older_date     
          from (
                select hiredate older_date
                     , add_months(hiredate,rownum) + rownum newer_date     
                from emp
                )     
        )    
```

#### 14.处理月份天数不定的办法

```
select to_char(add_months(last_day(sysdate) +1, -2), 'yyyymmdd')
      ,last_day(sysdate) 
from dual    
```

#### 16.找出今年的天数

```
select add_months(trunc(sysdate,'year'), 12) - trunc(sysdate,'year') 
from dual    

闰年的处理方法     
to_char( last_day( to_date('02' || :year,'mmyyyy') ), 'dd' )     
如果是28就不是闰年    
```

#### 17.yyyy与rrrr的区别

```
   'YYYY99 to_C     
   ------- ----     
   yyyy 99 0099     
   rrrr 99 1999     
   yyyy 01 0001     
   rrrr 01 2001    
```

#### 18.不同时区的处理

```
   select to_char( NEW_TIME( sysdate, 'GMT','EST'), 'dd/mm/yyyy hh:mi:ss') ,sysdate     
   from dual;    
```

#### 19.5秒钟一个间隔

```
   select to_DATE(FLOOR(to_CHAR(sysdate,'SSSSS')/300) * 300,'SSSSS') ,to_CHAR(sysdate,'SSSSS')     
   from dual    

   2002-11-1 9:55:00 35786     
   SSSSS表示5位秒数    
```

#### 20.一年的第几天

```
   select to_CHAR(SYSDATE,'DDD'),sysdate from dual
       
   310 2002-11-6 10:03:51    
```

#### 21.计算小时,分,秒,毫秒

```
    select     
     Days,     
     A,     
     TRUNC(A*24) Hours,     
     TRUNC(A*24*60 - 60*TRUNC(A*24)) minutes,     
     TRUNC(A*24*60*60 - 60*TRUNC(A*24*60)) Seconds,     
     TRUNC(A*24*60*60*100 - 100*TRUNC(A*24*60*60)) mSeconds     
    from     
    (     
     select     
     trunc(sysdate) Days,     
     sysdate - trunc(sysdate) A     
     from dual     
   )    


   select * from tabname     
   order by decode(mode,'FIFO',1,-1)*to_char(rq,'yyyymmddhh24miss');     
  
   //     
   floor((date2-date1) /365) 作为年     
   floor((date2-date1, 365) /30) 作为月     
   d(mod(date2-date1, 365), 30)作为日.
```

#### 23.next_day函数      返回下个星期的日期,day为1-7或星期日-星期六,1表示星期日

```
   next_day(sysdate,6)是从当前开始下一个星期五。后面的数字是从星期日开始算起。     
   1 2 3 4 5 6 7     
   日 一 二 三 四 五 六   
```

```  
   select    (sysdate-to_date('2003-12-03 12:55:45','yyyy-mm-dd hh24:mi:ss'))*24*60*60 from ddual
   日期 返回的是天 然后 转换为ss
```

#### 24,round[舍入到最接近的日期](day:舍入到最接近的星期日)

```
   select sysdate S1,
   round(sysdate) S2 ,
   round(sysdate,'year') YEAR,
   round(sysdate,'month') MonTH ,
   round(sysdate,'day') DAY 
   from dual
```

#### 25,trunc[截断到最接近的日期,单位为天] ,返回的是日期类型

```
   select sysdate S1,                    
     trunc(sysdate) S2,                 //返回当前日期,无时分秒
     trunc(sysdate,'year') YEAR,        //返回当前年的1月1日,无时分秒
     trunc(sysdate,'month') MonTH ,     //返回当前月的1日,无时分秒
     trunc(sysdate,'day') DAY           //返回当前星期的星期天,无时分秒
   from dual
```

#### 26,返回日期列表中最晚日期

```
select greatest('01-1月-04','04-1月-04','10-2月-04') from dual
```

#### 27.计算时间差

注:oracle时间差是以天数为单位,所以换算成年月,日

```
select floor(to_number(sysdate-to_date('2007-11-02 15:55:03','yyyy-mm-dd hh24:mi:ss'))/365) as spanYears from dual        //时间差-年
select ceil(moths_between(sysdate-to_date('2007-11-02 15:55:03','yyyy-mm-dd hh24:mi:ss'))) as spanMonths from dual        //时间差-月
select floor(to_number(sysdate-to_date('2007-11-02 15:55:03','yyyy-mm-dd hh24:mi:ss'))) as spanDays from dual             //时间差-天
select floor(to_number(sysdate-to_date('2007-11-02 15:55:03','yyyy-mm-dd hh24:mi:ss'))*24) as spanHours from dual         //时间差-时
select floor(to_number(sysdate-to_date('2007-11-02 15:55:03','yyyy-mm-dd hh24:mi:ss'))*24*60) as spanminutes from dual    //时间差-分
select floor(to_number(sysdate-to_date('2007-11-02 15:55:03','yyyy-mm-dd hh24:mi:ss'))*24*60*60) as spanSeconds from dual //时间差-秒
```

#### 28.更新时间

注:oracle时间加减是以天数为单位,设改变量为n,所以换算成年月,日

```
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate+n*365,'yyyy-mm-dd hh24:mi:ss') as newTime from dual        //改变时间-年
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),add_months(sysdate,n) as newTime from dual                                 //改变时间-月
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate+n,'yyyy-mm-dd hh24:mi:ss') as newTime from dual            //改变时间-日
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate+n/24,'yyyy-mm-dd hh24:mi:ss') as newTime from dual         //改变时间-时
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate+n/24/60,'yyyy-mm-dd hh24:mi:ss') as newTime from dual      //改变时间-分
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate+n/24/60/60,'yyyy-mm-dd hh24:mi:ss') as newTime from dual   //改变时间-秒
```

#### 29.查找月的第一天,最后一天

```
select Trunc(Trunc(SYSDATE, 'MonTH') - 1, 'MonTH') First_Day_Last_Month,
       Trunc(SYSDATE, 'MonTH') - 1 / 86400 Last_Day_Last_Month,
       Trunc(SYSDATE, 'MonTH') First_Day_Cur_Month,
       LAST_DAY(Trunc(SYSDATE, 'MonTH')) + 1 - 1 / 86400 Last_Day_Cur_Month
from dual;
```


## 转换函数

### to_char 转为字符串

#### to_CHAR(date[,fmt[,params]]) 日期转为字符串

- date为需要转换的日期，fmt为转换的格式(不区分大小写)，params为转换的语言（通常默认会自动选择，可以省略，与安装语言一致）  

**fmt参数格式**

- 默认格式：DD-Mon-RR   
- 格式有：  以02-02-1997为例

| 时域   | fmt参数 | 说明                   |
| :----- | :------ | :--------------------- |
| 年     | YY      | 97                     |
|        | YYYY    | 1997                   |
|        | YEAR    | NintENN AND NITY SEVEN |
| 月     | MM      | 02                     |
|        | Mon     | JUL                    |
|        | MonTH   | JULY                   |
| 日     | DD      | 02                     |
|        | DY      | Mon                    |
|        | DAY     | MonDAY                 |
| 时     | HH24    |                        |
|        | HH12    |                        |
| 分     | MI      |                        |
| 秒     | SS      |                        |
| 上下午 | AM      | 上午/下午              |

- 用双引号""向日期函数添加字符,日期要用单引号''括起

```sql
select to_CHAR(sysdate,'yyyy"年"mm"月"dd"日" hh:mi:ss')
from dual;
```

```sql
select to_CHAR(sysdate,'HH24:MI:SS AM')
from dual;

to_CHAR(SYSDATE,'HH24:MI:SSAM'
------------------------------
15:58:45 下午
```

#### to_CHAR(number[,fmt]) 数字转为字符串


| fmt参数 | 说明                        |
| :------ | :-------------------------- |
| 9       | 显示数字并忽略前面的0       |
| 0       | 显示数字，位数不足，用0补齐 |
| .或D    | 显示小数点                  |
| ,或G    | 显示千分位                  |
| $       | 美元符号                    |
| S       | 加正负号（前后都可以）      |

```sql
select to_CHAR(123456,'$0000000000')
from dual;
```


### to_DATE(char[,fmt[,params]]) 字符串转为日期

**fmt参数格式**


```sql
select to_DATE('2022-2-2','yyyy-mm-dd')
from dual;
```

#### 日期和字符转换函数用法（to_date,to_char）

```
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') as nowTime from dual;   //日期转化为字符串  
select to_char(sysdate,'yyyy') as nowYear from dual;   //获取时间的年  
select to_char(sysdate,'mm') as nowMonth from dual;   //获取时间的月  
select to_char(sysdate,'dd') as nowDay from dual;   //获取时间的日  
select to_char(sysdate,'hh24') as nowHour from dual;   //获取时间的时  
select to_char(sysdate,'mi') as nowminute from dual;   //获取时间的分  
select to_char(sysdate,'ss') as nowSecond from dual;   //获取时间的秒

select to_date('2004-05-07 13:23:44','yyyy-mm-dd hh24:mi:ss')    from dual//

```

### to_number(char[,fmt]) 字符串转数字

```sql
select to_number('$123,456.789','$999999.999')
from dual;
```

### 空值转换

#### NVL(expr1,expr2)

- NVL(expr1,expr2) 如果expr1为空值，则返回expr2

```sql
select last_name,salary,NVL(commission_pct,0)
from employees;
```

#### NVL2(expr1,expr2,expr3) 

- NVL2(expr1,expr2,expr3) 如果expr1不为空值则返回expr2，expr1为空值则返回expr3

```sql
--若工资有奖金率，commission_pct不为空值，则返回工资加奖金，否则返回工资上调500
select last_name,NVL2(commission_pct,salary * (1 + commission_pct),salary + 500)
from employees;
```

## 通用函数

-  适用于任何(包含空值)

### nullIF(expr1,expr2) 

- nullIF(expr1,expr2) 若相等返回null,不等返回expr2

```sql
--显示出那些换过工作的人员原工作，现工作。
select e.last_name,e.job_id,j.job_id,nullIF(e.job_id,j.job_id) "Old Job ID"
from employees e, job_history j
where e.employee_id = j.employee_id
order by last_name;
```

### coalesce(expr1,expr2,expr3)

1. 同时处理交替多值
2. 若第一个表达式为空，则返回下一个表达式对其他参数coalesce结果

```sql
select last_name,coalesce(commission_pct,salary,10)
from employees
order by commission_pct;
```

### decode(exp1,con1,result1,con2,resul2...default) 解码

- 实现if ..then 逻辑
- 第一个是表达式,最后一个是不满足任何一个条件的值
- decode 是专属oracle的语法 有逗号','
- decode(字段,条件1,表达式1,条件2,表达式2,…默认表达式n)

```sql
--查询部门为10，20，30的员工信息，
--若部门号为10，则工资*1.1，若部门号为20，则工资*1.2，若部门号为30，则工资*1.3
  
select employee_id
      ,last_name
      ,decode(department_id,10,salary * 1.1
                           ,20,salary * 1.2
                           ,30,salary * 1.3
              ) new_sal
from employees
where department_id in (10,20,30);
```

### TRUNC(date,精度说明符) 用于截取日期时间的TRUNC函数

- 精度说明符有: yyyy-mm-dd-hh-mi 截取时间到秒(不是ss)暂时不知道怎么操作
- 不可直接用TRUNC(sysdate,'yyyy-mm-dd')，会提示“精度说明符过多”
- 如果不填写精度说明符，则默认到DD，包含年月日，不包含时分秒

```sql
select TRUNC(sysdate,'yyyy') --'yyyy' = 'year'
from dual;
```

#### 处理数字

- trunc函数返回处理后的数值，其工作机制与ROUND函数极为类似，只是该函数不对指定小数前或后的部分做相应舍入选择处理，而统统截去。
- 其具体的语法格式如下

```sql
TRUNC（number[,decimals]）

--number 待做截取处理的数值
--decimals 指明需保留小数点后面的位数。可选项，忽略它则截去所有的小数部分。
```

**注意：**

- 第二个参数可以为负数，表示为小数点左边指定位数后面的部分截去，即均以0记。
- 与取整类似，比如参数为1即取整到十分位，如果是-1，则是取整到十位，以此类推；
- 如果所设置的参数为负数，且负数的位数大于或等于整数的字节数的话，则返回为0。
  - 如：TRUNC(89.985,-3)=0。

```sql
select trunc(123.98)
from dual;
```

```sql
select trunc(123.123,2)
from dual;
```

```sql
select trunc(123.123,-1)
from dual;
```

#### 处理日期

- trunc函数返回以指定元元素格式截去一部分的日期值。
- 其具体的语法格式如下：

```sql
TRUNC（date,[fmt]）

--date为必要参数，是输入的一个日期值
--fmt参数可忽略，是日期格式，用以指定的元素格式来截去输入的日期值。忽略它则由最近的日期截去
```

- 下面是该函数的使用情况：

```sql
trunc(sysdate,'yyyy') 
--返回当年第一天.
select trunc(sysdate,'YYYY')
from dual;
```

```sql
trunc(sysdate,'mm')
 --返回当月第一天.
select trunc(sysdate,'MM')
from dual;
```

```sql
trunc(sysdate,'d') 
--返回当前星期的第一天.
select trunc(sysdate,'D')
from dual;
```

### WM_ConCAT(column) 行转列

- 对分组函数的每一组结果中的某一指定值域进行整行输出。
- wm_concat()中使用的列不必出现在group by子句中。

```sql
select deptno,wm_concat(ename)
from emp
group by deptno;

DEPTNO WM_ConCAT(ENAME)
------ --------------------------------------------------------------------------------
    10 CLARK,MILLER,KinG
    20 SMITH,FORD,ADAMS,SCOTT,JonES
    30 allEN,JAMES,TURNER,BLAKE,MARTin,WARD
```

## 分析函数

### 偏移量 LEAD() LAG()

- 这种操作可以代替表的自联接，从而更方便地进行进行数据过滤，并且LAG和LEAD有更高的效率。

**LEAD()**

- 在一次查询中取出同一字段的后N行的数据作为独立的列

**LAG()**

- 在一次查询中取出同一字段的前N行的数据作为独立的列

**OVER()**

- 表示 LAG()与LEAD()操作的数据都在OVER()的范围内，
  - `PARTITIon BY `语句（用于分组） 
  - `order by` 语句（用于排序）。

- 例如：LEAD(FIELD, NUM, defaultVALUE) FIELD需要查找的字段，NUM往后查找的NUM行的数据，defaultVALUE没有符合条件的默认值。

# 组函数（聚集函数）

-  组函数 (多行函数) 输入多个参数、或内部扫描多次，输出一个结果。

| 常用组函数 | 说明     |
| ---------- | -------- |
| avg()      | 平均值   |
| count()    | 记录总数 |
| max()      | 最大值   |
| min()      | 最小值   |
| stdden()   | 标准差   |
| sum()      | 总和     |

- 组函数对null的处理：count()将null看作0来处理、其他组函数忽略输入集合中的null（如果作用在null上，则返回null）。

## group by

- **在select中所有未包含在组函数的列都应该包含在group by中**
- group by中的列不必包含在select中

1. **不能在WHEHE子句中使用组函数**
2. 在HAVinG中使用组函数

- 如果在能使用where的场景下，从SQL优化的角度来看，尽量使用where效率更高，因为HAVinG是在分组的基础上过滤分组的结果,而where是先过滤,再分组,要处理的记录数不同。所以where能使分组记录数大大降低，从而提高效率。

```sql
--求表中各部门平均工资
select avg(salary)
from employees
group by department_id;
```

### 查找平均工资最低的部门

```sql
select rownum
      ,d.department_name "部门名称"
      ,t1.department_id "部门号"
      ,t1.avg_sal "平均工资"
from (
      select department_id
            ,avg(salary) avg_sal
      from employees
      group by department_id
      order by avg_sal ASC
      ) t1
     ,departments d
where rownum <= 1
 AND t1.department_id = d.department_id
```

### 多列group by

- 先按在前的值域进行分组，再依次在之前分组的基础上进行分组。

```sql
select avg(salary),department_id,employee_id
from employees
group by department_id,employee_id; --按department_id分组后在内部按employee_id分组
```

```sql
--查询表中平均工资高于4000的部门
select avg(salary),department_id
from employees
HAVinG avg(salary) > 4000
group by department_id;
```

## HAVinG 子句

- SQL在形成之分组后才会使用HAVinG子句中的谓语，故可以在having子句中使用组函数（对分组后的数据进行筛选）
- 在having子句中不能使用（组函数的）别名

```sql
--错误 在having中不能使用（组函数的）别名
select job,avg(sal) as avg_sal
from emp
group by job
having avg_sal > 100

--正确
select job,avg(sal) as avg_sal
from emp
group by job
having avg(sal) > 100
```

## 嵌套组函数

```sql
--求部门平均工资的最大值
select max(avg(salary))
from employees
group by department_id;
```

```sql
--求部门平均工资最大值及部门名称
select distinct (
        select max(avg(salary))
        from employees
        group by department_id
        ) salary,
       (
        select department_id
        from (
              select avg(salary) sal,department_id
              from employees
              group by department_id
              )
        where sal = (
                      select max(avg(salary))
                      from employees
                      group by department_id
                      )
        ) department_id
from employees
```

## 分级汇总

- **rollup() 按分组的第一个列进行统计和最后的小计**
- **cube() 按分组的所有列的进行统计和最后的小计**

### rollup()

```sql
select deptno,job,sum(sal)
from emp
group by rollup(deptno,job)

DEPTNO JOB         sum(SAL)
------ --------- ----------
    10 CLERK           1300
    10 MANAGER         2450
    10 PRESIDENT       5000
    10                 8750
    20 CLERK           1900
    20 ANALYST         6000
    20 MANAGER         2975
    20                10875
    30 CLERK            950
    30 MANAGER         2850
    30 SALESMAN        5600
    30                 9400
                      29025
```

### cube()

```sql
select deptno,job,sum(sal)
from emp
group by cube(deptno,job)

DEPTNO JOB         sum(SAL)
------ --------- ----------
                      29025
       CLERK           4150
       ANALYST         6000
       MANAGER         8275
       SALESMAN        5600
       PRESIDENT       5000
    10                 8750
    10 CLERK           1300
    10 MANAGER         2450
    10 PRESIDENT       5000
    20                10875
    20 CLERK           1900
    20 ANALYST         6000
    20 MANAGER         2975
    30                 9400
    30 CLERK            950
    30 MANAGER         2850
    30 SALESMAN        5600
```

## STADEV() 返回一组值的标准偏差

```sql
select deptno,stddev(sal) from emp group by deptno;
--variance 返回一组值的方差差
select deptno,variance(sal) from emp group by deptno;
```


## 行列转换

### WM_ConCAT()函数 列转行

- 对分组函数的每一组结果中的某一指定值域进行整行输出。
- wm_concat()中使用的列不必出现在group by子句中。

```sql
select deptno,wm_concat(ename)
from emp
group by deptno;

DEPTNO WM_ConCAT(ENAME)
------ --------------------------------------------------------------------------------
    10 CLARK,MILLER,KinG
    20 SMITH,FORD,ADAMS,SCOTT,JonES
    30 allEN,JAMES,TURNER,BLAKE,MARTin,WARD
```

### 基本语句实现 行转列

```sql
select name "姓名"
      ,salary_type "薪水类型"
      ,cnt "数量(元)"
from salary_composite;

--行转列

select name "姓名"
      ,sum(decode(salary_type,'基本工资',cnt,0)) "基本工资"
      ,sum(DEOCDE(salary_type,'交通补贴',cnt,0)) "交通补贴"
      ,sum(decode(salary_type,'全勤奖',cnt,0)) "全勤奖"
      ,sum(decode(salary_type,'效益奖',cnt,0)) "效益奖"
from salary_composite
group by name;

--再换回去

select name "姓名"
      ,'基本工资' "薪水类型"
      ,base_salary "数量(元)"
from t_salary_composite_to k
where base_salary <> 0
UNIon all
select name
      ,'交通补贴'
      ,traffic_salary
from t_salary_composite_to
where traffic_salary <> 0
UNIon all
select name
      ,'全勤奖'
      ,ontime_salary
from t_composite_to
where ontime_salary <> 0
UNIon all 
select name
      ,'效益奖'
      ,bonus
where bonus <> 0
order by 1 DESC;
```

```
姓名  薪水类型  数量(元)
小米  基本工资  1000
小米  交通补贴  200
小米  全勤奖    500
小米  效益奖    100
小明  基本工资  2000
····

姓名 基本工资 交通补贴 全勤奖 效益奖
小米 1000    200      500   100
···
```

# sequence 序列

- sequence（序列）可供多个用户来产生唯一数据的数据库对象，与表无关。自动提供唯一的数值、共享对象、提供主键值。

1. 将sequence装入内存可以提高访问效率
2. sequence序列号是数据库系统按照一定规则自增的数字序列（不会重复）
6. sequence可用于记录数据库中最新动作（CRUD）的语句，序列号都会随着更新。

## create sequence 创建序列

```sql
create sequence sequence_name
[start with n1]
[increment by n2]
[maxvalue n3|no maxvalue]
[minvalue n4|no minvalue]
[cache n5|nocache] 
[cycle|nocycle]
[order|no order]
```

| 选项                                             | 说明                                                         |
| ------------------------------------------------ | ------------------------------------------------------------ |
| start with                                       | 生成的第一个数值                                             |
| increment by                                     | 递增量（正整数/负整数），指明每一次增加多少。                |
| maxvalue、minvalue<br />no maxvalue、no minvalue | 最大值、最小值<br />无上限、无下限                           |
| cache<br />nocache                               | 高速缓存中可以预分配的序列号个数，默认是20个<br />预分配序列号，每次只生成一个序列号，保证序列号的连续性。 |
| cycle<br />nocycle                               | 序列达到最值后是否从生成的第一个数值开始循环。<br />默认，不循环。 |
| order<br />no order                              | 指定按顺序生成序列。（RAC时保证序列号因为有请求才生成的）<br />默认，不指定按顺序生成序列。 |

- cache不能保证序列号是连续的。如果缓存中的序列号没有用完就关闭数据库等其它原因，没有使用的序列号就丢失（跳号）。如果指定cache值，ORACLE就可以预先在内存里面放置一些sequence,cache里面的取完后，oracle自动再取一组到cache。
- 序列装入内存可提高访问速度，但会在以下的情况下出现裂缝（跳号）：回滚、系统异常、多个表同时使用同一个序列。

> SEQUENCE_CACHE_ENTRIES参数，设置能同时被cache的sequence数目。

## .nextval、.currval 序列移动

| 操作     | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| .nextval | 引用下一序列值。<br />第一次.nextval返回的是初始值；随后的每次.nextval都会触发自动增长（increment by），返回增加后的值。 |
| .currval | 引用当前序列值。<br />序列创建后初始无值，无法查询当前值.currval。第一次使用序列时，要先查询下一值（.nextval）才能查询当前值（.currval）。 |

```sql
select emp_seq.nextval                
from dual;

select emp_seq.currval
from dual;
```

- 使用sequence：不包含子查询/SNAPSHOT/view的select语句、insert语句的子查询、insert语句的values子句、update语句的set子句 ...。

```sql
create table tab_name(
                      col_int int,
                      col_varchar varchar2(20),
                      col_seq int
                      );

--insert语句的values子句
insert tab_name into values(1, 'xyz', sequence_name.nextval); 
--或者 insert tab_name into values(1, 'abc', nextval FOR sequence_name);
insert tab_name into values(2, 'fgh', sequence_name.nextval); 
--或者 insert tab_name into values(2, 'fgh', nextval FOR sequence_name);

--update语句的set子句
update tab_name 
set col_varchar = '678', col_seq = sequence_name.nextval 
where col_int = 2; 

delete tab_name col_sql = sequence_name.nextval 
where col_int = 1;
```

## alter seq_name .. 修改序列 

- 若要修改start with、minvalue的值，则必须删除序列后再重新建立序列。
- 可修改序列的增量、最大值、循环选项、是否装入内存。修改之后，只有将来的序列值会被改变。

## drop sequence 删除序列

```sql
drop sequence emp_seq;
```

# index 索引 

- 创建索引就是对某些特定列中的数据排序，生成独立的索引表。在某列上创建索引后，如果该列出现在查询条件中，Oracle会自动的引用该索引，先从索引表中查询出符合条件记录的rowid（字段物理地址），根据rowid快速的定位到具体的记录。

1. 索引是独立于表的模糊对象，可以存储在与表不同的磁盘/表空间。
2. 索引被删除/破坏，不会对表产生影响，其影响的只是查询的速度。
3. 索引一旦建立，Oracle管理系统会对其进行自动维护，且由Oracle管理系统决定何时引用索引，用户不用在查询语句中指定使用哪个索引。
4. 删除表时，所有基于该表的索引会自动被删除。
4. 表中的数据非常多时，引用索引带来的查询效率非常可观。但索引会减慢数据插入表的速度（插入数据时也要维护索引）。

## create index 创建索引

| 创建时机 | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| 创建     | 1. 列中的数据分布范围很广<br/>2. 列经常在where子句或连接条件中出现<br/>3. 表经常被访问而且数据量很大，访问数据大概占数据总量的2%-4% |
| 不要创建 | 1. 表很小<br/>2. 列不经常作为连接条件出现在where子句<br/>3. 查询的数据大于2%-4%<br/>4. 表经常更新 |

> 当任何单个查询要检索的行少于或者等于整个表行数的10%时，索引就非常有用。

| 创建索引 | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| 自动创建 | 定义primary key、unique约束后系统自动在相应的列上创建唯一性索引。 |
| 手动创建 | 用户可以在其他列上创建非唯一性索引。                         |

```sql
create [unique] [bitmap] index 索引名 on 表(列1[,列2…]);
[tablespace 表空间名]
[sctfree n1]
[storage
 (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
)];
```

| 选项       | 说明             |
| ---------- | ---------------- |
| tablespace | 存储索引的表空间 |
| sctfree    | 索引块的空闲空间 |
| storage    | 存储块的空间     |

```sql
--在一个或多个列上创建索引
create index emp_last_name_index on employees(last_name);
```

## 索引的类型

**b-tree索引**

- Oracle数据中最常见的索引，就是b-tree索引，create index创建的普通索引就是b-tree索引，没有特殊的必须应用在哪些数据上。

**bitmap位图索引**

- 位图索引经常应用于列数据只有几个枚举值的情况（比如性别，或者我们经常开发中应用的代码字段）。这个时候使用bitmap位图索引，查询效率将会最快。

**函数索引**

- 比如经常对某个字段做查询的时候经常是带函数操作的，那么此时建一个函数索引就有价值了。
  - 例如：TRIM(列)等字符串操作函数，这个时候可以建立函数索引来提升这种查询效率。

**hash索引**

- hash索引可能是访问数据库中数据的最快方法，但它也有自身的缺点。
- 创建hash索引必须使用hash集群，相当于定义了一个hash集群键，通过这个集群键来告诉oracle来存储表。
  - 因此，需要在创建hash集群的时候指定这个值。
  - 存储数据时，所有相关集群键的行都存储在一个数据块当中，所以只要定位到hash键，就能快速定位查询到数据的物理位置。

**reverse反向索引**

- 这个索引不经常使用到，但是在特定的情况下，是使用该索引可以达到意想不到的效果。
  - 如：某一列的值为{10000,10001,10021,10121,11000,....}，假如通过b-tree索引，大部分都密集分布在某一个叶子节点上，但是通过反向处理后的值将变成{00001,10001,12001,12101,00011,...}，很明显的发现他们的值变得比较随机，可以比较平均的分部在各个叶子节点上，而不是之前全部集中在某一个叶子节点上，这样子就可大大提高检索的效率。

**分区索引和分区表的全局索引**

- 这两个索引是应用在分区表上面的
  - 前者的分区索引是对分区表内的单个分区进行数据索引
  - 后者是对分区表的全表进行全局索引。

## drop index 删除索引

- drop index不可回滚，只有索引的拥有者或拥有drop anyindex权限的用户才可以删除。

```sql
drop index emp_last_name_index;
```

## alter index 修改索引

### rename to 重命名索引

```sql
alter index 旧索引名 rename to 新索引名;
```

### coalesce、rebuild 合并索引、重新构造索引

- 索引建好后，经过很长一段时间的使用，索引表中存储的空间会产生一些碎片，导致索引的查询效率会有所下降，此时考虑合并索引、按照索引规则重新分类存储、或选择删除索引重新构造索引。

```sql
--合并索引
alter index 索引名 coalesce;

--重新构造
alter index 索引名 rebuild;
```

# synonym 同义词

- 同义词：任何表、视图、物化视图、序列、存储过程、函数、包、类型、JAVA类对象、用户定义类型、其他同义词的别名。
- 除了在数据字典中的定义外，同义词不占任何空间。
- 如果同义词底层的对象重命名/转移，只需重定义该同义词，基于该同义词的应用则无需任何修改。

1. 方便访问其他用户的对象。
2. 缩短对象名字的长度。

- 同义词常用于安全和方便：

1. 伪装对象名称和其所有者。
2. 为分布式数据库远程对象提供位置透明性。
3. 简化数据库用户访问对象SQL语句。
4. 当行使精细化访问控制时提供类似指定视图的访问限制。

> SCOTT用户权限不足：登入SYSDBA授予同义词权限
>
> ```sql
> grant create synonym to scott;
> ```

| 权限    | 说明                                                         |
| ------- | ------------------------------------------------------------ |
| public  | public组，每个用户都可以访问。                               |
| private | 默认，对象所有者。只有对象所有者显式授权后其他用户才可访问。 |

## 创建同义词

```sql
create [public] synonym 同义词 for 对象;
```

```sql
create synonym synonym_test for emp;
```

## 删除同义词

- 若删除public同义词，则需要指定public关键字。

```sql
drop [public] synonym 同义词;
```

# 数据字典

| 同义词         | 说明                                                         |
| -------------- | ------------------------------------------------------------ |
| user_sysnonyms | 当前用户所拥有的同义词                                       |
| all_synonyms   | 当前用户所能使用的所有同义词                                 |
| dba_synonyms   | 数据库中所有的同义词，包括每个用户创建的私有同义词和DBA创建的公共同义词<br />只有DBA能够访问，除了包含数据字典user_synonyms的所有列外，还有一个列owner代表同义词的创建者。 |

| 序列           | 说明               |
| -------------- | ------------------ |
| user_sequences | 当前用户拥有的序列 |

```sql
--若不将序列的值装入内存nocache可使用表user_SEQUENCES查看当前有效值
select emp_seq.currval
from user_SEQUENCES;
```

| 约束              | 说明 |
| ----------------- | ---- |
| user_constraints  |      |
| user_cons_columns |      |

```sql
select constraint_name, constraint_type, search_condition, status
from user_constraints
where table_name = 'table1';
```

```sql
select constraint_name, column_name
from user_cons_columns
where table_name = 'table1';
```

| 索引             | 说明 |
| ---------------- | ---- |
| user_indexes     |      |
| user_ind_columns |      |

```sql
select *
from user_indexes
matural join user_ind_columns;
```

```sql
select ic.index_name
      ,ic.column_name
      ,ic.column_position col_pos
      ,ix.uniqueness
from user_indexes ix
    ,user_ind_columns ic
where ic.index_name = ix.index_name
  and ic.table_name = 'employees';
```
