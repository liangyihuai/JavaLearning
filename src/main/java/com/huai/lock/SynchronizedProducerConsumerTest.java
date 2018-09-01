package com.huai.lock;

import java.util.LinkedList;
import java.util.List;

/**
 *synchronized/wait()只有一个阻塞队列，notifyAll会唤起所有阻塞队列下的线程
 */
public class SynchronizedProducerConsumerTest {
    private List<Integer> list = new LinkedList<>();
    private int capacity = 4;

    public synchronized void generate() throws InterruptedException {
        while(list.size() == capacity){
            wait();
        }

        int generateNum = list.size();
        System.out.println(Thread.currentThread().getName() + ", generate: " + generateNum);
        list.add(generateNum);
        Thread.sleep(1000);
        notifyAll();
    }

    public synchronized void take() throws InterruptedException {
        while(list.size() == 0){
            wait();
        }

        int data = ((LinkedList<Integer>)list).pop();
        System.out.println(Thread.currentThread().getName() + ", consume: " + data);
        Thread.sleep(1000);
        notifyAll();
    }

    static class Producer implements Runnable{
        private SynchronizedProducerConsumerTest conditionTest;

        Producer(SynchronizedProducerConsumerTest conditionTest){
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

        private SynchronizedProducerConsumerTest conditionTest;

        Consumer(SynchronizedProducerConsumerTest conditionTest){
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
        SynchronizedProducerConsumerTest test = new SynchronizedProducerConsumerTest();
        for(int i = 0; i < 3; i++){
            new Thread(new Producer(test)).start();
            new Thread(new Consumer(test)).start();
        }
    }
}


