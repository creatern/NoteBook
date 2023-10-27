# sequence 序列

- sequence（序列）可供多个用户来产生唯一数据的数据库对象，与表无关。自动提供唯一的数值、共享对象、提供主键值。

1. 将sequence装入内存可以提高访问效率
2. sequence序列号是数据库系统按照一定规则自增的数字序列（不会重复）
3. sequence可用于记录数据库中最新动作（CRUD）的语句，序列号都会随着更新。

# create sequence 创建序列

```sql
create sequence sequence_name
[start with n1]
[increment by n2]
[maxvalue n3|no maxvalue]
[minvalue n4|no minvalue]
[cache n5|nocache] 
[cycle|nocycle]
[order|no order]
```

| 选项                                             | 说明                                                         |
| ------------------------------------------------ | ------------------------------------------------------------ |
| start with                                       | 生成的第一个数值                                             |
| increment by                                     | 递增量（正整数/负整数），指明每一次增加多少。                |
| maxvalue、minvalue<br />no maxvalue、no minvalue | 最大值、最小值<br />无上限、无下限                           |
| cache<br />nocache                               | 高速缓存中可以预分配的序列号个数，默认是20个<br />预分配序列号，每次只生成一个序列号，保证序列号的连续性。 |
| cycle<br />nocycle                               | 序列达到最值后是否从生成的第一个数值开始循环。<br />默认，不循环。 |
| order<br />no order                              | 指定按顺序生成序列。（RAC时保证序列号因为有请求才生成的）<br />默认，不指定按顺序生成序列。 |

- cache不能保证序列号是连续的。如果缓存中的序列号没有用完就关闭数据库等其它原因，没有使用的序列号就丢失（跳号）。如果指定cache值，ORACLE就可以预先在内存里面放置一些sequence，cache里面的取完后，oracle自动再取一组到cache。
- 序列装入内存可提高访问速度，但会在以下的情况下出现裂缝（跳号）：回滚、系统异常、多个表同时使用同一个序列。

> sequence\_cache\_ENTRIES参数，设置能同时被cache的sequence数目。

# \.nextval、\.currval 序列移动

| 操作      | 说明                                                         |
| --------- | ------------------------------------------------------------ |
| \.nextval | 引用下一序列值。<br />第一次\.nextval返回的是初始值；随后的每次\.nextval都会触发自动增长（increment by），返回增加后的值。 |
| \.currval | 引用当前序列值。<br />序列创建后初始无值，无法查询当前值.currval。第一次使用序列时，要先查询下一值（\.nextval）才能查询当前值（\.currval）。 |

```sql
select emp_seq.nextval                
from dual;

select emp_seq.currval
from dual;
```

- 使用sequence：不包含子查询/SNAPSHOT/view的select语句、insert语句的子查询、insert语句的values子句、update语句的set子句 ...。

```sql
create table tab_name(
                      col_int int,
                      col_varchar varchar2(20),
                      col_seq int
                      );

-- insert语句的values子句
insert tab_name into values(1, 'xyz', sequence_name.nextval); 
-- 或者 insert tab_name into values(1, 'abc', nextval for sequence_name);
insert tab_name into values(2, 'fgh', sequence_name.nextval); 
-- 或者 insert tab_name into values(2, 'fgh', nextval for sequence_name);

-- update语句的set子句
update tab_name 
set col_varchar = '678', col_seq = sequence_name.nextval 
where col_int = 2; 

delete tab_name col_sql = sequence_name.nextval 
where col_int = 1;
```

# alter seq\_name 修改序列 

- 若要修改start with、minvalue的值，则必须删除序列后再重新建立序列。
- 可修改序列的增量、最大值、循环选项、是否装入内存。修改之后，只有将来的序列值会被改变。

# drop sequence 删除序列

```sql
drop sequence emp_seq;
```
