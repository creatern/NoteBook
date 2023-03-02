## 1.修改参数文件sqlnet.ora

- 位于admin文件夹下的，添加如下：

```
ENCRYPTION_WALLET_LOCATION=
(SOURCE=(METHOD=FILE)
       (METHOD_DATA=
        (DIRECTORY=F:\ORA_WALLET))) --路径文件夹(目录DIRECTORY)
```

## 2.设置钱包密码

```sql
ALTER SYSTEM 
SET ENCRYPTION KEY AUTHENTICATED BY "tiger";
```

## 3.使用

```sql
CREATE TABLESPACE secertspace
DATAFILE 'F:\NEWDB\ZDB\userdb01' 
SIZE 200M
ENCRYPTION USING 'AES128'
DEFAULT STORAGE(ENCRYPT);
```

## 4.配置自动打开钱包（可选）

