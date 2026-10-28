package com.vidasana.sismed.controller;

import com.vidasana.sismed.entity.Medico;
import com.vidasana.sismed.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public List<Medico> listarTodos() {
        return medicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarPorId(@PathVariable Long id) {

        return medicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Medico> guardar(
            @Valid @RequestBody Medico medico) {

        if (medicoService.existeDni(medico.getDni())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(
                medicoService.guardar(medico)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Medico medico) {

        return medicoService.buscarPorId(id)
                .map(medicoExistente -> {

                    medico.setId(id);

                    return ResponseEntity.ok(
                            medicoService.actualizar(medico)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (medicoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        medicoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}