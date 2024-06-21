package homework_14_06_2024;

public class Worker {
    private String name;
    private String lastname;
    private String profession;

    public Worker(String name, String lastname, String profession) {
        setName(name);
        setLastname(lastname);
        setProfession(profession);
    }

    public void goToWork() {
        System.out.println("Рабочий: " + getName() + " " + getLastname());
        System.out.println("Профессия: " + getProfession());
    }

    public void goToVacation(int days) {
        System.out.print("Рабочий " + getName() + " " + getLastname() + " уходит в отпуск на " + days);
        if (days == 1) {
            System.out.print(" день!\n");
        } else if (days <= 4) {
            System.out.print(" дня!\n");
        } else {
            System.out.print(" дней!\n");
        }
        System.out.println("Профессия рабочего: " + getProfession());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
