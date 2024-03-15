# select 基本查询

## select .. from

| 子句   | 作用             |
| ------ | ---------------- |
| select | 查询的条件       |
| from   | 确定查询的数据源 |

```sql
-- 查询指定值域
select emp_name
from emp;

-- 查询所有*
select *
from emp;

-- 查询运算式
select salary * 1.02
from emp;
```

| 关键字   | 说明             |
| -------- | ---------------- |
| as       | 别名（可省略）   |
| \|\|     | 连接符（Oracle） |
| distinct | 唯一性           |

```sql
select first_name || '' || last_name
from employees;
```

| 运算符类型 | 运算符              |
| ---------- | ------------------- |
| 算术运算符 | `+  - * /`          |
| 比较运算符 | `> >= < <= = <> !=` |
| 逻辑运算符 | and or not          |

## where 谓词查询

- where子句（如果存在）则必须置于from子句之后。

```sql
select * 
from emp
where salary > 100;
```

| 关键字          | 说明                                                      |
| --------------- | --------------------------------------------------------- |
| in              | 列表取值                                                  |
| is null         | 判断空值                                                  |
| like            | 模糊查询<br />-：一个字符<br />%：n个字符<br />\\：转义符 |
| between a and b | 范围查询（a&le;X&le;B）                                   |

```sql
select last_name, employee_id, salary
from employees
where salary > 1000
 and department_id in (10,20,30);
```

```sql
-- 查询没有部门的员工
select *
from emp
where dept_name = null;
```

```sql
-- 查询员工姓名含有a的
select last_name, employee_id
from employees
where last_name like '%a%';
```

```sql
select last_name, employee_id, salary
from employees
where salary between 1000 and 7000;
```

## order by 排序

- order by子句只能在语句最后出现，可以使用第一个查询中的列名、别名、相对位置。

| 顺序 | 说明       |
| :--- | :--------- |
| asc  | 默认，升序 |
| desc | 降序       |

```sql
select employee_id, last_name
from employees
order by employee_id desc;
```

- 多列排序：依次在先前列的排序结果上进行排序。

```sql
-- 先对employee_id排列，再在employee_id排列的基础上对last_name排列
select emlpoyee_id, last_name
from employees
order by employee_id, last_name;
```

- 别名排列：order by子句直接使用别名进行排序。

```sql
select employee_id, last_name, salary*1.1 sal -- 别名
from employees
order by sal;
```

## case .. when

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
-- 输出除编号为1的员工即where empno <> 1;
select *
from emp
where 0 = case
           when empno = '1' then 1
           else 0
          end
```

## 集合运算

| 运算 | 说明                 |
| ---- | -------------------- |
| 并集 | union<br />union all |
| 交集 | intersect            |
| 差集 | minus                |

- 多个进行集合运算的结果集的select字句中的列名、表达式在数量、数据类型上一一对应。

- 除union all之外，其余集合运算会自动去重，会自动按照第一个查询中的第一个列的升序排列。

```sql
-- 使用相对位置排序举例
select job_id,1
from employees
where department_id = 10
union
select job_Id ,2
from employees
where department_id = 20
order by 1 DESC;
```

# insert

## insert 插入数据

- insert一次只能向表中插入一条数据。

```sql
-- 显式插入
insert into emp(id,salary,name,hire_date)
values(1,1500,'张三',to_DATE('2002-2-3','yyyy-mm-dd'));

-- 隐式插入
insert into emp
values(2,3000,'李四',to_DATE('2003-3-2','yyyy-mm-dd'));

-- 显式null
insert into emp
values(5,null,'赵七'，null);

