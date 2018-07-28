package com.breeze.reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qiang.wen
 * @date 2018/7/27 16:19
 */
public class LockOneConditionDemo {

    public static void main(String[] args) {
        Order2 order = new Order2();
        MyThread2 threadA = new MyThread2(order);
        threadA.setName("threadA");
        MyThread2 threadB = new MyThread2(order);
        threadB.setName("threadB");
        MyThread3 threadC = new MyThread3(order);
        threadC.setName("threadC");
        threadA.start();
        threadB.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadC.start();
    }
}

class Order2{

    private int nums = 100;

    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void subStractNum(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + ",origin Num:" + nums);
            nums = nums -2;
            condition.await();
            System.out.println(Thread.currentThread().getName() + ",subStract Num:" + nums);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + ",结束-----");

    }

    public void doSign(){
        lock.lock();
        try{
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() +  "释放锁");
        }finally {
            lock.unlock();
        }
    }
}

class MyThread2 extends Thread{

    private Order2 order;

    public MyThread2(Order2 order){
        this.order = order;
    }

    @Override
    public void run() {
        order.subStractNum();
    }
}

class MyThread3 extends Thread{

    private Order2 order;

    public MyThread3(Order2 order){
        this.order = order;
    }

    @Override
    public void run() {
        order.doSign();
    }
}

