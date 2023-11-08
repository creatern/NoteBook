# 二叉树 概述

- 二叉树是有序树，有左右子树之分，一个结点最多有两个子结点。

| 二叉树     | 图示                                                         |
| ---------- | ------------------------------------------------------------ |
| 斜树       | <img src="../../pictures/Snipaste_2023-05-28_12-44-04.png" width="400"/> |
| 满二叉树   | <img src="../../pictures/Snipaste_2023-05-28_12-48-37.png" width="250"/> |
| 完全二叉树 | <img src="../../pictures/20231009234832.png" width="400"/><img src="../../pictures/Snipaste_2023-05-28_14-21-25.png" width="200"/> |


1. 非空二叉树上的叶子结点（度为0）数等于度为2的结点数加1：$n_0 = n_2 + 1$。

> 设n为树的总结点数，$n_0$为度为0的结点数，$n_1$为度为1的结点数，$n_2$为度为2的结点数
> $$
> \begin{matrix}
> 结点：& n = n_0 + n_1 + n_2 \\
> 分支：& n - 1 = n_1 + 2 \times n_2
> \end{matrix}
> $$
> 分支：
>
> - 从进入的角度：只有根结点没有入边，故分支数为 n - 1。
> - 从出发的角度：度为2的结点发出2条分支，故$2n_2$；度为1的结点发出1条分支，故$n_1$。

2. 非空二叉树上第k层上至多有 $2^{k-1}，( k \ge 1 )$ 个结点。
3. 深度为k的二叉树至多有$2^k - 1，( k \ge 1 )$个结点。 

>- 至多：假设该树为满二叉树。
>
>- 二叉树第1层：只有一个根结点。

**满二叉树**

- 所有分支结点都存在左子树和右子树，且所有的叶子节点都处于最底层。（只有度为0和2的结点）满二叉树的每层都含有最多的结点，若高度为h，则有$2^h-1$个结点。
- 对满二叉树按层序编号，从根结点（编号1）自上而下，自左向右。对于编号为i的结点：若有双亲，则其双亲编号$\frac{i}{2}$；若有左孩子，则左孩子编号$2i$；若有右孩子，则右孩子编号$2i+1$。

**完全二叉树**

- 对一棵具有n个结点的二叉树按层序编号，编号为i的结点与（该二叉树同样深度的）满二叉树中编号为i的结点在二叉树中的位置完全相同。

1. 深度为k的完全二叉树在第k-1层是满二叉树。（$满二叉树 \subseteq 完全二叉树$
2. 叶子结点只可能在层次最大的两层上出现，且最大层次中的叶子结点都集中在该层左侧连续的位置。
3. 若有度为1的结点，则只可能有一个，且该结点只有左孩子而无右孩子。 
4. 按层序编号后，如果编号为i的结点为叶子结点或其只有左孩子，则编号大于i的结点均为叶子结点。
5. 若n为奇数，则每个分支结点都有左孩子和右孩子；若n为偶数，则编号最大（$\frac{n}{2}$）的分支结点只有左孩子，没有右孩子。

6. 具有n( n > 0 )个结点的完全二叉树的深度为$\lfloor \log_{2}^{n} \rfloor + 1$。

> 设具有n个结点的完全二叉树的深度为k：
> $$
> \begin{matrix}
> & 2^{k-1} \le n \lt 2^k \\
> 取对数：& k-1 \le \log_2^n \lt k \\
> 即：& \log_2^n \lt k \le \log_2^n + 1
> &
> \end{matrix}
> $$

7. 对完全二叉树按层序编号，对结点i则有以下关系:
   1. i > 1：结点i的双亲的编号为$\lfloor \frac{i}{2} \rfloor$，否则该结点为根结点。
      - i为偶数：双亲的左孩子。
      - i为奇数：双亲的右孩子。
   2. $2i \le n$：结点i 的左孩子编号为$2i$,，否则无左孩子。
   3. $2i+1 \le n$：结点i的右孩子编号为$2i + 1$，否则无右孩子。

