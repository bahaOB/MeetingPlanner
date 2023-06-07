package com.meetingplanner.controllers;

import com.meetingplanner.requests.ReservationRequest;
import com.meetingplanner.services.MeetingPlannerService;
import com.meetingplanner.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class MeetingPlannerController {

    @Autowired
    private MeetingPlannerService meetingPlannerService;

    Logger logger = LoggerFactory.getLogger(MeetingPlannerController.class);

    @PostMapping(value ="/meetingPlanner", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> reserver(@RequestBody List<ReservationRequest> reservations){
        logger.info(" >>> Appel du service pour planifier des réunions pour ----> "+
                reservations.stream().map(r->r.getReunion()+" - "+r.getCreneau()+" - "+r.getType()+" - "+r.getNombrePersonnes()));

        if(reservations.isEmpty()){
            logger.info(" >>> Données en paramètre sont vides!");
            return new ResponseEntity<>(Constants.NO_DATA, HttpStatus.NO_CONTENT);
        }
        ResponseEntity<String> result = meetingPlannerService.reserver(reservations);

        logger.info(" >>> "+result.getBody());
        return result;
    }
}
