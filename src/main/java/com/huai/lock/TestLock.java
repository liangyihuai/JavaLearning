package com.huai.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock
{
    // @Test
    public void test() throws Exception
    {
        final Lock lock = new ReentrantLock();
        lock.lock();


        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                //System.out.println("sub-thread locked");
                //lock.lock();

                System.out.println("sub-thread sleep");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("sleep interrupted");
                }
                System.out.println(Thread.currentThread().getName() + " interrupted.");
            }
        },"child thread -1");

        System.out.println("t1.start");
        t1.start();

        System.out.println("t1 interrupt");
        t1.interrupt();


        System.out.println("main thread sleep");
        Thread.sleep(1000000);
    }

    public static void main(String[] args) throws Exception
    {
        new TestLock().test();
    }
}