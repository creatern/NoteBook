# 安装配置

1. 下载解压缩版：
2. 进行配置my.ini

```
[mysql]
default-character-set=utf8

[mysqld]
character-set-server=utf8
default-storage-engine=INNODB
basedir=D:\mysql-5.7.24-winx64
datadir=D:\mysql-5.7.24-winx64\data\
port=3306
skip-grant-tables
sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION
```

3. 管理员cmd输入以下：

```
mysqld --initialize-insecure
```

- 如果报错：

```
C:\Windows\System32>mysqld --initialize-insecure
2023-03-18T05:03:36.866446Z 0 [Warning] TIMESTAMP with implicit DEFAULT value is deprecated. Please use --explicit_defaults_for_timestamp server option (see documentation for more details).
2023-03-18T05:03:36.870989Z 0 [ERROR] --initialize specified but the data directory has files in it. Aborting.
2023-03-18T05:03:36.871823Z 0 [ERROR] Aborting
```

- 则删除当前解压的mysql目录中data内的所有内容，再执行该命令

4. 将mysql注册到主机：

```
mysqld -install
```

5. 启动MySQL服务

```
net start mysql
```

6. 修改默认账户密码

```
mysqladmin -u root password tiger
```

# 基础语句

## IF EXISTS 

- 判断是否存在

```
DROP TABLE IF EXISTS 表;
```



# 数据库

## 创建数据库

```sql
create database 数据库;
```

## 删除数据库

```sql
drop database 数据库;
```

# 数据类型

| 分类           | 数据类型     | 大小                  | 描述                            |
| -------------- | ------------ | --------------------- | ------------------------------- |
| 数值类型       | TINYINT      | 1 byte                | 小整数值                        |
|                | SMALLINT     | 2 bytes               | 大整数值                        |
|                | MEDIUMINT    | 3 bytes               | 大整数值                        |
|                | INT或INTEGER | 4 bytes               | 大整数值                        |
|                | BIGINT       | 8 bytes               | 极大整数值                      |
|                | FLOAT        | 4 bytes               | 单精度浮点数值                  |
|                | DOUBLE       | 8 bytes               | 双精度浮点数值                  |
|                | DECIMAL      |                       | 小数值                          |
| 日期和时间类型 | DATE         | 3                     | 日期值                          |
|                | TIME         | 3                     | 时间值或持续时间                |
|                | YEAR         | 1                     | 年份值                          |
|                | DATETIME     | 8                     | 混合日期和时间值                |
|                | TIMESTAMP    | 4                     | 混合日期和时间值，时间戳        |
| 字符串类型     | CHAR         | 0-255 bytes           | 定长字符串                      |
|                | VARCHAR      | 0-65535 bytes         | 变长字符串                      |
|                | TINYBLOB     | 0-255 bytes           | 不超过 255 个字符的二进制字符串 |
|                | TINYTEXT     | 0-255 bytes           | 短文本字符串                    |
|                | BLOB         | 0-65 535 bytes        | 二进制形式的长文本数据          |
|                | TEXT         | 0-65 535 bytes        | 长文本数据                      |
|                | MEDIUMBLOB   | 0-16 777 215 bytes    | 二进制形式的中等长度文本数据    |
|                | MEDIUMTEXT   | 0-16 777 215 bytes    | 中等长度文本数据                |
|                | LONGBLOB     | 0-4 294 967 295 bytes | 二进制形式的极大文本数据        |
|                | LONGTEXT     | 0-4 294 967 295 bytes | 极大文本数据                    |

# 基本查询

```sql
SELECT 
FROM
WHERE
GROUP BY
HAVING
ORDER BY
LIMIT
```

## SELECT

## WHERE

### (col1,col2) 多属性过滤

```sql
select *
from emp
where (id,emp_name) = (1001,'king');
```

## LIMIT 分页限定

- 类似于rownum

```sql
SELECT 
FROM
LIMIT 起始索引,查询条目数;
```

- `起始索引 = （当前页码 - 1）* 每页显示的条数 ` 

```sql
SELECT * 
FROM employees
LIMIT 0,3;  --从第0行（不包括0）开始(第一页)，查询3条数据 1~3

SELECT *
FROM employees
LIMIT employees
LIMIT 3,3; --从第3行（不包括3）开始，查询3条数据 4~6
```

# 表

- 基本和Oracle相同
  
## CREATE TABLE

### LIKE 复制表模式

- LIKE只复制表的结构，而不会导入数据。

```sql
create table emp_copy like emp;
```

## ALTER TABLE

### CHANGE 重命名列和数据类型

```sql
ALTER TABLE 表
CHANGE 列名 新列名 新数据类型;
```

## INSERT

### 多行插入

- 可以多行插入

```sql
INSERT INTO 表
VALUES
(id1,'name1'),
(id2,'name2'),
(id3,'name3');
```

# 约束

- NOT NULL
- UNIQUE
- PRIMARY KEY
- CHECK
- DEFAULT
- FOREIGN KEY 

## DEFAULT 默认约束

```sql
CREATE TABLE emp
(
    id CHAR(8) PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    address VARCHAR(30) DEFAULT '未知'
);

mysql> DESC emp;
+---------+-------------+------+-----+---------+-------+
| Field   | Type        | Null | Key | Default | Extra |
+---------+-------------+------+-----+---------+-------+
| id      | char(8)     | NO   | PRI | NULL    |       |
| name    | varchar(20) | NO   |     | NULL    |       |
| address | varchar(30) | YES  |     | 未知    |       |
+---------+-------------+------+-----+---------+-------+
```

