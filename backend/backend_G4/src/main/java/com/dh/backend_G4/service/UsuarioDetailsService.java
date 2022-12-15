package com.dh.backend_G4.service;

import com.dh.backend_G4.model.Usuario;
import com.dh.backend_G4.model.modelDTO.UsuarioDTO;
import com.dh.backend_G4.service.interfaceService.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final IUsuarioService usuarioService;
    private final ObjectMapper mapper;

    public UsuarioDetailsService(IUsuarioService usuarioService, ObjectMapper mapper) {
        this.usuarioService = usuarioService;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       //En nuestro caso el username es el correo del usuario, se busca el usuario por el correo
        UsuarioDTO usuariosDTO = usuarioService.buscarUsuarioByCorreo(username);
        Usuario usuario = mapper.convertValue(usuariosDTO, Usuario.class);
        //Se obtiene el rol
        var rol = usuario.getRol();
        if(rol != null){
            //Se crea User con datos de nuestra entidad Usuario
            User.UserBuilder userBuilder = User.withUsername(username);
            userBuilder.password(usuario.getPassword()).roles(rol.getNombre());
            return userBuilder.build();
        }else{
            throw new UsernameNotFoundException(username);
        }
    }
}
