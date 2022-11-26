package com.dh.backend_G4.model.modelDTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FechasReservas {
    private LocalDate fechaCheckIn;
    private LocalDate fechaCheckOut;
}
