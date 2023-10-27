# 枚举概述

> - JDK1.5之前需要自定义枚举类
> - JDK 1.5 新增的 enum 关键字用于定义枚举类
> - 若枚举只有一个对象, 则可以作为一种单例模式的实现方式。

- 枚举类的实例对象是有限个且确定的（已经在枚举类中定义）。

- 枚举类对象的属性不允许被改动, 使用 private final 修饰。
- 枚举类的构造器是私有的。若枚举类显式的定义了带参数的构造器, 则在列出枚举值时也必须对应的传入参数。

> 自定义枚举类
>
> 1. 私有化类的构造器，保证不能在类的外部创建其对象。
> 2. 在枚举类的内部创建枚举类的实例。声明为：public static final。
> 3. 对象如果有实例变量，应该声明为private final，并在构造器中初始化。
>
> ```java
> class Season {
>     //    1.声明Season对象的属性
>     private final String seasonName;
>     private final String seasonDesc;
> 
>     //    2.私有化类的构造器,并为对象属性赋值
>     private Season(String seasonName, String seasonDesc) {
>         this.seasonName = seasonName;
>         this.seasonDesc = seasonDesc;
>     }
> 
> //    3.提供当前枚举类的多个对象 public static final
>     public static final Season SPRING = new Season("春天","万物复苏");
>     public static final Season SUMMER = new Season("夏天","赤日炎炎");
>     public static final Season AUTUMN = new Season("秋天","硕果累累");
>     public static final Season WINTER = new Season("冬天","天寒地冻");
> 
> //    其他诉求：getter获取枚举类对象的属性
>     public String getSeasonName() {
>         return seasonName;
>     }
> 
>     public String getSeasonDesc() {
>         return seasonDesc;
>     }
> }
> ```

# enum

- enum关键字定义的枚举类默认继承了 java.lang.Enum类。

1. 构造器必须私有。
2. 所有实例必须在枚举类中显式列出（默认 public static final ）。多个对象之间用 `逗号,` 隔开，最后一个对象以`分号 ;`结束。
3. 必须在枚举类的第一行声明枚举类对象（枚举类的对象要移到最前面）。

```java
enum Season{

//    1.提供当前枚举类的对象,
    SPRING("春天","万物复苏"),
    SUMMER("夏天","赤日炎炎"),
    AUTUMN("秋天","硕果累累"),
    WINTER("冬天","天寒地冻");

//    2.声明Season对象的属性
    private final String seasonName;
    private final String seasonDesc;

//    3.私有化类的构造器,并为对象属性赋值
    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
    
    //其他诉求：

    // getter获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    //toString() Enum类中已经重写，默认只打印当前对象名
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
```

## 选择实现其他接口

| 选择                           | 说明                                                         |
| ------------------------------ | ------------------------------------------------------------ |
| 枚举类中实现接口的方法         | 每个枚举值在调用实现的接口方法呈现相同的行为方式。           |
| 枚举类的实例分别实现接口的方法 | 每个枚举值在调用实现的接口方法呈现不同的行为方式。<br />哪怕只有一个枚举类对象实现也可实现该接口，不会报异常。 |

- 以上两种选择可同时存在。除非每个枚举值都实现了接口的方法，否则选择1必须存在，作为枚举值默认的实现。

```java
public class SeasonTest {
    public static void main(String[] args) {
        Season season = Season.SPRING;
        season.show();
    }
}

interface Info{
    void show();
}

enum Season implements Info{

//    情况二：枚举类的对象分别实现接口中的方法
    SPRING("春天","万物复苏"){
    },
    SUMMER("夏天","赤日炎炎"){
        @Override
        public void show() {
            System.out.println("夏天");
        }
    },
    AUTUMN("秋天","硕果累累"){
        @Override
        public void show() {
            System.out.println("秋天");
        }
    },
    WINTER("冬天","天寒地冻"){
        @Override
        public void show() {
            System.out.println("冬天");
        }
    };
    
    private final String seasonName;
    private final String seasonDesc;

    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }

//    情况一：实现接口，在枚举类中实现抽象方法
    @Override
    public void show() {
        System.out.println("季节");
    }
}
```

# Enum
