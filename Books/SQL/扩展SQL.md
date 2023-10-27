# PL/SQL语句块

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

## 字符分隔符 q\'

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

# 变量

## SQL变量

## PL/SQL变量

- 命名规范：必须以英文字母开始、可以包含一个或多个英文字母或数字、可以包含最多30个字符、只能包含特殊字符`$, _, #`、不能与数据库的表或者列同名、不能是关键字。

| 命名惯例           | 标识符                              |
| :----------------- | :---------------------------------- |
| v\_name            | 变量                                |
| c\_name            | 常量                                |
| p\_name            | SQL\*PLUS替代变量（替代参数）       |
| g\_name            | SQL\*PLUS全局变量（宿主或绑定变量） |
| name\_cursor       | 游标                                |
| e\_name            | 异常                                |
| name\_table\_type  | PL/SQL表类型                        |
| name\_table        | PL/SQL表类型的变量                  |
| name\_record\_type | 记录类型                            |
| name\_record       | 记录类型的变量                      |

- oracle处理变量、参数、表、列的优先级：

1. PL/SQL首先检查数据库表中列的名字。
2. 数据库表中的列名优先于本地变量名。
3. 本地变量名和形参名优先于数据库的表名。

### 声明、初始化

- 变量的声明和初始化：在引用PL/SQL程序块中的变量之前，必须在声明段声明所有的变量。在声明变量的同时，可以为变量赋初值，但并不是必须的，但是，如果在一个变量声明中引用了其他变量，一定要确保在之前的语句中已经声明了所引用的变量。

```plsql
变量名 [constant] 数据类型 [not null]
    [:= | default 表达式];   --初始化赋值
```

| 选项            | 说明                       |
| --------------- | -------------------------- |
| constant        | 常量，声明时必须初始化     |
| not null        | 非空变量，声明时必须初始化 |
| :=<br />default | 初始化赋值                 |

```plsql
declare 
 v_desc varchar2(100)
   := '中国妇女革命先锋: ';
 v_pioneer varchar2(25);  --默认空值 null
begin
  dbms_output.put_line(v_desc || v_pioneer);
  v_pioneer := '苏妲己';
  dbms_output.put_line(v_desc || v_pioneer);
end;
```

#### variable

- variable直接在SQL环境中定义。只能是SQL允许的数据类型、不能进行初始化。

```plsql
variable 变量名 数据类型;
```

#### 绑定变量（bind variable）

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
> begin
> update emp
> set sal=sal*2
> where empno = p_empno;
> commit;
> end;
> ```

#### select .. into .. 变量赋值

- select子句提取（查找到）数据（表中列的值），通过into子句将提取的数据存放在PL/SQL的变量中。在PL/SQL的select语句中，into子句是强制性的（必须存在），并且into子句只能放在select子句和from子句之间，被用来指定存放从select子句中返回值的变量，并且变量的顺序必须与所选择的项相对应，且select语句必须返回且只能返回一行数据。

```
select 查询列表
into [变量名[,变量名]...]
     [记录名]
from 表
[where 条件表达式];
```

```plsql
declare
  -- 声明变量
  v_name varchar2(25);
  v_email varchar2(25);
  v_salary number(8, 2);
  v_job_id varchar2(10);
begin
  -- 通过 select ... into ... 语句为变量赋值
  -- 被赋值的变量与select中的列名要一一对应
 select last_name, email, salary, job_id 
 into v_name, v_email, v_salary, v_job_id
 from employees
 where employee_id = 186;
 -- 打印变量的值
 dbms_output.put_line(v_name || ', ' || v_email || ', ' ||  v_salary || ', ' ||  v_job_id);
