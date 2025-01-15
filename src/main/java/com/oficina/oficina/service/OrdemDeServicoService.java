package com.oficina.oficina.service;

import com.oficina.oficina.model.OrdemDeServico;
import com.oficina.oficina.repository.OrdemDeServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdemDeServicoService {

    @Autowired
    private OrdemDeServicoRepository ordemRepository;

    // Listar todas as ordens de serviço
    public List<OrdemDeServico> listarTodas() {
        return ordemRepository.findAll();
    }

    // Salvar uma nova ordem de serviço
    public OrdemDeServico salvar(OrdemDeServico ordem) {
        return ordemRepository.save(ordem);
    }

    // Buscar uma ordem de serviço por ID
    public OrdemDeServico buscarPorId(Long id) {
        Optional<OrdemDeServico> ordem = ordemRepository.findById(id);
        return ordem.orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada!"));
    }

    // Atualizar uma ordem de serviço existente
    public OrdemDeServico atualizar(OrdemDeServico ordem) {
        if (ordem.getId() == null || !ordemRepository.existsById(ordem.getId())) {
            throw new RuntimeException("Ordem de Serviço não encontrada para atualização!");
        }
        return ordemRepository.save(ordem);
    }

    // Deletar uma ordem de serviço por ID
    public void deletar(Long id) {
        if (!ordemRepository.existsById(id)) {
            throw new RuntimeException("Ordem de Serviço não encontrada para exclusão!");
        }
        ordemRepository.deleteById(id);
    }
}
