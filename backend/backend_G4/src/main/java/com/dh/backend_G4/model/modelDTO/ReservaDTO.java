package com.dh.backend_G4.model.modelDTO;

import com.dh.backend_G4.model.Producto;
import com.dh.backend_G4.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString
public class ReservaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private LocalTime horaCheckIn;
    private LocalDate fechaCheckIn;
    private LocalDate fechaCheckOut;
    private Producto producto;
    private Usuario usuario;
}
