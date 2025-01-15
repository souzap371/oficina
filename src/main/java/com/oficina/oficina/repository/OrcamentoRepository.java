package com.oficina.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oficina.oficina.model.Orcamento;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
}
