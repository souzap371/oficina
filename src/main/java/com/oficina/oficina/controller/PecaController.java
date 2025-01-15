package com.oficina.oficina.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oficina.oficina.model.Peca;
import com.oficina.oficina.service.PecaService;

import java.util.List;

@RestController
@RequestMapping("/pecas")
public class PecaController {

    private final PecaService service;

    public PecaController(PecaService service) {
        this.service = service;
    }

    @PostMapping("/entrada")
    public ResponseEntity<Peca> registrarEntrada(@RequestBody Peca peca, @RequestParam int quantidade) {
        return ResponseEntity.ok(service.registrarEntrada(peca, quantidade));
    }

    @PutMapping("/saida/{id}")
    public ResponseEntity<Peca> registrarSaida(@PathVariable Long id, @RequestParam int quantidade) {
        return ResponseEntity.ok(service.registrarSaida(id, quantidade));
    }

    @GetMapping
    public ResponseEntity<List<Peca>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/faltando")
    public ResponseEntity<List<Peca>> verificarPecasEmFalta() {
        return ResponseEntity.ok(service.verificarPecasEmFalta());
    }

    @GetMapping("/validade-proxima")
    public ResponseEntity<List<Peca>> verificarValidadeProxima() {
        return ResponseEntity.ok(service.verificarValidadeProxima());
    }
}
