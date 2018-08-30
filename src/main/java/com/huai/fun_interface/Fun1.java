package com.huai.fun_interface;

@FunctionalInterface
public interface Fun1<T, R> {
     R apply(T t);


    default void display(){
        System.out.println("Fun1: hello, world.");
    }


    static <T> Fun1<T, T> identify(){
        return t->t;
    }
}
