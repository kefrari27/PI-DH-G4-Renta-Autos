package com.dh.backend_G4.controller;

import com.dh.backend_G4.exceptions.ResourceNotFoundException;
import com.dh.backend_G4.model.modelDTO.PoliticaDTO;
import com.dh.backend_G4.service.interfaceService.IPoliticaService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/politicas")
public class PoliticaController {

    public static Logger logger = Logger.getLogger(PoliticaController.class);
    private final IPoliticaService politicaService;


    public PoliticaController(IPoliticaService politicaService) {
        this.politicaService = politicaService;
    }

    @GetMapping
    public ResponseEntity<Set<PoliticaDTO>> getPoliticas() throws ResourceNotFoundException {
        logger.info("Listando Politicas");
        Set<PoliticaDTO> politicas = politicaService.listar();
        if(!politicas.isEmpty()){
            return ResponseEntity.ok(politicas);
        }else{
            throw new ResourceNotFoundException("No hay Politicas para listar");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoliticaDTO> getPoliticaById(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Politica por id " + id);
        ResponseEntity<PoliticaDTO> response = null;
        if(id !=0){
            PoliticaDTO politicaDTObyId = politicaService.buscar(id);
            if(politicaDTObyId != null){
                response = ResponseEntity.ok(politicaDTObyId);
            }else{
                throw new ResourceNotFoundException("Politica no encontrada");
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<PoliticaDTO> createPolitica(@RequestBody PoliticaDTO politicaDTO) throws ResourceNotFoundException{
        logger.info("Agregando Politica");
        return new ResponseEntity<>(politicaService.guardar(politicaDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PoliticaDTO> updatePolitica(@RequestBody PoliticaDTO politicaDTO) throws ResourceNotFoundException{
        ResponseEntity<PoliticaDTO> response = null;
        if(politicaDTO.getId() != null && politicaService.buscar(politicaDTO.getId()) != null){
            logger.info("Actualizando Politica con id "+politicaDTO.getId());
            response = ResponseEntity.ok(politicaService.actualizar(politicaDTO));
        }else{
            throw new ResourceNotFoundException("Politica no encontrada para ser actualizada");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePolitica(@PathVariable Long id) throws ResourceNotFoundException{
        ResponseEntity<HttpStatus> response;
        if(id != 0 && politicaService.buscar(id) != null){
            logger.info("Eliminando Politica con id "+id);
            politicaService.eliminar(id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            throw new ResourceNotFoundException("Politica no encontrada para ser eliminada");
        }
        return response;
    }
}
