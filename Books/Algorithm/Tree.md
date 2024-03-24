
# 森林

- 森林是m棵互不相交的树的集合。

## 树、二叉树、森林的转换

- 树转为二叉树：

1. 为树中的所有相邻的兄弟节点之间加线。
2. 保留根节点和第一个子节点的连线，去除根节点与其他子节点的连线。
3. 层次调整：树的前序遍历等于二叉树的前序遍历，树的后序遍历等于二叉树的中序遍历

- 森林转二叉树：森林中树的兄弟节点\-\-\>二叉树中父节点的左孩子的右孩子节点或左孩子的孩子的孩子节点。

1. 将森林的每棵树转为二叉树。
2. 第一棵二叉树保持不变，从第二棵二叉树开始，移除将后一棵二叉树的根节点作为前一棵二叉树的根节点的右子树。
3. 层次调整：森林的前序遍历等于二叉树的前序遍历，森林的后序遍历等于二叉树的中序遍历

- 二叉树转树/森林：

1. 若该节点是其父节点的左节点，则将其右节点、右节点的子右节点……与其父节点相连。
2. 删除二叉树中所有的父节点与右节点的连线。
3. 层次调整。

# 几种特殊的树

## 最优二叉树（哈夫曼树）

- 二叉树的带权路径长度WPL：从根节点到各个叶子节点的路径长度与相应叶子节点权值的乘积之和

<img src="../../pictures/20231205005030.png" width="150"/> 

- 哈夫曼树：带权路径长度最小的二叉树。使权值越大的叶子节点越靠近根节点，且不存在度为1的节点。 

1. 由给定的n个权值的\{w<sub>1</sub>, w<sub>2</sub>, ..., w<sub>n</sub>\}，构成n棵只有根节点的二叉树集合F=\{T<sub>1</sub>, T<sub>2</sub>, .., T<sub>n</sub>\}，其中每棵二叉树T<sub>i</sub>中只有一个带权为w<sub>i</sub>的根节点，其左右子树均为空。
2. 在二叉树集合F中选取两棵根节点的值最小的树，作为左右子树以构造一棵新的二叉树，新二叉树的根节点的权值为这两棵二叉树的权值之和。
3. 在二叉树集合F中删除这两棵二叉树，并将新得到的二叉树加入到集合F中。
4. 重复2、3步，直至集合F中只剩下一棵二叉树，即为哈夫曼树。

```java
public class HuffmanTree {
    private HuffmanTreeNode root;
    List<HuffmanTreeNode> nodes;

    public HuffmanTree() {
        this.nodes = null;
    }

    public HuffmanTree(List<HuffmanTreeNode> nodes) {
        this.nodes = nodes;
    }

    public void createTree() {
        Queue<HuffmanTreeNode> queue = new PriorityQueue<>(new Comparator<HuffmanTreeNode>() {
            @Override
            public int compare(HuffmanTreeNode o1, HuffmanTreeNode o2) {
                return o1.value - o2.value;
            }
        });
        queue.addAll(nodes);

        while (!queue.isEmpty()) {
            HuffmanTreeNode n1 = queue.poll();
            HuffmanTreeNode n2 = queue.poll();
            HuffmanTreeNode parent = new HuffmanTreeNode(n1.value + n2.value, n1, n2);
            if (queue.isEmpty()) {
                root = parent;
                return;
            }
            queue.add(parent);
        }
    }

    public int getWeight() {
        Queue<HuffmanTreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int weight = 0;
        while (!queue.isEmpty()) {
            HuffmanTreeNode curNode = queue.poll();
            if (curNode.left != null) {
                curNode.left.deep = curNode.deep + 1;
                curNode.right.deep = curNode.deep + 1;
                queue.add(curNode.left);
                queue.add(curNode.right);
            } else {
                weight += curNode.deep * curNode.value;
            }
        }
        return weight;
    }
}

class HuffmanTreeNode {
    int value;
    HuffmanTreeNode left;
    HuffmanTreeNode right;
    int deep;

    public HuffmanTreeNode(int value) {
        this.value = value;
        this.deep = 0;
    }

    public HuffmanTreeNode(int value, HuffmanTreeNode left, HuffmanTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
```

### 哈夫曼编码

