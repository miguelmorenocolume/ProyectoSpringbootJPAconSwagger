package com.example.concesionariocoches;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coche")
public class Coche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCoche")
    private int idCoche;

    @Column(name = "matricula", length = 7)
    private String matricula;

    @Column(name = "marca", length = 45)
    private String marca;

    @Column(name = "modelo", length = 45)
    private String modelo;

    @ManyToMany(mappedBy = "coches", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Conductor> conductores;

    public Coche() {
        conductores = new HashSet<>();
    }

    public int getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Set<Conductor> getConductores() {
        return conductores;
    }

    public void setConductores(Set<Conductor> conductores) {
        this.conductores = conductores;
    }
}
