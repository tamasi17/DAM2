// dao/UsuarioDao.java
package conectores.ejercicio8;

import conectores.model.Usuario;
import java.util.List;

public interface UsuarioDao {
    void insertar(Usuario usuario);
    List<Usuario> obtenerTodos();
    Usuario buscarPorId(int id);
    void actualizarEmail(String nombre, String nuevoEmail);
    void eliminarPorNombre(String nombre);
}