end;
```

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
| DBMS_LOB | 对于CLOB和BLOB数据类型的列，许多操作是不能直接使用oracle的数据库命令来完成的。<br />维护LOB数据类型的列。 |
| BFILE    | 在数据库外的操作系统文件中存储大的二级制对象，如电影胶片等。<br />与其他LOB数据类型不同，BFILE数据类型是外部数据类型。<br />BFILE类型的数据是存储在数据库之外的，他们可能是操作系统文件。实际上，数据库中只存储了BFILE的一个指针，因此定义为BFILE数据类型的列是不能通过oracle的数据库命令来操作的，这些列只能通过操作系统命令或者第三方软件来维护。 |
| NCLOB    | 存储Nchar类型的单字节或定长字节的Unicode大数据对象。         |

### 宿主变量

- 宿主变量（全局变量）（主机变量）（替代变量和绑定变量）：在调用PL/SQL程序的环境中声明、在PL/SQL中使用非PL/SQL的变量。绑定变量（bind variables）是在宿主环境（调用PL/SQL的程序或工具）中创建的变量，也被称为宿主变量（host variables）。

- PL/SQL本身没有输入或输出功能，必须依赖于执行PL/SQL程序的环境将变量的值传入或传出PL/SQL程序块。在SQL\*Plus环境中，可以使用SQL\*Plus的替代变量将运行时的值传给PL/SQL程序块。在PL/SQL程序块中也可以使用前导的\&符号引用替代变量。在PL/SQL程序块执行之前，正文的值被替代进PL/SQL程序块中。不能够使用循环为替代变量赋予不同的值，而只有一个值将替代这个替代变量。
- 绑定变量是在使用（或调用）PL/SQL的环境中创建的，而不是在PL/SQL程序的声明段中定义的。在一个PL/SQL程序块中声明的所有变量只在执行这个程序块时可以使用。而在这个程序块执行之后，这些变量所使用的内存就都释放了。然而，绑定变量则不同，在程序块执行之后，绑定变量依然存在并可以访问。正因为如此，绑定变量可以在多个子程序（程序块）中使用。绑定变量既可以用在SQL语句中，也可以用在PL/SQL程序块中，就像其他任何类型的变量一样。也可以将绑定变量作为运行时的值传给PL/SQL子程序或从PL/SQL子程序中传出。

- SQL Developer也可以引用绑定变量并通过使用PRINT命令显示这个绑定变量的值。可以通过在绑定变量之前冠以冒号（:）的方式在PL/SQL程序块中引用绑定变量。在PL/SQL中使用SQL\*Plus的变量（绑定变量），必须冠以冒号。在PL/SQL程序段执行之后，这个变量仍然会存在。如果绑定的变量是number类型，那么不能指定精度和数值。如果绑定的变量是varchar2类型，那么可以指定字符串的长度（字节）

```
--SQL*Plus
variable g_dog_weight number

--PL/SQL
begin 
  :g_dog_weight := 38;
end;
```

### 动态获取数据

#### %type

- %type属性按照之前已经声明过的变量或者数据库中的列来声明一个变量，通过%type获取的列或变量的数据类型，会随着之前定义的变量名/列的数据类型的变化而变化。
- not null 约束不适用与使用%type属性声明的变量。但是，如果使用%type属性声明的变量是基于一个定义了not null约束的列，那么是可以将空值赋予这个变量的。

```
变量名 表名.列名%TYPR
```

```
变量名 之前定义的变量名%type    
```

- 如果列的定义发生了变化，则不再需要修改变量的声明。如果为某个特定的表声明了几个变量并且没有使用%type属性声明，当声明变量所基于的列（的定义）变更时，PL/SQL在编译这个程序头时定义变量的数据类型和精度的，确保使用%type属性声明的变量总是所基于（操作）的列相匹配。

1. 避免由于数据类型不匹配或精度不对所引起的错误。
2. 避免代码的硬编码（变量的数据类型和精度必须与操作的列或变量相匹配）。

- %type属性具有一定的额外开销，为了获取数据库中列数据类型，PL/SOL隐含地发出了一个查询语句。如果PL/SQL代码是存放在客户工具中的，那么在每次执行PL/SQL程序块时都必须执行这个查询语句。如果PL/SQL程序代码是存储过程（或存储函数），那么列的定义或变量的定义是作为p-code（parsed code）的一部分存储在数据库中的，因此也就没有%type的额外开销。然而，如果表的定义发生了变化，系统会强行重新编译相关的PL/SQL程序代码，不过，如果在修致表的定义时没有涉及到PL/SQL程序代码所使用的列，那么这个PL/SQL程序代码块仍然是有效的，因此也就不需要重新编译。

#### %rowtype

- %rowtype属性的数据类型动态变化，且直接就是变量而不再是数据类型，声明一个能够存储一个表或视图中一整行数据的记录（变量），该记录中的每一个字段的名字和数据类型取自表或视图中相应的列。

```plsql
declare 
 记录名 要存储的表名/视图名%rowtype;
 --不需要在定义一个变量；直接就是变量而不再是数据类型
 --变量名 记录名；错误
