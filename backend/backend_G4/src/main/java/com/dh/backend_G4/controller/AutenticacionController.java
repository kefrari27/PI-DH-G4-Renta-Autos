package com.dh.backend_G4.controller;

import com.dh.backend_G4.model.modelDTO.AuthenticationReq;
import com.dh.backend_G4.model.modelDTO.TokenInfo;
import com.dh.backend_G4.service.JwtUtilService;
import com.dh.backend_G4.service.UsuarioDetailsService;
import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/publico")
public class AutenticacionController {

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
    UserDetailsService usuarioDetailsService;

   @Autowired
   private JwtUtilService jwtUtilService;

   final static Logger logger = Logger.getLogger(AutenticacionController.class);

    @PostMapping
    public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationReq authenticationReq){
        logger.info("Autenticando al usuario {}");
        logger.info(authenticationReq.getUsuario());

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationReq.getUsuario(),
                    authenticationReq.getClave()));

            final UserDetails userDetails = usuarioDetailsService
                    .loadUserByUsername(authenticationReq.getUsuario());

            final String jwt = jwtUtilService.generateToken(userDetails);

            TokenInfo tokenInfo = new TokenInfo(jwt);
            return ResponseEntity.ok(tokenInfo);
    }
}
