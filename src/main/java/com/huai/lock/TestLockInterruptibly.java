package com.huai.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockInterruptibly
{

    // @Test
    public void test3() throws Exception
    {
        final Lock lock = new ReentrantLock();
        lock.lock();

        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    lock.lockInterruptibly();
                }
                catch(InterruptedException e)
                {
                    System.out.println(Thread.currentThread().getName() + " interrupted.");
                }
            }
        }, "child thread -1");

        t1.start();
        Thread.sleep(1000);

        t1.interrupt();

        Thread.sleep(1000000);
    }

    public static void main(String[] args) throws Exception
    {
        new TestLockInterruptibly().test3();
    }
}