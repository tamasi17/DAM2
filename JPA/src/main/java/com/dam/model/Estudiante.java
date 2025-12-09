package com.dam.model;

import jakarta.persistence.*;

@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombre;
    private String apellido;
    private String curso;

    @Column(unique = true)
    private String email;

    // necesario para Hibernate
    public Estudiante() {
    }

    public Estudiante(String nombre, String apellido, String curso, String email) {
        this.nombre = nombre;
        this.curso = curso;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }

    @Override
    public String toString() {
        return  id + "- " + nombre + ", '" + curso + ": '" + email;
    }
}
