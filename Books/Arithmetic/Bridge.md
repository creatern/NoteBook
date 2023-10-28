- 桥接模式（Bridge）（柄体/接口模式）：抽象化与实现脱耦。多层继承的替代实现。

<img src="../../pictures/设计模式-Bridge.drawio.svg" width="1200"/> 

| 角色                                | 等级 |                                                              |
| :---------------------------------- | :--- | :----------------------------------------------------------- |
| 抽象化<br />Abstraction             | 抽象 | 抽象化给出的定义，并保存一个对实现化角色的引用<br />只给出基于底层操作的更高一层操作。 |
| 修正抽象化<br />RefinedAbstraction  | 抽象 | 扩展抽象化角色，改变和修正父类对抽象化的定义                 |
| 实现化<br />Implementor             | 实现 | 实现化角色的接口，不是具体的实现。<br />不一定与抽象化角色的接口定义相同。<br />只给出底层操作 |
| 具体实现化<br />ConcreteImplementor | 实现 | 对实现化角色的具体实现                                       |

- 抽象化等级结构中的方法通过向对应的实现化对象委派实现自己的功能。即抽象化角色可以通过向不同的实现化对象委派，而动态的转换自己的功能。

<img src="../../pictures/设计模式-Bridge-Messages.drawio.svg" width="1200"/>

```java
//以SMS发送普通消息
AbstartMessage message = new CommonMessage(new MessageSMS());
message.sendMessage("信件内容", "Tom");
//以Email发送加急消息
message = new UrgentMessage(new MessageEmail());
message.sendMessage("信件内容", "Tom");
```

```java
abstract class AbstartMessage {

    MessageImplementor impl;

    public AbstartMessage(MessageImplementor impl) {
        this.impl = impl;
    }

    public void sendMessage(String message, String toUser) {
        this.impl.send(message, toUser);
    }
}

class CommonMessage extends AbstartMessage {

    public CommonMessage(MessageImplementor impl) {
        super(impl);
    }

    @Override
    public void sendMessage(String message, String toUser) {
        super.sendMessage(message, toUser);
    }
}

class UrgentMessage extends AbstartMessage {

    public UrgentMessage(MessageImplementor impl) {
        super(impl);
    }

    @Override
    public void sendMessage(String message, String toUser) {
        message = "加急!：" + message;
        super.sendMessage(message, toUser);
    }

    public void other() {//扩展新的功能}
}

interface MessageImplementor {
    public void send(String message, String toUser);
}

class MessageSMS implements MessageImplementor {
    @Override
    public void send(String message, String toUser) {
        System.out.println("SMS-[" + message + "]-TO-" + toUser);
    }
}

class MessageEmail implements MessageImplementor {
    @Override
    public void send(String message, String toUser) {
        System.out.println("Email-[" + message + "]-TO-" + toUser);
    }
}
```

