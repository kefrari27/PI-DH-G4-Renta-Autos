package com.dh.backend_G4.repository;

import com.dh.backend_G4.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("from Categoria c where c.titulo =:titulo")
    Categoria getCategoriaByTitulo(@Param("titulo") String titulo);
}
