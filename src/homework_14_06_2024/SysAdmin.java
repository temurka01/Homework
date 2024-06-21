package homework_14_06_2024;

public class SysAdmin extends Worker {
    public SysAdmin(String name, String lastname) {
        super(name, lastname, "СисАдмин");
    }

    @Override
    public void goToWork() {
        super.goToWork();
        System.out.println("Пошел настраивать сервера");
    }

    @Override
    public void goToVacation(int days) {
        super.goToVacation(days);
        System.out.println("Поехал в соседний город зависать с друзьями");
    }
}
