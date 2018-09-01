package com.huai.automaton.pushdown;

import com.huai.automaton.Automaton;
import com.huai.automaton.finite.FiniteState;

import java.util.List;
import java.util.Stack;

public class PushdownAutomaton implements Automaton {
    private PushdownState initial;
    private Stack<Character> stack = new Stack<>();

    public static final char bottomStackChar = '$';

    public PushdownAutomaton(PushdownState initial) {
        this.initial = initial;

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
                                   PushdownResult result, Stack<Character> st)
                                        throws CloneNotSupportedException {
        result.addState(state);

        if(index >= word.length()) return result;

        final List<PushdownTransition> states = state.getTransitionList(word.charAt(index));

        PushdownResult nextResult = null;
        for(PushdownTransition transition: states){
            PushdownEdge edge = transition.getEdge();
            if(edge.getStackOut() != null && !edge.getStackOut().equals(st.peek())) {
                continue;
            }
            if(edge.getStackOut() != null && edge.getStackOut().equals(st.peek())) {
                if(st.isEmpty()) return nextResult;
                st.pop();
            }
            if(edge.getStackIn() != null) {
                st.push(edge.getStackIn());
            }
            nextResult = testWord(transition.getState(), word, index+1,
                                result.clone(), (Stack<Character>) st.clone());
            if(nextResult.isValid()) {
                return nextResult;
            }
        }

        return nextResult;
    }


    /*private Result testWord(final FiniteState state, final String word, final Integer index, final Result result) {
        result.addState(state);
        if (index >= word.length()) return result;
        final List<FiniteState> states = state.getState(word.charAt(index));
        Result nextResult = null;
        for (FiniteState next : states) {
            nextResult = testWord(next, word, index+1, result.clone());
            if (nextResult.isValid()) {
                return nextResult;
            }
        }
        return nextResult;
    }*/

}
