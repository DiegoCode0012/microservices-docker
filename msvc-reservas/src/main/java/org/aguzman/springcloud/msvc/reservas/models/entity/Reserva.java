package org.aguzman.springcloud.msvc.reservas.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.boot.json.JsonParser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Entity
@Table(name="reservas")
public class Reserva {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="hora_inicio")
    @NotNull(message = "no puede estar vacío")
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "El formato debe ser 'yyyy-MM-dd'"
    )
    private String diaStart;


    @Column(name="hora_final")
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "El formato debe ser 'yyyy-MM-dd'"
    )
    @NotNull(message = "no puede estar vacío")
    private String diaEnd;
    @NotNull
    private Long habitacionId;

    private String estado;
    @NotNull
    private Long clienteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "no puede estar vacío") @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "El formato debe ser 'yyyy-MM-dd'"
    ) String getDiaStart() {
        return diaStart;
    }

    public void setDiaStart(@NotNull(message = "no puede estar vacío") @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "El formato debe ser 'yyyy-MM-dd'"
    ) String diaStart) {
        this.diaStart = diaStart;
    }

    public @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "El formato debe ser 'yyyy-MM-dd'"
    ) @NotNull(message = "no puede estar vacío") String getDiaEnd() {
        return diaEnd;
    }

    public void setDiaEnd(@Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "El formato debe ser 'yyyy-MM-dd'"
    ) @NotNull(message = "no puede estar vacío") String diaEnd) {
        this.diaEnd = diaEnd;
    }

    public @NotNull Long getHabitacionId() {
        return habitacionId;
    }

    public void setHabitacionId(@NotNull Long habitacionId) {
        this.habitacionId = habitacionId;
    }

    public @NotNull Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(@NotNull Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

