package homework_09_07_2024;

public class HumanService implements IHumanService {

    private final IHumanDAO humanDAO;

    public HumanService(IHumanDAO humanDAO) {
        this.humanDAO = humanDAO;
    }

    @Override
    public void create(Human human) {
        if (human.getAge() > 0) {
            humanDAO.create(human);
        } else {
            throw new IllegalArgumentException("Не верный возраст");
        }
    }

    @Override
    public Human findById(int id) {
        return humanDAO.findById(id);
    }

    @Override
    public void update(Human human) {
        humanDAO.update(human);
    }

    @Override
    public void delete(int id) {
        humanDAO.delete(id);
    }

    @Override
    public void upload() {
        humanDAO.upload();
    }
}
