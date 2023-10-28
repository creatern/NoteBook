# 适配器模式 Adapter

- 适配器模式（Adapter）（包装器 Wrapper）：（兼容性）当使用一个已经存在的类，其接口不符合要求时；创建一个可复用的类，与其他不相关的/不可预见的类协同工作，用来适配它们的父类接口。

<img src="../../pictures/设计模式-Wrapper.drawio.svg" width="400"/> 

| 类型                                   | 关系 | Source | 说明                                                         |
| :------------------------------------- | :--- | :----- | ------------------------------------------------------------ |
| 类适配器模式                           | 继承 | 类     | 单一源适配，清晰                                             |
| 对象适配器模式                         | 关联 | 对象   | 多源适配，但不太清晰                                         |
| 接口适配器模式<br />（默认适配器模式） | 实现 | 接口   | 只需实现一部分目标接口的方法。<br />抽象类实现接口，继承该抽象类，实现部分方法。 |

<img src="../../pictures/设计模式-Adapter-Spring.drawio.svg" width="1200"/>

# 类适配器模式

<img src="../../pictures/设计模式-Adapter.drawio.svg" width="700"/> 

<img src="../../pictures/设计模式-Adapter-class.drawio.svg" width="600"/> 

```jav
Phone phone = new Phone();
phone.charging(new VoltageAdapter());
```

```java
class Phone{
    public void charging(IVoltage5V iVoltage5V) {
        if(iVoltage5V.output5V() == 5) {
            System.out.println("正在充电...");
        } else if (iVoltage5V.output5V() > 5) {
            System.out.println("无法充电!!!");
        }
    }
}

interface IVoltage5V {
    int output5V();
}

class Voltage220V {
    public int output220V() {
        int src = 220;
        System.out.println("src");
        return src;
    }
}

class VoltageAdapter extends Voltage220V implements IVoltage5V {

    @Override
    public int output5V() {
        int srcV = output220V();
        int dstV = srcV / 44;
        return dstV;
    }
}
```

# 对象适配器模式

<img src="../../pictures/设计模式-Adapter-object.drawio.svg" width="1200"/>

```java
Phone phone = new Phone();
phone.charging(new VoltageAdapter(new Voltage220V()));
```

```java
class Phone{
    public void charging(IVoltage5V iVoltage5V) {
        if(iVoltage5V.output5V() == 5) {
            System.out.println("正在充电...");
        } else if (iVoltage5V.output5V() > 5) {
            System.out.println("无法充电!!!");
        }
    }
}

interface IVoltage5V {
    int output5V();
}

class Voltage220V {
    public int output220V() {
        int src = 220;
        System.out.println("src");
        return src;
    }
}

class VoltageAdapter extends Voltage220V implements IVoltage5V {

    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V){
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        int dstV = 0;
        if(null != voltage220V){
            int srcV = output220V();
            dstV = srcV / 44; 
        }
        return dstV;
    }
}
```

# 接口适配器模式

<img src="../../pictures/设计模式-Adapter-interface.drawio.svg" width="500"/> 

```java
//Swing的事件监听器
new MouseListener() {
    ...
}
```
