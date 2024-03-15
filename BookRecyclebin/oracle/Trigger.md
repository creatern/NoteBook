# 触发器 trigger

- 触发器：特殊的存储过程，是一个与表相关联的、存储的PL/SQL程序。每当一个特定的数据操作语句DML（insert、update、delete，没有select）在指定的表上发出时，oracle自动地执行触发器中定义的语句序列。

> 在oracle系统上，触发器类似过程和函数，都有声明、执行、异常处理过程PL/SQL块。

 **触发器在数据库里以独立的对象存储，它与存储过程不同的是：**

|                        触发器trigger                         |              存储过程procedure               |
| :----------------------------------------------------------: | :------------------------------------------: |
| 触发器是由一个事件(DML语句)来启动运行，即触发器是当某个事件发生时自动的隐式运行,并且触发器不能接受参数，所以运行触发器叫触发或点火 | 存储过程通过其它程序来启动运行或直接启动运行 |
|             源代码包含在USER triggerS数据字典中              |      源代码包含在USER_SOURCE数据字典中       |
|            不允许使用commit、savepoint和rollback             |     允许使用commit、savepoint和rollback      |

>1. 复杂的安全性检查 --比如周末不允许操作数据库
>   - 触发器案例一：禁止在非工作时间插入数据
>
>2. 数据的确认 --涨后的工资大于涨前的工资
>   - 触发器案例二：涨工资不能越涨越少 :old和:new的使用要注意
>
>3. 数据库审计 --跟踪表上所做的数据操作，什么时间什么人操作了什么数据，操作的人是什么。基于值的审计
>   - 触发器案例三：创建基于值的触发器
>
>4. 数据的备份和同步 --异地备份，把主数据的数据自动同步到备数据库中
>   - 触发器案例四：数据库的备份和同步
>   - 利用触发器实现数据的同步备份，多用于异地分布式数据库,
>   - 还能使用快照备份，快照备份是异步备份，而触发器是同步备份，没有延时的

## 触发器类型

| 触发器类型 | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| DML触发器  | oracle可以在DML语句操作前后触发，并且可以对每一个行或语句操作上触发。 |
| 替代触发器 | 不能直接对两个以上的表建立的视图进行操作。                   |
| 系统触发器 | 在oracle数据库系统的事件中进行触发。                         |

- 基于系统事件的触发器可以被定义在数据库一级，也可以被定义在模式一级。而基于数据定义语言（DDL）语句上、或基于用户登录/退出操作的触发器既可以定义在数据库一级，也可以定义在模式（用户）一级。
- 基于数据维护语言（DML）语句的触发器是定义在一个特定的表或一个特定的视图上。

```plsql
create or replace trigger 触发器名
before|after|instead of
insert|delete|update [of column[列名]]
on [模式（用户名）.]表/视图
[referencing old as 指定引用old的别名 | new as 指定引用new的别名]
[for each row] --行级触发器
[when 条件表达式] --只用于行级触发器
触发器体; --PL/SQL块等
```

## 触发器组成

### 触发事件

- 触发事件：即在何种情况下触发触发器。
  - 在一个表或视图上的，insert/update/delete语句
  - 在任何用户上的，create/ALTER/DROP语句
  - 一个数据库启动或实例关闭
  - 一个用户的登录或退出
  - 一个特定的错误信息或任何错误信息

### 触发时间

- 触发时间：即触发事件和该触发器的操作顺序。

| 触发时间   | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| before     | 在触发事件（执行）之前执行触发器。<br />决定所触发的语句是否应该允许被完成，在完成一个insert或update语句之前导出原来列的值。<br />初始化全局变量，验证一些复杂的业务规则。 |
| after      | 在触发事件之后执行触发器。<br />在执行触发器之前先完成触发的语句，在已经存在before触发器时，要在相同的触发语句上执行不同的操作。 <br />如果触发语句完成后没有commit，则其他用户看见的还是没有执行触发器的结果。 |
| instead of | 替换触发事件的语句来执行触发器。<br />被用于用其他方法不能修改的视图。 |

- 当不能通过SQL的DML语句直接修改视图时，可以在instead of触发器的体中写DML语句在这个视图的基表上直接执行。

### 触发器本身

- 触发器本身即该trigger被触发之后的目的和意图，正是触发器本身要做的事情，PL/SQL块、或call调用子句 `call 存储过程/函数等`。

### 触发频率

