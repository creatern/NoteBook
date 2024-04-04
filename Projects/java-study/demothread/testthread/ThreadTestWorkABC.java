package demothread.testthread;

public class ThreadTestWorkABC {
    public static void main(String[] args) {
        Application application = new Application();
        Thread workerA = new Thread(() -> {
            int count = 0;
            while (true) {
                application.setA(application.getA() + 1);
                System.out.println(application);
                System.out.println("total: " + ++count);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        workerA.setName("workA");

        Thread workerB = new Thread(() -> {
            while (true) {
                while (application.getA() > 0) {
                    application.setA(application.getA() - 1);
                    application.setB(application.getB() + 1);
                    System.out.println(application);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        workerB.setName("workB");

        Thread workerC = new Thread(() -> {
            while (true) {
                while (application.getB() > 0) {
                    application.setB(application.getB() - 1);
                    application.setC(application.getC() + 1);
                    System.out.println(application);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        workerC.setName("workC");

        workerA.start();
        workerB.start();
        workerC.start();
    }
}

class Application {
    // 流程为：a->b->c
    private int a = 0; // A完成切割的
    private int b = 0; // B完成打磨的
    private int c = 0; // C完成电镀的

    public synchronized int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public synchronized int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public synchronized int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + ": " +
                " a-" + getA() +
                " b-" + getB() +
                " c-" + getC();
    }
}