# 批量绑定 

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
 p_percent number
) 
is
  type idlist_type is table OF number
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
 - 其第n个元素存储的就是一个insert、UPDATE或DELETE语句的第n次执行时所处理的数据行数。
 - 如果第n次执行没有影响任何数据行，那么%BULK_ROWCOUNTO(i)就返回零。

```
declare 
  type name_list_type is table OF varchar2(20)
    INDEX by BINARY_INTEGER;
  
  v_name_list name_list_type;
begin
  v_name_list(1) := '一号';
  v_name_list(2) := '二号';
  v_name_list(3) := '三号';
  
  forALL i IN v_name_list.FIRST..v_name_list.LAST
    insert into my_test(contain)
    values(v_name_list(i));
  for i IN v_name_list.FIRST..v_name_list.LAST loop
    dbms_output.put_line('第' || i || '次操作的行数:'||SQL%BULK_ROWCOUNT(i));
  end loop;
end; 

***************
第1次操作的行数:1
第2次操作的行数:1
第3次操作的行数:1
PL/SQL procedure successfully completed
```

#### 使用RETURNING子句将DML语句的结果直接装入变量

- 在insert/UPDATE/DELETE语句中可以包括一个RETURNING子句，将受影响的数据行中指定列的值返回给PL/SQL变量或宿主变量。
  - 减少了select into语句的使用
  - 只需要较少的网络流量，服务器时间和服务器内存

```
create or replace procedure update_sal
(
 p_emp_id number
)
is
 v_last_name copy_emp.last_name%type;
 v_dept_id copy_emp.department_id%type;
 v_job_id copy_emp.job_id%type;
 v_sal copy_emp.salary%type;
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
 p_percent number
)
is
 type emp_id_list_type is table OF number
  INDEX by PLS_INTEGER;
 type sal_list_type is table OF copy_emp.salary%type
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
    
    for i IN 1..v_sal_list.COUNT loop
      dbms_output.put_line(v_emp_id_list(i)||': '||v_sal_list(i));
    end loop;
end;
```

#### 利用INDEX数组进行批量绑定

- 在使用forALL语句进行批量绑定的DML操作时，可以使用PLS_INTEGER或BINARY_INTEGER的下标集合，该集合的值（数组元素）是这个集合的下标。
- 可以在一个forALL语句中使用values OF子句来处理批量的DML操作

```
create table ins_emp
AS 
select *
from emp
where 1=2;
```

```
create or replace procedure insert_ins_emp
AS
 type emp_tab_type is table OF emp%ROWtype;
 v_emp emp_tab_type;
 
 type values_of_tab_type is table OF PLS_INTEGER
   INDEX by PLS_INTEGER;
 v_number values_of_tab_type;
 
begin
  select *
  BULK colLECT into v_emp
  from emp;
  
  for i IN 1..v_emp.COUNT loop
    v_number(i) := i;
  end loop;
  
  forALL i IN values OF v_number
   insert into ins_emp
   values v_emp(i);
   --values(v_emp(i));
   --21/4     PL/SQL: 
   --20/4     PL/SQL: SQL Statement ignored
end;
```

##### 为什么values子句带括号和不带括号: ORA-00947: 没有足够的值

```
create table test_table1
(
 id number
,name varchar2(20)
);

insert into test_table1
values(101,'一号');
insert into test_table1
values(102,'二号');
insert into test_table1
values(103,'三号');

create table copy_test_table1
AS
select *
from test_table1
where 1=2;
```

- 在`values v_emp(i)`中的是一整行数据，
- 而在`values(v_emp(i))`中的只是作为一列数据，即常规的values子句，
  - 应该要改为像`values (v_table(i).id,v_table(i).name)`的形式

### BULK colLECT into子句

- BULK colLECT into关键字指示SQL引擎在将集合返回给PL/SQL引擎之前批量绑定输出的集合。
  - BULK colLECT （即将数据绑定为一个集合），into子句（将绑定的数据放入一个集合）  
- 可以在select into、FETCH into和RETURNING into子句中使用该语法。（这里没有insert into，功能类似)

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
p_dept_id number
)
is
 type emp_tab_type is table OF copy_emp%ROWtype;
 v_emp_list emp_tab_type;

begin
  select *
  BULK colLECT into v_emp_list
  --将查询的结果放入集合中
  from copy_emp
  where department_id = p_dept_id;
  
  for i IN v_emp_list.FIRST..v_emp_list.LAST loop
    dbms_output.put_line('id:' ||CHR(9)||v_emp_list(i).employee_id ||CHR(10)||
                         'last:'||CHR(9)||v_emp_list(i).last_name||CHR(10)||
                         'dept:'||CHR(9)||v_emp_list(i).department_id||CHR(10)||
                         '******************************************');
  end loop;
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
 p_dept_id number
)
is
 type emp_tab_list_type is table OF copy_emp%ROWtype;
 
 v_emp_list emp_tab_list_type;
 
 CURSOR emp_cursor is
   select *
   from copy_emp
   where department_id = p_dept_id;
begin
  OPEN emp_cursor;
  FETCH emp_cursor BULK colLECT into v_emp_list;
  for i IN v_emp_list.FIRST..v_emp_list.LAST loop
    dbms_output.put_line('id:  ' || v_emp_list(i).employee_id);
  end loop;
  CLOSE emp_cursor;
end;


************

1.
create or replace procedure bulk_get_emps
(
 p_dept_id number
)
is
 CURSOR emp_cursor is
   select *
   from copy_emp
   where department_id = p_dept_id;
 
 v_emp_list copy_emp%ROWtype;
begin
  OPEN emp_cursor;
  FETCH emp_cursor into v_emp_list;
  while emp_cursor%FOUND loop
   dbms_output.put_line(v_emp_list.employee_id);
   FETCH emp_cursor into v_emp_list;
  end loop;
  CLOSE emp_cursor;
end;

2.
create or replace procedure bulk_get_emps2
(
 p_dept_id number
)
is
 CURSOR emp_cursor is
   select *
   from copy_emp
   where department_id = p_dept_id;
begin
  for c IN emp_cursor loop
    dbms_output.put_line('id:  ' || c.employee_id);
  end loop;
end;
```
