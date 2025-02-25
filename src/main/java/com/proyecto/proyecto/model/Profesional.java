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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "profesional")
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String especialidad;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "profesional_tratamiento",
        joinColumns = @JoinColumn(name = "profesional_id"),
        inverseJoinColumns = @JoinColumn(name = "tratamiento_id")
    )
    private List<Tratamiento> tratamientos;

    @OneToMany(mappedBy = "profesional",fetch = FetchType.EAGER)
    private List<Turno> turnos;
}

