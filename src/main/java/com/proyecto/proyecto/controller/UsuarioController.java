package com.proyecto.proyecto.controller;

import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.proyecto.DTO.UsuarioDTO;
import com.proyecto.proyecto.DTO.UsuarioNuevoDTO;
import com.proyecto.proyecto.service.UsuarioService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> getUsuarios() {
        return ResponseEntity.ok().body(usuarioService.buscarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable long id){
        try{
            return ResponseEntity.ok(usuarioService.buscarUsuario(id));
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevoUsuario(@RequestBody UsuarioNuevoDTO usuarioNuevoDTO) {
        try {
            usuarioService.agregarUsuario(usuarioNuevoDTO);
            return ResponseEntity.ok().body("Usuario creado");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/modificar/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        try {
            UsuarioDTO usuarioActualizado = usuarioService.actualizarUsuario(id, updates);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
