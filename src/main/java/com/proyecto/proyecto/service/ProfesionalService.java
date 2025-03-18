package com.proyecto.proyecto.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.DTO.ProfesionalDTO;
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
}
