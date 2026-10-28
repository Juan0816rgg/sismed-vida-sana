package com.vidasana.sismed.service;

import com.vidasana.sismed.entity.Medico;
import com.vidasana.sismed.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> buscarPorId(Long id) {
        return medicoRepository.findById(id);
    }

    public Optional<Medico> buscarPorDni(String dni) {
        return medicoRepository.findByDni(dni);
    }

    public Medico guardar(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico actualizar(Medico medico) {
        return medicoRepository.save(medico);
    }

    public void eliminar(Long id) {
        medicoRepository.deleteById(id);
    }

    public boolean existeDni(String dni) {
        return medicoRepository.existsByDni(dni);
    }
}