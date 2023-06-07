package com.meetingplanner.repositories;

import com.meetingplanner.entities.TypeReunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeReunionRepository extends JpaRepository<TypeReunion, Integer> {

    TypeReunion findByLibelleTypeReunion(String libelle);
}
