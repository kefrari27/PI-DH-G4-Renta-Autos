package com.dh.backend_G4.service.interfaceService;

import com.dh.backend_G4.model.modelDTO.CiudadDTO;

import java.util.Set;

public interface ICiudadService {
    public CiudadDTO guardar(CiudadDTO ciudadDTO);
    public Set<CiudadDTO> listar();
    public CiudadDTO buscar(Long id);
    public CiudadDTO actualizar(CiudadDTO ciudadDTO);
    public void eliminar(Long id);

    public Set<CiudadDTO> listarCiudadesConProductos();
}