<img src="../../pictures/Snipaste_2022-12-31_22-00-13.png" width="600"/> 

> <img src="../../pictures/Snipaste_2023-05-28_16-10-33.png" width="600"/>

# 二叉树遍历

| 遍历方式     | 说明                                                         |
| ------------ | ------------------------------------------------------------ |
| 先序遍历 DLR | <img src="../../pictures/Snipaste_2023-03-27_23-20-56.png" width="200"/> |
| 中序遍历 LDR | <img src="../../pictures/Snipaste_2023-03-27_23-21-47.png" width="250"/> |
| 后序遍历 LRD | <img src="../../pictures/Snipaste_2023-03-27_23-22-35.png" width="230"/> |
| 层次遍历     | <img src="../../pictures/Snipaste_2023-03-27_23-23-31.png" width="260"/> |

```java
void DLRTree(BinarySortTreeNode node) {
    if (node != null) {
        treeNodeData(node);//输出
        DLRTree(node.left);
        DLRTree(node.right);
    }
}

void LDRTree(BinarySortTreeNode node) {
    if (node != null) {
        LDRTree(node.left);
        treeNodeData(node);//输出
        LDRTree(node.right);
    }
}

void LRDTree(BinarySortTreeNode node) {
    if (node != null) {
        LRDTree(node.left);
        LRDTree(node.right);
        treeNodeData(node);//输出
    }
}

public void levelTree(BinarySortTreeNode node) {
    //需要使用队列辅助
    BinarySortTreeNode curNode;
    BinarySortTreeNode[] nodeQuery = new BinarySortTreeNode[MAXLEN];
    int head = 0, tail = 0;

    //先判断树是否为空
    if (node != null) {
        tail = (tail + 1) % MAXLEN;
        nodeQuery[tail] = node;
    }
    while (head != tail) {
        head = (head + 1) % MAXLEN;
        curNode = nodeQuery[head];

        treeNodeData(curNode);//输出

        //如果当前结点存在子树
        if (curNode.left != null) {
            tail = (tail + 1) % MAXLEN;
            nodeQuery[tail] = curNode.left;
        }
        if (curNode.right != null) {
            tail = (tail + 1) % MAXLEN;
            nodeQuery[tail] = curNode.right;
        }
    }
}
```

## 二叉树确定

- 先序排列（DLR）、后序排列（LRD）：确定各层级的根结点。
- 中序排列（LDR）：确定结点的左右子树之分。

> 求先序序列( ABCDEFGHI)和中序序列( BCAEDGHFI）所确定的二叉树：
>
> 1. 图a：
>
>    1. 由先序遍历：确定第一层的根结点为A；
>
>    2. 在1.1的基础上，由中序遍历确定BC为A的左子树一侧，而EDGHFI为A的右子树一侧。
>
>    <img src="../../pictures/Snipaste_2023-05-28_15-38-07.png" width="400"/> 
>
> 2. 图b：
>
>    1. 在图a的基础上，由先序遍历确定：
>       - B是A的左子树、即C的双亲结点。
>       - D是A的右子树、即EFGHI的双亲/祖先结点。
>    2. 在2.1的基础上，由中序遍历确定：
>       - C是B的右子树。
>       - E是D的左子树，GHFI在D的右子树一侧。
>
>    <img src="../../pictures/Snipaste_2023-05-28_15-42-27.png" width="400"/> 
>
> 3. 图c：
>
>    1. 在图b的基础上，由先序遍历确定：
>       - F是D的右子树、即GHI的双亲/祖先结点。
>    2. 在3.1的基础上，由中序遍历确定：
>       - GH在F的左子树一侧。
>       - I是F的右子树。
>
>    <img src="../../pictures/Snipaste_2023-05-28_15-47-24.png" width="400"/> 
>
>    3. 最后确定H是G的右子树。
>
> <img src="../../pictures/Snipaste_2023-03-28_18-48-08.png" width="500"/> 
