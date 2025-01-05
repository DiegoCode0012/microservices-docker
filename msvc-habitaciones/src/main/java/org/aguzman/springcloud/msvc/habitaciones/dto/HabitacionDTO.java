package org.aguzman.springcloud.msvc.habitaciones.dto;



public class HabitacionDTO {

    private Long id;

    private String numero;

    private boolean disponible;
    private TipoDTO tipoDTO;

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoDTO getTipoDTO() {
        return tipoDTO;
    }

    public void setTipoDTO(TipoDTO tipoDTO) {
        this.tipoDTO = tipoDTO;
    }
}
