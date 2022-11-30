package com.dh.backend_G4.repository;

import com.dh.backend_G4.model.Producto;
import com.dh.backend_G4.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    @Query("from Producto p where p.titulo =:titulo")
    Producto getProductoByTitulo(@Param("titulo") String titulo);

    @Query("from Producto p where p.categoria.id =:categoria_id")
    List<Producto> getProductoByCategoria(@Param("categoria_id") Long categoria_id);

    @Query("from Producto p where p.ciudad.id =:ciudad_id")
    List<Producto> getProductoByCiudad(@Param("ciudad_id") Long ciudad_id);

    @Query("from Producto p where p.titulo =:titulo AND p.categoria.id =:categoria_id AND p.ciudad.id =:ciudad_id")
    Producto getProductoByTituloAndCategoriaAndCiudad(@Param("titulo") String titulo, @Param("categoria_id") Long categoria_id, @Param("ciudad_id") Long ciudad_id);
}
