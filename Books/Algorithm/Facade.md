- 外观模式（Facade）（过程模式）：定义一个一致的接口，用以屏蔽内部子系统的细节，调用端只需要和该接口发生调用，而无需关心子系统的内部细节。
- 维护一个遗留的大型系统时，如果该系统难以维护和扩展，则考虑为新系统开发一个Facade类，提供遗留系统的比较清晰简单的接口，新系统和Facade类交互，提供复用性。 

<img src="../../pictures/设计模式-Facade.drawio.svg" width="600"/>   

<img src="../../pictures/设计模式-Facade-HomeTheater.drawio.svg" width="400"/> 

```java
HomeTheaterFacade home = new HomeTheaterFacade();
home.ready();
home.play();
home.exit();
```

```java
public class  HomeTheaterFacade{
    private DVDPlayer dvdPlayer;
    private Popcorn popcorn;

    public HomeTheaterFacade(){
        dvdPlayer = DVDPlayer.getInstance();
        popcorn = Popcorn.getInstance();
    }

    public void ready(){
        dvdPlayer.on();
        popcorn.on();
        popcorn.work();
    }

    public void play(){
        dvdPlayer.play();
    }

    public void exit(){
        dvdPlayer.off();
        popcorn.off();
    }
}

class DVDPlayer{
    private static DVDPlayer instance = new DVDPlayer();

    public static DVDPlayer getInstance(){return instance;}

    public void on(){System.out.println("DVD on");}
    public void off(){System.out.println("DVD off");}
    public void play(){System.out.println("DVD play");}
}

class Popcorn{
    private static Popcorn instance = new Popcorn();

    public static Popcorn getInstance(){return instance;}

    public void on(){System.out.println("Popcorn on");}
    public void off(){System.out.println("Popcorn off");}
    public void work(){System.out.println("Popcorn work");} 
}
```

