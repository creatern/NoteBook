# SQL`*`PLUS

## 在SQL*PLUS中设置格式

### sqlplus的报表功能

```
ttitle col 15 '我的报表' col 35 sql.pno    --15表示空15列，sql.pno表示报表页码
col deptno heading 部门号   --设置别名
col job heading 职位
col sum(sal) heading 工资总额
break on deptno skip 1  --deptno只显示一次，部门间间隔一行
```

***


### 1.COL username HEADING 用户名

```
COL username HEADING 用户名;   --执行成功的话不会有回显
    --COLUMN可以简写成COL，设置新的字段名（别名），使用SELECT语句来查询的时候就可以看到变化了，但使用DESC看结构还依然不变化。
```

### 2.设置结果显示的格式

```
COLUMN column_name FORMAT dataformat;
```

  注意：

  字符类型只能设置它的长度。 --字符格式用a开头，后面跟它要的长度

  如果是数值类型用，9表示一位数字，可以保留4位数和一位小数

  但如果数据中有四位的数，超过这个长度的就用#####表示了，与excel一致。

  如果使用col salary format $9999.9;则数字前面加了美元符号。

### 3. CLEAR 清除之前设置过的格式

```
COLUMN column_name CLEAR;
```
```
CLEAR columns

CLEAR breaks

CLEAR computes
```

***

## 报表构建

```
CREATE TABLE BOOKSHELF_CHECKOUT
(
 NAME VARCHAR2(25)
,TITLE VARCHAR2(25)
,CHECKOUTDATE DATE
,RETURNEDDATE DATE
)
```

@activity.sql

```
rem Bookshelf activity report

set headshep !

ttitle 'Checkout Log for 1/1/02-3/31/02'
btitle 'from the Bookshelf'

column Name format a20
column Title format a20 word_wrapped
column DaysOut format 999.99

column DaysOut heading 'Days!Out'

break on Name skip 1 on report
compute avg of DaysOut on Name
compute avg of DaysOut on report

set linesize 80
set pagesize 60
set newpage 0
set feedback off

spool activity.1st

SELECT Name
      ,Title
      ,CheckoutDate
      ,ReturnedDate
      ,ReturnedDate-CheckoutDate as DaysOut /*Count Days*/
FROM BOOKSHELF_CHECKOUT
ORDER BY Name
        ,CheckoutDate;

spool off
```

### REM 说明文字 注释


### ttitle btitle 标题
```
ttitle '开头'
btitle '结尾' 
```
每页标题 ttitle置顶 btitle置底

### COLUMN column_name FORMAT 宽度
```
COLUMN column_name FORMAT 宽度
```
- 设置该列的显示宽度

```
column Name format a20
``` 
- a表示字母列 
- 20表示宽度

```
column Title format a20 word_wrapped
```
- word_wrappeed 任何超过指定格式长度(`a20`个字符)的都将换到下一行
- truncated 将不显示超过该列指定格式长度的任意字符

```
column DaysOut heading 'Days!Out'
```
- 999.99 指定数字列长度 
    - 字符长度加上负号占7位 ，小数点也占一位

### COLUMN column_name HEADING '  '
```

```

### BREAK ON

```
BREAK ON column_name SKIP 1 ON REPORT
```
设置列中的名重复

#### COMPUTE AVG

```
break on Name skip 1 on report
compute avg of DaysOut on Name
compute avg of DaysOut on report
```

- 总是与BREAK ON语句一起使用
- 根据BREAK ON部分计算平均值

## 简单命令

### LIST 列出缓冲区命令 L

### n语句 （n 行号）
#### n 设定当前行

#### n 文本 替换第n行的语句

### CHANGE 修改 C

```
C /old/new
```
### DEL
- 不同于DELETE

```
DEL n  删除第n行语句
DEL m n 删除第m到n行语句
```
### APPEND 追加 A

### SAVE 保存
- 将缓冲区的语句存入脚本文件中
- 需要先存在路径
- SQL*PLUS的命令不会被放在缓冲区
- 缓冲区中只能存储一个SQL语句

```
SAVE 文件名.sql [ |APP|REP]

默认 保存
APP 追加在已有文件
REP 替换已有文件
```

### STORE 存储
- 将当前的`SQL*PLUS`环境设置保存到文件中

```
STORE SET 文件名.sql {CREATE|REPLACE}

CREATE 创建
REPLACE 替代
```

### EDIT 编辑

#### DEFIND_EDITOR = " "

### HOST 

### START 启动 @
```
@路径文件.sql
START 路径文件.sql
```

### SHOW 查看

```
SHOW headsep

SHOW linesize

SHOW pagesize

SHOW newpage
```

```
START 文件名.sql
```

### HELP 命令名


### 其他一些实用命令

#### 可以使用HOST CLS来清屏

#### 查看用户SHOW USER

#### 使用上下箭头可以选择历史输入记录来使用


### PRINT 打印绑定变量

```
PRINT --打印出所有绑定变量
PRINT 绑定变量名 --打印指定的绑定变量
```
- 自动打印

```
SET AUTOPRINT ON
```

## SET运算符

### Oracle 中 SET 命令

```
SET timing ON; //设置显示“已用时间：XXXX” 统计每条语句执行所花费时间
SET time ON //设置显示当前时间 系统时间

SET pagesize 10 //设置每一页的行数

SET heading ON //设置显示列名

SET autocommit ON //设置当前sessiON对修改的数据进行自动提交
SET autocommit OFF //设置当前sessiON不对修改的数据进行自动提交

SET autotrace ON //设置允许对执行的sql进行分析

SET trimout ON //去除标准输出每行的拖尾空格，缺省为OFF
SET trimSPOOL ON //去除重定向（SPOOL）输出每行的拖尾空格，缺省为OFF

SET echo {ON|OFF}  //在用start命令执行一个sql脚本时，是否显示脚本中正在执行的SQL语句
SET echo OFF //显示start启动的脚本中的每个sql命令，缺省为ON

SET feedback ON //设置显示“已选择XX行”
SET feedback OFF //回显本次sql命令处理的记录条数，缺省为ON

SET colsep’ ’ //输出分隔符

SET heading OFF //输出域标题，缺省为ON

SET pagesize 0 //输出每页行数，缺省为24,为了避免分页，可设定为0。

SET linesize 80 //输出一行字符个数，缺省为80

SET numwidth 12 //输出number类型域长度，缺省为10

SET termout OFF //显示脚本中的命令的执行结果，缺省为ON

SET serveroutput ON //设置允许显示输出类似dbms_output

SET verify OFF //可以关闭和打开提示确认信息old 1和new 1的显示.

SET NULL text   // 显示时，用text代替null值

SET headshep 符号 //设置分隔符(分行) 默认 |

SET PAUSE ' '  //设置停顿显示的语句
SET PAUSE {ON|OFF} //设置停顿;按回车继续
```

### SET AUTOTRACE ON 追踪sql语句 优化
- 需要权限 
```
GRANT select any dictionary TO user_name; 
```

1. SET AUTOTRACE参数

```
SET AUTOTRACE OFF -------------不生成AUTOTRACE 报告，这是缺省模式
```
```
SET AUTOTRACE ON
```
![](vx_images/273875614220968.png =252x)

```
SET AUTOTRACE ON EXPLAIN ----- AUTOTRACE只显示优化器执行路径报告 
```
![](vx_images/207885714239394.png =252x)

```
SET AUTOTRACE ON STATISTICS -- 只显示执行统计信息
```

![](vx_images/75845814227261.png =190x)

```
SET AUTOTRACE TRACEONLY ------ 同set autotrace on，但是不显示查询输出
```

![](vx_images/389315814247427.png =253x)

```
SET AUTOTRACE TRACEONLY EXPLAIN
```
![](vx_images/139415914240096.png =254x)

```
SET AUTOTRACE TRACEONLY STATISTICS
```

![](vx_images/387345914236651.png =194x)

2. 只能在sql plus下执行



## 利用Oracle中的SPOOL缓冲池技术可以实现Oracle数据导出到文本文件

SPOOL是SQLPLUS的命令，不是SQL语法里面的东西。
对于SPOOL数据的SQL，最好要自己定义格式，以方便程序直接导入,SQL语句如：

```
SELECT taskindex||'|'||commONindex||'|'||tasktype||'|'||to_number(to_char(sysdate,'YYYYMMDD')) 
FROM ssrv_sendsms_task; SPOOL常用的设置
```

```
SET colsep' ';　　　 //域输出分隔符
SET echo OFF;　　　　//显示start启动的脚本中的每个sql命令，缺省为ON
SET feedback OFF;　　//回显本次sql命令处理的记录条数，缺省为ON
SET heading OFF;　　 //输出域标题，缺省为ON
SET pagesize 0;　　//输出每页行数，缺省为24,为了避免分页，可设定为0。
SET termout OFF;　　 //显示脚本中的命令的执行结果，缺省为ON
SET trimout ON;　　　//去除标准输出每行的拖尾空格，缺省为OFF
SET trimSPOOL ON;　　//去除重定向（SPOOL）输出每行的拖尾空格，缺省为OFF

如果trimSPOOL设置为ON，将移除SPOOL文件中的尾部空 ，trimout同trimSPOOL功能相似，
只不过对象是控制台。使用glogin.sql或login.sql使得设置跨sessiON生效。
```

### 导出文本数据的建议格式

SQL*PLUS环境设置

```
SET NEWPAGE NONE
SET HEADING OFF
SET SPACE 0
SET PAGESIZE 0
SET TRIMOUT ON
SET TRIMSPOOL ON
SET LINESIZE 2500

注：
LINESIZE要稍微设置大些，免得数据被截断，它应和相应的TRIMSPOOL结合使用防止导出的文本有太多的尾部空格。
但是如果LINESIZE设置太大，会大大降低导出的速度，另外在WINDOWS下导出最好不要用PLSQL导出，速度比较慢，
直接用COMMEND下的SQLPLUS命令最小化窗口执行。对于字段内包含很多回车换行符的应该给与过滤，形成比较规矩的文本文件。
```

通常情况下，我们使用SPOOL方法，将数据库中的表导出为文本文件的时候会采用两种方法，如下述：

#### 方法一：采用以下格式脚本

```
SET colsep '|' --设置|为列分隔符 　　
SET trimSPOOL ON 　　
SET linesize 120 　　
SET pagesize 2000 　　
SET newpage 1 　　
SET heading OFF 　
SET term OFF
SET num 18
SET feedback OFF
 　　
SPOOL 路径+文件名 　　
SELECT * FROM tablename; 　　
SPOOL OFF
```

#### 方法二：采用以下脚本

```
SET trimSPOOL ON 　　
SET linesize 120 　　
SET pagesize 2000 　　
SET newpage 1 　　
SET heading OFF 　　
SET term OFF 　
　
SPOOL 路径+文件名 　　
SELECT col1||','||col2||','||col3||','||col4||'..' FROM tablename; 　　
SPOOL OFF
```

比较以上方法，
即方法一采用设定分隔符然后由sqlplus自己使用设定的分隔符对字段进行分割，方法二将分隔符拼接在SELECT语句中，即手工控制输出格式。在实践中，发现通过方法一导出来的数据具有很大的不确定性，这种方法导出来的数据再由sqlldr导入的时候出错的可能性在95%以上，尤其对大批量的数据表，如100万条记录的表更是如此，而且导出的数据文件大。而方法二导出的数据文件格式很规整，数据文件的大小可能是方法一的1/4左右。经这种方法导出来的数据文件再由sqlldr导入时，出错的可能性很小，基本都可以导入成功。因此，实践中我建议大家使用方法二手工去控制SPOOL文件的格式，这样可以减小出错的可能性，避免走很多弯路。

```
自测例：将ssrv_sendsms_task表中的数据导出到文本(数据库Oracle 9i  操作系统 SUSE LINUX Enterprise Server 9)
SPOOL_test.sh脚本如下： 
#!/bin/sh 
DB_USER=zxdbm_ismp                               #DB USER 
DB_PWD=zxin_smap                                 #DB PASSWORD 
DB_SERV=zx10_40_43_133                           #DB SERVICE NAME
sqlplus -s $DB_USER/$DB_PWD@$DB_SERV<<EOF # -s 参数屏蔽打印到屏幕上的其他信息，只显示sql执行后从DB中查询出来的信息，过滤掉SPOOL函数执行时在文件中写入的其他信息。
SET trimSPOOL ON 
SET linesize 120 
SET pagesize 2000 
SET newpage 1 
SET heading OFF 
SET term OFF 
SPOOL promt.txt 
SELECT taskindex||'|'||commONindex||'|'||tasktype||'|'||to_number(to_char(sysdate,'YYYYMMDD')) FROM ssrv_sendsms_task;
SPOOL OFF 
EOF
执行./SPOOL_test.sh后生成sp_test.txt，内容如下： 
83|115|1|20080307 
85|115|11|20080307 
86|115|10|20080307 
84|115|2|20080307 
6|5|14|20080307 
7|5|12|20080307 
9|5|15|20080307
注：上面自测例中，SPOOL promt.txt中的目标生成文件promt.txt,在HP-UNX环境下的shell脚本中调用Oracle的SPOOL函数，如果将上述逻辑代码封装为一个functiON，然后来调用这个functiON的话，则在shell脚本中最终是不会生成promt.txt文件的。只能直接执行逻辑代码，封装后则SPOOL函数失效。
对于promt.txt在相对路径下，下面2中方法在shell环境中执行时，两者只能择一，两者并存则SPOOL函数会失效。假设promt.txt文件生成的路径为：/home/zxin10/zhuo/batchoperate/SPOOLfile
```

```
方式[1] 
echo "start SPOOL in shell.."
sqlplus -s zxdbm_ismp/zxin_smap<<EOF 
SET pagesize 0 
SET echo OFF feed OFF term OFF heading OFF trims OFF 
SET colsep '|' 
SET trimSPOOL ON 
SET linesize 10000 
SET trimSPOOL ON 
SET linesize 120 
SET newpage 1 
SPOOL /home/zxin10/zhuo/batchoperate/SPOOLfile/promt.txt 
SELECT batchindex||'|'||productid||'|'||cONtentid||'|'||optype||'|'||uploadfile FROM zxdbm_700.s700_batch_operatiON WHERE status=1;
SPOOL OFF 
EOF 
echo "end.." 
```

```
方式[2] 
echo "start SPOOL in shell.." 
cd /home/zxin10/zhuo/batchoperate/SPOOLfile 
sqlplus -s zxdbm_ismp/zxin_smap<<EOF 
SET pagesize 0 
SET echo OFF feed OFF term OFF heading OFF trims OFF 
SET colsep '|' 
SET trimSPOOL ON 
SET linesize 10000 
SET trimSPOOL ON 
SET linesize 120 
SET newpage 1 
SPOOL promt.txt 
SELECT batchindex||'|'||productid||'|'||cONtentid||'|'||optype||'|'||uploadfile FROM zxdbm_700.s700_batch_operatiON WHERE status=1;
SPOOL OFF 
EOF
```

## login.sql文件

## glogin.sql文件

# SQL

## 脚本使用

```
@路径名

@Z:\VMshare\sql\del_data1.sql;
```

## 引号

### 单引号出现的地方如下

1. 字符串，例如：'hello'
2. 日期型，例如：'17-12月-80'
3. to_char/to_date(日期,'YYYY-MM-DD HH24:MI:SS')

### 双引号出现的地方如下

1. 列别名，例如：select ename "姓 名" from emp
2. to_char/to_date(日期,'YYYY"年"MM"月"DD"日" HH24:MI:SS')''号中的英文字符大小写不敏感

***

## DML DDL DCL 事务

### DML（data manipulation language）数据操纵语言

 就是我们最经常用到的 SELECT、UPDATE、INSERT、DELETE。
 主要用来对数据库的数据进行一些操作。

- INSERT 插入
- UPDATE 更新
- DELETE 删除
- SELECT 查询

### DDL（data definition language）数据库定义语言

 就是我们在创建表的时候用到的一些SQL，如：CREATE、ALTER、DROP等。
 DDL主要是用在定义或改变表的结构，数据类型，表之间的链接和约束等初始化工作上。

- CREATE 创建
- ALTER 修改
- DROP  删除

### DCL 数据库定义语言

- GRANT 授予访问权限
- REVOKE 撤销访问权限
- COMMIT 提交事务
- ROLLBACK 回滚
- SAVEPOINT 保存点
- LOCK 锁定

### Oracle事务 一组操作单元，使数据从一种状态变换到另一种状态

 由以下部分组成：

- 一个或多个DML语句
- 一个DDL语句
- 一个DCL语句
 以DML语句的执行开始
 以以下之一结束:
- COMMIT 或 ROLLBACK
- DDL(自动提交)
- 用户会话正常结束
- 系统异常终止

#### COMMIT,ROLLBACK优点

1. 数据完整性
2. 数据改变被提交之前预览
3. 将逻辑上的操作分组

#### 一般事务（DML）即数据修改（增、删、改）的事务

 事务会将所有在事务中被修改的数据行加上锁（行级锁），来阻止其它人（会话）同时对这些数据的修改操作。
 当事务被提交或回滚后，这些数据才会被释放锁。

#### 事务进程

##### 提交或回滚前的数据状态

 1. 改变前的数据状态是可以改变的
 2. 执行DML操作的用户可以通过SELECT语句查询之前的修正
 3. 其他用户不能看到当前用户所做的改变，直到当前用户结束事务
 4. DML语句所涉及的行被锁定，其他用户不能操作

```
当A操作一条数据N1后，暂未提交事务 ，此时B又上来操作同一条数据N1，这时的情况是：
 3) 所有除A以外的人看不到被A所修改后的数据
    当A提交事务后，所有人可以看到被A修改后的数据，看不到B修改后的数据。
    但B能看到自己修改后的数据（与A一样，因为B还未提交事务）。当B提交事务后，所有人可以看到被B修改后的数据。
 4) B会处于等待状态，直到A提交或回滚了针对这条数据的修改（这也就是行级锁的概念）
```

##### 提交后的数据状态

 1. 数据的改变保存到数据库中
 2. 改变前的数据已经丢失
 3. 所有用户可以看到结果
 4. 所有保存点被释放

##### 回滚后 ROLLBACK

 1. 数据改变被取消
 2. 修改前的数据状态被恢复
 3. 锁被释放
在PL/SQL和SQL*PLUS中，对数据修改完后，如果用户未提交事务，但关闭或断开了PL/SQL，此时ORACLE会立即提交此会话的事务。

#### 提交

 提交的类型：

##### 1)显式提交：用COMMIT命令直接完成的提交为显式提交

其格式为：SQL>COMMIT；

##### 2)隐式提交：用SQL命令间接完成的提交为隐式提交

ALTER，AUDIT，COMMENT，CONNECT，CREATE，DISCONNECT，DROP，EXIT，GRANT，NOAUDIT，QUIT，REVOKE，RENAME。
(此隐式提交是在自己的session，如果在其他人的session(如用户a)中正在修改相同的数据，则引起隐式提交的语句（用户a的k另一个session)则必须等待)
隐式提交(自动提交) 即无需显示执行commit语句，session中的操作被自动提交到数据库的过程。

###### 隐式提交的方式

1. 正常执行完DDL语句。包括CREATE，ALTER，DROP，TRUNCATE，RENAME。
2. 正常执行完DCL语句。包括GRANT，REVOKE。
3. 正常退出sql*plus，没有明确发出COMMIT或ROLLBACK。

###### 隐式提交的注意事项

1. 执行DDL语句时，前面的DML操作也会被提交到数据库中
2. 即使DDL语句执行失败，前面的DML操作也会被提交到数据库中
3. 在前面1和2的基础上总结

为了避免隐式提交或者回滚，尽量保证一条或者几条DML操作完成后有显示的提交或者回滚，防止后续执行的DCL或者DDL自动提交前期的DML操作。

###### 为了保证事务的一致性

 事务读一致性
COMMIT;
提交之前的数据，在其他用户那里是看不见的
在执行DDL语句的时候，Oracle需要在它的系统表中进行元数据的记录操作（即：除了建表还会进行不少INSERT操作），如果它不隐式提交就无法保证一致性；
从内部运行机制来看DDL语句和dml语句还是有很大区别的，

- DML会对每个语句的每条记录都做日志记录以便于回滚，
- 而DDL往往没必要搞这么复杂，从功能和易用性上看隐式提交都是最好的选择。

##### 3)自动提交：若把AUTOCOMMIT设置为ON，则在INSERT ALTER DROP语句执行后，系统将自动进行提交，这就是自动提交

其格式为：SQL>SET AUTOCOMMIT ON；

```
查看当前是否是自动提交：SHOW AUTOCOMMIT；
```

```
回滚到保存点 SAVEPOINT A; --在当前事务创保存点，DML语句新开一个事务，保存点须重新创
            ROLLBACK TO SAVEPOINT A; --回滚到保存点
```

## 基本查询

### SELECT  查询基本语句

```sql
SELCET column
FROM table;
```

- 注 查询他人的表需要注明如scott.employees
- 否则 ORA-00942: 表或视图不存在

#### 查询表中的所有字段

```sql
SELECT *
FROM employees
;
```

#### 查询指定字段

```sql
SELECT last_name
      ,emploee_id
FROM employees
;
```

### AS 给字段设置别名 

- 不会更改字段的名字，可以为多个字段设置别名 还可以是特殊符号，中文等

```sql
SELECT last_name AS name
      ,employee_id emploee_id  --AS可以省略，但是最好加上
FROM employees
;
```

### || 连接符
 
- 函数 WM_CONNECT()  和  CONCAT() 作用相同

```sql
dbms_output.put_line('hello'||','||'world');
```

```sql
SELECT first_name||''||last_name
FROM employees
;
```

```sql
SELECT CONCAT('C','D')
FROM dual;
```

### DISTINCT 查看唯一值 

```sql
SELECT DISTINCT username AS 用户名
FROM users;
```

### 运算符和表达式

- Oracle中的操作数可以有变量、常量、字段。

#### 运算符

#### 1)算术运算符 +  - * /

#### 2)比较运算符 > >= < <= = <> !=

- 都是用在WHERE条件里面的，两个数进行比较得到的结果是布尔类型的，
   - 比较运算符优先级高于逻辑运算符

#### 3)逻辑运算符 AND OR NOT

### WHERE 过滤 带条件的查询 

- WHERE子句必须紧跟FROM子句，而HAVING不必

```sql
SELECT last_name
      ,employee_id
      ,salary
FROM employees
WHERE salary > 1000
 AND department_id IN (10,20,30);
```

#### IN 等值列表中一个

#### IS NULL 空值

#### 模糊查询 LIKE

**通配符**
 
- _ 代表一个字符
-  % 代表任意个字符
- \ 转义符

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

#### 范围查询 BEWTEEN……AND……

- 表示在之间，即<=和>=

- NOT BETWEEN……AND……  不在……之间

```sql
SELECT last_name
      ,employee_id
      ,salary
FROM employees
WHERE salary BETWEEN 1000 AND 7000;
```

### ORDER BY 对查询结果排序 

- ASC 升序 从小到大排序 默认
- DESC 降序 从大到小排序

```sql
SELECT employee_id
      ,last_name
FROM employees
ORDER BY employee_id DESC;
```

#### 按多个列排

```sql
SELECT emlpoyee_id
      ,last_name
FROM employees
ORDER BY employee_id,last_name;
--先对employee_id排列，再在employee_id排列的基础上对last_name排列
```

#### 按别名排列

```sql
SELECT employee_id
      ,last_name
      ,salary*1.1 sal --别名
FROM employees
ORDER BY sal;
```

#### rownum 伪列 Top~n分析

- Oracle不支持SELECT TOP语句;而是使用ORDER BY来进行TOP~n
- TOP~n分析 查询一个列中最大/最小的n个值的集合
- **rownum行号 是一个伪列**，表上没有这一列，当做一些特殊操作的时候，Oracle自动加上。

**注意**

1. 行号永远按照默认的顺序生成；  
2. **行号只能使用<,<=**，不能使用=,>,>=的符号。
3. **对伪列ROWNUM起别名可以使用=,>,>=**

```sql
SELECT rownum,column1,column2
FROM (
      SELECT column1,column2
      FROM table1
      ORDER BY column1
      )
WHERE rownum <= 10; 
--/ FETCH FIRST 10 ROWS ONLY; 12c以上才可以使用FETCH语句
```

```sql
--对伪列ROWNUM起别名可以使用=,>,>=
SELECT rn,column1,column2
FROM (
      SELECT rownum rn,column1,column2
      FROM (
           SELECT column1,column2
           FROM table1
           ORDER BY salary DESC
            )
      )
WHERE rownum >= 10;
```

### CASE……WHEN语句

### DECODE函数

```sql
--查询部门为10，20，30的员工信息，
--若部门号为10，则工资*1.1，若部门号为20，则工资*1.2，若部门号为30，则工资*1.3

1)CASE 无逗号
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

2)DECODE 是专属oracle的语法 有逗号','
  DECODE(字段,条件1,表达式1,条件2,表达式2,…表达式n)
  
SELECT employee_id
      ,last_name
      ,DECODE(department_id,10,salary * 1.1
                           ,20,salary * 1.2
                           ,30,salary * 1.3
              ) new_sal
FROM employees
WHERE department_id IN (10,20,30);
```

## 表

表是基本存储单位，要把数据都存在表中.  
-Oracle中的表都是二维结构。  

- 一行也可以叫做记录，一列也可以叫做域或者字段。  
- 约定：要求每一列需要有相同的数据类型。  
  - 列名要是唯一的。  
  - 每一行的数据是唯一的。  

### 表中的数据类型  

#### 1)字符型

- 固定长度类型：
  - CHAR(n)（max2000）
  - nchar(n)（unicode格式，max1000，多数用来存储汉字）
- 可变长度类型：
  - VARCHAR2(n)（max4000）
  - nvarchar2(n)（unicode格式，max2000）

#### 2)数值型

- NUMBER(p,s) p为有效数字，s为小数点后面的位数，s可正可负
- FLOAT(n) 用来存储二进制数据，二进制数据的1-126位，一般使用NUMBER
- INT 整数
- INTEGER

#### 3)日期型

- DATE 范围为公元前4712年1月1日到公元9999年12月31日，可以精确到秒
- TIMESTAMP 可以精确到小数秒，一般用DATE类型

#### 4)其他类型

- LONG 可变长字符数据，最大2G
- RAW (LONG RAW) 原始的二进制
- BLOB 可存4G数据以二进制存放
- CLOB 可存4G数据以字符串存放
- BFILB 存储外部文件的二进制数据，最大4G

### CREATE TABLE 创建表 

**需要：**

1. 权限
2. 存储空间
3. 命名规则:
   - 1) 以字母开头
   - 2) 在1-30个字符之间
   - 3) 只能包含A ~ Z,a ~ z，0~9，_，$，#
   - 4) 不能和其他对象重名
   - 5) 不能是关键字

```sql
CREATE TABLE emp (
 id NUMBER(6,2) --6位有效数字，包含2位小数位即:9999.99
,salary NUMBER(10) --10位有效数字
,name VARCHAR2(25) --25长度
,hire_date DATE
);
```

### AS 复制表结构 

```sql
CREATE TABLE emp
AS
SELECT *
FROM employees
WHERE 1 = 2;  --只复制结构，不复制内容
--WHERE 1 = 1;  --既复制结构也 复制内容
```

### ALTER TABLE 修改表的结构

#### ADD 添加字段(列) 

```sql
ALTER TABLE emp
ADD job VARCHAR2(30);
```

#### MODIFY 更改字段数据类型   

- (可以为新追加的列定义默认值DEFAULT)

```sql
ALTER TABLE emp
MODIFY id NUMBER(10) [DEFAULT 100];
```

#### DROP COLUMN 删除字段 

```sql
ALTER TABLE emp
DROP COLUMN job;
```

#### RENAME COLUMN 修改字段名 

```sql
ALTER TABLE emp
RENAME COLUMN id TO emp_id;
```

### RENAEM TABLE 修改表名 

```sql
RENAME TABLE emp TO emp1;
```

### DROP 删除表

**删除表,数据和结构都删掉**

```sql
DROP TABLE emp;
```

- 并未真正删除表，而是把表放在回收站 RECYCLEBIN

**删除表的同时，删除约束**

```sql
DROP TABLE emp CASCADE CONSTRAINTS;
```

**一次性删除  **

```sql
DROP TABLE emp PURGE;
```

#### RECYCLEBIN 回收站

当删除一个表时，Oracle并没有真正删除该表，而是将该表重命名之后放在回收站

##### SHOW 查看回收站

```sql
SHOW RECYCLEBIN
```

##### PURGE 清空回收站中指定的表

```sql
PURGE TABLE "recyclebin_name"; --SHOW RECYCLEBIN; 获得RECYCLEBIN NAME
```

```sql
PURGE RECYCLEBIN;
```

##### FLASHBACK 捡回

```sql
--捡回
FLASHBACK TABLE emp TO BEFORE DROP;

--捡回并重命名
FLASH TABLE emp TO BEFORE DROP
RENAME TO employees;

DESC --查看是否捡回
```


### 操作表中的数据

```
SQL> DESC emp
Name      Type         Nullable Default Comments
--------- ------------ -------- ------- --------
ID        NUMBER(6,2)  Y
SALARY    NUMBER(10)   Y
NAME      VARCHAR2(25) Y
HIRE_DATE DATE         Y
```

#### INSERT 添加数据

```sql
INSERT INTO table1
VALUES (values,...)
```  

- 一次只能向表中插入一条数据
1. 为每一列添加一个新值
2. 按默认顺序列出每个列的值
3. 在INSERT子句中随意列出列名和值
4. 字符和日期型数据要在单引号''中

##### 1) 按默认

```sql
INSERT INTO emp(id,salary,name,hire_date)
VALUES(1,1500,'张三',TO_DATE('2002-2-3','yyyy-mm-dd')); --转换日期输入
```

##### 2) 不列出列名

```sql
INSERT INTO emp
VALUES(2,3000,'李四',TO_DATE('2003-3-2','yyyy-mm-dd'));
```

##### 3) 随意列出列名

```sql
INSERT INTO emp(name,salary,hire_date,id)
VALUES(2500,'王五',TO_DATE('2004-4-5','yyyy-mm-dd'),3);
```

##### 4) 向表中插入空值  

- 隐式 忽略

```sql
INSERT INTO emp(id,name)
VALUES(4,'陈六');
```

- 显式 NULL (VALUES)

```sql
INSERT INTO emp
VALUES(5,NULL,'赵七'，NULL);
```

##### 5) &变量 窗口输入

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

##### 6) 函数CHR

- CHR() 函数表示返回指定 ASCII 码的字符，作用和 ASCII() 相反。
- ASCII()函数表示返回指定字符的ASCII码，作用和 CHR() 相反。

```sql
INSERT INTO mytable 
VALUES chr(38);
```

- ASCII

```sql
SELECT ASCII('x'), ASCII('y'),ASCII('z') 
FROM dual;
```

- CHR

```sql
chr（）函数，
chr(9);  tab
chr(10);  换行符
chr(13);  回车符
chr(32);  空格符
chr(34;，  双引号“"”
```

##### 7) SELECT 从其他表中拷贝数据

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

#### MERGE 合并数据

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

-需求是，从T1表更新数据到T2表中。假设T2表的NAME 在T1表中已存在，就将MONEY累加，假设不存在。将T1表的记录插入到T2表中。
- 在等价的情况下，一定需要至少两条语句，一条为UPDATE，一条为INSERT,并且语句中必需要与推断的逻辑，或者写在过程中，假设是单条语句，就要写全条件。
- 写在UPDATE和INSERT的语句中，显的比较麻烦并且容易出错。假设了解MERGE，我们能够不借助存储过程，直接用单条SQL便实现了该业务逻辑，且代码非常简洁。详细例如以下：

```sql
MERGE INTO T2
USING T1
ON (T1.NAME=T2.NAME)
WHEN MATCHED THEN
 UPDATE
 SET T2.MONEY=T1.MONEY+T2.MONEY
WHEN NOT MATCHED THEN
 INSERT
 VALUES (T1.NAME,T1.MONEY);
```
 

##### 灵活之处

###### 1. UPDATE和INSERT动作可仅仅出现其一(必须同一时候出现。)

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

###### 2. 可对MERGE语句加条件

```sql
MERGE INTO T2
USING T1
ON (T1.NAME=T2.NAME)
WHEN MATCHED THEN
 UPDATE
 SET T2.MONEY=T1.MONEY+T2.MONEY
 WHERE T1.NAME='A';
```

######  3. 可用DELETE子句清除行

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

###### 4. 可采用无条件方式Insert

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

##### 误区

###### 1. 不能更新ON子句引用的列

```sql
MERGE INTO T2
USING T1
ON (T1.NAME=T2.NAME)
WHEN MATCHED THEN
 UPDATE
 SET T2.NAME=T1.NAME;

ORA-38104: 无法更新 ON 子句中引用的列: "T2"."NAME"
```

###### 2.  DELETE子句的WHERE顺序必须最后

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

###### 3. DELETE 子句仅仅能够删除目标表。而无法删除源表

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

###### 4. 更新同一张表的数据,需操心USING的空值

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

###### 5. 必需要在源表中获得一组稳定的行

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

##### 例1

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

##### 例2：

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

##### 9)插入日期

```
INSERT INTO mytable
VALUES (sysdate)
;

INSERT INTO mytable
VALUES (TO_CHAR('2022-2-2','yyyy-mm-dd'))
;
```

##### 10)插入NULL 不等于空格

```
INSERT INTO mytable
VALUES(null," ")
;
```

#### UPDATE 更新数据 

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


#### DELETE 删除数据 

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

