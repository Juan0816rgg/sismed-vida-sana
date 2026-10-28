package com.vidasana.sismed.repository;

import com.vidasana.sismed.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findAllByOrderByFechaPagoDesc();

    List<Pago> findByPacienteIdOrderByFechaPagoDesc(Long pacienteId);
}