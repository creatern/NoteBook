package demothread.testthread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTestPrimeNumber {
    public static void main(String[] args) {
        ThreadTestPrimeNumber tw = new ThreadTestPrimeNumber();
        long begin = System.currentTimeMillis();
        System.out.println(tw.countPrimeNumber(103, 30));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end-begin));
    }

    public int countPrimeNumber(int max, int thread) {
        /* 初始化数据*/
        int[] data = new int[max];
        for (int i = 0; i < max; i++) {
            data[i] = i + 1;
        }
        /* 对数据进行范围划分 */
        int[] range = new int[thread + 1]; // 每段数据的范围
        range[0] = 0;
        for (int i = 1; i < thread + 1; i++) {
            range[i] = range[i - 1] + max / thread;
        }
        /* 确保至少有一个线程完成剩余部分的数据 */
        if (range[thread] < max) {
            range[thread] = max;
        }
        /* 对每段数据设置处理任务 */
        ThreadTestPrimeNumber tw = new ThreadTestPrimeNumber(); // 用于工具类
        List<FutureTask<Integer>> futureTasks = new ArrayList<FutureTask<Integer>>(); // 任务列表
        for (int i = 0; i < thread; i++) {
            int start = range[i], end = range[i + 1]; // 设置范围
            FutureTask<Integer> task = new FutureTask<>(() -> {
                System.out.println(Thread.currentThread().getName() + ": " + start + "-" + end);
                int tmpCount = 0;
                for (int j = start; j < end; j++) {
                    if (tw.isPrime(data[j])) {
                        tmpCount++;
                    }
                }
                return tmpCount;
            });
            new Thread(task).start();
            futureTasks.add(task);
        }
        /* 汇总处理 */
        int count = 0; // 计数器 素数
        System.out.println(tw.isPrime(max) + ":" + max + ":" + count);
        for (FutureTask<Integer> task : futureTasks) {
            Integer tmp = null;
            try {
                tmp = task.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            count += tmp;
        }
        return count;
    }

    public boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
