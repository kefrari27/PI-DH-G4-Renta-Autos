package com.dh.backend_G4.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="imagenes")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descripcion;
    private String urlImagen;
    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = "foreing key (producto_id) references productos (producto_id)"))
    private Producto producto;
}
