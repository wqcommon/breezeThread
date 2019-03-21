package com.breeze;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        List<String> aList = new ArrayList<>();
        aList.add("a");
        aList.add("b");
//        System.out.println(aList.get(0));
//        System.out.println(aList.get(1));

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("aa");
            }
        });
        a.start();
        a.join();
        System.out.println("bb");
    }
}
