# 游标的使用

- 上下文：为了处理sql语句，Oralce必须分配一片私有内存区（上下文）来处理必需的信息，其中包括要处理的行的数目，一个指向语句被分析以后的表示形式的指针以及查询的活动集
- 游标是一个指向上下文的句柄（handle）或指针。通过游标，PL/sql可以控制上下文区和上下文区会发生什么事情。

| sql语句              | 游标      |
| :------------------- | :-------- |
| 非查询语句           | 隐式      |
| 结果是单行的查询语句 | 隐式/显式 |
| 结果是多行的查询语句 | 显式      |

| 游标类型 | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| 隐式游标 | Oracle服务器自动创建和管理，用户不能访问隐式游标。<br />当必须执行一个sql语句时，Oracle服务器自动创建一个隐式的游标 |
| 显式游标 | 用户显式的声明，用户可以访问和控制显式的游标                 |

# 显式游标处理

- 显式游标：命名一个私有的sql区，以此访问该sql语句的存储信息。一个游标的活动集是由游标声明时的select语句决定的。一个多行查询所返回地数据行地集合（全部数据行）被称为活动集。（活动集的大小就是满足查询条件的数据行的个数）显式游标可以一次将满足所有条件的数据全部放入内存中，之后就在内存中一行接一行地处理。

## 定义游标 cursor

1. 可以一行接一行地处理一个查询返回的全部结果（查询语句执行一次）。
2. 一直追踪当前正在处理的数据行。
3. 能够在PL/SQL程序块中显式地手工控制一个或多个游标。

```plsql
-- 定义游标 定义一个游标及其相对应的select语句
cursor 游标名 [(paramenter[,paramenter]……)] is 
    查询语句;
```

```plsql
-- 游标参数只为输入参数
paramenter_name[in] datefile [{:=default}expression]
```

1. 在指定数据类型时，不能使用长度约束。
2. 定义的游标的查询语句中不能有into子句。
3. 显式游标可以是任何有效的select语句（包括连接，子查询等）。

## 打开游标 open

- 打开游标：执行游标所对应的select语句，将其查询结果放入工作区，并且指针指向工作区的首部，标记游标结果集合。如果游标查询语句中带有for update选项，open语句还将锁定数据库表中游标结果集合对应的数据行。
- PL/SQL程序不能用open语句重复打开一个游标。
- 在向游标传递参数时，可以使用与游标参数相同的传值方法（即位置表示法和名称表示法）。

```plsql
open 游标名[([paramenter = ]valuel[,[paramenter = ]value]……)];
```

1. 为一个上下文区域动态地分配内存。
2. 对select语句进行语法分析。
3. 绑定输入变量（通过获取输入变量的内存地址为输入变量设置值）。
4. 标识活动集（生成满足查询条件的数据行的集合）。当执行open语句时，并没有执行提取活动集中的数据行并存入变量的操作，而是由fetch语句完成。
5. 将指针定位在（指向）活动集的第一行。

## 提取游标数据 fetch

- 提取游标数据：检索结果集合中的数据行放入指定的输出变量。fetch中的数据与into的变量应该顺序一一对应，且数据类型兼容。对该记录进行处理；继续处理，直到活动集合中没有记录。fetch会检查游标中是否还包含数据行。如果fetch语句没有提取任何值（数据），则在活动集中没有数据行要处理，但PL/SQL并不报错。

```plsql
fetch 游标名 into {变量1，变量2.... | 记录名};
```

1. 读取当前行的数据并装入PL/SQL的输出变量中。
2. 将游标的指针移向所标识的活动集中的下一行。

## 关闭游标 close

- 当提取和处理完游标结果集合数据后，应及时关闭游标，以释放该游标所占用的系统资源（释放上下文区域，取消活动集的定义），并使该游标的工作区改变或无效，不能再使用fetch语句取其中的数据（否则抛出异常：invalid\_cursor)。
- 关闭后的游标可以使用open语句重新打开。

```plsql
close  cursor_name;
```

```plsql
--打印80号部门的员工的工资
declare
 type emp_record is record(
                           v_sal employees.salary%type,
                           v_id employees.employee_id%type
                           );
 v_emp_record emp_record;
 cursor emp_sal_cursor is select salary,employee_id
                          from employees
                          where department_id = 80;
begin
  open emp_sal_cursor;
  fetch emp_sal_cursor into v_emp_record.v_sal,v_emp_record.v_id;
  while emp_sal_cursor%found loop
    dbms_output.put_line('emp_id:'||v_emp_record.v_id||'salary:'||v_emp_record.v_sal);
    fetch emp_sal_cursor into v_emp_record.v_sal,v_emp_record.v_id;
  end loop;
  close emp_sal_cursor;
end;  
```

