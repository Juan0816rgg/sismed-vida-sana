package com.vidasana.sismed.controller;

import com.vidasana.sismed.entity.Pago;
import com.vidasana.sismed.service.CitaService;
import com.vidasana.sismed.service.PacienteService;
import com.vidasana.sismed.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;
    private final PacienteService pacienteService;
    private final CitaService citaService;

    public PagoController(
            PagoService pagoService,
            PacienteService pacienteService,
            CitaService citaService) {

        this.pagoService = pagoService;
        this.pacienteService = pacienteService;
        this.citaService = citaService;
    }

    @GetMapping
    public List<Pago> listarTodos() {
        return pagoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> buscarPorId(
            @PathVariable Long id) {

        return pagoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(
            @Valid @RequestBody Pago pago) {

        if (pago.getPaciente() == null
                || pago.getPaciente().getId() == null) {

            return ResponseEntity.badRequest()
                    .body("Debe seleccionar un paciente");
        }

        var paciente =
                pacienteService.buscarPorId(
                        pago.getPaciente().getId());

        if (paciente.isEmpty()) {

            return ResponseEntity.badRequest()
                    .body("El paciente no existe");
        }

        pago.setPaciente(paciente.get());

        if (pago.getCita() != null
                && pago.getCita().getId() != null) {

            var cita =
                    citaService.buscarPorId(
                            pago.getCita().getId());

            if (cita.isEmpty()) {

                return ResponseEntity.badRequest()
                        .body("La cita no existe");
            }

            pago.setCita(cita.get());
        }

        if (pago.getMetodoPago() == null
                || pago.getMetodoPago().isBlank()) {

            pago.setMetodoPago("EFECTIVO");
        }

        pago.setEstado("PAGADO");

        return ResponseEntity.ok(
                pagoService.guardar(pago)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        if (pagoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        pagoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}