package com.proyecto.proyecto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.proyecto.proyecto.DTO.TurnoNuevoDTO;
import com.proyecto.proyecto.service.TurnoService;

@Controller
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    // Mostrar todos los turnos
    @Operation(summary = "Obtener todos los turnos", description = "Retorna una lista con todos los turnos agendados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de turnos obtenida con éxito"),
        @ApiResponse(responseCode = "500", description = "Error interno en el servidor")
    })
    @GetMapping
    public ResponseEntity<?> getTurnos() {
        return ResponseEntity.ok().body(turnoService.buscarTurnos());
    }

    // Agendar un nuevo turno
    @Operation(summary = "Agendar un nuevo turno", description = "Agenda un nuevo turno usando los datos proporcionados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Turno agendado con éxito"),
        @ApiResponse(responseCode = "400", description = "Error en los datos proporcionados")
    })
    @PostMapping("/agendar")
    public ResponseEntity<?> agendarTurno(
        @Parameter(description = "Objeto que contiene la información del nuevo turno a agendar") @RequestBody TurnoNuevoDTO turnoNuevoDTO) {
        try {
            turnoService.agendarTurno(turnoNuevoDTO);
            return ResponseEntity.ok().body("Turno agendado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Cancelar un turno
    @Operation(summary = "Cancelar un turno", description = "Cancela un turno especificado por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Turno cancelado con éxito"),
        @ApiResponse(responseCode = "400", description = "Error al cancelar el turno"),
        @ApiResponse(responseCode = "404", description = "Turno no encontrado")
    })
    @PatchMapping("/cancelar/{idTurno}")
    public ResponseEntity<?> cancelarTurno(
        @Parameter(description = "ID del turno a cancelar") @PathVariable Long idTurno) {
        try {
            turnoService.cancelarTurno(idTurno);
            return ResponseEntity.ok("Turno Cancelado");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
