# PL/SQL

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

- 但有一个要返回多行数据的select语句时，可以在PL/SQL程序中声明一个显式的游标，来一行一行地处理这select语句所返回地所有数据行。
- 一个多行查询所返回地数据行地集合（全部数据行）被称为活动集。（活动集的大小就是满足查询条件的数据行的个数）
- 一个游标的活动集是由游标声明时的select语句决定的。

- 显示游标功能：
   -  虽然使用select into语句已经可以将数据库表中的数据存入PL/SQL变量中，但是有时满足查询条件的数据行可能很多，这就使得程序的逻辑条件比较复杂，而且使用循环语句每循环一次将一行数据存入相应的PL/SQL变量中的方法存在着效率方面的问题，因为每次执行语句时，PL/SQL必须访问数据库中的表，而表是存放在硬盘上的。实验数据表明，硬盘的数据访问速度比内存慢1000~100000倍。而使用显式cursor就可以一次将满足所有条件的数据全部放入内存中，之后就在内存中一行接一行地处理了，比之前更快。
       - 可以一行接一行地处理一个查询返回的全部结果（查询语句执行一次）。
       - 一直追踪当前正在处理的数据行。
       - 能够使程序员在PL/SQL程序块中显式地手工控制一个或多个cursors。.

**1)定义游标 定义一个游标及其相对应的select语句**

- 格式 

```plsql
CURSOR 游标名 [(paramenter[,paramenter]……)] is 
    查询语句;
```

- 游标参数只为输入参数，格式为：

```plsql
paramenter_name[IN] datefile [{:=default}expression]
```
   - 在指定数据类型时，不能使用长度约束

-  定义的游标的查询语句中不能有into子句
- 显式游标可以是任何有效的select语句，包括连接，子查询等。

**2)打开游标 就是执行游标所对应的select语句，将其查询结果放入工作区，并且指针指向工作区的首部，标记游标结果集合**

- 如果游标查询语句中带有for UPDATE选项，OPEN语句还将锁定数据库表中游标结果集合对应的数据行
- 格式 

```plsql
OPEN 游标名[([paramenter = ]valuel[,[paramenter = ]value]……)];
```

1. 为一个上下文区域动态地分配内存
2. 对select语句进行语法分析
3. 绑定输入变量（通过获取输入变量的内存地址为输入变量设置值）
4. 标识活动集（生成满足查询条件的数据行的集合）
   - 当执行OPEN语句是，并没有执行提取活动集中的数据行并存入变量的操作，而是由FETCH语句完成）
5. 将指针定位在（指向）活动集的第一行

- 在向游标传递参数时，可以使用与游标参数相同的传值方法(即位置表示法和名称表示法)
- PL/SQL程序不能用OPEN语句重复打开一个游标

**3)提取游标数据 就是检索结果集合中的数据行放入指定的输出变量**

- 格式 

```plsql
FETCH 游标名 into {变量1，变量2.... | 记录名};
```

- FETCH中的数据与into的变量应该顺序一一对应，且数据类型兼容。
- 对该记录进行处理；继续处理，直到活动集合中没有记录

1. 读取当前行的数据并装入PL/SQL的输出变量中
2. 将游标的指针移向所标识的活动集中的下一行

- 检查游标中是否还包含数据行。如果FETCH语句没有提取任何值（数据），在活动集中没有数据行要处理，PL/SQL并不报错。

**4)关闭游标**

- 当提取和处理完游标结果集合数据后，应及时关闭游标，以释放该游标所占用的系统资源(释放上下文区域，取消活动集的定义)，并使该游标的工作区改变或无效，不能再使用FETCH语句取其中的数据（否则抛出异常：INVALID_CURSOR)。
- 关闭后的游标可以使用OPEN语句重新打开。
- 格式 

```plsql
CLOSE  cursor_name;
```

**应用**

```plsql
--打印80号部门的员工的工资
declare
 type emp_record is record(
                           v_sal employees.salary%type,
                           v_id employees.employee_id%type
                           );
 v_emp_record emp_record;
 CURSOR emp_sal_cursor is select salary,employee_id
                          from employees
                          where department_id = 80;
begin
  OPEN emp_sal_cursor;
  FETCH emp_sal_cursor into v_emp_record.v_sal,v_emp_record.v_id;
  while emp_sal_cursor%FOUND loop
    dbms_output.put_line('emp_id:'||v_emp_record.v_id||'salary:'||v_emp_record.v_sal);
    FETCH emp_sal_cursor into v_emp_record.v_sal,v_emp_record.v_id;
  end loop;
  CLOSE emp_sal_cursor;
end;  
```

```plsql
输出工资最高的前十名员工的信息与排名
declare
 v_emp_id number;
 v_emp_name copy_emp.last_name%type;
 v_dept_id copy_emp.department_id%type;
 v_salary copy_emp.salary%type;
 
 CURSOR copy_emp_cursor is
        select employee_id
              ,last_name
              ,department_id
              ,salary
        from copy_emp
        ORDER by salary DESC;

 v_i number := 0;
begin
  OPEN copy_emp_cursor;
  
  while copy_emp_cursor%ROWCOUNT < 10 loop
    
    FETCH copy_emp_cursor into v_emp_id,v_emp_name,v_dept_id,v_salary;
    v_i := v_i + 1;
    dbms_output.put_line('第' || v_i ||'名'|| CHR(10)||
                         v_emp_id ||'号员工'|| CHR(10) ||
                         '姓名为：'||v_emp_name || CHR(10)|| 
                         '部门为：'||v_dept_id || CHR(10) ||
                         '工资为：'||v_salary|| CHR(10) ||
                         '******************************'
                         );
  end loop;
  CLOSE copy_emp_cursor;
end;
```

```plsql
declare
 v_emp_id number;
 v_emp_name copy_emp.last_name%type;
 v_dept_id copy_emp.department_id%type;
 v_salary copy_emp.salary%type;
 v_rn number;
 
 CURSOR copy_emp_cursor is
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
  OPEN copy_emp_cursor;
  
  while copy_emp_cursor%ROWCOUNT < 10 loop
    
    FETCH copy_emp_cursor into v_rn,v_emp_id,v_emp_name,v_dept_id,v_salary;
    
    dbms_output.put_line('第' || v_rn || '名' || CHR(10) ||
                         v_emp_id ||'号员工'|| CHR(10) ||
                         '姓名为：'||v_emp_name || CHR(10)|| 
                         '部门为：'||v_dept_id || CHR(10) ||
                         '工资为：'||v_salary|| CHR(10) ||
                         '******************************'
                         );
  end loop;
  CLOSE copy_emp_cursor;
end;
```

```plsql
declare
 v_emp_id number;
 v_emp_name copy_emp.last_name%type;
 v_dept_id copy_emp.department_id%type;
 v_salary copy_emp.salary%type;
 
 CURSOR copy_emp_cursor is
        select employee_id
              ,last_name
              ,department_id
              ,salary
        from copy_emp
        ORDER by salary DESC;

 v_i number := 0;
begin
  OPEN copy_emp_cursor;
  
  loop
    FETCH copy_emp_cursor into v_emp_id,v_emp_name,v_dept_id,v_salary;
    exit when copy_emp_cursor%ROWCOUNT > 10 OR copy_emp_cursor%NOTFOUND OR copy_emp_cursor%NOTFOUND is null; 
    v_i := v_i + 1;
    dbms_output.put_line('第' || v_i ||'名'|| CHR(10)||
                         v_emp_id ||'号员工'|| CHR(10) ||
                         '姓名为：'||v_emp_name || CHR(10)|| 
                         '部门为：'||v_dept_id || CHR(10) ||
                         '工资为：'||v_salary|| CHR(10) ||
                         '******************************'
                         );
  end loop;
  CLOSE copy_emp_cursor;
end;
```

```plsql
declare 
 CURSOR emp_cursor is
   select *
   from employees;
 
 emp_rec emp_cursor%ROWtype;
 
 type emp_table_type is table OF employees%ROWtype
      INDEX by PLS_INTEGER;
 
 v_emp_rec emp_table_type;
 n number(3) := 1;
 
begin
  OPEN emp_cursor;
  
  loop
    FETCH emp_cursor into emp_rec;
    
    exit when emp_cursor%NOTFOUND OR emp_cursor%NOTFOUND is null;
    
    v_emp_rec(n) := emp_rec;
    n:= n + 1;
    
  end loop;
  CLOSE emp_cursor;
  
  <<outer_loop>>
  for i IN v_emp_rec.FIRST..v_emp_rec.LAST loop
    for j IN v_emp_rec.FIRST..v_emp_rec.LAST loop
      if v_emp_rec(i).employee_id = v_emp_rec(j).manager_id then
        dbms_output.put_line(v_emp_rec(i).last_name ||'手底下有人');
        
        continue outer_loop;
      end if;
    end loop;
  end loop outer_loop;
end;
```

### 游标属性

