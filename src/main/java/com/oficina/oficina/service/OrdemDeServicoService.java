package com.oficina.oficina.service;

import com.oficina.oficina.model.OrdemDeServico;
import com.oficina.oficina.repository.OrdemDeServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrdemDeServicoService {

    //@Autowired
    // private OrdemDeServicoRepository ordemDeServicoRepository;
    private final OrdemDeServicoRepository ordemDeServicoRepository;

    public OrdemDeServicoService(OrdemDeServicoRepository ordemDeServicoRepository) {
        this.ordemDeServicoRepository = ordemDeServicoRepository;
    }

    public List<OrdemDeServico> listarOrdensServico() {
        return ordemDeServicoRepository.findAll();
    }

    public Optional<OrdemDeServico> buscarPorId(Long id) {
        return ordemDeServicoRepository.findById(id);
    }

    public OrdemDeServico salvarOrdemDeServico(OrdemDeServico ordemDeServico) {
        if (ordemDeServico == null || ordemDeServico.getDescricao() == null || ordemDeServico.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("Descrição da ordem de serviço não pode ser vazia");
        }
        return ordemDeServicoRepository.save(ordemDeServico);
    }
    

    public void excluirOrdemDeServico(Long id) {
        ordemDeServicoRepository.deleteById(id);
    }
}