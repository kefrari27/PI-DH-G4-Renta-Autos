package com.dh.backend_G4.model.modelDTO;

import com.dh.backend_G4.model.Caracteristica;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


public class AddCaracteristicaDTO {
    private static final long serialVersionUID = 1L;
    private Long productoId;
    private Set<Caracteristica> caracteristicas;

    public AddCaracteristicaDTO() {
    }

    public Long getProductoId() {
        return productoId;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public void setCaracteristicas(Set<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }


}
