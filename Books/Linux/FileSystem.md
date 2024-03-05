# Linux文件系统的类型

- Linux支持多种文件系统，每种文件系统都在存储设备上实现了虚拟目录结构，只是特性略有不同。

## 早期的ext文件系统

### ext

- ext（extended filesystem，扩展文件系统）：为Linux提供了一个基本的类Unix文件系统，使用虚拟目录处理物理设备并在其中以固定大小的磁盘块（fixed-length block）形式保存数据。

#### inode

- inode（i节点）：ext使用inode跟踪存储在虚拟目录中文件的相关信息。i节点系统在每个物理存储设备中创建一个单独的表（i节点表）来保存文件信息。虚拟目录中的每个文件在i节点表中有对应的条目。
- ext跟踪的每个文件的额外数据包括以下内容：文件名、文件大小、文件属主、文件属组、文件访问权限、指向存有文件数据的每个块的指针。
- i节点号：Linux通过唯一的数值（i节点号）来引用i节点表中的i节点，这个值是创建文件时由文件系统分配的。文件系统是通过i节点号而非文件名和路径来标识文件的。

### ext2

- ext2在保持与ext相同的文件系统结构的同时，在功能上做了扩展：

1. 在i节点表中加入了文件的创建时间、修改时间、最后一次访问时间。
2. 允许的最大文件大小增至2TB，后期又增大到32TB。
3. 保存文件时按组分配磁盘块。

- ext2的限制：如果系统在存储文件和更新i节点表之间发生了某些事件，则两者内容可能无法同步，潜在的结果就是丢失文件在磁盘上的数据位置。（容易在系统崩溃或断电时损坏）

## 日志文件系统



# 常用的文件系统

## Ext4

- Ext4属于日志文件系统，大部分Linux发行版的默认文件系统；数据变更写入磁盘之前，Ext4会通过日志记录所有变更。

<img src="../../pictures/20240305124911.png" width="430"/> 

1. 大容量支持：Ext4支持的单个文件最大可达16TB，而文件系统的最大容量可以达到1EB（1EB = 1024PB = 1,048,576TB），这对于处理大规模数据存储需求提供了很好的支持。
2. 延迟分配（Delayed Allocation）：这是一种性能优化技术，可以减少磁盘碎片，提高文件系统的写入性能。延迟决定文件数据在磁盘上的具体位置，直到数据真正写入磁盘之前。
3. 块预分配（Preallocation）：允许应用程序预先分配一定数量的连续磁盘空间给文件，即使文件实际尚未增长到那么大。对于需要高性能写操作的应用（如数据库和多媒体应用）特别有用。
4. 可扩展的属性（Extended Attributes）：支持为文件和目录附加元数据，如安全性信息、描述信息等。
5. 日志（Journaling）：Ext4继承了Ext3的日志功能，可以在系统崩溃或意外断电后快速恢复文件系统的一致性，减少数据丢失的风险。
6. 目录索引（Directory Indexing）：使用哈希表来加速对大目录的访问速度，提高了在包含大量文件的目录中搜索文件的效率。
7. 碎片整理（Defragmentation）：支持在线碎片整理，可以在不卸载文件系统的情况下整理磁盘碎片，改善长时间运行系统的性能。

## XFS

- XFS属于日志文件系统，高性能的UNIX 64位文件系统。XFS专为高吞吐量和大容量存储而设计，支持大量的并发I/O操作，因此非常适合大型文件的存储和处理。

1. 高性能：XFS使用先进的索引技术和延迟写入策略，优化了大文件的存储和访问速度，特别适合大型数据库和视频编辑等对性能要求极高的应用。
2. 大容量支持：XFS支持的文件和文件系统大小远超过大多数其他文件系统，最大文件系统容量可达到数百TB，单个文件大小可达到数十TB。
3. 动态inode分配：XFS在文件系统创建时不固定inode数量，而是根据需要动态分配，提高了存储效率。
4. 日志（Journaling）：XFS记录文件系统的变更，以便于在系统崩溃后快速恢复，提高了数据的可靠性。
5. 在线操作：XFS支持在线扩展和缩减文件系统，以及在线检查和修复文件系统，这些操作可以在不中断文件系统服务的情况下进行。
6. 延迟分配和预取：XFS具有延迟分配（Delayed Allocation）和预取（Prefetching）技术，这有助于减少碎片和优化磁盘I/O性能。
7. 配额管理：XFS提供了强大的磁盘配额管理功能，支持基于用户和组的配额限制。

