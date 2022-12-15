package com.dh.backend_G4.model.modelDTO;

import com.dh.backend_G4.model.Caracteristica;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class ProductoCompletoDTO  implements Serializable {
    private static final long serialVersionUID = 1L;
    private ProductoDTO productoDTO;
    private Set<CaracteristicaDTO> caracteristicasDTO;
    private Set<ImagenDTO> imagenesDTO;
    private Set<PoliticaDTO> politicasDTO;
}
