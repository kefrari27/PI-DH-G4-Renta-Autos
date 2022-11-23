package com.dh.backend_G4.controller;

import com.dh.backend_G4.exceptions.ResourceNotFoundException;
import com.dh.backend_G4.model.Rol;
import com.dh.backend_G4.model.modelDTO.RolDTO;
import com.dh.backend_G4.model.modelDTO.UsuarioDTO;
import com.dh.backend_G4.service.interfaceService.IRolService;
import com.dh.backend_G4.service.interfaceService.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    final static Logger logger = Logger.getLogger(UsuarioController.class);
    private final IUsuarioService usuarioService;
    private final IRolService rolService;

    private final ObjectMapper mapper;

    public UsuarioController(IUsuarioService usuarioService, IRolService rolService, ObjectMapper mapper) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<Set<UsuarioDTO>> getUsuarios() throws ResourceNotFoundException {
        logger.info("Listando Usuarios");
        Set<UsuarioDTO> usuarios = usuarioService.listar();
        if(!usuarios.isEmpty()){
            return ResponseEntity.ok(usuarios);
        }else{
            throw new ResourceNotFoundException("No hay Usuarios para listar");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Usuario por id " + id);
        ResponseEntity<UsuarioDTO> response = null;
        if(id !=0){
            UsuarioDTO usuarioDTOById = usuarioService.buscar(id);
            if(usuarioDTOById != null){
                response = ResponseEntity.ok(usuarioDTOById);
            }else{
                throw new ResourceNotFoundException("Usuario no encontrado");
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) throws ResourceNotFoundException{
        logger.info("Agregando Usuario");
        UsuarioDTO usuario = usuarioService.guardar(usuarioDTO);
        if(usuario.getId() == null){
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        }else{
            throw new ResourceNotFoundException("El Usuario ya se encuentra registrado");
        }
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> updateUsuario(@RequestBody UsuarioDTO usuarioDTO) throws ResourceNotFoundException{
        ResponseEntity<UsuarioDTO> response = null;
        if(usuarioDTO.getId() != null && usuarioService.buscar(usuarioDTO.getId()) != null){
            logger.info("Actualizando Usuario con id "+usuarioDTO.getId());
            response = ResponseEntity.ok(usuarioService.actualizar(usuarioDTO));
        }else{
            throw new ResourceNotFoundException("Usuario no encontrado para ser actualizado");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable Long id) throws ResourceNotFoundException{
        ResponseEntity<HttpStatus> response;
        if(id != 0 && usuarioService.buscar(id) != null){
            logger.info("Eliminando Usuario con id "+id);
            usuarioService.eliminar(id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            throw new ResourceNotFoundException("Usuario no encontrado para ser eliminado");
        }
        return response;
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<UsuarioDTO> getUsuarioByCorreo(@PathVariable("correo") String correo) throws ResourceNotFoundException{
        logger.info("Buscando Usuario con correo " + correo);
        ResponseEntity<UsuarioDTO> response = null;
        UsuarioDTO usuarioDTOByCorreo = usuarioService.buscarUsuarioByCorreo(correo);
        logger.info("usuarioDTOByCorreo = "+usuarioDTOByCorreo);
        if(usuarioDTOByCorreo != null){
            response = ResponseEntity.ok(usuarioDTOByCorreo);
        }else{
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
        return response;
    }
}
