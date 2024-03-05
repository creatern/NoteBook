# MySQL Shell

<table><colgroup><col style="width: 10%"><col style="width: 80%"></colgroup><thead><tr>
          <th>提示符</th>
          <th>意义</th>
        </tr></thead><tbody><tr>
          <td><code class="literal">mysql&gt;</code></td>
          <td>准备好接受新查询</td>
        </tr><tr>
          <td><code class="literal">-&gt;</code></td>
          <td>等待下一行多行查询</td>
        </tr><tr>
          <td><code class="literal">'&gt;</code></td>
          <td>等待下一行，等待以单引号 ( <code class="literal">'</code>)开头的字符串的完成；可以通过<code class="literal">'\c</code>来断开</td>
        </tr><tr>
          <td><code class="literal">"&gt;</code></td>
          <td>等待下一行，等待以双引号 ( <code class="literal">"</code>)开头的字符串的完成</td>
        </tr><tr>
          <td><code class="literal">`&gt;</code></td>
          <td>等待下一行，等待以反引号 ( <code class="literal">`</code>)开头的标识符的完成</td>
        </tr><tr>
          <td><code class="literal">/*&gt;</code></td>
          <td>等待下一行，等待以 开头的评论完成<code class="literal">/*</code></td>
</tr></tbody></table>

# 连接MySQL Shell


```shell
# 连接MySQL
mysql [-h 主机地址] [-u 用户 -p] [数据库名] [< 文件 | 文件 | 文件] [> 文件]
```

<table>
    <tr>
        <th width="20%">连接选项</th>
        <th width="80%">意义</th>
    </tr>
    <tr>
        <td>-h 主机地址</td>
        <td>指定连接的服务器主机地址；若忽略，则使用本地地址</td>
    </tr>
    <tr>
        <td>-u 用户 -p</td>
        <td>指定通过用户密码方式登录；若忽略，则尝试匿名登录</td>
    </tr>
    <tr>
        <td><span name="连接时指定数据库">数据库名</span></td>
        <td>选择待会使用的数据库；若忽略，则需要通过<code>use database</code>选择使用的数据库</td>
    </tr>
    <tr>
        <td rowspan="3">&lt; 文件 | 文件 | 文件</td> 
        <td>批处理模式，MySQL从该文件中读取要执行的命令</td>
    </tr>
    <tr>
        <td><code>mysql -t</code>：以批处理模式获取交互式输出格式</td>
    </tr>
    <tr>
        <td><code>mysql -v</code>将执行的语句回显到输出中</td>
    </tr>
    <tr>
        <td>&gt; 文件</td>
        <td>捕获输出到指定文件</td>
    </tr>
</table>
```mysql
-- 断开连接的3种方式
exit
quit
\q
```

# 脚本与文件

```mysql
-- MySQL执行脚本文件的两种方式
source 文件
\. 文件
```

```mysql
select * from mytb
into outfile '导出到的文件位置';
```

- `--secure-file-priv`：MySQL的一个安全选项，用于限制数据的导入和导出；该选项被设置后，MySQL只能在指定的目录下进行文件的操作。

```mysql
# 查看变量名包含secure_file_priv的变量
show variables like 'secure_file_priv'
```

<table>
    <tr>
        <th width="25%"><Code>--secure-file-priv</Code>的值</th>
        <th width="65%">意义</th>
    </tr>
    <tr>
        <td>null</td>
        <td>禁止所有文件导入和导出</td>
    </tr>
    <tr>
        <td>具体的目录路径</td>
        <td>只允许在该目录及其子目录下进行文件操作</td>
    </tr>
    <tr>
        <td>空字符串 <code>''</code></td>
        <td>不限制文件操作的范围，但某些版本的MySQL可能不允许该选项</td>
    </tr>
</table>


