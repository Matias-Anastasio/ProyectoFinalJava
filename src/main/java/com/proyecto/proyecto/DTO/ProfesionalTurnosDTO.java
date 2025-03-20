package com.proyecto.proyecto.DTO;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfesionalTurnosDTO {
    private String nombre;
    private String apellido;
    private String especialidad;
    private String legajo;
    private List<TurnoDeProfesionalDTO> turnos;
}
