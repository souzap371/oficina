package com.oficina.oficina.service;


import com.oficina.oficina.model.Cliente;
import com.oficina.oficina.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        // Procura pelo cliente com base no ID
        Optional<Cliente> cliente = clienteRepository.findById(id);

        // Caso não encontre, lança uma exceção
        return cliente.orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
    }

    public void atualizar(Cliente cliente) {
        // Verifica se o cliente existe antes de atualizar
        if (!clienteRepository.existsById(cliente.getId())) {
            throw new RuntimeException("Cliente não encontrado com ID: " + cliente.getId());
        }
        clienteRepository.save(cliente); // Atualiza os dados
    }

    public void deletar(Long id) {
        // Verifica se o cliente existe antes de deletar
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado com ID: " + id);
        }
        clienteRepository.deleteById(id); // Deleta o cliente
    }
    
}
