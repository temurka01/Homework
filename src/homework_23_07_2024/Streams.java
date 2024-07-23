package homework_23_07_2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Streams {
    public static void main(String[] args) {
        ArrayList <Person> list = new ArrayList<>();
        list.add(new Person("Ivan", 19, "Kazan"));
        list.add(new Person("John", 35, "New York"));
        list.add(new Person("Andrey", 45, "Moscow"));
        list.add(new Person("Alexey", 12, "New York"));
        list.add(new Person("Bogdan", 23, "Kazan"));
        System.out.println(
                Arrays.toString(list.stream()
                .filter(num -> num.getAge() > 18)
                .map(Person::getName)
                .sorted().toArray())
        );

        long count = list.stream()
                .filter(num -> num.getAge() > 30)
                .count();
        System.out.println(count);

        Person first = list.stream()
                .max(Comparator.comparingInt(Person::getAge))
                .get();
        System.out.println(first.getName() + " " + first.getAge() + " " + first.getCity());

        double number = list.stream()
                .filter(num -> num.getCity().equals("New York"))
                .map(Person::getAge)
                .mapToDouble(val -> val)
                .average()
                .getAsDouble();
        System.out.println(number);
    }
}
