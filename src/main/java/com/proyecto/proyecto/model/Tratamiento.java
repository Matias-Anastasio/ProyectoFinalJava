package com.proyecto.proyecto.model;

import java.util.List;
import java.util.stream.Collectors;

import com.proyecto.proyecto.DTO.TratamientoDTO;
import com.proyecto.proyecto.DTO.TratamientoDeProfesionalDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tratamiento")
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private double precio;
    private String descripcion;
    private int duracion;
    
    @ManyToMany(mappedBy = "tratamientos", fetch = FetchType.EAGER)
    private List<Profesional> profesionales;

    public TratamientoDeProfesionalDTO toTratamientoDeProfesionalDto(){
        TratamientoDeProfesionalDTO tDto = new TratamientoDeProfesionalDTO();
        tDto.setNombre(this.nombre);
        tDto.setDescripcion(this.descripcion);
        tDto.setPrecio(this.precio);
        tDto.setDuracion(this.duracion);
        return tDto;
    }

    public TratamientoDTO toDto(){
        TratamientoDTO tDto = new TratamientoDTO();
        tDto.setNombre(this.nombre);
        tDto.setDescripcion(this.descripcion);
        tDto.setPrecio(this.precio);
        tDto.setDuracion(this.duracion);
        tDto.setProfesionales(this.profesionales.stream().map(p->p.toProfesionalDatosDto()).collect(Collectors.toList()));
        return tDto;
    }
}

