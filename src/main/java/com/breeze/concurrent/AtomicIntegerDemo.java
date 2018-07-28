package com.breeze.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qiang.wen
 * @date 2018/7/28 10:13
 */
public class AtomicIntegerDemo {

    public static void main(String[] args) {
        AtomicIntegerService service = new AtomicIntegerService();
        AtomicIntegerThread tA = new AtomicIntegerThread(service);
        AtomicIntegerThread tB = new AtomicIntegerThread(service);
        AtomicIntegerThread tC = new AtomicIntegerThread(service);
        AtomicIntegerThread tD = new AtomicIntegerThread(service);
        AtomicIntegerThread tE = new AtomicIntegerThread(service);
        tA.setName("tA");
        tB.setName("tB");
        tC.setName("tC");
        tD.setName("tD");
        tE.setName("tE");
        tA.start();
        tB.start();
        tC.start();
        tD.start();
        tE.start();
    }

}

class AtomicIntegerService{

    private AtomicInteger value = new AtomicInteger(10);

    public void subStract(){
        System.out.println(Thread.currentThread().getName() + ",origin value:" + value);
        int i = value.decrementAndGet();
        System.out.println(Thread.currentThread().getName() + ",i===:" + i);
        System.out.println(Thread.currentThread().getName() + ",dest value:" + value);
    }
}

class AtomicIntegerThread extends Thread{

    private AtomicIntegerService service;

    public AtomicIntegerThread(AtomicIntegerService service){
        this.service = service;
    }

    @Override
    public void run() {
        service.subStract();
    }
}
