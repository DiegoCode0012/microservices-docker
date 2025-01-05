package org.aguzman.springcloud.msvc.usuarios.services;

import org.aguzman.springcloud.msvc.usuarios.dto.ReservaDTO;
import org.aguzman.springcloud.msvc.usuarios.dto.UserDTO;
import org.aguzman.springcloud.msvc.usuarios.dto.UserDTOSimple;
import org.aguzman.springcloud.msvc.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UserDTOSimple> listar();
    UserDTOSimple porId(Long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
    UserDTO listarReservasPorUsuario(Long id);
}
