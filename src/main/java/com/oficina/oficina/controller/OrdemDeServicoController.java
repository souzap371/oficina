// package com.oficina.oficina.controller;


// import com.oficina.oficina.model.Cliente;
// import com.oficina.oficina.model.OrdemDeServico;
// import com.oficina.oficina.service.OrdemDeServicoService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @Controller
// @RequestMapping("/ordens-de-servico")
// public class OrdemDeServicoController {

//     // @Autowired
//     private OrdemDeServicoService ordemDeServicoService;
//     public OrdemDeServicoController(OrdemDeServicoService ordemDeServicoService) {
//         this.ordemDeServicoService = ordemDeServicoService;
//     }

//     @GetMapping("/listar")
//     public String listarOrdensServico(Model model) {
//         model.addAttribute("ordensServico", ordemDeServicoService.listarOrdensServico());
//         return "listarOrdensServico"; // Página de listagem
//     }

//     @GetMapping("/nova")
//     public String novaOrdemDeServico(Model model) {
//         model.addAttribute("ordemDeServico", new OrdemDeServico());
//         return "novaOrdemServico"; // Página para criar nova ordem
//     }

//     @PostMapping("/salvar")
//     public String salvarOrdemDeServico(@Valid @RequestBody OrdemDeServico ordemDeServico) {
//         // Supondo que você receba o ID do cliente na ordem de serviço
//         Long clienteId = ordemDeServico.getClienteId();
//         Cliente cliente = clienteRepository.findById(clienteId)
//                 .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));

//         ordemDeServico.setCliente(cliente);

//         // Salve a ordem de serviço no banco de dados
//         ordemDeServicoRepository.save(ordemDeServico);
//         return "Ordem de Serviço salva com sucesso!";
//     }

//     // @PostMapping("/salvar")
//     // public String salvarOrdemDeServico(@ModelAttribute OrdemDeServico ordemDeServico) {
//     //     ordemDeServicoService.salvarOrdemDeServico(ordemDeServico);
//     //     return "redirect:/ordens-de-servico/listar"; // Redireciona para a lista
//     // }

//     @GetMapping("/editar/{id}")
//     public String editarOrdemDeServico(@PathVariable Long id, Model model) {
//         Optional<OrdemDeServico> ordemDeServico = ordemDeServicoService.buscarPorId(id);
//         if (ordemDeServico.isPresent()) {
//             model.addAttribute("ordemDeServico", ordemDeServico.get());
//             return "editarOrdemDeServico"; // Página para editar
//         }
//         return "redirect:/ordens-de-servico/listar"; // Caso não encontre, redireciona para a lista
//     }

//     @PostMapping("/atualizar")
//     public String atualizarOrdemDeServico(@ModelAttribute OrdemDeServico ordemDeServico) {
//         if (ordemDeServico.getId() == null || !ordemDeServicoService.buscarPorId(ordemDeServico.getId()).isPresent()) {
//             return "redirect:/ordens-de-servico/listar?erro=OrdemNaoEncontrada";
//         }
//         ordemDeServicoService.salvarOrdemDeServico(ordemDeServico);
//         return "redirect:/ordens-de-servico/listar";
//     }


//     @GetMapping("/excluir/{id}")
//     public String excluirOrdemDeServico(@PathVariable Long id) {
//         if (!ordemDeServicoService.buscarPorId(id).isPresent()) {
//             return "redirect:/ordens-de-servico/listar?erro=OrdemNaoEncontrada";
//         }
//         ordemDeServicoService.excluirOrdemDeServico(id);
//         return "redirect:/ordens-de-servico/listar";
//     }

// }
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
        model.addAttribute("ordensServico", ordemDeServicoService.listarOrdensServico());
        return "listarOrdensServico"; // Página de listagem
    }

    @GetMapping("/nova")
    public String novaOrdemDeServico(Model model) {
        model.addAttribute("ordemDeServico", new OrdemDeServico());
        return "novaOrdemServico"; // Página para criar nova ordem
    }

    @PostMapping("/salvar")
    public String salvarOrdemDeServico(@RequestParam("clienteId") Long clienteId, @ModelAttribute OrdemDeServico ordemDeServico) {
        // Busca o cliente pelo id
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    
        // Associa o cliente à ordem de serviço
        ordemDeServico.setCliente(cliente);
    
        // Salve a ordem de serviço no banco de dados
        ordemDeServicoRepository.save(ordemDeServico);
        return "redirect:/ordens-de-servico/listar"; // Redireciona para a lista de ordens de serviço
    }
    

    @GetMapping("/editar/{id}")
    public String editarOrdemDeServico(@PathVariable Long id, Model model) {
        Optional<OrdemDeServico> ordemDeServico = ordemDeServicoService.buscarPorId(id);
        if (ordemDeServico.isPresent()) {
            model.addAttribute("ordemDeServico", ordemDeServico.get());
            return "editarOrdemDeServico"; // Página para editar
        }
        return "redirect:/ordens-de-servico/listar"; // Caso não encontre, redireciona para a lista
    }

    @PostMapping("/atualizar")
    public String atualizarOrdemDeServico(@ModelAttribute OrdemDeServico ordemDeServico) {
        if (ordemDeServico.getId() == null || !ordemDeServicoService.buscarPorId(ordemDeServico.getId()).isPresent()) {
            return "redirect:/ordens-de-servico/listar?erro=OrdemNaoEncontrada";
        }
        ordemDeServicoService.salvarOrdemDeServico(ordemDeServico);
        return "redirect:/ordens-de-servico/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirOrdemDeServico(@PathVariable Long id) {
        if (!ordemDeServicoService.buscarPorId(id).isPresent()) {
            return "redirect:/ordens-de-servico/listar?erro=OrdemNaoEncontrada";
        }
        ordemDeServicoService.excluirOrdemDeServico(id);
        return "redirect:/ordens-de-servico/listar";
    }
}
