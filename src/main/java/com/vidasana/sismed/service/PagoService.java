package com.vidasana.sismed.service;

import com.vidasana.sismed.entity.Pago;
import com.vidasana.sismed.repository.PagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<Pago> listarTodos() {
        return pagoRepository.findAllByOrderByFechaPagoDesc();
    }

    public Optional<Pago> buscarPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public List<Pago> listarPorPaciente(Long pacienteId) {
        return pagoRepository.findByPacienteIdOrderByFechaPagoDesc(pacienteId);
    }

    public Pago guardar(Pago pago) {
        return pagoRepository.save(pago);
    }

    public Pago actualizar(Pago pago) {
        return pagoRepository.save(pago);
    }

    public void eliminar(Long id) {
        pagoRepository.deleteById(id);
    }
}