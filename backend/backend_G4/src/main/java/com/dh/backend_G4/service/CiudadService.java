package com.dh.backend_G4.service;

import com.dh.backend_G4.model.Ciudad;
import com.dh.backend_G4.model.modelDTO.CiudadDTO;
import com.dh.backend_G4.repository.ICiudadRepository;
import com.dh.backend_G4.service.interfaceService.ICiudadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CiudadService implements ICiudadService {

    private final ICiudadRepository ciudadRepository;
    private final ObjectMapper mapper;

    public CiudadService(ICiudadRepository ciudadRepository, ObjectMapper mapper) {
        this.ciudadRepository = ciudadRepository;
        this.mapper = mapper;
    }

    @Override
    public CiudadDTO guardar(CiudadDTO ciudadDTO) {
        Ciudad ciudad = mapper.convertValue(ciudadDTO, Ciudad.class);
        ciudadRepository.save(ciudad);
        return ciudadDTO;
    }

    @Override
    public Set<CiudadDTO> listar() {
        List<Ciudad> ciudades = ciudadRepository.findAll();
        Set<CiudadDTO> ciudadesDTOS = new HashSet<>();

        for (Ciudad ciudad:ciudades) {
            ciudadesDTOS.add(mapper.convertValue(ciudad, CiudadDTO.class));
        }
        return ciudadesDTOS;
    }

    @Override
    public CiudadDTO buscar(Long id) {
        Optional<Ciudad> ciudad = ciudadRepository.findById(id);
        CiudadDTO ciudadDTO = null;
        if(ciudad.isPresent()){
            ciudadDTO = mapper.convertValue(ciudad, CiudadDTO.class);
        }
        return ciudadDTO;
    }

    @Override
    public CiudadDTO actualizar(CiudadDTO ciudadDTO) {
        Ciudad ciudad = mapper.convertValue(ciudadDTO, Ciudad.class);
        ciudadRepository.save(ciudad);
        return ciudadDTO;
    }

    @Override
    public void eliminar(Long id) {
        ciudadRepository.deleteById(id);
    }
}
