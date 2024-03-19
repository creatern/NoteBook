- Hunt算法采用[贪心策略](./贪心策略.md)，在划分数据的属性时，采取一系列局部最优决策来构造决策树。
- 在Hunt算法中，将训练记录相继划分成较纯的子集，以递归方式建立决策树。设D<sub>t</sub>是与结点t相关联的训练记录集，而<code>y={y<sub>1</sub>, y<sub>2</sub>, .. , y<sub>n</sub>}</code>是类标号，则Hunt算法的递归定义如下：

1. 如果D<sub>t</sub>中所有记录都属于同一个类y<sub>t</sub>，则t是叶结点，用y<sub>t</sub>标记。
2. 如果D<sub>t</sub>中包含属于多个类的记录，则选择一个<b>属性测试条件（attribute test condition）</b>将记录划分成较小的子集。
3. 对于测试条件的每个输出，创建一个子女结点，并根据测试结果将D<sub>t</sub>中的记录分布到子女结点中。然后，对于每个子女结点，递归地调用该算法。

<img src="../../pictures/Algorithm-Hunt.drawio.svg" width="500"/> 

- 决策树归纳的设计问题：

1. 如何分裂记录？
2. 如何停止分裂过程？

```java

```