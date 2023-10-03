# 概述

## 引号

| 引号   | 使用位置                                                     |
| ------ | ------------------------------------------------------------ |
| 单引号 | 字符型：'hello' <br />日期型：'17-12月-80' <br />to_char/to_date(日期,'YYYY-MM-DD HH24:MI:SS') |
| 双引号 | 列别名：select ename "姓 名" from emp;<br />to_char/to_date(日期,'YYYY"年"MM"月"DD"日" HH24:MI:SS') |
| 逃逸符 | 字符型、日期型等内部使用单引号时：使用两个连续的单引号''来表示一个单引号。<br />第一个单引号为逃逸符，用来消除第二个单引号的特殊含义。 |

## DML、DDL、DCL

| 术语                                          | 说明                                                         |
| --------------------------------------------- | ------------------------------------------------------------ |
| DML（data manipulation language）数据操纵语言 | 对数据库的数据进行一些操作<br />DML会对每个语句的每条记录都做日志记录以便于回滚 |
| DDL（data definition language）数据库定义语言 | 定义或改变表的结构，数据类型，表之间的链接和约束等初始化工作 |
| DCL （data control language) 数据库操纵语言   |                                                              |

| DML    | 说明 |
| ------ | ---- |
| INSERT | 插入 |
| UPDATE | 更新 |
| DELETE | 删除 |
| SELECT | 查询 |

| DDL    | 说明 |
| ------ | ---- |
| CREATE | 创建 |
| ALTER  | 修改 |
| DROP   | 删除 |

| DCL       | 说明         |
| --------- | ------------ |
| GRANT     | 授予访问权限 |
| REVOKE    | 撤销访问权限 |
| COMMIT    | 提交事务     |
| ROLLBACK  | 回滚         |
| SAVEPOINT | 保存点       |
| LOCK      | 锁定         |

## 事务

- 事务会将所有在事务中被修改的数据行加上锁（行级锁），来阻止其它人（会话）同时对这些数据的修改操作。当事务被提交或回滚后，这些数据才会被释放锁。

- 事务由以下部分组成：

  - 一个或多个DML语句。

  - 一个DDL语句。

  - 一个DCL语句。


- DML事务（一般事务）由以下情况之一结束:

  - COMMIT、ROLLBACK。

  - DDL(自动提交)。

  - 用户会话正常结束。

  - 系统异常终止。


- COMMIT、ROLLBACK作用：

1. 数据完整性。
2. 数据改变被提交之前预览。
3. 将逻辑上的操作分组。

### 事务进程（数据状态）

#### 提交/回滚前

  1. 改变前的数据状态是可以改变的。
  2. 执行DML操作的用户可以通过SELECT语句查询之前的修正。
  3. 其他用户不能看到当前用户所做的改变，直到当前用户结束事务。
  4. DML语句所涉及的行被锁定，其他用户不能操作。

> 当A操作一条数据N1后，暂未提交事务 ，此时B又上来操作同一条数据N1，这时的情况是：
>
> - 所有除A以外的人看不到被A所修改后的数据
>   - 当A提交事务后，所有人可以看到被A修改后的数据，看不到B修改后的数据。
>   - 但B能看到自己修改后的数据（与A一样，因为B还未提交事务）。当B提交事务后，所有人可以看到被B修改后的数据。
> - B会处于等待状态，直到A提交或回滚了针对这条数据的修改（行级锁）

#### 提交后

  1. 数据的改变保存到数据库中。
  2. 改变前的数据已经丢失。
  3. 所有用户可以看到结果。
  4. 所有保存点被释放。

#### 回滚后

1. 数据改变被取消。
2. 修改前的数据状态被恢复。
3. 锁被释放。

### 提交

#### 显式提交 commit

#### 隐式提交

> 触发隐式提交的SQL：ALTER，AUDIT，COMMENT，CONNECT，CREATE，DISCONNECT，DROP，EXIT，GRANT，NOAUDIT，QUIT，REVOKE，RENAME。

- 隐式提交(自动提交) ：无需显示执行commit语句，session中的操作被自动提交到数据库的过程。
  - 此隐式提交是在自己的session，如果在其他人的session中正在修改相同的数据，则引起隐式提交的语句则必须等待。

| 隐式提交的方式    | 说明                                  |
| ----------------- | ------------------------------------- |
| 正常执行完DDL语句 | CREATE、ALTER、DROP、TRUNCATE、RENAME |
| 正常执行完DCL语句 | GRANT、REVOKE                         |
| 正常退出sql*plus  | 没有明确发出COMMIT/ROLLBACK           |

- 执行DDL语句时（即使DDL语句执行失败），其之前的DML操作也会被提交到数据库中：为了避免隐式提交或者回滚，尽量保证一条或者几条DML操作完成后有显示的提交/回滚，防止后续执行的DCL或者DDL自动提交前期的DML操作。

- 事务读一致性：commit之前的数据，其他用户不可见。执行DDL语句时，Oracle需要在它的系统表中进行元数据的记录操作，如果不隐式提交就无法保证一致性。

#### 自动提交 autocommit

- 自动提交：autocommit设置为on，则DML语句执行后，系统将自动进行提交。

```sql
SET AUTOCOMMIT ON；
```

```sql
--查看当前是否是自动提交：
SHOW AUTOCOMMIT；
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

## 系统权限SYSDBA,SYSOPER

- SYSDBA > SYSOPER（超级权限）：同属于系统权限（System Privileges），Oracle允许拥有该权限的用户在数据库未打开的情况下访问实例、执行重大的数据库操作（数据库启动、关闭等）。

| sysdba可执行任务          | 说明                                           |
| ------------------------- | ---------------------------------------------- |
| STARTUP、SHUTDOWN         | 启动、关闭数据库                               |
| ALTER DATABASE            | 修改数据库<br />打开、装载、备份、改变字符集等 |
| CREATE DATABASE           | 创建数据库                                     |
| DROP DATABASE             | 删除数据库                                     |
| CREATE SPFILE             | 创建服务器参数文件                             |
| ALTER DATABASE ARCHIVELOG | 改变归档模式                                   |
| ALTER DATABASE RECOVER    | 执行数据库恢复                                 |

## 数据库管理

### 以SYSDBA登录数据库

```shell
sqlplus sys/change_on_install[@orcl] as sysdba
```


## 数据库安全性

- 用户
- 角色
- 概要文件 密码出错几次后锁定，时间、与CPU访问交互信息

**数据库安全性、系统安全性、数据安全性**

- 系统权限 对数据库的权限
- 对象权限 操作数据库对象的权限
  - 系统权限 超过100多种
  - DBA数据管理员 高权限

### 用户

#### 创建用户 

- 需要DBA权限来创建用户。

**注意项**

- 一般信息 表空间 分配用户创建表空间等数据库对象存储再硬盘中分配空间
- 系统 系统权限
- 角色 系统/对象权限赋予角色，角色赋予用户
- 限额 表空间

```sql
CREATE USER user_name
IDENTIFIED BY password;
```

#### 用户解锁

```sql
ALTER USER scott  
IDENTIFIED BY tiger 
ACCOUNT UNLOCK;
```

#### 用户信息

##### 查看当前用户名

```sql
SHOW USER
```

##### 查看所有用户名 ALL_USERS

```sql
SELECT *
FROM ALL_USERS
```

#### 用户密码

- 可用户自己修改

```sql
ALTER USER user_name
INDETIFIED BY password;
```

#### 切换用户

```sql
CONNECT scott/tiger@orcl
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

### GRANT 赋予权限

```sql
GRANT 权限列表
[ON [模式.]关系/视图]
TO 用户/角色列表;
```

#### WITH GRANT OPTION 获得分配权限的能力

```sql
GRANT 权限列表
[ON [模式.]关系/视图]
TO 用户/角色列表;
WITH GRANT OPTION;
```

#### GRANT BY current_role

- 通过角色授权，避免级联收权。

```sql
--先设置与当前会话关联的角色（默认空）必须是该授权用户拥有的角色
set role 角色
--通过与当前会话关联的角色授权
GRANT 权限列表
[ON [模式.]关系/视图]
TO 用户/角色列表
WITH GRANT OPTION
BY CURRENT_ROLE; 
```

### REVOKE 收回对象权限

```sql
REVOKE 权限列表
[ON [模式.]关系/视图]
FROM 用户/角色列表
```

#### REVOKE GRANT OPTION FOR 仅收回分配权

```sql
revoke grant option for 权限列表
[ON [模式.]关系/视图]
from 用户/角色列表
```

#### RESTRICT 阻止级联收权

- 在grant授权时，通常会产生一个**授权图**。当revoke收回权限时，默认级联收权：即A授权给B，B授权给C时，如果收回B的权限，则C的权限也被收回。
- RESTRICT 阻止级联收权；如果发生级联授权则使这次收权失败。

```sql
revoke 权限列表
[ON [模式.]关系/视图]
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
| USER_ROLE_PRIVS     | 用户拥有的角色             |
| USER_TAB_PRIVS_MADE | 用户分配的关于表对象权限   |
| USER_TAB_PRIVS_RECD | 用户拥有的关于表对象权限   |
| USER_COL_PRIVS_MADE | 用户分配的关于列的对象权限 |
| USER_COL_PRIVS_RECD | 用户拥有的关于列的对象权限 |
| USER_SYS_PRIVS      | 用户拥有的系统权限         |

# 基本查询

## SELECT  查询基本语句

```sql
SELECT column
FROM table;
```

### 查询指定值域

- 查询所有值域：使用`*`来代表所有值域。
- 指定值域的查询

```sql
--查询所有
select *
from emp;
```


```sql
--查询emp_name
select emp_name
from emp;
```

### 查询运算式

- 可以在查询中使用运算。

```sql
select salary * 1.02
from emp;
```

## FROM

- 在执行查询时，先对FROM子句进行解析，确定查询的数据源。

## AS 别名 

- 可以对关系或值域起别名。
- 别名没有命名规范，可以是特殊符号或中文。

```sql
SELECT last_name AS name
      ,employee_id emploee_id  --AS可以省略，但是最好加上
FROM employees;
```

## 连接符

### || 连接符 Oracle

- 将多个字段的输出连接为一个字段。

```sql
dbms_output.put_line('hello'||','||'world');
```

```sql
SELECT first_name || '' || last_name
FROM employees;
```

```sql
SELECT CONCAT('C','D')
FROM dual;
```

## DISTINCT 去重

```sql
SELECT DISTINCT username AS 用户名
FROM users;
```

## 运算符

### 算术运算符 +  - * /

### 比较运算符 > >= < <= = <> !=

- 都是用在WHERE条件里面的，两个数进行比较得到的结果是布尔类型的，
- 比较运算符优先级高于逻辑运算符

### 逻辑运算符 AND OR NOT

## WHERE 谓词查询 

- WHERE子句必须紧跟FROM子句，而HAVING不必

```sql
select * 
from emp
where salary > 100;
```

### IN 列表取值

- 筛选在列表内的值。

```sql
SELECT last_name
      ,employee_id
      ,salary
FROM employees
WHERE salary > 1000
 AND department_id IN (10,20,30);
```

### IS NULL

- 如果是空值则返回true.

```sql
--查询没有部门的员工
select *
from emp
where dept_name = null;
```

### LIKE 模糊查询

| 通配符 | 含义       |
| :----- | :--------- |
| _      | 一个字符   |
| %      | 任意个字符 |
| \      | 转义符     |

```sql
--查询员工姓名含有a的
SELECT last_name
      ,employee_id
FROM employees
WHERE last_name LIKE '%a%'
```

```sql
--12c以上(否则查询为空) LIKE
[charlist]
'[a-c]%' 以a-c中开头的
[! ] 不是……的
!
```

### BEWTEEN…AND… 范围查询 

- 相当于A<=且<=B

```sql
SELECT last_name
      ,employee_id
      ,salary
FROM employees
WHERE salary BETWEEN 1000 AND 7000;
```

## ORDER BY 查询结果排序 

### 基本排序

| 排序 | 说明                   |
| :--- | :--------------------- |
| ASC  | 升序 从小到大排序 默认 |
| DESC | 降序 从大到小排序      |

```sql
SELECT employee_id
      ,last_name
FROM employees
ORDER BY employee_id DESC;
```

### 多列排序

- 依次在先前列的排序结果上进行排序。

```sql
SELECT emlpoyee_id
      ,last_name
FROM employees
ORDER BY employee_id,last_name;
--先对employee_id排列，再在employee_id排列的基础上对last_name排列
```

### 别名排列

- order by子句直接使用别名进行排序。

```sql
SELECT employee_id
      ,last_name
      ,salary*1.1 sal --别名
FROM employees
ORDER BY sal;
```

## 集合运算

**对查询结果集的运算**

- 并集 UNION / UNION ALL
  - UNION 去重
  - UNION ALL 不去重
- 交集 INTERSECT
- 差集 MINUS

**注意事项**

- 在SELECT列表中的列名和表达式在数量和数据类型上相对应
- 括号可以改变相对应顺序
- ORDER BY子句
  - 只能在语句最后出现
  - 可以使用第一个查询中的列名，别名或相对位置
- 除UNION ALL以外，系统会自动将重复的记录删除
- 系统第一个查询的列名显示在输出中
- 除UNION ALL之外，系统会自动按照第一个查询中的第一个列的升序排列

```sql
SELECT employee_id,job_id
FROM employees
UNION
SELECT employee_id,job_id
FROM job_history;
```

```sql
--使用相对应位置排序举例
SELECT job_id,1
FROM employees
WHERE department_id = 10
UNION
SELECT job_Id ,2
FROM employees
WHERE department_id = 20
ORDER BY 1 DESC;
```

```sql
--查询所有员工的last_name,dpartment_id,department_name
SELECT last_name,e.department_id,department_name
FROM employees e,departments
--易错
SELECT last_name,department_id,TO_CHAR(NULL)
FROM employees
UNION
SELECT TO_CHAR(NULL),department_id,department_name
FROM departments;
```

## CASE…WHEN

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

```sql
--查询部门为10，20，30的员工信息，
--若部门号为10，则工资*1.1，若部门号为20，则工资*1.2，若部门号为30，则工资*1.3

