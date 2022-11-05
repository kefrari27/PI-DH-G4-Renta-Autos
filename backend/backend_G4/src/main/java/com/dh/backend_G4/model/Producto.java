package com.dh.backend_G4.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "productos")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "ciudad_id", referencedColumnName = "id", nullable = false)
    private Ciudad ciudad;
    @OneToMany(mappedBy = "producto")
    private Set<Imagen> imagenes;
    private String descripcion;
    @ManyToMany
    @JoinTable(name = "productos_has_caracteristicas",
            joinColumns = {@JoinColumn(name = "producto_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "caracteristica_id", nullable = false)})
    private Set<Caracteristica> caracteristicas;
    private String disponibilidad;
    private String politica;
}