- 触发频率：说明触发器内定义的动作被执行的次数，语句级(statement)触发器和行级(row)触发器。
  - 语句级触发器：指当某触发事件发生时该触发器只执行了一次。
  - 行级触发器：指当某触发事件发生时，对受到该操作影响的每一行数据，触发器都执行一次。

| 类型 |       语句级触发器       |            行级触发器            |
| ---- | :----------------------: | :------------------------------: |
| 创建 |         默认类型         |       for each row子句指定       |
| 频率 | 对于触发的事件只触发一次 |  对受触发事件影响的每行触发一次  |
| 条件 | 没有行受影响时也触发一次 | 触发事件未影响任何数据行就不触发 |

```plsql
create or replace trigger menu_test_trigger
before 
delete on menu
for each row
begin
  dbms_output.put_line('good');
end;
```

```plsql
create or replace trigger secure_emp_trigger
before 
insert on copy_emp
begin
  if(TO_char(sysdate,'DY') IN ('SAT','SUN')) 
   or (TO_char(sysdate,'HH24:MI') NOT between '08:00' and '18:00') then
    RAisE_APPLICATIon_ERRor(-20038,'非工作时间，拒绝输入数据');
  end if;
end;  
```

## 触发器执行模型概要及实现完整性约束的准备

- 在触发器中的触发事件或语句可能引起一个或多个完整性约束的检查，但是可以将约束的检查延迟到执行commit操作时。
- oracle服务器触发这四种触发器的方式如下。

1. 执行所有的before statement(语句)触发器。
2. 对受影响的每一行循环：
   - 执行所有的before row(行)触发器。
   - 执行所有的DML语句并进行完整性约束的检查。
   - 执行所有的after row(行)触发器。
3. 执行所有的after statement(语句)触发器。

- 触发器也可能引起其他触发器的触发（执行），即所谓的级联触发器。作为一个SQL语句的结果，所有执行的操作和检查必须成功。如果在一个触发器中抛出了一个异常并且这个异常没有被显式地处理，那么所执行地所有操作（包括触发语句）全部被回滚。保证触发器不违反完整性约束

**引用完整性**

1. 外键必须为空值（null）或者有相匹配地项
2. 外键可以没有相对应的键属性，但不可以有无效的项

- 虽然引用完整性可以有效地防止错误的DML语句，但在特殊情况下，可能会短时间地出现违法引用完整性地数据。可以创建一个after update触发器来解决。

### 利用触发器实现完整性约束

```plsql
--departments中的主键department_id是employees中的外键。

create or replace trigger emp_dept_fk_trg
after update of department_id on employees
for each row
  begin
    insert into departments(department_id,department_name)
    values(:new.department_id,'新的部门:'||TO_char(:new.department_id));
  exception
    when DUP_VAL_on_INDEX then
      null;  --如果存在该异常，则不进行任何操作.
             --并避免整个程序的中断。屏蔽该异常
  end;
```

# 行级触发器

- 如果在较大的表上执行许多修改，行触发器可能降低系统的效率。

## 条件谓词 insertING/DELETING/UPDATING

- 如果有多种类型的DML操作可以触发一个触发器，那么在触发器体中可以使用条件谓词来检查触发器的语句类型，从而根据相应的语句来进行相应的操作。

**条件谓词**

- inserting
- deleting
- updating
- `updating(列名)` 那一列被update，则返回true

```plsql
create or replace trigger secure_emp_trigger
before 
insert or delete or update on copy_emp
for each row
begin
  if insertING then
    RAisE_APPLICATIon_ERRor(-20001,'禁止插入数据');
  elsif DELETING then
    insert into user_test_table(user_name,old_id,new_id)
    values (user,:old.employee_id,:new.employee_id);
  elsif UPDATING then
    insert into user_test_table(user_name,old_id,new_id)
    values (user,:old.employee_id,:new.employee_id);
  end if;
end;
```

## 限定符 :new 和 :old

- 当一个行级触发器触发时，PL/SQL运行时的引整创建和维护两个数据结构，它们就是old和new，old和new具有完全相同的记录结构，并且使用 %rowtype 声明成与触发器所基于的表的数据类型一模一样。
- old存储触发器处理之前的记录的原始值，而new则包含了新值。

| 数据操作 |             old              |             new             |
| :------: | :--------------------------: | :-------------------------: |
|  insert  | （插入之前的值）空值（null） |          插入的值           |
|  update  |         修改之前的值         |        修改之后的值         |
|  delete  |         删除之前的值         | 空值（null)（删除之后的值） |

