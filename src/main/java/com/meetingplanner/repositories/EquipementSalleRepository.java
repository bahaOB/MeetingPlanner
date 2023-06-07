package com.meetingplanner.repositories;

import com.meetingplanner.entities.EquipementSalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipementSalleRepository extends JpaRepository<EquipementSalle, Integer> {

    @Query(value = "SELECT es.libelle " +
                    "FROM equipement_salle es, salle s " +
                    "WHERE es.nom_salle = s.nom " +
                    "AND es.nom_salle = :nomSalle " +
                    "ORDER BY es.libelle ASC ", nativeQuery = true)
    List<String> findEquipementsBySalle(@Param("nomSalle") String nomSalle);
}