CASE 无逗号
  SELECT
    CASE 字段
    WHEN 条件1 THEN 表达式1
    WHEN 条件2 THEN 表达式2
    ELSE 表达式n
  END
    
SELECT employee_id
      ,last_name
      ,CASE department_id 
         WHEN 10 THEN salary*1.1
         WHEN 20 THEN salary*1.2
         ELSE salary* 1.3
       END new_sal
FROM employees
WHERE department_id IN (10,20,30);
```

## NULL 空值处理

- null表示的是未知，而不是空、0、空格。
- null=null：判断的结果是false/unknow，两个null并不相等。

### IN 和 NOT IN

- 可以使用 IN(10,20,NULL)，但是**不可以使用 NOT IN(10,20,null)**
  - **在使用NOT IN的时候子查询之中必须不能包含NULL，否则不会有任何数据返回**。      
  - Oracle判断规则： 对于NOT IN如果测试值不在列表内，且列表中有一个NULL，则结果为FALSE。
- 单行子查询中返回**空值，要使用IN之类的关键字，= 的话查询结果永远为空**

```sql
--查询不是管理者的员工
SELECT *
FROM employees
WHERE employee_id NOT IN (
    SELECT manager_id
    FROM employees  --包含null值
);  
--会不返回结果，返回空值，
--因为当子查询中包含空值的时候，不能使用NOT IN，NOT IN等同于不等于所有（永远为假）

--改为：
SELECT*
FROM employees
WHERE employee_id NOT IN (
    SELECT manager_id
    FROM employees
    WHERE manager_id IS NOT NULL --去除NULL值的影响
);
```

### 组函数对null处理

- count()：将null看作0来处理。
- 其他：忽略输入集合中的null，如果作用在null上，则返回null。

# 数据类型

## 字符型

| 长度类型 | 数据类型     | 说明                                   |
| :------- | :----------- | :------------------------------------- |
| 固定     | CHAR(n)      | max2000                                |
|          | nchar(n)     | unicode格式，max1000，多数用来存储汉字 |
| 可变     | VARCHAR2(n)  | max4000                                |
|          | nvarchar2(n) | unicode格式，max2000                   |

## 数值型

| 数据类型    | 说明                                                    |
| :---------- | :------------------------------------------------------ |
| NUMBER(p,s) | p为有效数字，s为小数点后面的位数，s可正可负             |
| FLOAT(n)    | 用来存储二进制数据，二进制数据的1-126位，一般使用NUMBER |
| INT         | 整数                                                    |
| INTEGER     |                                                         |

## 日期型

| 数据类型  | 说明                                                       |
| :-------- | :--------------------------------------------------------- |
| DATE      | 范围为公元前4712年1月1日到公元9999年12月31日，可以精确到秒 |
| TIMESTAMP | 可以精确到小数秒，一般用DATE类型                           |

## 其他类型

| 数据类型 | 说明                             |
| :------- | :------------------------------- |
| LONG     | 可变长字符数据，最大2G           |
| RAW      | (LONG RAW) 原始的二进制          |
| BLOB     | 可存4G数据以二进制存放           |
| CLOB     | 可存4G数据以字符串存放           |
| BFILB    | 存储外部文件的二进制数据，最大4G |

# TABLE 表（关系）

-Oracle中的表都是二维结构。  

- 一行也可以叫做记录，一列也可以叫做域或者字段。  
- 约定：要求每一列需要有相同的数据类型。  
  - 列名要是唯一的。  
  - 每一行的数据是唯一的。  

## CREATE TABLE 创建表 

**需要：**

1. 权限
2. 存储空间
3. 命名规则:
   - 以字母开头
   - 在1-30个字符之间
   - 只能包含A ~ Z,a ~ z，0~9，_，$，#
   - 不能和其他对象重名
   - 不能是关键字

```sql
CREATE TABLE emp (
 id NUMBER(6,2) --6位有效数字，包含2位小数位即:9999.99
,salary NUMBER(10) --10位有效数字
,name VARCHAR2(25) --25长度
,hire_date DATE
);
```

## AS 复制表结构 

```sql
CREATE TABLE emp
AS
SELECT *
FROM employees
WHERE 1 = 2;  --只复制结构，不复制内容
--WHERE 1 = 1;  --既复制结构也 复制内容
```

## ALTER TABLE 修改表的结构

### ADD 添加字段(列) 

```sql
ALTER TABLE emp
ADD job VARCHAR2(30);
```

### MODIFY 更改字段数据类型   

- (可以为新追加的列定义默认值DEFAULT)

```sql
ALTER TABLE emp
MODIFY id NUMBER(10) [DEFAULT 100];
```

### DROP COLUMN 删除字段 

```sql
ALTER TABLE emp
DROP COLUMN job;
```

### RENAME COLUMN 修改字段名 

```sql
ALTER TABLE emp
RENAME COLUMN id TO emp_id;
```

## RENAEM TABLE 修改表名 

```sql
RENAME TABLE emp TO emp1;
```

## DROP 删除表

**删除表,数据和结构都删掉**

```sql
DROP TABLE emp;
```

- 并未真正删除表，而是把表放在回收站 RECYCLEBIN

**删除表的同时，删除约束**

```sql
DROP TABLE emp CASCADE CONSTRAINTS;
```

**一次性删除**

```sql
DROP TABLE emp PURGE;
```

### RECYCLEBIN 回收站

- 当删除一个表时，Oracle并没有真正删除该表，而是将该表重命名之后放在回收站

#### SHOW 查看回收站

```sql
SHOW RECYCLEBIN
```

#### PURGE 清空回收站中指定的表

```sql
PURGE TABLE "recyclebin_name"; --SHOW RECYCLEBIN; 获得RECYCLEBIN NAME
```

```sql
PURGE RECYCLEBIN;
```

#### FLASHBACK 捡回

```sql
--捡回
FLASHBACK TABLE emp TO BEFORE DROP;

--捡回并重命名
FLASH TABLE emp TO BEFORE DROP
RENAME TO employees;

DESC --查看是否捡回
```


## 操作表中的数据

```
SQL> DESC emp
Name      Type         Nullable Default Comments
--------- ------------ -------- ------- --------
ID        NUMBER(6,2)  Y
SALARY    NUMBER(10)   Y
NAME      VARCHAR2(25) Y
HIRE_DATE DATE         Y
```

### INSERT 添加数据

```sql
INSERT INTO table1
VALUES (values,...)
```

- 一次只能向表中插入一条数据

1. 为每一列添加一个新值
2. 按默认顺序列出每个列的值
3. 在INSERT子句中随意列出列名和值
4. 字符和日期型数据要在单引号''中

#### 显式插入

```sql
INSERT INTO emp(id,salary,name,hire_date)
VALUES(1,1500,'张三',TO_DATE('2002-2-3','yyyy-mm-dd')); --转换日期输入
```

#### 隐式插入

```sql
INSERT INTO emp
VALUES(2,3000,'李四',TO_DATE('2003-3-2','yyyy-mm-dd'));
```

- 显式 NULL (VALUES)

```sql
INSERT INTO emp
VALUES(5,NULL,'赵七'，NULL);
```

#### 按字段插入

- 对于没有列出的值域，则插入null。

```sql
INSERT INTO emp(name,salary,hire_date,id)
VALUES(2500,'王五',TO_DATE('2004-4-5','yyyy-mm-dd'),3);
```

#### &变量 窗口输入

- 在使用&输入时，对于日期和字符型等需要单引号的数据类型，可以在&外面加上单引号，从而不用在窗口输入时使用单引号
  - `'&name'`
- 而其他不需要单引号的数据类型则可以直接使用&
  - `&id`  

```sql
INSERT INTO emp
VALUES(&id,&name,&salary,&hire_date); --注意日期默认: 'dd-mm-yyyy'
                                      --注意字符和日期也要单引号''
--例：5 '李四' 2000 '11-2月-1999'

INSERT INTO emp
VALUES(&id,'&name',&salary,'&hire_date');
--例：5 李四 2000 11-2月-1999
```

#### SELECT 从其他表中拷贝数据

- 相应的列数据类型要一致

```sql
INSERT INTO emp
SELECT employee_id
      ,salary
      ,last_name
      ,hire_date 
FROM employees
WHERE department_id = 90;
```

#### 插入日期

```
INSERT INTO mytable
VALUES (sysdate)
;

INSERT INTO mytable
VALUES (TO_CHAR('2022-2-2','yyyy-mm-dd'))
;
```

#### 插入NULL 不等于空格

```
INSERT INTO mytable
VALUES(null," ")
;
```

### MERGE 合并数据

- 数据转储操作：从表中选择数据行以修改或插入到另一个表。
- MERGE命令是基于ON子句中的条件来决定对目标表执行的是修改还是插入操作。因此必须在目标表上有INSERT和UPDATE系统权限，并且源表上具有SELECT系统权限。

**语法：**

```sql
MERGE INTO 表 别名  --说明正在修改或插入的目标表
USING (table|view|sub_query) 别名  --标识要修改或插入的数据源（表，视图，子查询等）
ON (条件) --定义MERGE语句是进行修改还是插入操作的条件
WHEN MATCHED THEN  --当条件满足时，执行
 UPDATE SET         --执行的语句块
   col1 = col1_val,
   col2 = col2_val
WHEN NOT MATCHED THEN  --当条件不满足时，执行
 INSERT (列名)
 VALUES (值)
```

**例：**

- 需求是，从T1表更新数据到T2表中。假设T2表的NAME 在T1表中已存在，就将MONEY累加，假设不存在。将T1表的记录插入到T2表中。
- 在等价的情况下，一定需要至少两条语句，一条为UPDATE，一条为INSERT,并且语句中必需要与推断的逻辑，或者写在过程中，假设是单条语句，就要写全条件。
- 写在UPDATE和INSERT的语句中，显的比较麻烦并且容易出错。假设了解MERGE，我们能够不借助存储过程，直接用单条SQL便实现了该业务逻辑，且代码非常简洁。详细例如以下：

```sql
MERGE INTO T2
USING T1
ON (T1.NAME = T2.NAME)
WHEN MATCHED THEN
 UPDATE
 SET T2.MONEY = T1.MONEY + T2.MONEY
WHEN NOT MATCHED THEN
 INSERT
 VALUES (T1.NAME,T1.MONEY);
```


#### 灵活之处

##### 1. UPDATE和INSERT动作可仅仅出现其一(必须同一时候出现。)

```sql
--我们可选择只UPDATE目标表
MERGE INTO T2
USING T1
ON (T1.NAME = T2.NAME)
WHEN MATCHED THEN
 UPDATE
 SET T2.MONEY=T1.MONEY+T2.MONEY;

--也可选择只INSERT目标表而不做不论什么UPDATE动作

MERGE INTO T2
USING T1
ON (T1.NAME = T2.NAME)
WHEN NOT MATCHED THEN
 INSERT
 VALUES (T1.NAME,T1.MONEY);
```

##### 2. 可对MERGE语句加条件

```sql
MERGE INTO T2
USING T1
ON (T1.NAME=T2.NAME)
WHEN MATCHED THEN
 UPDATE
 SET T2.MONEY=T1.MONEY+T2.MONEY
 WHERE T1.NAME='A';
```

#####  3. 可用DELETE子句清除行

- 在使用DELETE子句时，必须与delete一起存在update set。

```sql
/*
在这样的情况下，首先是要先满足T1.NAME = T2.NAME的记录，假设T2.NAME=’A’并不满足T1.NAME=T2.NAME过滤出的记录集，
那这个DELETE是不会生效的。在满足的条件下，能够删除目标表的记录。
*/

MERGE INTO T2
USING T1
ON (T1.NAME=T2.NAME)
WHEN MATCHED THEN
 UPDATE
 SET T2.MONEY = T1.MONEY + T2.MONEY
 
 DELETE WHERE (T2.NAME = 'A');
```

##### 4. 可采用无条件方式insert

```sql
/*
在语法ON(keyword)处写上恒不等条件（如1=2）后，MATCHED语句的INSERT就变为无条件INSERT了，详细例如以下
*/

MERGE INTO T2
USING T1
ON (1=2)
WHEN NOT MATCHED THEN
 INSERT
 VALUES (T1.NAME,T1.MONEY);
```

#### 误区

##### 1. 不能更新ON子句引用的列

```sql
MERGE INTO T2
USING T1
ON (T1.NAME=T2.NAME)
WHEN MATCHED THEN
 UPDATE
 SET T2.NAME=T1.NAME;

ORA-38104: 无法更新 ON 子句中引用的列: "T2"."NAME"
```

##### 2.  DELETE子句的WHERE顺序必须最后

```sql
MERGE INTO T2
USING T1
ON (T1.NAME=T2.NAME)
WHEN MATCHED THEN
 UPDATE
 SET T2.MONEY = T1.MONEY + T2.MONEY
 
 DELETE 
 WHERE (T2.NAME = 'A')
 
 WHERE T1.NAME='A';

ORA-00933: SQL 命令未正确结束
```

##### 3. DELETE 子句仅仅能够删除目标表。而无法删除源表

```sql
/*
 这里须要引起注意，不管DELETE WHERE (T2.NAME = 'A' )这个写法的T2是否改写为T1。效果都一样，都是对目标表进行删除。
*/

