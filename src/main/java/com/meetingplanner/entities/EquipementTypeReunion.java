package com.meetingplanner.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EquipementTypeReunion")
public class EquipementTypeReunion extends Equipement implements Serializable {

    @ManyToOne
    @JoinColumn(name = "typeReunion",referencedColumnName = "idTypeReunion", nullable = false)
    private TypeReunion typeReunion;

    public EquipementTypeReunion(){
        super();
    }

    public EquipementTypeReunion(String libelle, TypeReunion typeReunion){
        super(libelle);
        this.typeReunion = typeReunion;
    }

    public TypeReunion getTypeReunion() {
        return typeReunion;
    }

    public void setTypeReunion(TypeReunion typeReunion) {
        this.typeReunion = typeReunion;
    }
}
