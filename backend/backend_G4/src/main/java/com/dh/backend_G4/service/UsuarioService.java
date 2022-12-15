package com.dh.backend_G4.service;

import com.dh.backend_G4.model.Rol;
import com.dh.backend_G4.model.Usuario;
import com.dh.backend_G4.model.modelDTO.RolDTO;
import com.dh.backend_G4.model.modelDTO.UsuarioDTO;
import com.dh.backend_G4.repository.IRolRepository;
import com.dh.backend_G4.repository.IUsuarioRepository;
import com.dh.backend_G4.service.interfaceService.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final IRolRepository rolRepository;
    private final ObjectMapper mapper;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(IUsuarioRepository usuarioRepository, IRolRepository rolRepository, ObjectMapper mapper, @Lazy PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioDTO guardar(UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioRegistrado = buscarUsuarioByCorreo(usuarioDTO.getCorreo());
        if(usuarioRegistrado == null){
            Rol rol = rolRepository.getRolByNombre("user");
            Rol rolDTO = usuarioDTO.getRol();
            rolDTO.setId(rol.getId());
            usuarioDTO.setRol(rolDTO);
            Usuario usuario =  mapper.convertValue(usuarioDTO, Usuario.class);
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioRepository.save(usuario);
            return usuarioDTO;
        }else{
            return usuarioRegistrado;
        }
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
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return usuarioDTO;
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioDTO buscarUsuarioCreado(String correo, String nombre, String apellido) {
        Optional<Usuario> usuario = usuarioRepository.getUsuarioCreado(correo, nombre, apellido);
        UsuarioDTO usuarioDTO = null;
        if(usuario.isPresent()){
            usuarioDTO = mapper.convertValue(usuario, UsuarioDTO.class);
        }
        return usuarioDTO;
    }
}
