package homework_09_07_2024;

import java.io.*;
import java.util.LinkedList;

public class HumanDAOInFileMemory implements IHumanDAO {

    private final static int memoryLenght = 20; //длина массива
    private static final LinkedList<Integer> idReserved = new LinkedList<>(); //список свободных id
    private final Human[] humans = new Human[memoryLenght];

    public HumanDAOInFileMemory() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/homework_09_07_2024/resources/humans.txt"));
            String line;
            int id;
            //заполняю массив с файла
            while ((line = bufferedReader.readLine()) != null) {
                String[] lines = line.split("/");
                id = Integer.parseInt(lines[0]);
                humans[id] = new Human(lines[2], lines[1], lines[3], Integer.parseInt(lines[4]));
                humans[id].setId(id);
            }
            //заполняю список свободных id
            for (int i = 0; i < memoryLenght; i++) {
                if (humans[i] == null) { //если ячейка массива пуста записываю её id
                    idReserved.addLast(i);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException :C");
        }
    }

    @Override
    public void create(Human human) {
        if (!idReserved.isEmpty()) { //если есть свободные id
            int id = idReserved.removeFirst(); //берем первый из списка и сразу его удаляем
            humans[id] = human; //записываем человека в ячейку с взятым id
            humans[id].setId(id);
        } else { //иначе (свободных id не осталось)
            System.out.println("Закончилось место в памяти");
        }
    }

    @Override
    public Human findById(int id) {
        if (id > memoryLenght - 1) {
            System.out.println("Введен некоректный Id");
            return null;
        }
        if (humans[id] != null) {
            return humans[id];
        }
        return null;
    }

    @Override
    public void update(Human human) {
        humans[human.getId()] = human;
    }

    @Override
    public void delete(int id) {
        humans[id] = null;
        idReserved.addLast(id);//записываем свободный id в список
    }

    @Override
    public void upload() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/homework_09_07_2024/resources/humans.txt", false));
            for (int i = 0; i < memoryLenght; i++) {
                if (humans[i] != null) {
                    bufferedWriter.write(humans[i].toString() + "\n");
                    bufferedWriter.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException :C");
        }
    }
}