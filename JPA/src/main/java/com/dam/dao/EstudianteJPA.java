package com.dam.dao;

import com.dam.model.Estudiante;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EstudianteJPA implements EstudianteDao {

    private final EntityManager entityManager;

    public EstudianteJPA(EntityManager em){
        this.entityManager = em;
    }

    @Override
    public void insert(Estudiante e) {
        this.entityManager.persist(e);
    }

    @Override
    public Estudiante get(long id) {
        return entityManager.find(Estudiante.class, id);
    }

    @Override
    public List<Estudiante> findAll() {
        return (List<Estudiante>) entityManager.createQuery("SELECT e FROM Estudiante e", Estudiante.class);
    }

    @Override
    public Estudiante update(Estudiante e) {
        return entityManager.merge(e);
    }

    @Override
    public void delete(Estudiante e) {
        entityManager.remove(e);
    }
}
