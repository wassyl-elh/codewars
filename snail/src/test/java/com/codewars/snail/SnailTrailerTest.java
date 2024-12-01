package com.codewars.snail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SnailTrailerTest {

    private final SnailTrailer router = new SnailTrailer();

    private static final int[][] GRID_1 = new int[][] {
        {1,2,3},
        {4,5,6},
        {7,8,9}
    };
    private static final int[] RESULT_1 = new int[] {1,2,3,6,9,8,7,4,5};

    private static final int[][] GRID_2 = new int[][] {
            {1,2,3,1},
            {4,5,6,4},
            {7,8,9,7},
            {7,8,9,7}
    };

    private static final int[] RESULT_2 = new int[] {1, 2, 3, 1, 4, 7, 7, 9, 8, 7, 7, 4, 5, 6, 9, 8};

    @Test
    void snailNominalCase1(){
        assertArrayEquals(RESULT_1, router.snail(GRID_1));
    }

    @Test
    void snailNominalCase2(){
        assertArrayEquals(RESULT_2, router.snail(GRID_2));
    }

}
