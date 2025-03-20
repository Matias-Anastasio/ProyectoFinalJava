package com.proyecto.proyecto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.proyecto.proyecto.DTO.UsuarioDTO;
import com.proyecto.proyecto.DTO.UsuarioNuevoDTO;
import com.proyecto.proyecto.service.UsuarioService;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "API para la gestión de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Obtener todos los usuarios", description = "Retorna una lista con todos los usuarios registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida con éxito"),
        @ApiResponse(responseCode = "500", description = "Error interno en el servidor")
    })
    @GetMapping
    public ResponseEntity<?> getUsuarios() {
        return ResponseEntity.ok().body(usuarioService.buscarUsuarios());
    }

    @Operation(summary = "Obtener un usuario por ID", description = "Retorna los detalles de un usuario específico dado su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "400", description = "Error al encontrar el usuario")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(
        @Parameter(description = "ID del usuario a obtener") @PathVariable long id) {
        try {
            return ResponseEntity.ok(usuarioService.buscarUsuarioDTO(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Crear un nuevo usuario", description = "Registra un nuevo usuario en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario creado con éxito"),
        @ApiResponse(responseCode = "400", description = "Error al crear el usuario")
    })
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevoUsuario(
        @Parameter(description = "Datos del nuevo usuario a crear") @RequestBody UsuarioNuevoDTO usuarioNuevoDTO) {
        try {
            return ResponseEntity.ok(usuarioService.agregarUsuario(usuarioNuevoDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado con éxito"),
        @ApiResponse(responseCode = "400", description = "Error al actualizar el usuario"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PatchMapping("/{id}/modificar")
    public ResponseEntity<?> actualizarUsuario(
        @Parameter(description = "ID del usuario a actualizar") @PathVariable Long id,
        @Parameter(description = "Datos a actualizar en el usuario") @RequestBody Map<String, Object> updates) {
        try {
            UsuarioDTO usuarioActualizado = usuarioService.actualizarUsuario(id, updates);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario del sistema dado su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
        @ApiResponse(responseCode = "400", description = "Error al eliminar el usuario"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @Transactional
    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<?> eliminarUsuario(
        @Parameter(description = "ID del usuario a eliminar") @PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
