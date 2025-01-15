package com.oficina.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oficina.oficina.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    // Pode-se adicionar métodos personalizados se necessário, como busca por placa.
}
