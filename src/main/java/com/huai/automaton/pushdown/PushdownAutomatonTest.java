package com.huai.automaton.pushdown;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PushdownAutomatonTest {

    @Test
    public void testA() {
        //用于检测字符串中形如{0011, 01, 000111, 00001111}的字符串
        char bottomStackChar = '$';
        PushdownState state2 = new PushdownState("z2");
        PushdownState state3 = new PushdownState("z3");
        PushdownState state4 = new PushdownState("z4");

        state2.addTransition(state2, new PushdownEdge('0', null, '0'));
        state2.addTransition(state3, new PushdownEdge('1', '0', null));
        state3.addTransition(state3, new PushdownEdge('1', '0', null));
        state3.addTransition(state4, new PushdownEdge(null, bottomStackChar, null));

        state4.setFinal();

        PushdownAutomaton automaton = new PushdownAutomaton(state2, bottomStackChar);

        assertTrue(automaton.testWord("0000011111").isValid());
        PushdownAutomaton automaton1 = new PushdownAutomaton(state2, bottomStackChar);
        assertTrue(automaton1.testWord("000111").isValid());
        PushdownAutomaton automaton2 = new PushdownAutomaton(state2, bottomStackChar);
        assertTrue(automaton2.testWord("0011").isValid());
        PushdownAutomaton automaton3 = new PushdownAutomaton(state2, bottomStackChar);
        assertFalse(automaton3.testWord("0010011111").isValid());

       // System.out.println(result.toString());

    }

    @Test
    public void testB(){
        //用于检测一个字符串，形如{ abba, baba, aabbaa}
        char bottomStackChar = '$';
        PushdownState s2 = new PushdownState("q2");
        PushdownState s3 = new PushdownState("q3");
        PushdownState s4 = new PushdownState("q4");

        s2.addTransition(s2, new PushdownEdge('a', null, 'a'));
        s2.addTransition(s2, new PushdownEdge('b', null, 'b'));
        s2.addTransition(s3, new PushdownEdge(null, null, null));
        s3.addTransition(s3, new PushdownEdge('a', 'a', null));
        s3.addTransition(s3, new PushdownEdge('b', 'b', null));
        s3.addTransition(s4, new PushdownEdge(null, bottomStackChar, null));
        s4.setFinal();

        PushdownAutomaton automaton = new PushdownAutomaton(s2, bottomStackChar);
        assertTrue(automaton.testWord("aaaa").isValid());
        PushdownAutomaton automaton1 = new PushdownAutomaton(s2, bottomStackChar);
        assertTrue(automaton1.testWord("abba").isValid());
        PushdownAutomaton automaton2 = new PushdownAutomaton(s2, bottomStackChar);
        assertTrue(automaton2.testWord("aaaabaabaaaa").isValid());
        PushdownAutomaton automaton3 = new PushdownAutomaton(s2, bottomStackChar);
        assertFalse(automaton3.testWord("aaaabbabaa").isValid());

        //System.out.println(result.toString());
    }

}
