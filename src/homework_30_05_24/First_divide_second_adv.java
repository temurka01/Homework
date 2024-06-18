package homework_30_05_24;

import java.util.Scanner;

public class First_divide_second_adv {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа: ");
        int first = scanner.nextInt();
        int second = scanner.nextInt();
        if (first > second) {
            System.out.println("Первое число больше второго, частное равно = " + ((double) first / second));
        } else if (first < second) {
            System.out.println("Второе число больше первого, частное равно = " + ((double) second / first));
        } else {
            System.out.println("Числа равны, частное равно = 1");
        }
    }
}
