- 装饰者模式（Decorator）：动态地将新功能附加到对象。

<img src="../../pictures/设计模式-Decorator.drawio.svg" width="600"/> 

> IO流中的套接
>
> <img src="../../pictures/设计模式-Decorator-IO.drawio.svg" width="1200"/>

<img src="../../pictures/设计模式-Decorator-Drink.drawio.svg" width="1200"/>

```java
Drink drink = new Chocolate(new Latte());
System.out.println(drink.cost());
System.out.println(drink.getDescription());
```

```java
abstract class Drink{
    private double price;
    private String description;

    public abstract double cost();

    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}

class Coffee  extends Drink {
    @Override
    public double cost() {
        return super.getPrice();
    }

}


class Latte extends Coffee{
    public Latte(){
        setPrice(100);
        setDescription("Latte Coffee");
    }
}

class Decorator extends Drink{
    private Drink drink;

    public Decorator(Drink drink){
        this.drink = drink;
    }

    public String getDescription() {
        return drink.getDescription() + "&&" + cost();
    }

    @Override
    public double cost() {
        return super.getPrice() + drink.cost();
    }
}

class Chocolate extends Decorator{

    public Chocolate(Drink drink){
        super(drink);
        setPrice(10);
        setDescription("Chocolate");
    }
}
```