#### TRUNCATE 清空数据

- 不可回滚

```sql
TRUNCATE TABLE 表;
```

#### DEFAULT 设置某字段的默认值 

##### 1) 创建表时添加

```sql
CREATE TABLE emp(
 salary NUMBER(20) DEFAULT 1000
);
```

##### 2) 修改表时添加默认值 MODIFY

```sql2
ALTER TABLE emp
MODIFY salary NUMBER(10) DEFAULT 1000;
--如果在新加记录的时候不想要默认值了，
  则按正常的添加方式添加了就可以替换默认值了
```

### 查看表的属性

#### DESC

```sql
DESC tablename;
```

#### ALL_TAB_COLUMS 表的列属性

```sql
SELECT column_name,data_type,nullable,data_default
FROM ALL_TAB_COLUMNS
WHERE table_name = 'employees'
```

DBA_TAB_COLUMNS描述了数据库中所有表、视图和群集的列。  
USER_TAB_COLUMNS介绍了当前用户拥有的表、视图和群集的列。此视图不显示该列。OWNER

#### USER_TAB_COLS 查看用户下所有表是否含日期字段

```sql
SELECT c.table_name,c.column_name
FROM USER_TAB_COLS c
WHERE c.data_type = 'DATE'
ORDER BY table_name;
```

#### DBA_EXTENTS视图 与 DBA_SEGMENTS视图

```sql
SELECT segment_name,SUM(bytes)/1024/1024 ||'M'
FROM dba_extents
WHERE segment_name = ' '
GROUP BY segment_name;
```

##### DBA_EXTENDS描述的是数据库所有表空间中段的扩展信息  

使用DBA_EXTENDS必须保证相应的数据文件处于online状态，否则无法返回任何记录。  
常使用的是USER_EXTENTS视图，比DBA_EXTENDS视图少几个字段。

##### DBA_SEGMENTS 视图描述的是数据库中所有段的存储和分配信息

### 只读表

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

### 临时表 GLOBLE TEMPORARY TABLE 

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

#### 创建临时表

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

#### 使用临时表

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

#### 全局临时表

- 以常规表的形式创建临时数据表的表结构，但要在每一个表的主键中加入一个**SessionID 列**以区分不同的会话。(可以有 lob 列和主外键)
- 写一个**用户注销触发器**，在用户结束会话的时候删除本次会话所插入的所有记录 (SessionID 等于本次会话 ID 的记录 ) 。
   - 程序写入数据时，要顺便将当前的会话 ID(SessionID) 写入表中。
   - 程序读取数据时，只读取与当前会话 ID 相同的记录即可。

**功能增强的扩展设计：**

- 可以在数据表上建立一个视图，视图对记录的筛选条件就是当前会话的SessionID 。
- 数据表中的SessionID 列可以通过Trigger 实现，以实现对应用层的透明性。
- 高级用户可以访问全局数据，以实现更加复杂的功能。

### 簇表 CLUSTER

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

### 外部表

<https://www.cnblogs.com/xidabei/p/7453274.html>

#### 1

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

#### 2

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

#### 3

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



## VIEW 视图

**SCOTT用户创建视图VIEW权限不足：**

- 使用DBA授予SCOTT用户创建视图VIEW权限；

```sql
GRANT CREATE VIEW TO scott; 
```

**视图：从表中抽出的逻辑上相关的数据集合**

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

### 创建视图  CREATE [OR REPLACE] VIEW

- CREATE VIEW子句中各列的别名应和子查询中各列相对应

#### 简单视图和复杂视图

**简单视图 是单个表并且不包含函数或表达式的视图，**

- 可以在该视图上可以执行DML语句（即可执行增、删、改操作）

**复杂视图 就是指那些包含函数、表达式或者分组数据的视图，**

- 在该视图上执行DML语句时必须要符合特定条件。
- 通常我们在定义复杂视图时必须为函数或表达式定义别名。

| 特性     | 简单视图 | 复杂视图   |
| :------- | :------- | :-------- |
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

#### 连接视图   

```sql
--(table1,table2外键为column2)
CREATE OR REPLACE VIEW table1_view
AS
SELECT column1,t1.column2,column3
FROM table1 t1,table2 t2
WHERE t1.column2(+) = t2.column2;
```

### 查询视图 (SQL*PLUS)

```sql
SELECT *
FROM table1_view;
```

### 修改视图

- 相当于重新创建一个视图

```sql
CREATE OR REPLACE VIEW table1_view(column1,column2,……) 
AS 
SELECT column1,column2,……
FROM table1;
```

### 视图中DML操作规定

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

#### WITH READ ONLY 屏蔽DML操作

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

### 删除视图 

- 只是删除视图的定义，并不会删除基表的数据

```sql
DROP VIEW table1_view;
```

## 子查询

- 子查询可以返回单行结果，可以返回多行结果，也可以不返回结果  
    - 如果子查询未返回任何行，则主查询也不会返回任何结果

**目的**

- 主要目的：子查询的出现主要是为了解决多表查询之中的性能问题。
- 次要目的：
  - 很多时候在 FROM 子句里面使用子查询，
  - 是因为在外部查询里无法再继续使用统计函数操作的时侯

**使用方式**

1. 在括号内  
2. 在比较条件右侧  

**规定**

- 子查询 (内查询) 在主查询之前一次执行完成。
- 子查询的结果被主查询(外查询)使用
- 单行子查询只能使用单行操作符 =、>、>=、<、<=、<>
  - 一个子查询语句只返回一行结果，不能返回空值
- 多行子查询只能使用多行操作符
  - IN
  - EXISTS
  - ANY
  - SOME
  - ALL

### IN 等号列表中任意一个

#### NULL值问题

- NULL 不是0或者空格 而是**表示未知**

**子查询中的NULL:**

- 可以使用 IN(10,20,NULL)，但是**不可以使用 NOT IN(10,20,null)**
  - **在使用NOT IN的时候子查询之中必须不能包含NULL，否则不会有任何数据返回**。      
  - Oracle判断规则： 对于NOT IN如果测试值不在列表内，且列表中有一个NULL，则结果为FALSE
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

#### IN 与 "=" 的区别

```sql
select name 
from student 
where name in ('zhang','wang','li','zhao');
与
select name 
from student 
where name = 'zhang' 
  or name = 'li' 
  or name = 'wang' 
  or name= 'zhao'
;
的结果是相同的。
```

### EXISTS 检查在子查询中是否存在满足条件的行

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

#### NOT EXISTS

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

#### IN和EXISTS的区别

- 如果**子查询**得出的结果集记录较少，主查询中的表较大且又有索引时应该用**IN**,  
- 如果**外层**的主查询记录较少，子查询中的表大，又有索引时使用**EXISTS**。  
- 另外IN是不对NULL进行处理

**区分IN和EXISTS主要是造成了驱动顺序的改变（这是性能变化的关键）**

- 如果是EXISTS，那么以外层表为驱动表，先被访问，
- 如果是IN，那么先执行子查询，所以我们会以驱动表的快速返回为目标，那么就会考虑到索引及结果集的关系了
- **IN是把外表和内表作hash join，而EXISTS是对外表作LOOP**，每次LOOP再对内表进行查询

**性能上的比较**

```sql
--比如
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
--相对的
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

- in 是把外表和内表作hash join，  
- 而exists是对外表作loop，每次loop再对内表进行查询。

```
例如：表A（小表），表B（大表）
1：
select * 
from A 
where cc in (select cc 
             from B
            )
效率低，用到了A表上cc列的索引；

select * 
from A 
where exists(select cc 
             from B 
             where cc=A.cc
            )
效率高，用到了B表上cc列的索引。

相反的
2：
select * 
from B 
where cc in (
             select cc 
             from A
            )
效率高，用到了B表上cc列的索引；

select * 
from B 
where exists(select cc 
             from A 
             where cc=B.cc
            )
效率低，用到了A表上cc列的索引。
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

- 如果查询语句使用了**not in 那么内外表都进行全表扫描，没有用到索引**；  
- 而not extsts 的子查询依然能用到表上的索引。  
- 所以无论哪个表大，用not exists都比not in要快。  
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

### ANY 和子查询返回的一个值比较

### SOME 一些 和ANY的用法基本相同,用ANY的地方都可以用SOME代替

- SOME大多用在=操作中,表示等于所选集合中的任何一个。  
- ANY也可以用于=操作中，效果和SOME相同

### ALL 和子查询返回的所有值比较

### 多行子查询使用单行比较符 (不可使用GROUP BY)

### 子查询的使用

- SELECT后面使用，要求一定要只返回一条记录，要是单行子查询才行，多行子查询不行  
- HAVING后面使用 在HAVING子句之中使用子查询只能够返回单行单列的数据  
- FROM后面放置 比较特殊重要 FROM子句里面出现的子查询，其返回的结果一定是多行多列数据
  - FROM子句的主要功能是确定数据来源，而且数据来源应该都是数据表，表是一种行列的集合
- WHERE后面放置
- 不可以使用子查询的位置：GROUP BY

#### 主查询和子查询可以不是同一张表  

**从理论上来讲，尽量使用多表查询比较好，  **

-  因为子查询需要对数据库访问两次，而多表查询只需要对数据库访问一次。但实际情况下有可能不一样，因为多表查询的笛卡尔集可能很大所以慢了。  
- 一般不在子查询中使用排序ORDER BY；但在**Top-N分析**问题中，必须对子查询排序  

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

#### CASE中使用单列子查询

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

#### 相关子查询 按照一行接一行的顺序执行

**主查询的每一行都执行一次子查询**

- GET 从主查询中获取候选列
- EXECUTE 子查询使用主查询数据
- USE 如果满足内查询的条件则返回该行

#### 子查询中使用主查询的列

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

#### 相关更新 

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

#### 相关删除

```sql
-- 删除employees表中，基于emp_history表皆有数据
DELETE FROM employees e
WHERE (
       SELECT employee_id
       FROM emp_history
       WHERE employee_id = e.employee_id
       );  
```

### GROUP BY多字段分组和FROM子查询的区别？

- 即：此时 emp 表之中存在有 14000 条数据，dept 表中存在有 4000 条数据。  多表查询一定会产生笛卡儿积  

**多字段分组**：积的数量：emp 的 14000 条*dept 的 4000 条 = 56,000,000 条数据；

**FROM子查询**：

- 第一步（内嵌子查询）：针对于 emp 表查询，最多操作 14000 条记录，最多返回 4000 条记录；
- 第二步，子查询和 dept 表关联；
- 积的数量：dept 表的 4000 条* 子查询返回的最多 4000 条 = 16000000 条记录；
- 总的数据量：16000000 + 14000 = 16,014,000 条记录

### 多列子查询 主查询与子查询返回的多个列进行比较

- 成对比较
- 不成对比较

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

### 在FROM中使用子查询

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

## 多表查询(连接)

### 全自动连接(自然连接) NATURAL JOIN

在连接两表相同列时，可能有多个相同的重复列,不包含重复的属性  

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

### 使用同名列的连接 JOIN .. USING .. 

- USING多表连接时，若列名不同，则不适用

```sql
SELECT t1.no1,no2,no4
FROM table1 t1
 JOIN table2 USING (no1);
```

### 等值连接 

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

#### 内连接

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

#### 外连接  

##### 左外连接 LEFT [OUTER] JOIN 

- 当连接条件不成立的时候，等号左边的表仍然被包含
   - 即可以包含FROM子句中的表与ON中的表不匹配的值 

```sql
--可以返回左表(table1)中与右表(table2)不匹配的值
SELECT t1.no1,no2,no4
FROM table1 t1,table2,t2
 LEFT OUTER JOIN table2 ON t1.no1 = t2.no1;
```

##### 右外连接 RIGHT [OUTER] JOIN 

- 当连接条件不成立的时候，等号右边的表仍然被包含

```sql
--可以返回右表(table2)中与左表(table1)不匹配的值
SELECT t1.no1,no2,no4
FROM table1 t1
 RIGHT OUTER JOIN table2 ON t1.no1 = t2.no1;
```

##### 全连接 FULL [OUTER] JOIN 

- 查询结果等于左外连接和右外连接的和

```sql
SELECT t1.no1,no2,no4
FROM table1 t1
 FULL OUTER JOIN table2 ON t1.no1 = t2.no1;
--可以返回左右表中不匹配的行
```

##### 外连接运算符(+)

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

#### 交叉连接 CROSS JOIN 不带ON子句

- 返回被连接的两个表所有数据行的笛卡尔积，
- 返回到结果集合中的数据行数等于第一个表中符合查询条件的数据行数乘以第二个表中符合查询条件的数据行数.

```sql
SELECT t1.no1,no2,no4
FROM table1 t1
CROSS JOIN table2;
```

## 分层查询(树查询)(递归查询)

### 基本语法

```sql
SELECT [LEVEL],列,..
FROM 表
START WITH 列 = '值'  --根节点
--以满足条件的元组作为根节点 LEVEL=1
CONNECT BY [NOCYCLE] PRIOR 父键 = 子键;
--当子键（指定子节点的列）的值=父键（指定父节点的列）的值时，递归查找,会一层一层找下去,直到不符合这一规则,则查找停止
```

```sql
--表
CREATE TABLE MENU
(   
 "ID" NUMBER,
 "DATA" VARCHAR2(100), 
 "PID" NUMBER
);
--插入数据
INSERT INTO menu (id, data, pid) VALUES (7, 'g', 3);
INSERT INTO menu (id, data, pid) VALUES (1, 'a', null);
INSERT INTO menu (id, data, pid) VALUES (2, 'b', null);
INSERT INTO menu (id, data, pid) VALUES (3, 'c', 2);
INSERT INTO menu (id, data, pid) VALUES (4, 'd', 2);
INSERT INTO menu (id, data, pid) VALUES (5, 'e', 4);
INSERT INTO menu (id, data, pid) VALUES (6, 'f', 1);

SELECT id,data,NVL(TO_CHAR(pid),'NULL')
FROM menu
START WITH pid IS NULL  
--指定层次化查询的根节点
CONNECT BY PRIOR id = pid;  
--当前节点的pid等于上一层节点的id，如果满足条件,就加入到树结果集中              
```

**STRAT WITH 选择根节点**

- START WITH 一般用于指定层次化查询的开始节点(也就是树的最顶级节点)，找到最顶级节点,   然后按照一定的规则开始查找其剩余的子节点
    - `START WITH 列 = '值'`用于确定由表选择的要用作根节点的行。
- 由`列 = '值'`选择START WITH中满足条件的所有行都将成为树的根节点。
    - 于是，结果集中潜在树的数量等于根节点的数量。
- 因此，如果省略START WITH子句，则返回的每一行都是其自己树的根节点。

**CONNECT BY 定义父/子关系**

- CONNECT BY 用于查找剩余子节点的规则
- 对于任何给定行，其父级和子级均由`CONNECT BY`子句确定。
   - 带有PRIOR关键字标识的为父键，
   - 即子节点的指定列的值要满足等于父节点的（带有PRIOR标识的）列的值，才可以继续分层。 
- `CONNECT BY`子句必须由使用等号 (=) 进行比较的两个表达式组成。
   - 这两个表达式必须有一个前面带有关键字PRIOR,
   
**LEVEL 节点级别**

- LEVEL是一个伪列，可在SELECT命令中列出现的任何位置使用
- 伪列 LEVEL 返回这一行在树中的层次，根为第一层。
  - 根节点的LEVEL为1。根节点的直接子级的LEVEL为2，依此类推
- 对于结果集中的每一行，LEVEL返回一个非零整数值，指出由此行表示的节点在层次结构中的深度。

### ORDER SIBLINGS BY 对同级排序 

- 通过使用 `ORDER SIBLINGS BY`子句，可对结果集进行排序， 以便按所选列值的升序或降序显示同级。
   - 这是 ORDER BY 子句的特例，只能用于分层查询。

```sql
SELECT LEVEL, LPAD (' ', 2 * (LEVEL - 1)) || ename "employee", empno, mgr
FROM emp 
START WITH mgr IS NULL
-- 以mgr为null的元组作为根节点，LEVEL=1
CONNECT BY PRIOR empno = mgr
--连接条件为：子节点的mgr等于父节点的empno的元组
ORDER SIBLINGS BY ename ASC；
--在结果集中按ename升序排列
```

```sql
SELECT LEVEL
      ,empno
      ,ename
      ,sal
      ,mgr
FROM emp
CONNECT BY PRIOR empno = mgr
START WITH mgr IS NULL
ORDER BY 1;
```

### 回环CYCLE

层次化查询会检测数据中是否存在回环(死循环)，如果存在回环，则会抛出 ORA-01436: CONNECT BY loop in user data . 的错误

- 如果在CONNECT BY子句后面加上 NOCYCLE 则产生回环的最后一层的节点会被删除

```sql
SELECT id,data,NVL(TO_CHAR(pid),'NULL')
FROM menu
START WITH (data ='a' OR data= 'b')
CONNECT BY NOCYCLE PRIOR id = pid; 
--产生回环的最后一层的节点会被删除,(可能并没有删除)
```

### CONNECT_BY_ISCYCLE / CONNECT_BY_ISLEAF / CONNECT_BY_ROOT

1. CONNECT_BY_ISCYCLE 
   - 当这一行有一个子节点同时也是它的祖先节点时返回 1 ，否则返回 0 。
2. CONNECT_BY_ISLEAF 
   - 当这一行是叶节点时返回 1 ，否则返回 0 。
3. CONNECT_BY_ROOT 一元运算符，可用于限定列，以便根据当前行返回被视为根节点的行的列值
   - CONECT_BY_ROOT查询操作符可以加在CONNECT BY之后的某个字段之前，表示获得这一行的根节点的该字段的值。
   - CONNECT_BY_ROOT 运算符可在 SELECT 列表、WHERE 子句、GROUP BY 子句、HAVING 子句、ORDER BY 子句和 ORDER SIBLINGS BY 子句中使用，只要 SELECT 命令用于分层查询。
   - CONNECT_BY_ROOT 运算符不能在分层查询的 CONNECT BY 子句或 START WITH 子句中使用。
   - 可将 CONNECT_BY_ROOT 应用于一个涉及列的表达式,但这样做时,该表达式必须用圆括号括起，当应用于不带圆括号的表达式时,CONNECT_BY_ROOT运算符仅影响紧跟其后的一列

```sql
--使用 CONNECT_BY_ROOT 运算符根据以员工 Zlotkey 和 King 开头的树，为结果集中列出的每个员工返回根节点的员工编号和员工名称。
--(查询管理者Zlotkey和King和所管理员工的last_name,employee_id,manager_id)
SELECT LEVEL,
       LPAD(' ',2 * (LEVEL - 1)) || last_name "employee",
       employee_id,
       manager_id,  
       CONNECT_BY_ROOT employee_id "mgr_id",
       CONNECT_BY_ROOT last_name "mgr_name"
FROM employees
START WITH last_name IN ('Zlotkey','King')
CONNECT BY PRIOR employee_id = manager_id
ORDER SIBLINGS BY last_name ASC;
```

```sql
--只生成一个以单个顶层级别员工开头的树
SELECT LEVEL,LPAD(' ',2 * (LEVEL - 1)) ||last_name "name",employee_id,manager_id,
       CONNECT_BY_ROOT employee_id "mgr_empno",
       CONNECT_BY_ROOT last_name "mgr_ename"
FROM employees
START WITH manager_id IS NULL
CONNECT BY PRIOR employee_id = manager_id
ORDER SIBLINGS BY last_name ASC;
```

### 使用SYS_CONNECT_BY_PATH检索路径

**SYS_CONNECT_BY_PATH函数，在分层查询中用于检索在当前节点和根节点之间出现的指定列的值。**

- SYS_CONNECT_BY_PATH (column, delimiter);    
  - column 是位于分层查询中指定的表中且调用该函数的列的名称。
  - delimiter 是 varchar 值，用于分隔指定列中的每个条目。
- SYS_CONNECT_BY_PATH (exp,char) 
  - 返回从根节点到这一行计算其中每个exp表达式的值，并把它们连接成字符串，
  - 每个节点之间用char字符来分割。

## 单行函数

函数作用：
1)方便数据的统计
2)处理查询结果

1)数值函数
2)字符函数
3)日期函数
4)转换函数
***

### 数值函数

#### 1)四舍五入 ROUND(n[,m])

`省略m:0; m>0:小数点后m位; m<0:小数点前m位`

```
SELECT ROUND(4.5),ROUND(2.3),ROUND(2.12,2)
FROM dual;

ROUND(4.5) ROUND(2.3) ROUND(2.12,2)
---------- ---------- -------------
         5          2          2.12
```

描述 : 传回一个数值，该数值是按照指定的小数位元数进行四舍五入运算的结果。

```
SELECT ROUND( number, [ decimal_places ] ) 
FROM DUAL

参数:
number : 欲处理之数值
decimal_places : 四舍五入 , 小数取几位 ( 预设为 0 )
```

Sample :

```
select round(123.456, 0) 
from dual;          
回传 123
```

```
select round(123.456, 1) 
from dual;          
回传 123.5
```

```
select round(-123.456, 2) 
from dual;        
回传 -123.46
```

***

#### 2)取整函数 CEIL() 取最大值(向上取整) FLOOR() 取最小值(向下取整)

```
SELECT CEIL(23.45),FLOOR(23.45) 
FROM dual;

CEIL(23.45) FLOOR(23.45)
----------- ------------
         24           23
```

ceil和floor函数在一些业务数据的时候，有时还是很有用的。

- ceil(n) 取大于等于数值n的最小整数；
- floor(n)取小于等于数值n的最大整数

应用：

##### 对于每个员工，显示其加入公司的天数

1.

```
select floor(sysdate-hiredate) "入职天数"
      ,ename 
from emp;
```

2.

```
select trunc(sysdate-hiredate) "入职天数"
      ,ename 
from emp;
```

##### Oracle计算时间差

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

***

#### 3)常用计算

##### ABS(n)   取绝对值

##### MOD(m,n)  取模(取余数) m%n

##### POWER(m,n) 取m的n次幂

##### SQRT(n) 求平方根

##### SIGN(n) 返回值的符号

***

#### 4)三角函数 提供弧度参数

##### SIN(n) ASIN(n)

##### COS(N) ACOS(n)

##### TAN(n) ATAN(n)

***

#### 5)随机

##### 数 DBMS_RANDOM.VALUE
- 生成一个指定范围的38位随机小数（小数点后38位），若不指定范围则默认为范围为[0,1)的随机数。

```
SELECT DBMS_RANDOM.VALUE
FROM dual;

SELECT TRUNC(DBMS_RANDOM.VALUE(10,50),0)
FROM dual;
```

##### 字符串 DBMS_RANDOM.STRING()

```
SELECT DBMS_RANDOM.STRING('A',20)
FROM dual
```

***

### 字符函数

#### 1)大小写转换函数

##### UPPER(char) 转换为大写

##### LOWER(char) 转换为小写

##### INITCAP(char) 首字母大写

```
SELECT UPPER('hello'),LOWER('HELLO'),INITCAP('hELlo')
FROM dual;

UPPER('HELLO') LOWER('HELLO') INITCAP('HELLO')
-------------- -------------- ----------------
HELLO          hello          Hello
```

```
SELECT employee_id,salary
FROM employees
WHERE LOWER(last_name) = 'king'
```

#### 2)获取子字符 SUBSTR(char,[m[,n]])  

 获取子字符，m 从哪取，从哪个位置开始取  
            n 取出多少位,而不是取到哪一位
 n省略时，从m的位置截取到结束，
 m从1开始,如果m写0也是从第一个字符开始。
 如果m为负数时，从字符串的尾部开始截取

```
SELECT SUBSTR('hello',2,3)
FROM dual;

SUBSTR('HELLO',2,3)
-------------------
ell
```

```
SELECT employee_id,last_name,salary
FROM employees
WHERE LOWER(SUBSTR(last_name,0)) = 'king'; --即WHERE LOWER(last_name) = 'king';  --LIKE 模糊查询类似作用
```

#### 3)获取字符串长度函数 LENTH(char) 会包含空格的长度

#### 4)字符串连接函数 CONCAT(char1,char2) 与||作用一样

```
SELECT CONCAT('hello ','world')
FROM dual;
```

```
SELECT CONCAT(first_name,last_name)
FROM employees;
```

#### 5)INSTR(c1,c2) 获取c2在c1中的位置

```
SELECT INSTR('hello','h')
FROM dual;
```

#### 6)LPAD(c1,n,c2) 用c2从左往右补满(n-LENTH(c1))空位

#### 6)RPAD(c1,n,c2) 用c2从左往右补满(n-LENTH(c1))空位

```
SELECT LPAD(salary,10,'*')
FROM employees;
```

#### 去除子符串函数

##### 1)TRIM(c2 FROM c1) 首尾均去除，要求首/尾是要去除的字符代表从c1中去除c2字符串

就是子文本替换，要求c2中只能是一个字符

```
SELECT TRIM('e' FROM 'hello') --结果不变
FROM dual;

TRIM('E'FROM'HELLO')
--------------------
hello
```

```
select trim(leading 9 from 9998767999) s1,
       trim(trailing 9 from 9998767999) s2,
       trim(9 from 9998767999) s3 
from dual;
```

##### 2)LTRIM(c1[,c2]) 从c1中去除c2，从左边开始去除，要求第一个就是要去除的字符，有多少个重复的该字符就会去除多少次

```
SELECT LTRIM('h','hello')
FROM dual;

LTRIM('H','HELLO')
--------------------
ello
```

##### 3)RTRIM(c1[,c2]) 从c1中去除c2，要求右侧第一个就是要去除的字符，有多少个重复的该字符就会去除多少次

##### 4)TRIM(c1) 代表去除首尾的空格，删首尾空，LTRIM和RTRIM只有一个参数时同理

```
SELECT TRIM('   hello    ')
FROM dual;
```

##### 5)REPLACE(char,s_string[,r_string]) 字符串替换函数，省略第三个参数则用空白替换

```
SELECT REPLACE('hello','el','lll')
FROM dual;

REPLACE('HELLO','EL','LLL')
------------------------
helllo
```

##### 6)TRANSLATE() 字符替换

```
TRANSLATE('ABC','C','XX') 
'ABC' --要被替换的字符串
'AC' --''内的所有字符被一一替换，不是字符串'AC'而是替换'A''C';
'XX' --要替换成的字符与'AC'一一对应
```

```
   select translate('abc','b','xx') from dual; -- x是1位
   --返回axc
   select translate('abc','bc','xx') from dual;
   --axx
   select translate('abc','bc','x') from dual;
   --ax
```

```
SELECT TRANSLATE('abcd','acd','#')
FROM dual;
--#B
```

###### 统计某个字符出现次数

```
SELECT LENGTH(TRANSLATE('abcdbbb','b' || 'abcdbbb','b'))
FROM dual

而不是
SELECT LENGTH('abcdbbb') - LENGTH(TRANSLATE('abcdbbb','b',''))
FROM dual; --返回空值 
```

#### Ascii值

##### 返回字符串首字母的Ascii值

```
  select ascii('a') from dual
```

#### chr()

##### chr(10)  回车/换行

##### 返回ascii值对应的字母

```
   select chr(97) from dual
```

### 日期函数

日期不允许加法，可以相减
系统时间 sysdate 默认格式：DD-MON-RR 天-月-年

#### 1)ADD_MONTHS(date,i)  用于添加指定的月份，返回在指定的日期上添加的月份

i可以是任意整数，如果i是负数，则是在原有的值上减去该月份了

```
SELECT ADD_MONTHS(sysdate,1)
FROM dual;
```

#### 2)NEXT_DAY(date,char)  第二个参数指定星期几，在中文环境下输入星期X即可，返回下一个周几是哪一天

```
SELECT NEXT_DAY(sysdate,'星期二')
FROM dual;
```

#### 3)LAST_DAY(date) 用于返回日期所在月的最后一天

```
SELECT LAST_DAY(sysdate)
FROM dual;
```

#### 4)MONTHS_BETWEEN(date1,date2) 计算两个日期之间间隔的月份，前者减后者

```
SELECT MONTHS_BETWEEN('20-5月-15','10-1月-15') 
FROM dual;
```

#### 5)EXTRACT(date FROM datetime) 返回相应的日期部分

```
SELECT EXTRACT(year FROM sysdate)  --可以改month或者day
FROM dual; 
```

```
SELECT EXTRACT(hour FROM timestamp '2015-10-1 17:25:13') 
FROM dual;
```

#### 6)TRUNC(date,精度说明符) 用于截取日期时间的TRUNC函数

- 精度说明符有: yyyy-mm-dd-hh-mi 截取时间到秒(不是ss)暂时不知道怎么操作
- 不可直接用TRUNC(sysdate,'yyyy-mm-dd')，会提示“精度说明符过多”
- 如果不填写精度说明符，则默认到DD，包含年月日，不包含时分秒

```
SELECT TRUNC(sysdate,'yyyy') --'yyyy' = 'year'
FROM dual;
```

##### 1.1 trunc函数处理数字

trunc函数返回处理后的数值，其工作机制与ROUND函数极为类似，只是该函数不对指定小数前或后的部分做相应舍入选择处理，而统统截去。
其具体的语法格式如下

```
TRUNC（number[,decimals]）
其中：
number 待做截取处理的数值
decimals 指明需保留小数点后面的位数。可选项，忽略它则截去所有的小数部分。
```

```
select trunc(123.98)
from dual;
```

```
select trunc(123.123,2)
from dual;
```

```
select trunc(123.123,-1)
from dual;
```

注意：

- 第二个参数可以为负数，表示为小数点左边指定位数后面的部分截去，即均以0记。
- 与取整类似，比如参数为1即取整到十分位，如果是-1，则是取整到十位，以此类推；
- 如果所设置的参数为负数，且负数的位数大于或等于整数的字节数的话，则返回为0。
  - 如：TRUNC(89.985,-3)=0。

##### 1.2trunc函数处理日期

trunc函数返回以指定元元素格式截去一部分的日期值。
其具体的语法格式如下：

```
TRUNC（date,[fmt]）
其中：
date为必要参数，是输入的一个日期值
fmt参数可忽略，是日期格式，用以指定的元素格式来截去输入的日期值。忽略它则由最近的日期截去
```

下面是该函数的使用情况：

```
trunc(sysdate,'yyyy') 
--返回当年第一天.
select trunc(sysdate,'YYYY')
from dual;
```

```
trunc(sysdate,'mm')
 --返回当月第一天.
select trunc(sysdate,'MM')
from dual;
```

```
trunc(sysdate,'d') 
--返回当前星期的第一天.
select trunc(sysdate,'D')
from dual;
```

#### 7)ROUND(date,精度说明符) 四舍五入

```
SELECT ROUND(sysdate,'yyyy')
FROM dual;
```

描述 : 传回一个数值，该数值是按照指定的小数位元数进行四舍五入运算的结果。

```
SELECT ROUND( number, [ decimal_places ] ) 
FROM DUAL

参数:
number : 欲处理之数值
decimal_places : 四舍五入 , 小数取几位 ( 预设为 0 )
```

Sample :

```
select round(123.456, 0) 
from dual;          
回传 123
```

```
select round(123.456, 1) 
from dual;          
回传 123.5
```

```
select round(-123.456, 2) 
from dual;        
回传 -123.46
```

***

#### 应用

##### 1. 显示Two Hundred Twenty-Two  

```
select to_char( to_date(222,'J'),'Jsp') 
from dual     
```

***

##### 2.求某天是星期几

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

##### 3. 设置日期语言  NLS_DATE_LANGUAGE

###### 1)修改会话

```
ALTER SESSION SET NLS_DATE_LANGUAGE='AMERICAN';     
```

###### 2)参数

```
TO_DATE ('2002-08-26', 'YYYY-mm-dd', 'NLS_DATE_LANGUAGE = American')    
```

***

##### 4. 两个日期间的天数   相减返回天数

```
select floor(sysdate - to_date('20020405','yyyymmdd'))   
from dual;    
```

***

##### 5. 时间为null的用法

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

##### 6.月份差  

```
a_date between to_date('20011201','yyyymmdd') and to_date('20011231','yyyymmdd')     

那么12月31号中午12点之后和12月1号的12点之前是不包含在这个范围之内的。     
所以，当时间需要精确的时候，觉得to_char还是必要的
```  

##### 7. 日期格式冲突问题

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

##### 8

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

##### 9. 查找月份

```
    select months_between(to_date('01-31-1999','MM-DD-YYYY'),to_date('12-31-1998','MM-DD-YYYY')) "MONTHS" 
    FROM DUAL;     
    --1     
   
    select months_between(to_date('02-01-1999','MM-DD-YYYY'),to_date('12-31-1998','MM-DD-YYYY')) "MONTHS" 
    FROM DUAL;     
    --1.03225806451613
```

##### 10. Next_day的用法

```
    Next_day(date, day)     
   
    Monday-Sunday, for format code DAY     
    Mon-Sun, for format code DY     
    1-7, for format code D    
```

##### 11

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

##### 12.获得小时数

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

##### 13.年月日的处理

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

##### 14.处理月份天数不定的办法

```
select to_char(add_months(last_day(sysdate) +1, -2), 'yyyymmdd')
      ,last_day(sysdate) 
from dual    
```

##### 16.找出今年的天数

```
select add_months(trunc(sysdate,'year'), 12) - trunc(sysdate,'year') 
from dual    

闰年的处理方法     
to_char( last_day( to_date('02' || :year,'mmyyyy') ), 'dd' )     
如果是28就不是闰年    
```

##### 17.yyyy与rrrr的区别

```
   'YYYY99 TO_C     
   ------- ----     
   yyyy 99 0099     
   rrrr 99 1999     
   yyyy 01 0001     
   rrrr 01 2001    
```

