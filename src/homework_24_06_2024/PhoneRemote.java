package homework_24_06_2024;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class PhoneRemote implements RemoteController {

    private Television TV;
    private int condition;
    private int prevCondition;


    public PhoneRemote(Television TV) {
        setTV(TV);
        Random random = new Random();
        setCondition(random.nextInt(TV.getChannels().length));
        setPrevCondition(getCondition());
    }

    public void turnOn() {
        System.out.println("Запускаем приложение на телефоне для управления телевизором, и включаем его");
        Scanner scanner = new Scanner(System.in);
        int in;
        LocalDateTime now = LocalDateTime.now(); //В зависимости от текущего часа я выбираю одну из 8 программ
        int hour = now.getHour();
        int number = hour / (24 / Const.PROGRAMS.length); // по хорошему эту секцию надо засунуть в цикл
        do {
            System.out.println("************************************************");
            System.out.println(getTV().getChannels()[getCondition()].getChannelName());
            System.out.println(getTV().getChannels()[getCondition()].getPrograms()[number].getProgramName());
            System.out.println("************************************************");
            System.out.print("Нажмите на кнопку управления в приложении: ");
            in = scanner.nextInt();
            if (in == 0 || in == -1) {
                switchByDirection(in);
            } else if (in == -2) {
                switchBack();
            } else if (in > 0) {
                switchByNumber(in);
            }

        } while (in > -3);
    }

    @Override
    public void switchByNumber(int number) {
        if (number <= TV.getChannels().length) {
            System.out.println("Перемещаемся на канал под номером " + number + ", через приложение на телефоне");
            setPrevCondition(getCondition());
            setCondition(number - 1);
        } else {
            System.out.println("Введен некорректный номер канала на телефоне");
        }
    }

    @Override
    public void switchByDirection(int direction) {
        if (direction == -1) {
            System.out.println("Перемещаемся на один канал назад, через приложение на телефоне");
            if (getCondition() == 0) {
                setPrevCondition(getCondition());
                setCondition(TV.getChannels().length - 1);
            } else {
                setPrevCondition(getCondition());
                setCondition(getCondition() - 1);
            }
        } else {
            System.out.println("Перемещаемся на один канал вперед, через приложение на телефоне");
            if (getCondition() == TV.getChannels().length - 1) {
                setPrevCondition(getCondition());
                setCondition(0);
            } else {
                setPrevCondition(getCondition());
                setCondition(getCondition() + 1);
            }
        }
    }

    @Override
    public void switchBack() {
        System.out.println("Перемещаемся на предыдущий канал, через приложение на телефоне");
        int temp = getPrevCondition();
        setPrevCondition(getCondition());
        setCondition(temp);
    }

    public Television getTV() {
        return TV;
    }

    public void setTV(Television TV) {
        this.TV = TV;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getPrevCondition() {
        return prevCondition;
    }

    public void setPrevCondition(int prevCondition) {
        this.prevCondition = prevCondition;
    }
}