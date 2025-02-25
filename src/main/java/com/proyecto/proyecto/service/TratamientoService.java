package com.proyecto.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.model.Tratamiento;
import com.proyecto.proyecto.repository.TratamientoRepository;

@Service
public class TratamientoService {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    public List<Tratamiento> buscarTratamientos(){
        return tratamientoRepository.findAll();
    }

    public void guardarTratamiento(Tratamiento tratamiento){
        tratamientoRepository.save(tratamiento);
    }
}