##### 18.不同时区的处理

```
   select to_char( NEW_TIME( sysdate, 'GMT','EST'), 'dd/mm/yyyy hh:mi:ss') ,sysdate     
   from dual;    
```

##### 19.5秒钟一个间隔

```
   select TO_DATE(FLOOR(TO_CHAR(sysdate,'SSSSS')/300) * 300,'SSSSS') ,TO_CHAR(sysdate,'SSSSS')     
   from dual    

   2002-11-1 9:55:00 35786     
   SSSSS表示5位秒数    
```

##### 20.一年的第几天

```
   select TO_CHAR(SYSDATE,'DDD'),sysdate from dual
       
   310 2002-11-6 10:03:51    
```

##### 21.计算小时,分,秒,毫秒

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

##### 23.next_day函数      返回下个星期的日期,day为1-7或星期日-星期六,1表示星期日

```
   next_day(sysdate,6)是从当前开始下一个星期五。后面的数字是从星期日开始算起。     
   1 2 3 4 5 6 7     
   日 一 二 三 四 五 六   
```

```  
   select    (sysdate-to_date('2003-12-03 12:55:45','yyyy-mm-dd hh24:mi:ss'))*24*60*60 from ddual
   日期 返回的是天 然后 转换为ss
```

##### 24,round[舍入到最接近的日期](day:舍入到最接近的星期日)

```
   select sysdate S1,
   round(sysdate) S2 ,
   round(sysdate,'year') YEAR,
   round(sysdate,'month') MONTH ,
   round(sysdate,'day') DAY 
   from dual
```

##### 25,trunc[截断到最接近的日期,单位为天] ,返回的是日期类型

```
   select sysdate S1,                    
     trunc(sysdate) S2,                 //返回当前日期,无时分秒
     trunc(sysdate,'year') YEAR,        //返回当前年的1月1日,无时分秒
     trunc(sysdate,'month') MONTH ,     //返回当前月的1日,无时分秒
     trunc(sysdate,'day') DAY           //返回当前星期的星期天,无时分秒
   from dual
```

##### 26,返回日期列表中最晚日期

```
select greatest('01-1月-04','04-1月-04','10-2月-04') from dual
```

##### 27.计算时间差

注:oracle时间差是以天数为单位,所以换算成年月,日

```
select floor(to_number(sysdate-to_date('2007-11-02 15:55:03','yyyy-mm-dd hh24:mi:ss'))/365) as spanYears from dual        //时间差-年
select ceil(moths_between(sysdate-to_date('2007-11-02 15:55:03','yyyy-mm-dd hh24:mi:ss'))) as spanMonths from dual        //时间差-月
select floor(to_number(sysdate-to_date('2007-11-02 15:55:03','yyyy-mm-dd hh24:mi:ss'))) as spanDays from dual             //时间差-天
select floor(to_number(sysdate-to_date('2007-11-02 15:55:03','yyyy-mm-dd hh24:mi:ss'))*24) as spanHours from dual         //时间差-时
select floor(to_number(sysdate-to_date('2007-11-02 15:55:03','yyyy-mm-dd hh24:mi:ss'))*24*60) as spanMinutes from dual    //时间差-分
select floor(to_number(sysdate-to_date('2007-11-02 15:55:03','yyyy-mm-dd hh24:mi:ss'))*24*60*60) as spanSeconds from dual //时间差-秒
```

##### 28.更新时间

注:oracle时间加减是以天数为单位,设改变量为n,所以换算成年月,日

```
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate+n*365,'yyyy-mm-dd hh24:mi:ss') as newTime from dual        //改变时间-年
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),add_months(sysdate,n) as newTime from dual                                 //改变时间-月
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate+n,'yyyy-mm-dd hh24:mi:ss') as newTime from dual            //改变时间-日
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate+n/24,'yyyy-mm-dd hh24:mi:ss') as newTime from dual         //改变时间-时
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate+n/24/60,'yyyy-mm-dd hh24:mi:ss') as newTime from dual      //改变时间-分
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate+n/24/60/60,'yyyy-mm-dd hh24:mi:ss') as newTime from dual   //改变时间-秒
```

##### 29.查找月的第一天,最后一天

```
SELECT Trunc(Trunc(SYSDATE, 'MONTH') - 1, 'MONTH') First_Day_Last_Month,
       Trunc(SYSDATE, 'MONTH') - 1 / 86400 Last_Day_Last_Month,
       Trunc(SYSDATE, 'MONTH') First_Day_Cur_Month,
       LAST_DAY(Trunc(SYSDATE, 'MONTH')) + 1 - 1 / 86400 Last_Day_Cur_Month
FROM dual;
```

***

### 转换函数

#### 1)TO_CHAR(date[,fmt[,params]]) 日期转为字符串

 date为需要转换的日期，fmt为转换的格式，params为转换的语言（通常默认会自动选择，可以省略，与安装语言一致）  

```
 默认格式：DD-MON-RR   
 格式有：  
  YY YYYY YEAR (1997: 97 ,1997 ,NINTENN AND NITY SEVEN)
  MM MON MONTH (2月: 02 ,JUL ,JULY)
  DD DY DAY (02: 02 ,MON ,MONDAY)
  HH24 HH12
  MI SS
  AM (上午/下午)
```

 用双引号""向日期函数添加字符,日期要用单引号''括起

```
SELECT TO_CHAR(sysdate,'yyyy"年"mm"月"dd"日" hh:mi:ss')
FROM dual;
```

```
SELECT TO_CHAR(sysdate,'HH24:MI:SS AM')
FROM dual;

TO_CHAR(SYSDATE,'HH24:MI:SSAM'
------------------------------
15:58:45 下午
```

#### 2)TO_DATE(char[,fmt[,params]]) 字符串转为日期

```
SELECT TO_DATE('2022-2-2','yyyy-mm-dd')
FROM dual;
```

##### TO_DATE格式(以时间:2007-11-02   13:45:25为例)

![](vx_images/178054410239085.png =526x)

##### 日期和字符转换函数用法（to_date,to_char）

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

#### 3)TO_CHAR(number[,fmt]) 数字转为字符串

```
 格式有：
  9:显示数字并忽略前面的0
  0：显示数字，位数不足，用0补齐
  .或D：显示小数点
  ,或G：显示千分位
  $：美元符号
  S：加正负号（前后都可以） 
```

```
SELECT TO_CHAR(123456,'$0000000000')
FROM dual;
```

#### 4)TO_NUMBER(char[,fmt])

```
SELECT TO_NUMBER('$123,456.789','$999999.999')
FROM dual;
```

### 通用函数 适用于任何(包含空值)

#### 1)NVL 转换空值

NVL(expr1,expr2) 如果expr1为空值，则返回expr2

```
SELECT last_name,salary,NVL(commission_pct,0)
FROM employees;
```

#### 2)NVL2 转换空值

NVL2(expr1,expr2,expr3) 如果expr1不为空值则返回expr2，expr1为空值则返回expr3

```
若工资有奖金率，commission_pct不为空值，则返回工资加奖金，否则返回工资上调500
SELECT last_name,NVL2(commission_pct,salary * (1 + commission_pct),salary + 500)
FROM employees;
```

#### 3)NULLIF

NULLIF(expr1,expr2) 若相等返回NULL,不等返回expr2

```
显示出那些换过工作的人员原工作，现工作。
SELECT e.last_name,e.job_id,j.job_id,NULLIF(e.job_id,j.job_id) "Old Job ID"
FROM employees e, job_history j
WHERE e.employee_id = j.employee_id
ORDER BY last_name;
```

#### 4)COALESCE

1. 同时处理交替多值
2. 若第一个表达式为空，则返回下一个表达式对其他参数COALESCE结果

- COALESCE(expr1,expr2,expr3)

```
SELECT last_name,COALESCE(commission_pct,salary,10)
FROM employees
ORDER BY commission_pct;
```

#### 5)CASE [实现switch ..case 逻辑]

```sql
SELECT CASE X-FIELD
        WHEN X-FIELD < 40 THEN 'X-FIELD 小于 40'
        WHEN X-FIELD < 50 THEN 'X-FIELD 小于 50'
        WHEN X-FIELD < 60 THEN 'X-FIELD 小于 60'
        ELSE 'UNBEKNOWN'
    END
FROM DUAL

注:CASE语句在处理类似问题就显得非常灵活。当只是需要匹配少量数值时，用Decode更为简洁。
```

#### 6)DECODE [实现if ..then 逻辑]   注:第一个是表达式,最后一个是不满足任何一个条件的值

```
   select deptno,decode(deptno,10,'1',20,'2',30,'3','其他') from dept;
```

   例:

```
select seed,account_name,decode(seed,111,1000,200,2000,0) from t_userInfo//如果seed为111,则取1000;为200,取2000;其它取0
select seed,account_name,decode(sign(seed-111),1,'big seed',-1,'little seed','equal seed') from t_userInfo//如果seed>111,则显示大;为200,则显示小;其它则显
```

### 排序函数

#### RANK() OVER 分组排序

- RANK() OVER 用于指定条件后的进行排名.
- 特点是对指定栏位的排名可以使用本函数,
- 如果出现两个相同的值,它会将其分为一组,同时将下一个栏位所占名次空出来
   - 即如果存在组内排序栏位的列值相同，则：归为同一排名（比如有两个相同的列，则他们的栏位都是2，那么下一个列的栏位就是4，而不是3）
   
**语法**

```sql
SELECT RANK() OVER(PARTITION BY 分组栏位的列 ORDER BY 组内排序栏位的列 DESC|ASC)
FROM 表;
```

```sql
SELECT last_name,employee_id,manager_id,
       RANK() OVER(PARTITION BY manager_id ORDER BY employee_id DESC)
FROM employees;
```

#### DENSE_RANK() OVER

- 如果出现两个相同的值，它会将其分为一组，但并不会空出所占栏位数
    - 即如果存在组内排序栏位的列值相同，则：归为同一排名（比如有两个相同的列，则他们的栏位都是2，那么下一个列的栏位还是3）

```sql
SELECT DENSE_RANK() OVER(PARTITION BY 分组的列 ORDER BY 组内排序的列 DESC|ASC)
FROM 表;
```

```sql
SELECT last_name,employee_id,manager_id,
       DENSE_RANK() OVER(PARTITION BY manager_id ORDER BY employee_id DESC)
FROM employees;
```

#### ROW_NUMBER() OVER

- 不需要考虑是否并列,哪怕根据条件查询出来的数值相同也会进行连续排列
   -  即如果存在组内排序栏位的列值相同，则：仍然按次序排（比如有两个相同的列，则他们的栏位依次排列，如2，3）

```sql
SELECT ROW_NUMBER OVER(PARTITION BY 分组的列 ORDER BY 组内排序的列 DESC|ASCC)
FROM 表;
```

```sql
SELECT last_name,employee_id,manager_id,
       ROW_NUMBER() OVER(PARTITION BY manager_id ORDER BY employee_id DESC)
FROM employees;
```

### 分析函数

#### 偏移量 LEAD() LAG()

这种操作可以代替表的自联接，从而更方便地进行进行数据过滤，并且LAG和LEAD有更高的效率。

**LEAD()**

- 在一次查询中取出同一字段的后N行的数据作为独立的列

**LAG()**

- 在一次查询中取出同一字段的前N行的数据作为独立的列

**OVER()**

- 表示 LAG()与LEAD()操作的数据都在OVER()的范围内，
   - `PARTITION BY `语句（用于分组） 
   - `ORDER BY` 语句（用于排序）。

- 例如：LEAD(FIELD, NUM, DEFAULTVALUE) FIELD需要查找的字段，NUM往后查找的NUM行的数据，DEFAULTVALUE没有符合条件的默认值。

## 组函数

### 常用组函数

-  组函数 (多行函数) 输入多个参数，或者是内部扫描多次，输出一个结果

|   组函数   |   说明   |
| --------- | -------- |
| AVG()     | 平均值   |
| COUNT()   | 记录总数 |
| MAX()     | 最大值   |
| MIN()     | 最小值   |
| STDDEN()  | 标准差   |
| SUM()     | 总和     |
| WM_CONCAT | 行转列   |


```sql
SELECT department_id,WM_CONCAT(last_name)
FROM employees
GROUP BY department_id 
--使用GROUP BY可以对多个字段进行分组,
--GROUP BY关键字后面跟需要分组的字段

/*
输出:
DEPARTMENT_ID WM_CONCAT(LAST_NAME)
------------- --------------------------------------------------------------------------------
           10 Whalen
           20 Hartstein,Fay
           30 Raphaely,Colmenares,Himuro,Tobias,Baida,Khoo
           40 Mavris
           50 OConnell,Feeney,Walsh,Jones,McCain,Everett,Bell,Perkins,Gates,Dilly,Chung,Cabrio
           60 Hunold,Lorentz,Pataballa,Austin,Ernst
           70 Baer
           80 Russell,Johnson,Livingston,Taylor,Hutton,Abel,Kumar,Bates,Smith,Fox,Bloom,Ozer,B
           90 kk,De Haan,Kochhar
          100 Greenberg,Urman,Sciarra,Chen,Faviet
          110 Higgins,Gietz
              Grant
12 rows selected
*/
```

### NVL 使无法忽略空值

### DISTINCT 非空不重复

```sql
SELECT DISTINCT employee_id,salary
FROM employees;
```

```sql
SELECT COUNT(DISTINCT employee_id)
FROM employees;
```

### GROUP BY

- **在SELECT中所有未包含在组函数的列都应该包含在GROUP BY中**
- GROUP BY中的列不必包含在SELECT中
1. **不能在WHEHE子句中使用组函数**
2. 在HAVING中使用组函数
- 如果在能使用WHERE的场景下，从SQL优化的角度来看，尽量使用WHERE效率更高，因为HAVING是在分组的基础上过滤分组的结果,而WHERE是先过滤,再分组,要处理的记录数不同。所以WHERE能使分组记录数大大降低，从而提高效率

```sql
--求表中各部门平均工资
SELECT AVG(salary)
FROM employees
GROUP BY department_id;
```

#### 查找平均工资最低的部门

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

#### 多列GROUP BY

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

### 嵌套组函数

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

### ROLLUP()

- ROLLUP就可以实现小计、总计的效果，可以用在报表里面。


- **rollup 按分组的第一个列进行统计和最后的小计**
- **cube 按分组的所有列的进行统计和最后的小计**


```sql
--按部门、不同的职位显示工资的总额；同时按部门，统计工资总额；统计所有员工的工资总额。
SELECT department_id,job_id,SUM(salary) 
FROM employees 
GROUP BY ROLLUP(department_id,job_id);

--再次优化，先运行:
BREAK ON department_id SKIP 2

DEPARTMENT_ID JOB_ID     SUM(SALARY)
------------- ---------- -----------
              SA_REP            7000
                                7000
           10 AD_ASST           4400
           10                   4400
           20 MK_MAN           13000
           20 MK_REP            6000
           20                  19000
           30 AC_MGR           12000
           30 PU_CLERK         13900
           30                  25900
           40 HR_REP            6500
           40                   6500
           50 ST_MAN           36400
           50 SH_CLERK         64300
           50 ST_CLERK         55700
           50                 156400
           60 IT_PROG          28800
           60                  28800
           70 PR_REP           10000
           70                  10000
DEPARTMENT_ID JOB_ID     SUM(SALARY)
------------- ---------- -----------
           80 SA_MAN           61000
           80 SA_REP          243500
           80                 304500
           90 AD_VP            34000
           90 AD_PRES          24000
           90                  58000
          100 PU_CLERK         12000
          100 FI_ACCOUNT       32700
          100                  44700
          110 AC_MGR           12000
          110 AC_ACCOUNT        8300
          110                  20300
                              685500
33 rows selected
```

#### 分级汇总

```sql
select deptno
      ,job
      ,sum(sal) 
from emp 
group by deptno,job;
    
select deptno
      ,job 
      ,sum(sal) 
from emp 
group by rollup(deptno,job);
    
cube 产生组内所有列的统计和最后的小计
select deptno
      ,job 
      ,sum(sal) 
from emp 
group by cube(deptno,job);
```

### STADEV() 返回一组值的标准偏差

```sql
select deptno,stddev(sal) from emp group by deptno;
--variance 返回一组值的方差差
select deptno,variance(sal) from emp group by deptno;
```


### 行列转换

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

## WITH 临时查询表

1. 避免在SELECT语句中重复书写相同的语句块
2. WITH子句将该子句执行一次并存储到用户的临时表空间中
3. 使用WITH子句可提高查询效率

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

## SEQUENCE 序列

序列 可供多个用户来产生唯一数据的数据库对象,与表无关系

1. 自动提供唯一的数值
2. 共享对象
3. 主要用于提供主键值
4. 将序列装入内存可以提高访问效率

1. SEQUENCE号是数据库系统按照一定规则自增的数字序列，因为自增所以不会重复。
2. 用于记录数据库中最新动作的语句，只要语句有动作(I/U/D等),SEQUENCE号都会随着更新,可以根据SEQUENCE号来SELECT出更新的语句

### CREATE SEQUENCE 创建序列

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

### 使用序列 .NEXTVAL和.CURRVAL

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

### 查询序列 USER_SEQUENCES

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

### 修改序列 ALTER sequence_name ……

- **如果想要修改START WITH的值必须先删除SEQUENCE再重新写**
- 想改变序列化的MINVALUE必须删除序列化后再重新建立序列化.**不可以修改序列化的MINVALUE**。
- 修改序列的增量、最大值、最小值、循环选项、是否装入内存

**注意:**

1. 必须是序列的拥有者或对序列有ALTER权限
2. 只有将来的序列值会被改变
3. 改变序列的初始值只能通过删除后重建序列

### 删除序列

```sql
DROP SEQUENCE emp_seq;
```

### 触发器实现自动递增列

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

## INDEX 索引 

数据库中索引(Index)的概念与目录的概念非常类似。如果某列出现在查询的条件中，而该列的数据是无序的，查询时只能从第一行开始一行一行的匹配。创建索引就是对某些特定列中的数据排序，生成独立的索引表。在某列上创建索引后，如果该列出现在查询条件中，Oracle会自动的引用该索引，先从索引表中查询出符合条件记录的ROWID，由于ROWID是记录的物理地址，因此可以根据ROWID快速的定位到具体的记录，**表中的数据非常多时，引用索引带来的查询效率非常可观**。

1. 一种独立于表的模糊对象，可以存储在与表不同的磁盘或表空间
2. 索引被删除或破坏，不会对表产生影响，其影响的只是查询的速度
3. 索引一旦建立，Oracle管理系统会对其进行自动维护，且由Oracle管理系统决定何时引用索引，用户不用在查询语句中指定使用哪个索引
4. 在删除一个表时，所有基于该表的索引会自动被删除
5. 通过指针加速Oracle服务器的查询速度
6. 通过快速定位数据的方法，减少磁盘I/O

### 创建索引

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

### 索引类型

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

### 查询索引 

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

### 删除索引 DROP INDEX

1. 不可回滚
2. 只有索引的拥有者或拥有DROP ANY INDEX权限的用户才可以删除

```sql
DROP INDEX emp_last_name_index;
```

### 修改索引

#### 重命名索引

```sql
ALTER INDEX 旧索引名
RENAME TO 新索引名;
```

#### 合并索引、重新构造索引

- 索引建好后，经过很长一段时间的使用，索引表中存储的空间会产生一些碎片，导致索引的查询效率会有所下降，这个时候可以合并索引
- 原理是按照索引规则重新分类存储一下，或者也可以选择删除索引重新构造索引。

```sql
--合并索引
ALTER INDEX 索引名 COALESCE;

--重新构造
ALTER INDEX 索引名 REBUILD;
```

## 约束 CONSTRAINT

约束的作用是定义规则（最重要），确保完整性。
注意:

1. 若不指定约束名，ORACLE自动按SYS_Cn格式指定约束名
2. 创建约束在建表同时，修改约束在建表之后
3. 在表级定义约束-表约束，列级定义约束-列约束
4. 通过数据字典或视图查询约束


### 约束类型

#### 非空约束 NOT NULL

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

#### 唯一约束 UNIQUE (允许出现多个空值)

- 命名标准:uk_或_uk

```sql
CREATE TABLE 表名
(
 列1 数据类型 [CONSTRAINT 约束名] UNIQUE
,列2 数据类型
);
```

#### 主键约束 PRIMARY KEY (column)(非空且唯一)

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

#### 外键约束 FOREIGN KEY

- 作为外键的列，其对应的关联表的列必须是主键。
- 命名标准:fk_或_fk

**列级**

```sql
CREATE TABLE 表名
(
 列1 数据类型 [CONSTRAINT 约束名] [DATATYPE] REFERENCES 要关联的表(列,...)
 -- DATATYPE 仅数据类型关联
 [ON DELETE CASCADE]  --级联删除
 [ON DELETE SET NULL] --级联置空
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

#### 检查约束 CHECK(条件)

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

### 表级与列级约束

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

### 修改表约束

1. 约束可以添加或删除，但不可以修改
2. 有效或无效化约束
3. 添加NOT NULL约束要用MODIFY关键字

#### 一、删除 DROP

```sql
ALTER TABLE 表
DROP CONSTRAINT 约束名;
```

```sql
ALTER TABLE 表
DROP PRIMARY KEY 约束名 [CASCADE];   
--CASCADE 删除主键约束的同时，（如果存在）以该主键（作为关联表的外键）建立的外键约束也一起删除。
```

#### 二、修改 MODIFY 在修改之前表里面不要有任何数据

```sql
ALTER TABLE 表
MODIFY (列名 数据类型 约束);
```

#### 三、改名

```sql
ALTER TABLE 表
RENAME CONSTRAINT 旧的约束名 TO 新的约束名;
```

#### 四、添加 ADD 添加的是表约束

```sql
ALTER TABLE 表
ADD 约束;
```

```sql;
ALTER TABLE table1
ADD CONSTRAINT table_fk FORREIGN KEY (no2) REFERENCES table2(no2);
```

#### 五、无效化 DISABLE COSTRAINT

```sql
ALTER TABLE 表
DISABLE CONSTRAINT 约束名;
```

#### 激活 ENABLE CONSTRAINT

```sql
ALTER TABLE 表
ENABLE CONSTRAINT 约束名;
```

- 当定义或激活UNIQUE或PRIMARY KEY约束时，系统自动创建UNIQUE或PRIMARY KEY索引

### 查询约束

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

## 同义词SYNONYM

### SCOTT用户权限不足

登入SYSDBA授予同义词权限

```sql
GRANT CREATE SYNONYM TO SCOTT;
```

### 同义词

- Oracle中同义词是任何表、视图、物化视图、序列、存储过程、函数、包、类型、JAVA类对象、用户定义类型,或是其他的同义词的别名。
- 由于其只是一个别名，所以除了在数据字典中的定义不占任何空间

1. 方便访问其他用户的对象
2. 缩短对象名字的长度

同义词常用于安全和方便

1. 伪装对象名称和其所有者。
2. 为分布式数据库远程对象提供位置透明性
3. 简化数据库用户访问对象SQL语句
4. 当行使精细化访问控制时提供类似指定视图的访问限制

### 可以创建public和private同义词

- public同义词属于PUBLIC组，每个用户都可以访问。
- private同义词属于对象所有者，只有其显式授权后其他用户才可访问。

同义词的优势体现在如果其底层的对象重命名或者转移，那么只需要重定义该同义词。基于该同义词的应用则无需任何修改。

### 创建同义词

```sql
CREATE [PUBLIC] SYNONYM 赋予同义词
FOR 对象;
```

```sql
CREATE SYNONYM synonym_test
FOR employees;
```

### 删除同义词

```sql
DROP SYNONYM m_seq;
```

**删除public同义词，必须加PUBLIC关键字**

```sql
DROP PUBLIC SYNONYM m_seq;
```

### 同义词数据字典

#### user_sysnonyms 记录了当前用户所拥有的同义词

- SYNONYM_NAME NOT NULL VARCHAR2(30) 同义词的名称  
- TABLE_OWNER VARCHAR2(30) 所指向的对象属主  
- TABLE_NAME NOT NULL VARCHAR2(30) 所指向的对象名称  
- DB_LINK VARCHAR2(128) 数据库链接

#### all_synonyms 记录了当前用户所能使用的所有同义词，包括私有同义词和公共同义词

#### dba_synonyms 记录了数据库中所有的同义词，包括每个用户创建的私有同义词和DBA创建的公共同义词

这个视图只有DBA能够访问，它的结构除了包含数据字典user_synonyms的所有列外，还有一个列owner代表同义词的创建者。

```sql
查询当前用户创建了哪些同义词，它们各代表哪个用户的哪个对象
SELECT synonym_name
      ,table_name
      ,table_owner 
FROM USER_SYNONYMS; 
```

## 布尔运算

**对查询的结果集进行的运算**

- 并集 UNION / UNION ALL
  - UNION 去重
  - UNION ALL 不去重
- 交集 INTERSECT
- 差集 MINUS

```sql
SELECT employee_id,job_id
FROM employees
UNION
SELECT employee_id,job_id
FROM job_history;
```

- 在SELECT列表中的列名和表达式在数量和数据类型上相对应
- 括号可以改变相对应顺序
- ORDER BY子句
  - 只能在语句最后出现
  - 可以使用第一个查询中的列名，别名或相对位置
- 除UNION ALL以外，系统会自动将重复的记录删除
- 系统第一个查询的列名显示在输出中
- 除UNION ALL之外，系统会自动按照第一个查询中的第一个列的升序排列

```sql
使用相对应位置排序举例
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
查询所有员工的last_name,dpartment_id,department_name
SELECT last_name,e.department_id,department_name
FROM employees e,departments
易错
SELECT last_name,department_id,TO_CHAR(NULL)
FROM employees
UNION
SELECT TO_CHAR(NULL),department_id,department_name
FROM departments;
```

### 可用相关更新

# PL/SQL

## PL/SQL基本语法

### 准备工作

```sql
SET SERVEROUTPUT ON
```

```plsql
 hellowrold 程序
BEGIN
  dbms_output.put_line('hello world');   --一次只能打印一行，不能打印多行(FOR可以)
END;
/
```

[语法格式]

```plsql
--DECLARE
  --声明的变量、类型、游标
BEGIN
  --程序的执行部分（类似于java里的main()方法）
  dbms_output.put_line('helloworld');
--EXCEPTION
  --针对BEGIN块中出现的异常，提供处理的机制
  --WHEN .... THEN ...
  --WHEN  .... THEN ...
END;
```

- PL/SQL程序块的结束并不自动结束事物，必须显示地使用COMMIT语句和ROLLBACK语句。
    - （这句话只适用于`SQL*PLUS`中）至少PL/SQL developer中自动提交
- 在PL/SQL语句中，不能直接使用组函数，只能在其嵌入的SQL语句中使用

### EXECUTE语句

- EXECUTE语句后面跟PL/SQL语句，可执行。相当于：

```plsql
BEGIN
  所跟的语句；
END;
```

```plsql
BEGIN
 EXECUTE IMMEDIATE '
   CREATE OR REPLACE PROCEDURE show_time IS
   BEGIN
     DBMS_OUTPUT.PUT_LINE(''当前时间：''||SYSDATE);
   END;
 ';
END;
```

## 变量

### 命名规范

1. 必须以英文字母开始
2. 可以包含一个或多个英文字母或数字
3. 可以包含最多30个字符
4. 只能包含特殊字符：$, _, #
5. 不能与数据库的表或者列同名
6. 不能是关键字

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

#### Oracle处理变量，参数，表和列的优先级

1. PL/SQL首先检查数据库表中列的名字
2. 数据库表中的列名优先于本地变量名
3. 本地变量名和形参名优先于数据库的表名

#### 变量的声明和初始化

- 在引用PL/SQL程序块中的变量之前，必须在声明段声明所有的变量。
- 在声明变量的同时，可以为变量赋初值，但并不是必须的
- 如果在一个变量声明中引用了其他变量，一定要确保在之前的语句中已经声明了所引用的变量

**语法**

```plsql
变量名 [CONSTANT] 数据类型 [NOT NULL]
    [:= | DEFAULT 表达式];   --初始化赋值
```

- CONSTANT 限制所声明的变量的值不能更改，即常量
   - 常量声明必须初始化
- NOT NULL 非空约束
   - 非空变量声明时必须初始化
- := 或 DEFAULT 初始化赋值


**直接在SQL环境中定义**

- 只能是SQL允许的数据类型
- 不能进行初始化

```plsql
VARIABLE 变量名 数据类型;
```

##### 例1
```plsql
DECLARE
 v_dogid NUMBER(10) NOT NULL
   := 38;
 v_name VARCHAR2(25)
   := 'White Tiger';
 c_color CONSTANT VARCHAR2(15)
   := 'White';
 v_birthday DATE;
BEGIN
 ...
END;
```

##### 例2

```plsql
DECLARE 
 v_desc VARCHAR2(100)
   := '中国妇女革命先锋: ';
 v_pioneer VARCHAR2(25);  --默认空值 NULL
BEGIN
  DBMS_OUTPUT.PUT_LINE(v_desc || v_pioneer);
  v_pioneer := '苏妲己';
  DBMS_OUTPUT.PUT_LINE(v_desc || v_pioneer);
END;

*******************************
中国妇女革命先锋: 
中国妇女革命先锋: 苏妲己
PL/SQL procedure successfully completed
```

##### 例3

```plsql
DECLARE 
 v_desc VARCHAR2(100)
   := '中国妇女革命先锋: ';
 v_pioneer VARCHAR2(25)
   := '潘金莲';
BEGIN
  DBMS_OUTPUT.PUT_LINE(v_desc || v_pioneer);
  v_pioneer := '苏妲己';
  v_desc := '历史上的成功女性：';
  DBMS_OUTPUT.PUT_LINE(v_desc || v_pioneer);
END;

********************************
中国妇女革命先锋: 潘金莲
历史上的成功女性：苏妲己
PL/SQL procedure successfully completed
```

#### 1. 使用一个变量

```plsql
DECLARE
  --声明一个变量
  v_name VARCHAR2(25);
BEGIN
  --通过 SELECT ... INTO ... 语句为变量赋值
 SELECT last_name 
 INTO v_name
 FROM employees
 WHERE employee_id = 186;
 -- 打印变量的值
 dbms_output.put_line(v_name);
END;
```

#### 2. 使用多个变量

```plsql
DECLARE
  --声明变量
  v_name VARCHAR2(25);
  v_email VARCHAR2(25);
  v_salary NUMBER(8, 2);
  v_job_id VARCHAR2(10);
BEGIN
  --通过 SELECT ... INTO ... 语句为变量赋值
  --被赋值的变量与SELECT中的列名要一一对应
 SELECT last_name, email, salary, job_id 
 INTO v_name, v_email, v_salary, v_job_id
 FROM employees
 WHERE employee_id = 186;
 -- 打印变量的值
 dbms_output.put_line(v_name || ', ' || v_email || ', ' ||  v_salary || ', ' ||  v_job_id);
END;
```

#### oracle 绑定变量（bind variable）

- 在oracle 中，对于一个提交的sql语句,存在两种可选的解析过程, 一种叫做硬解析,一种叫做软解析.

一个硬解析需要经解析,制定执行路径,优化访问计划等许多的步骤.硬解释不仅仅耗费大量的cpu，更重要的是会占据重要的们闩（latch）资源，严重的影响系统的规模的扩大（即限制了系统的并发行）， 而且引起的问题不能通过增加内存条和cpu的数量来解决。之所以这样是因为门闩是为了顺序访问以及修改一些内存区域而设置的，这些内存区域是不能被同时修改。当一个sql语句提交后，oracle会首先检查一下共享缓冲池（shared pool）里有没有与之完全相同的语句，如果有的话只须执行软分析即可，否则就得进行硬分析。

而唯一使得oracle 能够重复利用执行计划的方法就是采用绑定变量。绑定变量的实质就是用于替代sql语句中的常量的替代变量。绑定变量能够使得每次提交的sql语句都完全一样。

- 普通sql语句：

```plsql
SELECT fname, lname, pcode FROM cust WHERE id = 674;
SELECT fname, lname, pcode FROM cust WHERE id = 234;
SELECT fname, lname, pcode FROM cust WHERE id = 332;
```

- 含绑定变量的sql 语句：

```plsql
SELECT fname
     ,lname
     ,pcode 
FROM cust 
WHERE id = :cust_no;
```

- Sql*plus 中使用绑定变量：

```plsql
sql> variable x number;
sql> exec :x := 123;
sql> SELECT fname, lname, pcode FROM cust WHERE id =:x;
```

- pl/sql很多时候都会自动绑定变量而无需编程人员操心，即很多你写得sql语句都会自动利用绑定变量，如下例所示：

```plsql
create or replace procedure dsal(p_empno in number)
as
  begin
    update emp
    set sal=sal*2
    where empno = p_empno;
    commit;
  end;
/
```

- 也许此时你会想要利用绑定变量来替代p_empno,但是这是完全没有必要的，
    - 因为在pl/sql中，引用变量即是引用绑定变量。但是在pl/sql中动态sql并不是这样。
- 在vb，java以及其他应用程序中都得显式地利用绑定变量。对于绑定变量的支持不仅仅限于oracle,其他RDBMS如SQLserver也支持这一特性。

- 但是并不是任何情况下都需要使用绑定变量，　下面是两种例外情况：
1. 对于隔相当一段时间才执行一次的SQL语句，这是利用绑定变量的好处会被不能有效利用优化器而抵消
2. 数据仓库的情况下。

### 字符分隔符 q'操作符

- `q'`操作符声明一个定界符
    - 可以指定任何字符作为定界符，只要这个字符没有出现在字符串中
    - 定界符：用来在字符串中使用特殊字符（如：'）,在两个定界符中可以当作普通字符使用
