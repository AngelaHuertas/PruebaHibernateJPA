// Persona.java
package com.angela.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "personas")
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_persona")
    private Long idPersona;

    @Column(nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "dueno", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Mascota> mascotas = new ArrayList<>();

    public Persona() {}

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdPersona() { return idPersona; }
    public void setIdPersona(Long idPersona) { this.idPersona = idPersona; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<Mascota> getMascotas() { return mascotas; }

    public void addMascota(Mascota m) {
        mascotas.add(m);
        m.setDueno(this);
    }

    public void removeMascota(Mascota m) {
        mascotas.remove(m);
        m.setDueno(null);
    }

    @Override
    public String toString() {
        return "Persona [id=" + idPersona + ", nombre=" + nombre + "]";
    }
}
