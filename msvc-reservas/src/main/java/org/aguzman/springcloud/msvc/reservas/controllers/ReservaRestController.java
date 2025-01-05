package org.aguzman.springcloud.msvc.reservas.controllers;

import org.aguzman.springcloud.msvc.reservas.dto.ReservaDTO;
import org.aguzman.springcloud.msvc.reservas.models.entity.Reserva;
import org.aguzman.springcloud.msvc.reservas.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ReservaRestController {
    @Autowired
    private ReservaService service;

    @GetMapping("/")
    public List<ReservaDTO> getAll() {
        return service.findAll();
    }
    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody Reserva reserva, BindingResult result) {
        Reserva reserva1=null;
        if (result.hasErrors()) {
            return validar(result);
        }
        try{
            reserva1 = service.save(reserva);
            if (reserva1==null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("mensaje", "La habitación con ID"+reserva.getHabitacionId()+"no esta disponible"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "No existe el usuario o la habitacion por " +
                            "el id o error en la comunicacion: " + e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(reserva1);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Reserva o = service.findReservaById(id);
        if (o!=null) {
            try{
                service.deleteReserva(o);
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("mensaje",  e.getMessage()));
            }
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping("/reservasByUser/{id}")
    public ResponseEntity<?> listarReservasPorUsuario(@PathVariable Long id) {
        List<ReservaDTO> reservasPorUsuario=null;
        try{
             reservasPorUsuario = service.findAll().stream()
                    .filter(r -> r.getUserDTO().getId().equals(id))
                    .collect(Collectors.toList());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "Error en la comunicacion de los microservicios: " + e.getMessage()));
        }
        return ResponseEntity.ok(reservasPorUsuario);
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
