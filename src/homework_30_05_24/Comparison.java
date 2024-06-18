package homework_30_05_24;

import java.util.Scanner;

public class Comparison {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа: ");
        int first = scanner.nextInt();
        int second = scanner.nextInt();
        if (first > second) {
            System.out.println(first + " > " + second);
        } else if (first < second) {
            System.out.println(first + " < " + second);
        } else {
            System.out.println(first + " = " + second);
        }
    }
}