begin
 ..
end;
```

```plsql
declare
--声明一个记录类型的变量
  v_emp_record employees%rowtype;
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



### 布尔变量

- 布尔变量：只有值true、false、null可以赋给一个布尔变量，可以通过and、or、not这些逻辑操作符对变量进行比较产生true/false/null。数字、字符和日期表达式可以被用来返回一个布尔值。

> null表示未知无法确定的值。

- 不等于的操作符：\<\> 、\!= 、\~= 、^=。


#### null

1. 涉及空值的简单比较总是产生null
2. 将逻辑运算符not应用于null产生null。
3. 在条件控制语句中，如果条件为null，则不执行。
4. 在算术表达式中，只要有null，则整个表达式的结果就是null。

#### 逻辑运算符

| 逻辑运算 | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| and      | false> null > true <br />false and null = false <br />true and null = null <br />true and false= false |
| or       | true \> null \> false<br />false or null = null <br />true or null = true <br />true or false= true |
| not      | not true = false<br />not false= true<br />not null = null   |

###  程序块的嵌套、变量的作用域

- 嵌套：只要允许可执行语句的地方，就可以进行PL/SQL块的嵌套（一个嵌套块变成一个语句），异常段也同样可以包含多个嵌套的程序块。
- 一个标识符的作用域可直接引用该标识符的一个程序单元的区域。在同一个程序块中同一个标识符不能声明两次，但是在两个不同的程序块中所声明的标识符是可以同名的（是毫不相干的两个变量）。
- 在一个PL/SQL程序块中声明的变量，对于这一程序块本身来说是局域（本地）变量，而对于所有其子块就是全局变量。
- 一个变量（标识符）在声明它的程序块中是可见的，并且在所有嵌套的子块中也是可见的。如果一个PL/SQL块没有发现本地声明的变量，该PL/SQL程序块将向上查找包含它的父块。程序块绝不会向下查看所包含的子块或查找同一级别（没有嵌套关系）块。作用域适用于所有的对象，包括变量、游标、用户定义的异常和约束。

#### 外层定义标号\<\<label\>\>

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
  person_id number(10) := 1;
begin
  <<son>>
  declare
   person_name varchar2 (10):= '内';
   person_id number := 0; 
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

# 流程控制

## 条件判断

### if

```plsql
if …… then
……;
elsif …… then
……;
else
……;
end if;
```

```plsql
declare
 v_age number(3) := &p_age;
begin
  if v_age < 18 then
    dbms_output.put_line('未成年');
  elsif v_age < 60 then
    dbms_output.put_line('中年');
  else 
    dbms_output.put_line('老年');
  end if;
end; 
```

### case

1. case语句

```plsql
case 选择器
 when 表达式 then...
 when   ... then...
   ...
 [else...]
end；
```

2. case表达式

```plsql
case
  when 布尔条件表达式 then...
   ...
  [else...]
end;
```

```plsql
变量 :=
case 选择器
 when 条件值 then
   赋值 --没有分号，不是语句，只是一个值
 end;  --不需要end case;
```

