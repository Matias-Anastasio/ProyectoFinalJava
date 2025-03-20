package com.proyecto.proyecto.DTO;

import java.time.LocalDate;

import com.proyecto.proyecto.model.EstadoPago;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagoDTO {

    private double monto;
    private String metodoDePago;
    private EstadoPago estado;
    private LocalDate fechaDePago;
    private TurnoDTO turno;
}
