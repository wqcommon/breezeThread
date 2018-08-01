package com.breeze.concurrent.queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author qiang.wen
 * @date 2018/7/31 20:05
 *
 * 支持优先级的无界阻塞队列，使用数组存储，采用二叉堆来实现的
 *
 * 扩容进制：每次扩容原大小的50%
 */
public class PriorityBlockingQueueDemo {

    private static final PriorityBlockingQueue<PBTask> queue = new PriorityBlockingQueue<>();


    public static void main(String[] args) {
        //新增队列，不会阻塞，因为是无界的，可以一直添加
        addQueueEle();
        //取队列
        while (queue.size() > 0){
            try {
                System.out.println("peek===="+queue.peek());
                System.out.println("take====" + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);

    }

    private static void addQueueEle() {

        queue.put(new PBTask("task4",40));
        queue.put(new PBTask("task2",20));
        queue.put(new PBTask("task1",10));
        queue.put(new PBTask("task3",30));
    }
}


class PBTask implements Comparable<PBTask>{

    private String taskId;

    private int taskLevel;

    public PBTask(String taskId, int taskLevel) {
        this.taskId = taskId;
        this.taskLevel = taskLevel;
    }

    @Override
    public int compareTo(PBTask o) {
        //数字小优先级高，默认升序排列，最小堆
//        return this.taskLevel - o.taskLevel;
        //最大堆，降序排列
        return o.taskLevel - this.taskLevel;
    }

    @Override
    public String toString() {
        return this.taskId + "," + this.taskLevel;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(int taskLevel) {
        this.taskLevel = taskLevel;
    }
}