```plsql
-- 输出工资最高的前十名员工的信息与排名
declare
 v_emp_id number;
 v_emp_name copy_emp.last_name%type;
 v_dept_id copy_emp.department_id%type;
 v_salary copy_emp.salary%type;
 
 cursor copy_emp_cursor is
        select employee_id
              ,last_name
              ,department_id
              ,salary
        from copy_emp
        ORDER by salary DESC;

 v_i number := 0;
begin
  open copy_emp_cursor;
  
  while copy_emp_cursor%rowcount < 10 loop
    
    fetch copy_emp_cursor into v_emp_id,v_emp_name,v_dept_id,v_salary;
    v_i := v_i + 1;
    dbms_output.put_line('第' || v_i ||'名'|| chr(10)||
                         v_emp_id ||'号员工'|| chr(10) ||
                         '姓名为：'||v_emp_name || chr(10)|| 
                         '部门为：'||v_dept_id || chr(10) ||
                         '工资为：'||v_salary|| chr(10) ||
                         '******************************'
                         );
  end loop;
  close copy_emp_cursor;
end;
```

```plsql
declare
 v_emp_id number;
 v_emp_name copy_emp.last_name%type;
 v_dept_id copy_emp.department_id%type;
 v_salary copy_emp.salary%type;
 v_rn number;
 
 cursor copy_emp_cursor is
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
  open copy_emp_cursor;
  
  while copy_emp_cursor%rowcount < 10 loop
    
    fetch copy_emp_cursor into v_rn,v_emp_id,v_emp_name,v_dept_id,v_salary;
    
    dbms_output.put_line('第' || v_rn || '名' || chr(10) ||
                         v_emp_id ||'号员工'|| chr(10) ||
                         '姓名为：'||v_emp_name || chr(10)|| 
                         '部门为：'||v_dept_id || chr(10) ||
                         '工资为：'||v_salary|| chr(10) ||
                         '******************************'
                         );
  end loop;
  close copy_emp_cursor;
end;
```

```plsql
declare
 v_emp_id number;
 v_emp_name copy_emp.last_name%type;
 v_dept_id copy_emp.department_id%type;
 v_salary copy_emp.salary%type;
 
 cursor copy_emp_cursor is
        select employee_id
              ,last_name
              ,department_id
              ,salary
        from copy_emp
        ORDER by salary DESC;

 v_i number := 0;
begin
  open copy_emp_cursor;
  
  loop
    fetch copy_emp_cursor into v_emp_id,v_emp_name,v_dept_id,v_salary;
    exit when copy_emp_cursor%rowcount > 10 OR copy_emp_cursor%notfound OR copy_emp_cursor%notfound is null; 
    v_i := v_i + 1;
    dbms_output.put_line('第' || v_i ||'名'|| chr(10)||
                         v_emp_id ||'号员工'|| chr(10) ||
                         '姓名为：'||v_emp_name || chr(10)|| 
                         '部门为：'||v_dept_id || chr(10) ||
                         '工资为：'||v_salary|| chr(10) ||
                         '******************************'
                         );
  end loop;
  close copy_emp_cursor;
end;
```

```plsql
declare 
 cursor emp_cursor is
   select *
   from employees;
 
 emp_rec emp_cursor%ROWtype;
 
 type emp_table_type is table of employees%ROWtype
      inDEX by PLS_inTEGER;
 
 v_emp_rec emp_table_type;
 n number(3) := 1;
 
begin
  open emp_cursor;
  
  loop
    fetch emp_cursor into emp_rec;
    
    exit when emp_cursor%notfound OR emp_cursor%notfound is null;
    
    v_emp_rec(n) := emp_rec;
    n:= n + 1;
    
  end loop;
  close emp_cursor;
  
  <<outer_loop>>
  for i in v_emp_rec.FIRST..v_emp_rec.LAST loop
    for j in v_emp_rec.FIRST..v_emp_rec.LAST loop
      if v_emp_rec(i).employee_id = v_emp_rec(j).manager_id then
        dbms_output.put_line(v_emp_rec(i).last_name ||'手底下有人');
        
        continue outer_loop;
      end if;
    end loop;
  end loop outer_loop;
end;
```

# 游标属性

| 游标属性  | 说明（可在前面冠以sql表示隐式游标）                |
| --------- | -------------------------------------------------- |
| %found    | 布尔型属性，当最近一次读记录时成功返回则值为true。 |
| %notfound | 布尔型属性，与%found相反。                         |
| %isopen   | 布尔型属性，当游标打开时返回true。                 |
| %rowcount | 数字型属性，返回已从游标中读取的记录数。           |

| 布尔型属性      | 说明                                      |
| --------------- | ----------------------------------------- |
| null            | 在第一次获取数据之前（执行fetch语句之前） |
| true            | 一条记录成功的被获取到                    |
| false           | 没有记录返回                              |
| invalid\_cursor | 游标没有打开                              |

