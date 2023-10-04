# 分层查询(树查询)(递归查询)

## 基本语法

```sql
SELECT [LEVEL],列,..
FROM 表
START WITH 列 = '值' --以满足条件的元组作为根节点 LEVEL=1
CONNECT BY [NOCYCLE] PRIOR 父键 = 子键; 
--当子键（指定子节点的列）的值=父键（指定父节点的列）的值时，递归查找,会一层一层找下去,直到不符合这一规则,则查找停止
```

```sql
SELECT id,data,NVL(TO_CHAR(pid),'NULL')
FROM menu
START WITH pid IS NULL --指定层次化查询的根节点
CONNECT BY PRIOR id = pid;  --当前节点的pid等于上一层节点的id，如果满足条件,就加入到树结果集中
```

**STRAT WITH 选择根节点**

- START WITH 一般用于指定层次化查询的开始节点(也就是树的最顶级节点)，找到最顶级节点,   然后按照一定的规则开始查找其剩余的子节点
  - `START WITH 列 = '值'`用于确定由表选择的要用作根节点的行。
- 由`列 = '值'`选择START WITH中满足条件的所有行都将成为树的根节点。
  - 于是，结果集中潜在树的数量等于根节点的数量。
- 因此，如果省略START WITH子句，则返回的每一行都是其自己树的根节点。

**CONNECT BY 定义父/子关系**

- CONNECT BY 用于查找剩余子节点的规则
- 对于任何给定行，其父级和子级均由`CONNECT BY`子句确定。
  - 带有PRIOR关键字标识的为父键，
  - 即子节点的指定列的值要满足等于父节点的（带有PRIOR标识的）列的值，才可以继续分层。 
- `CONNECT BY`子句必须由使用等号 (=) 进行比较的两个表达式组成。
  - 这两个表达式必须有一个前面带有关键字PRIOR,

**LEVEL 节点级别**

- LEVEL是一个伪列，可在SELECT命令中列出现的任何位置使用
- 伪列 LEVEL 返回这一行在树中的层次，根为第一层。
  - 根节点的LEVEL为1。根节点的直接子级的LEVEL为2，依此类推
- 对于结果集中的每一行，LEVEL返回一个非零整数值，指出由此行表示的节点在层次结构中的深度。

## ORDER SIBLINGS BY 对同级排序 

- 通过使用 `ORDER SIBLINGS BY`子句，可对结果集进行排序， 以便按所选列值的升序或降序显示同级。
  - 这是 ORDER BY 子句的特例，只能用于分层查询。

```sql
SELECT LEVEL, LPAD (' ', 2 * (LEVEL - 1)) || ename "employee", empno, mgr
FROM emp 
START WITH mgr IS NULL
-- 以mgr为null的元组作为根节点，LEVEL=1
CONNECT BY PRIOR empno = mgr
--连接条件为：子节点的mgr等于父节点的empno的元组
ORDER SIBLINGS BY ename ASC；
--在结果集中按ename升序排列
```

```sql
SELECT LEVEL
      ,empno
      ,ename
      ,sal
      ,mgr
FROM emp
CONNECT BY PRIOR empno = mgr
START WITH mgr IS NULL
ORDER BY 1;
```

## 回环CYCLE

层次化查询会检测数据中是否存在回环(死循环)，如果存在回环，则会抛出 ORA-01436: CONNECT BY loop in user data . 的错误

- 如果在CONNECT BY子句后面加上 NOCYCLE 则产生回环的最后一层的节点会被删除

```sql
SELECT id,data,NVL(TO_CHAR(pid),'NULL')
FROM menu
START WITH (data ='a' OR data= 'b')
CONNECT BY NOCYCLE PRIOR id = pid; 
--产生回环的最后一层的节点会被删除,(可能并没有删除)
```

## CONNECT_BY_ISCYCLE / CONNECT_BY_ISLEAF / CONNECT_BY_ROOT

1. CONNECT_BY_ISCYCLE 
   - 当这一行有一个子节点同时也是它的祖先节点时返回 1 ，否则返回 0 。
2. CONNECT_BY_ISLEAF 
   - 当这一行是叶节点时返回 1 ，否则返回 0 。
