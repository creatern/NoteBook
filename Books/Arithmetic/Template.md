- 模板方法模式 Template：抽象类公开定义执行其方法的模板，子类按需重写方法实现，调用时仍然按抽象类中定义的模板执行。

1. 统一算法：父类的模板方法保证算法的结构不变，子类提供部分步骤的实现。某个过程需要执行一系列步骤，个别步骤的实现可能不同。
2. 模板方法通常使用final修饰，防止被子类重写。
3. 代码复用。

```java
Employee acc = new Account();
acc.exec();
```

```java
publlic abstract class Employee{

    //模板方法
    public final void exec(){
        prepare();
        work();
        complete();
    } 

    public void prepare(){
        System.out.println("准备...");
    }

    public abstract void work();

    public void complete(){
        System.out.println("完成...");
    }
}

class Account extends Employee{
    public void work(){
        System.out.println("会计计帐...");
    }
}
```

- 钩子方法：默认不执行任何行为的方法，子类按需选择覆盖。

```java
publlic abstract class Employee{

    //模板方法
    public final void exec(){
        prepare();
        if(leisure()) work();
        complete();
    }

    //钩子方法
    public boolean leisure(){
        return true;
    }

    public void prepare(){
        System.out.println("准备...");
    }

    public abstract void work();

    public void complete(){
        System.out.println("完成...");
    }
}

class Account extends Employee{
    public void work(){
        System.out.println("会计计帐...");
    }
}
```

> Spring IOC：AbstractApplicationContext#refresh()模板方法，部分代码
>
> ```java
> this.postProcessBeanFactory(beanFactory);
> StartupStep beanPostProcess = this.applicationStartup.start("spring.context.beans.post-process");
> this.invokeBeanFactoryPostProcessors(beanFactory);
> this.registerBeanPostProcessors(beanFactory);
> beanPostProcess.end();
> this.initMessageSource();
> this.initApplicationEventMulticaster();
> this.onRefresh(); //钩子方法
> this.registerListeners();
> this.finishBeanFactoryInitialization(beanFactory);
> this.finishRefresh();
> ```
>
> <img src="../../pictures/设计模式-Template.drawio.svg" width="500"/> 

