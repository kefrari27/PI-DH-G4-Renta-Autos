package com.dh.backend_G4.service.interfaceService;

import com.dh.backend_G4.model.modelDTO.ReservaDTO;

import java.time.LocalDate;
import java.util.Set;

public interface IReservaService {
    public ReservaDTO guardar(ReservaDTO reservaDTO);
    public Set<ReservaDTO> listar();
    public ReservaDTO buscar(Long id);
    public ReservaDTO actualizar(ReservaDTO reservaDTO);
    public void eliminar(Long id);
    public Set<ReservaDTO> buscarReservabyProducto(Long id);
    public Boolean comprobarDisponibilidadFechaNuevaReserva(ReservaDTO reservaDTO, LocalDate fechaCheckIn, LocalDate fechaCheckOut);
}
