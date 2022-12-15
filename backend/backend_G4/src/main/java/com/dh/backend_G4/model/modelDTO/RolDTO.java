package com.dh.backend_G4.model.modelDTO;

import com.dh.backend_G4.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class RolDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nombre;
    private Set<Usuario> usuarios;
}
