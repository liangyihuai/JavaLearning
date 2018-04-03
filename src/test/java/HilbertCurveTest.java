
import com.huai.linearQuadtree.HilbertCurve;
import com.huai.linearQuadtree.Util;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HilbertCurveTest {

    private static final HilbertCurve c = HilbertCurve.bits(5).dimensions(2);

    @Test
    public void testIndex1() {
        HilbertCurve c = HilbertCurve.bits(2).dimensions(2);

        assertEquals(7, c.index(1, 2).intValue());
    }

    @Test
    public void testIndex2() {
        assertEquals(256, c.index(0, 16).intValue());
    }

    @Test
    public void testToBigInteger() {
        long[] ti = { 0, 16 };
        assertEquals(256, c.toIndex(ti).intValue());
    }

    @Test
    public void testBitSet() {
        BitSet b = new BitSet(10);
        b.set(8);
        byte[] a = b.toByteArray();
        Util.reverse(a);
        assertEquals(256, new BigInteger(1, a).intValue());
    }



    @Test
    public void testTranspose() {
        long[] ti = c.transpose(BigInteger.valueOf(63));
        assertEquals(2, ti.length);
        assertEquals(0, ti[0]);
        assertEquals(16, ti[1]);
    }

    @Test
    public void testTransposeZero() {
        long[] ti = c.transpose(BigInteger.valueOf(0));
        assertEquals(2, ti.length);
        assertEquals(0, ti[0]);
        assertEquals(0, ti[1]);
    }

    @Test
    public void testPointFromIndexBits1() {
        int bits = 2;
        HilbertCurve c = HilbertCurve.bits(bits).dimensions(2);
        for (long i = 0; i < Math.round(Math.pow(2, bits)); i++) {
            System.out.println(i + "\t" + Arrays.toString(c.point(BigInteger.valueOf(i))));
        }
    }





}
