package com.huai.automaton.pushdown;

/**
 * 用于表示状态之间转换的边线。也就是状态根据它来决定应该向哪一个状态转换。
 *
 */
public class PushdownEdge {

    private Character input;//输入字符
    private Character stackOut;//入栈元素
    private Character stackIn;//出栈元素

    public PushdownEdge(Character c, Character stackOut, Character stackIn) {
        this.input = c;
        this.stackOut = stackOut;
        this.stackIn = stackIn;
    }

    public Character getInput() {
        return input;
    }

    public void setInput(Character input) {
        this.input = input;
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

        if (input != null ? !input.equals(that.input) : that.input != null) return false;
        if (stackOut != null ? !stackOut.equals(that.stackOut) : that.stackOut != null) return false;
        return stackIn != null ? stackIn.equals(that.stackIn) : that.stackIn == null;
    }

    @Override
    public int hashCode() {
        int result = input != null ? input.hashCode() : 0;
        result = 31 * result + (stackOut != null ? stackOut.hashCode() : 0);
        result = 31 * result + (stackIn != null ? stackIn.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "edge{" +
                "input=" + input +
                ",stackOut=" + stackOut +
                ",stackIn=" + stackIn +
                '}';
    }
}
