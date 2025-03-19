package com.proyecto.proyecto.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.proyecto.proyecto.DTO.TurnoDTO;
import com.proyecto.proyecto.DTO.TurnoDeProfesionalDTO;
import com.proyecto.proyecto.DTO.TurnoDeUsuarioDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "turno")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private LocalTime hora;
    private EstadoTurno estado;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profesional_id", nullable = false)
    private Profesional profesional;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tratamiento_id", nullable = false)
    private Tratamiento tratamiento;

    @OneToOne(mappedBy = "turno", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Pago pago;


    public TurnoDeUsuarioDTO toTurnoDeUsuarioDto(){
        TurnoDeUsuarioDTO tDto = new TurnoDeUsuarioDTO();
        tDto.setFecha(this.fecha);
        tDto.setHora(this.hora);
        tDto.setEstado(this.estado);
        tDto.setProfesional(this.profesional.getApellido() + " " + this.profesional.getNombre());
        tDto.setTratamiento(this.tratamiento.getNombre());
        return tDto;
    }

    public TurnoDeProfesionalDTO toTurnoDeProfesionalDto(){
        TurnoDeProfesionalDTO tDto = new TurnoDeProfesionalDTO();
        tDto.setFecha(this.fecha);
        tDto.setHora(this.hora);
        tDto.setEstado(this.estado);
        tDto.setTratamiento(this.tratamiento.getNombre());
        if(usuario!=null){
        tDto.setUsuario(this.usuario.getApellido() + " " + this.usuario.getNombre());
        }else{
            tDto.setUsuario("Usuario no disponible");
        }
        return tDto;
    }

    public TurnoDTO toDto(){
        TurnoDTO tDto = new TurnoDTO();
        tDto.setFecha(this.fecha);
        tDto.setHora(this.hora);
        tDto.setEstado(this.estado);
        if(usuario!=null){
            tDto.setUsuario(this.usuario.getApellido() + " " + this.usuario.getNombre());
            }else{
                tDto.setUsuario("Usuario no disponible");
            }
        tDto.setProfesional(this.profesional.getApellido() + " " + this.profesional.getNombre());
        tDto.setTratamiento(this.tratamiento.getNombre());
        return tDto;
    }
}

