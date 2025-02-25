package com.proyecto.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.model.Profesional;
import com.proyecto.proyecto.repository.ProfesionalRepository;

@Service
public class ProfesionalService {

    @Autowired
    private ProfesionalRepository profesionalRepository;

    public List<Profesional> buscarProfesionales(){
        return profesionalRepository.findAll();
    }

    public void guardarProfesional(Profesional profesional){
        profesionalRepository.save(profesional);
    }
}
