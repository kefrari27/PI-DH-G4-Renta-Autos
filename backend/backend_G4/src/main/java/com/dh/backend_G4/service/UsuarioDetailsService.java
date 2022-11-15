package com.dh.backend_G4.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class UsuarioDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Aqui se emulan los datos, podemos traer los usuarios de la base de datos
        Map<String, String> usuarios = Map.of(
            "mgrisales", "USER",
            "jgrisales", "ADMIN"
        );
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
