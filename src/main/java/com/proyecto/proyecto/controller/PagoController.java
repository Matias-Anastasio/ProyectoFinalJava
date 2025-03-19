package com.proyecto.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.proyecto.DTO.MetodoDePagoDTO;
import com.proyecto.proyecto.service.PagoService;

@Controller
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<?> getPagos() {
        return ResponseEntity.ok().body(pagoService.buscarPagos());
    }

    @PutMapping("/pagar/{id}")
    public ResponseEntity<?> pagar(@PathVariable long id, @RequestBody MetodoDePagoDTO metodoDePago) {
        try{
            pagoService.pagar(id, metodoDePago.getMetodoDePago());
            return ResponseEntity.ok().body("Pago realizado");
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
