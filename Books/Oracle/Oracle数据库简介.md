# RDBMS 关系数据库

## DBMS 数据库管理系统

- A <b>database management system (DBMS)</b> is software that controls the storage, organization, and retrieval of data.

<table>
    <caption>DBMS具有的元素</caption>
    <tr>
        <td width="25%">Kernal code<br/>（内核代码）</td>
        <td width="75%">管理DBMS的内存和存储</td>
    </tr>
    <tr>
        <td>Repository of metadata<br/>（元数据存储库）</td>
        <td>也就是 数据字典（data dictionary）</td>
    </tr>
    <tr>
        <td>Query language<br/>（查询语言）</td>
        <td>使应用程序能够访问数据</td>
    </tr>
</table>

- A <b>database application</b> is a software program that interacts with a database to access and manipulate data.

<table>
    <caption>第一代DBMS的类型</caption>
    <tr>
        <td width="10%">Hierarchical</td>
        <td width="90%">A <b>hierarchical database（分层数据库）</b> organizes data in a tree structure. Each parent record has one or more child records (one-to-many), similar to the structure of a file system.</td>
    </tr>
    <tr>
        <td>Network</td>
        <td>A <b>network database（网络数据库）</b> is similar to a hierarchical database, except records have a many-to-many rather than a one-to-many relationship.</td>
    </tr>
</table>

## Relational Model 关系模型

- A <b>relational database</b> is a database that conforms to <b>the relational model</b>.

<table>
    <tr>
        <td width="25%">Structures 数据库结构</td>
        <td width="75%">Well-defined objects store or access the data of a database.</td>
    </tr>
    <tr>
        <td>Operations 数据库操作</td>
        <td>Clearly defined actions enable applications to manipulate the data and structures of a database.</td>
    </tr>
    <tr>
        <td>Integrity rules 完整性规则</td>
        <td>Integrity rules govern operations on the data and structures of a database.</td>
    </tr>
</table>

- A relational database stores data in a set of simple relations. A relation is a set of tuples. A tuple is an unordered set of attribute values.
- A table is a two-dimensional representation of a relation in the form of <b>rows (tuples)</b> and <b>columns (attributes)</b>. Each row in a table has the same set of columns. A relational database is a database that stores data in <b>relations (tables)</b>. 

## RDBMS 关系数据库管理系统

- Relational Database Management System (<b>RDBMS</b>，关系数据库管理系统): The relational model is the basis for a relational database management system (RDBMS). An RDBMS moves data into a database, stores the data, and retrieves it so that applications can manipulate it.
- An RDBMS distinguishes between the following types of operations:

<table>
    <tr>
        <td width="20%">Logical operations<br/>逻辑操作</td>
        <td width="80%">In this case, an application specifies what content is required.</td>
    </tr>
    <tr>
        <td>Physical operations<br/>物理操作</td>
        <td>In this case, the RDBMS determines how things should be done and carries out the operation. The RDBMS stores and retrieves data so that physical operations are <b>transparent</b> to database applications. （物理操作对数据库应用程序是透明的）</td>
    </tr>
</table>

## Oracle Database

- <b>Oracle Database</b> is an <b>RDBMS</b>. An RDBMS that implements object-oriented features such as user-defined types(用户定义类型), inheritance(继承性), and polymorphism(多态性) is called an <b>object-relational database management system (ORDBMS)</b>. Oracle Database has extended the relational model to an object-relational model, making it possible to store complex business models in a relational database.

# Schema Objects 模式对象

- One characteristic of an RDBMS is the independence of physical data storage from logical data structures.（RDBMS的物理数据存储独立于逻辑数据结构）
- In Oracle Database, a <b>database schema</b> is a collection of logical data structures, or schema objects. A database user owns a database schema, which has the same name as the user name.（数据库用户拥有与用户名同名的数据库架构）

1. <b>Schema objects</b> are user-created structures that directly refer to the data in the database. The database supports many types of schema objects, the most important of which are <b>tables and indexes</b>.（模式中的对象通常包括表、索引、数据类型、序列、视图、存储过程、主键、外键等等）
2. A schema object is <b>one type of database object</b>. Some database objects, such as profiles and roles, do not reside in schemas.（模式对象&sube;数据库对象）

### Tables 表

- You define a table with a table name and set of columns. In general, you give each column a name, a data type, and a width when you create the table.
- A table is a set of rows. A column identifies an attribute of the entity described by the table, whereas a row identifies an instance of the entity. 
- You can optionally specify a rule, called an <b>integrity constraint（完整性约束）</b>, for a column. 

### Indexes 索引

