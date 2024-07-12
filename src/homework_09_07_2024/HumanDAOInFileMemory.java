package homework_09_07_2024;

import java.io.*;

public class HumanDAOInFileMemory implements IHumanDAO {

    private final static int memoryLenght = 20;
    private static final int[] idReserved = new int[memoryLenght];
    private static int controlSumm = -memoryLenght;
    private final Human[] humans = new Human[memoryLenght];

    public HumanDAOInFileMemory() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/homework_09_07_2024/resources/humans.txt"));
            String line;
            int id;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lines = line.split("/");
                id = Integer.parseInt(lines[0]);
                humans[id] = new Human(lines[2], lines[1], lines[3], Integer.parseInt(lines[4]));
                humans[id].setId(id);
            }
            // заполняю массив с свободными значениями id
            int j = 0;
            for (int i = 0; i < memoryLenght; i++) {
                if (humans[i] == null) {
                    idReserved[j] = i;
                    controlSumm += 1;
                    j++;
                }
            }
            while (j != memoryLenght) {
                idReserved[j] = -1;

                j++;
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException :C");
        }
    }

    @Override
    public void create(Human human) {
        if (controlSumm != -memoryLenght) {
            int id = 0;
            for (int i = 0; i < memoryLenght; i++) {
                if (idReserved[i] != -1) {
                    id = idReserved[i];
                    idReserved[i] = -1;
                    controlSumm -= 1;
                    break;
                }
            }
            humans[id] = human;
            humans[id].setId(id);
        } else {
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
        for (int i = 0; i < memoryLenght; i++) {
            if (idReserved[i] == -1) {
                idReserved[i] = id;
                controlSumm += id;
                break;
            }
        }
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