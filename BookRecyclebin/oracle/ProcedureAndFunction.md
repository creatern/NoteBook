# PL/SQL的子程序

- 模块化和分层的子程序设计。

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

| 匿名程序块                                 | 子程序                         |
| ------------------------------------------ | ------------------------------ |
| 无名PL/SQL程序块                           | 命名的PL/SQL程序块             |
| 每次执行时编译，只被编译和执行一次         | 只编译一次，可多次执行         |
| 不能存储在数据库中（不是永久的数据库对象） | 可以存储在数据库中             |
| 不能被其他应用程序调用                     | 有名字，可以被其他应用程序调用 |
| 不带返回值                                 | 调用函数的子程序必须有返回值   |
| 不能带有参数                               | 可以带有参数                   |


**存储函数function和过程procedure**

- [存储函数：有返回值，创建完成后，通过select function() from dual;执行]
- [存储过程：由于没有返回值，创建完成后，不能使用select语句，只能使用pl/sql块执行]

|          procedure           |                function                |
| :--------------------------: | :------------------------------------: |
|    作为一个PL/SQL语句执行    |          作为一个表达式来调用          |
|    在头中不包含RETURN子句    | 在头中必须包含且只能包含一个RETURN子句 |
|  可以使用多个输出参数传递值  |          必须返回一个单一的值          |
| 可以包含一个无值的RETURN语句 |       必须包含至少一个RETURN语句       |

# 过程 procedure

- 存储过程（procedure）是SQL语句和流程控制语句的预编译集合
  语法：

```plsql
create [OR REPLACE] procedure 过程名
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

|                       IN                       |            OUT             |                            IN OUT                            |
| :--------------------------------------------: | :------------------------: | :----------------------------------------------------------: |
|                      默认                      |                            |                                                              |
|           从调用环境传递一个值给过程           | 从过程传递一个值给调用环境 | 从调用环境传递一个值给过程，并使用相同的参数名从过程返回给调用环境一个可能不同的值 |
|                形参如同常量使用                |        初始化的变量        |                         初始化的变量                         |
| 实参可以是一个文字，表达式，常量或初始化的变量 |       必须是一个变量       |                        必须是一个变量                        |
|               可以赋予一个默认值               |       不能赋予默认值       |                        不能赋予默认值                        |

- IN参数允许将值传递给子程序。它是一个只读参数。在子程序中，IN参数的作用如常数，它不能被赋值。可以将常量，文字，初始化的变量或表达式作为IN参数传递。也可以将其初始化为默认值; 然而，在这种情况下，从子程序调用中省略它。 它是参数传递的默认模式。参数通过引用传递。
- OUT参数返回一个值给调用程序。在子程序中，OUT参数像变量一样。 可以更改其值并在分配该值后引用该值。实际参数必须是可变的，并且通过值传递。在返回调用环境之前，必须先为OUT或IN OUT参数赋值，不能为OUT和IN OUT参数赋默认值
- IN OUT参数将初始值传递给子程序，并将更新的值返回给调用者。 它可以分配一个值，该值可以被读取。对应于IN OUT形式参数的实际参数必须是变量，而不是常量或表达式。正式参数必须分配一个值。实际参数(实参)通过值传递。

#### IN参数的使用

- 作为输入对象
- 需要一个表达式，文字等。


```plsql
create or replace procedure raise_salary
(
 p_emp_id IN copy_emp.employee_id%type
,p_rate IN number
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
 p_emp_id IN copy_emp.employee_id%type
,p_name OUT copy_emp.last_name%type
,p_sal OUT copy_emp.salary%type
,p_job OUT copy_emp.job_id%type
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
 v_last_name copy_emp.last_name%type;
 v_sal copy_emp.salary%type;
 v_job copy_emp.job_id%type;
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
 p_id IN copy_emp.employee_id%type
,p_new_job IN copy_emp.job_id%type
,p_new_sal IN copy_emp.salary%type
,p_dept OUT copy_emp.department_id%type
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
SQL> variable v_dept number;
SQL> execute update_sal(117,'IT',7000,:v_dept);
```

2. 按名字

- 以任意顺序列出实参和与之对应的形参。
- 使用关联操作符`=>`    

```plsql
SQL> variable v_dept number;
SQL> execute update_sal(p_id => 117, p_dept => :v_dept, p_new_job => 'IT', p_new_sal => 9000);
```

3. 组合

- 前两种混合使用

#### 在过程中声明和调用另一个过程

```plsql
create or replace procedure audit_emp_dml
(
 p_id IN copy_emp.employee_id%type
)
is
 procedure log_exec
 is
 begin
   insert into log_table(user_id,log_date,employee_id)
   values(TO_char(USER),sysdate,p_id);
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
 p_new_sal IN number
,p_grade IN number(2) 
)
is 
  error_sal exception;
  
  procedure check_sal
    (
    ,p_max IN number
    ,p_min IN number
    ) 
    is
    begin
      if p_new_sal NOT between p_max and p_min then
        RAisE error_sal;
      end if;
    end check_sal;
begin
   if p_grade = 1 then
       check_sal(1000,5000);
       dbms_output.put_line('工资为：'||p_new_sal||CHR(9)||'等级为：'||p_grade);
   elsif p_grade = 2 then
       check_sal(5000,10000);
       dbms_output.put_line('工资为：'||p_new_sal||CHR(9)||'等级为：'||p_grade);
   else 
     RAisE error_sal;
   end if;
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
 p_id IN number
)
is
 emp_null exception;
