```shell
# 查看当前域名解析的信息
resolvectl status
```

```shell
# 修改DNS
sudo vim /etc/systemd/resolved.conf
sudo systemctl restart systemd-resolved.service
```
