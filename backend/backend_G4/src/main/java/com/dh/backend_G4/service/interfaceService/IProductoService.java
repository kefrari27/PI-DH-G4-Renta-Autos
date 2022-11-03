package com.dh.backend_G4.service.interfaceService;

import com.dh.backend_G4.model.modelDTO.ProductoDTO;

import java.util.Set;


public interface IProductoService {
    public ProductoDTO guardar(ProductoDTO productoDTO);
    public Set<ProductoDTO> listar();
    public ProductoDTO buscar(Long id);
    public ProductoDTO actualizar(ProductoDTO productoDTO);
    public void eliminar(Long id);
}