begin
  UPDATE copy_emp
  set salary = 1000
  where employee_id = p_id;
  
  if(SQL%NOTFOUND OR SQL%NOTFOUND = null) then
     RAisE emp_null;
  end if;
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
create table test
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
 p_name IN test.use_name%type
)
is
begin
  insert into test(use_name,use_date)
  values(p_name,sysdate);
exception
  when OTHERS then
    commit;
end user_test;
```

- 没有带异常处理的存储过程

```plsql
create or replace procedure user_test
(
 p_name IN test.use_name%type
)
is
begin
  insert into test(use_name,use_date)
  values(p_name,sysdate);
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
 p_dept_id number
,v_sum_sal OUT number
)
is
CURSOR sal_cursor is 
   select salary 
   from employees 
   where department_id = p_dept_id;
begin
       v_sum_sal := 0;
       for sal_rec IN sal_cursor loop
           --dbms_output.put_line(sal_rec.salary);
           v_sum_sal := v_sum_sal + sal_rec.salary;
       end loop;       
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
 p_dept_id number
,temp OUT number
)
is
       CURSOR sal_cursor is 
          select employee_id id
                ,hire_date hd
                ,salary sal 
          from employees 
          where department_id = p_dept_id;

       a number(4, 2) := 0;
begin
       temp := 0;       
       for c IN sal_cursor loop
           a := 0;    
           if c.hd < to_date('1995-1-1', 'yyyy-mm-dd') then
              a := 0.05;
           elsif c.hd < to_date('1998-1-1', 'yyyy-mm-dd') then
              a := 0.03;
           else
              a := 0.01;
           end if;
           temp := temp + c.sal * a;
           UPDATE employees 
           set salary = salary * (1 + a) 
           where employee_id = c.id;
       end loop;       
end;
```

# 函数 function

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
create or replace function 函数名
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
create or replace function get_sal
(
 v_id IN copy_emp.employee_id%type
)
RETURN number
is
 v_sal copy_emp.salary%type := 0;
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
- connect by和start with和ORDER by和GROUP by子句
- insert语句中的values子句
- UPDATE语句中的set子句

```plsql
create or replace function format_phone
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
  - 不能在create table或ALTER table 语句的CHECK约束子句中调用这样的函数
  - 不能使用这样的函数为一个列说明默认值
- 需要指出的是：在一个SQL表达式中只能调用存储函数，而不能调用存储过程，除非这个过程是从一个函数中调用的并且满足以上所列的要求。
- 要执行一个调用存储函数的SQL语句，Oracle服务器就必须确定所调用的函数是不是没有一些特定的副作用，因为这些副作用可能会对数据库中的表产生无法接受的更改。为此，当在一个SQL语句的表达式中调用一个函数时，Oracle需加上以下一些附加的限制：
  - 从表达式中调用函数时，该函数不能包含DML语句，即该函数不能修改数据库中表的数据。
  - 当从一个UPDATE/DELETE语句中调用一个函数时，该函数不能查询或更改这个语句正在操作的表。
  - 当从一个select、insert,UPDATE或DELETE语句中调用一个函数时，该函数不能直接地或通过子程序（或SQL)间接地执行事物控制语句，如：
    - 一个commit或rollback语句
    - 一个会话控制语句（如set role)
    - 一个系统控制语句（如ALTER system)
    - 任何DDL语句（如create)

##### 从SQL中用名字表示法和混合表示法调用函数

```plsql
create or replace function test_func
(
 p_num1 IN number := 1
,p_num2 IN number := 1
)
RETURN number
is
 v_num number;
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
where object_type = 'function';
```

![](c:/users/zjk10/oneDrive/NoteBook/pictures/Snipaste_2022-11-18_11-14-17.png =400x)

- status属性：
  - VALID 表示可以被调用
  - INVALID 表示不可以被调用，必须重新编辑
    - 一般由于该工程使用的对象（通常是表）的定义被修改了 


#### 应用

##### 函数的 helloworld: 返回一个 "helloworld" 的字符串

```plsql
create or replace function hello_func
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
create or replace function hello_func(v_logo varchar2)
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
create or replace function func1
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
create or replace function add_func(a number, b number)
RETURN number
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
create or replace function sum_sal(dept_id number)
RETURN number
is       
CURSOR sal_cursor is select salary 
                     from employees 
                     where department_id = dept_id;
v_sum_sal number(8) := 0;   
begin
       for c IN sal_cursor loop
           v_sum_sal := v_sum_sal + c.salary;
       end loop;       
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
create or replace function sum_sal(dept_id number, total_count OUT number)
RETURN number
is 
CURSOR sal_cursor is select salary 
                     from employees 
                     where department_id = dept_id;
v_sum_sal number(8) := 0;   
begin
       total_count := 0;
       for c IN sal_cursor loop
           v_sum_sal := v_sum_sal + c.salary;
           total_count := total_count + 1;
       end loop;       
       --dbms_output.put_line('sum salary: ' || v_sum_sal);
       RETURN v_sum_sal;
end;   

执行函数:
declare 
  v_total number(3) := 0;
begin
    dbms_output.put_line(sum_sal(80, v_total));
    dbms_output.put_line(v_total);
end;
```


