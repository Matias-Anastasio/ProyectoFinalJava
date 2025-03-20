package com.proyecto.proyecto.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TurnoNuevoDTO {
    private LocalDate fecha;
    private LocalTime hora;
    private long usuarioId;
    private long profesionalId;
    private long tratamientoId;

}
