package com.dh.backend_G4.controller;

import com.dh.backend_G4.exceptions.ResourceNotFoundException;
import com.dh.backend_G4.model.modelDTO.CaracteristicaDTO;
import com.dh.backend_G4.service.interfaceService.ICaracteristicaService;
import org.apache.log4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/caracteristicas")
public class CaracteristicaController {

    final static Logger logger = Logger.getLogger(CategoriaController.class);
    private final ICaracteristicaService caracteristicaService;

    public CaracteristicaController(ICaracteristicaService caracteristicaService) {
        this.caracteristicaService = caracteristicaService;
    }

    @GetMapping
    public ResponseEntity<Set<CaracteristicaDTO>> getCaracteristicas() throws ResourceNotFoundException {
        logger.info("Listando Caracteristicas");
        Set<CaracteristicaDTO> caracteristicas = caracteristicaService.listar();
        if(!caracteristicas.isEmpty()){
            return ResponseEntity.ok(caracteristicas);
        }else{
            throw new ResourceNotFoundException("No hay Caracteristicas para listar");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaracteristicaDTO> getCaracteristicaById(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Caracteristica por id " + id);
        ResponseEntity<CaracteristicaDTO> response = null;
        if(id !=0){
            CaracteristicaDTO caracteristicaDTOById = caracteristicaService.buscar(id);
            if(caracteristicaDTOById != null){
                response = ResponseEntity.ok(caracteristicaDTOById);
            }else{
                throw new ResourceNotFoundException("Caracteristica no encontrada");
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<CaracteristicaDTO> createCaracteristica(@RequestBody CaracteristicaDTO caracteristicaDTO) throws ResourceNotFoundException{
        logger.info("Agregando Caracteristica");
        /*if(caracteristicaService.guardar(caracteristicaDTO) ==null) {
            throw new ResourceNotFoundException("La Caracteristica no pudo ser almacenada");
        }*/
        return new ResponseEntity<>(caracteristicaService.guardar(caracteristicaDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CaracteristicaDTO> updateCaracteristica(@RequestBody CaracteristicaDTO caracteristicaDTO) throws ResourceNotFoundException{
        ResponseEntity<CaracteristicaDTO> response = null;
        if(caracteristicaDTO.getId() != null && caracteristicaService.buscar(caracteristicaDTO.getId()) != null){
            logger.info("Actualizando Caracteristica con id "+caracteristicaDTO.getId());
            response = ResponseEntity.ok(caracteristicaService.actualizar(caracteristicaDTO));
        }else{
            throw new ResourceNotFoundException("Caracteristica no encontrada para ser actualizada");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCaracteristica(@PathVariable Long id) throws ResourceNotFoundException{
        ResponseEntity<HttpStatus> response;
        if(id != 0 && caracteristicaService.buscar(id) != null){
            logger.info("Eliminando Caracteristica con id "+id);
            caracteristicaService.eliminar(id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            throw new ResourceNotFoundException("Caracteristica no encontrada para ser eliminada");
        }
        return response;
    }

}
