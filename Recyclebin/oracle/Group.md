# 组函数（聚集函数）

-  组函数 (多行函数) 输入多个参数、或内部扫描多次，输出一个结果。

| 常用组函数 | 说明     |
| ---------- | -------- |
| avg()      | 平均值   |
| count()    | 记录总数 |
| max()      | 最大值   |
| min()      | 最小值   |
| sum()      | 总和     |

- 组函数对null的处理：count()将null看作0来处理、其他组函数忽略输入集合中的null（如果作用在null上，则返回null）。
- 组函数可以相互嵌套。

| 数学                     | 说明                                                         |
| ------------------------ | ------------------------------------------------------------ |
| stdden()<br />variance() | 标准差<br />方差                                             |
| **分级汇总**             | **说明**                                                     |
| rollup()                 | 按分组的第一个列进行统计和最后的小计                         |
| cube()                   | 按分组的所有列的进行统计和最后的小计                         |
| **行列转换**             | **说明**                                                     |
| wm_concat()              | 列转行：对分组函数的每一组结果中的某一指定值域进行整行输出。<br />wm_concat()中使用的列不必出现在group by子句中。 |

# group by

- select子句中所有未包含在组函数的列都应该包含在group by子句，而group by子句中的列不必包含在select子句。

```sql
-- 求表中各部门平均工资
select avg(salary)
from employees
group by department_id;

-- 求部门平均工资的最大值
select max(avg(salary))
from employees
group by department_id;
```

- 多列group by：先按在前的值域进行分组，再依次在之前分组的基础上进行分组。

```sql
-- 按department_id分组后在内部按employee_id分组
select avg(salary), department_id, employee_id
from employees
group by department_id, employee_id;
```

# having

- 可以在having中使用组函数（不能使用组函数的别名），而不能在where子句中使用组函数。sql形成分组后才会使用having子句中的谓语，故可以在having子句中使用组函数（对分组后的数据进行筛选）。

```sql
select job,avg(sal) as avg_sal
from emp
group by job
having avg(sal) > 100
```
