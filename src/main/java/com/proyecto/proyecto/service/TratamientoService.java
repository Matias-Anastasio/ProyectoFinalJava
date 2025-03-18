package com.proyecto.proyecto.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.DTO.TratamientoDTO;
import com.proyecto.proyecto.model.Tratamiento;
import com.proyecto.proyecto.repository.TratamientoRepository;

@Service
public class TratamientoService {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    public List<TratamientoDTO> buscarTratamientos(){
        return tratamientoRepository.findAll().stream().map(trat->trat.toDto()).collect(Collectors.toList());
    }

    public void guardarTratamiento(Tratamiento tratamiento){
        tratamientoRepository.save(tratamiento);
    }

    public Optional<Tratamiento> buscarTratamientoPorId(long id){
        return tratamientoRepository.findById(id);
    }
}
