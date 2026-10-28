package com.vidasana.sismed.controller;

import com.vidasana.sismed.entity.Cita;
import com.vidasana.sismed.service.CitaService;
import com.vidasana.sismed.service.MedicoService;
import com.vidasana.sismed.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;

    public CitaController(
            CitaService citaService,
            PacienteService pacienteService,
            MedicoService medicoService) {

        this.citaService = citaService;
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
    }

    @GetMapping
    public List<Cita> listarTodos() {
        return citaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> buscarPorId(
            @PathVariable Long id) {

        return citaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(
            @Valid @RequestBody Cita cita) {

        if (cita.getPaciente() == null
                || cita.getPaciente().getId() == null) {

            return ResponseEntity.badRequest()
                    .body("Debe seleccionar un paciente");
        }

        if (cita.getMedico() == null
                || cita.getMedico().getId() == null) {

            return ResponseEntity.badRequest()
                    .body("Debe seleccionar un médico");
        }

        var paciente =
                pacienteService.buscarPorId(
                        cita.getPaciente().getId());

        var medico =
                medicoService.buscarPorId(
                        cita.getMedico().getId());

        if (paciente.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("El paciente no existe");
        }

        if (medico.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("El médico no existe");
        }

        cita.setPaciente(paciente.get());
        cita.setMedico(medico.get());

        if (cita.getEstado() == null
                || cita.getEstado().isBlank()) {

            cita.setEstado("PENDIENTE");
        }

        return ResponseEntity.ok(
                citaService.guardar(cita)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Cita cita) {

        return citaService.buscarPorId(id)
                .map(citaExistente -> {

                    cita.setId(id);

                    return ResponseEntity.ok(
                            citaService.actualizar(cita)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        if (citaService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        citaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}