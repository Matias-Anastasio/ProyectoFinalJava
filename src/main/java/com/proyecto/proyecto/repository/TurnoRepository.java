package com.proyecto.proyecto.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyecto.model.EstadoTurno;
import com.proyecto.proyecto.model.Profesional;
import com.proyecto.proyecto.model.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long>{
    Optional<Turno> findByFechaAndHoraAndProfesionalAndEstadoNot(LocalDate fecha, LocalTime hora, Profesional profesional,EstadoTurno estado);

    List<Turno> findByFechaAndProfesionalAndEstadoNot(LocalDate fecha, Profesional profesional, EstadoTurno cancelado);
}
