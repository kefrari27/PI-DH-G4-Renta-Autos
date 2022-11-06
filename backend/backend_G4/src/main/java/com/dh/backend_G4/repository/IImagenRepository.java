package com.dh.backend_G4.repository;

import com.dh.backend_G4.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImagenRepository extends JpaRepository<Imagen, Long> {
    @Query("from Imagen i where i.producto.id =:id_producto")
    List<Imagen> getImagenesByProductoId(@Param("id_producto") Long id_producto);

}
