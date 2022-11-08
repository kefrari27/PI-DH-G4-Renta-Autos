package com.dh.backend_G4.model.modelDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiudadDTO {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nombre;
    private String provincia;
    private String pais;
    private String latitud;
    private String longitud;
}
