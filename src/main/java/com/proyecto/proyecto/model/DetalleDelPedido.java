package com.proyecto.proyecto.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

//Almacena los productos comprados en un pedido

@Entity
@Data
@Table(name = "DETALLE_DEL_PEDIDO")
public class DetalleDelPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private double precioUnitario;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "DETALLE_PRODUCTO",
        joinColumns = @JoinColumn(name = "DETALLE_DEL_PEDIDO_ID"),
        inverseJoinColumns = @JoinColumn(name = "PRODUCTO_ID")
    )
    private List<Producto> productos;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PEDIDO_ID")
    private Pedido pedido;
}
