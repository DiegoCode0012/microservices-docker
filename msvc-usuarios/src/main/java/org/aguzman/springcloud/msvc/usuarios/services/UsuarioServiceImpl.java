package org.aguzman.springcloud.msvc.usuarios.services;

import org.aguzman.springcloud.msvc.usuarios.clients.ReservaClientRest;
import org.aguzman.springcloud.msvc.usuarios.dto.ReservaDTO;
import org.aguzman.springcloud.msvc.usuarios.dto.UserDTO;
import org.aguzman.springcloud.msvc.usuarios.dto.UserDTOSimple;
import org.aguzman.springcloud.msvc.usuarios.models.entity.Usuario;
import org.aguzman.springcloud.msvc.usuarios.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private ReservaClientRest reservaClientRest;

    @Override
    @Transactional(readOnly = true)
    public List<UserDTOSimple> listar() {
        List<Usuario> usuarios= repository.findAll();
        List<UserDTOSimple> usuariosDTO = new ArrayList<>();
        for(Usuario usuario:usuarios) {
            UserDTOSimple userDTO = new UserDTOSimple();
            userDTO.setId(usuario.getId());
            userDTO.setUsername(usuario.getNombre());
            userDTO.setEmail(usuario.getEmail());
            usuariosDTO.add(userDTO);
        }
        return usuariosDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTOSimple porId(Long id) {
        UserDTOSimple userDTO=null;
        Optional<Usuario> usuario = repository.findById(id);
        if(usuario.isPresent()) {
            userDTO = new UserDTOSimple();
            userDTO.setId(usuario.get().getId());
            userDTO.setUsername(usuario.get().getNombre());
            userDTO.setEmail(usuario.get().getEmail());
            return userDTO;
        }
        return null;
    }

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserDTO listarReservasPorUsuario(Long id) {
        UserDTO userDTO=null;
        Optional<Usuario> userDTOSimple=repository.findById(id);
        if (userDTOSimple.isPresent()){
            userDTO = new UserDTO();
            userDTO.setId(userDTOSimple.get().getId());
            userDTO.setUsername(userDTOSimple.get().getNombre());
            userDTO.setEmail(userDTOSimple.get().getEmail());
            reservaClientRest.listarReservasPorUsuario(id).forEach(userDTO::add);
            return userDTO;
        }
        return null;
    }

}
