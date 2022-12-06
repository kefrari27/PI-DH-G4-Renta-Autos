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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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
        //Se eliminan las imágenes relacionadas con el producto
        eliminarImagenesByProducto(id);
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
        /*Optional<Producto> producto = productoRepository.findById(addCaracteristicaDTO.getProductoId());
        if(producto.isPresent()) {
            producto.get().getCaracteristicas().addAll(addCaracteristicaDTO.getCaracteristicas());
            productoRepository.saveAndFlush(producto.get());
            return mapper.convertValue(producto.get(), ProductoDTO.class);
        }
        throw new ResourceNotFoundException("No existe el producto");*/

        ProductoDTO producto = buscar(addCaracteristicaDTO.getProductoId());
        producto.setCaracteristicas(addCaracteristicaDTO.getCaracteristicas());
        productoRepository.saveAndFlush(mapper.convertValue(producto, Producto.class));

        return producto;
    }

    @Override
    public Set<ProductoDTO> listarProductosByCategoria(Long id) {
        List<Producto> productos = productoRepository.getProductoByCategoria(id);
        Set<ProductoDTO> productoDTOS = new HashSet<>();

        for(Producto producto: productos){
            productoDTOS.add(mapper.convertValue(producto, ProductoDTO.class));
        }
        return productoDTOS;
    }

    @Override
    public Set<ProductoDTO> listarProductosByCiudad(Long id) {
        List<Producto> productos = productoRepository.getProductoByCiudad(id);
        Set<ProductoDTO> productoDTOS = new HashSet<>();

        for(Producto producto: productos){
            productoDTOS.add(mapper.convertValue(producto, ProductoDTO.class));
        }
        return productoDTOS;
    }

    @Override
    public Set<ProductoDTO> listarProductosAleatorios(int cantidad) {
        Set<ProductoDTO> productos = listar();
        List<ProductoDTO> productosList = new ArrayList<>(productos);
        Set<ProductoDTO> productosAleatorios = new HashSet<>();
        Set<Integer> posiciones = new HashSet<>();
        //Si existen más productos en total de los solicitados
        if(productos.size()>cantidad){

            //Se generan las posiciones aleatorias
            for (int i = 0; i < cantidad; i++) {
                boolean generado = false;
                while (!generado){
                    Integer min = 0;
                    Integer max = productos.size();
                    Random random = new Random();
                    Integer pos = random.nextInt(max) + min;
                    if(!posiciones.contains(pos)){
                        posiciones.add(pos);
                        generado = true;
                    }
                }
            }
            //A partir de las posiciones se almacenan los productos en productosAleatorios
            for (int i = 0; i < posiciones.size(); i++) {
                productosAleatorios.add(productosList.get(i));
            }
        }else{
            for (int i = 0; i < productosList.size(); i++) {
                productosAleatorios.add(productosList.get(i));
            }
        }
        return productosAleatorios;
    }

    @Override
    public ProductoDTO buscarProductoByTituloAndCategoriaAndCiudad(String titulo, Long categoria_id, Long ciudad_id) {
        Producto producto = productoRepository.getProductoByTituloAndCategoriaAndCiudad(titulo, categoria_id, ciudad_id);
        ProductoDTO productoDTO = mapper.convertValue(producto, ProductoDTO.class);
        return productoDTO;
    }



}
