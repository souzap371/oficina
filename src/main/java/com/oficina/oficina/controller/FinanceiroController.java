package com.oficina.oficina.controller;

import com.oficina.oficina.model.Financeiro;
import com.oficina.oficina.service.FinanceiroService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/financeiro")
@AllArgsConstructor
public class FinanceiroController {

    private final FinanceiroService financeiroService;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("transacoes", financeiroService.listarTodos());
        model.addAttribute("totalReceita", financeiroService.calcularTotalReceita());
        model.addAttribute("totalDespesa", financeiroService.calcularTotalDespesa());
        model.addAttribute("valorLiquido", financeiroService.calcularValorLiquido()); // Adicionando valor líquido
        return "listarFinan";
    }
    

    
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("financeiro", new Financeiro());
        return "formFinan";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Financeiro financeiro) {
        if (financeiro.getDescricao() == null || financeiro.getDescricao().isEmpty()) {
            return "redirect:/financeiro/novo?erro=descricao_invalida";
        }
        financeiroService.salvar(financeiro);
        return "redirect:/financeiro/listar";
    }

    // @GetMapping("/excluir/{id}")
    // public String excluir(@PathVariable Long id) {
    //     if (!financeiroService.existePorId(id)) {
    //         return "redirect:/financeiro/listar?erro=nao_encontrado";
    //     }
    //     financeiroService.excluir(id);
    //     return "redirect:/financeiro/listar";
    // }
    //   @GetMapping("excluir/{id}")
    //     public ResponseEntity<String> excluir(@PathVariable Long id) {
    //         if (!financeiroService.existePorId(id)) {
    //             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transação não encontrada");
    //         }
    //         financeiroService.excluir(id);
    //         return ResponseEntity.ok("redirect:/financeiro/listar");
    // }

    @GetMapping("excluir/{id}")
public String excluir(@PathVariable Long id) {
    if (!financeiroService.existePorId(id)) {
        return "erro"; // Retorne uma página de erro se necessário
    }
    financeiroService.excluir(id);
    return "redirect:/financeiro/listar";
}

}
