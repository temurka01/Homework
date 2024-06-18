package homework_30_05_24;

import java.util.Scanner;

public class Range_of_numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа: ");
        int first = scanner.nextInt();
        int second = scanner.nextInt();
        for (int i = first; i <= second; i++) {
            System.out.print(i + " ");
        }
    }
}
