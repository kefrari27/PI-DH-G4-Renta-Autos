package com.dh.backend_G4.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "ciudades")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String provincia;
    private String pais;
    private String latitud;
    private String longitud;
    @OneToMany(mappedBy = "ciudad", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Producto> productos;
    @OneToMany(mappedBy = "ciudad", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Usuario> usuarios;
}
