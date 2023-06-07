package com.meetingplanner.services;

import com.meetingplanner.entities.*;
import com.meetingplanner.exceptions.APIException;
import com.meetingplanner.exceptions.MeetingException;
import com.meetingplanner.requests.ReservationRequest;
import com.meetingplanner.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MeetingPlannerService {

    @Autowired
    private SalleService salleService;

    @Autowired
    private ReunionService reunionService;

    private Calendar cal = Calendar.getInstance();

    public ResponseEntity<String> reserver(List<ReservationRequest> reservations){

        List<Salle> sallesDisponibles = new ArrayList<>();
        List<ReservationRequest> reservationsNonEffectues = new ArrayList<>();

        for(ReservationRequest reservation : reservations){

            int[] creneaux = Arrays.stream(
                    reservation.getCreneau().replaceAll("[^0-9-]", "").split("-")
                    ).mapToInt(Integer::parseInt).toArray();

            validateCreneau(creneaux, reservation);

            try{
                sallesDisponibles = salleService.sallesDisponiblesSelonCreneauEtNombrePlaces(
                                reservation.getNombrePersonnes(),
                                reservation.getCreneau(),
                                creneaux)
                        .stream().sorted().collect(Collectors.toList());

                if(sallesDisponibles.isEmpty()){
                    throw new APIException(Constants.NOT_AVAILABLE);
                }else{

                    boolean salleContenantEquipement = reunionService.findSalleContenantEquipements(sallesDisponibles, reservation);

                    if(!salleContenantEquipement){
                        boolean autreSalle = reunionService.findAnotherSalle(sallesDisponibles, reservation);

                        if(!autreSalle){
                            reservationsNonEffectues.add(reservation);
                        }
                    }
                }
            }catch(Exception e){
                return new ResponseEntity<>(Constants.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        if(!reservationsNonEffectues.isEmpty())
            return new ResponseEntity<>(Constants.NOT_FOUND +
                    reservationsNonEffectues.
                            stream().
                            map(rne -> rne.getReunion()).
                            collect(Collectors.joining("-","{","}")),
                    HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(Constants.SUCCESS, HttpStatus.OK);
    }

    public void validateCreneau(int[] creneaux, ReservationRequest r){
        if( cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
            cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
            (creneaux[1]-creneaux[0]!=1) ||
            creneaux[0]<8 ||
            creneaux[1]>20 ){
                throw new MeetingException(Constants.NOT_AUTHORIZED +
                        r.getReunion()+" - "+r.getCreneau()+" - "+r.getType()+" - "+r.getNombrePersonnes());
        }
    }

}
