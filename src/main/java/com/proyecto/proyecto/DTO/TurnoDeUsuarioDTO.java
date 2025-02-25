package com.proyecto.proyecto.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TurnoDeUsuarioDTO {
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private String profesional;
    private String tratamiento;
}
