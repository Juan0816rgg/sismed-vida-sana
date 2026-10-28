package com.vidasana.sismed.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "atenciones")
public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cita_id", nullable = false, unique = true)
    @NotNull(message = "La cita es obligatoria")
    private Cita cita;

    @Column(name = "fecha_atencion", nullable = false)
    private LocalDateTime fechaAtencion = LocalDateTime.now();

    @NotBlank(message = "Los síntomas son obligatorios")
    @Size(max = 500, message = "Los síntomas no pueden superar los 500 caracteres")
    @Column(nullable = false, length = 500)
    private String sintomas;

    @Size(max = 1000, message = "Las observaciones no pueden superar los 1000 caracteres")
    @Column(length = 1000)
    private String observaciones;

    @Column(nullable = false, length = 30)
    private String estado = "ATENDIDA";

    public Atencion() {
    }

    public Atencion(
            Cita cita,
            String sintomas,
            String observaciones) {

        this.cita = cita;
        this.sintomas = sintomas;
        this.observaciones = observaciones;
        this.fechaAtencion = LocalDateTime.now();
        this.estado = "ATENDIDA";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public LocalDateTime getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(LocalDateTime fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}