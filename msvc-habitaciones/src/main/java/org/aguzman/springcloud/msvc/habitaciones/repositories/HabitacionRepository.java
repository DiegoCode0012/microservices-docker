package org.aguzman.springcloud.msvc.habitaciones.repositories;

import org.aguzman.springcloud.msvc.habitaciones.models.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

}
