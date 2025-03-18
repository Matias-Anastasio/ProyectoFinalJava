package com.proyecto.proyecto.DTO;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {
    private String nombre;
    private String apellido;
    private String username;
    private String email;
    private String telefono;
    private List<TurnoDeUsuarioDTO> turnos;
}
