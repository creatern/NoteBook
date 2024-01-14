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

# 扩展选项

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

## 传输限制

<table>
    <tr>
        <td width="30%">--max-size</td>
        <td width="70%">最大可传输文件大小</td>
    </tr>
    <tr>
        <td>--min-size</td>
        <td>最小可传输文件大小</td>
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