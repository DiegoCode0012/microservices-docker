package org.aguzman.springcloud.msvc.reservas.repositories;

import org.aguzman.springcloud.msvc.reservas.models.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
