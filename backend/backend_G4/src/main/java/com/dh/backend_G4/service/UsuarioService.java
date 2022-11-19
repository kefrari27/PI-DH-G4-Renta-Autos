package com.dh.backend_G4.service;

import com.dh.backend_G4.model.Usuario;
import com.dh.backend_G4.model.modelDTO.UsuarioDTO;
import com.dh.backend_G4.repository.IUsuarioRepository;
import com.dh.backend_G4.service.interfaceService.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final ObjectMapper mapper;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(IUsuarioRepository usuarioRepository, ObjectMapper mapper, @Lazy PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioDTO guardar(UsuarioDTO usuarioDTO) {
        Usuario usuario =  mapper.convertValue(usuarioDTO, Usuario.class);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        //Usuario usuarioPasswordCodificado = codificarPassword(usuario);
        //usuarioRepository.save(usuarioPasswordCodificado);
        usuarioRepository.save(usuario);
        return usuarioDTO;
    }

    @Override
    public Set<UsuarioDTO> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        Set<UsuarioDTO> usuariosDTO = new HashSet<>();

        for (Usuario usuario:usuarios) {
            usuariosDTO.add(mapper.convertValue(usuario, UsuarioDTO.class));
        }
        return usuariosDTO;
    }

    @Override
    public UsuarioDTO buscar(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        UsuarioDTO usuarioDTO = null;
        if(usuario.isPresent()){
            usuarioDTO = mapper.convertValue(usuario, UsuarioDTO.class);
        }
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO buscarUsuarioByCorreo(String correo) {
        Optional<Usuario> usuario = usuarioRepository.getUsuarioByCorreo(correo);
        UsuarioDTO usuarioDTO = null;
        if(usuario.isPresent()){
            usuarioDTO = mapper.convertValue(usuario, UsuarioDTO.class);
        }
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO actualizar(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.convertValue(usuarioDTO, Usuario.class);
        usuarioRepository.save(usuario);
        return usuarioDTO;
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
    /*
    @Override
    public Usuario codificarPassword(Usuario usuario){
        usuario.setPassword(bcryptPasswordEncoder.encode(usuario.getPassword()));
        return usuario;
    }
    @Override
    public void decodificarPassword(String password, Usuario usuario){
        bcryptPasswordEncoder.matches(password, usuario.getPassword());
    }*/

}
