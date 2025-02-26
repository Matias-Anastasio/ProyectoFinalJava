package com.proyecto.proyecto.model;

import java.util.List;
import java.util.stream.Collectors;

import com.proyecto.proyecto.DTO.ProfesionalDTO;
import com.proyecto.proyecto.DTO.ProfesionalDatosDTO;

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

    public ProfesionalDTO toDto(){
        ProfesionalDTO pDto = new ProfesionalDTO();
        pDto.setNombre(nombre);
        pDto.setApellido(apellido);
        pDto.setEspecialidad(especialidad);
        pDto.setTratamientos(tratamientos.stream().map(t->t.toTratamientoDeProfesionalDto()).collect(Collectors.toList()));
        pDto.setTurnos(turnos.stream().map(t->t.toTurnoDeProfesionalDto()).collect(Collectors.toList()));
        return pDto;
    }

    public ProfesionalDatosDTO toProfesionalDatosDto(){
        ProfesionalDatosDTO pDto = new ProfesionalDatosDTO();
        pDto.setNombre(nombre);
        pDto.setApellido(apellido);
        pDto.setEspecialidad(especialidad);
        return pDto;
    }
}

