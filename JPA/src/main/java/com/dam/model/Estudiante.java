package com.dam.model;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

import java.util.ArrayList;
import java.util.List;

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

    @JoinColumn(name = "id_expediente") // desde estudiante haremos getExpediente()
    @OneToOne(
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private Expediente expediente;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "estudiantes_asignaturas",
            joinColumns = @JoinColumn(name="id_estudiante"),
            inverseJoinColumns = @JoinColumn(name ="id_asignatura")

    )
    private List<Asignatura> asignaturas = new ArrayList<>();

    @OneToMany(
            mappedBy = "estudiante", // nombre del atributo que tiene la FK
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Credential> credenciales = new ArrayList<>();

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


    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }

    public List<Credential> getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(List<Credential> credenciales) {
        this.credenciales = credenciales;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    // Helper methods
    public void addCredential(Credential credential){
        credenciales.add(credential);
        credential.setEstudiante(this); // esto mantiene consistencia entre las entidades relacionadas
    }

    public void removeCredential(Credential credential){
        credenciales.remove(credential);
        credential.setEstudiante(null); // si borramos credencial, quitamos su relacion con el Estudiante
    }

    public void matricularse(Asignatura asignatura){
        asignaturas.add(asignatura);
        asignatura.getEstudiantes().add(this);
    }

    public void desmatricularse(Asignatura asignatura){
        asignaturas.remove(asignatura);
        asignatura.getEstudiantes().remove(this);
    }

    @Override
    public String toString() {
        return  id + "- " + nombre + ", '" + curso + ": '" + email;
    }
}
