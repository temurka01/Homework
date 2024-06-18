package homework_06_06_2024;

import java.util.Scanner;

public class Power {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число и его степень: ");
        int base = scanner.nextInt();
        int exponent = scanner.nextInt();
        System.out.println("Результат возведения в степень - " + power(base, exponent));
    }

    public static int power(int base, int exponent) {
        int result = base;
        for (int i = 1; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
}
