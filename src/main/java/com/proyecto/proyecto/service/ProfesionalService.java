package com.proyecto.proyecto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.DTO.ProfesionalDTO;
import com.proyecto.proyecto.DTO.ProfesionalNuevoDTO;
import com.proyecto.proyecto.model.Profesional;
import com.proyecto.proyecto.repository.ProfesionalRepository;

@Service
public class ProfesionalService {

    @Autowired
    private ProfesionalRepository profesionalRepository;

    public List<ProfesionalDTO> buscarProfesionales(){
        return profesionalRepository.findAll().stream().map(prof->prof.toDto()).collect(Collectors.toList());
    }

    public void guardarProfesional(Profesional profesional){
        profesionalRepository.save(profesional);
    }

    public Optional<Profesional> buscarProfesionalPorId(long id){
        return profesionalRepository.findById(id);
    }

    public ProfesionalDTO agregarProfesional(ProfesionalNuevoDTO profesionalNuevoDTO) {
        if(profesionalRepository.existsByLegajo(profesionalNuevoDTO.getLegajo())){
            throw new RuntimeException("Ya existe el profesional con el legajo ingresado");
        }

        Profesional nuevoProfesional = new Profesional();
        nuevoProfesional.setApellido(profesionalNuevoDTO.getApellido());
        nuevoProfesional.setEspecialidad(profesionalNuevoDTO.getEspecialidad());
        nuevoProfesional.setNombre(profesionalNuevoDTO.getNombre());
        nuevoProfesional.setLegajo(profesionalNuevoDTO.getLegajo());
        nuevoProfesional.setTratamientos(new ArrayList<>());
        nuevoProfesional.setTurnos(new ArrayList<>());

        return profesionalRepository.save(nuevoProfesional).toDto();
    }
}
