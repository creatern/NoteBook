# Git基础

## Git初始化

- 设置Git用户名、用户签名：首次安装Git时必须设置用户签名，否则无法提交代码。

```git
git config --global user.name 用户名
git config --global user.email 邮箱(不会检验邮箱是否正确)
```

- 初始化本地库：目标文件目录位置再右键打开Git Bash，自动切换到该路径。

```git
git init
```

## 基础命令

| 基础命令                          | 说明           |
| :-------------------------------- | :------------- |
| git status                        | 查看本地库状态 |
| git add 文件名                    | 添加到暂存区   |
| git -rm --cached 文件             | 从暂存区移除   |
| git commit [-m "日志信息"] 文件名 | 提交到本地库   |

## 版本控制

- 版本指针的移动：在完成提交之后，会有新的历史版本，同时指针指向该版本。
- 指针文件：`Xxx/.git/refs/heads/分支`。

| 命令       | 说明             |
| :--------- | :--------------- |
| git reflog | 查看版本信息     |
| git log    | 查看版本详细信息 |
| git reset  | 版本穿梭         |

<img src="../../pictures/2860321240278.png" width="600"/> 

## 分支

- 分支：在开发过程中，任何分支的修改都不会对其他分支有影响。

> 并行推进多个分支，提高开发效率。

| 命令         | 说明          |
| :----------- | :------------ |
| git branch   | 创建/查看分支 |
| git checkout | 切换分支      |
| git merge    | 分支合并      |

<img src="../../pictures/144484721236833.png" width="600"/> 

### 冲突合并

- 冲突合并：合并分支时，两个分支在同一个文件的同一个位置有两套完全不同的修改。Git无法决定要保留哪个修改，进入MERGING状态，由我们选择保留/修改的内容。只会修改合并到的分支的文件，要被合并的分支不受影响。

## GitHub/Gitee 远程仓库

| 命令       | 说明                                                         |
| :--------- | :----------------------------------------------------------- |
| git remote | 远程地址                                                     |
| git push   | 推送本地仓库上的内容到远程仓库<br />本地库的版本必须高于远程库的版本。 |
| git pull   | 拉取远程库到本地库、且直接合并后自动提交到本地库             |
| git clone  | 克隆远程仓库到本地                                           |

> 版本穿梭之后，本地仓库和远程仓库版本不一致时，只能使用强制推送覆盖远程仓库。
>
> ```shell
> git push -f 远程仓库地址 远程分支
> ```

### SSH

> 对于网络连接情况不好的，尽量使用SSH，而不是HTTP协议。

| ssh文件    | 说明 |
| ---------- | ---- |
| id_rsa     | 私钥 |
| id_rsa.pub | 公钥 |

- 生成.ssh文件

```git
ssh-keygen -t rsa -C 邮箱
```

```git
zjk10@DESKTOP-HSVIOTH MINGW64 ~
$ ssh-keygen -t rsa -C zjk1054860443@163.com
Generating public/private rsa key pair.
Enter file in which to save the key (/c/Users/zjk10/.ssh/id_rsa): 生成的ssh文件路径（回车跳过）
Created directory '/c/Users/zjk10/.ssh'. 
Enter passphrase (empty for no passphrase): （回车跳过）
Enter same passphrase again: （回车跳过）
Your identification has been saved in /c/Users/zjk10/.ssh/id_rsa
Your public key has been saved in /c/Users/zjk10/.ssh/id_rsa.pub
The key fingerprint is:
SHA256:Jo45BrJ4Vp9MtMque+T74/vDotYPCrI8wyBMtl6VE8U zjk1054860443@163.com
The key's randomart image is:
+---[RSA 3072]----+
|      o.         |
|     . E         |
|      +          |
| o   = .         |
|= o o = S        |
|+= =.O +         |
|O =oB.*.         |
|.X ++o+.o        |
|..====+*o.       |
+----[SHA256]-----+
```

