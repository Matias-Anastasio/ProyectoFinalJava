package com.proyecto.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.model.Pago;
import com.proyecto.proyecto.repository.PagoRepository;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> buscarPagos(){
        return pagoRepository.findAll();
    }

    public void guardarPago(Pago pago){
        pagoRepository.save(pago);
    }
}
