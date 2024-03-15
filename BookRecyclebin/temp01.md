对于一个提交的sql语句，存在两种可选的解析过程：硬解析、软解析。

硬解析需要经解析、制定执行路径、优化访问计划等许多的步骤。硬解释不仅仅耗费大量的cpu，更重要的是会占据重要的们闩（latch）资源，严重的影响系统的规模的扩大（即限制了系统的并发行）， 而且引起的问题不能通过增加内存条和cpu的数量来解决。之所以这样是因为门闩是为了顺序访问以及修改一些内存区域而设置的，这些内存区域是不能被同时修改。当一个sql语句提交后，oracle会首先检查一下共享缓冲池（shared pool）里有没有与之完全相同的语句，如果有的话只须执行软分析即可，否则就得进行硬分析。





配置篇

#### [安装与配置](./Books/Oracle/安装与配置.md)

#### [数据库启动与关闭](./Books/Oracle/数据库启动与关闭.md)

#### [内存结构](./Books/Oracle/内存结构.md)

#### [逻辑结构](./Books/Oracle/逻辑结构.md)

#### [物理结构](./Books/Oracle/物理结构.md)

### 基础篇

#### [事务与权限](./Books/Oracle/事务与权限.md)

#### [CRUD](./Books/Oracle/CRUD.md)

#### [数据类型](./Books/Oracle/数据类型.md)

#### [Table](./Books/Oracle/Table.md)

#### [View](./Books/Oracle/View.md)

#### [子查询](./Books/Oracle/子查询.md)

#### [join 连接](./Books/Oracle/Join.md)

#### [组函数](./Books/Oracle/Group.md)

#### [Sequence 序列](./Books/Oracle/Sequence.md)

#### [index 索引](./Books/Oracle/Index.md)

### PL/SQL

#### [synonym 同义词](./Books/Oracle/Synonym.md)

#### [cursor 游标](./Books/Oracle/Cursor.md)

#### [procedure、function 子程序](./Books/Oracle/ProcedureAndFunction.md)

#### [trigger 触发器](./Books/Oracle/Trigger.md)

#### [Exception 异常处理](./Books/Oracle/Exception.md)

#### [批量绑定](./Books/Oracle/bulk.md)

#### [PL/SQL软件包](./Books/Oracle/package.md)

#### [程序使用权限](./Books/Oracle/程序使用权限.md)

#### [自治事务](./Books/Oracle/自治事务.md)

#### [外部语言例程](./Books/Oracle/外部语言例程.md)

#### [源码导出与加密](./Books/Oracle/源码导出与加密.md)
