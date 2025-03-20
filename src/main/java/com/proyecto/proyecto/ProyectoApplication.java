package com.proyecto.proyecto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proyecto.proyecto.model.EstadoPago;
import com.proyecto.proyecto.model.EstadoTurno;
import com.proyecto.proyecto.model.Pago;
import com.proyecto.proyecto.model.Profesional;
import com.proyecto.proyecto.model.Tratamiento;
import com.proyecto.proyecto.model.Turno;
import com.proyecto.proyecto.model.Usuario;
import com.proyecto.proyecto.service.PagoService;
import com.proyecto.proyecto.service.ProfesionalService;
import com.proyecto.proyecto.service.TratamientoService;
import com.proyecto.proyecto.service.TurnoService;
import com.proyecto.proyecto.service.UsuarioService;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProfesionalService profesionalService;

	@Autowired
	private TratamientoService tratamientoService;

	@Autowired
	private TurnoService turnoService;

	@Autowired
	private PagoService pagoService;


	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario usuario1 = new Usuario();
		usuario1.setNombre("Juan");
		usuario1.setApellido("Pérez");
		usuario1.setEmail("juan.perez@gmail.com");
		usuario1.setTelefono("123456789");
		usuario1.setUsername("JuanPerez");

		Usuario usuario2 = new Usuario();
		usuario2.setNombre("María");
		usuario2.setApellido("López");
		usuario2.setEmail("maria.lopez@yahoo.com");
		usuario2.setTelefono("987654321");
		usuario2.setUsername("MariaLopez");

		Tratamiento tratamiento1 = new Tratamiento();
		tratamiento1.setNombre("Limpieza Facial");
		tratamiento1.setCodigo("C01-LF");
		tratamiento1.setDescripcion("Eliminación de impurezas y células muertas");
		tratamiento1.setDuracion(60);
		tratamiento1.setPrecio(50.0);

		Tratamiento tratamiento2 = new Tratamiento();
		tratamiento2.setNombre("Masaje Relajante");
		tratamiento2.setCodigo("C02-MR");
		tratamiento2.setDescripcion("Terapia de relajación muscular profunda");
		tratamiento2.setDuracion(90);
		tratamiento2.setPrecio(80.0);

		Profesional profesional1 = new Profesional();
		profesional1.setNombre("Ana");
		profesional1.setApellido("Lopez");
		profesional1.setEspecialidad("Dermatología");
		profesional1.setLegajo("L1-234");

		Profesional profesional2 = new Profesional();
		profesional2.setNombre("Carlos");
		profesional2.setApellido("Diaz");
		profesional2.setEspecialidad("Fisioterapia");
		profesional2.setLegajo("L1-235");

		Turno turno1 = new Turno();
		turno1.setUsuario(usuario1);
		turno1.setTratamiento(tratamiento1);
		turno1.setProfesional(profesional1);
		turno1.setFecha(LocalDate.of(2025, 3, 10));
		turno1.setHora(LocalTime.of(15, 0, 0));
		turno1.setEstado(EstadoTurno.CONFIRMADO);

		Turno turno2 = new Turno();
		turno2.setUsuario(usuario2);
		turno2.setTratamiento(tratamiento2);
		turno2.setProfesional(profesional2);
		turno2.setFecha(LocalDate.of(2025, 3, 15));
		turno2.setHora(LocalTime.of(15, 0, 0));
		turno2.setEstado(EstadoTurno.PENDIENTE);

		Pago pago1 = new Pago();
		pago1.setTurno(turno1);
		pago1.setMonto(turno1.getTratamiento().getPrecio());
		pago1.setMetodoDePago("Tarjeta de crédito");
		pago1.setFechaDePago(LocalDate.of(2025, 2, 25));
		pago1.setEstado(EstadoPago.REALIZADO);

		Pago pago2 = new Pago();
		pago2.setTurno(turno2);
		pago2.setMonto(turno2.getTratamiento().getPrecio());
		pago2.setMetodoDePago("Transferencia bancaria");
		pago2.setFechaDePago(LocalDate.of(2025, 2, 25));
		pago2.setEstado(EstadoPago.PENDIENTE);
		
		profesional1.setTratamientos(List.of(tratamiento1));
		profesional2.setTratamientos(List.of(tratamiento2));
		
		usuarioService.guardarUsuario(usuario1);
		usuarioService.guardarUsuario(usuario2);
		tratamientoService.guardarTratamiento(tratamiento1);
		tratamientoService.guardarTratamiento(tratamiento2);
		profesionalService.guardarProfesional(profesional1);
		profesionalService.guardarProfesional(profesional2);
		turnoService.guardarTurno(turno1);
		turnoService.guardarTurno(turno2);
		pagoService.guardarPago(pago1);
		pagoService.guardarPago(pago2);
		

	}


}
