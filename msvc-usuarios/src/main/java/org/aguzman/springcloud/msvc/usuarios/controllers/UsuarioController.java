package org.aguzman.springcloud.msvc.usuarios.controllers;


import org.aguzman.springcloud.msvc.usuarios.dto.ReservaDTO;
import org.aguzman.springcloud.msvc.usuarios.dto.UserDTO;
import org.aguzman.springcloud.msvc.usuarios.dto.UserDTOSimple;
import org.aguzman.springcloud.msvc.usuarios.models.entity.Usuario;
import org.aguzman.springcloud.msvc.usuarios.services.UsuarioService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService clienteService;

    @GetMapping("/")
    public List<UserDTOSimple> getAll() {
        return clienteService.listar();
    }

    @GetMapping("/datos/{id}")
    public ResponseEntity<?> getUserMasInfo(@PathVariable Long id) {
            UserDTO lst =null;
        try{
            lst=clienteService.listarReservasPorUsuario(id);
            if (lst==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("mensaje", "Usuario no encontrado: " + id + "en la BBDD"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("mensaje", "Error en la comunicacion de los microservicios: " + e.getMessage()));
        }
        return ResponseEntity.ok().body(lst);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  detalle(@PathVariable Long id) {
        UserDTOSimple h = clienteService.porId(id);
        if (h!=null) {
            return ResponseEntity.ok(h);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("El usuario con el " + id + " no fue encontrado en BBDD.");
    }


    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.guardar(usuario));
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
