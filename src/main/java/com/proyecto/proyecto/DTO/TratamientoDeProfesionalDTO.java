package com.proyecto.proyecto.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TratamientoDeProfesionalDTO {
    private String nombre;
    private double precio;
    private String descripcion;
    private double duracion;
}