- 如果没有使用q'操作符定义定界符，就必须重复字符串中的单引号。此时，第一个单引号实际上是逃逸符号（即去掉紧随其后的一个单引号的特殊含义，而作为一个普通字符处理）

#### 例1

```plsql
DECLARE
 v_special_day VARCHAR2(250);
BEGIN
  v_special_day := q'!Happy Woman's Day on 8th March!';
  --q'将!作为定界符
  DBMS_OUTPUT.PUT_LINE(v_special_day);
  
  v_special_day := q'[TO many People,"today']'; 
  --q' 将[]作为定界符
  DBMS_OUTPUT.PUT_LINE(v_special_day);
  
  v_special_day := '你好''呀';
  --''逃逸符号
  DBMS_OUTPUT.PUT_LINE(v_special_day);
END;

******************
Happy Woman's Day on 8th March
TO many People,"today'
你好'呀
PL/SQL procedure successfully completed
``` 

#### 定界符

**简单定界符**

| 操作符 |    含义    |
| :----: | :-------: |
|   +    | 加法运算符 |
|   -    | 减法运算符 |
|   *    | 乘法运算符 |
|   /    | 除法运算符 |
|   =    | 相等操作符 |
|   ;    | 语句结束符 |
|   @    | 远程访问符 |

**组合定界符**

| 操作符 |     含义      |
| :----: | :-----------: |
|  `||`  |   连接操作符   |
|   :=   |   赋值操作符   |
|   !=   |   不等运算符   |
|   <>   |   不等运算符   |
|   /*   | 开始注释定界符 |
|   */   | 结束注释定界符 |
|   --   |   单行注释符   |

### 变量的数据类型

#### 标量
- 只保持一个单一的值，而这个值依赖与变量的数据类型
- 即：
    - SQL中的数据类型
#### 组合
- 组合数据类型包括内部元素（结构），而这些元素既可以是标量数据类型也可以是组合数据类型。
- RECORD（记录）和PL/SQL TABLE就属于组合数据类型
#### 引用
- 引用数据类型保持指向一个存储位置的指针值

#### 大对象LOB

- 大对象数据类型保持被称为定位器（指针）的值，这个定位器声明在表之外的大对象（声音，图形）的位置（地址）
- 在数据库中，表中的列可以是LOB数据类型（CLOB, BLOB等)
- LOB数据类型，可以在数据库中存储大量的无结构数据块（正文，图形，声音和影像信息）
- 存储量最多可达128T（数据量的大小取决于数据块的大小）
- LOB数据类型允许高效，随机和分段的访问大量的无结构数据

##### CLOB
- 用于在数据库中存储单字节的大数据对象，如演讲稿，说明书或简历等

##### BLOB
- 用于在数据库中存储大的二级制对象，如图片或幻灯片等。当从数据库中提取这样的数据或向数据库中插入这样的数据时，数据库并不解释这些数据。使用这些数据的外部应用程序必须自己解释这些数据。

##### DBMS_LOB

- 对于CLOB和BLOB数据类型的列，许多操作是不能直接使用Oracle的数据库命令来完成的，因此，Oracle提供了一个叫DBMS_LOB的PL/SQL软件包来维护LOB数据类型的列

##### BFILE

- 用于在数据库外的操作系统文件中存储大的二级制对象，如电影胶片等。
- 与其他LOB数据类型不同，BFILE数据类型是外部数据类型。
- BFILE类型的数据是存储在数据库之外的，他们可能是操作系统文件。实际上，数据库中只存储了BFILE的一个指针，因此定义为BFILE数据类型的列是不能通过Oracle的数据库命令来操作的，这些列只能通过操作系统命令或者第三方软件来维护。

##### NCLOB
用于在数据库中存储NCHAR类型的单字节或定长字节的Unicode大数据对象。
#### (全局变量)宿主变量（主机变量）(替代变量和绑定变量)
- 在PL/SQL中使用非PL/SQL的变量
- 宿主变量是在调用PL/SQL程序的环境中声明的

**替代变量和绑定变量**

- 因为PL/SQL本身没有输入或输出功能，所以必须依赖于执行PL/SQL程序的环境将变量的值传入或传出PL/SQL程序块。

- 在`SQL*Plus`环境中，可以使用`SQL*Plus`的替代变量将运行时的值传给PL/SQL程序块。在PL/SQL程序块中也可以使用前导的&符号引用替代变量，就像在SQL语句中引用`SQL*PIus`的替代变量一样。在PL/SQL程序块执行之前，正文的值被替代进PL/SQL程序块中。因此，不能够使用循环为替代变量赋予不同的值，而只有一个值将替代这个替代变量。
- 绑定变量(bind variables)是在宿主环境（调用PL/SQL的程序或工具，如`SQL*Plus`)中创建的变量。也正是由于这一原因，绑定变量有时也被称为宿主变量(host variables)。
- 绑定变量是在使用（或调用）PL/SQL的环境中创建的，而不是在PL/SQL程序的声明段中定义的。在一个PL/SQL程序块中声明的所有变量只在执行这个程序块时可以使用。而在这个程序块执行之后，这些变量所使用的内存就都释放了。然而，绑定变量则不同，在程序块执行之后，绑定变量依然存在并可以访问。正因为如此，绑定变量可以在多个子程序（程序块）中使用。绑定变量既可以用在SQL语句中，也可以用在PL/SQL程序块中，就像其他任何类型的变量一样。也可以将绑定变量作为运行时的值传给PL/SQL子程序或从PL/SQL子程序中传出。
- 在`SQL*Pus或SQL Developer`中创建一个绑定变量，需要使用`SQL*Plus`的VARIABLE命令，可以使用VARIABLE命令定义NUMBER和VARCHAR2类型

`SQL*Plus`变量，如

```
VARIABLE g_dog_weight NUMBER
VARIABLE g_pioneer VARCHAR2(25)
```

- 当使用`SQL*Plus`客开发和执行一个PL/SQL程序是，`SQL*Plus`就成了这个PL/SQL程序的宿主环境。在`SQL*Plus`中声明的变量被称为宿主变量（绑定变量）。同理：Oracel Form。

SQL Developer也可以引用绑定变量并通过使用PRINT命令显示这个绑定变量的值。可以通过在绑定变量之前冠以冒号(：)的方式在PL/SQL程序块中引用绑定变量。
- 在PL/SQL中使用`SQL*Plus`的变量（绑定变量），必须冠以冒号。在PL/SQL程序段执行之后，这个变量仍然会存在。
   - 如果绑定的变量是NUMBER类型，那么不能指定精度和数值。
   - 如果绑定的变量是VARCHAR2类型，那么可以指定字符串的长度（字节）

```
--SQL*Plus
VARIABLE g_dog_weight NUMBER

--PL/SQL
BEGIN 
  :g_dog_weight := 38;
END;
```

##### 例

```
--SQL*PLUS
VARIABLE g_ename VARCHAR2(15);
VARIABLE g_sal NUMBER;
VARIABLE g_dept_id NUMBER;

--PL/SQL
BEGIN
  SELECT last_name
        ,salary
        ,department_id
  INTO :g_ename
      ,:g_sal
      ,:g_dept_id
  FROM employees
  WHERE employee_id = 112;
END;

--SQL*PLUS
PRINT g_ename;
PRINT g_sal;
PRINT g_dept_name;
```

##### 例

```
--SQL*PLUS
SET VERIFY OFF;  --关闭替换变量内容的显示
SET AUTOPRINT ON;

VARIABLE g_ename VARCHAR2(20);
VARIABLE g_sal NUMBER;
VARIABLE g_dept_id NUMBER;

--PL/SQL
DECLARE
 v_emp_id NUMBER(10) := &employee_id;
BEGIN 
  SELECT last_name
        ,salary
        ,department_id
  INTO :g_ename
      ,:g_sal
      ,:g_dept_id
  FROM employees
  WHERE employee_id = v_emp_id;
END;
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
- NOT NULL 约束不适用与使用%TYPE属性声明的变量。
    - 但是，如果使用%TYPE属性声明的变量是基于一个定义了NOT NULL约束的列，那么是可以将空值赋予这个变量的。

#### 优点

1. 避免由于数据类型不匹配或精度不对所引起的错误
2. 避免代码的硬编码（变量的数据类型和精度必须与操作的列或变量相匹配）
3. 如果列的定义发生了变化，则不再需要修改变量的声明。如果为某个特定的表声明了几个变量并且没有使用%TYPE属性声明，当声明变量所基于的列（的定义）校变更时，PL/SQL是在编译这个程序头时定变量的数据类型和大小（精度）的。这样也就确保使用%TYPE属性声明的变量总是所基于（操作）的列相匹配。

#### 缺点

1. 当然%TYPE属性也不例外。%TYPE属性是具有一定的额外开销的，因为为了获取数据库中列数据类型，PL/SOL隐含地发出了一个查询(SELECT)语句。如果PL/SQL代码是存放在客户工具中的，那么在每次执行PL/SQL程序块时都必须执行这个查询语句。
2. 如果PL/SQL程序代码是存储过程（也可以是存储函数），那么列的定义或变量的定义是作为P-code（parsed code)的一部B存储在数据库中的，因此也就没有以上所说的额外开销了。
3. 然而，如果表的定义发生了变化，系统会强行重新编译相关的PL/SQL程序代码。在Oracle11g和Oracle12c中放宽了这一方面的限制，如果在修致表的定义时没有涉及到PL/SQL程序代码所使用的列，那么这个PL/SQL程序代码块仍然是有效的，因此也就不需要重新编译了。

### 布尔变量的声明与使用

#### 布尔变量的特性：

- 只有值TRUE/FALSE/NULL可以赋给一个布尔变量
- 可以通过AND/OR/NOT这些逻辑操作符对变量进行比较
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
 |   BETWEEN ... AND ...  |
 |  IN(...)   |
 |   LIKE  |
 |  IS NULL   |
 
### 布尔条件

**TRUE/FALSE/NULL**


#### NULL
1. 涉及空值的简单比较总是产生NULL
2. 将逻辑运算符NOT应用于NULL产生NULL
    - 注：语句：NOT NULL 非空值
3. 在条件控制语句中，如果条件为NULL，则不执行
4. 在算术表达式中，只要有NULL，则整个表达式的结果就是NULL

#### 逻辑运算符
##### AND:
- FALSE > NULL > TRUE

- FALSE AND NULL = FALSE
- TRUE AND NULL = NULL
- TRUE AND FALSE = FALSE

##### OR

- TRUE > NULL > FALSE
- FALSE AND NULL = NULL
- TRUE AND NULL = TRUE
- TRUE OR FALSE = TRUE

##### NOT

- NOT TRUE = FALSE
- NOT FALSE = TRUE
- NOT NULL = NULL


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

```
DECLARE
 v_mumdog_sex CHAR(1) := 'F';
 v_mumdog_weight NUMBER(5,2) := 63;
BEGIN
  DECLARE
   v_babydog_sex CHAR(1) := 'M';
   v_baby_dog_weight NUMBER(5,2) := 3.8;
  BEGIN
    DBMS_OUTPUT.PUT_LINE(v_babydog_sex);
    DBMS_OUTPUT.PUT_LINE(v_baby_dog_weight);
    DBMS_OUTPUT.PUT_LINE(v_mumdog_sex);
    DBMS_OUTPUT.PUT_LINE(v_mumdog_weight);
  END;
    DBMS_OUTPUT.PUT_LINE(v_mumdog_sex);
    DBMS_OUTPUT.PUT_LINE(v_mumdog_weight);
    
    DBMS_OUTPUT.PUT_LINE(V_baby_dog_sex);
END;
    /*
ORA-06550: 第 17 行, 第 26 列: 
PLS-00201: 必须声明标识符 'V_BABY_DOG_SEX'
ORA-06550: 第 17 行, 第 5 列: 
PL/SQL: Statement ignored
    */
```

#### 例：变量重名

```
DECLARE 
  person_name VARCHAR2(25) := '外';
  person_id NUMBER(10) := 1;
BEGIN
  DECLARE
   person_name VARCHAR2 (10):= '内';
   person_id NUMBER := 0; 
  BEGIN
    DBMS_OUTPUT.PUT_LINE(person_name || person_id);
  END;
  
  DBMS_OUTPUT.PUT_LINE(person_name || person_id);
END;

******************
内0
外1
PL/SQL procedure successfully completed
```

#### 外层定义标号`<<label>>`

注意：GOTO语句中的`<<label>>`

```
<<label>>
DECLARE
 ...
BEGIN
 ...
END label;
```

```
<<father>>
DECLARE 
  person_name VARCHAR2(25) := '外';
  person_id NUMBER(10) := 1;
BEGIN
  <<son>>
  DECLARE
   person_name VARCHAR2 (10):= '内';
   person_id NUMBER := 0; 
  BEGIN
    DBMS_OUTPUT.PUT_LINE(person_name || person_id);
  END son;
  
  DBMS_OUTPUT.PUT_LINE(person_name || person_id);
END father;

******************
内0
外1
PL/SQL procedure successfully completed
```

### INTO子句（SQL中禁止）

- 通过SELECT子句提取（查找到）数据（表中列的值），而通过INTO子句将提取的数据存放在PL/SQL的变量中。

```
SELECT 查询列表
INTO [变量名[,变量名]...]
     [记录名]
FROM 表
[WHERE 条件表达式];
```

- 在PL/SQL的SELECT语句中，INTO子句是强制性的（必须存在），
- 并且INTO子句只能放在SELECT子句和FROM子句之间
- INTO子句被用来指定变量，这些变量被用来存放从SELECT子句中返回的值。
- INTO子句中必须为每一个SELECT语句选择的项（列/表达式）指定一个变量（PL/SQL变量或者宿主变量等），并且变量的顺序必须与所选择的项相对应。
- 每一个PL/SQL的SELECT语句必须返回且只能返回一行数据。

#### 例1

```
DECLARE
 v_dept_id departments.department_id%TYPE;
 v_dept_name departments.department_name%TYPE;
BEGIN
  SELECT department_id
        ,department_name
  INTO v_dept_id
      ,v_dept_name
  FROM departments
  WHERE department_id = 80;
END;
```

### 常量 CONSTANT

- PL/SQL中使用

```
常量名 CONSTANT 数据类型 [初始化];
```

## 流程控制语句

### 条件判断

#### 1)IF

```plsql
IF …… THEN
……;
ELSIF …… THEN
……;
ESLE
……;
END IF;
```

```plsql
DECLARE
 v_age NUMBER(3) := &p_age;
BEGIN
  IF v_age < 18 THEN
    DBMS_OUTPUT.PUT_LINE('未成年');
  ELSIF v_age < 60 THEN
    DBMS_OUTPUT.PUT_LINE('中年');
  ELSE 
    DBMS_OUTPUT.PUT_LINE('老年');
  END IF;
END; 
```

```plsql
/*
查询150号员工的工资
若工资大于10000，则打印'salary > 10000'，
若在5000到1000之间则打印'5000 <= salary <= 10000'
否则打印'salary < 5000'
*/
-- 1.
DECLARE
  v_emp_sal employees.salary%type;
BEGIN
  SELECT salary 
  INTO v_emp_sal
  FROM employees
  WHERE employee_id = 150;
  
  IF v_emp_sal > 10000 THEN 
    dbms_output.put_line('salary >= 10000');
  ELSIF v_emp_sal BETWEEN 5000 AND 10000 THEN 
    dbms_output.put_line('5000 <= salary <= 10000');
  ELSE 
    dbms_output.put_line('salary < 5000');
  END IF;
END;

-- 2.使用赋值给v_temp表来打印
DECLARE 
 v_emp_sal employees.salary%type;
 v_temp VARCHAR2(25);
BEGIN
  SELECT salary
  INTO v_emp_sal
  FROM employees
  WHERE employee_id = 150;
  
  IF v_emp_sal > 10000 THEN  
    v_temp := 'salary > 10000';
  ELSIF v_emp_sal BETWEEN 5000 AND 10000 THEN 
    v_temp := '5000 <= salary <= 10000';
  ELSE 
    v_temp := 'salary < 5000';
  END IF;
  
  dbms_output.put_line(v_temp);
END;
```

#### 2)CASE  --DECODE()只能在SQL中使用

1. CASE语句

```plsql
CASE 选择器
 WHEN 表达式 THEN...
 WHEN   ... THEN...
   ...
 [ELSE...]
END；
```

2. CASE表达式

```plsql
CASE
  WHEN 布尔条件表达式 THEN...
   ...
  [ELSE...]
END;
```

##### 例1

```plsql
/*
查询150号员工的工资
若工资大于或等于10000，则打印'salary >= 10000'，
若在5000到1000之间则打印'5000 <= salary < 10000'
否则打印'salary < 5000'
*/

DECLARE
 v_emp_sal employees.salary%type;
 v_temp VARCHAR2(25);
BEGIN
  SELECT salary
  INTO v_emp_sal
  FROM employees
  WHERE employee_id = 150;
  v_temp :=
  CASE TRUNC(v_emp_sal/5000)
    WHEN 0 THEN 'salary < 5000'
    WHEN 1 THEN '5000 <= salary < 10000'
    WHEN 2 THEN 'salary >= 10000'
  END;
  dbms_output.put_line(v_temp);
END;
```

##### 例2

```plsql
/*
查询122号员工的job_id，
若其值为'IT_PROG' 则打印'A'
       'AC_MGT' 则打印'B'
       'AC_ACCOUNT' 则打印'C'
       否则打印'D'
*/

DECLARE
 v_emp_job employees.job_id%type;
 v_temp VARCHAR2(25);
BEGIN
  SELECT job_id
  INTO v_emp_job
  FROM employees
  WHERE employee_id = 122;
  v_temp :=
  CASE v_emp_job
    WHEN 'IT_PROG' THEN 'A'
    WHEN 'AC_MGT' THEN 'B'
    WHEN 'AC_ACCOUNT' THEN 'C'
  ELSE 'D'
  END;
  dbms_output.put_line(v_temp);
END;
```

##### 例3

```plsql
DECLARE
 v_emp_id NUMBER := &p_emp_id;
 v_last_name employees.last_name%TYPE;
 v_job_id employees.job_id%TYPE;
 v_sal employees.salary%TYPE;
 
 v_sal_increment NUMBER;
BEGIN
  SELECT job_id
  INTO v_job_id
  FROM employees
  WHERE employee_id = v_emp_id;
  
  CASE v_job_id
    WHEN '%IT%' THEN
      v_sal_increment := 1.45;
    WHEN '%AC%' THEN
      v_sal_increment := 1.3;
    ELSE
      v_sal_increment := 1.1;
  END CASE;
  
  SELECT employee_id
        ,last_name
        ,salary * v_sal_increment
  INTO v_emp_id
      ,v_last_name
      ,v_sal
  FROM employees
  WHERE employee_id = v_emp_id;
 
  DBMS_OUTPUT.PUT_LINE(v_job_id ||'部门的'|| v_last_name ||'，加薪后的工资：' || v_sal);
END;     
```

##### 思考：为什么例2的CASE...END;不能有CASE，而例3的CASE...END;必须有END CASE；

- 例2中的CASE语句

```plsql
变量 :=
CASE 选择器
 WHEN 条件值 THEN
   赋值 --没有分号，不是语句，只是一个值
 END;  --不需要END CASE;
```

- 例3中的CASE语句

```plsql
CASE 选择器
  WHEN 条件值 THEN
    语句; --有分号；代码块；不是简单的值。
END CASE;
```

### 循环结构

- 初始化条件 循环体 循环条件 迭代条件

- 使用循环：
   - 如果语句在循环中至少执行一次，一般使用LOOP
   - 如果在每次开始重复是都必须测试条件，一般使用WHILE
   - 如果重复的次数已知，一般使用FOR

#### 1)LOOP

```plsql
LOOP
执行语句；
...
EXIT [WHEN 条件表达式];
--如果EXIT语句放在循环体后面，则必定先执行一次循环
--在不满足条件时，也会多执行一次执行
--如果EXIT语句放在循环体前面，则先检查是否满足条件
END LOOP;
```

```plsql
--使用循环语句打印1~100
-- 1.
 DECLARE
 v_i NUMBER(10) := 1;
BEGIN
  LOOP
    dbms_output.put_line(v_i);
  EXIT WHEN v_i >= 100;
  v_i := v_i + 1;
  END LOOP;
END;
 
--2.
 DECLARE
 v_i NUMBER(10) := 1;
BEGIN
  LOOP
    dbms_output.put_line(v_i);
    v_i := v_i + 1;
  EXIT WHEN v_i > 100;
  END LOOP;
END;
```

```plsql
--使用循环语句往表中插入数据

CREATE TABLE emp_p1
(
 emp_no NUMBER(10)
,hire_date DATE
,job VARCHAR2(20)
,sal NUMBER(10)
,depart_no NUMBER(10)
)

DECLARE 
  v_emp_no emp_p1.emp_no%TYPE;
  v_dept_no emp_p1.depart_no%TYPE := 12;
  v_sal emp_p1.sal%TYPE := 12990;
  v_hire_date emp_p1.hire_date%TYPE := TO_DATE('1999-02-02','yyyy-mm-dd');
  v_job emp_p1.job%TYPE := 'counter';
  
  v_counter NUMBER(2) := 1;
  v_max_num NUMBER(2) := 3;
BEGIN 
  SELECT NVL(MAX(emp_no),0)
  INTO v_emp_no
  FROM emp_p1;
  
  LOOP
    INSERT INTO emp_p1(emp_no,depart_no,sal,hire_date,job)
    VALUES((v_emp_no + v_counter),v_dept_no,v_sal,v_hire_date,v_job);
    
    v_counter := v_counter + 1;
    
    EXIT WHEN v_counter > v_max_num;
    
   END LOOP;
END;
```

#### 2)FOR

```plsql
FOR 循环计数器 IN [REVERSE] 下限……上限 LOOP
要执行的语句;
END LOOP;
```

- 每循环一次，循环变量自动加1；
- 如果下限和上限不是整数，则自动四舍五入，如果四舍五入后，下限大于上限则FOR循环不会被执行

**原则**

1. 只在循环中引用计数器，计数器在循环之外没有定义
2. 不用为计数器赋值，即不要将计数器变量赋值赋值符号左边
3. 上限和下限都不应该为null

##### 关键字REVERSE

- 反向FOR循环
- 使用关键字REVERSE，循环变量自动减1.
- 跟在REVERSE后的数字必须是从小到大的顺序，且必须是整数，不能是变量或表达式

##### 可以使用EXIT退出循环

- 只有EXIT WHEN 表达式中表达式的结果是TRUE才会执行EXIT；
- NULL或者FASLE不执行

```plsql
BEGIN
  FOR i IN 最小..最大 LOOP
    循环体;
    [EXIT [标号] WHEN 条件;]
  END LOOP;
END;
```

```plsql
--使用循环语句打印1~100
BEGIN
  FOR i IN 1..100 LOOP
    dbms_output.put_line(i);
  END LOOP;
END;
```

```plsql
--输出2~100之间的质数
DECLARE
  v_flag NUMBER(1) := 0;
BEGIN
   FOR i IN 2 .. 100 LOOP
       v_flag := 1;
       FOR j IN 2 .. SQRT(i) LOOP
           IF i MOD j = 0 THEN
              v_flag := 0;  
           END IF;        
       END LOOP;
       IF v_flag = 1 THEN
           dbms_output.put_line(i);
       END IF;
   END LOOP;
END;
```

#### 2)WHILE

- 在WHILE LOOP循环语句中，循环的条件必须放在WHILE和LOOP两个关键字之间，而循环的条件是每次开始循环前检查的。

```plsql
WHILE 条件 LOOP
  循环体;
END LOOP;
```

```plsql
CREATE TABLE dept_p1
(
 dept_no NUMBER(10)
,loc VARCHAR(50)
)

CREATE SEQUENCE dept_no_seq
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 99
 CYCLE
 NOCACHE;

DECLARE 
 v_dept_no dept_p1.dept_no%TYPE;
 v_loc dept_p1.loc%TYPE := '&p_loc';
 
 v_counter NUMBER(2) := 1;
 v_max_num NUMBER(2) := &p_max_num;
BEGIN
  WHILE v_counter <= v_max_num LOOP
    INSERT INTO dept_p1(dept_no,loc)
    VALUES(dept_no_seq.NEXTVAL,v_loc);
    
    v_counter := v_counter + 1;
  END LOOP;
END;
```

```plsql
--输出2~100的质数
DECLARE
  v_flag NUMBER(1):=1;
  v_i NUMBER(3):=2;
  v_j NUMBER(2):=2;
BEGIN
  WHILE (v_i<=100) LOOP
        WHILE v_j <= sqrt(v_i) LOOP
              IF (MOD(v_i,v_j) = 0) THEN v_flag := 0;
        END IF;
        v_j := v_j + 1;
        END LOOP;
  IF (v_flag=1) THEN dbms_output.put_line(v_i);
  END IF;
        v_flag :=1;
        v_j := 2;
        v_i :=v_i +1;
  END LOOP;
END;
```

#### 3)GOTO语句 无条件跳转到指定的标号中去 (非必要不使用)

```plsql
GOTO label;
……
<<label>>
```

```plsql
--输出2~100之间的质数
DECLARE
 v_flag NUMBER(5) := 1;
BEGIN
  FOR i IN 2 .. 100 LOOP
    FOR j IN 2 .. SQRT(i) LOOP
      IF MOD(i,j) = 0 THEN v_flag := 0;
      GOTO label;
      END IF;
    END LOOP;
    <<label>>
    IF v_flag = 1 THEN dbms_output.put_line(i);
    END IF;
    v_flag := 1;
  END LOOP;
END;
```

#### 循环的嵌套和标号

- 标号：`<<label>>`
   - 标号必须放在一个语句之前，可以是同一行
   - 在基本循环LOOP中，标号要放在关键字LOOP之前
   - 在FOR和WHILE循环中，标号要放在关键字FOR和WHILE之前。
   - 如果一个循环加上了标号，在END LOOP之后可以包含标号名（非强制）

```plsql
DECLARE
 v_i NUMBER := 1;
BEGIN
 <<i_LOOP>>
 LOOP
  v_i := v_i + 1;
  DBMS_OUTPUT.PUT_LINE(v_i);
  EXIT WHEN v_i >= 10;
 END LOOP i_LOOP;
END;
```

```plsql
DECLARE
 v_total NUMBER := 0;
 v_factorial NUMBER := 1;
 v_num NUMBER := &p_num;
 
BEGIN
  <<out_loop>>
  FOR i IN 1..v_num LOOP
    v_total := v_total + i;
    DBMS_OUTPUT.PUT_LINE('1~' || i || '的自然数之和为：' || v_total);
    
    <<inner_loop>>
    FOR j IN 1..v_num LOOP
      EXIT inner_loop WHEN ( i + j > 4 );
      v_factorial := v_factorial * j;
      DBMS_OUTPUT.PUT_LINE(j || '的阶乘为：' || v_factorial);
    END LOOP inner_loop;
    v_factorial := 1;
    
  END LOOP out_loop;
END;

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

#### CONTINUE语句

```plsql
BEGIN
  FOR i IN 最小..最大 LOOP
    循环体;
    [CONTINUE [标号] WHEN 条件;]
  END LOOP;
END;
```

```plsql
DECLARE
 v_max_num  NUMBER := &p_max_num;
 v_sum NUMBER := 0;
 v_fac NUMBER := 1;
BEGIN
  FOR i IN 1..v_max_num LOOP
    v_sum := v_sum + i;
  END LOOP;
  DBMS_OUTPUT.PUT_LINE('1~' || v_max_num || '自然数的和为：' || v_sum);
  
  FOR i IN 1..v_max_num LOOP
    v_fac := v_fac * i;
  END LOOP;
  DBMS_OUTPUT.PUT_LINE('1~' || v_max_num || '自然数的阶乘为：' || v_fac);
  
  v_sum := 0;
  <<t_loop>>
  FOR i IN 1..v_max_num LOOP
    CONTINUE t_loop WHEN ( MOD(i,2) = 0);
    v_sum := v_sum + i;
  END LOOP;
  DBMS_OUTPUT.PUT_LINE('1~' || v_max_num || '奇数的和为：' || v_sum);
END;
```

## 组合数据类型

1. 记录 RECODE
    - 将逻辑上相关的但是数据类型不同的数据当作一个逻辑单元来处理，一个记录可以包含多个不同的数据。 
2. 集合
    - 将一组（集合）数据当作一个单独的单元来处理。
    
- INDEX BY表
- 嵌套表
- 变长数组

- 注： 
   - 嵌套表和变长数组不属于PL/SQL的数据类型，是Oracle模式（用户）一级的表中有效的数据类型，
   - 而不能在用户的表中定义INDEX BY表类型的列


### 记录类型RECORD

一个记录即一组存储在若干字段中的相关联的数据，而记录中的每个字段都具有各自的名字和数据类型。

特性：
- 一定包含一个或多个被称为字段的组件。可以是任何的标量，记录或INDEX BY表的数据类型
- 与数据库中表的行不同
- 每一个定义的记录可以根据实际需要有任意多个字段
- 可以为记录赋初值或定义NOT NULL约束，没有初始化的默认值为null
- 可以声明和引用嵌套的记录
- 在从一个表中获取一行数据时，记录非常方便
- 如果两个记录具有完全相同的数据类型，可以将一个记录直接赋值给另一个记录


#### 自定义记录类型

```plsql
DECLARE
  TYPE 要定义的记录数据类型名称 IS RECORD(
    --字段声明
    变量1 数据类型 [约束],
    ... 
    --注意：没有分号 ;
  );
  变量名(记录名) 刚刚定义的数据类型名称 [赋初值];
  --创建变量
BEGIN
  --访问字段
  记录名.字段名 --可以当成一个变量使用
END;
```

```plsql

```

```plsql
DECLARE
  --定义一个记录类型
  TYPE customer_type IS RECORD(
    v_cust_name VARCHAR2(20),
    v_cust_id NUMBER(10));
  --声明自定义记录类型的变量
  v_customer_type customer_type;
BEGIN
  v_customer_type.v_cust_name := '刘德华';
  v_customer_type.v_cust_id := 1001;
  dbms_output.put_line(v_customer_type.v_cust_name||','||v_customer_type.v_cust_id);
END;
```

```plsql
DECLARE 
 TYPE person IS RECORD(
   name VARCHAR2(20)
  ,age NUMBER(3)
  );

 TYPE student IS RECORD(
   name VARCHAR2(20)
  ,age NUMBER(3)
  );
  
 Jac person;
 Jac_copy person;
 Tom student;
 
BEGIN
  Jac.name := 'Jac';
  Jac.age := 18;
  DBMS_OUTPUT.PUT_LINE(Jac.name || ' : ' || Jac.age);
  
  Jac_copy := Jac;
  --相同类型的（person）可直接赋值
  
  --Tom := Jac; 报错PLS-00382
  --student和person不是同一类型
END;
```

#### 自定义记录类型

```plsql
DECLARE
  --定义一个记录类型
  TYPE emp_record IS RECORD(
    v_name VARCHAR2(25),
    v_email VARCHAR2(25),
    v_salary NUMBER(8, 2),
    v_job_id VARCHAR2(10));
  --声明自定义记录类型的变量
  v_emp_record emp_record;
BEGIN
  --通过 SELECT ... INTO ... 语句为变量赋值
 SELECT last_name, email, salary, job_id 
 INTO v_emp_record
 FROM employees
 WHERE employee_id = 186;
 -- 打印变量的值
 dbms_output.put_line(v_emp_record.v_name || ', ' || v_emp_record.v_email || ', ' ||v_emp_record.v_salary || ', ' ||  v_emp_record.v_job_id);
END;
```

##### 使用 %TYPE 定义变量，动态的获取数据的声明类型

```plsql
DECLARE
  --定义一个记录类型
  TYPE emp_record IS RECORD(
    v_name employees.last_name%TYPE,
    v_email employees.email%TYPE,
    v_salary employees.salary%TYPE,
    v_job_id employees.job_id%TYPE);
  --声明自定义记录类型的变量
  v_emp_record emp_record;
BEGIN
  --通过 SELECT ... INTO ... 语句为变量赋值
 SELECT last_name, email, salary, job_id 
 INTO v_emp_record
 FROM employees
 WHERE employee_id = 186;
 -- 打印变量的值
 dbms_output.put_line(v_emp_record.v_name || ', ' || v_emp_record.v_email || ', ' ||  v_emp_record.v_salary || ', ' ||  v_emp_record.v_job_id);
END;
```

##### 使用 %ROWTYPE
- 利用%ROWTYPE属性声明一个能够存储一个表或视图中一整行数据的记录（变量）。
- 该记录中的每一个字段的名字和数据类型取自表或视图中相应的列。
- 数据类型动态变化
- 不需要在定义一个变量；直接就是变量而不再是数据类型

```plsql
DECLARE 
 记录名 要存储的表名/视图名%ROWTYPE;
 --不需要在定义一个变量；直接就是变量而不再是数据类型
 --变量名 记录名；错误
BEGIN
 ..
END;
```

```
DECLARE
--声明一个记录类型的变量
  v_emp_record employees%ROWTYPE;
BEGIN
  --通过 SELECT ... INTO ... 语句为变量赋值
 SELECT * 
 INTO v_emp_record
 FROM employees
 WHERE employee_id = 186; 
 -- 打印变量的值
 dbms_output.put_line(v_emp_record.last_name || ', ' || v_emp_record.email || ', ' ||  v_emp_record.salary || ', ' ||  v_emp_record.job_id  || ', ' ||  v_emp_record.hire_date);
END;
```

