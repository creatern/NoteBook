- 观察者模式 Observer：以集合的方式管理用户（Observer），避免对核心类（Data）的修改。 

<img src="../../pictures/设计模式-Observer.drawio.svg" width="500"/>

- java.util.Observable（相当于Subject）：已经实现核心方法，通过继承来实现观察者模式。

```java
@Deprecated(since="9")
public class Observable {
    private boolean changed = false;
    private Vector<Observer> obs;

    public Observable() {
        obs = new Vector<>();
    }

    public synchronized void addObserver(Observer o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    public synchronized void deleteObserver(Observer o) {
        obs.removeElement(o);
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    public void notifyObservers(Object arg) {
        Object[] arrLocal;

        synchronized (this) {
            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }
        for (int i = arrLocal.length-1; i>=0; i--)
            ((Observer)arrLocal[i]).update(this, arg);
    }

    public synchronized void deleteObservers() {
        obs.removeAllElements();
    }

    protected synchronized void setChanged() {
        changed = true;
    }

    protected synchronized void clearChanged() {
        changed = false;
    }

    public synchronized boolean hasChanged() {
        return changed;
    }

    public synchronized int countObservers() {
        return obs.size();
    }
}
```

<img src="../../pictures/设计模式-Observer-weather.drawio.svg" width="600"/> 

```java
WeatherData data = new WeatherDate();
data.registryObserver(new CurrentConditions());
```

```java
interface Subject{

    void registryObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver();
}

class WeatherData implements Subject{

    //现实中的数据
    private float temperature;
    private float pressure;
    private float humidity;

    private List<Observer> observers;

    public WeatherData(){
        observers = new ArrayList<Observer>();
    }

    public void registryObserver(Observer observer){
        observers.add(observer);
    }

    void remove(Observer observer){
        if(observers.contains(observer)) observers.remove(observer);
    }

    void notifyObserver(){
        for(int i = 0; i < observers.size(); i++){
            observers.get(i).update(temperature,pressure,humidity);
        }
    }
}

interface Observer{
    void update(float temperature, float pressure, float humidity);
}

class CurrentConditions implements Observer{

    //观察到的数据
    private float temperature;
    private float pressure;
    private float humidity;

    public void update(float temperature, float pressure, float humidity){
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    public void display(){
        System.out.println("CurrentConditions[" + temperature + "," + pressure + "," + humidity + "]");
    }
}
```