```plsql
case 选择器
  when 条件值 then
    语句; --有分号；代码块；不是简单的值。
end case;
```



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
  case TRUNC(v_emp_sal/5000)
    when 0 then 'salary < 5000'
    when 1 then '5000 <= salary < 10000'
    when 2 then 'salary >= 10000'
  end;
  dbms_output.put_line(v_temp);
end;
```

```plsql
declare
 v_emp_id number := &p_emp_id;
 v_last_name employees.last_name%type;
 v_job_id employees.job_id%type;
 v_sal employees.salary%type;
 
 v_sal_increment number;
begin
  select job_id
  into v_job_id
  from employees
  where employee_id = v_emp_id;
  
  case v_job_id
    when '%IT%' then
      v_sal_increment := 1.45;
    when '%AC%' then
      v_sal_increment := 1.3;
    else
      v_sal_increment := 1.1;
  end case;
  
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

## 循环结构

- 初始化条件 循环体 循环条件 迭代条件

- 使用循环：
  - 如果语句在循环中至少执行一次，一般使用loop
  - 如果在每次开始重复是都必须测试条件，一般使用while
  - 如果重复的次数已知，一般使用for

### loop

```plsql
loop
执行语句；
...
exit [when 条件表达式];
--如果exit语句放在循环体后面，则必定先执行一次循环
--在不满足条件时，也会多执行一次执行
--如果exit语句放在循环体前面，则先检查是否满足条件
end loop;
```

```plsql
--使用循环语句打印1~100
-- 1.
 declare
 v_i number(10) := 1;
begin
  loop
    dbms_output.put_line(v_i);
  exit when v_i >= 100;
  v_i := v_i + 1;
  end loop;
end;
 
--2.
 declare
 v_i number(10) := 1;
begin
  loop
    dbms_output.put_line(v_i);
    v_i := v_i + 1;
  exit when v_i > 100;
  end loop;
end;
```

```plsql
--使用循环语句往表中插入数据

create table emp_p1
(
 emp_no number(10)
,hire_date DATE
,job varchar2(20)
,sal number(10)
,depart_no number(10)
)

declare 
  v_emp_no emp_p1.emp_no%type;
  v_dept_no emp_p1.depart_no%type := 12;
  v_sal emp_p1.sal%type := 12990;
  v_hire_date emp_p1.hire_date%type := TO_DATE('1999-02-02','yyyy-mm-dd');
  v_job emp_p1.job%type := 'counter';
  
  v_counter number(2) := 1;
  v_max_num number(2) := 3;
begin 
  select NVL(MAX(emp_no),0)
  into v_emp_no
  from emp_p1;
  
  loop
    insert into emp_p1(emp_no,depart_no,sal,hire_date,job)
    values((v_emp_no + v_counter),v_dept_no,v_sal,v_hire_date,v_job);
    
    v_counter := v_counter + 1;
    
    exit when v_counter > v_max_num;
    
   end loop;
end;
```

### for

- for：每循环一次，循环变量自动加1。如果下限和上限不是整数，则自动四舍五入，如果四舍五入后，下限大于上限，则for循环不会被执行。

1. 只在循环中引用计数器，计数器在循环之外没有定义
2. 不用为计数器赋值，即不要将计数器变量赋值赋值符号左边
3. 上限和下限都不应该为null

```plsql
for 循环计数器 IN [reverse] 下限……上限 loop
	要执行的语句;
end loop;
```

```plsql
--输出2~100之间的质数
declare
  v_flag number(1) := 0;
begin
   for i IN 2 .. 100 loop
       v_flag := 1;
       for j IN 2 .. sqrt(i) loop
           if i mod j = 0 then
              v_flag := 0;  
           end if;        
       end loop;
       if v_flag = 1 then
           dbms_output.put_line(i);
       end if;
   end loop;
end;
```

#### reverse 反向for循环

- reverse：循环变量自动减1。跟在reverse后的数字必须是从小到大的顺序，且必须是整数，不能是变量或表达式。

#### exit 退出循环

- exit：只有exit when表达式的结果是true才会执行exit，null、false则不执行。

