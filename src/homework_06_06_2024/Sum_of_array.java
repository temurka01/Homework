package homework_06_06_2024;

import java.util.Scanner;

public class Sum_of_array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[10];
        System.out.println("Введите 10 чисел ");
        for (int i = 0; i < 10; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println("Сумма элементов массива равна " + sumArray(array));
    }

    public static int sumArray(int... array) {
        int summ = 0;
        for (int i = 0; i < array.length; i++) {
            summ += array[i];
        }
        return summ;
    }
}
