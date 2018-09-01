package com.huai.automaton.pushdown;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PushdownAutomatonTest {

    @Test
    public void testA() {
        PushdownState state2 = new PushdownState("z1");
        PushdownState state3 = new PushdownState("z2");
        PushdownState state4 = new PushdownState("z3");

        state2.addTransition(state2, new PushdownEdge('0', null, '0'));
        state2.addTransition(state3, new PushdownEdge('1', '0', null));
        state3.addTransition(state3, new PushdownEdge('1', '0', null));
        state3.addTransition(state4, new PushdownEdge(null, 'z', null));

        state4.setFinal();

        PushdownAutomaton automaton = new PushdownAutomaton(state2);

        assertTrue(automaton.testWord("0001").isValid());

        //System.out.println(automaton.testWord("0011").toString());

    }

}
