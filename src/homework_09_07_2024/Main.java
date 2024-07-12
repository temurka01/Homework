package homework_09_07_2024;

public class Main {
    public static void main(String[] args) {
        IHumanService service = new HumanService(new HumanDAOInFileMemory());

        service.create(new Human("Oleg", "Igonin", "Leopoldovich", 28));
        service.create(new Human("Ivan", "Ivanov", "Ivanovich", 18));
        service.delete(2);
        service.create(new Human("Petr", "Petrov", "Petrovich", 28));

        service.upload();
    }
}
