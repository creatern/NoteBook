# SQL

| DML、DDL、DCL                                 | 说明                                                         |
| --------------------------------------------- | ------------------------------------------------------------ |
| DML（data manipulation language）数据操纵语言 | 对数据库的数据进行一些操作<br />DML会对每个语句的每条记录都做日志记录以便于回滚。<br />insert、update、delete、select。 |
| DDL（data definition language）数据库定义语言 | 定义或改变表的结构，数据类型，表之间的链接和约束等初始化工作。<br />create、alter、drop。 |
| DCL （data control language) 数据库操纵语言   | grant、revoke、commit、rollback、savepoint、lock。           |

## SQL概述

### 事务

- 事务会将所有在事务中被修改的数据行加上锁（行级锁），来阻止其它人（会话）同时对这些数据的修改操作。当事务被提交或回滚后，这些数据才会被释放锁。事务分为：一个或多个DML语句、一个DDL语句、一个DCL语句。


- DML事务（一般事务）由以下情况之一结束：commit、rollback；DDL(自动提交)；用户会话正常结束；系统异常终止。

- commit、rollback提供了：数据完整性、数据改变被提交之前预览、将逻辑上的操作分组。

| 事务进程（数据状态） | 说明                                                         |
| -------------------- | ------------------------------------------------------------ |
| 提交/回滚前          | 1. 改变前的数据状态是可以改变的。<br/>2. 执行DML操作的用户可以通过select语句查询之前的修正。<br/>3. 其他用户不能看到当前用户所做的改变，直到当前用户结束事务。<br/>4. DML语句所涉及的行被锁定，其他用户不能操作。 |
| 提交后               | 1. 数据的改变保存到数据库中。<br/>2. 改变前的数据已经丢失。<br/>3. 所有用户可以看到结果。<br/>4. 所有保存点被释放。 |
| 回滚后               | 1. 数据改变被取消。<br/>2. 修改前的数据状态被恢复。<br/>3. 锁被释放。 |

### 提交 commit

#### 显式提交、隐式提交

| 提交     | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| 显式提交 | commit语句。                                                 |
| 隐式提交 | 无需显示执行commit语句，session中的操作被自动提交到数据库的过程。<br />此隐式提交是在自己的session，如果在其他人的session中正在修改相同的数据，则引起隐式提交的语句则必须等待。s |

> 触发隐式提交的SQL：alter，audit，comment，connect，create，disconnect，drop，exit，grant，noaudit，quit，revoke，rename。

| 隐式提交          | 说明                                  |
| ----------------- | ------------------------------------- |
| 正常执行完DDL语句 | create、alter、drop、truncate、rename |
| 正常执行完DCL语句 | grant、revoke                         |
| 正常退出          | 没有明确发出commit/rollback           |

- 执行DDL语句时（即使DDL语句执行失败），其之前的DML操作也会被提交到数据库中：为了避免隐式提交或者回滚，尽量保证一条或者几条DML操作完成后有显示的提交/回滚，防止后续执行的DCL或者DDL自动提交前期的DML操作。

- 事务读一致性：commit之前的数据，其他用户不可见。执行DDL语句时，Oracle需要在它的系统表中进行元数据的记录操作，如果不隐式提交就无法保证一致性。

#### 自动提交 autocommit

- 自动提交：autocommit设置为on，则DML语句执行后，系统将自动进行提交。

```sql
set autocommit on；
```

```sql
-- 查看当前是否是自动提交：
show autocommit；
```

#### 保存点 savepoint

```sql
-- 设置保存点：当事物提交后保存点也被清除，需要重新设置
savepoint 保存点名称;         
```

```sql
-- 回滚到保存点
rollback to savepoint 保存点名称; 
```

## 权限管理

- sysdba &gt; sysoper（超级权限）：同属于系统权限（system Privileges），Oracle允许拥有该权限的用户在数据库未打开的情况下访问实例、执行重大的数据库操作（数据库启动、关闭等）。

| sysdba可执行任务          | 说明                                           |
| ------------------------- | ---------------------------------------------- |
| startup、shutdown         | 启动、关闭数据库                               |
| alter database            | 修改数据库<br />打开、装载、备份、改变字符集等 |
| create database           | 创建数据库                                     |
| drop database             | 删除数据库                                     |
| create spfile             | 创建服务器参数文件                             |
| alter database archivelog | 改变归档模式                                   |
| alter database recover    | 执行数据库恢复                                 |

```shell
# 以sysdba登录数据库
sqlplus sys/change_on_install[@orcl] as sysdba
```

### 数据库安全性

- 数据库安全性：用户、角色、概要文件。

> 数据库安全性、系统安全性、数据安全性。

#### 用户

##### 创建用户 

- 创建用户需要DBA权限。

```sql
create user user_name
identitied by password;
```

##### 用户解锁

```sql
alter user scott  
identitied by tiger 
account unlock;
```

##### 用户信息

```sql
-- 查看当前用户名
show user
```

```sql
-- 查看所有用户名 all_users
select *
from all_users
```

##### 用户密码

- 可用户自己修改

```sql
alter user user_name
identitied by password;
```

##### 切换用户

```sql
connect scott/tiger@orcl
```

#### 角色

- 角色可以像用户一样被赋予权限和收回权限，当角色被赋予某个用户时，其权限也被赋予给用户。因此，一个用户的权限=其本身的权限\+其拥有的角色的权限。

##### 创建角色

```sql
create role 角色名
```

##### 常用角色

| 角色     | 拥有的权限     |
| :------- | :------------- |
| connect  | 连接数据库     |
| resource | 使用数据库资源 |

### 权限管理

1. 不同对象具有不同的对象权限。
2. 对象的拥有者或sysdba/system用户拥有所有的权限。
3. 对象的拥有者可以向外分配权限。

#### public 公共用户

- 向public分配/收回权限，相当于向所有用户（包括还未创建的和已经创建的用户）分配/收回权限。

#### SQL标准权限

| 权限              | 权限     |
| :---------------- | :------- |
| select            | 查询     |
| insert            | 插入     |
| update            | 更新     |
| delete            | 删除     |
| all \[privilege\] | 以上所有 |

#### 权限管理命令

##### grant 赋予权限

```sql
grant 权限列表
[on [模式.]关系/视图]
to 用户/角色列表
[with grant option] -- 获得分配权限的能力
[by current_role] -- 通过角色授权，避免级联收权
```

```sql
-- 先设置与当前会话关联的角色（默认空）必须是该授权用户拥有的角色
set role 角色
```

##### revoke 收回对象权限

```sql
revoke [grant option for] 权限列表 -- grant option for 仅收回分配权
[on [模式.]关系/视图]
from 用户/角色列表
[restrict] -- 阻止级联收权
```

- restrict阻止级联收权，如果发生级联授权，则使这次收权失败。grant授权时，通常会产生一个授权图。当revoke收回权限时，默认级联收权：即a授权给b，b授权给c时，如果收回b的权限，则c的权限也被收回。

#### 行级授权

##### VPD Oracle虚拟私有数据库

- VPD（Oracle虚拟私有数据库）将函数和关系相关联，该函数返回一个谓词，并自动将该谓词添加到使用该关系的任何查询。

> 隐患：可能会改变查询的含义。

#### 权限分配情况

| 数据库字典             | 权限描述                   |
| :--------------------- | :------------------------- |
| role\_sys\_privs       | 角色拥有的系统权限         |
| role\_tab\\_privs      | 角色拥有的对象权限         |
| user\_role\_privs      | 用户拥有的角色             |
| user\_tab\_privs\_made | 用户分配的关于表对象权限   |
| user\_tab\_privs\_recd | 用户拥有的关于表对象权限   |
| user\_col\_privs\_made | 用户分配的关于列的对象权限 |
| user\_col\_privs\_recd | 用户拥有的关于列的对象权限 |
| user\_sys\_privs       | 用户拥有的系统权限         |

## select 基本查询

### select .. from

| 子句   | 作用               |
| ------ | ------------------ |
| select | 查询的条件。       |
| from   | 确定查询的数据源。 |

```sql
-- 查询指定值域
select emp_name
from emp;

-- 查询所有*
select *
from emp;

-- 查询运算式
select salary * 1.02
from emp;
```

| 关键字   | 说明             |
| -------- | ---------------- |
| as       | 别名（可省略）   |
| \|\|     | 连接符（Oracle） |
| distinct | 唯一性           |

```sql
select first_name || '' || last_name
from employees;
```

| 运算符类型 | 运算符              |
| ---------- | ------------------- |
| 算术运算符 | `+  - * /`          |
| 比较运算符 | `> >= < <= = <> !=` |
| 逻辑运算符 | and or not          |

### where 谓词查询

- where子句（如果存在）则必须置于from子句之后。

```sql
select * 
from emp
where salary > 100;
```

| 关键字          | 说明                                                      |
| --------------- | --------------------------------------------------------- |
| in              | 列表取值                                                  |
| is null         | 判断空值                                                  |
| like            | 模糊查询<br />-：一个字符<br />%：n个字符<br />\\：转义符 |
| between a and b | 范围查询（a&le;X&le;B）                                   |

```sql
select last_name, employee_id, salary
from employees
where salary > 1000
 and department_id in (10,20,30);
```

```sql
-- 查询没有部门的员工
select *
from emp
where dept_name = null;
```

```sql
-- 查询员工姓名含有a的
select last_name, employee_id
from employees
where last_name like '%a%';
```

```sql
select last_name, employee_id, salary
from employees
where salary between 1000 and 7000;
```

### order 排序

- order by子句只能在语句最后出现，可以使用第一个查询中的列名、别名、相对位置。

| 顺序 | 说明       |
| :--- | :--------- |
| asc  | 默认，升序 |
| desc | 降序       |

```sql
select employee_id, last_name
from employees
order by employee_id desc;
```

- 多列排序：依次在先前列的排序结果上进行排序。

```sql
-- 先对employee_id排列，再在employee_id排列的基础上对last_name排列
select emlpoyee_id, last_name
from employees
order by employee_id, last_name;
```

- 别名排列：order by子句直接使用别名进行排序。

```sql
select employee_id, last_name, salary*1.1 sal -- 别名
from employees
order by sal;
```

### case .. when

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
-- 输出除编号为1的员工即where empno <> 1;
select *
from emp
where 0 = case
           when empno = '1' then 1
           else 0
          end
```

### 集合运算

| 运算 | 说明                 |
| ---- | -------------------- |
| 并集 | union<br />union all |
| 交集 | intersect            |
| 差集 | minus                |

- 多个进行集合运算的结果集的select字句中的列名、表达式在数量、数据类型上一一对应。

- 除union all之外，其余集合运算会自动去重，会自动按照第一个查询中的第一个列的升序排列。

```sql
-- 使用相对位置排序举例
select job_id,1
from employees
where department_id = 10
union
select job_Id ,2
from employees
where department_id = 20
order by 1 DESC;
```

## 数据类型

| 字符型（固定长度） |                                                            |
| ------------------ | ---------------------------------------------------------- |
| char(n)            | max2000                                                    |
| nchar(n)           | unicode格式，max1000，多数用来存储汉字                     |
| varchar2(n)        | max4000                                                    |
| nvarchar2(n)       | unicode格式，max2000                                       |
| **数值型**         |                                                            |
| number(p,s)        | p为有效数字，s为小数点后面的位数，s可正可负                |
| float(n)           | 存储二进制数据，二进制数据的1-126位，一般使用number        |
| int                | 整数                                                       |
| integer            |                                                            |
| **日期型**         |                                                            |
| date               | 范围为公元前4712年1月1日到公元9999年12月31日，可以精确到秒 |
| timestamp          | 可以精确到小数秒，一般用date类型                           |
| **其他类型**       |                                                            |
| long               | 可变长字符数据，最大2G                                     |
| raw                | (long raw) 原始的二进制                                    |
| blob               | 可存4G数据以二进制存放                                     |
| clob               | 可存4G数据以字符串存放                                     |
| bfilb              | 存储外部文件的二进制数据，最大4G                           |

## table 表（关系）

- table二维表，row行（记录）， column列（域、字段）。  每一列是相同的数据类型、列名唯一的；每一行的数据是唯一的。  

### 表结构操作

#### desc 查看表结构

```sql
desc tablename;
```

#### create table 创建表

- create table：权限、存储空间、命名规则。

- 命名规则：以字母开头、在1-30个字符之间、只能包含`a ~ Z,a ~ z，0~9，_，$，#`、不能和其他对象重名、不能是关键字。

```sql
create table emp (
 id number(6,2)
,salary number(10)
,name varchar2(25)
,hire_date DATE
);
```

##### as 复制表 

```sql
create table emp
as
select *
from employees
where 1 = 2;  -- 只复制结构，不复制内容
-- where 1 = 1;  -- 既复制结构也 复制内容
```

#### alter table 修改表结构

##### add 添加字段

```sql
alter table emp
add job varchar2(30);
```

##### modify 更改字段数据类型   

- modify：更改字段数据类型、为新追加的列定义默认值default。

```sql
alter table emp
modify id number(10) [default 100];
```

##### drop column 删除字段 

```sql
alter table emp
drop column job;
```

##### rename column 修改字段名 

```sql
alter table emp
rename column id to emp_id;
```

#### rename table 修改表名 

```sql
rename table emp to emp1;
```

#### drop 删除表

```sql
-- 删除表,数据和结构都删掉
drop table emp;

-- 删除表的同时，删除约束
drop table emp cascade constraints;
```

##### recyclebin 回收站

- drop并未真正删除表，而是把表重命名之后放在回收站 recyclebin。

```sql
-- 删除表且清空回收站中的该表
drop table emp purge;
```

###### show 查看回收站

```sql
-- 查看回收站中表重命名的信息
show recyclebin
```

###### purge 清空回收站

```sql
-- 清空回收站中指定recyclebin_name的表
purge table "recyclebin_name";
```

```sql
-- 清空回收站
purge recyclebin;
```

###### flashback 闪回

```sql
-- 闪回
flashback table emp to before drop;

-- 闪回并重命名
flashback table emp to before drop
rename to employees;
```

### constraint 约束

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
    no1 number(10) not null,  -- 非空约束
    no2 number(10,2) unique,   -- 唯一性约束
    no3 number(20) check(0 < no3 <= 99),  -- check约束
    no4 varchar2(25) datatype references table2(no4),  
    -- 外键table2为主表，table1为从表。
    constraint table1_no1_c check(1 < no1 < 9), -- check约束
    constraint table1_no1_pk primary key (no1),  -- 主键（非空且唯一）
    constraint table1_no3_fk foreign key (no3) references table2(no1),   -- 外键
    constraint table1_no4_fk foreign key (no4) references table2(no4) on delete cascade, -- 外键，级联删除
    constraint table1_no5_fk foreign key (no5) references table2(no5) on delete set null  -- 外键，级联置空
);
```

```sql
-- 非空约束 not null
create table 表名
( 
 列名 数据类型 not null
,列名 数据类型
);
```

```sql
-- 唯一性约束 unique
create table 表名
(
 列1 数据类型 [constraint 约束名] unique
,列2 数据类型
);
```

```sql
-- 主键约束 primary key

-- 列级
create table 表名
(
 列1 数据类型 [constraint 约束名] primary key
,列2 数据类型
);

-- 表级
create table 表名
(
 列1 数据类型 
,列2 数据类型
,列3 数据类型
,[constraint 约束名] primary key(列1[,列2,...])
);
```

```sql
-- 外键约束 foreign key

-- 列级
create table 表名
(
    列1 数据类型 [constraint 约束名] [datatype] references 要关联的表(列,...)
    --  datatype 仅数据类型关联
    [on delete|update cascade]  -- 级联删除/更新
    [on delete|update set null] -- 置空
    ,列2 数据类型
);

-- 表级
create table 表名
(
    列1 数据类型 
    ,列2 数据类型
    ,[constraint 约束名] foreign key(列1[,列2,...])
    references 要关联的表(列,...)
    [on delete cascade]  -- 级联删除
    [on delete set null] -- 级联置空
);
```

```sql
-- 检查约束 check

-- 列级
create table 表名
(
    列1 数据类型 [constraint 约束名] check (条件表达式)
    ,列2 数据类型
    ,列3 数据类型
);

-- 表级
create table 表名
(
    列1 数据类型
    ,列2 数据类型
    ,列3 数据类型
    ,[constraint 约束名] check (条件表达式)
);
```

#### alter table 修改表约束

1. 约束可以添加/删除，但不可以修改。
2. 有效、无效化约束。
3. 添加not null约束要用modify关键字。

##### drop constraint 删除

```sql
alter table 表
drop constraint 约束名;
```

```sql
-- cascade 删除主键约束的同时，（如果存在）以该主键（作为关联表的外键）建立的外键约束也一起删除。
alter table 表
drop primary key 约束名 [cascade];   
```

##### modify 修改 

```sql
-- 修改不得破坏表中原有的数据。
alter table 表
modify (列名 数据类型 约束);
```

##### rename constraint 改名

```sql
alter table 表
rename constraint 旧的约束名 to 新的约束名;
```

##### add constraint 添加表约束

```sql
alter table 表
add constraint 约束;
```

```sql;
alter table t1
add constraint table_fk forreign KEY (no2) references t2(no2);
```

##### disable/enable constraint 无效化/激活约束

- 当定义/激活unique、primary key约束时，系统自动创建unique、primary key索引。

```sql
alter table 表
disable constraint 约束名;
```

```sql
alter table 表
enable constraint 约束名;
```

### default 默认值

```sql
-- 创建表时添加
create table emp(
 salary number(20) default 1000
);
```

```sql
-- 修改表时添加默认值 modify
alter table emp
modify salary number(10) default 1000;
```

### 表的类型

#### 只读表 reade only

```sql
-- 只读
alter table tb_name reade only;

-- 可读写
alter table tb_name read write;
```

- 不允许在只读表上进行的操作：DML、truncate、锁住指定的数据 `select ...for update`、修改表的属性  `alter table add|modify`、把列置于不可用的状态 `alter table set column unused`、分区操作 `alter table drop|truncate|EXCHANGE [SUB]PARTITIon`、`alter table upgrade including data`、`alter type cascade including`、在线重定义、闪回表 `flashback table`。

#### 临时表 global temporary table 

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

-- 无数据显示,数据自动清除
select * from temp_dept;  
```

##### 全局临时表

- 全局临时表以常规表的形式创建临时数据表的表结构（可以有 lob 列和主外键），但要在每一个表的主键中加入一个SessionID 列以区分不同的会话。
- 用户注销触发器在用户结束会话的时候删除本次会话所插入的所有记录 （SessionID 等于本次会话 ID 的记录 ）。程序写入数据时，要顺便将当前的会话ID写入表中；程序读取数据时，只读取与当前会话ID相同的记录即可。

##### with 临时查询表

- with子句将该子句执行一次并存储到用户的临时表空间中，在查询结束后，该临时表被删除。

```sql
-- 查询公司各部门的总工资大于公司中各部门的平均总工资的部门信息
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

#### 簇表 cluster

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

#### 外部表

```sql
/*
‪f:\temtb\data\temstu.txt
‪‪50016,小张,上海,22
30011,小李,天津,24
*/

-- 外部表文件路径
create or replace directory dat_dir
as 'f:\temtb\data';
create or replace directory log_dir
as 'f:\temtb\log';
create or replace directory bad_dir
as 'f:\temtb\bad';
grant read on directory dat_dir to rawman;
grant read, write on directory log_dir to rawman;
grant read, write on directory bad_dir to rawman;

-- 创建外部表
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

## 数据操作

```
SQL> desc emp
name      type         nullable default Comments
-- -- -- -- - -- -- -- -- -- --  -- -- -- --  -- -- -- - -- -- -- -- 
ID        number(6,2)  Y
SALARY    number(10)   Y
name      varchar2(25) Y
HIRE_DATE DATE         Y
```

### insert 添加数据

- insert一次只能向表中插入一条数据。

```sql
-- 显式插入
insert into emp(id,salary,name,hire_date)
values(1,1500,'张三',to_DATE('2002-2-3','yyyy-mm-dd'));

-- 隐式插入
insert into emp
values(2,3000,'李四',to_DATE('2003-3-2','yyyy-mm-dd'));

-- 显式null
insert into emp
values(5,null,'赵七'，null);

-- 字段插入 对于没有列出的值域，则插入null
insert into emp(name,salary,hire_date,id)
values(2500,'王五',to_DATE('2004-4-5','yyyy-mm-dd'),3);
```

#### \& 窗口输入

- 窗口输入 ：对于日期和字符型等需要单引号的数据类型，可以在&外面加上单引号（`'&name'`），从而不用在窗口输入时使用单引号。而其他不需要单引号的数据类型则可以直接使用`&id` 。

```sql
insert into emp
values(&id,&name,&salary,&hire_date);
-- 5 '李四' 2000 '11-2月-1999'

insert into emp
values(&id,'&name',&salary,'&hire_date');
-- 5 李四 2000 11-2月-1999
```

#### select 复制插入

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

### update 更新数据 

```sql
update 表
set 列=新的列
where -- 没有where ，则全部更新
```

```sql
-- 更新114号员工的工作和工资使其和205号员工相同
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


### delete 删除数据 

```sql
delete from table_name
where  -- 若无where子句，则全部删除
```

```sql
-- 删除emp表中id为1的员工
delete from emp
where id = 1;
```

##### truncate 清空数据

| 操作     | 对比     |
| -------- | -------- |
| delete   | 可以回滚 |
| truncate | 不可回滚 |

```sql
truncate table 表;
```

### merge 合并数据

- 数据转储操作：从表中选择数据行以修改或插入到另一个表。
- merge基于on子句中的条件来决定对目标表执行的是修改还是插入操作，必须在目标表上有insert和update系统权限，源表上具有select系统权限。

```sql
merge into 表 别名  -- 说明正在修改或插入的目标表
using (table|view|sub_query) 别名  -- 标识要修改或插入的数据源（表，视图，子查询等）
on (条件) -- 定义merge语句是进行修改还是插入操作的条件
when matched then  -- 当条件满足时，执行
 update set         -- 执行的语句块
   col1 = col1_val,
   col2 = col2_val
when not matched then  -- 当条件不满足时，执行
 insert (列名)
 values (值)
```

1. update、insert可仅仅出现其一、附加where条件。（无条件insert：`on(1=1)`）
2. delete子句仅仅能够删除目标表的行而无法删除源表的行，必须存在update set，且delete字句中的where必须位于最后。
3. 不能更新on子句引用的列，且应该保证on中条件的唯一性（在源表中获得一组稳定的行）。

> on(t1.name = t2.name)：则t1和t2表中的name应该是一一对应的，而不能重复。

4. 自我更新的using若存在null，则改为count()将null改为0处理。

```sql
merge into t_bonus t
using (select employee_id, salary
       from  s_employee
      ) s
on (t.employee_id = s.employee_id)
when matched then 
  update set t.bonus = s.salary + 40
  where t.bonus > 1000
             
  delete 
  where (t.bonus > 1300)
when not matched then 
  insert into (t.employee_id, t.bonus)
  values (s.employee_id, s.salary +20)
  where (s.salary > 3000)
```

```sql
merge into t2
using (select * 
       from t2 
       where name='d') t
on (t.name=T2.name)
when matched then
 update set t2.MonEY=100
when not matched then
 insert values ('d',200);

-- 本来t表应该由于name=D不存在而要添加记录。可是实际却根本无变化。
SQL> select * from t2;
name                      MonEY
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -
a                            30
c                            20
-- 由于此时select * from t2 where name='d'为null,所以出现了无法插入的情况。

-- 自我更新的using若存在null，则改为count()将null改为0处理。
merge into t2
using (select count(*) CNT 
       from t2 
       where name='d') t
on (t.CNT <> 0)
when matched then
 update
 set t2.MonEY = 100
when not matched then
 insert
 values ('d',100);

SQL> select * from t2;
name                      MonEY
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -
a                            30
c                            20
d                           100
```

## view 视图

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

### 视图操作

#### create \[or replace\] view 创建/修改视图

- create view子句中各列的别名应和子查询中各列相对应。
- 修改视图只能重新创建视图。

| 特性     | 简单视图 | 复杂视图           |
| :------- | :------- | :----------------- |
| 表的数量 | 一个     | 一个、多个         |
| 函数     | 没有     | 有（使用别名）     |
| 分组     | 没有     | 有                 |
| DML      | 可以     | 必须要符合特定条件 |

```sql
-- 简单视图
create or replace view table1_view
as
select *
from table1;
```

```sql
-- 复杂视图:组函数一定要使用别名，否则报错。
create or replace view table1_view
as
select avg(salary) avg_sal,department_id -- 组函数一定要使用别名，否则报错
from employees
group by department_id;  
```

```sql
-- 连接视图 (table1,table2外键为column2)
create or replace view table1_view
as
select column1,t1.column2,column3
from table1 t1,table2 t2
where t1.column2(+) = t2.column2;
```

#### select 查询视图

```sql
select *
from table1_view;
```

#### drop view 删除视图 

- drop view只是删除视图的定义，并不会删除基表的数据。

```sql
drop view table1_view;
```

### 视图DML条件

- 用户可以通过视图对基表执行增删改操作，但是有很多在基表上的限制。

> 若基表中某列不能为空，但是该列没有出现在视图中，则不能通过视图执行insert操作。若基表设置了某些约束，这时候插入视图或者修改视图的值，有可能会报错。

1. 简单视图中可执行DML操作。
2. 视图定义中包含以下元素之一时，不能用delete语句：组函数、group by子句、distinct关键字、rownum伪例
3. 且若还包含列的定义为表达式时，不能用update语句：列的定义为表达式。
4. 且若还包含以下的元素时，不能用insert语句：表中非空的列在视图定义中未包括。

#### with read only 只读视图（屏蔽DML）

- with read only：选读屏蔽，设置只读。对只读视图的任何DML操作都会返回ORACLE SEVER错误。

```sql
create or replace view table1_view(no1,no2)
as
select no1,no2
from table1
with read only;
```

### materialized 物化视图

- 物化视图（相当于一个表）将视图的查询结果存储在数据库中。使用该视图时，使用的是查询的结果，而不是查询表达式。

#### create\|alter materialized view 创建/修改物化视图

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
refresh [ fast|complete|force ]  -- 刷新方法
[
on [commit|demand ]   | -- 刷新模式
start with (start_time) next (next_time) -- 设置刷新时间
]
as 
查询表达式;
```

| 生成数据        | 说明                                     |
| --------------- | ---------------------------------------- |
| build immediate | 创建物化视图的时候就生成数据             |
| build deferred  | 创建时不生成数据，以后根据需要在生成数据 |

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
tablespace test_space  --  日志空间  
with primary key ;      --  指定为主键类型
```

```sql
-- 删除物化视图日志
drop materialized view log on test_table;
-- 删除物化视图        
drop materialized view mv_materialized_test; 
```

##### 刷新时间

```sql
create materialized view mv_materialized_test 
refresh force
on demand 
start with sysdate next to_date( concat (to_char( sysdate + 1 , 'dd-mm-yyyy' ), '10:25:00' ), 'dd-mm-yyyy hh24:mi:ss' ) 
as
select  *  from  user_info;  -- 这个物化视图在每天10：25进行刷新 
```

##### 查询重写（QueryRewrite）

- `enable|disable query rewrite`：指出创建的物化视图是否支持查询重写，默认disable。 查询重写是当对物化视图的基表进行查询时 ， oracle会自动判断能否通过查询物化视图来得到结果，如果可以，直接从已经计算好的物化视图中读取数据。

## 子查询

- 子查询 （内查询） 在主查询之前执行完成，其结果被主查询（外查询）使用。子查询的查询性能通常消耗较大，尽量减少使用子查询。

| 子查询返回       | 说明                     |
| ---------------- | ------------------------ |
| 未返回任何行     | 主查询也不会返回任何结果 |
| 单行结果（标量） | 使用在适用于标量的位置   |
| 多行结果（关系） | 使用在适用于关系的位置   |

### 单行子查询

- 单行子查询只能返回一行结果，不能返回空值、只能使用单行操作符 `=、>、>=、<、<=、<>`。


```sql
-- 显示员工的employee_id,last_name和location。其中，若员工department_id与location_id为1800的department_id相同，则location为’Canada’,其余则 为’USA’。

-- 1.1 case -- 在case中使用单列子查询
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

-- 1.2 case -- 在case中使用单列子查询
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

-- 2. decode
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

### 多行子查询

| 多行操作符     | 描述                                           |
| :------------- | :--------------------------------------------- |
| in<br />exists | 等号列表中任意一个/关系中是否存在满足条件的行  |
| any<br />some  | 和子查询返回的任意一个值比较，只要一个满足即可 |
| all            | 和子查询返回的所有值比较，需要满足所有         |


```sql
-- 返回job_id和141号员工相同，salary比143号员工多的姓名，job_id工资
select last_name
      ,salary
      ,job_id
from employees
where job_id = (
                select job_id
                from employees
                where employee_id = 141
                )
and salary > (
              select salary
              from employees
              where employee_id = 143
              );
```

```sql
-- 返回其他部门中比job_id为'IT_PROG'部门任一工资低的员工的员工号，姓名，job_id和salary
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
-- 查找部门中工资高于部门平均工资的员工
-- 1)
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

-- 2)
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
and a.salary > b.avg_sal;
```

#### in、 exists

| 运算符 | 区别                                                         |
| ------ | ------------------------------------------------------------ |
| in     | 先执行子查询。<br />把外表和内表作hash join。<br />子查询得出的结果集记录较少，主查询中的表较大且又有索引时。<br />不对null进行处理。 |
| exists | 外层表为驱动表，先被访问。<br />对外表作loop，每次loop再对内表进行查询。<br />外层的主查询记录较少，子查询中的表较大且又有索引时。<br />为非关联子查询指定exists子句是不适当的，会产生笛卡乘积。 |

```sql
-- in 以下两个查询等价
select name 
from student 
where name in ('zhang','wang','li','zhao');

select name 
from student 
where name = 'zhang' 
  or name = 'li' 
  or name = 'wang' 
  or name= 'zhao';
```

```sql
-- exists 查询公司管理者的employee_id,last_name,job_id,department_id
select employee_id
      ,last_name
      ,job_id
      ,department_id
from employees outer
where exists(
    select *
    from employees
    where manager_id = outer.employee_id
);
```

```sql
-- not exists 查询departments表中不存在与employees表的部门的department_id,department_name
select department_id
      ,department_name
from departments d
where not exists(
    select 'x'  -- 'x'可以是任何
    from employees
    where department_id = d.department_id
);
```

