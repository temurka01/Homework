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
                Remote remote = new Remote(Const.TV);
                remote.turnOn();
                break;
            }
            case 2: {
                PhoneRemote phoneRemote = new PhoneRemote(Const.TV);
                phoneRemote.turnOn();
                break;
            }
            case 3: {
                ChildRemote childRemote = new ChildRemote(Const.TV);
                childRemote.turnOn();
                break;
            }
        }
    }
}