## Btrfs

- Btrfs（B-tree File System）属于COW文件系统（Copy-on-write，写入时复制），Btrfs是一个开源的、现代的文件系统，针对管理大容量数据而设计。

<img src="../../pictures/Linux-filesystem-Btrfs.drawio.svg" width="500"/> 

1. 快照和克隆：Btrfs支持创建文件系统的快照（只读副本）。快照可以用于备份和恢复数据，或用于测试中的数据回滚。Btrfs还支持克隆（快照的可写副本）。
2. 子卷：Btrfs允许在单个文件系统内创建多个子卷，这些子卷共享同一存储池的空间。子卷可以用于隔离不同用户的数据或不同类型的数据。
3. 数据和元数据校验：Btrfs通过对数据和元数据进行校验，提供对数据完整性的保护，有助于检测和修复损坏的数据。
4. 动态inode分配：与传统文件系统固定inode数量不同，Btrfs动态分配inode，这意味着理论上inode的数量是无限的，提高了文件系统的灵活性和扩展性。
5. 透明压缩：Btrfs支持数据的透明压缩，可以减少磁盘空间的使用，提高存储效率。
6. 在线卷扩展和缩减：Btrfs文件系统可以在不中断服务的情况下动态调整大小，这对于云计算和虚拟化环境非常有用。
7. RAID支持：Btrfs原生支持RAID 0、RAID 1、RAID 10、RAID 5和RAID 6，无需额外的硬件或软件RAID解决方案。

## FAT16/32

- FAT32是最通用的文件系统，通常用于便携式媒体，但文件大小限制在4GB以内

## exFAT

- exFAT是FAT32的升级版，64位，轻量级的文件系统，适用于U盘和SD媒体；没有日志或COW

## ZFS

- ZFS（Zettabyte File System）是一个高级文件系统和逻辑卷管理器，目标是解决数据损坏、提供高存储容量以及管理简便性等问题。

<img src="../../pictures/Linux-filesystem-zfs.drawio.svg" width="500"/> 

1. 数据完整性：ZFS通过校验和（checksums）来确保数据的完整性。每个文件块在写入时都会计算校验和，并在后续读取时验证，确保数据未被损坏或更改。
2. 存储池：ZFS使用存储池（pools）的概念来管理物理存储，可以将多个硬盘组合成一个逻辑存储池，而不是传统的单个卷或分区。提供了高度的灵活性和容易扩展的存储解决方案。
3. 快照和克隆：ZFS支持快照（snapshots）和克隆（clones），对于数据备份、恢复和测试环境非常有用。快照允许用户在特定时间点创建文件系统的只读副本；而克隆则是基于这些快照的可写副本。
4. 动态条带大小（Dynamic Striping）：ZFS可以根据需要动态调整条带的大小，以优化存储利用率和性能。
5. 内置压缩和去重：ZFS支持数据的实时压缩和去重，这可以显著提高存储效率，尤其是在存储大量重复数据的环境中。
6. RAID-Z：ZFS的RAID-Z是传统RAID的改进版本，提供了更高的数据保护和修复能力，特别是在处理大型磁盘阵列时。
7. 自动修复：ZFS可以自动检测并修复数据损坏，尤其是在配备了冗余存储（如RAID-Z）的情况下。
8. 大容量存储：ZFS设计之初就考虑到了扩展性，理论上可以支持高达Zettabyte（10<sup>21</sup>字节）的存储容量。
9. 高效的IO调度：ZFS采用了先进的IO调度算法，可以优化不同类型负载的性能，包括随机读写和顺序读写。
10. 透明加密：ZFS支持基于数据集的透明加密，确保存储在ZFS上的数据安全性。

## F2FS

- F2FS（Flash-Friendly File System）是一种专为基于NAND闪存的存储设备（如固态硬盘SSD、eMMC和SD卡）设计的文件系统。F2FS旨在克服传统文件系统在闪存设备上的性能瓶颈和寿命限制，通过一系列优化措施来充分利用NAND闪存的特性。

<img src="../../pictures/Linux-filesystem-f2fs.drawio.svg" width="600"/> 