- 获取公钥：

```git
zjk10@DESKTOP-HSVIOTH MINGW64 ~
$ cd .ssh

zjk10@DESKTOP-HSVIOTH MINGW64 ~/.ssh
$ ll
total 5
-rw-r--r-- 1 zjk10 197609 2610 Nov  9 23:27 id_rsa
-rw-r--r-- 1 zjk10 197609  575 Nov  9 23:27 id_rsa.pub

zjk10@DESKTOP-HSVIOTH MINGW64 ~/.ssh
$ cat id_rsa.pub
ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQDCo4Y42+qZM6Z0Y3dXnkOnWrP1zhJHfz3v6bRRkHt8o+OtLN1nKK51Lcf05E/ObMsaReKSMwwIomxHYKcpU82A8G5/TekMaYcESSrIgM2jm0k/CTAbSgMC1C0VtSC7ORWUAW6HdNcolusF7lD80Bs61P7A8gr2jYryHBbQfM2vp7n/9BsulTshxPT2azVAhHiz/iaC915eQuLEwvnQMsdGKsplECz2V1WcH8hzy8txiHQJDLYkth5wnBfzBNC8+NjDfAeIAvrk16p9s364grQtt1Hj1lCTeWuqhvQ/JnoyM5G6jQHMxzUIpUpejZF6CJdPpDAYsRm/wTLKeerBpapUh7/TS1nnxbIxiXg6b32UTzdwllggWox7Qj2HbBpdg6MMdCPUlyZWUVpgzjUHf6LSvr0g2h3wSKLWLZWMKbHih2REjKawAGV+6y5JzzfVFSYYN0PnQQwtLHbiJoygRxZ6290tRfpW1vcLvC7/wXFzSASB1WZbvRRBm4HgIAKa8Pc= zjk1054860443@163.com
```

<img src="../../pictures/Snipaste_2023-06-06_15-50-33.png" width="1200"/> 

# config 命令别名

- 命令别名：简化git命令的使用。文件位置：用户目录下的.gitconfig。

| 语句                                                         | 操作                                       |
| ------------------------------------------------------------ | ------------------------------------------ |
| `git config --global alias.命令别名 '原git命令'`             | 新建全局命令别名                           |
| `git config --global --unset alias.命令别名`                 | 删除全局命令别名                           |
| `git config --local alias.命令别名 '原git命令'`<br />`git config alias.命令别名 '原git命令'` | 新建局部命令别名<br />（只对当前仓库有效） |
| `git config --global --remove-section alias`                 | 删除所有全局别名                           |
| `git config --remove-section alias`                          | 删除所有局部别名                           |

- 相当于`~/.gitconfig`中：

```
[alias]
    book = "!f(){git add --all;git commit -m "$1";git push book;};f"
```

# \.ignore 忽略文件

```
*.class

*.log

*.ctxt

.mtj.tmp/

*.jar
*.war
*.nar
*.ear
*.zip
*.tar.gz
*.rar

hs_err_pid*

.classpath
.project
.settings
target
.idea
*.iml
```

- `~/.gitconfig`文件引用的忽略配置文件：

```
[user]
    name = scott
    email = zjk1054860443@163.com
[core]
    excludesfile = C:/Users/zjk10/git.ignore （忽略配置文件的路径）
```

# IDEA&Git

## 版本控制

- 查看版本信息：

<img src="../../pictures/334981304248072.png" width="1200"/> 

- 版本穿梭：

<img src="../../pictures/98881304250468.png" width="1200"/> 

## 冲突合并

<img src="../../pictures/405683004235714.png" width="522"/> 

## 口令登录 Token

<img src="../../pictures/120503904225012.png" width="600"/> 

- 设置口令权限：

<img src="../../pictures/463824304238531.png" width="700"/> 

- 获取口令并在IDEA中设置：

<img src="../../pictures/256234504239826.png" width="700"/> 


