package com.manjunath;

import org.junit.Test;
import static org.junit.Assert.*;

public class SqrootTest {  
    @Test
    public void testSqrt() {
        assertEquals(2.0, Math.sqrt(4), 0.001);
        assertEquals(0.0, Math.sqrt(0), 0.001);
    }
}
