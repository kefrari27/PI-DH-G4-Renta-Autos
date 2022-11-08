package com.dh.backend_G4.model.modelDTO;

import com.dh.backend_G4.model.Producto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImagenDTO {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String titulo;
    private String descripcion;
    private String urlImagen;
    private Producto producto;
}
