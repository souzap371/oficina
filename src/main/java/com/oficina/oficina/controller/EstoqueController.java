package com.oficina.oficina.controller;


import com.oficina.oficina.model.Estoque;
import com.oficina.oficina.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    // Exibir todos os itens no estoque
    @GetMapping
    public String listarEstoque(Model model) {
        List<Estoque> itens = estoqueService.listarTodos();
        model.addAttribute("itens", itens);
        return "estoque/lista";
    }

    // Formulário para adicionar um novo item ao estoque
    @GetMapping("/novo")
    public String novoItem(Model model) {
        model.addAttribute("item", new Estoque());
        return "estoque/novo";
    }

    // Salvar um novo item no estoque
    @PostMapping
    public String salvarItem(@ModelAttribute Estoque item) {
        estoqueService.salvar(item);
        return "redirect:/estoque";
    }

    // Atualizar a quantidade de um item no estoque
    @PostMapping("/atualizar")
    public String atualizarItem(@ModelAttribute Estoque item) {
        estoqueService.atualizar(item);
        return "redirect:/estoque";
    }

    // Deletar um item do estoque
    @GetMapping("/deletar/{id}")
    public String deletarItem(@PathVariable Long id) {
        estoqueService.deletar(id);
        return "redirect:/estoque";
    }
}
