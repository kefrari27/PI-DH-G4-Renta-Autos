package com.dh.backend_G4.service.interfaceService;

import com.dh.backend_G4.exceptions.ResourceNotFoundException;
import com.dh.backend_G4.model.modelDTO.AddCaracteristicaDTO;
import com.dh.backend_G4.model.modelDTO.ImagenDTO;
import com.dh.backend_G4.model.modelDTO.ProductoDTO;

import java.util.Set;

public interface IProductoService {
    public ProductoDTO guardar(ProductoDTO productoDTO);
    public Set<ProductoDTO> listar();
    public ProductoDTO buscar(Long id);
    public ProductoDTO actualizar(ProductoDTO productoDTO);
    public void eliminar(Long id);
    public Set<ImagenDTO> listarImagenesByProducto(Long id);
    public void eliminarImagenesByProducto(Long id);
    public ProductoDTO addCaracteristica(AddCaracteristicaDTO addCaracteristicaDTO) throws ResourceNotFoundException;

    public Set<ProductoDTO>listarProductosByCategoria(Long id);
    public Set<ProductoDTO>listarProductosByCiudad(Long id);
    public Set<ProductoDTO>listarProductosAleatorios(int cantidad);
    public ProductoDTO buscarProductoByTituloAndCategoriaAndCiudad(String titulo, Long categoria_id, Long ciudad_id);


}
