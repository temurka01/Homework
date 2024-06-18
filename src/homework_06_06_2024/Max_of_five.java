package homework_06_06_2024;

import java.util.Scanner;

public class Max_of_five {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[5];
        System.out.println("Введите 5 чисел ");
        for (int i = 0; i < 5; i++) {
            array[i] = scanner.nextInt();
        }
        int max = array[0];
        for (int i = 1; i < 5; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        System.out.println("Маскимальный элемент - " + max);
    }
}