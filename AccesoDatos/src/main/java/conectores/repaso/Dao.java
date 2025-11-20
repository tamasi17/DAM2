package conectores.repaso;

import java.sql.Connection;
import java.util.List;

public interface Dao<T> {

    T get(int id);
    boolean insertOne(T entity);
    boolean insertMany(List<T> list);
    boolean insertMany(List<T> list, Connection connection);
    boolean exists(int id);
    boolean deleteById(int id);
    List<T> findAll();
    List<T> findByAttr(T filtro);
    int countRows();


}