##### 赋值语句：通过变量实现查询语句

```plsql
DECLARE
  v_emp_record employees%ROWTYPE;
  v_employee_id employees.employee_id%TYPE;
BEGIN
  --使用赋值符号位变量进行赋值
  v_employee_id := 186;
  --通过 SELECT ... INTO... 语句为变量赋值
 SELECT * 
 INTO v_emp_record
 FROM employees
 WHERE employee_id = v_employee_id;
 -- 打印变量的值
 dbms_output.put_line(v_emp_record.last_name || ', ' || v_emp_record.email || ', ' ||  v_emp_record.salary || ', ' ||  v_emp_record.job_id  || ', ' ||  v_emp_record.hire_date);
END;
```

##### 通过变量实现DELETE、INSERT、UPDATE等操作

###### DELETE

```plsql
DECLARE
  v_emp_id employees.employee_id%TYPE;
BEGIN
  v_emp_id := 109;
  DELETE FROM employees
  WHERE employee_id = v_emp_id;
  --COMMIT;
END; 
```

###### INSERT

```plsql
DECLARE
 copy_emp_rec employees%ROWTYPE;
 
BEGIN
  SELECT *
  INTO copy_emp_rec
  FROM employees
  WHERE employee_id = 120;
  
  INSERT INTO copy_emp
  VALUES copy_emp_rec;
END;
```

```plsql
DECLARE
 copy_emp_rec copy_emp%ROWTYPE;
 
BEGIN
  SELECT *
  INTO copy_emp_rec
  FROM copy_emp
  WHERE employee_id = 120;
  
  INSERT INTO test_emp(emp_id,salary)
  VALUES(copy_emp_rec.employee_id
        ,copy_emp_rec.salary
        );
        
END; 
```

###### UPDATE

```plsql
DECLARE
 copy_emp_rec copy_emp%ROWTYPE;
 
BEGIN
  SELECT *
  INTO copy_emp_rec
  FROM copy_emp
  WHERE employee_id = 120;
  
  UPDATE test_emp
  SET emp_id = 1
     ,salary = 1000
  WHERE emp_id = copy_emp_rec.employee_id;
END; 
```

- UPDATE ...
- SET ROW = ...

```plsql
DECLARE
 copy_emp_rec employees%ROWTYPE;
 
BEGIN
  SELECT *
  INTO copy_emp_rec
  FROM employees
  WHERE employee_id = 120;
  
  UPDATE copy_emp
  SET ROW = copy_emp_rec
  WHERE employee_id = copy_emp_rec.employee_id;
END; 
```

### INDEX BY表 （PL/SQL表）

- INDEX BY表时用户定义的一种组合（集合）数据类型，
- INDEX表可以利用一个主键（下标）值作为索引的方式存储数据，而主键(下标)值不必是顺序的
   - INDEX BY表就是一组关联的键值对
   
**特性：**  

- INDEX BY表由两个组件（两列）所组成
   - 数据类型为BINARY_INTEGER或PLS_INTEGER的主键
   - 标量或记录(RECORD)数据类型的列
- INDEX BY表没有界限，大小可以动态的

**说明:**

- 主键（下标）的数据类型一般是BINARY_INTEGER或PLS_INTEGER数据类型，
   - 因为与NUMBER类型的数据相比，BINARY_INTEGER或PLS INTEGER数据需要较少的存储空间。
   - BINARY_INTEGER或PLS_INTEGER是以一种紧资格式所表示的数字整型数，而且它们的算术操作是使用机器算法实现的，因此它们的算术运算也要比SUMBER类型的数据快。“主键”也可以使用变长字符(VARCHAR2)类型或VARCHAR2的子类型，但是效率方面要打折扣。
- 在INDEX BY表中，用标量数据类型或记录数据类型的列来存储值，如果这一列是标量型，那么它就只能存储一个值。而如果这一列是记录型，那么它就可以存储多个值。实际上，这一列就相当于数组中的元素。
- 虽然INDEX BY表的大小没有限制，但是BINARY_INTEGER或PLS_NTEGER类型的“主键”(下标)受限于BINARY_INTEGER或PLS_INTEGER类型数据的最大值，其取值范围为-2147483647~2147483647。要注意的是，“主键”既可以是正也可以是负，而且“主键”不一定是连续的。


**声明INDEX BY表数据类型和INDEX BY表型变量的语法：**

```plsql
DECLARE
 TYPE 数据类型名 IS TABLE OF 列数据类型|变量%TYPE|表%ROWTYPE
     [INDEX BY PLS_INTEGER|BINARY_INTEGER|VARCHAR2(20)];

 变量名 数据类型名；
BEGIN
  ...
END;

这里需要指出的是，创建一个NDEX BY表型变量需要两步：
(1)声明一个INDEX BY表的数据类型
(2)利用以上声明的NDEX BY表数据类型声明一个这一数据类型的变量。
```

- 在INDEX BY表上可以加NOT NULL约束，以防止将空值NULL赋于NDEX BY表中元素，要注意的是，在声明INDEX BY表时是不能对它进行初始化的。
- 当INDEX BY表被创建时Oracle并不有自动填入任何值。必须在PL/SQL程序中以程序的方式为INDEX BY表赋值，然后才可以使用这个数组，
- INDEX BY表中的元素可以是任何标量类型/记录类型。
- INDEX BY表的大小是没有限制的，在INDEX BY表中，数据行（元素）的个数可以动态地增长，因此可以在INDEX BY表中添加新的数据行（元素）
- 列(元素)可以属于任何变量或记录数据类型，而主键（下标）既可以是一个数字，也可以是一个字符串。不能在声明INDEX BY表时将其初始化，即在声明时不能为INDEX BY表赋值，此时它没有包含任何键（下标）也没有包含任何（元素）值。需要使用显示的执行语句为INDEX BY表赋值，其INDEX BY表的结构：

```plsql
DECLARE
 TYPE name_table_type IS TABLE OF employees.last_name%TYPE
      INDEX BY PLS_INTEGER;
      
 TYPE hire_date_table_type IS TABLE OF employees.hire_date%TYPE
      INDEX BY BINARY_INTEGER;
  
 name_table name_table_type;
 hire_date_table hire_date_table_type;
 
 v_count NUMBER(6) := &p_count;
 
BEGIN
  FOR i IN 1..v_count LOOP
    name_table(i) := CONCAT(TO_CHAR(i),'号');
    hire_date_table(i) := TO_DATE('1997-11-12','yyyy-mm-dd');
    DBMS_OUTPUT.PUT_LINE(name_table(i) || ': ' || hire_date_table(i));
  END LOOP;
END;
```

#### INDEX BY表的方法

```
表名.方法
```

|     方法     |                                 描述                                  |
| :---------: | :------------------------------------------------------------------: |
|  EXISTS(n)  |             如果第n个元素在PL/SQL表（数组）中存在，返回TRUE              |
|    COUNT    |                  返回一个PL/SQL表当前所包含的元素个数                   |
|    FIRST    | 返回在一个PL/SQL表中第一个（最小的）下标数字,如果PL/SQL表是空的，返回NULL |
|    LAST     | 返回在一个PL/SQL表中最后一个（最大的）下标数字,如果PL/SQL表是空的返回NULL |
|  PRIOR(n)   |             返回在一个PL/SQL表中当前元素的前n个元素的下标值              |
|   NEXT(n)   |             返回在一个PL/SQL表中当前元素的后n个元素的下标值              |
|   DELETE    |                  DELETE即删除一个PL/SQL表中的全部元素                  |
|  DELETE(n)  |                    即删除一个PL/SQL表中的第n个元素                     |
| DELETE(m,n) |                     即删除数组中m~n范围内的全部元素                     |

- PRIOR()和NEXT()这两种方法到底有什么用处？
   - 对于“主键”（下标)是VARCHAR2的关联数组(associative arrays),利用这些方法可以方便地返回适当的“主键”(下标)，而其顺序是基于字符串中字符的二进制的值。在循环操作中使用这些方法要比利用下标加减的方法更可靠，因为在循环操作期间有可能数组中有些元素被删除了或插入了新的元素。

```plsql
DECLARE
 TYPE emp_num_type IS TABLE OF NUMBER
      INDEX BY VARCHAR(20);
 
 total_employees emp_num_type;
 i VARCHAR2(20);

BEGIN
 -- 往INDEX BY表total_employees中插入数据
 
 SELECT COUNT(*)
 INTO total_employees('10号部门')
 --total_employees添加下标为'10号部门',
 --total_employees('10号部门')内容为COUNT(*)的结果(数字)
 FROM employees
 WHERE department_id = 10;
 
 SELECT COUNT(*) 
 INTO total_employees('20号部门')
 FROM employees
 WHERE department_id = 20;
 
 SELECT COUNT(*)
 INTO total_employees('30号部门')
 FROM employees
 WHERE department_id = 30;
 
-- 为 i 赋值为total_employees的第一个下标(主键)
 i := total_employees.FIRST;

 DBMS_OUTPUT.PUT_LINE('按升序列出各部门名称和员工数：');

 WHILE i IS NOT NULL LOOP
   DBMS_OUTPUT.PUT_LINE(i || '的员工总数：' || TO_CHAR(total_employees(i)));
   DBMS_OUTPUT.PUT_LINE('i的值: ' || i);
    
-- 为 i 赋值为total_employees的当前下标的下一个下标  
   i:= total_employees.NEXT(i);
 END LOOP;
 DBMS_OUTPUT.PUT_LINE('i的值: ' || i);
     
 DBMS_OUTPUT.PUT_LINE(CHR(10));

-- 为 i 赋值为total_employees的最后一个下标(主键)
 i := total_employees.LAST;
 DBMS_OUTPUT.PUT_LINE('按降序列出各部门名称和员工数：');
 
 WHILE i IS NOT NULL LOOP
   DBMS_OUTPUT.PUT_LINE(i || '的员工总数：' || TO_CHAR(total_employees(i)));
   DBMS_OUTPUT.PUT_LINE('i的值: ' || i);

-- 为 i 赋值为total_employees的当前下标的上一个下标  
   i := total_employees.PRIOR(i);
 END LOOP;
    DBMS_OUTPUT.PUT_LINE('i的值: ' || i);
END;


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
DECLARE
 TYPE dept_table_type IS TABLE OF departments%ROWTYPE
      INDEX BY PLS_INTEGER;
 dept_table dept_table_type;
 v_count NUMBER := 5;
 j NUMBER;
BEGIN
  FOR i IN 1..v_count LOOP
    SELECT *
    INTO dept_table(i * 10)
    FROM departments
    WHERE department_id = i * 10;
  END LOOP;
  
  j := dept_table.FIRST;
  WHILE j IS NOT NULL LOOP
    DBMS_OUTPUT.PUT_LINE(dept_table(j).department_id || ' ' ||
                         dept_table(j).department_name
                         );
    j := dept_table.NEXT(j);
  END LOOP;
END;

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

- 但有一个要返回多行数据的SELECT语句时，可以在PL/SQL程序中声明一个显式的游标，来一行一行地处理这SELECT语句所返回地所有数据行。
- 一个多行查询所返回地数据行地集合（全部数据行）被称为活动集。（活动集的大小就是满足查询条件的数据行的个数）
- 一个游标的活动集是由游标声明时的SELECT语句决定的。

- 显示游标功能：
   -  虽然使用SELECT INTO语句已经可以将数据库表中的数据存入PL/SQL变量中，但是有时满足查询条件的数据行可能很多，这就使得程序的逻辑条件比较复杂，而且使用循环语句每循环一次将一行数据存入相应的PL/SQL变量中的方法存在着效率方面的问题，因为每次执行语句时，PL/SQL必须访问数据库中的表，而表是存放在硬盘上的。实验数据表明，硬盘的数据访问速度比内存慢1000~100000倍。而使用显式cursor就可以一次将满足所有条件的数据全部放入内存中，之后就在内存中一行接一行地处理了，比之前更快。
       - 可以一行接一行地处理一个查询返回的全部结果（查询语句执行一次）。
       - 一直追踪当前正在处理的数据行。
       - 能够使程序员在PL/SQL程序块中显式地手工控制一个或多个cursors。.

**1)定义游标 定义一个游标及其相对应的SELECT语句**

- 格式 

```
CURSOR 游标名 [(paramenter[,paramenter]……)] IS 
    查询语句;
```

- 游标参数只为输入参数，格式为：

```
paramenter_name[IN] datefile [{:=DEFAULT}expression]
```
   - 在指定数据类型时，不能使用长度约束

-  定义的游标的查询语句中不能有INTO子句
- 显式游标可以是任何有效的SELECT语句，包括连接，子查询等。

**2)打开游标 就是执行游标所对应的SELECT语句，将其查询结果放入工作区，并且指针指向工作区的首部，标记游标结果集合**

- 如果游标查询语句中带有FOR UPDATE选项，OPEN语句还将锁定数据库表中游标结果集合对应的数据行
- 格式 

```
OPEN 游标名[([paramenter = ]valuel[,[paramenter = ]value]……)];
```

1. 为一个上下文区域动态地分配内存
2. 对SELECT语句进行语法分析
3. 绑定输入变量（通过获取输入变量的内存地址为输入变量设置值）
4. 标识活动集（生成满足查询条件的数据行的集合）
   - 当执行OPEN语句是，并没有执行提取活动集中的数据行并存入变量的操作，而是由FETCH语句完成）
5. 将指针定位在（指向）活动集的第一行

- 在向游标传递参数时，可以使用与游标参数相同的传值方法(即位置表示法和名称表示法)
- PL/SQL程序不能用OPEN语句重复打开一个游标

**3)提取游标数据 就是检索结果集合中的数据行放入指定的输出变量**

- 格式 

```
FETCH 游标名 INTO {变量1，变量2.... | 记录名};
```

- FETCH中的数据与INTO的变量应该顺序一一对应，且数据类型兼容。
- 对该记录进行处理；继续处理，直到活动集合中没有记录

1. 读取当前行的数据并装入PL/SQL的输出变量中
2. 将游标的指针移向所标识的活动集中的下一行

- 检查游标中是否还包含数据行。如果FETCH语句没有提取任何值（数据），在活动集中没有数据行要处理，PL/SQL并不报错。

**4)关闭游标**

- 当提取和处理完游标结果集合数据后，应及时关闭游标，以释放该游标所占用的系统资源(释放上下文区域，取消活动集的定义)，并使该游标的工作区改变或无效，不能再使用FETCH语句取其中的数据（否则抛出异常：INVALID_CURSOR)。
- 关闭后的游标可以使用OPEN语句重新打开。
- 格式 

```
CLOSE  cursor_name;
```

**应用**

```
打印80号部门的员工的工资
DECLARE
 TYPE emp_record IS record(
                           v_sal employees.salary%type,
                           v_id employees.employee_id%type
                           );
 v_emp_record emp_record;
 CURSOR emp_sal_cursor IS SELECT salary,employee_id
                          FROM employees
                          WHERE department_id = 80;
BEGIN
  OPEN emp_sal_cursor;
  FETCH emp_sal_cursor INTO v_emp_record.v_sal,v_emp_record.v_id;
  WHILE emp_sal_cursor%FOUND LOOP
    dbms_output.put_line('emp_id:'||v_emp_record.v_id||'salary:'||v_emp_record.v_sal);
    FETCH emp_sal_cursor INTO v_emp_record.v_sal,v_emp_record.v_id;
  END LOOP;
  CLOSE emp_sal_cursor;
END;  
```

```
输出工资最高的前十名员工的信息与排名
DECLARE
 v_emp_id NUMBER;
 v_emp_name copy_emp.last_name%TYPE;
 v_dept_id copy_emp.department_id%TYPE;
 v_salary copy_emp.salary%TYPE;
 
 CURSOR copy_emp_cursor IS
        SELECT employee_id
              ,last_name
              ,department_id
              ,salary
        FROM copy_emp
        ORDER BY salary DESC;

 v_i NUMBER := 0;
BEGIN
  OPEN copy_emp_cursor;
  
  WHILE copy_emp_cursor%ROWCOUNT < 10 LOOP
    
    FETCH copy_emp_cursor INTO v_emp_id,v_emp_name,v_dept_id,v_salary;
    v_i := v_i + 1;
    DBMS_OUTPUT.PUT_LINE('第' || v_i ||'名'|| CHR(10)||
                         v_emp_id ||'号员工'|| CHR(10) ||
                         '姓名为：'||v_emp_name || CHR(10)|| 
                         '部门为：'||v_dept_id || CHR(10) ||
                         '工资为：'||v_salary|| CHR(10) ||
                         '******************************'
                         );
  END LOOP;
  CLOSE copy_emp_cursor;
END;
```

```
DECLARE
 v_emp_id NUMBER;
 v_emp_name copy_emp.last_name%TYPE;
 v_dept_id copy_emp.department_id%TYPE;
 v_salary copy_emp.salary%TYPE;
 v_rn NUMBER;
 
 CURSOR copy_emp_cursor IS
        SELECT rownum rn
              ,employee_id
              ,last_name
              ,department_id
              ,salary
        FROM (SELECT employee_id
                     ,last_name
                     ,department_id
                     ,salary
              FROM copy_emp
              ORDER BY salary DESC
              );
BEGIN
  OPEN copy_emp_cursor;
  
  WHILE copy_emp_cursor%ROWCOUNT < 10 LOOP
    
    FETCH copy_emp_cursor INTO v_rn,v_emp_id,v_emp_name,v_dept_id,v_salary;
    
    DBMS_OUTPUT.PUT_LINE('第' || v_rn || '名' || CHR(10) ||
                         v_emp_id ||'号员工'|| CHR(10) ||
                         '姓名为：'||v_emp_name || CHR(10)|| 
                         '部门为：'||v_dept_id || CHR(10) ||
                         '工资为：'||v_salary|| CHR(10) ||
                         '******************************'
                         );
  END LOOP;
  CLOSE copy_emp_cursor;
END;
```

```
DECLARE
 v_emp_id NUMBER;
 v_emp_name copy_emp.last_name%TYPE;
 v_dept_id copy_emp.department_id%TYPE;
 v_salary copy_emp.salary%TYPE;
 
 CURSOR copy_emp_cursor IS
        SELECT employee_id
              ,last_name
              ,department_id
              ,salary
        FROM copy_emp
        ORDER BY salary DESC;

 v_i NUMBER := 0;
BEGIN
  OPEN copy_emp_cursor;
  
  LOOP
    FETCH copy_emp_cursor INTO v_emp_id,v_emp_name,v_dept_id,v_salary;
    EXIT WHEN copy_emp_cursor%ROWCOUNT > 10 OR copy_emp_cursor%NOTFOUND OR copy_emp_cursor%NOTFOUND IS NULL; 
    v_i := v_i + 1;
    DBMS_OUTPUT.PUT_LINE('第' || v_i ||'名'|| CHR(10)||
                         v_emp_id ||'号员工'|| CHR(10) ||
                         '姓名为：'||v_emp_name || CHR(10)|| 
                         '部门为：'||v_dept_id || CHR(10) ||
                         '工资为：'||v_salary|| CHR(10) ||
                         '******************************'
                         );
  END LOOP;
  CLOSE copy_emp_cursor;
END;
```

```
DECLARE 
 CURSOR emp_cursor IS
   SELECT *
   FROM employees;
 
 emp_rec emp_cursor%ROWTYPE;
 
 TYPE emp_table_type IS TABLE OF employees%ROWTYPE
      INDEX BY PLS_INTEGER;
 
 v_emp_rec emp_table_type;
 n NUMBER(3) := 1;
 
BEGIN
  OPEN emp_cursor;
  
  LOOP
    FETCH emp_cursor INTO emp_rec;
    
    EXIT WHEN emp_cursor%NOTFOUND OR emp_cursor%NOTFOUND IS NULL;
    
    v_emp_rec(n) := emp_rec;
    n:= n + 1;
    
  END LOOP;
  CLOSE emp_cursor;
  
  <<outer_loop>>
  FOR i IN v_emp_rec.FIRST..v_emp_rec.LAST LOOP
    FOR j IN v_emp_rec.FIRST..v_emp_rec.LAST LOOP
      IF v_emp_rec(i).employee_id = v_emp_rec(j).manager_id THEN
        DBMS_OUTPUT.PUT_LINE(v_emp_rec(i).last_name ||'手底下有人');
        
        CONTINUE outer_loop;
      END IF;
    END LOOP;
  END LOOP outer_loop;
END;
```

### 游标属性

- %FOUND 布尔型属性 当最近一次读记录时成功返回则值为TRUE
   - NULL：在第一次获取数据之前（执行FETCH语句之前）
   - TRUE：一条记录成功的被获取到
   - FALSE：没有记录返回
   - INVALID_CURSOR：游标没有打开
- %NOTFOUND 布尔型属性 与%FOUND相反
- %ISOPEN 布尔型属性 当游标打开时返回TRUE
- %ROWCOUNT 数字型属性 返回已从游标中读取的记录数

-注：可在前面冠以SQL表示隐式游标
   - 如： SQL%FOUND

```
DECLARE
 v_dept_id copy_emp.department_id%TYPE := &p_dept_id;
BEGIN
  UPDATE copy_emp
  SET salary = 99999
  WHERE department_id = v_dept_id;
  
  DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT || '行数据被更新');
END;

*****
1行数据被更新
```

```
 DECLARE
       --类型定义
       CURSOR c_job IS
       SELECT empno,ename,job,sal
       FROM emp
       WHERE job='MANAGER';
       --定义一个游标变量
       c_row c_job%rowtype;
BEGIN
       OPEN c_job;
         LOOP
           --提取一行数据到c_row
           FETCH c_job INTO c_row;
           --判读是否提取到值，没取到值就退出
           --取到值c_job%notfound 是false 
           --取不到值c_job%notfound 是true
           -- NULL：在第一次获取数据之前（执行FETCH语句之前）
           EXIT WHEN c_job%notfound OR c_job%NOTFOUND IS NULL;
            dbms_output.put_line(c_row.empno||'-'||c_row.ename||'-'||c_row.job||'-'||c_row.sal);
         END LOOP;
       --关闭游标
      CLOSE c_job;
END;
```

### 游标的FOR循环

-  PL/SQL语言提供了游标FOR循环语句，自动执行游标的OPEN、FETCH、CLOSE语句和循环语句的功能；
    - 不需要输入OPEN,FETCH,CLOSE；
1. 当进入循环时，游标FOR循环语句自动打开游标，并提取第一行的游标数据；
2. 当程序处理完当前所提取的数据而进行下一次循环时，游标FOR循环语句自动提取下一行数据供程序处理
3. 当提取完结果集合中的所有数据后结束循环，并自动关闭游标。

- 格式

```
FOR index_variable IN 游标名[vlaue[,value]……] LOOP
          --游标执行代码
END LOOP;
```

- 其中，index_variable为游标FOR循环语句隐含声明的索引变量，该变量为**记录变量**，其结构与游标查询语句返回的结构集合的结构相同。
   - 在程序中可以通过引用该索引记录变量来读取所提取的游标数据，
   - index_variable中各元素的名称与游标查询语句选择列表中所制定的列名相同
- 如果再游标查询语句的选择列表中存在计算列，则必须为这些计算列指定别名后才能通过游标FOR循环语句中的索引变量来访问这些列数据。

```
打印出80号部门的所有员工的工资
DECLARE
  CURSOR v_emp_cursor IS SELECT salary,employee_id
                         FROM employees
                         WHERE department_id = 80;
BEGIN 
  FOR i IN v_emp_cursor LOOP
    dbms_output.put_line('employee_id:'||RPAD(i.salary,4,'*')||'  salary:'||LPAD(i.salary,7,'0'));
  END LOOP;
END;
```

```
利用游标调整公司中员工的工资
  工资范围      调整基数
 0-5000          5%
 5000-10000      3%
 10000-15000     2%
 15000--         1%

 1)WHILE
 DECLARE
  CURSOR v_cursor IS SELECT employee_id,salary
                     FROM employees;
  v_id employees.employee_id%type;
  v_sal employees.salary%type;
  v_temp NUMBER(10,5);
BEGIN
  OPEN v_cursor;
  FETCH v_cursor INTO v_emp_record.v_id,v_emp_record.v_sal;
  WHILE v_cursor %FOUND LOOP
    IF v_sal < 5000 THEN v_temp := 0.05;
    ELSIF v_sal <10000 THEN v_temp := 0.03;
    ELSIF v_sal <15000 THEN v_temp := 0.02;
    ELSE v_temp := 0.01;
    END IF;
  UPDATE employees
  SET salary = salary * (1 + v_temp)
  WHERE emplkoyee_id  = v_id;
  END LOOP;
  FETCH v_cursor INTO v_id,v_sal;
END;

2)DEOCDE函数
UPDATE employees
SET salary = salary * (1 + DECODE(TRUNC(salary / 5000),0,0.05,
                                                       1,0.03,
                                                       2,0.02,
                                                       0.01));
3)FOR循环
DECLARE 
  CURSOR emp_sal_cursor IS SELECT employee_id,salary
                           FROM employees;
  v_temp NUMBER(4,2);
BEGIN
  FOR i IN emp_sal_cursor LOOP
    IF i.salary < 5000 THEN v_temp := 0.05;
    ELSIF i.salary < 10000 THEN v_temp := 0.03;
    ELSIF i.salary < 15000 THEN v_temp := 0.02;
    ELSE v_temp := 0.01;
    END IF;
    UPDATE employees
    SET salary = salary * (1 + v_temp)
    WHERE employee_id = i.employee_id;
  END LOOP;
END;
```
### 游标使用子查询

#### 在游标的FOR循环中使用子查询

- 如果使用子查询的游标FOR循环，不需要在声明段中声明游标，但是必须提供一个在循环体中本身可以确定活动集的SELECT语句，用来定义游标。
- 且如果在游标的FOR循环使用子查询，则不能显式的调用游标的属性，因为该游标没有被声明。

```
BEGIN
  FOR emp_rec IN (SELECT * FROM employees) LOOP
    IF emp_rec.department_id = 20 THEN 
      DBMS_OUTPUT.PUT_LINE('20号部门: ' || emp_rec.employee_id);
    END IF;
  END LOOP;
END;

***************
20号部门: 201
20号部门: 202
PL/SQL procedure successfully completed
```

#### 在游标的定义中使用子查询

- 此时在子查询的每一个函数或表达式都必须有别名，用来映射成临时表
    - 函数和表达式不能作为列名

```
DECLARE
 CURSOR dept_total_cursor IS 
        SELECT DISTINCT e.department_id
                       ,d.department_name
                       ,max_sal
                       ,avg_sal
                       ,count_num
        FROM departments d
            ,employees e
            ,(SELECT MAX(salary) max_sal
                    ,AVG(salary) avg_sal
                    ,COUNT(*) count_num
                    ,department_id
              FROM employees
              GROUP BY department_id
              ) t
        WHERE e.department_id = d.department_id
          AND e.department_id = t.department_id;
BEGIN
  FOR dept_rec IN dept_total_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('部门号: '||dept_rec.department_id || CHR(10)||
                        '部门名称: '||dept_rec.department_name||CHR(10)||
                        '人数: '||dept_rec.count_num ||CHR(10)||
                        '最高工资: '||dept_rec.max_sal||CHR(10)||
                        '平均工资: '||dept_rec.avg_sal);
    DBMS_OUTPUT.PUT_LINE('**********************');
    DBMS_OUTPUT.ENABLE (buffer_size=>null);
  END LOOP;
END;
``` 

### 带参数的游标
- 可以在PL/SQL程序块中多次打开一个已经关闭的显式游标，而且每次可以返回不同的动态集。而每次执行时，关闭之前的游标并以新的一组参数重新打开。
- 对于在游标中定义的每一个形参，在OPEN语句中必须有一个对应的实参。
- 参数的数据类型与标量类型的变量相同。
- 只定义数据类型，而不定义长度。

```
DECLARE
    CURSOR emp_sal_cursor(dept_id NUMBER, sal NUMBER) IS 
           SELECT salary + 1000 sal, employee_id id 
           FROM employees 
           WHERE department_id = dept_id and salary > sal;
    temp NUMBER(4, 2);
BEGIN
          DBMS_OUTPUT.PUT_LINE('工资'|| CHR(9) || 'id' || CHR(9) ||'temp');    
          FOR c IN emp_sal_cursor(sal => 4000, dept_id => 80) LOOP
          --dbms_output.put_line(c.id || ': ' || c.sal);
          IF c.sal <= 5000 THEN
             temp := 0.05;
          ELSIF c.sal <= 10000 THEN
             temp := 0.03;   
          ELSIF c.sal <= 15000 THEN
             temp := 0.02;
          ELSE
             temp := 0.01;
          END IF;      

          dbms_output.put_line(c.sal || CHR(9) || c.id || CHR(9) || temp);
          --UPDATE employees SET salary = salary * (1 + temp) WHERE employee_id = c.id;
    END LOOP;
END;
```

```
DECLARE 
 CURSOR dept_avg_sal_cursor(p_dept_id NUMBER) IS
    SELECT department_id id
          ,AVG(salary) avg_sal
    FROM employees
    GROUP BY department_id
    HAVING department_id = p_dept_id;
    
    v_dept_id NUMBER := &p_id;
BEGIN 
  FOR dept_rec IN dept_avg_sal_cursor(v_dept_id) LOOP
    DBMS_OUTPUT.PUT_LINE('部门号：' || dept_rec.id ||CHR(9)||
                         '平均工资: '|| dept_rec.avg_sal);
  END LOOP;
END;
```

### 隐式游标

- 当必须执行一个SQL语句时，Oracle服务器自动创建一个隐式的游标

```
更新指定员工 salary(涨工资 10)，如果该员工没有找到，则打印”查无此人” 信息
BEGIN
         UPDATE employees 
         SET salary = salary + 10 
         WHERE employee_id = 1005;    
         IF SQL%NOTFOUND THEN
            dbms_output.put_line('查无此人!');
         END IF;
END;
```

### FOR UPDATE子句

- FOR UPDATE 子句必须是SELECT语句的最后一个子句。
- 在查询多个表时，可以使用FOR UPDATE子句限制锁定特定表中的数据行(在OPEN时锁住)
   - 避免其他用户或进程的DML操作的冲突，在提取数据时，将所操作的数据行全部锁住。
   
- 语法

```
SELECT ...
FROM ...
FOR UPDATE [OF 列名][NOWAIT | WAIT n];

--[OF 列名] 只锁住包含该列的表中的数据行
--[NOWAIT | WAIT n] 访问的数据行被其他会话锁住则返回一个错误。n为等待n秒
```

```
DECLARE
 CURSOR copy_emp_cursor IS
        SELECT employee_id
              ,last_name
              ,salary
        FROM copy_emp
        WHERE department_id = 20
        FOR UPDATE OF salary NOWAIT;
BEGIN
  OPEN copy_emp_cursor;
END;
```

### WHERE CURRENT OF 子句

- 可以通过WHERE CURRENT OF 子句引用显式游标的当前行来标识要修改的数据行，
- 要引用显式游标的当前行，WHERE CURRENT OF子句需要与FOR UPDATE子句一起使用
- 在UPDATE和DELETE语句中使用WHERE CURRENT OF子句，而在游标声明时说明FOR UPDATE子句。
- 必须在游标的查询语句中包含FOR UPDATE子句, 以便在打开这个游标时锁住访问的数据行

```
WHERE CURRENT OF 游标;
```

```
DECLARE
 CURSOR emp_cursor IS
   SELECT *
   FROM copy_emp
   WHERE department_id = 20
   FOR UPDATE OF salary NOWAIT;
BEGIN
  FOR emp_rec IN emp_cursor LOOP
    UPDATE copy_emp
    SET salary = emp_rec.salary * 0.15
    WHERE CURRENT OF emp_cursor;
  END LOOP;
END; 
```
***

## 触发器TRIGGER

- 触发器 触发器是一个特殊的存储过程。是一个与表相关联的、存储的PL/SQL程序。
- 在Oracle系统上，触发器类似过程和函数，都有声明、执行、异常处理过程PL/SQL块
- 每当一个特定的数据操作语句DML(INSERT、UPDATE、DELETE,注意没有SELECT)在指定的表上发出时,oracle自动地执行触发器中定义的语句序列。

 **触发器在数据库里以独立的对象存储，它与存储过程不同的是：**
 
|                                                      触发器TRIGGER                                                       |             存储过程PROCEDURE             |
| :---------------------------------------------------------------------------------------------------------------------: | :--------------------------------------: |
| 触发器是由一个事件(DML语句)来启动运行，即触发器是当某个事件发生时自动的隐式运行,并且触发器不能接受参数，所以运行触发器叫触发或点火 | 存储过程通过其它程序来启动运行或直接启动运行 |
|                                            源代码包含在USER TRIGGERS数据字典中                                            |     源代码包含在USER_SOURCE数据字典中      |
|                                          不允许使用COMMIT、SAVEPOINT和ROLLBACK                                           |    允许使用COMMIT、SAVEPOINT和ROLLBACK    |

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

### 触发器一般语法

```
CREATE OR REPLACE TRIGGER 触发器名
BEFORE|AFTER|INSTEAD OF
INSERT|DELETE|UPDATE [OF COLUMN[列名]]
ON [模式（用户名）.]表/视图
[REFERENCING OLD AS 指定引用OLD的别名 | NEW AS 指定引用NEW的别名]
[FOR EACH ROW] --行级触发器
[WHEN 条件表达式] --只用于行级触发器
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
  - BEFORE 在触发事件(执行)之前执行触发器
     - 决定所触发的语句是否应该允许被完成
     - 在完成一个INSERT或UPDATE语句之前导出原来列的值
     - 初始化全局变量，验证一些复杂的业务规则 
  - AFTER 在触发事件之后执行触发器
    - 在执行触发器之前先完成触发的语句 
    - 在已经存在BEFORE触发器时，要在相同的触发语句上执行不同的操作  
    - 如果触发语句完成后没有COMMIT，则其他用户看见的还是没有执行触发器的结果
  - INSTEAD OF 替换触发事件的语句来执行触发器。
     - 被用于用其他方法不能修改的视图 （当不能通过SQL的DML语句直接修改视图时，可以在INSTEAD OF触发器的体中写DML语句在这个视图的基表上直接执行）
