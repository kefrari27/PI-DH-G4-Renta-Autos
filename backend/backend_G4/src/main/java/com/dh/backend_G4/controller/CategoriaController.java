package com.dh.backend_G4.controller;

import com.dh.backend_G4.exceptions.ResourceNotFoundException;
import com.dh.backend_G4.model.modelDTO.CategoriaDTO;
import com.dh.backend_G4.service.interfaceService.ICategoriaService;
import org.apache.log4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/categorias")
public class CategoriaController {

    final static Logger logger = Logger.getLogger(CategoriaController.class);
    private final ICategoriaService categoriaService;

    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<Set<CategoriaDTO>> getCategorias() throws ResourceNotFoundException {
        logger.info("Listando Categorias");
        Set<CategoriaDTO> categorias = categoriaService.listar();
        if(!categorias.isEmpty()){
            return ResponseEntity.ok(categorias);
        }else{
            throw new ResourceNotFoundException("No hay Categorias para listar");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Categoria por id " + id);
        ResponseEntity<CategoriaDTO> response = null;
        if(id !=0){
            CategoriaDTO categoriaDTOById = categoriaService.buscar(id);
            if(categoriaDTOById != null){
                response = ResponseEntity.ok(categoriaDTOById);
            }else{
                throw new ResourceNotFoundException("Categoría no encontrada");
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> createCategoria(@RequestBody CategoriaDTO categoriaDTO) throws ResourceNotFoundException{
        logger.info("Agregando Categoria");
        /*if(categoriaService.guardar(categoriaDTO) ==null) {
            throw new ResourceNotFoundException("La Categoría no pudo ser almacenada");
        }*/
        return new ResponseEntity<>(categoriaService.guardar(categoriaDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoriaDTO> updateCategoria(@RequestBody CategoriaDTO categoriaDTO) throws ResourceNotFoundException{
        ResponseEntity<CategoriaDTO> response = null;
        if(categoriaDTO.getId() != null && categoriaService.buscar(categoriaDTO.getId()) != null){
            logger.info("Actualizando Categoria con id "+categoriaDTO.getId());
            response = ResponseEntity.ok(categoriaService.actualizar(categoriaDTO));
        }else{
            throw new ResourceNotFoundException("Categoría no encontrada para ser actualizada");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategoria(@PathVariable Long id) throws ResourceNotFoundException{
        ResponseEntity<HttpStatus> response;
        if(id != 0 && categoriaService.buscar(id) != null){
            logger.info("Eliminando Categoría con id "+id);
            categoriaService.eliminar(id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            throw new ResourceNotFoundException("Categoría no encontrada para ser eliminada");
        }
        return response;
    }
}
