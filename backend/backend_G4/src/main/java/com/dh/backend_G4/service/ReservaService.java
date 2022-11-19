package com.dh.backend_G4.service;

import com.dh.backend_G4.model.Imagen;
import com.dh.backend_G4.model.Producto;
import com.dh.backend_G4.model.Reserva;
import com.dh.backend_G4.model.modelDTO.ImagenDTO;
import com.dh.backend_G4.model.modelDTO.ProductoDTO;
import com.dh.backend_G4.model.modelDTO.ReservaDTO;
import com.dh.backend_G4.repository.IReservaRepository;
import com.dh.backend_G4.service.interfaceService.IReservaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservaService implements IReservaService {

    private final IReservaRepository reservaRepository;
    private final ObjectMapper mapper;

    public ReservaService(IReservaRepository reservaRepository, ObjectMapper mapper) {
        this.reservaRepository = reservaRepository;
        this.mapper = mapper;
    }


    @Override
    public ReservaDTO guardar(ReservaDTO reservaDTO) {
        Reserva reserva = mapper.convertValue(reservaDTO, Reserva.class);
        reservaRepository.save(reserva);
        return reservaDTO;
    }

    @Override
    public Set<ReservaDTO> listar() {
        List<Reserva> reservas = reservaRepository.findAll();
        Set<ReservaDTO> reservaDTOS = new HashSet<>();

        for(Reserva reserva: reservas){
            reservaDTOS.add(mapper.convertValue(reserva, ReservaDTO.class));
        }
        return reservaDTOS;
    }

    @Override
    public ReservaDTO buscar(Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        ReservaDTO reservaDTO = null;
        if(reserva.isPresent()){
            reservaDTO = mapper.convertValue(reserva, ReservaDTO.class);
        }
        return reservaDTO;
    }

    @Override
    public ReservaDTO actualizar(ReservaDTO reservaDTO) {
        Reserva reserva = mapper.convertValue(reservaDTO, Reserva.class);
        reservaRepository.save(reserva);
        return reservaDTO;
    }

    @Override
    public void eliminar(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public Set<ReservaDTO> buscarReservabyProducto(Long id) {
        List<Reserva> reservas = reservaRepository.getReservasByProductoId(id);
        Set<ReservaDTO> reservaDTOS = new HashSet<>();
        for (Reserva reserva:reservas) {
            reservaDTOS.add(mapper.convertValue(reserva, ReservaDTO.class));
        }
        return reservaDTOS;
    }
}
