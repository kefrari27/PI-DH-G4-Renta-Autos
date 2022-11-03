package com.dh.backend_G4.repository;

import com.dh.backend_G4.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    @Query("from Producto p where p.titulo =:titulo")
    Producto getProductoByTitulo(@Param("titulo") String titulo);
}
