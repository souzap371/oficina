package com.oficina.oficina.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oficina.oficina.model.Orcamento;
import com.oficina.oficina.service.OrcamentoService;

import java.util.List;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {
    private final OrcamentoService service;

    public OrcamentoController(OrcamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Orcamento> criarOrcamento(@RequestBody Orcamento orcamento) {
        return ResponseEntity.ok(service.criarOrcamento(orcamento));
    }

    @GetMapping
    public ResponseEntity<List<Orcamento>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orcamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}/aprovar")
    public ResponseEntity<Orcamento> aprovarOrcamento(@PathVariable Long id) {
        return ResponseEntity.ok(service.aprovarOrcamento(id));
    }
}
