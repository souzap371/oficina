package com.oficina.oficina.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class OrdemDeServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataCriacao;
    private String descricao;
    private Double valorTotal;

    @OneToMany(mappedBy = "ordemDeServico", cascade = CascadeType.ALL)
    private List<Orcamento> orcamentos;

    // Construtor padrão
    public OrdemDeServico() {
    }

    // Construtor parametrizado
    public OrdemDeServico(LocalDate dataCriacao, String descricao, Double valorTotal) {
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(List<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }
}
