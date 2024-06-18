package homework_06_06_2024;

import java.util.Scanner;

public class If_in_array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[10];
        System.out.println("Введите 10 чисел ");
        for (int i = 0; i < 10; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.print("Введите число для поиска ");
        int target = scanner.nextInt();
        if (contains(target, array)) {
            System.out.println("Содержит");
        } else {
            System.out.println("Не содержит");
        }
    }

    public static boolean contains(int target, int... array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return true;
            }
        }
        return false;
    }


}
