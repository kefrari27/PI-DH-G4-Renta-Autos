package com.dh.backend_G4.repository;

import com.dh.backend_G4.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("from Usuario u where u.correo =:correo")
    Optional<Usuario> getUsuarioByCorreo(@Param("correo") String correo);
}