```sql
-- 带in的关联子查询是多余的，因为in子句和子查询中相关的操作的功能是一样的。

-- 查找员工中在部门的员工姓名
select staff_name 
from staff_member 
where staff_id in (select staff_id 
                   from staff_func 
                   where staff_member.staff_id = staff_func.staff_id
                  );          
-- 修改
select staff_name 
from staff_member 
where staff_id in (select staff_id 
                   from staff_func
                  );
```

```sql
-- 为非关联子查询指定exists子句是不适当的，会产生笛卡乘积。 
select staff_name 
from staff_member 
where staff_id exists (select staff_id 
                       from staff_func
                       );
-- 改为：
select staff_name 
from staff_member 
where staff_id exists (select staff_id 
                       from staff_func 
                       where staff_member.staff_id = staff_func.staff_id
                      );
```

| 运算符     | 对比                                                         |
| ---------- | ------------------------------------------------------------ |
| not in     | 如果查询语句使用了not in，那么内外表都进行全表扫描，而没有用到索引。<br />尽量不要使用not in子句。使用minus 子句都比not in 子句快。 |
| not exists | not extsts 的子查询依然能用到表上的索引。<br />无论哪个表大，用not exists都比not in要快。 |

### 子查询中使用主查询的列

```sql
-- 查询员工中工资大于部门平均工资的员工last_name,salary和department_id
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
-- 若employees表中的employee_id与job_history中employee_Id相同的数目不小于2，输出这些相同的员工的employee_id,job_id
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

### 相关子查询

| 相关子查询 | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| 相关查询   | 在where子句中，可以使用子句中关系的别名。<br />而在from子句中，则不能使用在当前子句中的别名。 |
| 相关更新   | 相关子查询依据一个表中的数据更新/修改另一个表中的数据。      |
| 相关删除   | 相关子查询依据一个表中的数据删除另一个表中的数据。           |

```sql
--  相关更新
update emlpoyees e
set department_name = (
                       select department_name
                       from departments d
                       where e.departmet_id = d.department_id
                       );
```

```sql
--  删除employees表中，基于emp_history表皆有数据
delete from employees e
where (
       select employee_id
       from emp_history
       where employee_id = e.employee_id
       );  
```

### 成对比较 

- 成对比较：多列子查询、主查询与子查询返回的多个列进行比较。

```sql
-- 查询与141号或174号员工的manager_id,department_id相同的其他员工信息

-- 1)成对比较
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
and employee_id not in  (141,174);

-- 2)不成对比较
select employee_id
      ,manager_id
      ,department_id
from employees
where manager_id in (
                     select manager_id
                     from employees
                     where employee_id in (141,174)
                     )
  and department_id in (
                        select department_id
                        from employees
                        where employee_id in (141,174)
                        )
  and employee_id not in (141,174);
```

## join 连接

- join子句可以视为from子句的一部分。
- 多个表的连接需要中间表。

| 连接                     | 说明                                                         |
| ------------------------ | ------------------------------------------------------------ |
| 内连接<br />（常规连接） | 合并具有同一列的两个以上的表的行，结果集不包含一个表与另一个表不匹配的行。<br />不适合查询大表，用来比较表中的行、查询分层数据。 |
| 外连接                   | `[outer] join`                                               |

### join 内连接

#### self/inner join 自连接

- 自连接（self/inner join） 将自身表的一个镜像（通过别名将同一张表视为多张表）当作另一个表来对待。

```sql
select t1.no1,no2,no3
from table1
inner join table1 t1 on no1;
```

```sql
-- 查询所有雇用日期相同(同一天入职)的员工
select e1.employee_id,e1.hire_date
from employees e1
 inner join employees e2 
  on e1.employee_id <> e2.employee_id
  and e1.hire_date = e2.hire_date;
```

#### natural join 自然连接

- natural join（全自动连接、自然连接）：只返回在两个关系中的都拥有的相同属性的相同元组。

- 自然连接的两个表的有多个字段是相同名称、相同数据类型（系列）。如果自然连接的两个表仅是字段名称相同、但数据类型不同，那么将会返回一个错误。
- natural join的select子句中的列不能有限定词，而其他连接都需要限定词。

> 在设计表时，应该尽量在不同表中具有相同含义的字段使用相同的名字和数据类型，以便使用natural join。

```sql
select no1,no2,no4
from tabel1
 natural join table2;
```

```sql
select department_id,
       department_name
from employees
 natural join departments;
```


#### join .. using 同名列连接

-  join .. using：使用同名列的连接。using多表连接时，若列名不同，则不适用。

```sql
select t1.no1,no2,no4
from table1 t1
 join table2 using (no1);
```

#### join .. on 等值连接（where）

```sql
select t1.no1, no2,no4
from table1 t1
 join table2 t2 on t1.no1 = t2.no1
[where...];
```

```sql
select t1.no1, no2, no4
from table1 t1, table2 t2
where t1.no1 = t2.no1;
```

```sql
-- 1.join
select distinct e.department_id,d.department_id
from employees e
join departments d
on e.department_id = d.department_id;

-- 2.where
select distinct e.department_id,d.department_id
from employees e
    ,departments d
where e.department_id = d.department_id;
```

- 多个表的连接需要中间表。

```sql
-- 1.join
select employee_id,e.department_id,department_name,d.location_id,city
from employees e
  join departments d on e.department_id = d.department_id
  join locations l on d.location_id = l.location_id; 
  -- department表中的department_id和location_id

-- 2. where
select employee_id,e.department_id,department_name,d.location_id,city
from employees e,departments d,locations l
where e.department_id = d.department_id
  and d.location_id = l.location_id;
```

### cross join 交叉连接

- cross join（不带on子句）返回被连接的两个表所有数据行的笛卡尔积。返回到结果集合中的数据行数等于第一个表中符合查询条件的数据行数乘以第二个表中符合查询条件的数据行数。

```sql
select t1.no1,no2,no4
from table1 t1
 cross join table2;
```

### \[outer\] join 外连接

| 外连接                                 | 包含from子句中的表与on中的表不匹配的值           |
| -------------------------------------- | ------------------------------------------------ |
| left \[outer\] join<br />（左外连接）  | 当连接条件不成立的时候，等号左边的表仍然被包含。 |
| right \[outer\] join<br />（右外连接） | 当连接条件不成立的时候，等号右边的表仍然被包含。 |
| full \[outer\] join<br />（全连接）    | 查询结果等于左外连接和右外连接的和。             |

```sql
-- left [outer] join可以返回左表(table1)中与右表(table2)不匹配的值
select t1.no1,no2,no4
from table1 t1,table2,t2
 left outer join table2 on t1.no1 = t2.no1;
```

```sql
-- right [outer] join可以返回右表(table2)中与左表(table1)不匹配的值
select t1.no1,no2,no4
from table1 t1
 right outer join table2 on t1.no1 = t2.no1;
```

```sql
-- full [outer] join可以返回左右表中不匹配的行
select t1.no1, no2, no4
from table1 t1
 full outer join table2 on t1.no1 = t2.no1;
```

#### (+) 外连接运算符

- 在连接条件中无匹配行的表的列后面加(+)，没有匹配的行时，结果表中相对应的列为空。

> 哪边无加号(+)就是什么连接。

```sql
-- 左外连接
select t1.no1,no2,no4
from table1 t1,table2 t2
where t1.no1 = t2.no1(+);  

-- 右外连接
select t1.no1,no2,no4
from table1 t1,table2 t2
where t1.no1(+) = t2.no1;
```

## 组函数（聚集函数）

-  组函数 (多行函数) 输入多个参数、或内部扫描多次，输出一个结果。

| 常用组函数 | 说明     |
| ---------- | -------- |
| avg()      | 平均值   |
| count()    | 记录总数 |
| max()      | 最大值   |
| min()      | 最小值   |
| sum()      | 总和     |

- 组函数对null的处理：count()将null看作0来处理、其他组函数忽略输入集合中的null（如果作用在null上，则返回null）。
- 组函数可以相互嵌套。

| 数学                     | 说明                                                         |
| ------------------------ | ------------------------------------------------------------ |
| stdden()<br />variance() | 标准差<br />方差                                             |
| **分级汇总**             | **说明**                                                     |
| rollup()                 | 按分组的第一个列进行统计和最后的小计                         |
| cube()                   | 按分组的所有列的进行统计和最后的小计                         |
| **行列转换**             | **说明**                                                     |
| wm_concat()              | 列转行：对分组函数的每一组结果中的某一指定值域进行整行输出。<br />wm_concat()中使用的列不必出现在group by子句中。 |

### group by

- select子句中所有未包含在组函数的列都应该包含在group by子句，而group by子句中的列不必包含在select子句。

```sql
-- 求表中各部门平均工资
select avg(salary)
from employees
group by department_id;

-- 求部门平均工资的最大值
select max(avg(salary))
from employees
group by department_id;
```

- 多列group by：先按在前的值域进行分组，再依次在之前分组的基础上进行分组。

```sql
-- 按department_id分组后在内部按employee_id分组
select avg(salary), department_id, employee_id
from employees
group by department_id, employee_id;
```

### having

- 可以在having中使用组函数（不能使用组函数的别名），而不能在where子句中使用组函数。sql形成分组后才会使用having子句中的谓语，故可以在having子句中使用组函数（对分组后的数据进行筛选）。

```sql
select job,avg(sal) as avg_sal
from emp
group by job
having avg(sal) > 100
```

## sequence 序列

- sequence（序列）可供多个用户来产生唯一数据的数据库对象，与表无关。自动提供唯一的数值、共享对象、提供主键值。

1. 将sequence装入内存可以提高访问效率
2. sequence序列号是数据库系统按照一定规则自增的数字序列（不会重复）
3. sequence可用于记录数据库中最新动作（CRUD）的语句，序列号都会随着更新。

### create sequence 创建序列

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

- cache不能保证序列号是连续的。如果缓存中的序列号没有用完就关闭数据库等其它原因，没有使用的序列号就丢失（跳号）。如果指定cache值，ORACLE就可以预先在内存里面放置一些sequence，cache里面的取完后，oracle自动再取一组到cache。
- 序列装入内存可提高访问速度，但会在以下的情况下出现裂缝（跳号）：回滚、系统异常、多个表同时使用同一个序列。

> SEQUENCE\_CACHE\_ENTRIES参数，设置能同时被cache的sequence数目。

### \.nextval、\.currval 序列移动

| 操作      | 说明                                                         |
| --------- | ------------------------------------------------------------ |
| \.nextval | 引用下一序列值。<br />第一次\.nextval返回的是初始值；随后的每次\.nextval都会触发自动增长（increment by），返回增加后的值。 |
| \.currval | 引用当前序列值。<br />序列创建后初始无值，无法查询当前值.currval。第一次使用序列时，要先查询下一值（\.nextval）才能查询当前值（\.currval）。 |

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

-- insert语句的values子句
insert tab_name into values(1, 'xyz', sequence_name.nextval); 
-- 或者 insert tab_name into values(1, 'abc', nextval for sequence_name);
insert tab_name into values(2, 'fgh', sequence_name.nextval); 
-- 或者 insert tab_name into values(2, 'fgh', nextval for sequence_name);

-- update语句的set子句
update tab_name 
set col_varchar = '678', col_seq = sequence_name.nextval 
where col_int = 2; 

delete tab_name col_sql = sequence_name.nextval 
where col_int = 1;
```

### alter seq\_name 修改序列 

- 若要修改start with、minvalue的值，则必须删除序列后再重新建立序列。
- 可修改序列的增量、最大值、循环选项、是否装入内存。修改之后，只有将来的序列值会被改变。

### drop sequence 删除序列

```sql
drop sequence emp_seq;
```

## index 索引 

- 创建索引就是对某些特定列中的数据排序，生成独立的索引表。在某列上创建索引后，如果该列出现在查询条件中，Oracle会自动的引用该索引，先从索引表中查询出符合条件记录的rowid（字段物理地址），根据rowid快速的定位到具体的记录。

1. 索引是独立于表的模糊对象，可以存储在与表不同的磁盘/表空间。
2. 索引被删除/破坏，不会对表产生影响，其影响的只是查询的速度。
3. 索引一旦建立，Oracle管理系统会对其进行自动维护，且由Oracle管理系统决定何时引用索引，用户不用在查询语句中指定使用哪个索引。
4. 删除表时，所有基于该表的索引会自动被删除。
5. 表中的数据非常多时，引用索引带来的查询效率非常可观。但索引会减慢数据插入表的速度（插入数据时也要维护索引）。

### create index 创建索引

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
-- 在一个或多个列上创建索引
create index emp_last_name_index on employees(last_name);
```

| 索引的类型                     | 说明                                                         |
| ------------------------------ | ------------------------------------------------------------ |
| b-tree索引                     | create index创建的普通索引就是b-tree索引，没有特殊的必须应用在哪些数据上。 |
| bitmap位图索引                 | 位图索引经常应用于列数据只有几个枚举值的情况，查询效率将会最快。 |
| 函数索引                       | 适合对某个字段做查询的时候经常是带函数操作的。               |
| hash索引                       | 创建hash索引必须使用hash集群，需要在创建hash集群的时候指定这个集群键值。<br />存储数据时，所有相关集群键的行都存储在一个数据块当中，只要定位到hash键，就能快速定位查询到数据的物理位置。 |
| reverse反向索引                |                                                              |
| 分区索引<br />分区表的全局索引 | 应用在分区表上面的索引。<br />分区索引是对分区表内的单个分区进行数据索引。<br />分区表的全局索引对分区表的全表进行全局索引。 |

### drop index 删除索引

- drop index不可回滚，只有索引的拥有者或拥有drop anyindex权限的用户才可以删除。

```sql
drop index emp_last_name_index;
```

### alter index 修改索引

#### rename to 重命名索引

```sql
alter index 旧索引名 rename to 新索引名;
```

#### coalesce 合并索引、rebuild 重新构造索引

- 索引建好后，经过很长一段时间的使用，索引表中存储的空间会产生一些碎片，导致索引的查询效率会有所下降，此时考虑合并索引、按照索引规则重新分类存储、或选择删除索引重新构造索引。

```sql
-- 合并索引
alter index 索引名 coalesce;

-- 重新构造
alter index 索引名 rebuild;
```

## synonym 同义词

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

> SCOTT用户权限不足：登入sysdba授予同义词权限
>
> ```sql
> grant create synonym to scott;
> ```

| 权限    | 说明                                                         |
| ------- | ------------------------------------------------------------ |
| public  | public组，每个用户都可以访问。                               |
| private | 默认，对象所有者。只有对象所有者显式授权后其他用户才可访问。 |

### 创建同义词

```sql
create [public] synonym 同义词 for 对象;
```

```sql
create synonym synonym_test for emp;
```

### 删除同义词

- 若删除public同义词，则需要指定public关键字。

```sql
drop [public] synonym 同义词;
```

# PL/SQL

## PL/SQL语句块

```sql
-- 打开屏幕输出
set serveroutput on
```

```plsql
--declare
  --声明的变量、类型、游标
begin
  --程序的执行部分（类似于java里的main()方法）
  dbms_output.put_line('helloworld');
--exception
  --针对begin块中出现的异常，提供处理的机制
  --when .... then ...
  --when  .... then ...
end;
```

- PL/SQL程序块的结束并不自动结束事物，必须显示地使用commit语句和rollback语句。

> PL/SQL developer中会自动提交。

- PL/SQL语句中不能直接使用组函数，只能在其嵌入的SQL语句中使用。

- execute（exec）：执行PL/SQL语句。

```plsql
begin
 execute immediate '
   create or replace procedure show_time 
   is
   begin
     dbms_output.put_line(''当前时间：''||sysdate);
   end;
 ';
end;
```

### 字符分隔符 q\'

- `q'`操作符声明一个定界符。可以指定任何字符作为定界符，只要这个字符没有出现在字符串中。

| 引号   | 使用位置                                                     |
| ------ | ------------------------------------------------------------ |
| 单引号 | 字符型：'hello' <br />日期型：'17-12月-80' <br />to\_char/to\_date(日期, \'YYYY-MM-DD HH24:MI:SS\') |
| 双引号 | 列别名：select ename \"姓 名\" from emp;<br />to_char/to\_date(日期, \'YYYY\"年\"MM\"月\"DD\"日\" HH24:MI:SS\') |
| 逃逸符 | 字符型、日期型等内部使用单引号时，使用两个连续的单引号\' \'来表示一个单引号。<br />第一个单引号为逃逸符，用来消除第二个单引号的特殊含义。 |

> 定界符：用来在字符串中使用特殊字符（如：'）,在两个定界符中可以当作普通字符使用。
>
> | 简单定界符 |    含义    |
> | :--------: | :--------: |
> |     \+     | 加法运算符 |
> |     \-     | 减法运算符 |
> |     \*     | 乘法运算符 |
> |     /      | 除法运算符 |
> |     =      | 相等操作符 |
> |     ;      | 语句结束符 |
> |     @      | 远程访问符 |
>
> | 组合定界符 |      含义      |
> | :--------: | :------------: |
> |    \|\|    |   连接操作符   |
> |     :=     |   赋值操作符   |
> |    \!=     |   不等运算符   |
> |    \<\>    |   不等运算符   |
> |    /\*     | 开始注释定界符 |
> |    \*/     | 结束注释定界符 |
> |    \-\-    |   单行注释符   |

```plsql
declare
 v_special_day varchar2(250);
begin
  -- q'将!作为定界符
  v_special_day := q'!Happy Woman's Day on 8th March!';
  dbms_output.put_line(v_special_day);
  
  -- q' 将[]作为定界符
  v_special_day := q'[TO many People,"today']'; 
  dbms_output.put_line(v_special_day);
  
  -- ''逃逸符号
  v_special_day := '你好''呀';
  dbms_output.put_line(v_special_day);
end;

/******************
Happy Woman's Day on 8th March
TO many People,"today'
你好'呀
PL/SQL procedure successfully completed
*/
```

## 变量

- 命名规范：必须以英文字母开始、可以包含一个或多个英文字母或数字、可以包含最多30个字符、只能包含特殊字符`$, _, #`、不能与数据库的表或者列同名、不能是关键字。

|               标识符                |     命名惯例      |
| :--------------------------------: | :--------------: |
|                变量                |      v_name      |
|                常量                |      c_name      |
|    `SQL*PLUS`替代变量（替代参数）    |      p_name      |
| `SQL*PLUS`全局变量（宿主或绑定变量） |      g_name      |
|                游标                |   name_cursor    |
|                异常                |      e_name      |
|            PL/SQL表类型             | name_table_type  |
|         PL/SQL表类型的变量          |    name_table    |
|              记录类型               | name_record_type |
|           记录类型的变量            |   name_record    |

- Oracle处理变量、参数、表、列的优先级：

1. PL/SQL首先检查数据库表中列的名字。
2. 数据库表中的列名优先于本地变量名。
3. 本地变量名和形参名优先于数据库的表名。

### 声明、初始化

- 变量的声明和初始化：在引用PL/SQL程序块中的变量之前，必须在声明段声明所有的变量。在声明变量的同时，可以为变量赋初值，但并不是必须的。如果在一个变量声明中引用了其他变量，一定要确保在之前的语句中已经声明了所引用的变量。

```plsql
变量名 [constant] 数据类型 [not null]
    [:= | default 表达式];   --初始化赋值
```

| 选项              | 说明                         |
| ----------------- | ---------------------------- |
| constant          | 常量，声明时必须初始化。     |
| not null          | 非空变量，声明时必须初始化。 |
| `:=`<br />default | 初始化赋值。                 |

- variable直接在SQL环境中定义。只能是SQL允许的数据类型、不能进行初始化。

```plsql
variable 变量名 数据类型;
```

```plsql
declare 
 v_desc varchar2(100)
   := '中国妇女革命先锋: ';
 v_pioneer varchar2(25);  --默认空值 NULL
begin
  dbms_output.put_line(v_desc || v_pioneer);
  v_pioneer := '苏妲己';
  dbms_output.put_line(v_desc || v_pioneer);
end;
```

### select .. into .. 变量赋值

```plsql
declare
  --声明变量
  v_name varchar2(25);
  v_email varchar2(25);
  v_salary NUMBER(8, 2);
  v_job_id varchar2(10);
begin
  --通过 select ... into ... 语句为变量赋值
  --被赋值的变量与select中的列名要一一对应
 select last_name, email, salary, job_id 
 into v_name, v_email, v_salary, v_job_id
 from employees
 where employee_id = 186;
 -- 打印变量的值
 dbms_output.put_line(v_name || ', ' || v_email || ', ' ||  v_salary || ', ' ||  v_job_id);
end;
```

### 绑定变量（bind variable）

- 绑定变量（`:变量`）：重复利用执行计划，使得每次提交的sql语句都完全一样。

```plsql
select fname, lname, pcode 
from cust 
where id = :cust_no;
```

```plsql
sql> variable x number;
sql> exec :x := 123;
sql> select fname, lname, pcode from cust where id =:x;
```

> 在pl/sql中，引用变量（p_empno）即是引用绑定变量。
>
> ```plsql
> create or replace procedure dsal(p_empno in number)
> as
>   begin
>     update emp
>     set sal=sal*2
>     where empno = p_empno;
>     commit;
>   end;
> ```

### 数据类型

| 数据类型  | 说明                                                         |
| --------- | ------------------------------------------------------------ |
| 标量      | 只保持一个单一的值，而这个值依赖与变量的数据类型。<br />SQL中的数据类型。 |
| 组合      | 组合数据类型包括内部元素（结构），而这些元素既可以是标量数据类型也可以是组合数据类型。<br />record（记录）、PL/SQL table。 |
| 引用      | 引用数据类型保持指向一个存储位置的指针值。                   |
| 大对象LOB | 大对象数据类型保持被称为定位器（指针）的值，这个定位器声明在表之外的大对象（声音，图形）的位置（地址）。<br />在数据库中，表中的列可以是LOB数据类型（CLOB, BLOB等）。<br />LOB数据类型可以在数据库中存储大量的无结构数据块（正文，图形，声音和影像信息），存储量最多可达128T（数据量的大小取决于数据块的大小）。<br /> LOB数据类型允许高效，随机和分段的访问大量的无结构数据。 |

| LOB      | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| CLOB     | 存储单字节的大数据对象，如演讲稿、说明书、简历等。           |
| BLOB     | 存储大的二级制对象，如图片、幻灯片等。<br />从数据库中提取这样的数据、向数据库中插入这样的数据时，数据库并不解释这些数据，使用这些数据的外部应用程序必须自己解释这些数据。 |
| DBMS_LOB | 对于CLOB和BLOB数据类型的列，许多操作是不能直接使用Oracle的数据库命令来完成的。<br />维护LOB数据类型的列。 |
| BFILE    | 在数据库外的操作系统文件中存储大的二级制对象，如电影胶片等。<br />与其他LOB数据类型不同，BFILE数据类型是外部数据类型。<br />BFILE类型的数据是存储在数据库之外的，他们可能是操作系统文件。实际上，数据库中只存储了BFILE的一个指针，因此定义为BFILE数据类型的列是不能通过Oracle的数据库命令来操作的，这些列只能通过操作系统命令或者第三方软件来维护。 |
| NCLOB    | 存储Nchar类型的单字节或定长字节的Unicode大数据对象。         |

### （全局变量）宿主变量（主机变量）（替代变量和绑定变量）
- 宿主变量：在调用PL/SQL程序的环境中声明、在PL/SQL中使用非PL/SQL的变量。

**替代变量和绑定变量**

- 因为PL/SQL本身没有输入或输出功能，所以必须依赖于执行PL/SQL程序的环境将变量的值传入或传出PL/SQL程序块。

- 在`SQL*Plus`环境中，可以使用`SQL*Plus`的替代变量将运行时的值传给PL/SQL程序块。在PL/SQL程序块中也可以使用前导的&符号引用替代变量，就像在SQL语句中引用`SQL*PIus`的替代变量一样。在PL/SQL程序块执行之前，正文的值被替代进PL/SQL程序块中。因此，不能够使用循环为替代变量赋予不同的值，而只有一个值将替代这个替代变量。
- 绑定变量(bind variables)是在宿主环境（调用PL/SQL的程序或工具，如`SQL*Plus`)中创建的变量。也正是由于这一原因，绑定变量有时也被称为宿主变量(host variables)。
- 绑定变量是在使用（或调用）PL/SQL的环境中创建的，而不是在PL/SQL程序的声明段中定义的。在一个PL/SQL程序块中声明的所有变量只在执行这个程序块时可以使用。而在这个程序块执行之后，这些变量所使用的内存就都释放了。然而，绑定变量则不同，在程序块执行之后，绑定变量依然存在并可以访问。正因为如此，绑定变量可以在多个子程序（程序块）中使用。绑定变量既可以用在SQL语句中，也可以用在PL/SQL程序块中，就像其他任何类型的变量一样。也可以将绑定变量作为运行时的值传给PL/SQL子程序或从PL/SQL子程序中传出。
- 在`SQL*Pus或SQL Developer`中创建一个绑定变量，需要使用`SQL*Plus`的variable命令，可以使用variable命令定义NUMBER和varchar2类型

`SQL*Plus`变量，如

```
variable g_dog_weight NUMBER
variable g_pioneer varchar2(25)
```

- 当使用`SQL*Plus`客开发和执行一个PL/SQL程序是，`SQL*Plus`就成了这个PL/SQL程序的宿主环境。在`SQL*Plus`中声明的变量被称为宿主变量（绑定变量）。同理：Oracel form。

SQL Developer也可以引用绑定变量并通过使用PRINT命令显示这个绑定变量的值。可以通过在绑定变量之前冠以冒号(：)的方式在PL/SQL程序块中引用绑定变量。
- 在PL/SQL中使用`SQL*Plus`的变量（绑定变量），必须冠以冒号。在PL/SQL程序段执行之后，这个变量仍然会存在。
   - 如果绑定的变量是NUMBER类型，那么不能指定精度和数值。
   - 如果绑定的变量是varchar2类型，那么可以指定字符串的长度（字节）

```
--SQL*Plus
variable g_dog_weight NUMBER

--PL/SQL
begin 
  :g_dog_weight := 38;
end;
```

##### 例

```
--SQL*PLUS
variable g_ename varchar2(15);
variable g_sal NUMBER;
variable g_dept_id NUMBER;

--PL/SQL
begin
  select last_name
        ,salary
        ,department_id
  into :g_ename
      ,:g_sal
      ,:g_dept_id
  from employees
  where employee_id = 112;
end;

--SQL*PLUS
PRINT g_ename;
PRINT g_sal;
PRINT g_dept_name;
```

##### 例

```
--SQL*PLUS
set VERIFY OFF;  --关闭替换变量内容的显示
set AUTOPRINT on;

variable g_ename varchar2(20);
variable g_sal NUMBER;
variable g_dept_id NUMBER;

--PL/SQL
declare
 v_emp_id NUMBER(10) := &employee_id;
begin 
  select last_name
        ,salary
        ,department_id
  into :g_ename
      ,:g_sal
      ,:g_dept_id
  from employees
  where employee_id = v_emp_id;
end;
```

### %TYPE属性

使用%TYPE属性按照之前已经声明过的变量或者数据库中的列来声明一个变量。

1.

```
变量名 表名.列名%TYPR
```

2.

```
变量名 之前定义的变量名%TYPE    
```

- 通过%TYPE获取的列或变量的数据类型，会随着之前定义的变量名/列的数据类型的变化而变化
- not null 约束不适用与使用%TYPE属性声明的变量。
    - 但是，如果使用%TYPE属性声明的变量是基于一个定义了not null约束的列，那么是可以将空值赋予这个变量的。

#### 优点

1. 避免由于数据类型不匹配或精度不对所引起的错误
2. 避免代码的硬编码（变量的数据类型和精度必须与操作的列或变量相匹配）
3. 如果列的定义发生了变化，则不再需要修改变量的声明。如果为某个特定的表声明了几个变量并且没有使用%TYPE属性声明，当声明变量所基于的列（的定义）校变更时，PL/SQL是在编译这个程序头时定变量的数据类型和大小（精度）的。这样也就确保使用%TYPE属性声明的变量总是所基于（操作）的列相匹配。

#### 缺点

1. 当然%TYPE属性也不例外。%TYPE属性是具有一定的额外开销的，因为为了获取数据库中列数据类型，PL/SOL隐含地发出了一个查询(select)语句。如果PL/SQL代码是存放在客户工具中的，那么在每次执行PL/SQL程序块时都必须执行这个查询语句。
2. 如果PL/SQL程序代码是存储过程（也可以是存储函数），那么列的定义或变量的定义是作为p-code（parsed code)的一部b存储在数据库中的，因此也就没有以上所说的额外开销了。
3. 然而，如果表的定义发生了变化，系统会强行重新编译相关的PL/SQL程序代码。在Oracle11g和Oracle12c中放宽了这一方面的限制，如果在修致表的定义时没有涉及到PL/SQL程序代码所使用的列，那么这个PL/SQL程序代码块仍然是有效的，因此也就不需要重新编译了。

### 布尔变量的声明与使用

#### 布尔变量的特性：

- 只有值TRUE/FALSE/NULL可以赋给一个布尔变量
- 可以通过and/OR/NOT这些逻辑操作符对变量进行比较
- 这些变量总是产生TRUE/FALSE/NULL
- 数字，字符和日期表达式可以被用来返回一个布尔值

**NULL表示未知无法确定的值**

可以使用比较运算符来构造布尔表达式

不等于：<> 和 != 和 ~= 和 ^=


| 操作符    |
| :-- |
|   =  |
|   >  |
|   >=  |
|   <  |
|   <=  |
|   <>  |
|   between ... and ...  |
|  IN(...)   |
|   LIKE  |
|  is NULL   |

### 布尔条件

**TRUE/FALSE/NULL**


#### NULL

1. 涉及空值的简单比较总是产生NULL
2. 将逻辑运算符NOT应用于NULL产生NULL
    - 注：语句：not null 非空值
3. 在条件控制语句中，如果条件为NULL，则不执行
4. 在算术表达式中，只要有NULL，则整个表达式的结果就是NULL

#### 逻辑运算符

##### and:

- FALSE > NULL > TRUE

- FALSE and NULL = FALSE
- TRUE and NULL = NULL
- TRUE and FALSE = FALSE

##### OR

- TRUE > NULL > FALSE
- FALSE and NULL = NULL
- TRUE and NULL = TRUE
- TRUE OR FALSE = TRUE

##### NOT

