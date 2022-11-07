package com.dh.backend_G4.controller;


import com.dh.backend_G4.exceptions.ResourceNotFoundException;
import com.dh.backend_G4.model.modelDTO.AddCaracteristicaDTO;
import com.dh.backend_G4.model.modelDTO.ImagenDTO;
import com.dh.backend_G4.model.modelDTO.ProductoDTO;
import com.dh.backend_G4.service.interfaceService.IProductoService;
import org.apache.log4j.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("api/v1/productos")
public class ProductoController {
    public static Logger logger = Logger.getLogger(ProductoController.class);
    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<Set<ProductoDTO>> getProductos() throws ResourceNotFoundException {
        logger.info("Listando Productos");
        Set<ProductoDTO> productos = productoService.listar();
        if(!productos.isEmpty()){
            return ResponseEntity.ok(productos);
        }else{
            throw new ResourceNotFoundException("No hay Productos para listar");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Producto por id " + id);
        ResponseEntity<ProductoDTO> response = null;
        if(id !=0){
            ProductoDTO productoDTOById = productoService.buscar(id);
            if(productoDTOById != null){
                response = ResponseEntity.ok(productoDTOById);
            }else{
                throw new ResourceNotFoundException("Producto no encontrado");
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO) throws ResourceNotFoundException{
        logger.info("Agregando Producto");
        /*if(categoriaService.guardar(categoriaDTO) ==null) {
            throw new ResourceNotFoundException("La Categoría no pudo ser almacenada");
        }*/
        return new ResponseEntity<>(productoService.guardar(productoDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductoDTO> updateProducto(@RequestBody ProductoDTO productoDTO) throws ResourceNotFoundException{
        ResponseEntity<ProductoDTO> response = null;
        if(productoDTO.getId() != null && productoService.buscar(productoDTO.getId()) != null){
            logger.info("Actualizando Producto con id "+productoDTO.getId());
            response = ResponseEntity.ok(productoService.actualizar(productoDTO));
        }else{
            throw new ResourceNotFoundException("Producto no encontrado para ser actualizado");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProducto(@PathVariable("id") Long id) throws ResourceNotFoundException{
        ResponseEntity<HttpStatus> response;
        if(id != 0 && productoService.buscar(id) != null){
            logger.info("Eliminando Producto con id "+id);
            productoService.eliminar(id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            throw new ResourceNotFoundException("Producto no encontrado para ser eliminado");
        }
        return response;
    }

    @GetMapping("/imagenesByProductoId/{id}")
    public ResponseEntity<Set<ImagenDTO>> getImagenesByProductoId(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Imagenes por ProductoId " + id);
        ResponseEntity<Set<ImagenDTO>> response = null;
        if(id !=0){
            Set<ImagenDTO> imagenDTOByProductoId = productoService.listarImagenesByProducto(id);
            if(!imagenDTOByProductoId.isEmpty()){
                response = ResponseEntity.ok(imagenDTOByProductoId);
            }else{
                throw new ResourceNotFoundException("Imagen no encontrada para producto id = "+id);
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

    @DeleteMapping("/imagenesByProductoId/{id}")
    public ResponseEntity<HttpStatus> deleteImagenesByProducto(@PathVariable("id") Long id) throws ResourceNotFoundException{
        ResponseEntity<HttpStatus> response;
        if(id != 0 && productoService.buscar(id) != null){
            logger.info("Eliminando Imagenes de Producto con id "+id);
            productoService.eliminarImagenesByProducto(id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            throw new ResourceNotFoundException("Producto no encontrado para ser eliminado");
        }
        return response;
    }

    @PostMapping("/addCaracteristica")
    public ResponseEntity<ProductoDTO> addCaracteristica(@RequestBody AddCaracteristicaDTO addCaracteristicaDTO) throws ResourceNotFoundException{
        logger.info("Agregando Caracteristica");
        return new ResponseEntity<>(productoService.addCaracteristica(addCaracteristicaDTO), HttpStatus.OK);
    }

    @GetMapping("/productosByCategoria/{id}")
    public ResponseEntity<Set<ProductoDTO>> getProductosByCategoriaId(@PathVariable("id") Long id) throws ResourceNotFoundException {
        logger.info("Listando Productos por Categoria");
        Set<ProductoDTO> productos = productoService.listarProductosByCategoria(id);
        if(!productos.isEmpty()){
            return ResponseEntity.ok(productos);
        }else{
            throw new ResourceNotFoundException("No hay Productos para listar para la Categoria seleccionada");
        }
    }

    @GetMapping("/productosByCiudad/{id}")
    public ResponseEntity<Set<ProductoDTO>> getProductosByCiudadId(@PathVariable("id") Long id) throws ResourceNotFoundException {
        logger.info("Listando Productos por Ciudad");
        Set<ProductoDTO> productos = productoService.listarProductosByCiudad(id);
        if(!productos.isEmpty()){
            return ResponseEntity.ok(productos);
        }else{
            throw new ResourceNotFoundException("No hay Productos para listar para la Ciudad seleccionada");
        }
    }

    @GetMapping("/productosAleatorios/{cantidad}")
    public ResponseEntity<Set<ProductoDTO>> getProductosAleatorios(@PathVariable("cantidad") Integer cantidad) throws ResourceNotFoundException{
        logger.info("Generando " + cantidad + " productos aleatorios");
        Set<ProductoDTO> productosAleatorios = productoService.listarProductosAleatorios(cantidad);
        if(!productosAleatorios.isEmpty()){
            return ResponseEntity.ok(productosAleatorios);
        }else{
            throw new ResourceNotFoundException("No hay Productos para mostrar aleatoriamente");
        }
    }

}