- 哈夫曼编码：通过数据出现的频率来分配权重，并由该权重来构建哈夫曼树。0和1究竟是表示左子树还是右子树没有明确规定。左、右孩子节点的顺序是任意的，所以构造出的哈夫曼树并不唯一，但各哈夫曼树的带权路径长度WPL相同且为最优。此外，如有若干权值相同的节点，则构造出的哈夫曼树更可能不同，但WPL必然相同且是最优的。

<details>
    <summary>有一段文字内容为“ BADCADFEED”要网络传输给别人：</summary>
    1. 如果使用二进制数据表示：<br/>
<img src="../../pictures/Snipaste_2023-03-28_19-44-41.png" width="450"/> <br/>
- 这样按照固定长度编码编码后就是“001000011010000011101100100011“，按每3位来译码。<br/>
2. 使用哈夫曼树时：<br/>
- 假设六个字母的频率为A 27,B 8,C 15,D 15,E 30,F 5；按权值百分比来分配，并重新按照赫夫曼树来规划它们。<br/>
- 左图为构造赫夫曼树的过程的权值显示。右图为将权值左分支改为0，右分支改为1后的赫夫曼树<br/>
<img src="../../pictures/Snipaste_2023-03-28_19-42-11.png" width="600"/> <br/>
- 原编码二进制串: 000011000011101100100011 (共 30个字符)<br/>
- 新编码二进制串: 10100101010111100(共25个字符)<br/>
- 数据被压缩，节省了空间。<br/>
</details>

- 前缀编码：不等长编码必须满足任一个字符的编码都不是另一个字符的编码的前缀。哈夫曼编码的字符在哈夫曼树中的位置只能是叶子节点、叶子节点一定代表哈夫曼编码的字符，即：n<sub>0</sub>= 哈夫曼编码的字符数。

- 非法的哈夫曼编码，即不满足前缀编码的，如{ 0, 00, 01, 10, 11}，其中0代表的字符是00、01的字符编码的前缀，故不满足前缀编码。

<img src="../../pictures/数据结构-哈夫曼编码-前缀编码.drawio.svg" width="260"/> 

```java
package tree;

import java.util.PriorityQueue;

public class HuffmanCode {

    public static void main(String[] args) {
        HuffmanCode huffmanCode = new HuffmanCode();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(15, 'D'));
        queue.add(new Node(27, 'A'));
        queue.add(new Node(5, 'F'));
        queue.add(new Node(8, 'B'));
        queue.add(new Node(15, 'C'));
        queue.add(new Node(30, 'E'));
        huffmanCode.init(queue);
        huffmanCode.DLR(huffmanCode.root);

    }

    Node root;

    /**
     * 依据传入PriorityQueue的Data的weight值来创建哈夫曼树
     *
     * @param queue 创建哈夫曼树的节点
     */
    public void init(PriorityQueue<Node> queue) {
        //只要queue非空就继续添加
        while (!queue.isEmpty()) {
            Node left = queue.poll();
            Node right = queue.poll();
            //父节点
            Node parent = new Node(left, right, left.weight + right.weight);
            //如果队列中没有节点
            if (queue.isEmpty()) {
                root = parent;
                return;
            }
            //将当前的父节点加入到Queue中重新排序
            queue.add(parent);
        }
    }

    public void DLR(Node node) {
        if (node != null) {
            System.out.print(node.characteristic);
            DLR(node.left);
            DLR(node.right);
        }
    }

    //将哈夫曼树变为二进制串
//    public String unCoding() {
        //1.获得编码的顺序
        //2.
//    }
}

class Node implements Comparable {
    Node left;
    Node right;
    int weight; //频率百分比 权值
    char characteristic;

    public Node() {
    }

    public Node(Node left, Node right, int weight) {
        this.left = left;
        this.right = right;
        this.weight = weight;
    }

    public Node(int weight, char characteristic) {
        this.weight = weight;
        this.characteristic = characteristic;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Node))
            throw new RuntimeException("不是同一个类型");

        Node node = (Node) o;
        return this.weight - node.weight;
    }
}
```

## 字典树（前缀树）

