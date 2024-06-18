package homework_30_05_24;

import java.util.Scanner;

public class First_multiply_second_adv {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа: ");
        int first = scanner.nextInt();
        int second = scanner.nextInt();
        if (first < 0 || second < 0) {
            System.out.println("Я не хочу работать с отрицательными числами");
        } else {
            System.out.println("Произведение равно = " + (first * second));
        }
    }
}
