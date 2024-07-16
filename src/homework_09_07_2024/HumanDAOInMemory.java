package homework_09_07_2024;

public class HumanDAOInMemory implements IHumanDAO {

    private static int countId = 0;//текущий id
    private final static int memoryLenght = 10; //длина массива
    private static int wasDeleted = 0;//количество удаленных обьектов, для создания новых после удаления старых
    private final Human[] humans = new Human[memoryLenght];

    @Override
    public void create(Human human) {
        if (countId > memoryLenght - 1) { //если текущий id больше размерности массива
            if (wasDeleted != 0) { //проверка на наличие удаленых элементов
                for (int i = 0; i < memoryLenght; i++) {
                    if (humans[i] == null) { //если найдено пустое место в массиве, то на его место ставится новый обьект
                        human.setId(i);
                        humans[i] = human;
                        wasDeleted--; //количество пустыхз мест в массиве уменьшается
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
        wasDeleted++; //количество пустых мест в массиве увеличивается
    }

    @Override
    public void upload() {

    }
}
