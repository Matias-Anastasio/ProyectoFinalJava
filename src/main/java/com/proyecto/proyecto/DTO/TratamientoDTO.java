package com.proyecto.proyecto.DTO;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TratamientoDTO {
    private String nombre;
    private double precio;
    private String descripcion;
    private double duracion;
    private List<ProfesionalDTO> profesionales;
}