```plsql
declare
 v_dept_id copy_emp.department_id%type := &p_dept_id;
begin
  update copy_emp
  set salary = 99999
  where department_id = v_dept_id;
  
  dbms_output.put_line(sql%rowcount || '行数据被更新');
end;
```

```plsql
 declare
       --类型定义
       cursor c_job is
       select empno,ename,job,sal
       from emp
       where job='MANAGER';
       --定义一个游标变量
       c_row c_job%rowtype;
begin
       open c_job;
         loop
           --提取一行数据到c_row
           fetch c_job into c_row;
           --判读是否提取到值，没取到值就退出
           --取到值c_job%notfound 是false 
           --取不到值c_job%notfound 是true
           -- null：在第一次获取数据之前（执行fetch语句之前）
           exit when c_job%notfound OR c_job%notfound is null;
            dbms_output.put_line(c_row.empno||'-'||c_row.ename||'-'||c_row.job||'-'||c_row.sal);
         end loop;
       --关闭游标
      close c_job;
end;
```

# 游标的for循环

-  PL/sql语言提供了游标for循环语句，自动执行游标的open、fetch、close语句和循环语句的功能，不需要输入open、fetch、close。

1. 当进入循环时，游标for循环语句自动打开游标，并提取第一行的游标数据。
2. 当程序处理完当前所提取的数据而进行下一次循环时，游标for循环语句自动提取下一行数据供程序处理。
3. 当提取完结果集合中的所有数据后结束循环，并自动关闭游标。

```plsql
for index_variable in 游标名[vlaue[,value]……] loop
          --游标执行代码
end loop;
```

- index\_variable：游标for循环语句隐含声明的索引变量（记录变量），其结构与游标查询语句返回的结构集合的结构相同。在程序中可以通过引用该索引记录变量来读取所提取的游标数据，index\_variable中各元素的名称与游标查询语句选择列表中所制定的列名相同。
- 如果游标查询语句的选择列表中存在计算列，则必须为这些计算列指定别名后，才能通过游标for循环语句中的索引变量来访问这些列数据。

```plsql
--打印出80号部门的所有员工的工资
declare
  cursor v_emp_cursor is select salary,employee_id
                         from employees
                         where department_id = 80;
begin 
  for i in v_emp_cursor loop
    dbms_output.put_line('employee_id:'||RPAD(i.salary,4,'*')||'  salary:'||LPAD(i.salary,7,'0'));
  end loop;
end;
```

```plsql
--利用游标调整公司中员工的工资
  工资范围      调整基数
 0-5000          5%
 5000-10000      3%
 10000-15000     2%
 15000--         1%

-- 1)while
 declare
  cursor v_cursor is select employee_id,salary
                     from employees;
  v_id employees.employee_id%type;
  v_sal employees.salary%type;
  v_temp number(10,5);
begin
  open v_cursor;
  fetch v_cursor into v_emp_record.v_id,v_emp_record.v_sal;
  while v_cursor %found loop
    if v_sal < 5000 then v_temp := 0.05;
    elsif v_sal <10000 then v_temp := 0.03;
    elsif v_sal <15000 then v_temp := 0.02;
    else v_temp := 0.01;
    end if;
  update employees
  set salary = salary * (1 + v_temp)
  where emplkoyee_id  = v_id;
  end loop;
  fetch v_cursor into v_id,v_sal;
end;

-- 2)DEOCDE函数
update employees
set salary = salary * (1 + DECODE(TRUNC(salary / 5000),0,0.05,
                                                       1,0.03,
                                                       2,0.02,
                                                       0.01));
-- 3)for循环
declare 
  cursor emp_sal_cursor is select employee_id,salary
                           from employees;
  v_temp number(4,2);
begin
  for i in emp_sal_cursor loop
    if i.salary < 5000 then v_temp := 0.05;
    elsif i.salary < 10000 then v_temp := 0.03;
    elsif i.salary < 15000 then v_temp := 0.02;
    else v_temp := 0.01;
    end if;
    update employees
    set salary = salary * (1 + v_temp)
    where employee_id = i.employee_id;
  end loop;
end;
```

# 游标子查询

## 在游标的for循环中使用子查询

- 如果使用子查询的游标for循环，不需要在声明段中声明游标，但是必须提供一个在循环体中本身可以确定活动集的select语句，用来定义游标，且如果在游标的for循环使用子查询，则不能显式的调用游标的属性，因为该游标没有被声明。

```plsql
begin
  for emp_rec in (select * from employees) loop
    if emp_rec.department_id = 20 then 
      dbms_output.put_line('20号部门: ' || emp_rec.employee_id);
    end if;
  end loop;
end;

/*
***************
20号部门: 201
20号部门: 202
PL/sql procedure successfully completed
*/
```

## 在游标的定义中使用子查询

