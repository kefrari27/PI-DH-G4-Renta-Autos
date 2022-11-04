package com.dh.backend_G4.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "categorias")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descripcion;
    private String urlImagen;
    @OneToMany(mappedBy = "categoria", orphanRemoval = true)
    private Set<Producto> productos;
}