- NOT TRUE = FALSE
- NOT FALSE = TRUE
- not null = NULL

###  程序块的嵌套和变量的作用域

- 只要允许可执行语句的地方，就可以进行PL/SQL块的嵌套。一个嵌套块变成一个语句。
- 异常段也同样可以包含多个嵌套的程序块。
- 一个标识符的作用域是可直接引用该标识符的一个程序单元的区域
    - 由于程序块的嵌套就很容易造成变量名（标识符）的重名，虽然在同一个程序块中同一个标识符不能声明两次，但是在两个不同的程序块中所声明的标识符是可以同名的。实际上，两个同名的标识符（变量）是完全不同的变量，其中一个变量的修改完全不会影响另一个.（一个变量（标识符对象)的作用域就是可以直接引用这个变量的程序区域（程序块、过程、函数或软件包），一个标识符（变量）只在这一区域是可见的，在该区域可以使用非限定名称的方式来引用这一标识符。
- 在一个PL/SQL程序块中声明的变量（标识符）对于这一程序块本身来说是局域（本地）变量，而对于所有其子块就是全局变量。
   - 如果一个全局变量在一个子块中又再次进行了声明（与全局变同名)，那么，这两个变量的作用域就重叠了。然而，在子块中只有本地变量是可见的，因为要用同名的全局变量必须使用限定名称的方式来引用这个全局变量。如果变量是在同一级别的不同序块中声明的，则是不能引用的，因为这些变量对于该程序块来说既不是本地变量也不是全局变量。
   
- 一个变量（标识符）在声明它的程序块中是可见的，并且在所有嵌套的子块中也是可见的。如果一个PL/SQL块没有发现本地声明的变量，该PL/SQL程序块将向上查找包含它的父块。程序块绝不会向下查看所包含的子块或查找同一级别（没有嵌套关系）块。作用域适用于所有的对象，包括变量、游标、用户定义的异常和约束。

#### 例:全局变量和局部变量
其中，变量v_mumdog_sex和v_mumdog_weight是在外层（父）块中定义的，而变量v_babydog sex和v_babydog_weight是在内层（子）块中定义的。v_mumdog sex和vmumdog weight就是子块的全局变量，因此在子块中可以直接引用。但是在父块中是不能引用子块中声明的变量的，因为这些变量对父块是不可见的。

```plsql
declare
 v_mumdog_sex char(1) := 'f';
 v_mumdog_weight NUMBER(5,2) := 63;
begin
  declare
   v_babydog_sex char(1) := 'm';
   v_baby_dog_weight NUMBER(5,2) := 3.8;
  begin
    dbms_output.put_line(v_babydog_sex);
    dbms_output.put_line(v_baby_dog_weight);
    dbms_output.put_line(v_mumdog_sex);
    dbms_output.put_line(v_mumdog_weight);
  end;
    dbms_output.put_line(v_mumdog_sex);
    dbms_output.put_line(v_mumdog_weight);
    
    dbms_output.put_line(V_baby_dog_sex);
end;
    /*
ORA-06550: 第 17 行, 第 26 列: 
PLS-00201: 必须声明标识符 'V_BAby_DOG_SEX'
ORA-06550: 第 17 行, 第 5 列: 
PL/SQL: Statement ignored
    */
```

#### 例：变量重名

```
declare 
  person_name varchar2(25) := '外';
  person_id NUMBER(10) := 1;
begin
  declare
   person_name varchar2 (10):= '内';
   person_id NUMBER := 0; 
  begin
    dbms_output.put_line(person_name || person_id);
  end;
  
  dbms_output.put_line(person_name || person_id);
end;

******************
内0
外1
PL/SQL procedure successfully completed
```

#### 外层定义标号`<<label>>`

注意：GOTO语句中的`<<label>>`

```
<<label>>
declare
 ...
begin
 ...
end label;
```

```
<<father>>
declare 
  person_name varchar2(25) := '外';
  person_id NUMBER(10) := 1;
begin
  <<son>>
  declare
   person_name varchar2 (10):= '内';
   person_id NUMBER := 0; 
  begin
    dbms_output.put_line(person_name || person_id);
  end son;
  
  dbms_output.put_line(person_name || person_id);
end father;

******************
内0
外1
PL/SQL procedure successfully completed
```

### into子句（SQL中禁止）

- 通过select子句提取（查找到）数据（表中列的值），而通过into子句将提取的数据存放在PL/SQL的变量中。

```
select 查询列表
into [变量名[,变量名]...]
     [记录名]
from 表
[where 条件表达式];
```

- 在PL/SQL的select语句中，into子句是强制性的（必须存在），
- 并且into子句只能放在select子句和from子句之间
- into子句被用来指定变量，这些变量被用来存放从select子句中返回的值。
- into子句中必须为每一个select语句选择的项（列/表达式）指定一个变量（PL/SQL变量或者宿主变量等），并且变量的顺序必须与所选择的项相对应。
- 每一个PL/SQL的select语句必须返回且只能返回一行数据。

#### 例1

```
declare
 v_dept_id departments.department_id%TYPE;
 v_dept_name departments.department_name%TYPE;
begin
  select department_id
        ,department_name
  into v_dept_id
      ,v_dept_name
  from departments
  where department_id = 80;
end;
```

## 流程控制语句

### 条件判断

#### 1)IF

```plsql
IF …… then
……;
ELSIF …… then
……;
ESLE
……;
end IF;
```

```plsql
declare
 v_age NUMBER(3) := &p_age;
begin
  IF v_age < 18 then
    dbms_output.put_line('未成年');
  ELSIF v_age < 60 then
    dbms_output.put_line('中年');
  ELSE 
    dbms_output.put_line('老年');
  end IF;
end; 
```

```plsql
/*
查询150号员工的工资
若工资大于10000，则打印'salary > 10000'，
若在5000到1000之间则打印'5000 <= salary <= 10000'
否则打印'salary < 5000'
*/
-- 1.
declare
  v_emp_sal employees.salary%type;
begin
  select salary 
  into v_emp_sal
  from employees
  where employee_id = 150;
  
  IF v_emp_sal > 10000 then 
    dbms_output.put_line('salary >= 10000');
  ELSIF v_emp_sal between 5000 and 10000 then 
    dbms_output.put_line('5000 <= salary <= 10000');
  ELSE 
    dbms_output.put_line('salary < 5000');
  end IF;
end;

-- 2.使用赋值给v_temp表来打印
declare 
 v_emp_sal employees.salary%type;
 v_temp varchar2(25);
begin
  select salary
  into v_emp_sal
  from employees
  where employee_id = 150;
  
  IF v_emp_sal > 10000 then  
    v_temp := 'salary > 10000';
  ELSIF v_emp_sal between 5000 and 10000 then 
    v_temp := '5000 <= salary <= 10000';
  ELSE 
    v_temp := 'salary < 5000';
  end IF;
  
  dbms_output.put_line(v_temp);
end;
```

#### 2)CASE  --DECODE()只能在SQL中使用

1. CASE语句

```plsql
CASE 选择器
 when 表达式 then...
 when   ... then...
   ...
 [ELSE...]
end；
```

2. CASE表达式

```plsql
CASE
  when 布尔条件表达式 then...
   ...
  [ELSE...]
end;
```

##### 例1

```plsql
/*
查询150号员工的工资
若工资大于或等于10000，则打印'salary >= 10000'，
若在5000到1000之间则打印'5000 <= salary < 10000'
否则打印'salary < 5000'
*/

declare
 v_emp_sal employees.salary%type;
 v_temp varchar2(25);
begin
  select salary
  into v_emp_sal
  from employees
  where employee_id = 150;
  v_temp :=
  CASE TRUNC(v_emp_sal/5000)
    when 0 then 'salary < 5000'
    when 1 then '5000 <= salary < 10000'
    when 2 then 'salary >= 10000'
  end;
  dbms_output.put_line(v_temp);
end;
```

##### 例2

```plsql
/*
查询122号员工的job_id，
若其值为'IT_PROG' 则打印'a'
       'AC_MGT' 则打印'b'
       'AC_account' 则打印'c'
       否则打印'd'
*/

declare
 v_emp_job employees.job_id%type;
 v_temp varchar2(25);
begin
  select job_id
  into v_emp_job
  from employees
  where employee_id = 122;
  v_temp :=
  CASE v_emp_job
    when 'IT_PROG' then 'a'
    when 'AC_MGT' then 'b'
    when 'AC_account' then 'c'
  ELSE 'd'
  end;
  dbms_output.put_line(v_temp);
end;
```

##### 例3

```plsql
declare
 v_emp_id NUMBER := &p_emp_id;
 v_last_name employees.last_name%TYPE;
 v_job_id employees.job_id%TYPE;
 v_sal employees.salary%TYPE;
 
 v_sal_increment NUMBER;
begin
  select job_id
  into v_job_id
  from employees
  where employee_id = v_emp_id;
  
  CASE v_job_id
    when '%IT%' then
      v_sal_increment := 1.45;
    when '%AC%' then
      v_sal_increment := 1.3;
    ELSE
      v_sal_increment := 1.1;
  end CASE;
  
  select employee_id
        ,last_name
        ,salary * v_sal_increment
  into v_emp_id
      ,v_last_name
      ,v_sal
  from employees
  where employee_id = v_emp_id;
 
  dbms_output.put_line(v_job_id ||'部门的'|| v_last_name ||'，加薪后的工资：' || v_sal);
end;     
```

##### 思考：为什么例2的CASE...end;不能有CASE，而例3的CASE...end;必须有end CASE；

- 例2中的CASE语句

```plsql
变量 :=
CASE 选择器
 when 条件值 then
   赋值 --没有分号，不是语句，只是一个值
 end;  --不需要end CASE;
```

- 例3中的CASE语句

```plsql
CASE 选择器
  when 条件值 then
    语句; --有分号；代码块；不是简单的值。
end CASE;
```

### 循环结构

- 初始化条件 循环体 循环条件 迭代条件

- 使用循环：
   - 如果语句在循环中至少执行一次，一般使用LOOP
   - 如果在每次开始重复是都必须测试条件，一般使用WHILE
   - 如果重复的次数已知，一般使用for

#### 1)LOOP

```plsql
LOOP
执行语句；
...
exit [when 条件表达式];
--如果exit语句放在循环体后面，则必定先执行一次循环
--在不满足条件时，也会多执行一次执行
--如果exit语句放在循环体前面，则先检查是否满足条件
end LOOP;
```

```plsql
--使用循环语句打印1~100
-- 1.
 declare
 v_i NUMBER(10) := 1;
begin
  LOOP
    dbms_output.put_line(v_i);
  exit when v_i >= 100;
  v_i := v_i + 1;
  end LOOP;
end;
 
--2.
 declare
 v_i NUMBER(10) := 1;
begin
  LOOP
    dbms_output.put_line(v_i);
    v_i := v_i + 1;
  exit when v_i > 100;
  end LOOP;
end;
```

```plsql
--使用循环语句往表中插入数据

CREATE table emp_p1
(
 emp_no NUMBER(10)
,hire_date DATE
,job varchar2(20)
,sal NUMBER(10)
,depart_no NUMBER(10)
)

declare 
  v_emp_no emp_p1.emp_no%TYPE;
  v_dept_no emp_p1.depart_no%TYPE := 12;
  v_sal emp_p1.sal%TYPE := 12990;
  v_hire_date emp_p1.hire_date%TYPE := TO_DATE('1999-02-02','yyyy-mm-dd');
  v_job emp_p1.job%TYPE := 'counter';
  
  v_counter NUMBER(2) := 1;
  v_max_num NUMBER(2) := 3;
begin 
  select NVL(MAX(emp_no),0)
  into v_emp_no
  from emp_p1;
  
  LOOP
    INSERT into emp_p1(emp_no,depart_no,sal,hire_date,job)
    VALUES((v_emp_no + v_counter),v_dept_no,v_sal,v_hire_date,v_job);
    
    v_counter := v_counter + 1;
    
    exit when v_counter > v_max_num;
    
   end LOOP;
end;
```

#### 2)for

```plsql
for 循环计数器 IN [REVERSE] 下限……上限 LOOP
要执行的语句;
end LOOP;
```

- 每循环一次，循环变量自动加1；
- 如果下限和上限不是整数，则自动四舍五入，如果四舍五入后，下限大于上限则for循环不会被执行

**原则**

1. 只在循环中引用计数器，计数器在循环之外没有定义
2. 不用为计数器赋值，即不要将计数器变量赋值赋值符号左边
3. 上限和下限都不应该为null

##### 关键字REVERSE

- 反向for循环
- 使用关键字REVERSE，循环变量自动减1.
- 跟在REVERSE后的数字必须是从小到大的顺序，且必须是整数，不能是变量或表达式

##### 可以使用exit退出循环

- 只有exit when 表达式中表达式的结果是TRUE才会执行exit；
- NULL或者FASLE不执行

```plsql
begin
  for i IN 最小..最大 LOOP
    循环体;
    [exit [标号] when 条件;]
  end LOOP;
end;
```

```plsql
--使用循环语句打印1~100
begin
  for i IN 1..100 LOOP
    dbms_output.put_line(i);
  end LOOP;
end;
```

```plsql
--输出2~100之间的质数
declare
  v_flag NUMBER(1) := 0;
begin
   for i IN 2 .. 100 LOOP
       v_flag := 1;
       for j IN 2 .. SQRT(i) LOOP
           IF i MOD j = 0 then
              v_flag := 0;  
           end IF;        
       end LOOP;
       IF v_flag = 1 then
           dbms_output.put_line(i);
       end IF;
   end LOOP;
end;
```

#### 2)WHILE

- 在WHILE LOOP循环语句中，循环的条件必须放在WHILE和LOOP两个关键字之间，而循环的条件是每次开始循环前检查的。

```plsql
WHILE 条件 LOOP
  循环体;
end LOOP;
```

```plsql
CREATE table dept_p1
(
 dept_no NUMBER(10)
,loc varchar(50)
)

CREATE SEQUENCE dept_no_seq
 START WITH 1
 INCREMENT by 1
 MINVALUE 1
 MAXVALUE 99
 CYCLE
 NOCACHE;

declare 
 v_dept_no dept_p1.dept_no%TYPE;
 v_loc dept_p1.loc%TYPE := '&p_loc';
 
 v_counter NUMBER(2) := 1;
 v_max_num NUMBER(2) := &p_max_num;
begin
  WHILE v_counter <= v_max_num LOOP
    INSERT into dept_p1(dept_no,loc)
    VALUES(dept_no_seq.NEXTVAL,v_loc);
    
    v_counter := v_counter + 1;
  end LOOP;
end;
```

```plsql
--输出2~100的质数
declare
  v_flag NUMBER(1):=1;
  v_i NUMBER(3):=2;
  v_j NUMBER(2):=2;
begin
  WHILE (v_i<=100) LOOP
        WHILE v_j <= sqrt(v_i) LOOP
              IF (MOD(v_i,v_j) = 0) then v_flag := 0;
        end IF;
        v_j := v_j + 1;
        end LOOP;
  IF (v_flag=1) then dbms_output.put_line(v_i);
  end IF;
        v_flag :=1;
        v_j := 2;
        v_i :=v_i +1;
  end LOOP;
end;
```

#### 3)GOTO语句 无条件跳转到指定的标号中去 (非必要不使用)

```plsql
GOTO label;
……
<<label>>
```

```plsql
--输出2~100之间的质数
declare
 v_flag NUMBER(5) := 1;
begin
  for i IN 2 .. 100 LOOP
    for j IN 2 .. SQRT(i) LOOP
      IF MOD(i,j) = 0 then v_flag := 0;
      GOTO label;
      end IF;
    end LOOP;
    <<label>>
    IF v_flag = 1 then dbms_output.put_line(i);
    end IF;
    v_flag := 1;
  end LOOP;
end;
```

#### 循环的嵌套和标号

- 标号：`<<label>>`
   - 标号必须放在一个语句之前，可以是同一行
   - 在基本循环LOOP中，标号要放在关键字LOOP之前
   - 在for和WHILE循环中，标号要放在关键字for和WHILE之前。
   - 如果一个循环加上了标号，在end LOOP之后可以包含标号名（非强制）

```plsql
declare
 v_i NUMBER := 1;
begin
 <<i_LOOP>>
 LOOP
  v_i := v_i + 1;
  dbms_output.put_line(v_i);
  exit when v_i >= 10;
 end LOOP i_LOOP;
end;
```

```plsql
declare
 v_total NUMBER := 0;
 v_factorial NUMBER := 1;
 v_num NUMBER := &p_num;
 
begin
  <<out_loop>>
  for i IN 1..v_num LOOP
    v_total := v_total + i;
    dbms_output.put_line('1~' || i || '的自然数之和为：' || v_total);
    
    <<inner_loop>>
    for j IN 1..v_num LOOP
      exit inner_loop when ( i + j > 4 );
      v_factorial := v_factorial * j;
      dbms_output.put_line(j || '的阶乘为：' || v_factorial);
    end LOOP inner_loop;
    v_factorial := 1;
    
  end LOOP out_loop;
end;

1~1的自然数之和为：1
1的阶乘为：1
2的阶乘为：2
3的阶乘为：6
1~2的自然数之和为：3
1的阶乘为：1
2的阶乘为：2
1~3的自然数之和为：6
1的阶乘为：1
PL/SQL procedure successfully completed
```

#### ConTINUE语句

```plsql
begin
  for i IN 最小..最大 LOOP
    循环体;
    [ConTINUE [标号] when 条件;]
  end LOOP;
end;
```

```plsql
declare
 v_max_num  NUMBER := &p_max_num;
 v_sum NUMBER := 0;
 v_fac NUMBER := 1;
begin
  for i IN 1..v_max_num LOOP
    v_sum := v_sum + i;
  end LOOP;
  dbms_output.put_line('1~' || v_max_num || '自然数的和为：' || v_sum);
  
  for i IN 1..v_max_num LOOP
    v_fac := v_fac * i;
  end LOOP;
  dbms_output.put_line('1~' || v_max_num || '自然数的阶乘为：' || v_fac);
  
  v_sum := 0;
  <<t_loop>>
  for i IN 1..v_max_num LOOP
    ConTINUE t_loop when ( MOD(i,2) = 0);
    v_sum := v_sum + i;
  end LOOP;
  dbms_output.put_line('1~' || v_max_num || '奇数的和为：' || v_sum);
end;
```

## 组合数据类型

1. 记录 RECODE
    - 将逻辑上相关的但是数据类型不同的数据当作一个逻辑单元来处理，一个记录可以包含多个不同的数据。 
2. 集合
    - 将一组（集合）数据当作一个单独的单元来处理。
    
- INDEX by表
- 嵌套表
- 变长数组

- 注： 
   - 嵌套表和变长数组不属于PL/SQL的数据类型，是Oracle模式（用户）一级的表中有效的数据类型，
   - 而不能在用户的表中定义INDEX by表类型的列


### 记录类型record

一个记录即一组存储在若干字段中的相关联的数据，而记录中的每个字段都具有各自的名字和数据类型。

特性：
- 一定包含一个或多个被称为字段的组件。可以是任何的标量，记录或INDEX by表的数据类型
- 与数据库中表的行不同
- 每一个定义的记录可以根据实际需要有任意多个字段
- 可以为记录赋初值或定义not null约束，没有初始化的默认值为null
- 可以声明和引用嵌套的记录
- 在从一个表中获取一行数据时，记录非常方便
- 如果两个记录具有完全相同的数据类型，可以将一个记录直接赋值给另一个记录


#### 自定义记录类型

```plsql
declare
  TYPE 要定义的记录数据类型名称 is record(
    --字段声明
    变量1 数据类型 [约束],
    ... 
    --注意：没有分号 ;
  );
  变量名(记录名) 刚刚定义的数据类型名称 [赋初值];
  --创建变量
begin
  --访问字段
  记录名.字段名 --可以当成一个变量使用
end;
```

```plsql
declare
  --定义一个记录类型
  TYPE customer_type is record(
    v_cust_name varchar2(20),
    v_cust_id NUMBER(10)
  );
  --声明自定义记录类型的变量
  v_customer_type customer_type;
begin
  v_customer_type.v_cust_name := '刘德华';
  v_customer_type.v_cust_id := 1001;
  dbms_output.put_line(v_customer_type.v_cust_name||','||v_customer_type.v_cust_id);
end;
```

```plsql
declare 
 TYPE person is record(
   name varchar2(20)
  ,age NUMBER(3)
  );

 TYPE student is record(
   name varchar2(20)
  ,age NUMBER(3)
  );
  
 Jac person;
 Jac_copy person;
 Tom student;
 
begin
  Jac.name := 'Jac';
  Jac.age := 18;
  dbms_output.put_line(Jac.name || ' : ' || Jac.age);
  
  Jac_copy := Jac;
  --相同类型的（person）可直接赋值
  
  --Tom := Jac; 报错PLS-00382
  --student和person不是同一类型
end;
```

#### 自定义记录类型

```plsql
declare
  --定义一个记录类型
  TYPE emp_record is record(
    v_name varchar2(25),
    v_email varchar2(25),
    v_salary NUMBER(8, 2),
    v_job_id varchar2(10));
  --声明自定义记录类型的变量
  v_emp_record emp_record;
begin
  --通过 select ... into ... 语句为变量赋值
 select last_name, email, salary, job_id 
 into v_emp_record
 from employees
 where employee_id = 186;
 -- 打印变量的值
 dbms_output.put_line(v_emp_record.v_name || ', ' || v_emp_record.v_email || ', ' ||v_emp_record.v_salary || ', ' ||  v_emp_record.v_job_id);
end;
```

##### 使用 %TYPE 定义变量，动态的获取数据的声明类型

```plsql
declare
  --定义一个记录类型
  TYPE emp_record is record(
    v_name employees.last_name%TYPE,
    v_email employees.email%TYPE,
    v_salary employees.salary%TYPE,
    v_job_id employees.job_id%TYPE);
  --声明自定义记录类型的变量
  v_emp_record emp_record;
begin
  --通过 select ... into ... 语句为变量赋值
 select last_name, email, salary, job_id 
 into v_emp_record
 from employees
 where employee_id = 186;
 -- 打印变量的值
 dbms_output.put_line(v_emp_record.v_name || ', ' || v_emp_record.v_email || ', ' ||  v_emp_record.v_salary || ', ' ||  v_emp_record.v_job_id);
end;
```

##### 使用 %ROWTYPE
- 利用%ROWTYPE属性声明一个能够存储一个表或视图中一整行数据的记录（变量）。
- 该记录中的每一个字段的名字和数据类型取自表或视图中相应的列。
- 数据类型动态变化
- 不需要在定义一个变量；直接就是变量而不再是数据类型

```plsql
declare 
 记录名 要存储的表名/视图名%ROWTYPE;
 --不需要在定义一个变量；直接就是变量而不再是数据类型
 --变量名 记录名；错误
begin
 ..
end;
```

```plsql
declare
--声明一个记录类型的变量
  v_emp_record employees%ROWTYPE;
begin
  --通过 select ... into ... 语句为变量赋值
 select * 
 into v_emp_record
 from employees
 where employee_id = 186; 
 -- 打印变量的值
 dbms_output.put_line(v_emp_record.last_name || ', ' || v_emp_record.email || ', ' ||  v_emp_record.salary || ', ' ||  v_emp_record.job_id  || ', ' ||  v_emp_record.hire_date);
end;
```

##### 赋值语句：通过变量实现查询语句

```plsql
declare
  v_emp_record employees%ROWTYPE;
  v_employee_id employees.employee_id%TYPE;
begin
  --使用赋值符号位变量进行赋值
  v_employee_id := 186;
  --通过 select ... into... 语句为变量赋值
 select * 
 into v_emp_record
 from employees
 where employee_id = v_employee_id;
 -- 打印变量的值
 dbms_output.put_line(v_emp_record.last_name || ', ' || v_emp_record.email || ', ' ||  v_emp_record.salary || ', ' ||  v_emp_record.job_id  || ', ' ||  v_emp_record.hire_date);
end;
```

##### 通过变量实现DELETE、INSERT、UPDATE等操作

###### DELETE

```plsql
declare
  v_emp_id employees.employee_id%TYPE;
begin
  v_emp_id := 109;
  DELETE from employees
  where employee_id = v_emp_id;
  --commit;
end; 
```

###### INSERT

```plsql
declare
 copy_emp_rec employees%ROWTYPE;
 
begin
  select *
  into copy_emp_rec
  from employees
  where employee_id = 120;
  
  INSERT into copy_emp
  VALUES copy_emp_rec;
end;
```

```plsql
declare
 copy_emp_rec copy_emp%ROWTYPE;
 
begin
  select *
  into copy_emp_rec
  from copy_emp
  where employee_id = 120;
  
  INSERT into test_emp(emp_id,salary)
  VALUES(copy_emp_rec.employee_id
        ,copy_emp_rec.salary
        );
        
end; 
```

###### UPDATE

```plsql
declare
 copy_emp_rec copy_emp%ROWTYPE;
 
begin
  select *
  into copy_emp_rec
  from copy_emp
  where employee_id = 120;
  
  UPDATE test_emp
  set emp_id = 1
     ,salary = 1000
  where emp_id = copy_emp_rec.employee_id;
end; 
```

- UPDATE ...
- set ROW = ...

```plsql
declare
 copy_emp_rec employees%ROWTYPE;
 
begin
  select *
  into copy_emp_rec
  from employees
  where employee_id = 120;
  
  UPDATE copy_emp
  set ROW = copy_emp_rec
  where employee_id = copy_emp_rec.employee_id;
end; 
```

### INDEX by表 （PL/SQL表）

- INDEX by表时用户定义的一种组合（集合）数据类型，
- INDEX表可以利用一个主键（下标）值作为索引的方式存储数据，而主键(下标)值不必是顺序的
   - INDEX by表就是一组关联的键值对
   

**特性：**  

- INDEX by表由两个组件（两列）所组成
   - 数据类型为BINARY_INTEGER或PLS_INTEGER的主键
   - 标量或记录(record)数据类型的列
- INDEX by表没有界限，大小可以动态的

**说明:**

- 主键（下标）的数据类型一般是BINARY_INTEGER或PLS_INTEGER数据类型，
   - 因为与NUMBER类型的数据相比，BINARY_INTEGER或PLS INTEGER数据需要较少的存储空间。
   - BINARY_INTEGER或PLS_INTEGER是以一种紧资格式所表示的数字整型数，而且它们的算术操作是使用机器算法实现的，因此它们的算术运算也要比SUMBER类型的数据快。“主键”也可以使用变长字符(varchar2)类型或varchar2的子类型，但是效率方面要打折扣。
- 在INDEX by表中，用标量数据类型或记录数据类型的列来存储值，如果这一列是标量型，那么它就只能存储一个值。而如果这一列是记录型，那么它就可以存储多个值。实际上，这一列就相当于数组中的元素。
- 虽然INDEX by表的大小没有限制，但是BINARY_INTEGER或PLS_NTEGER类型的“主键”(下标)受限于BINARY_INTEGER或PLS_INTEGER类型数据的最大值，其取值范围为-2147483647~2147483647。要注意的是，“主键”既可以是正也可以是负，而且“主键”不一定是连续的。


**声明INDEX by表数据类型和INDEX by表型变量的语法：**

```plsql
declare
 TYPE 数据类型名 is table OF 列数据类型|变量%TYPE|表%ROWTYPE
     [INDEX by PLS_INTEGER|BINARY_INTEGER|varchar2(20)];

 变量名 数据类型名；
begin
  ...
end;

这里需要指出的是，创建一个NDEX by表型变量需要两步：
(1)声明一个INDEX by表的数据类型
(2)利用以上声明的NDEX by表数据类型声明一个这一数据类型的变量。
```

- 在INDEX by表上可以加not null约束，以防止将空值NULL赋于NDEX by表中元素，要注意的是，在声明INDEX by表时是不能对它进行初始化的。
- 当INDEX by表被创建时Oracle并不有自动填入任何值。必须在PL/SQL程序中以程序的方式为INDEX by表赋值，然后才可以使用这个数组，
- INDEX by表中的元素可以是任何标量类型/记录类型。
- INDEX by表的大小是没有限制的，在INDEX by表中，数据行（元素）的个数可以动态地增长，因此可以在INDEX by表中添加新的数据行（元素）
- 列(元素)可以属于任何变量或记录数据类型，而主键（下标）既可以是一个数字，也可以是一个字符串。不能在声明INDEX by表时将其初始化，即在声明时不能为INDEX by表赋值，此时它没有包含任何键（下标）也没有包含任何（元素）值。需要使用显示的执行语句为INDEX by表赋值，其INDEX by表的结构：

```plsql
declare
 TYPE name_table_type is table OF employees.last_name%TYPE
      INDEX by PLS_INTEGER;
      
 TYPE hire_date_table_type is table OF employees.hire_date%TYPE
      INDEX by BINARY_INTEGER;
  
 name_table name_table_type;
 hire_date_table hire_date_table_type;
 
 v_count NUMBER(6) := &p_count;
 
begin
  for i IN 1..v_count LOOP
    name_table(i) := ConCAT(TO_char(i),'号');
    hire_date_table(i) := TO_DATE('1997-11-12','yyyy-mm-dd');
    dbms_output.put_line(name_table(i) || ': ' || hire_date_table(i));
  end LOOP;
end;
```

#### INDEX by表的方法

```
表名.方法
```

|     方法     |                                 描述                                  |
| :---------: | :------------------------------------------------------------------: |
|  EXisTS(n)  |             如果第n个元素在PL/SQL表（数组）中存在，返回TRUE              |
|    COUNT    |                  返回一个PL/SQL表当前所包含的元素个数                   |
|    FIRST    | 返回在一个PL/SQL表中第一个（最小的）下标数字,如果PL/SQL表是空的，返回NULL |
|    LAST     | 返回在一个PL/SQL表中最后一个（最大的）下标数字,如果PL/SQL表是空的返回NULL |
|  PRIOR(n)   |             返回在一个PL/SQL表中当前元素的前n个元素的下标值              |
|   NEXT(n)   |             返回在一个PL/SQL表中当前元素的后n个元素的下标值              |
|   DELETE    |                  DELETE即删除一个PL/SQL表中的全部元素                  |
|  DELETE(n)  |                    即删除一个PL/SQL表中的第n个元素                     |
| DELETE(m,n) |                     即删除数组中m~n范围内的全部元素                     |

