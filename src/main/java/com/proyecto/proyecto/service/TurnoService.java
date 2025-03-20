package com.proyecto.proyecto.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.DTO.TurnoDTO;
import com.proyecto.proyecto.DTO.TurnoNuevoDTO;
import com.proyecto.proyecto.model.EstadoPago;
import com.proyecto.proyecto.model.EstadoTurno;
import com.proyecto.proyecto.model.Pago;
import com.proyecto.proyecto.model.Profesional;
import com.proyecto.proyecto.model.Tratamiento;
import com.proyecto.proyecto.model.Turno;
import com.proyecto.proyecto.model.Usuario;
import com.proyecto.proyecto.repository.TurnoRepository;

import jakarta.transaction.Transactional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProfesionalService profesionalService;

    @Autowired
    private TratamientoService tratamientoService;

    @Autowired
    PagoService pagoService;

    public List<TurnoDTO> buscarTurnos() {
        return turnoRepository.findAll().stream().map(turno -> turno.toDto()).collect(Collectors.toList());
    }

    public void guardarTurno(Turno turno) {
        turnoRepository.save(turno);
    }

    @Transactional
    public void agendarTurno(TurnoNuevoDTO turnoNuevoDTO) {

        // obtengo el usuario, el profesional y el tratamiento
        Usuario usuario = usuarioService.buscarUsuario(turnoNuevoDTO.getUsuarioId());

        Profesional profesional = profesionalService.buscarProfesional(turnoNuevoDTO.getProfesionalId());

        Tratamiento tratamiento = tratamientoService.buscarTratamiento(turnoNuevoDTO.getTratamientoId());
                

        Optional<Turno> turnoExistente = turnoRepository.findByFechaAndHoraAndProfesionalAndEstadoNot(
                turnoNuevoDTO.getFecha(), turnoNuevoDTO.getHora(), profesional, EstadoTurno.CANCELADO);

        // valido que no exista otro turno con el mismo profesional en el horario
        // seleccionado
        if (turnoExistente.isPresent()) {
            throw new RuntimeException("Ya existe un turno en este horario para este profesional.");
        }

        // valido que el profesional realice dicho tratamiento
        if (!profesional.getTratamientos().contains(tratamiento)) {
            throw new RuntimeException("El profesional seleccionado no realiza dicho tratamiento.");
        }

        // valido que el nuevo turno no se solape con otros
        LocalTime horaFinal = turnoNuevoDTO.getHora().plusMinutes(tratamiento.getDuracion());

        List<Turno> turnosExistentes = turnoRepository.findByFechaAndProfesionalAndEstadoNot(
                turnoNuevoDTO.getFecha(), profesional, EstadoTurno.CANCELADO);

        for (Turno tExistente : turnosExistentes) {

            boolean seSolapan = (
            // El nuevo turno comienza antes de que termine el turno existente
            (turnoNuevoDTO.getHora()
                    .isBefore(tExistente.getHora().plusMinutes(tExistente.getTratamiento().getDuracion())))
                    &&
                    (horaFinal.isAfter(tExistente.getHora())));

            if (seSolapan) {
                throw new RuntimeException("El horario solicitado se solapa con otro turno.");
            }
        }

        Turno nuevoTurno = new Turno();
        nuevoTurno.setFecha(turnoNuevoDTO.getFecha());
        nuevoTurno.setHora(turnoNuevoDTO.getHora());
        nuevoTurno.setUsuario(usuario);
        nuevoTurno.setProfesional(profesional);
        nuevoTurno.setEstado(EstadoTurno.PENDIENTE);
        Pago pago = new Pago();
        pago.setEstado(EstadoPago.PENDIENTE);
        pago.setMonto(tratamiento.getPrecio());
        pago.setTurno(nuevoTurno);
        nuevoTurno.setPago(pago);
        nuevoTurno.setTratamiento(tratamiento);

        turnoRepository.save(nuevoTurno);
    }

    public void cancelarTurno(Long idTurno) {
        Turno turno = turnoRepository.findById(idTurno)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));

        turno.setEstado(EstadoTurno.CANCELADO);
        pagoService.cancelarPago(turno.getPago().getId());

        turnoRepository.save(turno);
    }

}
