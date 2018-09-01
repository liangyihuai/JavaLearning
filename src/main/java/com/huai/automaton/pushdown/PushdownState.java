package com.huai.automaton.pushdown;

import com.huai.automaton.State;

import java.util.LinkedList;
import java.util.List;

public class PushdownState extends State{
    private Boolean isFinal = false;
    private List<PushdownTransition> transitionList = new LinkedList<>();

    public PushdownState(String name) {
        super(name);
    }

    public void setFinal(){
        this.isFinal = true;
    }

    public PushdownTransition getNullInputTransition(){
        for(PushdownTransition transition: transitionList){
            if(transition.getEdge().getC() == null)
                return transition;
        }
        return null;
    }

    public PushdownState addTransition(PushdownState state, PushdownEdge ...edges) {
        for(PushdownEdge edge: edges){
            PushdownTransition transition = new PushdownTransition(state, edge);
            if(transitionList.contains(transition)) continue;
            transitionList.add(transition);
        }
        return this;
    }

    public List<PushdownState> getStates(char input){
        List<PushdownState> result = new LinkedList<>();
        for(PushdownTransition transition: transitionList){
            if(transition.getEdge().getC().equals(input))
                result.add(transition.getState());
        }
        return result;
    }

    public List<PushdownTransition> getTransitionList(char input){
        List<PushdownTransition> result = new LinkedList<>();
        for(PushdownTransition transition: transitionList){
            if(((Character)input).equals(transition.getEdge().getC())){
                result.add(transition);
            }
        }
        return result;
    }

    public List<PushdownTransition> getTransitionList(){
        return this.transitionList;
    }

    public Boolean isFinalState(){
        return isFinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PushdownState that = (PushdownState) o;

        if (isFinal != null ? !isFinal.equals(that.isFinal) : that.isFinal != null) return false;
        return transitionList != null ? transitionList.equals(that.transitionList) : that.transitionList == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isFinal != null ? isFinal.hashCode() : 0);
        result = 31 * result + (transitionList != null ? transitionList.hashCode() : 0);
        return result;
    }
}
