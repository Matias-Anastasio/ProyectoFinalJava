package com.proyecto.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyecto.model.Profesional;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional,Long>{

    boolean existsByLegajo(String legajo);

}
