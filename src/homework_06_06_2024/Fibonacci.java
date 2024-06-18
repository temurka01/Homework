package homework_06_06_2024;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число n: ");
        int n = scanner.nextInt();
        System.out.println(n + "-е число Фибоначчи равно " + fibonacci(n - 1, 0, 1));
    }

    public static int fibonacci(int n, int curr, int next) {
        if (n == 0) {
            return curr;
        }
        return fibonacci(n - 1, next, curr + next);
    }
}