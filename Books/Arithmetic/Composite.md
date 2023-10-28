# 组合模式 Composite

- 组合模式（合成模型模式）：将对象组合成树形结构以表示“整体-部分”的结构层次，对单个对象和组合对象的访问具有一致性。

<img src="../../pictures/设计模式-Composite.drawio.svg" width="600"/> 

| 类/接口   | 构件角色 | 说明                                         |
| --------- | -------- | -------------------------------------------- |
| Component | 抽象     | 共有的行为                                   |
| Leaf      | 树叶     | 参加组合的原始对象的行为<br>没有下级的子对象 |
| Composite | 树枝     | 参加组合的有子对象的对象                     |

1. 遍历组织机构、处理的对象具有树形结构时。
2. 要求较高的抽象性，节点和叶子相似。

# 透明方式

- 不安全：此时的Leaf和Composite对象没有被区分。

| 角色     | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| 抽象构件 | 声明所有管理子类对象的方法：包括Leaf和Composite内的所有方法。 |
| 树叶构件 | 参加组合的原始对象的行为<br>没有下级的子对象<br>**具有管理子对象一般行为的方法** |
| 树枝构件 | 参加组合的有子对象的对象                                     |

# 安全方式

- 只在Composite内声明管理子类对象的方法。

| 角色     | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| 抽象构件 | 声明所有管理子类对象的方法：包括Leaf和Composite内的所有方法。 |
| 树叶构件 | 参加组合的原始对象的行为<br>没有下级的子对象                 |
| 树枝构件 | 参加组合的有子对象的对象<br>**具有管理子对象一般行为的方法** |

```java
public interface Component{
    //返回自身实例
    Composite getComposite();
    //业务方法
    void sampleOperation();
}
```

```java
public class Composite implements Component {

    private Vector componentVector = new Vector();

    @Override
    public Composite getComposite() {
        return this;
    }

    @Override
    public void sampleOperation() {
        Enumeration enumeration = getChild();
        while (enumeration.hasMoreElements()) {
            ((Component) enumeration.nextElement()).sampleOperation();
        }
    }

    public void add(Component component) {
        componentVector.addElement(component);
    }

    public void remove(Component component) {
        componentVector.remove(component);
    }

    public Enumeration getChild() {
        return componentVector.elements();
    }
}
```

```java
public class Leaf implements Component{

    @Override
    public Composite getComposite() {
        return null;
    }

    @Override
    public void sampleOperation() {}
}
```

