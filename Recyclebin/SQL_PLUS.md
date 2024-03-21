# 报表格式设置

## sqlplus的报表功能

```sql
ttitle col 15 '我的报表' col 35 sql.pno    --15表示空15列，sql.pno表示报表页码
col deptno heading 部门号   --设置别名
col job heading 职位
col sum(sal) heading 工资总额
break on deptno skip 1  --deptno只显示一次，部门间间隔一行
```



```sql
COL 字段名 HEADING 显示的字段名;   --执行成功的话不会有回显
```

## 设置结果显示的格式

```sql
COL column_name FORMAT dataformat;
```

  字符类型只能设置它的长度。 --字符格式用a开头，后面跟它要的长度

  如果是数值类型用，9表示一位数字，可以保留4位数和一位小数

  但如果数据中有四位的数，超过这个长度的就用#####表示了，与excel一致。

  如果使用col salary format $9999.9;则数字前面加了美元符号。

## CLEAR 清除之前设置过的格式

```sql
COLUMN column_name CLEAR;
```

```sql
CLEAR columns

CLEAR breaks

CLEAR computes
```

- @activity.sql

```sql
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

## REM 说明文字 注释


## ttitle btitle 标题

```sql
ttitle '开头'
btitle '结尾' 
```

每页标题 ttitle置顶 btitle置底

## COLUMN column_name FORMAT 宽度

```sql
COLUMN column_name FORMAT 宽度
```

- 设置该列的显示宽度

```sql
column Name format a20
```

- a表示字母列 
- 20表示宽度

```sql
column Title format a20 word_wrapped
```

- word_wrappeed 任何超过指定格式长度(`a20`个字符)的都将换到下一行
- truncated 将不显示超过该列指定格式长度的任意字符

```sql
column DaysOut heading 'Days!Out'
```

- 999.99 指定数字列长度 
  - 字符长度加上负号占7位 ，小数点也占一位

## COLUMN column_name HEADING '  '

```

```

## BREAK ON

```
BREAK ON column_name SKIP 1 ON REPORT
```

设置列中的名重复

### COMPUTE AVG

```
break on Name skip 1 on report
compute avg of DaysOut on Name
compute avg of DaysOut on report
```

- 总是与BREAK ON语句一起使用
- 根据BREAK ON部分计算平均值

# 基础命令工具

| 命令                                               | 说明                                                         |
| -------------------------------------------------- | ------------------------------------------------------------ |
| list<br />l                                        | 列出缓冲区                                                   |
| n（n：行号）                                       | 设定当前行                                                   |
| n 文本                                             | 替换第n行的语句                                              |
| change /old/new<br />c /old/new                    | 修改/替换                                                    |
| del n                                              | 删除第n行语句                                                |
| del m n                                            | 删除第m至n行语句                                             |
| append<br />a                                      | 追加                                                         |
| `save 文件名.sql [app|rep]`                        | 保存,，将缓冲区的语句存入脚本文件中，缓冲区中只能存储一个SQL语句。<br />SQL*PLUS的命令不会被放在缓冲区。<br />app：追加在已有文件<br />rep：替换已有文件 |
| `store set 文件名.sql {create|replace}`            | 将当前的`SQL*PLUS`环境设置保存到文件中。<br />create：创建<br />replace：替代 |
| edit                                               | 编辑                                                         |
| host                                               |                                                              |
| host cls                                           | 清屏                                                         |
| start 脚本文件.sql<br />@ 脚本文件.sql             | 启动脚本文件                                                 |
| `show {user|headsep|linesize|pagesize|newpage|..}` | 查看                                                         |
| print                                              | 输出所有绑定变量                                             |
| print 绑定变量名                                   | 输出指定的绑定变量                                           |
| help                                               | 帮助                                                         |

- 自动打印

```
SET AUTOPRINT ON
```

# set命令

|命令|说明|
|:--|:--|
|set timing on; |显示“已用时间：XXXX”。统计每条语句执行所花费时间|
|set time on; |设置显示当前时间（系统时间）|
|set pagesize 10; |设置每一页的行数|
|set heading on; |设置显示列名|
|set autocommit `{on|off}`; |设置当前session是否对修改的数据进行自动提交|
|set autotrace on; |设置允许对执行的sql进行分析|
|set trimout on; |去除标准输出每行的拖尾空格，缺省为off|
|set trimspool on; |去除重定向（spool）输出每行的拖尾空格<br />默认off|
|set echo `{on|off}`; |执行sql脚本时，是否显示脚本中正在执行的SQL语句|
|set feedback `{on|off}`; |设置显示“已选择XX行”，回显本次sql命令处理的记录条数。<br />默认on|
|set colsep ’ ’; |输出分隔符|
|set heading `{on|off}`; |输出域标题，默认on|
|set pagesize 0; |输出每页行数，默认24。<br />为避免分页，可设定为0。|
|set linesize 80; |输出一行字符个数，默认80|
|set numwidth 12; |输出number类型域长度，默认10|
|set termout `{on|off}`; |显示脚本中的命令的执行结果，默认on|
|set serveroutput on; |设置允许显示输出类似dbms_output|
|set verify `{on|off}`; |打开/关闭提示确认信息old 1、new 1的显示|
|set NULL text; | 显示时，用text代替null值|
|set headshep 符号; |设置分隔符(分行) 默认|
|set PAUSE ' ';  |设置停顿显示的语句|
|set PAUSE `{on|off}`; |设置停顿，回车继续|

## SET AUTOTRACE ON 追踪sql语句 优化

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

```
SET AUTOTRACE ON EXPLAIN ----- AUTOTRACE只显示优化器执行路径报告 
```

```
SET AUTOTRACE ON STATISTICS -- 只显示执行统计信息
```

```
SET AUTOTRACE TRACEONLY ------ 同set autotrace on，但是不显示查询输出
```

```
SET AUTOTRACE TRACEONLY EXPLAIN
```

```
SET AUTOTRACE TRACEONLY STATISTICS
```

2. 只能在sql plus下执行

# SPOOL

- SPOOL缓冲池技术：实现Oracle数据导出到文本文件。

```
SELECT taskindex||'|'||commONindex||'|'||tasktype||'|'||to_number(to_char(sysdate,'YYYYMMDD')) 
FROM ssrv_sendsms_task; SPOOL常用的设置
```

```sql
SET colsep' ';　　　 --域输出分隔符
SET echo OFF;　　　　--显示start启动的脚本中的每个sql命令，缺省为ON
SET feedback OFF;　　--回显本次sql命令处理的记录条数，缺省为ON
SET heading OFF;　　 --输出域标题，缺省为ON
SET pagesize 0;　　--输出每页行数，缺省为24,为了避免分页，可设定为0。
SET termout OFF;　　 --显示脚本中的命令的执行结果，缺省为ON
SET trimout ON;　　　--去除标准输出每行的拖尾空格，缺省为OFF
SET trimSPOOL ON;　　--去除重定向（SPOOL）输出每行的拖尾空格，缺省为OFF

如果trimSPOOL设置为ON，将移除SPOOL文件中的尾部空 ，trimout同trimSPOOL功能相似，
只不过对象是控制台。使用glogin.sql或login.sql使得设置跨sessiON生效。
```

## 导出文本数据的建议格式

SQL*PLUS环境设置

```sql
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

### 方法一：采用以下格式脚本

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

### 方法二：采用以下脚本

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
