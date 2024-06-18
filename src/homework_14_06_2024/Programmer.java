package homework_14_06_2024;

public class Programmer extends Worker {
    public Programmer(String name, String lastname) {
        super(name, lastname, "Программист");
    }

    @Override
    public void goToWork() {
        super.goToWork();
        System.out.println("Пошел писать классы, клац клац клац");
    }

    @Override
    public void goToVacation(int days) {
        super.goToVacation(days);
        System.out.println("Улетел на море, загорать на пляже");
    }
}
