package com.meetingplanner.services;

import com.meetingplanner.entities.EquipementAmovible;
import com.meetingplanner.entities.EquipementPrete;
import com.meetingplanner.repositories.EquipementAmovibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipementAmovibleService {

    @Autowired
    EquipementAmovibleRepository equipementAmovibleRepository;

    public List<EquipementPrete> findEquipementsPretes(String creneau){

        List<EquipementAmovible> eas = equipementAmovibleRepository.findAll();
        List<EquipementPrete> eps = new ArrayList<>();

        eas.forEach(ea -> eps.add(
                new EquipementPrete(
                        ea.getLibelle(),
                        equipementAmovibleRepository.countEquipementPrete(creneau, ea.getLibelle())
                )
        ));

        return eps;
    }
}
