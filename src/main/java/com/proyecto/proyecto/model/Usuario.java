package com.proyecto.proyecto.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

//Representa a los clientes que pueden hacer compras en la tienda

@Entity
@Data
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Turno> turnos;
}

