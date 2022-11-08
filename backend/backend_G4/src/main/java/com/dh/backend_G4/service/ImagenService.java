package com.dh.backend_G4.service;

import com.dh.backend_G4.model.Imagen;
import com.dh.backend_G4.model.modelDTO.ImagenDTO;
import com.dh.backend_G4.repository.IImagenRepository;
import com.dh.backend_G4.service.interfaceService.IImagenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ImagenService implements IImagenService {

    private final IImagenRepository imagenRepository;
    private final ObjectMapper mapper;

    public ImagenService(IImagenRepository imagenRepository, ObjectMapper mapper) {
        this.imagenRepository = imagenRepository;
        this.mapper = mapper;
    }

    @Override
    public ImagenDTO guardar(ImagenDTO imagenDTO) {
        Imagen imagen = mapper.convertValue(imagenDTO, Imagen.class);
        imagenRepository.save(imagen);
        return imagenDTO;
    }

    @Override
    public Set<ImagenDTO> listar() {
        List<Imagen> imagenes = imagenRepository.findAll();
        Set<ImagenDTO> imagenesDTOS = new HashSet<>();
        for (Imagen imagen:imagenes) {
            imagenesDTOS.add(mapper.convertValue(imagen, ImagenDTO.class));
        }
        return imagenesDTOS;
    }

    @Override
    public ImagenDTO buscar(Long id) {
        Optional<Imagen> imagen = imagenRepository.findById(id);
        ImagenDTO imagenDTO = null;
        if(imagen.isPresent()){
            imagenDTO = mapper.convertValue(imagen, ImagenDTO.class);
        }
        return imagenDTO;
    }

    @Override
    public ImagenDTO actualizar(ImagenDTO imagenDTO) {
        Imagen imagen = mapper.convertValue(imagenDTO, Imagen.class);
        imagenRepository.save(imagen);
        return imagenDTO;
    }

    @Override
    public void eliminar(Long id) {
        imagenRepository.deleteById(id);
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
}
