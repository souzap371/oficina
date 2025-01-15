package com.oficina.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oficina.oficina.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}

