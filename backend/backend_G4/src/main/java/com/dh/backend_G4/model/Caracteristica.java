package com.dh.backend_G4.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "caracteristicas")
//@Table
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String icono;
    private String titulo;
    private String descripcion;
    @ManyToMany(mappedBy = "caracteristicas")
    private Set<Producto> productos;
}
