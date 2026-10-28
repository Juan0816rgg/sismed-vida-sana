package com.vidasana.sismed.service;

import com.vidasana.sismed.entity.Diagnostico;
import com.vidasana.sismed.repository.DiagnosticoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticoService {

    private final DiagnosticoRepository diagnosticoRepository;

    public DiagnosticoService(
            DiagnosticoRepository diagnosticoRepository) {

        this.diagnosticoRepository = diagnosticoRepository;
    }

    public List<Diagnostico> listarTodos() {
        return diagnosticoRepository.findAll();
    }

    public Optional<Diagnostico> buscarPorId(Long id) {
        return diagnosticoRepository.findById(id);
    }

    public List<Diagnostico> listarPorAtencion(Long atencionId) {
        return diagnosticoRepository.findByAtencionId(atencionId);
    }

    public Diagnostico guardar(Diagnostico diagnostico) {
        return diagnosticoRepository.save(diagnostico);
    }

    public Diagnostico actualizar(Diagnostico diagnostico) {
        return diagnosticoRepository.save(diagnostico);
    }

    public void eliminar(Long id) {
        diagnosticoRepository.deleteById(id);
    }
}