SELECT * FROM T1;
NAME                      MONEY
-------------------- ----------
A                            10
B                            20

SELECT * FROM T2;
NAME                      MONEY
-------------------- ----------
A                            30
C                            20

MERGE INTO T2
USING T1
ON (T1.NAME = T2.NAME)
WHEN MATCHED THEN
   UPDATE
   SET T2.MONEY = T1.MONEY + T2.MONEY
   DELETE 
   WHERE (T2.NAME = 'A' );
  
  
SELECT * FROM T1;

NAME                      MONEY
-------------------- ----------
A                            10
B                            20


SELECT * FROM T2;

NAME                      MONEY
-------------------- ----------
C                            20
```

##### 4. 更新同一张表的数据,需操心USING的空值

```sql
SELECT * FROM T2;
NAME                      MONEY
-------------------- ----------
A                            30
C                            20

/*
需求为对T2表进行自我更新。假设在T2表中发现NAME=D的记录，就将该记录的MONEY字段更新为100，假设NAME=D的记录不存在，
则自己主动添加。NAME=D而且MONEY=100的记录。依据语法完毕例如以下代码:
*/

MERGE INTO T2
USING (select * 
       from t2 
       where NAME='D') T
ON (T.NAME=T2.NAME)
WHEN MATCHED THEN
 UPDATE
 SET T2.MONEY=100
WHEN NOT MATCHED THEN
 INSERT
 VALUES ('D',200);

--可是查询发现。本来T表应该由于NAME=D不存在而要添加记录。可是实际却根本无变化。

SQL> SELECT * FROM T2;
NAME                      MONEY
-------------------------------------------------------
A                            30
C                            20

/*
   原来是由于此时select * from t2 where NAME='D'为NULL,所以出现了无法插入的情况。
   我们能够利用COUNT(*)的值不会为空的特点来等价改造。详细例如以下:
*/

MERGE INTO T2
USING (select COUNT(*) CNT 
       from t2 
       where NAME='D') T
ON (T.CNT <> 0)
WHEN MATCHED THEN
 UPDATE
 SET T2.MONEY = 100
WHEN NOT MATCHED THEN
 INSERT
 VALUES ('D',100);

SQL> SELECT * FROM T2;
NAME                      MONEY
-------------------------------
A                            30
C                            20
D                           100
```

##### 5. 必需要在源表中获得一组稳定的行

```sql
---构造数据，请注意这里多插入一条A记录，就产生了ORA-30926错误
INSERT INTO T1 
VALUES ('A',30);

COMMIT;

---此时继续运行例如以下
MERGE INTO T2
USING T1
ON (T1.NAME=T2.NAME)
WHEN MATCHED THEN
 UPDATE
 SET T2.MONEY=T1.MONEY + T2.MONEY;

ORA-30926: 无法在源表中获得一组稳定的行

/*
oracle中的merge语句应该保证on中的条件的唯一性，T1.NAME=T2.NAME的时候。T1表记录相应到了T2表的两条记录，所以就出错了。
 
解决方法。比方我们能够对T1表和T2表的关联字段建主键，这样基本上就不可能出现这种问题，并且一般而言，MERGE语句的关联字段互相有主键。 MERGE的效率将比较高！或者是将T1表的ID列做一个聚合。这样归并成单条，也能避免此类错误。

如： 
*/ 

MERGE INTO T2 
USING (SELECT NAME,SUM(MONEY) AS MONEY 
       FROM T1 GROUP BY NAME)T1 
ON (T1.NAME = T2.NAME) 
WHEN MATCHED THEN 
 UPDATE
 SET T2.MONEY = T1.MONEY + T2.MONEY; 
 --正常情况下，一般出现反复的NAME须要引起怀疑，不太应该。
```

#### 例1

- 根据基本工资salary设置员工奖金bonus

目标表

```
CREATE TABLE t_bonus
(
 employee_id VARCHAR2(10)
,bonus NUMBER
);

INSERT INTO t_bonus
VALUES (111,121);

INSERT INTO t_bonus
VALUES (211,1221);

INSERT INTO t_bonus
VALUES (212,1300);
```

源表

```
CREATE TABLE s_employee
(
 employee_id VARCHAR2(10)
,salary NUMBER
);

INSERT INTO s_employee
VALUES (111,221);

INSERT INTO s_employee
VALUES (211,1221);

INSERT INTO s_employee
VALUES (212,1300);

INSERT INTO s_employee
VALUES (611,2222);

INSERT INTO s_employee
VALUES (613,3300);
```

MERGE

```
MERGE INTO t_bonus T
USING (SELECT employee_id
             ,salary
       FROM  s_employee
      ) S
ON (T.employee_id = S.employee_id)
WHEN MATCHED THEN 
  UPDATE SET T.bonus = S.salary + 40
  WHERE T.bonus > 1000
             
  DELETE 
  WHERE (T.bonus > 1300)
WHEN NOT MATCHED THEN 
  INSERT INTO (T.employee_id
              ,T.bonus)
  VALUES (S.employee_id
         ,S.salary +20)
  WHERE (S.salary > 3000)
```

#### 例2：

目标表

```
CREATE TABLE copy_emp
AS 
SELECT *
FROM employees
```

MERGE语句

```
BEGIN
  MERGE INTO copy_emp c
  USING employees e
  ON(c.employee_id = e.employee_id)
  WHEN MATCHED THEN
    UPDATE
    SET c.salary = c.salary + 10000;
END;
```

### UPDATE 更新数据 

```sql
UPDATE 表
SET 列=新的列
WHERE --没有WHERE ，则全部更新
```

```sql
--更新114号员工的工作和工资使其和205号员工相同
UPDATE employees
SET job_id = (
             SELECT job_id
             FROM employees
             WHERE employee_id = 205
             )
   ,salary = (
              SELECT salary
              FROM employees
              WHERE employee_id = 205
              )
WHERE employee_id = 114;
```


### DELETE 删除数据 

- 可以回滚

```sql
DELETE FROM
WHERE  --若无WHERE子句，则全部删除
```

```sql
删除emp表中id为1的员工
DELETE FROM emp
WHERE id = 1;
```

### TRUNCATE 清空数据

- 不可回滚

```sql
TRUNCATE TABLE 表;
```

### DEFAULT 设置某字段的默认值 

#### 1) 创建表时添加

```sql
CREATE TABLE emp(
 salary NUMBER(20) DEFAULT 1000
);
```

#### 2) 修改表时添加默认值 MODIFY

```sql2
ALTER TABLE emp
MODIFY salary NUMBER(10) DEFAULT 1000;
--如果在新加记录的时候不想要默认值了，
  则按正常的添加方式添加了就可以替换默认值了
```

## 查看表的属性

### DESC

```sql
DESC tablename;
```

### ALL_TAB_COLUMS 表的列属性

```sql
SELECT column_name,data_type,nullable,data_default
FROM ALL_TAB_COLUMNS
WHERE table_name = 'employees'
```

DBA_TAB_COLUMNS描述了数据库中所有表、视图和群集的列。  
USER_TAB_COLUMNS介绍了当前用户拥有的表、视图和群集的列。此视图不显示该列。OWNER

### USER_TAB_COLS 查看用户下所有表是否含日期字段

```sql
SELECT c.table_name,c.column_name
FROM USER_TAB_COLS c
WHERE c.data_type = 'DATE'
ORDER BY table_name;
```

### DBA_EXTENTS视图 与 DBA_SEGMENTS视图

```sql
SELECT segment_name,SUM(bytes)/1024/1024 ||'M'
FROM dba_extents
WHERE segment_name = ' '
GROUP BY segment_name;
```

#### DBA_EXTENDS描述的是数据库所有表空间中段的扩展信息  

使用DBA_EXTENDS必须保证相应的数据文件处于online状态，否则无法返回任何记录。  
常使用的是USER_EXTENTS视图，比DBA_EXTENDS视图少几个字段。

#### DBA_SEGMENTS 视图描述的是数据库中所有段的存储和分配信息

## 只读表

```sql
ALTER TABLE tb_ordernary5 READ ONLY;
```

**回到可读写**

```sql
ALTER TABLE tb_ordernary5 READ WRITE;
```

**不允许在只读表上进行的操作:**

- DML
- TRUNCATE
- 锁住指定的数据 SELECT ...FOR UPDATE
- 修改表的属性  ALTER TABLE ADD|MODIFY
- 把列置于不可用的状态 ALTER TABLE SET COLUMN UNUSED
- 分区操作 ALTER TABLE DROP|TRUNCATE|EXCHANGE [SUB]PARTITION
- ALTER TABLE UPGRADE INCLUDING DATA
- ALTER TYPE CASCADE INCLUDING
- 在线重定义
- 闪回表 FLASHBACK TABLE

## 临时表 GLOBLE TEMPORARY TABLE 

- 只在会话期间或在事务处理期间存在的表.
- 临时表在插入数据时，动态分配空间

```sql
create global temporary table temp_dept
(dno number,
dname varchar2(10))
on commit delete rows;
insert into temp_dept values(10,'ABC');
commit;

select * from temp_dept; --无数据显示,数据自动清除

on commit preserve rows:在会话期间表一直可以存在（保留数据）
on commit delete rows:事务结束清除数据（在事务结束时自动删除表的数据）
```

**oracle数据库的临时表的特点：**

- 临时表默认保存在TEMP中；
- 表结构一直存在，直到删除；即创建一次，永久使用；
- 不支持主外键。
- 可以索引临时表和在临时表基础上建立视图。建立在临时表上的索引也是临时的,也是只对当前会话或者事务有效. 临时表可以拥有触发器.

**事务型和会话型临时表**

**会话型：**

- 基于会话的临时表，数据从会话开始到会话结束之间是有效的，当会话结束时，表中的数据会自动清空。
- 不同会话之间的数据是隔离的，互不影响。

**事务型：**

- 基于事务的临时表，其比会话型的临时表更灵活，可以认为是从会话型临时表的优化，
- 因为表中的数据的保存时间与会话型相同，有效期从会话开始，在会话结束时，数据库自动清空临时表中的数据。
- 与会话型临时表不同的是 在事务提交或者事务回滚时将清空临时表中的数据。
- 当然，会话型临时表在会话期间可以采用 delete 临时表名; 的方式清空临时表数据。

### 创建临时表

```sql
CREATE GLOBAL TEMPORARY TABLE 临时表
(
    ……
)
ON COMMIT [PRESERVE | DELETE] ROWS;

--preserve时就是会话（SESSION）型的临时表
--delete就是事务（TRANSACTION）型的临时表 
```

```sql
declare tempisexist integer := 0;
begin
　 select count(*) 
   into tempisexist 
   from all_tables 
   where table_name='NK_SLTJ';
　 if tempisexist=0 then--不存在临时表就创建一个
　　　　execute immediate('
　　　　　　CREATE GLOBAL TEMPORARY TABLE NK_SLTJ
　　　　　　(
　　　　　　　　LCK_FJNM varchar(36),
　　　　　　　　LCMC varchar(70),
　　　　　　　　GFX integer,
　　　　　　　　ZFX integer,
　　　　　　　　DFX integer,
　　　　　　　　KZDSL integer
　　　　　　)
　　　　　　on commit preserve rows'　　--preserve表示会话级。
　　　　);
　　end if;
end;
```

### 使用临时表

```sql
　　declare
　　　　FXZ NUMBER;
　　　　FJNM varchar(36);
　　　　ZZNM varchar(36):='77c48880-a2be-4d3c-97b7-26f8de0bee63';
　　　　CURSOR NKFXZcur is 
            select NKFXJZ_FXZ
                  ,NKLCK_FJNM 
            from NKFXJZ 
            INNER JOIN NKLCK ON NKLCK_NM = NKFXJZ_LCNM 
            where NKLCK_ZZNM=ZZNM;
　　begin
　　　　delete NK_SLTJ;　--防止在统一会话中多次执行导致数据重复，因此程序一开始就应清空临时表数据　
　　　　insert into NK_SLTJ SELECT NKLCK_FJNM, NKLCK_MC,0,0,0,KZD FROM　　--向临时表插入数据。
　　　　　　( select KZDSL.NKLCK_FJNM,KZDSL.KZD,LCJZ.NKLCK_MC from
　　　　　　(select substr(NKLCK_FJNM,1,4) as NKLCK_FJNM, count(NKNKJZ_KZD) AS KZD from NKLCK LEFT join NKNKJZ ON NKNKJZ_LCNM =NKLCK_NM WHERE NKLCK_ZZNM=ZZNM group by substr(NKLCK_FJNM,1,4)) KZDSL
　　　　INNER JOIN
　　　　　　(select NKLCK_FJNM, NKLCK_MC from NKLCK WHERE NKLCK_JC=1 and NKLCK_ZZNM=ZZNM) LCJZ ON LCJZ.NKLCK_FJNM=KZDSL.NKLCK_FJNM) ccc;

