package org.aguzman.springcloud.msvc.reservas.services;

import feign.FeignException;
import org.aguzman.springcloud.msvc.reservas.clients.HabitacionClientRest;
import org.aguzman.springcloud.msvc.reservas.clients.UsuarioClientRest;
import org.aguzman.springcloud.msvc.reservas.dto.HabitacionDTO;
import org.aguzman.springcloud.msvc.reservas.dto.ReservaDTO;
import org.aguzman.springcloud.msvc.reservas.dto.UserDTO;
import org.aguzman.springcloud.msvc.reservas.models.entity.Reserva;
import org.aguzman.springcloud.msvc.reservas.repositories.ReservaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReservaRepository reservaDao;

    @Autowired
    private UsuarioClientRest clientRest;


    @Autowired
    private HabitacionClientRest habitacionRest;


    @Override
    public Reserva save(Reserva reserva) {
        Reserva reserva1=null;
            HabitacionDTO habitacionAEvaluar= habitacionRest.detalleHabitacion(reserva.getHabitacionId());
            UserDTO userDTO=clientRest.detalle(reserva.getClienteId());
            if(habitacionAEvaluar.isDisponible() && userDTO!=null){
                reserva.setEstado("OCUPADO");
                habitacionRest.cambiarEstadoHabitacion(habitacionAEvaluar.getId(),false);
                reserva1 =reservaDao.save(reserva);
            }else{
                return null;
            }
    return  reserva1;
    }


    @Override
    public Reserva findReservaById(Long id) {
        return reservaDao.findById(id).orElse(null);
    }

    @Override
    public List<ReservaDTO> findAll() {
        List<Reserva> reservas = reservaDao.findAll();
        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for (Reserva reserva : reservas) {
            ReservaDTO reservaDTO = new ReservaDTO();
            reservaDTO.setId(reserva.getId());
            reservaDTO.setDiaStart(reserva.getDiaStart());
            reservaDTO.setDiaEnd(reserva.getDiaEnd());
            reservaDTO.setEstado(reserva.getEstado());
            if (reserva.getClienteId() != null) {
                UserDTO clientDTO = clientRest.detalle(reserva.getClienteId());
                reservaDTO.setUserDTO(clientDTO);
            }
            if (reserva.getHabitacionId() != null) {
                HabitacionDTO habitacionDTO = habitacionRest.detalleHabitacion(reserva.getHabitacionId());
                log.info("" +habitacionDTO);
                reservaDTO.setHabitacionDTO(habitacionDTO);
            }
            reservasDTO.add(reservaDTO);
        }
        return reservasDTO;
    }

    @Override
    public void deleteReserva(Reserva reserva) {
        habitacionRest.cambiarEstadoHabitacion(reserva.getHabitacionId(),true);
        reservaDao.deleteById(reserva.getId());
    }
}