- 在游标的定义中使用子查询，此时在子查询的每一个函数或表达式都必须有别名，用来映射成临时表。（函数和表达式不能作为列名）

```plsql
declare
 cursor dept_total_cursor is 
        select DisTinCT e.department_id
                       ,d.department_name
                       ,max_sal
                       ,avg_sal
                       ,count_num
        from departments d
            ,employees e
            ,(select MAX(salary) max_sal
                    ,avg(salary) avg_sal
                    ,COUNT(*) count_num
                    ,department_id
              from employees
              group by department_id
              ) t
        where e.department_id = d.department_id
          and e.department_id = t.department_id;
begin
  for dept_rec in dept_total_cursor loop
    dbms_output.put_line('部门号: '||dept_rec.department_id || chr(10)||
                        '部门名称: '||dept_rec.department_name||chr(10)||
                        '人数: '||dept_rec.count_num ||chr(10)||
                        '最高工资: '||dept_rec.max_sal||chr(10)||
                        '平均工资: '||dept_rec.avg_sal);
    dbms_output.put_line('**********************');
    DBMS_OUTPUT.ENABLE (buffer_size=>null);
  end loop;
end;
```

# 带参数的游标

- PL/SQL程序块中可以多次打开一个已经关闭的显式游标，而且每次可以返回不同的动态集。而每次执行时，关闭之前的游标并以新的一组参数重新打开。

1. 对于在游标中定义的每一个形参，在open语句中必须有一个对应的实参。

2. 参数的数据类型与标量类型的变量相同。

3. 只定义数据类型，而不定义长度。

```plsql
declare
    cursor emp_sal_cursor(dept_id number, sal number) is 
           select salary + 1000 sal, employee_id id 
           from employees 
           where department_id = dept_id and salary > sal;
    temp number(4, 2);
begin
          dbms_output.put_line('工资'|| chr(9) || 'id' || chr(9) ||'temp');    
          for c in emp_sal_cursor(sal => 4000, dept_id => 80) loop
          --dbms_output.put_line(c.id || ': ' || c.sal);
          if c.sal <= 5000 then
             temp := 0.05;
          elsif c.sal <= 10000 then
             temp := 0.03;   
          elsif c.sal <= 15000 then
             temp := 0.02;
          else
             temp := 0.01;
          end if;      

          dbms_output.put_line(c.sal || chr(9) || c.id || chr(9) || temp);
          --update employees set salary = salary * (1 + temp) where employee_id = c.id;
    end loop;
end;
```

```plsql
declare 
 cursor dept_avg_sal_cursor(p_dept_id number) is
    select department_id id, avg(salary) avg_sal
    from employees
    group by department_id
    having department_id = p_dept_id;
    
    v_dept_id number := &p_id;
begin 
  for dept_rec in dept_avg_sal_cursor(v_dept_id) loop
    dbms_output.put_line('部门号：' || dept_rec.id ||chr(9)||
                         '平均工资: '|| dept_rec.avg_sal);
  end loop;
end;
```

# 隐式游标

- 当必须执行一个sql语句时，Oracle服务器自动创建一个隐式的游标

```plsql
--更新指定员工 salary(涨工资 10)，如果该员工没有找到，则打印”查无此人” 信息
begin
         update employees 
         set salary = salary + 10 
         where employee_id = 1005;    
         if sql%notfound then
            dbms_output.put_line('查无此人!');
         end if;
end;
```

# for update

- for update 子句必须是select语句的最后一个子句，在查询多个表时，可以使用for update子句限制锁定特定表中的数据行（open时锁住），避免其他用户或进程的DML操作的冲突，在提取数据时，将所操作的数据行全部锁住。

```plsql
select ...
from ...
for update [of 列名][NOWAIT | WAIT n];

--[of 列名] 只锁住包含该列的表中的数据行
--[NOWAIT | WAIT n] 访问的数据行被其他会话锁住则返回一个错误。n为等待n秒
```

```plsql
declare
 cursor copy_emp_cursor is
        select employee_id
              ,last_name
              ,salary
        from copy_emp
        where department_id = 20
        for update of salary nowait;
begin
  open copy_emp_cursor;
end;
```

# where current of

- where current of 子句：引用显式游标的当前行来标识要修改的数据行。与for update子句一起使用来引用显式游标的当前行，在update和delete语句中使用where current of子句，而在游标声明时声明for update子句。必须在游标的查询语句中包含for update子句, 以便在打开这个游标时锁住访问的数据行。

```
where current of 游标;
```

```
declare
 cursor emp_cursor is
   select *
   from copy_emp
   where department_id = 20
   for update of salary NOWAIT;
begin
  for emp_rec in emp_cursor loop
    update copy_emp
    set salary = emp_rec.salary * 0.15
    where current of emp_cursor;
  end loop;
end; 
```