-- 字段插入 对于没有列出的值域，则插入null
insert into emp(name,salary,hire_date,id)
values(2500,'王五',to_DATE('2004-4-5','yyyy-mm-dd'),3);
```

## \& 窗口输入

- 窗口输入 ：对于日期和字符型等需要单引号的数据类型，可以在&外面加上单引号（`'&name'`），从而不用在窗口输入时使用单引号。而其他不需要单引号的数据类型则可以直接使用`&id` 。

```sql
insert into emp
values(&id,&name,&salary,&hire_date);
-- 5 '李四' 2000 '11-2月-1999'

insert into emp
values(&id,'&name',&salary,'&hire_date');
-- 5 李四 2000 11-2月-1999
```

## select 复制插入

- 相应的列数据类型要一致

```sql
insert into emp
select employee_id
      ,salary
      ,last_name
      ,hire_date 
from employees
where department_id = 90;
```

## merge 合并数据

- 数据转储操作：从表中选择数据行以修改或插入到另一个表。
- merge基于on子句中的条件来决定对目标表执行的是修改还是插入操作，必须在目标表上有insert和update系统权限，源表上具有select系统权限。

```sql
merge into 表 别名  -- 说明正在修改或插入的目标表
using (table|view|sub_query) 别名  -- 标识要修改或插入的数据源（表，视图，子查询等）
on (条件) -- 定义merge语句是进行修改还是插入操作的条件
when matched then  -- 当条件满足时，执行
 update set         -- 执行的语句块
   col1 = col1_val,
   col2 = col2_val
when not matched then  -- 当条件不满足时，执行
 insert (列名)
 values (值)
```

1. update、insert可仅仅出现其一、附加where条件。（无条件insert：`on(1=1)`）
2. delete子句仅仅能够删除目标表的行而无法删除源表的行，必须存在update set，且delete字句中的where必须位于最后。
3. 不能更新on子句引用的列，且应该保证on中条件的唯一性（在源表中获得一组稳定的行）。

> on(t1.name = t2.name)：则t1和t2表中的name应该是一一对应的，而不能重复。

4. 自我更新的using若存在null，则改为count()将null改为0处理。

```sql
merge into t_bonus t
using (select employee_id, salary
       from  s_employee
      ) s
on (t.employee_id = s.employee_id)
when matched then 
  update set t.bonus = s.salary + 40
  where t.bonus > 1000
             
  delete 
  where (t.bonus > 1300)
when not matched then 
  insert into (t.employee_id, t.bonus)
  values (s.employee_id, s.salary +20)
  where (s.salary > 3000)
```

```sql
merge into t2
using (select * 
       from t2 
       where name='d') t
on (t.name=T2.name)
when matched then
 update set t2.MonEY=100
when not matched then
 insert values ('d',200);

-- 本来t表应该由于name=D不存在而要添加记录。可是实际却根本无变化。
SQL> select * from t2;
name                      MonEY
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -
a                            30
c                            20
-- 由于此时select * from t2 where name='d'为null,所以出现了无法插入的情况。

-- 自我更新的using若存在null，则改为count()将null改为0处理。
merge into t2
using (select count(*) CNT 
       from t2 
       where name='d') t
on (t.CNT <> 0)
when matched then
 update
 set t2.MonEY = 100
when not matched then
 insert
 values ('d',100);

SQL> select * from t2;
name                      MonEY
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -
a                            30
c                            20
d                           100
```

# update 更新数据 

```sql
update 表
set 列=新的列
where -- 没有where ，则全部更新
```

```sql
-- 更新114号员工的工作和工资使其和205号员工相同
update employees
set job_id = (
             select job_id
             from employees
             where employee_id = 205
             )
   ,salary = (
              select salary
              from employees
              where employee_id = 205
              )
where employee_id = 114;
```

# delete

## delete 删除数据 

```sql
delete from table_name
where  -- 若无where子句，则全部删除
```

```sql
-- 删除emp表中id为1的员工
delete from emp
where id = 1;
```

## truncate 清空数据

| 操作     | 对比     |
| -------- | -------- |
| delete   | 可以回滚 |
| truncate | 不可回滚 |

```sql
truncate table 表;
```