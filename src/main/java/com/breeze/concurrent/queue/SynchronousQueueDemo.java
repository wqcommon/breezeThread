package com.breeze.concurrent.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * @author qiang.wen
 * @date 2018/8/6 16:50
 *
 * SynchronousQueue : 同步队列
 * 特点：
 * 1、SynchronousQueue没有容量。与其他BlockingQueue不同，SynchronousQueue是一个不存储元素的BlockingQueue。每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然。
 * 2、因为没有容量，所以对应 peek, contains, clear, isEmpty … 等方法其实是无效的。例如clear是不执行任何操作的，contains始终返回false,peek始终返回null。
 * 3、SynchronousQueue分为公平和非公平，默认情况下采用非公平性访问策略，当然也可以通过构造函数来设置为公平性访问策略（为true即可）。
 * 4、若使用 TransferQueue, 则队列中永远会存在一个 dummy node（这点后面详细阐述）
 *
 * 应用场景：SynchronousQueue非常适合做交换工作，生产者的线程和消费者的线程同步以传递某些信息、事件或者任务。
 *
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        //队尾匹配队头出队，先进先出，体现公平原则，采用TransferQueue实现
//        SynchronousQueue<Integer> queue = new SynchronousQueue<>(true);
//        new Thread(){
//            @Override
//            public void run() {
//                //没有线程调用take(),立马返回
//                queue.offer(1);
//            }
//        }.start();

        //非公平模式，使用TransferStack实现
        SynchronousQueue<Integer> queue = new SynchronousQueue<>(false);
        new Thread(){
            @Override
            public void run() {
                try {
                    //当没有线程调用take(),一直阻塞
                    queue.put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    queue.put(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                   Integer val =  queue.take();
                    System.out.println("=======" + val);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    Integer val =  queue.take();
//                    System.out.println("=======" + val);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();

        while (true){}
    }
}