1. 日志结构文件系统（LFS）：F2FS采用日志结构文件系统的设计，将所有的写入操作顺序地组织成日志形式，这有助于减少随机写入，提高写入性能，并减少对闪存的磨损。
2. 写入放大（Write Amplification）的优化：通过减少不必要的数据擦写和移动，F2FS能够降低写入放大效应，延长闪存设备的使用寿命。
3. 多层块管理：F2FS将存储空间分为多个区域（如热数据区、温数据区和冷数据区），根据数据的访问频率将数据智能地分配到不同区域，以优化读写性能和提高存储效率。
4. 垃圾回收（GC）策略：F2FS实现了一种有效的垃圾回收机制，以回收无用数据占用的空间，确保持续的高性能写入操作。
5. 支持TRIM/Discard命令：F2FS支持TRIM命令，可以通知底层存储设备哪些数据块不再使用，从而允许存储设备进行内部优化，提高性能和寿命。
6. 检查点（Checkpoint）机制：F2FS利用检查点机制来减少文件系统一致性检查（fsck）的时间，通过记录文件系统的状态来快速恢复文件系统。

# /proc/filesystems

- nodev：虚拟文件系统，只存在于内存，由Systemd管理

```shell
# 查看已安装的文件系统
cat /proc/filesystems
```

# 挂载/卸载文件系统

- 文件系统必须先挂载到正在运行的文件系统，然后才能访问，且需要一个挂载点
- <span name="挂载点">挂载点</span>：