- PRIOR()和NEXT()这两种方法到底有什么用处？
   - 对于“主键”（下标)是varchar2的关联数组(associative arrays),利用这些方法可以方便地返回适当的“主键”(下标)，而其顺序是基于字符串中字符的二进制的值。在循环操作中使用这些方法要比利用下标加减的方法更可靠，因为在循环操作期间有可能数组中有些元素被删除了或插入了新的元素。

```plsql
declare
 TYPE emp_num_type is table OF NUMBER
      INDEX by varchar(20);
 
 total_employees emp_num_type;
 i varchar2(20);

begin
 -- 往INDEX by表total_employees中插入数据
 
 select COUNT(*)
 into total_employees('10号部门')
 --total_employees添加下标为'10号部门',
 --total_employees('10号部门')内容为COUNT(*)的结果(数字)
 from employees
 where department_id = 10;
 
 select COUNT(*) 
 into total_employees('20号部门')
 from employees
 where department_id = 20;
 
 select COUNT(*)
 into total_employees('30号部门')
 from employees
 where department_id = 30;
 
-- 为 i 赋值为total_employees的第一个下标(主键)
 i := total_employees.FIRST;

 dbms_output.put_line('按升序列出各部门名称和员工数：');

 WHILE i is not null LOOP
   dbms_output.put_line(i || '的员工总数：' || TO_char(total_employees(i)));
   dbms_output.put_line('i的值: ' || i);
    
-- 为 i 赋值为total_employees的当前下标的下一个下标  
   i:= total_employees.NEXT(i);
 end LOOP;
 dbms_output.put_line('i的值: ' || i);
     
 dbms_output.put_line(CHR(10));

-- 为 i 赋值为total_employees的最后一个下标(主键)
 i := total_employees.LAST;
 dbms_output.put_line('按降序列出各部门名称和员工数：');
 
 WHILE i is not null LOOP
   dbms_output.put_line(i || '的员工总数：' || TO_char(total_employees(i)));
   dbms_output.put_line('i的值: ' || i);

-- 为 i 赋值为total_employees的当前下标的上一个下标  
   i := total_employees.PRIOR(i);
 end LOOP;
    dbms_output.put_line('i的值: ' || i);
end;


**********************
按升序列出各部门名称和员工数：
10号部门的员工总数：1
i的值: 10号部门
20号部门的员工总数：2
i的值: 20号部门
30号部门的员工总数：6
i的值: 30号部门
i的值: 


按降序列出各部门名称和员工数：
30号部门的员工总数：6
i的值: 30号部门
20号部门的员工总数：2
i的值: 20号部门
10号部门的员工总数：1
i的值: 10号部门
i的值: 
PL/SQL procedure successfully completed
```

```
declare
 TYPE dept_table_type is table OF departments%ROWTYPE
      INDEX by PLS_INTEGER;
 dept_table dept_table_type;
 v_count NUMBER := 5;
 j NUMBER;
begin
  for i IN 1..v_count LOOP
    select *
    into dept_table(i * 10)
    from departments
    where department_id = i * 10;
  end LOOP;
  
  j := dept_table.FIRST;
  WHILE j is not null LOOP
    dbms_output.put_line(dept_table(j).department_id || ' ' ||
                         dept_table(j).department_name
                         );
    j := dept_table.NEXT(j);
  end LOOP;
end;

*********************
10 Administration
20 Marketing
30 Purchasing
40 Human Resources
50 Shipping
PL/SQL procedure successfully completed
```
***

## 游标CURSOR

### 游标的使用

 在PL/SQL程序，对于处理多行记录的事务经常使用游标来实现
- 上下文 
    - 为了处理SQL语句,Oralce必须分配一片叫上下文的私有内存区来处理必需的信息，其中包括要处理的行的数目，一个指向语句被分析以后的表示形式的指针以及查询的活动集

- 游标是一个指向上下文的句柄(handle)或指针。通过游标，PL/SQL可以控制上下文区和上下文区会发生什么事情

对于不同的SQL语句有游标的使用情况不同

| SQL语句             | 游标      |
| :------------------ | :-------- |
| 非查询语句          | 隐式      |
| 结果是单行的查询语句 | 隐式/显式 |
| 结果是多行的查询语句 | 显式      |

- 隐式游标：
   - 有Oracle服务器自动创建和管理，用户不能访问隐式游标。当必须执行一个SQL语句时，Oracle服务器自动创建一个隐式的游标
- 显式游标：
   - 由用户显式的声明，用户可以访问和控制显式的游标
### 显式游标处理

Oracle服务器为处理的每一个SQL语句分配一个私有的SQL内存去以执行SQL语句和存储处理的信息。可以使用显式的游标来命名一个私有的SQL区，来访问该SQL语句的存储信息。

- 但有一个要返回多行数据的select语句时，可以在PL/SQL程序中声明一个显式的游标，来一行一行地处理这select语句所返回地所有数据行。
- 一个多行查询所返回地数据行地集合（全部数据行）被称为活动集。（活动集的大小就是满足查询条件的数据行的个数）
- 一个游标的活动集是由游标声明时的select语句决定的。

- 显示游标功能：
   -  虽然使用select into语句已经可以将数据库表中的数据存入PL/SQL变量中，但是有时满足查询条件的数据行可能很多，这就使得程序的逻辑条件比较复杂，而且使用循环语句每循环一次将一行数据存入相应的PL/SQL变量中的方法存在着效率方面的问题，因为每次执行语句时，PL/SQL必须访问数据库中的表，而表是存放在硬盘上的。实验数据表明，硬盘的数据访问速度比内存慢1000~100000倍。而使用显式cursor就可以一次将满足所有条件的数据全部放入内存中，之后就在内存中一行接一行地处理了，比之前更快。
       - 可以一行接一行地处理一个查询返回的全部结果（查询语句执行一次）。
       - 一直追踪当前正在处理的数据行。
       - 能够使程序员在PL/SQL程序块中显式地手工控制一个或多个cursors。.

**1)定义游标 定义一个游标及其相对应的select语句**

- 格式 

```plsql
CURSOR 游标名 [(paramenter[,paramenter]……)] is 
    查询语句;
```

- 游标参数只为输入参数，格式为：

```plsql
paramenter_name[IN] datefile [{:=default}expression]
```
   - 在指定数据类型时，不能使用长度约束

-  定义的游标的查询语句中不能有into子句
- 显式游标可以是任何有效的select语句，包括连接，子查询等。

**2)打开游标 就是执行游标所对应的select语句，将其查询结果放入工作区，并且指针指向工作区的首部，标记游标结果集合**

- 如果游标查询语句中带有for UPDATE选项，OPEN语句还将锁定数据库表中游标结果集合对应的数据行
- 格式 

```plsql
OPEN 游标名[([paramenter = ]valuel[,[paramenter = ]value]……)];
```

1. 为一个上下文区域动态地分配内存
2. 对select语句进行语法分析
3. 绑定输入变量（通过获取输入变量的内存地址为输入变量设置值）
4. 标识活动集（生成满足查询条件的数据行的集合）
   - 当执行OPEN语句是，并没有执行提取活动集中的数据行并存入变量的操作，而是由FETCH语句完成）
5. 将指针定位在（指向）活动集的第一行

- 在向游标传递参数时，可以使用与游标参数相同的传值方法(即位置表示法和名称表示法)
- PL/SQL程序不能用OPEN语句重复打开一个游标

**3)提取游标数据 就是检索结果集合中的数据行放入指定的输出变量**

- 格式 

```plsql
FETCH 游标名 into {变量1，变量2.... | 记录名};
```

- FETCH中的数据与into的变量应该顺序一一对应，且数据类型兼容。
- 对该记录进行处理；继续处理，直到活动集合中没有记录

1. 读取当前行的数据并装入PL/SQL的输出变量中
2. 将游标的指针移向所标识的活动集中的下一行

- 检查游标中是否还包含数据行。如果FETCH语句没有提取任何值（数据），在活动集中没有数据行要处理，PL/SQL并不报错。

**4)关闭游标**

- 当提取和处理完游标结果集合数据后，应及时关闭游标，以释放该游标所占用的系统资源(释放上下文区域，取消活动集的定义)，并使该游标的工作区改变或无效，不能再使用FETCH语句取其中的数据（否则抛出异常：INVALID_CURSOR)。
- 关闭后的游标可以使用OPEN语句重新打开。
- 格式 

```plsql
CLOSE  cursor_name;
```

**应用**

```plsql
--打印80号部门的员工的工资
declare
 TYPE emp_record is record(
                           v_sal employees.salary%type,
                           v_id employees.employee_id%type
                           );
 v_emp_record emp_record;
 CURSOR emp_sal_cursor is select salary,employee_id
                          from employees
                          where department_id = 80;
begin
  OPEN emp_sal_cursor;
  FETCH emp_sal_cursor into v_emp_record.v_sal,v_emp_record.v_id;
  WHILE emp_sal_cursor%FOUND LOOP
    dbms_output.put_line('emp_id:'||v_emp_record.v_id||'salary:'||v_emp_record.v_sal);
    FETCH emp_sal_cursor into v_emp_record.v_sal,v_emp_record.v_id;
  end LOOP;
  CLOSE emp_sal_cursor;
end;  
```

```plsql
输出工资最高的前十名员工的信息与排名
declare
 v_emp_id NUMBER;
 v_emp_name copy_emp.last_name%TYPE;
 v_dept_id copy_emp.department_id%TYPE;
 v_salary copy_emp.salary%TYPE;
 
 CURSOR copy_emp_cursor is
        select employee_id
              ,last_name
              ,department_id
              ,salary
        from copy_emp
        ORDER by salary DESC;

 v_i NUMBER := 0;
begin
  OPEN copy_emp_cursor;
  
  WHILE copy_emp_cursor%ROWCOUNT < 10 LOOP
    
    FETCH copy_emp_cursor into v_emp_id,v_emp_name,v_dept_id,v_salary;
    v_i := v_i + 1;
    dbms_output.put_line('第' || v_i ||'名'|| CHR(10)||
                         v_emp_id ||'号员工'|| CHR(10) ||
                         '姓名为：'||v_emp_name || CHR(10)|| 
                         '部门为：'||v_dept_id || CHR(10) ||
                         '工资为：'||v_salary|| CHR(10) ||
                         '******************************'
                         );
  end LOOP;
  CLOSE copy_emp_cursor;
end;
```

```plsql
declare
 v_emp_id NUMBER;
 v_emp_name copy_emp.last_name%TYPE;
 v_dept_id copy_emp.department_id%TYPE;
 v_salary copy_emp.salary%TYPE;
 v_rn NUMBER;
 
 CURSOR copy_emp_cursor is
        select rownum rn
              ,employee_id
              ,last_name
              ,department_id
              ,salary
        from (select employee_id
                     ,last_name
                     ,department_id
                     ,salary
              from copy_emp
              ORDER by salary DESC
              );
begin
  OPEN copy_emp_cursor;
  
  WHILE copy_emp_cursor%ROWCOUNT < 10 LOOP
    
    FETCH copy_emp_cursor into v_rn,v_emp_id,v_emp_name,v_dept_id,v_salary;
    
    dbms_output.put_line('第' || v_rn || '名' || CHR(10) ||
                         v_emp_id ||'号员工'|| CHR(10) ||
                         '姓名为：'||v_emp_name || CHR(10)|| 
                         '部门为：'||v_dept_id || CHR(10) ||
                         '工资为：'||v_salary|| CHR(10) ||
                         '******************************'
                         );
  end LOOP;
  CLOSE copy_emp_cursor;
end;
```

```plsql
declare
 v_emp_id NUMBER;
 v_emp_name copy_emp.last_name%TYPE;
 v_dept_id copy_emp.department_id%TYPE;
 v_salary copy_emp.salary%TYPE;
 
 CURSOR copy_emp_cursor is
        select employee_id
              ,last_name
              ,department_id
              ,salary
        from copy_emp
        ORDER by salary DESC;

 v_i NUMBER := 0;
begin
  OPEN copy_emp_cursor;
  
  LOOP
    FETCH copy_emp_cursor into v_emp_id,v_emp_name,v_dept_id,v_salary;
    exit when copy_emp_cursor%ROWCOUNT > 10 OR copy_emp_cursor%NOTFOUND OR copy_emp_cursor%NOTFOUND is NULL; 
    v_i := v_i + 1;
    dbms_output.put_line('第' || v_i ||'名'|| CHR(10)||
                         v_emp_id ||'号员工'|| CHR(10) ||
                         '姓名为：'||v_emp_name || CHR(10)|| 
                         '部门为：'||v_dept_id || CHR(10) ||
                         '工资为：'||v_salary|| CHR(10) ||
                         '******************************'
                         );
  end LOOP;
  CLOSE copy_emp_cursor;
end;
```

```plsql
declare 
 CURSOR emp_cursor is
   select *
   from employees;
 
 emp_rec emp_cursor%ROWTYPE;
 
 TYPE emp_table_type is table OF employees%ROWTYPE
      INDEX by PLS_INTEGER;
 
 v_emp_rec emp_table_type;
 n NUMBER(3) := 1;
 
begin
  OPEN emp_cursor;
  
  LOOP
    FETCH emp_cursor into emp_rec;
    
    exit when emp_cursor%NOTFOUND OR emp_cursor%NOTFOUND is NULL;
    
    v_emp_rec(n) := emp_rec;
    n:= n + 1;
    
  end LOOP;
  CLOSE emp_cursor;
  
  <<outer_loop>>
  for i IN v_emp_rec.FIRST..v_emp_rec.LAST LOOP
    for j IN v_emp_rec.FIRST..v_emp_rec.LAST LOOP
      IF v_emp_rec(i).employee_id = v_emp_rec(j).manager_id then
        dbms_output.put_line(v_emp_rec(i).last_name ||'手底下有人');
        
        ConTINUE outer_loop;
      end IF;
    end LOOP;
  end LOOP outer_loop;
end;
```

### 游标属性

- %FOUND 布尔型属性 当最近一次读记录时成功返回则值为TRUE
   - NULL：在第一次获取数据之前（执行FETCH语句之前）
   - TRUE：一条记录成功的被获取到
   - FALSE：没有记录返回
   - INVALID_CURSOR：游标没有打开
- %NOTFOUND 布尔型属性 与%FOUND相反
- %isOPEN 布尔型属性 当游标打开时返回TRUE
- %ROWCOUNT 数字型属性 返回已从游标中读取的记录数

-注：可在前面冠以SQL表示隐式游标
   - 如： SQL%FOUND

```plsql
declare
 v_dept_id copy_emp.department_id%TYPE := &p_dept_id;
begin
  UPDATE copy_emp
  set salary = 99999
  where department_id = v_dept_id;
  
  dbms_output.put_line(SQL%ROWCOUNT || '行数据被更新');
end;

*****
1行数据被更新
```

```plsql
 declare
       --类型定义
       CURSOR c_job is
       select empno,ename,job,sal
       from emp
       where job='MANAGER';
       --定义一个游标变量
       c_row c_job%rowtype;
begin
       OPEN c_job;
         LOOP
           --提取一行数据到c_row
           FETCH c_job into c_row;
           --判读是否提取到值，没取到值就退出
           --取到值c_job%notfound 是false 
           --取不到值c_job%notfound 是true
           -- NULL：在第一次获取数据之前（执行FETCH语句之前）
           exit when c_job%notfound OR c_job%NOTFOUND is NULL;
            dbms_output.put_line(c_row.empno||'-'||c_row.ename||'-'||c_row.job||'-'||c_row.sal);
         end LOOP;
       --关闭游标
      CLOSE c_job;
end;
```

### 游标的for循环

-  PL/SQL语言提供了游标for循环语句，自动执行游标的OPEN、FETCH、CLOSE语句和循环语句的功能；
    - 不需要输入OPEN,FETCH,CLOSE；
1. 当进入循环时，游标for循环语句自动打开游标，并提取第一行的游标数据；
2. 当程序处理完当前所提取的数据而进行下一次循环时，游标for循环语句自动提取下一行数据供程序处理
3. 当提取完结果集合中的所有数据后结束循环，并自动关闭游标。

- 格式

```plsql
for index_variable IN 游标名[vlaue[,value]……] LOOP
          --游标执行代码
end LOOP;
```

- 其中，index_variable为游标for循环语句隐含声明的索引变量，该变量为**记录变量**，其结构与游标查询语句返回的结构集合的结构相同。
   - 在程序中可以通过引用该索引记录变量来读取所提取的游标数据，
   - index_variable中各元素的名称与游标查询语句选择列表中所制定的列名相同
- 如果再游标查询语句的选择列表中存在计算列，则必须为这些计算列指定别名后才能通过游标for循环语句中的索引变量来访问这些列数据。

```plsql
--打印出80号部门的所有员工的工资
declare
  CURSOR v_emp_cursor is select salary,employee_id
                         from employees
                         where department_id = 80;
begin 
  for i IN v_emp_cursor LOOP
    dbms_output.put_line('employee_id:'||RPAD(i.salary,4,'*')||'  salary:'||LPAD(i.salary,7,'0'));
  end LOOP;
end;
```

```plsql
--利用游标调整公司中员工的工资
  工资范围      调整基数
 0-5000          5%
 5000-10000      3%
 10000-15000     2%
 15000--         1%

-- 1)WHILE
 declare
  CURSOR v_cursor is select employee_id,salary
                     from employees;
  v_id employees.employee_id%type;
  v_sal employees.salary%type;
  v_temp NUMBER(10,5);
begin
  OPEN v_cursor;
  FETCH v_cursor into v_emp_record.v_id,v_emp_record.v_sal;
  WHILE v_cursor %FOUND LOOP
    IF v_sal < 5000 then v_temp := 0.05;
    ELSIF v_sal <10000 then v_temp := 0.03;
    ELSIF v_sal <15000 then v_temp := 0.02;
    ELSE v_temp := 0.01;
    end IF;
  UPDATE employees
  set salary = salary * (1 + v_temp)
  where emplkoyee_id  = v_id;
  end LOOP;
  FETCH v_cursor into v_id,v_sal;
end;

-- 2)DEOCDE函数
UPDATE employees
set salary = salary * (1 + DECODE(TRUNC(salary / 5000),0,0.05,
                                                       1,0.03,
                                                       2,0.02,
                                                       0.01));
-- 3)for循环
declare 
  CURSOR emp_sal_cursor is select employee_id,salary
                           from employees;
  v_temp NUMBER(4,2);
begin
  for i IN emp_sal_cursor LOOP
    IF i.salary < 5000 then v_temp := 0.05;
    ELSIF i.salary < 10000 then v_temp := 0.03;
    ELSIF i.salary < 15000 then v_temp := 0.02;
    ELSE v_temp := 0.01;
    end IF;
    UPDATE employees
    set salary = salary * (1 + v_temp)
    where employee_id = i.employee_id;
  end LOOP;
end;
```
### 游标使用子查询

#### 在游标的for循环中使用子查询

- 如果使用子查询的游标for循环，不需要在声明段中声明游标，但是必须提供一个在循环体中本身可以确定活动集的select语句，用来定义游标。
- 且如果在游标的for循环使用子查询，则不能显式的调用游标的属性，因为该游标没有被声明。

```plsql
begin
  for emp_rec IN (select * from employees) LOOP
    IF emp_rec.department_id = 20 then 
      dbms_output.put_line('20号部门: ' || emp_rec.employee_id);
    end IF;
  end LOOP;
end;

/*
***************
20号部门: 201
20号部门: 202
PL/SQL procedure successfully completed
*/
```

#### 在游标的定义中使用子查询

- 此时在子查询的每一个函数或表达式都必须有别名，用来映射成临时表
    - 函数和表达式不能作为列名

```plsql
declare
 CURSOR dept_total_cursor is 
        select DisTINCT e.department_id
                       ,d.department_name
                       ,max_sal
                       ,avg_sal
                       ,count_num
        from departments d
            ,employees e
            ,(select MAX(salary) max_sal
                    ,AVG(salary) avg_sal
                    ,COUNT(*) count_num
                    ,department_id
              from employees
              GROUP by department_id
              ) t
        where e.department_id = d.department_id
          and e.department_id = t.department_id;
begin
  for dept_rec IN dept_total_cursor LOOP
    dbms_output.put_line('部门号: '||dept_rec.department_id || CHR(10)||
                        '部门名称: '||dept_rec.department_name||CHR(10)||
                        '人数: '||dept_rec.count_num ||CHR(10)||
                        '最高工资: '||dept_rec.max_sal||CHR(10)||
                        '平均工资: '||dept_rec.avg_sal);
    dbms_output.put_line('**********************');
    DBMS_OUTPUT.ENABLE (buffer_size=>null);
  end LOOP;
end;
```

### 带参数的游标
- 可以在PL/SQL程序块中多次打开一个已经关闭的显式游标，而且每次可以返回不同的动态集。而每次执行时，关闭之前的游标并以新的一组参数重新打开。
- 对于在游标中定义的每一个形参，在OPEN语句中必须有一个对应的实参。
- 参数的数据类型与标量类型的变量相同。
- 只定义数据类型，而不定义长度。

```plsql
declare
    CURSOR emp_sal_cursor(dept_id NUMBER, sal NUMBER) is 
           select salary + 1000 sal, employee_id id 
           from employees 
           where department_id = dept_id and salary > sal;
    temp NUMBER(4, 2);
begin
          dbms_output.put_line('工资'|| CHR(9) || 'id' || CHR(9) ||'temp');    
          for c IN emp_sal_cursor(sal => 4000, dept_id => 80) LOOP
          --dbms_output.put_line(c.id || ': ' || c.sal);
          IF c.sal <= 5000 then
             temp := 0.05;
          ELSIF c.sal <= 10000 then
             temp := 0.03;   
          ELSIF c.sal <= 15000 then
             temp := 0.02;
          ELSE
             temp := 0.01;
          end IF;      

          dbms_output.put_line(c.sal || CHR(9) || c.id || CHR(9) || temp);
          --UPDATE employees set salary = salary * (1 + temp) where employee_id = c.id;
    end LOOP;
end;
```

```plsql
declare 
 CURSOR dept_avg_sal_cursor(p_dept_id NUMBER) is
    select department_id id
          ,AVG(salary) avg_sal
    from employees
    GROUP by department_id
    HAVING department_id = p_dept_id;
    
    v_dept_id NUMBER := &p_id;
begin 
  for dept_rec IN dept_avg_sal_cursor(v_dept_id) LOOP
    dbms_output.put_line('部门号：' || dept_rec.id ||CHR(9)||
                         '平均工资: '|| dept_rec.avg_sal);
  end LOOP;
end;
```

### 隐式游标

- 当必须执行一个SQL语句时，Oracle服务器自动创建一个隐式的游标

```plsql
--更新指定员工 salary(涨工资 10)，如果该员工没有找到，则打印”查无此人” 信息
begin
         UPDATE employees 
         set salary = salary + 10 
         where employee_id = 1005;    
         IF SQL%NOTFOUND then
            dbms_output.put_line('查无此人!');
         end IF;
end;
```

### for UPDATE子句

- for UPDATE 子句必须是select语句的最后一个子句。
- 在查询多个表时，可以使用for UPDATE子句限制锁定特定表中的数据行(在OPEN时锁住)
   - 避免其他用户或进程的DML操作的冲突，在提取数据时，将所操作的数据行全部锁住。
   
- 语法

```plsql
select ...
from ...
for UPDATE [OF 列名][NOWAIT | WAIT n];

--[OF 列名] 只锁住包含该列的表中的数据行
--[NOWAIT | WAIT n] 访问的数据行被其他会话锁住则返回一个错误。n为等待n秒
```

```plsql
declare
 CURSOR copy_emp_cursor is
        select employee_id
              ,last_name
              ,salary
        from copy_emp
        where department_id = 20
        for UPDATE OF salary NOWAIT;
begin
  OPEN copy_emp_cursor;
end;
```

### where CURRENT OF 子句

- 可以通过where CURRENT OF 子句引用显式游标的当前行来标识要修改的数据行，
- 要引用显式游标的当前行，where CURRENT OF子句需要与for UPDATE子句一起使用
- 在UPDATE和DELETE语句中使用where CURRENT OF子句，而在游标声明时说明for UPDATE子句。
- 必须在游标的查询语句中包含for UPDATE子句, 以便在打开这个游标时锁住访问的数据行

```
where CURRENT OF 游标;
```

```
declare
 CURSOR emp_cursor is
   select *
   from copy_emp
   where department_id = 20
   for UPDATE OF salary NOWAIT;
begin
  for emp_rec IN emp_cursor LOOP
    UPDATE copy_emp
    set salary = emp_rec.salary * 0.15
    where CURRENT OF emp_cursor;
  end LOOP;
end; 
```
***

## 触发器TRIGGER

- 触发器 触发器是一个特殊的存储过程。是一个与表相关联的、存储的PL/SQL程序。
- 在Oracle系统上，触发器类似过程和函数，都有声明、执行、异常处理过程PL/SQL块
- 每当一个特定的数据操作语句DML(INSERT、UPDATE、DELETE,注意没有select)在指定的表上发出时,oracle自动地执行触发器中定义的语句序列。

 **触发器在数据库里以独立的对象存储，它与存储过程不同的是：**

|                                                      触发器TRIGGER                                                       |             存储过程procedure             |
| :---------------------------------------------------------------------------------------------------------------------: | :--------------------------------------: |
| 触发器是由一个事件(DML语句)来启动运行，即触发器是当某个事件发生时自动的隐式运行,并且触发器不能接受参数，所以运行触发器叫触发或点火 | 存储过程通过其它程序来启动运行或直接启动运行 |
|                                            源代码包含在USER TRIGGERS数据字典中                                            |     源代码包含在USER_SOURCE数据字典中      |
|                                          不允许使用commit、SAVEPOINT和rollback                                           |    允许使用commit、SAVEPOINT和rollback    |

**触发器类型**

1. DML触发器
   - ORACLE可以在DML 语句进行触发，可以在DML操作前或操作后进行触发，并且可以对每一个行或语句操作上进行触发
2. 替代触发器
   - 由于在ORACLE里，不能直接对两个以上的表建立的视图进行操作。所以给出了替代触发器
3. 系统触发器
   - 可以在ORACLE数据库系统的事件中进行触发，如ORACLE系统的启动和关闭

**触发器的各个组件。**

- 基于系统事件的触发器可以被定义在数据库一级，也可以被定义在模式一级。
  - 例如，一个数据库关闭触发器是定义在数据库一级的，
- 而基于数据定义语言(DDL)语句上或基于用户登录或退出操作的触发器既可以定义在数据库一级，也可以定义在模式（用户）一级。
- 基于数据维护语言(DML)语句的触发器是定义在一个特定的表或一个特定的视图上。

### 一般语法

```plsql
create or replace TRIGGER 触发器名
BEforE|AFTER|INSTEAD OF
INSERT|DELETE|UPDATE [OF colUMN[列名]]
on [模式（用户名）.]表/视图
[REFERENCING OLD AS 指定引用OLD的别名 | NEW AS 指定引用NEW的别名]
[for EACH ROW] --行级触发器
[when 条件表达式] --只用于行级触发器
触发器体; --PL/SQL块等
```

**触发器组成**

- **触发事件** 即在何种情况下触发TRIGGER
  - 在一个表或视图上的，INSERT/UPDATE/DELETE语句
  - 在任何用户上的，CREATE/ALTER/DROP语句
  - 一个数据库启动或实例关闭
  - 一个用户的登录或退出
  - 一个特定的错误信息或任何错误信息
- **触发时间** 
  - 即触发事件和该TRIGGER的操作顺序
  - BEforE 在触发事件(执行)之前执行触发器
     - 决定所触发的语句是否应该允许被完成
     - 在完成一个INSERT或UPDATE语句之前导出原来列的值
     - 初始化全局变量，验证一些复杂的业务规则 
  - AFTER 在触发事件之后执行触发器
    - 在执行触发器之前先完成触发的语句 
    - 在已经存在BEforE触发器时，要在相同的触发语句上执行不同的操作  
    - 如果触发语句完成后没有commit，则其他用户看见的还是没有执行触发器的结果
  - INSTEAD OF 替换触发事件的语句来执行触发器。
     - 被用于用其他方法不能修改的 **视图** （当不能通过SQL的DML语句直接修改视图时，可以在INSTEAD OF触发器的体中写DML语句在这个视图的基表上直接执行）
- **触发器本身** 即该TRIGGER被触发之后的目的和意图，正是触发器本身要做的事情，
  - PL/SQL块
  - CALL调用子句 `CALL 存储过程/函数等`
- **触发频率** 说明触发器内定义的动作被执行的次数,即语句级(STATEMENT)触发器和行级(ROW)触发器
  - **语句级触发器** 是指当某触发事件发生时该触发器只执行了一次
  - **行级触发器** 是指当某触发事件发生时，对受到该操作影响的每一行数据，触发器都执行一次
- **触发器类型**

| 语句级触发器    |  行级触发器   |
| :-: | :-: |
|  创建触发器的默认类型   |   创建触发器时使用for EACH ROW子句指定为行级触发器  |
|  对于触发的事件只触发一次   |  对受触发事件影响的每行触发一次    |
| 没有行受影响时也触发一次    |  触发事件未影响任何数据行就不触发   |

```pslql
create or replace TRIGGER menu_test_trigger
BEforE 
DELETE on menu
for EACH ROW
begin
  dbms_output.put_line('good');
end;
```

```plsql
create or replace TRIGGER secure_emp_trigger
BEforE 
INSERT on copy_emp
begin
  IF(TO_char(sysdate,'DY') IN ('SAT','SUN')) 
   OR (TO_char(sysdate,'HH24:MI') NOT between '08:00' and '18:00') then
    RAisE_APPLICATIon_ERROR(-20038,'非工作时间，拒绝输入数据');
  end IF;
end;  
```

### 触发器执行模型概要及实现完整性约束的准备

**一个单一的DML语句有可能触发以下四种触发器：**

1. BEforE语句触发器
2. AFTER语句触发器
3. BEforE行触发器
4. AFTER行触发器

- 在触发器中的触发事件或语句可能引起一个或多个完整性约束的检查，但是可以将约束的检查延迟到执行commit操作时。
- Oracle服务器触发这四种触发器的方式如下。

1. 执行所有的BEforE STATEMENT(语句)触发器。
2. 对受影响的每一行循环：
   - 执行所有的BEforE ROW(行)触发器。
   - 执行所有的DML语句并进行完整性约束的检查。
   - 执行所有的AFTER ROW(行)触发器。
3. 执行所有的AFTER STATEMENT(语句)触发器。

