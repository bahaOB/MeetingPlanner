package com.meetingplanner.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "Salle")
public class Salle implements Serializable, Comparable<Salle> {
    @Id
    @Column(name = "nom")
    private String nom;

    @Column(name = "nombrePlaces")
    private int nombrePlaces;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idEtage", nullable = false)
    private Etage etage;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "salles_equipements_amovibles",
            joinColumns = @JoinColumn(name = "nomSalle", referencedColumnName = "nom"),
            inverseJoinColumns = @JoinColumn(name = "idEquipementAmovible", referencedColumnName = "id")
    )
    private List<EquipementAmovible> equipementsPretes;

    public Salle() {
    }

    public Salle(String nom, int nombrePlaces) {
        this.nom = nom;
        this.nombrePlaces = nombrePlaces;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombrePlaces() {
        return nombrePlaces;
    }

    public void setNombrePlaces(int nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }

    public Etage getEtage() {
        return etage;
    }

    public void setEtage(Etage etage) {
        this.etage = etage;
    }

    public List<EquipementAmovible> getEquipementsPretes() {
        return equipementsPretes;
    }

    public void setEquipementsPretes(List<EquipementAmovible> equipementsPretes) {
        this.equipementsPretes = equipementsPretes;
    }

  @Override
    public String toString() {
        return nom;
    }

    @Override
    public int compareTo(Salle o) {
        return Comparator.comparing(Salle::getNombrePlaces).compare(this, o);
    }
}
