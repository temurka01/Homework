package homework_24_06_2024;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Выберите способ управления телевизором: " +
                "\n1 - обычный пульт" +
                "\n2 - приложение на телефоне" +
                "\n3 - эксплуатация ребенка");
        Scanner scanner = new Scanner(System.in);
        short Case = scanner.nextShort();
        System.out.println("Числа от 1 для переключения по номерам каналов" +
                "\n0 для переключения на следующий по порядку канал" +
                "\n-1 для переключения на предыдущий по порядку канал" +
                "\n-2 для переключения на предыдущий канал" +
                "\nЛюбое другое число для выключения телевизора");
        switch (Case) {
            case 1: {
                Remote remote = new Remote(CONST.TV);
                remote.turn_on();
                break;
            }
            case 2: {
                Phone_Remote phoneRemote = new Phone_Remote(CONST.TV);
                phoneRemote.turn_on();
                break;
            }
            case 3: {
                Child_Remote childRemote = new Child_Remote(CONST.TV);
                childRemote.turn_on();
                break;
            }
        }
    }
}
