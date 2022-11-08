package com.dh.backend_G4.service.interfaceService;

import com.dh.backend_G4.model.modelDTO.ImagenDTO;

import java.util.Set;

public interface IImagenService {
    public ImagenDTO guardar(ImagenDTO imagenDTO);
    public Set<ImagenDTO> listar();
    public ImagenDTO buscar(Long id);
    public ImagenDTO actualizar(ImagenDTO imagenDTO);
    public void eliminar(Long id);
    public Set<ImagenDTO> listarImagenesByProducto(Long id);
}
