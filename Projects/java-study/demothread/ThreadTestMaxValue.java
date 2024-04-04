package demothread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTestMaxValue {
    public static void main(String[] args) {
        // 生成伪随机数组
        Integer[] arr = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = (int) Math.floor(Math.random() * 4000);
        }

        ThreadTestMaxValue threadTest = new ThreadTestMaxValue();
        System.out.println(threadTest.getMaxValue(arr, 5));
    }

    /* 多线程分段处理数组 */
    public int getMaxValue(Integer[] arr, int threadNum) {
        int[] range = new int[threadNum + 1]; // 每段数组的范围，start-range[i]，end-range[i+1]
        int arrLength = arr.length; // 数组的长度，之后需要用于计算range[i]
        /* 计算每段数组的range */
        range[0] = 0;
        for (int i = 1; i < threadNum + 1; i++) {
            range[i] = range[i - 1] + arrLength / threadNum;
            /* 防止最后一个range的范围超过数组长度 */
            if (range[i] > arrLength) {
                range[i] = arrLength;
            }
        }
        /* 启动多线程来分段计算最大值 */
        Worker[] workers = new Worker[threadNum]; // call
        List<FutureTask<Integer>> futureTasks = new ArrayList<FutureTask<Integer>>(); // get
        for (int i = 0; i < threadNum; i++) {
            workers[i] = new Worker(arr, range[i], range[i + 1]);
            FutureTask<Integer> task = new FutureTask<Integer>(workers[i]);
            futureTasks.add(task); // 将任务线程加入List中，以此在之后获取计算的结果
            new Thread(task).start(); // 启动单个任务线程
        }
        /* 获取各线程的计算结果并汇总 */
        int max = Integer.MIN_VALUE; // 最终的最大值
        for (FutureTask<Integer> task : futureTasks) {
            Integer temp = null;
            try {
                temp = task.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            if (max < temp) {
                max = temp;
            }
        }
        return max;
    }
}

/* 用于处理每段数组的线程 */
class Worker implements Callable<Integer> {

    Integer[] arr;
    int begin;
    int end;

    public Worker(Integer[] arr, int begin, int end) {
        this.arr = arr;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        Integer max = arr[begin];
        for (int i = 0; i < end; i++) {
            if (max < arr[i]) max = arr[i];
        }
        return max;
    }
}