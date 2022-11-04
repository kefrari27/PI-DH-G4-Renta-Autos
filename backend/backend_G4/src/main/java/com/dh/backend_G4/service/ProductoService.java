package com.dh.backend_G4.service;

import com.dh.backend_G4.model.Producto;
import com.dh.backend_G4.model.modelDTO.ProductoDTO;
import com.dh.backend_G4.repository.IProductoRepository;
import com.dh.backend_G4.service.interfaceService.IProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductoService implements IProductoService {
    private final IProductoRepository productoRepository;
    private final ObjectMapper mapper;

    public ProductoService(IProductoRepository productoRepository, ObjectMapper mapper) {
        this.productoRepository = productoRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductoDTO guardar(ProductoDTO productoDTO) {
        Producto producto = mapper.convertValue(productoDTO, Producto.class);
        ProductoDTO response = null;
        if(productoRepository.getProductoByTitulo(productoDTO.getTitulo()) == null){
           productoRepository.save(producto);
           Producto productoAlmacenado = productoRepository.getProductoByTitulo(productoDTO.getTitulo());
            response = mapper.convertValue(productoAlmacenado, ProductoDTO.class);
        }
        return response;

    }

    @Override
    public Set<ProductoDTO> listar() {
        List<Producto> productos = productoRepository.findAll();
        Set<ProductoDTO> productoDTOS = new HashSet<>();

        for(Producto producto: productos){
            productoDTOS.add(mapper.convertValue(producto, ProductoDTO.class));
        }
        return productoDTOS;
    }

    @Override
    public ProductoDTO buscar(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        ProductoDTO productoDTO = null;
        if(producto.isPresent()){
            productoDTO = mapper.convertValue(producto, ProductoDTO.class);
        }
        return productoDTO;
    }

    @Override
    public ProductoDTO actualizar(ProductoDTO productoDTO) {
        Producto producto = mapper.convertValue(productoDTO, Producto.class);
        productoRepository.save(producto);
        return productoDTO;
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
