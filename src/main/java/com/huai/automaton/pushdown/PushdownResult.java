package com.huai.automaton.pushdown;

import java.util.LinkedList;
import java.util.List;

public class PushdownResult implements Cloneable{
    private String word;
    private List<PushdownState> states = new LinkedList<>();

    public PushdownState addState(PushdownState state){
        this.states.add(state);
        return state;
    }

    public Boolean isValid(){
        if(states.size() > 0){
            PushdownState lastState = states.get(states.size()-1);
            for(PushdownTransition transition: lastState.getTransitionList()){
                if(transition.getState().isFinalState())return true;
            }
        }
        return false;
    }

    @Override
    protected PushdownResult clone() throws CloneNotSupportedException {
        PushdownResult result = new PushdownResult();
        for(PushdownState s: states){
            result.addState(s);
        }
        return result;
    }
}


