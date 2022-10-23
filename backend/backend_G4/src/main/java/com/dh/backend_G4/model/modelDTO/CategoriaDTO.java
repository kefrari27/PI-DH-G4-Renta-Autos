package com.dh.backend_G4.model.modelDTO;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String titulo;
    private String descripcion;
    private String urlImagen;
}
