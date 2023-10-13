package ru.mts.homework.sort;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class SortsTest {

    @Test
    void bubbleSort() {
        assertTrue(Arrays.equals(new int[] {1,2,3,4,5,6,7,8,9}, Sorts.bubbleSort(new int[] {6,3,5,1,2,4,7,9,8})));
    }


    @Test
    void basicSort() {
        assertTrue(Arrays.equals(new int[] {1,2,3,4,5,6,7,8,9}, Sorts.basicSort(new int[] {6,3,5,1,2,4,7,9,8})));
    }
}