　　　　open NKFXZcur; --打开游标
　　　　fetch NKFXZcur INTO FXZ,FJNM; --提取游标数据
　　　　while NKFXZcur%FOUND loop --循环
　　　　　　if FXZ>=3.5 and FXZ<=5 then --高风险
　　　　　　　　update NK_SLTJ set GFX=GFX+1 where LCK_FJNM=substr(FJNM,0,4);
　　　　　　elsif FXZ>2 and FXZ<3.5 then --中风险
　　　　　　　　update NK_SLTJ set ZFX=ZFX+1 where LCK_FJNM=substr(FJNM,0,4);
　　　　　　else
　　　　　　　　if FXZ>=0 and FXZ<=2 then --低风险
　　　　　　　　　　update NK_SLTJ set DFX=DFX+1 where LCK_FJNM=substr(FJNM,0,4);
　　　　　　　　end if;
　　　　　　end if;
　　　　　　fetch NKFXZcur INTO FXZ,FJNM ;
　　　　end loop;--结束循环
　　close NKFXZcur; --关闭游标
end;
```

### 全局临时表

- 以常规表的形式创建临时数据表的表结构，但要在每一个表的主键中加入一个**SessionID 列**以区分不同的会话。(可以有 lob 列和主外键)
- 写一个**用户注销触发器**，在用户结束会话的时候删除本次会话所插入的所有记录 (SessionID 等于本次会话 ID 的记录 ) 。
  - 程序写入数据时，要顺便将当前的会话 ID(SessionID) 写入表中。
  - 程序读取数据时，只读取与当前会话 ID 相同的记录即可。

**功能增强的扩展设计：**

- 可以在数据表上建立一个视图，视图对记录的筛选条件就是当前会话的SessionID 。
- 数据表中的SessionID 列可以通过Trigger 实现，以实现对应用层的透明性。
- 高级用户可以访问全局数据，以实现更加复杂的功能。

## 簇表 CLUSTER

```sql
CREATE CLUSTER tb_cluster(postcode int)
TABLESPACE userdb;
```

```sql
CREATE TABLE student
(
 id INT PRIMARY KEY
,name VARCHAR2(20) NOT NULL
,postcode INT
)
CLUSTER tb_cluster(postcode)
;
```

```sql
CREATE TABLE address_info
(
 postcode INT PRIMARY KEY
,name VARCHAR2(40)
,detail VARCHAR2(40)
)
CLUSTER tb_cluster(postcode)
;
```

```sql
CREATE TABLE students
(
 name VARCHAR2(40) PRIMARY KEY
,id NUMBER
,detail VARCHAR2(100)
)
ORGANIZATION INDEX
TABLESPACE userdb
PCTTHRESHOLD 30
INCLUDING detail
OVERFLOW TABLESPACE userdb
;
```

## 外部表

<https://www.cnblogs.com/xidabei/p/7453274.html>

### 1

```sql
建立文件:
‪F:\temtb\data\temstu.txt
‪‪50016,小张,上海,22
30011,小李,天津,24
40022,晶晶,广州,25
30044,小宝,六盘水,22
20055,小狐狸,彬州,26

文件夹:
F:\temtb\data
F:\temtb\log
F:\temtb\bad
```

### 2

```sql
SQL> CREATE OR REPLACE DIRECTORY dat_dir
  2  AS 'F:\temtb\data';      --外部表文件路径

目录已创建。

SQL> CREATE OR REPLACE DIRECTORY log_dir
  2  AS 'F:\temtb\log';

目录已创建。

SQL> CREATE OR REPLACE DIRECTORY bad_dir
  2  AS 'F:\temtb\bad';

目录已创建。

SQL> GRANT READ ON DIRECTORY dat_dir TO rawman;

授权成功。

SQL> GRANT READ,WRITE ON DIRECTORY log_dir TO rawman;

授权成功。

SQL> GRANT READ,WRITE ON DIRECTORY bad_dir TO rawman;

授权成功。
```

### 3

```sql
CREATE TABLE fitness_member
(
         id INTEGER
        ,name VARCHAR2(14)
        ,city VARCHAR2(30)
        ,AGE INT
)
ORGANIZATION EXTERNAL
(
        TYPE ORACLE_LOADER
        DEFAULT DIRECTORY dat_dir
        ACCESS PARAMETERS
        (
                records delimited by newline
                badfile bad_dir:'empxt%a_%p.bad'
                logfile log_file:'empxt%a_%p.log'
                fileds terminated by ','
                missing field VALUES are NULL
                (id,name,city,age)
        )
        default directory exd_dir
        LOCATION ('temstu.txt')
)
PARALLEL
REJECT LIMIT UNLIMITED;

表创建成功
```

```sql
SELECT *
FROM fitness_member;



error
ORA-29913: 执行 ODCIEXTTABLEOPEN 调出时出错
ORA-29400: 数据插件错误KUP-00554: error encountered while parsing access
parameters
KUP-01005: syntax error: found "identifier": expecting one of: "badfile,
byteordermark, characterset, column, data, delimited, discardfile,
disable_directory_link_check, fields, fixed, load, logfile, language,
nodiscardfile, nobadfile, nologfile, date_cache, preprocessor, readsize,
string, skip, territory, variable"
KUP-01008: the bad identifier was: fileds
KUP-01007: at line 4 column 17
```



# VIEW 视图

**SCOTT用户创建视图VIEW权限不足：**

- 使用DBA授予SCOTT用户创建视图VIEW权限；

```sql
GRANT CREATE VIEW TO scott; 
```

**视图：从表中抽出的逻辑上相关的数据集合**

- 视图就是一个定义好的查询表达式。

1. 视图是一种虚表
2. 视图建立在已有的基础上，即基表，当基表的数据发生变化时，视图里面的数据也会跟着发生变化
3. 向视图提供内容的语句为SELECT语句，即视图可理解为储存的SELECT语句
4. 视图向用户提供基表数据的另一种表现形式

**视图作用**

1. 控制数据访问
2. 简化查询
3. 避免重复访问相同的数据

**连接视图 指那些基于多个表建立的视图**

- 一般来说不会在该视图上执行INSERT、UPDATE、DELETE操作。

**只读视图 就是只允许进行SELECT操作的视图**

- 在该视图时指定WITH READ ONLY选项。
- 在这种视图上不能执行INSERT、UPDATE、DELETE的操作。

**CHECK约束视图 在视图上定义CHECK约束**

- 在该视图上执行INSERT或UPDATE操作时,数据必须符合查询结果。

## 创建视图  CREATE [OR REPLACE] VIEW

- CREATE VIEW子句中各列的别名应和子查询中各列相对应

### 简单视图和复杂视图

**简单视图 是单个表并且不包含函数或表达式的视图，**

- 可以在该视图上可以执行DML语句（即可执行增、删、改操作）

**复杂视图 就是指那些包含函数、表达式或者分组数据的视图，**

- 在该视图上执行DML语句时必须要符合特定条件。
- 通常我们在定义复杂视图时必须为函数或表达式定义别名。

| 特性     | 简单视图 | 复杂视图   |
| :------- | :------- | :--------- |
| 表的数量 | 一个     | 一个或多个 |
| 函数     | 没有     | 有         |
| 分组     | 没有     | 有         |
| DML      | 可以     | 不可以     |


**简单视图语法**

```sql
CREATE OR REPLACE VIEW table1_view
AS
SELECT *
FROM table1;
```

**复杂视图语法**

- 组函数一定要使用别名，否则报错

```sql
CREATE OR REPLACE VIEW table1_view
AS
SELECT AVG(salary) avg_sal,department_id --组函数一定要使用别名，否则报错
FROM employees
GROUP BY department_id;
```

### 连接视图   

```sql
--(table1,table2外键为column2)
CREATE OR REPLACE VIEW table1_view
AS
SELECT column1,t1.column2,column3
FROM table1 t1,table2 t2
WHERE t1.column2(+) = t2.column2;
```

## 查询视图 (SQL*PLUS)

```sql
SELECT *
FROM table1_view;
```

## 修改视图

- 相当于重新创建一个视图

```sql
CREATE OR REPLACE VIEW table1_view(column1,column2,……) 
AS 
SELECT column1,column2,……
FROM table1;
```

## 视图中DML操作规定

1. 可以在简单视图中执行DML操作
2. 当视图定义中包含以下元素之一时，不能使用DELETE语句
   - 组函数
   - GROUP BY子句
   - DISTINCT关键字
   - ROWNUM伪例
3. 且若还包含列的定义为表达式时，不能用UPDATE语句
   - 列的定义为表达式
4. 且若还包含以下的元素时，不能用INSERT INTO语句
   - 表中非空的列在视图定义中未包括

### WITH READ ONLY 屏蔽DML操作

- 用户是可以通过视图对基表执行增删改操作(现实基本不能)，但是有很多在基表上的限制,如:
  - 基表中某列不能为空，但是该列没有出现在视图中，则不能通过视图执行INSERT操作，
  - 基表设置了某些约束，这时候插入视图或者修改视图的值，有可能会报错

**WITH READ ONLY**

1. `WITH READ ONLY` 选读屏蔽，设置只读
2. 任何DML操作的返回ORACLE SEVER错误

```sql
CREATE OR REPLACE VIEW table1_view(no1,no2)
AS
SELECT no1,no2
FROM table1
WITH READ ONLY;
```

## 删除视图 

- 只是删除视图的定义，并不会删除基表的数据

```sql
DROP VIEW table1_view;
```

## 物化视图

- 将视图的查询结果存储在数据库中，使用该视图时，使用的是查询的结果，而不是查询表达式。
- 相当于一个表

### 创建或修改物化视图

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

**权限**

```sql
grant create materialized view to 用户
```

#### 生成数据

- **build immediate**是在 创建物化视图的时候就生成数据 。
- **build deferred** 则在创建时不生成数据，以后根据需要在生成数据 。

#### 视图刷新

##### 刷新模式

- **on demand** 默认，仅在该物化视图“需要”被刷新了，才进行刷新(REFRESH)，即更新物化视图，以保证和基表数据的一致性;
- **on commit**  提交触发，一旦基表有了commit，即事务提交，则立刻刷新，立刻更新物化视图，使得数据和基表一致。一般用这种方法在操作基表时速度会比较慢。

##### 刷新方法

- 完全刷新（**COMPLETE**）： 会删除表中所有的记录（如果是单表刷新，可能会采用TRUNCATE的方式），然后根据物化视图中查询语句的定义重新生成物化视图。
- 快速刷新（**FAST**）： 采用增量刷新的机制，只将自上次刷新以后对基表进行的所有操作刷新到物化视图中去。FAST必须创建基于主表的视图日志。对于增量刷新选项，如果在子查询中存在分析函数，则物化视图不起作用。
  - Oracle物化视图的快速刷新机制是通过**物化视图日志**完成的。 Oracle通过一个物化视图日志还可以支持多个物化视图的快速刷新 。 物化视图日志根据不同物化视图的快速刷新的需要，可以建立为ROWID或PRIMARY KEY类型的 。还可以选择是否包括SEQUENCE、INCLUDING NEW VALUES以及指定列的列表。
- **FORCE**方式： 这是默认的数据刷新方式。Oracle会自动判断是否满足快速刷新的条件，如果满足则进行快速刷新，否则进行完全刷新。

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

- **enable query rewrite 和 disable query rewrite** 
- 分别指出创建的物化视图是否支持查询重写。 查询重写是指当对物化视图的基表进行查询时 ， oracle会自动判断能否通过查询物化视图来得到结果，如果可以，则避免了聚集或连接操作，而直接从已经计算好的物化视图中读取数据。
- 默认为 disable query rewrite 

### 在源表建立 物化视图日志

```sql
create materialized  view log on test_table  
tablespace test_space  -- 日志空间  
with primary key ;      -- 指定为主键类型
```

### 删除物化视图及日志：

```sql
--删除物化视图日志
drop materialized view log on test_table;
--删除物化视图        
drop materialized view mv_materialized_test; 
```

# 子查询

- 子查询 (内查询) 在主查询之前一次执行完成。结果被主查询(外查询)使用.
- 子查询可以返回单行结果，可以返回多行结果，也可以不返回结果。如果子查询未返回任何行，则主查询也不会返回任何结果。
  - 返回**单行结果：标量**：可以使用在适用于标量的位置。
  - 返回**多行结果：关系**：可以使用在适用于关系的位置。
- 子查询的查询性能通常消耗较大，因此尽量减少使用子查询。

## 单行子查询

- 只能返回一行结果，不能返回空值
- 只能使用单行操作符 =、>、>=、<、<=、<>


```sql
--显示员工的employee_id,last_name和location。
--其中，若员工department_id与location_id为1800的department_id相同，则location为’Canada’,其余则 为’USA’。

--1.1 CASE --在CASE中使用单列子查询
SELECT employee_id
      ,last_name
      ,department_id
      ,(CASE WHEN department_id = (
                                   SELECT department_id
                                   FROM departments
                                   WHERE location_id = 1800
                                  )
                 THEN 'Canada'
             ELSE 'USA'
        END) location
FROM employees
ORDER BY department_id;

--1.2 CASE --在CASE中使用单列子查询
SELECT employee_id
      ,last_name
      ,department_id
      ,(CASE department_id WHEN  (
                                  SELECT department_id
                                  FROM departments
                                  WHERE location_id = 1800
                                  )
                                THEN 'Canada'
                           ELSE 'USA'
END) location
FROM employees
ORDER BY department_id;

--2. DECODE
SELECT employee_id
      ,last_name
      ,DECODE( department_id
              ,(
                SELECT department_id
                FROM departments
                WHERE location_id = 1800
                )
              ,'Canada'
              ,'USA'
             ) location
FROM employees
ORDER BY location DESC;
```

## 多行子查询

**多行操作符**

| 操作符      | 描述                                          | 说明             |
| :---------- | :-------------------------------------------- | :--------------- |
| IN / EXISTS | 等号列表中任意一个/关系中是否存在满足条件的行 | 存在             |
| ANY / SOME  | 和子查询返回的任意一个值比较                  | 只要一个满足即可 |
| ALL         | 和子查询返回的所有值比较                      | 需要满足所有     |


```sql
--返回job_id和141号员工相同，salary比143号员工多的姓名，job_id工资
SELECT last_name
      ,salary
      ,job_id
FROM employees
WHERE job_id = (
                SELECT job_id
                FROM employees
                WHERE employee_id = 141
                )
AND salary > (
              SELECT salary
              FROM employees
              WHERE employee_id = 143
              );
