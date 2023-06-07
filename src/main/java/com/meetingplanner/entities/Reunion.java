package com.meetingplanner.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Reunion")
public class Reunion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idReunion")
    private int idReunion;

    @Column(name = "titre")
    private String titre;

    @Column(name = "creneau")
    private String creneau;

    @Column(name = "dateCreation")
    private Date dateCreation;

    @Column(name = "nombrePersonnes")
    private int nombrePersonnes;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "typeR", referencedColumnName = "idTypeReunion", nullable = false)
    private TypeReunion typeR;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "nomSalle", referencedColumnName = "nom")
    private Salle salle;

    public Reunion() {
    }

    public Reunion(String titre, String creneau, Date dateCreation, int nombrePersonnes, TypeReunion typeR, Salle salle) {
        this.titre = titre;
        this.creneau = creneau;
        this.dateCreation = dateCreation;
        this.nombrePersonnes = nombrePersonnes;
        this.typeR = typeR;
        this.salle = salle;
    }

    public int getIdReunion() {
        return idReunion;
    }

    public void setIdReunion(int idReunion) {
        this.idReunion = idReunion;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCreneau() {
        return creneau;
    }

    public void setCreneau(String creneau) {
        this.creneau = creneau;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getNombrePersonnes() {
        return nombrePersonnes;
    }

    public void setNombrePersonnes(int nombrePersonnes) {
        this.nombrePersonnes = nombrePersonnes;
    }

    public TypeReunion getTypeR() {
        return typeR;
    }

    public void setTypeR(TypeReunion typeR) {
        this.typeR = typeR;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    @Override
    public String toString() {
        return "Reunion{" +
                "titre='" + titre + '\'' +
                ", creneau='" + creneau + '\'' +
                ", dateCreation='" + dateCreation + '\'' +
                ", nombrePersonnes=" + nombrePersonnes +
                ", typeR=" + typeR +
                ", salle=" + salle +
                '}';
    }
}
