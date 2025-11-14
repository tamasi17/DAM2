package jdbc.simulacro;

import java.util.List;

public interface Dao<T> {

    T get(int id);
    void insert(T entity);
    void insert(List<T> entity);
    void update(T entity);
    void deleteById(int id);
    boolean exists(int id);
    List<T> findAll();
    List<T> findByAttributes(T filtro);


}
