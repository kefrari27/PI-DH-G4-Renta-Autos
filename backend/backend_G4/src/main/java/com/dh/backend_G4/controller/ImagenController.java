package com.dh.backend_G4.controller;

import com.dh.backend_G4.exceptions.ResourceNotFoundException;
import com.dh.backend_G4.model.modelDTO.ImagenDTO;
import com.dh.backend_G4.service.interfaceService.IImagenService;
import org.apache.log4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/imagenes")
public class ImagenController {
    public static Logger logger = Logger.getLogger(ImagenController.class);
    private final IImagenService imagenService;

    public ImagenController(IImagenService imagenService) {
        this.imagenService = imagenService;
    }

    @GetMapping
    public ResponseEntity<Set<ImagenDTO>> getImagenes() throws ResourceNotFoundException {
        logger.info("Listando Imagenes");
        Set<ImagenDTO> imagenes = imagenService.listar();
        if(!imagenes.isEmpty()){
            return ResponseEntity.ok(imagenes);
        }else{
            throw new ResourceNotFoundException("No hay Imagenes para listar");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenDTO> getImagenById(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Imagen por id " + id);
        ResponseEntity<ImagenDTO> response = null;
        if(id !=0){
            ImagenDTO imagenDTOById = imagenService.buscar(id);
            if(imagenDTOById != null){
                response = ResponseEntity.ok(imagenDTOById);
            }else{
                throw new ResourceNotFoundException("Imagen no encontrada");
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<ImagenDTO> createImagen(@RequestBody ImagenDTO imagenDTO) throws ResourceNotFoundException{
        logger.info("Agregando Imagen");
        /*if(categoriaService.guardar(categoriaDTO) ==null) {
            throw new ResourceNotFoundException("La Categoría no pudo ser almacenada");
        }*/
        return new ResponseEntity<>(imagenService.guardar(imagenDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ImagenDTO> updateImagen(@RequestBody ImagenDTO imagenDTO) throws ResourceNotFoundException{
        ResponseEntity<ImagenDTO> response = null;
        if(imagenDTO.getId() != null && imagenService.buscar(imagenDTO.getId()) != null){
            logger.info("Actualizando Imagen con id "+imagenDTO.getId());
            response = ResponseEntity.ok(imagenService.actualizar(imagenDTO));
        }else{
            throw new ResourceNotFoundException("Imagen no encontrada para ser actualizada");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteImagen(@PathVariable Long id) throws ResourceNotFoundException{
        ResponseEntity<HttpStatus> response;
        if(id != 0 && imagenService.buscar(id) != null){
            logger.info("Eliminando Imagen con id "+id);
            imagenService.eliminar(id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            throw new ResourceNotFoundException("Imagen no encontrada para ser eliminada");
        }
        return response;
    }

    @GetMapping("/imagenesByProductoId/{id}")
    public ResponseEntity<Set<ImagenDTO>> getImagenesByProductoId(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Imagenes por ProductoId " + id);
        ResponseEntity<Set<ImagenDTO>> response = null;
        if(id !=0){
            Set<ImagenDTO> imagenDTOByProductoId = imagenService.listarImagenesByProducto(id);
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



}
