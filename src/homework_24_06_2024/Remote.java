package homework_24_06_2024;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class Remote implements Remote_Controller {
    private Television TV;
    private int condition;
    private int prev_condition;


    public Remote(Television TV) {
        setTV(TV);
        Random random = new Random();
        setCondition(random.nextInt(TV.getChannels().length));
        setPrev_condition(getCondition());
    }

    public void turn_on() {
        System.out.println("Берем в руки пульт и включаем телевизор");
        Scanner scanner = new Scanner(System.in);
        int in;
        LocalDateTime now = LocalDateTime.now(); //В зависимости от текущего часа я выбираю одну из 8 программ
        int hour = now.getHour();
        int number = hour / (24 / CONST.PROGRAMS.length); // по хорошему эту секцию надо засунуть в цикл
        do {
            System.out.println("************************************************");
            System.out.println(getTV().getChannels()[getCondition()].getChannel_name());
            System.out.println(getTV().getChannels()[getCondition()].getPrograms()[number].getProgram_name());
            System.out.println("************************************************");
            System.out.print("Нажмите на кнопку управления на пульте: ");
            in = scanner.nextInt();
            if (in == 0 || in == -1) {
                switch_by_direction(in);
            } else if (in == -2) {
                switch_back();
            } else if (in > 0) {
                switch_by_number(in);
            }

        } while (in > -3);
    }

    @Override
    public void switch_by_number(int number) {
        if (number <= TV.getChannels().length) {
            System.out.println("Перемещаемся на канал под номером " + number + ", через обычный пульт");
            setPrev_condition(getCondition());
            setCondition(number - 1);
        } else {
            System.out.println("Введен некорректный номер канала на обычном пульте");
        }
    }

    @Override
    public void switch_by_direction(int direction) {
        if (direction == -1) {
            System.out.println("Перемещаемся на один канал назад, через обычный пульт");
            if (getCondition() == 0) {
                setPrev_condition(getCondition());
                setCondition(TV.getChannels().length - 1);
            } else {
                setPrev_condition(getCondition());
                setCondition(getCondition() - 1);
            }
        } else {
            System.out.println("Перемещаемся на один канал вперед, через обычный пульт");
            if (getCondition() == TV.getChannels().length - 1) {
                setPrev_condition(getCondition());
                setCondition(0);
            } else {
                setPrev_condition(getCondition());
                setCondition(getCondition() + 1);
            }
        }
    }

    @Override
    public void switch_back() {
        System.out.println("Перемещаемся на предыдущий канал, через обычный пульт");
        int temp = getPrev_condition();
        setPrev_condition(getCondition());
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

    public int getPrev_condition() {
        return prev_condition;
    }

    public void setPrev_condition(int prev_condition) {
        this.prev_condition = prev_condition;
    }
}
