package com.breeze.concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author qiang.wen
 * @date 2018/7/30 17:24
 *
 * 基于数组实现的线程安全的阻塞队列
 *
 * 有界队列
 */
public class ArrayBlockingQueueDemo {

    //有界队列
    private static final ArrayBlockingQueue<AbQTest1> queue = new ArrayBlockingQueue<AbQTest1>(1);

    public static void main(String[] args) throws InterruptedException {
//        queueSetVal();

        queueSetAndGetVal();
        while (true){

        }

    }


    private static void queueSetAndGetVal(){
        new Thread(){
            @Override
            public void run() {
                //设置值
                int i = 1;
                while (true){
                    try {
                        queue.put(new AbQTest1(i++,String.valueOf(i++)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                //获取值
               while (true){
                   //remove 获取并移除头部对象，当队列为空，抛出异常
//                   AbQTest1 val = queue.remove();
                   //poll 获取并移除头部对象，当队列为空，返回null
//                   AbQTest1 val = queue.poll();
                   //take 获取并移除头部对象，当队列为空，阻塞直到队列有数据
//                   AbQTest1 val = null;
//                   try {
//                       val = queue.take();
//                   } catch (InterruptedException e) {
//                       e.printStackTrace();
//                   }
                   //element 获取头部对象，但不删除，当队列是空的，抛出异常
//                   AbQTest1 val = queue.element();
                   //peek 获取头部元素，但不删除，当队列是空的，返回null
                   AbQTest1 val = queue.peek();
                   System.out.println(val);
               }
            }
        }.start();
    }


    /**
     * 设置值测试
     * @throws InterruptedException
     */
    private static void queueSetVal() throws InterruptedException {
        ArrayBlockingQueue<AbQTest1> queue = new ArrayBlockingQueue<AbQTest1>(1);
        //put 方法，当队列满了之后，会一直等待，直到有空闲空间，再继续插入
//        queue.put(new AbQTest1(1,"1"));
//        System.out.println("1 step,size:" + queue.size());
//        queue.put(new AbQTest1(2,"2"));
//        System.out.println("2 step,size:" + queue.size());
        //add 方法，当队列满了之后，抛出异常
//        queue.add(new AbQTest1(1,"1"));
//        System.out.println("1 step,size:" + queue.size());
//        queue.add(new AbQTest1(2,"2"));
//        System.out.println("2 step,size:" + queue.size());
        //offer方法，当队列满了之后，返回false
//        queue.offer(new AbQTest1(1,"1"));
//        System.out.println("1 step,size:" + queue.size());
//        queue.offer(new AbQTest1(2,"2"));
//        System.out.println("2 step,size:" + queue.size());
        //offer带时间参数方法，当队列满了，会等待一段时间，超过该时间，返回false
        queue.offer(new AbQTest1(1,"1"),10, TimeUnit.SECONDS);
        System.out.println("1 step,size:" + queue.size());
        queue.offer(new AbQTest1(2,"2"),10, TimeUnit.SECONDS);
        System.out.println("2 step,size:" + queue.size());
    }
}


class AbQTest1{

    private int age;

    private String id;

    public AbQTest1(int age, String id) {
        this.age = age;
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id;
    }
}