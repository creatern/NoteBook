# Oracle安装

- 以Oracle Linux 9为例

## 预安装

```shell
# 安装 oracle-database-preinstall的RPM包
# 会自动创建标准（非角色分配）Oracle 安装所有者，并根据 Oracle 安装的需要进行分组和设置其他内核配置设置
# 具体参考 https://docs.oracle.com/en/database/oracle/oracle-database/19/ladbi/Chunk151261164.html#GUID-C15A642B-534D-4E4A-BDE8-6DC7772AA9C8
dnf install oracle-database-preinstall-19c-1.0-1.el9.x86_64
```