- %FOUND 布尔型属性 当最近一次读记录时成功返回则值为true
   - null：在第一次获取数据之前（执行FETCH语句之前）
   - true：一条记录成功的被获取到
   - FALSE：没有记录返回
   - INVALID_CURSOR：游标没有打开
- %NOTFOUND 布尔型属性 与%FOUND相反
- %isOPEN 布尔型属性 当游标打开时返回true
- %ROWCOUNT 数字型属性 返回已从游标中读取的记录数

-注：可在前面冠以SQL表示隐式游标
   - 如： SQL%FOUND

```plsql
declare
 v_dept_id copy_emp.department_id%type := &p_dept_id;
begin
  UPDATE copy_emp
  set salary = 99999
  where department_id = v_dept_id;
  
  dbms_output.put_line(SQL%ROWCOUNT || '行数据被更新');
end;

*****
1行数据被更新
```

```plsql
 declare
       --类型定义
       CURSOR c_job is
       select empno,ename,job,sal
       from emp
       where job='MANAGER';
       --定义一个游标变量
       c_row c_job%rowtype;
begin
       OPEN c_job;
         loop
           --提取一行数据到c_row
           FETCH c_job into c_row;
           --判读是否提取到值，没取到值就退出
           --取到值c_job%notfound 是false 
           --取不到值c_job%notfound 是true
           -- null：在第一次获取数据之前（执行FETCH语句之前）
           exit when c_job%notfound OR c_job%NOTFOUND is null;
            dbms_output.put_line(c_row.empno||'-'||c_row.ename||'-'||c_row.job||'-'||c_row.sal);
         end loop;
       --关闭游标
      CLOSE c_job;
end;
```

### 游标的for循环

-  PL/SQL语言提供了游标for循环语句，自动执行游标的OPEN、FETCH、CLOSE语句和循环语句的功能；
    - 不需要输入OPEN,FETCH,CLOSE；
1. 当进入循环时，游标for循环语句自动打开游标，并提取第一行的游标数据；
2. 当程序处理完当前所提取的数据而进行下一次循环时，游标for循环语句自动提取下一行数据供程序处理
3. 当提取完结果集合中的所有数据后结束循环，并自动关闭游标。

- 格式

```plsql
for index_variable IN 游标名[vlaue[,value]……] loop
          --游标执行代码
end loop;
```

- 其中，index_variable为游标for循环语句隐含声明的索引变量，该变量为**记录变量**，其结构与游标查询语句返回的结构集合的结构相同。
   - 在程序中可以通过引用该索引记录变量来读取所提取的游标数据，
   - index_variable中各元素的名称与游标查询语句选择列表中所制定的列名相同
- 如果再游标查询语句的选择列表中存在计算列，则必须为这些计算列指定别名后才能通过游标for循环语句中的索引变量来访问这些列数据。

```plsql
--打印出80号部门的所有员工的工资
declare
  CURSOR v_emp_cursor is select salary,employee_id
                         from employees
                         where department_id = 80;
begin 
  for i IN v_emp_cursor loop
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
  CURSOR v_cursor is select employee_id,salary
                     from employees;
  v_id employees.employee_id%type;
  v_sal employees.salary%type;
  v_temp number(10,5);
begin
  OPEN v_cursor;
  FETCH v_cursor into v_emp_record.v_id,v_emp_record.v_sal;
  while v_cursor %FOUND loop
    if v_sal < 5000 then v_temp := 0.05;
    elsif v_sal <10000 then v_temp := 0.03;
    elsif v_sal <15000 then v_temp := 0.02;
    else v_temp := 0.01;
    end if;
  UPDATE employees
  set salary = salary * (1 + v_temp)
  where emplkoyee_id  = v_id;
  end loop;
  FETCH v_cursor into v_id,v_sal;
end;

-- 2)DEOCDE函数
UPDATE employees
set salary = salary * (1 + DECODE(TRUNC(salary / 5000),0,0.05,
                                                       1,0.03,
                                                       2,0.02,
                                                       0.01));
-- 3)for循环
declare 
  CURSOR emp_sal_cursor is select employee_id,salary
                           from employees;
  v_temp number(4,2);
begin
  for i IN emp_sal_cursor loop
    if i.salary < 5000 then v_temp := 0.05;
    elsif i.salary < 10000 then v_temp := 0.03;
    elsif i.salary < 15000 then v_temp := 0.02;
    else v_temp := 0.01;
    end if;
    UPDATE employees
    set salary = salary * (1 + v_temp)
    where employee_id = i.employee_id;
  end loop;
end;
```
### 游标使用子查询

#### 在游标的for循环中使用子查询

- 如果使用子查询的游标for循环，不需要在声明段中声明游标，但是必须提供一个在循环体中本身可以确定活动集的select语句，用来定义游标。
- 且如果在游标的for循环使用子查询，则不能显式的调用游标的属性，因为该游标没有被声明。

```plsql
begin
  for emp_rec IN (select * from employees) loop
    if emp_rec.department_id = 20 then 
      dbms_output.put_line('20号部门: ' || emp_rec.employee_id);
    end if;
  end loop;
end;

/*
***************
20号部门: 201
20号部门: 202
PL/SQL procedure successfully completed
*/
```

#### 在游标的定义中使用子查询

- 此时在子查询的每一个函数或表达式都必须有别名，用来映射成临时表
    - 函数和表达式不能作为列名

```plsql
declare
 CURSOR dept_total_cursor is 
        select DisTINCT e.department_id
                       ,d.department_name
                       ,max_sal
                       ,avg_sal
                       ,count_num
        from departments d
            ,employees e
            ,(select MAX(salary) max_sal
                    ,AVG(salary) avg_sal
                    ,COUNT(*) count_num
                    ,department_id
              from employees
              GROUP by department_id
              ) t
        where e.department_id = d.department_id
          and e.department_id = t.department_id;
begin
  for dept_rec IN dept_total_cursor loop
    dbms_output.put_line('部门号: '||dept_rec.department_id || CHR(10)||
                        '部门名称: '||dept_rec.department_name||CHR(10)||
                        '人数: '||dept_rec.count_num ||CHR(10)||
                        '最高工资: '||dept_rec.max_sal||CHR(10)||
                        '平均工资: '||dept_rec.avg_sal);
    dbms_output.put_line('**********************');
    DBMS_OUTPUT.ENABLE (buffer_size=>null);
  end loop;
end;
```

### 带参数的游标
- 可以在PL/SQL程序块中多次打开一个已经关闭的显式游标，而且每次可以返回不同的动态集。而每次执行时，关闭之前的游标并以新的一组参数重新打开。
- 对于在游标中定义的每一个形参，在OPEN语句中必须有一个对应的实参。
- 参数的数据类型与标量类型的变量相同。
- 只定义数据类型，而不定义长度。

```plsql
declare
    CURSOR emp_sal_cursor(dept_id number, sal number) is 
           select salary + 1000 sal, employee_id id 
           from employees 
           where department_id = dept_id and salary > sal;
    temp number(4, 2);
begin
          dbms_output.put_line('工资'|| CHR(9) || 'id' || CHR(9) ||'temp');    
          for c IN emp_sal_cursor(sal => 4000, dept_id => 80) loop
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

          dbms_output.put_line(c.sal || CHR(9) || c.id || CHR(9) || temp);
          --UPDATE employees set salary = salary * (1 + temp) where employee_id = c.id;
    end loop;
end;
```

```plsql
declare 
 CURSOR dept_avg_sal_cursor(p_dept_id number) is
    select department_id id
          ,AVG(salary) avg_sal
    from employees
    GROUP by department_id
    HAVING department_id = p_dept_id;
    
    v_dept_id number := &p_id;
begin 
  for dept_rec IN dept_avg_sal_cursor(v_dept_id) loop
    dbms_output.put_line('部门号：' || dept_rec.id ||CHR(9)||
                         '平均工资: '|| dept_rec.avg_sal);
  end loop;
end;
```

### 隐式游标

- 当必须执行一个SQL语句时，Oracle服务器自动创建一个隐式的游标

```plsql
--更新指定员工 salary(涨工资 10)，如果该员工没有找到，则打印”查无此人” 信息
begin
         UPDATE employees 
         set salary = salary + 10 
         where employee_id = 1005;    
         if SQL%NOTFOUND then
            dbms_output.put_line('查无此人!');
         end if;
end;
```

### for UPDATE子句

- for UPDATE 子句必须是select语句的最后一个子句。
- 在查询多个表时，可以使用for UPDATE子句限制锁定特定表中的数据行(在OPEN时锁住)
   - 避免其他用户或进程的DML操作的冲突，在提取数据时，将所操作的数据行全部锁住。
   
- 语法

```plsql
select ...
from ...
for UPDATE [OF 列名][NOWAIT | WAIT n];

--[OF 列名] 只锁住包含该列的表中的数据行
--[NOWAIT | WAIT n] 访问的数据行被其他会话锁住则返回一个错误。n为等待n秒
```