- **触发器本身** 即该TRIGGER被触发之后的目的和意图，正是触发器本身要做的事情，
  - PL/SQL块
  - CALL调用子句 `CALL 存储过程/函数等`
- **触发频率** 说明触发器内定义的动作被执行的次数,即语句级(STATEMENT)触发器和行级(ROW)触发器
  - 语句级触发器 是指当某触发事件发生时该触发器只执行了一次
  - 行级触发器 是指当某触发事件发生时，对受到该操作影响的每一行数据，触发器都执行一次
- **触发器类型**

 | 语句级触发器    |  行级触发器   |
 | :-: | :-: |
 |  创建触发器的默认类型   |   创建触发器时使用FOR EACH ROW子句指定为行级触发器  |
 |  对于触发的事件只触发一次   |  对受触发事件影响的每行触发一次    |
 | 没有行受影响时也触发一次    |  触发事件未影响任何数据行就不触发   |

```
CREATE OR REPLACE TRIGGER menu_test_tri
BEFORE 
DELETE ON menu
FOR EACH ROW
BEGIN
  dbms_output.put_line('good');
END;
```

```
CREATE OR REPLACE TRIGGER secure_emp
BEFORE INSERT ON copy_emp
BEGIN
  IF(TO_CHAR(SYSDATE,'DY') IN ('SAT','SUN')) 
   OR (TO_CHAR(SYSDATE,'HH24:MI') NOT BETWEEN '08:00' AND '18:00') THEN
    RAISE_APPLICATION_ERROR(-20038,'非工作时间，拒绝输入数据');
  END IF;
END;  
```

### 行级触发器

#### 带有条件谓词的语句触发器

- 如果有多种类型的DML操作可以触发一个触发器，那么在触发器体中可以使用条件谓词（INSERTING/DELETING/UPDATING）来检查触发器的语句类型。

```
aCREATE OR REPLACE TRIGGER secure_emp
BEFORE INSERT OR DELETE OR UPDATE ON copy_emp
FOR EACH ROW
BEGIN
  IF INSERTING THEN
    RAISE_APPLICATION_ERROR(-20001,'禁止插入数据');
  ELSIF DELETING THEN
    INSERT INTO user_test_table(user_name,old_id,new_id)
    VALUES (user,:OLD.employee_id,:NEW.employee_id);
  ELSIF UPDATING THEN
    INSERT INTO user_test_table(user_name,old_id,new_id)
    VALUES (user,:OLD.employee_id,:NEW.employee_id);
  END IF;
END;
```

- `UPDATING(列名)` 那一列被UPDATE，则返回TRUE

#### 在行级触发器使用限定符 :NEW和:OLD

- 为了方便行触发器的开发，当一个行级触发器触发时，PL/SQL运行时的引整创建和维护两个数据结构，它们就是OLD和NEW,
- OLD和NEW具有完全相同的记录结构，并且使用%ROWTYPE声明成与触发器所基于的表的数据类型一模一样。
- 其中，OLD存储触发器处理之前的记录的原始值，而NEW则包含了新值。

| 数据操作 |            OLD             |            NEW            |
| :------: | :------------------------: | :-----------------------: |
|  INSERT  | （插入之前的值）空值（NULL） |          插入的值          |
|  UPDATE  |        修改之前的值         |        修改之后的值        |
|  DELETE  |        删除之前的值         | 空值（NULL)（删除之后的值） |

**注意：**

- 只在行触发器中有OLD和NEW限定词。
- 在每个SQL和PL/SQL语句中，这两个限定词前必须冠以冒号(：)（`:NEW`和`:OLD`）。
    - 如果这两个限定词是在WHEN所在条件中引用则不能冠以冒号。
    - 否则ORA-25000: 在触发器 WHEN 子句中, 连接变量的使用无效
- 如果在较大的表上执行许多修改，行触发器可能降低系统的效率。

```
CREATE OR REPLACE TRIGGER update_emp_trigger
AFTER 
 UPDATE ON employees
FOR EACH ROW
BEGIN
  dbms_output.put_line('old_sal'||:OLD.salary||'new_sal'||:NEW.salary);
END;
```

```
CREATE OR REPLACE TRIGGER audit_emp_values
BEFORE INSERT OR DELETE OR UPDATE ON copy_emp
FOR EACH ROW
  BEGIN
    INSERT INTO user_test_table(user_name,old_id,new_id)
    VALUES (USER,:OLD.employee_id,:NEW.employee_id);
  END;
```

**注意：**

- 在AFTER类型的触发器中，无法更改此触发器类型的 NEW 值

```
CREATE OR REPLACE TRIGGER test_trigger
AFTER INSERT ON user_test_table
FOR EACH ROW
  BEGIN
    :NEW.new_id := 1;
  END;

ORA-04084: 无法更改此触发器类型的 NEW 值
```

#### WHEN子句有条件的创建行触发器

- 可以通过在触发器的定义中增加一个WHEN子句，并在这个子句中说明一个布尔表达式的方法来限制一个触发器的操作。
- 如果在触发器中包括了个WHEN子句，那么WHN子句中的表达式要评估（测试)该触发器所影响的每一行。
   - 对于每一行，如果这个表达式的评估结果是TRUE，那么触发器体（PL/SQL程序代码）将执行。
   - 如果表达式对于某一行的测试结果是FALSE或NUL，,那么触发器体就不执行。
- WHEN子句的评估并不影响触发的SQL语句的执行，即如果一个WHEN子句测试为FALSE，触发语句并不回滚。
- WHEN子句是不能包括在语句触发器的定义中的，这个子句只能包括在行触发器的定义中。

- 注：限定符 :NEW和:OLD在WHEN子句中不能带冒号
   - ORA-25000: 在触发器 WHEN 子句中, 连接变量的使用无效
   
```
CREATE OR REPLACE TRIGGER set_com_pct
BEFORE INSERT OR UPDATE ON copy_emp
FOR EACH ROW
WHEN (NEW.job_id = 'IT')
  BEGIN
    IF INSERTING THEN
      :NEW.commission_pct := 0;
    ELSE
      IF :OLD.commission_pct = 0 
       OR :OLD.commission_pct IS NULL THEN
        :NEW.commission_pct := 0.3;
      ELSE 
        :NEW.commission_pct := :OLD.commission_pct;
      END IF;
    END IF;
  END; 
```

#### 触发器执行模型概要及实现完整性约束的准备

**一个单一的DML语句有可能触发以下四种触发器：**

1. BEFORE语句触发器
2. AFTER语句触发器
3. BEFORE行触发器
4. AFTER行触发器
- 在触发器中的触发事件或语句可能引起一个或多个完整性约束的检查，但是可以将约束的检查延迟到执行COMMIT操作时。
- Oracle服务器触发这四种触发器的方式如下。
1. 执行所有的BEFORE STATEMENT(语句)触发器。
2. 对受影响的每一行循环：
   - 执行所有的BEFORE ROW(行)触发器。
   - 执行所有的DML语句并进行完整性约束的检查。
   - 执行所有的AFTER ROW(行)触发器。
3. 执行所有的AFTER STATEMENT(语句)触发器。

触发器也可能引起其他触发器的触发（执行），即所谓的级联触发器。作为一个SQL语句的结果，所有执行的操作和检查必须成功。如果在一个触发器中抛出了一个异常并且这个异常没有被显式地处理，那么所执行地所有操作（包括触发语句）全部被回滚。保证触发器不违反完整性约束

**引用完整性**

1. 外键必须为空值（NULL）或者有相匹配地项
2. 外键可以没有相对应的键属性，但不可以有无效的项

虽然引用完整性可以有效地防止错误的DML语句，但在特殊情况下，可能会短时间地出现违法引用完整性地数据。可以创建一个AFTER UPDATE触发器来解决。

##### 利用触发器实现完整性约束

```
departments中的主键department_id是employees中的外键。

CREATE OR REPLACE TRIGGER emp_dept_fk_trg
AFTER UPDATE OF department_id ON employees
FOR EACH ROW
  BEGIN
    INSERT INTO departments(department_id,department_name)
    VALUES(:NEW.department_id,'新的部门:'||TO_CHAR(:NEW.department_id));
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
      NULL;  --如果存在该异常，则不进行任何操作.
             --并避免整个程序的中断。屏蔽该异常
  END;
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
CREATE OR REPLACE VIEW copy_emp_detaikls
AS
SELECT *
FROM copy_emp;
```
```
CREATE OR REPLACE TRIGGER new_emp_details
INSTEAD OF INSERT OR UPDATE OR DELETE ON copy_emp_details
FOR EACH ROW
  BEGIN
    IF INSERTING THEN
    --直接往copy_emp表中插入数据
      INSERT INTO copy_emp(employee_id,last_name,salary,department_id)
      VALUES(:NEW.employee_id,:NEW.last_name,:NEW.salary,:NEW.department_id);
    ELSIF DELETING THEN
    --直接删除copy_emp表中的数据
      DELETE FROM copy_emp
      WHERE employee_id = :OLD.employee_id;
    ELSIF UPDATING('salary') THEN
      UPDATE copy_emp
      SET salary = :NEW.salary
      WHERE employee_id = :OLD.employee_id;
    ELSIF UPDATING('department_id') THEN
      UPDATE copy_emp
      SET department_id = :NEW.department_id
      WHERE employee_id = :OLD.employee_id;
    END IF;
  END;
```

#### 在变异表上触发器的限制

**变异表**

目前正在由UPDATE，DELTETE或INSERT语句修改的表，或者是一个受到声明的DELETE CASCADE引用完整性（外键约束）操作影响可能需要修改的表。（对于语句触发器而言，以上的不认为是变异表）

- 当一个行级触发器试图修改或测试一个正在通过一个DML语句修改的表时，系统会产生一个变异表错误(ORA-4091)。使用触发器读写表中的数据时必须遵守一定的规则，但是这些规则只适用于行级触发器，而语句触发器并不受影响。其限制和限制的目的如下：
  - 发出触发语句的会话不能查询或修改变异表。
  - 这一限制防止一个触发器看到一个不一致的数据集。
  - 这一限制适用于所有使用FOR EACH ROW子句的触发器。
  - 使用INSTEAD OF触发器正在修改的视图不被认为是变异的。

被触发的表（触发器进行操作的表）本身是一个变异表，同样使用外键(FOREIGN KEY)约束引用的任何表也是变异表。正是这样的限制防止一个行触发器看到一个不一致的数据集合（正在修改的数据)。

**例**

```
CREATE OR REPLACE TRIGGER check_salary
 BEFORE INSERT OR UPDATE OF salary,department_id ON copy_emp
 FOR EACH ROW
DECLARE
 v_min_sal copy_emp.salary%TYPE;
 v_max_sal copy_emp.salary%TYPE;
BEGIN
  SELECT MIN(salary)
        ,MAX(salary)
  INTO v_min_sal
      ,v_max_sal
  FROM copy_emp
  WHERE department_id = :NEW.department_id;
  IF :NEW.salary < v_min_sal OR :NEW.salary > v_max_sal THEN
    RAISE_APPLICATION_ERROR(-20001,'工资不在允许范围内');
  END IF;
END;

UPDATE copy_emp
SET salary = 1000
WHERE employee_id = 177;
*********************
ORA-04091: 表 SCOTT.COPY_EMP 发生了变化, 触发器/函数不能读它
ORA-06512: 在 "SCOTT.CHECK_SALARY", line 5
ORA-04088: 触发器 'SCOTT.CHECK_SALARY' 执行过程中出错
```

**解决**

1. 将汇总数据（最低工资和最高工资）存储在另一个汇总表中，而该汇总表中的数据由其他的DML触发器进行持续的更新。
2. 将汇总数据存储在一个PL/SQL软件包中，并在这个软件包中访问这些汇总数据。这可以通过BEFORE语句触发器中来实现
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
|      在触发语句之前。       |             BEFORE STATEMENT              |
| 在触发语句影响的每一行之后。 |              AFTER STATEMENT              |
| 在触发语句影响的每一行之前。 |              BEFORE EACH ROW              |
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
       - BEFORE STATEMENT --> AFTER STATEMENT --> BEFORE EACH ROW --> AFTER EACH ROW
    - 对于一个基于视图的复合触发器，唯一允许的程序段就INSTEAD OF EACH ROW子句开始的段。

**语法**

1. 基于表的复合触发器 

```
CREATE OR REPLACE TRIGGER [用户.]触发器名
 FOR INSERT|UPDATE|DELETE ON [用户.]表名
 COMPOUND TRIGGER
-- 初始段（不要DECLARE)
 声明部分;
 子程序;
-- 可选段
 BEFORE STATAMENT IS
   语句;(PL/SQL代码块)
 AFTER STATEMENT IS
   语句;
 BEFORE EACH ROW IS
   语句;
 AFTER EACH ROW IS
   语句;
END;
```

2. 基于视图的复合触发器

```
CREATE OR REPLACE TRIGGER [用户.]触发器名
 FOR INSERT|UPDATE|DELETE ON [用户.]视图
 COMPOUND TRIGGER
--初始段
  声明部分;
  子程序;
--可选段
  INSTEAD OF EACH ROW IS
    语句;(PL/SQL程序块)
END;
```

**使用复合触发器时，Oracle限制:**

- 一个复合触发器的体代码必须复合了整个触发器程序块，而且必须是使用PL/SQL编写的。
- 一个复合触发器必须是一个DML触发器。
- 一个复合触发器必须被定义在一个表上或者一个视图上。
- 一个复合触发器体不能有初始化段，也不能有异常段。
   - 在任何其他时间点程序段执行之前BEFORE STATEMENT程序段永远只执行一次。
- 在一个程序段中出现的一个异常必须在这个段中处理，复合触发器无法将异常的控制传递给其他段。
- OLD、NEW和PARENT不能出现在声明段中，也不能出现在BEFORE STATEMENT和AFTER STATEMENT段中。
- 复合触发器的触发顺序是无法保证的，除非使用了FOLLOWS子句。

#### 例 使用复合触发器解决变异表问题

```
CREATE OR REPLACE TRIGGER check_salary
 FOR INSERT OR UPDATE OF salary,job_id ON copy_emp
 WHEN (NEW.job_id <> 'AA')
 COMPOUND TRIGGER
 
 TYPE sal_type IS TABLE OF copy_emp.salary%TYPE;
 min_sal sal_type;
 max_sal sal_type;
 
 TYPE dept_id_type IS TABLE OF copy_emp.department_id%TYPE;
 dept_id_list dept_id_type;
 
 TYPE dept_sal_type IS TABLE OF copy_emp.salary%TYPE
   INDEX BY VARCHAR2(38);
 dept_min_sal dept_sal_type;
 dept_max_sal dept_sal_type;

BEFORE STATEMENT IS
 BEGIN
   SELECT MIN(salary)
         ,MAX(salary)
         ,NVL(department_id,-1)
   BULK COLLECT INTO min_sal
                    ,max_sal
                    ,dept_id_list
   FROM copy_emp
   GROUP BY department_id;
   
   FOR i IN 1..dept_id_list.COUNT() LOOP
     dept_min_sal(dept_id_list(i)) := min_sal(i);
     dept_max_sal(dept_id_list(i)) := max_sal(i);
   END LOOP;
 END BEFORE STATEMENT;
 
 AFTER EACH ROW IS
   BEGIN
     IF :NEW.salary < dept_min_sal(:NEW.department_id) 
       OR :NEW.salary > dept_max_sal(:NEW.department_id) THEN
       RAISE_APPLICATION_ERROR(-20001,'新工资超过部门工资范围');
     END IF;
   END AFTER EACH ROW;
END; 
``` 



### 系统触发器（以及DDL触发器)

#### DDL触发器

- 除了DML语句之外，还可以指定一种或多种DDL语句来触发触发器（代码的执行）。可以为这些事件(DDL语句)在数据库上或模式上（需要指定模式即用户）创建触发器，还可以说明BEFORE和AFTER作为这类触发器的触发时机。Oracle数据库在现存的用户事务中存放这类触发器。
  - 要注意的是，不能将通过一个PL/SQL过程执行的任何DDL操作说明为一个触发器的事件。
- 只有所创建的对象是一个cluster(簇)、表、索引、表空间、视图、函数、过程、软件包、触发器、(数据)类型、序列(sequence)、同义词(synonym)、角色或用户时，DDL触发器才能触发。
- 基于数据定义语言(DDL)语句上的触发器的触发器既可以定义在数据库一级，也可以定义在模式（用户）一级

**创建基于DDL语句的触发器的语法格式如下：**

```
CREATE OR REPLACE TRIGGER 触发器名
 BEFORE|AFTER [DDL事件1[OR DDL事件2 OR..] ON 数据库|模式
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
 BEFORE|AFTER [数据库事件1[OR数据库事件2OR..]] ON DATABASE|SCHEMA
   触发器体
```

**数据库事件**

|     数据库事件     |        触发时机        |             触发器的级             |
| :---------------: | :-------------------: | :-------------------------------: |
| AFTER SERVERERROR | 一个Oracle错误被抛出时 | 可以创建基于数据库或模式一级的触发器 |
|    AFTER LOGON    |  一个用户登录数据库时   | 可以创建基于数据库或模式一级的触发器 |
|   BEFORE LOGOFF   |  一个用户退出数据库时   | 可以创建基于数据库或模式一级的触发器 |
|   AFTER STARTUP   |      开启数据库时      |     只能创建数据库一级的触发器      |
|  BEFORE SHUTDOWN  |    正常关闭数据库时    |     只能创建数据库一级的触发器      |


**权限**

- 仅仅拥有 `CREATE ANY TRIGGER`的权限是不够的，创建触发器(trigger)时，ORACLE有一个限制，触发器(trigger)的拥有者必须被显示(explicitly)授予访问触发器(trigger)中涉及到的对象的权限(也就是说这些权限不能由角色继承而来)。
- 则创建数据库级触发器需要：`ADMINISTER DATABASE TRIGGER`权限

#### 例：用户登录'登出触发器

**用户级触发器**

```plsql
--创建序列，当作编号log_id
CREATE SEQUENCE log_onoff_seq
 START WITH 1
 INCREMENT BY 1
 MAXVALUE 99
 NOCACHE
 CYCLE;

--存放用户登入登出信息
CREATE TABLE log_onoff_table
(
 log_id NUMBER(3)
,user_id VARCHAR2(38)
,log_date DATE
,action VARCHAR2(50)
);

--存放用户错误信息
CREATE TABLE error_table
(
 user_name VACHAR2(20)
,error_time DATE
,error_msg VARCHAR2(50)
);

--登入触发器
CREATE OR REPLACE TRIGGER user_log_on_trigger
AFTER LOGON ON SCHEMA
 DECLARE
   v_log_id NUMBER;
 BEGIN 
   SELECT log_onoff_seq.NEXTVAL
   INTO v_log_id
   FROM dual;
 
   INSERT INTO log_onoff_table
   VALUES(v_log_id,USER,SYSDATE,'用户登入');
 END;
 
--登出触发器 --有问题！！！无法写入
CREATE OR REPLACE TRIGGER log_off_trigger
BEFORE LOGOFF ON SCHEMA
  BEGIN
    INSERT INTO log_onoff_table
    VALUES(log_onoff_seq.NEXTVAL,USER,SYSDATE,'用户登出');
  END;
 
--ERROR记录触发器
CREATE OR REPLACE TRIGGER error_message_trigger
AFTER SERVERERROR ON SCHEMA
  BEGIN
    INSERT INTO error_table
    VALUES (USER,SYSDATE,'.');
    --如何获取异常信息
  END;
```

**数据库级触发器** 

```
--SYSDBA赋予scott ：ADMINISTER DATABASE TRIGGER权限
GRANT ADMINISTER DATABASE TRIGGER TO scott;

--存放用户登入登出信息
CREATE TABLE system_log_onoff_table
(
 user_id VARCHAR2(38)
,log_date DATE
,action VARCHAR2(50)
);

--登入触发器
CREATE OR REPLACE TRIGGER sys_log_on_trigger
AFTER LOGON ON DATABASE
 BEGIN 
   INSERT INTO system_log_onoff_table
   VALUES(USER,SYSDATE,'用户登入');
 END;
 
--登出触发器
CREATE OR REPLACE TRIGGER sys_log_off_trigger
 BEFORE LOGOFF ON DATABASE
  BEGIN
    INSERT INTO system_log_onoff_table
    VALUES(USER,SYSDATE,'用户登出');
  END;

--ERROR记录触发器

--开启数据库触发器
CREATE OR REPLACE TRIGGER sys_startup_database_trigger
 AFTER STARTUP ON DATABASE
   BEGIN
     INSERT INTO system_log_onoff_table
     VALUES('orcl数据库',SYSDATE,'启动数据库');
   END;
   
--关闭数据库触发器
CREATE OR REPLACE TRIGGER sys_startup_database_trigger
 BEFORE SHUTDOWN ON DATABASE
   BEGIN
     INSERT INTO system_log_onoff_table
     VALUES('orcl数据库',SYSDATE,'关闭数据库');
   END;
```  

**问**

- 可以使用视图的方式，来避免其他用户的直接修改。但是要怎么实现插入新的信息？

#### 数据库级系统触发器的权限问题

**报错** 创建数据库级触发器

- ORA-04098: 触发器 'SYS.USER_LOG_ON_TRIGGER' 无效且未通过重新验证


**删除**

- 删除之前的触发器或DISABLE(简单粗暴)

```plsql
ALTER TRIGGER user_log_on_trigger DISABLE;
DROP TIRGGER user_log_on_trigger;
```

- 掀桌子 SYSDBA

```plsql
SQL> DROP TRIGGER user_log_on_trigger;

触发器已删除。
```

- 查看

```plsql
SELECT trigger_name
      ,trigger_type
      ,triggering_event
      ,status
FROM USER_TRIGGERS;
```

### CALL调用语句

- 在触发器体中使用，调用一个存储过程（可看作一个PL/SQL程序块使用），该过程可以是PL/SQL，C，C++，Java语言。

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
CREATE OR REPLACE PROCEDURE update_sal_action
 IS
 BEGIN
  DBMS_OUTPUT.PUT_LINE('更新成功！');
 END;

--创建触发器并使用CALL调用过程
CREATE OR REPLACE TRIGGER update_sal_trigger
 BEFORE UPDATE OR INSERT OF salary ON copy_emp
 FOR EACH ROW
 WHEN (NEW.employee_id <> 1)
 --调用过程update_sal_action
 CALL update_sal_action
```

### 触发器的管理与维护

- 每个触发器都具有激活(ENABLED)和禁止(DISABLED)两种模式（状态），一个触发器只能处于ENABLED状态或DISABLED状态。
1. ENABLE: 如果发出了一个触发语句并且该触发器的限制（如果有的话）评估（测试）为TRUE(默认)，那么触发器运行它的触发器操作(PL/SQL程序代码)。
2. DISABLE: 触发器不运行它的触发器操作(PL/SQL程序代码)，即使发出了一个触发语句并且该触发器的限制（如果有的话）评估为TRUE也不运行。

当一个触发器被首次创建时，它的状态默认是ENABLED。Oracle服务器对激活的触发器要检查所定义的完整性约束并保证这些触发器不会违反任何定义的完整性约束。另外，Oracle服务器还为查询和约束提供读取一致性的视图、管理依赖关系，并且如果一个触发器是修改分布数据库中远程的表,   Oracle服务器还提供一种两阶段的提交处理过程。即可以利用ALTER TRIGGER语句控制指定的触发器的状态，也可以利用ALTER TABLE语句控制指定表上所有触发器的状态。

- 关闭（禁止）或重新开启（激活）一个数据库触发器的命令如下：

```
ALTER TRIGGER 触发器名 DISABLE|ENABLE;
```

- 关闭（禁止）或重新开启（激活）一个表上的所有触发器的命令则为：

```
ALTER TABLE 表名 DISABLE|ENABLE ALL TRIGGERS;
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

**为什么将一个触发器设置为DISABLED状态**

其实，将一个触发器设置为DISABLED状态往往是一个无奈之举。有时一个系统可能已经满负荷运行，系统的效率很低。此时可能已经没有其他方法可以提高系统效率了，在这种情况下，就可能暂时关闭一个或多个触发器以换取系统效率的提高。实际上，这是一种以牺牲数据的完整性和系统安全为代价的系统优化举措，应该也是不得已而为之。系统的安全与效率永远是一对矛盾，越安全的系统，效率往往越低，反之效率越高的系统，就越不安全。最后，作为系统的开发者或管理者要在这两者之间做出艰难的平衡。一般触发器处在DISABLED状态应该是一个临时而短暂的状态，一旦系统效率正常之后，应该尽快将这些触发器重新设置回ENABLED。一般将一个触发器临时设置成DISABLED状态的情况可能如下：

1. 该触发器所引用的一个对象无法获得。
2. 在执行大规模数据装入操作时，想不触发触发器以加快数据的装入。
3. 重新装入数据。

在实际工作中，所开发的数据库触发器要经过严格的测试之后才敢在真正生产系统上使用。测试触发器的程序代码一般是一个相当耗时的测试过程，一般触发器越复杂要测试的细节可能就越多，通常测试一个数据库触发器的基本步骤如下：

1. 测试每一个触发的数据操作，以及没有触发的数据操作。
   - 首先测试大多数成功的情况。
   - 测试最可能失败的情况以观察触发器能否恰当地处理。
2. 测试WHEN子句的每一种可能。
3. 测试由基本数据造成的触发器的直接触发以及由过程引起的间接触发。
4. 测试触发器对其他地触发器的影响。
5. 测试其他触发器对该触发器的影响。
6. 可使用DBMS OUTPUT软件包调试（排错)触发器。

#### 数据字典 USER_OBJECTS , USER_TRIGGERS

- 尽量使用USER_TRIGGERS数据字典来查询信息。USER_OBJECT信息可能有误。

```
SELECT object_id
      ,object_name
      ,created
      ,object_type
FROM USER_OBJECTS
WHERE object_type = 'TRIGGER';
```

```
SELECT trigger_name
      ,trigger_type
      ,triggering_event
      ,status
FROM USER_TRIGGERS;
```

#### 触发器的权限问题

- 创建触发器的权限 需要：
    1. `CREATE TRIGGER` CREATE ANY TRIGGER
    2. 拥有在触发语句中所指定的表，
    3. 且有对这个表的ALTER权限。或ALTER ANY TABLE权限

- 如果用户的触发器引用了如何不在用户模式中的对象，那么创建该触发器的用户必须在引用的过程，函数和软件包上具有执行权限，且该执行权限不是通过角色授予的。

- 创建数据库级触发器 需要：`ADMINISTER DATABASE TRIGGER`权限

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

 
**存储函数FUNCTION和过程PROCEDURE**

- [存储函数：有返回值，创建完成后，通过SELECT FUNCTION() FROM dual;执行]
- [存储过程：由于没有返回值，创建完成后，不能使用SELECT语句，只能使用pl/sql块执行]

|         PROCEDURE          |              FUNCTION               |
| :------------------------: | :---------------------------------: |
|    作为一个PL/SQL语句执行    |         作为一个表达式来调用          |
|    在头中不包含RETURN子句    | 在头中必须包含且只能包含一个RETURN子句 |
|  可以使用多个输出参数传递值   |         必须返回一个单一的值          |
| 可以包含一个无值的RETURN语句 |      必须包含至少一个RETURN语句       |

### 过程 PROCEDURE

- 存储过程（Procedure）是SQL语句和流程控制语句的预编译集合
语法：

```plsql
CREATE [OR REPLACE] PROCEDURE 过程名
    [(参数1 [方式] 数据类型1 --不能有长度
     ,...)]
IS | AS  --PL/SQL程序块
    [本地变量的声明;...]
BEGIN
 --执行的操作;
END [过程名];
```

- 在存储过程中，IS或AS之后的语句是PL/SQL程序块
- IS或AS 相当于替代了DECLARE关键字，作用可看作相同 ，
   - 可以正常声明在PL/SQL程序块中的声明（包括再声明一个过程）
- **参数中不能带有长度信息**

**调用过程**

1.

```plsql
EXECUTE 过程(参数,...);
```
```plsql
VARIABLE 变量 数据类型(长度);

EXECUTE :变量 := 赋值;

EXECUTE 过程(:变量);
```

2.

```plsql
DECLARE
 OUT参数对应的赋值实参的声明；
BEGIN
  过程(参数,赋值实参,...);
END;
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
CREATE OR REPLACE PROCEDURE raise_salary
(
 p_emp_id IN copy_emp.employee_id%TYPE
,p_rate IN NUMBER
)
IS
BEGIN
  UPDATE copy_emp
  SET salary = salary * (1 + p_rate * 0.01)
  WHERE employee_id = p_emp_id;
END raise_salary;
```

```plsql
EXECUTE raise_salary
```   

#### OUT参数的使用

- 作为输出对象,
- 需要一个变量，无需赋值

```plsql
CREATE OR REPLACE PROCEDURE get_employee
(
 p_emp_id IN copy_emp.employee_id%TYPE
,p_name OUT copy_emp.last_name%TYPE
,p_sal OUT copy_emp.salary%TYPE
,p_job OUT copy_emp.job_id%TYPE
)
IS
BEGIN
  SELECT last_name
        ,salary
        ,job_id
  INTO p_name
      ,p_sal
      ,p_job
  FROM copy_emp
  WHERE employee_id = p_emp_id;
END get_employee;
```

```plsql
DECLARE
 v_last_name copy_emp.last_name%TYPE;
 v_sal copy_emp.salary%TYPE;
 v_job copy_emp.job_id%TYPE;
BEGIN
  get_employee(179,v_last_name,v_sal,v_job);
  --使用过程
  DBMS_OUTPUT.PUT_LINE('**************************'||
                       'name:'||CHR(9)|| v_last_name ||CHR(10)||
                       'sal:'||CHR(9)||v_sal||CHR(10)||
                       'job:'||CHR(9)||v_job||CHR(10)
                       );
END;
```

#### IN OUT参数的使用

- 作为输入和输出的对象
- 需要一个已经赋值的变量

```plsql
CREATE OR REPLACE PROCEDURE standard_phone
(
 p_phone_id IN OUT VARCHAR2
)
IS
BEGIN
  p_phone_id := '(' || SUBSTR(p_phone_id,1,3) ||')'||CHR(9)||
                '-' || UPPER(p_phone_id);
  DBMS_OUTPUT.PUT_LINE(p_phone_id);
END standard_phone;
```

```plsql
SQL> VARIABLE g_phone_no VARCHAR2(20)
SQL> EXECUTE :g_phone_no := 'xiaomi'

PL/SQL 过程已成功完成。

SQL> PRINT

G_PHONE_NO
--------------------------------------------------------------------------------
xiaomi

SQL> EXECUTE standard_phone(:g_phone_no);

PL/SQL 过程已成功完成。
```
```
DECLARE
 v_phone_no VARCHAR2(20) := 'ximao';
BEGIN
  standard_phone(v_phone_no);
END;
```  
  
##### 使用`SQP*PLUS`查看OUT参数

1. 声明`SQL*PLUS`的宿主变量
2. 如果是IN OUT参数需要为这个变量赋值
3. 以宿主变量为参数执行过程
4. 使用`SQL*PLUS`的PRINT命令
                          
#### 传递实参的表示法

```plsql
CREATE OR REPLACE PROCEDURE update_sal
(
 p_id IN copy_emp.employee_id%TYPE
,p_new_job IN copy_emp.job_id%TYPE
,p_new_sal IN copy_emp.salary%TYPE
,p_dept OUT copy_emp.department_id%TYPE
)
IS
BEGIN
  UPDATE copy_emp
  SET copy_emp.salary = p_new_sal
     ,job_id = p_new_job
  WHERE employee_id = p_id;
  
  SELECT department_id
  INTO p_dept
  FROM copy_emp
  WHERE employee_id = p_id;
END; 
```

1. 按位置

- 以所声明的形参相同的位置列出实参

```plsql
SQL> VARIABLE v_dept NUMBER;
SQL> EXECUTE update_sal(117,'IT',7000,:v_dept);
```

2. 按名字

- 以任意顺序列出实参和与之对应的形参。
- 使用关联操作符`=>`    

```plsql
SQL> VARIABLE v_dept NUMBER;
SQL> EXECUTE update_sal(p_id => 117, p_dept => :v_dept, p_new_job => 'IT', p_new_sal => 9000);
```

3. 组合

- 前两种混合使用

#### 在过程中声明和调用另一个过程