```

```sql
--返回其他部门中比job_id为'IT_PROG'部门任一工资低的员工的员工号，姓名，job_id和salary
SELECT employee_id
      ,last_name
      ,job_id
      ,salary
FROM employees
WHERE salary < ANY(
                   SELECT salary
                   FROM employees
                   WHERE job_id = 'IT_PROG'
                   );
```


```sql
--查找部门中工资高于部门平均工资的员工
--1)
SELECT last_name
      ,department_id
      ,salary
      ,(
        SELECT AVG(salary)
        FROM employees e
        WHERE e1.department_id = e.department_id
        GROUP BY department_id
        ) avg_sal
FROM employees e1
WHERE salary > (
                SELECT AVG(salary)
                FROM employees e2
                WHERE e1.department_id = e2.department_id
                GROUP BY department_id
                );

--2)
SELECT a.last_name
      ,a.salary
      ,a.department_id
      ,b.avg_sal
FROM employees a,(
                  SELECT department_id
                        ,AVG(salary) avg_sal
                  FROM employees
                  GROUP BY department_id
                  ) b
WHERE a.department_id = b.department_id
AND a.salary > b.avg_sal;
```

### IN 和 EXISTS

#### IN

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

#### EXISTS 

**检查规则**

1. 如果在子查询中存在满足条件的行，不在子查询中继续查找，条件返回TRUE。
2. 如果在子查询中不存在满足条件的行，条件返回FALSE，继续在子查询中查找。

```sql
--查询公司管理者的employee_id,last_name,job_id,department_id
SELECT employee_id
      ,last_name
      ,job_id
      ,department_id
FROM employees OUTER
WHERE EXISTS(
    SELECT *
    FROM employees
    WHERE manager_id = OUTER.employee_id
);
```

**NOT EXISTS**

- 检查在子查询中是否存在不满足条件的行

```sql
--查询departments表中不存在与employees表的部门的department_id,department_name
SELECT department_id
      ,department_name
FROM departments d
WHERE NOT EXISTS(
    SELECT 'x'  --'x'可以是任何
    FROM employees
    WHERE department_id = d.department_id
);
```

#### IN 和 EXISTS 区别

- 如果**子查询**得出的结果集记录较少，主查询中的表较大且又有索引时应该用**IN**,  
- 如果**外层**的主查询记录较少，子查询中的表大，又有索引时使用**EXISTS**。  
- 另外IN不对NULL进行处理。

**区分IN和EXISTS主要是驱动顺序的改变（这是性能变化的关键）**

- 如果是EXISTS，那么外层表为驱动表，先被访问。如果是IN，那么先执行子查询。
- **IN是把外表和内表作hash join，而EXISTS是对外表作LOOP**，每次LOOP再对内表进行查询

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

#### NOT IN 和 NOT EXISTS

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

### GROUP BY多字段分组和FROM子查询的区别

- 即：此时 emp 表之中存在有 14000 条数据，dept 表中存在有 4000 条数据。  多表查询一定会产生笛卡儿积  

**多字段分组**：积的数量：emp 的 14000 条*dept 的 4000 条 = 56,000,000 条数据；

**FROM子查询**：

- 第一步（内嵌子查询）：针对于 emp 表查询，最多操作 14000 条记录，最多返回 4000 条记录；
- 第二步，子查询和 dept 表关联；
- 积的数量：dept 表的 4000 条* 子查询返回的最多 4000 条 = 16000000 条记录；
- 总的数据量：16000000 + 14000 = 16,014,000 条记录

## 子查询中使用主查询的列

```sql
--查询员工中工资大于部门平均工资的员工last_name,salary和department_id
SELECT last_name
      ,salary
      ,department_id
FROM employees outer
WHERE salary > (
                SELECT AVG(salary)
                FROM employees
                WHERE department_id = out.department_id
                );
```

```sql
--若employees表中的employee_id与job_history中employee_Id相同的数目不小于2，输出这些相同的员工的employee_id,job_id
SELECT e.employee_id
      ,last_name
      ,e.job_id
FROM employees e
WHERE 2 <= (
            SELECT COUNT(*)
            FROM job_hitoryy
            WHERE employee_id = e.employee_id
            );
```

## 相关子查询

### 相关查询

- 在where子句中，可以使用子句中关系的别名。
- 而在from子句中，则不能使用在当前子句中的别名。

### 相关更新 

- 使用相关子查询依据一个表中的数据更新/修改另一个表中的数据

```sql
ALTER TABLE employees
ADD(department_name VARCHAR2(14));
```

```sql
UPDATE emlpoyees e
SET department_name = (
                       SELECT department_name
                       FROM departments d
                       WHERE e.departmet_id = d.department_id
                       );
```

### 相关删除

```sql
-- 删除employees表中，基于emp_history表皆有数据
DELETE FROM employees e
WHERE (
       SELECT employee_id
       FROM emp_history
       WHERE employee_id = e.employee_id
       );  
```

## 成对比较 

- 多列子查询 主查询与子查询返回的多个列进行比较
- 成对比较

```sql
--查询与141号或174号员工的manager_id,department_id相同的其他员工信息

--1)成对比较
SELECT employee_id
      ,manager_id
      ,department_id
FROM employees
WHERE (manager_id,department_id) IN (
                                     SELECT manager_id
                                           ,department_id
                                     FROM employees
                                     WHERE employee_id IN (141,174)
                                     )
AND employee_id NOT IN  (141,174);

--2)不成对比较
SELECT employee_id
      ,manager_id
      ,department_id
FROM employees
WHERE manager_id IN (
                     SELECT manager_id
                     FROM employees
                     WHERE employee_id IN (141,174)
                     )
  AND department_id IN (
                        SELECT department_id
                        FROM employees
                        WHERE employee_id IN (141,174)
                        )
  AND employee_id NOT IN (141,174);
```

# JOIN 连接

- 连接分为内连接（常规连接）和外连接。

## 内连接

**用处**

- 合并具有同一列的两个以上的表的行
- 结果集不包含一个表与另一个表不匹配的行

**解释**

- 自连接 SELF JOIN ; INNER JOIN (正常SELECT) 不适合查询大表，用来比较表中的行，或查询分层数据
- 通过别名，将同一张表视为多张表
- 连接的表是同一张表，使用自连接可以将自身表的一个镜像当作另一个表来对待，从而能够得到一些特殊的数据。

**语法**

```sql
SELECT t1.no1,no2,no3
FROM table1
INNER JOIN table1 t1 ON no1;
```

```sql
--查询所有雇用日期相同(同一天入职)的员工
SELECT e1.employee_id,e1.hire_date
FROM employees e1
 INNER JOIN employees e2 
  ON e1.employee_id <> e2.employee_id
  AND e1.hire_date = e2.hire_date;
```

### natural join

- 全自动连接(自然连接) NATURAL JOIN
- 只返回在两个关系中的都拥有的相同属性的相同元组。

1. 如果做自然连接的两个表的有多个字段都满足有<mark>相同名称和相同数据类型（系列）</mark>，那么他们会被作为自然连接的条件。
2. 如果自然连接的两个表仅是字段名称相同，但数据类型不同，那么将会返回一个错误。
3. 在设计表时，应该尽量在不同表中具有相同含义的字段使用相同的名字和数据类型。以便使用NATURAL JOIN

```sql
SELECT no1,no2,no4
FROM tabel1
NATURAL JOIN table2;
```

```sql
SELECT department_id,
       department_name
FROM employees
NATURAL JOIN departments;
```

- 注：`NATURAL JOIN`的SELECT子句中的列，不能有限定词，
  - 如`employees.department_id`
  - 其他连接都需要限定词


### join using

- 使用同名列的连接 JOIN .. USING .. 
- USING多表连接时，若列名不同，则不适用，类似于natural join的扩展。

```sql
SELECT t1.no1,no2,no4
FROM table1 t1
 JOIN table2 USING (no1);
```

### join on

- 等值连接 join on

**1. [INNER] JOIN**

- JOIN子句可以视为FROM子句的一部分

```sql
SELECT t1.no1,no2,no4
FROM table1 t1
 JOIN table2 t2 ON t1.no1 = t2.no1
[WHERE...];
```

**2. WHERE**

```sql
SELECT t1.no1,no2,no4
FROM table1 t1,table2 t2
WHERE t1.no1 = t2.no1;
```

**例：两表连接**

```sql
--1.JOIN
SELECT DISTINCT e.department_id,d.department_id
FROM employees e
JOIN departments d
ON e.department_id = d.department_id;

--2.WHERE
SELECT DISTINCT e.department_id,d.department_id
FROM employees e
    ,departments d
WHERE e.department_id = d.department_id;
```

**例：三表连接**

- 注意：需要中间表连接。 

```sql
--1.JOIN
SELECT employee_id,e.department_id,department_name,d.location_id,city
FROM employees e
  JOIN departments d ON e.department_id = d.department_id
  JOIN locations l ON d.location_id = l.location_id; 
  --department表中的department_id和location_id

--2. WHERE
SELECT employee_id,e.department_id,department_name,d.location_id,city
FROM employees e,departments d,locations l
WHERE e.department_id = d.department_id
  AND d.location_id = l.location_id;
```

## 外连接  

### 左外连接 LEFT [OUTER] JOIN 

- 当连接条件不成立的时候，等号左边的表仍然被包含
  - 即可以包含FROM子句中的表与ON中的表不匹配的值 

```sql
--可以返回左表(table1)中与右表(table2)不匹配的值
SELECT t1.no1,no2,no4
FROM table1 t1,table2,t2
 LEFT OUTER JOIN table2 ON t1.no1 = t2.no1;
```

### 右外连接 RIGHT [OUTER] JOIN 

- 当连接条件不成立的时候，等号右边的表仍然被包含

```sql
--可以返回右表(table2)中与左表(table1)不匹配的值
SELECT t1.no1,no2,no4
FROM table1 t1
 RIGHT OUTER JOIN table2 ON t1.no1 = t2.no1;
```

### 全连接 FULL [OUTER] JOIN 

- 查询结果等于左外连接和右外连接的和

```sql
SELECT t1.no1,no2,no4
FROM table1 t1
 FULL OUTER JOIN table2 ON t1.no1 = t2.no1;
--可以返回左右表中不匹配的行
```

### 外连接运算符(+)

- 在连接条件中无匹配行的表的列后面加(+)
- 两表在连接中除了内连接，还包含返回左(右)表中不满足条件的行，称为左(右)内连接
- 没有匹配的行时，结果表中相对应的列为空

**哪边无加号+就是什么连接**

```sql
--左外连接
SELECT t1.no1,no2,no4
FROM table1 t1,table2 t2
WHERE t1.no1 = t2.no1(+);  

--右外连接
SELECT t1.no1,no2,no4
FROM table1 t1,table2 t2
WHERE t1.no1(+) = t2.no1;
```

### 交叉连接 CROSS JOIN 不带ON子句

- 返回被连接的两个表所有数据行的笛卡尔积，
- 返回到结果集合中的数据行数等于第一个表中符合查询条件的数据行数乘以第二个表中符合查询条件的数据行数.

```sql
SELECT t1.no1,no2,no4
FROM table1 t1
CROSS JOIN table2;
```

# 常用函数

## 数值函数

### ROUND(n[,m]) 四舍五入 

- 传回一个数值，该数值是按照指定的小数位元数进行四舍五入运算的结果。
  - 省略m：0  
  - m>0:小数点后m位;
  - m<0:小数点前m位

```sql
SELECT ROUND( number, [ decimal_places ] ) 
FROM DUAL

/*
参数:
number : 欲处理数值
decimal_places : 四舍五入 , 小数取几位 ( 预设为 0 )
*/
```

```sql
SELECT ROUND(4.5),ROUND(2.3),ROUND(2.12,2)
FROM dual;

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
SELECT CEIL(23.45),FLOOR(23.45) 
FROM dual;

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
select ceil((To_date('2008-05-02 00:00:00' , 'yyyy-mm-dd hh24-mi-ss') - 
             To_date('2008-04-30 23:59:59' , 'yyyy-mm-dd hh24-mi-ss')) * 24 * 60 * 60 * 1000
           ) 相差豪秒数 
FROM DUAL;

/*
相差豪秒数

----------

  86401000

1 row selected
*/
```

```
--获取两时间的相差秒数

select ceil((To_date('2008-05-02 00:00:00' , 'yyyy-mm-dd hh24-mi-ss') - 
             To_date('2008-04-30 23:59:59' , 'yyyy-mm-dd hh24-mi-ss')) * 24 * 60 * 60
           ) 相差秒数 
FROM DUAL;

/*
相差秒数

----------

     86401

1 row selected
*/
```

```
--获取两时间的相差分钟数

select ceil(((To_date('2008-05-02 00:00:00' , 'yyyy-mm-dd hh24-mi-ss') - 
              To_date('2008-04-30 23:59:59' , 'yyyy-mm-dd hh24-mi-ss'))) * 24 * 60
           )  相差分钟数 
FROM DUAL;

/*
相差分钟数

----------

      1441

1 row selected
*/
```

```
--获取两时间的相差小时数

select ceil((To_date('2008-05-02 00:00:00' , 'yyyy-mm-dd hh24-mi-ss') - 
             To_date('2008-04-30 23:59:59' , 'yyyy-mm-dd hh24-mi-ss')) * 24
           )  相差小时数 
FROM DUAL;

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

select ceil((To_date('2008-05-02 00:00:00' , 'yyyy-mm-dd hh24-mi-ss') - 
             To_date('2008-04-30 23:59:59' , 'yyyy-mm-dd hh24-mi-ss'))
             )  相差天数 
FROM DUAL;

