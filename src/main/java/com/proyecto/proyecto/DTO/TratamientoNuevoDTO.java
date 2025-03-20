package com.proyecto.proyecto.DTO;

import lombok.Data;

@Data
public class TratamientoNuevoDTO {
    private String nombre;
    private double precio;
    private String codigo;
    private String descripcion;
    private int duracion;
}