```plsql
declare
 CURSOR copy_emp_cursor is
        select employee_id
              ,last_name
              ,salary
        from copy_emp
        where department_id = 20
        for UPDATE OF salary NOWAIT;
begin
  OPEN copy_emp_cursor;
end;
```

### where CURRENT OF 子句

- 可以通过where CURRENT OF 子句引用显式游标的当前行来标识要修改的数据行，
- 要引用显式游标的当前行，where CURRENT OF子句需要与for UPDATE子句一起使用
- 在UPDATE和DELETE语句中使用where CURRENT OF子句，而在游标声明时说明for UPDATE子句。
- 必须在游标的查询语句中包含for UPDATE子句, 以便在打开这个游标时锁住访问的数据行

```
where CURRENT OF 游标;
```

```
declare
 CURSOR emp_cursor is
   select *
   from copy_emp
   where department_id = 20
   for UPDATE OF salary NOWAIT;
begin
  for emp_rec IN emp_cursor loop
    UPDATE copy_emp
    set salary = emp_rec.salary * 0.15
    where CURRENT OF emp_cursor;
  end loop;
end; 
```
***

## 触发器TRIGGER

- 触发器 触发器是一个特殊的存储过程。是一个与表相关联的、存储的PL/SQL程序。
- 在Oracle系统上，触发器类似过程和函数，都有声明、执行、异常处理过程PL/SQL块
- 每当一个特定的数据操作语句DML(insert、UPDATE、DELETE,注意没有select)在指定的表上发出时,oracle自动地执行触发器中定义的语句序列。

 **触发器在数据库里以独立的对象存储，它与存储过程不同的是：**

|                                                      触发器TRIGGER                                                       |             存储过程procedure             |
| :---------------------------------------------------------------------------------------------------------------------: | :--------------------------------------: |
| 触发器是由一个事件(DML语句)来启动运行，即触发器是当某个事件发生时自动的隐式运行,并且触发器不能接受参数，所以运行触发器叫触发或点火 | 存储过程通过其它程序来启动运行或直接启动运行 |
|                                            源代码包含在USER TRIGGERS数据字典中                                            |     源代码包含在USER_SOURCE数据字典中      |
|                                          不允许使用commit、SAVEPOINT和rollback                                           |    允许使用commit、SAVEPOINT和rollback    |

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

### 一般语法

```plsql
create or replace TRIGGER 触发器名
BEforE|AFTER|INSTEAD OF
insert|DELETE|UPDATE [OF colUMN[列名]]
on [模式（用户名）.]表/视图
[REFERENCING OLD AS 指定引用OLD的别名 | NEW AS 指定引用NEW的别名]
[for EACH ROW] --行级触发器
[when 条件表达式] --只用于行级触发器
触发器体; --PL/SQL块等
```

**触发器组成**

- **触发事件** 即在何种情况下触发TRIGGER
  - 在一个表或视图上的，insert/UPDATE/DELETE语句
  - 在任何用户上的，create/ALTER/DROP语句
  - 一个数据库启动或实例关闭
  - 一个用户的登录或退出
  - 一个特定的错误信息或任何错误信息
- **触发时间** 
  - 即触发事件和该TRIGGER的操作顺序
  - BEforE 在触发事件(执行)之前执行触发器
     - 决定所触发的语句是否应该允许被完成
     - 在完成一个insert或UPDATE语句之前导出原来列的值
     - 初始化全局变量，验证一些复杂的业务规则 
  - AFTER 在触发事件之后执行触发器
    - 在执行触发器之前先完成触发的语句 
    - 在已经存在BEforE触发器时，要在相同的触发语句上执行不同的操作  
    - 如果触发语句完成后没有commit，则其他用户看见的还是没有执行触发器的结果
  - INSTEAD OF 替换触发事件的语句来执行触发器。
     - 被用于用其他方法不能修改的 **视图** （当不能通过SQL的DML语句直接修改视图时，可以在INSTEAD OF触发器的体中写DML语句在这个视图的基表上直接执行）
- **触发器本身** 即该TRIGGER被触发之后的目的和意图，正是触发器本身要做的事情，
  - PL/SQL块
  - CALL调用子句 `CALL 存储过程/函数等`
- **触发频率** 说明触发器内定义的动作被执行的次数,即语句级(STATEMENT)触发器和行级(ROW)触发器
  - **语句级触发器** 是指当某触发事件发生时该触发器只执行了一次
  - **行级触发器** 是指当某触发事件发生时，对受到该操作影响的每一行数据，触发器都执行一次
- **触发器类型**

| 语句级触发器    |  行级触发器   |
| :-: | :-: |
|  创建触发器的默认类型   |   创建触发器时使用for EACH ROW子句指定为行级触发器  |
|  对于触发的事件只触发一次   |  对受触发事件影响的每行触发一次    |
| 没有行受影响时也触发一次    |  触发事件未影响任何数据行就不触发   |

```pslql
create or replace TRIGGER menu_test_trigger
BEforE 
DELETE on menu
for EACH ROW
begin
  dbms_output.put_line('good');
end;
```

```plsql
create or replace TRIGGER secure_emp_trigger
BEforE 
insert on copy_emp
begin
  if(TO_char(sysdate,'DY') IN ('SAT','SUN')) 
   OR (TO_char(sysdate,'HH24:MI') NOT between '08:00' and '18:00') then
    RAisE_APPLICATIon_ERROR(-20038,'非工作时间，拒绝输入数据');
  end if;
end;  
```

### 触发器执行模型概要及实现完整性约束的准备

**一个单一的DML语句有可能触发以下四种触发器：**

1. BEforE语句触发器
2. AFTER语句触发器
3. BEforE行触发器
4. AFTER行触发器

- 在触发器中的触发事件或语句可能引起一个或多个完整性约束的检查，但是可以将约束的检查延迟到执行commit操作时。
- Oracle服务器触发这四种触发器的方式如下。

1. 执行所有的BEforE STATEMENT(语句)触发器。
2. 对受影响的每一行循环：
   - 执行所有的BEforE ROW(行)触发器。
   - 执行所有的DML语句并进行完整性约束的检查。
   - 执行所有的AFTER ROW(行)触发器。
3. 执行所有的AFTER STATEMENT(语句)触发器。

- 触发器也可能引起其他触发器的触发（执行），即所谓的级联触发器。作为一个SQL语句的结果，所有执行的操作和检查必须成功。如果在一个触发器中抛出了一个异常并且这个异常没有被显式地处理，那么所执行地所有操作（包括触发语句）全部被回滚。保证触发器不违反完整性约束

**引用完整性**

1. 外键必须为空值（null）或者有相匹配地项
2. 外键可以没有相对应的键属性，但不可以有无效的项

- 虽然引用完整性可以有效地防止错误的DML语句，但在特殊情况下，可能会短时间地出现违法引用完整性地数据。可以创建一个AFTER UPDATE触发器来解决。

##### 利用触发器实现完整性约束

```plsql
--departments中的主键department_id是employees中的外键。

create or replace TRIGGER emp_dept_fk_trg
AFTER UPDATE OF department_id on employees
for EACH ROW
  begin
    insert into departments(department_id,department_name)
    values(:NEW.department_id,'新的部门:'||TO_char(:NEW.department_id));
  exception
    when DUP_VAL_on_INDEX then
      null;  --如果存在该异常，则不进行任何操作.
             --并避免整个程序的中断。屏蔽该异常
  end;
```

### 行级触发器

- 如果在较大的表上执行许多修改，行触发器可能降低系统的效率。

#### 条件谓词 insertING/DELETING/UPDATING

- 如果有多种类型的DML操作可以触发一个触发器，那么在触发器体中可以使用条件谓词来检查触发器的语句类型，从而根据相应的语句来进行相应的操作。

**条件谓词**

- inserting
- deleting
- updating
- `updating(列名)` 那一列被UPDATE，则返回true

```plsql
create or replace TRIGGER secure_emp_trigger
BEforE 
insert OR DELETE OR UPDATE on copy_emp
for EACH ROW
begin
  if insertING then
    RAisE_APPLICATIon_ERROR(-20001,'禁止插入数据');
  elsif DELETING then
    insert into user_test_table(user_name,old_id,new_id)
    values (user,:OLD.employee_id,:NEW.employee_id);
  elsif UPDATING then
    insert into user_test_table(user_name,old_id,new_id)
    values (user,:OLD.employee_id,:NEW.employee_id);
  end if;
end;
```

#### 限定符 :new 和 :old

- 当一个行级触发器触发时，PL/SQL运行时的引整创建和维护两个数据结构，它们就是OLD和NEW，OLD和NEW具有完全相同的记录结构，并且使用 **%ROWtype** 声明成与触发器所基于的表的数据类型一模一样。
- OLD存储触发器处理之前的记录的原始值，而NEW则包含了新值。

| 数据操作 |            OLD             |            NEW            |
| :------: | :------------------------: | :-----------------------: |
|  insert  | （插入之前的值）空值（null） |          插入的值          |
|  UPDATE  |        修改之前的值         |        修改之后的值        |
|  DELETE  |        删除之前的值         | 空值（null)（删除之后的值） |

