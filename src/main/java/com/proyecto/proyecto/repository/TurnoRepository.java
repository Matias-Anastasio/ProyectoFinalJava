package com.proyecto.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyecto.model.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long>{

}
