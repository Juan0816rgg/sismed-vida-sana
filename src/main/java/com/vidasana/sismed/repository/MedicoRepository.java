package com.vidasana.sismed.repository;

import com.vidasana.sismed.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Optional<Medico> findByDni(String dni);

    boolean existsByDni(String dni);
}