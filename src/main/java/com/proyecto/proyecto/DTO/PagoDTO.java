package com.proyecto.proyecto.DTO;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagoDTO {

    private double monto;
    private String metodoDePago;
    private LocalDate fechaDePago;
    private TurnoDTO turno;
}
