# rsync 数据同步

- `rsync`（remote sync）：数据同步，默认使用增量备份

<table>
    <tr>
        <td width="30%">--recursive, -r</td>
        <td width="70%">递归处理子目录</td>
    </tr>
    <tr>
        <td>--archive, -a</td>
        <td>归档模式，表示以递归方式传输文件，并保持所有文件属性；也就是组合<code>-rlptgoD</code></td>
    </tr>
    <tr>
        <td>--dry-run, -n</td>
        <td>模拟执行，并没有实际动作</td>
    </tr>
</table>

# 工作模式

<table>
    <tr>
        <th width="30%"><code>rsync</code>工作模式</th>
        <th width="70%">说明</th>
    </tr>
    <tr>
        <td>本地备份</td>
        <td><code>rsync [OPTION]... SRC DEST</code></td>
    </tr>
    <tr>
        <td>通过ssh等，本地备份到远程</td>
        <td><code>rsync [OPTION]... SRC [USER@]host:DEST</code></td>
    </tr>
    <tr>
        <td>通过ssh等，远程拷贝到本地</td>
        <td><code>rsync [OPTION]... [USER@]HOST:SRC DEST</code></td>
    </tr>
    <tr>
        <td>从本地备份到远程rsync服务器</td>
        <td><code>rsync [OPTION]... SRC [USER@]HOST::DEST</code></td>
    </tr>
    <tr>
        <td>从远程rsync服务器拷贝到本地</td>
        <td><code>rsync [OPTION]... [USER@]HOST::SRC DEST</code></td>
    </tr>
    <tr>
        <td>列远程机的文件列表</td>
        <td><code>rsync [OPTION]... rsync://[USER@]HOST[:PORT]/SRC [DEST]</code></td>
    </tr>
</table>

## 本地备份

```shell
# 指定将 ~/testDir1 ~/testDir2 传输到/media/test/testDisk
rsync -av ~/testDir1 ~/testDir2 /media/test/testDisk
```

## SSH远程传输

- `rsync`能够基于ssh等协议快速地实现多台主机间的文件同步工作

<table>
    <td width="30%">-e</td>
    <td width="70%">指定传输数据使用的协议 <code>-e ssh</code></td>
</table>

```shell
# 通过ssh，指定将 ~/testDir1 ~/testDir2 传输到目标主机上的/testDisk目录；
rsync -av ~/testDir1 ~/testDir2 zjk@zjk-pi.local:/testDisk
```

## rsync 服务器

- rsyncd进程：rsync备份服务器守护进程

```shell
# 安装rsync服务器
sudo apt install rsync

# 创建/编写rsync配置文件
sudo vim /etc/rsyncd.conf

# 编写简单身份验证（可选）
sudo vim /etc/rsyncd.secrets
sudo chmod 0600 /etc/rsyncd.secrets

# 编写每日消息文件（可选）
sudo vim /etc/rsyncd.motd

# 创建并设置对应的备份目录
mkdir /home/zjk/MyDisk/backups
chmod 0700 /home/zjk/MyDisk/backups

# 启动rsyncd进程 rsyc --daemon
sudo systemctl start rsync
sudo systemctl enable rsync

# 防火墙允许
sudo ufw allow rsync

# 测试rsyncd进程是否正在监听
rsync 192.168.31.70::

# 进行传输
rsync -av ~/test.txt 192.168.31.70::mybackup_01
```

```shell
# 查看备份目录列表
rsync 192.168.31.70::

# 备份到rsync服务器
rsync -av ~/Documents backupman@192.168.31.70::mybackup_01
```

### /etc/rsyncd.conf

- `/etc/rsyncd.conf`：rsyncd服务器配置文件

