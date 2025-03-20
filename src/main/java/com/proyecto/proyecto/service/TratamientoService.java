package com.proyecto.proyecto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.DTO.TratamientoDTO;
import com.proyecto.proyecto.model.Profesional;
import com.proyecto.proyecto.model.Tratamiento;
import com.proyecto.proyecto.repository.TratamientoRepository;

@Service
public class TratamientoService {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    @Lazy
    private ProfesionalService profesionalService;

    public List<TratamientoDTO> buscarTratamientos(){
        return tratamientoRepository.findAll().stream().map(trat->trat.toDto()).collect(Collectors.toList());
    }

    public void guardarTratamiento(Tratamiento tratamiento){
        tratamientoRepository.save(tratamiento);
    }

    public Tratamiento buscarTratamiento(long id){
        return tratamientoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tratamiento no encontrado"));
    }

    public void agregarProfesional(Long idProfesional, long idTratamiento) {
        
        Tratamiento tratamiento = buscarTratamiento(idTratamiento);

        Profesional profesional = profesionalService.buscarProfesional(idProfesional);
        
        if(tratamiento.getProfesionales().contains(profesional)){
            throw new RuntimeException("El tratamiento ya lo realiza dicho profesional");
        }
        
        tratamiento.agregarProfesional(profesional);
        tratamientoRepository.save(tratamiento).toDto();
    }

    public void quitarProfesional(Long idTratamiento, Long idProfesional) {
        
        Tratamiento tratamiento = buscarTratamiento(idTratamiento);

        Profesional profesional = profesionalService.buscarProfesional(idProfesional);

        tratamiento.removerProfesional(profesional);
    }


}
