package com.oficina.oficina.service;

import org.springframework.stereotype.Service;

import com.oficina.oficina.model.Orcamento;
import com.oficina.oficina.repository.OrcamentoRepository;

import java.util.List;

@Service
public class OrcamentoService {
    private final OrcamentoRepository repository;

    public OrcamentoService(OrcamentoRepository repository) {
        this.repository = repository;
    }

    public Orcamento criarOrcamento(Orcamento orcamento) {
        orcamento.setAprovado(false); // Orçamentos criados começam como não aprovados
        return repository.save(orcamento);
    }

    public List<Orcamento> listarTodos() {
        return repository.findAll();
    }

    public Orcamento buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Orçamento não encontrado!"));
    }

    public Orcamento aprovarOrcamento(Long id) {
        Orcamento orcamento = buscarPorId(id);
        orcamento.setAprovado(true);
        return repository.save(orcamento);
    }
}
