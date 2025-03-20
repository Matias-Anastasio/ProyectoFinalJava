package com.proyecto.proyecto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.proyecto.proyecto.DTO.TratamientoDTO;
import com.proyecto.proyecto.DTO.TratamientoNuevoDTO;
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

    public TratamientoDTO agregarTratamiento(TratamientoNuevoDTO tratamientoNuevoDTO) {
        
        if(tratamientoRepository.existsByCodigo(tratamientoNuevoDTO.getCodigo())){
            throw new RuntimeException("Ya se encuentra cargado el tratamiento ingresado.");
        }

        Tratamiento nuevoTratamiento = new Tratamiento();
        nuevoTratamiento.setCodigo(tratamientoNuevoDTO.getCodigo());
        nuevoTratamiento.setDescripcion(tratamientoNuevoDTO.getDescripcion());
        nuevoTratamiento.setDuracion(tratamientoNuevoDTO.getDuracion());
        nuevoTratamiento.setNombre(tratamientoNuevoDTO.getNombre());
        nuevoTratamiento.setPrecio(tratamientoNuevoDTO.getPrecio());
        nuevoTratamiento.setProfesionales(new ArrayList<>());

        return tratamientoRepository.save(nuevoTratamiento).toDto();
    }

    public TratamientoDTO actualizarTratamiento(long id, Map<String,Object> updates){
        Tratamiento tratamiento = buscarTratamiento(id);

        updates.forEach((campo,valor)->{
            switch (campo) {
                case "nombre":
                    tratamiento.setNombre((String) valor);
                    break;
                case "descripcion":
                    tratamiento.setDescripcion((String) valor);
                    break;
                case "precio":
                    tratamiento.setPrecio((double) valor);
                    break;
                case "duracion":
                    tratamiento.setDuracion((int) valor);
                    break;
                default:
                    throw new RuntimeException("Campo no válido: "+ campo);
            }
        });
    
        return tratamientoRepository.save(tratamiento).toDto();
    }


}
