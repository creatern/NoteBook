# view 视图

> scott用户创建视图view权限不足：使用dba授予scott用户创建视图view权限。
>
> ```sql
> grant create view to scott; 
> ```

- 视图：从表中抽出的逻辑上相关的数据集合（视图==储存的select语句）。视图是一种虚表，建立在已有的基础上（基表），当基表的数据发生变化时，视图里面的数据也会跟着发生变化。视图向用户提供基表数据的另一种表现形式，向视图提供内容的语句为select语句 。

- 视图用来控制数据访问、简化查询、避免重复访问相同的数据。

| 视图类型      | 说明                                                         |
| ------------- | ------------------------------------------------------------ |
| 连接视图      | 基于多个表建立的视图。<br />一般不会在该视图上执行insert、update、delete。 |
| 只读视图      | 允许进行select操作的视图<br />创建视图时指定with read only选项。<br />不能执行insert、update、delete。 |
| check约束视图 | 视图上定义check约束<br />该视图上执行insert或update操作时,数据必须符合查询结果。 |

# 视图操作

## create \[or replace\] view 创建/修改视图

- create view子句中各列的别名应和子查询中各列相对应。
- 修改视图只能重新创建视图。

| 特性     | 简单视图 | 复杂视图           |
| :------- | :------- | :----------------- |
| 表的数量 | 一个     | 一个、多个         |
| 函数     | 没有     | 有（使用别名）     |
| 分组     | 没有     | 有                 |
| DML      | 可以     | 必须要符合特定条件 |

```sql
-- 简单视图
create or replace view table1_view
as
select *
from table1;
```

```sql
-- 复杂视图:组函数一定要使用别名，否则报错。
create or replace view table1_view
as
select avg(salary) avg_sal,department_id -- 组函数一定要使用别名，否则报错
from employees
group by department_id;  
```

```sql
-- 连接视图 (table1,table2外键为column2)
create or replace view table1_view
as
select column1,t1.column2,column3
from table1 t1,table2 t2
where t1.column2(+) = t2.column2;
```

## select 查询视图

```sql
select *
from table1_view;
```

## drop view 删除视图 

- drop view只是删除视图的定义，并不会删除基表的数据。

```sql
drop view table1_view;
```

# 视图DML条件

- 用户可以通过视图对基表执行增删改操作，但是有很多在基表上的限制。

> 若基表中某列不能为空，但是该列没有出现在视图中，则不能通过视图执行insert操作。若基表设置了某些约束，这时候插入视图或者修改视图的值，有可能会报错。

1. 简单视图中可执行DML操作。
2. 视图定义中包含以下元素之一时，不能用delete语句：组函数、group by子句、distinct关键字、rownum伪例
3. 且若还包含列的定义为表达式时，不能用update语句：列的定义为表达式。
4. 且若还包含以下的元素时，不能用insert语句：表中非空的列在视图定义中未包括。

## with read only 只读视图（屏蔽DML）

- with read only：选读屏蔽，设置只读。对只读视图的任何DML操作都会返回ORACLE SEVER错误。

```sql
create or replace view table1_view(no1,no2)
as
select no1,no2
from table1
with read only;
```

# materialized 物化视图

- 物化视图（相当于一个表）将视图的查询结果存储在数据库中。使用该视图时，使用的是查询的结果，而不是查询表达式。

## create\|alter materialized view 创建/修改物化视图

> 创建物化视图的权限
>
> ```sql
> grant create materialized view to 用户;
> ```

```sql
create|alter materialized view 视图名
as 
查询表达式
```

```sql
create|alter materialized view view_name
refresh [ fast|complete|force ]  -- 刷新方法
[
on [commit|demand ]   | -- 刷新模式
start with (start_time) next (next_time) -- 设置刷新时间
]
as 
查询表达式;
```

| 生成数据        | 说明                                     |
| --------------- | ---------------------------------------- |
| build immediate | 创建物化视图的时候就生成数据             |
| build deferred  | 创建时不生成数据，以后根据需要在生成数据 |

### 刷新模式

| 刷新模式  | 说明                                                         |
| --------- | ------------------------------------------------------------ |
| on demand | 默认，仅在该物化视图“需要”被刷新时，才进行刷新（refresh），更新物化视图，以保证和基表数据的一致性。 |
| on commit | 提交触发，一旦基表有了commit（事务提交），则立刻刷新，立刻更新物化视图，使得数据和基表一致。<br />一般用这种方法在操作基表时速度会比较慢。 |

| 刷新方法                   | 说明                                                         |
| -------------------------- | ------------------------------------------------------------ |
| complete<br />（完全刷新） | 删除表中所有的记录（如果是单表刷新，可能会采用truncate的方式），然后根据物化视图中查询语句的定义重新生成物化视图。 |
| fast<br />（快速刷新）     | 增量刷新的机制，只将自上次刷新以后对基表进行的所有操作刷新到物化视图中去。<br />fast必须创建基于主表的视图日志。<br />对于增量刷新选项，如果在子查询中存在分析函数，则物化视图不起作用。 |
| force                      | 默认，自动判断是否满足快速刷新的条件，如果满足则进行快速刷新，否则进行完全刷新。 |

> Oracle物化视图的快速刷新机制是通过物化视图日志完成的。Oracle通过一个物化视图日志还可以支持多个物化视图的快速刷新 。 物化视图日志根据不同物化视图的快速刷新的需要，可以建立为rowid、primary key类型的 ，还可以选择是否包括sequence、including new values以及指定列的列表。

#### 物化视图日志

```sql
create materialized  view log on test_table  
tablespace test_space  --  日志空间  
with primary key ;      --  指定为主键类型
```

```sql
-- 删除物化视图日志
drop materialized view log on test_table;
-- 删除物化视图        
drop materialized view mv_materialized_test; 
```

### 刷新时间

```sql
create materialized view mv_materialized_test 
refresh force
on demand 
start with sysdate next to_date( concat (to_char( sysdate + 1 , 'dd-mm-yyyy' ), '10:25:00' ), 'dd-mm-yyyy hh24:mi:ss' ) 
as
select  *  from  user_info;  -- 这个物化视图在每天10：25进行刷新 
```

### 查询重写（QueryRewrite）

- `enable|disable query rewrite`：指出创建的物化视图是否支持查询重写，默认disable。 查询重写是当对物化视图的基表进行查询时 ， oracle会自动判断能否通过查询物化视图来得到结果，如果可以，直接从已经计算好的物化视图中读取数据。
