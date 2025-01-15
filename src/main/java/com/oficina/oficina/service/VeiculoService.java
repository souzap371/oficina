package com.oficina.oficina.service;

import org.springframework.stereotype.Service;

import com.oficina.oficina.model.Veiculo;
import com.oficina.oficina.repository.VeiculoRepository;

import java.util.List;

@Service
public class VeiculoService {
    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public Veiculo cadastrarVeiculo(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public List<Veiculo> listarTodos() {
        return repository.findAll();
    }

    public Veiculo buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));
    }

    public void excluirVeiculo(Long id) {
        repository.deleteById(id);
    }
}
