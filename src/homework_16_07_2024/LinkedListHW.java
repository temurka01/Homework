package homework_16_07_2024;

import java.util.Arrays;
import java.util.LinkedList;

public class LinkedListHW {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>(Arrays.asList("Альметьевск", "Бавлы", "Вахитово", "Елабуга", "Заинск", "Именьково", "Казань", "Лениногорск", "Мамадыш", "Набережные Челны"));
        linkedList.addFirst("Алексеевка");
        linkedList.addFirst("Азеево");
        linkedList.addFirst("Джалиль");
        linkedList.addFirst("Бугульма");
        linkedList.addFirst("Менделеевск");
        ComparatorStr comparatorStr = new ComparatorStr();
        linkedList.sort(comparatorStr);
        System.out.println(Arrays.toString(linkedList.toArray()));
        linkedList.addLast("Раифа");
        linkedList.addLast("Свияжск");
        linkedList.addLast("Столбище");
        linkedList.addLast("Октябрьский");
        linkedList.addLast("Мензелинск");
        linkedList.sort(comparatorStr);
        System.out.println(Arrays.toString(linkedList.toArray()));
        for (int i = 0; i < 3; i++) {
            linkedList.removeFirst();
        }
        System.out.println(Arrays.toString(linkedList.toArray()));
        for (int i = 0; i < 4; i++) {
            linkedList.removeLast();
        }
        System.out.println(Arrays.toString(linkedList.toArray()));
    }
}
