package com.dh.backend_G4.controller;

import com.dh.backend_G4.exceptions.ResourceNotFoundException;
import com.dh.backend_G4.model.modelDTO.UsuarioDTO;
import com.dh.backend_G4.service.interfaceService.IMailService;
import com.dh.backend_G4.service.interfaceService.IRolService;
import com.dh.backend_G4.service.interfaceService.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Set;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    final static Logger logger = Logger.getLogger(UsuarioController.class);
    private final IUsuarioService usuarioService;
    private final IRolService rolService;
    private final IMailService mailService;
    private final ObjectMapper mapper;

    public UsuarioController(IUsuarioService usuarioService, IRolService rolService, IMailService mailService, ObjectMapper mapper) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.mailService = mailService;
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
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) throws ResourceNotFoundException, MessagingException, InterruptedException {
        logger.info("Agregando Usuario");
        UsuarioDTO usuario = usuarioService.guardar(usuarioDTO);
        if(usuario.getId() == null){
            sendMailUsuario(usuarioDTO);
            Thread.sleep(2000);
            UsuarioDTO usuarioCreado = usuarioService.buscarUsuarioCreado(usuarioDTO.getCorreo(), usuarioDTO.getNombre(), usuarioDTO.getApellido());
            return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
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
    @Async
    public void sendMailUsuario(UsuarioDTO usuarioDTO) throws MessagingException, MailSendException {
        String name =usuarioDTO.getNombre();
        String to = usuarioDTO.getCorreo();
        String subject = "Registro DigitalBooking4";
        String content = "Nos alegra que hagas parte de la familia DigitalBooking4, estamos ansiosos de compartir contigo maravillosas experiencias en tu próximo viaje.";
        mailService.sendEmailBienvenida(name,to,subject,content);
    }
}
