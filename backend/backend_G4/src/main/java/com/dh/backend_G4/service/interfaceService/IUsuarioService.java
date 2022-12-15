package com.dh.backend_G4.service.interfaceService;

import com.dh.backend_G4.model.Usuario;
import com.dh.backend_G4.model.modelDTO.UsuarioDTO;

import java.util.Set;

public interface IUsuarioService {
    public UsuarioDTO guardar (UsuarioDTO usuarioDTO);
    public Set<UsuarioDTO> listar();
    public UsuarioDTO buscar(Long id);
    public UsuarioDTO buscarUsuarioByCorreo(String correo);
    public UsuarioDTO actualizar(UsuarioDTO usuarioDTO);
    public void eliminar(Long id);
    public UsuarioDTO buscarUsuarioCreado(String correo, String nombre, String apellido);
    //public Usuario codificarPassword(Usuario usuario);
    //public void decodificarPassword(String password, Usuario usuario);
}
