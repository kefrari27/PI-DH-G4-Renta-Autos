package com.dh.backend_G4.service;

import com.dh.backend_G4.model.Categoria;
import com.dh.backend_G4.model.modelDTO.CategoriaDTO;
import com.dh.backend_G4.repository.ICategoriaRepository;
import com.dh.backend_G4.service.interfaceService.ICategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoriaService implements ICategoriaService {

    private final ICategoriaRepository categoriaRepository;
    private final ObjectMapper mapper;

    public CategoriaService(ICategoriaRepository categoriaRepository, ObjectMapper mapper) {
        this.categoriaRepository = categoriaRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoriaDTO guardar(CategoriaDTO categoriaDTO) {
        Categoria categoria = mapper.convertValue(categoriaDTO, Categoria.class);
        CategoriaDTO response = null;
        if(categoriaRepository.getCategoriaByTitulo(categoriaDTO.getTitulo()) == null){
            categoriaRepository.save(categoria);
            Categoria categoriaAlmacenada = categoriaRepository.getCategoriaByTitulo(categoriaDTO.getTitulo());
            response = mapper.convertValue(categoriaAlmacenada, CategoriaDTO.class);
        }
        return response;
    }

    @Override
    public Set<CategoriaDTO> listar() {
        List<Categoria> categorias = categoriaRepository.findAll();
        Set<CategoriaDTO> categoriaDTOS = new HashSet<>();

        for(Categoria categoria: categorias){
            categoriaDTOS.add(mapper.convertValue(categoria, CategoriaDTO.class));
        }
        return categoriaDTOS;
    }

    @Override
    public CategoriaDTO buscar(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        CategoriaDTO categoriaDTO = null;
        if(categoria.isPresent()){
            categoriaDTO = mapper.convertValue(categoria, CategoriaDTO.class);
        }
        return categoriaDTO;
    }

    @Override
    public CategoriaDTO actualizar(CategoriaDTO categoriaDTO) {
        Categoria categoria = mapper.convertValue(categoriaDTO, Categoria.class);
        categoriaRepository.save(categoria);
        return categoriaDTO;
    }

    @Override
    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}