```plsql
begin
  for i IN 最小..最大 loop
    循环体;
    [exit [标号] when 条件;]
  end loop;
end;
```

### while

- 在while loop循环语句中，循环的条件必须放在while和loop两个关键字之间，而循环的条件是每次开始循环前检查的。

```plsql
while 条件 loop
  循环体;
end loop;
```

```plsql
create table dept_p1
(
 dept_no number(10)
,loc varchar(50)
)

create sequence dept_no_seq
 start with 1
 increment by 1
 minvalue 1
 maxvalue 99
 cycle
 nocache;

declare 
 v_dept_no dept_p1.dept_no%type;
 v_loc dept_p1.loc%type := '&p_loc';
 
 v_counter number(2) := 1;
 v_max_num number(2) := &p_max_num;
begin
  while v_counter <= v_max_num loop
    insert into dept_p1(dept_no,loc)
    values(dept_no_seq.nextval,v_loc);
    
    v_counter := v_counter + 1;
  end loop;
end;
```

### goto 无条件跳转

```plsql
goto label;
……
<<label>>
```

```plsql
--输出2~100之间的质数
declare
 v_flag number(5) := 1;
begin
  for i IN 2 .. 100 loop
    for j IN 2 .. sqrt(i) loop
      if mod(i,j) = 0 then v_flag := 0;
      goto label;
      end if;
    end loop;
    <<label>>
    if v_flag = 1 then dbms_output.put_line(i);
    end if;
    v_flag := 1;
  end loop;
end;
```

### continue

```plsql
begin
  for i IN 最小..最大 loop
    循环体;
    [continue [标号] when 条件;]
  end loop;
end;
```

```plsql
declare
 v_max_num  number := &p_max_num;
 v_sum number := 0;
 v_fac number := 1;
begin
  for i IN 1..v_max_num loop
    v_sum := v_sum + i;
  end loop;
  dbms_output.put_line('1~' || v_max_num || '自然数的和为：' || v_sum);
  
  for i IN 1..v_max_num loop
    v_fac := v_fac * i;
  end loop;
  dbms_output.put_line('1~' || v_max_num || '自然数的阶乘为：' || v_fac);
  
  v_sum := 0;
  <<t_loop>>
  for i IN 1..v_max_num loop
    continue t_loop when ( mod(i,2) = 0);
    v_sum := v_sum + i;
  end loop;
  dbms_output.put_line('1~' || v_max_num || '奇数的和为：' || v_sum);
end;
```

# 组合数据类型


## 记录类型 record

- 一个记录即一组存储在若干字段中的相关联的数据，而记录中的每个字段都具有各自的名字和数据类型。记录包含一个或多个字段，可以是：标量、记录、index by表的数据类型。可以为记录赋初值或定义not null约束，没有初始化的默认值为null，可以声明和引用嵌套的记录。如果两个记录具有完全相同的数据类型，可以将一个记录直接赋值给另一个记录

```plsql
declare
  type 要定义的记录数据类型名称 is record(
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
 type person is record(
   name varchar2(20)
  ,age number(3)
  );

 type student is record(
   name varchar2(20)
  ,age number(3)
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

## index by表 （PL/SQL表）

- index by表就是一组关联的键值对。index by表是用户定义的一种组合（集合）数据类型，可以利用一个主键（下标）值作为索引的方式存储数据，而主键（下标）值不必是顺序的，既可以是正也可以是负。index by表没有界限，大小可以动态的。

- index by表由两个组件（两列）所组成：数据类型为binary\_integer或pls\_integer的主键，标量或记录（record）数据类型的列。index by表中的元素可以是任何标量类型/记录类型，而主键（下标）既可以是一个数字，也可以是一个字符串。index by表的大小是没有限制的，在index by表中，数据行（元素）的个数可以动态地增长。

```plsql
declare
 type 数据类型名 is table OF 列数据类型|变量%type|表%rowtype
     [index by pls_integer|binary_integer|varchar2(20)];

 变量名 数据类型名；
begin
  ...
