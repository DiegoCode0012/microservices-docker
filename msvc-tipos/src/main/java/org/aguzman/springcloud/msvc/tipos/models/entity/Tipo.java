package org.aguzman.springcloud.msvc.tipos.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "tipos")
public class Tipo {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double precio;

    @NotNull
    @Pattern(regexp = "Estandar|Doble|Suite|Familiar", message = "debe ser ESTANDAR,DOBLE,SUITE O FAMILIAR")
    @Column(unique = true)
    private String descripcion;

    public double PrecioHabitacion(String x) {
        double precio = 0;
        if(x.equalsIgnoreCase("Estandar")) {
            precio=50.0;
        }else if (x.equalsIgnoreCase("Doble")) {
            precio=100.0;
        }else if (x.equalsIgnoreCase("Suite")) {
            precio=400.0;
        }else if(x.equalsIgnoreCase("Familiar")){
            precio=250.0;
        }
        return precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public @NotNull @Pattern(regexp = "Estandar|Doble|Suite|Familiar", message = "debe ser ESTANDAR,DOBLE,SUITE O FAMILIAR") String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotNull @Pattern(regexp = "Estandar|Doble|Suite|Familiar", message = "debe ser ESTANDAR,DOBLE,SUITE O FAMILIAR") String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Tipo{" +
                "id=" + id +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
