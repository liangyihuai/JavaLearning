package com.huai.thread;


import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JoinTest implements Runnable {

    private String name;

    public JoinTest(String name) {
        this.name = name;
    }

    public void run() {
        System.out.printf("%s begins: %s\n", name, new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s has finished: %s\n", name, new Date());
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new JoinTest("One"));
        Thread thread2 = new Thread(new JoinTest("Two"));
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Thread.currentThread().isInterrupted();
        System.out.println("Main thread is finished");
    }

}