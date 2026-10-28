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
public class DashboardController {

    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final CitaRepository citaRepository;
    private final AtencionRepository atencionRepository;
    private final PagoRepository pagoRepository;

    public DashboardController(
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

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        long totalPacientes =
                pacienteRepository.count();

        long totalMedicos =
                medicoRepository.count();

        long totalCitas =
                citaRepository.count();

        long totalAtenciones =
                atencionRepository.count();

        BigDecimal totalPagos =
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
                "totalPacientes",
                totalPacientes
        );

        model.addAttribute(
                "totalMedicos",
                totalMedicos
        );

        model.addAttribute(
                "totalCitas",
                totalCitas
        );

        model.addAttribute(
                "totalAtenciones",
                totalAtenciones
        );

        model.addAttribute(
                "totalPagos",
                totalPagos
        );

        return "dashboard";
    }
}