<table>
    <tr>
        <th>全局设置</th>
        <th>意义</th>
    </tr>
    <tr>
        <td>hosts allow</td>
        <td rowspan="2">限制可访问的主机</td>
    </tr>
    <tr>
        <td>hosts deny</td>
    </tr>
    <tr>
        <td>strict modes</td>
        <td>若为yes，则rsync服务端将执行严格的权限检查，rsync将拒绝任何其上层目录权限过于开放（例如 world-writable 或者 group-writable）的模块路径，防止由于上级目录权限设置不当导致的安全漏洞</td>
    </tr>
    <tr>
        <td>port</td>
        <td>指定了 rsync 守护进程监听的 TCP 端口号，默认sync 服务器就是在 873 端口上等待客户端的连接请求</td>
    </tr>
    <tr>
        <td>address</td>
        <td>0.0.0.0，使得当前网段的所有主机可以通过服务器的地址来访问</td>
    </tr>
    <tr>
        <td>pid file</td>
        <td><code>/var/run/rsyncd.pid</code>，指定了 rsync 服务器（rsync daemon）运行时生成的进程 ID (PID) 文件的位置</td>
    </tr>
    <tr>
        <td>log file</td>
        <td><code>/var/log/rsyncd.log</code>，指定日志文件的位置</td>
    </tr>
    <tr>
        <td>motd file</td>
        <td><code>/etc/rsyncd.motd</code>自定义每日消息文件</td>
    </tr>
    <tr>
        <th width="15%">[模块名]</th>
        <th width="85%">意义</th>
    </tr>
    <tr>
        <td>path</td>
        <td>模块使用的目录</td>
    </tr>
    <tr>
        <td>comment</td>
        <td>描述</td>
    </tr>
    <tr>
        <td>list</td>
        <td>若为yes，则允许用户查看模块中的文件列表；若为no，则表示隐藏模块</td>
    </tr>
    <tr>
        <td>read only</td>
        <td>no 允许用户将文件上传到模块</td>
    </tr>
    <tr>
        <td>auth users</td>
        <td>指定该模块的用户<code>/etc/rsyncd-users</code></td>
    </tr>
    <tr>
        <td rowspan="2">use chroot</td>
        <td>“chroot监狱”，文件系统内的一个独立环境，包含自己的根文件系统、命令库以及需要运行的所有其他程序；但rsync无法通过符号链接找到chroot环境外的文件</td>
    </tr>
    <tr>
        <td>默认为yes，</td>
    </tr>
    <tr>
        <td>uid = 0</td>
        <td rowspan="2">设置为root或0，可以保留UID和GID，且可以正确管理权限</td>
    </tr>
    <tr>
        <td>gid = 0</td>
    </tr>
    <tr>
        <td>secrets file</td>
        <td><code>/etc/rsyncd.secrets</code>，指定 rsync 服务进行用户认证时使用的密码文件路径，包含用户名及其对应密码的文本文件，用于对访问 rsync 服务器上的特定模块进行身份验证；必须设置为<code>0600</code>权限，否则rsync服务器不承认该文件</td>
    </tr>
</table>


```shell
port = 873
address = 0.0.0.0
pid file = /var/run/rsyncd.pid
log file = /var/log/rsyncd.log
motd file = /etc/rsyncd.motd

[mybackup_01]
	path = /home/zjk/MyDisk/backups
	comment = "server public archive"
	list = yes
	read only = no
	use chroot = no
	uid = 0
	gid = 0
	secrets file = /etc/rsyncd.secrets
    auth users = backupman
```

### /etc/rsyncd.secrets

- `/etc/rsyncd.secrets`：自定义用户密码文件，设置rsyncd模块的访问控制，简单的身份验证和访问控制

```shell
# 身份验证设置 user:password
backupman:tiger
```

```shell
# 确保密码文件的安全性
sudo chmod 600 /etc/rsyncd.secrets
```

### /etc/rsyncd.motd

- `/etc/rsyncd-motd`：每日消息（Message Of The Day，MOTD），在该自定义文件中编辑消息即可

# 扩展选项

## 传输限制

<table>
    <tr>
        <td>--log-file</td>
        <td>每次传输记录的日志文件</td>
    </tr>
    <tr>
        <td>--log-file-format</td>
        <td>指定日志文件的格式</td>
    </tr>
    <tr>
        <td>--partial</td>
        <td>保留传输中断的文件，等待再次连接时继续</td>
    </tr>
    <tr>
        <td>--partial-dir</td>
        <td>指定传输中断的文件存放的目录</td>
    </tr>
    <tr>
        <td width="30%">--max-size</td>
        <td width="70%">最大可传输文件大小</td>
    </tr>
    <tr>
        <td>--min-size</td>
        <td>最小可传输文件大小</td>
    </tr>
    <tr>
        <td>--bwlimit</td>
        <td>传输带宽限制，单位KB</td>
    </tr>
</table>

## --delete 删除不一致文件

