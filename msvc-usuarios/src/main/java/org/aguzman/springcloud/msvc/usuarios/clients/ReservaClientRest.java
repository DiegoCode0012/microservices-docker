package org.aguzman.springcloud.msvc.usuarios.clients;

import org.aguzman.springcloud.msvc.usuarios.dto.ReservaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="msvc-reservas",url = "msvc-reservas:8002")
public interface ReservaClientRest {
    @GetMapping("/reservasByUser/{id}")
    List<ReservaDTO> listarReservasPorUsuario(@PathVariable Long id);
}
