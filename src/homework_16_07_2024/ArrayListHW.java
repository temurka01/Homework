package homework_16_07_2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayListHW {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("Андрей", "Борис", "Владислав", "Григорий", "Дмитрий", "Евгений", "Игорь", "Кирилл", "Леонид", "Матвей"));
        arrayList.add(5, "Евлампий");
        arrayList.remove(4);
        System.out.println(arrayList.contains("Иван"));
        System.out.println(arrayList.get(9));
        arrayList.set(3, "Тарас");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        ArrayList<String> subArrayList = new ArrayList<>(arrayList.subList(4, 8));
        ArrayList<String> arrayList2 = new ArrayList<>(arrayList);
        arrayList2.removeAll(subArrayList);
    }
}
