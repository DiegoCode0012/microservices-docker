package org.aguzman.springcloud.msvc.reservas.services;

import org.aguzman.springcloud.msvc.reservas.dto.ReservaDTO;
import org.aguzman.springcloud.msvc.reservas.models.entity.Reserva;

import java.util.List;

public interface ReservaService {
    public Reserva save(Reserva reserva);
    public Reserva findReservaById(Long id);
    public List<ReservaDTO> findAll();
    public void deleteReserva(Reserva reserva);
}
