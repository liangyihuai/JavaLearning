package com.huai.fork_join;

import java.util.concurrent.*;

public class ForkJoinTest extends RecursiveTask<Integer>{

    private static int thres = 2;
    private int start;
    private int end;


    public ForkJoinTest(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= thres;
        if(canCompute){
            for(int i = start; i <= end; i++){
                sum += i;
            }
        }else{
            int middle = (start+end)/2;
            ForkJoinTest leftTask = new ForkJoinTest(start, middle);
            ForkJoinTest rightTask = new ForkJoinTest(middle+1, end);
//            leftTask.fork();
//            rightTask.fork();
            invokeAll(leftTask, rightTask);

            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            System.out.println("left result:"+leftResult);
            System.out.println("right result:"+rightResult);

            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTest task = new ForkJoinTest(1, 6);
        Future result = pool.submit(task);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
