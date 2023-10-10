package ru.mts.homework.sort;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class SortsTest {

    @org.junit.jupiter.api.Test
    void bubbleSort() {
        assertEquals(new int[] {6,3,5,1,2,4,7,9,8}, new int[] {1,2,3,4,5,6,7,8,9});
    }

    @org.junit.jupiter.api.Test
    void basicSort() {
        assertEquals(new int[] {1,2,3,4,5,6,7,8,9}, Sorts.bubbleSort(new int[] {6,3,5,1,2,4,7,9,8}));
    }
}