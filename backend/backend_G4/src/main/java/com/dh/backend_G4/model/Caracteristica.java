package com.dh.backend_G4.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "caracteristicas")
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
    @ManyToMany(mappedBy = "caracteristicas", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Producto> productos;

}
