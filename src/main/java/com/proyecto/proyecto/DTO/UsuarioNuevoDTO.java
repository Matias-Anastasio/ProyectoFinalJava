package com.proyecto.proyecto.DTO;

import lombok.Data;

@Data
public class UsuarioNuevoDTO {
    private String nombre;
    private String apellido;
    private String username;
    private String email;
    private String telefono;
}
