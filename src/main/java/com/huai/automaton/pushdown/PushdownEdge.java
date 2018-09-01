package com.huai.automaton.pushdown;

public class PushdownEdge {

    private Character c;
    private Character stackOut;
    private Character stackIn;

    public PushdownEdge(Character c, Character stackOut, Character stackIn) {
        this.c = c;
        this.stackOut = stackOut;
        this.stackIn = stackIn;
    }

    public Character getC() {
        return c;
    }

    public void setC(Character c) {
        this.c = c;
    }

    public Character getStackOut() {
        return stackOut;
    }

    public void setStackOut(Character stackOut) {
        this.stackOut = stackOut;
    }

    public Character getStackIn() {
        return stackIn;
    }

    public void setStackIn(Character stackIn) {
        this.stackIn = stackIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PushdownEdge that = (PushdownEdge) o;

        if (c != null ? !c.equals(that.c) : that.c != null) return false;
        if (stackOut != null ? !stackOut.equals(that.stackOut) : that.stackOut != null) return false;
        return stackIn != null ? stackIn.equals(that.stackIn) : that.stackIn == null;
    }

    @Override
    public int hashCode() {
        int result = c != null ? c.hashCode() : 0;
        result = 31 * result + (stackOut != null ? stackOut.hashCode() : 0);
        result = 31 * result + (stackIn != null ? stackIn.hashCode() : 0);
        return result;
    }
}
