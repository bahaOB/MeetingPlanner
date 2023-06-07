package com.meetingplanner.services;

import com.meetingplanner.entities.EquipementAmovible;
import com.meetingplanner.entities.EquipementPrete;
import com.meetingplanner.repositories.EquipementAmovibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckEquipementService {

    @Autowired
    private EquipementAmovibleRepository equipementAmovibleRepository;

    public List<String> checkEquipementsManquants(List<String> liste1, List<String> liste2){
        if(liste1.size() > liste2.size())
            return liste1.stream().filter(a-> !liste2.contains(a)).collect(Collectors.toList());
        return liste2.stream().filter(a-> !liste1.contains(a)).collect(Collectors.toList());
    }

    public List<EquipementAmovible> checkEquipementsDisponibles(List<EquipementPrete> eps){
        List<EquipementAmovible> eas = equipementAmovibleRepository.findAll();

        if(eps.size() > 0) {
            List<EquipementAmovible> equipementsDisponibles = new ArrayList<>();

            for(EquipementAmovible ea : eas){
                for(EquipementPrete ep : eps){
                    if(ea.getLibelle().equals(ep.getLib()) &&
                            ea.getNombreEquipements() > ep.getNbreEquipementsPretes()){
                        equipementsDisponibles.add(ea);
                        break;
                    }
                }
            }
            return equipementsDisponibles;
        }
        return eas;
    }

}
