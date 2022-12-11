package com.dh.backend_G4.model.modelDTO;

import com.dh.backend_G4.model.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
public class ProductoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String titulo;
    private Categoria categoria;
    private Ciudad ciudad;
    private String descripcion;
    private String disponibilidad;
    //private String politica;
    private String direccion;
    private Set<Caracteristica> caracteristicas;
    private Set<Imagen> imagenes;
    private Set<Politica> politicas;
}
