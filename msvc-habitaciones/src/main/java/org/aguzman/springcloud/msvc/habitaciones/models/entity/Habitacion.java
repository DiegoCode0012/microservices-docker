package org.aguzman.springcloud.msvc.habitaciones.models.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="habitaciones")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    @NotNull
    @Pattern(regexp="^[0-9]+$", message="debe contener solo números")
    @Size(min=3, max=3, message="debe tener exactamente 3 dígitos")
    private String numero;

    private boolean disponible;
    @NotNull
    private Long tipoId;

    public @NotNull @Pattern(regexp = "^[0-9]+$", message = "debe contener solo números") @Size(min = 3, max = 3, message = "debe tener exactamente 3 dígitos") String getNumero() {
        return numero;
    }

    public void setNumero(@NotNull @Pattern(regexp = "^[0-9]+$", message = "debe contener solo números") @Size(min = 3, max = 3, message = "debe tener exactamente 3 dígitos") String numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }


}


