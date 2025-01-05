package org.aguzman.springcloud.msvc.reservas.clients;

import org.aguzman.springcloud.msvc.reservas.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="msvc-usuarios",url = "msvc-usuarios:8001")
public interface UsuarioClientRest {
    @GetMapping("/{id}")
    UserDTO detalle(@PathVariable Long id);

}
