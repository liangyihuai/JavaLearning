package com.huai.fun_interface;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestPredicate {
    public static void main(String[] args) {
        TestPredicate test = new TestPredicate();
        test.printBigValue(10, val->val > 5);
        test.printBigValueAnd(10, val->val > 5);
        test.printBigValueAnd(6, val->val>5);

        Predicate<Integer> p1 = v -> v > 3;
        Predicate<Integer> p2 = v -> v < 5;
        Predicate<Integer> p3 = v -> v > 8;
        Predicate<Integer> p4 = p1.and(p2).or(p3);

        System.out.println(p4.test(4));
        System.out.println(p4.test(9));

        test.testBiPredicate();

        test.testSupplier();
    }

    public void printBigValue(int value, Predicate<Integer> predicate){
        if(predicate.test(value)){
            System.out.println(value);
        }
    }

    public void printBigValueAnd(int value, Predicate<Integer> predicate){
        if(predicate.and(v->v < 8).test(value)){
            System.out.println("value < 8: "+value);
        }else{
            System.out.println("value should < 8 at least");
        }
    }

    public void testBiPredicate(){
        BiPredicate<Integer, Integer> p1 = (a, b)-> a > b;
        BiPredicate<Integer, Integer> p2 = (a, b)-> b > 5;
        BiPredicate<Integer, Integer> p3 = p1.and(p2);

        System.out.println(p3.test(5, 6));
        System.out.println(p3.test(8, 7));
    }

    public void testSupplier(){
        Supplier<TestPredicate> anotherSupplier;
        for(int i = 0; i < 10; i++){
//            anotherSupplier = TestPredicate::new;
            anotherSupplier = TestPredicate::getTestPredicate;
            System.out.println(anotherSupplier.get());
        }
    }

    static TestPredicate getTestPredicate(){
        return new TestPredicate();
    }
}
