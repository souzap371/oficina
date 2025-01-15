package com.oficina.oficina.service;


import com.oficina.oficina.model.Estoque;
import com.oficina.oficina.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    // Listar todos os itens no estoque
    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }

    // Salvar um novo item no estoque
    public Estoque salvar(Estoque item) {
        return estoqueRepository.save(item);
    }

    // Atualizar um item no estoque
    public Estoque atualizar(Estoque item) {
        return estoqueRepository.save(item);
    }

    // Buscar um item por ID
    public Estoque buscarPorId(Long id) {
        Optional<Estoque> item = estoqueRepository.findById(id);
        return item.orElseThrow(() -> new RuntimeException("Item não encontrado!"));
    }

    // Deletar um item do estoque
    public void deletar(Long id) {
        estoqueRepository.deleteById(id);
    }
}
