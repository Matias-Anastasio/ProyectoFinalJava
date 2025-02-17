package com.proyecto.proyecto.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

//Representa una orden de compra realizada por un usuario

@Entity
@Data
@Table(name = "PEDIDO")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private double total;
    private String estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;
}
