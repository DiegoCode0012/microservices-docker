package org.aguzman.springcloud.msvc.tipos.controllers;

import org.aguzman.springcloud.msvc.tipos.dto.TipoDTO;
import org.aguzman.springcloud.msvc.tipos.models.entity.Tipo;
import org.aguzman.springcloud.msvc.tipos.services.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class TipoController {

    @Autowired
    private TipoService service;

    @GetMapping("/")
    public ResponseEntity<List<Tipo>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> detalleTipo(@PathVariable Long id) throws InterruptedException{
            TipoDTO h = service.detalle(id);
            if(id.equals(10L)){
                throw new InterruptedException("Error al comunicarse con el microservicio");
            }
            if (h!=null) {
                return ResponseEntity.ok(h);
            }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("message", "El tipo con ID " + id + " no fue encontrado."));
    }
    @PostMapping("")
    public ResponseEntity<?> crear(@Valid @RequestBody Tipo tipo, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        Tipo type = service.guardar(tipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(type);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Tipo tipo, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validar(result);
        }
            Tipo type = service.porId(id);
            type.setDescripcion(tipo.getDescripcion());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(type));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Tipo o = service.porId(id);
        if (o != null) {
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
