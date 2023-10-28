- 建造者模式（生成器模式，Builder）：将复杂对象的建造过程抽象化，通过对该建造过程的不同实现构造不同的对象。

| 角色                       | 说明                                                         |
| -------------------------- | ------------------------------------------------------------ |
| Product 产品               | 具体的产品                                                   |
| Builder 抽象建造者         | 为产品对象的各个部件指定抽象接口。                           |
| ConcreteBuilder 具体建造者 | 实现各个部件的具体构造装配。                                 |
| Director 指导者            | 与客户端联系，并告诉建造者应该建造的产品，调用具体建造者中的方法。<br />1. 隔离客户与对象的生成过程。<br />2. 负责控制产品对象的生成过程。 |

<img src="../../pictures/设计模式-Builder.drawio.svg" width="900"/>

| 建造者模式 | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| 优势       | 1. 客户不必知道产品内存组成的细节，将产品本身和产品的创建过程解耦，使得相同的创建过程可以产生不同的产品对象。<br/>2. 每个具体建造者都相对独立，用户使用不同的具体建造者可以得到不同的产品对象。<br/>3. 精细化控制产品的创建过程。<br/>4. OCP：增加新的具体建造者无需修改原有代码。 |
| 缺陷       | 1. 建造者模式产生的产品应该是组成成分相似的，不适用与产品之间差异性大的。<br/>2. 如果产品的内部变化复杂，需要定义较多具体建造者实现其变化。 |

<img src="../../pictures/设计模式-Builder-imple.drawio.svg " width="900"/>

```java
ProductABuilder productABuilder = new ProductABuilder();
Director director = new Director();
director.makeProduct(productABuilder);
Product productA = productABuilder.getProduct();
```

```java
public class Director {
    public void construct(Builder builder){ //传入建造者的具体实现
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
    }
}

interface Builder {
    void buildPartA();
    void buildPartB();
    void buildPartC();
    Product getProduct();
}

class ProductABuilder implements Builder{
    Product productA = new Product();

    @Override
    public void buildPartA() {
        productA.setPartA("productA-partA");
    }

    @Override
    public void buildPartB() {
        productA.setPartB("productA-partB");
    }

    @Override
    public void buildPartC() {
        productA.setPartC("productA-partC");
    }

    @Override
    public Product getProduct() {
        return productA;
    }
}

public class Product {
    private String partA;
    private String partB;
    private String partC;
}
```

> StringBuilder#append()：
>
> | 类/接口               | 角色说明                                                     |
> | --------------------- | ------------------------------------------------------------ |
> | Appendable            | 抽象建造者                                                   |
> | AbstractStringBuilder | 建造者                                                       |
> | StringBuilder         | 指导者<br />具体建造者：建造方法由AbstractStringBuilder实现，StringBuilder继承AbstractStringBuilder |
>
> <img src="../../pictures/设计模式-Builder-StringBuilder.drawio.svg" width="600"/> 

| 比较         | 产品                                               |
| ------------ | -------------------------------------------------- |
| 抽象工厂模式 | 产品簇<br />不关心构建过程，只关心是什么工厂生产的 |
| 建造者模式   | 指定蓝图建造产品<br />组装配零配件而产生新产品     |

