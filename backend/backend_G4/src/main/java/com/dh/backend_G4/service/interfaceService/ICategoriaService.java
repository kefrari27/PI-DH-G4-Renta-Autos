package com.dh.backend_G4.service.interfaceService;

import com.dh.backend_G4.model.modelDTO.CategoriaDTO;

import java.util.Set;

public interface ICategoriaService {
    public CategoriaDTO guardar(CategoriaDTO categoriaDTO);
    public Set<CategoriaDTO> listar();
    public CategoriaDTO buscar(Long id);
    public CategoriaDTO actualizar(CategoriaDTO categoriaDTO);
    public void eliminar(Long id);
}
