package org.aguzman.springcloud.msvc.habitaciones.services;

import org.aguzman.springcloud.msvc.habitaciones.dto.HabitacionDTO;
import org.aguzman.springcloud.msvc.habitaciones.models.entity.Habitacion;

import java.util.List;

public interface HabitacionService {
    List<HabitacionDTO> listar();
    List<HabitacionDTO> listarHabitacionesDisponibles();
    HabitacionDTO detalle(Long id);
    Habitacion guardar(Habitacion habitacion,boolean estado);
    void eliminar(Long id);
    Habitacion porId(Long id);
}
