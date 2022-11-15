package com.dh.backend_G4.controller;

import com.dh.backend_G4.exceptions.ResourceNotFoundException;
import com.dh.backend_G4.model.modelDTO.RolDTO;
import com.dh.backend_G4.service.interfaceService.IRolService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/roles")
public class RolController {
    final static Logger logger = Logger.getLogger(RolController.class);
    private final IRolService rolService;

    public RolController(IRolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<Set<RolDTO>> getRoles() throws ResourceNotFoundException {
        logger.info("Listando Roles");
        Set<RolDTO> roles = rolService.listar();
        if(!roles.isEmpty()){
            return ResponseEntity.ok(roles);
        }else{
            throw new ResourceNotFoundException("No hay Roles para listar");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> getRolById(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Rol por id " + id);
        ResponseEntity<RolDTO> response = null;
        if(id !=0){
            RolDTO rolDTOById = rolService.buscar(id);
            if(rolDTOById != null){
                response = ResponseEntity.ok(rolDTOById);
            }else{
                throw new ResourceNotFoundException("Rol no encontrado");
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<RolDTO> createRol(@RequestBody RolDTO rolDTO) throws ResourceNotFoundException{
        logger.info("Agregando Rol");
        return new ResponseEntity<>(rolService.guardar(rolDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RolDTO> updateRol(@RequestBody RolDTO rolDTO) throws ResourceNotFoundException{
        ResponseEntity<RolDTO> response = null;
        if(rolDTO.getId() != null && rolService.buscar(rolDTO.getId()) != null){
            logger.info("Actualizando Rol con id "+rolDTO.getId());
            response = ResponseEntity.ok(rolService.actualizar(rolDTO));
        }else{
            throw new ResourceNotFoundException("Rol no encontrado para ser actualizado");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRol(@PathVariable Long id) throws ResourceNotFoundException{
        ResponseEntity<HttpStatus> response;
        if(id != 0 && rolService.buscar(id) != null){
            logger.info("Eliminando Rol con id "+id);
            rolService.eliminar(id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            throw new ResourceNotFoundException("Rol no encontrado para ser eliminado");
        }
        return response;
    }
}
