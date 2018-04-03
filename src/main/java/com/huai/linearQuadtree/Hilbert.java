package com.huai.linearQuadtree;


import org.slf4j.event.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Hilbert {

    static Logger logger = Logger.getLogger(Hilbert.class.getName());

    public static boolean debug = logger.isLoggable(java.util.logging.Level.INFO);

    static Map<String,  Pair[][]> hilbert_map = new HashMap<String,  Pair[][]>();

    public Hilbert() {
        hilbert_map.put("a", new Pair[][] {
                { new Pair(0, "d"), new Pair(1, "a") },
                { new Pair(3, "b"), new Pair(2, "a") } });
        hilbert_map.put( "b", new Pair[][] {
                { new Pair(2, "b"), new Pair(1, "b") },
                { new Pair(3, "a"), new Pair(0, "c") } });
        hilbert_map.put( "c", new Pair[][] {
                { new Pair(2, "c"), new Pair(3, "d") },
                { new Pair(1, "c"), new Pair(0, "b") } });
        hilbert_map.put( "d", new Pair[][] {
                { new Pair(0, "a"), new Pair(3, "c") },
                { new Pair(1, "d"), new Pair(2, "d") } });

    }
    /**
     * Our x and y coordinates, then, should be normalized to a range of 0 to
     * 2order-1
     * @param x
     * @param y
     * @param order An order 1 curve fills a 2x2 grid, an order 2 curve fills a 4x4 grid, and so forth.
     * @return
     */
    public int xy2d(int x, int y, int order) {
        String current_square = "a";
        int position = 0;
        int quad_x = 0;
        int quad_y = 0;
        int quad_position = 0;
        for (int i = order - 1; i >= 0; i--) {
            position <<= 2;
            quad_x = (x & (1 << i)) > 0 ? 1 : 0;
            quad_y = (y & (1 << i)) > 0 ? 1 : 0;

//            System.out.print(quad_x);
//            System.out.print('\n');
//            System.out.print(quad_y);
//            System.out.print('\n');

            Pair p = hilbert_map.get(current_square)[quad_x][quad_y];
            quad_position = p.no;
            current_square = p.square;
            position |= quad_position;
        }

        return position;
    }

    static int SCALE_FACTOR = (int) 1e5;
    static int hibert_order = 1;

    static double max_length = 1.5;

    static {
        int Max = (int) (max_length * SCALE_FACTOR);

        int size = 1;
        while (size < Max) {
            size <<= 1;
            hibert_order++;
        }
    }

    static class Pair {
        int no = 0;
        String square;

        Pair(int no, String square) {
            this.no = no;
            this.square = square;
        }
    }

    public static void main(String args[]){
        Hilbert h = new Hilbert();
        int position = h.xy2d(1, 2, 3);
        System.out.println(position);
    }
}