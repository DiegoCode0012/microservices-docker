package org.aguzman.springcloud.msvc.reservas.clients;

import org.aguzman.springcloud.msvc.reservas.dto.HabitacionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="msvc-habitaciones",url = "msvc-habitaciones:8003")
public interface HabitacionClientRest {
    @GetMapping("/{id}")
    HabitacionDTO detalleHabitacion(@PathVariable Long id);

    @PutMapping("/{id}")
    void cambiarEstadoHabitacion(@PathVariable Long id, @RequestParam boolean estado);
}
