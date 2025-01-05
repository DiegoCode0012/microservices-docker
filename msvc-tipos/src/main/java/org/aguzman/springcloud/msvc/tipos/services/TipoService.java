package org.aguzman.springcloud.msvc.tipos.services;

import org.aguzman.springcloud.msvc.tipos.dto.TipoDTO;
import org.aguzman.springcloud.msvc.tipos.models.entity.Tipo;

import java.util.List;
import java.util.Optional;

public interface TipoService {
    List<Tipo> listar();
    TipoDTO detalle(Long id);
    Tipo guardar(Tipo curso);
    void eliminar(Long id);
    Tipo porId(Long id);
}
