package homework_06_06_2024;

import java.util.Random;
import java.util.Scanner;

public class Pick_a_number {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        pick(randomNumber, scanner);
    }

    public static void pick(int number, Scanner scanner) {
        System.out.print("Введите число: ");
        int newNumber = scanner.nextInt();
        if (newNumber > number) {
            System.out.println("Меньше! ");
            pick(number, scanner);
        } else if (newNumber < number) {
            System.out.println("Больше! ");
            pick(number, scanner);
        } else {
            System.out.println("Вы угадали число!");
        }
    }
}
