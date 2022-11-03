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
    //@JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
    @JoinColumn(name = "categoria_id", nullable = false, unique = true, foreignKey = @ForeignKey(foreignKeyDefinition = "foreing key (categoria_id) references categorias (categoria_id)"))
    private Categoria categoria;
    @ManyToOne
    //@JoinColumn(name = "ciudad_id", referencedColumnName = "id", nullable = false)
    @JoinColumn(name = "ciudad_id", nullable = false, unique = true, foreignKey = @ForeignKey(foreignKeyDefinition = "foreing key (ciudad_id) references ciudades (ciudad_id)"))
    private Ciudad ciudad;
    @OneToMany(mappedBy = "producto", orphanRemoval = true)
    private Set<String> imagenes;
    private String descripcion;
    @ManyToMany
    @JoinTable(name = "productos_has_caracteristicas",
            joinColumns = {@JoinColumn(name = "producto_id", nullable = false, unique = true, foreignKey = @ForeignKey(foreignKeyDefinition = "foreing key (producto_id) references productos (producto_id)"))},
            inverseJoinColumns = {@JoinColumn(name = "caracteristica_id", nullable = false, unique = true, foreignKey = @ForeignKey(foreignKeyDefinition = "foreing key (caracteristica_id) references caracteristicas (caracteristica_id)"))})
    private Set<Caracteristica> caracteristicas;
    private String disponibilidad;
    private String politica;
}
