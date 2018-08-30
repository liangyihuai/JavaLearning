package com.huai.linearQuadtree;

import java.util.EmptyStackException;
import java.util.Stack;

public class ContantQueue {

    private Stack<Value> s1 = new Stack<>();
    private Stack<Value> s2 = new Stack<>();


    public void pushRear(int value){
        System.out.println("input number: "+value);
        pushRear(s1, value);
    }

    private void pushRear(Stack<Value> s, int value){
        if(s.empty())
            s.push(new Value(value, value));
        else{
            if(value > s.peek().min){
                s.push(new Value(value, s.peek().min));
            }else{
                s.push(new Value(value, value));
            }
        }
    }

    public int popFront(){
        if(empty()) throw new EmptyStackException();
        return s2.pop().value;
    }

    public int getMin(){
        if(empty()) throw new EmptyStackException();
        return s2.peek().min;
    }

    public boolean empty(){
        if(s2.empty()){
            transferNums();
        }
        if(s2.empty()) return true;
        else return false;
    }

    private void transferNums(){
        if(s1.empty()) return;
        while(!s1.empty()){
            pushRear(s2, s1.pop().value);
        }
    }

    class Value{
        int value;
        int min;

        public Value(){}

        public Value(int value, int min){
            this.value = value;
            this.min = min;
        }
    }

    public static void main(String[] args) {
        ContantQueue queue = new ContantQueue();

        queue.pushRear(4);
        queue.pushRear(2);
        queue.pushRear(5);
        queue.pushRear(1);

        assert queue.popFront() == 4;
        assert queue.popFront() == 2;
        assert queue.popFront() == 5;
        assert queue.popFront() == 1;

        queue.pushRear(6);
        queue.pushRear(2);
        queue.pushRear(9);
        queue.pushRear(7);

        assert queue.popFront() == 6;
        assert queue.getMin() == 2;
        assert queue.popFront() == 2;
        assert queue.getMin() == 7;

        queue.pushRear(16);
        queue.pushRear(8);

        assert queue.popFront() == 9;
        assert queue.popFront() == 7;
        assert queue.popFront() == 16;
        assert queue.popFront() == 8;
    }
}
