package com.proyecto.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
