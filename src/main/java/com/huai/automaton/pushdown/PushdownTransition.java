package com.huai.automaton.pushdown;

import com.huai.automaton.finite.FiniteState;

public class PushdownTransition {
    private PushdownState state;
    private PushdownEdge edge;


    public PushdownTransition(final PushdownState state, final PushdownEdge edge) {
        this.state = state;
        this.edge = edge;
    }

    public PushdownEdge getEdge() {
        return edge;
    }

    public PushdownState getState(){
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PushdownTransition that = (PushdownTransition) o;

        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        return edge != null ? edge.equals(that.edge) : that.edge == null;
    }

    @Override
    public int hashCode() {
        int result = state != null ? state.hashCode() : 0;
        result = 31 * result + (edge != null ? edge.hashCode() : 0);
        return result;
    }
}