```plsql
CREATE OR REPLACE PROCEDURE audit_emp_dml
(
 p_id IN copy_emp.employee_id%TYPE
)
IS
 PROCEDURE log_exec
 IS
 BEGIN
   INSERT INTO log_table(user_id,log_date,employee_id)
   VALUES(TO_CHAR(USER),SYSDATE,p_id);
 END log_exec;
BEGIN
  DELETE FROM copy_emp
  WHERE employee_id = p_id;
  log_exec;
END audit_emp_dml;
```
```plsql
CREATE OR REPLACE PROCEDURE reset_sal
(
 p_new_sal IN NUMBER
,p_grade IN NUMBER(2) 
)
IS 
  error_sal EXCEPTION;
  
  PROCEDURE check_sal
    (
    ,p_max IN NUMBER
    ,p_min IN NUMBER
    ) 
    IS
    BEGIN
      IF p_new_sal NOT BETWEEN p_max AND p_min THEN
        RAISE error_sal;
      END IF;
    END check_sal;
BEGIN
   IF p_grade = 1 THEN
       check_sal(1000,5000);
       DBMS_OUTPUT.PUT_LINE('工资为：'||p_new_sal||CHR(9)||'等级为：'||p_grade);
   ELSIF p_grade = 2 THEN
       check_sal(5000,10000);
       DBMS_OUTPUT.PUT_LINE('工资为：'||p_new_sal||CHR(9)||'等级为：'||p_grade);
   ELSE 
     RAISE error_sal;
   END IF;
EXCEPTION
   WHEN error_sal THEN
     DBMS_OUTPUT.PUT_LINE('输入工资有误');
     COMMIT;
   WHEN OTHERS THEN
     ROLLBACK;
END reset_sal;

ORA-00900: 无效 SQL 语句
```

#### 在过程中处理异常

```plsql
CREATE OR REPLACE PROCEDURE update_employee
(
 p_id IN NUMBER
)
IS
 emp_null EXCEPTION;
BEGIN
  UPDATE copy_emp
  SET salary = 1000
  WHERE employee_id = p_id;
  
  IF(SQL%NOTFOUND OR SQL%NOTFOUND = NULL) THEN
     RAISE emp_null;
  END IF;
EXCEPTION
  WHEN emp_null THEN
    DBMS_OUTPUT.PUT_LINE('该员工不存在');
    ROLLBACK;
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('其他错误');
    ROLLBACK;
END update_employee;
```

##### 在调用过程时有异常处理和没有异常处理的作用

- 在调用过程时，如果在过程中出现了异常，而存储过程中有相应的异常段来处理异常，即捕获异常，调用该过程的PL/SQL程序块可以进行执行。而如果在存储过程中没有相应的异常段来处理异常，则会出现传播异常，使得整个PL/SQL程序块终止执行。

**例**

- 要操作的表

```plsql
CREATE TABLE test
(
 use_name VARCHAR2(25) UNIQUE
,use_date DATE
,use_errors VARCHAR2(50)
)
```

- 有带异常处理的存储过程

```plsql
CREATE OR REPLACE PROCEDURE user_test
(
 p_name IN test.use_name%TYPE
)
IS
BEGIN
  INSERT INTO test(use_name,use_date)
  VALUES(p_name,SYSDATE);
EXCEPTION
  WHEN OTHERS THEN
    COMMIT;
END user_test;
```

- 没有带异常处理的存储过程

```plsql
CREATE OR REPLACE PROCEDURE user_test
(
 p_name IN test.use_name%TYPE
)
IS
BEGIN
  INSERT INTO test(use_name,use_date)
  VALUES(p_name,SYSDATE);
END user_test;
```

- 测试语句

```plsql
BEGIN
  user_test('1');
  user_test('1'); --use_name唯一性不可重复的异常
  user_test('2');
END;
```

- 有带异常处理的结果
  - 成功执行了PL/SQL程序块 ，只有出现异常的部分转入异常段处理。 

```plsql
SQL> /
PL/SQL procedure successfully completed

SQL> SELECT*
  2  FROM test
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
BEGIN
  user_test('1');
  user_test('1'); --use_name唯一性不可重复的异常
  user_test('2');
END;
ORA-00001: 违反唯一约束条件 (SCOTT.SYS_C0013141)
ORA-06512: 在 "SCOTT.USER_TEST", line 7
ORA-06512: 在 line 3

SQL> SELECT *
  2  FROM test
  3  ;
USE_NAME                  USE_DATE    USE_ERRORS
------------------------- ----------- --------------------------------------------------

```


#### 存储过程的删除与查看

##### 查看

- 通过查找模式对象的方法
  - 数据字典：user_objects

```plsql
SQL> COL OBJECT_NAME FOR A20
--先设置显示格式

SELECT object_id
      ,object_name
      ,created
      ,status
FROM user_objects
WHERE object_type = 'PROCEDURE';
```
![](C:/Users/zjk10/OneDrive/NoteBook/pictures/54505115221047.png =402x)

- status属性：
   - VALID 表示可以被调用
   - INVALID 表示不可以被调用，必须重新编辑
      - 一般由于该工程使用的对象（通常是表）的定义被修改了 

##### 删除

```plsql
DROP PROCEDURE 过程;
```

#### 例

##### 定义一个存储过程: 获取给定部门的工资总和(通过 out 参数), 要求:部门号和工资总额定义为参数

```plsql
CREATE OR REPLACE PROCEDURE sum_sal_procedure
(
 p_dept_id NUMBER
,v_sum_sal OUT NUMBER
)
IS
CURSOR sal_cursor IS 
   SELECT salary 
   FROM employees 
   WHERE department_id = p_dept_id;
BEGIN
       v_sum_sal := 0;
       FOR sal_rec IN sal_cursor LOOP
           --dbms_output.put_line(sal_rec.salary);
           v_sum_sal := v_sum_sal + sal_rec.salary;
       END LOOP;       
       dbms_output.put_line('sum salary: ' || v_sum_sal);
END;

[执行]
DECLARE 
     v_sum_sal number(10) := 0;
BEGIN
     sum_sal_procedure(80,v_sum_sal);
END;
```

##### 自定义一个存储过程完成以下操作

```plsql
对给定部门(作为输入参数)的员工进行加薪操作, 若其到公司的时间在 (? , 95) 期间,为其加薪 %5
                                                         [95 , 98)              %3       
                                                         [98, ?)                %1
得到以下返回结果: 为此次加薪公司每月需要额外付出多少成本(定义一个 OUT 型的输出参数).
CREATE OR REPLACE PROCEDURE add_sal_procedure
(
 p_dept_id NUMBER
,temp OUT NUMBER
)
IS
       CURSOR sal_cursor IS 
          SELECT employee_id id
                ,hire_date hd
                ,salary sal 
          FROM employees 
          WHERE department_id = p_dept_id;

       a NUMBER(4, 2) := 0;
BEGIN
       temp := 0;       
       FOR c IN sal_cursor LOOP
           a := 0;    
           IF c.hd < to_date('1995-1-1', 'yyyy-mm-dd') THEN
              a := 0.05;
           ELSIF c.hd < to_date('1998-1-1', 'yyyy-mm-dd') THEN
              a := 0.03;
           ELSE
              a := 0.01;
           END IF;
           temp := temp + c.sal * a;
           UPDATE employees 
           SET salary = salary * (1 + a) 
           WHERE employee_id = c.id;
       END LOOP;       
END;
```

### 函数 FUNCTION

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
CREATE OR REPLACE FUNCTION 函数名
(
 参数1 [模式1] 数据类型1
 ,...
)
RETURN 返回值的数据类型 
IS|AS 
 --PL/SQL块变量、记录类型、游标的声明(类似于前面的DECLARE的部分)
BEGIN
 --函数体(可以实现增删改查等操作，返回值需要return)
       RETURN 表达式;
       --RETURN子句中的数据类型一定不能包括数据长度。
END [函数名];
```

- 虽然在函数中可以使用OUT和INOUT参数，但是这并不是一个良好的编程习惯，
    - 因为这样的函数有多个出口，所以它们很难调试和维护。
    - 因此，如需要从一个函数返回多个值时，最好考虑使用组合数据类型，如PL/SQL的记录或INDEX BY表(PL/SQL的数组)。

```plsql
CREATE OR REPLACE FUNCTION get_sal
(
 v_id IN copy_emp.employee_id%TYPE
)
RETURN NUMBER
IS
 v_sal copy_emp.salary%TYPE := 0;
BEGIN
  SELECT salary
  INTO v_sal
  FROM copy_emp
  WHERE employee_id = v_id;
  
  RETURN v_sal;
END get_sal;
```
- 调用

1. PL/SQL程序块

```plsql
DECLARE 
BEGIN
  DBMS_OUTPUT.PUT_LINE('sal:'||get_sal(177));
END;
```

2.  EXECUTE语句

```plsql
EXECUTE DBMS_OUTPUT.PUT_LINE(get_sal(177));
```

3. 在SQL语句中调用

```sql
SELECT employee_id
      ,get_sal(employee_id) sal
      --需要限制查询只返回一个值
      --否则：ORA-01422: 实际返回的行数超出请求的行数
FROM copy_emp
WHERE employee_id = 177
```

4. 当查询不到返回值时：

```sql
ORA-01403: 未找到任何数据
```

#### 在SQL表达式中使用用户定义的函数

- 扩展了SQL的功能，特别是在执行非常复杂、非常令人费解或SQL无法完成的计算时，非常有用。
- 与在应用程序中过滤数据相比，使用函数在WHERE子句中过滤数据可以提高效率，因为可以创建一个基于这个函数的索引以提高查询的效率。
- 可以增加数据的独立性，因为复杂的数据分析处理是在Oracle服务器中进行的，而不是将数据提取到应用程序中进行处理。如果数据量大，利用存储函数会明显减少网络的流量。
- 存储函数是可以共享的，在第一次调用时这个函数会被装入数据库的内存缓冲区，因此之后的调用就可能使用的是内存中的版本，当然速度会快很多。另外，也可以将经常使用的函数常驻内存以提高效率。
- 通过对字符串编码和使用函数来操作这一字符串，可以维护和操控这一新的数据类型（如提取电话号码中的国家号、地区号或本地号)。

**只要允许使用SQL内置单行函数的地方就可以调用**

- SELECT子句的列表
- WHERE子句和HEVING子句的条件
- CONNECT BY和START WITH和ORDER BY和GROUP BY子句
- INSERT语句中的VALUES子句
- UPDATE语句中的SET子句

```plsql
CREATE OR REPLACE FUNCTION format_phone
(
 p_phone IN VARCHAR2
)
RETURN VARCHAR2
IS
 v_phone VARCHAR2(38);
BEGIN
 v_phone := '('||SUBSTR(p_phone,1,3)||')'||
            '-'||SUBSTR(p_phone,5,3) ||
            '-'||SUBSTR(p_phone,9);
 RETURN v_phone;
END;
```
```sql
SELECT employee_id
      ,last_name
      ,format_phone(phone_number)
FROM employees;
```

##### 从SQL表达式调用函数的限制

- 为了从SQL表达式中调用一个用户定义的SQL函数，这用户定义的函数必须满足如下条件：
  - 该函数只能是存储函数
  - 该函数只接受IN参数
  - 只接受有效的SQL数据类型作为参数，不接受PL/SQL说明的数据类型做参数
  - 返回的数据类型只能是有效的SQL数据类型，而不能是PL/SQL说明的数据类型
- 另外，在一个SQL表达式中调用一个函数时也有如下限制：
  1. 所有的参数必须使用位置表示法，而不能使用名字表示法。
  2. 必须拥有所调用的函数或者在所调用的函数上有执行(EXECUTE)权限。
- 在用户定义的PL/SQL函数上还有一些额外的限制，它们包括：
   - 不能在CREATE TABLE或ALTER TABLE 语句的CHECK约束子句中调用这样的函数
   - 不能使用这样的函数为一个列说明默认值
- 需要指出的是：在一个SQL表达式中只能调用存储函数，而不能调用存储过程，除非这个过程是从一个函数中调用的并且满足以上所列的要求。
- 要执行一个调用存储函数的SQL语句，Oracle服务器就必须确定所调用的函数是不是没有一些特定的副作用，因为这些副作用可能会对数据库中的表产生无法接受的更改。为此，当在一个SQL语句的表达式中调用一个函数时，Oracle需加上以下一些附加的限制：
   - 从表达式中调用函数时，该函数不能包含DML语句，即该函数不能修改数据库中表的数据。
   - 当从一个UPDATE/DELETE语句中调用一个函数时，该函数不能查询或更改这个语句正在操作的表。
   - 当从一个SELECT、INSERT,UPDATE或DELETE语句中调用一个函数时，该函数不能直接地或通过子程序（或SQL)间接地执行事物控制语句，如：
       - 一个COMMIT或ROLLBACK语句
       - 一个会话控制语句（如SET ROLE)
       - 一个系统控制语句（如ALTER SYSTEM)
       - 任何DDL语句（如CREATE)
       

##### 从SQL中用名字表示法和混合表示法调用函数

```plsql
CREATE OR REPLACE FUNCTION test_func
(
 p_num1 IN NUMBER := 1
,p_num2 IN NUMBER := 1
)
RETURN NUMBER
IS
 v_num NUMBER;
BEGIN
  v_num := p_num1 + p_num2;
  RETURN v_num;
END test_func;
```

```sql
SQL> SELECT test_func(p_num2 => 4)
  2  FROM dual;
```

#### 函数的查询与删除

- 通过查找模式对象的方法
  - 数据字典：user_objects

```sql
SQL> COL OBJECT_NAME FOR A20
--先设置显示格式

SELECT object_id
      ,object_name
      ,created
      ,status
FROM user_objects
WHERE object_type = 'FUNCTION';
```

![](C:/Users/zjk10/OneDrive/NoteBook/pictures/Snipaste_2022-11-18_11-14-17.png =400x)

- status属性：
   - VALID 表示可以被调用
   - INVALID 表示不可以被调用，必须重新编辑
      - 一般由于该工程使用的对象（通常是表）的定义被修改了 


#### 应用

##### 函数的 helloworld: 返回一个 "helloworld" 的字符串

```plsql
CREATE OR REPLACE FUNCTION hello_func
RETURN VARCHAR2
IS
BEGIN
       RETURN 'helloworld';
END;

执行函数
BEGIN 
    dbms_output.put_line(hello_func());
END;
或者： 
SELECT hello_func() 
FROM dual;
```

##### 返回一个"helloworld: atguigu"的字符串，其中atguigu 由执行函数时输入

```plsql
--函数的声明(有参数的写在小括号里)
CREATE OR REPLACE FUNCTION hello_func(v_logo varchar2)
--返回值类型
RETURN VARCHAR2
IS 
--PL/SQL块变量的声明
BEGIN
       --函数体
       RETURN 'helloworld'|| v_logo;
END;
```

##### 创建一个存储函数，返回当前的系统时间

```plsql
CREATE OR REPLACE FUNCTION func1
RETURN DATE
IS
v_date DATE;  --定义变量
BEGIN
 --函数体
 --v_date := sysdate;
       SELECT sysdate 
       INTO v_date 
       FROM dual;
       dbms_output.put_line('我是函数哦');
       RETUEN v_date;
END;

执行法1：
SELECT func1 
FROM dual;
执行法2：
DECLARE
  v_date DATE;
BEGIN
  v_date := func1;
  dbms_output.put_line(v_date);
END;
```

##### 定义带参数的函数: 两个数相加

```plsql
CREATE OR REPLACE FUNCTION add_func(a NUMBER, b NUMBER)
RETURN NUMBER
IS
BEGIN
       RETURN (a + b);
END;
执行函数
BEGIN
    dbms_output.put_line(add_func(12, 13));
END;
或者
    SELECT add_func(12,13) 
    FROM dual;
```

##### 定义一个函数: 获取给定部门的工资总和, 要求:部门号定义为参数, 工资总额定义为返回值

```plsql
CREATE OR REPLACE FUNCTION sum_sal(dept_id NUMBER)
RETURN NUMBER
IS       
CURSOR sal_cursor IS SELECT salary 
                     FROM employees 
                     WHERE department_id = dept_id;
v_sum_sal NUMBER(8) := 0;   
BEGIN
       FOR c IN sal_cursor LOOP
           v_sum_sal := v_sum_sal + c.salary;
       END LOOP;       
       --dbms_output.put_line('sum salary: ' || v_sum_sal);
       RETURN v_sum_sal;
END;
执行函数
BEGIN
    dbms_output.put_line(sum_sal(80));
END;
```

##### 关于 OUT 型的参数: 因为函数只能有一个返回值, PL/SQL 程序可以通过 OUT 型的参数实现有多个返回值

```plsql
要求: 定义一个函数: 获取给定部门的工资总和 和 该部门的员工总数(定义为 OUT 类型的参数).
要求: 部门号定义为参数, 工资总额定义为返回值.
CREATE OR REPLACE FUNCTION sum_sal(dept_id NUMBER, total_count OUT NUMBER)
RETURN NUMBER
IS 
CURSOR sal_cursor IS SELECT salary 
                     FROM employees 
                     WHERE department_id = dept_id;
v_sum_sal NUMBER(8) := 0;   
BEGIN
       total_count := 0;
       FOR c IN sal_cursor loop
           v_sum_sal := v_sum_sal + c.salary;
           total_count := total_count + 1;
       END LOOP;       
       --dbms_output.put_line('sum salary: ' || v_sum_sal);
       RETURN v_sum_sal;
END;   

执行函数:
DECLARE 
  v_total NUMBER(3) := 0;
BEGIN
    dbms_output.put_line(sum_sal(80, v_total));
    dbms_output.put_line(v_total);
END;
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
   - 当一个子块处理了一个异常时，该子块正常结束，程序的控制直接转到紧随子块的END语句其后的语句。
   - 然而，如果一个PL/SQL程序抛出了一个异常并且当前程序块没有为这个异常定义异常处理程序，那么该异常就会传播到后续的包含块，直到找到一个异常处理程序为止。如果所有的包含程序都无法处理这个异常，在宿主环境的结果中就会产生一个无法处理的异常。当这个异常传播给个包含程序块时，原程序块中的其余的可执行语句将被绕过（忽略掉）。

- 以上所说的异常处理方式的好处是：
    - 可以在一个程序块中只包含该程序块所需的异常处理语包而将其他更为适用的异常处理语句放在包含块中。这样可以明显地减少代码量，也使程序的逻辑流程更为清晰。

- 与其他程序设计语言相比，PL/SOL的异常处理有着明显的优势。首先对于绝大多数数据库中出现的错误（异常），PL/SQL都可以隐含（自动）地抛出(RAISE)，这无疑降低了代码的复杂度，也减少了相应的代码量。另外，抛出的异常是统一跳转到异常段处理的，因此当在多个程序语句处需要同样的异常处理时，PL/SQL只需要一个异常处理程序，这使得异常处理的代码量明显减少，否则有可能异常（错误）处理的代码会淹没正常的程序语句。可以毫不夸张地说，恰当地使用PL/SOL的异常处理会使程序代码更为清晰、简练。

**格式**

```
EXCEPTION
  WHEN first_exception THEN 
     <code to handle first_exception>;
  WHEN seconde_exception then 
     <code to handle seconde_exception>; 
  WHEN OTHERS THEN 
     <code to handle others>;
END; 
```

- 当一个异常发生时，PL/SQL在离开这个异常段之前只执行一个异常处理子句。
- 一个异常段中只能有一个OTHERS子句。
- 异常不能出现在SQL语句或赋值语句中。

### [预定义异常]

- Oracle预定义的异常情况大概有24个，
- 对于这种异常情况的处理，无需在程序中定义，由Oracle自动将其引发

```
DECLARE
  v_sal employees.salary%TYPE;
BEGIN
  SELECT salary INTO v_sal
  FROM employees
  WHERE employee_id >100;
  dbms_output.put_line(v_sal);
EXCEPTION
  WHEN Too_many_rows THEN dbms_output.put_line('输出的行数太多了');
END;
```

### [非预定义异常]

- 即其他标准的Oracle错误
- 对这种异常情况的处理需要用户在程序中定义，然后由Oracle自动将其引发

#### 捕获非预定义的异常

- **使用PRAGMA EXCEPTION_INIT()函数为标准Oracle错误创建异常。**

- PRAGMA ：伪指令 
   - 表示这个语句时一个编译指令，而当PL/SQ程序块执行时不会被处理。
   - 指示PL/SQ编译器将在这个程序块中出现的所有该异常名解释成相关的Oracle服务器错误代码

```
DECLARE
  异常名 EXCEPTION; --声明异常名
  PRAGMA EXCEPTION_INIT(已经声明的异常,标准Oracle错误代码);
BEGIN
  ...;
EXCEPTION
  ...;
END;
```

```
DECLARE
  v_sal employees.salary%TYPE;
  --声明一个异常
  DELETE_mgr_excep EXCEPTION;
  --把自定义的异常和oracle的错误关联起来
  PRAGMA EXCEPTION_INIT(DELETE_mgr_excep,-2292);
BEGIN
  DELETE FROM employees
  WHERE employee_id = 100;
  
  SELECT salary INTO v_sal
  FROM employees
  WHERE employee_id > 100;
  
  dbms_output.put_line(v_sal);
EXCEPTION
  WHEN Too_many_rows THEN 
     dbms_output.put_line('输出的行数太多了');
  WHEN DELETE_mgr_excep THEN 
     dbms_output.put_line('Manager不能直接被删除');
  WHEN OTHERS THEN
     NULL; --如果存在该异常，则不做任何操作
END;
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

```
--先建一个表来存储错误信息
CREATE TABLE errors
(
 user_name VARCHAR2(255)
,error_date DATE
,error_code NUMBER(10)
,error_message VARCHAR2(255)
);

--PL/SQL程序块
DECLARE
 v_emp_id copy_emp.employee_id%TYPE := &p_emp_id;
 v_dept_id copy_emp.department_id%TYPE := &p_dept_id;
 
 v_error_code NUMBER;
 v_error_message VARCHAR2(255);

BEGIN
  INSERT INTO copy_emp(employee_id,last_name,department_id,job_id,salary)
  VALUES(v_emp_id,'一号',v_dept_id,'安保',5000);
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    
    v_error_code := SQLCODE;
    v_error_message := SQLERRM;
    
    INSERT INTO errors(user_name,error_date,error_code,error_message)
    VALUES(USER,SYSDATE,v_error_code,v_error_message);
    
    COMMIT;
END;
```
### [用户自定义异常]

- 程序执行过程中，出现编程人员认为的非正常情况。
- 对这种异常的处理，需要用户在程序中定义，然后显式地在程序中将其引发

```
DECLARE
  异常名 EXCEPTION;
BEGIN
  RAISE 异常名;  --抛出异常
EXCEPTION
  WHEN 异常名 THEN
    ...;
  WHEN OTHERS THEN
    ...;
```

- RAISE语句抛出异常

```
DECLARE 
 update_null EXCEPTION;
 too_high_sal EXCEPTION;
 too_low_sal EXCEPTION;
 
 v_id NUMBER := &p_id;
 v_sal NUMBER := &p_sal;
BEGIN
 UPDATE copy_emp
 SET job_id = '安保'
    ,salary = v_sal
 WHERE employee_id = v_id;
 
--利用隐式游标的属性SQL%NOTFOUND来确认是否找到员工
 IF SQL%NOTFOUND THEN
   RAISE update_null;
 END IF;
 
 IF (v_sal >= 5000) THEN
   RAISE too_high_sal;
 ELSIF v_sal <= 0 THEN
   RAISE too_low_sal;
 END IF;
 
 DBMS_OUTPUT.PUT_LINE('更新成功' || CHR(10) ||
                      'employee_id: ' || v_id ||CHR(10) ||
                      'salary: ' || v_sal || CHR(10)
                       );
                       
EXCEPTION
  WHEN update_null THEN
    DBMS_OUTPUT.PUT_LINE('查询不到该员工，更新失败');
    ROLLBACK;
  WHEN too_high_sal THEN
    DBMS_OUTPUT.PUT_LINE('工资过高(1~5000)，更新失败');
    ROLLBACK;
  WHEN too_low_sal THEN
    DBMS_OUTPUT.PUT_LINE('工资过低(1~5000)，更新失败');
    ROLLBACK;
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('其他错误：' || SQLCODE || ' : ' || SQLERRM);
END;
```

```
DECLARE
  v_sal employees.salary%TYPE;
  
  --声明一个异常 非预定义异常
  DELETE_mgr_excep EXCEPTION;
  --把自定义的异常和oracle的错误关联起来
  PRAGMA EXCEPTION_INIT(DELETE_mgr_excep,-2292);
  
  --声明一个异常 用户自定义的异常
  too_high_sal EXCEPTION;
BEGIN
  SELECT salary INTO v_sal
  FROM employees
  WHERE employee_id =100;
  
  IF v_sal > 1000 THEN
     RAISE too_high_sal;
     --使用RAISE语句显式的抛出异常
  END IF;
  
  DELETE FROM employees
  WHERE employee_id = 100;
  
  dbms_output.put_line(v_sal);
EXCEPTION
  WHEN Too_many_rows THEN 
     dbms_output.put_line('输出的行数太多了');
  WHEN DELETE_mgr_excep THEN 
     dbms_output.put_line('Manager不能直接被删除');
  --处理异常
  WHEN too_high_sal THEN 
     dbms_output.put_line('工资过高了');
END;
```

### RAISE_APPLICATION_ERROR过程

- RAISE_APPLICATION_ERROR过程以一种与预定义异常的显示格式一样的方式返回一个非标准的错误代码和错误信息（用户自己定义的错误代码和错误信息）
- 语法

```
RAISE_APPLICATION_ERROR(用户说明的异常号码,'用户定义的异常信息'[,{TRUE | FALSE}])

用户说明的异常号码 范围只能是-20000~20999
用户定义的异常信息 字符串 最大2048个字节
{TRUE | FALSE}: TRUE 这个错误被放在之前错误层之上
                FALSE 默认 这个错误取代之前所有的错误
```

- RAISE_APPLICATION_ERROR过程的主要用处是处理SQLCODE和SQLERRM函数的返回值。
    -  该过程在日志表中提供了一致的记录错误信息的方法。
- 要注意的是，RAISE_APPLICATION_ERROR过程将终止所在PL/SQL程序块中语句的进一步执行。
- RAISE_APPLICATION_ERROR过程既可以用在PL/SQL程序的执行段中，也可以用在PL/SQL程序的异常段中，或同时用在这两个段中。
- 无论是Oracle服务器产生的预定义的错误、非预定义的错误，还是用户定义的错误，该过程都会返回一致的错误信息，都是以错误号码和错误信息的方式显示给用户。

```
DECLARE
 e_invalid_employee EXCEPTION;
 PRAGMA EXCEPTION_INIT(e_invalid_employee,-20001);

BEGIN
  DELETE FROM copy_emp
  WHERE last_name = '&p_last_name';
  
  IF SQL%NOTFOUND THEN
    RAISE e_invalid_employee;
  END IF;
  
  COMMIT;
EXCEPTION
  WHEN e_invalid_employee THEN    
    RAISE_APPLICATION_ERROR(-20999,'公司没有雇佣该员工');
    --并没有要求与PRAGMA EXCEPTION_INIT语句中的一样，
    --只显示RAISE_APPLICATION_ERROR语句中的参数
    --所以PRAGMA EXCEPTION_INIT语句在这有什么用呢？
    --将所说明的异常与一个自定义的错误号码关联起来，在编程时，更好。
END;
```

```
DECLARE
 e_invalid_employee EXCEPTION;
 --用户自定义的异常
BEGIN
  DELETE FROM copy_emp
  WHERE last_name = '&p_last_name';
  
  IF SQL%NOTFOUND THEN
    RAISE e_invalid_employee;
  END IF;
  
  COMMIT;
EXCEPTION
  WHEN e_invalid_employee THEN
    RAISE_APPLICATION_ERROR(-20099,'公司没有雇佣该员工');
END;
```

### 异常的基本程序

#### 通过 SELECT ... INTO ... 查询某人的工资, 若没有查询到, 则输出 "未找到数据"

```
DECLARE
  --定义一个变量
  v_sal employees.salary%TYPE;
BEGIN
  --使用 SELECT ... INTO ... 为 v_sal 赋值
  SELECT salary 
  INTO v_sal 
  FROM employees 
  WHERE employee_id = 1000;
  
  dbms_output.put_line('salary:　' || v_sal);
EXCEPTION
  WHEN No_data_found THEN 
       dbms_output.put_line('未找到数据');
END;
或
DECLARE
  --定义一个变量
  v_sal employees.salary%TYPE;
BEGIN
  --使用 SELECT ... INTO ... 为 v_sal 赋值
  SELECT salary 
  INTO v_sal 
  FROM employees;
  
  dbms_output.put_line('salary:　' || v_sal);
EXCEPTION
  WHEN No_data_found THEN 
       dbms_output.put_line('未找到数据!');
  WHEN Too_many_rows THEN 
       dbms_output.put_line('数据过多!');     
END;
```

#### 更新指定员工工资，如工资小于300，则加100；对 NO_DATA_FOUND 异常, TOO_MANY_ROWS 进行处理

```
DECLARE
   v_sal employees.salary%TYPE;
BEGIN
   SELECT salary 
   INTO v_sal 
   FROM employees
   WHERE employee_id = 100;
  
   IF(v_sal < 300) THEN 
      UPDATE employees 
      SET salary = salary + 100
      WHERE employee_id = 100;
   ELSE dbms_output.put_line('工资大于300');
   END IF;
EXCEPTION
   WHEN no_data_found THEN 
      dbms_output.put_line('未找到数据');
   WHEN too_many_rows THEN 
      dbms_output.put_line('输出的数据行太多');
END;
```

#### 处理非预定义的异常处理: "违反完整约束条件"

```
DECLARE
  --1. 定义异常 
  temp_EXCEPTION EXCEPTION;
  --2. 将其定义好的异常情况，与标准的 ORACLE 错误联系起来，使用 EXCEPTION_INIT 语句
  PRAGMA EXCEPTION_INIT(temp_EXCEPTION, -2292);
BEGIN
  DELETE FROM employees 
  WHERE employee_id = 100;
EXCEPTION
  --3. 处理异常
  WHEN temp_EXCEPTION THEN
       dbms_output.put_line('违反完整性约束!');
END;
```

#### 自定义异常: 更新指定员工工资，增加100；若该员工不存在则抛出用户自定义异常: no_result

```
DECLARE
  --自定义异常                                   
  no_result EXCEPTION;   
BEGIN
  UPDATE employees 
  SET salary = salary + 100 
  WHERE employee_id = 1001;
  --使用隐式游标, 抛出自定义异常
  IF sql%notfound THEN
     raise no_result;
  END IF;  

EXCEPTION

  --处理程序抛出的异常
  WHEN no_result THEN
     dbms_output.put_line('更新失败');
END;
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
1. 在一个子程序中定义的变量只能在这个子程序中被引用，并且对于外部组件是不可见的，即本地变量local var只能在过程A中使用。
2. 在一个软件包体中声明的私有软件包变量可以被同一软件包体中的其他组件所引用，但是它们对于软件包之外的任何子程序或对象都是不可见的，即在软件包体中的过程A和过程B是可以使用私有变量private var的，但是软件包之外的子程序或对象就不可以使用了。
- 而全局声明的组件在软件包的内部和外部都是可见的，例如：
1. 在一个软件包说明部分声明的一个公共变量可以在软件包之外引用和修改，即公共变量public_var可以在软件包之外引用。
2. 在个软件包说明部分声明的一个软件包子程序可以被外部的程序代码所调用——过程A可以从软件包之外的一个环境中调用。
- 私有子程序（如过程B)只能被该软件包中的公共子程序调用（如过程Λ）或者由被该软件包中的其他私有软件包结构所调用。

### 软件包的开发

**原则：**

1. 将一个软件包说明的语句正文与这个软件包体的语句分开存放在两个脚本文件中，方便对软件包说明或软件包体的修改
2. 一个软件包说明可以在没有软件包体的情况下存在，即软件包说明可以不说明子程序也不需要包体。
    - 而如果软件包说明不存在是不能创建软件包体的。

- Oracle服务器将软件包说明和软件包体分开存放。
   - 从而在改变软件包体中某个程序的实现时不会使所调用或引用程序结构的其他模式对象变为无效。
   
**创建软件包的说明**

```
CREATE OR REPLACE PACKAGE 包名 IS|AS
    声明的公共变量，常量，游标，异常，用户定义的数据类型和子类型
    公共过程和函数的声明
END [包名];
```

**创建软件包体**



```
--软件包说明
CREATE OR REPLACE PACKAGE salary_pkg IS
  v_std_salary NUMBER := 1380;
  PROCEDURE reset_salary(p_new_sal NUMBER, p_grade NUMBER);
END salary_pkg;

--软件包体
CREATE OR REPLACE PACKAGE BODY salary_pkg IS
  FUNCTION validate
  (
  p_sal NUMBER
  ,p_grade NUMBER
  ) 
  RETURN BOOLEAN 
  IS
    v_min_sal 	salgrade.losal%type;
    v_max_sal 	salgrade.hisal%type;
  BEGIN
    SELECT losal, hisal 
    INTO   v_min_sal, v_max_sal
    FROM   salgrade
    WHERE  grade = p_grade;
    RETURN (p_sal BETWEEN v_min_sal AND v_max_sal);
  END validate;

  PROCEDURE reset_salary
  (
  p_new_sal NUMBER
  ,p_grade NUMBER
  ) 
  IS 
  BEGIN
    IF validate(p_new_sal, p_grade) THEN
      v_std_salary := p_new_sal;
    ELSE RAISE_APPLICATION_ERROR(-20038, '工资超限!');
    END IF;
  END reset_salary;
END salary_pkg;
```

**软件包的调用**

```
SQL> EXECUTE salary_pkg.reset_salary(2450,4);
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
SELECT object_id
      ,object_name
      ,object_type
      ,created
      ,status
FROM user_objects
WHERE object_type IN ('PACKAGE','PACKAGE BODY');
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
CREATE OR REPLACE PACKAGE dept_pkg IS
 PROCEDURE add_dept
   (
    p_dept_id IN copy_dept.department_id%TYPE
   ,p_dept_name IN copy_dept.department_name%TYPE
   ,p_loc_id IN copy_dept.location_id%TYPE
   );
--重载过程add_dept
 PROCEDURE add_dept
   (
    p_dept_name IN copy_dept.department_name%TYPE
   ,p_loc_id IN copy_dept.location_id%TYPE
   );
END dept_pkg;
```