**注意：**

- 只在行触发器中有OLD和NEW限定词。
- 在每个SQL和PL/SQL语句中，这两个限定词前必须冠以冒号(：)（`:NEW`和`:OLD`）。
    - 如果这两个限定词是在when所在条件中引用则不能冠以冒号。否则ORA-25000: 在触发器 when 子句中, 连接变量的使用无效

```
create or replace TRIGGER update_emp_trigger
AFTER 
 UPDATE on employees
for EACH ROW
begin
  dbms_output.put_line('old_sal'||:OLD.salary||'new_sal'||:NEW.salary);
end;
```

```
create or replace TRIGGER audit_emp_values
BEforE insert OR DELETE OR UPDATE on copy_emp
for EACH ROW
  begin
    insert into user_test_table(user_name,old_id,new_id)
    values (USER,:OLD.employee_id,:NEW.employee_id);
  end;
```

##### AFTER

- 在AFTER类型中，无法更改此触发器类型的 new值。

```
create or replace TRIGGER test_trigger
AFTER insert on user_test_table
for EACH ROW
  begin
    :NEW.new_id := 1;
  end;

ORA-04084: 无法更改此触发器类型的 NEW 值
```

#### when 条件行触发器

- 可以通过在触发器的定义中增加一个when子句，并在这个子句中说明一个布尔表达式的方法来限制一个触发器的操作。
- 如果在触发器中包括了个when子句，那么WHN子句中的表达式要评估（测试)该触发器所影响的每一行。
   - 对于每一行，如果这个表达式的评估结果是true，那么触发器体（PL/SQL程序代码）将执行。如果表达式对于某一行的测试结果是FALSE或NUL，,那么触发器体就不执行。
- when子句的评估并不影响触发的SQL语句的执行，即如果一个when子句测试为FALSE，触发语句并不回滚。
- when子句是不能包括在语句触发器的定义中的，这个子句只能包括在行触发器的定义中。
- **限定符 :NEW和:OLD在when子句中不能带冒号**
   - ORA-25000: 在触发器 when 子句中, 连接变量的使用无效
   
```plsql
create or replace TRIGGER set_com_pct
BEforE insert OR UPDATE on copy_emp
for EACH ROW
when (NEW.job_id = 'IT')
  begin
    if insertING then
      :NEW.commission_pct := 0;
    else
      if :OLD.commission_pct = 0 
       OR :OLD.commission_pct is null then
        :NEW.commission_pct := 0.3;
      else 
        :NEW.commission_pct := :OLD.commission_pct;
      end if;
    end if;
  end; 
```

#### 替代触发器 INSTEAD OF

- Oracle服务器触发该触发器并替代执行所触发的语句
- INSTEAD OF触发器被用于直接在视图所基于的表上执行insert/UPDATE/DELETE语句。从而修改视图
  - INSTEAD OF触发器以不可见的方式工作 并在后台执行相应的正确操作。
  - 如果一个视图本身是可以修改的并且上面有INSTEAD OF触发器，那么触发器优先
- INSTEAD OF 触发器是行级触发器，对视图执行插入和修改操作是，该视图的CHECK选项不起作用。
  - 此时在触发器体中必须实现这样的检查操作。
  
##### 例

```
create or replace VIEW copy_emp_detaikls
AS
select *
from copy_emp;
```
```
create or replace TRIGGER new_emp_details
INSTEAD OF insert OR UPDATE OR DELETE on copy_emp_details
for EACH ROW
  begin
    if insertING then
    --直接往copy_emp表中插入数据
      insert into copy_emp(employee_id,last_name,salary,department_id)
      values(:NEW.employee_id,:NEW.last_name,:NEW.salary,:NEW.department_id);
    elsif DELETING then
    --直接删除copy_emp表中的数据
      DELETE from copy_emp
      where employee_id = :OLD.employee_id;
    elsif UPDATING('salary') then
      UPDATE copy_emp
      set salary = :NEW.salary
      where employee_id = :OLD.employee_id;
    elsif UPDATING('department_id') then
      UPDATE copy_emp
      set department_id = :NEW.department_id
      where employee_id = :OLD.employee_id;
    end if;
  end;
```

#### 在变异表上触发器的限制

**变异表**

目前正在由UPDATE，DELTETE或insert语句修改的表，或者是一个受到声明的DELETE CASCADE引用完整性（外键约束）操作影响可能需要修改的表。（对于语句触发器而言，以上的不认为是变异表）

- 当一个行级触发器试图修改或测试一个正在通过一个DML语句修改的表时，系统会产生一个变异表错误(ORA-4091)。使用触发器读写表中的数据时必须遵守一定的规则，但是这些规则只适用于行级触发器，而语句触发器并不受影响。其限制和限制的目的如下：
  - 发出触发语句的会话不能查询或修改变异表。
  - 这一限制防止一个触发器看到一个不一致的数据集。
  - 这一限制适用于所有使用for EACH ROW子句的触发器。
  - 使用INSTEAD OF触发器正在修改的视图不被认为是变异的。

被触发的表（触发器进行操作的表）本身是一个变异表，同样使用外键(forEIGN KEY)约束引用的任何表也是变异表。正是这样的限制防止一个行触发器看到一个不一致的数据集合（正在修改的数据)。

**例**

```
create or replace TRIGGER check_salary
 BEforE insert OR UPDATE OF salary,department_id on copy_emp
 for EACH ROW
declare
 v_min_sal copy_emp.salary%type;
 v_max_sal copy_emp.salary%type;
begin
  select MIN(salary)
        ,MAX(salary)
  into v_min_sal
      ,v_max_sal
  from copy_emp
  where department_id = :NEW.department_id;
  if :NEW.salary < v_min_sal OR :NEW.salary > v_max_sal then
    RAisE_APPLICATIon_ERROR(-20001,'工资不在允许范围内');
  end if;
end;

UPDATE copy_emp
set salary = 1000
where employee_id = 177;
*********************
ORA-04091: 表 SCOTT.COPY_EMP 发生了变化, 触发器/函数不能读它
ORA-06512: 在 "SCOTT.CHECK_SALARY", line 5
ORA-04088: 触发器 'SCOTT.CHECK_SALARY' 执行过程中出错
```

**解决**

1. 将汇总数据（最低工资和最高工资）存储在另一个汇总表中，而该汇总表中的数据由其他的DML触发器进行持续的更新。
2. 将汇总数据存储在一个PL/SQL软件包中，并在这个软件包中访问这些汇总数据。这可以通过BEforE语句触发器中来实现
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
|      在触发语句之前。       |             BEforE STATEMENT              |
| 在触发语句影响的每一行之后。 |              AFTER STATEMENT              |
| 在触发语句影响的每一行之前。 |              BEforE EACH ROW              |
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
       - BEforE STATEMENT --> AFTER STATEMENT --> BEforE EACH ROW --> AFTER EACH ROW
    - 对于一个基于视图的复合触发器，唯一允许的程序段就INSTEAD OF EACH ROW子句开始的段。

**语法**

1. 基于表的复合触发器 

```
create or replace TRIGGER [用户.]触发器名
 for insert|UPDATE|DELETE on [用户.]表名
 COMPOUND TRIGGER
-- 初始段（不要declare)
 声明部分;
 子程序;
-- 可选段
 BEforE STATAMENT is
   语句;(PL/SQL代码块)
 AFTER STATEMENT is
   语句;
 BEforE EACH ROW is
   语句;
 AFTER EACH ROW is
   语句;
end;
```

2. 基于视图的复合触发器

```
create or replace TRIGGER [用户.]触发器名
 for insert|UPDATE|DELETE on [用户.]视图
 COMPOUND TRIGGER
--初始段
  声明部分;
  子程序;
--可选段
  INSTEAD OF EACH ROW is
    语句;(PL/SQL程序块)
end;
```

**使用复合触发器时，Oracle限制:**

- 一个复合触发器的体代码必须复合了整个触发器程序块，而且必须是使用PL/SQL编写的。
- 一个复合触发器必须是一个DML触发器。
- 一个复合触发器必须被定义在一个表上或者一个视图上。
- 一个复合触发器体不能有初始化段，也不能有异常段。
   - 在任何其他时间点程序段执行之前BEforE STATEMENT程序段永远只执行一次。
- 在一个程序段中出现的一个异常必须在这个段中处理，复合触发器无法将异常的控制传递给其他段。
- OLD、NEW和PARENT不能出现在声明段中，也不能出现在BEforE STATEMENT和AFTER STATEMENT段中。
- 复合触发器的触发顺序是无法保证的，除非使用了FOLLOWS子句。

#### 例 使用复合触发器解决变异表问题

