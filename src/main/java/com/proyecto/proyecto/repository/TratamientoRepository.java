package com.proyecto.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyecto.model.Tratamiento;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento,Long>{

}
