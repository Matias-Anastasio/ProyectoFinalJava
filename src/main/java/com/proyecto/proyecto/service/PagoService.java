package com.proyecto.proyecto.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.proyecto.DTO.PagoDTO;
import com.proyecto.proyecto.model.EstadoPago;
import com.proyecto.proyecto.model.EstadoTurno;
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

    public void cancelarPago(Long id) {
        Pago pago = pagoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        pago.setEstado(EstadoPago.CANCELADO);

        pagoRepository.save(pago);
    }

    public Optional<Pago> buscarPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public void pagar(long id, String metodoDePago) {
        
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        if (pago.getEstado() == EstadoPago.REALIZADO) {
            throw new RuntimeException("El pago ya ha sido realizado.");
        }

        if (pago.getEstado() == EstadoPago.CANCELADO) {
            throw new RuntimeException("El turno está cancelado.");
        }

        pago.setMetodoDePago(metodoDePago);
        pago.setFechaDePago(LocalDate.now());
        pago.setEstado(EstadoPago.REALIZADO);
        pago.setFechaDePago(LocalDate.now());
        pago.getTurno().setEstado(EstadoTurno.CONFIRMADO);
        pagoRepository.save(pago);
    }
    
}
