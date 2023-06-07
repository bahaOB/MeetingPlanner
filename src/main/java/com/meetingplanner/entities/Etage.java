package com.meetingplanner.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Etage")
public class Etage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idEtage")
    private int idEtage;

    @Column(name = "numEtage")
    private int numEtage;

    public Etage() {
    }

    public Etage(int idEtage, int numEtage) {
        this.idEtage = idEtage;
        this.numEtage = numEtage;
    }

    public int getIdEtage() {
        return idEtage;
    }

    public void setIdEtage(int idEtage) {
        this.idEtage = idEtage;
    }

    public int getNumEtage() {
        return numEtage;
    }

    public void setNumEtage(int numEtage) {
        this.numEtage = numEtage;
    }
/*
    @Override
    public String toString() {
        return "Etage{" +
                "idEtage=" + idEtage +
                ", numEtage=" + numEtage +
                '}';
    }*/
}
