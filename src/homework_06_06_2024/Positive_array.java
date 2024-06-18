package homework_06_06_2024;

import java.util.Scanner;

public class Positive_array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[10];
        System.out.println("Введите 10 чисел ");
        for (int i = 0; i < 10; i++) {
            array[i] = scanner.nextInt();
        }
        for (int i = 0; i < 10; i++) {
            if (array[i] > 0) {
                System.out.print(array[i] + " ");
            }
        }
    }
}