- 触发器也可能引起其他触发器的触发（执行），即所谓的级联触发器。作为一个SQL语句的结果，所有执行的操作和检查必须成功。如果在一个触发器中抛出了一个异常并且这个异常没有被显式地处理，那么所执行地所有操作（包括触发语句）全部被回滚。保证触发器不违反完整性约束

**引用完整性**

1. 外键必须为空值（NULL）或者有相匹配地项
2. 外键可以没有相对应的键属性，但不可以有无效的项

- 虽然引用完整性可以有效地防止错误的DML语句，但在特殊情况下，可能会短时间地出现违法引用完整性地数据。可以创建一个AFTER UPDATE触发器来解决。

##### 利用触发器实现完整性约束

```plsql
--departments中的主键department_id是employees中的外键。

create or replace TRIGGER emp_dept_fk_trg
AFTER UPDATE OF department_id on employees
for EACH ROW
  begin
    INSERT into departments(department_id,department_name)
    VALUES(:NEW.department_id,'新的部门:'||TO_char(:NEW.department_id));
  exception
    when DUP_VAL_on_INDEX then
      NULL;  --如果存在该异常，则不进行任何操作.
             --并避免整个程序的中断。屏蔽该异常
  end;
```

### 行级触发器

- 如果在较大的表上执行许多修改，行触发器可能降低系统的效率。

#### 条件谓词 INSERTING/DELETING/UPDATING

- 如果有多种类型的DML操作可以触发一个触发器，那么在触发器体中可以使用条件谓词来检查触发器的语句类型，从而根据相应的语句来进行相应的操作。

**条件谓词**

- inserting
- deleting
- updating
- `updating(列名)` 那一列被UPDATE，则返回TRUE

```plsql
create or replace TRIGGER secure_emp_trigger
BEforE 
INSERT OR DELETE OR UPDATE on copy_emp
for EACH ROW
begin
  IF INSERTING then
    RAisE_APPLICATIon_ERROR(-20001,'禁止插入数据');
  ELSIF DELETING then
    INSERT into user_test_table(user_name,old_id,new_id)
    VALUES (user,:OLD.employee_id,:NEW.employee_id);
  ELSIF UPDATING then
    INSERT into user_test_table(user_name,old_id,new_id)
    VALUES (user,:OLD.employee_id,:NEW.employee_id);
  end IF;
end;
```

#### 限定符 :new 和 :old

- 当一个行级触发器触发时，PL/SQL运行时的引整创建和维护两个数据结构，它们就是OLD和NEW，OLD和NEW具有完全相同的记录结构，并且使用 **%ROWTYPE** 声明成与触发器所基于的表的数据类型一模一样。
- OLD存储触发器处理之前的记录的原始值，而NEW则包含了新值。

| 数据操作 |            OLD             |            NEW            |
| :------: | :------------------------: | :-----------------------: |
|  INSERT  | （插入之前的值）空值（NULL） |          插入的值          |
|  UPDATE  |        修改之前的值         |        修改之后的值        |
|  DELETE  |        删除之前的值         | 空值（NULL)（删除之后的值） |

**注意：**

- 只在行触发器中有OLD和NEW限定词。
- 在每个SQL和PL/SQL语句中，这两个限定词前必须冠以冒号(：)（`:NEW`和`:OLD`）。
    - 如果这两个限定词是在when所在条件中引用则不能冠以冒号。否则ORA-25000: 在触发器 when 子句中, 连接变量的使用无效

```
create or replace TRIGGER update_emp_trigger
AFTER 
 UPDATE on employees
for EACH ROW
begin
  dbms_output.put_line('old_sal'||:OLD.salary||'new_sal'||:NEW.salary);
end;
```

```
create or replace TRIGGER audit_emp_values
BEforE INSERT OR DELETE OR UPDATE on copy_emp
for EACH ROW
  begin
    INSERT into user_test_table(user_name,old_id,new_id)
    VALUES (USER,:OLD.employee_id,:NEW.employee_id);
  end;
```

##### AFTER

- 在AFTER类型中，无法更改此触发器类型的 new值。

```
create or replace TRIGGER test_trigger
AFTER INSERT on user_test_table
for EACH ROW
  begin
    :NEW.new_id := 1;
  end;

ORA-04084: 无法更改此触发器类型的 NEW 值
```

#### when 条件行触发器

- 可以通过在触发器的定义中增加一个when子句，并在这个子句中说明一个布尔表达式的方法来限制一个触发器的操作。
- 如果在触发器中包括了个when子句，那么WHN子句中的表达式要评估（测试)该触发器所影响的每一行。
   - 对于每一行，如果这个表达式的评估结果是TRUE，那么触发器体（PL/SQL程序代码）将执行。如果表达式对于某一行的测试结果是FALSE或NUL，,那么触发器体就不执行。
- when子句的评估并不影响触发的SQL语句的执行，即如果一个when子句测试为FALSE，触发语句并不回滚。
- when子句是不能包括在语句触发器的定义中的，这个子句只能包括在行触发器的定义中。
- **限定符 :NEW和:OLD在when子句中不能带冒号**
   - ORA-25000: 在触发器 when 子句中, 连接变量的使用无效
   
```plsql
create or replace TRIGGER set_com_pct
BEforE INSERT OR UPDATE on copy_emp
for EACH ROW
when (NEW.job_id = 'IT')
  begin
    IF INSERTING then
      :NEW.commission_pct := 0;
    ELSE
      IF :OLD.commission_pct = 0 
       OR :OLD.commission_pct is NULL then
        :NEW.commission_pct := 0.3;
      ELSE 
        :NEW.commission_pct := :OLD.commission_pct;
      end IF;
    end IF;
  end; 
```

#### 替代触发器 INSTEAD OF

- Oracle服务器触发该触发器并替代执行所触发的语句
- INSTEAD OF触发器被用于直接在视图所基于的表上执行INSERT/UPDATE/DELETE语句。从而修改视图
  - INSTEAD OF触发器以不可见的方式工作 并在后台执行相应的正确操作。
  - 如果一个视图本身是可以修改的并且上面有INSTEAD OF触发器，那么触发器优先
- INSTEAD OF 触发器是行级触发器，对视图执行插入和修改操作是，该视图的CHECK选项不起作用。
  - 此时在触发器体中必须实现这样的检查操作。
  
##### 例

```
create or replace VIEW copy_emp_detaikls
AS
select *
from copy_emp;
```
```
create or replace TRIGGER new_emp_details
INSTEAD OF INSERT OR UPDATE OR DELETE on copy_emp_details
for EACH ROW
  begin
    IF INSERTING then
    --直接往copy_emp表中插入数据
      INSERT into copy_emp(employee_id,last_name,salary,department_id)
      VALUES(:NEW.employee_id,:NEW.last_name,:NEW.salary,:NEW.department_id);
    ELSIF DELETING then
    --直接删除copy_emp表中的数据
      DELETE from copy_emp
      where employee_id = :OLD.employee_id;
    ELSIF UPDATING('salary') then
      UPDATE copy_emp
      set salary = :NEW.salary
      where employee_id = :OLD.employee_id;
    ELSIF UPDATING('department_id') then
      UPDATE copy_emp
      set department_id = :NEW.department_id
      where employee_id = :OLD.employee_id;
    end IF;
  end;
```

#### 在变异表上触发器的限制

**变异表**

目前正在由UPDATE，DELTETE或INSERT语句修改的表，或者是一个受到声明的DELETE CASCADE引用完整性（外键约束）操作影响可能需要修改的表。（对于语句触发器而言，以上的不认为是变异表）

- 当一个行级触发器试图修改或测试一个正在通过一个DML语句修改的表时，系统会产生一个变异表错误(ORA-4091)。使用触发器读写表中的数据时必须遵守一定的规则，但是这些规则只适用于行级触发器，而语句触发器并不受影响。其限制和限制的目的如下：
  - 发出触发语句的会话不能查询或修改变异表。
  - 这一限制防止一个触发器看到一个不一致的数据集。
  - 这一限制适用于所有使用for EACH ROW子句的触发器。
  - 使用INSTEAD OF触发器正在修改的视图不被认为是变异的。

被触发的表（触发器进行操作的表）本身是一个变异表，同样使用外键(forEIGN KEY)约束引用的任何表也是变异表。正是这样的限制防止一个行触发器看到一个不一致的数据集合（正在修改的数据)。

**例**

```
create or replace TRIGGER check_salary
 BEforE INSERT OR UPDATE OF salary,department_id on copy_emp
 for EACH ROW
declare
 v_min_sal copy_emp.salary%TYPE;
 v_max_sal copy_emp.salary%TYPE;
begin
  select MIN(salary)
        ,MAX(salary)
  into v_min_sal
      ,v_max_sal
  from copy_emp
  where department_id = :NEW.department_id;
  IF :NEW.salary < v_min_sal OR :NEW.salary > v_max_sal then
    RAisE_APPLICATIon_ERROR(-20001,'工资不在允许范围内');
  end IF;
end;

UPDATE copy_emp
set salary = 1000
where employee_id = 177;
*********************
ORA-04091: 表 SCOTT.COPY_EMP 发生了变化, 触发器/函数不能读它
ORA-06512: 在 "SCOTT.CHECK_SALARY", line 5
ORA-04088: 触发器 'SCOTT.CHECK_SALARY' 执行过程中出错
```

**解决**

1. 将汇总数据（最低工资和最高工资）存储在另一个汇总表中，而该汇总表中的数据由其他的DML触发器进行持续的更新。
2. 将汇总数据存储在一个PL/SQL软件包中，并在这个软件包中访问这些汇总数据。这可以通过BEforE语句触发器中来实现
3. 复合触发器(compound trigger)。

### 复合触发器(compound trigger)

复合触发器体(compound trigger body)支持一种常见的PL/SQL状态，在这种状态中对于每一个触发时机，触发器体的PL/SQL程序代码都是可以访问的。当触发语句完成时（即当触发语句引起一个错误时)，这种常态将自动消失。利用这一新的特性，应用程序可以通过允许数据行存放到第二个表（如一个历史表或一个审计表）中进行累加操作之后再对这些数据行进行批量插入操作。

- 复合触发器使得PL/SQL的编程更容易，并且也改进了运行的性能以及提高了可扩展性。

**使用情景：**

1. 对于不同的时间点（时机），在程序中要实现对共享公用数据的一些操作。
2. 将一些数据行累积在一起并存放在第二个表中，以便能够定期地批量插入这些数据。
3. 避免变异表错误（ORA-04091)。

**触发时机**

- 在一个表上定义的一个复合触发器都具有一个或多个时间点的程序段共有4个不同的时间点，
- 复合触发器是基于一个表的单一触发器，在这个触发器中运行为4个触发时机（时间点）指定的每一个操作，这4个触发时机分别为：

|   (时机/时间点)Time Point   | (复合触发器的部分)Compound Trigger Section |
| :------------------------: | :---------------------------------------: |
|      在触发语句之前。       |             BEforE STATEMENT              |
| 在触发语句影响的每一行之后。 |              AFTER STATEMENT              |
| 在触发语句影响的每一行之前。 |              BEforE EACH ROW              |
|      在触发语句之后。       |              AFTER EACH ROW               |

- 每个时间点（程序）段必须按照表中所列的顺序出现在一个复合触发器的代码中。
   - 如果缺少某一个时间点段，那么在这个时间点就没有任何事情发生，即什么也不做。
- 一个复合触发器既可以基于一个表，也可以基于一个视图(INSTEAD OF)。

**程序段**

- 每个复合触发器都一定具有两种类型的程序段，这两种类型的程序段分别是：

1. 初始段，在该段中声明变量和子程序。
    - 这段中的程序代码会在可选段中的任何代码执行之前执行。
2. 为每一种可能的触发时间点定义代码的可选段。
    - 取决于是在一个表上还是在一个视图上定义复合触发器，这些触发时间点是不同的，并且这些触发时间点的程序代码必须按照顺序编写
       - BEforE STATEMENT --> AFTER STATEMENT --> BEforE EACH ROW --> AFTER EACH ROW
    - 对于一个基于视图的复合触发器，唯一允许的程序段就INSTEAD OF EACH ROW子句开始的段。

**语法**

1. 基于表的复合触发器 

```
create or replace TRIGGER [用户.]触发器名
 for INSERT|UPDATE|DELETE on [用户.]表名
 COMPOUND TRIGGER
-- 初始段（不要declare)
 声明部分;
 子程序;
-- 可选段
 BEforE STATAMENT is
   语句;(PL/SQL代码块)
 AFTER STATEMENT is
   语句;
 BEforE EACH ROW is
   语句;
 AFTER EACH ROW is
   语句;
end;
```

2. 基于视图的复合触发器

```
create or replace TRIGGER [用户.]触发器名
 for INSERT|UPDATE|DELETE on [用户.]视图
 COMPOUND TRIGGER
--初始段
  声明部分;
  子程序;
--可选段
  INSTEAD OF EACH ROW is
    语句;(PL/SQL程序块)
end;
```

**使用复合触发器时，Oracle限制:**

- 一个复合触发器的体代码必须复合了整个触发器程序块，而且必须是使用PL/SQL编写的。
- 一个复合触发器必须是一个DML触发器。
- 一个复合触发器必须被定义在一个表上或者一个视图上。
- 一个复合触发器体不能有初始化段，也不能有异常段。
   - 在任何其他时间点程序段执行之前BEforE STATEMENT程序段永远只执行一次。
- 在一个程序段中出现的一个异常必须在这个段中处理，复合触发器无法将异常的控制传递给其他段。
- OLD、NEW和PARENT不能出现在声明段中，也不能出现在BEforE STATEMENT和AFTER STATEMENT段中。
- 复合触发器的触发顺序是无法保证的，除非使用了FOLLOWS子句。

#### 例 使用复合触发器解决变异表问题

```
create or replace TRIGGER check_salary
 for INSERT OR UPDATE OF salary,job_id on copy_emp
 when (NEW.job_id <> 'AA')
 COMPOUND TRIGGER
 
 TYPE sal_type is table OF copy_emp.salary%TYPE;
 min_sal sal_type;
 max_sal sal_type;
 
 TYPE dept_id_type is table OF copy_emp.department_id%TYPE;
 dept_id_list dept_id_type;
 
 TYPE dept_sal_type is table OF copy_emp.salary%TYPE
   INDEX by varchar2(38);
 dept_min_sal dept_sal_type;
 dept_max_sal dept_sal_type;

BEforE STATEMENT is
 begin
   select MIN(salary)
         ,MAX(salary)
         ,NVL(department_id,-1)
   BULK colLECT into min_sal
                    ,max_sal
                    ,dept_id_list
   from copy_emp
   GROUP by department_id;
   
   for i IN 1..dept_id_list.COUNT() LOOP
     dept_min_sal(dept_id_list(i)) := min_sal(i);
     dept_max_sal(dept_id_list(i)) := max_sal(i);
   end LOOP;
 end BEforE STATEMENT;
 
 AFTER EACH ROW is
   begin
     IF :NEW.salary < dept_min_sal(:NEW.department_id) 
       OR :NEW.salary > dept_max_sal(:NEW.department_id) then
       RAisE_APPLICATIon_ERROR(-20001,'新工资超过部门工资范围');
     end IF;
   end AFTER EACH ROW;
end; 
```



### 系统触发器（以及DDL触发器)

#### DDL触发器

- 除了DML语句之外，还可以指定一种或多种DDL语句来触发触发器（代码的执行）。可以为这些事件(DDL语句)在数据库上或模式上（需要指定模式即用户）创建触发器，还可以说明BEforE和AFTER作为这类触发器的触发时机。Oracle数据库在现存的用户事务中存放这类触发器。
  - 要注意的是，不能将通过一个PL/SQL过程执行的任何DDL操作说明为一个触发器的事件。
- 只有所创建的对象是一个cluster(簇)、表、索引、表空间、视图、函数、过程、软件包、触发器、(数据)类型、序列(sequence)、同义词(synonym)、角色或用户时，DDL触发器才能触发。
- 基于数据定义语言(DDL)语句上的触发器的触发器既可以定义在数据库一级，也可以定义在模式（用户）一级

**创建基于DDL语句的触发器的语法格式如下：**

```
create or replace TRIGGER 触发器名
 BEforE|AFTER [DDL事件1[OR DDL事件2 OR..] on 数据库|模式
 --DDL事件包括CREATE、ALTER和DROP语句等，
   触发器体(PL/SQL程序块)
```

- 一个定义在数据库一级的触发器对数据库中的所有用户都会触发，而定义在模式或表一级的触发器只有当触发事件涉及到指定的模式或表时才会触发。现将可能引起一个触发器触发的触发事件归纳如下（一共有4大类）：

1. 一个在数据库或模式中一个对象上的数据定义语句。
2. 一个指定用户（或任何用户）的登录或退出。
3. 一个数据库的关闭或启动。
4. 所发生的任何错误。

#### 系统触发器

**基于Oracle数据库系统事件创建触发器的语法格式如下：**

```
CREATE[OR REPLACE]TRIGGER触发器名
 BEforE|AFTER [数据库事件1[OR数据库事件2OR..]] on database|SCHEMA
   触发器体
```

**数据库事件**

|     数据库事件     |        触发时机        |             触发器的级             |
| :---------------: | :-------------------: | :-------------------------------: |
| AFTER SERVERERROR | 一个Oracle错误被抛出时 | 可以创建基于数据库或模式一级的触发器 |
|    AFTER LOGon    |  一个用户登录数据库时   | 可以创建基于数据库或模式一级的触发器 |
|   BEforE LOGOFF   |  一个用户退出数据库时   | 可以创建基于数据库或模式一级的触发器 |
|   AFTER startup   |      开启数据库时      |     只能创建数据库一级的触发器      |
|  BEforE shutdown  |    正常关闭数据库时    |     只能创建数据库一级的触发器      |


**权限**

- 仅仅拥有 `CREATE ANY TRIGGER`的权限是不够的，创建触发器(trigger)时，ORACLE有一个限制，触发器(trigger)的拥有者必须被显示(explicitly)授予访问触发器(trigger)中涉及到的对象的权限(也就是说这些权限不能由角色继承而来)。
- 则创建数据库级触发器需要：`ADMINisTER database TRIGGER`权限

#### 例：用户登录'登出触发器

**用户级触发器**

```plsql
--创建序列，当作编号log_id
CREATE SEQUENCE log_onoff_seq
 START WITH 1
 INCREMENT by 1
 MAXVALUE 99
 NOCACHE
 CYCLE;

--存放用户登入登出信息
CREATE table log_onoff_table
(
 log_id NUMBER(3)
,user_id varchar2(38)
,log_date DATE
,action varchar2(50)
);

--存放用户错误信息
CREATE table error_table
(
 user_name VAchar2(20)
,error_time DATE
,error_msg varchar2(50)
);

--登入触发器
create or replace TRIGGER user_log_on_trigger
AFTER LOGon on SCHEMA
 declare
   v_log_id NUMBER;
 begin 
   select log_onoff_seq.NEXTVAL
   into v_log_id
   from dual;
 
   INSERT into log_onoff_table
   VALUES(v_log_id,USER,sysdate,'用户登入');
 end;
 
--登出触发器 --有问题！！！无法写入
create or replace TRIGGER log_off_trigger
BEforE LOGOFF on SCHEMA
  begin
    INSERT into log_onoff_table
    VALUES(log_onoff_seq.NEXTVAL,USER,sysdate,'用户登出');
  end;
 
--ERROR记录触发器
create or replace TRIGGER error_message_trigger
AFTER SERVERERROR on SCHEMA
  begin
    INSERT into error_table
    VALUES (USER,sysdate,'.');
    --如何获取异常信息
  end;
```

**数据库级触发器** 

```
--sysdba赋予scott ：ADMINisTER database TRIGGER权限
GRANT ADMINisTER database TRIGGER TO scott;

--存放用户登入登出信息
CREATE table system_log_onoff_table
(
 user_id varchar2(38)
,log_date DATE
,action varchar2(50)
);

--登入触发器
create or replace TRIGGER sys_log_on_trigger
AFTER LOGon on database
 begin 
   INSERT into system_log_onoff_table
   VALUES(USER,sysdate,'用户登入');
 end;
 
--登出触发器
create or replace TRIGGER sys_log_off_trigger
 BEforE LOGOFF on database
  begin
    INSERT into system_log_onoff_table
    VALUES(USER,sysdate,'用户登出');
  end;

--ERROR记录触发器

--开启数据库触发器
create or replace TRIGGER sys_startup_database_trigger
 AFTER startup on database
   begin
     INSERT into system_log_onoff_table
     VALUES('orcl数据库',sysdate,'启动数据库');
   end;
   
--关闭数据库触发器
create or replace TRIGGER sys_startup_database_trigger
 BEforE shutdown on database
   begin
     INSERT into system_log_onoff_table
     VALUES('orcl数据库',sysdate,'关闭数据库');
   end;
```

**问**

- 可以使用视图的方式，来避免其他用户的直接修改。但是要怎么实现插入新的信息？

#### 数据库级系统触发器的权限问题

**报错** 创建数据库级触发器

- ORA-04098: 触发器 'sys.USER_LOG_on_TRIGGER' 无效且未通过重新验证


**删除**

- 删除之前的触发器或DisABLE(简单粗暴)

```plsql
ALTER TRIGGER user_log_on_trigger DisABLE;
DROP TIRGGER user_log_on_trigger;
```

- 掀桌子 sysdba

```plsql
SQL> DROP TRIGGER user_log_on_trigger;

触发器已删除。
```

- 查看

```plsql
select trigger_name
      ,trigger_type
      ,triggering_event
      ,status
from USER_TRIGGERS;
```

### CALL调用语句

- 在触发器体中使用，调用一个存储过程（可看作一个PL/SQL程序块使用），该过程可以是PL/SQL，c，c++，Java语言。

**语法：**

```plsql
CALL 过程名
```

- CALL语句不要分号;结尾
- 在一个触发器体中最多只能使用一个CALL语句。
- 在一个触发器体中若存在一个CALL语句，则不能使用PL/SQL程序块等。
   - 即该触发器体也只能存在该CALL语句，不能有其他。

#### 例

```plsql
--创建过程
create or replace procedure update_sal_action
 is
 begin
  dbms_output.put_line('更新成功！');
 end;

--创建触发器并使用CALL调用过程
create or replace TRIGGER update_sal_trigger
 BEforE UPDATE OR INSERT OF salary on copy_emp
 for EACH ROW
 when (NEW.employee_id <> 1)
 --调用过程update_sal_action
 CALL update_sal_action
```

### 触发器的管理与维护

- 每个触发器都具有激活(ENABLED)和禁止(DisABLED)两种模式（状态），一个触发器只能处于ENABLED状态或DisABLED状态。
1. ENABLE: 如果发出了一个触发语句并且该触发器的限制（如果有的话）评估（测试）为TRUE(默认)，那么触发器运行它的触发器操作(PL/SQL程序代码)。
2. DisABLE: 触发器不运行它的触发器操作(PL/SQL程序代码)，即使发出了一个触发语句并且该触发器的限制（如果有的话）评估为TRUE也不运行。

当一个触发器被首次创建时，它的状态默认是ENABLED。Oracle服务器对激活的触发器要检查所定义的完整性约束并保证这些触发器不会违反任何定义的完整性约束。另外，Oracle服务器还为查询和约束提供读取一致性的视图、管理依赖关系，并且如果一个触发器是修改分布数据库中远程的表,   Oracle服务器还提供一种两阶段的提交处理过程。即可以利用ALTER TRIGGER语句控制指定的触发器的状态，也可以利用ALTER table语句控制指定表上所有触发器的状态。

- 关闭（禁止）或重新开启（激活）一个数据库触发器的命令如下：

```
ALTER TRIGGER 触发器名 DisABLE|ENABLE;
```

- 关闭（禁止）或重新开启（激活）一个表上的所有触发器的命令则为：

```
ALTER table 表名 DisABLE|ENABLE ALL TRIGGERS;
```

- 如果由于某种原因，一个触发器变成了无效的(invalid)，应该使用ALTER TRIGGER显式地重新编译这个触发器（的PL/SQL程序代码），重新编译一个表上的一个触发器的命令如下：

```
ALTER TRIGGER 触发器名 COMPILE;
```

- 如果已经不再需要某个触发器时，应该使用DROP TRIGGER语句从数据库中删除这个没用的触发器，从数据库中删除一个触发器语句如下：
    - 注意，当一个表被删除时，所有在该表上的触发器也会被删除。
```
DROP TRIGGER 触发器名;
```

**为什么将一个触发器设置为DisABLED状态**

其实，将一个触发器设置为DisABLED状态往往是一个无奈之举。有时一个系统可能已经满负荷运行，系统的效率很低。此时可能已经没有其他方法可以提高系统效率了，在这种情况下，就可能暂时关闭一个或多个触发器以换取系统效率的提高。实际上，这是一种以牺牲数据的完整性和系统安全为代价的系统优化举措，应该也是不得已而为之。系统的安全与效率永远是一对矛盾，越安全的系统，效率往往越低，反之效率越高的系统，就越不安全。最后，作为系统的开发者或管理者要在这两者之间做出艰难的平衡。一般触发器处在DisABLED状态应该是一个临时而短暂的状态，一旦系统效率正常之后，应该尽快将这些触发器重新设置回ENABLED。一般将一个触发器临时设置成DisABLED状态的情况可能如下：

1. 该触发器所引用的一个对象无法获得。
2. 在执行大规模数据装入操作时，想不触发触发器以加快数据的装入。
3. 重新装入数据。

在实际工作中，所开发的数据库触发器要经过严格的测试之后才敢在真正生产系统上使用。测试触发器的程序代码一般是一个相当耗时的测试过程，一般触发器越复杂要测试的细节可能就越多，通常测试一个数据库触发器的基本步骤如下：

1. 测试每一个触发的数据操作，以及没有触发的数据操作。
   - 首先测试大多数成功的情况。
   - 测试最可能失败的情况以观察触发器能否恰当地处理。
2. 测试when子句的每一种可能。
3. 测试由基本数据造成的触发器的直接触发以及由过程引起的间接触发。
4. 测试触发器对其他地触发器的影响。
5. 测试其他触发器对该触发器的影响。
6. 可使用DBMS OUTPUT软件包调试（排错)触发器。

#### 数据字典 USER_OBJECTS , USER_TRIGGERS

- 尽量使用USER_TRIGGERS数据字典来查询信息。USER_OBJECT信息可能有误。

```
select object_id
      ,object_name
      ,created
      ,object_type
from USER_OBJECTS
where object_type = 'TRIGGER';
```

```
select trigger_name
      ,trigger_type
      ,triggering_event
      ,status
from USER_TRIGGERS;
```

#### 触发器的权限问题

- 创建触发器的权限 需要：
    1. `CREATE TRIGGER` CREATE ANY TRIGGER
    2. 拥有在触发语句中所指定的表，
    3. 且有对这个表的ALTER权限。或ALTER ANY table权限

- 如果用户的触发器引用了如何不在用户模式中的对象，那么创建该触发器的用户必须在引用的过程，函数和软件包上具有执行权限，且该执行权限不是通过角色授予的。

- 创建数据库级触发器 需要：`ADMINisTER database TRIGGER`权限

### 触发器的具体应用场景

1. 复杂的安全性检查 --比如周末不允许操作数据库
      - 触发器案例一：禁止在非工作时间插入数据

2. 数据的确认 --涨后的工资大于涨前的工资
      - 触发器案例二：涨工资不能越涨越少 :old和:new的使用要注意

3. 数据库审计 --跟踪表上所做的数据操作，什么时间什么人操作了什么数据，操作的人是什么。基于值的审计
     - 触发器案例三：创建基于值的触发器

4. 数据的备份和同步 --异地备份，把主数据的数据自动同步到备数据库中
     - 触发器案例四：数据库的备份和同步
     - 利用触发器实现数据的同步备份，多用于异地分布式数据库,
     - 还能使用快照备份，快照备份是异步备份，而触发器是同步备份，没有延时的

## PL/SQL的子程序

- 模块化和分层的子程序设计

**分层：**
Oracle建议将SQL逻辑和业务逻辑分开，即创建一个至少具有两层的应用设计：

（避免SQL语句发布在整个PL/SQL程序中的麻烦[因为PL/SQL允许SQL语句无缝嵌入PL/SQL程序中]）

  - 数据访问层 ： 使用SQL语句访问数据的子程序
  - 业务逻辑层 ： 实现业务处理规则的子程序，这些子程序可以调用也可以不调用数据访问层的子程序

**PL/SQL程序代码块实现模块化程序设计的组件（模块）：**

- 匿名程序块
- 子程序（过程/函数）
- 软件包
- 数据库触发器

**子程序**

- 子程序就是一个命名的PL/SQL程序块，可以接收参数和在调用环境中被调用 
   - 可以在一个PL/SQL程序块中，也可以在另一个子程序中声明和定义一个子程序 
- 子程序包含一个声明段，一个执行段，一个可选的异常处理段。
- 子程序可以被编译或存储在数据库中以提高模块化，可扩展性和重用性。

**优点**

- 使代码易于维护：
   - 因为子程序只存放在一个地方，所以任何修改也只需在一个地方进行，使受影响的应用程序最少，并使测试工作量大幅度地下降，同时出错的概率也明显下降。
- 提高了数据的安全性：
   - 默认子程序是以定义者权限执行的，这个执行权限并不允许一个调用者直接访问子程序可以访问的对象。实际上，数据的安全是通过控制非授权用户以安全权限间接访问数据库对象来实现的。
- 保证数据的完整性：
   - 数据的完整性是通过修改操作要么一起都执行，要么都不执行来实现的。
- 改进性能：
   - 服务器进程将SQL(也可能是PL/SQL)语句的正文和分析后的代码(parsed code)以及执行计划都放在共享池(shared pool)的库高速缓存中。在进行编译时，服务器进程首先会在共享池中搜索是否有相同的SQL或PL/SQL语句（正文），如果有，就不进行任何后续的编译处理，而是直接使用已存在的分析后的代码和执行计划。因为是使用的同一个过程和函数，所以PL/SQL共享的概率明显地增加。另外，使用存储过程和函数也可以减少网络的流量，从而进一步提高系统的整体性能。
- 使代码更清晰易读：
   - 通过使用合适的过程或函数名，以及使用约定俗成的可以描述子程序操作的对象命名规则会显著增加代码的易读性，因此也就减少了对注释的需求，并且使代码的清晰度加强。
   

**与匿名程序块的比较（之前写的PL/SQL程序块）**