end;
```

- 不能在声明index by表时将其初始化。可以在index by表上加not null约束，以防止将空值null赋于index by表中元素。

```plsql
declare
 type name_table_type is table OF employees.last_name%type
      index by pls_integer;
      
 type hire_date_table_type is table OF employees.hire_date%type
      index by binary_integer;
  
 name_table name_table_type;
 hire_date_table hire_date_table_type;
 
 v_count number(6) := &p_count;
 
begin
  for i IN 1..v_count loop
    name_table(i) := ConCAT(TO_char(i),'号');
    hire_date_table(i) := TO_DATE('1997-11-12','yyyy-mm-dd');
    dbms_output.put_line(name_table(i) || ': ' || hire_date_table(i));
  end loop;
end;
```

```
表名.方法
```

| 方法                                   | 描述                                                         |
| :------------------------------------- | :----------------------------------------------------------- |
| exists(n)                              | 如果第n个元素在PL/SQL表（数组）中存在，返回true              |
| count                                  | 返回一个PL/SQL表当前所包含的元素个数                         |
| first                                  | 返回在一个PL/SQL表中第一个（最小的）下标数字，如果PL/SQL表是空的，返回null |
| last                                   | 返回在一个PL/SQL表中最后一个（最大的）下标数字，如果PL/SQL表是空的返回null |
| prior(n)                               | 返回在一个PL/SQL表中当前元素的前n个元素的下标值              |
| next(n)                                | 返回在一个PL/SQL表中当前元素的后n个元素的下标值              |
| delete<br />delete(n)<br />delete(m,n) | 删除一个PL/SQL表中的全部元素<br />删除一个PL/SQL表中的第n个元素<br />删除数组中m\~n范围内的全部元素 |

```plsql
declare
 type emp_num_type is table OF number
      index by varchar(20);
 
 total_employees emp_num_type;
 i varchar2(20);

begin
 -- 往index by表total_employees中插入数据
 
 select count(*)
 into total_employees('10号部门')
 --total_employees添加下标为'10号部门',
 --total_employees('10号部门')内容为count(*)的结果(数字)
 from employees
 where department_id = 10;
 
 select count(*) 
 into total_employees('20号部门')
 from employees
 where department_id = 20;
 
 select count(*)
 into total_employees('30号部门')
 from employees
 where department_id = 30;
 
-- 为 i 赋值为total_employees的第一个下标(主键)
 i := total_employees.first;

 dbms_output.put_line('按升序列出各部门名称和员工数：');

 while i is not null loop
   dbms_output.put_line(i || '的员工总数：' || TO_char(total_employees(i)));
   dbms_output.put_line('i的值: ' || i);
    
-- 为 i 赋值为total_employees的当前下标的下一个下标  
   i:= total_employees.next(i);
 end loop;
 dbms_output.put_line('i的值: ' || i);
     
 dbms_output.put_line(CHR(10));

-- 为 i 赋值为total_employees的最后一个下标(主键)
 i := total_employees.last;
 dbms_output.put_line('按降序列出各部门名称和员工数：');
 
 while i is not null loop
   dbms_output.put_line(i || '的员工总数：' || TO_char(total_employees(i)));
   dbms_output.put_line('i的值: ' || i);

-- 为 i 赋值为total_employees的当前下标的上一个下标  
   i := total_employees.prior(i);
 end loop;
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

```plsql
declare
 type dept_table_type is table OF departments%rowtype
      index by pls_integer;
 dept_table dept_table_type;
 v_count number := 5;
 j number;
begin
  for i IN 1..v_count loop
    select *
    into dept_table(i * 10)
    from departments
    where department_id = i * 10;
  end loop;
  
  j := dept_table.first;
  while j is not null loop
    dbms_output.put_line(dept_table(j).department_id || ' ' ||
                         dept_table(j).department_name
                         );
    j := dept_table.next(j);
  end loop;
end;

*********************
10 Administration
20 Marketing
30 Purchasing
40 Human Resources
50 Shipping
PL/SQL procedure successfully completed
```
