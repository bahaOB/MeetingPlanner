package com.meetingplanner.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EquipementAmovible")
public class EquipementAmovible extends Equipement implements Serializable {

    @Column(name = "nombreEquipements")
    private int nombreEquipements;

    public EquipementAmovible(){
        super();
    }

    public EquipementAmovible(String libelle, int nombreEquipements){
        super(libelle);
        this.nombreEquipements = nombreEquipements;
    }

    public int getNombreEquipements() {
        return nombreEquipements;
    }

    public void setNombreEquipements(int nombreEquipements) {
        this.nombreEquipements = nombreEquipements;
    }

}
