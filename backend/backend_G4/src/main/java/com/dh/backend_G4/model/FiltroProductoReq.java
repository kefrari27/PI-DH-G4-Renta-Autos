package com.dh.backend_G4.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class FiltroProductoReq implements Serializable {
    private static final long serialVersionUID = 1L;
    private Ciudad ciudad;
    private String fechaCheckIn;
    private String fechaCheckOut;
}
