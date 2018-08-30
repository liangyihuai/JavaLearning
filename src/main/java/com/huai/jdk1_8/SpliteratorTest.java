package com.huai.jdk1_8;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class SpliteratorTest {
    public static void main(String[] args) {
        SpliteratorTest1 spliterator1 = new SpliteratorTest1();
        spliterator1.test();
    }

    public void test(){
        List<String> arrs = new ArrayList<>();
        arrs.add("a");
        arrs.add("b");
        arrs.add("c");
        arrs.add("d");
        arrs.add("e");
        arrs.add("f");
        arrs.add("h");
        arrs.add("i");
        arrs.add("j");

        Spliterator<String> a = arrs.spliterator();

        Spliterator<String> b = a.trySplit();
        Spliterator<String> c = a.trySplit();
        Spliterator<String> d = a.trySplit();


        while(a.tryAdvance(s-> System.out.print(s+", "))){ }
        System.out.println();

        b.forEachRemaining(s-> System.out.print(s+", "));
        System.out.println();

        while(b.tryAdvance(s-> System.out.print(s+", "))){ }
        System.out.println();
        while(c.tryAdvance(s-> System.out.print(s+", "))){ }
        System.out.println();
        while(d.tryAdvance(s-> System.out.print(s+", "))){ }
        System.out.println();

    }
}

class SpliteratorTest1{
    AtomicInteger count = new AtomicInteger(0);
    List<String> strList = createList();
    Spliterator<String> spliterator = strList.spliterator();


    public void test(){
        for(int i = 0; i < 4; i++){
            new MyThread().start();
        }

        try {
            Thread.currentThread().join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result: "+count);
    }

    class MyThread extends Thread{

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("thread:" +threadName+" start...");

            spliterator.trySplit().forEachRemaining(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    if(isInteger(s)){
                        int num = Integer.parseInt(s);
                        count.addAndGet(num);
                        System.out.println("count: "+num+", "+threadName);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
            System.out.println("线程"+threadName+"运行结束-----");
        }




    }

    private List<String> createList(){
        List<String> result = new ArrayList<>();
        for(int i=0; i<100; i++){
            if(i % 10 == 0){
                result.add(i+"");
            }else{
                result.add("aaa");
            }
        }
        return result;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
