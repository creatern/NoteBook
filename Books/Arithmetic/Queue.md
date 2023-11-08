  

# 队列 Queue 概述

- 队列：先入先出（FIFO）。

| 结构          | 说明                 |
| ------------- | -------------------- |
| 队头（front） | 队头元素的前一个位置 |
| 队尾（rear）  | 队尾元素位置         |

# 顺序存储结构

## 顺序队列

> 如果将队头固定在数组下标为0的位置：
>
> - 入队：对数组进行追加，T(n) = O(1)。
> - 出队：对数组下标为0的元素删除，T(n) = O(n)：需要向前移动n-1个元素。

<img src="../../pictures/Snipaste_2022-11-24_22-14-06.png" width="600"/> 

- 顺序队列：rear、front属性的增长没有可逆的。

```java
public class ArrayQueue {
    private int maxSize; // 表示数组的最大容量
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr; // 模拟队列

    // 创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列头的前一个位置
        rear = -1; // 指向队列最后一个数据
    }

    // 判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int value) {
        if (isFull()) {
            System.out.println("队列满！！！");
            return;
        }
        rear++; // rear后移
        arr[rear] = value;
    }

    // 获取队列的数据，出队列
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列为空");
        }
        // front后移
        return arr[++front];
    }

    // 遍历
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空！！！");
            return;
        }
        for (int i : arr) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    // 显示队列的头，并不取出
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空！！！");
        }
        return arr[front];
    }
}
```

## 循环队列

| 指针  | 说明                                       |
| ----- | ------------------------------------------ |
| front | 队头，初始值=0                             |
| rear  | 队尾，空出一个空间做为约定，rear的初始值=0 |

- 队满：$(rear+1) \% M = front \% M$。

- 队空：$rear==front$。

- 队中有效数据的个数：$(rear+M-front) \% M$。

- 循环增长：对rear和front的取模操作，解决假溢出。

  - `rear = (rear + 1) % maxSize;`

  - `front = (front + 1) % maxSize;`

> 假溢出：数组空间发生上溢，但数组的低端还有空闲空间。

```java
//使用数组模拟队列
public class CircleArrayQueue {
    private int M; // 表示数组的最大容量
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr; // 模拟队列

    // 判断队列是否满
    public boolean isFull() {
        return (rear + 1) % M == front % M;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int value) {
        if (isFull()) {
            System.out.println("队列满！！！");
            return;
        }
        
        arr[rear] = value;
        // 将rear后移 考虑取模，防止数组越界
        rear = (rear + 1) % M;
    }

    // 获取队列的数据，出队列
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列为空");
        }
        
        int value = arr[front];
        // front 后移
        front = (front + 1) % M;
        return value;
    }

    // 遍历 从front开始遍历
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空！！！");
            return;
        }

        // 求出当前队列有序数据的个数 (rear + M -front) % M
        // 在经过多次使用后，front的值很可能以及超过M，所以需要取模 int i = front % M
        for (int i = front % M; i < front + ((rear + M - front) % M); i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    // 显示队列的头，并不取出
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空！！！");
        }
        return arr[front];
    }
}
```

# 链式存储结构

- 无序添加的单向链表。

<img src="../../pictures/Snipaste_2023-05-28_01-49-14.png" width="700"/> 

```java
public class LinkedQueue<T> {

    private Node front; //队头
    private Node rear; //队尾

    public LinkedQueue() {
        front = rear = new Node();
    }

    /**
     * 判断队列空
     *
     * @return true-空
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 入队
     *
     * @param element 结点的数值域data
     */
    public void enQueue(T element) {
        Node node = new Node(element);
        rear.setNext(node);
        rear = node;
    }

    /**
     * 出队
     *
     * @return 出队结点的数值域data
     */
    public T deQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        Node<T> headNode = front.getNext();
        front.setNext(headNode.getNext());
        if (headNode.getNext() == null)
            rear = front;
        return headNode.getData();
    }

    /**
     * 查看队头
     * @return 队头结点的数值域data
     */
    public T getHead() {
        if (isEmpty())
            throw new RuntimeException("队列空");
        Node<T> head = front.getNext();
        return head.getData();
    }
    
    class Node<T> {
        private T data;
        private Node next;

        public Node() {

        }

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
```

# 双端队列

- 双端队列：允许在队列的两端进行插入和删除操作。

> 二进一出队列：允许在两端插入，但只允许在一端删除。
>
> 一进二出队列：只允许在一端插入，允许在两端删除。

