package homework_30_05_24;
import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число: ");
        int number = scanner.nextInt();
        int result = 1; // для больших number нужно ставить long
        for(int i = 2; i <= number; i++){
            result *= i;
        }
        System.out.println("Факториал числа равен = " + result);
    }
}
