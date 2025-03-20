package com.proyecto.proyecto.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import com.proyecto.proyecto.model.EstadoTurno;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TurnoDTO {
    private LocalDate fecha;
    private LocalTime hora;
    private EstadoTurno estado;
    private String usuario;
    private String profesional;
    private String tratamiento;
}
