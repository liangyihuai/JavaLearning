package com.huai.automaton.pushdown;

import com.huai.automaton.Automaton;

import java.util.List;
import java.util.Stack;

public class PushdownAutomaton implements Automaton {
    private final PushdownState initial;
    private final Stack<Character> stack = new Stack<>();

    public PushdownAutomaton(PushdownState initial, char bottomStackChar) {
        this.initial = initial;
        stack.push(bottomStackChar);
    }

    public PushdownResult testWord(String word){

        try {
            return testWord(initial, word, 0, new PushdownResult(), stack);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PushdownResult testWord(PushdownState state, String word, int index,
                                   PushdownResult result, Stack<Character> st) throws CloneNotSupportedException {
        result.addState(state);

        //递归的结束条件：1. 刚刚遍历完字符串中所有的字母，并且最后一个状态时结束状态。
        // 2. 遍历完所有的字符之后，不存在空的输入状态转换。
        if(index == word.length() && state.isFinalState()) return result;
        if(index > word.length() && state.getTransitionList(null).size() == 0) return result;

        //获取输入字符为空的状态转换。
        List<PushdownTransition> states = state.getTransitionList(null);
        if(index < word.length()){
            //合并上输入为非空的状态转换
            states.addAll(state.getTransitionList(word.charAt(index)));
        }
        PushdownResult nextResult = new PushdownResult(word);
        for(PushdownTransition transition: states){
            PushdownEdge edge = transition.getEdge();//获取该转换的边线
            //栈的顶端元素不等于边线的栈入值
            if(edge.getStackOut() != null && (st.empty() || !edge.getStackOut().equals(st.peek()))) {
                continue;
            }//如果栈的顶端元素等于边线的栈入值，弹出栈顶元素
            if(edge.getStackOut() != null && edge.getStackOut().equals(st.peek())) {
               // if(st.size() <= 1) continue;
                if(!st.empty())if(!st.empty())st.pop();
            }
            //将边线的栈入值压入栈中
            if(edge.getStackIn() != null) {
                st.push(edge.getStackIn());
            }
            result.addEdge(transition.getEdge());

            int nextIndex = index;
            if(edge.getInput() != null) nextIndex++;//如果当前的输入不为空，才增加字符串的索引
            //递归调用
            nextResult = testWord(transition.getState(), word, nextIndex, result.clone(), (Stack<Character>) st.clone());
            if(nextResult.isValid()) {
                return nextResult;
            }
        }

        return nextResult;
    }

}
