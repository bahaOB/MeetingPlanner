package com.meetingplanner.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TypeReunion")
public class TypeReunion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTypeReunion")
    private int idTypeReunion;

    @Column(name = "libelleTypeReunion")
    private String libelleTypeReunion;

    public TypeReunion() {
    }

    public TypeReunion(int idTypeReunion, String libelleTypeReunion) {
        this.idTypeReunion = idTypeReunion;
        this.libelleTypeReunion = libelleTypeReunion;
    }

    public int getIdTypeReunion() {
        return idTypeReunion;
    }

    public void setIdTypeReunion(int idTypeReunion) {
        this.idTypeReunion = idTypeReunion;
    }

    public String getLibelleTypeReunion() {
        return libelleTypeReunion;
    }

    public void setLibelleTypeReunion(String libelleTypeReunion) {
        this.libelleTypeReunion = libelleTypeReunion;
    }

}
