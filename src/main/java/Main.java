import ru.mts.homework.sort.*;
import ru.mts.homework.exceptions.*;;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SizeException {
        Scanner input = new Scanner(System.in);
        Integer n, k;

        Map<String, Integer> mapSorts = new HashMap<>();
        mapSorts.put("BubbleSort", 100000);
        mapSorts.put("BasicSort", 100000);


        System.out.println("Введите размер массива, а затем введите сам массив:");

        n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }

        System.out.println("Выберите тип сортировки: 1 - сортировка пузырьком, иначе - сортировка слиянием");
        k = input.nextInt();

        Boolean found = false;

        if (k == 1) {
            for (Map.Entry<String, Integer> item: mapSorts.entrySet()) {
                if (item.getKey().equals("BubbleSort") && item.getValue() >= n) {
                    found = true;
                    break;
                }
            }
            if (found) {
                BubbleSort.bubbleSort(arr);
            } else {
                throw new SizeException("Введён слишком большой массив");
            }
        } else {
            for (Map.Entry<String, Integer> item: mapSorts.entrySet()) {
                if (item.getKey().equals("BubbleSort") && item.getValue() >= n) {
                    found = true;
                    break;
                }
            }
            if (found) {
                BasicSort.basicSort(arr);
            } else {
                throw new SizeException("Введён слишком большой массив");
            }
        }

        System.out.println("Отсортированный массив:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

    }
}