```
create or replace TRIGGER check_salary
 for insert OR UPDATE OF salary,job_id on copy_emp
 when (NEW.job_id <> 'AA')
 COMPOUND TRIGGER
 
 type sal_type is table OF copy_emp.salary%type;
 min_sal sal_type;
 max_sal sal_type;
 
 type dept_id_type is table OF copy_emp.department_id%type;
 dept_id_list dept_id_type;
 
 type dept_sal_type is table OF copy_emp.salary%type
   INDEX by varchar2(38);
 dept_min_sal dept_sal_type;
 dept_max_sal dept_sal_type;

BEforE STATEMENT is
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
 end BEforE STATEMENT;
 
 AFTER EACH ROW is
   begin
     if :NEW.salary < dept_min_sal(:NEW.department_id) 
       OR :NEW.salary > dept_max_sal(:NEW.department_id) then
       RAisE_APPLICATIon_ERROR(-20001,'新工资超过部门工资范围');
     end if;
   end AFTER EACH ROW;
end; 
```



### 系统触发器（以及DDL触发器)

#### DDL触发器

- 除了DML语句之外，还可以指定一种或多种DDL语句来触发触发器（代码的执行）。可以为这些事件(DDL语句)在数据库上或模式上（需要指定模式即用户）创建触发器，还可以说明BEforE和AFTER作为这类触发器的触发时机。Oracle数据库在现存的用户事务中存放这类触发器。
  - 要注意的是，不能将通过一个PL/SQL过程执行的任何DDL操作说明为一个触发器的事件。
- 只有所创建的对象是一个cluster(簇)、表、索引、表空间、视图、函数、过程、软件包、触发器、(数据)类型、序列(sequence)、同义词(synonym)、角色或用户时，DDL触发器才能触发。
- 基于数据定义语言(DDL)语句上的触发器的触发器既可以定义在数据库一级，也可以定义在模式（用户）一级

**创建基于DDL语句的触发器的语法格式如下：**

```
create or replace TRIGGER 触发器名
 BEforE|AFTER [DDL事件1[OR DDL事件2 OR..] on 数据库|模式
 --DDL事件包括create、ALTER和DROP语句等，
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
create[OR REPLACE]TRIGGER触发器名
 BEforE|AFTER [数据库事件1[OR数据库事件2OR..]] on database|SCHEMA
   触发器体
```

**数据库事件**

|     数据库事件     |        触发时机        |             触发器的级             |
| :---------------: | :-------------------: | :-------------------------------: |
| AFTER SERVERERROR | 一个Oracle错误被抛出时 | 可以创建基于数据库或模式一级的触发器 |
|    AFTER LOGon    |  一个用户登录数据库时   | 可以创建基于数据库或模式一级的触发器 |
|   BEforE LOGOFF   |  一个用户退出数据库时   | 可以创建基于数据库或模式一级的触发器 |
|   AFTER startup   |      开启数据库时      |     只能创建数据库一级的触发器      |
|  BEforE shutdown  |    正常关闭数据库时    |     只能创建数据库一级的触发器      |


**权限**

- 仅仅拥有 `create ANY TRIGGER`的权限是不够的，创建触发器(trigger)时，ORACLE有一个限制，触发器(trigger)的拥有者必须被显示(explicitly)授予访问触发器(trigger)中涉及到的对象的权限(也就是说这些权限不能由角色继承而来)。
- 则创建数据库级触发器需要：`ADMINisTER database TRIGGER`权限

#### 例：用户登录'登出触发器

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
create or replace TRIGGER user_log_on_trigger
AFTER LOGon on SCHEMA
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
create or replace TRIGGER log_off_trigger
BEforE LOGOFF on SCHEMA
  begin
    insert into log_onoff_table
    values(log_onoff_seq.nextval,USER,sysdate,'用户登出');
  end;
 
--ERROR记录触发器
create or replace TRIGGER error_message_trigger
AFTER SERVERERROR on SCHEMA
  begin
    insert into error_table
    values (USER,sysdate,'.');
    --如何获取异常信息
  end;
```

**数据库级触发器** 

```
--sysdba赋予scott ：ADMINisTER database TRIGGER权限
GRANT ADMINisTER database TRIGGER TO scott;

--存放用户登入登出信息
create table system_log_onoff_table
(
 user_id varchar2(38)
,log_date DATE
,action varchar2(50)
);

--登入触发器
create or replace TRIGGER sys_log_on_trigger
AFTER LOGon on database
 begin 
   insert into system_log_onoff_table
   values(USER,sysdate,'用户登入');
 end;
 
--登出触发器
create or replace TRIGGER sys_log_off_trigger
 BEforE LOGOFF on database
  begin
    insert into system_log_onoff_table
    values(USER,sysdate,'用户登出');
  end;

--ERROR记录触发器

--开启数据库触发器
create or replace TRIGGER sys_startup_database_trigger
 AFTER startup on database
   begin
     insert into system_log_onoff_table
     values('orcl数据库',sysdate,'启动数据库');
   end;
   
--关闭数据库触发器
create or replace TRIGGER sys_startup_database_trigger
 BEforE shutdown on database
   begin
     insert into system_log_onoff_table
     values('orcl数据库',sysdate,'关闭数据库');
   end;
```

**问**

- 可以使用视图的方式，来避免其他用户的直接修改。但是要怎么实现插入新的信息？

#### 数据库级系统触发器的权限问题

**报错** 创建数据库级触发器

- ORA-04098: 触发器 'sys.USER_LOG_on_TRIGGER' 无效且未通过重新验证


**删除**

- 删除之前的触发器或DisABLE(简单粗暴)

```plsql
ALTER TRIGGER user_log_on_trigger DisABLE;
DROP TIRGGER user_log_on_trigger;
```

- 掀桌子 sysdba

```plsql
SQL> DROP TRIGGER user_log_on_trigger;

触发器已删除。
```

- 查看

```plsql
select trigger_name
      ,trigger_type
      ,triggering_event
      ,status
from USER_TRIGGERS;
```

### CALL调用语句

- 在触发器体中使用，调用一个存储过程（可看作一个PL/SQL程序块使用），该过程可以是PL/SQL，c，c++，Java语言。

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
create or replace procedure update_sal_action
 is
 begin
  dbms_output.put_line('更新成功！');
 end;

--创建触发器并使用CALL调用过程
create or replace TRIGGER update_sal_trigger
 BEforE UPDATE OR insert OF salary on copy_emp
 for EACH ROW
 when (NEW.employee_id <> 1)
 --调用过程update_sal_action
 CALL update_sal_action
```

### 触发器的管理与维护

- 每个触发器都具有激活(ENABLED)和禁止(DisABLED)两种模式（状态），一个触发器只能处于ENABLED状态或DisABLED状态。
1. ENABLE: 如果发出了一个触发语句并且该触发器的限制（如果有的话）评估（测试）为true(默认)，那么触发器运行它的触发器操作(PL/SQL程序代码)。
2. DisABLE: 触发器不运行它的触发器操作(PL/SQL程序代码)，即使发出了一个触发语句并且该触发器的限制（如果有的话）评估为true也不运行。

当一个触发器被首次创建时，它的状态默认是ENABLED。Oracle服务器对激活的触发器要检查所定义的完整性约束并保证这些触发器不会违反任何定义的完整性约束。另外，Oracle服务器还为查询和约束提供读取一致性的视图、管理依赖关系，并且如果一个触发器是修改分布数据库中远程的表,   Oracle服务器还提供一种两阶段的提交处理过程。即可以利用ALTER TRIGGER语句控制指定的触发器的状态，也可以利用ALTER table语句控制指定表上所有触发器的状态。

- 关闭（禁止）或重新开启（激活）一个数据库触发器的命令如下：

```
ALTER TRIGGER 触发器名 DisABLE|ENABLE;
```

- 关闭（禁止）或重新开启（激活）一个表上的所有触发器的命令则为：

```
ALTER table 表名 DisABLE|ENABLE ALL TRIGGERS;
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

**为什么将一个触发器设置为DisABLED状态**

其实，将一个触发器设置为DisABLED状态往往是一个无奈之举。有时一个系统可能已经满负荷运行，系统的效率很低。此时可能已经没有其他方法可以提高系统效率了，在这种情况下，就可能暂时关闭一个或多个触发器以换取系统效率的提高。实际上，这是一种以牺牲数据的完整性和系统安全为代价的系统优化举措，应该也是不得已而为之。系统的安全与效率永远是一对矛盾，越安全的系统，效率往往越低，反之效率越高的系统，就越不安全。最后，作为系统的开发者或管理者要在这两者之间做出艰难的平衡。一般触发器处在DisABLED状态应该是一个临时而短暂的状态，一旦系统效率正常之后，应该尽快将这些触发器重新设置回ENABLED。一般将一个触发器临时设置成DisABLED状态的情况可能如下：

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

#### 数据字典 USER_OBJECTS , USER_TRIGGERS

- 尽量使用USER_TRIGGERS数据字典来查询信息。USER_OBJECT信息可能有误。

```
select object_id
      ,object_name
      ,created
      ,object_type
from USER_OBJECTS
where object_type = 'TRIGGER';
```

```
select trigger_name
      ,trigger_type
      ,triggering_event
      ,status
