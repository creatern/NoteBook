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

- You define a table with a table name, such as employees, and set of columns. In general, you give each column a name, a data type, and a width when you create the table.
- A table is a set of rows. A column identifies an attribute of the entity described by the table, whereas a row identifies an instance of the entity. 
- You can optionally specify a rule, called an integrity constraint, for a column. 

#### Indexes 索引