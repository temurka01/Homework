package homework_14_06_2024;

public class Main {
    public static void main(String[] args) {
        Worker programmer = new Programmer("Иван", "Иванов");
        Worker tester = new Tester("Антон", "Антонов");
        Worker sysadmin = new SysAdmin("Григорий", "Григорьев");
        Worker devops = new DevOps("Борис", "Борисов");
        Worker[] workers = {programmer, tester, sysadmin, devops};
        for (int i = 0; i < workers.length; i++) {
            System.out.println("*********************************");
            workers[i].goToWork();
            System.out.println("*********************************");
            workers[i].goToVacation(i+3);
        }
    }
}
