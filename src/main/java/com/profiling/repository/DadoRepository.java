package com.profiling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.profiling.model.DadosGrandes;

public interface DadoRepository extends JpaRepository<DadosGrandes, Long> {

}

