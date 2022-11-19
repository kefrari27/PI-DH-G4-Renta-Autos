package com.dh.backend_G4.model.modelDTO;

import com.dh.backend_G4.model.Producto;
import com.dh.backend_G4.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class ReservaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private LocalTime horaCheckIn;
    private LocalDateTime fechaCheckIn;
    private LocalDateTime fechaCheckOut;
    private Producto producto;
    private Usuario usuario;
}
