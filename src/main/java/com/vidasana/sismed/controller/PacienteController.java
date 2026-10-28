package com.vidasana.sismed.controller;

import com.vidasana.sismed.entity.Paciente;
import com.vidasana.sismed.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<Paciente> listarTodos() {
        return pacienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(
            @Valid @RequestBody Paciente paciente) {

        if (pacienteService.existeDni(paciente.getDni())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(
                pacienteService.guardar(paciente)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Paciente paciente) {

        return pacienteService.buscarPorId(id)
                .map(pacienteExistente -> {

                    paciente.setId(id);

                    return ResponseEntity.ok(
                            pacienteService.actualizar(paciente)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (pacienteService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        pacienteService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}