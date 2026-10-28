package com.vidasana.sismed.service;

import com.vidasana.sismed.entity.Paciente;
import com.vidasana.sismed.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Optional<Paciente> buscarPorDni(String dni) {
        return pacienteRepository.findByDni(dni);
    }

    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente actualizar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }

    public boolean existeDni(String dni) {
        return pacienteRepository.existsByDni(dni);
    }
}