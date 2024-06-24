package homework_24_06_2024;

import java.util.Random;

public class Remote implements Remote_controller {
    private Television TV;
    private int condition;
    private int prev_condition;


    public Remote(Television TV) {
        setTV(TV);
        Random random = new Random();
        setCondition(random.nextInt(TV.getChannels().length) + 1);
        setPrev_condition(getCondition());
    }

    @Override
    public void switch_by_number(int number) {
        if (number <= TV.getChannels().length) {
            setPrev_condition(getCondition());
            setCondition(number - 1);
        } else {
            System.out.println("Введен некорректный номер канала");
        }
    }

    @Override
    public void switch_by_direction(int direction) {
        if (direction == -1) {
            if (getCondition() == 0) {
                setPrev_condition(getCondition());
                setCondition(TV.getChannels().length - 1);
            } else {
                setPrev_condition(getCondition());
                setCondition(getCondition() - 1);
            }
        } else {
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
