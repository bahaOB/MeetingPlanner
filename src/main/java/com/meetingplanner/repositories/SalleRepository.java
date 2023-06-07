package com.meetingplanner.repositories;

import com.meetingplanner.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SalleRepository extends JpaRepository<Salle, String> {

    @Query(value = "SELECT s.* " +
                   "FROM salle s " +
                   "WHERE s.nombre_places >= :nombrePlaces " +
                   "AND s.nom NOT IN ( SELECT r.nom_salle " +
                                       "FROM reunion r " +
                                       "WHERE r.date_creation = now() "  +
                                       "AND r.creneau IN (:creneau, :creneauPrecedent));"
                   ,nativeQuery = true)
    List<Salle> findSallesDisponiblesSelonCreneauEtNombrePlaces(@Param("nombrePlaces") int nombrePlaces,
                                                                @Param("creneau") String creneau,
                                                                @Param("creneauPrecedent") String creneauPrecedent);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO salles_equipements_amovibles(nom_salle, id_equipement_amovible) " +
                    "Values(:nomSalle, :idEquipementAmovible);", nativeQuery = true)
    void saveSallesEquipementsPretes(@Param("nomSalle") String nomSalle,
                                     @Param("idEquipementAmovible") int idEquipementAmovible);

}
