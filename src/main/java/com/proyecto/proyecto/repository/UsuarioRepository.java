package com.proyecto.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyecto.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByTelefono(String telefono);

    void deleteAllById(Long id);

}
