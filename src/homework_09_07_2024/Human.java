package homework_09_07_2024;

public class Human {
    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private int age;

    public Human(String firstName,
                 String lastName,
                 String patronymic,
                 int age) {
        setFirstName(firstName);
        setLastName(lastName);
        setPatronymic(patronymic);
        setAge(age);
    }

    public void printInfo() {
        System.out.println("Фамилия - " + getLastName());
        System.out.println("Имя - " + getFirstName());
        System.out.println("Отчество - " + getPatronymic());
        System.out.println("Возраст - " + getAge());
        System.out.println("*****************************");
    }

    public String toString() {
        return getId() + "/" +
                getLastName() + "/" +
                getFirstName() + "/" +
                getPatronymic() + "/" +
                getAge();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
