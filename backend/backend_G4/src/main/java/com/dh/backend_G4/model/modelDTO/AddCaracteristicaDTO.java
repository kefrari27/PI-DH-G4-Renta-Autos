package com.dh.backend_G4.model.modelDTO;

import com.dh.backend_G4.model.Caracteristica;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AddCaracteristicaDTO {
    private static final long serialVersionUID = 1L;
    private Long productoId;
    private Set<Caracteristica> caracteristicas;
}