|               匿名程序块                |            子程序            |
| --------------------------------------- | ---------------------------- |
| 无名PL/SQL程序块                        | 命名的PL/SQL程序块            |
| 每次执行时编译，只被编译和执行一次         | 只编译一次，可多次执行         |
| 不能存储在数据库中（不是永久的数据库对象） | 可以存储在数据库中            |
| 不能被其他应用程序调用                    | 有名字，可以被其他应用程序调用 |
| 不带返回值                              | 调用函数的子程序必须有返回值   |
| 不能带有参数                             | 可以带有参数                  |


**存储函数FUNCTIon和过程procedure**

- [存储函数：有返回值，创建完成后，通过select FUNCTIon() from dual;执行]
- [存储过程：由于没有返回值，创建完成后，不能使用select语句，只能使用pl/sql块执行]

|         procedure          |              FUNCTIon               |
| :------------------------: | :---------------------------------: |
|    作为一个PL/SQL语句执行    |         作为一个表达式来调用          |
|    在头中不包含RETURN子句    | 在头中必须包含且只能包含一个RETURN子句 |
|  可以使用多个输出参数传递值   |         必须返回一个单一的值          |
| 可以包含一个无值的RETURN语句 |      必须包含至少一个RETURN语句       |

### 过程 procedure

- 存储过程（procedure）是SQL语句和流程控制语句的预编译集合
语法：

```plsql
CREATE [OR REPLACE] procedure 过程名
    [(参数1 [方式] 数据类型1 --不能有长度
     ,...)]
is | AS  --PL/SQL程序块
    [本地变量的声明;...]
begin
 --执行的操作;
end [过程名];
```

- 在存储过程中，is或AS之后的语句是PL/SQL程序块
- is或AS 相当于替代了declare关键字，作用可看作相同 ，
   - 可以正常声明在PL/SQL程序块中的声明（包括再声明一个过程）
- **参数中不能带有长度信息**

**调用过程**

1.

```plsql
execute 过程(参数,...);
```
```plsql
variable 变量 数据类型(长度);

execute :变量 := 赋值;

execute 过程(:变量);
```

2.

```plsql
declare
 OUT参数对应的赋值实参的声明；
begin
  过程(参数,赋值实参,...);
end;
```

- 在一个PL/SQL存储过程的定义中，任何地方都不能引用替代变量和宿主变量
- 参数： （看作本地变量）用来在调用环境和过程之间进行数据的传递。在过程的头中声明的，即在过程名之后和本地变量声明段之前。
   - 形参定义了一个在PL/SQ程序块的执行段中使用的一个变量名。当调用这个过程时，使用实参提供输入值或接受返回的结果
   - 在说明参数的数据类型时，不用声明长度 
- 方式：

|                     IN                     |           OUT            |                                   IN OUT                                    |
| :----------------------------------------: | :----------------------: | :-------------------------------------------------------------------------: |
|                    默认                     |                          |                                                                             |
|          从调用环境传递一个值给过程           | 从过程传递一个值给调用环境 | 从调用环境传递一个值给过程，并使用相同的参数名从过程返回给调用环境一个可能不同的值 |
|               形参如同常量使用               |       初始化的变量        |                                 初始化的变量                                 |
| 实参可以是一个文字，表达式，常量或初始化的变量 |      必须是一个变量       |                                必须是一个变量                                |
|              可以赋予一个默认值              |      不能赋予默认值       |                                不能赋予默认值                                |

- IN参数允许将值传递给子程序。它是一个只读参数。在子程序中，IN参数的作用如常数，它不能被赋值。可以将常量，文字，初始化的变量或表达式作为IN参数传递。也可以将其初始化为默认值; 然而，在这种情况下，从子程序调用中省略它。 它是参数传递的默认模式。参数通过引用传递。
- OUT参数返回一个值给调用程序。在子程序中，OUT参数像变量一样。 可以更改其值并在分配该值后引用该值。实际参数必须是可变的，并且通过值传递。在返回调用环境之前，必须先为OUT或IN OUT参数赋值，不能为OUT和IN OUT参数赋默认值
- IN OUT参数将初始值传递给子程序，并将更新的值返回给调用者。 它可以分配一个值，该值可以被读取。对应于IN OUT形式参数的实际参数必须是变量，而不是常量或表达式。正式参数必须分配一个值。实际参数(实参)通过值传递。

#### IN参数的使用

- 作为输入对象
- 需要一个表达式，文字等。


```plsql
create or replace procedure raise_salary
(
 p_emp_id IN copy_emp.employee_id%TYPE
,p_rate IN NUMBER
)
is
begin
  UPDATE copy_emp
  set salary = salary * (1 + p_rate * 0.01)
  where employee_id = p_emp_id;
end raise_salary;
```

```plsql
execute raise_salary
```

#### OUT参数的使用

- 作为输出对象,
- 需要一个变量，无需赋值

```plsql
create or replace procedure get_employee
(
 p_emp_id IN copy_emp.employee_id%TYPE
,p_name OUT copy_emp.last_name%TYPE
,p_sal OUT copy_emp.salary%TYPE
,p_job OUT copy_emp.job_id%TYPE
)
is
begin
  select last_name
        ,salary
        ,job_id
  into p_name
      ,p_sal
      ,p_job
  from copy_emp
  where employee_id = p_emp_id;
end get_employee;
```

```plsql
declare
 v_last_name copy_emp.last_name%TYPE;
 v_sal copy_emp.salary%TYPE;
 v_job copy_emp.job_id%TYPE;
begin
  get_employee(179,v_last_name,v_sal,v_job);
  --使用过程
  dbms_output.put_line('**************************'||
                       'name:'||CHR(9)|| v_last_name ||CHR(10)||
                       'sal:'||CHR(9)||v_sal||CHR(10)||
                       'job:'||CHR(9)||v_job||CHR(10)
                       );
end;
```

#### IN OUT参数的使用

- 作为输入和输出的对象
- 需要一个已经赋值的变量

```plsql
create or replace procedure standard_phone
(
 p_phone_id IN OUT varchar2
)
is
begin
  p_phone_id := '(' || SUBSTR(p_phone_id,1,3) ||')'||CHR(9)||
                '-' || UPPER(p_phone_id);
  dbms_output.put_line(p_phone_id);
end standard_phone;
```

```plsql
SQL> variable g_phone_no varchar2(20)
SQL> execute :g_phone_no := 'xiaomi'

PL/SQL 过程已成功完成。

SQL> PRINT

G_PHonE_NO
--------------------------------------------------------------------------------
xiaomi

SQL> execute standard_phone(:g_phone_no);

PL/SQL 过程已成功完成。
```
```
declare
 v_phone_no varchar2(20) := 'ximao';
begin
  standard_phone(v_phone_no);
end;
```

##### 使用`SQP*PLUS`查看OUT参数

1. 声明`SQL*PLUS`的宿主变量
2. 如果是IN OUT参数需要为这个变量赋值
3. 以宿主变量为参数执行过程
4. 使用`SQL*PLUS`的PRINT命令
                          
#### 传递实参的表示法

```plsql
create or replace procedure update_sal
(
 p_id IN copy_emp.employee_id%TYPE
,p_new_job IN copy_emp.job_id%TYPE
,p_new_sal IN copy_emp.salary%TYPE
,p_dept OUT copy_emp.department_id%TYPE
)
is
begin
  UPDATE copy_emp
  set copy_emp.salary = p_new_sal
     ,job_id = p_new_job
  where employee_id = p_id;
  
  select department_id
  into p_dept
  from copy_emp
  where employee_id = p_id;
end; 
```

1. 按位置

- 以所声明的形参相同的位置列出实参

```plsql
SQL> variable v_dept NUMBER;
SQL> execute update_sal(117,'IT',7000,:v_dept);
```

2. 按名字

- 以任意顺序列出实参和与之对应的形参。
- 使用关联操作符`=>`    

```plsql
SQL> variable v_dept NUMBER;
SQL> execute update_sal(p_id => 117, p_dept => :v_dept, p_new_job => 'IT', p_new_sal => 9000);
```

3. 组合

- 前两种混合使用

#### 在过程中声明和调用另一个过程

```plsql
create or replace procedure audit_emp_dml
(
 p_id IN copy_emp.employee_id%TYPE
)
is
 procedure log_exec
 is
 begin
   INSERT into log_table(user_id,log_date,employee_id)
   VALUES(TO_char(USER),sysdate,p_id);
 end log_exec;
begin
  DELETE from copy_emp
  where employee_id = p_id;
  log_exec;
end audit_emp_dml;
```
```plsql
create or replace procedure reset_sal
(
 p_new_sal IN NUMBER
,p_grade IN NUMBER(2) 
)
is 
  error_sal exception;
  
  procedure check_sal
    (
    ,p_max IN NUMBER
    ,p_min IN NUMBER
    ) 
    is
    begin
      IF p_new_sal NOT between p_max and p_min then
        RAisE error_sal;
      end IF;
    end check_sal;
begin
   IF p_grade = 1 then
       check_sal(1000,5000);
       dbms_output.put_line('工资为：'||p_new_sal||CHR(9)||'等级为：'||p_grade);
   ELSIF p_grade = 2 then
       check_sal(5000,10000);
       dbms_output.put_line('工资为：'||p_new_sal||CHR(9)||'等级为：'||p_grade);
   ELSE 
     RAisE error_sal;
   end IF;
exception
   when error_sal then
     dbms_output.put_line('输入工资有误');
     commit;
   when OTHERS then
     rollback;
end reset_sal;

ORA-00900: 无效 SQL 语句
```

#### 在过程中处理异常

```plsql
create or replace procedure update_employee
(
 p_id IN NUMBER
)
is
 emp_null exception;
begin
  UPDATE copy_emp
  set salary = 1000
  where employee_id = p_id;
  
  IF(SQL%NOTFOUND OR SQL%NOTFOUND = NULL) then
     RAisE emp_null;
  end IF;
exception
  when emp_null then
    dbms_output.put_line('该员工不存在');
    rollback;
  when OTHERS then
    dbms_output.put_line('其他错误');
    rollback;
end update_employee;
```

##### 在调用过程时有异常处理和没有异常处理的作用

- 在调用过程时，如果在过程中出现了异常，而存储过程中有相应的异常段来处理异常，即捕获异常，调用该过程的PL/SQL程序块可以进行执行。而如果在存储过程中没有相应的异常段来处理异常，则会出现传播异常，使得整个PL/SQL程序块终止执行。

**例**

- 要操作的表

```plsql
CREATE table test
(
 use_name varchar2(25) UNIQUE
,use_date DATE
,use_errors varchar2(50)
)
```

- 有带异常处理的存储过程

```plsql
create or replace procedure user_test
(
 p_name IN test.use_name%TYPE
)
is
begin
  INSERT into test(use_name,use_date)
  VALUES(p_name,sysdate);
exception
  when OTHERS then
    commit;
end user_test;
```

- 没有带异常处理的存储过程

```plsql
create or replace procedure user_test
(
 p_name IN test.use_name%TYPE
)
is
begin
  INSERT into test(use_name,use_date)
  VALUES(p_name,sysdate);
end user_test;
```

- 测试语句

```plsql
begin
  user_test('1');
  user_test('1'); --use_name唯一性不可重复的异常
  user_test('2');
end;
```

- 有带异常处理的结果
  - 成功执行了PL/SQL程序块 ，只有出现异常的部分转入异常段处理。 

```plsql
SQL> /
PL/SQL procedure successfully completed

SQL> select*
  2  from test
  3  ;
USE_NAME                  USE_DATE    USE_ERRORS
------------------------- ----------- --------------------------------------------------
1                         2022/10/6 1 
2                         2022/10/6 1 
```

- 没有带异常处理的结果
  - 没有执行PL/SQL程序块
  
```plsql
SQL> /
begin
  user_test('1');
  user_test('1'); --use_name唯一性不可重复的异常
  user_test('2');
end;
ORA-00001: 违反唯一约束条件 (SCOTT.sys_C0013141)
ORA-06512: 在 "SCOTT.USER_TEST", line 7
ORA-06512: 在 line 3

SQL> select *
  2  from test
  3  ;
USE_NAME                  USE_DATE    USE_ERRORS
------------------------- ----------- --------------------------------------------------

```


#### 存储过程的删除与查看

##### 查看

- 通过查找模式对象的方法
  - 数据字典：user_objects

```plsql
SQL> col OBJECT_NAME for a20
--先设置显示格式

select object_id
      ,object_name
      ,created
      ,status
from user_objects
where object_type = 'procedure';
```
![](c:/users/zjk10/oneDrive/NoteBook/pictures/54505115221047.png =402x)

- status属性：
   - VALID 表示可以被调用
   - INVALID 表示不可以被调用，必须重新编辑
      - 一般由于该工程使用的对象（通常是表）的定义被修改了 

##### 删除

```plsql
DROP procedure 过程;
```

#### 例

##### 定义一个存储过程: 获取给定部门的工资总和(通过 out 参数), 要求:部门号和工资总额定义为参数

```plsql
create or replace procedure sum_sal_procedure
(
 p_dept_id NUMBER
,v_sum_sal OUT NUMBER
)
is
CURSOR sal_cursor is 
   select salary 
   from employees 
   where department_id = p_dept_id;
begin
       v_sum_sal := 0;
       for sal_rec IN sal_cursor LOOP
           --dbms_output.put_line(sal_rec.salary);
           v_sum_sal := v_sum_sal + sal_rec.salary;
       end LOOP;       
       dbms_output.put_line('sum salary: ' || v_sum_sal);
end;

[执行]
declare 
     v_sum_sal number(10) := 0;
begin
     sum_sal_procedure(80,v_sum_sal);
end;
```

##### 自定义一个存储过程完成以下操作

```plsql
对给定部门(作为输入参数)的员工进行加薪操作, 若其到公司的时间在 (? , 95) 期间,为其加薪 %5
                                                         [95 , 98)              %3       
                                                         [98, ?)                %1
得到以下返回结果: 为此次加薪公司每月需要额外付出多少成本(定义一个 OUT 型的输出参数).
create or replace procedure add_sal_procedure
(
 p_dept_id NUMBER
,temp OUT NUMBER
)
is
       CURSOR sal_cursor is 
          select employee_id id
                ,hire_date hd
                ,salary sal 
          from employees 
          where department_id = p_dept_id;

       a NUMBER(4, 2) := 0;
begin
       temp := 0;       
       for c IN sal_cursor LOOP
           a := 0;    
           IF c.hd < to_date('1995-1-1', 'yyyy-mm-dd') then
              a := 0.05;
           ELSIF c.hd < to_date('1998-1-1', 'yyyy-mm-dd') then
              a := 0.03;
           ELSE
              a := 0.01;
           end IF;
           temp := temp + c.sal * a;
           UPDATE employees 
           set salary = salary * (1 + a) 
           where employee_id = c.id;
       end LOOP;       
end;
```

### 函数 FUNCTIon

- 函数是一个命名的PL/SQL程序块，它可以接受参数，可以被调用，并且它会返回一个值。
- 函数与过程在结构上极为相似，但是通常函数被用来计算一个值。
- 一个函数可以声明一个参数或多个参数,必须返回给它的调用环境一个值（而且也只能是一个值），
    - 而一个过程可以返回零个或多个值给它的调用环境。
- 与过程相似，一个函数也有一个头，并包含一个声明段、一个执行段和一个可选的异常处理段。
- 在函数头中必须包含一个RETURN(返回)子句，并且在函数的执行段中必须包含一个RETURN(返回)语句（至少一个，有时可能是多个）。
    - 在函数中必须有一个RETURN(返回)语句以提供一个返回值，
    - 并且这个值的数据类型要与该函数声明中的RETURN子句的数据类型相一致。
- 为了重复执行一个函数，可以将该函数作为一个模式对象存储在数据库中。存储在数据库中的函数被称为存储函数。
    - 当然，也可以将函数创建在客户端的应用程序中。
    - 函数方便了程序代码的重用并使程序代码的维护更加容易。一旦函数被验证过，它们就可以被用在任何应用程序中，而且应用程序的数量不限。如果处理需要改变，只有相关的函数需要更改
- 函数也可以作为SQL表达式或PL/SQL表达式的一部分来调用。
    - 在一个SQL表达式的环境中，一个函数必须遵守一些特殊的规则以控制函数所造成的副作用。
- 在存储函数的PL/SQL程序块中不能引用宿主或绑定变量。
    - 调用时，可以引用

- 语法：

```plsql
--函数的声明(有参数的写在小括号里)
create or replace FUNCTIon 函数名
(
 参数1 [模式1] 数据类型1
 ,...
)
RETURN 返回值的数据类型 
is|AS 
 --PL/SQL块变量、记录类型、游标的声明(类似于前面的declare的部分)
begin
 --函数体(可以实现增删改查等操作，返回值需要return)
       RETURN 表达式;
       --RETURN子句中的数据类型一定不能包括数据长度。
end [函数名];
```

- 虽然在函数中可以使用OUT和INOUT参数，但是这并不是一个良好的编程习惯，
    - 因为这样的函数有多个出口，所以它们很难调试和维护。
    - 因此，如需要从一个函数返回多个值时，最好考虑使用组合数据类型，如PL/SQL的记录或INDEX by表(PL/SQL的数组)。

```plsql
create or replace FUNCTIon get_sal
(
 v_id IN copy_emp.employee_id%TYPE
)
RETURN NUMBER
is
 v_sal copy_emp.salary%TYPE := 0;
begin
  select salary
  into v_sal
  from copy_emp
  where employee_id = v_id;
  
  RETURN v_sal;
end get_sal;
```
- 调用

1. PL/SQL程序块

```plsql
declare 
begin
  dbms_output.put_line('sal:'||get_sal(177));
end;
```

2.  execute语句

```plsql
execute dbms_output.put_line(get_sal(177));
```

3. 在SQL语句中调用

```sql
select employee_id
      ,get_sal(employee_id) sal
      --需要限制查询只返回一个值
      --否则：ORA-01422: 实际返回的行数超出请求的行数
from copy_emp
where employee_id = 177
```

4. 当查询不到返回值时：

```sql
ORA-01403: 未找到任何数据
```

#### 在SQL表达式中使用用户定义的函数

- 扩展了SQL的功能，特别是在执行非常复杂、非常令人费解或SQL无法完成的计算时，非常有用。
- 与在应用程序中过滤数据相比，使用函数在where子句中过滤数据可以提高效率，因为可以创建一个基于这个函数的索引以提高查询的效率。
- 可以增加数据的独立性，因为复杂的数据分析处理是在Oracle服务器中进行的，而不是将数据提取到应用程序中进行处理。如果数据量大，利用存储函数会明显减少网络的流量。
- 存储函数是可以共享的，在第一次调用时这个函数会被装入数据库的内存缓冲区，因此之后的调用就可能使用的是内存中的版本，当然速度会快很多。另外，也可以将经常使用的函数常驻内存以提高效率。
- 通过对字符串编码和使用函数来操作这一字符串，可以维护和操控这一新的数据类型（如提取电话号码中的国家号、地区号或本地号)。

**只要允许使用SQL内置单行函数的地方就可以调用**

- select子句的列表
- where子句和HEVING子句的条件
- connect by和START WITH和ORDER by和GROUP by子句
- INSERT语句中的VALUES子句
- UPDATE语句中的set子句

```plsql
create or replace FUNCTIon format_phone
(
 p_phone IN varchar2
)
RETURN varchar2
is
 v_phone varchar2(38);
begin
 v_phone := '('||SUBSTR(p_phone,1,3)||')'||
            '-'||SUBSTR(p_phone,5,3) ||
            '-'||SUBSTR(p_phone,9);
 RETURN v_phone;
end;
```
```sql
select employee_id
      ,last_name
      ,format_phone(phone_number)
from employees;
```

##### 从SQL表达式调用函数的限制

- 为了从SQL表达式中调用一个用户定义的SQL函数，这用户定义的函数必须满足如下条件：
  - 该函数只能是存储函数
  - 该函数只接受IN参数
  - 只接受有效的SQL数据类型作为参数，不接受PL/SQL说明的数据类型做参数
  - 返回的数据类型只能是有效的SQL数据类型，而不能是PL/SQL说明的数据类型
- 另外，在一个SQL表达式中调用一个函数时也有如下限制：
  1. 所有的参数必须使用位置表示法，而不能使用名字表示法。
  2. 必须拥有所调用的函数或者在所调用的函数上有执行(execute)权限。
- 在用户定义的PL/SQL函数上还有一些额外的限制，它们包括：
   - 不能在CREATE table或ALTER table 语句的CHECK约束子句中调用这样的函数
   - 不能使用这样的函数为一个列说明默认值
- 需要指出的是：在一个SQL表达式中只能调用存储函数，而不能调用存储过程，除非这个过程是从一个函数中调用的并且满足以上所列的要求。
- 要执行一个调用存储函数的SQL语句，Oracle服务器就必须确定所调用的函数是不是没有一些特定的副作用，因为这些副作用可能会对数据库中的表产生无法接受的更改。为此，当在一个SQL语句的表达式中调用一个函数时，Oracle需加上以下一些附加的限制：
   - 从表达式中调用函数时，该函数不能包含DML语句，即该函数不能修改数据库中表的数据。
   - 当从一个UPDATE/DELETE语句中调用一个函数时，该函数不能查询或更改这个语句正在操作的表。
   - 当从一个select、INSERT,UPDATE或DELETE语句中调用一个函数时，该函数不能直接地或通过子程序（或SQL)间接地执行事物控制语句，如：
       - 一个commit或rollback语句
       - 一个会话控制语句（如set role)
       - 一个系统控制语句（如ALTER system)
       - 任何DDL语句（如CREATE)
       

##### 从SQL中用名字表示法和混合表示法调用函数

```plsql
create or replace FUNCTIon test_func
(
 p_num1 IN NUMBER := 1
,p_num2 IN NUMBER := 1
)
RETURN NUMBER
is
 v_num NUMBER;
begin
  v_num := p_num1 + p_num2;
  RETURN v_num;
end test_func;
```

```sql
SQL> select test_func(p_num2 => 4)
  2  from dual;
```

#### 函数的查询与删除

- 通过查找模式对象的方法
  - 数据字典：user_objects

```sql
SQL> col OBJECT_NAME for a20
--先设置显示格式

select object_id
      ,object_name
      ,created
      ,status
from user_objects
where object_type = 'FUNCTIon';
```

![](c:/users/zjk10/oneDrive/NoteBook/pictures/Snipaste_2022-11-18_11-14-17.png =400x)

- status属性：
   - VALID 表示可以被调用
   - INVALID 表示不可以被调用，必须重新编辑
      - 一般由于该工程使用的对象（通常是表）的定义被修改了 


#### 应用

##### 函数的 helloworld: 返回一个 "helloworld" 的字符串

```plsql
create or replace FUNCTIon hello_func
RETURN varchar2
is
begin
       RETURN 'helloworld';
end;

执行函数
begin 
    dbms_output.put_line(hello_func());
end;
或者： 
select hello_func() 
from dual;
```

##### 返回一个"helloworld: atguigu"的字符串，其中atguigu 由执行函数时输入

```plsql
--函数的声明(有参数的写在小括号里)
create or replace FUNCTIon hello_func(v_logo varchar2)
--返回值类型
RETURN varchar2
is 
--PL/SQL块变量的声明
begin
       --函数体
       RETURN 'helloworld'|| v_logo;
end;
```

##### 创建一个存储函数，返回当前的系统时间

```plsql
create or replace FUNCTIon func1
RETURN DATE
is
v_date DATE;  --定义变量
begin
 --函数体
 --v_date := sysdate;
       select sysdate 
       into v_date 
       from dual;
       dbms_output.put_line('我是函数哦');
       RETUEN v_date;
end;

执行法1：
select func1 
from dual;
执行法2：
declare
  v_date DATE;
begin
  v_date := func1;
  dbms_output.put_line(v_date);
end;
```

##### 定义带参数的函数: 两个数相加

```plsql
create or replace FUNCTIon add_func(a NUMBER, b NUMBER)
RETURN NUMBER
is
begin
       RETURN (a + b);
end;
执行函数
begin
    dbms_output.put_line(add_func(12, 13));
end;
或者
    select add_func(12,13) 
    from dual;
```

##### 定义一个函数: 获取给定部门的工资总和, 要求:部门号定义为参数, 工资总额定义为返回值

```plsql
create or replace FUNCTIon sum_sal(dept_id NUMBER)
RETURN NUMBER
is       
CURSOR sal_cursor is select salary 
                     from employees 
                     where department_id = dept_id;
v_sum_sal NUMBER(8) := 0;   
begin
       for c IN sal_cursor LOOP
           v_sum_sal := v_sum_sal + c.salary;
       end LOOP;       
       --dbms_output.put_line('sum salary: ' || v_sum_sal);
       RETURN v_sum_sal;
end;
执行函数
begin
    dbms_output.put_line(sum_sal(80));
end;
```

##### 关于 OUT 型的参数: 因为函数只能有一个返回值, PL/SQL 程序可以通过 OUT 型的参数实现有多个返回值

```plsql
要求: 定义一个函数: 获取给定部门的工资总和 和 该部门的员工总数(定义为 OUT 类型的参数).
要求: 部门号定义为参数, 工资总额定义为返回值.
create or replace FUNCTIon sum_sal(dept_id NUMBER, total_count OUT NUMBER)
RETURN NUMBER
is 
CURSOR sal_cursor is select salary 
                     from employees 
                     where department_id = dept_id;
v_sum_sal NUMBER(8) := 0;   
begin
       total_count := 0;
       for c IN sal_cursor loop
           v_sum_sal := v_sum_sal + c.salary;
           total_count := total_count + 1;
       end LOOP;       
       --dbms_output.put_line('sum salary: ' || v_sum_sal);
       RETURN v_sum_sal;
end;   

执行函数:
declare 
  v_total NUMBER(3) := 0;
begin
    dbms_output.put_line(sum_sal(80, v_total));
    dbms_output.put_line(v_total);
end;
```


## 异常处理

- 用来处理正常执行过程中未预料的事件，程序块的异常处理，预定义的错误和自定义的错误，
- 由于PL/SQL程序块一旦产生异常而没有指出如何处理时，程序就会自动终止整个程序运行
- 当PL/SQL抛出一个异常时，那个程序就终止了，但是可以说明一段异常处理程序在这个程序块结束之前执行最终的操作。
   - 当程序发生错误时，程序的控制无条件转到异常处理部分（如果声明了异常段）。

**处理异常的方式** 

1. 捕获异常

   - 在PL/SQL程序中包含了一个异常段以捕获异常。如果异常在这个程序的执行段中被抛出，那么处理就自动跳转到这个程序的异常段中的相应异常处理程序。如果异常处理程序成功地处理了这个异常，那么这个异常就不会传播到包含它的程序段，也不会传播到调用环境，而且这个PL/SQL程序块成功地结束。
2. 传播异常
   - 如果一个异常在程序的执行段被抛出并且没有对应的异常处理程序，那么这个PL/SQL程序块以失败而终止，并且这个异常被传播到包含它的程序块或调用环境，调用环境可以是任何应用程序（如调用PL/SQL程序的SQL*Pus)。
   - 当一个子块处理了一个异常时，该子块正常结束，程序的控制直接转到紧随子块的end语句其后的语句。
   - 然而，如果一个PL/SQL程序抛出了一个异常并且当前程序块没有为这个异常定义异常处理程序，那么该异常就会传播到后续的包含块，直到找到一个异常处理程序为止。如果所有的包含程序都无法处理这个异常，在宿主环境的结果中就会产生一个无法处理的异常。当这个异常传播给个包含程序块时，原程序块中的其余的可执行语句将被绕过（忽略掉）。

- 以上所说的异常处理方式的好处是：
    - 可以在一个程序块中只包含该程序块所需的异常处理语包而将其他更为适用的异常处理语句放在包含块中。这样可以明显地减少代码量，也使程序的逻辑流程更为清晰。

- 与其他程序设计语言相比，PL/SOL的异常处理有着明显的优势。首先对于绝大多数数据库中出现的错误（异常），PL/SQL都可以隐含（自动）地抛出(RAisE)，这无疑降低了代码的复杂度，也减少了相应的代码量。另外，抛出的异常是统一跳转到异常段处理的，因此当在多个程序语句处需要同样的异常处理时，PL/SQL只需要一个异常处理程序，这使得异常处理的代码量明显减少，否则有可能异常（错误）处理的代码会淹没正常的程序语句。可以毫不夸张地说，恰当地使用PL/SOL的异常处理会使程序代码更为清晰、简练。

**格式**

```
exception
  when first_exception then 
     <code to handle first_exception>;
  when seconde_exception then 
     <code to handle seconde_exception>; 
  when OTHERS then 
     <code to handle others>;
end; 
```

- 当一个异常发生时，PL/SQL在离开这个异常段之前只执行一个异常处理子句。
- 一个异常段中只能有一个OTHERS子句。
- 异常不能出现在SQL语句或赋值语句中。

### 预定义异常

- Oracle预定义的异常情况大概有24个，
- 对于这种异常情况的处理，无需在程序中定义，由Oracle自动将其引发

```
declare
  v_sal employees.salary%TYPE;
begin
  select salary into v_sal
  from employees
  where employee_id >100;
  dbms_output.put_line(v_sal);
exception
  when Too_many_rows then dbms_output.put_line('输出的行数太多了');
end;
```

### 非预定义异常

- 对这种异常情况的处理需要用户在程序中定义，然后由Oracle自动将其引发

#### 捕获非预定义的异常

- **使用PRAGMA exception_INIT()函数为标准Oracle错误创建异常。**

- PRAGMA ：伪指令 
   - 表示这个语句时一个编译指令，而当PL/SQ程序块执行时不会被处理。
   - 指示PL/SQ编译器将在这个程序块中出现的所有该异常名解释成相关的Oracle服务器错误代码

```plsql
declare
  异常名 exception; --声明异常名
  PRAGMA exception_INIT(已经声明的异常,标准Oracle错误代码);
begin
  ...;
exception
  ...;
end;
```

```plsql
declare
  v_sal employees.salary%TYPE;
  --声明一个异常
  DELETE_mgr_excep exception;
  --把自定义的异常和oracle的错误关联起来
  PRAGMA exception_INIT(DELETE_mgr_excep,-2292);
begin
  DELETE from employees
  where employee_id = 100;
  
  select salary into v_sal
  from employees
  where employee_id > 100;
  
  dbms_output.put_line(v_sal);
exception
  when Too_many_rows then 
     dbms_output.put_line('输出的行数太多了');
  when DELETE_mgr_excep then 
     dbms_output.put_line('Manager不能直接被删除');
  when OTHERS then
     NULL; --如果存在该异常，则不做任何操作
end;
```

