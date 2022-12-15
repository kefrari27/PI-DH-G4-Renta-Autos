package com.dh.backend_G4.service;

import com.dh.backend_G4.model.Rol;
import com.dh.backend_G4.model.modelDTO.RolDTO;
import com.dh.backend_G4.repository.IRolRepository;
import com.dh.backend_G4.service.interfaceService.IRolService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RolService implements IRolService {

    private final IRolRepository rolRepository;
    private final ObjectMapper mapper;

    public RolService(IRolRepository rolRepository, ObjectMapper mapper) {
        this.rolRepository = rolRepository;
        this.mapper = mapper;
    }

    @Override
    public RolDTO guardar(RolDTO rolDTO) {
        Rol rol = mapper.convertValue(rolDTO, Rol.class);
        rolRepository.save(rol);
        return rolDTO;
    }

    @Override
    public Set<RolDTO> listar() {
        List<Rol> roles = rolRepository.findAll();
        Set<RolDTO> rolesDTO = new HashSet<>();

        for (Rol rol:roles) {
            rolesDTO.add(mapper.convertValue(rol, RolDTO.class));
        }
        return rolesDTO;
    }

    @Override
    public RolDTO buscar(Long id) {
        Optional<Rol> rol = rolRepository.findById(id);
        RolDTO rolDTO = null;
        if(rol.isPresent()){
            rolDTO = mapper.convertValue(rol, RolDTO.class);
        }
        return rolDTO;
    }

    @Override
    public RolDTO actualizar(RolDTO rolDTO) {
        Rol rol = mapper.convertValue(rolDTO, Rol.class);
        rolRepository.save(rol);
        return rolDTO;
    }

    @Override
    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }

    @Override
    public RolDTO buscarRolPorNombre(String nombre) {
        Rol rol = rolRepository.getRolByNombre(nombre);
        RolDTO rolDTO = null;
        if(rol != null){
            rolDTO = mapper.convertValue(rol, RolDTO.class);
        }
        return rolDTO;
    }
}
