package com.proyecto.proyecto.model;

import java.time.LocalDate;

import com.proyecto.proyecto.DTO.PagoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

//Registra los pagos realizados por los usuarios

@Entity
@Data
@NoArgsConstructor
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double monto;
    private String metodoDePago;
    private LocalDate fechaDePago;
    private EstadoPago estado;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "turno_id", nullable = false)
    private Turno turno;

    public PagoDTO toDto(){
        PagoDTO tDto = new PagoDTO();
        tDto.setFechaDePago(fechaDePago);
        tDto.setMetodoDePago(metodoDePago);
        tDto.setEstado(estado);
        tDto.setMonto(monto);
        tDto.setTurno(turno.toDto());
        return tDto;
    }
}