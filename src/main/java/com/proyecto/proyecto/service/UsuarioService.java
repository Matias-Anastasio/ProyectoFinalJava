package com.proyecto.proyecto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import com.proyecto.proyecto.DTO.UsuarioDTO;
import com.proyecto.proyecto.DTO.UsuarioNuevoDTO;
import com.proyecto.proyecto.model.Usuario;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    @Lazy
    private TurnoService turnoService;

    private void verificarEmail(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException("el correo ingresado ya se encuentra registrado");
        }

    }

    private void verificarUsername(String username) {
        if (usuarioRepository.existsByUsername(username)) {
            throw new RuntimeException("el username ingresado ya se encuentra registrado");
        }
    }

    private void verificarTelefono(String telefono) {
        if (usuarioRepository.existsByTelefono(telefono)) {
            throw new RuntimeException("el telefono ingresado ya se encuentra registrado");
        }
    }

    public List<UsuarioDTO> buscarUsuarios() {
        return usuarioRepository.findAll().stream().map(usuario -> usuario.toDto()).collect(Collectors.toList());
    }

    public Usuario buscarUsuario(long id) {
        return usuarioRepository.findById(id).orElseThrow(()->new RuntimeException("Usuario no encontrado"));
    }
    
    public UsuarioDTO buscarUsuarioDTO(long id){
        return buscarUsuario(id).toDto();
    }

    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }


    public UsuarioDTO agregarUsuario(UsuarioNuevoDTO usuarioNuevoDTO) {

        verificarEmail(usuarioNuevoDTO.getEmail());

        verificarUsername(usuarioNuevoDTO.getUsername());

        verificarTelefono(usuarioNuevoDTO.getTelefono());

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(usuarioNuevoDTO.getNombre());
        nuevoUsuario.setEmail(usuarioNuevoDTO.getEmail());
        nuevoUsuario.setApellido(usuarioNuevoDTO.getNombre());
        nuevoUsuario.setTelefono(usuarioNuevoDTO.getTelefono());
        nuevoUsuario.setUsername(usuarioNuevoDTO.getUsername());
        nuevoUsuario.setTurnos(new ArrayList<>());

        return usuarioRepository.save(nuevoUsuario).toDto();

    }

    public UsuarioDTO actualizarUsuario(Long id, Map<String, Object> updates) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        updates.forEach((campo, valor) -> {
            switch (campo) {
                case "nombre":
                    usuario.setNombre((String) valor);
                    break;
                case "apellido":
                    usuario.setApellido((String) valor);
                    break;
                case "email":
                    verificarEmail((String) valor);
                    usuario.setEmail((String) valor);
                    break;
                case "telefono":
                    verificarTelefono((String) valor);
                    usuario.setTelefono((String) valor);
                    break;
                case "username":
                    verificarUsername((String) valor);
                    usuario.setUsername((String) valor);
                    break;
                default:
                    throw new RuntimeException("Campo no válido: " + campo);
            }
        });

        
        return usuarioRepository.save(usuario).toDto();
    }

    @Transactional
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.getTurnos().forEach(turno -> {
            turno.setUsuario(null);
            turnoService.cancelarTurno(turno.getId());
        });

        usuarioRepository.deleteAllById(id);
    }
}
