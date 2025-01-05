package org.aguzman.springcloud.msvc.habitaciones.client;

import org.aguzman.springcloud.msvc.habitaciones.dto.TipoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc-tipos",url = "msvc-tipos:8004")
public interface TipoClientRest {
    @GetMapping("/{id}")
    TipoDTO detalleTipo(@PathVariable Long id);
}
