package com.dh.backend_G4.service;

import com.dh.backend_G4.model.Caracteristica;
import com.dh.backend_G4.model.modelDTO.CaracteristicaDTO;
import com.dh.backend_G4.repository.ICaracteristicaRepository;
import com.dh.backend_G4.service.interfaceService.ICaracteristicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CaracteristicaService implements ICaracteristicaService {

    private final ICaracteristicaRepository caracteristicaRepository;
    private final ObjectMapper mapper;

    public CaracteristicaService(ICaracteristicaRepository caracteristicaRepository, ObjectMapper mapper) {
        this.caracteristicaRepository = caracteristicaRepository;
        this.mapper = mapper;
    }


    @Override
    public CaracteristicaDTO guardar(CaracteristicaDTO caracteristicaDTO) {
        Caracteristica caracteristica = mapper.convertValue(caracteristicaDTO, Caracteristica.class);
        caracteristicaRepository.save(caracteristica);
        return caracteristicaDTO;
    }

    @Override
    public Set<CaracteristicaDTO> listar() {
        List<Caracteristica> caracteristicas = caracteristicaRepository.findAll();
        Set<CaracteristicaDTO> caracteristicasDTOS = new HashSet<>();

        for(Caracteristica caracteristica:caracteristicas){
            caracteristicasDTOS.add(mapper.convertValue(caracteristica, CaracteristicaDTO.class));
        }
        return caracteristicasDTOS;
    }

    @Override
    public CaracteristicaDTO buscar(Long id) {
        Optional<Caracteristica> caracteristica = caracteristicaRepository.findById(id);
        CaracteristicaDTO caracteristicaDTO = null;
        if(caracteristica.isPresent()){
            caracteristicaDTO = mapper.convertValue(caracteristica, CaracteristicaDTO.class);
        }
        return caracteristicaDTO;
    }

    @Override
    public CaracteristicaDTO actualizar(CaracteristicaDTO caracteristicaDTO) {
        Caracteristica caracteristica = mapper.convertValue(caracteristicaDTO, Caracteristica.class);
        caracteristicaRepository.save(caracteristica);
        return caracteristicaDTO;
    }

    @Override
    public void eliminar(Long id) {
        caracteristicaRepository.deleteById(id);
    }
}
