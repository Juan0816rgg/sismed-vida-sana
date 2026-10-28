package com.vidasana.sismed.service;

import com.vidasana.sismed.entity.Atencion;
import com.vidasana.sismed.repository.AtencionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtencionService {

    private final AtencionRepository atencionRepository;

    public AtencionService(AtencionRepository atencionRepository) {
        this.atencionRepository = atencionRepository;
    }

    public List<Atencion> listarTodos() {
        return atencionRepository.findAll();
    }

    public Optional<Atencion> buscarPorId(Long id) {
        return atencionRepository.findById(id);
    }

    public Optional<Atencion> buscarPorCita(Long citaId) {
        return atencionRepository.findByCitaId(citaId);
    }

    public Atencion guardar(Atencion atencion) {
        return atencionRepository.save(atencion);
    }

    public Atencion actualizar(Atencion atencion) {
        return atencionRepository.save(atencion);
    }

    public void eliminar(Long id) {
        atencionRepository.deleteById(id);
    }

    public boolean existePorCita(Long citaId) {
        return atencionRepository.existsByCitaId(citaId);
    }
}