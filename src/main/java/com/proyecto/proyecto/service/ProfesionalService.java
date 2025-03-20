package com.proyecto.proyecto.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.proyecto.proyecto.DTO.ProfesionalDTO;
import com.proyecto.proyecto.DTO.ProfesionalNuevoDTO;
import com.proyecto.proyecto.DTO.ProfesionalTratamientosDTO;
import com.proyecto.proyecto.DTO.ProfesionalTurnosDTO;
import com.proyecto.proyecto.model.Profesional;
import com.proyecto.proyecto.model.Tratamiento;
import com.proyecto.proyecto.repository.ProfesionalRepository;

import jakarta.transaction.Transactional;

@Service
public class ProfesionalService {

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Autowired
    private TratamientoService tratamientoService;

    @Autowired
    @Lazy
    private TurnoService turnoService;

    public List<ProfesionalDTO> buscarProfesionales(){
        return profesionalRepository.findAll().stream().map(prof->prof.toDto()).collect(Collectors.toList());
    }

    public void guardarProfesional(Profesional profesional){
        profesionalRepository.save(profesional);
    }

    public Profesional buscarProfesional(long id){
        return profesionalRepository.findById(id).orElseThrow(() -> new RuntimeException("Profesional no encontrado"));
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

    public ProfesionalTratamientosDTO agregarTratamiento(long idProfesional, long idTratamiento) {
        
        Profesional profesional = buscarProfesional(idProfesional);
        
        Tratamiento tratamiento = tratamientoService.buscarTratamiento(idTratamiento);

        if (profesional.getTratamientos().contains(tratamiento)) {
            throw new RuntimeException("El profesional ya realiza el tratamiento ingresado");
        }

        profesional.agregarTratamiento(tratamiento);
        
        tratamientoService.agregarProfesional(profesional.getId(),tratamiento.getId());

        return profesionalRepository.save(profesional).toProfesionalTratamientosDto();
    }

    public ProfesionalTurnosDTO turnosDelProfesional(long id) {
        
        return buscarProfesional(id).toProfesionalTurnosDto();
    }

    public ProfesionalTratamientosDTO tratamientosDelProfesional(long id) {
        return buscarProfesional(id).toProfesionalTratamientosDto();
    }

    @Transactional
    public void eliminarProfesional(long id) {
        Profesional profesional = buscarProfesional(id);

        profesional.getTratamientos().forEach(t -> tratamientoService.quitarProfesional(t.getId(),profesional.getId()));

        profesional.getTurnos().forEach(t->{
            t.setProfesional(null);
            if (t.getFecha().isAfter(LocalDate.now()) && t.getHora().isAfter(LocalTime.now())) {
                turnoService.cancelarTurno(t.getId());
            }
        });
        
        profesionalRepository.delete(profesional);

    }
}
