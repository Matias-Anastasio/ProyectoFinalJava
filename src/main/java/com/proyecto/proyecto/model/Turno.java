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
    @JoinColumn(name = "profesional_id")
    private Profesional profesional;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tratamiento_id")
    private Tratamiento tratamiento;
    @OneToOne(mappedBy = "turno", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Pago pago;


    public TurnoDeUsuarioDTO toTurnoDeUsuarioDto(){
        TurnoDeUsuarioDTO tDto = new TurnoDeUsuarioDTO();
        tDto.setFecha(this.fecha);
        tDto.setHora(this.hora);
        tDto.setEstado(this.estado);
        tDto.setProfesional(profesional!=null?profesional.getNombre() + " " + profesional.getApellido() : "Profesional no disponible");
        tDto.setTratamiento(this.tratamiento.getNombre());
        return tDto;
    }

    public TurnoDeProfesionalDTO toTurnoDeProfesionalDto(){
        TurnoDeProfesionalDTO tDto = new TurnoDeProfesionalDTO();
        tDto.setFecha(this.fecha);
        tDto.setHora(this.hora);
        tDto.setEstado(this.estado);
        tDto.setTratamiento(this.tratamiento.getNombre());
        tDto.setUsuario(usuario!=null?this.usuario.getApellido() + " " + this.usuario.getNombre():"Usuario no disponible");
        return tDto;
    }

    public TurnoDTO toDto(){
        TurnoDTO tDto = new TurnoDTO();
        tDto.setFecha(this.fecha);
        tDto.setHora(this.hora);
        tDto.setEstado(this.estado);
        tDto.setUsuario(usuario!=null?this.usuario.getApellido() + " " + this.usuario.getNombre():"Usuario no disponible");
        tDto.setProfesional(profesional!=null?profesional.getNombre() + " " + profesional.getApellido() : "Profesional no disponible");
        tDto.setTratamiento(this.tratamiento.getNombre());
        return tDto;
    }
}

