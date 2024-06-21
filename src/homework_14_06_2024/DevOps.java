package homework_14_06_2024;

public class DevOps extends Worker {
    public DevOps(String name, String lastname) {
        super(name, lastname, "ДевОпс");
    }

    @Override
    public void goToWork() {
        super.goToWork();
        System.out.println("Пошел автоматизировать процессы");
    }

    @Override
    public void goToVacation(int days) {
        super.goToVacation(days);
        System.out.println("Поехал на природу с семьей");
    }
}
