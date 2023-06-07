package com.meetingplanner.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EquipementSalle")
public class EquipementSalle extends Equipement implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nomSalle", referencedColumnName = "nom", nullable = false)
    private Salle salle;

    public EquipementSalle(){
        super();
    }

    public EquipementSalle(String libelle, Salle salle){
        super(libelle);
        this.salle = salle;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
}
