package homework_24_06_2024;

public class Const {
    public static final Program[] PROGRAMS = {              //лень писать по 8(или больше) видов программ для каждого канала
            new Program("Программа 1"),         // поэтому одна для всех
            new Program("Программа 2"),
            new Program("Программа 3"),
            new Program("Программа 4"),
            new Program("Программа 5"),
            new Program("Программа 6"),
            new Program("Программа 7"),
            new Program("Программа 8")
    };
    public static final Television TV = new Television(
            new Channel("Первый канал", Const.PROGRAMS),
            new Channel("Россия 1", Const.PROGRAMS),
            new Channel("ТВЦ", Const.PROGRAMS),
            new Channel("НТВ", Const.PROGRAMS),
            new Channel("5 Канал", Const.PROGRAMS)
    );
}
