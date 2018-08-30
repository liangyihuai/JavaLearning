package com.huai.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStream {
    public static void main(String[] args) {
        TestStream test = new TestStream();
        test.test2();
    }

    public void testStream1(){
        List<String> list = Arrays.asList("bcd", "cde", "def", "abc");
        List<String> result = list.stream()
                .filter(e->e.length() >= 3)
                .map(e->e.charAt(0))
                .map(e->String.valueOf(e))
                .collect(Collectors.toList());
        System.out.println(result);

        ArrayList<String> a = new ArrayList<>();
    }

    public void testStream11(){
        List<String> list = Arrays.asList("bcd", "cde", "def", "abc");
        List<String> result = list.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() >= 3;
            }
        }).map(new Function<String, Character>() {
            @Override
            public Character apply(String s) {
                return s.charAt(0);
            }
        }).map(new Function<Character, String>() {
            @Override
            public String apply(Character character) {
                return String.valueOf(character);
            }
        }).collect(Collectors.toList());
    }

    public void test2(){
        String str = " * Returns a composed predicate that represents a short-circuiting logical\n" +
                "     * OR of this predicate and another.  When evaluating the composed\n" +
                "     * predicate, if this predicate is {@code true}, then the {@code other}\n" +
                "     * predicate is not evaluated.\n" +
                "     *\n" +
                "     * <p>Any exceptions thrown during evaluation of either predicate are relayed\n" +
                "     * to the caller; if evaluation of this predicate throws an exception, the\n" +
                "     * {@code other} predicate will not be evaluated.\n" +
                "     *\n" +
                "     * @param other a predicate that will be logically-ORed with this\n" +
                "     *              predicate\n" +
                "     * @return a composed predicate that represents the short-circuiting logical\n" +
                "     * OR of this predicate and the {@code other} predicate\n" +
                "     * @throws NullPointerException if other is null";

        IntStream stream = str.chars();
        List<Character> result = stream.filter(c->(c != '\n' && c != ',' && c != '.'))
                .map(new IntUnaryOperator() {
                    @Override
                    public int applyAsInt(int operand) {
                        if(operand == '*') return '-';
                        else return operand;
                    }
                })
                .mapToObj(c->(char)c)
                .collect(Collectors.toList());

        StringBuffer buf = new StringBuffer();
        for(char c: result){
            buf.append(c);
        }
        System.out.println(buf.toString());


    }

    public void testNotStream1(){
        List<String> list = Arrays.asList("bcd", "cde", "def", "abc");
        List<String> result = new ArrayList<>(list.size());
        for(String str: list){
            if(str.length() >= 3){
                char e = str.charAt(0);
                String tempStr = String.valueOf(e);
                result.add(tempStr);
            }
        }
        System.out.println(result);
    }
}
