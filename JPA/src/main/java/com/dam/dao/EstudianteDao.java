package com.dam.dao;

import com.dam.model.Estudiante;

import java.util.List;

public interface EstudianteDao {

    void insert (Estudiante e);
    Estudiante get (long id);
    List<Estudiante> findAll();
    Estudiante update(Estudiante e);
    void delete(Estudiante e);

}
