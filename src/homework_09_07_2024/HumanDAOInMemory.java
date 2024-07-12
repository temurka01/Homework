package homework_09_07_2024;

public class HumanDAOInMemory implements IHumanDAO {

    private static int countId = 0;
    private final static int memoryLenght = 10;
    private static int wasDeleted = 0;
    private final Human[] humans = new Human[memoryLenght];

    @Override
    public void create(Human human) {
        if (countId > memoryLenght - 1) {
            if (wasDeleted != 0) {
                for (int i = 0; i < memoryLenght; i++) {
                    if (humans[i] == null) {
                        human.setId(i);
                        humans[i] = human;
                        wasDeleted--;
                    }
                }
            } else {
                System.out.println("Закончилось место в памяти");
            }
        }
        human.setId(countId);
        humans[countId] = human;
        countId++;
    }

    @Override
    public Human findById(int id) {
        if(id > memoryLenght - 1){
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
        wasDeleted++;
    }

    @Override
    public void upload() {

    }
}
