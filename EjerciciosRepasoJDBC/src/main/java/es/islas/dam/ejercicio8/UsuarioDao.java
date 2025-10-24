// dao/UsuarioDao.java
package es.islas.dam.ejercicio8;

import es.islas.dam.model.Usuario;
import java.util.List;

public interface UsuarioDao {
    void insertar(Usuario usuario);
    List<Usuario> obtenerTodos();
    Usuario buscarPorId(int id);
    void actualizarEmail(String nombre, String nuevoEmail);
    void eliminarPorNombre(String nombre);
}
