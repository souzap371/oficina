package com.oficina.oficina.controller;

import com.oficina.oficina.model.Cliente;
import com.oficina.oficina.model.OrdemDeServico;
import com.oficina.oficina.repository.ClienteRepository;
import com.oficina.oficina.repository.OrdemDeServicoRepository;
import com.oficina.oficina.service.OrdemDeServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ordens-de-servico")
public class OrdemDeServicoController {

    private final OrdemDeServicoService ordemDeServicoService;
    private final ClienteRepository clienteRepository;
    private final OrdemDeServicoRepository ordemDeServicoRepository;

    @Autowired
    public OrdemDeServicoController(OrdemDeServicoService ordemDeServicoService, 
                                    ClienteRepository clienteRepository,
                                    OrdemDeServicoRepository ordemDeServicoRepository) {
        this.ordemDeServicoService = ordemDeServicoService;
        this.clienteRepository = clienteRepository;
        this.ordemDeServicoRepository = ordemDeServicoRepository;
    }
        @GetMapping("/listar")
        public String listarOrdensServico(Model model) {
        List<OrdemDeServico> ordens = ordemDeServicoService.listarOrdensServico();
        
        // Evita erro caso a lista esteja nula
        if (ordens == null) {
            ordens = List.of(); // Retorna uma lista vazia ao invés de null
        }

        model.addAttribute("ordensServico", ordens);
        return "listarOrdensServico"; // Página de listagem
    }

    @GetMapping("/nova")
    public String novaOrdemDeServico(Model model) {
        model.addAttribute("ordemDeServico", new OrdemDeServico());
        model.addAttribute("clientes", clienteRepository.findAll()); // Adiciona a lista de clientes
        return "novaOrdemServico";
}

@PostMapping("/salvar")
public String salvarOrdemDeServico(@RequestParam("clienteId") Long clienteId, 
                                   @ModelAttribute OrdemDeServico ordemDeServico, 
                                   Model model) {
    try {
        if (clienteId == null || clienteId <= 0) {
            model.addAttribute("erro", "Cliente não encontrado");
            model.addAttribute("clientes", clienteRepository.findAll());
            return "novaOrdemServico"; // Retorna para a página de criação com erro
        }

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        ordemDeServico.setCliente(cliente);
        ordemDeServicoRepository.save(ordemDeServico);

        return "redirect:/ordens-de-servico/listar";

    } catch (IllegalArgumentException e) {
        model.addAttribute("erro", "Erro ao salvar ordem de serviço: " + e.getMessage());
        model.addAttribute("clientes", clienteRepository.findAll());
        return "novaOrdemServico";
    }
}


    @GetMapping("/cancelar")
    public String cancelarCriacao() {
        return "redirect:/ordens-de-servico/listar";
    }



    

@GetMapping("/editar/{id}")
public String editarOrdemDeServico(@PathVariable Long id, Model model) {
    Optional<OrdemDeServico> ordemDeServico = ordemDeServicoService.buscarPorId(id);

    if (ordemDeServico.isPresent()) {
        model.addAttribute("ordemDeServico", ordemDeServico.get());
        model.addAttribute("clientes", clienteRepository.findAll()); // Adiciona lista de clientes para seleção
        return "editarOrdemServico";
    }

    return "redirect:/ordens-de-servico/listar?erro=OrdemNaoEncontrada"; // Adiciona feedback ao usuário
}


@PostMapping("/atualizar")
public String atualizarOrdemDeServico(@RequestParam("clienteId") Long clienteId, 
                                      @ModelAttribute OrdemDeServico ordemDeServico,
                                      Model model) {
    if (clienteId == null || clienteId <= 0) {
        model.addAttribute("erro", "Cliente não encontrado");
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("ordemDeServico", ordemDeServico);
        return "editarOrdemServico";
    }

    Optional<OrdemDeServico> ordemExistente = ordemDeServicoService.buscarPorId(ordemDeServico.getId());
    if (ordemExistente.isEmpty()) {
        return "redirect:/ordens-de-servico/listar?erro=OrdemNaoEncontrada";
    }

    Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    
    ordemDeServico.setCliente(cliente);
    ordemDeServicoService.salvarOrdemDeServico(ordemDeServico);

    return "redirect:/ordens-de-servico/listar";
}


@GetMapping("/detalhes/{id}")
public String detalhesOrdemDeServico(@PathVariable Long id, Model model) {
    Optional<OrdemDeServico> ordemDeServico = ordemDeServicoService.buscarPorId(id);
    if (ordemDeServico.isPresent()) {
        model.addAttribute("ordemDeServico", ordemDeServico.get());
        return "detalhesOrdemServico"; // Página de detalhes
    }
    return "redirect:/ordens-de-servico/listar?erro=OrdemNaoEncontrada";
}

@GetMapping("/excluir/{id}")
public String excluirOrdemDeServico(@PathVariable Long id) {
    Optional<OrdemDeServico> ordem = ordemDeServicoService.buscarPorId(id);
    
    if (ordem.isEmpty()) {
        return "redirect:/ordens-de-servico/listar?erro=OrdemNaoEncontrada";
    }

    ordemDeServicoService.excluirOrdemDeServico(id);
    return "redirect:/ordens-de-servico/listar?sucesso=OrdemExcluida";
}



}
