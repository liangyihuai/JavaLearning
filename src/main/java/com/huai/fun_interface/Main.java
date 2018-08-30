package com.huai.fun_interface;


import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        //Function<Integer, Integer> fun = x -> x + 1;

        Function<String, String> fun1 = Function.identity();
        String str1 = fun1.apply("helloWorld");
        System.out.println(str1);

        Function<Integer, Function<Integer, Integer>> add = z->(y->z+y);

        int x = 2;
        Function<Integer, Integer> add1 = add.apply(1);
        System.out.println(add1.apply(x));

        BiFunction<Integer, Integer, Integer> multiply = (a, b)->a * b;
        System.out.println(multiply.apply(3, 5));

        UnaryOperator<Integer> op1 = y -> y + 1;
        System.out.println(op1.apply(3));

        BinaryOperator<Integer> biOp1 = (y1, y2) -> y1 + y2;
        System.out.println(biOp1.apply(4, 5));

        BinaryOperator<Integer> biOp2 = BinaryOperator.minBy((y1, y2)-> y1-y2);
        System.out.println(biOp2.apply(50, 200));
    }
}