/*

相差天数

----------

         2

1 row selected

*/
```

```
--获取两时间月份差

select (EXTRACT(year FROM to_date('2009-05-01','yyyy-mm-dd')) - EXTRACT(year FROM to_date('2008-04-30','yyyy-mm-dd'))) * 12 + 

        EXTRACT(month FROM to_date('2008-05-01','yyyy-mm-dd')) - EXTRACT(month FROM to_date('2008-04-30','yyyy-mm-dd')) months

from dual;

/*

MONTHS

----------

        13

1 row selected
```

*/

```
--获取两时间年份差

select EXTRACT(year FROM to_date('2009-05-01','yyyy-mm-dd')) - EXTRACT(year FROM to_date('2008-04-30','yyyy-mm-dd')) years from dual;

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
| SIN(n) ASIN(n) |      |
| COS(N) ACOS(n) |      |
| TAN(n) ATAN(n) |      |


### 随机

#### DBMS_RANDOM.VALUE(n,m) 随机数 

- 生成一个指定范围[n,m)的38位随机小数（小数点后38位），若不指定范围则默认为范围为[0,1)的随机数。

```sql
SELECT DBMS_RANDOM.VALUE
FROM dual;

SELECT TRUNC(DBMS_RANDOM.VALUE(10,50),0)
FROM dual;
```

#### DBMS_RANDOM.STRING('c',n) 随机字符串

- 生成一个长度为n的随机字符串。

```sql
SELECT DBMS_RANDOM.STRING('A',20)
FROM dual
```

## 字符函数

### 大小写转换函数

| 函数          | 说明       |
| :------------ | :--------- |
| UPPER(char)   | 转换为大写 |
| LOWER(char)   | 转换为小写 |
| INITCAP(char) | 首字母大写 |

```sql
SELECT UPPER('hello'),LOWER('HELLO'),INITCAP('hELlo')
FROM dual;

UPPER('HELLO') LOWER('HELLO') INITCAP('HELLO')
-------------- -------------- ----------------
HELLO          hello          Hello
```

```sql
SELECT employee_id,salary
FROM employees
WHERE LOWER(last_name) = 'king'
```

### SUBSTR(char,[m[,n]])  获取子字符

- char：字符串
- m 子字符串的起始位置，m从1开始,如果m写0也是从第一个字符开始。如果m为负数时，从字符串的尾部开始截取。
- n 取出多少位,而不是取到哪一位，n省略时，从m的位置截取到结束，

```sql
SELECT SUBSTR('hello',2,3)
FROM dual;

SUBSTR('HELLO',2,3)
-------------------
ell
```

```sql
SELECT employee_id,last_name,salary
FROM employees
WHERE LOWER(SUBSTR(last_name,0)) = 'king'; --即WHERE LOWER(last_name) = 'king';  --LIKE 模糊查询类似作用
```

### LENTH(char) 获取字符串长度函数 

- 获取的长度会包含空格的长度 

### CONCAT(char1,char2)  字符串连接函数 

- 与||作用一样

```sql
SELECT CONCAT('hello ','world')
FROM dual;
```

```sql
SELECT CONCAT(first_name,last_name)
FROM employees;
```

### INSTR(c1,c2) 获取c2在c1中的位置

```sql
SELECT INSTR('hello','h')
FROM dual;
```

### 填充函数

#### LPAD(c1,n,c2) 左填充

- 用c2从左往右补满(n-LENTH(c1))空位

```sql
SELECT LPAD(salary,10,'*')
FROM employees;
```

#### RPAD(c1,n,c2) 右填充

- 用c2从左往右补满(n-LENTH(c1))空位

### 去除字符串函数

#### TRIM(c2 FROM c1) 首尾均去除

- 要求首/尾是要去除的字符代表从c1中去除c2字符串。就是子文本替换，要求c2中只能是一个字符

```sql
SELECT TRIM('e' FROM 'hello') --结果不变
FROM dual;

TRIM('E'FROM'HELLO')
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
SELECT TRIM('   hello    ')
FROM dual;
```

#### LTRIM(c1[,c2]) 左去除

- 从c1中去除c2，从左边开始去除，要求第一个就是要去除的字符，有多少个重复的该字符就会去除多少次
- 默认去除左边的空格

```sql
SELECT LTRIM('h','hello')
FROM dual;

LTRIM('H','HELLO')
--------------------
ello
```

#### RTRIM(c1[,c2]) 右去除

- 从c1中去除c2，要求右侧第一个就是要去除的字符，有多少个重复的该字符就会去除多少次
- 默认去除右边的空格


#### REPLACE(char,s_string[,r_string]) 字符串替换函数，省略第三个参数则用空白替换

```
SELECT REPLACE('hello','el','lll')
FROM dual;

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
SELECT TRANSLATE('abcd','acd','#')
FROM dual;
--#B
```

##### 统计某个字符出现次数

```sql
SELECT LENGTH(TRANSLATE('abcdbbb','b' || 'abcdbbb','b'))
FROM dual

--而不是
SELECT LENGTH('abcdbbb') - LENGTH(TRANSLATE('abcdbbb','b',''))
FROM dual; --返回空值 
```




```sql
INSERT INTO mytable 
VALUES chr(38);
```

- ASCII

```sql
SELECT ASCII('x'), ASCII('y'),ASCII('z') 
FROM dual;
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
- 系统时间 sysdate 默认格式：DD-MON-RR 天-月-年

### ADD_MONTHS(date,i)  用于添加指定的月份，返回在指定的日期上添加的月份

i可以是任意整数，如果i是负数，则是在原有的值上减去该月份了

```
SELECT ADD_MONTHS(sysdate,1)
FROM dual;
```

### NEXT_DAY(date,char)  第二个参数指定星期几，在中文环境下输入星期X即可，返回下一个周几是哪一天

```
SELECT NEXT_DAY(sysdate,'星期二')
FROM dual;
```

### LAST_DAY(date) 用于返回日期所在月的最后一天

```
SELECT LAST_DAY(sysdate)
FROM dual;
```

### MONTHS_BETWEEN(date1,date2) 计算两个日期之间间隔的月份，前者减后者

```
SELECT MONTHS_BETWEEN('20-5月-15','10-1月-15') 
FROM dual;
```

### EXTRACT(date FROM datetime) 返回相应的日期部分

```
SELECT EXTRACT(year FROM sysdate)  --可以改month或者day
FROM dual; 
```

```
SELECT EXTRACT(hour FROM timestamp '2015-10-1 17:25:13') 
FROM dual;
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
ALTER SESSION SET NLS_DATE_LANGUAGE='AMERICAN';     
```

##### 2)参数

```
TO_DATE ('2002-08-26', 'YYYY-mm-dd', 'NLS_DATE_LANGUAGE = American')    
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
UNION     
select 1
       ,TO_DATE(null) 
from dual;     
--注意要用TO_DATE(null)    
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
select * from V$NLS_PARAMETERS    
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
    select months_between(to_date('01-31-1999','MM-DD-YYYY'),to_date('12-31-1998','MM-DD-YYYY')) "MONTHS" 
    FROM DUAL;     
    --1     
   
    select months_between(to_date('02-01-1999','MM-DD-YYYY'),to_date('12-31-1998','MM-DD-YYYY')) "MONTHS" 
    FROM DUAL;     
    --1.03225806451613
```

#### 10. Next_day的用法

```
    Next_day(date, day)     
   
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
    SELECT EXTRACT(HOUR FROM TIMESTAMP '2001-02-16 2:38:40') 
    from offer;
         
    SQL> select sysdate ,to_char(sysdate,'hh') from dual;     
   
    SYSDATE TO_CHAR(SYSDATE,'HH')     
    -------------------- ---------------------     
    2003-10-13 19:35:21 07     
   
    SQL> select sysdate ,to_char(sysdate,'hh24') from dual;     
   
    SYSDATE TO_CHAR(SYSDATE,'HH24')     
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
                ,mod(trunc(months_between( newer_date, older_date )),12 ) MONTHS
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
   'YYYY99 TO_C     
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
   select TO_DATE(FLOOR(TO_CHAR(sysdate,'SSSSS')/300) * 300,'SSSSS') ,TO_CHAR(sysdate,'SSSSS')     
   from dual    

   2002-11-1 9:55:00 35786     
   SSSSS表示5位秒数    
```

#### 20.一年的第几天

```
   select TO_CHAR(SYSDATE,'DDD'),sysdate from dual
       
   310 2002-11-6 10:03:51    
```

#### 21.计算小时,分,秒,毫秒

```
    select     
     Days,     
     A,     
     TRUNC(A*24) Hours,     
     TRUNC(A*24*60 - 60*TRUNC(A*24)) Minutes,     
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
   round(sysdate,'month') MONTH ,
   round(sysdate,'day') DAY 
   from dual
```

#### 25,trunc[截断到最接近的日期,单位为天] ,返回的是日期类型

```
   select sysdate S1,                    
     trunc(sysdate) S2,                 //返回当前日期,无时分秒
     trunc(sysdate,'year') YEAR,        //返回当前年的1月1日,无时分秒
     trunc(sysdate,'month') MONTH ,     //返回当前月的1日,无时分秒
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
select floor(to_number(sysdate-to_date('2007-11-02 15:55:03','yyyy-mm-dd hh24:mi:ss'))*24*60) as spanMinutes from dual    //时间差-分
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
SELECT Trunc(Trunc(SYSDATE, 'MONTH') - 1, 'MONTH') First_Day_Last_Month,
       Trunc(SYSDATE, 'MONTH') - 1 / 86400 Last_Day_Last_Month,
       Trunc(SYSDATE, 'MONTH') First_Day_Cur_Month,
       LAST_DAY(Trunc(SYSDATE, 'MONTH')) + 1 - 1 / 86400 Last_Day_Cur_Month
FROM dual;
```


## 转换函数

### to_char 转为字符串

#### TO_CHAR(date[,fmt[,params]]) 日期转为字符串

- date为需要转换的日期，fmt为转换的格式(不区分大小写)，params为转换的语言（通常默认会自动选择，可以省略，与安装语言一致）  

**fmt参数格式**

- 默认格式：DD-MON-RR   
- 格式有：  以02-02-1997为例

| 时域   | fmt参数 | 说明                   |
| :----- | :------ | :--------------------- |
| 年     | YY      | 97                     |
|        | YYYY    | 1997                   |
|        | YEAR    | NINTENN AND NITY SEVEN |
| 月     | MM      | 02                     |
|        | MON     | JUL                    |
|        | MONTH   | JULY                   |
| 日     | DD      | 02                     |
|        | DY      | MON                    |
|        | DAY     | MONDAY                 |
| 时     | HH24    |                        |
|        | HH12    |                        |
| 分     | MI      |                        |
| 秒     | SS      |                        |
| 上下午 | AM      | 上午/下午              |

- 用双引号""向日期函数添加字符,日期要用单引号''括起

```sql
SELECT TO_CHAR(sysdate,'yyyy"年"mm"月"dd"日" hh:mi:ss')
FROM dual;
```

```sql
SELECT TO_CHAR(sysdate,'HH24:MI:SS AM')
FROM dual;

TO_CHAR(SYSDATE,'HH24:MI:SSAM'
------------------------------
15:58:45 下午
```

#### TO_CHAR(number[,fmt]) 数字转为字符串


| fmt参数 | 说明                        |
| :------ | :-------------------------- |
| 9       | 显示数字并忽略前面的0       |
| 0       | 显示数字，位数不足，用0补齐 |
| .或D    | 显示小数点                  |
| ,或G    | 显示千分位                  |
| $       | 美元符号                    |
| S       | 加正负号（前后都可以）      |

```sql
SELECT TO_CHAR(123456,'$0000000000')
FROM dual;
```


### TO_DATE(char[,fmt[,params]]) 字符串转为日期

**fmt参数格式**


```sql
SELECT TO_DATE('2022-2-2','yyyy-mm-dd')
FROM dual;
```

#### 日期和字符转换函数用法（to_date,to_char）

```
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') as nowTime from dual;   //日期转化为字符串  
select to_char(sysdate,'yyyy') as nowYear from dual;   //获取时间的年  
select to_char(sysdate,'mm') as nowMonth from dual;   //获取时间的月  
select to_char(sysdate,'dd') as nowDay from dual;   //获取时间的日  
select to_char(sysdate,'hh24') as nowHour from dual;   //获取时间的时  
select to_char(sysdate,'mi') as nowMinute from dual;   //获取时间的分  
select to_char(sysdate,'ss') as nowSecond from dual;   //获取时间的秒

select to_date('2004-05-07 13:23:44','yyyy-mm-dd hh24:mi:ss')    from dual//

```

### TO_NUMBER(char[,fmt]) 字符串转数字

```sql
SELECT TO_NUMBER('$123,456.789','$999999.999')
FROM dual;
```

### 空值转换

#### NVL(expr1,expr2)

- NVL(expr1,expr2) 如果expr1为空值，则返回expr2

```sql
SELECT last_name,salary,NVL(commission_pct,0)
FROM employees;
```

#### NVL2(expr1,expr2,expr3) 

- NVL2(expr1,expr2,expr3) 如果expr1不为空值则返回expr2，expr1为空值则返回expr3

```sql
--若工资有奖金率，commission_pct不为空值，则返回工资加奖金，否则返回工资上调500
SELECT last_name,NVL2(commission_pct,salary * (1 + commission_pct),salary + 500)
FROM employees;
```

## 通用函数

-  适用于任何(包含空值)

### NULLIF(expr1,expr2) 

- NULLIF(expr1,expr2) 若相等返回NULL,不等返回expr2

