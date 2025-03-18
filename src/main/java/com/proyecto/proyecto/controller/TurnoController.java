package com.proyecto.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public ResponseEntity<?> getTurnos(){
        return ResponseEntity.ok().body(turnoService.buscarTurnos());
    }

    @PostMapping("/agendar")
    public ResponseEntity<?> agendarTurno(@RequestBody TurnoNuevoDTO turnoNuevoDTO) {
        try {
            turnoService.agendarTurno(turnoNuevoDTO);
            return ResponseEntity.ok().body("Turno agendado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
