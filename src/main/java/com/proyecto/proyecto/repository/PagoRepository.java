package com.proyecto.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyecto.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago,Long>{

}
