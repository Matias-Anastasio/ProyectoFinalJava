package com.proyecto.proyecto.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

//Registra los pagos realizados por los usuarios

@Entity
@Data
@Table(name = "PAGO")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double monto;
    private String metodoDePago;
    private LocalDate fechaDePago;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PEDIDO_ID", unique = true)
    private Pedido pedido;
}