#### 捕获异常的两个函数

- SQLCODE
   - 为错误代码返回一个数值（可以赋给数字变量） 
| SQLCODE返回值    |描述     |
| :-: | :-: |
|    0 |   没有遇到异常  |
|    1 |    用户定义的异常 |
|   +100  |  NO_DATA_FOUND异常   |
|    负数 |   其他的Oracle服务器错误号码  |

- SQLERRM
   - 返回字符串数据，包含了与错误号相关的错误信息 

#### 例：一般的调试语句

```plsql
--先建一个表来存储错误信息
CREATE table errors
(
 user_name varchar2(255)
,error_date DATE
,error_code NUMBER(10)
,error_message varchar2(255)
);

--PL/SQL程序块
declare
 v_emp_id copy_emp.employee_id%TYPE := &p_emp_id;
 v_dept_id copy_emp.department_id%TYPE := &p_dept_id;
 
 v_error_code NUMBER;
 v_error_message varchar2(255);

begin
  INSERT into copy_emp(employee_id,last_name,department_id,job_id,salary)
  VALUES(v_emp_id,'一号',v_dept_id,'安保',5000);
exception
  when OTHERS then
    rollback;
    
    v_error_code := SQLCODE;
    v_error_message := SQLERRM;
    
    INSERT into errors(user_name,error_date,error_code,error_message)
    VALUES(USER,sysdate,v_error_code,v_error_message);
    
    commit;
end;
```
### 用户自定义异常

- 程序执行过程中，出现编程人员认为的非正常情况。对这种异常的处理，需要用户在程序中定义，然后显式地在程序中将其引发

```plsql
declare
  异常名 exception;
begin
  RAisE 异常名;  --抛出异常
exception
  when 异常名 then
    ...;
  when OTHERS then
    ...;
```

- RAisE语句抛出异常

```plsql
declare 
 update_null exception;
 too_high_sal exception;
 too_low_sal exception;
 
 v_id NUMBER := &p_id;
 v_sal NUMBER := &p_sal;
begin
 UPDATE copy_emp
 set job_id = '安保'
    ,salary = v_sal
 where employee_id = v_id;
 
--利用隐式游标的属性SQL%NOTFOUND来确认是否找到员工
 IF SQL%NOTFOUND then
   RAisE update_null;
 end IF;
 
 IF (v_sal >= 5000) then
   RAisE too_high_sal;
 ELSIF v_sal <= 0 then
   RAisE too_low_sal;
 end IF;
 
 dbms_output.put_line('更新成功' || CHR(10) ||
                      'employee_id: ' || v_id ||CHR(10) ||
                      'salary: ' || v_sal || CHR(10)
                       );
                       
exception
  when update_null then
    dbms_output.put_line('查询不到该员工，更新失败');
    rollback;
  when too_high_sal then
    dbms_output.put_line('工资过高(1~5000)，更新失败');
    rollback;
  when too_low_sal then
    dbms_output.put_line('工资过低(1~5000)，更新失败');
    rollback;
  when OTHERS then
    dbms_output.put_line('其他错误：' || SQLCODE || ' : ' || SQLERRM);
end;
```

```plsql
declare
  v_sal employees.salary%TYPE;
  
  --声明一个异常 非预定义异常
  DELETE_mgr_excep exception;
  --把自定义的异常和oracle的错误关联起来
  PRAGMA exception_INIT(DELETE_mgr_excep,-2292);
  
  --声明一个异常 用户自定义的异常
  too_high_sal exception;
begin
  select salary into v_sal
  from employees
  where employee_id =100;
  
  IF v_sal > 1000 then
     RAisE too_high_sal;
     --使用RAisE语句显式的抛出异常
  end IF;
  
  DELETE from employees
  where employee_id = 100;
  
  dbms_output.put_line(v_sal);
exception
  when Too_many_rows then 
     dbms_output.put_line('输出的行数太多了');
  when DELETE_mgr_excep then 
     dbms_output.put_line('Manager不能直接被删除');
  --处理异常
  when too_high_sal then 
     dbms_output.put_line('工资过高了');
end;
```

### RAisE_APPLICATIon_ERROR过程

- RAisE_APPLICATIon_ERROR过程以一种与预定义异常的显示格式一样的方式返回一个非标准的错误代码和错误信息（用户自己定义的错误代码和错误信息）
- 语法

```
RAisE_APPLICATIon_ERROR(用户说明的异常号码,'用户定义的异常信息'[,{TRUE | FALSE}])

用户说明的异常号码 范围只能是-20000~20999
用户定义的异常信息 字符串 最大2048个字节
{TRUE | FALSE}: TRUE 这个错误被放在之前错误层之上
                FALSE 默认 这个错误取代之前所有的错误
```

- RAisE_APPLICATIon_ERROR过程的主要用处是处理SQLCODE和SQLERRM函数的返回值。
    -  该过程在日志表中提供了一致的记录错误信息的方法。
- 要注意的是，RAisE_APPLICATIon_ERROR过程将终止所在PL/SQL程序块中语句的进一步执行。
- RAisE_APPLICATIon_ERROR过程既可以用在PL/SQL程序的执行段中，也可以用在PL/SQL程序的异常段中，或同时用在这两个段中。
- 无论是Oracle服务器产生的预定义的错误、非预定义的错误，还是用户定义的错误，该过程都会返回一致的错误信息，都是以错误号码和错误信息的方式显示给用户。

```plsql
declare
 e_invalid_employee exception;
 PRAGMA exception_INIT(e_invalid_employee,-20001);
         --PRAGMA exception_INIT
         --将所说明的异常与一个自定义的错误号码关联起来。

begin
  DELETE from copy_emp
  where last_name = '&p_last_name';
  
  IF SQL%NOTFOUND then
    RAisE e_invalid_employee;
  end IF;
  
  commit;
exception
  when e_invalid_employee then    
    RAisE_APPLICATIon_ERROR(-20999,'公司没有雇佣该员工');
    --并没有要求与PRAGMA exception_INIT语句中的一样，
    --只显示RAisE_APPLICATIon_ERROR语句中的参数
end;
```

```plsql
declare
 e_invalid_employee exception;
 --用户自定义的异常
begin
  DELETE from copy_emp
  where last_name = '&p_last_name';
  
  IF SQL%NOTFOUND then
    RAisE e_invalid_employee;
  end IF;
  
  commit;
exception
  when e_invalid_employee then
    RAisE_APPLICATIon_ERROR(-20099,'公司没有雇佣该员工');
end;
```

### 异常的基本程序

#### 通过 select ... into ... 查询某人的工资, 若没有查询到, 则输出 "未找到数据"

```plsql
declare
  --定义一个变量
  v_sal employees.salary%TYPE;
begin
  --使用 select ... into ... 为 v_sal 赋值
  select salary 
  into v_sal 
  from employees 
  where employee_id = 1000;
  
  dbms_output.put_line('salary:　' || v_sal);
exception
  when No_data_found then 
       dbms_output.put_line('未找到数据');
end;
或
declare
  --定义一个变量
  v_sal employees.salary%TYPE;
begin
  --使用 select ... into ... 为 v_sal 赋值
  select salary 
  into v_sal 
  from employees;
  
  dbms_output.put_line('salary:　' || v_sal);
exception
  when No_data_found then 
       dbms_output.put_line('未找到数据!');
  when Too_many_rows then 
       dbms_output.put_line('数据过多!');     
end;
```

#### 更新指定员工工资，如工资小于300，则加100；对 NO_DATA_FOUND 异常, TOO_MANY_ROWS 进行处理

```plsql
declare
   v_sal employees.salary%TYPE;
begin
   select salary 
   into v_sal 
   from employees
   where employee_id = 100;
  
   IF(v_sal < 300) then 
      UPDATE employees 
      set salary = salary + 100
      where employee_id = 100;
   ELSE dbms_output.put_line('工资大于300');
   end IF;
exception
   when no_data_found then 
      dbms_output.put_line('未找到数据');
   when too_many_rows then 
      dbms_output.put_line('输出的数据行太多');
end;
```

#### 处理非预定义的异常处理: "违反完整约束条件"

```plsql
declare
  --1. 定义异常 
  temp_exception exception;
  --2. 将其定义好的异常情况，与标准的 ORACLE 错误联系起来，使用 exception_INIT 语句
  PRAGMA exception_INIT(temp_exception, -2292);
begin
  DELETE from employees 
  where employee_id = 100;
exception
  --3. 处理异常
  when temp_exception then
       dbms_output.put_line('违反完整性约束!');
end;
```

#### 自定义异常: 更新指定员工工资，增加100；若该员工不存在则抛出用户自定义异常: no_result

```plsql
declare
  --自定义异常                                   
  no_result exception;   
begin
  UPDATE employees 
  set salary = salary + 100 
  where employee_id = 1001;
  --使用隐式游标, 抛出自定义异常
  IF sql%notfound then
     raise no_result;
  end IF;  

exception

  --处理程序抛出的异常
  when no_result then
     dbms_output.put_line('更新失败');
end;
```


## PL/SQL软件包

将相关的PL/SQL数据类型，变量，数据结构，异常和子程序捆绑在一起放入一个容器中（软件包）；
- PL/SQL软件包将逻辑上相关的组件放在一起
   - PL/SQL数据类型
   - 变量，数据结构和异常
   - 子程序（过程和函数）
   
- PLSOL软件包通常是由两部分所组成，分别是：软件包说明部分，软件包体部分（可选的）。
- 软件包本身不能被调用，也不能被参数化，更不能被嵌套。
   - 但是软件包中的子程序（过程或函数)是可以被调用的，当然软件包中的变量等也可以被引用。
   - 当一个软件包编译成功之后，其中的内容（如子程序或变量）就可以为许多应用程序所共享。

- 与存储过程或存储函数有所不同，当一个软件包中的内容（如一个过程、一个函数或一个变量）被第一次引用时，整个软件包都会被装入内存。而后续对相同软件包的访问（哪怕是不同的结构，如不同的函数、不同的过程、不同的常量等)都是直接访问内存，不需要磁盘输入输出(IO)操作。
   - 因此与使用单独的存储过程或存储函数相比，软件包可以明显提高系统的效率。将常用的一些软件包常驻内存以进一步提高系统整体的效率。牺牲一些内存空间，这是一个典型的以空间换时间的优化方法。
- 创建过程和函数的方法都是将过程和函数作为独立的模式对象存储在数据库中的，即所谓的存储过程和存储函数，而PL/SQL软件包则提供了一种替代这种存储过程与函数的方法，而且软件包还具有一些其他的优点，其主要优点如下：
1. 模块化和更易于维护：
   - 将逻辑相关的程序结构封装在一个命名的模块（软件包）中。
   - 这样每一个软件包更容易理解，并且软件包之问的接口变得简单、清晰、易于辨认和理解。
2. 更易于应用程序的设计：
   - 软件包的说明部分和体部分的编码和编译可以分开。
   - 在开始设计程序时，只需要软件包中接口（界面）信息的说明，可以在没有程序体的情况下开发和编译软件包说明部分的代码。
   - 因此，引用这个软件包的存储子程序也可以进行编译，直到准备完成应用程序之前都不需要定义软件包的体（程序的逻辑流程）。
3. 隐藏信息：
   - 用户来决定哪些程序结构是公共的（可见的和可以访问的）而哪些是私有的(隐藏的和不能访问的)。
   - 只有在软件包的说明中声明的结构对应于程序才是可见和可以访问的。
   - 因为软件包体隐藏了私有结构的定义，所以如果该定义发生了变化，只有这一个软件包受影响（不会影响应用程序或任何调用程序)。这就使用户能够在改变软件包实现的同时而不需要重新编译调用程序。
   - 通过将软件包的实现细节隐藏起来（用户无法知道软件包实现的细节），可以保护软件包的一致性
4. 附加了额外的功能：
   - 软件包的公共变量和游标在整个会话期间是持续的，
   - 因此，在这个环境中执行的所有子程序都可以共享这些公共变量和游标。它们也使用户能够在不用将其存储在数据库中的情况下跨事务地维护数据。
   - 私有结构在整个会话期间也是持续的，但是它们只在软件包内部是可以访问的。
5. 较好的性能：
   - 当第一次引用软件包中的一个子程序时，整个软件包都被装入内存，因此在后续调用该软件包中的相关子程序时就不需要额外进行磁盘输入/输出(IO)操作了。
   - 对所有用户，软件包在内存中只有一份拷贝。软件包中的子程序也避免了子程序之间的级联依赖，因此也就避免了不必要的编译，也使得依赖的层次简单化
6. 重载：
  - 利用软件包，可以重载过程和函数，即在同一个软件包中多个子程序可以同名，每一个具有不同的参数数量或不同的参数数据类型。

### PL/SQL软件包的组件及可见性

**软件包说明(package specification):**

是应用程序的接口，在软件包说明中声明公共数据类型、变量、常量、异常、游标以及可以使用的子程序。软件包说明中也可以包括伪指令(PRAGMAS）——编译器指令。

**软件包体(package body):**

定义了自身的子程序并且必须完全实现在软件包说明部分中声明的子程序。软件包体也可以定义PL/SQL结构，如数据类型、变量、常量、异常和游标。.

- 公共组件是在软件包说明部分中定义的。说明部分为软件包的用户定义了一个公共的应用程序设计接口（Application Programming Interface,.API),以使用这一软件包的特性和功能，即公共组件可以在软件包之外的任何Oracle服务器环境中引用。
- 私有组件是放在软件包体之内的并且只能被同一软件包中的其他结构所引用。但是私有组件可以引用该软件包中的公共组件。

如果一个软件包的说明部分没有包含子程序声明，那么就不需要定义软件包体

**一个软件包组件的可见性是指这个组件是否可以被看见，即是否可以被其他的组件或对象所引用，软件包组件的可见性依赖于这些组件是声明为本地的还是全局的**

- 本地变量在它们被声明的结构之中是可见的，例如：
1. 在一个子程序中定义的变量只能在这个子程序中被引用，并且对于外部组件是不可见的，即本地变量local var只能在过程a中使用。
2. 在一个软件包体中声明的私有软件包变量可以被同一软件包体中的其他组件所引用，但是它们对于软件包之外的任何子程序或对象都是不可见的，即在软件包体中的过程a和过程b是可以使用私有变量private var的，但是软件包之外的子程序或对象就不可以使用了。
- 而全局声明的组件在软件包的内部和外部都是可见的，例如：
1. 在一个软件包说明部分声明的一个公共变量可以在软件包之外引用和修改，即公共变量public_var可以在软件包之外引用。
2. 在个软件包说明部分声明的一个软件包子程序可以被外部的程序代码所调用——过程a可以从软件包之外的一个环境中调用。
- 私有子程序（如过程b)只能被该软件包中的公共子程序调用（如过程Λ）或者由被该软件包中的其他私有软件包结构所调用。

### 软件包的开发

**原则：**

1. 将一个软件包说明的语句正文与这个软件包体的语句分开存放在两个脚本文件中，方便对软件包说明或软件包体的修改
2. 一个软件包说明可以在没有软件包体的情况下存在，即软件包说明可以不说明子程序也不需要包体。
    - 而如果软件包说明不存在是不能创建软件包体的。

- Oracle服务器将软件包说明和软件包体分开存放。
   - 从而在改变软件包体中某个程序的实现时不会使所调用或引用程序结构的其他模式对象变为无效。
   

**创建软件包的说明**

```
create or replace PACKAGE 包名 is|AS
    声明的公共变量，常量，游标，异常，用户定义的数据类型和子类型
    公共过程和函数的声明
end [包名];
```

**创建软件包体**



```
--软件包说明
create or replace PACKAGE salary_pkg is
  v_std_salary NUMBER := 1380;
  procedure reset_salary(p_new_sal NUMBER, p_grade NUMBER);
end salary_pkg;

--软件包体
create or replace PACKAGE BODY salary_pkg is
  FUNCTIon validate
  (
  p_sal NUMBER
  ,p_grade NUMBER
  ) 
  RETURN BOOLEAN 
  is
    v_min_sal   salgrade.losal%type;
    v_max_sal   salgrade.hisal%type;
  begin
    select losal, hisal 
    into   v_min_sal, v_max_sal
    from   salgrade
    where  grade = p_grade;
    RETURN (p_sal between v_min_sal and v_max_sal);
  end validate;

  procedure reset_salary
  (
  p_new_sal NUMBER
  ,p_grade NUMBER
  ) 
  is 
  begin
    IF validate(p_new_sal, p_grade) then
      v_std_salary := p_new_sal;
    ELSE RAisE_APPLICATIon_ERROR(-20038, '工资超限!');
    end IF;
  end reset_salary;
end salary_pkg;
```

**软件包的调用**

```
SQL> execute salary_pkg.reset_salary(2450,4);
```

**查看错误**

```
SHOW ERRORS;
```

#### 创建和使用无体的PL/SQL软件包

- 无体软件包

在一个独立子程序中声明的变量和常量只在这个子程序执行期间存在。为了在一整个用户会话期间提供所存在的数据，可以创建一个公共（全局）变量和常量声明的软件包说明部分。在这个情况下不需要软件包体。

### 软件包的查找与删除

#### 查询

- 使用 user_objects 数据字典
  - PACKAGE
  - PACKAGE BODY

```
select object_id
      ,object_name
      ,object_type
      ,created
      ,status
from user_objects
where object_type IN ('PACKAGE','PACKAGE BODY');
```

#### 删除

- 删除包的说明和包体

```
DROP PACKAGE 包名;
```

- 只删除包体

```
DROP PACKAGE BODY 包名;
```

### 重载

- 需要子程序的形参在个数/顺序/数据类型不同
   - 同一个类型系列的数据类型视为相同数据类型（子类型） 
   - 与返回值类型无关
- 不能重载独立子程序

```
create or replace PACKAGE dept_pkg is
 procedure add_dept
   (
    p_dept_id IN copy_dept.department_id%TYPE
   ,p_dept_name IN copy_dept.department_name%TYPE
   ,p_loc_id IN copy_dept.location_id%TYPE
   );
--重载过程add_dept
 procedure add_dept
   (
    p_dept_name IN copy_dept.department_name%TYPE
   ,p_loc_id IN copy_dept.location_id%TYPE
   );
end dept_pkg;
```

```
create or replace PACKAGE BODY dept_pkg is
 procedure add_dept
   (
    p_dept_id IN copy_dept.department_id%TYPE
   ,p_dept_name IN copy_dept.department_name%TYPE
   ,p_loc_id IN copy_dept.location_id%TYPE
   )
   is
   begin
     INSERT into copy_dept(department_id,department_name,location_id)
     VALUES(p_dept_id,p_dept_name,p_loc_id);
   end add_dept;
   
 procedure add_dept
   (
    p_dept_name IN copy_dept.department_name%TYPE
   ,p_loc_id IN copy_dept.location_id%TYPE
   )
   is
   begin
     INSERT into copy_dept(department_name,location_id)
     VALUES(p_dept_name,p_loc_id);
   end add_dept;
end dept_pkg;
```

### STandARD软件包与子程序重载

- 定义了PL/SQL环境和PL/SQL程序可以自动获取的全局数据类型，异常和子程序的声明，在Oracle系统安装时自动安装。

- 查看：DESC STandARD

- 如果在一个PL/SQL程序中重新声明了一个与内置函数同名的函数(如：`TO_char()`)；则在调用内置函数时需要`STandARD.TO_char()`
- 如果重新声明的与内置函数同名的是一个独立子程序，则在访问该子程序时需要`用户名.TO_char()`


### 前向声明

- 在软件包体的开始部分声明一个子程序的头（说明），并以分号`；`结束。

**使用情况**

1. 按照逻辑或字母顺序调用子程序
2. 定义相互递归的子程序。
3. 在一个软件包体中将子程序按分组或逻辑的顺序存放

**注意**

1. 子程序的所有形参必须出现在前向声明中和子程序的体中
2. 在前向声明之后子程序体可以出现在任何地方，但是前向声明和子程序体必须出现在同一个程序单元中。
    - 通常子程序说明放在软件包的说明部分，公共子程序声明不需要前向声明。

**前向引用的问题**

- PL/SQL语言不允许前向引用的，在使用一个标识符之前必须先声明它。即先声明后引用的原则。
- 使用前向声明的方法解决。 

#### 例

```
create or replace PACKAGE sal_pkg is
 procedure reset_sal(p_sal NUMBER,p_grade NUMBER,p_id NUMBER);
end;
```

```
create or replace PACKAGE BODY sal_pkg is
--向前声明
  --检查工资是否符合要求
  FUNCTIon check_sal(p_sal NUMBER,p_grade NUMBER) RETURN BOOLEAN;
  --根据员工号码更新工资
  procedure update_sal_emp(p_sal NUMBER,p_id NUMBER);
  
  procedure reset_sal
    (
     p_sal NUMBER
    ,p_grade NUMBER
    ,p_id NUMBER
    )
    is
     sal_error exception;
    begin
      IF check_sal(p_sal,p_grade) then
        update_sal_emp(p_sal,p_id);
        dbms_output.put_line('更新成功');
      ELSE 
        RAisE sal_error;
      end IF;
    exception
      when sal_error then
        dbms_output.put_line('更新失败');
        rollback;
      when OTHERS then
        dbms_output.put_line('其他错误');
        rollback;
    end reset_sal;
    
  FUNCTIon check_sal
    (
     p_sal NUMBER
    ,p_grade NUMBER
    )
    RETURN BOOLEAN
    is
     v_min_sal salgrade.losal%TYPE;
     v_max_sal salgrade.hisal%TYPE;
    begin
      select losal
            ,hisal
      into v_min_sal
          ,v_max_sal
      from salgrade
      where grade = grade;
      
      RETURN (p_sal between v_min_sal and v_max_sal);
    end check_sal;
    
   procedure update_sal_emp
     (
      p_sal NUMBER
     ,p_id NUMBER
     )
     is
     begin
       UPDATE copy_emp
       set salary = p_sal
       where employee_id = p_id;
     end update_sal_emp;
end sal_pkg;
```
### 软件包的初始化

- 当一个软件包中的某个组件被第一次引用时，整个软件包都被装入内存中，为整个用户会话所使用。
- 如果没有显式的初始化，则变量的初始值默认为空值null

**使用如下方法初始化软件包的变量**

1. 在软件包的说明中的变量声明中使用赋值操作
2. 对于复杂的初始化操作：一般在软件包体的末尾添加初始化代码块
    - 即使用一次性过程（在用户会话中第一次调用这个软件包时只执行一次的过程）
    - 如果使用了在软件包的说明中的变量声明中使用赋值操作，则会被软件包体末尾初始化代码所覆盖。

#### 例

```
create or replace PACKAGE select_emp is
  v_emp_dept copy_emp.department_id%TYPE := 1;
  v_emp_name copy_emp.last_name%TYPE;
  
  procedure emp_find(p_id NUMBER);
end;
```

```
create or replace PACKAGE BODY select_emp is
 procedure emp_find
   (
    p_id NUMBER
   )
   is
   begin
     select last_name
     into v_emp_name
     from copy_emp
     where employee_id = p_id;
   end emp_find;
   
--一次性过程：为软件包的变量赋初始值
  begin
    v_emp_name := 'KING';
    select department_id
    into v_emp_dept
    from copy_emp
    where employee_id = 117;
  --没有end；直接以软件包体的end结束
end select_emp;
```

```
execute dbms_output.put_line(select_emp.v_emp_dept);
execute dbms_output.put_line(select_emp.v_emp_name);
execute select_emp.emp_find(117);
```

### 在SQL中使用软件包中的函数

- 当执行一个调用存储函数（包括存储软件包中的函数）的SQL语句时，Oracle服务器必须指定这些存储函数的纯净级别（即这些函数有没有副作用）
- 通常这些副作用包括对数据库表中数据的修改或对软件包中公共变量（在软件包说明中声明的变量）的修改。控制副作用是非常重要的，因为这些副作用可能阻止正确的并行查询，产生顺序依赖并因此而产生不确定的结果，或需要一些越轨的操作（如在不同的用户会话中维护一个软件包的状态)。当在一个SQL查询语句或DML语句中调用一个函数时，一些限制是不允许出现在这个SQL语句中的。
- 一个函数所具有的副作用越少，则这个函数在一个查询语句中越容易优化，特别是当使用启示(Hint)PARALLEL_ENABLE或DETERMINisTIC时。如果要在SQL语句中调用一个存储函数,那么这个存储函数（以及任何它所调用的子程序）就必须遵守如下的纯净级别的规定：
1. 当在一个select语句或一个并行的DML语句中调用一个存储函数时，该函数不能更改数据库中任何表中的数据。
2. 当在一个DML语句中调用一个存储函数时，该函数不能查询也不能更改这个语句所更改的任何表
3. 当在-个select语句或一个DML语句中调用一个存储函数时，该函数不能执行SQL事务控制语句、会话控制语句和系统控制语句。
- 以上这些规则的目的就是控制函数使用的副作用。如果任何SQL语句中使用的函数体（程序代码）违反了以上的规则，该语句在执行时（在对这个语句进行词法分析时）将产生运行错误

### 软件包中变量的持续状态

公有和私有软件包变量的集合代表一个用户会话的软件包的状态，即在某一个指定的时间内存储在所有软件包变量中的值。通常，一个软件包的状态存在于用户会话的整个生命周期。

对于一个用户会话来说，当一个软件包被第一次装入内存时该软件包的变量被初始化。对于每一个会话，默认软件包变量都是唯一的并且这些变量的值一直保持到这个用户会话终止。换句话说，变量是存储在为每一个用户由数据库所分配的用户全局区(User Global Area,UGA)的内存中，当一个软件包中的子程序被调用，该子程序的逻辑变更了变量的状态时，软件包的状态就发生了变化。公共软件包的状态也可能直接由对其类型的适当操作所改变。

- PRAGMA代表这个语句是一个编译指令，由PRAGMA说明的语句是在编译期间处理的，而不是在运行时处理的。

- PRAGMA SERIALLY_RESUABLE指令
   - 这些指令并不影响一个程序的含义，它们仅仅将信息传输给编译器。如果在软件包说明中添加了PRAGMA SERIALLY_RESUABLE指令，那么Oracle数据库将软件包的变量存储在由多个用户会话共享的系统全局区(system GlobalArea,SGA)内存中。在这种情况下，软件包的状态被维护在一个子程序调用的生命周期中或对一个软件包结果的一个单一的引用生命周期中。如果想要节省内存并且如果不需要为每一个用户会话保持软件包的状态，那么SERIALLY_RESUABLE指令就非常有用。

当一个软件包需要声明大量的临时工作区，但是这些临时工作区只使用一次而且在同一个会话中后续数据库调用也不再需要它们时，PRAGMA指令非常适合。可以将一个无体软件包标记为串行可重用。但是如果一个软件包具有说明和体两部分，就必须标记说明和体两部分，不能只标记体。

串行可重用软件包的全局内存是统一存储在系统全局区(SGA)中的，而不是在每个用户所具有的用户全局区(UGA)中分配的，这样软件包的工作区就可以重用了。当对服务器的调用结束时，这些内存返回给SGA。每次软件包被重用时，它的公共变量都被初始化为它们的默认值或空值。

- 数据库触发器或在SQL语句中调用的PL/SQL子程序时不能访问串行可重用软件包的。否则产生错误

#### 例

### 软件包中游标的持续状态

- 与软件包中变量的持续状态相同，软件包中游标的持续状态时在整个会话期间跨越事物的。然而对于相同用户的不同会话（同一个用户的不同连接），它们的状态并不保持。

#### 例

```
create or replace PACKAGE emp_pkg is
 procedure open_emp;
 FUNCTIon next_employee(p_n NUMBER := 1) RETURN BOOLEAN;
 procedure close_emp;
end emp_pkg;
```

```
create or replace PACKAGE BODY emp_pkg is
 CURSOR emp_cursor is
  select employee_id
  from employees;
 
 procedure open_emp
   is
   begin
     IF NOT emp_cursor%isOPEN then
       OPEN emp_cursor;
     end IF;
   end open_emp;
   
 FUNCTIon next_employee
   (
    p_n NUMBER := 1
   )
   RETURN BOOLEAN 
   is
    v_emp_id employees.employee_id%TYPE;
   begin
     for count IN emp_cursor LOOP
       FETCH emp_cursor 
       into v_emp_id;
       
       dbms_output.put_line('employee_id:'||v_emp_id);
     end LOOP;
     
     RETURN emp_cursor%FOUND;
   end next_employee;
   
 procedure close_emp 
   is
   begin
     IF emp_cursor%isOPEN then
       CLOSE emp_cursor;
     end IF;
   end close_emp;
end emp_pkg;
```

### 在软件包中使用PL/SQL记录表（记录数组）

- 可以在PL/SQL软件包中声明自定义的数据类型，在利用自定义的数据类型声明变量

#### 例

```
create or replace PACKAGE emp_dog is
 TYPE emp_table_type is table OF employees%ROWTYPE
      INDEX by PLS_INTEGER;
 
 procedure get_emp(p_emp OUT emp_table_type);
end;
```
```
create or replace PACKAGE BODY emp_dog is
 procedure get_emp
   (
   p_emp OUT emp_table_type
   )
   is
    v_count BINARY_INTEGER := 1;
   begin
     for emp_record IN (select * from employees) LOOP
       p_emp(v_count) := emp_record;
       v_count := v_count + 1;
     end LOOP;
   end get_emp;
end;
```

### 标准化

#### 标准化异常和异常处理

- 创建一个标准的错误处理软件包，在该软件包中包括所有在应用程序中用户命名的和用户定义的异常
- 标准化异常处理既可以通过使用独立的存储子程序实现，也可以通过将一个子程序添加到定义标准异常的软件包中实现

**考虑：**

1. 在该程序中使用的每个命名的异常
2. 在这个程序中使用的无名或用户定义的异常。
3. 调用RAisE_APPLICATIon_ERROR的过程
4. 显示SQLCODE和SQLERRM的程序
5. 附加的对象（错误日志表等）

#### 标准化常量

- 创建一个独立的共享软件包，该软件包中包含了应用程序中所有的常量。
   - 只能在PL/SQL程序中使用，SQL语句不能使用
- 为提高数据库系统的效率  ： 该过程或软件包应该在数据库启动时就装入系统内存缓冲区

#### 本地子程序进行标准化

- 本地子程序：在一个程序（软件包/过程/匿名PL/SQL块）声明段的结尾定义的一个函数或过程
   - 如：在过程中定义使用另一个过程 
