package com.proyecto.proyecto.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.repository.UsuarioRepository;
import com.proyecto.proyecto.DTO.UsuarioDTO;
import com.proyecto.proyecto.model.Usuario;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> BuscarUsuarios(){
        return usuarioRepository.findAll().stream().map(usuario->usuario.toDto()).collect(Collectors.toList());
    }

    public void guardarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarUsuarioPorId(long id){
        return usuarioRepository.findById(id);
    }
}
