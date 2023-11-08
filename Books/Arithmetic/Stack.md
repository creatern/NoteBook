# 栈 概述

- 栈（Stack）：限制线性表中元素的插入和删除只能在线性表的同一端进行。先进后出（FILO）、后进先出（LIFO）。

| 结构           | 说明           |
| -------------- | -------------- |
| 栈顶（Top）    | 允许插入和删除 |
| 栈底（Bottom） | 固定           |

<img src="../../pictures/Snipaste_2022-12-18_20-10-38.png" width="1000"/>

| 方法   | 操作           |
| ------ | -------------- |
| peek() | 查看栈顶元素   |
| pop()  | 弹出栈顶元素   |
| push() | 向栈中压入元素 |

> 顺序栈 SequentialStack
> 链栈 LinkedStack

# 双端栈

<img src="../../pictures/Snipaste_2023-05-28_02-28-33.png" width="600"/> 

- 两个顺序栈使用一个数组的两端分别存储（顺序栈单向延申）。栈一的栈顶top1为该数组的0下标开始，每次top1++存放新的元素。栈二的栈顶top2为该数组的length-1下标开始，每次top2--存放新的元素 。`top1 == top2 - 1`时，栈满。
- 两个栈相向增长，减少对数组空间的浪费，减少发生上溢的概率：最好一个栈增长时，另一个栈缩短。

```java
public class BothStack {
    char[] bothStack = new char[20];
    int top1 = 0; //stack1的栈顶
    int top2 = bothStack.length - 1; //stack2的栈顶

    public BothStack(int length) {
        bothStack = new char[length];
        this.top1 = 0; //stack1的栈顶
        this.top2 = bothStack.length - 1; //stack2的栈顶
    }

    public void push(char value, int type) {
        //判断是否栈满
        if (top1 == top2 - 1) {
            throw new RuntimeException("栈满");
        }

        //按不同的栈操作
        if (type == 1) {
            bothStack[top1++] = value;
        } else if (type == 2) {
            bothStack[top2--] = value;
        } else {
            throw new RuntimeException("未选择正确的栈");
        }
    }

    public char pop(int type) {
        //按不同的栈操作
        if (type == 1) {
            if (top1 == 0) {
                throw new RuntimeException("stack1栈空");
            }
            return bothStack[--top1];
        } else if (type == 2) {
            if (top1 == bothStack.length - 1) {
                throw new RuntimeException("stack2栈空");
            }
            return bothStack[++top2];
        } else {
            throw new RuntimeException("未选择正确的栈");
        }
    }
}
```