**注意：**

- 只在行触发器中有old和new限定词。
- 在每个SQL和PL/SQL语句中，这两个限定词前必须冠以冒号(：)（`:new`和`:old`）。
  - 如果这两个限定词是在when所在条件中引用则不能冠以冒号。否则orA-25000: 在触发器 when 子句中, 连接变量的使用无效

```
create or replace trigger update_emp_trigger
after 
 update on employees
for each row
begin
  dbms_output.put_line('old_sal'||:old.salary||'new_sal'||:new.salary);
end;
```

```
create or replace trigger audit_emp_values
before insert or delete or update on copy_emp
for each row
  begin
    insert into user_test_table(user_name,old_id,new_id)
    values (USER,:old.employee_id,:new.employee_id);
  end;
```

### after

- 在after类型中，无法更改此触发器类型的 new值。

```
create or replace trigger test_trigger
after insert on user_test_table
for each row
  begin
    :new.new_id := 1;
  end;

orA-04084: 无法更改此触发器类型的 new 值
```

## when 条件行触发器

- 可以通过在触发器的定义中增加一个when子句，并在这个子句中说明一个布尔表达式的方法来限制一个触发器的操作。
- 如果在触发器中包括了个when子句，那么WHN子句中的表达式要评估（测试)该触发器所影响的每一行。
  - 对于每一行，如果这个表达式的评估结果是true，那么触发器体（PL/SQL程序代码）将执行。如果表达式对于某一行的测试结果是FALSE或NUL，,那么触发器体就不执行。
- when子句的评估并不影响触发的SQL语句的执行，即如果一个when子句测试为FALSE，触发语句并不回滚。
- when子句是不能包括在语句触发器的定义中的，这个子句只能包括在行触发器的定义中。
- **限定符 :new和:old在when子句中不能带冒号**
  - orA-25000: 在触发器 when 子句中, 连接变量的使用无效

```plsql
create or replace trigger set_com_pct
before insert or update on copy_emp
for each row
when (new.job_id = 'IT')
  begin
    if insertING then
      :new.commission_pct := 0;
    else
      if :old.commission_pct = 0 
       or :old.commission_pct is null then
        :new.commission_pct := 0.3;
      else 
        :new.commission_pct := :old.commission_pct;
      end if;
    end if;
  end; 
```

## 替代触发器 instead of

- oracle服务器触发该触发器并替代执行所触发的语句
- instead of触发器被用于直接在视图所基于的表上执行insert/update/delete语句。从而修改视图
  - instead of触发器以不可见的方式工作 并在后台执行相应的正确操作。
  - 如果一个视图本身是可以修改的并且上面有instead of触发器，那么触发器优先
- instead of 触发器是行级触发器，对视图执行插入和修改操作是，该视图的CHECK选项不起作用。
  - 此时在触发器体中必须实现这样的检查操作。

### 例

```
create or replace VIEW copy_emp_detaikls
as
select *
from copy_emp;
```

```
create or replace trigger new_emp_details
instead of insert or update or delete on copy_emp_details
for each row
  begin
    if insertING then
    --直接往copy_emp表中插入数据
      insert into copy_emp(employee_id,last_name,salary,department_id)
      values(:new.employee_id,:new.last_name,:new.salary,:new.department_id);
    elsif DELETING then
    --直接删除copy_emp表中的数据
      delete from copy_emp
      where employee_id = :old.employee_id;
    elsif UPDATING('salary') then
      update copy_emp
      set salary = :new.salary
      where employee_id = :old.employee_id;
    elsif UPDATING('department_id') then
      update copy_emp
      set department_id = :new.department_id
      where employee_id = :old.employee_id;
    end if;
  end;
```

## 在变异表上触发器的限制

**变异表**

目前正在由update，DELTETE或insert语句修改的表，或者是一个受到声明的delete CasCADE引用完整性（外键约束）操作影响可能需要修改的表。（对于语句触发器而言，以上的不认为是变异表）

- 当一个行级触发器试图修改或测试一个正在通过一个DML语句修改的表时，系统会产生一个变异表错误(orA-4091)。使用触发器读写表中的数据时必须遵守一定的规则，但是这些规则只适用于行级触发器，而语句触发器并不受影响。其限制和限制的目的如下：
  - 发出触发语句的会话不能查询或修改变异表。
  - 这一限制防止一个触发器看到一个不一致的数据集。
  - 这一限制适用于所有使用for each row子句的触发器。
  - 使用instead of触发器正在修改的视图不被认为是变异的。

