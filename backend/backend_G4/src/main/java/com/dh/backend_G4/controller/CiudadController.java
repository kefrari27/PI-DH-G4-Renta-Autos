package com.dh.backend_G4.controller;

import com.dh.backend_G4.exceptions.ResourceNotFoundException;
import com.dh.backend_G4.model.modelDTO.CiudadDTO;
import com.dh.backend_G4.service.interfaceService.ICiudadService;
import org.apache.log4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/ciudades")
public class CiudadController {

    public static Logger logger = Logger.getLogger(CiudadController.class);
    private final ICiudadService ciudadService;

    public CiudadController(ICiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @GetMapping
    public ResponseEntity<Set<CiudadDTO>> getCiudades() throws ResourceNotFoundException {
        logger.info("Listando Ciudades");
        Set<CiudadDTO> ciudades = ciudadService.listar();
        if(!ciudades.isEmpty()){
            return ResponseEntity.ok(ciudades);
        }else{
            throw new ResourceNotFoundException("No hay Ciudades para listar");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiudadDTO> getCiudadById(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Ciudad por id " + id);
        ResponseEntity<CiudadDTO> response = null;
        if(id !=0){
            CiudadDTO ciudadDTOById = ciudadService.buscar(id);
            if(ciudadDTOById != null){
                response = ResponseEntity.ok(ciudadDTOById);
            }else{
                throw new ResourceNotFoundException("Ciudad no encontrada");
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<CiudadDTO> createCiudad(@RequestBody CiudadDTO ciudadDTO) throws ResourceNotFoundException{
        logger.info("Agregando Ciudad");
        /*if(caracteristicaService.guardar(caracteristicaDTO) ==null) {
            throw new ResourceNotFoundException("La Caracteristica no pudo ser almacenada");
        }*/
        return new ResponseEntity<>(ciudadService.guardar(ciudadDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CiudadDTO> updateCiudad(@RequestBody CiudadDTO ciudadDTO) throws ResourceNotFoundException{
        ResponseEntity<CiudadDTO> response = null;
        if(ciudadDTO.getId() != null && ciudadService.buscar(ciudadDTO.getId()) != null){
            logger.info("Actualizando Ciudad con id "+ciudadDTO.getId());
            response = ResponseEntity.ok(ciudadService.actualizar(ciudadDTO));
        }else{
            throw new ResourceNotFoundException("Ciudad no encontrada para ser actualizada");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCiudad(@PathVariable Long id) throws ResourceNotFoundException{
        ResponseEntity<HttpStatus> response;
        if(id != 0 && ciudadService.buscar(id) != null){
            logger.info("Eliminando Ciudad con id "+id);
            ciudadService.eliminar(id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            throw new ResourceNotFoundException("Ciudad no encontrada para ser eliminada");
        }
        return response;
    }

}
