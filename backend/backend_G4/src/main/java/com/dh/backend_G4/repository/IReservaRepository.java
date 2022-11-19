package com.dh.backend_G4.repository;

import com.dh.backend_G4.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("from Reserva r where r.producto.id =:id_producto")
    List<Reserva> getReservasByProductoId(@Param("id_producto") Long id_producto);
}
