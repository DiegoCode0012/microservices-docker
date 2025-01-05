package org.aguzman.springcloud.msvc.habitaciones.controllers;


import feign.FeignException;
import org.aguzman.springcloud.msvc.habitaciones.dto.HabitacionDTO;
import org.aguzman.springcloud.msvc.habitaciones.models.entity.Habitacion;
import org.aguzman.springcloud.msvc.habitaciones.services.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HabitacionController {
    @Autowired
    private HabitacionService service;


    @GetMapping("/")
    public ResponseEntity<?> listarHabitaciones() {
        List<HabitacionDTO> x = service.listar();
        return ResponseEntity.ok().body(x);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<?> disponibles() {
        List<HabitacionDTO> x = service.listarHabitacionesDisponibles();
        return ResponseEntity.ok().body(x);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detalleHabitacion(@PathVariable Long id) {
        HabitacionDTO h = service.detalle(id);
        if (h!=null) {
            return ResponseEntity.ok(h);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("La habitacion con el " + id + " no fue encontrado en BBDD.");
    }

    @PostMapping()
    public ResponseEntity<?> crear(@Valid @RequestBody Habitacion habitacion, BindingResult result) {
        Habitacion habitacion1=null;
        if (result.hasErrors()) {
            return validar(result);
        }
        try{
             habitacion1 = service.guardar(habitacion,true);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "No existe el tipo por " +
                            "el id o error en la comunicacion: " + e.getMessage()));
        }

            return ResponseEntity.status(HttpStatus.CREATED).body(habitacion1);

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> cambiarEstadoHabitacion(@PathVariable Long id, boolean estado) {
        Habitacion habitacion=null;
        try{
             habitacion=service.porId(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("mensaje", "Error en la comunicación con el msvc-tipo " + e.getMessage()));
        }

        if (habitacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "No se pudo cambiar el estado de la habitación, la habitación no existe"));
        }
        Habitacion habitacion1 = service.guardar(habitacion,estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(habitacion1);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHabitacion(@PathVariable Long id) {
        service.eliminar(id);
        Habitacion o = service.porId(id);
        if (o!=null) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
