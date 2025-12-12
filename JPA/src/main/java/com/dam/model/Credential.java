package com.dam.model;

import jakarta.persistence.*;

@Entity
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String servicio;
    private String usuario;

    // un Estudiante tiene muchas credenciales, una Credential pertenece a un solo estudiante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    public Credential(){
    }

    public Credential(String servicio, String usuario) {
        this.servicio = servicio;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "["+ id + "] " + servicio + " - " + usuario + ": " + estudiante.getNombre();
    }
}