- 主要应用于：自上而下的设计方法。可以移除冗余的程序代码以减少程序模块的大小。
   - 如果一个 模块需要多次使用相同的一段代码，并且只有这个模块需要这些代码时。

**特点：**

1. 本地子程序只在定义他们的程序块中可以访问
2. 本地子程序作为包含他们的程序块的一部分被编译。

## 批量绑定 

**Oracle服务器处理PL/SQL块的方式：**

1. PL/SQL运行引擎：运行所有过程化的语句，但是将SQL语句传递给SQL引擎
2. SQL引擎：编译或执行SQL语句，并且在某些情况下将数据返回给PL/SQL引擎

在执行期间，每一个SQL语句都会引起这两个引擎之间的环境切换，如果处理的SQL语句的数量非常大是，系统的性能会受到很大的影响。如：在一个循环体中使用SQL语句利用一个下标为一个集合赋值。

通过使用批量绑定使环境切换的次数达到最少可以明显地提高系统的效率。批量绑定将一个调用中的整个集合一次一起绑定，对于SQL引擎只有一次环境切换，也就是说，一个批量绑定在一次环境来回切换中处理整个集合的全部值，与之相比，在一个循环中每次重复只处理一个集合的元素并造成一次环境的切换。一个SQL语句所影响的数据行越多，利用批量绑定获取的性能改善就越大。使用批量绑定可以改进如下操作性能：

1. 引用集合元素的DML语句。
2. 引用集合元素的SQL(查询)语句。
3. 引用集合并使用RETURNING into子句的游标for循环。

### forALL语句
- forALL关键字指示PL/SQL引擎在将集合发送给SQL语句之前批量绑定输入的集合。
  - forALL语句不是一个for循环（也不需要循环），而是一次性绑定所有的数据。 

```
forALL index IN 下限..上限
[SAVE exceptionS]
  SQL语句;
```

- SAVE exceptionS关键字是可选的。如果某些DML操作成功了而另外的一些失败了，那么可以利用它追踪或报告那些失败的操作。
    - 使用SAVE exceptionS关键字后造成失败的操作将被存储在一个名为%BULK_exceptionS的游标属性中，该属性是一个记录集合（数组)，而它会标出批量DML操作重复的次数和对应的错误号码。
   - 为了管理异常和让批量绑定即使出现错误也能够完成，最好在forALL语句上下限之后、DML语句之前添加上SAVE exceptionS关键字。

- 在执行期间所有抛出的异常都存储在游标属性%BULK_exceptionS中，该属性是一个记录集合。每一个记录有如下两个字段：
   1. %BULK_exceptionS(i).ERROR_INDEX：存储异常抛出期间forALL语句“重复的次数”。
   2. %BULK_exceptionS(i).ERROR_CODE：存储对应的Oracle错误代码。
- 存储在%BULK_exceptionS中的值引用的是最近执行的forALL语句，其下标是从1到%BULK exceptionS.COUNT。

```
create or replace procedure my_raise_sal
(
 p_percent NUMBER
) 
is
  TYPE idlist_type is table OF NUMBER
    INDEX by PLS_INTEGER;
    
  v_emp_id idlist_type;
begin
  v_emp_id(1) := 117;
  v_emp_id(2) := 177;
  v_emp_id(3) := 122;
  
--完成批量绑定索引表v_emp_id
  forALL i IN v_emp_id.FIRST..v_emp_id.LAST
    UPDATE copy_emp
    set salary = (1 + p_percent / 100) * salary
    where employee_id = v_emp_id(i);
end;
```

#### 游标属性%BULK_ROWCOUNT

为了方便DML操作，除了游标属性%BULK_exceptionS之外，PL/SQL还提供了另一个属性以支持批量操作(%BULK_ROWCOUNT)

- %BULK_ROWCOUNT属性是一个复合结构，它是专门为forALL语句的使用而设计的。
 - 其第n个元素存储的就是一个INSERT、UPDATE或DELETE语句的第n次执行时所处理的数据行数。
 - 如果第n次执行没有影响任何数据行，那么%BULK_ROWCOUNTO(i)就返回零。

```
declare 
  TYPE name_list_type is table OF varchar2(20)
    INDEX by BINARY_INTEGER;
  
  v_name_list name_list_type;
begin
  v_name_list(1) := '一号';
  v_name_list(2) := '二号';
  v_name_list(3) := '三号';
  
  forALL i IN v_name_list.FIRST..v_name_list.LAST
    INSERT into my_test(contain)
    VALUES(v_name_list(i));
  for i IN v_name_list.FIRST..v_name_list.LAST LOOP
    dbms_output.put_line('第' || i || '次操作的行数:'||SQL%BULK_ROWCOUNT(i));
  end LOOP;
end; 

***************
第1次操作的行数:1
第2次操作的行数:1
第3次操作的行数:1
PL/SQL procedure successfully completed
```

#### 使用RETURNING子句将DML语句的结果直接装入变量

- 在INSERT/UPDATE/DELETE语句中可以包括一个RETURNING子句，将受影响的数据行中指定列的值返回给PL/SQL变量或宿主变量。
   - 减少了select into语句的使用
   - 只需要较少的网络流量，服务器时间和服务器内存

```
create or replace procedure update_sal
(
 p_emp_id NUMBER
)
is
 v_last_name copy_emp.last_name%TYPE;
 v_dept_id copy_emp.department_id%TYPE;
 v_job_id copy_emp.job_id%TYPE;
 v_sal copy_emp.salary%TYPE;
begin
  UPDATE copy_emp
  set salary = salary * 1.15
  where employee_id = p_emp_id
  RETURNING last_name,department_id,job_id,salary
  into v_last_name,v_dept_id,v_job_id,v_sal;
  
  dbms_output.put_line('name:'||CHR(9)||v_last_name||CHR(10)||
                       'id:  '||CHR(9)||p_emp_id||CHR(10)||
                       'dept:'||CHR(9)||v_dept_id||CHR(10)||
                       'job: '||CHR(9)||v_job_id||CHR(10)||
                       'sal: '||CHR(9)||V_sal||CHR(10)
                       );
end;

********
create or replace TRIGGER update_sal_tri
BEforE UPDATE OF salary on copy_emp
for EACH ROW
begin
  dbms_output.put_line('old_sal:'||:OLD.salary||CHR(10)||
                       'new_sal:'||:NEW.salary||CHR(10));
end;

********
SQL> execute update_sal(177);
old_sal:304.18
new_sal:349.81

name:   Livingston
id:     177
dept:   80
job:    IT
sal:    349.81

PL/SQL procedure successfully completed
```

#### 带有RETURNING和BULK colLECT into 关键字的forALL语句

```
create or replace procedure bulk_raise_sal
(
 p_percent NUMBER
)
is
 TYPE emp_id_list_type is table OF NUMBER
  INDEX by PLS_INTEGER;
 TYPE sal_list_type is table OF copy_emp.salary%TYPE
  INDEX by BINARY_INTEGER;
 
 v_emp_id_list emp_id_list_type;
 v_sal_list sal_list_type;
begin
  v_emp_id_list(1) := 177;
  v_emp_id_list(2) := 122;
  
  forALL i IN v_emp_id_list.FIRST..v_emp_id_list.LAST
    UPDATE copy_emp
    set salary = (1 + p_percent / 100) * salary
    where employeE_id = v_emp_id_list(i)
    RETURNING salary 
    BULK colLECT into v_sal_list;
    
    for i IN 1..v_sal_list.COUNT LOOP
      dbms_output.put_line(v_emp_id_list(i)||': '||v_sal_list(i));
    end LOOP;
end;
```

#### 利用INDEX数组进行批量绑定

- 在使用forALL语句进行批量绑定的DML操作时，可以使用PLS_INTEGER或BINARY_INTEGER的下标集合，该集合的值（数组元素）是这个集合的下标。
- 可以在一个forALL语句中使用VALUES OF子句来处理批量的DML操作

```
CREATE table ins_emp
AS 
select *
from emp
where 1=2;
```
```
create or replace procedure insert_ins_emp
AS
 TYPE emp_tab_type is table OF emp%ROWTYPE;
 v_emp emp_tab_type;
 
 TYPE values_of_tab_type is table OF PLS_INTEGER
   INDEX by PLS_INTEGER;
 v_number values_of_tab_type;
 
begin
  select *
  BULK colLECT into v_emp
  from emp;
  
  for i IN 1..v_emp.COUNT LOOP
    v_number(i) := i;
  end LOOP;
  
  forALL i IN VALUES OF v_number
   INSERT into ins_emp
   VALUES v_emp(i);
   --VALUES(v_emp(i));
   --21/4     PL/SQL: 
   --20/4     PL/SQL: SQL Statement ignored
end;
```

##### 为什么VALUES子句带括号和不带括号: ORA-00947: 没有足够的值

```
CREATE table test_table1
(
 id NUMBER
,name varchar2(20)
);

INSERT into test_table1
VALUES(101,'一号');
INSERT into test_table1
VALUES(102,'二号');
INSERT into test_table1
VALUES(103,'三号');

CREATE table copy_test_table1
AS
select *
from test_table1
where 1=2;
```

- 在`VALUES v_emp(i)`中的是一整行数据，
- 而在`VALUES(v_emp(i))`中的只是作为一列数据，即常规的VALUES子句，
   - 应该要改为像`VALUES (v_table(i).id,v_table(i).name)`的形式

### BULK colLECT into子句

- BULK colLECT into关键字指示SQL引擎在将集合返回给PL/SQL引擎之前批量绑定输出的集合。
    - BULK colLECT （即将数据绑定为一个集合），into子句（将绑定的数据放入一个集合）  
- 可以在select into、FETCH into和RETURNING into子句中使用该语法。（这里没有INSERT into，功能类似)

```
...
BULK colLECT into 集合名1[,集合名2]
...
```

#### select语句中使用BULK colLECT into子句

- 当在PL/SQL语句中使用一个select语句时，可以加上BULK colLECT into子句。可以快速地获取一个数据行地集合而不再需要使用游标机制。

```
select 列1,列2...
BULK colLEC into 集合名（索引表等）
from 表
```

##### 例

```
create or replace procedure bulk_get_emps
(
p_dept_id NUMBER
)
is
 TYPE emp_tab_type is table OF copy_emp%ROWTYPE;
 v_emp_list emp_tab_type;

begin
  select *
  BULK colLECT into v_emp_list
  --将查询的结果放入集合中
  from copy_emp
  where department_id = p_dept_id;
  
  for i IN v_emp_list.FIRST..v_emp_list.LAST LOOP
    dbms_output.put_line('id:' ||CHR(9)||v_emp_list(i).employee_id ||CHR(10)||
                         'last:'||CHR(9)||v_emp_list(i).last_name||CHR(10)||
                         'dept:'||CHR(9)||v_emp_list(i).department_id||CHR(10)||
                         '******************************************');
  end LOOP;
end; 

******************
SQL> execute bulk_get_emps(20);
id: 201
last:   Hartstein
dept:   20
******************************************
id: 202
last:   Fay
dept:   20
******************************************
PL/SQL procedure successfully completed
```

#### 在游标的FETCH语句中使用BULK colLECT into子句

- 当在PL/SQL语句中使用游标时，可以在FETCH语句中加入BULK colLECT into子句进行批量绑定

```
create or replace procedure bulk_get_emps2
(
 p_dept_id NUMBER
)
is
 TYPE emp_tab_list_type is table OF copy_emp%ROWTYPE;
 
 v_emp_list emp_tab_list_type;
 
 CURSOR emp_cursor is
   select *
   from copy_emp
   where department_id = p_dept_id;
begin
  OPEN emp_cursor;
  FETCH emp_cursor BULK colLECT into v_emp_list;
  for i IN v_emp_list.FIRST..v_emp_list.LAST LOOP
    dbms_output.put_line('id:  ' || v_emp_list(i).employee_id);
  end LOOP;
  CLOSE emp_cursor;
end;


************

1.
create or replace procedure bulk_get_emps
(
 p_dept_id NUMBER
)
is
 CURSOR emp_cursor is
   select *
   from copy_emp
   where department_id = p_dept_id;
 
 v_emp_list copy_emp%ROWTYPE;
begin
  OPEN emp_cursor;
  FETCH emp_cursor into v_emp_list;
  WHILE emp_cursor%FOUND LOOP
   dbms_output.put_line(v_emp_list.employee_id);
   FETCH emp_cursor into v_emp_list;
  end LOOP;
  CLOSE emp_cursor;
end;

2.
create or replace procedure bulk_get_emps2
(
 p_dept_id NUMBER
)
is
 CURSOR emp_cursor is
   select *
   from copy_emp
   where department_id = p_dept_id;
begin
  for c IN emp_cursor LOOP
    dbms_output.put_line('id:  ' || c.employee_id);
  end LOOP;
end;
```

## 程序的定义者权限和调用者权限

**定义者权限模型：**

在Oracle8i之前的所有Oracle版本中，所有程序都是以创建这一程序的用户的权限执行的，这也被称为定义者权限模型，在该模型下：

- 允许具有一个程序执行权限的调用者调用这一程序，但是该调用者可以在这个程序所访问的对象上没有任何权限。（以拥有该程序的用户的权限）
- 要求一个程序的**拥有者**具有该程序所引用的对象的所有必须的权限。
    - 以上所说的程序包括过程、函数和软件包。

**调用者权限模型**

从Oracle8i开始，Oracle引入了另一个权限模型，那就是调用者权限模型。

- 一个程序是以**调用该程序的用户的权限**执行的。
- 一个用户以**调用者**权限运行一个过程时，该用户需要具有这个过程所引用的对象的相应权限。

提高Oracle系统的安全性。引入调用者权限模型的主要目的是防止那些权限较低的用户通过调用软件包的方式访问他们不应该访问的对象（如一些表中的敏感数据）或进行不应该的操作。

**举例**
- 在定义者权限模型中，如果hr用户调用scott用户拥有的PL/SQL存储过程get emp_sal，那么get_emp_sali过程是以拥有者scott的权限来运行的。
- 在调用者权限模型中，如果hr用户调用scott用户拥有的PL/SQL存储过程get emp_sal，那么get_emp_sali过程是以调用者hr的权限来运行的。

### 设置调用者权限

- PL/SQL默认是AUTHID DEFINER,即（ 定义者模型）以程序的拥有者的权限执行该子程序。出于安全的考虑，大多数Oracle数据库自带的PL/SQL软件包，如DBMS_LOB、DEMS_ROWID等都是调用者模型。
- 要将一个子程序定义成使用调用者权来执行的子程序，只需要在参数表和is(或AS)关键字之间加上`AUTHID CURRENT_USER`。可以使用如下语句将不同的PL/SQL子程序结构设置成以调用者权限执行：

```
CREATE FUNCTIon 函数名 
  RETURN 数据类型 
  AUTHID CURRENT_USER 
  is
    声明，函数体;
***********************
CREATE procedure 过程名 
  AUTHID CURRENT_USER 
  is
   声明，过程体;
***********************
CREATE PACKAGE 软件包名
  AUTHID CURRENT_USER 
  is
   声明；
***********************
CREATE TYPE 类型名 AUTHID CURRENT_USER is 对象；
```

#### 例

```
create or replace procedure get_salary
 (
  v_emp_id copy_emp.employee_id%TYPE
 )
 AUTHID CURRENT_USER
 is
  v_sal NUMBER;
  begin
    select salary
    into v_sal
    from copy_emp
    where employee_id = v_emp_id;
    
    dbms_output.put_line('id:'||v_emp_id||' , sal:'||v_sal);
  end;
```

## 自治事务

在日志表中，从Oracle8i开始，Oracle引入了自治事务(Autonomous Transactions.)，自治事务使得创建独立的事务成为可能。一个自治事务(AT)是一个由另外一个主事务（MT:Main Transaction）开启的独立事务，自治事务是使用`PRAGMA AUTonOMOUS_TRANSACTIon`关键字定义的

**语法**

- 关键字`PRAGMA AUTonOMOUS_TRANSACTIon`指示PL/SQL编译器将一个例行程序标记为自治的（独立的）。例行程序包括（最外层的匿名PL/SQL程序块，本地的/独立的/软件包的函数和过程），一个SQL对象类型的方法，数据库触发器。
- 关键字`PRAGMA AUTonOMOUS_TRANSACTIon`可以在一个例行程序的声明段中的任意位置。一般放在声明段的最上方。

**例**

```
create or replace procedure sal_between
 (
  v_min_sal NUMBER
 ,v_max_sal NUMBER
 )
 is
  PRAGMA AUTonOMOUS_TRANSACTIon;
--自治事物
  CURSOR emp_id_cursor is
   select employee_id
   from copy_emp
   where salary between v_min_sal and v_max_sal;

  begin
    for c IN emp_id_cursor LOOP
      dbms_output.put_line(c.employee_id);
    end LOOP;
    commit;
  end;

***********
create or replace procedure test_pro
is
 begin
  sal_between(1000,2000);
  rollback
 end;
```

**具体操作顺序**

- 主事务与自治事务之间的关系，具体操作顺序如下：
   - 如：在过程(MT)中调用（只是调用，而不是声明定义）另一个过程(AT)
1. 主事务开始。
2. 主事务调用存储过程以启动自治事务。
3. 主事务处于挂起状态。
4. 自治事务操作开始。
5. 自治事务以一个commit或rollback语句结束。
6. 主事务操作被恢复。
7. 主事务结束。

**自治事务的特性和限制如下：**

1. 虽然是在一个事务中调用，但是自治事务是独立（不依赖）于主事务的，即主事务与自治事务不是嵌套事务（没有嵌套关系）。
2. 如果主事务回滚，那么自治事务并不回滚。
3. 当一个自治事务提交时，其他事务可以看见该自治事务所做的改变。
4. 主事务与自治事务的操作方式类似堆栈
    - 在任何给定的时间只有“最上层”的事务可以访问。当自治事务结束时，该自治事务被弹出，并且调用事务（主事务）被恢复。
5. 自治事务可以被递归地调用，而且除了受限于资源之外，对于递归调用的层数没有任何限制。
6. 不能使用关键字PRAGMA将一个软件包中的子程序标记为自治的，只能将独立的子程序标记为自治的
7. 不能将一个嵌套的PL/SQL程序块会匿名的PL/SQL程序块标记为自治的。

**自治事务的使用好处：**

- 当一个自治事务开始之后是完全独立的，该自治事务不依赖于主事务的提交，也不与主事务共享锁或其他资源。
- 更重要的是自治事务可以帮助创建模块化和能够重用的软件组件。
     - 例如，一些存储过程可以开始和完成它们自己的自治事务。一个调用应用程序不需要知道有一个过程的自治操作，并且这个过程也不需要知道该应用程序的事务操作情况。这使得自治事务比普通的事务产生错误的几率更低，而且更容易使用。

## 导出程序的源代码、源代码加密

- `set long 长度` 设置输出显示的行数

### 导出
#### 使用USER_SOURCE数据字典导出

- ALL_SOURCE 数据字典
- DBA_SOURCE 数据字典

**XXX_SOURCE数据字典中没有存放触发器 的源代码**

``` 
select line
      ,text
from USER_SOURCE
where LOWER(name) = '存储过程名|存储函数名|软件包名|
```

#### 导出触发器的类型/触发事件/描述/代码

**USER_OBJECTS数据字典**

- 对象数据字典

```
select object_name
      ,object_type
      ,created
      ,status
      ,last_ddl_time  --最后使用时间    
from USER_OBJECTS
where object_type = 'TRIGGER';
```

**USER_TRIGGERS数据字典**

```
select trigger_name
      ,table_name
      ,triggering_event
      ,status
from USER_TRIGGERS;
```

**触发器的描述**

```
select description
from USER_TRIGGERS
where LOWER(trigger_name) = '触发器名';
```

**获取触发器的源代码**

```
select trigger_body
from USER_TRIGGERS
where LOWER(trigger_name) = '触发器名';
```

### PL/SQL源代码加密及动态加密

Oracle使用了一种叫做模糊(Obfuscation)或封装(wrapping)的技术来加密PL/SQL程序代码。所谓一个PL/SQL程序单元的模糊处理就是隐藏PL/SQL源代码的处理（即将PL/SQL源代码转换成人们无法阅读的“乱码”)。
- 在Oracle中既可以使用软件包DBMS_DDL的子程序加密（封装)PL/SQL源代码，
    - 通常使用 **软件包DBMS_DDL的子程序加密（封装）** 一个单独的PL/SQL程序单元，
       - 如一个单一的动态产生的CREATE procedure命令， 
- 也可以使用**封装实用程序**(wrap utility)加密PL/SQL源代码。
    - 而封装实用程序(wrap utility)是以命令行的方式

首先它可以防止其他人真正看到源程序代码，任何人也无法通过数据字典USER_SOURCE、ALL_SOURCE或DBA_SOURCE看到源代码。其次，`SQL*PIus`可以处理模糊的（加密的）源文件，并且导入(Import)和导出(Export)实用程序也接受封装的（加密的）文件。

**对比DBMS_DDL和WRAP**

- WRAP实用程序处理一个输入的SQL文件并只将该文件中的PL/SQL程序单元加密（模糊），这些PL/SQL程序单元包括：
   - 软件包说明和体
   - 过程和函数
   - 类型说明和体

- WRAP实用程序不能加密（模糊）文件中的如下内容：

   - 匿名PL/SQL程序块
   - 触发器
   - 非PL/SQL代码

- 软件包DBMS DDL通常用来加密（模糊）在另一个程序单元中动态产生的程序单元，使用DBMS_DDL软件包无法一次加密（模糊）多个程序单元，
   - 因为DBMS_DDL软件包中的子程序每次执行时只接受一个create or replace语句。

|          功能           | DBMS_DDL | WARP |
| :--------------------: | :------: | :--: |
|     代码加密（模糊）    |    t     |   t  |
|     动态加密（模糊）     |    t     |  f   |
| 一次加密（模糊）多个程序 |    f     |  t   |

#### DBMS_DDL软件包

**动态加密**

动态加密就是在创建一个PL/SQL程序单元（如过程、函数和软件包等)的同时对这个程序单元的源代码进行加密。Oracle的动态加密方法是从Oracle10g开始引入的，它是通过调用软件包DBMS_DDL中的两个子程序实现的，这两个子程序分别是CREATE_WRAPPED过程和WRAP函数。

- **CREATE_WRAPPED过程**的功能为：将一个单独的create or replace语句作为输入（这个语句可以是如下的创建语句之一：创建一个PL/SQL软件包说明、一个软件包体、函数、过程、类型说明或类型体)，随后产生一个新的create or replace语句，但是PL/SQL源代码正文已经被加密（模糊）并执行这个新产生的语句。
- **WRAP函数**的功能为：将一个单独的create or replace语句作为输入（这个语句可以是如下的创建语句之一：创建一个PL/SQL软件包说明、一个软件包体、函数、过程、类型说明或类型体)并返回一个新的create or replace语句，在这个语句中PL/SQL程序单元的正文已经被加密（模糊）。

**软件包DBMS DDL及其CREATE WRAPPED过程和WRAP函数之间的关系，以及这两个子程序之间的共同和不同之处**

PL/SQL

- DBMS_DDL软件包 包含：
  - CREATE_WRAPPED过程 
     - 封装（加密）正文并创建PL/SQL程序单元  
  - DBMS_DDL.WRAP函数 
     - 与CREATE_WRAPPED过程功能相同 
     - 但允许比CREATE_WRAPPED过程更大的输入 
     

**语法**

```
begin 
  DBMS_DDL.CREATE_WRAPPED('加密的代码');
  --加密的代码可以先放在一个字符串常量里面；
end;
```

##### 例1 CREATE_WRAPPED过程加密PL/SQL源代码

```
begin
 DBMS_DDL.CREATE_WRAPPED ('
   create or replace procedure show_time is
   begin
     dbms_output.put_line(''当前时间：''||sysdate);
   end;
 ');
end;
```

```
declare
  c_code constant varchar2(10000) := '
   create or replace procedure show_time is
    begin
      dbms_output.put_line(''当前时间：''||sysdate);
    end;
  ';
begin
  DBMS_DDL.CREATE_WRAPPED(c_code);
end;
```

#### WRAP PL/SQL封装实用程序

除了使用软件包DBMS DDL的子程序动态地加密一个单独的PL/SQL程序单元之外，还可以使用PL/SQL的封装实用程序(Wrap Utility)以命令行的方式运行加密一个SQL脚本文件。

PL/SQL的封装实用程序是一个独立的实用程序，它将PL/SQL的源代码转换成可移植的目标代码(portable object code)。利用这一实用程序，能够以一种不暴露源程序代码的方式交付PL/SQL应用程序（因为这个应用程序中可能包含专利的算法和专利的数据结构）。

该封装实用程序的功能就是将可以阅读的源代码转换成无法阅读的代码。提供这种隐藏应用程序内部（逻辑）结构的方法，可以防止应用程序被滥用。

**封装（加密）后的程序代码（如PL/SQL存储程序)具有以下一些特殊性质：**

- 独立于任何IT平台，因此一个编译的程序单元只需发布一个版本。
- 允许动态装入，因此用户在添加一个新特性时不需要关闭和重新启动系统。
- 允许动态绑定，因此外部引用的解析是在装入时进行的。
- 提供了严格的依赖检查，因此无效的程序单元在调用时被自动地重新编译。
- 支持正常的导入和导出操作，因此导入/导出(import/export)实用程序可以处理封装（加密)的文件。

封装实用程序是一个操作系统的可执行文件，它的名字为**WRAP**。要使用封装实用程序加密一个文件，需要在**操作系统提示符(cmd)**(而不是`SQL*PLUS`)下输入以下命令：

```
WRAP INAME=输入文件名 [onAME=输出文件名]
--不能有空格
--别加分号；不然会认为文件名里面也有分号
```

- 输入文件可以包括任何SQL语句的组合，然而PL/SQL封装程序只封装（加密）如下的CREATE语句：
1. CREATE [OR REPLACE] TYPE
2. CREATE [OR REPLACE] TYPE BODY
3. CREATE [OR REPLACE] PACKAGE
4. CREATE [OR REPLACE] PACKAGE BODY
5. CREATE [OR REPLACE] FUNCTIon
6. CREATE [OR REPLACE] procedure
- 除了以上列出的CREATE语句之外，所有其他的SQL CREATE语句都被原样存入输出文件（即没有加密)。

**在使用以上命令加密一个操作系统文件时，Oracle系统有如下约定：**

1. 只有INAME参数是必需的。如果没有说明onAME参数，那么输出文件的名字与输入文件相同，但是其文件的扩展名为plb。
2. 输入文件的扩展名可以是任意的扩展名，但是默认扩展名为.$sql。
3. NAME和onAME参数的值（即输入文件名和输出文件名）是否区分大小写取决于使用的操作系统。
4. 通常输出文件要比输入文件大许多。
5. 在NAME和onAME之间的等号两边不能有任何空格。

- 当封装（加密）的文件创建成功之后，要在`SQL*Pus(或isQL*PIus)`中执行这个加密后的.plb文件以编译加密后的源代码并将其存储在数据库中，其执行方法与执行SQL脚本文件一模一样。

- 当一个文件被封装（加密）之后，其中的对象类型、子程序具有如下形式：头，紧跟一个单词，wrapped,随后是加密的程序体。

##### 例

```
g:\sqlTest>WRAP INAME=wraptest.sql onAME=testout.pld;

PL/SQL Wrapper: Release 11.2.0.1.0- 64bit Production on 星期六 10月 15 19:04:52 2022
Copyright (c) 1993, 2009, Oracle.  All rights reserved.
Processing wraptest.sql to testout.pld;

g:\sqlTest>type testout.pld
create or replace procedure show_name wrapped
a000000
354
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
7
5f 9e
vyFRGoGq/9HR5cTR1Eu3tRhA6D0wg5nnm7+fMr2ywFwW/4WW0HIMRy5DJY8J523+x9IyXOfH
dMAzuHRlJXxlUF0lXZt0iwm4wDL+0oYJpuEfSZqPMLVQyKlQLwDKSv4I0sc9qRF3oisJuIHH
LcmmpvpLBBA=

g:\sqlTest>notepad testout.pld
--记事本打开也是加密

SQL> @G:\sqlTest\testout.pld

过程已创建。
```

### 加密的原则

- 在加密（封装）一个软件包时，只能加密这个软件包体，不能加密这个软件包说明，因为其他程序员在使用该软件包时需要知道公有变量和子程序等信息。
   - 以这样的方式加密软件包，其他程序员或用户可以使用这个软件包，但是他们无法了解软件包实现的细节（程序的逻辑流程)。
- 加密（封装）程序（包括软件包DBMS_DDL中的加密子程序和Wrap实用程序）只能探测到语法错误，不能探测到语义错误，
   - 因为加密（封装）程序无法解析外部引用。
   - 然而，PL/SQL编译器会解析外部引用。因此，语义错误是指加密输出文件(plb文件)被编译时报告的。
- 由于加密后的输出文件无法编辑，所以必须保留并维护原始的PL/SOL程序源代码。如果需要（如一个引用的对象发生了变化），将修改源代码并重新加密修改后的源代码。
- 确保加密源代码的所有重要部分，并在发布应用程序之前使用正文编辑器浏览加密后的文件以确认没有遗漏。

## 外部语言例程

### Java

- javasyspriv权限和javauserpriv权限。
- Oracle调用Java类的必须是静态公有方法。
- 将Java方法的返回值作为查询的数据。

#### Oracle内部创建Java语句

1. 在SQLPlus中创建Oracle中的Java类与方法

```sql
create or replace and compile java source named 函数名 as
public class 类名{
    public static 返回值 方法名(参数列表){}
}
```

```sql
create or replace and compile java source named hello as
public class Hello{
       public static String say(String name){
              return "Hello " + name;
       }
}
```

2. 创建函数调用载入Oracle中的Java方法

```sql
create or replace function hello (name varchar2)
return varchar2
as 
language java name 'Hello.say(java.lang.String) return java.lang.String';
```

3. 通过创建的函数来调用Java程序

```sql
select hello(user) 
from dual;
```

#### 导入外部Java类

1. 创建要调用的Java代码，并编译成 **Class文件** ，需要先javac
2. loadjava命令在控制台上将class文件加载到Oracle环境中

```shell
loadjava -u sys/sys@orcl -oci8 -verbose -grant scott -synonym -resolve -schema scott class文件路径
```

- 卸载已经加载的Java代码

```shell
dropjava -user sys/sys@orcl 类名
```

3. 通过创建的函数来调用Java程序

# Oracle