package homework_14_06_2024;

public class Worker {
    private final String name;
    private final String lastname;
    private final String profession;

    public Worker(String name, String lastname, String profession) {
        this.name = name;
        this.lastname = lastname;
        this.profession = profession;
    }

    public void goToWork() {
        System.out.println("Рабочий: " + this.name + " " + this.lastname);
        System.out.println("Профессия: " + this.profession);
    }

    public void goToVacation(int days) {
        System.out.println("Рабочий " + this.name + " " + this.lastname + " уходит в отпуск на " + days);
        if (days == 1) {
            System.out.println(" день!");
        } else if (days <= 4) {
            System.out.println(" дня!");
        } else {
            System.out.println(" дней!");
        }
        System.out.println("Профессия рабочего: " + profession);
    }
}
