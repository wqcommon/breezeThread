package com.breeze.concurrent.queue;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author qiang.wen
 * @date 2018/8/2 19:33
 *
 * 延迟队列，支持优先级；无界的阻塞队列，所有元素必须实现Delayed接口
 */

public class DelayQueueDemo {

    private static final DelayQueue<DqTask> queue = new DelayQueue<DqTask>();

    public static void main(String[] args) throws InterruptedException {
        queue.offer(new DqTask(5,"task5",5, "2018-08-03 11:10:20"));
        queue.offer(new DqTask(4,"task4",4,"2018-08-03 11:11:20"));
        queue.offer(new DqTask(1,"task1",1,"2018-08-03 11:12:20"));
        queue.offer(new DqTask(2,"task2",2,"2018-08-03 11:13:20"));
        queue.offer(new DqTask(3,"task3",3,"2018-08-03 11:14:20"));

        while(queue.size() > 0){
            long start = System.currentTimeMillis();
            System.out.println("取数据前---" + start);
            System.out.println(queue.take());
            long end = System.currentTimeMillis();
            System.out.println("取数据后---" + end);
            System.out.println("总耗时----" + (end - start));

        }

    }
}


class DqTask implements Delayed {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private int id;

    private String taskName;

    private int level;

    private String insertTime;

    public DqTask(int id, String taskName, int level,String insertTime) {
        this.id = id;
        this.taskName = taskName;
        this.level = level;
        this.insertTime = insertTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        LocalDateTime time = LocalDateTime.parse(insertTime,dateTimeFormatter);
        long delay = ChronoUnit.NANOS.between(LocalDateTime.now(),time);
        return delay;
    }

    @Override
    public int compareTo(Delayed o) {
        DqTask dqTask = (DqTask) o;
        return (int)(TimeUnit.NANOSECONDS.toSeconds(this.getDelay(TimeUnit.NANOSECONDS)) - TimeUnit.NANOSECONDS.toSeconds(dqTask.getDelay(TimeUnit.NANOSECONDS))) ;
    }

    @Override
    public String toString() {
        return this.id + "," + this.taskName + "," + this.level;
    }
}