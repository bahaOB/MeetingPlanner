package com.meetingplanner.services;

import com.meetingplanner.entities.EquipementAmovible;
import com.meetingplanner.entities.EquipementPrete;
import com.meetingplanner.entities.Reunion;
import com.meetingplanner.entities.Salle;
import com.meetingplanner.repositories.*;
import com.meetingplanner.requests.ReservationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReunionService {

    @Autowired
    private EquipementSalleRepository equipementSalleRepository;

    @Autowired
    private EquipementTypeReunionRepository equipementTypeReunionRepository;

    @Autowired
    private TypeReunionRepository typeReunionRepository;

    @Autowired
    private ReunionRepository reunionRepository;

    @Autowired
    private SalleService salleService;

    @Autowired
    private CheckEquipementService checkEquipementService;

    @Autowired
    private EquipementAmovibleService equipementAmovibleService;

    public boolean findSalleContenantEquipements(List<Salle> sallesDisponibles,
                                          ReservationRequest reservationRequest){
        for(Salle sd : sallesDisponibles){
            List<String> equipementSalles = equipementSalleRepository.
                    findEquipementsBySalle(sd.getNom());

            List<String> equipementsTypeReunion = equipementTypeReunionRepository.
                    findEquipementsByTypeReunion(reservationRequest.getType());

            if (equipementSalles.containsAll(equipementsTypeReunion)) {
                reunionRepository.save(new Reunion(
                        reservationRequest.getReunion(),
                        reservationRequest.getCreneau(),
                        new Date(),
                        reservationRequest.getNombrePersonnes(),
                        typeReunionRepository.findByLibelleTypeReunion(reservationRequest.getType()),
                        sd));
                return true;
            }
        }
        return false;
    }

    public boolean findAnotherSalle(List<Salle> sallesDisponibles,
                             ReservationRequest reservationRequest) {

        List<EquipementPrete> ep = equipementAmovibleService.
                findEquipementsPretes(reservationRequest.getCreneau());

        List<EquipementAmovible> equipementsDisponibles = checkEquipementService.
                checkEquipementsDisponibles(ep);

        for(Salle sd : sallesDisponibles){
            List<String> equipementSalles = equipementSalleRepository.
                    findEquipementsBySalle(sd.getNom());

            List<String> equipementsTypeReunion = equipementTypeReunionRepository.
                    findEquipementsByTypeReunion(reservationRequest.getType());

            List<String> equipementsManquants = checkEquipementService.
                    checkEquipementsManquants(equipementSalles, equipementsTypeReunion);

            if (new ArrayList<>(equipementsDisponibles.stream().map(EquipementAmovible::getLibelle)
                    .collect(Collectors.toList())).containsAll(equipementsManquants)) {

                reunionRepository.save(new Reunion(
                        reservationRequest.getReunion(),
                        reservationRequest.getCreneau(),
                        new Date(),
                        reservationRequest.getNombrePersonnes(),
                        typeReunionRepository.findByLibelleTypeReunion(reservationRequest.getType()),
                        sd
                ));

                salleService.saveSallesEquipementsPretes(
                        sd.getNom(),
                        equipementsDisponibles
                                .stream()
                                .filter(e -> equipementsManquants.contains(e.getLibelle()))
                                .collect(Collectors.toList()));

                return true;
            }
        }
        return false;
    }
}
