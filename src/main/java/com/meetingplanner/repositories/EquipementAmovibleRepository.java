package com.meetingplanner.repositories;

import com.meetingplanner.entities.EquipementAmovible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipementAmovibleRepository extends JpaRepository<EquipementAmovible, Integer> {

    @Query(value =  "SELECT count(sea.id_equipement_amovible) " +
                    "FROM salle s, reunion r, salles_equipements_amovibles sea, equipement_amovible ea " +
                    "WHERE s.nom = r.nom_salle " +
                    "AND s.nom = sea.nom_salle " +
                    "AND sea.id_equipement_amovible = ea.id " +
                    "AND r.creneau = :creneau " +
                    "AND ea.libelle = :libelle", nativeQuery = true)
    int countEquipementPrete(@Param("creneau") String creneau, @Param("libelle") String libelle);
}
