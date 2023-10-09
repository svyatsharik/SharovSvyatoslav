
import ru.mts.homework.sort.Sorts;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n, k;

        System.out.println("Введите размер массива, а затем введите сам массив:");

        n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }

        System.out.println("Выберите тип сортировки: 1 - сортировка пузырьком, иначе - сортировка слиянием");
        k = input.nextInt();

        if (k == 1) {
            Sorts.bubbleSort(arr);
        } else {
            Sorts.basicSort(arr);
        }

        System.out.println("Отсортированный массив:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

    }
}