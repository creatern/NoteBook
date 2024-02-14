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
  v_sal employees.salary%type;
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
  v_sal employees.salary%type;
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
     null; --如果存在该异常，则不做任何操作
end;
```

#### 捕获异常的两个函数

- SQLCODE
  - 为错误代码返回一个数值（可以赋给数字变量） 

| SQLCODE返回值 |            描述            |
| :-----------: | :------------------------: |
|       0       |        没有遇到异常        |
|       1       |       用户定义的异常       |
|     +100      |     NO_DATA_FOUND异常      |
|     负数      | 其他的Oracle服务器错误号码 |

- SQLERRM
  - 返回字符串数据，包含了与错误号相关的错误信息 

#### 例：一般的调试语句

```plsql
--先建一个表来存储错误信息
create table errors
(
 user_name varchar2(255)
,error_date DATE
,error_code number(10)
,error_message varchar2(255)
);

--PL/SQL程序块
declare
 v_emp_id copy_emp.employee_id%type := &p_emp_id;
 v_dept_id copy_emp.department_id%type := &p_dept_id;
 
 v_error_code number;
 v_error_message varchar2(255);

begin
  insert into copy_emp(employee_id,last_name,department_id,job_id,salary)
  values(v_emp_id,'一号',v_dept_id,'安保',5000);
exception
  when OTHERS then
    rollback;
    
    v_error_code := SQLCODE;
    v_error_message := SQLERRM;
    
    insert into errors(user_name,error_date,error_code,error_message)
    values(USER,sysdate,v_error_code,v_error_message);
    
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
 
 v_id number := &p_id;
 v_sal number := &p_sal;
begin
 UPDATE copy_emp
 set job_id = '安保'
    ,salary = v_sal
 where employee_id = v_id;
 
--利用隐式游标的属性SQL%NOTFOUND来确认是否找到员工
 if SQL%NOTFOUND then
   RAisE update_null;
 end if;
 
 if (v_sal >= 5000) then
   RAisE too_high_sal;
 elsif v_sal <= 0 then
   RAisE too_low_sal;
 end if;
 
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
  v_sal employees.salary%type;
  
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
  
  if v_sal > 1000 then
     RAisE too_high_sal;
     --使用RAisE语句显式的抛出异常
  end if;
  
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
RAisE_APPLICATIon_ERROR(用户说明的异常号码,'用户定义的异常信息'[,{true | FALSE}])

用户说明的异常号码 范围只能是-20000~20999
用户定义的异常信息 字符串 最大2048个字节
{true | FALSE}: true 这个错误被放在之前错误层之上
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
  
  if SQL%NOTFOUND then
    RAisE e_invalid_employee;
  end if;
  
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
  
  if SQL%NOTFOUND then
    RAisE e_invalid_employee;
  end if;
  
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
  v_sal employees.salary%type;
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
  v_sal employees.salary%type;
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
   v_sal employees.salary%type;
begin
   select salary 
   into v_sal 
   from employees
   where employee_id = 100;
  
   if(v_sal < 300) then 
      UPDATE employees 
      set salary = salary + 100
      where employee_id = 100;
   else dbms_output.put_line('工资大于300');
   end if;
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
  if sql%notfound then
     raise no_result;
  end if;  

exception

  --处理程序抛出的异常
  when no_result then
     dbms_output.put_line('更新失败');
end;
```


## 
