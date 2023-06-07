package com.meetingplanner.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.meetingplanner.entities.EquipementAmovible;
import com.meetingplanner.entities.EquipementPrete;
import com.meetingplanner.entities.Salle;
import com.meetingplanner.repositories.*;
import com.meetingplanner.requests.ReservationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MeetingPlannerServiceTests {

    @InjectMocks
    SalleService salleService;

    @InjectMocks
    CheckEquipementService checkEquipementService;

    @InjectMocks
    EquipementAmovibleService equipementAmovibleService;

    @InjectMocks
    ReunionService reunionService;

    @Mock
    EquipementAmovibleRepository equipementAmovibleRepository;

    @Mock
    EquipementSalleRepository equipementSalleRepository;

    @Mock
    EquipementTypeReunionRepository equipementTypeReunionRepository;

    @Mock
    TypeReunionRepository typeReunionRepository;

    @Mock
    ReunionRepository reunionRepository;

    @Mock
    SalleRepository salleRepository;

    List<Salle> salles = new ArrayList<>();
    List<EquipementAmovible> eas = new ArrayList<>();
    List<EquipementPrete> eps = new ArrayList<>();
    ReservationRequest rr1 = new ReservationRequest();
    ReservationRequest rr2 = new ReservationRequest();

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

        Salle s1 = new Salle("E1001", 21);
        Salle s2 = new Salle("E1002", 10);
        Salle s3 = new Salle("E2002", 15);
        Salle s4 = new Salle("E3001", 13);

        salles.add(s1);
        salles.add(s2);
        salles.add(s3);
        salles.add(s4);

        EquipementAmovible e1 = new EquipementAmovible("Ecran", 5);
        EquipementAmovible e2 = new EquipementAmovible("Pieuvre", 4);
        EquipementAmovible e3 = new EquipementAmovible("Tableau", 2);
        EquipementAmovible e4 = new EquipementAmovible("Webcam", 4);

        eas.add(e1);
        eas.add(e2);
        eas.add(e3);
        eas.add(e4);

        EquipementPrete p1 = new EquipementPrete("Ecran", 0);
        EquipementPrete p2 = new EquipementPrete("Pieuvre", 0);
        EquipementPrete p3 = new EquipementPrete("Tableau", 0);
        EquipementPrete p4 = new EquipementPrete("Webcam", 0);

        eps.add(p1);
        eps.add(p2);
        eps.add(p3);
        eps.add(p4);

        rr1.setReunion("Reunion 1");
        rr1.setCreneau("8h-9h");
        rr1.setNombrePersonnes(10);
        rr1.setType("VC");

        rr2.setReunion("Reunion 2");
        rr2.setCreneau("8h-9h");
        rr2.setNombrePersonnes(12);
        rr2.setType("RC");
    }

    @Test
    void sallesAvailableAndFindSalleWithEquipments(){

        when(salleRepository.findSallesDisponiblesSelonCreneauEtNombrePlaces(
                10,
                "8h-9h",
                "8h-9h")).thenReturn(salles);

        List<Salle> listeSalles = salleService.sallesDisponiblesSelonCreneauEtNombrePlaces(
                                    10,
                                    "8h-9h",
                                    new int[]{8,9});

        boolean b = reunionService.findSalleContenantEquipements(listeSalles, rr1);

        //boolean c = reunionService.findAnotherSalle(listeSalles, rr2);

        assertEquals(4, listeSalles.size());
        assertTrue(b);
        //assertTrue(c);

        verify(salleRepository, times(1)).findSallesDisponiblesSelonCreneauEtNombrePlaces(
                                                                    10,
                                                                    "8h-9h",
                                                                    "8h-9h");
    }

    @Test
    void saveSalleEquipments(){
        List<EquipementAmovible> es = new ArrayList<>();

        EquipementAmovible e = new EquipementAmovible("Ecran", 5);

        es.add(e);

        salleService.saveSallesEquipementsPretes("E1001", es);
        verify(salleRepository, times(1)).saveSallesEquipementsPretes("E1001", e.getId());
    }

    @Test
    void checkMissEquipments(){
        List<String> liste1 = new ArrayList<>(Arrays.asList("Ecran","Pieuvre","Webcam"));
        List<String> liste2 = new ArrayList<>(Arrays.asList("Tableau","Webcam"));

        assertEquals(new ArrayList<>(Arrays.asList("Ecran", "Pieuvre")),
                checkEquipementService.checkEquipementsManquants(liste1, liste2));
    }

    @Test
    void checkEquipmentsAvailability(){
        when(checkEquipementService.checkEquipementsDisponibles(eps)).thenReturn(eas);

        List<EquipementAmovible> listeEas = checkEquipementService.checkEquipementsDisponibles(new ArrayList<EquipementPrete>());

        assertEquals(eas,listeEas);
    }

    @Test
    void findEquipementsPretes(){
        EquipementAmovibleService mock = org.mockito.Mockito.mock(EquipementAmovibleService.class);
        when(mock.findEquipementsPretes(rr1.getCreneau())).thenReturn(eps);

        List<EquipementPrete> listeEps = mock.findEquipementsPretes(rr1.getCreneau());
        assertEquals(eps, listeEps);
    }

}
