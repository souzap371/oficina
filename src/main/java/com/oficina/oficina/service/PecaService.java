package com.oficina.oficina.service;

import org.springframework.stereotype.Service;

import com.oficina.oficina.model.Peca;
import com.oficina.oficina.repository.PecaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class PecaService {

    private final PecaRepository repository;

    public PecaService(PecaRepository repository) {
        this.repository = repository;
    }

    // Adicionar uma nova peça ou atualizar uma existente
    public Peca registrarEntrada(Peca peca, int quantidade) {
        Peca existente = repository.findByNome(peca.getNome());
        if (existente != null) {
            existente.setQuantidade(existente.getQuantidade() + quantidade);
            return repository.save(existente);
        }
        peca.setQuantidade(quantidade);
        return repository.save(peca);
    }

    // Registrar a saída de peças
    public Peca registrarSaida(Long id, int quantidade) {
        Peca peca = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada!"));
        if (peca.getQuantidade() < quantidade) {
            throw new RuntimeException("Quantidade insuficiente no estoque!");
        }
        peca.setQuantidade(peca.getQuantidade() - quantidade);
        return repository.save(peca);
    }

    // Listar todas as peças
    public List<Peca> listarTodas() {
        return repository.findAll();
    }

    // Verificar peças em falta
    public List<Peca> verificarPecasEmFalta() {
        return repository.findByQuantidadeLessThan(5); // Notifica peças com menos de 5 unidades
    }

    // Verificar peças com validade próxima
    public List<Peca> verificarValidadeProxima() {
        LocalDate hoje = LocalDate.now();
        return repository.findByDataValidadeBefore(hoje.plusDays(30)); // Peças com validade menor que 30 dias
    }
}
