package com.dh.backend_G4.service;

import com.dh.backend_G4.exceptions.ResourceNotFoundException;
import com.dh.backend_G4.model.Imagen;
import com.dh.backend_G4.model.Producto;
import com.dh.backend_G4.model.modelDTO.AddCaracteristicaDTO;
import com.dh.backend_G4.model.modelDTO.ImagenDTO;
import com.dh.backend_G4.model.modelDTO.ProductoDTO;
import com.dh.backend_G4.repository.IImagenRepository;
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
    private final IImagenRepository imagenRepository;

    private final ObjectMapper mapper;

    public ProductoService(IProductoRepository productoRepository, IImagenRepository imagenRepository, ObjectMapper mapper) {
        this.productoRepository = productoRepository;
        this.imagenRepository = imagenRepository;
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

    @Override
    public Set<ImagenDTO> listarImagenesByProducto(Long id) {
        List<Imagen> imagenes = imagenRepository.getImagenesByProductoId(id);
        Set<ImagenDTO> imagenesDTOS = new HashSet<>();
        for (Imagen imagen:imagenes) {
            imagenesDTOS.add(mapper.convertValue(imagen, ImagenDTO.class));
        }
        return imagenesDTOS;
    }

    @Override
    public void eliminarImagenesByProducto(Long id) {
        List<Imagen> imagenes = imagenRepository.getImagenesByProductoId(id);
        if(!imagenes.isEmpty()){
            for (Imagen imagen:imagenes) {
                imagenRepository.deleteById(imagen.getId());
            }
        }
    }

    @Override
    public ProductoDTO addCaracteristica(AddCaracteristicaDTO addCaracteristicaDTO) throws ResourceNotFoundException {
        Optional<Producto> producto = productoRepository.findById(addCaracteristicaDTO.getProductoId());
        if(producto.isPresent()) {
            producto.get().getCaracteristicas().addAll(addCaracteristicaDTO.getCaracteristicas());
            productoRepository.saveAndFlush(producto.get());
            return mapper.convertValue(producto.get(), ProductoDTO.class);
        }
        throw new ResourceNotFoundException("No existe el producto");

    }

}
