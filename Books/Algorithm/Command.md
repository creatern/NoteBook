- 命令模式 Command：将请求封装为对象，使用不同的参数表示不同的请求，且支持可撤销的操作。消除请求发送者和请求接收者之间的耦合。

1. 命令队列：可将命令对象存入队列，多线程执行。
2. 尽量避免系统中存在过多的具体命令类。

<img src="../../pictures/设计模式-Command.drawio.svg" width="700"/> 

<img src="../../pictures/设计模式-Command-controller.drawio.svg" width="1200"/>

```java
LightReceiver lightReceiver = new LightReceiver();
RemoteController remoteController = new RemoteController();
remoteController.setCommands(0,new LightOnCommand(lightReceiver),new LightOffCommand(lightReceiver));
remoteController.onButtonWasPushed(0);
remoteController.offButtonWasPushed(0);
remoteController.undoButtonWasPushed();
```

```java
class RemoteController {
    Command[] onCommands;
    Command[] offCommands;
    Command undoCommand; //记录上一次操作，用于撤销

    public RemoteController() {
        onCommands = new Command[5];
        offCommands = new Command[5];

        for (int i = 0; i < 5; i++) {
            onCommands[i] = new NoCommand();
            offCommands[i] = new NoCommand();
        }
    }

    public void setCommands(int no, Command onCommand, Command offCommand) {
        onCommands[no] = onCommand;
        offCommands[no] = offCommand;
    }

    public void onButtonWasPushed(int no) {
        onCommands[no].execute();
        undoCommand = onCommands[no];
    }

    public void offButtonWasPushed(int no) {
        offCommands[no].execute();
        undoCommand = offCommands[no];
    }

    public void undoButtonWasPushed() {
        undoCommand.undo();
    }
}

interface Command {

    void execute();

    void undo();
}

class LightOnCommand implements Command {

    LightReceiver light;

    public LightOnCommand(LightReceiver light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

class LightOffCommand implements Command {

    LightReceiver light;

    public LightOffCommand(LightReceiver light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}

class NoCommand implements Command {

    @Override
    public void execute() {
    }

    @Override
    public void undo() {
    }
}

class LightReceiver {

    public void on() {
        System.out.println("打开电灯");
    }

    public void off() {
        System.out.println("关闭电灯");
    }
}
```

