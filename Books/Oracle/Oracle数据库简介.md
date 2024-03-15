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