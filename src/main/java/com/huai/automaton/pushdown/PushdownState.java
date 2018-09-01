package com.huai.automaton.pushdown;

import com.huai.automaton.State;

import java.util.LinkedList;
import java.util.List;

/**
 * 表示一个状态，每一个状态对应多个转换，因为该状态可以转换为多种其他状态。
 */
public class PushdownState extends State{
    private Boolean isFinal = false;//是否为结尾元素
    private final List<PushdownTransition> transitionList = new LinkedList<>();

    public PushdownState(String name) {
        super(name);
    }

    public void setFinal(){
        this.isFinal = true;
    }


    public PushdownState addTransition(PushdownState state, PushdownEdge ...edges) {
        for(PushdownEdge edge: edges){
            PushdownTransition transition = new PushdownTransition(state, edge);
            if(transitionList.contains(transition)) continue;
            transitionList.add(transition);
        }
        return this;
    }


    /**
     * 根据输入值来获取状态转换对象。
     * @param input 如果为null，则返回输入值为null的转换对象
     * @return
     */
    public List<PushdownTransition> getTransitionList(Character input){
        List<PushdownTransition> result = new LinkedList<>();
        for(PushdownTransition transition: transitionList){
            if(input == null && transition.getEdge().getInput() == null){
                result.add(transition);
            }else if(input != null && input.equals(transition.getEdge().getInput())){
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
