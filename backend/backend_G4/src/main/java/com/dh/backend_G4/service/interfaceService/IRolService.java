package com.dh.backend_G4.service.interfaceService;

import com.dh.backend_G4.model.modelDTO.RolDTO;

import java.util.Set;

public interface IRolService {
    public RolDTO guardar (RolDTO rolDTO);
    public Set<RolDTO> listar();
    public RolDTO buscar(Long id);
    public RolDTO actualizar(RolDTO rolDTO);
    public void eliminar(Long id);
    public RolDTO buscarRolPorNombre(String nombre);
}
