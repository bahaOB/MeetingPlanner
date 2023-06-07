package com.meetingplanner.repositories;

import com.meetingplanner.entities.EquipementTypeReunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipementTypeReunionRepository extends JpaRepository<EquipementTypeReunion, Integer> {

    @Query(value = "SELECT etr.libelle " +
                    "FROM equipement_type_reunion etr, type_reunion tr " +
                    "WHERE etr.type_reunion = tr.id_type_reunion " +
                    "AND tr.libelle_type_reunion = :typeReunion " +
                    "ORDER BY etr.libelle ASC", nativeQuery = true)
    List<String> findEquipementsByTypeReunion(@Param("typeReunion") String typeReunion);
}
