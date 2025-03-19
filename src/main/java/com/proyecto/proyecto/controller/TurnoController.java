package com.proyecto.proyecto.controller;

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
    @GetMapping
    public ResponseEntity<?> getTurnos() {
        return ResponseEntity.ok().body(turnoService.buscarTurnos());
    }

    // Agendar un nuevo turno
    @PostMapping("/agendar")
    public ResponseEntity<?> agendarTurno(@RequestBody TurnoNuevoDTO turnoNuevoDTO) {
        try {
            turnoService.agendarTurno(turnoNuevoDTO);
            return ResponseEntity.ok().body("Turno agendado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Cancelar un turno
    @PatchMapping("/cancelar/{idTurno}")
    public ResponseEntity<?> cancelarTurno(@PathVariable Long idTurno) {
        try {
            turnoService.cancelarTurno(idTurno);
            return ResponseEntity.ok("turno Cancelado");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
