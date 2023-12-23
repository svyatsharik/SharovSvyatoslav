package ru.mts.homework.sorts;

import org.junit.jupiter.api.Test;
import ru.mts.homework.sorts.sort.BubbleSort;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {
    @Test
    void bubbleSort() {
        assertTrue(Arrays.equals(new int[] {1,2,3,4,5,6,7,8,9}, BubbleSort.bubbleSort(new int[] {6,3,5,1,2,4,7,9,8})));
    }

}