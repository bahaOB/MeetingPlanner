package com.meetingplanner.repositories;

import com.meetingplanner.entities.Salle;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MeetingPlannerRepositoryTests {

    @Autowired
    SalleRepository salleRepository;

    @Autowired
    EquipementAmovibleRepository equipementAmovibleRepository;

    @Autowired
    EquipementSalleRepository equipementSalleRepository;

    @Autowired
    EquipementTypeReunionRepository equipementTypeReunionRepository;

    @Test
    public void sallesDisponibles(){
        List<Salle> salles = salleRepository.findAll();
        Assertions.assertThat(salles).containsAll(salleRepository.
                findSallesDisponiblesSelonCreneauEtNombrePlaces(4,"8h-9h","8h-9h")
        );
    }

    @Test
    public void countEquipementPrete(){
        Assertions.assertThat(0).isEqualTo(equipementAmovibleRepository.countEquipementPrete("8h-9h","VC"));
    }

    @Test
    public void findEquipementBySalle(){
        List<String> equipementsSalle = Collections.singletonList("Ecran");
        Assertions.assertThat(equipementsSalle).isEqualTo(equipementSalleRepository.findEquipementsBySalle("E1002"));
    }

    @Test
    public void findEquipementByTypeReunion(){
        List<String> equipementsTR = Arrays.asList("Ecran","Pieuvre","Webcam");
        Assertions.assertThat(equipementsTR).isEqualTo(equipementTypeReunionRepository.findEquipementsByTypeReunion("VC"));
    }
}
