package com.dh.backend_G4.model.modelDTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthenticationReq implements Serializable {
    private static final long serialVersionUID = 1L;
    private String usuario;
    private String clave;
}
