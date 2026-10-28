package com.vidasana.sismed.repository;

import com.vidasana.sismed.entity.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosticoRepository
        extends JpaRepository<Diagnostico, Long> {

    List<Diagnostico> findByAtencionId(Long atencionId);
}