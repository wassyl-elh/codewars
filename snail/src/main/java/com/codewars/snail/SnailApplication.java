package com.codewars.snail;

import java.util.Arrays;

public class SnailApplication {

    public static void main(String[] args) {
        var router = new SnailTrailer();

        var grid1 = new int[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        var grid2 = new int[][] {
                {1,2,3,1},
                {4,5,6,4},
                {7,8,9,7},
                {7,8,9,7}
        };

        var result1 = router.snail(grid1);
        System.out.println(Arrays.toString(result1));
        var result2 = router.snail(grid2);
        System.out.println(Arrays.toString(result2));
    }
}