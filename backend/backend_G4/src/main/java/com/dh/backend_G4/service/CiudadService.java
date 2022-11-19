package com.dh.backend_G4.service;

import com.dh.backend_G4.model.Ciudad;
import com.dh.backend_G4.model.Producto;
import com.dh.backend_G4.model.modelDTO.CiudadDTO;
import com.dh.backend_G4.model.modelDTO.ProductoDTO;
import com.dh.backend_G4.repository.ICiudadRepository;
import com.dh.backend_G4.service.interfaceService.ICiudadService;
import com.dh.backend_G4.service.interfaceService.IProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CiudadService implements ICiudadService {

    private final ICiudadRepository ciudadRepository;
    private final IProductoService productoService;
    private final ObjectMapper mapper;

    public CiudadService(ICiudadRepository ciudadRepository, IProductoService productoService, ObjectMapper mapper) {
        this.ciudadRepository = ciudadRepository;
        this.productoService = productoService;
        this.mapper = mapper;
    }

    @Override
    public CiudadDTO guardar(CiudadDTO ciudadDTO) {
        Ciudad ciudad = mapper.convertValue(ciudadDTO, Ciudad.class);
        ciudadRepository.save(ciudad);
        return ciudadDTO;
    }

    @Override
    public Set<CiudadDTO> listar() {
        List<Ciudad> ciudades = ciudadRepository.findAll();
        Set<CiudadDTO> ciudadesDTOS = new HashSet<>();

        for (Ciudad ciudad:ciudades) {
            ciudadesDTOS.add(mapper.convertValue(ciudad, CiudadDTO.class));
        }
        return ciudadesDTOS;
    }

    @Override
    public CiudadDTO buscar(Long id) {
        Optional<Ciudad> ciudad = ciudadRepository.findById(id);
        CiudadDTO ciudadDTO = null;
        if(ciudad.isPresent()){
            ciudadDTO = mapper.convertValue(ciudad, CiudadDTO.class);
        }
        return ciudadDTO;
    }

    @Override
    public CiudadDTO actualizar(CiudadDTO ciudadDTO) {
        Ciudad ciudad = mapper.convertValue(ciudadDTO, Ciudad.class);
        ciudadRepository.save(ciudad);
        return ciudadDTO;
    }

    @Override
    public void eliminar(Long id) {
        ciudadRepository.deleteById(id);
    }

    @Override
    public Set<CiudadDTO> listarCiudadesConProductos() {
        Set<ProductoDTO> productosDTO = productoService.listar();
        Set<Producto> productos = new HashSet<>();
        Set<Long> ciudadesId = new HashSet<>();
        Set<CiudadDTO> ciudadesConProductos = new HashSet<>();
        //Se convierte de ProductoDTO a Producto
        for (ProductoDTO productoDTO:productosDTO) {
            productos.add(mapper.convertValue(productoDTO, Producto.class));
        }
        //Se genera un Set de id de ciudades a partir de los productos
        for (Producto producto:productos) {
            Long idCiudad = producto.getCiudad().getId();
            if(!ciudadesId.contains(idCiudad)){
                ciudadesId.add(idCiudad);
            }
        }
        //A paritr de los id encontrados anteriormente se buscan las ciudades y se genera el nuevo Set de ciudades que poseen productos
        for (Long ciudadId:ciudadesId) {
            ciudadesConProductos.add(buscar(ciudadId));
        }

        return ciudadesConProductos;
    }

}
