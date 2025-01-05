package org.aguzman.springcloud.msvc.usuarios.dto;

import java.util.Date;

public class ReservaDTO {

    private Long id;

    private String diaStart;

    private String diaEnd;


    private HabitacionDTO HabitacionDTO;

    private String estado;


    public String getDiaStart() {
        return diaStart;
    }

    public void setDiaStart(String diaStart) {
        this.diaStart = diaStart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiaEnd() {
        return diaEnd;
    }

    public void setDiaEnd(String diaEnd) {
        this.diaEnd = diaEnd;
    }

    public org.aguzman.springcloud.msvc.usuarios.dto.HabitacionDTO getHabitacionDTO() {
        return HabitacionDTO;
    }

    public void setHabitacionDTO(org.aguzman.springcloud.msvc.usuarios.dto.HabitacionDTO habitacionDTO) {
        HabitacionDTO = habitacionDTO;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
