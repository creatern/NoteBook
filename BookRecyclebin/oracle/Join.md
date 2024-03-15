# join 连接概述

- join子句可以视为from子句的一部分。
- 多个表的连接需要中间表。

| 连接                     | 说明                                                         |
| ------------------------ | ------------------------------------------------------------ |
| 内连接<br />（常规连接） | 合并具有同一列的两个以上的表的行，结果集不包含一个表与另一个表不匹配的行。<br />不适合查询大表，用来比较表中的行、查询分层数据。 |
| 外连接                   | `[outer] join`                                               |

# join 内连接

## self/inner join 自连接

- 自连接（self/inner join） 将自身表的一个镜像（通过别名将同一张表视为多张表）当作另一个表来对待。

```sql
select t1.no1,no2,no3
from table1
inner join table1 t1 on no1;
```

```sql
-- 查询所有雇用日期相同(同一天入职)的员工
select e1.employee_id,e1.hire_date
from employees e1
 inner join employees e2 
  on e1.employee_id <> e2.employee_id
  and e1.hire_date = e2.hire_date;
```

## natural join 自然连接

- natural join（全自动连接、自然连接）：只返回在两个关系中的都拥有的相同属性的相同元组。

- 自然连接的两个表的有多个字段是相同名称、相同数据类型（系列）。如果自然连接的两个表仅是字段名称相同、但数据类型不同，那么将会返回一个错误。
- natural join的select子句中的列不能有限定词，而其他连接都需要限定词。

> 在设计表时，应该尽量在不同表中具有相同含义的字段使用相同的名字和数据类型，以便使用natural join。

```sql
select no1,no2,no4
from tabel1
 natural join table2;
```

```sql
select department_id,
       department_name
from employees
 natural join departments;
```


## join .. using 同名列连接

-  join .. using：使用同名列的连接。using多表连接时，若列名不同，则不适用。

```sql
select t1.no1,no2,no4
from table1 t1
 join table2 using (no1);
```

## join .. on 等值连接（where）

```sql
select t1.no1, no2,no4
from table1 t1
 join table2 t2 on t1.no1 = t2.no1
[where...];
```

```sql
select t1.no1, no2, no4
from table1 t1, table2 t2
where t1.no1 = t2.no1;
```

```sql
-- 1.join
select distinct e.department_id,d.department_id
from employees e
join departments d
on e.department_id = d.department_id;

-- 2.where
select distinct e.department_id,d.department_id
from employees e
    ,departments d
where e.department_id = d.department_id;
```

- 多个表的连接需要中间表。

```sql
-- 1.join
select employee_id,e.department_id,department_name,d.location_id,city
from employees e
  join departments d on e.department_id = d.department_id
  join locations l on d.location_id = l.location_id; 
  -- department表中的department_id和location_id

-- 2. where
select employee_id,e.department_id,department_name,d.location_id,city
from employees e,departments d,locations l
where e.department_id = d.department_id
  and d.location_id = l.location_id;
```

## cross join 交叉连接

- cross join（不带on子句）返回被连接的两个表所有数据行的笛卡尔积。返回到结果集合中的数据行数等于第一个表中符合查询条件的数据行数乘以第二个表中符合查询条件的数据行数。

```sql
select t1.no1,no2,no4
from table1 t1
 cross join table2;
```

# \[outer\] join 外连接

| 外连接                                 | 包含from子句中的表与on中的表不匹配的值           |
| -------------------------------------- | ------------------------------------------------ |
| left \[outer\] join<br />（左外连接）  | 当连接条件不成立的时候，等号左边的表仍然被包含。 |
| right \[outer\] join<br />（右外连接） | 当连接条件不成立的时候，等号右边的表仍然被包含。 |
| full \[outer\] join<br />（全连接）    | 查询结果等于左外连接和右外连接的和。             |

```sql
-- left [outer] join可以返回左表(table1)中与右表(table2)不匹配的值
select t1.no1,no2,no4
from table1 t1,table2,t2
 left outer join table2 on t1.no1 = t2.no1;
```

```sql
-- right [outer] join可以返回右表(table2)中与左表(table1)不匹配的值
select t1.no1,no2,no4
from table1 t1
 right outer join table2 on t1.no1 = t2.no1;
```

```sql
-- full [outer] join可以返回左右表中不匹配的行
select t1.no1, no2, no4
from table1 t1
 full outer join table2 on t1.no1 = t2.no1;
```

## SQL92 外连接符 (+)

- 在连接条件中无匹配行的表的列后面加(+)，没有匹配的行时，结果表中相对应的列为空。

> 哪边无加号(+)就是什么连接。

```sql
-- 左外连接
select t1.no1,no2,no4
from table1 t1,table2 t2
where t1.no1 = t2.no1(+);  

-- 右外连接
select t1.no1,no2,no4
from table1 t1,table2 t2
where t1.no1(+) = t2.no1;
```

```sql
# 左外连接，额外返回department表中不满足连接条件的行
select employee_id, department_name
from employee e, department d
where e.department_id = d.department_id(+);
```