```sql
--显示出那些换过工作的人员原工作，现工作。
SELECT e.last_name,e.job_id,j.job_id,NULLIF(e.job_id,j.job_id) "Old Job ID"
FROM employees e, job_history j
WHERE e.employee_id = j.employee_id
ORDER BY last_name;
```

### COALESCE(expr1,expr2,expr3)

1. 同时处理交替多值
2. 若第一个表达式为空，则返回下一个表达式对其他参数COALESCE结果

```sql
SELECT last_name,COALESCE(commission_pct,salary,10)
FROM employees
ORDER BY commission_pct;
```

### DECODE(exp1,con1,result1,con2,resul2...default) 解码

- 实现if ..then 逻辑
- 第一个是表达式,最后一个是不满足任何一个条件的值
- DECODE 是专属oracle的语法 有逗号','
- DECODE(字段,条件1,表达式1,条件2,表达式2,…默认表达式n)

```sql
--查询部门为10，20，30的员工信息，
--若部门号为10，则工资*1.1，若部门号为20，则工资*1.2，若部门号为30，则工资*1.3
  
SELECT employee_id
      ,last_name
      ,DECODE(department_id,10,salary * 1.1
                           ,20,salary * 1.2
                           ,30,salary * 1.3
              ) new_sal
FROM employees
WHERE department_id IN (10,20,30);
```

### TRUNC(date,精度说明符) 用于截取日期时间的TRUNC函数

- 精度说明符有: yyyy-mm-dd-hh-mi 截取时间到秒(不是ss)暂时不知道怎么操作
- 不可直接用TRUNC(sysdate,'yyyy-mm-dd')，会提示“精度说明符过多”
- 如果不填写精度说明符，则默认到DD，包含年月日，不包含时分秒

```sql
SELECT TRUNC(sysdate,'yyyy') --'yyyy' = 'year'
FROM dual;
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

### WM_CONCAT(column) 行转列

- 对分组函数的每一组结果中的某一指定值域进行整行输出。
- wm_concat()中使用的列不必出现在group by子句中。

```sql
select deptno,wm_concat(ename)
from emp
group by deptno;

DEPTNO WM_CONCAT(ENAME)
------ --------------------------------------------------------------------------------
    10 CLARK,MILLER,KING
    20 SMITH,FORD,ADAMS,SCOTT,JONES
    30 ALLEN,JAMES,TURNER,BLAKE,MARTIN,WARD
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
  - `PARTITION BY `语句（用于分组） 
  - `ORDER BY` 语句（用于排序）。

- 例如：LEAD(FIELD, NUM, DEFAULTVALUE) FIELD需要查找的字段，NUM往后查找的NUM行的数据，DEFAULTVALUE没有符合条件的默认值。

# 组函数（聚集函数）

## 常用组函数

-  组函数 (多行函数) 输入多个参数，或者是内部扫描多次，输出一个结果

| 组函数   | 说明     |
| -------- | -------- |
| AVG()    | 平均值   |
| COUNT()  | 记录总数 |
| MAX()    | 最大值   |
| MIN()    | 最小值   |
| STDDEN() | 标准差   |
| SUM()    | 总和     |

### 对null的操作

- count()：将null看作0来处理。
- 其他：忽略输入集合中的null，如果作用在null上，则返回null。

## GROUP BY

- **在SELECT中所有未包含在组函数的列都应该包含在GROUP BY中**
- GROUP BY中的列不必包含在SELECT中

1. **不能在WHEHE子句中使用组函数**
2. 在HAVING中使用组函数

- 如果在能使用WHERE的场景下，从SQL优化的角度来看，尽量使用WHERE效率更高，因为HAVING是在分组的基础上过滤分组的结果,而WHERE是先过滤,再分组,要处理的记录数不同。所以WHERE能使分组记录数大大降低，从而提高效率。

```sql
--求表中各部门平均工资
SELECT AVG(salary)
FROM employees
GROUP BY department_id;
```

### 查找平均工资最低的部门

```sql
SELECT rownum
      ,d.department_name "部门名称"
      ,t1.department_id "部门号"
      ,t1.avg_sal "平均工资"
FROM (
      SELECT department_id
            ,AVG(salary) avg_sal
      FROM employees
      GROUP BY department_id
      ORDER BY avg_sal ASC
      ) t1
     ,departments d
WHERE rownum <= 1
 AND t1.department_id = d.department_id
```

### 多列GROUP BY

- 先按在前的值域进行分组，再依次在之前分组的基础上进行分组。

```sql
SELECT AVG(salary),department_id,employee_id
FROM employees
GROUP BY department_id,employee_id; --按department_id分组后在内部按employee_id分组
```

```sql
--查询表中平均工资高于4000的部门
SELECT AVG(salary),department_id
FROM employees
HAVING AVG(salary) > 4000
GROUP BY department_id;
```

## HAVING 子句

- SQL在形成之分组后才会使用HAVING子句中的谓语，故可以在having子句中使用组函数（对分组后的数据进行筛选）
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
SELECT MAX(AVG(salary))
FROM employees
GROUP BY department_id;
```

```sql
--求部门平均工资最大值及部门名称
SELECT DISTINCT (
        SELECT MAX(AVG(salary))
        FROM employees
        GROUP BY department_id
        ) salary,
       (
        SELECT department_id
        FROM (
              SELECT AVG(salary) sal,department_id
              FROM employees
              GROUP BY department_id
              )
        WHERE sal = (
                      SELECT MAX(AVG(salary))
                      FROM employees
                      GROUP BY department_id
                      )
        ) department_id
FROM employees
```

## 分级汇总

- **rollup() 按分组的第一个列进行统计和最后的小计**
- **cube() 按分组的所有列的进行统计和最后的小计**

### rollup()

```sql
select deptno,job,sum(sal)
from emp
group by rollup(deptno,job)

DEPTNO JOB         SUM(SAL)
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

DEPTNO JOB         SUM(SAL)
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

### WM_CONCAT()函数 列转行

- 对分组函数的每一组结果中的某一指定值域进行整行输出。
- wm_concat()中使用的列不必出现在group by子句中。

```sql
select deptno,wm_concat(ename)
from emp
group by deptno;

DEPTNO WM_CONCAT(ENAME)
------ --------------------------------------------------------------------------------
    10 CLARK,MILLER,KING
    20 SMITH,FORD,ADAMS,SCOTT,JONES
    30 ALLEN,JAMES,TURNER,BLAKE,MARTIN,WARD
```

### 基本语句实现 行转列

```sql
SELECT name "姓名"
      ,salary_type "薪水类型"
      ,cnt "数量(元)"
FROM salary_composite;

--行转列

SELECT name "姓名"
      ,SUM(DECODE(salary_type,'基本工资',cnt,0)) "基本工资"
      ,SUM(DEOCDE(salary_type,'交通补贴',cnt,0)) "交通补贴"
      ,SUM(DECODE(salary_type,'全勤奖',cnt,0)) "全勤奖"
      ,SUM(DECODE(salary_type,'效益奖',cnt,0)) "效益奖"
FROM salary_composite
GROUP BY name;

--再换回去

SELECT name "姓名"
      ,'基本工资' "薪水类型"
      ,base_salary "数量(元)"
FROM t_salary_composite_to k
WHERE base_salary <> 0
UNION ALL
SELECT name
      ,'交通补贴'
      ,traffic_salary
FROM t_salary_composite_to
WHERE traffic_salary <> 0
UNION ALL
SELECT name
      ,'全勤奖'
      ,ontime_salary
FROM t_composite_to
WHERE ontime_salary <> 0
UNION ALL 
SELECT name
      ,'效益奖'
      ,bonus
WHERE bonus <> 0
ORDER BY 1 DESC;
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

# WITH 临时查询表

- WITH子句将该子句执行一次并存储到用户的临时表空间中，在查询结束后，该临时表被删除。
- 使用WITH子句可提高查询效率，避免在SELECT语句中重复书写相同的语句块

```sql
--查询公司各部门的总工资大于公司中各部门的平均总工资的部门信息
WITH
dept_costs
AS
(
SELECT department_name,d.department_id,SUM(salary) sum_sal
FROM employees e,departments d
WHERE e.department_id = d.department_id
GROUP BY department_name,d.department_id
),
avg_cost
AS
(
SELECT SUM(sum_sal)/COUNT(*) dept_avg
FROM dept_costs
)

SELECT *
FROM dept_costs
WHERE sum_sal >(
                SELECT dept_avg
                FROM avg_cost
                )
ORDER BY department_name
```

# SEQUENCE 序列

序列 可供多个用户来产生唯一数据的数据库对象,与表无关系

1. 自动提供唯一的数值
2. 共享对象
3. 主要用于提供主键值
4. 将序列装入内存可以提高访问效率

5. SEQUENCE号是数据库系统按照一定规则自增的数字序列，因为自增所以不会重复。
6. 用于记录数据库中最新动作的语句，只要语句有动作(I/U/D等),SEQUENCE号都会随着更新,可以根据SEQUENCE号来SELECT出更新的语句

## CREATE SEQUENCE 创建序列

```sql
CREATE SEQUENCE sequence_name
[START WITH n1] --START WITH 生成的第一个n1值
[INCREMENT BY n2] --INCREMENT BY n2 递增量,可以为正整数或负整数，指明每一次增加多少
[MAXVALUE n3|NO MAXVALUE] --MAXVALUE 最大值，NO MAXVALUE 用于指定序列没有上限
[MINVALUE n4|NO MINVALUE] --MINVALUE 最小值，NO MINVALUE 没有指定最小下限
[CACHE n5|NOCACHE] --CACHE 用高速缓存中可以预分配的序列号个数,默认是20。 
   --如果缓存中的序列号没有用完就关闭数据库等其它原因. 没有使用的序列号就丢失了,所以不能保证序列号是连续的。
   --如果指定CACHE值，ORACLE就可以预先在内存里面放置一些SEQUENCE,这样存取的快些。CACHE里面的取完后，oracle自动再取一组到CACHE。 
      --使用CACHE或许会跳号,故使用NOCACHE避免
   --SEQUENCE_CACHE_ENTRIES参数，设置能同时被cache的sequence数目
                    --nocache高速缓冲中不预分配序列号，每次只生成一个序列号,虽然降低了获取序列号的速度,但是可以保证序列号的连续性。
[CYCLE|NOCYCLE] --CYCLE 序列达到最大值或最小值后是否循环。再从n1开始循环，默认NO CYCLE
[ORDER|NO ORDER] --ORDER 用于指定按顺序生成序列，只有在RAC时需要指定，
                    --指定ORDER是为了保证序列号是因为有请求才生成的。
                    --在使用序列号作为一个时间戳时很有用，
                 --NO ORDER 是不指定按顺序生成序列(默认)
--n1到n5都是整数
```

```sql
CREATE SEQUENCE emp_seq 
INCREMENT BY 10   --每次增长10
START WITH 120   --从120开始增长
MAXVALUE 9999    -- 提供的最大值
NOCACHE        -- 不需要缓存登录
NOCYCLE;       -- 不需要循环
```

## 使用序列 .NEXTVAL和.CURRVAL

- 在创建序列后，**第一次使用序列要先查询下一值.nextval才能使用查询当前值.currval**
  - 序列创建后初始无值,无法查询当前值.currval

```sql
SELECT emp_seq.nextval 
--引用下一序列值,每执行一次SEQUENCE号会增加,每使用一次,都会触发一次自增长                  
--第一次NEXTVAL返回的是初始值；随后的NEXTVAL会自动增加你定义的INCREMENT BY值，然后返回增加后的值。                   
--在同一个语句里面使用多个NEXTVAL，其值就是不一样的
FROM dual;
```

```sql
SELECT emp_seq.currval --引用当前序列值
FROM dual;
```

**注意：**

1. 将序列装入内存可提高访问速度
2. 序列在以下的情况下出现裂缝
   - 回滚
   - 系统异常
   - 多个表同时使用同一个序列
3. 若不将序列的值装入内存NOCACHE可使用表USER_SEQUENCES查看当前有效值

**在SQL语句中可以使用SEQUENCE的地方：**

- 不包含子查询、SNAPSHOT、VIEW的 SELECT 语句
- INSERT语句的子查询中
- INSERT语句的VALUES中
- UPDATE 的 SET中

**在表中使用sequence：**

```sql
CREATE TABLE tab_name(
                      col_int INT,
                      col_varchar VARCHAR2(20),
                      col_seq INT
                      );

INSERT tab_name INTO VALUES(1, 'xyz', sequence_name.nextval); 
--或者 INSERT tab_name INTO VALUES(1, 'abc', nextval FOR sequence_name);
INSERT tab_name INTO VALUES(2, 'fgh', sequence_name.nextval); 
--或者 INSERT tab_name INTO VALUES(2, 'fgh', nextval FOR sequence_name);

UPDATA tab_name 
SET col_varchar = '678', col_seq = sequence_name.nextval 
WHERE col_int = 2; 

DELETE tab_name col_sql = sequence_name.nextval 
WHERE col_int = 1;
```

## 查询序列 USER_SEQUENCES

- 数据字典 USER_SEQUENCES

```sql
SQL> SELECT *
  2  FROM USER_SEQUENCES
  3  WHERE LOWER(SEQUENCE_NAME) = 'test_seq';
