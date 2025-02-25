package com.proyecto.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.model.Turno;
import com.proyecto.proyecto.repository.TurnoRepository;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    public List<Turno> buscarTurnos(){
        return turnoRepository.findAll();
    }

    public void guardarTurno(Turno turno){
        turnoRepository.save(turno);
    }
}
