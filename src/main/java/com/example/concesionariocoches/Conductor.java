package com.example.concesionariocoches;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "conductor")
public class Conductor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConductor")
    private int idConductor;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "apellidos", length = 45)
    private String apellidos;

    @Column(name = "anyosCarnet")
    private int anyosCarnet;

    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "conductor_conduce_coche",
            joinColumns = @JoinColumn(name = "idConductor"),
            inverseJoinColumns = @JoinColumn(name = "idCoche")
    )
    @JsonIgnore
    private Set<Coche> coches;

    public Conductor() {
        coches = new HashSet<>();
    }

    @Embedded
    private Direccion direccion;

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getAnyosCarnet() {
        return anyosCarnet;
    }

    public void setAnyosCarnet(int anyosCarnet) {
        this.anyosCarnet = anyosCarnet;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Set<Coche> getCoches() {
        return coches;
    }

    public void setCoches(Set<Coche> coches) {
        this.coches = coches;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
