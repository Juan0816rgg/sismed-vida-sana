package com.vidasana.sismed.controller;

import com.vidasana.sismed.entity.Atencion;
import com.vidasana.sismed.service.AtencionService;
import com.vidasana.sismed.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atenciones")
public class AtencionController {

    private final AtencionService atencionService;
    private final CitaService citaService;

    public AtencionController(
            AtencionService atencionService,
            CitaService citaService) {

        this.atencionService = atencionService;
        this.citaService = citaService;
    }

    @GetMapping
    public List<Atencion> listarTodos() {
        return atencionService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atencion> buscarPorId(
            @PathVariable Long id) {

        return atencionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(
            @Valid @RequestBody Atencion atencion) {

        if (atencion.getCita() == null
                || atencion.getCita().getId() == null) {

            return ResponseEntity.badRequest()
                    .body("Debe seleccionar una cita");
        }

        Long citaId = atencion.getCita().getId();

        if (atencionService.existePorCita(citaId)) {

            return ResponseEntity.badRequest()
                    .body("Esta cita ya tiene una atención registrada");
        }

        var cita = citaService.buscarPorId(citaId);

        if (cita.isEmpty()) {

            return ResponseEntity.badRequest()
                    .body("La cita no existe");
        }

        atencion.setCita(cita.get());
        atencion.setEstado("ATENDIDA");

        return ResponseEntity.ok(
                atencionService.guardar(atencion)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atencion> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Atencion atencion) {

        return atencionService.buscarPorId(id)
                .map(atencionExistente -> {

                    atencion.setId(id);

                    return ResponseEntity.ok(
                            atencionService.actualizar(atencion)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        if (atencionService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        atencionService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}