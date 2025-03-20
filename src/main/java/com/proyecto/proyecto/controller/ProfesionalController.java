package com.proyecto.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.proyecto.DTO.ProfesionalNuevoDTO;
import com.proyecto.proyecto.service.ProfesionalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/profesionales")
@Tag(name = "Profesionales", description = "Controlador para gestionar profesionales")
public class ProfesionalController {

    @Autowired
    private ProfesionalService profesionalService;

    @Operation(summary = "Obtener todos los profesionales", description = "Devuelve la lista de todos los profesionales registrados")
    @GetMapping
    public ResponseEntity<?> getProfesionales(){
        return ResponseEntity.ok().body(profesionalService.buscarProfesionales());
    }

    @Operation(summary = "Agregar un nuevo profesional", description = "Crea un nuevo profesional con los datos proporcionados")
    @PostMapping("/nuevo")
    public ResponseEntity<?> addProfesional(@RequestBody ProfesionalNuevoDTO profesionalNuevoDTO){
        try{
            return ResponseEntity.ok(profesionalService.agregarProfesional(profesionalNuevoDTO));
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Asignar un tratamiento a un profesional", description = "Asocia un tratamiento existente a un profesional")
    @PostMapping("/{idProfesional}/agregarTratamiento/{idTratamiento}")
    public ResponseEntity<?> addTratamiento(@PathVariable long idProfesional, @PathVariable long idTratamiento){
        try {
            return ResponseEntity.ok(profesionalService.agregarTratamiento(idProfesional,idTratamiento));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Obtener turnos de un profesional", description = "Devuelve la lista de turnos de un profesional específico")
    @GetMapping("/{id}/turnos")
    public ResponseEntity<?> getProfesionalTurnos(@PathVariable long id){
        try{
            return ResponseEntity.ok(profesionalService.turnosDelProfesional(id));
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Obtener tratamientos de un profesional", description = "Devuelve la lista de tratamientos de un profesional específico")
    @GetMapping("/{id}/tratamientos")
    public ResponseEntity<?> getProfesionalTratamientos(@PathVariable long id){
        try{
            return ResponseEntity.ok(profesionalService.tratamientosDelProfesional(id));
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un profesional", description = "Elimina un profesional basado en su ID")
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