SEQUENCE_NAME                   MIN_VALUE  MAX_VALUE INCREMENT_BY CYCLE_FLAG ORDER_FLAG CACHE_SIZE LAST_NUMBER
------------------------------ ---------- ---------- ------------ ---------- ---------- ---------- -----------
TEST_SEQ                                1         99            1 Y          N                  30          31
```

- 若不将序列的值装入内存NOCACHE可使用表USER_SEQUENCES查看当前有效值

```sql
SELECT emp_seq.currval
FROM USER_SEQUENCES;
```

## 修改序列 ALTER sequence_name ……

- **如果想要修改START WITH的值必须先删除SEQUENCE再重新写**
- 想改变序列化的MINVALUE必须删除序列化后再重新建立序列化.**不可以修改序列化的MINVALUE**。
- 修改序列的增量、最大值、最小值、循环选项、是否装入内存

**注意:**

1. 必须是序列的拥有者或对序列有ALTER权限
2. 只有将来的序列值会被改变
3. 改变序列的初始值只能通过删除后重建序列

## 删除序列

```sql
DROP SEQUENCE emp_seq;
```

## 触发器实现自动递增列

- Oracle不支持实现自动递增列，即不能够把SEQUENCE做为默认值放在列的属性里，
- 但可以通过**触发器实现**，设置好触发器之后，所有的插入语句，将忽略传入的主键，只使用指定的SEQUENCE生成主键。

```sql
CREATE SEQUENCE seq_id
minvalue 1
maxvalue 99999999
start with 1
increment by 1
nocache
order;

建触发器代码为：
CREATE OR REPLACE TRIGGER menu_tri
BEFORE 
  INSERT ON menu
FOR EACH ROW
DECLARE 
  next_id NUMBER(5);
BEGIN
  IF :NEW.id = 0 OR :NEW.id IS NULL
    THEN SELECT menu_seq.nextval
         INTO next_id
         FROM sys.dual;
         :NEW.id := next_id;
  END IF;
END;
  注：:new 代表 数据改变后的新值，相对应的有 :old 原值
      := 代表 赋值
      :nextid表示引用sqlplus中定义的变量 
```

# INDEX 索引 

- 数据库中索引(Index)的概念与目录的概念非常类似。如果某列出现在查询的条件中，而该列的数据是无序的，查询时只能从第一行开始一行一行的匹配。创建索引就是对某些特定列中的数据排序，生成独立的索引表。在某列上创建索引后，如果该列出现在查询条件中，Oracle会自动的引用该索引，先从索引表中查询出符合条件记录的ROWID，由于ROWID是记录的物理地址，因此可以根据ROWID快速的定位到具体的记录，**表中的数据非常多时，引用索引带来的查询效率非常可观**。

1. 一种独立于表的模糊对象，可以存储在与表不同的磁盘或表空间
2. 索引被删除或破坏，不会对表产生影响，其影响的只是查询的速度
3. 索引一旦建立，Oracle管理系统会对其进行自动维护，且由Oracle管理系统决定何时引用索引，用户不用在查询语句中指定使用哪个索引
4. 在删除一个表时，所有基于该表的索引会自动被删除
5. 通过指针加速Oracle服务器的查询速度
6. 通过快速定位数据的方法，减少磁盘I/O

## 创建索引

1. 自动创建 在定义PRIMARY KEY 或 UNIQUE约束后系统自动在相应的列上创建唯一性索引
2. 手动创建 用户可以在其他列上创建非唯一性索引，以加速查询

```sql
CREATE [UNIQUE] [BITMAP] INDEX 索引名 ON 表(列1[,列2…]);
[TABLESPACE 表空间名] --表示索引存储的表空间
[SCTFREE n1]    --索引块的空闲空间n1
[STORAGE         --存储块的空间
 (
    INITIAL 64K  --初始64k
    NEXT 1M
    MINEXTENTS 1
    MAXEXTENTS unlimited
)];
```

```sql
在一个或多个列上创建索引
CREATE INDEX emp_last_name_index ON employees(last_name);
```

**什么时候创建索引**

1. 列中的数据分布范围很广
2. 列经常在WHERE子句或连接条件中出现
3. 表经常被访问而且数据量很大，访问数据大概占数据总量的2%-4%
   - 当任何单个查询要检索的行少于或者等于整个表行数的10%时，索引就非常有用。

**什么时候不要创建索引**

1. 表很小
2. 列不经常作为连接条件出现在WHERE子句
3. 查询的数据大于2%-4%
4. 表经常更新

索引并不需要被引用，只是我们在用name进行查询时，速度会更快，但插入的速度会慢(在插入一个数据时，还需要维护一个索引)

在查询中可能经常使用job的小写作为条件的表达式，因此创建索引时，可以先对JOB列中的所有值转换为小写后创建索引，而这时需要使用lower函数，这种索引称为基于函数的索引。

## 索引类型

**b-tree索引**

- Oracle数据中最常见的索引，就是b-tree索引，CREATE INDEX创建的普通索引就是b-tree索引，没有特殊的必须应用在哪些数据上。

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

## 查询索引 

**数据字典 USER_INDEXES**
**数据字典 USER_IND_COLUMNS**

```sql
SELECT *
FROM USER_INDEXES
NATURAL JOIN USER_IND_COLUMNS;
```

```sql
SELECT ic.index_name
      ,ic.column_name
      ,ic.column_position col_pos
      ,ix.uniqueness
FROM USER_INDEXES ix
    ,USER_IND_COLUMNS ic
WHERE ic.index_name = ix.index_name
  AND ic.table_name = 'employees';
```

## 删除索引 DROP INDEX

1. 不可回滚
2. 只有索引的拥有者或拥有DROP ANY INDEX权限的用户才可以删除

```sql
DROP INDEX emp_last_name_index;
```

## 修改索引

### 重命名索引

```sql
ALTER INDEX 旧索引名
RENAME TO 新索引名;
```

### 合并索引、重新构造索引

- 索引建好后，经过很长一段时间的使用，索引表中存储的空间会产生一些碎片，导致索引的查询效率会有所下降，这个时候可以合并索引
- 原理是按照索引规则重新分类存储一下，或者也可以选择删除索引重新构造索引。

```sql
--合并索引
ALTER INDEX 索引名 COALESCE;

--重新构造
ALTER INDEX 索引名 REBUILD;
```

# 约束 CONSTRAINT

约束的作用是定义规则（最重要），确保完整性。
注意:

1. 若不指定约束名，ORACLE自动按SYS_Cn格式指定约束名
2. 创建约束在建表同时，修改约束在建表之后
3. 在表级定义约束-表约束，列级定义约束-列约束
4. 通过数据字典或视图查询约束


## 约束类型

### 非空约束 NOT NULL

- 只能用于列COLUMN
- 非空约束是没有名字的  

**语法**

```sql
CREATE TABLE 表名
( 
 列名 数据类型 NOT NULL
,列名 数据类型
);
```

### 唯一约束 UNIQUE (允许出现多个空值)

- 可作为超码
- 命名标准:uk_或_uk

```sql
CREATE TABLE 表名
(
 列1 数据类型 [CONSTRAINT 约束名] UNIQUE
,列2 数据类型
);
```

### 主键约束 PRIMARY KEY (column)(非空且唯一)

- 一张表只能设计一个主键约束
- 主键约束可以由多个字段构成，称为联合主键或者复合主键。
- 命名标准:pk_或_pk

**列级**

```sql
CREATE TABLE 表名
(
 列1 数据类型 [CONSTRAINT 约束名] PRIMARY KEY
,列2 数据类型
);
```

**表级**

```sql
CREATE TABLE 表名
(
 列1 数据类型 
,列2 数据类型
,列3 数据类型
,[CONSTRAINT 约束名] PRIMARY KEY(列1[,列2,...])
);
```

### 外键约束 FOREIGN KEY

- 作为外键的列，其对应的关联表的列必须是主键。
- 命名标准:fk_或_fk

**列级**

```sql
CREATE TABLE 表名
(
 列1 数据类型 [CONSTRAINT 约束名] [DATATYPE] REFERENCES 要关联的表(列,...)
 -- DATATYPE 仅数据类型关联
 [ON DELETE|UPDATE CASCADE]  --级联删除/更新
 [ON DELETE|UPDATE SET NULL] --置空
,列2 数据类型
);
```

**表级**

```sql
CREATE TABLE 表名
(
 列1 数据类型 
,列2 数据类型
,[CONSTRAINT 约束名] FOREIGN KEY(列1[,列2,...])
 REFERENCES 要关联的表(列,...)
 [ON DELETE CASCADE]  --级联删除
 [ON DELETE SET NULL] --级联置空
);
```

### 检查约束 CHECK(条件)

- 命名标准:_c

**列级**

```sql
CREATE TABLE 表名
(
 列1 数据类型 [CONSTRAINT 约束名] CHECK (条件表达式)
,列2 数据类型
,列3 数据类型
);
```

**表级**

```sql
CREATE TABLE 表名
(
 列1 数据类型 
,列2 数据类型
,列3 数据类型
,[CONSTRAINT 约束名] CHECK (条件表达式)
);
```

## 表级与列级约束

1. 列约束 在列后面声明
2. 表约束 末处声明CONSTRAINT，在末尾添加约束

-

1. 列约束只作用在一个列上，
   表约束可作用在多个列上
2. 列约束必须跟在列后面，
   表约束不与列一起，而是单独定义
3. NOT NULL 非空约束只能定义在列上

```sql
CREATE TABLE table1(
                    no1 NUMBER(10) NOT NULL,  --非空约束
                    no2 NUMBER(10,2) UNIQUE,   --唯一性约束
                    no3 NUMBER(20) CHECK(0 < no3 <= 99),  --CHECK约束
                    no4 VARCHAR2(25) DATATYPE REFERENCES table2(no4),  --外键table2为主表，table1为从表，也叫主从表。
                                                                         主表当中的字段必须是主表中的主键字段，主从表的字段要设置成同一个数据类型。
                                                                         在向设置了外键约束的表输入值的时候，从表中外键字段的值必须来自主表中的相应字段的值，或者为NULL值。
CONSTRAINT table1_no1_c CHECK(1 < no1 < 9), --CHECK约束
CONSTRAINT table1_no1_pk PRIMARY KEY (no1),  --主键（非空且唯一）
CONSTRAINT table1_no3_fk FOREIGN KEY (no3) REFERENCES table2(no1),   --外键
CONSTRAINT table1_no4_fk FOREIGN KEY (no4) REFERENCES table2(no4) ON DELETE CASCADE, --外键，级联删除
CONSTRAINT table1_no5_fk FOREIGN KEY (no5) REFERENCES table2(no5) ON DELETE SET NULL  --外键，级联置空
                    );
```

## 修改表约束

1. 约束可以添加或删除，但不可以修改
2. 有效或无效化约束
3. 添加NOT NULL约束要用MODIFY关键字

### 一、删除 DROP

```sql
ALTER TABLE 表
DROP CONSTRAINT 约束名;
```

```sql
ALTER TABLE 表
DROP PRIMARY KEY 约束名 [CASCADE];   
--CASCADE 删除主键约束的同时，（如果存在）以该主键（作为关联表的外键）建立的外键约束也一起删除。
```

### 二、修改 MODIFY 在修改之前表里面不要有任何数据

```sql
ALTER TABLE 表
MODIFY (列名 数据类型 约束);
```

### 三、改名

```sql
ALTER TABLE 表
RENAME CONSTRAINT 旧的约束名 TO 新的约束名;
```

### 四、添加 ADD 添加的是表约束

```sql
ALTER TABLE 表
ADD 约束;
```

```sql;
ALTER TABLE table1
ADD CONSTRAINT table_fk FORREIGN KEY (no2) REFERENCES table2(no2);
```

### 五、无效化 DISABLE COSTRAINT

```sql
ALTER TABLE 表
DISABLE CONSTRAINT 约束名;
```

### 激活 ENABLE CONSTRAINT

```sql
ALTER TABLE 表
ENABLE CONSTRAINT 约束名;
```

- 当定义或激活UNIQUE或PRIMARY KEY约束时，系统自动创建UNIQUE或PRIMARY KEY索引

## 查询约束

**数据字典:USER_CONSTRAINTS, USER_CONS_COLUMNS**

```sql
SELECT constraint_name,constraint_type,search_condition,status
FROM USER_CONSTRAINTS
WHERE table_name = 'table1';
```

```sql
SELECT constraint_name,column_name
FROM USER_CONS_COLUMNS
WHERE table_name = 'table1';
```

# SYNONYM 同义词

- 同义词：任何表、视图、物化视图、序列、存储过程、函数、包、类型、JAVA类对象、用户定义类型、其他同义词的别名。除了在数据字典中的定义外其不占任何空间。
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

| 权限    | 说明                                                 |
| ------- | ---------------------------------------------------- |
| public  | PUBLIC组，每个用户都可以访问。                       |
| private | 默认。对象所有者，只有其显式授权后其他用户才可访问。 |

## 创建同义词

```sql
create [public] synonym 同义词 for 对象;
```

```sql
create synonym synonym_test for emp;
```

## 删除同义词

```sql
drop [public] synonym 同义词;
```

- 删除public同义词，需要指定public关键字。

## 同义词数据字典

| 数据字典       | 说明                                                         |
| -------------- | ------------------------------------------------------------ |
| user_sysnonyms | 当前用户所拥有的同义词<br />YNONYM_NAME NOT NULL VARCHAR2(30) 同义词的名称<br />TABLE_OWNER VARCHAR2(30) 所指向的对象属主<br />TABLE_NAME NOT NULL VARCHAR2(30) 所指向的对象名称<br />DB_LINK VARCHAR2(128) 数据库链接 |
| all_synonyms   | 前用户所能使用的所有同义词                                   |
| dba_synonyms   | 数据库中所有的同义词，包括每个用户创建的私有同义词和DBA创建的公共同义词<br />只有DBA能够访问，除了包含数据字典user_synonyms的所有列外，还有一个列owner代表同义词的创建者。 |

```sql
--查询当前用户创建了哪些同义词，它们各代表哪个用户的哪个对象
SELECT synonym_name
      ,table_name
      ,table_owner 
FROM USER_SYNONYMS; 
```
