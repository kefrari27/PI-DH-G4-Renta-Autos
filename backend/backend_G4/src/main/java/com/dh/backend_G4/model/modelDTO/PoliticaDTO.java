package com.dh.backend_G4.model.modelDTO;

import com.dh.backend_G4.model.Producto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PoliticaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String normas;
    private String saludYSeguridad;
    private String cancelacion;
    private Producto producto;
}
