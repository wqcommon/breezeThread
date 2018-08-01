package com.breeze.concurrent.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author qiang.wen
 * @date 2018/7/30 20:15
 *
 * 基于单向链表实现的有界阻塞队列，比ArrayBlockingQueue吞吐量高
 */
public class LinkedBlockingQueueDemo {

    private static final LinkedBlockingQueue<Lbq> queue = new LinkedBlockingQueue<>(1);

    public static void main(String[] args) throws InterruptedException {
//        queueSetVal();
        queueSetAndGetVal();
        while (true){}
    }


    private static void queueSetVal() throws InterruptedException {
        LinkedBlockingQueue<Lbq> queue = new LinkedBlockingQueue<>(1);
        //put 插入元素，当队列满了，一直阻塞直到有空闲空间继续插入
//        queue.put(new Lbq(1,1));
//        System.out.println("step 1 ,size :" + queue.size());
//        queue.put(new Lbq(2,2));
//        System.out.println("step 2,size :" + queue.size());
        //add 插入元素，当队列满了，抛出异常
//        queue.add(new Lbq(1,1));
//        System.out.println("step 1 ,size :" + queue.size());
//        queue.add(new Lbq(2,2));
//        System.out.println("step 2,size :" + queue.size());
        //offer 插入元素，当队列满了，返回false
        queue.offer(new Lbq(1,1));
        System.out.println("step 1 ,size :" + queue.size());
        queue.offer(new Lbq(2,2));
        System.out.println("step 2,size :" + queue.size());
    }

    private static void queueSetAndGetVal(){
        new Thread(){
            @Override
            public void run() {
                //设置值
                int i = 1;
                while (true){
                    try {
                        queue.put(new Lbq(i,i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

            }
        }.start();

        new Thread(){
            @Override
            public void run() {
               //获取值
                while (true){
//                    Lbq val = null;
//                    try {
                    //take 获取头部元素，当队列为空，一直阻塞直到有元素返回
//                        val = queue.take();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    //remove 获取头部元素，当队列为空，抛出异常
//                    Lbq val = queue.remove();
                    //poll 获取头部元素，当队列为空，返回null
//                    Lbq val = queue.poll();
                    //peek 获取头部元素，但不删除，当队列为空，返回null，
                    //element 获取头部元素，但不删除，当队列为空，抛出异常
                    Lbq val = queue.peek();
                    System.out.println(val);
                }

            }
        }.start();
    }
}


class Lbq{

    private int age;

    private int id;

    public Lbq(int age, int id) {
        this.age = age;
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id + "," + this.age;
    }
}