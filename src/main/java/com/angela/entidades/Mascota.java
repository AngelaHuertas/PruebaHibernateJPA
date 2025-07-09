package com.angela.entidades;

import java.io.Serializable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mascotas")
public class Mascota implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_mascota")
    private Long idMascota;

    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(length = 45)
    private String raza;

    @Column(length = 45)
    private String colorMascota;

    @Column(length = 45)
    private String sexo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona dueno;

    public Mascota() {}

    public Mascota(String nombre, String raza, String colorMascota, String sexo) {
        this.nombre = nombre;
        this.raza = raza;
        this.colorMascota = colorMascota;
        this.sexo = sexo;
    }

    public Long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColorMascota() {
        return colorMascota;
    }

    public void setColorMascota(String colorMascota) {
        this.colorMascota = colorMascota;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Persona getDueno() {
        return dueno;
    }

    public void setDueno(Persona dueno) {
        this.dueno = dueno;
    }

    @Override
    public String toString() {
        return "Mascota [idMascota=" + idMascota + ", nombre=" + nombre + ", raza=" + raza
                + ", colorMascota=" + colorMascota + ", sexo=" + sexo
                + ", duenoId=" + (dueno != null ? dueno.getIdPersona() : null) + "]";
    }
}

