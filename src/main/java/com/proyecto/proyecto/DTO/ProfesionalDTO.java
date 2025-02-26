package com.proyecto.proyecto.DTO;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfesionalDTO {
    private String nombre;
    private String apellido;
    private String especialidad;
    private List<TratamientoDeProfesionalDTO> tratamientos;
    private List<TurnoDeProfesionalDTO> turnos;
}