```
CREATE OR REPLACE PACKAGE BODY dept_pkg IS
 PROCEDURE add_dept
   (
    p_dept_id IN copy_dept.department_id%TYPE
   ,p_dept_name IN copy_dept.department_name%TYPE
   ,p_loc_id IN copy_dept.location_id%TYPE
   )
   IS
   BEGIN
     INSERT INTO copy_dept(department_id,department_name,location_id)
     VALUES(p_dept_id,p_dept_name,p_loc_id);
   END add_dept;
   
 PROCEDURE add_dept
   (
    p_dept_name IN copy_dept.department_name%TYPE
   ,p_loc_id IN copy_dept.location_id%TYPE
   )
   IS
   BEGIN
     INSERT INTO copy_dept(department_name,location_id)
     VALUES(p_dept_name,p_loc_id);
   END add_dept;
END dept_pkg;
```

### STANDARD软件包与子程序重载

- 定义了PL/SQL环境和PL/SQL程序可以自动获取的全局数据类型，异常和子程序的声明，在Oracle系统安装时自动安装。

- 查看：DESC STANDARD

- 如果在一个PL/SQL程序中重新声明了一个与内置函数同名的函数(如：`TO_CHAR()`)；则在调用内置函数时需要`STANDARD.TO_CHAR()`
- 如果重新声明的与内置函数同名的是一个独立子程序，则在访问该子程序时需要`用户名.TO_CHAR()`


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
CREATE OR REPLACE PACKAGE sal_pkg IS
 PROCEDURE reset_sal(p_sal NUMBER,p_grade NUMBER,p_id NUMBER);
END;
```

```
CREATE OR REPLACE PACKAGE BODY sal_pkg IS
--向前声明
  --检查工资是否符合要求
  FUNCTION check_sal(p_sal NUMBER,p_grade NUMBER) RETURN BOOLEAN;
  --根据员工号码更新工资
  PROCEDURE update_sal_emp(p_sal NUMBER,p_id NUMBER);
  
  PROCEDURE reset_sal
    (
     p_sal NUMBER
    ,p_grade NUMBER
    ,p_id NUMBER
    )
    IS
     sal_error EXCEPTION;
    BEGIN
      IF check_sal(p_sal,p_grade) THEN
        update_sal_emp(p_sal,p_id);
        DBMS_OUTPUT.PUT_LINE('更新成功');
      ELSE 
        RAISE sal_error;
      END IF;
    EXCEPTION
      WHEN sal_error THEN
        DBMS_OUTPUT.PUT_LINE('更新失败');
        ROLLBACK;
      WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('其他错误');
        ROLLBACK;
    END reset_sal;
    
  FUNCTION check_sal
    (
     p_sal NUMBER
    ,p_grade NUMBER
    )
    RETURN BOOLEAN
    IS
     v_min_sal salgrade.losal%TYPE;
     v_max_sal salgrade.hisal%TYPE;
    BEGIN
      SELECT losal
            ,hisal
      INTO v_min_sal
          ,v_max_sal
      FROM salgrade
      WHERE grade = grade;
      
      RETURN (p_sal BETWEEN v_min_sal AND v_max_sal);
    END check_sal;
    
   PROCEDURE update_sal_emp
     (
      p_sal NUMBER
     ,p_id NUMBER
     )
     IS
     BEGIN
       UPDATE copy_emp
       SET salary = p_sal
       WHERE employee_id = p_id;
     END update_sal_emp;
END sal_pkg;
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
CREATE OR REPLACE PACKAGE select_emp IS
  v_emp_dept copy_emp.department_id%TYPE := 1;
  v_emp_name copy_emp.last_name%TYPE;
  
  PROCEDURE emp_find(p_id NUMBER);
END;
```

```
CREATE OR REPLACE PACKAGE BODY select_emp IS
 PROCEDURE emp_find
   (
    p_id NUMBER
   )
   IS
   BEGIN
     SELECT last_name
     INTO v_emp_name
     FROM copy_emp
     WHERE employee_id = p_id;
   END emp_find;
   
--一次性过程：为软件包的变量赋初始值
  BEGIN
    v_emp_name := 'KING';
    SELECT department_id
    INTO v_emp_dept
    FROM copy_emp
    WHERE employee_id = 117;
  --没有END；直接以软件包体的END结束
END select_emp;
```

```
EXECUTE DBMS_OUTPUT.PUT_LINE(select_emp.v_emp_dept);
EXECUTE DBMS_OUTPUT.PUT_LINE(select_emp.v_emp_name);
EXECUTE select_emp.emp_find(117);
```

### 在SQL中使用软件包中的函数

- 当执行一个调用存储函数（包括存储软件包中的函数）的SQL语句时，Oracle服务器必须指定这些存储函数的纯净级别（即这些函数有没有副作用）
- 通常这些副作用包括对数据库表中数据的修改或对软件包中公共变量（在软件包说明中声明的变量）的修改。控制副作用是非常重要的，因为这些副作用可能阻止正确的并行查询，产生顺序依赖并因此而产生不确定的结果，或需要一些越轨的操作（如在不同的用户会话中维护一个软件包的状态)。当在一个SQL查询语句或DML语句中调用一个函数时，一些限制是不允许出现在这个SQL语句中的。
- 一个函数所具有的副作用越少，则这个函数在一个查询语句中越容易优化，特别是当使用启示(Hint)PARALLEL_ENABLE或DETERMINISTIC时。如果要在SQL语句中调用一个存储函数,那么这个存储函数（以及任何它所调用的子程序）就必须遵守如下的纯净级别的规定：
1. 当在一个SELECT语句或一个并行的DML语句中调用一个存储函数时，该函数不能更改数据库中任何表中的数据。
2. 当在一个DML语句中调用一个存储函数时，该函数不能查询也不能更改这个语句所更改的任何表
3. 当在-个SELECT语句或一个DML语句中调用一个存储函数时，该函数不能执行SQL事务控制语句、会话控制语句和系统控制语句。
- 以上这些规则的目的就是控制函数使用的副作用。如果任何SQL语句中使用的函数体（程序代码）违反了以上的规则，该语句在执行时（在对这个语句进行词法分析时）将产生运行错误

### 软件包中变量的持续状态

公有和私有软件包变量的集合代表一个用户会话的软件包的状态，即在某一个指定的时间内存储在所有软件包变量中的值。通常，一个软件包的状态存在于用户会话的整个生命周期。

对于一个用户会话来说，当一个软件包被第一次装入内存时该软件包的变量被初始化。对于每一个会话，默认软件包变量都是唯一的并且这些变量的值一直保持到这个用户会话终止。换句话说，变量是存储在为每一个用户由数据库所分配的用户全局区(User Global Area,UGA)的内存中，当一个软件包中的子程序被调用，该子程序的逻辑变更了变量的状态时，软件包的状态就发生了变化。公共软件包的状态也可能直接由对其类型的适当操作所改变。

- PRAGMA代表这个语句是一个编译指令，由PRAGMA说明的语句是在编译期间处理的，而不是在运行时处理的。

- PRAGMA SERIALLY_RESUABLE指令
   - 这些指令并不影响一个程序的含义，它们仅仅将信息传输给编译器。如果在软件包说明中添加了PRAGMA SERIALLY_RESUABLE指令，那么Oracle数据库将软件包的变量存储在由多个用户会话共享的系统全局区(System GlobalArea,SGA)内存中。在这种情况下，软件包的状态被维护在一个子程序调用的生命周期中或对一个软件包结果的一个单一的引用生命周期中。如果想要节省内存并且如果不需要为每一个用户会话保持软件包的状态，那么SERIALLY_RESUABLE指令就非常有用。

当一个软件包需要声明大量的临时工作区，但是这些临时工作区只使用一次而且在同一个会话中后续数据库调用也不再需要它们时，PRAGMA指令非常适合。可以将一个无体软件包标记为串行可重用。但是如果一个软件包具有说明和体两部分，就必须标记说明和体两部分，不能只标记体。

串行可重用软件包的全局内存是统一存储在系统全局区(SGA)中的，而不是在每个用户所具有的用户全局区(UGA)中分配的，这样软件包的工作区就可以重用了。当对服务器的调用结束时，这些内存返回给SGA。每次软件包被重用时，它的公共变量都被初始化为它们的默认值或空值。

- 数据库触发器或在SQL语句中调用的PL/SQL子程序时不能访问串行可重用软件包的。否则产生错误

#### 例

### 软件包中游标的持续状态

- 与软件包中变量的持续状态相同，软件包中游标的持续状态时在整个会话期间跨越事物的。然而对于相同用户的不同会话（同一个用户的不同连接），它们的状态并不保持。

#### 例

```
CREATE OR REPLACE PACKAGE emp_pkg IS
 PROCEDURE open_emp;
 FUNCTION next_employee(p_n NUMBER := 1) RETURN BOOLEAN;
 PROCEDURE close_emp;
END emp_pkg;
```

```
CREATE OR REPLACE PACKAGE BODY emp_pkg IS
 CURSOR emp_cursor IS
  SELECT employee_id
  FROM employees;
 
 PROCEDURE open_emp
   IS
   BEGIN
     IF NOT emp_cursor%ISOPEN THEN
       OPEN emp_cursor;
     END IF;
   END open_emp;
   
 FUNCTION next_employee
   (
    p_n NUMBER := 1
   )
   RETURN BOOLEAN 
   IS
    v_emp_id employees.employee_id%TYPE;
   BEGIN
     FOR count IN emp_cursor LOOP
       FETCH emp_cursor 
       INTO v_emp_id;
       
       DBMS_OUTPUT.PUT_LINE('employee_id:'||v_emp_id);
     END LOOP;
     
     RETURN emp_cursor%FOUND;
   END next_employee;
   
 PROCEDURE close_emp 
   IS
   BEGIN
     IF emp_cursor%ISOPEN THEN
       CLOSE emp_cursor;
     END IF;
   END close_emp;
END emp_pkg;
```

### 在软件包中使用PL/SQL记录表（记录数组）

- 可以在PL/SQL软件包中声明自定义的数据类型，在利用自定义的数据类型声明变量

#### 例

```
CREATE OR REPLACE PACKAGE emp_dog IS
 TYPE emp_table_type IS TABLE OF employees%ROWTYPE
      INDEX BY PLS_INTEGER;
 
 PROCEDURE get_emp(p_emp OUT emp_table_type);
END;
```
```
CREATE OR REPLACE PACKAGE BODY emp_dog IS
 PROCEDURE get_emp
   (
   p_emp OUT emp_table_type
   )
   IS
    v_count BINARY_INTEGER := 1;
   BEGIN
     FOR emp_record IN (SELECT * FROM employees) LOOP
       p_emp(v_count) := emp_record;
       v_count := v_count + 1;
     END LOOP;
   END get_emp;
END;
```

### 标准化

#### 标准化异常和异常处理

- 创建一个标准的错误处理软件包，在该软件包中包括所有在应用程序中用户命名的和用户定义的异常
- 标准化异常处理既可以通过使用独立的存储子程序实现，也可以通过将一个子程序添加到定义标准异常的软件包中实现

**考虑：**

1. 在该程序中使用的每个命名的异常
2. 在这个程序中使用的无名或用户定义的异常。
3. 调用RAISE_APPLICATION_ERROR的过程
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
3. 引用集合并使用RETURNING INTO子句的游标FOR循环。

### FORALL语句
- FORALL关键字指示PL/SQL引擎在将集合发送给SQL语句之前批量绑定输入的集合。
  - FORALL语句不是一个FOR循环（也不需要循环），而是一次性绑定所有的数据。 

```
FORALL index IN 下限..上限
[SAVE EXCEPTIONS]
  SQL语句;
```

- SAVE EXCEPTIONS关键字是可选的。如果某些DML操作成功了而另外的一些失败了，那么可以利用它追踪或报告那些失败的操作。
    - 使用SAVE EXCEPTIONS关键字后造成失败的操作将被存储在一个名为%BULK_EXCEPTIONS的游标属性中，该属性是一个记录集合（数组)，而它会标出批量DML操作重复的次数和对应的错误号码。
   - 为了管理异常和让批量绑定即使出现错误也能够完成，最好在FORALL语句上下限之后、DML语句之前添加上SAVE EXCEPTIONS关键字。

- 在执行期间所有抛出的异常都存储在游标属性%BULK_EXCEPTIONS中，该属性是一个记录集合。每一个记录有如下两个字段：
   1. %BULK_EXCEPTIONS(i).ERROR_INDEX：存储异常抛出期间FORALL语句“重复的次数”。
   2. %BULK_EXCEPTIONS(i).ERROR_CODE：存储对应的Oracle错误代码。
- 存储在%BULK_EXCEPTIONS中的值引用的是最近执行的FORALL语句，其下标是从1到%BULK EXCEPTIONS.COUNT。

```
CREATE OR REPLACE PROCEDURE my_raise_sal
(
 p_percent NUMBER
) 
IS
  TYPE idlist_type IS TABLE OF NUMBER
    INDEX BY PLS_INTEGER;
    
  v_emp_id idlist_type;
BEGIN
  v_emp_id(1) := 117;
  v_emp_id(2) := 177;
  v_emp_id(3) := 122;
  
--完成批量绑定索引表v_emp_id
  FORALL i IN v_emp_id.FIRST..v_emp_id.LAST
    UPDATE copy_emp
    SET salary = (1 + p_percent / 100) * salary
    WHERE employee_id = v_emp_id(i);
END;
```

#### 游标属性%BULK_ROWCOUNT

为了方便DML操作，除了游标属性%BULK_EXCEPTIONS之外，PL/SQL还提供了另一个属性以支持批量操作(%BULK_ROWCOUNT)

- %BULK_ROWCOUNT属性是一个复合结构，它是专门为FORALL语句的使用而设计的。
 - 其第n个元素存储的就是一个INSERT、UPDATE或DELETE语句的第n次执行时所处理的数据行数。
 - 如果第n次执行没有影响任何数据行，那么%BULK_ROWCOUNTO(i)就返回零。

```
DECLARE 
  TYPE name_list_type IS TABLE OF VARCHAR2(20)
    INDEX BY BINARY_INTEGER;
  
  v_name_list name_list_type;
BEGIN
  v_name_list(1) := '一号';
  v_name_list(2) := '二号';
  v_name_list(3) := '三号';
  
  FORALL i IN v_name_list.FIRST..v_name_list.LAST
    INSERT INTO my_test(contain)
    VALUES(v_name_list(i));
  FOR i IN v_name_list.FIRST..v_name_list.LAST LOOP
    DBMS_OUTPUT.PUT_LINE('第' || i || '次操作的行数:'||SQL%BULK_ROWCOUNT(i));
  END LOOP;
END; 

***************
第1次操作的行数:1
第2次操作的行数:1
第3次操作的行数:1
PL/SQL procedure successfully completed
```

#### 使用RETURNING子句将DML语句的结果直接装入变量

- 在INSERT/UPDATE/DELETE语句中可以包括一个RETURNING子句，将受影响的数据行中指定列的值返回给PL/SQL变量或宿主变量。
   - 减少了SELECT INTO语句的使用
   - 只需要较少的网络流量，服务器时间和服务器内存

```
CREATE OR REPLACE PROCEDURE update_sal
(
 p_emp_id NUMBER
)
IS
 v_last_name copy_emp.last_name%TYPE;
 v_dept_id copy_emp.department_id%TYPE;
 v_job_id copy_emp.job_id%TYPE;
 v_sal copy_emp.salary%TYPE;
BEGIN
  UPDATE copy_emp
  SET salary = salary * 1.15
  WHERE employee_id = p_emp_id
  RETURNING last_name,department_id,job_id,salary
  INTO v_last_name,v_dept_id,v_job_id,v_sal;
  
  DBMS_OUTPUT.PUT_LINE('name:'||CHR(9)||v_last_name||CHR(10)||
                       'id:  '||CHR(9)||p_emp_id||CHR(10)||
                       'dept:'||CHR(9)||v_dept_id||CHR(10)||
                       'job: '||CHR(9)||v_job_id||CHR(10)||
                       'sal: '||CHR(9)||V_sal||CHR(10)
                       );
END;

********
CREATE OR REPLACE TRIGGER update_sal_tri
BEFORE UPDATE OF salary ON copy_emp
FOR EACH ROW
BEGIN
  DBMS_OUTPUT.PUT_LINE('old_sal:'||:OLD.salary||CHR(10)||
                       'new_sal:'||:NEW.salary||CHR(10));
END;

********
SQL> EXECUTE update_sal(177);
old_sal:304.18
new_sal:349.81

name:	Livingston
id:  	177
dept:	80
job: 	IT
sal: 	349.81

PL/SQL procedure successfully completed
``` 

#### 带有RETURNING和BULK COLLECT INTO 关键字的FORALL语句

```
CREATE OR REPLACE PROCEDURE bulk_raise_sal
(
 p_percent NUMBER
)
IS
 TYPE emp_id_list_type IS TABLE OF NUMBER
  INDEX BY PLS_INTEGER;
 TYPE sal_list_type IS TABLE OF copy_emp.salary%TYPE
  INDEX BY BINARY_INTEGER;
 
 v_emp_id_list emp_id_list_type;
 v_sal_list sal_list_type;
BEGIN
  v_emp_id_list(1) := 177;
  v_emp_id_list(2) := 122;
  
  FORALL i IN v_emp_id_list.FIRST..v_emp_id_list.LAST
    UPDATE copy_emp
    SET salary = (1 + p_percent / 100) * salary
    WHERE employeE_id = v_emp_id_list(i)
    RETURNING salary 
    BULK COLLECT INTO v_sal_list;
    
    FOR i IN 1..v_sal_list.COUNT LOOP
      DBMS_OUTPUT.PUT_LINE(v_emp_id_list(i)||': '||v_sal_list(i));
    END LOOP;
END;
```

#### 利用INDEX数组进行批量绑定

- 在使用FORALL语句进行批量绑定的DML操作时，可以使用PLS_INTEGER或BINARY_INTEGER的下标集合，该集合的值（数组元素）是这个集合的下标。
- 可以在一个FORALL语句中使用VALUES OF子句来处理批量的DML操作

```
CREATE TABLE ins_emp
AS 
SELECT *
FROM emp
WHERE 1=2;
```
```
CREATE OR REPLACE PROCEDURE insert_ins_emp
AS
 TYPE emp_tab_type IS TABLE OF emp%ROWTYPE;
 v_emp emp_tab_type;
 
 TYPE values_of_tab_type IS TABLE OF PLS_INTEGER
   INDEX BY PLS_INTEGER;
 v_number values_of_tab_type;
 
BEGIN
  SELECT *
  BULK COLLECT INTO v_emp
  FROM emp;
  
  FOR i IN 1..v_emp.COUNT LOOP
    v_number(i) := i;
  END LOOP;
  
  FORALL i IN VALUES OF v_number
   INSERT INTO ins_emp
   VALUES v_emp(i);
   --VALUES(v_emp(i));
   --21/4     PL/SQL: 
   --20/4     PL/SQL: SQL Statement ignored
END;
```

##### 为什么VALUES子句带括号和不带括号: ORA-00947: 没有足够的值

```
CREATE TABLE test_table1
(
 id NUMBER
,name VARCHAR2(20)
);

INSERT INTO test_table1
VALUES(101,'一号');
INSERT INTO test_table1
VALUES(102,'二号');
INSERT INTO test_table1
VALUES(103,'三号');

CREATE TABLE copy_test_table1
AS
SELECT *
FROM test_table1
WHERE 1=2;
```
 
- 在`VALUES v_emp(i)`中的是一整行数据，
- 而在`VALUES(v_emp(i))`中的只是作为一列数据，即常规的VALUES子句，
   - 应该要改为像`VALUES (v_table(i).id,v_table(i).name)`的形式

### BULK COLLECT INTO子句

- BULK COLLECT INTO关键字指示SQL引擎在将集合返回给PL/SQL引擎之前批量绑定输出的集合。
    - BULK COLLECT （即将数据绑定为一个集合），INTO子句（将绑定的数据放入一个集合）  
- 可以在SELECT INTO、FETCH INTO和RETURNING INTO子句中使用该语法。（这里没有INSERT INTO，功能类似)

```
...
BULK COLLECT INTO 集合名1[,集合名2]
...
```

#### SELECT语句中使用BULK COLLECT INTO子句

- 当在PL/SQL语句中使用一个SELECT语句时，可以加上BULK COLLECT INTO子句。可以快速地获取一个数据行地集合而不再需要使用游标机制。

```
SELECT 列1,列2...
BULK COLLEC INTO 集合名（索引表等）
FROM 表
```

##### 例

```
CREATE OR REPLACE PROCEDURE bulk_get_emps
(
p_dept_id NUMBER
)
IS
 TYPE emp_tab_type IS TABLE OF copy_emp%ROWTYPE;
 v_emp_list emp_tab_type;

BEGIN
  SELECT *
  BULK COLLECT INTO v_emp_list
  --将查询的结果放入集合中
  FROM copy_emp
  WHERE department_id = p_dept_id;
  
  FOR i IN v_emp_list.FIRST..v_emp_list.LAST LOOP
    DBMS_OUTPUT.PUT_LINE('id:' ||CHR(9)||v_emp_list(i).employee_id ||CHR(10)||
                         'last:'||CHR(9)||v_emp_list(i).last_name||CHR(10)||
                         'dept:'||CHR(9)||v_emp_list(i).department_id||CHR(10)||
                         '******************************************');
  END LOOP;
END; 

******************
SQL> EXECUTE bulk_get_emps(20);
id:	201
last:	Hartstein
dept:	20
******************************************
id:	202
last:	Fay
dept:	20
******************************************
PL/SQL procedure successfully completed
```

#### 在游标的FETCH语句中使用BULK COLLECT INTO子句

- 当在PL/SQL语句中使用游标时，可以在FETCH语句中加入BULK COLLECT INTO子句进行批量绑定

```
CREATE OR REPLACE PROCEDURE bulk_get_emps2
(
 p_dept_id NUMBER
)
IS
 TYPE emp_tab_list_type IS TABLE OF copy_emp%ROWTYPE;
 
 v_emp_list emp_tab_list_type;
 
 CURSOR emp_cursor IS
   SELECT *
   FROM copy_emp
   WHERE department_id = p_dept_id;
BEGIN
  OPEN emp_cursor;
  FETCH emp_cursor BULK COLLECT INTO v_emp_list;
  FOR i IN v_emp_list.FIRST..v_emp_list.LAST LOOP
    DBMS_OUTPUT.PUT_LINE('id:  ' || v_emp_list(i).employee_id);
  END LOOP;
  CLOSE emp_cursor;
END;


************

1.
CREATE OR REPLACE PROCEDURE bulk_get_emps
(
 p_dept_id NUMBER
)
IS
 CURSOR emp_cursor IS
   SELECT *
   FROM copy_emp
   WHERE department_id = p_dept_id;
 
 v_emp_list copy_emp%ROWTYPE;
BEGIN
  OPEN emp_cursor;
  FETCH emp_cursor INTO v_emp_list;
  WHILE emp_cursor%FOUND LOOP
   DBMS_OUTPUT.PUT_LINE(v_emp_list.employee_id);
   FETCH emp_cursor INTO v_emp_list;
  END LOOP;
  CLOSE emp_cursor;
END;

2.
CREATE OR REPLACE PROCEDURE bulk_get_emps2
(
 p_dept_id NUMBER
)
IS
 CURSOR emp_cursor IS
   SELECT *
   FROM copy_emp
   WHERE department_id = p_dept_id;
BEGIN
  FOR c IN emp_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('id:  ' || c.employee_id);
  END LOOP;
END;
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
- 要将一个子程序定义成使用调用者权来执行的子程序，只需要在参数表和IS(或AS)关键字之间加上`AUTHID CURRENT_USER`。可以使用如下语句将不同的PL/SQL子程序结构设置成以调用者权限执行：

```
CREATE FUNCTION 函数名 
  RETURN 数据类型 
  AUTHID CURRENT_USER 
  IS
    声明，函数体;
***********************
CREATE PROCEDURE 过程名 
  AUTHID CURRENT_USER 
  IS
   声明，过程体;
***********************
CREATE PACKAGE 软件包名
  AUTHID CURRENT_USER 
  IS
   声明；
***********************
CREATE TYPE 类型名 AUTHID CURRENT_USER IS 对象；
```

#### 例

```
CREATE OR REPLACE PROCEDURE get_salary
 (
  v_emp_id copy_emp.employee_id%TYPE
 )
 AUTHID CURRENT_USER
 IS
  v_sal NUMBER;
  BEGIN
    SELECT salary
    INTO v_sal
    FROM copy_emp
    WHERE employee_id = v_emp_id;
    
    DBMS_OUTPUT.PUT_LINE('id:'||v_emp_id||' , sal:'||v_sal);
  END;
```

## 自治事务

在日志表中，从Oracle8i开始，Oracle引入了自治事务(Autonomous Transactions.)，自治事务使得创建独立的事务成为可能。一个自治事务(AT)是一个由另外一个主事务（MT:Main Transaction）开启的独立事务，自治事务是使用`PRAGMA AUTONOMOUS_TRANSACTION`关键字定义的

**语法**

- 关键字`PRAGMA AUTONOMOUS_TRANSACTION`指示PL/SQL编译器将一个例行程序标记为自治的（独立的）。例行程序包括（最外层的匿名PL/SQL程序块，本地的/独立的/软件包的函数和过程），一个SQL对象类型的方法，数据库触发器。
- 关键字`PRAGMA AUTONOMOUS_TRANSACTION`可以在一个例行程序的声明段中的任意位置。一般放在声明段的最上方。

**例**

```
CREATE OR REPLACE PROCEDURE sal_between
 (
  v_min_sal NUMBER
 ,v_max_sal NUMBER
 )
 IS
  PRAGMA AUTONOMOUS_TRANSACTION;
--自治事物
  CURSOR emp_id_cursor IS
   SELECT employee_id
   FROM copy_emp
   WHERE salary BETWEEN v_min_sal AND v_max_sal;

  BEGIN
    FOR c IN emp_id_cursor LOOP
      DBMS_OUTPUT.PUT_LINE(c.employee_id);
    END LOOP;
    COMMIT;
  END;

***********
CREATE OR REPLACE PROCEDURE test_pro
IS
 BEGIN
  sal_between(1000,2000);
  ROLLBACK
 END;
```

**具体操作顺序**

- 主事务与自治事务之间的关系，具体操作顺序如下：
   - 如：在过程(MT)中调用（只是调用，而不是声明定义）另一个过程(AT)
1. 主事务开始。
2. 主事务调用存储过程以启动自治事务。
3. 主事务处于挂起状态。
4. 自治事务操作开始。
5. 自治事务以一个COMMIT或ROLLBACK语句结束。
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

## 导出程序的源代码以及源代码加密

- `set long 长度` 设置输出显示的行数

### 导出
#### 使用USER_SOURCE数据字典导出

- ALL_SOURCE 数据字典
- DBA_SOURCE 数据字典

**XXX_SOURCE数据字典中没有存放触发器 的源代码**

``` 
SELECT line
      ,text
FROM USER_SOURCE
WHERE LOWER(name) = '存储过程名|存储函数名|软件包名|
```

#### 导出触发器的类型/触发事件/描述/代码

**USER_OBJECTS数据字典**

- 对象数据字典

```
SELECT object_name
      ,object_type
      ,created
      ,status
      ,last_ddl_time  --最后使用时间    
FROM USER_OBJECTS
WHERE object_type = 'TRIGGER';
```

**USER_TRIGGERS数据字典**

```
SELECT trigger_name
      ,table_name
      ,triggering_event
      ,status
FROM USER_TRIGGERS;
```

**触发器的描述**

```
SELECT description
FROM USER_TRIGGERS
WHERE LOWER(trigger_name) = '触发器名';
```

**获取触发器的源代码**

```
SELECT trigger_body
FROM USER_TRIGGERS
WHERE LOWER(trigger_name) = '触发器名';
```

### PL/SQL源代码加密及动态加密

Oracle使用了一种叫做模糊(Obfuscation)或封装(wrapping)的技术来加密PL/SQL程序代码。所谓一个PL/SQL程序单元的模糊处理就是隐藏PL/SQL源代码的处理（即将PL/SQL源代码转换成人们无法阅读的“乱码”)。
- 在Oracle中既可以使用软件包DBMS_DDL的子程序加密（封装)PL/SQL源代码，
    - 通常使用 **软件包DBMS_DDL的子程序加密（封装）** 一个单独的PL/SQL程序单元，
       - 如一个单一的动态产生的CREATE PROCEDURE命令， 
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
   - 因为DBMS_DDL软件包中的子程序每次执行时只接受一个CREATE OR REPLACE语句。

|          功能           | DBMS_DDL | WARP |
| :--------------------: | :------: | :--: |
|     代码加密（模糊）    |    T     |   T  |
|     动态加密（模糊）     |    T     |  F   |
| 一次加密（模糊）多个程序 |    F     |  T   |

#### DBMS_DDL软件包

**动态加密**

动态加密就是在创建一个PL/SQL程序单元（如过程、函数和软件包等)的同时对这个程序单元的源代码进行加密。Oracle的动态加密方法是从Oracle10g开始引入的，它是通过调用软件包DBMS_DDL中的两个子程序实现的，这两个子程序分别是CREATE_WRAPPED过程和WRAP函数。

- **CREATE_WRAPPED过程**的功能为：将一个单独的CREATE OR REPLACE语句作为输入（这个语句可以是如下的创建语句之一：创建一个PL/SQL软件包说明、一个软件包体、函数、过程、类型说明或类型体)，随后产生一个新的CREATE OR REPLACE语句，但是PL/SQL源代码正文已经被加密（模糊）并执行这个新产生的语句。
- **WRAP函数**的功能为：将一个单独的CREATE OR REPLACE语句作为输入（这个语句可以是如下的创建语句之一：创建一个PL/SQL软件包说明、一个软件包体、函数、过程、类型说明或类型体)并返回一个新的CREATE OR REPLACE语句，在这个语句中PL/SQL程序单元的正文已经被加密（模糊）。

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
BEGIN 
  DBMS_DDL.CREATE_WRAPPED('加密的代码');
  --加密的代码可以先放在一个字符串常量里面；
END;
```

##### 例1 CREATE_WRAPPED过程加密PL/SQL源代码

```
BEGIN
 DBMS_DDL.CREATE_WRAPPED ('
   CREATE OR REPLACE PROCEDURE show_time IS
   BEGIN
     DBMS_OUTPUT.PUT_LINE(''当前时间：''||SYSDATE);
   END;
 ');
END;
```

```
DECLARE
  c_code CONSTANT VARCHAR2(10000) := '
   CREATE OR REPLACE PROCEDURE show_time IS
    BEGIN
      DBMS_OUTPUT.PUT_LINE(''当前时间：''||SYSDATE);
    END;
  ';
BEGIN
  DBMS_DDL.CREATE_WRAPPED(c_code);
END;
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
WRAP INAME=输入文件名 [ONAME=输出文件名]
--不能有空格
--别加分号；不然会认为文件名里面也有分号
```

- 输入文件可以包括任何SQL语句的组合，然而PL/SQL封装程序只封装（加密）如下的CREATE语句：
1. CREATE [OR REPLACE] TYPE
2. CREATE [OR REPLACE] TYPE BODY
3. CREATE [OR REPLACE] PACKAGE
4. CREATE [OR REPLACE] PACKAGE BODY
5. CREATE [OR REPLACE] FUNCTION
6. CREATE [OR REPLACE] PROCEDURE
- 除了以上列出的CREATE语句之外，所有其他的SQL CREATE语句都被原样存入输出文件（即没有加密)。

**在使用以上命令加密一个操作系统文件时，Oracle系统有如下约定：**

1. 只有INAME参数是必需的。如果没有说明ONAME参数，那么输出文件的名字与输入文件相同，但是其文件的扩展名为plb。
2. 输入文件的扩展名可以是任意的扩展名，但是默认扩展名为.$sql。
3. NAME和ONAME参数的值（即输入文件名和输出文件名）是否区分大小写取决于使用的操作系统。
4. 通常输出文件要比输入文件大许多。
5. 在NAME和ONAME之间的等号两边不能有任何空格。

- 当封装（加密）的文件创建成功之后，要在`SQL*Pus(或iSQL*PIus)`中执行这个加密后的.plb文件以编译加密后的源代码并将其存储在数据库中，其执行方法与执行SQL脚本文件一模一样。

- 当一个文件被封装（加密）之后，其中的对象类型、子程序具有如下形式：头，紧跟一个单词，wrapped,随后是加密的程序体。

##### 例

```
G:\sqlTest>WRAP INAME=wraptest.sql ONAME=testout.pld;

PL/SQL Wrapper: Release 11.2.0.1.0- 64bit Production on 星期六 10月 15 19:04:52 2022
Copyright (c) 1993, 2009, Oracle.  All rights reserved.
Processing wraptest.sql to testout.pld;

G:\sqlTest>type testout.pld
CREATE OR REPLACE PROCEDURE show_name wrapped
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
dMAzuHRlJXxlUF0lXZt0iwm4wDL+0oYJpuEfSZqPMLVQyKlQLwDKSv4I0sc9qRF3oIsJuIHH
LcmmpvpLBBA=

G:\sqlTest>notepad testout.pld
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

## PL/SQL Developer

### 将查询导出为XML

1. PL/SQL Developer
2. File - New - SQL Windows
3. 执行SQL语句 Execute
4. 选中所有数据，右键单击，Export Resuts, XML File