- An <b>index</b> is an optional data structure that you can create on one or more columns of a table. Indexes can increase the performance of data retrieval.

1. When processing a request, the database can use available indexes to locate the requested rows efficiently. Indexes are useful when applications often query a specific row or range of rows.
2. Indexes are logically and physically independent of the data. 

# Data Access 数据访问

## Structured Query Language (SQL)

- Structured Query Language （SQL，结构化查询语言）is a set-based declarative language that provides an interface to an RDBMS such as Oracle Database. 

1. SQL is nonprocedural and describes *what* should be done.
2. SQL is the <b>ANSI</b> standard language for relational databases. All operations on the data in an Oracle database are performed using SQL statements. 
3. SQL statements enable you to perform the following tasks:
   1. Query data
   2. Insert, update, and delete rows in a table
   3. Create, replace, alter, and drop objects
   4. Control access to the database and its objects
   5. Guarantee database consistency and integrity

## PL/SQL and Java

- <b>PL/SQL</b> is a procedural extension to Oracle SQL and integrated with Oracle Database.
- A primary benefit of PL/SQL is the ability to <b>store application logic in the database itself</b>. A PL/SQL procedure or function is a schema object that consists of a set of SQL statements and other PL/SQL constructs, grouped together, stored in the database, and run as a unit to solve a specific problem or to perform a set of related tasks. The principal benefit of <b>server-side programming</b> is that built-in functionality can be deployed anywhere.
-  <b>Oracle Database can alsostore program units written in Java</b>. A Java stored procedure is a Java method published to SQL and stored in the database for general use. You can call existing PL/SQL programs from Java and Java programs from PL/SQL.

# Transaction Management 事务管理

- Oracle Database is designed as a <b>multiuser database</b>. The database must ensure that multiple users can work concurrently without corrupting one another's data.

## Transactions 事务

- A <b>transaction（事务）</b> is <b>a logical, atomic unit of work（逻辑上的一个原子性的作业单元）</b> that contains one or more <b>SQL statements（SQL语句）</b>.
- An RDBMS must be able to group SQL statements so that they are either all <b>committed</b>, which means they are applied to the database, or all <b>rolled back</b>, which means they are undone.

> Transactions are one feature that set Oracle Database apart from a file system. If you perform an atomic operation that updates several files, and if the system fails halfway through, then the files will not be consistent. In contrast, a transaction moves an Oracle database from one consistent state to another. The basic principle of a transaction is "all or nothing": an atomic operation succeeds or fails as a whole.

## Data Concurrency 数据并发

- A requirement of a multiuser RDBMS is the control of data concurrency, which is <b>the simultaneous access of the same data by multiple users</b>.Without concurrency controls, users could change data improperly.
- If multiple users access the same data, then one way of managing concurrency is to make users wait. However, the goal of a DBMS is to reduce wait time so it is either nonexistent or negligible. All SQL statements that modify data must proceed with as little interference as possible. <u>Destructive interactions, which are interactions that incorrectly update data or alter underlying data structures, must be avoided.</u>
- Oracle Database uses locks to control concurrent access to data. A <b>lock（事务锁）</b> is a mechanism that prevents destructive interaction between transactions accessing a shared resource. Locks help ensure data integrity while allowing maximum concurrent access to data.

## Data Consistency 数据一致性

- In Oracle Database, each user must see <b>a consistent view of the data</b>, including visible changes made by a user's own transactions and committed transactions of other users.

> For example, the database must prevent <b>the dirty read problem（脏读）</b>, which occurs when one transaction sees uncommitted changes made by another concurrent transaction.

- Oracle Database always enforces <b>statement-level read consistency（语句级读一致性）</b>, which guarantees that the data that a single query returns is committed and consistent for a single point in time. Depending on the transaction isolation level, this point is the time at which the statement was opened or the time the transaction began. The Oracle Flashback Query feature enables you to specify this point in time explicitly.
- The database can also provide read consistency to all queries in a transaction, known as <b>transaction-level read consistency（事务级读一致性）</b>. In this case, each statement in a transaction sees data from the same point in time, which is the time at which the transaction began.

# Oracle Database Architecture Oracle 数据库架构

- A <b>database server（数据库服务器）</b> is the key to information management.

1.  In general, a server reliably manages a large amount of data in a <b>multiuser</b> environment so that users can concurrently access the same data. 
2. A database server also <b>prevents unauthorized access</b> and provides efficient solutions for <b>failure recovery（故障恢复）</b>.

## Database and Instance 数据库和实例

- An Oracle database server consists of a database and at least one <b>database instance（数据库实例 [实例]）</b>, commonly referred to as simply an instance.