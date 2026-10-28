package com.vidasana.sismed.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "diagnosticos")
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "atencion_id", nullable = false)
    @NotNull(message = "La atención es obligatoria")
    private Atencion atencion;

    @NotBlank(message = "El diagnóstico es obligatorio")
    @Size(max = 500, message = "El diagnóstico no puede superar los 500 caracteres")
    @Column(nullable = false, length = 500)
    private String descripcion;

    @Size(max = 500, message = "El tratamiento no puede superar los 500 caracteres")
    @Column(length = 500)
    private String tratamiento;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    public Diagnostico() {
    }

    public Diagnostico(
            Atencion atencion,
            String descripcion,
            String tratamiento) {

        this.atencion = atencion;
        this.descripcion = descripcion;
        this.tratamiento = tratamiento;
        this.fechaRegistro = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Atencion getAtencion() {
        return atencion;
    }

    public void setAtencion(Atencion atencion) {
        this.atencion = atencion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}