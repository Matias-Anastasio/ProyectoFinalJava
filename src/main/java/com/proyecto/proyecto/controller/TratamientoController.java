package com.proyecto.proyecto.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.proyecto.DTO.TratamientoNuevoDTO;
import com.proyecto.proyecto.service.TratamientoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/tratamientos")
@Tag(name = "Tratamientos", description = "API para la gestión de tratamientos")
public class TratamientoController {

    @Autowired
    private TratamientoService tratamientoService;

    @Operation(summary = "Obtener todos los tratamientos", description = "Devuelve una lista de tratamientos disponibles.")
    @GetMapping
    public ResponseEntity<?> getTratamientos(){
        return ResponseEntity.ok().body(tratamientoService.buscarTratamientos());
    }

    @Operation(summary = "Agregar un nuevo tratamiento", description = "Crea y almacena un nuevo tratamiento en el sistema.")
    @PostMapping("/agregar")
    public ResponseEntity<?> addTratamiento(@RequestBody TratamientoNuevoDTO tratamientoNuevoDTO){
        try {
            return ResponseEntity.ok(tratamientoService.agregarTratamiento(tratamientoNuevoDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Modificar un tratamiento", description = "Actualiza los datos de un tratamiento específico basado en su ID.")
    @PatchMapping("/{id}/modificar")
    public ResponseEntity<?> editTratamiento(@PathVariable long id, @RequestBody Map<String, Object> updates){
        try {
            return ResponseEntity.ok(tratamientoService.actualizarTratamiento(id, updates));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
