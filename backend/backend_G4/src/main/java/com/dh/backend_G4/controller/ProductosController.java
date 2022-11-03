package com.dh.backend_G4.controller;

import com.dh.backend_G4.exceptions.GlobalExceptionHandler;
import com.dh.backend_G4.exceptions.ResourceNotFoundException;
import com.dh.backend_G4.model.modelDTO.CategoriaDTO;
import com.dh.backend_G4.model.modelDTO.ProductoDTO;
import com.dh.backend_G4.service.interfaceService.IProductoService;
import org.apache.log4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/productos")
public class ProductosController {
    final static Logger logger = Logger.getLogger(ProductosController.class);
    private final IProductoService productoService;

    public ProductosController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<Set<ProductoDTO>> getProductos() throws ResourceNotFoundException {
        logger.info("Listando Productos");
        Set<ProductoDTO> productos = productoService.listar();
        if(!productos.isEmpty()){
            return ResponseEntity.ok(productos);
        }else{
            throw new ResourceNotFoundException("No hay productos para listar");
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
            throw new ResourceNotFoundException("El producto no pudo ser almacenado");
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
    public ResponseEntity<HttpStatus> deleteProducto(@PathVariable Long id) throws ResourceNotFoundException{
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

}
