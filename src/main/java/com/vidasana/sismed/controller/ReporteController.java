package com.vidasana.sismed.controller;

import com.vidasana.sismed.repository.AtencionRepository;
import com.vidasana.sismed.repository.CitaRepository;
import com.vidasana.sismed.repository.MedicoRepository;
import com.vidasana.sismed.repository.PacienteRepository;
import com.vidasana.sismed.repository.PagoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Controller
public class ReporteController {

    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final CitaRepository citaRepository;
    private final AtencionRepository atencionRepository;
    private final PagoRepository pagoRepository;

    public ReporteController(
            PacienteRepository pacienteRepository,
            MedicoRepository medicoRepository,
            CitaRepository citaRepository,
            AtencionRepository atencionRepository,
            PagoRepository pagoRepository) {

        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.citaRepository = citaRepository;
        this.atencionRepository = atencionRepository;
        this.pagoRepository = pagoRepository;
    }

    @GetMapping("/reportes")
    public String reportes(Model model) {

        long pacientes =
                pacienteRepository.count();

        long medicos =
                medicoRepository.count();

        long citas =
                citaRepository.count();

        long atenciones =
                atencionRepository.count();

        BigDecimal ingresos =
                pagoRepository.findAll()
                        .stream()
                        .filter(pago ->
                                "PAGADO".equalsIgnoreCase(
                                        pago.getEstado()))
                        .map(pago -> pago.getMonto())
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );

        model.addAttribute(
                "pacientes",
                pacientes
        );

        model.addAttribute(
                "medicos",
                medicos
        );

        model.addAttribute(
                "citas",
                citas
        );

        model.addAttribute(
                "atenciones",
                atenciones
        );

        model.addAttribute(
                "ingresos",
                ingresos
        );

        return "reportes";
    }
}