- 字典树（Trie树、前缀树）利用字符串的公共前缀来减少查询时间，最大限度地减少无谓的字符串比较，查询效率比哈希树高。首先看单词的第一个字母是不是在字典的第一层。如果不在，说明字典树里没有该单词；如果在，就在该字母的孩子节点里找是不是有单词的第二个字母，没有说明没有该单词，有的话用同样的方法继续查找。

> 典型用于统计、排序、和保存大量字符串，经常被搜索引擎系统用于文本词频统计。

1. 根节点不包含字符，除了根节点每个节点都只包含一个字符。（为了能够包括所有字符串）
2. 从根节点到目标节点所经过的所有字符都串起来目标节点对应的字符串。
3. 每个节点的子节点字符不同，使得每个单词、字符都是唯一的。

<img src="../../pictures/Snipaste_2023-02-14_20-24-27.png" width="400"/> 


```java
class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
        root.worldEnd = false;
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            Character character = word.charAt(i);
            //如果当前节点没有包含相关的后缀，则设置并获取该字符
            if (!node.childdren.containsKey(character)) {
                node.childdren.put(character, new TrieNode());
            }
            node = node.childdren.get(character);
        }
        node.worldEnd = true;
    }

    //全词匹配
    public boolean search(String word) {
        TrieNode node = root;
        boolean found = true;
        for (int i = 0; i < word.length(); i++) {
            Character character = word.charAt(i);
            if (!node.childdren.containsKey(character)) {
                return false;
            }
            node = node.childdren.get(character);
        }
        return found && node.worldEnd;
    }

    //前缀匹配
    public boolean starWith(String prefix) {
        TrieNode node = root;
        boolean found = true;
        for (int i = 0; i < prefix.length(); i++) {
            Character character = prefix.charAt(i);
            if (!node.childdren.containsKey(character)) {
                return false;
            }
            node = node.childdren.get(character);
        }
        return found;
    }
}

class TrieNode {
    Map<Character, TrieNode> childdren;
    boolean worldEnd;

    public TrieNode() {
        childdren = new HashMap<>();
        worldEnd = false;
    }
}
```

## 平衡二叉树

- 平衡二叉树上任一节点的左子树和右子树的深度之差不超过1。
- 平衡因子BF：将二叉树上节点的左子树深度减去右子树深度的值。只要二叉树上有一个节点的平衡因子的绝对值大于1，则该二叉树就是不平衡的

### 最小不平衡子树

- 在平衡二叉树的构造过程中以距离插入节点最近的且平衡因子绝对值大于1的节点为根的子树。

- 扁担原理：将根节点（支撑点）从A改为B。

```java
Node temp = root; //暂存A节点
root = root.left; //将B节点（A节点的左子树）设为根节点
temp.left = root.right; //将A节点的左子树设为B节点的右子树
root.right = temp; //将A节点设为B节点的右子树
```

| 平衡调整 | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| LL型     | 新插入的结点在结点A的左子树的左子树上。<br /><img src="../../pictures/Snipaste_2023-03-28_19-08-27.png" width="600"/> |
| RR型     | <img src="../../pictures/Snipaste_2023-03-28_19-14-49.png" width="600"/> |
| LR型     | 两次旋转操作，先左旋转后右旋转。先将A结点的左孩子B的右子树的根结点C向左上旋转提升到B结点的位置（即进行一次RR平衡旋转(左单旋转)），然后再把该C结点向右上旋转提升到A结点的位置（即进行一次LL平衡旋转(右单旋转)）。<br /><img src="../../pictures/Snipaste_2023-03-28_19-23-14.png" width="600"/> |
| RL型     | 两次旋转操作，先右旋转后左旋转。先将A结点的右孩子B的左子树的根结点C向右上旋转提升到B结点的位置（即进行一次LL平衡旋转(右单旋转)），然后再把该C结点向左上旋转提升到A结点的位置（即进行一次RR平衡旋转(左单旋转)）。<br /><img src="../../pictures/Snipaste_2023-03-28_19-24-53.png" width="600"/> |

## 二叉排序树

- 从任意节点开始，左子树节点值总比右子树值要小（左子树节点值 \< 根节点值 \< 右子树节点值），中序遍历（LDR）可以得到一组有序（从小到大）的数值。

## 线索二叉树

## B树

B\-tree

B\+tree

B\*tree

红黑树
