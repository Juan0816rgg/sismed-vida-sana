package com.vidasana.sismed.service;

import com.vidasana.sismed.entity.Cita;
import com.vidasana.sismed.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<Cita> listarTodos() {
        return citaRepository.findAllByOrderByFechaHoraAsc();
    }

    public Optional<Cita> buscarPorId(Long id) {
        return citaRepository.findById(id);
    }

    public Cita guardar(Cita cita) {
        return citaRepository.save(cita);
    }

    public Cita actualizar(Cita cita) {
        return citaRepository.save(cita);
    }

    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }

    public List<Cita> listarPorEstado(String estado) {
        return citaRepository.findByEstadoOrderByFechaHoraAsc(estado);
    }
}