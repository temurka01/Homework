package homework_06_06_2024;

import java.util.Scanner;

public class Min_and_max {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[10];
        System.out.println("Введите 10 чисел ");
        for (int i = 0; i < 10; i++) {
            array[i] = scanner.nextInt();
        }
        int[] result = minMax(array);
        System.out.println("Минимум - " + result[0] + " Максимум - " + result[1]);
    }

    public static int[] minMax(int... array) {
        int[] result = {array[0], array[0]};
        for (int i = 1; i < array.length; i++) {
            if (array[i] > result[1]) {
                result[1] = array[i];
            }
            if (array[i] < result[0]) {
                result[0] = array[i];
            }
        }
        return result;
    }
}