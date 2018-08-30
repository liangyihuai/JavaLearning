package com.huai.thread_future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyFeatureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new MyFeatureTest().cool();
    }

    public void cool() throws ExecutionException, InterruptedException {
        System.out.println("order cooker online.");

        FutureTask<Cooker> orderCooker = new FutureTask<Cooker>(new Callable<Cooker>() {
            @Override
            public Cooker call() throws Exception {
                Thread.sleep(2000);
                return new Cooker();
            }
        });
        new Thread(orderCooker).start();

        if(orderCooker.isDone()){
            System.out.println("haven't get the cooker.");
        }

        System.out.println("prepare food");
        Food food = new Food();
        Cooker cooker = orderCooker.get();
        System.out.println("get the cooker");
        cook(food, cooker);
    }

    static class Cooker{ }

    static class Food{}

    public void cook(Food food, Cooker cooker){
        System.out.println("cooking");
    }
}