被触发的表（触发器进行操作的表）本身是一个变异表，同样使用外键(forEIGN KEY)约束引用的任何表也是变异表。正是这样的限制防止一个行触发器看到一个不一致的数据集合（正在修改的数据)。

**例**

```
create or replace trigger check_salary
 before insert or update of salary,department_id on copy_emp
 for each row
declare
 v_min_sal copy_emp.salary%type;
 v_max_sal copy_emp.salary%type;
begin
  select MIN(salary)
        ,MAX(salary)
  into v_min_sal
      ,v_max_sal
  from copy_emp
  where department_id = :new.department_id;
  if :new.salary < v_min_sal or :new.salary > v_max_sal then
    RAisE_APPLICATIon_ERRor(-20001,'工资不在允许范围内');
  end if;
end;

update copy_emp
set salary = 1000
where employee_id = 177;
*********************
orA-04091: 表 SCOTT.COPY_EMP 发生了变化, 触发器/函数不能读它
orA-06512: 在 "SCOTT.CHECK_SALARY", line 5
orA-04088: 触发器 'SCOTT.CHECK_SALARY' 执行过程中出错
```

**解决**

1. 将汇总数据（最低工资和最高工资）存储在另一个汇总表中，而该汇总表中的数据由其他的DML触发器进行持续的更新。
2. 将汇总数据存储在一个PL/SQL软件包中，并在这个软件包中访问这些汇总数据。这可以通过before语句触发器中来实现
3. 复合触发器(compound trigger)。

# 复合触发器(compound trigger)

复合触发器体(compound trigger body)支持一种常见的PL/SQL状态，在这种状态中对于每一个触发时机，触发器体的PL/SQL程序代码都是可以访问的。当触发语句完成时（即当触发语句引起一个错误时)，这种常态将自动消失。利用这一新的特性，应用程序可以通过允许数据行存放到第二个表（如一个历史表或一个审计表）中进行累加操作之后再对这些数据行进行批量插入操作。

- 复合触发器使得PL/SQL的编程更容易，并且也改进了运行的性能以及提高了可扩展性。

**使用情景：**

1. 对于不同的时间点（时机），在程序中要实现对共享公用数据的一些操作。
2. 将一些数据行累积在一起并存放在第二个表中，以便能够定期地批量插入这些数据。
3. 避免变异表错误（orA-04091)。

**触发时机**

- 在一个表上定义的一个复合触发器都具有一个或多个时间点的程序段共有4个不同的时间点，
- 复合触发器是基于一个表的单一触发器，在这个触发器中运行为4个触发时机（时间点）指定的每一个操作，这4个触发时机分别为：

|   (时机/时间点)Time Point    | (复合触发器的部分)Compound trigger Section |
| :--------------------------: | :----------------------------------------: |
|       在触发语句之前。       |              before statement              |
| 在触发语句影响的每一行之后。 |              after statement               |
| 在触发语句影响的每一行之前。 |              before each row               |
|       在触发语句之后。       |               after each row               |

- 每个时间点（程序）段必须按照表中所列的顺序出现在一个复合触发器的代码中。
  - 如果缺少某一个时间点段，那么在这个时间点就没有任何事情发生，即什么也不做。
- 一个复合触发器既可以基于一个表，也可以基于一个视图(instead of)。

**程序段**

- 每个复合触发器都一定具有两种类型的程序段，这两种类型的程序段分别是：

1. 初始段，在该段中声明变量和子程序。
   - 这段中的程序代码会在可选段中的任何代码执行之前执行。
2. 为每一种可能的触发时间点定义代码的可选段。
   - 取决于是在一个表上还是在一个视图上定义复合触发器，这些触发时间点是不同的，并且这些触发时间点的程序代码必须按照顺序编写
     - before statement --> after statement --> before each row --> after each row
   - 对于一个基于视图的复合触发器，唯一允许的程序段就instead of each row子句开始的段。

**语法**

1. 基于表的复合触发器 

```
create or replace trigger [用户.]触发器名
 for insert|update|delete on [用户.]表名
 COMPOUND trigger
-- 初始段（不要declare)
 声明部分;
 子程序;
-- 可选段
 before STATAMENT is
   语句;(PL/SQL代码块)
 after statement is
   语句;
 before each row is
   语句;
 after each row is
   语句;
end;
```

