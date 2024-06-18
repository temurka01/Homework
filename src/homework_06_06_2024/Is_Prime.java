package homework_06_06_2024;

import java.util.Scanner;

public class Is_Prime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        int number = scanner.nextInt();
        if (isPrime(number)) {
            System.out.println("Простое");
        } else {
            System.out.println("Не простое");
        }
    }

    public static boolean isPrime(int number) {
        for (int i = 2; i < Math.pow(number, 0.5); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
