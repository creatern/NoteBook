# grant

```mysql
grant 权限选项 on 数据库名.表等对象
to '被授权者的用户名'@'被授权者的主机地址';
```

```mysql
# 查看当前用户被允许的授权行为
SHOW GRANTS FOR CURRENT_USER();
```

```mysql
# 为（在本地登录的）zjk用户授予 caption 数据库的所有权限
GRANT ALL ON captin.* TO 'zjk'@'localhost';
```

# create

```shell
CREATE USER 'zjk'@'localhost' IDENTIFIED BY 'tiger';
```