2. 基于视图的复合触发器

```
create or replace trigger [用户.]触发器名
 for insert|update|delete on [用户.]视图
 COMPOUND trigger
--初始段
  声明部分;
  子程序;
--可选段
  instead of each row is
    语句;(PL/SQL程序块)
end;
```

**使用复合触发器时，oracle限制:**

- 一个复合触发器的体代码必须复合了整个触发器程序块，而且必须是使用PL/SQL编写的。
- 一个复合触发器必须是一个DML触发器。
- 一个复合触发器必须被定义在一个表上或者一个视图上。
- 一个复合触发器体不能有初始化段，也不能有异常段。
  - 在任何其他时间点程序段执行之前before statement程序段永远只执行一次。
- 在一个程序段中出现的一个异常必须在这个段中处理，复合触发器无法将异常的控制传递给其他段。
- old、new和PARENT不能出现在声明段中，也不能出现在before statement和after statement段中。
- 复合触发器的触发顺序是无法保证的，除非使用了FOLLOWS子句。

## 例 使用复合触发器解决变异表问题

```
create or replace trigger check_salary
 for insert or update of salary,job_id on copy_emp
 when (new.job_id <> 'AA')
 COMPOUND trigger
 
 type sal_type is table of copy_emp.salary%type;
 min_sal sal_type;
 max_sal sal_type;
 
 type dept_id_type is table of copy_emp.department_id%type;
 dept_id_list dept_id_type;
 
 type dept_sal_type is table of copy_emp.salary%type
   INDEX by varchar2(38);
 dept_min_sal dept_sal_type;
 dept_max_sal dept_sal_type;

before statement is
 begin
   select MIN(salary)
         ,MAX(salary)
         ,NVL(department_id,-1)
   BULK colLECT into min_sal
                    ,max_sal
                    ,dept_id_list
   from copy_emp
   GROUP by department_id;
   
   for i IN 1..dept_id_list.COUNT() loop
     dept_min_sal(dept_id_list(i)) := min_sal(i);
     dept_max_sal(dept_id_list(i)) := max_sal(i);
   end loop;
 end before statement;
 
 after each row is
   begin
     if :new.salary < dept_min_sal(:new.department_id) 
       or :new.salary > dept_max_sal(:new.department_id) then
       RAisE_APPLICATIon_ERRor(-20001,'新工资超过部门工资范围');
     end if;
   end after each row;
end; 
```



# 系统触发器（以及DDL触发器)

## DDL触发器

- 除了DML语句之外，还可以指定一种或多种DDL语句来触发触发器（代码的执行）。可以为这些事件(DDL语句)在数据库上或模式上（需要指定模式即用户）创建触发器，还可以说明before和after作为这类触发器的触发时机。oracle数据库在现存的用户事务中存放这类触发器。
  - 要注意的是，不能将通过一个PL/SQL过程执行的任何DDL操作说明为一个触发器的事件。
- 只有所创建的对象是一个cluster(簇)、表、索引、表空间、视图、函数、过程、软件包、触发器、(数据)类型、序列(sequence)、同义词(synonym)、角色或用户时，DDL触发器才能触发。
- 基于数据定义语言(DDL)语句上的触发器的触发器既可以定义在数据库一级，也可以定义在模式（用户）一级

**创建基于DDL语句的触发器的语法格式如下：**

```
create or replace trigger 触发器名
 before|after [DDL事件1[or DDL事件2 or..] on 数据库|模式
 --DDL事件包括create、ALTER和DROP语句等，
   触发器体(PL/SQL程序块)
```

- 一个定义在数据库一级的触发器对数据库中的所有用户都会触发，而定义在模式或表一级的触发器只有当触发事件涉及到指定的模式或表时才会触发。现将可能引起一个触发器触发的触发事件归纳如下（一共有4大类）：

1. 一个在数据库或模式中一个对象上的数据定义语句。
2. 一个指定用户（或任何用户）的登录或退出。
3. 一个数据库的关闭或启动。
4. 所发生的任何错误。

## 系统触发器

**基于oracle数据库系统事件创建触发器的语法格式如下：**

```
create[or REPLACE]trigger触发器名
 before|after [数据库事件1[or数据库事件2or..]] on database|SCHEMA
   触发器体
```

