package homework_09_07_2024;

public interface IHumanDAO {
    void create(Human human);

    Human findById(int id);

    void update(Human human);

    void delete(int id);
    void upload();
}