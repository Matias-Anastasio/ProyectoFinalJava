package com.proyecto.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.proyecto.DTO.ProfesionalNuevoDTO;
import com.proyecto.proyecto.service.ProfesionalService;

@Controller
@RequestMapping("/profesionales")
public class ProfesionalController {

    @Autowired
    private ProfesionalService profesionalService;

    @GetMapping
    public ResponseEntity<?> getProfesionales(){
        return ResponseEntity.ok().body(profesionalService.buscarProfesionales());
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> addProfesional(@RequestBody ProfesionalNuevoDTO profesionalNuevoDTO){
        try{
            return ResponseEntity.ok(profesionalService.agregarProfesional(profesionalNuevoDTO));
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{idProfesional}/agregarTratamiento/{idTratamiento}")
    public ResponseEntity<?> addTratamiento(@PathVariable long idProfesional, @PathVariable long idTratamiento){
        try {
            return ResponseEntity.ok(profesionalService.agregarTratamiento(idProfesional,idTratamiento));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/turnos")
    public ResponseEntity<?> getProfesionalTurnos(@PathVariable long id){
        try{
            return ResponseEntity.ok(profesionalService.turnosDelProfesional(id));
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/tratamientos")
    public ResponseEntity<?> getProfesionalTratamientos(@PathVariable long id){
        try{
            return ResponseEntity.ok(profesionalService.tratamientosDelProfesional(id));
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<?> deleteProfesional(@PathVariable long id){
        try {
            profesionalService.eliminarProfesional(id);
            return ResponseEntity.ok().body("Profesional eliminado");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