from USER_TRIGGERS;
```

#### 触发器的权限问题

- 创建触发器的权限 需要：
    1. `create TRIGGER` create ANY TRIGGER
    2. 拥有在触发语句中所指定的表，
    3. 且有对这个表的ALTER权限。或ALTER ANY table权限

- 如果用户的触发器引用了如何不在用户模式中的对象，那么创建该触发器的用户必须在引用的过程，函数和软件包上具有执行权限，且该执行权限不是通过角色授予的。

- 创建数据库级触发器 需要：`ADMINisTER database TRIGGER`权限

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


**存储函数FUNCTIon和过程procedure**

- [存储函数：有返回值，创建完成后，通过select FUNCTIon() from dual;执行]
- [存储过程：由于没有返回值，创建完成后，不能使用select语句，只能使用pl/sql块执行]

|         procedure          |              FUNCTIon               |
| :------------------------: | :---------------------------------: |
|    作为一个PL/SQL语句执行    |         作为一个表达式来调用          |
|    在头中不包含RETURN子句    | 在头中必须包含且只能包含一个RETURN子句 |
|  可以使用多个输出参数传递值   |         必须返回一个单一的值          |
| 可以包含一个无值的RETURN语句 |      必须包含至少一个RETURN语句       |

### 过程 procedure

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

### 函数 FUNCTIon

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
create or replace FUNCTIon 函数名
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
create or replace FUNCTIon get_sal
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
create or replace FUNCTIon format_phone
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
create or replace FUNCTIon test_func
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
where object_type = 'FUNCTIon';
```

![](c:/users/zjk10/oneDrive/NoteBook/pictures/Snipaste_2022-11-18_11-14-17.png =400x)

- status属性：
   - VALID 表示可以被调用
   - INVALID 表示不可以被调用，必须重新编辑
      - 一般由于该工程使用的对象（通常是表）的定义被修改了 


#### 应用

##### 函数的 helloworld: 返回一个 "helloworld" 的字符串

```plsql
create or replace FUNCTIon hello_func
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
create or replace FUNCTIon hello_func(v_logo varchar2)
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
create or replace FUNCTIon func1
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
create or replace FUNCTIon add_func(a number, b number)
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
create or replace FUNCTIon sum_sal(dept_id number)
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
create or replace FUNCTIon sum_sal(dept_id number, total_count OUT number)
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
| SQLCODE返回值    |描述     |
| :-: | :-: |
|    0 |   没有遇到异常  |
|    1 |    用户定义的异常 |
|   +100  |  NO_DATA_FOUND异常   |
|    负数 |   其他的Oracle服务器错误号码  |

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
1. 在一个子程序中定义的变量只能在这个子程序中被引用，并且对于外部组件是不可见的，即本地变量local var只能在过程a中使用。
2. 在一个软件包体中声明的私有软件包变量可以被同一软件包体中的其他组件所引用，但是它们对于软件包之外的任何子程序或对象都是不可见的，即在软件包体中的过程a和过程b是可以使用私有变量private var的，但是软件包之外的子程序或对象就不可以使用了。
- 而全局声明的组件在软件包的内部和外部都是可见的，例如：
1. 在一个软件包说明部分声明的一个公共变量可以在软件包之外引用和修改，即公共变量public_var可以在软件包之外引用。
2. 在个软件包说明部分声明的一个软件包子程序可以被外部的程序代码所调用——过程a可以从软件包之外的一个环境中调用。
- 私有子程序（如过程b)只能被该软件包中的公共子程序调用（如过程Λ）或者由被该软件包中的其他私有软件包结构所调用。

### 软件包的开发

**原则：**

1. 将一个软件包说明的语句正文与这个软件包体的语句分开存放在两个脚本文件中，方便对软件包说明或软件包体的修改
2. 一个软件包说明可以在没有软件包体的情况下存在，即软件包说明可以不说明子程序也不需要包体。
    - 而如果软件包说明不存在是不能创建软件包体的。

- Oracle服务器将软件包说明和软件包体分开存放。
   - 从而在改变软件包体中某个程序的实现时不会使所调用或引用程序结构的其他模式对象变为无效。
   

**创建软件包的说明**

```
create or replace PACKAGE 包名 is|AS
    声明的公共变量，常量，游标，异常，用户定义的数据类型和子类型
    公共过程和函数的声明
end [包名];
```

**创建软件包体**



```
--软件包说明
create or replace PACKAGE salary_pkg is
  v_std_salary number := 1380;
  procedure reset_salary(p_new_sal number, p_grade number);
end salary_pkg;

--软件包体
create or replace PACKAGE BODY salary_pkg is
  FUNCTIon validate
  (
  p_sal number
  ,p_grade number
  ) 
  RETURN BOOLEAN 
  is
    v_min_sal   salgrade.losal%type;
    v_max_sal   salgrade.hisal%type;
  begin
    select losal, hisal 
    into   v_min_sal, v_max_sal
    from   salgrade
    where  grade = p_grade;
    RETURN (p_sal between v_min_sal and v_max_sal);
  end validate;

  procedure reset_salary
  (
  p_new_sal number
  ,p_grade number
  ) 
  is 
  begin
    if validate(p_new_sal, p_grade) then
      v_std_salary := p_new_sal;
    else RAisE_APPLICATIon_ERROR(-20038, '工资超限!');
    end if;
  end reset_salary;
end salary_pkg;
```

**软件包的调用**

```
SQL> execute salary_pkg.reset_salary(2450,4);
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
select object_id
      ,object_name
      ,object_type
      ,created
      ,status
from user_objects
where object_type IN ('PACKAGE','PACKAGE BODY');
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
create or replace PACKAGE dept_pkg is
 procedure add_dept
   (
    p_dept_id IN copy_dept.department_id%type
   ,p_dept_name IN copy_dept.department_name%type
   ,p_loc_id IN copy_dept.location_id%type
   );
--重载过程add_dept
 procedure add_dept
   (
    p_dept_name IN copy_dept.department_name%type
   ,p_loc_id IN copy_dept.location_id%type
   );
end dept_pkg;
```

```
create or replace PACKAGE BODY dept_pkg is
 procedure add_dept
   (
    p_dept_id IN copy_dept.department_id%type
   ,p_dept_name IN copy_dept.department_name%type
   ,p_loc_id IN copy_dept.location_id%type
   )
   is
   begin
     insert into copy_dept(department_id,department_name,location_id)
     values(p_dept_id,p_dept_name,p_loc_id);
   end add_dept;
   
 procedure add_dept
   (
    p_dept_name IN copy_dept.department_name%type
   ,p_loc_id IN copy_dept.location_id%type
   )
   is
   begin
     insert into copy_dept(department_name,location_id)
     values(p_dept_name,p_loc_id);
   end add_dept;
end dept_pkg;
```

### STandARD软件包与子程序重载

- 定义了PL/SQL环境和PL/SQL程序可以自动获取的全局数据类型，异常和子程序的声明，在Oracle系统安装时自动安装。

- 查看：DESC STandARD

- 如果在一个PL/SQL程序中重新声明了一个与内置函数同名的函数(如：`TO_char()`)；则在调用内置函数时需要`STandARD.TO_char()`
- 如果重新声明的与内置函数同名的是一个独立子程序，则在访问该子程序时需要`用户名.TO_char()`


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
create or replace PACKAGE sal_pkg is
 procedure reset_sal(p_sal number,p_grade number,p_id number);
end;
```

```
create or replace PACKAGE BODY sal_pkg is
--向前声明
  --检查工资是否符合要求
  FUNCTIon check_sal(p_sal number,p_grade number) RETURN BOOLEAN;
  --根据员工号码更新工资
  procedure update_sal_emp(p_sal number,p_id number);
  
  procedure reset_sal
    (
     p_sal number
    ,p_grade number
    ,p_id number
    )
    is
     sal_error exception;
    begin
      if check_sal(p_sal,p_grade) then
        update_sal_emp(p_sal,p_id);
        dbms_output.put_line('更新成功');
      else 
        RAisE sal_error;
      end if;
    exception
      when sal_error then
        dbms_output.put_line('更新失败');
        rollback;
      when OTHERS then
        dbms_output.put_line('其他错误');
        rollback;
    end reset_sal;
    
  FUNCTIon check_sal
    (
     p_sal number
    ,p_grade number
    )
    RETURN BOOLEAN
    is
     v_min_sal salgrade.losal%type;
     v_max_sal salgrade.hisal%type;
    begin
      select losal
            ,hisal
      into v_min_sal
          ,v_max_sal
      from salgrade
      where grade = grade;
      
      RETURN (p_sal between v_min_sal and v_max_sal);
    end check_sal;
    
   procedure update_sal_emp
     (
      p_sal number
     ,p_id number
     )
     is
     begin
       UPDATE copy_emp
       set salary = p_sal
       where employee_id = p_id;
     end update_sal_emp;
end sal_pkg;
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
create or replace PACKAGE select_emp is
  v_emp_dept copy_emp.department_id%type := 1;
  v_emp_name copy_emp.last_name%type;
  
  procedure emp_find(p_id number);
end;
```

