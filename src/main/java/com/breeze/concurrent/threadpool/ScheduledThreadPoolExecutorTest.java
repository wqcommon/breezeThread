package com.breeze.concurrent.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 包含执行计划的线程池执行器
 */
public class ScheduledThreadPoolExecutorTest {

    public static void main(String[] args) {
         singleThreadTest();
    }

    private static void singleThreadTest(){
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        Task task = new Task(1);
        service.schedule(task,10, TimeUnit.SECONDS);
        Task task2 = new Task(2);
        service.scheduleAtFixedRate(task2,2,5,TimeUnit.SECONDS);
        Task task3 = new Task(3);
        service.scheduleWithFixedDelay(task3,2,5,TimeUnit.SECONDS);
        service.shutdown();
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