3. CONNECT_BY_ROOT 一元运算符，可用于限定列，以便根据当前行返回被视为根节点的行的列值
   - CONECT_BY_ROOT查询操作符可以加在CONNECT BY之后的某个字段之前，表示获得这一行的根节点的该字段的值。
   - CONNECT_BY_ROOT 运算符可在 SELECT 列表、WHERE 子句、GROUP BY 子句、HAVING 子句、ORDER BY 子句和 ORDER SIBLINGS BY 子句中使用，只要 SELECT 命令用于分层查询。
   - CONNECT_BY_ROOT 运算符不能在分层查询的 CONNECT BY 子句或 START WITH 子句中使用。
   - 可将 CONNECT_BY_ROOT 应用于一个涉及列的表达式,但这样做时,该表达式必须用圆括号括起，当应用于不带圆括号的表达式时,CONNECT_BY_ROOT运算符仅影响紧跟其后的一列

```sql
--使用 CONNECT_BY_ROOT 运算符根据以员工 Zlotkey 和 King 开头的树，为结果集中列出的每个员工返回根节点的员工编号和员工名称。
--(查询管理者Zlotkey和King和所管理员工的last_name,employee_id,manager_id)
SELECT LEVEL,
       LPAD(' ',2 * (LEVEL - 1)) || last_name "employee",
       employee_id,
       manager_id,  
       CONNECT_BY_ROOT employee_id "mgr_id",
       CONNECT_BY_ROOT last_name "mgr_name"
FROM employees
START WITH last_name IN ('Zlotkey','King')
CONNECT BY PRIOR employee_id = manager_id
ORDER SIBLINGS BY last_name ASC;
```

```sql
--只生成一个以单个顶层级别员工开头的树
SELECT LEVEL,LPAD(' ',2 * (LEVEL - 1)) ||last_name "name",employee_id,manager_id,
       CONNECT_BY_ROOT employee_id "mgr_empno",
       CONNECT_BY_ROOT last_name "mgr_ename"
FROM employees
START WITH manager_id IS NULL
CONNECT BY PRIOR employee_id = manager_id
ORDER SIBLINGS BY last_name ASC;
```

## SYS_CONNECT_BY_PATH检索路径

**SYS_CONNECT_BY_PATH函数，在分层查询中用于检索在当前节点和根节点之间出现的指定列的值。**

- SYS_CONNECT_BY_PATH (column, delimiter);    
  - column 是位于分层查询中指定的表中且调用该函数的列的名称。
  - delimiter 是 varchar 值，用于分隔指定列中的每个条目。
- SYS_CONNECT_BY_PATH (exp,char) 
  - 返回从根节点到这一行计算其中每个exp表达式的值，并把它们连接成字符串，
  - 每个节点之间用char字符来分割。

# 排名 

## rank()

| 函数           | 描述     | 说明                                                         |
| -------------- | -------- | ------------------------------------------------------------ |
| rank()         | 基本排名 | 如果出现两个相同的值，它会将其分为同一名，同时将下一个栏位所占名次空出来。<br>比如有两个相同的列，则他们的栏位都是2，那么下一个列的栏位就是4，而不是3 |
| dense_rank()   |          | 如果出现两个相同的值，它会将其分为同一名，但并不会空出所占栏位数。<br>比如有两个相同的列，则他们的栏位都是2，那么下一个列的栏位还是3 |
| row_number()   |          | 如果存在组内排序栏位的列值相同，则：仍然按次序排<br>比如有两个相同的列，则他们的栏位依次排列，如2，3 |
| percent_rank() | 比例排名 | 以百分数形式给出排名                                         |
| ntile(n)       | 桶排名   | 按指定的顺序取到每个分区中的元组，并把他们分成具有n个相同元组数目的桶，对于每个元组，编号由所在桶的编号决定。 |

- RANK() OVER 用于指定条件后的进行排名.
- 特点是对指定栏位的排名可以使用本函数,

```sql
SELECT RANK() OVER([PARTITION BY 分组栏位的列] ORDER BY 组内排序栏位的列 DESC|ASC)
FROM 表;
```

- PARTITION BY：分区，按指定属性进行分组，再在各自分组内进行排名。