<table>
    <tr>
        <td width="30%" colspan="2">--delete</td>
        <td width="70%">删除目标目录有，而源目录没有的文件</td>
    </tr>
    <tr>
        <td rowspan="4">时机</td>
        <td>--delete-before</td>
        <td></td>
    </tr>
    <tr>
        <td>--delete-during</td>
        <td></td>
    </tr>
    <tr>
        <td>--delete-delay</td>
        <td></td>
    </tr>
    <tr>
        <td>--delete-after</td>
        <td>文件同步完成之后删除</td>
    </tr>
    <tr>
        <td rowspan="4">加强</td>
        <td>--delete-excluded</td>
        <td>同样删除被<code>--exclude</code>排除的文件</td>
    </tr>
    <tr>
        <td>--force</td>
        <td>即使目录非空，也强制删除</td>
    </tr>
    <tr>
        <td>--ignore-errors</td>
        <td>即使出现传输错误，也执行<code>--delete</code></td>
    </tr>
    <tr>
        <td>--max-delete</td>
        <td>最多可以被删除的文件数量</td>
    </tr>
</table>

## -b 删除或更新时备份

<table>
    <tr>
        <td width="30%">--backup, -b</td>
        <td width="70%">在删除或更新目标目录已经存在的文件时，将该文件更名后进行备份，默认行为是删除</td>
    </tr>
    <tr>
        <td>--backup-dir</td>
        <td>指定<code>-b</code>文件备份时，存放的目录</td>
    </tr>
    <tr>
        <td>--suffix</td>
        <td>指定<code>-b</code>文件备份时，文件更名的前缀，默认是~</td>
    </tr>
</table>

## --exclude 排除模式

<table>
    <tr>
        <td width="30%">--exclude</td>
        <td width="70%">排除不需要传输的文件模式</td>
    </tr>
    <tr>
        <td>--include</td>
        <td>不排除而需要传输的文件模式，对<code>--exclude</code>的进一步筛选</td>
    </tr>
    <tr>
        <td>--files-from</td>
        <td>排除列表文件，<code>-</code>排除，<code>+</code>不排除</td>
    </tr>
    <tr>
        <td>--exclude-from</td>
        <td>排除某个配置文件中指定的文件模式</td>
    </tr>
    <tr>
        <td>--include-from</td>
        <td>不排除某个配置文件中指定的文件模式</td>
    </tr>
</table>

```shell
# 排除file1.txt、dir1目录下的所有文件（并没有排除dir1目录）、以.开头的文件
rsync -av --exclude={'file1.txt', 'dir1/*', '.*'} source/ destination

# 如果排除模式很多，可以将它们写入一个文件，每个模式一行
rsync -av --exclude-from='exclude-file.txt' source/ destination
```

```shell
# 排除列表文件

# 排除的文件
- /*

# 不排除的文件
+ /home
```

## --link-dest 基准目录

- `rsync`使用增量备份，默认直接比较源目录和目标目录；可以通过`--link-dest`指定基准目录，将源目录与基准目录之间变动的部分，同步到目标目录
- 基准目录方式：第一次同步是全量备份，所有文件在基准目录里面同步一份；以后每一次同步都是增量备份，只同步源目录与基准目录之间有变动的部分，将这部分保存在一个新的目标目录（包含所有文件）；而实际上，只有那些变动过的文件是存在于该目标目录，其他没有变动的文件都是指向基准目录文件的硬链接。

<table>
    <tr>
        <td width="30%">--link-dest</td>
        <td width="70%">指定同步时的基准目录</td>
    </tr>
</table>

```bash
#!/bin/bash

# A script to perform incremental backups using rsync

set -o errexit
set -o nounset
set -o pipefail

readonly SOURCE_DIR="${HOME}"
readonly BACKUP_DIR="/mnt/data/backups"
readonly DATETIME="$(date '+%Y-%m-%d_%H:%M:%S')"
readonly BACKUP_PATH="${BACKUP_DIR}/${DATETIME}"
readonly LATEST_LINK="${BACKUP_DIR}/latest"

mkdir -p "${BACKUP_DIR}"

rsync -av --delete \
  "${SOURCE_DIR}/" \
  --link-dest "${LATEST_LINK}" \
  --exclude=".cache" \
  "${BACKUP_PATH}"

rm -rf "${LATEST_LINK}"
ln -s "${BACKUP_PATH}" "${LATEST_LINK}"

# 每一次同步都会生成一个新目录${BACKUP_DIR}/${DATETIME}，并将软链接${BACKUP_DIR}/latest指向这个目录。下一次备份时，就将${BACKUP_DIR}/latest作为基准目录，生成新的备份目录。最后，再将软链接${BACKUP_DIR}/latest指向新的备份目录。
```