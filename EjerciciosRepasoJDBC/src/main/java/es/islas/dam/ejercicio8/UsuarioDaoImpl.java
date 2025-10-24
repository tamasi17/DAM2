// dao/UsuarioDaoImpl.java
package es.islas.dam.ejercicio8;

import es.islas.dam.util.ConnectionFactory;
import es.islas.dam.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoImpl implements UsuarioDao {



    private static final String SQL_INSERT = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT id, nombre, email FROM usuarios";
    private static final String SQL_SELECT_BY_ID = "SELECT id, nombre, email FROM usuarios WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE usuarios SET email = ? WHERE nombre = ?";
    private static final String SQL_DELETE = "DELETE FROM usuarios WHERE nombre = ?";

        @Override
    public void insertar(Usuario usuario) {

    }

    @Override
    public List<Usuario> obtenerTodos() {
        return new ArrayList<>();
    }

    @Override
    public Usuario buscarPorId(int id) {
        return new Usuario();
    }

    @Override
    public void actualizarEmail(String nombre, String nuevoEmail) {

    }

    @Override
    public void eliminarPorNombre(String nombre) {

    }
}
