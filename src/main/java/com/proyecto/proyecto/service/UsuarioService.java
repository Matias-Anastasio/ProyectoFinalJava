package com.proyecto.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.repository.UsuarioRepository;
import com.proyecto.proyecto.model.Usuario;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> BuscarUsuarios(){
        return usuarioRepository.findAll();
    }

    public void guardarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}
