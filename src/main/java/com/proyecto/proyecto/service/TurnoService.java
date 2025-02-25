package com.proyecto.proyecto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.DTO.TurnoDTO;
import com.proyecto.proyecto.model.Turno;
import com.proyecto.proyecto.repository.TurnoRepository;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    public List<TurnoDTO> buscarTurnos(){
        return turnoRepository.findAll().stream().map(turno->turno.toDto()).collect(Collectors.toList());
    }

    public void guardarTurno(Turno turno){
        turnoRepository.save(turno);
    }
}
