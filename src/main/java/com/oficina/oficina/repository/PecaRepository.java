package com.oficina.oficina.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oficina.oficina.model.Peca;

public interface PecaRepository extends JpaRepository<Peca, Long> {
     // Método para buscar peça por nome
     Peca findByNome(String nome);

     // Método para buscar peças com quantidade menor que um valor
    List<Peca> findByQuantidadeLessThan(int quantidade);

    // Método para buscar peças com data de validade anterior a uma data
    List<Peca> findByDataValidadeBefore(LocalDate data);
     
}

