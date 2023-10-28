- 访问者模式 Visitor：在被访问的类中加入一个对外提供接待访问者的接口。封装一些作用于某种数据结构的各元素的操作，可以在不改变数据结构的前提下，定义作用于这些元素的新操作。将数据结构和操作分离，解决数据结构和操作耦合性问题。

- 较稳定的数据结构、经常变化的功能需求的系统。

<img src="../../pictures/设计模式-Visitor.drawio.svg" width="700"/>

| 访问者模式 | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| 优势       | 1. 符合单一职责原则。<br />2. 统一功能，适用于数据结构相对稳定的系统。 |
| 缺陷       | 1. 具体元素的细节向访问者公布，违背迪米特拉法制。<br />2. 违背依赖倒转原则，访问者依赖具体元素，而不是抽象元素。 |

- 双分派：得到执行的操作取决于请求的类型、两个接收者的类型。

  <img src="../../pictures/设计模式-Visitor-Singer.drawio.svg" width="1200"/>

```java
ObjectStructure objectStructure = new ObjectStructure();
objectStructure.attach(new AJudge());
objectStructure.attach(new Bjudge());
objectStructure.display(new Success());
```

```java
public class ObjectStructure {

    private List<Judge> judges = new LinkedList<>();

    public void attach(Judge j) {
        persons.add(j);
    }

    public void detach(Judge j) {
        persons.remove(j);
    }

    public void display(Evaluate evaluate) {
        for(Judge j: judgs) {
            j.accept(evaluate);
        }
    }
}

abstract class Judge{
    public abstract void accept(Evaluate evaluate);
}

class AJudge extends Judge{
    public void accept(Evaluate evaluate){
        evaluate.getAResult();
    }
}

abstract class Evaluate{
    public abstract void getAResult();
    public abstract void getBResult();
}

class Success extends Evaluate{
    public void getAResult(){
        System.out.println("A评委：评价为成功");
    }
    public void getBResult(){
        System.out.println("B评委：评价为成功");
    }
}
```

