package com.example.concesionariocoches;

import javax.persistence.*;

@Embeddable

public class Direccion {

    @Column(name = "calle", length = 45)
    private String calle;

    @Column(name = "cp")
    private int cp;

    @Column(name = "localidad", length = 45)
    private String localidad;

    @Column(name = "provincia", length = 45)
    private String provincia;

    @Column(name = "numero")
    private int numero;

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
