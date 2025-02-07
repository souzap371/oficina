package com.oficina.oficina.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oficina.oficina.model.Financeiro;

@Repository
public interface FinanceiroRepository extends JpaRepository<Financeiro, Long> {
}