```
create or replace PACKAGE BODY select_emp is
 procedure emp_find
   (
    p_id number
   )
   is
   begin
     select last_name
     into v_emp_name
     from copy_emp
     where employee_id = p_id;
   end emp_find;
   
--一次性过程：为软件包的变量赋初始值
  begin
    v_emp_name := 'KING';
    select department_id
    into v_emp_dept
    from copy_emp
    where employee_id = 117;
  --没有end；直接以软件包体的end结束
end select_emp;
```

```
execute dbms_output.put_line(select_emp.v_emp_dept);
execute dbms_output.put_line(select_emp.v_emp_name);
execute select_emp.emp_find(117);
```

### 在SQL中使用软件包中的函数

- 当执行一个调用存储函数（包括存储软件包中的函数）的SQL语句时，Oracle服务器必须指定这些存储函数的纯净级别（即这些函数有没有副作用）
- 通常这些副作用包括对数据库表中数据的修改或对软件包中公共变量（在软件包说明中声明的变量）的修改。控制副作用是非常重要的，因为这些副作用可能阻止正确的并行查询，产生顺序依赖并因此而产生不确定的结果，或需要一些越轨的操作（如在不同的用户会话中维护一个软件包的状态)。当在一个SQL查询语句或DML语句中调用一个函数时，一些限制是不允许出现在这个SQL语句中的。
- 一个函数所具有的副作用越少，则这个函数在一个查询语句中越容易优化，特别是当使用启示(Hint)PARALLEL_ENABLE或DETERMINisTIC时。如果要在SQL语句中调用一个存储函数,那么这个存储函数（以及任何它所调用的子程序）就必须遵守如下的纯净级别的规定：
1. 当在一个select语句或一个并行的DML语句中调用一个存储函数时，该函数不能更改数据库中任何表中的数据。
2. 当在一个DML语句中调用一个存储函数时，该函数不能查询也不能更改这个语句所更改的任何表
3. 当在-个select语句或一个DML语句中调用一个存储函数时，该函数不能执行SQL事务控制语句、会话控制语句和系统控制语句。
- 以上这些规则的目的就是控制函数使用的副作用。如果任何SQL语句中使用的函数体（程序代码）违反了以上的规则，该语句在执行时（在对这个语句进行词法分析时）将产生运行错误

### 软件包中变量的持续状态

公有和私有软件包变量的集合代表一个用户会话的软件包的状态，即在某一个指定的时间内存储在所有软件包变量中的值。通常，一个软件包的状态存在于用户会话的整个生命周期。

对于一个用户会话来说，当一个软件包被第一次装入内存时该软件包的变量被初始化。对于每一个会话，默认软件包变量都是唯一的并且这些变量的值一直保持到这个用户会话终止。换句话说，变量是存储在为每一个用户由数据库所分配的用户全局区(User Global Area,UGA)的内存中，当一个软件包中的子程序被调用，该子程序的逻辑变更了变量的状态时，软件包的状态就发生了变化。公共软件包的状态也可能直接由对其类型的适当操作所改变。

- PRAGMA代表这个语句是一个编译指令，由PRAGMA说明的语句是在编译期间处理的，而不是在运行时处理的。

- PRAGMA SERIALLY_RESUABLE指令
   - 这些指令并不影响一个程序的含义，它们仅仅将信息传输给编译器。如果在软件包说明中添加了PRAGMA SERIALLY_RESUABLE指令，那么Oracle数据库将软件包的变量存储在由多个用户会话共享的系统全局区(system GlobalArea,SGA)内存中。在这种情况下，软件包的状态被维护在一个子程序调用的生命周期中或对一个软件包结果的一个单一的引用生命周期中。如果想要节省内存并且如果不需要为每一个用户会话保持软件包的状态，那么SERIALLY_RESUABLE指令就非常有用。

当一个软件包需要声明大量的临时工作区，但是这些临时工作区只使用一次而且在同一个会话中后续数据库调用也不再需要它们时，PRAGMA指令非常适合。可以将一个无体软件包标记为串行可重用。但是如果一个软件包具有说明和体两部分，就必须标记说明和体两部分，不能只标记体。

串行可重用软件包的全局内存是统一存储在系统全局区(SGA)中的，而不是在每个用户所具有的用户全局区(UGA)中分配的，这样软件包的工作区就可以重用了。当对服务器的调用结束时，这些内存返回给SGA。每次软件包被重用时，它的公共变量都被初始化为它们的默认值或空值。

- 数据库触发器或在SQL语句中调用的PL/SQL子程序时不能访问串行可重用软件包的。否则产生错误

#### 例

### 软件包中游标的持续状态

- 与软件包中变量的持续状态相同，软件包中游标的持续状态时在整个会话期间跨越事物的。然而对于相同用户的不同会话（同一个用户的不同连接），它们的状态并不保持。

#### 例

```
create or replace PACKAGE emp_pkg is
 procedure open_emp;
 FUNCTIon next_employee(p_n number := 1) RETURN BOOLEAN;
 procedure close_emp;
end emp_pkg;
```

```
create or replace PACKAGE BODY emp_pkg is
 CURSOR emp_cursor is
  select employee_id
  from employees;
 
 procedure open_emp
   is
   begin
     if NOT emp_cursor%isOPEN then
       OPEN emp_cursor;
     end if;
   end open_emp;
   
 FUNCTIon next_employee
   (
    p_n number := 1
   )
   RETURN BOOLEAN 
   is
    v_emp_id employees.employee_id%type;
   begin
     for count IN emp_cursor loop
       FETCH emp_cursor 
       into v_emp_id;
       
       dbms_output.put_line('employee_id:'||v_emp_id);
     end loop;
     
     RETURN emp_cursor%FOUND;
   end next_employee;
   
 procedure close_emp 
   is
   begin
     if emp_cursor%isOPEN then
       CLOSE emp_cursor;
     end if;
   end close_emp;
end emp_pkg;
```

### 在软件包中使用PL/SQL记录表（记录数组）

- 可以在PL/SQL软件包中声明自定义的数据类型，在利用自定义的数据类型声明变量

#### 例

```
create or replace PACKAGE emp_dog is
 type emp_table_type is table OF employees%ROWtype
      INDEX by PLS_INTEGER;
 
 procedure get_emp(p_emp OUT emp_table_type);
end;
```
```
create or replace PACKAGE BODY emp_dog is
 procedure get_emp
   (
   p_emp OUT emp_table_type
   )
   is
    v_count BINARY_INTEGER := 1;
   begin
     for emp_record IN (select * from employees) loop
       p_emp(v_count) := emp_record;
       v_count := v_count + 1;
     end loop;
   end get_emp;
end;
```

### 标准化

#### 标准化异常和异常处理

- 创建一个标准的错误处理软件包，在该软件包中包括所有在应用程序中用户命名的和用户定义的异常
- 标准化异常处理既可以通过使用独立的存储子程序实现，也可以通过将一个子程序添加到定义标准异常的软件包中实现

**考虑：**

1. 在该程序中使用的每个命名的异常
2. 在这个程序中使用的无名或用户定义的异常。
3. 调用RAisE_APPLICATIon_ERROR的过程
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
- 要将一个子程序定义成使用调用者权来执行的子程序，只需要在参数表和is(或AS)关键字之间加上`AUTHID CURRENT_USER`。可以使用如下语句将不同的PL/SQL子程序结构设置成以调用者权限执行：

```
create FUNCTIon 函数名 
  RETURN 数据类型 
  AUTHID CURRENT_USER 
  is
    声明，函数体;
***********************
create procedure 过程名 
  AUTHID CURRENT_USER 
  is
   声明，过程体;
***********************
create PACKAGE 软件包名
  AUTHID CURRENT_USER 
  is
   声明；
***********************
create type 类型名 AUTHID CURRENT_USER is 对象；
```

#### 例

```
create or replace procedure get_salary
 (
  v_emp_id copy_emp.employee_id%type
 )
 AUTHID CURRENT_USER
 is
  v_sal number;
  begin
    select salary
    into v_sal
    from copy_emp
    where employee_id = v_emp_id;
    
    dbms_output.put_line('id:'||v_emp_id||' , sal:'||v_sal);
  end;
```

## 自治事务

在日志表中，从Oracle8i开始，Oracle引入了自治事务(Autonomous Transactions.)，自治事务使得创建独立的事务成为可能。一个自治事务(AT)是一个由另外一个主事务（MT:Main Transaction）开启的独立事务，自治事务是使用`PRAGMA AUTonOMOUS_TRANSACTIon`关键字定义的

**语法**

- 关键字`PRAGMA AUTonOMOUS_TRANSACTIon`指示PL/SQL编译器将一个例行程序标记为自治的（独立的）。例行程序包括（最外层的匿名PL/SQL程序块，本地的/独立的/软件包的函数和过程），一个SQL对象类型的方法，数据库触发器。
- 关键字`PRAGMA AUTonOMOUS_TRANSACTIon`可以在一个例行程序的声明段中的任意位置。一般放在声明段的最上方。

