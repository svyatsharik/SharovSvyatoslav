package ru.mts.homework.sort;
import java.util.Arrays;

public class Sorts {

    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] basicSort(int[] arr) {

        Arrays.sort(arr);
        return arr;
    }
}
