package com.proyecto.proyecto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.DTO.PagoDTO;
import com.proyecto.proyecto.model.Pago;
import com.proyecto.proyecto.repository.PagoRepository;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<PagoDTO> buscarPagos(){
        return pagoRepository.findAll().stream().map(pago->pago.toDto()).collect(Collectors.toList());
    }

    public void guardarPago(Pago pago){
        pagoRepository.save(pago);
    }
}
