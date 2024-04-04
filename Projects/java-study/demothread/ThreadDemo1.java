package demothread;

public class ThreadDemo1 {
    public static void main(String[] args) {
//        System.out.println(Thread.currentThread().getThreadGroup());
        Thread curThread = Thread.currentThread();
//        System.out.println(curThread.threadId());
//        System.out.println(curThread.getName());
//        System.out.println(curThread.getState());
//        System.out.println(curThread.isInterrupted());
        curThread.interrupt();

        if (curThread.isInterrupted()) {
            Thread virtualThread = Thread.ofVirtual().start(() -> {
                int count = 5;
                while(count > 0) {
                    System.out.println(count);
                    count--;
                }
            });
            System.out.println(virtualThread.isVirtual());
            System.out.println("virtualThread is Deamon ? " +virtualThread.isDaemon());
        }
        System.out.println(curThread.isInterrupted());
        try {
            curThread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("now :" + curThread.getName() + "'s interrupted state is " + curThread.isInterrupted());
        }
//        while (true)
//            System.out.println(curThread.getName() + ": " + curThread.isInterrupted());

        new Thread(()->{
            for(int i = 0; i < 5; i++) System.out.println(Thread.currentThread().getName());
        }).start();
    }
}
