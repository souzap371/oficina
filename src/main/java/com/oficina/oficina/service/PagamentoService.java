package com.oficina.oficina.service;

import org.springframework.stereotype.Service;

import com.oficina.oficina.model.Pagamento;
import com.oficina.oficina.repository.PagamentoRepository;

import java.util.List;

@Service
public class PagamentoService {
    private final PagamentoRepository repository;

    public PagamentoService(PagamentoRepository repository) {
        this.repository = repository;
    }

    public Pagamento registrarPagamento(Pagamento pagamento) {
        return repository.save(pagamento);
    }

    public List<Pagamento> listarTodos() {
        return repository.findAll();
    }

    public Pagamento buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pagamento não encontrado!"));
    }
}

