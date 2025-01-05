package org.aguzman.springcloud.msvc.habitaciones.services;

import feign.FeignException;
import org.aguzman.springcloud.msvc.habitaciones.client.TipoClientRest;
import org.aguzman.springcloud.msvc.habitaciones.dto.HabitacionDTO;
import org.aguzman.springcloud.msvc.habitaciones.dto.TipoDTO;
import org.aguzman.springcloud.msvc.habitaciones.models.entity.Habitacion;
import org.aguzman.springcloud.msvc.habitaciones.repositories.HabitacionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class HabitacionServiceImpl implements HabitacionService {

    @Autowired
    private HabitacionRepository repository;

    @Autowired
    private TipoClientRest client;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(readOnly = true)
    public List<HabitacionDTO> listar() {
        List<Habitacion> lst =repository.findAll();
        List<HabitacionDTO> lstDTO =new ArrayList<>();
        for (Habitacion habitacion : lst) {
            HabitacionDTO habitacionDTO =new HabitacionDTO();
            habitacionDTO.setNumero(habitacion.getNumero());
            habitacionDTO.setId(habitacion.getId());
            habitacionDTO.setDisponible(habitacion.isDisponible());
            if (habitacion.getTipoId()!=null){
                TipoDTO tipoDTO=client.detalleTipo(habitacion.getTipoId());
                habitacionDTO.setTipoDTO(tipoDTO);
            }
            lstDTO.add(habitacionDTO);
        }
        return  lstDTO;
    }

    @Override
    public List<HabitacionDTO> listarHabitacionesDisponibles() {
        List<Habitacion> lst =repository.findAll().stream().filter(Habitacion::isDisponible).toList();
        List<HabitacionDTO> lstDTO =new ArrayList<>();
        for (Habitacion habitacion : lst) {
            HabitacionDTO habitacionDTO =new HabitacionDTO();
            habitacionDTO.setNumero(habitacion.getNumero());
            habitacionDTO.setId(habitacion.getId());
            habitacionDTO.setDisponible(habitacion.isDisponible());
            if (habitacion.getTipoId()!=null){
                TipoDTO tipoDTO=client.detalleTipo(habitacion.getTipoId());
                habitacionDTO.setTipoDTO(tipoDTO);
            }
            lstDTO.add(habitacionDTO);
        }
        return  lstDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public HabitacionDTO detalle(Long id) {
        HabitacionDTO habitacionDTO=null;
        Habitacion habitacion = repository.findById(id).orElse(null);
        if(habitacion !=null) {
            habitacionDTO= new HabitacionDTO();
            habitacionDTO.setId(habitacion.getId());
            habitacionDTO.setNumero(habitacion.getNumero());
            habitacionDTO.setDisponible(habitacion.isDisponible());
            habitacionDTO.setTipoDTO(client.detalleTipo(habitacion.getTipoId()));
            return habitacionDTO;
        }
        return habitacionDTO;
    }


    @Override
    @Transactional
    public Habitacion guardar(Habitacion habitacion,boolean estado) {
        //CUANDO SE CREA OTRA VEZ UNA HABITACION , LA PONEMOS DISPONIBLE
        if (habitacion.getId()==null){
            habitacion.setDisponible(true);
        }
           habitacion.setDisponible(estado);
        TipoDTO tipoDTO = client.detalleTipo(habitacion.getTipoId());
        habitacion.setTipoId(tipoDTO.getId());
            return  repository.save(habitacion);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Habitacion porId(Long id) {
        return repository.findById(id).orElse(null);
    }




}