**数据库事件**

|    数据库事件     |        触发时机        |              触发器的级              |
| :---------------: | :--------------------: | :----------------------------------: |
| after SERVERERRor | 一个oracle错误被抛出时 | 可以创建基于数据库或模式一级的触发器 |
|    after LOGon    |  一个用户登录数据库时  | 可以创建基于数据库或模式一级的触发器 |
|   before LOGofF   |  一个用户退出数据库时  | 可以创建基于数据库或模式一级的触发器 |
|   after startup   |      开启数据库时      |      只能创建数据库一级的触发器      |
|  before shutdown  |    正常关闭数据库时    |      只能创建数据库一级的触发器      |


**权限**

- 仅仅拥有 `create ANY trigger`的权限是不够的，创建触发器(trigger)时，orACLE有一个限制，触发器(trigger)的拥有者必须被显示(explicitly)授予访问触发器(trigger)中涉及到的对象的权限(也就是说这些权限不能由角色继承而来)。
- 则创建数据库级触发器需要：`ADMINisTER database trigger`权限

## 例：用户登录'登出触发器

**用户级触发器**

```plsql
--创建序列，当作编号log_id
create sequence log_onoff_seq
 start with 1
 increment by 1
 maxvalue 99
 nocache
 cycle;

--存放用户登入登出信息
create table log_onoff_table
(
 log_id number(3)
,user_id varchar2(38)
,log_date DATE
,action varchar2(50)
);

--存放用户错误信息
create table error_table
(
 user_name VAchar2(20)
,error_time DATE
,error_msg varchar2(50)
);

--登入触发器
create or replace trigger user_log_on_trigger
after LOGon on SCHEMA
 declare
   v_log_id number;
 begin 
   select log_onoff_seq.nextval
   into v_log_id
   from dual;
 
   insert into log_onoff_table
   values(v_log_id,USER,sysdate,'用户登入');
 end;
 
--登出触发器 --有问题！！！无法写入
create or replace trigger log_off_trigger
before LOGofF on SCHEMA
  begin
    insert into log_onoff_table
    values(log_onoff_seq.nextval,USER,sysdate,'用户登出');
  end;
 
--ERRor记录触发器
create or replace trigger error_message_trigger
after SERVERERRor on SCHEMA
  begin
    insert into error_table
    values (USER,sysdate,'.');
    --如何获取异常信息
  end;
```

**数据库级触发器** 

```
--sysdba赋予scott ：ADMINisTER database trigger权限
GRANT ADMINisTER database trigger TO scott;

--存放用户登入登出信息
create table system_log_onoff_table
(
 user_id varchar2(38)
,log_date DATE
,action varchar2(50)
);

--登入触发器
create or replace trigger sys_log_on_trigger
after LOGon on database
 begin 
   insert into system_log_onoff_table
   values(USER,sysdate,'用户登入');
 end;
 
--登出触发器
create or replace trigger sys_log_off_trigger
 before LOGofF on database
  begin
    insert into system_log_onoff_table
    values(USER,sysdate,'用户登出');
  end;

--ERRor记录触发器

--开启数据库触发器
create or replace trigger sys_startup_database_trigger
 after startup on database
   begin
     insert into system_log_onoff_table
     values('orcl数据库',sysdate,'启动数据库');
   end;
   
--关闭数据库触发器
create or replace trigger sys_startup_database_trigger
 before shutdown on database
   begin
     insert into system_log_onoff_table
     values('orcl数据库',sysdate,'关闭数据库');
   end;
```

**问**

- 可以使用视图的方式，来避免其他用户的直接修改。但是要怎么实现插入新的信息？

## 数据库级系统触发器的权限问题

**报错** 创建数据库级触发器

- orA-04098: 触发器 'sys.USER_LOG_on_trigger' 无效且未通过重新验证


**删除**

- 删除之前的触发器或disable(简单粗暴)

```plsql
ALTER trigger user_log_on_trigger disable;
DROP TIRGGER user_log_on_trigger;
```

- 掀桌子 sysdba

```plsql
SQL> DROP trigger user_log_on_trigger;

触发器已删除。
```

- 查看

```plsql
select trigger_name
      ,trigger_type
      ,triggering_event
      ,status
from USER_triggerS;
```

# call 调用语句

- 在触发器体中使用，调用一个存储过程（可看作一个PL/SQL程序块使用），该过程可以是PL/SQL，c，c++，Java语言。

