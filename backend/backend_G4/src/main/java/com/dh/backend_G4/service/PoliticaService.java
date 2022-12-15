package com.dh.backend_G4.service;

import com.dh.backend_G4.model.Politica;
import com.dh.backend_G4.model.modelDTO.PoliticaDTO;
import com.dh.backend_G4.repository.IPoliticaRepository;
import com.dh.backend_G4.service.interfaceService.IPoliticaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PoliticaService implements IPoliticaService {

    private final IPoliticaRepository politicaRepository;
    private final ObjectMapper mapper;

    public PoliticaService(IPoliticaRepository politicaRepository, ObjectMapper mapper) {
        this.politicaRepository = politicaRepository;
        this.mapper = mapper;
    }

    @Override
    public PoliticaDTO guardar(PoliticaDTO politicaDTO) {
        Politica politica = mapper.convertValue(politicaDTO, Politica.class);
        politicaRepository.save(politica);
        return politicaDTO;
    }

    @Override
    public Set<PoliticaDTO> listar() {
        List<Politica> politicas = politicaRepository.findAll();
        Set<PoliticaDTO> politicaDTOS = new HashSet<>();

        for (Politica politica:politicas) {
            politicaDTOS.add(mapper.convertValue(politica, PoliticaDTO.class));
        }
        return politicaDTOS;
    }

    @Override
    public PoliticaDTO buscar(Long id) {
        Optional<Politica> politica = politicaRepository.findById(id);
        PoliticaDTO politicaDTO = null;
        if(politica.isPresent()){
            politicaDTO = mapper.convertValue(politica, PoliticaDTO.class);
        }
        return politicaDTO;
    }

    @Override
    public PoliticaDTO actualizar(PoliticaDTO politicaDTO) {
        Politica politica = mapper.convertValue(politicaDTO, Politica.class);
        politicaRepository.save(politica);
        return politicaDTO;
    }

    @Override
    public void eliminar(Long id) {
        politicaRepository.deleteById(id);
    }
}
