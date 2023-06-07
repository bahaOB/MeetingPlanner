package com.meetingplanner.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Equipement")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Equipement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;

    @Column(name = "libelle")
    private String libelle;


    public Equipement() {
        super();
    }

    public Equipement(String libelle) {
        super();
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
