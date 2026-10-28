package com.vidasana.sismed.controller;

import com.vidasana.sismed.entity.Diagnostico;
import com.vidasana.sismed.service.AtencionService;
import com.vidasana.sismed.service.DiagnosticoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosticos")
public class DiagnosticoController {

    private final DiagnosticoService diagnosticoService;
    private final AtencionService atencionService;

    public DiagnosticoController(
            DiagnosticoService diagnosticoService,
            AtencionService atencionService) {

        this.diagnosticoService = diagnosticoService;
        this.atencionService = atencionService;
    }

    @GetMapping
    public List<Diagnostico> listarTodos() {
        return diagnosticoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diagnostico> buscarPorId(
            @PathVariable Long id) {

        return diagnosticoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(
            @Valid @RequestBody Diagnostico diagnostico) {

        if (diagnostico.getAtencion() == null
                || diagnostico.getAtencion().getId() == null) {

            return ResponseEntity.badRequest()
                    .body("Debe seleccionar una atención");
        }

        Long atencionId =
                diagnostico.getAtencion().getId();

        var atencion =
                atencionService.buscarPorId(atencionId);

        if (atencion.isEmpty()) {

            return ResponseEntity.badRequest()
                    .body("La atención no existe");
        }

        diagnostico.setAtencion(atencion.get());

        return ResponseEntity.ok(
                diagnosticoService.guardar(diagnostico)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diagnostico> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Diagnostico diagnostico) {

        return diagnosticoService.buscarPorId(id)
                .map(diagnosticoExistente -> {

                    diagnostico.setId(id);

                    return ResponseEntity.ok(
                            diagnosticoService.actualizar(diagnostico)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        if (diagnosticoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        diagnosticoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}