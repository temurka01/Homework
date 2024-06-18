package homework_14_06_2024;

public class Worker {
    String name;
    String lastname;
    String profession;

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
        if (days <= 4) {
            System.out.println("Рабочий " + this.name + " " + this.lastname + " уходит в отпуск на " + days + " дня!");
        } else {
            System.out.println("Рабочий " + this.name + " " + this.lastname + " уходит в отпуск на " + days + " дней!");
        }
        System.out.println("Профессия рабочего: " + profession);
    }
}
