package com.dh.backend_G4.service.interfaceService;

import com.dh.backend_G4.model.modelDTO.CaracteristicaDTO;

import java.util.Set;

public interface ICaracteristicaService {
    public CaracteristicaDTO guardar(CaracteristicaDTO caracteristicaDTO);
    public Set<CaracteristicaDTO> listar();
    public CaracteristicaDTO buscar(Long id);
    public CaracteristicaDTO actualizar(CaracteristicaDTO caracteristicaDTO);
    public void eliminar(Long id);
}
