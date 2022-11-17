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

   /* private final IUsuarioService usuarioService;
    private final ObjectMapper mapper;

    public UsuarioDetailsService(IUsuarioService usuarioService, ObjectMapper mapper) {
        this.usuarioService = usuarioService;
        this.mapper = mapper;
    }*/


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Aqui se emulan los datos, podemos traer los usuarios de la base de datos
        Map<String, String> usuarios = Map.of(
            "mgrisales", "admin",
            "jgrisales", "user"
        );
        //UsuarioDTO usuariosDTO = usuarioService.buscarUsuarioByCorreo(username);
        //Usuario usuarios = mapper.convertValue(usuariosDTO, Usuario.class);

        var rol = usuarios.get(username);
        if(rol != null){
            User.UserBuilder userBuilder = User.withUsername(username);
            // "secreto" => $2a$10$Hp6B8gZKaOGE7I75jf/03ujSkJE3yPQZ8Vs/j8n7701FnlZFYTt0O
            String encryptedPassword = "$2a$10$Hp6B8gZKaOGE7I75jf/03ujSkJE3yPQZ8Vs/j8n7701FnlZFYTt0O";
            userBuilder.password(encryptedPassword).roles(rol);
            return userBuilder.build();
        }else{
            throw new UsernameNotFoundException(username);
        }
    }
}
