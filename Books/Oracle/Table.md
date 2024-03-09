# table 表（关系）

- table二维表，row行（记录）， column列（域、字段）。  每一列是相同的数据类型、列名唯一的；每一行的数据是唯一的。  

# 表结构操作

## desc 查看表结构

```sql
desc tablename;
```

## create table 创建表

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

### as 复制表 

```sql
create table emp
as
select *
from employees
where 1 = 2;  -- 只复制结构，不复制内容
-- where 1 = 1;  -- 既复制结构也 复制内容
```

## alter table 修改表结构

### add 添加字段

```sql
alter table emp
add job varchar2(30);
```

### modify 更改字段数据类型   

- modify：更改字段数据类型、为新追加的列定义默认值default。

```sql
alter table emp
modify id number(10) [default 100];
```

### drop column 删除字段 

```sql
alter table emp
drop column job;
```

### rename column 修改字段名 

```sql
alter table emp
rename column id to emp_id;
```

## rename table 修改表名 

```sql
rename table emp to emp1;
```

## drop 删除表

```sql
-- 删除表,数据和结构都删掉
drop table emp;

-- 删除表的同时，删除约束
drop table emp cascade constraints;
```

### recyclebin 回收站

- drop并未真正删除表，而是把表重命名之后放在回收站 recyclebin。

```sql
-- 删除表且清空回收站中的该表
drop table emp purge;
```

#### show 查看回收站

```sql
-- 查看回收站中表重命名的信息
show recyclebin
```

#### purge 清空回收站

```sql
-- 清空回收站中指定recyclebin_name的表
purge table "recyclebin_name";
```

```sql
-- 清空回收站
purge recyclebin;
```

#### flashback 闪回

```sql
-- 闪回
flashback table emp to before drop;

-- 闪回并重命名
flashback table emp to before drop
rename to employees;
```

# constraint 约束

## constraint 指定约束

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

## alter table 修改表约束

1. 约束可以添加/删除，但不可以修改。
2. 有效、无效化约束。
3. 添加not null约束要用modify关键字。

### drop constraint 删除

```sql
alter table 表
drop constraint 约束名;
```

```sql
-- cascade 删除主键约束的同时，（如果存在）以该主键（作为关联表的外键）建立的外键约束也一起删除。
alter table 表
drop primary key 约束名 [cascade];   
```

### modify 修改 

```sql
-- 修改不得破坏表中原有的数据。
alter table 表
modify (列名 数据类型 约束);
```

### rename constraint 改名

```sql
alter table 表
rename constraint 旧的约束名 to 新的约束名;
```

### add constraint 添加表约束

```sql
alter table 表
add constraint 约束;
```

```sql;
alter table t1
add constraint table_fk forreign KEY (no2) references t2(no2);
```

### disable/enable constraint 无效化/激活约束

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

# 表的类型

## 只读表 reade only

```sql
-- 只读
alter table tb_name reade only;

-- 可读写
alter table tb_name read write;
```

- 不允许在只读表上进行的操作：DML、truncate、锁住指定的数据 `select ...for update`、修改表的属性  `alter table add|modify`、把列置于不可用的状态 `alter table set column unused`、分区操作 `alter table drop|truncate|EXCHANGE [SUB]PARTITIon`、`alter table upgrade including data`、`alter type cascade including`、在线重定义、闪回表 `flashback table`。

## 临时表 global temporary table 

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

### 全局临时表

- 全局临时表以常规表的形式创建临时数据表的表结构（可以有 lob 列和主外键），但要在每一个表的主键中加入一个SessionID 列以区分不同的会话。
- 用户注销触发器在用户结束会话的时候删除本次会话所插入的所有记录 （SessionID 等于本次会话 ID 的记录 ）。程序写入数据时，要顺便将当前的会话ID写入表中；程序读取数据时，只读取与当前会话ID相同的记录即可。

### with 临时查询表

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

## 簇表 cluster

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

## 外部表

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
