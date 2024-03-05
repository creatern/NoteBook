# show databases

- `show databases`：只显示当前用户具有权限的数据库；需要注意该命令是databases，而不是database。

## database()

- `database()`：该函数将当前数据库名作为`utf8mb3`字符集中的字符串返回；若没有，则返回null

```mysql
select database();
```

# create database

- `create database`：创建数据库，并没有立刻选择使用该数据库

```mysql
create database 数据库名;
```

# use database

- `use database`：选择并使用指定的数据库；此外，可以在<a href="./Shell.md#连接时指定数据库">连接时添加数据库选项</a>来选择数据库并使用

```mysql
use database mydb;
```
