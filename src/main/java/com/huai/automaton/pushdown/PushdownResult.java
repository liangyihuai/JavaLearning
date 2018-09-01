package com.huai.automaton.pushdown;

import com.huai.automaton.Result;

import java.util.LinkedList;
import java.util.List;

/**
 * 表示算法的输出结果，保存了用来检测的字符串，所有转换的状态和对应的边缘。
 */
public class PushdownResult implements Result, Cloneable {
    private String word;
    private List<PushdownState> states = new LinkedList<>();
    private List<PushdownEdge> edges = new LinkedList<>();

    public PushdownResult(){}

    public PushdownResult(String word){
        this.word = word;
    }

    public PushdownState addState(PushdownState state){
        this.states.add(state);
        return state;
    }

    public PushdownEdge addEdge(PushdownEdge edge){
        this.edges.add(edge);
        return edge;
    }

    public Boolean isValid(){
        if(states.size() > 0){
            PushdownState lastState = states.get(states.size()-1);
            if(lastState.isFinalState()) return true;
        }
        return false;
    }

    @Override
    protected PushdownResult clone() throws CloneNotSupportedException {
        PushdownResult result = new PushdownResult();
        for(PushdownState s: states){
            result.addState(s);
        }
        for(PushdownEdge e: edges){
            result.addEdge(e);
        }
        result.setWord(word);
        return result;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        StringBuffer string = new StringBuffer();
        for (int i = 0; i < states.size(); i++) {
            string.append("[");
            string.append(states.get(i).getName());
            string.append("]");
            if(i < edges.size()){
                string.append("--");
                string.append(edges.get(i).toString());
                string.append("->");
            }

        }

        return string.toString();
    }
}


