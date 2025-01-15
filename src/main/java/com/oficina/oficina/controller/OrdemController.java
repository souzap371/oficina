package com.oficina.oficina.controller;


import com.oficina.oficina.model.OrdemDeServico;
import com.oficina.oficina.service.OrdemDeServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ordens")
public class OrdemController {

    @Autowired
    private OrdemDeServicoService ordemService;

    // Exibir todas as ordens
    @GetMapping
    public String listarOrdens(Model model) {
        List<OrdemDeServico> ordens = ordemService.listarTodas();
        model.addAttribute("ordens", ordens);
        return "ordens/lista";
    }

    // Formulário para criar uma nova ordem
    @GetMapping("/nova")
    public String novaOrdem(Model model) {
        model.addAttribute("ordem", new OrdemDeServico());
        return "ordens/nova";
    }

    // Salvar uma nova ordem
    @PostMapping
    public String salvarOrdem(@ModelAttribute OrdemDeServico ordem) {
        ordemService.salvar(ordem);
        return "redirect:/ordens";
    }

    // Visualizar os detalhes de uma ordem
    @GetMapping("/{id}")
    public String visualizarOrdem(@PathVariable Long id, Model model) {
        OrdemDeServico ordem = ordemService.buscarPorId(id);
        model.addAttribute("ordem", ordem);
        return "ordens/detalhes";
    }

    // Formulário para editar uma ordem existente
    @GetMapping("/editar/{id}")
    public String editarOrdem(@PathVariable Long id, Model model) {
        OrdemDeServico ordem = ordemService.buscarPorId(id);
        model.addAttribute("ordem", ordem);
        return "ordens/editar";
    }

    // Atualizar uma ordem
    @PostMapping("/editar")
    public String atualizarOrdem(@ModelAttribute OrdemDeServico ordem) {
        ordemService.atualizar(ordem);
        return "redirect:/ordens";
    }

    // Deletar uma ordem
    @GetMapping("/deletar/{id}")
    public String deletarOrdem(@PathVariable Long id) {
        ordemService.deletar(id);
        return "redirect:/ordens";
    }
}
