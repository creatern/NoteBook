# SQL概述

<table>
    <tr>
        <td width="35%">数据定义语言<br/>（Data-Definition Language，DDL）</td>
        <td width="65%">SQL DDL提供定义关系模式、删除关系以及修改关系模式的命令</td>
    </tr>
    <tr>
        <td>数据操纵语言<br/>（Data-Manipulation Language，DML）</td>
        <td>SQL DML提供从数据库中查询信息以及在数据库中插入元组、删除元组、修改元组的能力</td>
    </tr>
</table>

# DDL

<table>
    <tr>
        <td width="30%">完整性（integrity）</td>
        <td width="70%">SQL DDL包括定义完整性约束的命令，保存在数据库中的数据必须满足所定义的完整性约束。破坏完整性约束的更新是不允许的。</td>
    </tr>
    <tr>
        <td>视图定义（view definition）</td>
        <td>SQL DDL包括定义视图的命令</td>
    </tr>
    <tr>
        <td>事务控制（transaction control）</td>
        <td>SQL包括定义事务的开始点和结束点的命令</td>
    </tr>
    <tr>
        <td>嵌入式SQL（embedded SQL）</td>
        <td></td>
    </tr>
    <tr>
        <td>动态SQL（dynamic SQL）</td>
        <td></td>
    </tr>
    <tr>
        <td>授权（authorization）</td>
        <td>SQL DDL包括定义对更新和视图的访问权限的命令</td>
    </tr>
</table>

- SQL DDL不仅能够定义关系的集合，还能够定义有关每个关系的信息，包括每个更新的模式、每个属性的取值类型、完整性约束、为每个更新维护的索引集合、每个关系的安全性和权限信息、每个关系在磁盘上的物理存储结构。

## 基本类型

<table>
    <tr>
        <td width="20%" rowspan="2">char(n)</td>
        <td width="80%">具有用户指定长度n的固定长度的字符串（character）</td>
    </tr>
    <tr>
        <td>char类型会在字符串的内容长度未达到n时，追加适当的空格来补全以达到n长度；且在比较两个不同长度的char类型时，会在短值char后追加空格以使得长度相等</td>
    </tr>
    <tr>
        <td>varchar(n)</td>
        <td>具有用户指定的最大长度n的可变长度的字符串（character varying）</td>
    </tr>
    <tr>
        <td>int</td>
        <td>整数（依赖于机器的整数的有限子集），integer</td>
    </tr>
    <tr>
        <td>smallint</td>
        <td>小整数（依赖于机器的整数类型的子集）</td>
    </tr>
    <tr>
        <td>numeric(p,d)</td>
        <td>具有用户指定精度的定点数。该数有p为数学（加上一位符号位），并且小数点右边有p位中的d位数字</td>
    </tr>
    <tr>
        <td>real</td>
        <td rowspan="2">浮点数与双精度浮点数，精度依赖于机器</td>
    </tr>
    <tr>
        <td>double precision</td>
    </tr>
    <tr>
        <td>float(n)</td>
        <td>精度至少为n位数字的浮点数</td>
    </tr>
</table>

## 基本模式定义
