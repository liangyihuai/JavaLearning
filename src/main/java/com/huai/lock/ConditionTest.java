package com.huai.lock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个condition对象表示一个阻塞队列
 *
 * Condition的强大之处在于它可以为多个线程间建立不同的Condition，
 * 使用synchronized/wait()只有一个阻塞队列，
 * notifyAll会唤起所有阻塞队列下的线程，而使用lock/condition，
 * 可以实现多个阻塞队列，signalAll只会唤起某个阻塞队列下的阻塞线程。
 * https://blog.csdn.net/chenchaofuck1/article/details/51592429
 */
public class ConditionTest {
    private int count = 0;
    private int capacity = 4;
    private List<Integer> list = new LinkedList<>();
    private Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public void generate() throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == capacity) {
                notFull.await();
            }
            int generateNum = list.size();
            System.out.println(Thread.currentThread().getName() + ", generate: " + generateNum);
            list.add(generateNum);
            Thread.sleep(1000);
            notEmpty.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void take() throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == 0) {
                notEmpty.await();
            }
            int data = ((LinkedList<Integer>)list).pop();
            System.out.println(Thread.currentThread().getName() + ", consume: " + data);
            Thread.sleep(1000);
            notFull.signalAll();
        }finally {
            lock.unlock();
        }
    }

    static class Producer implements Runnable{
        private ConditionTest conditionTest;

        Producer(ConditionTest conditionTest){
            this.conditionTest = conditionTest;
        }

        @Override
        public void run() {
            while(true){
                try {
                    conditionTest.generate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class Consumer implements Runnable{

        private ConditionTest conditionTest;

        Consumer(ConditionTest conditionTest){
            this.conditionTest = conditionTest;
        }

        @Override
        public void run() {
            while(true){
                try {
                    conditionTest.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        ConditionTest test = new ConditionTest();
        for(int i = 0; i < 3; i++){
            new Thread(new Producer(test)).start();
            new Thread(new Consumer(test)).start();
        }
    }
}