1. 每个挂载点只能挂载一个文件系统，若挂载了多个，则被最后挂载的文件系统覆盖。
2. 挂载点的目录通常是[/mnt和/media](./文件管理.md)，只是惯例；`/mnt`通常用于静态挂载（[/etc/fstab](#/etc/fstab)），而`/media`通常用于自动挂载可移动媒体

## mount

- <span name="mount"><code>mount</code></span>：虚拟目录[挂载点](#挂载点)通常是在/mnt中新建一个目录来挂载。

```
mount [-lhV]
mount -a [options]
mount [options] [--source] <source> | [--target] <directory>
mount [options] <source> <directory>
mount <operation> <mountpoint> [<target>]
```

## umount

- <code>umount</code>（unmount）：与mount挂载命令需要同时提供设备名与挂载目录不同，umount卸载命令只需要提供设备名或挂载目录之一即可完成卸载。

```
umount [参数] 设备或目录名
```

<table><tbody><tr><td>-a</td><td data-immersive-translate-effect="1" data-immersive_translate_walked="7c05567e-e5ff-4670-b322-b61cd71b9233">卸载“/etc/mtab”文件中记录的所有设备</td></tr><tr><td>-F</td><td>强制卸载设备而不询问</td></tr><tr><td>-h</td><td>显示帮助信息</td></tr><tr><td>-n</td><td>卸载时不要将信息写入“/etc/mtab”文件中</td></tr><tr><td>-r</td><td>使用只读方式重新挂载文件系统</td></tr><tr><td>-t</td><td>仅卸载指定的文件系统</td></tr><tr><td>-v</td><td>显示执行过程详细信息</td></tr><tr><td>-V</td><td>显示版本信息</td></tr></tbody></table>

```shell
# 卸载指定的文件系统并显示过程
umount -v /dev/sdb
```

## mountpoint

- <span name="mountpoint"><code>mountpoint</code></span>：查看指定目录是否为挂载点

```shell
mountpoint /
```

## <span name="/etc/fstab">/etc/fstab</span>

- `/etc/fstab`：自动挂载文件，在该文件内设置的挂载点和设备会在系统启动时自动挂载 

```shell
# 测试/etc/fstab配置
sudo findmnt --verbose --verify

# 挂载/etc/fstab内的新配置
sudo mount -a
```

<img src="../../pictures/Snipaste_2022-11-29_00-08-22.png" width="850"/> 

<table>
    <tr>
        <td width="20%" rowspan="2">device</td>
        <td width="80%" colspan="2">UUID或文件系统标签；不要使用/dev名称，该类名称不唯一且会改变</td>
    </tr>
    <tr><td colspan="2"><code>lsblk -o UUID,LABEL</code></td></tr>
    <tr>
        <td>mountpoint</td>
        <td colspan="2">挂载点</td>
    </tr>
    <tr>
        <td>type</td>
        <td colspan="2">文件系统类型，设置为<code>auto</code>可自动检测文件系统类型</td>
    </tr>
    <tr>
        <td rowspan="15">options</td>
        <td colspan="2">挂载选项列表，定义权限，逗号分割</td>
    </tr>
    <tr><td>defaults</td><td>默认选项，包括<code>rw、suid、dev、exec、auto、nouser、async</code>，在之后追加其他选项会覆盖默认值；或者去除default仅设置部分权限</td></tr>
    <tr><td>rw</td><td>读写</td></tr>
    <tr><td>ro</td><td>只读</td></tr>
    <tr><td>suid</td><td>允许setuid、setgid 位操作</td></tr>
    <tr><td>dev</td><td>块设备和字符设备</td></tr>
    <tr><td>exec</td><td>允许二进制文件运行</td></tr>
    <tr><td>auto</td><td>指定哪些文件系统应在引导时启动</td></tr>
    <tr><td>nouser</td><td>非root用户不能挂载或卸载文件系统</td></tr>
    <tr><td>async</td><td>异步I/O，Linux的标准模式</td></tr>
    <tr><td>user</td><td>非root用户可以挂载或卸载自己的设备</td></tr>
    <tr><td>users</td><td>任何用户都可以挂载或卸载设备</td></tr>
    <tr><td>noauto</td><td>禁止在启动时自动挂载</td></tr>
    <tr><td>noatime</td><td>不更新文件的“time accessed”属性，以提供性能（只提高一点点）</td></tr>
    <tr><td>gid</td><td>仅限指定的组可以访问</td></tr>
    <tr>
        <td rowspan="2">dump</td>
        <td colspan="2">备份设置</td>
    </tr>
    <tr>
        <td colspan="2"><code>0</code>：不进行备份；<code>1</code>：每天备份一次；<code>2</code>每两天备份一次；以此类推</td>
    </tr>
    <tr>
        <td rowspan="3">pass</td>
        <td colspan="2">文件系统检查器<code>fsck</code>在启动时首先检查哪个文件系统</td>
    </tr>
    <tr><td colspan="2"><code>0</code>：不运行检查；<code>1</code>：每次都运行检查；<code>2</code>：仅在非正常关机、达到最大加载次数、一定天数后运行检查</td></tr>
    <tr><td colspan="2">推荐设置：根文件系统 1，任何其他Linux文件系统 2，非Linux文件系统 0</td></tr>
</table>


# 调整文件系统大小

- 扩展文件系统：

1. 文件系统Ext4、XFS、Btrfs，允许在离线或在线时扩展；而文件系统FAT16/32只能离线且必须卸载之后再调整大小
2. 必须确保分区末尾有可用空间

```shell
# Ext4
sudo resize2fs /dev/sdc

# XFS
sudo xfs_growfs -d /dev/sdc

# Btrfs
sudo btrfs filesystem resize max /dev/sdc

# FAT16/32，必须先卸载
sudo fatresize -i /dev/sdc
```

- 缩小文件系统：

1. XFS文件系统只能扩展而不能缩小；Ext4、FAT16/32缩小前必须先卸载；Btrfs可以在线缩小
2. 必须确保文件系统已使用的空间小于收缩后的大小；为保存元数据、浪费的块空间以及以防万一，需要留出大约40%的空间，即 `已使用空间 * 1.4`
3. 若分区位于外部设备，则先卸载；若分区属于正在运行的系统，则必须从可引导的救援磁盘上运行`parted`，或通过另一个启动的Linux来执行

# tune2fs

- `tune2fs`：调整/查看磁盘的文件系统参数

# mkfs

- mkfs（make file system）：对设备进行格式化文件系统操作

<table><tbody><tr><td>-c</td><td>检查指定设备是否损坏</td></tr><tr><td>-t</td><td>设置档案系统的模式</td></tr><tr><td>-V </td><td>显示执行过程详细信息</td></tr><tr><td>--help</td><td>显示帮助信息</td></tr><tr><td>--version</td><td>显示版本信息</td></tr></tbody></table>

```shell
# 查看当前系统中支持mkfs的文件系统格式
ls -l /usr/sbin/mkfs.*

# 对指定的硬盘进行格式化文件系统操作，并输出详细过程信息
sudo mkfs -V -t xfs /dev/sdb
```

# e2label ntfs格式、ext2/3/4格式