```sql
--按工资给出排名
select ename,rank() over(order by(sal) desc) sal_rank
from emp;

ENAME        SAL_RANK
---------- ----------
KING                1
FORD                2
SCOTT               2
JONES               4
BLAKE               5
CLARK               6
ALLEN               7
TURNER              8
MILLER              9
WARD               10
MARTIN             10
ADAMS              12
JAMES              13
SMITH              14
```

```sql
--各部门分别按工资给出部门内部员工排名
select ename,rank() over(partition by deptno order by(sal) desc) sal_rank
from emp;

ENAME        SAL_RANK
---------- ----------
KING                1
CLARK               2
MILLER              3
SCOTT               1
FORD                1
JONES               3
ADAMS               4
SMITH               5
BLAKE               1
ALLEN               2
TURNER              3
MARTIN              4
WARD                4
JAMES               6
```

```sql
--高于各工资的员工比例
select ename,sal,percent_rank() over(order by sal desc) sal_rank
from emp;

ENAME            SAL   SAL_RANK
---------- --------- ----------
KING         5000.00          0
FORD         3000.00 0.07692307
SCOTT        3000.00 0.07692307
JONES        2975.00 0.23076923
BLAKE        2850.00 0.30769230
CLARK        2450.00 0.38461538
ALLEN        1600.00 0.46153846
TURNER       1500.00 0.53846153
MILLER       1300.00 0.61538461
WARD         1250.00 0.69230769
MARTIN       1250.00 0.69230769
ADAMS        1100.00 0.84615384
JAMES         950.00 0.92307692
SMITH         800.00          1
```

```sql
--分成3个桶按工资给出排名
select ename,ntile(3) over(order by(sal) desc) sal_rank
from emp;

ENAME        SAL_RANK
---------- ----------
KING                1
FORD                1
SCOTT               1
JONES               1
BLAKE               1
CLARK               2
ALLEN               2
TURNER              2
MILLER              2
WARD                2
MARTIN              3
ADAMS               3
JAMES               3
SMITH               3
```

## Top~n

- TOP分析：查询一个列中最大/最小的n个值的集合

**rownum 伪列**

- Oracle不支持SELECT TOP语句;而是使用ORDER BY来进行TOP~n
- **rownum行号 是一个伪列**，表上没有这一列，当做一些特殊操作的时候，Oracle自动加上。

**注意**

1. 行号永远按照默认的顺序生成；  
2. **行号只能使用<,<=**，不能使用=,>,>=的符号。
3. **对伪列ROWNUM起别名可以使用=,>,>=**

```sql
SELECT rownum,column1,column2
FROM (
      SELECT column1,column2
      FROM table1
      ORDER BY column1
      )
WHERE rownum <= 10; 
--/ FETCH FIRST 10 ROWS ONLY; 12c以上才可以使用FETCH语句
```

```sql
--对伪列ROWNUM起别名可以使用=,>,>=
SELECT rn,column1,column2
FROM (
      SELECT rownum rn,column1,column2
      FROM (
           SELECT column1,column2
           FROM table1
           ORDER BY salary DESC
            )
      )
WHERE rownum >= 10;
```

# 分窗

- 在一定范围内的元组上计算聚集函数。趋势分析。

```sql
select 组函数(字段) over(order by 字段 asc|desc rows n|unbounded peceding)
from 关系;
```

```sql
rows n peceding --按指定顺序的前n个元组上计算组函数
rows unbounded peceding --按指定顺序的前面所有的元组计算组函数
rows between n1 preceding and n2 following --按指定顺序的前n1个到后n2个为止的元组上计算组函数
rows between n1 preceding and current row --按指定顺序的前n1个到当前为止的元组上计算组函数
```

```sql
select deptno,avg(sal) over(order by sal desc rows 3 preceding)
from emp;

DEPTNO AVG(SAL)OVER(ORDERBYSALDESCROW
------ ------------------------------
    10                           5000
    20                           4000
    20               3666.66666666667
    20                        3493.75
    30                           3365
    10                         3212.5
    30               2982.14285714286
    30                       2796.875
    10               2630.55555555555
    30                         2492.5
    30               2379.54545454545
    20               2272.91666666667
    30               2171.15384615385
    20               2073.21428571428
```

# 旋转
