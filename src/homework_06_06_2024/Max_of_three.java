package homework_06_06_2024;

import java.util.Scanner;

public class Max_of_three {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите три числа:");
        int first = scanner.nextInt();
        int second = scanner.nextInt();
        int third = scanner.nextInt();
        if (first >= second && first >= third) {
            System.out.println(first);
        } else if (second > third) {
            System.out.println(second);
        } else {
            System.out.println(third);
        }
    }
}
