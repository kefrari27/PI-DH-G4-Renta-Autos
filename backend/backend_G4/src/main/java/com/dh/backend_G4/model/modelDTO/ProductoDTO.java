package com.dh.backend_G4.model.modelDTO;

import com.dh.backend_G4.model.Caracteristica;
import com.dh.backend_G4.model.Categoria;
import com.dh.backend_G4.model.Ciudad;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class ProductoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String titulo;
    private Categoria categoria;
    private Ciudad ciudad;
    private Set<String> imagenes;
    private String descripcion;
    private Set<Caracteristica> caracteristicas;
    private String disponibilidad;
    private String politica;
}
