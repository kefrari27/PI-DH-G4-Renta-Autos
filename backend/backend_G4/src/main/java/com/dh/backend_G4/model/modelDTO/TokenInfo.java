package com.dh.backend_G4.model.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String jwtToken;
    private Long usuarioId;
}
