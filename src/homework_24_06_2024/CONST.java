package homework_24_06_2024;

public class CONST {
    public static final Program[] PROGRAMS = {new Program("Программа 1"),
            new Program("Программа 2"),
            new Program("Программа 3"),
            new Program("Программа 4"),
            new Program("Программа 5"),
            new Program("Программа 6"),
            new Program("программа 7"),
            new Program("Программа 8")
    };
    public static final Television TV = new Television(new Channel("Первый канал", CONST.PROGRAMS),
                new Channel("Россия 1", CONST.PROGRAMS),
                new Channel("ТВЦ", CONST.PROGRAMS),
                new Channel("НТВ", CONST.PROGRAMS),
                new Channel("5 Канал", CONST.PROGRAMS)
    );
}