**例**

```
create or replace procedure sal_between
 (
  v_min_sal number
 ,v_max_sal number
 )
 is
  PRAGMA AUTonOMOUS_TRANSACTIon;
--自治事物
  CURSOR emp_id_cursor is
   select employee_id
   from copy_emp
   where salary between v_min_sal and v_max_sal;

  begin
    for c IN emp_id_cursor loop
      dbms_output.put_line(c.employee_id);
    end loop;
    commit;
  end;

***********
create or replace procedure test_pro
is
 begin
  sal_between(1000,2000);
  rollback
 end;
```

**具体操作顺序**

- 主事务与自治事务之间的关系，具体操作顺序如下：
   - 如：在过程(MT)中调用（只是调用，而不是声明定义）另一个过程(AT)
1. 主事务开始。
2. 主事务调用存储过程以启动自治事务。
3. 主事务处于挂起状态。
4. 自治事务操作开始。
5. 自治事务以一个commit或rollback语句结束。
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

## 导出程序的源代码、源代码加密

- `set long 长度` 设置输出显示的行数

### 导出
#### 使用USER_SOURCE数据字典导出

- ALL_SOURCE 数据字典
- DBA_SOURCE 数据字典

**XXX_SOURCE数据字典中没有存放触发器 的源代码**

``` 
select line
      ,text
from USER_SOURCE
where LOWER(name) = '存储过程名|存储函数名|软件包名|
```

#### 导出触发器的类型/触发事件/描述/代码

**USER_OBJECTS数据字典**

- 对象数据字典

```
select object_name
      ,object_type
      ,created
      ,status
      ,last_ddl_time  --最后使用时间    
from USER_OBJECTS
where object_type = 'TRIGGER';
```

**USER_TRIGGERS数据字典**

```
select trigger_name
      ,table_name
      ,triggering_event
      ,status
from USER_TRIGGERS;
```

**触发器的描述**

```
select description
from USER_TRIGGERS
where LOWER(trigger_name) = '触发器名';
```

**获取触发器的源代码**

```
select trigger_body
from USER_TRIGGERS
where LOWER(trigger_name) = '触发器名';
```

### PL/SQL源代码加密及动态加密

Oracle使用了一种叫做模糊(Obfuscation)或封装(wrapping)的技术来加密PL/SQL程序代码。所谓一个PL/SQL程序单元的模糊处理就是隐藏PL/SQL源代码的处理（即将PL/SQL源代码转换成人们无法阅读的“乱码”)。
- 在Oracle中既可以使用软件包DBMS_DDL的子程序加密（封装)PL/SQL源代码，
    - 通常使用 **软件包DBMS_DDL的子程序加密（封装）** 一个单独的PL/SQL程序单元，
       - 如一个单一的动态产生的create procedure命令， 
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
   - 因为DBMS_DDL软件包中的子程序每次执行时只接受一个create or replace语句。

|          功能           | DBMS_DDL | WARP |
| :--------------------: | :------: | :--: |
|     代码加密（模糊）    |    t     |   t  |
|     动态加密（模糊）     |    t     |  f   |
| 一次加密（模糊）多个程序 |    f     |  t   |

#### DBMS_DDL软件包

**动态加密**

动态加密就是在创建一个PL/SQL程序单元（如过程、函数和软件包等)的同时对这个程序单元的源代码进行加密。Oracle的动态加密方法是从Oracle10g开始引入的，它是通过调用软件包DBMS_DDL中的两个子程序实现的，这两个子程序分别是create_WRAPPED过程和WRAP函数。

- **create_WRAPPED过程**的功能为：将一个单独的create or replace语句作为输入（这个语句可以是如下的创建语句之一：创建一个PL/SQL软件包说明、一个软件包体、函数、过程、类型说明或类型体)，随后产生一个新的create or replace语句，但是PL/SQL源代码正文已经被加密（模糊）并执行这个新产生的语句。
- **WRAP函数**的功能为：将一个单独的create or replace语句作为输入（这个语句可以是如下的创建语句之一：创建一个PL/SQL软件包说明、一个软件包体、函数、过程、类型说明或类型体)并返回一个新的create or replace语句，在这个语句中PL/SQL程序单元的正文已经被加密（模糊）。

**软件包DBMS DDL及其create WRAPPED过程和WRAP函数之间的关系，以及这两个子程序之间的共同和不同之处**

PL/SQL

- DBMS_DDL软件包 包含：
  - create_WRAPPED过程 
     - 封装（加密）正文并创建PL/SQL程序单元  
  - DBMS_DDL.WRAP函数 
     - 与create_WRAPPED过程功能相同 
     - 但允许比create_WRAPPED过程更大的输入 
     

**语法**

```
begin 
  DBMS_DDL.create_WRAPPED('加密的代码');
  --加密的代码可以先放在一个字符串常量里面；
end;
```

##### 例1 create_WRAPPED过程加密PL/SQL源代码

```
begin
 DBMS_DDL.create_WRAPPED ('
   create or replace procedure show_time is
   begin
     dbms_output.put_line(''当前时间：''||sysdate);
   end;
 ');
end;
```

```
declare
  c_code constant varchar2(10000) := '
   create or replace procedure show_time is
    begin
      dbms_output.put_line(''当前时间：''||sysdate);
    end;
  ';
begin
  DBMS_DDL.create_WRAPPED(c_code);
end;
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
WRAP INAME=输入文件名 [onAME=输出文件名]
--不能有空格
--别加分号；不然会认为文件名里面也有分号
```

- 输入文件可以包括任何SQL语句的组合，然而PL/SQL封装程序只封装（加密）如下的create语句：
1. create [OR REPLACE] type
2. create [OR REPLACE] type BODY
3. create [OR REPLACE] PACKAGE
4. create [OR REPLACE] PACKAGE BODY
5. create [OR REPLACE] FUNCTIon
6. create [OR REPLACE] procedure
- 除了以上列出的create语句之外，所有其他的SQL create语句都被原样存入输出文件（即没有加密)。

**在使用以上命令加密一个操作系统文件时，Oracle系统有如下约定：**

1. 只有INAME参数是必需的。如果没有说明onAME参数，那么输出文件的名字与输入文件相同，但是其文件的扩展名为plb。
2. 输入文件的扩展名可以是任意的扩展名，但是默认扩展名为.$sql。
3. NAME和onAME参数的值（即输入文件名和输出文件名）是否区分大小写取决于使用的操作系统。
4. 通常输出文件要比输入文件大许多。
5. 在NAME和onAME之间的等号两边不能有任何空格。

- 当封装（加密）的文件创建成功之后，要在`SQL*Pus(或isQL*PIus)`中执行这个加密后的.plb文件以编译加密后的源代码并将其存储在数据库中，其执行方法与执行SQL脚本文件一模一样。

- 当一个文件被封装（加密）之后，其中的对象类型、子程序具有如下形式：头，紧跟一个单词，wrapped,随后是加密的程序体。

##### 例

```
g:\sqlTest>WRAP INAME=wraptest.sql onAME=testout.pld;

PL/SQL Wrapper: Release 11.2.0.1.0- 64bit Production on 星期六 10月 15 19:04:52 2022
Copyright (c) 1993, 2009, Oracle.  All rights reserved.
Processing wraptest.sql to testout.pld;

g:\sqlTest>type testout.pld
create or replace procedure show_name wrapped
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
dMAzuHRlJXxlUF0lXZt0iwm4wDL+0oYJpuEfSZqPMLVQyKlQLwDKSv4I0sc9qRF3oisJuIHH
LcmmpvpLBBA=

g:\sqlTest>notepad testout.pld
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

## 外部语言例程

### Java

- javasyspriv权限和javauserpriv权限。
- Oracle调用Java类的必须是静态公有方法。
- 将Java方法的返回值作为查询的数据。

#### Oracle内部创建Java语句

1. 在SQLPlus中创建Oracle中的Java类与方法

```sql
create or replace and compile java source named 函数名 as
public class 类名{
    public static 返回值 方法名(参数列表){}
}
```

```sql
create or replace and compile java source named hello as
public class Hello{
       public static String say(String name){
              return "Hello " + name;
       }
}
```

2. 创建函数调用载入Oracle中的Java方法

```sql
create or replace function hello (name varchar2)
return varchar2
as 
language java name 'Hello.say(java.lang.String) return java.lang.String';
```

3. 通过创建的函数来调用Java程序

```sql
select hello(user) 
from dual;
```

#### 导入外部Java类

1. 创建要调用的Java代码，并编译成 **Class文件** ，需要先javac
2. loadjava命令在控制台上将class文件加载到Oracle环境中

```shell
loadjava -u sys/sys@orcl -oci8 -verbose -grant scott -synonym -resolve -schema scott class文件路径
```

- 卸载已经加载的Java代码

```shell
dropjava -user sys/sys@orcl 类名
```

3. 通过创建的函数来调用Java程序
