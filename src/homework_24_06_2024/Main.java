package homework_24_06_2024;

import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Remote remote = new Remote(CONST.TV);
        int in;
        LocalDateTime now = LocalDateTime.now(); //В зависимости от текущего часа я выбираю одну из 8 программ
        int hour = now.getHour() / 3;
        do {
            System.out.println("****************");
            System.out.println(remote.getTV().getChannels()[remote.getCondition()].getChannel_name());
            System.out.println(remote.getTV().getChannels()[remote.getCondition()].getPrograms()[hour].getProgram_name());
            System.out.println("****************");
            System.out.print("Введите число: ");
            in = scanner.nextInt();
            if (in == 0 || in == -1) {
                remote.switch_by_direction(in);
            } else if (in == -2) {
                remote.switch_back();
            } else if (in > 0) {
                remote.switch_by_number(in);
            }

        } while (in > -3);
    }
}
