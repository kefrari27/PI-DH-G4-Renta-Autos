package com.dh.backend_G4.model;

import lombok.*;

import javax.persistence.*;

@Entity
//@Table (name = "categorias")
@Table
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
    //private String imagen;
}
