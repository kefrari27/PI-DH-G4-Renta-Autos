package com.dh.backend_G4.repository;

import com.dh.backend_G4.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("from Reserva r where r.producto.id =:id_producto")
    List<Reserva> getReservasByProductoId(@Param("id_producto") Long id_producto);

    @Query("from Reserva r where r.fechaCheckIn BETWEEN :startDate AND :endDate")
    List<Reserva> getReservasByRango(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);

    @Query("from Reserva r where r.usuario.id =:id_usuario")
    List<Reserva> getReservasByUsuarioId(@Param("id_usuario") Long id_usuario);

    @Query("from Reserva r where r.producto.id =:id_producto AND r.fechaCheckIn =:fechaCheckin AND r.fechaCheckOut =:fechaCheckout ")
    Reserva getReservaEspecifica(@Param("id_producto") Long id_producto, @Param("fechaCheckin") LocalDate fechaCheckin, @Param("fechaCheckout") LocalDate fechaCheckout);

}
