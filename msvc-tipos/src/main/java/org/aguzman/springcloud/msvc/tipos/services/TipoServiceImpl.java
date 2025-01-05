package org.aguzman.springcloud.msvc.tipos.services;

import org.aguzman.springcloud.msvc.tipos.dto.TipoDTO;
import org.aguzman.springcloud.msvc.tipos.models.entity.Tipo;
import org.aguzman.springcloud.msvc.tipos.repositories.TipoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@EnableFeignClients
@Service
public class TipoServiceImpl implements TipoService {

    @Autowired
    private TipoRepository repository;
    private final Logger log = LoggerFactory.getLogger(getClass());


    @Override
    @Transactional(readOnly = true)
    public List<Tipo> listar() {
        return  repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public TipoDTO detalle(Long id) {
        TipoDTO tipoDTO = new TipoDTO();
        Tipo tipo = repository.findById(id).orElse(null);
        log.info(""+tipo);
        if(tipo !=null) {
            tipoDTO.setId(tipo.getId());
            tipoDTO.setDescripcion(tipo.getDescripcion());
            tipoDTO.setPrecio(tipo.getPrecio());
            return tipoDTO;
        }
        return null;
    }


    @Override
    @Transactional
    public Tipo guardar(Tipo tipo) {
        Double x=tipo.PrecioHabitacion(tipo.getDescripcion());
        tipo.setPrecio(x);
        return repository.save(tipo);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Tipo porId(Long id) {
        return repository.findById(id).orElse(null);
    }


}
