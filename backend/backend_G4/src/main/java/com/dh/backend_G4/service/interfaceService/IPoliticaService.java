package com.dh.backend_G4.service.interfaceService;

import com.dh.backend_G4.model.modelDTO.PoliticaDTO;

import java.util.Set;

public interface IPoliticaService {
    public PoliticaDTO guardar(PoliticaDTO politicaDTO);
    public Set<PoliticaDTO> listar();
    public PoliticaDTO buscar(Long id);
    public PoliticaDTO actualizar(PoliticaDTO politicaDTO);
    public void eliminar(Long id);
}
