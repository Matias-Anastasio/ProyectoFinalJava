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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Operation(summary = "Obtener lista de pagos", description = "Devuelve una lista con todos los pagos registrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pagos obtenida con éxito"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<?> getPagos() {
        return ResponseEntity.ok().body(pagoService.buscarPagos());
    }

    @Operation(summary = "Realizar un pago", description = "Realiza un pago con el método de pago proporcionado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago realizado con éxito"),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/pagar/{id}")
    public ResponseEntity<?> pagar(@PathVariable long id, @RequestBody MetodoDePagoDTO metodoDePago) {
        try {
            pagoService.pagar(id, metodoDePago.getMetodoDePago());
            return ResponseEntity.ok().body("Pago realizado");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
