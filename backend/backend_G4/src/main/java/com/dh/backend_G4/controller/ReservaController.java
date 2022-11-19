package com.dh.backend_G4.controller;

import com.dh.backend_G4.exceptions.ResourceNotFoundException;
import com.dh.backend_G4.model.modelDTO.ImagenDTO;
import com.dh.backend_G4.model.modelDTO.ProductoDTO;
import com.dh.backend_G4.model.modelDTO.ReservaDTO;
import com.dh.backend_G4.service.interfaceService.IReservaService;
import org.apache.log4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/reservas")
public class ReservaController {

    public static Logger logger = Logger.getLogger(ProductoController.class);
    private final IReservaService reservaService;

    public ReservaController(IReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<Set<ReservaDTO>> getReservas() throws ResourceNotFoundException {
        logger.info("Listando Reservas");
        Set<ReservaDTO> reservas = reservaService.listar();
        if(!reservas.isEmpty()){
            return ResponseEntity.ok(reservas);
        }else{
            throw new ResourceNotFoundException("No hay Reservas para listar");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Reserva por id " + id);
        ResponseEntity<ReservaDTO> response = null;
        if(id !=0){
            ReservaDTO reservaDTOById = reservaService.buscar(id);
            if(reservaDTOById != null){
                response = ResponseEntity.ok(reservaDTOById);
            }else{
                throw new ResourceNotFoundException("Reserva no encontrada");
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> createReserva(@RequestBody ReservaDTO reservaDTO) throws ResourceNotFoundException{
        logger.info("Agregando Reserva");
        return new ResponseEntity<>(reservaService.guardar(reservaDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ReservaDTO> updateReserva(@RequestBody ReservaDTO reservaDTO) throws ResourceNotFoundException{
        ResponseEntity<ReservaDTO> response = null;
        if(reservaDTO.getId() != null && reservaService.buscar(reservaDTO.getId()) != null){
            logger.info("Actualizando Reserva con id "+reservaDTO.getId());
            response = ResponseEntity.ok(reservaService.actualizar(reservaDTO));
        }else{
            throw new ResourceNotFoundException("Reserva no encontrada para ser actualizada");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReserva(@PathVariable("id") Long id) throws ResourceNotFoundException{
        ResponseEntity<HttpStatus> response;
        if(id != 0 && reservaService.buscar(id) != null){
            logger.info("Eliminando Reserva con id "+id);
            reservaService.eliminar(id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            throw new ResourceNotFoundException("Reserva no encontrada para ser eliminado");
        }
        return response;
    }

    @GetMapping("/reservasByProductoId/{id}")
    public ResponseEntity<Set<ReservaDTO>> getReservasByProductoId(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Reservas por ProductoId " + id);
        ResponseEntity<Set<ReservaDTO>> response = null;
        if(id !=0){
            Set<ReservaDTO> reservasDTOByProductoId = reservaService.buscarReservabyProducto(id);
            if(!reservasDTOByProductoId.isEmpty()){
                response = ResponseEntity.ok(reservasDTOByProductoId);
            }else{
                throw new ResourceNotFoundException("Reservas no encontradas para producto id = "+id);
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

}
