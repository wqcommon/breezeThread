package com.breeze.concurrent.threadpool;

import java.util.concurrent.*;

/**
 * 线程池执行
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {
//        singleExecutorTestOne();
//        singleExcutorTestTwo();
//        mutiliExecutorTestOne();
          mutiliExecutorTestTwo();
    }

    private static void mutiliExecutorTestTwo(){
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(200);
        ExecutorService executor = new ThreadPoolExecutor(20,20,0L,TimeUnit.SECONDS,queue);
        Task task = new Task(1);
        Task task2 = new Task(2);
        Task task3 = new Task(3);
        Future f = executor.submit(task);
        executor.execute(task2);
        Future f2 = executor.submit(task3);
        executor.shutdown();
    }


    private static void mutiliExecutorTestOne(){
        ExecutorService executor = Executors.newFixedThreadPool(20);
        Task task = new Task(1);
        Task task2 = new Task(2);
        Task task3 = new Task(3);
        executor.execute(task);
        executor.execute(task2);
        executor.execute(task3);
        executor.shutdown();

    }

    private static void singleExcutorTestTwo(){
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        ExecutorService executor = new ThreadPoolExecutor(1,1,0L, TimeUnit.SECONDS,queue);
        Task task = new Task(1);
        Task task2 = new Task(2);
        Task task3 = new Task(3);
        executor.execute(task);
        executor.execute(task2);
        Future<Integer> future = executor.submit(task3,100);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

    private static  void singleExecutorTestOne(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Task task = new Task(1);
        Task task2 = new Task(2);
        Task task3 = new Task(3);
        executorService.execute(task);
        executorService.submit(task2);
        executorService.execute(task3);
        executorService.shutdown();//不接受新的任务，但处理排队任务
    }

    private static class Task implements Runnable{

        private int taskId;

        public Task(int taskId){
            this.taskId = taskId;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId() + "执行的task的id为：" + taskId);
        }
    }
}
