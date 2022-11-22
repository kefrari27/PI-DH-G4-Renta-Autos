package com.dh.backend_G4.repository;

import com.dh.backend_G4.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long> {
    @Query("from Rol r where r.nombre =:nombre")
    Rol getRolByNombre(@Param("nombre") String nombre);
}
