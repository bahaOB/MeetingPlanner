package com.meetingplanner.services;

import com.meetingplanner.entities.EquipementAmovible;
import com.meetingplanner.entities.Salle;
import com.meetingplanner.repositories.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalleService {

    @Autowired
    private SalleRepository salleRepository;

    public List<Salle> sallesDisponiblesSelonCreneauEtNombrePlaces(int nombrePlaces, String creneau, int[] creneaux){
        if(creneaux[0] == 8 && creneaux[1] == 9){
            return salleRepository.findSallesDisponiblesSelonCreneauEtNombrePlaces(
                    nombrePlaces,
                    creneau,
                    creneau);
        }
        creneaux[0] -= 1;
        creneaux[1] -= 1;
        return salleRepository.findSallesDisponiblesSelonCreneauEtNombrePlaces(
                nombrePlaces,
                creneau,
                creneaux[0]+"-"+creneaux[1]);
    }

    public void saveSallesEquipementsPretes(String nomSalle, List<EquipementAmovible> equipementsPretes){
        equipementsPretes.forEach(ep ->
                salleRepository.saveSallesEquipementsPretes(nomSalle, ep.getId())
        );
    }
}