```plsql
call 过程名
```

- call语句不要分号;结尾
- 在一个触发器体中最多只能使用一个call语句。
- 在一个触发器体中若存在一个call语句，则不能使用PL/SQL程序块等。
  - 即该触发器体也只能存在该call语句，不能有其他。

```plsql
--创建过程
create or replace procedure update_sal_action
 is
 begin
  dbms_output.put_line('更新成功！');
 end;

--创建触发器并使用call调用过程
create or replace trigger update_sal_trigger
 before update or insert of salary on copy_emp
 for each row
 when (new.employee_id <> 1)
 --调用过程update_sal_action
 call update_sal_action
```

# 触发器的管理与维护

- 每个触发器都具有激活(enabled)和禁止(disabled)两种模式（状态），一个触发器只能处于enabled状态或disabled状态。

1. enable: 如果发出了一个触发语句并且该触发器的限制（如果有的话）评估（测试）为true(默认)，那么触发器运行它的触发器操作(PL/SQL程序代码)。
2. disable: 触发器不运行它的触发器操作(PL/SQL程序代码)，即使发出了一个触发语句并且该触发器的限制（如果有的话）评估为true也不运行。

当一个触发器被首次创建时，它的状态默认是enabled。oracle服务器对激活的触发器要检查所定义的完整性约束并保证这些触发器不会违反任何定义的完整性约束。另外，oracle服务器还为查询和约束提供读取一致性的视图、管理依赖关系，并且如果一个触发器是修改分布数据库中远程的表,   oracle服务器还提供一种两阶段的提交处理过程。即可以利用ALTER trigger语句控制指定的触发器的状态，也可以利用ALTER table语句控制指定表上所有触发器的状态。

- 关闭（禁止）或重新开启（激活）一个数据库触发器的命令如下：

```
ALTER trigger 触发器名 disable|enable;
```

- 关闭（禁止）或重新开启（激活）一个表上的所有触发器的命令则为：

```
ALTER table 表名 disable|enable ALL triggerS;
```

- 如果由于某种原因，一个触发器变成了无效的(invalid)，应该使用ALTER trigger显式地重新编译这个触发器（的PL/SQL程序代码），重新编译一个表上的一个触发器的命令如下：

```
ALTER trigger 触发器名 COMPILE;
```

- 如果已经不再需要某个触发器时，应该使用DROP trigger语句从数据库中删除这个没用的触发器，从数据库中删除一个触发器语句如下：
  - 注意，当一个表被删除时，所有在该表上的触发器也会被删除。

```
DROP trigger 触发器名;
```

**为什么将一个触发器设置为disabled状态**

其实，将一个触发器设置为disabled状态往往是一个无奈之举。有时一个系统可能已经满负荷运行，系统的效率很低。此时可能已经没有其他方法可以提高系统效率了，在这种情况下，就可能暂时关闭一个或多个触发器以换取系统效率的提高。实际上，这是一种以牺牲数据的完整性和系统安全为代价的系统优化举措，应该也是不得已而为之。系统的安全与效率永远是一对矛盾，越安全的系统，效率往往越低，反之效率越高的系统，就越不安全。最后，作为系统的开发者或管理者要在这两者之间做出艰难的平衡。一般触发器处在disabled状态应该是一个临时而短暂的状态，一旦系统效率正常之后，应该尽快将这些触发器重新设置回enabled。一般将一个触发器临时设置成disabled状态的情况可能如下：

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

## 数据字典 USER_OBJECTS , USER_triggerS

- 尽量使用USER_triggerS数据字典来查询信息。USER_OBJECT信息可能有误。

```
select object_id
      ,object_name
      ,created
      ,object_type
from USER_OBJECTS
where object_type = 'trigger';
```

```
select trigger_name
      ,trigger_type
      ,triggering_event
      ,status
from USER_triggerS;
```

## 触发器的权限问题

- 创建触发器的权限 需要：
  1. `create trigger` create ANY trigger
  2. 拥有在触发语句中所指定的表，
  3. 且有对这个表的ALTER权限。或ALTER ANY table权限

- 如果用户的触发器引用了如何不在用户模式中的对象，那么创建该触发器的用户必须在引用的过程，函数和软件包上具有执行权限，且该执行权限不是通过角色授予的。

- 创建数据库级触发器 需要：`ADMINisTER database trigger`权限
