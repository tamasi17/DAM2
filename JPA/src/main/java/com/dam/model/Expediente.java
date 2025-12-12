package com.dam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Expediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean ffeSuperada;
    private Double notaMedia;

    public Expediente() {
    }

    public Expediente(boolean ffeSuperada, Double notaMedia) {
        this.ffeSuperada = ffeSuperada;
        this.notaMedia = notaMedia;
    }

    public Long getId() {
        return id;
    }


    public boolean isFfeSuperada() {
        return ffeSuperada;
    }

    public void setFfeSuperada(boolean ffeSuperada) {
        this.ffeSuperada = ffeSuperada;
    }

    public Double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(Double notaMedia) {
        this.notaMedia = notaMedia;
    }

    @Override
    public String toString() {
        return "Expediente [" + id +
                "] FFE Superada: " + ffeSuperada +
                ", Nota Media: " + notaMedia;
    }
}
