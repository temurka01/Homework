package homework_14_06_2024;

public class Tester extends Worker {
    public Tester(String name, String lastname) {
        super(name, lastname, "Тестировщик");
    }
    @Override
    public void goToWork() {
        super.goToWork();
        System.out.println("Пошел искать баги в программе");
    }

    @Override
    public void goToVacation(int days) {
        super.goToVacation(days);
        System.out.println("Поехал в Москву ходить по музеям");
    }
}
