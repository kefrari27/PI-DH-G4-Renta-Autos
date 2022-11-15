package com.dh.backend_G4.model.modelDTO;

import com.dh.backend_G4.model.Ciudad;
import com.dh.backend_G4.model.Rol;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private Rol rol;
    private Ciudad ciudad;
}
