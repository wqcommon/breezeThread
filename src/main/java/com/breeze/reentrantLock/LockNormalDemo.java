package com.breeze.reentrantLock;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qiang.wen
 * @date 2018/7/27 11:16
 */
public class LockNormalDemo {

    public static void main(String[] args) {
        Order order = new Order();
        MyThread threadA = new MyThread(order);
        threadA.setName("threadA");
        MyThread threadB = new MyThread(order);
        threadB.setName("threadB");
        MyThread threadC = new MyThread(order);
        threadC.setName("threadC");
        threadA.start();
        threadB.start();
        threadC.start();
    }
}

class Order{

    private int nums = 100;

    private ReentrantLock lock = new ReentrantLock();

    public void subStractNum(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + ",origin Num:" + nums);
            nums = nums -2;
            System.out.println(Thread.currentThread().getName() + ",subStract Num:" + nums);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + ",结束-----");

    }
}

class MyThread extends Thread{

    private Order order;

    public MyThread(Order order){
        this.order = order;
    }

    @Override
    public void run() {
        order.subStractNum();
    }
}